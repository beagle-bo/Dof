package com.blackflight.dof.data.source.db.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.blackflight.dof.data.entity.BodyEntity;
import com.blackflight.dof.data.source.db.EquipmentDbSchema;

import java.util.UUID;

/**
 * Created by peter on 16-11-16.
 */

public class BodyCursorWrapper extends CursorWrapper {
    public BodyCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public BodyEntity getBody() {
        UUID id = UUID.fromString(getString(getColumnIndexOrThrow(EquipmentDbSchema.BodyTable.Columns.ID)));
        UUID bodyTypeId = UUID.fromString(getString(getColumnIndexOrThrow(EquipmentDbSchema.BodyTable.Columns.BODY_TYPE_ID)));

        BodyEntity bodyEntity = new BodyEntity(id, bodyTypeId);

        return bodyEntity;
    }
}
