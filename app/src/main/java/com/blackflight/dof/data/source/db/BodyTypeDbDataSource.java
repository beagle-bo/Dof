package com.blackflight.dof.data.source.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.blackflight.dof.data.entity.BodyTypeEntity;
import com.blackflight.dof.data.source.BodyTypeDataSource;
import com.blackflight.dof.data.source.db.cursor.BodyTypeCursorWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by peter on 12-11-16.
 */

public class BodyTypeDbDataSource implements BodyTypeDataSource {

    private EquipmentDbHelper mEquipmentDbHelper;

    BodyTypeDbDataSource(Context context) {
        mEquipmentDbHelper = new EquipmentDbHelper(context);
    }

    @Override
    public BodyTypeEntity getById(UUID id) throws Exception {
        BodyTypeEntity bodyTypeEntity = null;
        SQLiteDatabase db = mEquipmentDbHelper.getReadableDatabase();

        String[] projection = {
                EquipmentDbSchema.BodyTypeTable.Columns.ID,
                EquipmentDbSchema.BodyTypeTable.Columns.MODEL_NAME,
                EquipmentDbSchema.BodyTypeTable.Columns.IMAGE_FORMAT_ID
        };

        String selection = EquipmentDbSchema.BodyTypeTable.Columns.ID + "=" + id;

        Cursor c = db.query(EquipmentDbSchema.BodyTypeTable.NAME, projection, selection, null, null, null, null);
        BodyTypeCursorWrapper bodyTypeCursor = new BodyTypeCursorWrapper(c);
        if (bodyTypeCursor.moveToFirst()) {
            bodyTypeEntity = bodyTypeCursor.getBodyType();
        }
        bodyTypeCursor.close();

        db.close();

        return bodyTypeEntity;
    }

    @Override
    public Collection<BodyTypeEntity> getAll() throws Exception {
        List<BodyTypeEntity> bodyTypes = new ArrayList<>();
        SQLiteDatabase db = mEquipmentDbHelper.getReadableDatabase();

        String[] projection = {
                EquipmentDbSchema.BodyTypeTable.Columns.ID,
                EquipmentDbSchema.BodyTypeTable.Columns.MODEL_NAME,
                EquipmentDbSchema.BodyTypeTable.Columns.IMAGE_FORMAT_ID
        };

        Cursor c = db.query(EquipmentDbSchema.BodyTypeTable.NAME, projection, null, null, null, null, null);
        BodyTypeCursorWrapper bodyTypeCursor = new BodyTypeCursorWrapper(c);
        while (bodyTypeCursor.moveToNext()) {
            BodyTypeEntity bodyTypeEntity = bodyTypeCursor.getBodyType();
            bodyTypes.add(bodyTypeEntity);
        }
        bodyTypeCursor.close();

        db.close();

        return bodyTypes;
    }

    @Override
    public void addOrUpdate(BodyTypeEntity bodyTypeEntity) throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EquipmentDbSchema.BodyTypeTable.Columns.ID, bodyTypeEntity.getId().toString());
        values.put(EquipmentDbSchema.BodyTypeTable.Columns.MODEL_NAME, bodyTypeEntity.getModelName());
        values.put(EquipmentDbSchema.BodyTypeTable.Columns.IMAGE_FORMAT_ID, bodyTypeEntity.getImageFormatId().toString());

        long rowId = db.insertWithOnConflict(EquipmentDbSchema.BodyTypeTable.NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (rowId == -1) {
            values.remove(EquipmentDbSchema.BodyTypeTable.Columns.ID);
            db.update(EquipmentDbSchema.BodyTypeTable.NAME, values,
                    EquipmentDbSchema.BodyTypeTable.Columns.ID + "=" + bodyTypeEntity.getId(), null);
        }
        db.close();
    }

    @Override
    public void addOrUpdateAll(Collection<BodyTypeEntity> bodyEntities) throws Exception {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void deleteById(UUID id) throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        String selection = EquipmentDbSchema.BodyTypeTable.Columns.ID + "=" + id;
        db.delete(EquipmentDbSchema.BodyTypeTable.NAME, selection, null);

        db.close();
    }

    @Override
    public void deleteAll() throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        db.delete(EquipmentDbSchema.BodyTypeTable.NAME, null, null);

        db.close();
    }
}
