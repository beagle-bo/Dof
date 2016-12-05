package com.blackflight.dof.data.source.db.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.blackflight.dof.data.entity.LensTypeEntity;
import com.blackflight.dof.data.source.db.EquipmentDbSchema;

import java.util.UUID;

/**
 * Created by peter on 16-11-16.
 */

public class LensTypeCursorWrapper extends CursorWrapper {
    public LensTypeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public LensTypeEntity getLensType() {
        UUID id = UUID.fromString(getString(getColumnIndexOrThrow(EquipmentDbSchema.LensTypeTable.Columns.ID)));
        String modelName = getString(getColumnIndexOrThrow(EquipmentDbSchema.LensTypeTable.Columns.MODEL_NAME));
        double minFocalDistance = getDouble(getColumnIndexOrThrow(EquipmentDbSchema.LensTypeTable.Columns.MIN_FOCAL_DISTANCE));
        double maxFocalDistance = getDouble(getColumnIndexOrThrow(EquipmentDbSchema.LensTypeTable.Columns.MAX_FOCAL_DISTANCE));
        String fStopScale = getString(getColumnIndexOrThrow(EquipmentDbSchema.LensTypeTable.Columns.F_STOP_SCALE));
        double minApertureValue = getDouble(getColumnIndexOrThrow(EquipmentDbSchema.LensTypeTable.Columns.MIN_APERTURE_VALUE));
        double maxApertureValue = getDouble(getColumnIndexOrThrow(EquipmentDbSchema.LensTypeTable.Columns.MAX_APERTURE_VALUE));

        LensTypeEntity lensTypeEntity = new LensTypeEntity(id, modelName,
                minFocalDistance, maxFocalDistance, fStopScale, minApertureValue, maxApertureValue);

        return lensTypeEntity;
    }
}
