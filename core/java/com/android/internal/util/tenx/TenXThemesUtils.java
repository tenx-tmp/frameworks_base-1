/*
 * Copyright (C) 2020-2022 TenX-OS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.internal.util.tenx;

import android.content.om.IOverlayManager;
import android.os.RemoteException;

import android.util.Log;

public class TenXThemesUtils {

    public static final String TAG = "TenXThemeUtils";

    // QS Tile Styles
    public static final String[] QS_TILE_STYLES = {
            "com.android.systemui.qstile.default",
            "com.android.systemui.qstile.minimal",
            "com.android.systemui.qstile.stroke",
    };

    // QQS Max rows
    public static final String[] QQS_MAX_ROWS = {
            "com.android.systemui.qqs.rows_default",
            "com.android.systemui.qqs.rows_extra",
    };

    // Switches qs tile style to user selected.
    public static void updateQSTileStyle(IOverlayManager om, int userId, int qsTileStyle) {
        if (qsTileStyle == 0) {
            stockQSTileStyle(om, userId);
        } else {
            try {
                om.setEnabled(TenXThemesUtils.QS_TILE_STYLES[qsTileStyle],
                        true, userId);
            } catch (RemoteException e) {
                Log.w(TAG, "Can't change qs tile icon", e);
            }
        }
    }

    // Switches qs tile style back to stock.
    public static void stockQSTileStyle(IOverlayManager om, int userId) {
        // skip index 0
        for (int i = 0; i < TenXThemesUtils.QS_TILE_STYLES.length; i++) {
            String qstilestyle = TenXThemesUtils.QS_TILE_STYLES[i];
            try {
                om.setEnabled(qstilestyle,
                        false /*disable*/, userId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    // Switches QQS Max rows to user selected.
    public static void updateQQSMaxRows(IOverlayManager om, int userId, int qqsMaxRows) {
        if (qqsMaxRows == 0) {
            stockQQSMaxRows(om, userId);
        } else {
            try {
                om.setEnabled(TenXThemesUtils.QQS_MAX_ROWS[qqsMaxRows],
                        true, userId);
            } catch (RemoteException e) {
                Log.w(TAG, "Can't change qqs max rows", e);
            }
        }
    }

    // Switches QQS MAx rows back to stock.
    public static void stockQQSMaxRows(IOverlayManager om, int userId) {
        // skip index 0
        for (int i = 0; i < TenXThemesUtils.QQS_MAX_ROWS.length; i++) {
            String qqsmaxrows = TenXThemesUtils.QQS_MAX_ROWS[i];
            try {
                om.setEnabled(qqsmaxrows,
                        false /*disable*/, userId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
