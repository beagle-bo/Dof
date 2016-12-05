package com.blackflight.dof.data.source.db.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.blackflight.dof.data.entity.LensEntity;
import com.blackflight.dof.data.source.db.EquipmentDbSchema;

import java.util.UUID;

/**
 * Created by peter on 16-11-16.
 */

public class LensCursorWrapper extends CursorWrapper {
    public LensCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public LensEntity getLens() {
        UUID id = UUID.fromString(getString(getColumnIndexOrThrow(EquipmentDbSchema.LensTable.Columns.ID)));
        UUID lensTypeId = UUID.fromString(getString(getColumnIndexOrThrow(EquipmentDbSchema.LensTable.Columns.LENS_TYPE_ID)));
        double focalDistance = getDouble(getColumnIndexOrThrow(EquipmentDbSchema.LensTable.Columns.FOCAL_DISTANCE));
        double apertureValue = getDouble(getColumnIndexOrThrow(EquipmentDbSchema.LensTable.Columns.APERTURE_VALUE));

        LensEntity lensEntity = new LensEntity(id, lensTypeId, focalDistance, apertureValue, 1000);

        return lensEntity;
    }
}
