/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.entity.AbstractHttpEntity
 *  org.apache.http.util.Args
 */
package org.apache.http.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.util.Args;

public class SerializableEntity
extends AbstractHttpEntity {
    private byte[] objSer;
    private Serializable objRef;

    public InputStream getContent() throws IOException, IllegalStateException {
        if (this.objSer != null) return new ByteArrayInputStream(this.objSer);
        this.createBytes(this.objRef);
        return new ByteArrayInputStream(this.objSer);
    }

    public void writeTo(OutputStream outStream) throws IOException {
        Args.notNull((Object)outStream, (String)"Output stream");
        if (this.objSer == null) {
            ObjectOutputStream out = new ObjectOutputStream(outStream);
            out.writeObject(this.objRef);
            out.flush();
        } else {
            outStream.write(this.objSer);
            outStream.flush();
        }
    }

    public boolean isRepeatable() {
        return true;
    }

    public long getContentLength() {
        return this.objSer == null ? -1L : (long)this.objSer.length;
    }

    private void createBytes(Serializable ser) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(ser);
        out.flush();
        this.objSer = baos.toByteArray();
    }

    public boolean isStreaming() {
        return this.objSer == null;
    }

    public SerializableEntity(Serializable serializable) {
        Args.notNull((Object)serializable, (String)"Source object");
        this.objRef = serializable;
    }

    public SerializableEntity(Serializable ser, boolean bufferize) throws IOException {
        Args.notNull((Object)ser, (String)"Source object");
        if (bufferize) {
            this.createBytes(ser);
        } else {
            this.objRef = ser;
        }
    }
}
