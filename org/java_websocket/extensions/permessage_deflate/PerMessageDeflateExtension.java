/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.enums.Opcode
 *  org.java_websocket.exceptions.InvalidDataException
 *  org.java_websocket.exceptions.InvalidFrameException
 *  org.java_websocket.extensions.CompressionExtension
 *  org.java_websocket.extensions.ExtensionRequestData
 *  org.java_websocket.extensions.IExtension
 *  org.java_websocket.framing.ContinuousFrame
 *  org.java_websocket.framing.DataFrame
 *  org.java_websocket.framing.Framedata
 *  org.java_websocket.framing.FramedataImpl1
 */
package org.java_websocket.extensions.permessage_deflate;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.java_websocket.enums.Opcode;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.extensions.CompressionExtension;
import org.java_websocket.extensions.ExtensionRequestData;
import org.java_websocket.extensions.IExtension;
import org.java_websocket.framing.ContinuousFrame;
import org.java_websocket.framing.DataFrame;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.FramedataImpl1;

public class PerMessageDeflateExtension
extends CompressionExtension {
    private Inflater inflater;
    private Deflater deflater;
    private static final int BUFFER_SIZE = 1024;
    private static final int serverMaxWindowBits = 32768;
    private static final String SERVER_NO_CONTEXT_TAKEOVER = "server_no_context_takeover";
    private static final String EXTENSION_REGISTERED_NAME = "permessage-deflate";
    private boolean clientNoContextTakeover = false;
    private static final String SERVER_MAX_WINDOW_BITS = "server_max_window_bits";
    private static final String CLIENT_NO_CONTEXT_TAKEOVER = "client_no_context_takeover";
    private static final byte[] TAIL_BYTES = new byte[]{0, 0, -1, -1};
    private static final String CLIENT_MAX_WINDOW_BITS = "client_max_window_bits";
    private Map<String, String> requestedParameters = new LinkedHashMap<String, String>();
    private int threshold = 1024;
    private static final int clientMaxWindowBits = 32768;
    private boolean serverNoContextTakeover = true;

    public void encodeFrame(Framedata inputFrame) {
        int bytesCompressed;
        if (!(inputFrame instanceof DataFrame)) {
            return;
        }
        byte[] payloadData = inputFrame.getPayloadData().array();
        if (payloadData.length < this.threshold) {
            return;
        }
        if (!(inputFrame instanceof ContinuousFrame)) {
            ((DataFrame)inputFrame).setRSV1(true);
        }
        this.deflater.setInput(payloadData);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while ((bytesCompressed = this.deflater.deflate(buffer, 0, buffer.length, 2)) > 0) {
            output.write(buffer, 0, bytesCompressed);
        }
        byte[] outputBytes = output.toByteArray();
        int outputLength = outputBytes.length;
        if (inputFrame.isFin()) {
            if (PerMessageDeflateExtension.endsWithTail(outputBytes)) {
                outputLength -= TAIL_BYTES.length;
            }
            if (this.serverNoContextTakeover) {
                this.deflater.end();
                this.deflater = new Deflater(-1, true);
            }
        }
        ((FramedataImpl1)inputFrame).setPayload(ByteBuffer.wrap(outputBytes, 0, outputLength));
    }

    public void setInflater(Inflater inflater) {
        this.inflater = inflater;
    }

    public boolean acceptProvidedExtensionAsClient(String inputExtension) {
        String[] requestedExtensions;
        String[] stringArray = requestedExtensions = inputExtension.split(",");
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String extension = stringArray[n2];
            ExtensionRequestData extensionData = ExtensionRequestData.parseExtensionRequest((String)extension);
            if (EXTENSION_REGISTERED_NAME.equalsIgnoreCase(extensionData.getExtensionName())) {
                Map headers = extensionData.getExtensionParameters();
                return true;
            }
            ++n2;
        }
        return false;
    }

    public IExtension copyInstance() {
        return new PerMessageDeflateExtension();
    }

    public String getProvidedExtensionAsClient() {
        this.requestedParameters.put(CLIENT_NO_CONTEXT_TAKEOVER, "");
        this.requestedParameters.put(SERVER_NO_CONTEXT_TAKEOVER, "");
        return "permessage-deflate; server_no_context_takeover; client_no_context_takeover";
    }

    public void setServerNoContextTakeover(boolean serverNoContextTakeover) {
        this.serverNoContextTakeover = serverNoContextTakeover;
    }

    public Inflater getInflater() {
        return this.inflater;
    }

    public void setClientNoContextTakeover(boolean clientNoContextTakeover) {
        this.clientNoContextTakeover = clientNoContextTakeover;
    }

    public boolean isServerNoContextTakeover() {
        return this.serverNoContextTakeover;
    }

    public String getProvidedExtensionAsServer() {
        return "permessage-deflate; server_no_context_takeover" + (this.clientNoContextTakeover ? "; client_no_context_takeover" : "");
    }

    public void setDeflater(Deflater deflater) {
        this.deflater = deflater;
    }

    public int getThreshold() {
        return this.threshold;
    }

    public void decodeFrame(Framedata inputFrame) throws InvalidDataException {
        if (!(inputFrame instanceof DataFrame)) {
            return;
        }
        if (!inputFrame.isRSV1() && inputFrame.getOpcode() != Opcode.CONTINUOUS) {
            return;
        }
        if (inputFrame.getOpcode() == Opcode.CONTINUOUS && inputFrame.isRSV1()) {
            throw new InvalidDataException(1008, "RSV1 bit can only be set for the first frame.");
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            this.decompress(inputFrame.getPayloadData().array(), output);
            if (this.inflater.getRemaining() > 0) {
                this.inflater = new Inflater(true);
                this.decompress(inputFrame.getPayloadData().array(), output);
            }
            if (inputFrame.isFin()) {
                this.decompress(TAIL_BYTES, output);
                if (this.clientNoContextTakeover) {
                    this.inflater = new Inflater(true);
                }
            }
        }
        catch (DataFormatException e) {
            throw new InvalidDataException(1008, e.getMessage());
        }
        ((FramedataImpl1)inputFrame).setPayload(ByteBuffer.wrap(output.toByteArray(), 0, output.size()));
    }

    public String toString() {
        return "PerMessageDeflateExtension";
    }

    private static boolean endsWithTail(byte[] data) {
        if (data.length < 4) {
            return false;
        }
        int length = data.length;
        int i = 0;
        while (i < TAIL_BYTES.length) {
            if (TAIL_BYTES[i] != data[length - TAIL_BYTES.length + i]) {
                return false;
            }
            ++i;
        }
        return true;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public boolean acceptProvidedExtensionAsServer(String inputExtension) {
        String[] requestedExtensions;
        String[] stringArray = requestedExtensions = inputExtension.split(",");
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String extension = stringArray[n2];
            ExtensionRequestData extensionData = ExtensionRequestData.parseExtensionRequest((String)extension);
            if (EXTENSION_REGISTERED_NAME.equalsIgnoreCase(extensionData.getExtensionName())) {
                Map headers = extensionData.getExtensionParameters();
                this.requestedParameters.putAll(headers);
                if (!this.requestedParameters.containsKey(CLIENT_NO_CONTEXT_TAKEOVER)) return true;
                this.clientNoContextTakeover = true;
                return true;
            }
            ++n2;
        }
        return false;
    }

    public PerMessageDeflateExtension() {
        this.inflater = new Inflater(true);
        this.deflater = new Deflater(-1, true);
    }

    private void decompress(byte[] data, ByteArrayOutputStream outputBuffer) throws DataFormatException {
        int bytesInflated;
        this.inflater.setInput(data);
        byte[] buffer = new byte[1024];
        while ((bytesInflated = this.inflater.inflate(buffer)) > 0) {
            outputBuffer.write(buffer, 0, bytesInflated);
        }
    }

    public Deflater getDeflater() {
        return this.deflater;
    }

    public boolean isClientNoContextTakeover() {
        return this.clientNoContextTakeover;
    }

    public void isFrameValid(Framedata inputFrame) throws InvalidDataException {
        if (inputFrame instanceof ContinuousFrame) {
            if (inputFrame.isRSV1()) throw new InvalidFrameException("bad rsv RSV1: " + inputFrame.isRSV1() + " RSV2: " + inputFrame.isRSV2() + " RSV3: " + inputFrame.isRSV3());
            if (inputFrame.isRSV2()) throw new InvalidFrameException("bad rsv RSV1: " + inputFrame.isRSV1() + " RSV2: " + inputFrame.isRSV2() + " RSV3: " + inputFrame.isRSV3());
            if (inputFrame.isRSV3()) {
                throw new InvalidFrameException("bad rsv RSV1: " + inputFrame.isRSV1() + " RSV2: " + inputFrame.isRSV2() + " RSV3: " + inputFrame.isRSV3());
            }
        }
        super.isFrameValid(inputFrame);
    }
}
