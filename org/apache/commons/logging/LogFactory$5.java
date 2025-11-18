/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.LogFactory
 */
package org.apache.commons.logging;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.PrivilegedAction;
import java.util.Properties;
import org.apache.commons.logging.LogFactory;

/*
 * Exception performing whole class analysis ignored.
 */
static final class LogFactory.5
implements PrivilegedAction {
    private final /* synthetic */ URL val$url;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Object run() {
        InputStream stream = null;
        try {
            URLConnection connection = this.val$url.openConnection();
            connection.setUseCaches(false);
            stream = connection.getInputStream();
            if (stream == null) return null;
            Properties props = new Properties();
            props.load(stream);
            stream.close();
            stream = null;
            Properties properties = props;
            return properties;
        }
        catch (IOException e) {
            if (!LogFactory.isDiagnosticsEnabled()) return null;
            LogFactory.access$000((String)new StringBuffer().append("Unable to read URL ").append(this.val$url).toString());
        }
        finally {
            block15: {
                if (stream != null) {
                    try {
                        stream.close();
                    }
                    catch (IOException e) {
                        if (!LogFactory.isDiagnosticsEnabled()) break block15;
                        LogFactory.access$000((String)new StringBuffer().append("Unable to close stream for URL ").append(this.val$url).toString());
                    }
                }
            }
        }
        return null;
    }

    LogFactory.5(URL uRL) {
        this.val$url = uRL;
    }
}
