/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.util.Args
 */
package org.apache.http.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import org.apache.http.util.Args;

public class VersionInfo {
    private final String infoClassloader;
    private final String infoModule;
    public static final String VERSION_PROPERTY_FILE = "version.properties";
    public static final String PROPERTY_TIMESTAMP = "info.timestamp";
    private final String infoRelease;
    private final String infoTimestamp;
    private final String infoPackage;
    public static final String PROPERTY_RELEASE = "info.release";
    public static final String UNAVAILABLE = "UNAVAILABLE";
    public static final String PROPERTY_MODULE = "info.module";

    public final String getPackage() {
        return this.infoPackage;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(20 + this.infoPackage.length() + this.infoModule.length() + this.infoRelease.length() + this.infoTimestamp.length() + this.infoClassloader.length());
        sb.append("VersionInfo(").append(this.infoPackage).append(':').append(this.infoModule);
        if (!UNAVAILABLE.equals(this.infoRelease)) {
            sb.append(':').append(this.infoRelease);
        }
        if (!UNAVAILABLE.equals(this.infoTimestamp)) {
            sb.append(':').append(this.infoTimestamp);
        }
        sb.append(')');
        if (UNAVAILABLE.equals(this.infoClassloader)) return sb.toString();
        sb.append('@').append(this.infoClassloader);
        return sb.toString();
    }

    protected VersionInfo(String pckg, String module, String release, String time, String clsldr) {
        Args.notNull((Object)pckg, (String)"Package identifier");
        this.infoPackage = pckg;
        this.infoModule = module != null ? module : UNAVAILABLE;
        this.infoRelease = release != null ? release : UNAVAILABLE;
        this.infoTimestamp = time != null ? time : UNAVAILABLE;
        this.infoClassloader = clsldr != null ? clsldr : UNAVAILABLE;
    }

    public final String getRelease() {
        return this.infoRelease;
    }

    public final String getModule() {
        return this.infoModule;
    }

    public static String getUserAgent(String name, String pkg, Class<?> cls) {
        VersionInfo vi = VersionInfo.loadVersionInfo(pkg, cls.getClassLoader());
        String release = vi != null ? vi.getRelease() : UNAVAILABLE;
        String javaVersion = System.getProperty("java.version");
        return String.format("%s/%s (Java/%s)", name, release, javaVersion);
    }

    public final String getClassloader() {
        return this.infoClassloader;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static VersionInfo loadVersionInfo(String pckg, ClassLoader clsldr) {
        Properties vip;
        ClassLoader cl;
        block5: {
            Args.notNull((Object)pckg, (String)"Package identifier");
            cl = clsldr != null ? clsldr : Thread.currentThread().getContextClassLoader();
            vip = null;
            try {
                InputStream is = cl.getResourceAsStream(pckg.replace('.', '/') + "/" + VERSION_PROPERTY_FILE);
                if (is == null) break block5;
                try {
                    Properties props = new Properties();
                    props.load(is);
                    vip = props;
                }
                finally {
                    is.close();
                }
            }
            catch (IOException ex) {
                // empty catch block
            }
        }
        VersionInfo result = null;
        if (vip == null) return result;
        result = VersionInfo.fromMap(pckg, vip, cl);
        return result;
    }

    public final String getTimestamp() {
        return this.infoTimestamp;
    }

    protected static VersionInfo fromMap(String pckg, Map<?, ?> info, ClassLoader clsldr) {
        Args.notNull((Object)pckg, (String)"Package identifier");
        String module = null;
        String release = null;
        String timestamp = null;
        if (info != null) {
            module = (String)info.get(PROPERTY_MODULE);
            if (module != null && module.length() < 1) {
                module = null;
            }
            if ((release = (String)info.get(PROPERTY_RELEASE)) != null && (release.length() < 1 || release.equals("${pom.version}"))) {
                release = null;
            }
            if ((timestamp = (String)info.get(PROPERTY_TIMESTAMP)) != null && (timestamp.length() < 1 || timestamp.equals("${mvn.timestamp}"))) {
                timestamp = null;
            }
        }
        String clsldrstr = null;
        if (clsldr == null) return new VersionInfo(pckg, module, release, timestamp, clsldrstr);
        clsldrstr = clsldr.toString();
        return new VersionInfo(pckg, module, release, timestamp, clsldrstr);
    }

    public static VersionInfo[] loadVersionInfo(String[] pckgs, ClassLoader clsldr) {
        Args.notNull((Object)pckgs, (String)"Package identifier array");
        ArrayList<VersionInfo> vil = new ArrayList<VersionInfo>(pckgs.length);
        String[] arr$ = pckgs;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            String pckg = arr$[i$];
            VersionInfo vi = VersionInfo.loadVersionInfo(pckg, clsldr);
            if (vi != null) {
                vil.add(vi);
            }
            ++i$;
        }
        return vil.toArray(new VersionInfo[vil.size()]);
    }
}
