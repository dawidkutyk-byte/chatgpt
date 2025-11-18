/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.permission.PermissionChecker
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.permission.PermissionCheckers$Always
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.permission;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.permission.PermissionChecker;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.permission.PermissionCheckers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState;

final class PermissionCheckers {
    static final PermissionChecker FALSE;
    static final PermissionChecker TRUE;
    static final PermissionChecker NOT_SET;

    static {
        NOT_SET = new Always(TriState.NOT_SET, null);
        FALSE = new Always(TriState.FALSE, null);
        TRUE = new Always(TriState.TRUE, null);
    }

    private PermissionCheckers() {
    }
}
