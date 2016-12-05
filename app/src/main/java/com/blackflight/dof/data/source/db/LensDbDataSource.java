package com.blackflight.dof.data.source.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.blackflight.dof.data.entity.LensEntity;
import com.blackflight.dof.data.source.LensDataSource;
import com.blackflight.dof.data.source.db.cursor.LensCursorWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by peter on 12-11-16.
 */

public class LensDbDataSource implements LensDataSource {

    private EquipmentDbHelper mEquipmentDbHelper;

    LensDbDataSource(Context context) {
        mEquipmentDbHelper = new EquipmentDbHelper(context);
    }

    @Override
    public LensEntity getById(UUID id) throws Exception {
        LensEntity lensEntity = null;
        SQLiteDatabase db = mEquipmentDbHelper.getReadableDatabase();

        String[] projection = {
                EquipmentDbSchema.LensTable.Columns.ID,
                EquipmentDbSchema.LensTable.Columns.LENS_TYPE_ID,
                EquipmentDbSchema.LensTable.Columns.FOCAL_DISTANCE,
                EquipmentDbSchema.LensTable.Columns.APERTURE_VALUE
        };

        String selection = EquipmentDbSchema.LensTable.Columns.ID + "=" + id;

        Cursor c = db.query(EquipmentDbSchema.LensTable.NAME, projection, selection, null, null, null, null);
        LensCursorWrapper lensCursor = new LensCursorWrapper(c);
        if (lensCursor.moveToFirst()) {
            lensEntity = lensCursor.getLens();
        }
        lensCursor.close();

        db.close();

        return lensEntity;
    }

    @Override
    public Collection<LensEntity> getAll() throws Exception {
        List<LensEntity> lenses = new ArrayList<>();
        SQLiteDatabase db = mEquipmentDbHelper.getReadableDatabase();

        String[] projection = {
                EquipmentDbSchema.LensTable.Columns.ID,
                EquipmentDbSchema.LensTable.Columns.LENS_TYPE_ID,
                EquipmentDbSchema.LensTable.Columns.FOCAL_DISTANCE,
                EquipmentDbSchema.LensTable.Columns.APERTURE_VALUE
        };

        Cursor c = db.query(EquipmentDbSchema.LensTable.NAME, projection, null, null, null, null, null);
        LensCursorWrapper lensCursor = new LensCursorWrapper(c);
        while (lensCursor.moveToNext()) {
            LensEntity lensEntity = lensCursor.getLens();
            lenses.add(lensEntity);
        }
        lensCursor.close();

        db.close();

        return lenses;
    }

    @Override
    public void addOrUpdate(LensEntity lensEntity) throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EquipmentDbSchema.LensTable.Columns.ID, lensEntity.getId().toString());
        values.put(EquipmentDbSchema.LensTable.Columns.LENS_TYPE_ID, lensEntity.getLensTypeId().toString());
        values.put(EquipmentDbSchema.LensTable.Columns.FOCAL_DISTANCE, lensEntity.getFocalDistance());
        values.put(EquipmentDbSchema.LensTable.Columns.APERTURE_VALUE, lensEntity.getApertureValue());

        long rowId = db.insertWithOnConflict(EquipmentDbSchema.LensTable.NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (rowId == -1) {
            values.remove(EquipmentDbSchema.LensTable.Columns.ID);
            db.update(EquipmentDbSchema.LensTable.NAME, values,
                    EquipmentDbSchema.LensTable.Columns.ID + "=" + lensEntity.getId(), null);
        }
        db.close();
    }

    @Override
    public void addOrUpdateAll(Collection<LensEntity> lensEntities) throws Exception {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void deleteById(UUID id) throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        String selection = EquipmentDbSchema.LensTable.Columns.ID + "=" + id;
        db.delete(EquipmentDbSchema.LensTable.NAME, selection, null);

        db.close();
    }

    @Override
    public void deleteAll() throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        db.delete(EquipmentDbSchema.LensTable.NAME, null, null);

        db.close();
    }
}
