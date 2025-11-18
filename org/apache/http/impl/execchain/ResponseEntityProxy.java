/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.conn.EofSensorInputStream
 *  org.apache.http.conn.EofSensorWatcher
 *  org.apache.http.entity.HttpEntityWrapper
 *  org.apache.http.impl.execchain.ConnectionHolder
 */
package org.apache.http.impl.execchain;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.conn.EofSensorInputStream;
import org.apache.http.conn.EofSensorWatcher;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.execchain.ConnectionHolder;

class ResponseEntityProxy
extends HttpEntityWrapper
implements EofSensorWatcher {
    private final ConnectionHolder connHolder;

    public boolean streamClosed(InputStream wrapped) throws IOException {
        try {
            boolean open = this.connHolder != null && !this.connHolder.isReleased();
            try {
                if (wrapped != null) {
                    wrapped.close();
                }
                this.releaseConnection();
            }
            catch (SocketException ex) {
                if (!open) return false;
                throw ex;
            }
        }
        catch (IOException ex) {
            this.abortConnection();
            throw ex;
        }
        catch (RuntimeException ex) {
            this.abortConnection();
            throw ex;
        }
        finally {
            this.cleanup();
        }
        return false;
    }

    public InputStream getContent() throws IOException {
        return new EofSensorInputStream(this.wrappedEntity.getContent(), (EofSensorWatcher)this);
    }

    public void consumeContent() throws IOException {
        this.releaseConnection();
    }

    public boolean streamAbort(InputStream wrapped) throws IOException {
        this.cleanup();
        return false;
    }

    public boolean isRepeatable() {
        return false;
    }

    ResponseEntityProxy(HttpEntity entity, ConnectionHolder connHolder) {
        super(entity);
        this.connHolder = connHolder;
    }

    public boolean eofDetected(InputStream wrapped) throws IOException {
        try {
            if (wrapped != null) {
                wrapped.close();
            }
            this.releaseConnection();
        }
        catch (IOException ex) {
            this.abortConnection();
            throw ex;
        }
        catch (RuntimeException ex) {
            this.abortConnection();
            throw ex;
        }
        finally {
            this.cleanup();
        }
        return false;
    }

    private void abortConnection() {
        if (this.connHolder == null) return;
        this.connHolder.abortConnection();
    }

    public void releaseConnection() {
        if (this.connHolder == null) return;
        this.connHolder.releaseConnection();
    }

    private void cleanup() throws IOException {
        if (this.connHolder == null) return;
        this.connHolder.close();
    }

    public static void enchance(HttpResponse response, ConnectionHolder connHolder) {
        HttpEntity entity = response.getEntity();
        if (entity == null) return;
        if (!entity.isStreaming()) return;
        if (connHolder == null) return;
        response.setEntity((HttpEntity)new ResponseEntityProxy(entity, connHolder));
    }

    public void writeTo(OutputStream outStream) throws IOException {
        try {
            if (outStream != null) {
                this.wrappedEntity.writeTo(outStream);
            }
            this.releaseConnection();
        }
        catch (IOException ex) {
            this.abortConnection();
            throw ex;
        }
        catch (RuntimeException ex) {
            this.abortConnection();
            throw ex;
        }
        finally {
            this.cleanup();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ResponseEntityProxy{");
        sb.append(this.wrappedEntity);
        sb.append('}');
        return sb.toString();
    }
}
