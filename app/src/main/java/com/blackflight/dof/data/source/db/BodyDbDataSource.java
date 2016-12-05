package com.blackflight.dof.data.source.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.blackflight.dof.data.entity.BodyEntity;
import com.blackflight.dof.data.source.BodyDataSource;
import com.blackflight.dof.data.source.db.cursor.BodyCursorWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by peter on 12-11-16.
 */

public class BodyDbDataSource implements BodyDataSource {

    private EquipmentDbHelper mEquipmentDbHelper;

    BodyDbDataSource(Context context) {
        mEquipmentDbHelper = new EquipmentDbHelper(context);
    }

    @Override
    public BodyEntity getById(UUID id) throws Exception {
        BodyEntity bodyEntity = null;
        SQLiteDatabase db = mEquipmentDbHelper.getReadableDatabase();

        String[] projection = {
                EquipmentDbSchema.BodyTable.Columns.ID,
                EquipmentDbSchema.BodyTable.Columns.BODY_TYPE_ID,
        };

        String selection = EquipmentDbSchema.BodyTable.Columns.ID + "=" + id;

        Cursor c = db.query(EquipmentDbSchema.BodyTable.NAME, projection, selection, null, null, null, null);
        BodyCursorWrapper bodyCursor = new BodyCursorWrapper(c);
        if (bodyCursor.moveToFirst()) {
            bodyEntity = bodyCursor.getBody();
        }
        bodyCursor.close();

        db.close();

        return bodyEntity;
    }

    @Override
    public Collection<BodyEntity> getAll() throws Exception {
        List<BodyEntity> bodies = new ArrayList<>();
        SQLiteDatabase db = mEquipmentDbHelper.getReadableDatabase();

        String[] projection = {
                EquipmentDbSchema.BodyTable.Columns.ID,
                EquipmentDbSchema.BodyTable.Columns.BODY_TYPE_ID
        };

        Cursor c = db.query(EquipmentDbSchema.BodyTable.NAME, projection, null, null, null, null, null);
        BodyCursorWrapper bodyCursor = new BodyCursorWrapper(c);
        while (bodyCursor.moveToNext()) {
            BodyEntity bodyEntity = bodyCursor.getBody();
            bodies.add(bodyEntity);
        }
        bodyCursor.close();

        db.close();

        return bodies;
    }

    @Override
    public void addOrUpdate(BodyEntity bodyEntity) throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EquipmentDbSchema.BodyTable.Columns.ID, bodyEntity.getId().toString());
        values.put(EquipmentDbSchema.BodyTable.Columns.BODY_TYPE_ID, bodyEntity.getBodyTypeId().toString());

        long rowId = db.insertWithOnConflict(EquipmentDbSchema.BodyTable.NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (rowId == -1) {
            values.remove(EquipmentDbSchema.BodyTable.Columns.ID);
            db.update(EquipmentDbSchema.BodyTable.NAME, values,
                    EquipmentDbSchema.BodyTable.Columns.ID + "=" + bodyEntity.getId(), null);
        }
        db.close();
    }

    @Override
    public void addOrUpdateAll(Collection<BodyEntity> bodyEntities) throws Exception {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void deleteById(UUID id) throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        String selection = EquipmentDbSchema.BodyTable.Columns.ID + "=" + id;
        db.delete(EquipmentDbSchema.BodyTable.NAME, selection, null);

        db.close();
    }

    @Override
    public void deleteAll() throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        db.delete(EquipmentDbSchema.BodyTable.NAME, null, null);

        db.close();
    }
}
