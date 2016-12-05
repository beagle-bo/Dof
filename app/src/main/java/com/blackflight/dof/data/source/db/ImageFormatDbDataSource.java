package com.blackflight.dof.data.source.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.blackflight.dof.data.entity.ImageFormatEntity;
import com.blackflight.dof.data.source.ImageFormatDataSource;
import com.blackflight.dof.data.source.db.cursor.ImageFormatCursorWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by peter on 12-11-16.
 */

public class ImageFormatDbDataSource implements ImageFormatDataSource {

    private EquipmentDbHelper mEquipmentDbHelper;

    ImageFormatDbDataSource(Context context) {
        mEquipmentDbHelper = new EquipmentDbHelper(context);
    }

    @Override
    public ImageFormatEntity getById(UUID id) throws Exception {
        ImageFormatEntity imageFormatEntity = null;
        SQLiteDatabase db = mEquipmentDbHelper.getReadableDatabase();

        String[] projection = {
                EquipmentDbSchema.ImageFormatTable.Columns.ID,
                EquipmentDbSchema.ImageFormatTable.Columns.WIDTH,
                EquipmentDbSchema.ImageFormatTable.Columns.HEIGHT
        };

        String selection = EquipmentDbSchema.ImageFormatTable.Columns.ID + "=" + id;

        Cursor c = db.query(EquipmentDbSchema.ImageFormatTable.NAME, projection, selection, null, null, null, null);
        ImageFormatCursorWrapper imageFormatCursor = new ImageFormatCursorWrapper(c);
        if (imageFormatCursor.moveToFirst()) {
            imageFormatEntity = imageFormatCursor.getImageFormat();
        }
        imageFormatCursor.close();

        db.close();

        return imageFormatEntity;
    }

    @Override
    public Collection<ImageFormatEntity> getAll() throws Exception {
        List<ImageFormatEntity> imageFormats = new ArrayList<>();
        SQLiteDatabase db = mEquipmentDbHelper.getReadableDatabase();

        String[] projection = {
                EquipmentDbSchema.ImageFormatTable.Columns.ID,
                EquipmentDbSchema.ImageFormatTable.Columns.WIDTH,
                EquipmentDbSchema.ImageFormatTable.Columns.HEIGHT
        };

        Cursor c = db.query(EquipmentDbSchema.ImageFormatTable.NAME, projection, null, null, null, null, null);
        ImageFormatCursorWrapper imageFormatCursor = new ImageFormatCursorWrapper(c);
        while (imageFormatCursor.moveToNext()) {
            ImageFormatEntity imageFormatEntity = imageFormatCursor.getImageFormat();
            imageFormats.add(imageFormatEntity);
        }
        imageFormatCursor.close();

        db.close();

        return imageFormats;
    }

    @Override
    public void addOrUpdate(ImageFormatEntity imageFormatEntity) throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EquipmentDbSchema.ImageFormatTable.Columns.ID, imageFormatEntity.getId().toString());
        values.put(EquipmentDbSchema.ImageFormatTable.Columns.WIDTH, imageFormatEntity.getWidth());
        values.put(EquipmentDbSchema.ImageFormatTable.Columns.HEIGHT, imageFormatEntity.getHeight());

        long rowId = db.insertWithOnConflict(EquipmentDbSchema.ImageFormatTable.NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (rowId == -1) {
            values.remove(EquipmentDbSchema.ImageFormatTable.Columns.ID);
            db.update(EquipmentDbSchema.ImageFormatTable.NAME, values,
                    EquipmentDbSchema.ImageFormatTable.Columns.ID + "=" + imageFormatEntity.getId(), null);
        }
        db.close();
    }

    @Override
    public void addOrUpdateAll(Collection<ImageFormatEntity> bodyEntities) throws Exception {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void deleteById(UUID id) throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        String selection = EquipmentDbSchema.ImageFormatTable.Columns.ID + "=" + id;
        db.delete(EquipmentDbSchema.ImageFormatTable.NAME, selection, null);

        db.close();
    }

    @Override
    public void deleteAll() throws Exception {
        SQLiteDatabase db = mEquipmentDbHelper.getWritableDatabase();

        db.delete(EquipmentDbSchema.ImageFormatTable.NAME, null, null);

        db.close();
    }
}
