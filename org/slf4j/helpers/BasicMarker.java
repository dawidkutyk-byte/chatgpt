/*
 * Decompiled with CFR 0.152.
 */
package org.slf4j.helpers;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Marker;

public class BasicMarker
implements Marker {
    private final String name;
    private static String OPEN = "[ ";
    private static String CLOSE = " ]";
    private List<Marker> referenceList = new CopyOnWriteArrayList<Marker>();
    private static String SEP = ", ";
    private static final long serialVersionUID = -2849567615646933777L;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Marker)) {
            return false;
        }
        Marker other = (Marker)obj;
        return this.name.equals(other.getName());
    }

    @Override
    public boolean hasReferences() {
        return this.referenceList.size() > 0;
    }

    public String toString() {
        if (!this.hasReferences()) {
            return this.getName();
        }
        Iterator<Marker> it = this.iterator();
        StringBuilder sb = new StringBuilder(this.getName());
        sb.append(' ').append(OPEN);
        while (true) {
            if (!it.hasNext()) {
                sb.append(CLOSE);
                return sb.toString();
            }
            Marker reference = it.next();
            sb.append(reference.getName());
            if (!it.hasNext()) continue;
            sb.append(SEP);
        }
    }

    @Override
    public boolean remove(Marker referenceToRemove) {
        return this.referenceList.remove(referenceToRemove);
    }

    @Override
    public boolean contains(String name) {
        Marker ref;
        if (name == null) {
            throw new IllegalArgumentException("Other cannot be null");
        }
        if (this.name.equals(name)) {
            return true;
        }
        if (!this.hasReferences()) return false;
        Iterator<Marker> i$ = this.referenceList.iterator();
        do {
            if (!i$.hasNext()) return false;
        } while (!(ref = i$.next()).contains(name));
        return true;
    }

    @Override
    public void add(Marker reference) {
        if (reference == null) {
            throw new IllegalArgumentException("A null value cannot be added to a Marker as reference.");
        }
        if (this.contains(reference)) {
            return;
        }
        if (reference.contains(this)) {
            return;
        }
        this.referenceList.add(reference);
    }

    @Override
    @Deprecated
    public boolean hasChildren() {
        return this.hasReferences();
    }

    BasicMarker(String name) {
        if (name == null) {
            throw new IllegalArgumentException("A marker name cannot be null");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean contains(Marker other) {
        Marker ref;
        if (other == null) {
            throw new IllegalArgumentException("Other cannot be null");
        }
        if (this.equals(other)) {
            return true;
        }
        if (!this.hasReferences()) return false;
        Iterator<Marker> i$ = this.referenceList.iterator();
        do {
            if (!i$.hasNext()) return false;
        } while (!(ref = i$.next()).contains(other));
        return true;
    }

    @Override
    public Iterator<Marker> iterator() {
        return this.referenceList.iterator();
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
