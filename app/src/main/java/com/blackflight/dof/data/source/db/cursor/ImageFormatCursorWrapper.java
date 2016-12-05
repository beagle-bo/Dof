package com.blackflight.dof.data.source.db.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.blackflight.dof.data.entity.ImageFormatEntity;
import com.blackflight.dof.data.source.db.EquipmentDbSchema;

import java.util.UUID;

/**
 * Created by peter on 16-11-16.
 */

public class ImageFormatCursorWrapper extends CursorWrapper {
    public ImageFormatCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public ImageFormatEntity getImageFormat() {
        UUID id = UUID.fromString(getString(getColumnIndexOrThrow(EquipmentDbSchema.ImageFormatTable.Columns.ID)));
        double width = getDouble(getColumnIndexOrThrow(EquipmentDbSchema.ImageFormatTable.Columns.WIDTH));
        double height = getDouble(getColumnIndexOrThrow(EquipmentDbSchema.ImageFormatTable.Columns.HEIGHT));

        ImageFormatEntity imageFormatEntity = new ImageFormatEntity(id, width, height);

        return imageFormatEntity;
    }
}
