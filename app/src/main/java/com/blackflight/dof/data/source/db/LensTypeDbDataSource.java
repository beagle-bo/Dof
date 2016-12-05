package com.blackflight.dof.data.source.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.blackflight.dof.data.entity.LensTypeEntity;
import com.blackflight.dof.data.source.LensTypeDataSource;
import com.blackflight.dof.data.source.db.cursor.LensTypeCursorWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by peter on 12-11-16.
 */

public class LensTypeDbDataSource implements LensTypeDataSource {

    private EquipmentDbHelper mEquipmentDbHelper;

    LensTypeDbDataSource(Context context) {
        mEquipmentDbHelper = new EquipmentDbHelper(context);
    }

    @Override
    public LensTypeEntity getById(UUID id) throws Exception {
        LensTypeEntity lensTypeEntity = null;
        SQLiteDatabase db = mEquipmentDbHelper.getReadableDatabase();

        String[] projection = {
                EquipmentDbSchema.LensTypeTable.Columns.ID,
                EquipmentDbSchema.LensTypeTable.Columns.MODEL_NAME,
                EquipmentDbSchema.LensTypeTable.Columns.MIN_FOCAL_DISTANCE,
                EquipmentDbSchema.LensTypeTable.Columns.MAX_FOCAL_DISTANCE,
                EquipmentDbSchema.LensTypeTable.Columns.F_STOP_SCALE,
                EquipmentDbSchema.LensTypeTable.Columns.MIN_APERTURE_VALUE,
                EquipmentDbSchema.LensTypeTable.Columns.MAX_APERTURE_VALUE,
        };

        String selection = EquipmentDbSchema.LensTypeTable.Columns.ID + "=" + id;

        Cursor c = db.query(EquipmentDbSchema.LensTypeTable.NAME, projection, selection, null, null, null, null);
        LensTypeCursorWrapper lensTypeCursor = new LensTypeCursorWrapper(c);
        if (lensTypeCursor.moveToFirst()) {
            lensTypeEntity = lensTypeCursor.getLensType();
        }
        lensTypeCursor.close();

        db.close();

        return lensTypeEntity;
    }

    @Override
    public Collection<LensTypeEntity> getAll() throws Exception {
        List<LensTypeEntity> lensTypes = new ArrayList<>();
        SQLiteDatabase db = mEquipmentDbHelper.getReadableDatabase();

        String[] projection = {
                EquipmentDbSchema.LensTypeTable.Columns.ID,
                EquipmentDbSchema.LensTypeTable.Columns.MODEL_NAME,
                EquipmentDbSchema.LensTypeTable.Columns.MIN_FOCAL_DISTANCE,
                EquipmentDbSchema.LensTypeTable.Columns.MAX_FOCAL_DISTANCE,
                EquipmentDbSchema.LensTypeTable.Columns.F_STOP_SCALE,
                EquipmentDbSchema.LensTypeTable.Columns.MIN_APERTURE_VALUE,
                EquipmentDbSchema.LensTypeTable.Columns.MAX_APERTURE_VALUE,
        };

        Cursor c = db.query(EquipmentDbSchema.LensTypeTable.NAME, projection, null, null, null, null, null);
        LensTypeCursorWrapper lensTypeCursor = new LensTypeCursorWrapper(c);
        while (lensTypeCursor.moveToNext()) {
            LensTypeEntity lensTypeEntity = lensTypeCursor.getLensType();
            lensTypes.add(lensTypeEntity);
        }
        lensTypeCursor.close();

        db.close();

        return lensTypes;
    }

    @Override
    public void addOrUpdate(LensTypeEntity lensTypeEntity) throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EquipmentDbSchema.LensTypeTable.Columns.ID, lensTypeEntity.getId().toString());
        values.put(EquipmentDbSchema.LensTypeTable.Columns.MODEL_NAME, lensTypeEntity.getModelName());
        values.put(EquipmentDbSchema.LensTypeTable.Columns.MIN_FOCAL_DISTANCE, lensTypeEntity.getMinFocalDistance());
        values.put(EquipmentDbSchema.LensTypeTable.Columns.MAX_FOCAL_DISTANCE, lensTypeEntity.getMaxFocalDistance());
        values.put(EquipmentDbSchema.LensTypeTable.Columns.F_STOP_SCALE, lensTypeEntity.getFStopScale());
        values.put(EquipmentDbSchema.LensTypeTable.Columns.MIN_APERTURE_VALUE, lensTypeEntity.getMinApertureValue());
        values.put(EquipmentDbSchema.LensTypeTable.Columns.MAX_APERTURE_VALUE, lensTypeEntity.getMaxApertureValue());

        long rowId = db.insertWithOnConflict(EquipmentDbSchema.LensTypeTable.NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (rowId == -1) {
            values.remove(EquipmentDbSchema.LensTypeTable.Columns.ID);
            db.update(EquipmentDbSchema.LensTypeTable.NAME, values,
                    EquipmentDbSchema.LensTypeTable.Columns.ID + "=" + lensTypeEntity.getId(), null);
        }
        db.close();
    }

    @Override
    public void addOrUpdateAll(Collection<LensTypeEntity> lensTypeEntities) throws Exception {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void deleteById(UUID id) throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        String selection = EquipmentDbSchema.LensTypeTable.Columns.ID + "=" + id;
        db.delete(EquipmentDbSchema.LensTypeTable.NAME, selection, null);

        db.close();
    }

    @Override
    public void deleteAll() throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        db.delete(EquipmentDbSchema.LensTypeTable.NAME, null, null);

        db.close();
    }
}
