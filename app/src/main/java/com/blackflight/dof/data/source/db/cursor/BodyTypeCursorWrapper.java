package com.blackflight.dof.data.source.db.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.blackflight.dof.data.entity.BodyTypeEntity;
import com.blackflight.dof.data.source.db.EquipmentDbSchema;

import java.util.UUID;

/**
 * Created by peter on 16-11-16.
 */

public class BodyTypeCursorWrapper extends CursorWrapper {

    public BodyTypeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public BodyTypeEntity getBodyType() {
        UUID id = UUID.fromString(getString(getColumnIndexOrThrow(EquipmentDbSchema.BodyTypeTable.Columns.ID)));
        String modelName = getString(getColumnIndexOrThrow(EquipmentDbSchema.BodyTypeTable.Columns.MODEL_NAME));
        UUID imageFormatId = UUID.fromString(getString(getColumnIndexOrThrow(EquipmentDbSchema.BodyTypeTable.Columns.IMAGE_FORMAT_ID)));

        BodyTypeEntity bodyTypeEntity = new BodyTypeEntity(id, modelName, imageFormatId);

        return bodyTypeEntity;
    }
}
