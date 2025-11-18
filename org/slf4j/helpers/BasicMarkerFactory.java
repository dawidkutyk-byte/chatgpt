/*
 * Decompiled with CFR 0.152.
 */
package org.slf4j.helpers;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.IMarkerFactory;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarker;

public class BasicMarkerFactory
implements IMarkerFactory {
    private final ConcurrentMap<String, Marker> markerMap = new ConcurrentHashMap<String, Marker>();

    @Override
    public boolean exists(String name) {
        if (name != null) return this.markerMap.containsKey(name);
        return false;
    }

    @Override
    public Marker getDetachedMarker(String name) {
        return new BasicMarker(name);
    }

    @Override
    public Marker getMarker(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Marker name cannot be null");
        }
        Marker marker = (Marker)this.markerMap.get(name);
        if (marker != null) return marker;
        marker = new BasicMarker(name);
        Marker oldMarker = this.markerMap.putIfAbsent(name, marker);
        if (oldMarker == null) return marker;
        marker = oldMarker;
        return marker;
    }

    @Override
    public boolean detachMarker(String name) {
        if (name != null) return this.markerMap.remove(name) != null;
        return false;
    }
}
