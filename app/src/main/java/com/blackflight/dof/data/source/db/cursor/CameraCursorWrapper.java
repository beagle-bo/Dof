package com.blackflight.dof.data.source.db.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.blackflight.dof.data.entity.CameraEntity;
import com.blackflight.dof.data.source.db.EquipmentDbSchema;

import java.util.UUID;

/**
 * Created by peter on 14-11-16.
 */

public class CameraCursorWrapper extends CursorWrapper {
    public CameraCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public CameraEntity getCamera() {
        UUID id = UUID.fromString(getString(getColumnIndexOrThrow(EquipmentDbSchema.CameraTable.Columns.ID)));
        UUID bodyId = UUID.fromString(getString(getColumnIndexOrThrow(EquipmentDbSchema.CameraTable.Columns.BODY_ID)));
        UUID lensId = null;
        int lenscolumnIndex = getColumnIndexOrThrow(EquipmentDbSchema.CameraTable.Columns.LENS_ID);
        if (!isNull(lenscolumnIndex)) {
            lensId = UUID.fromString(getString(getColumnIndexOrThrow(EquipmentDbSchema.CameraTable.Columns.LENS_ID)));
        }
        CameraEntity cameraEntity = new CameraEntity(id, bodyId, lensId);

        return cameraEntity;
    }
}
