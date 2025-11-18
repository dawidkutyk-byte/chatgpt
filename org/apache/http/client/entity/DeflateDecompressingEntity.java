/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntity
 *  org.apache.http.client.entity.DecompressingEntity
 *  org.apache.http.client.entity.DeflateInputStreamFactory
 *  org.apache.http.client.entity.InputStreamFactory
 */
package org.apache.http.client.entity;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.DecompressingEntity;
import org.apache.http.client.entity.DeflateInputStreamFactory;
import org.apache.http.client.entity.InputStreamFactory;

public class DeflateDecompressingEntity
extends DecompressingEntity {
    public DeflateDecompressingEntity(HttpEntity entity) {
        super(entity, (InputStreamFactory)DeflateInputStreamFactory.getInstance());
    }
}
