package com.blackflight.dof.data.source.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.blackflight.dof.data.entity.CameraEntity;
import com.blackflight.dof.data.source.CameraDataSource;
import com.blackflight.dof.data.source.db.cursor.CameraCursorWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by peter on 12-11-16.
 */

public class CameraDbDataSource implements CameraDataSource {

    private EquipmentDbHelper mEquipmentDbHelper;

    CameraDbDataSource(Context context) {
        mEquipmentDbHelper = new EquipmentDbHelper(context);
    }

    @Override
    public CameraEntity getById(UUID id) throws Exception {
        CameraEntity cameraEntity = null;
        SQLiteDatabase db = mEquipmentDbHelper.getReadableDatabase();

        String[] projection = {
                EquipmentDbSchema.CameraTable.Columns.ID,
                EquipmentDbSchema.CameraTable.Columns.BODY_ID,
                EquipmentDbSchema.CameraTable.Columns.LENS_ID,
        };

        String selection = EquipmentDbSchema.CameraTable.Columns.ID + "=" + id;

        Cursor c = db.query(EquipmentDbSchema.CameraTable.NAME, projection, selection, null, null, null, null);
        CameraCursorWrapper cameraCursor = new CameraCursorWrapper(c);
        if (cameraCursor.moveToFirst()) {
            cameraEntity = cameraCursor.getCamera();
        }
        cameraCursor.close();

        db.close();

        return cameraEntity;
    }

    @Override
    public Collection<CameraEntity> getAll() throws Exception {
        List<CameraEntity> cameras = new ArrayList<>();
        SQLiteDatabase db = mEquipmentDbHelper.getReadableDatabase();

        String[] projection = {
                EquipmentDbSchema.CameraTable.Columns.ID,
                EquipmentDbSchema.CameraTable.Columns.BODY_ID,
                EquipmentDbSchema.CameraTable.Columns.LENS_ID,
        };

        Cursor c = db.query(EquipmentDbSchema.CameraTable.NAME, projection, null, null, null, null, null);
        CameraCursorWrapper cameraCursor = new CameraCursorWrapper(c);
        while (cameraCursor.moveToNext()) {
            CameraEntity camera = cameraCursor.getCamera();
            cameras.add(camera);
        }
        cameraCursor.close();

        db.close();

        return cameras;
    }


    @Override
    public void addOrUpdate(CameraEntity cameraEntity) throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EquipmentDbSchema.CameraTable.Columns.ID, cameraEntity.getId().toString());
        values.put(EquipmentDbSchema.CameraTable.Columns.BODY_ID, cameraEntity.getBodyId().toString());
        values.put(EquipmentDbSchema.CameraTable.Columns.LENS_ID, cameraEntity.getLensId().toString());

        long rowId = db.insertWithOnConflict(EquipmentDbSchema.CameraTable.NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (rowId == -1) {
            values.remove(EquipmentDbSchema.CameraTable.Columns.ID);
            db.update(EquipmentDbSchema.CameraTable.NAME, values,
                    EquipmentDbSchema.CameraTable.Columns.ID + "=" + cameraEntity.getId(), null);
        }
        db.close();
    }

    @Override
    public void addOrUpdateAll(Collection<CameraEntity> cameraEntities) throws Exception {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void deleteById(UUID id) throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        String selection = EquipmentDbSchema.CameraTable.Columns.ID + "=" + id;
        db.delete(EquipmentDbSchema.CameraTable.NAME, selection, null);

        db.close();
    }

    @Override
    public void deleteAll() throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        db.delete(EquipmentDbSchema.CameraTable.NAME, null, null);

        db.close();
    }
}
