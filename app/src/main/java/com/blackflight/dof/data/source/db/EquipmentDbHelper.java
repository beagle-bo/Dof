package com.blackflight.dof.data.source.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.effect.EffectUpdateListener;

/**
 * Created by peter on 12-11-16.
 */

public class EquipmentDbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "equipment.db";

    public EquipmentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CAMERA_TABLE = "CREATE TABLE " + EquipmentDbSchema.CameraTable.NAME +
                "(" +
                EquipmentDbSchema.CameraTable.Columns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EquipmentDbSchema.CameraTable.Columns.ID + "VARCHAR(36) UNIQUE, " +
                EquipmentDbSchema.CameraTable.Columns.BODY_ID + " VARCHAR(36) NOT NULL REFERENCES " +
                        EquipmentDbSchema.BodyTable.NAME +
                        "(" + EquipmentDbSchema.BodyTable.Columns.ID + "), " +
                EquipmentDbSchema.CameraTable.Columns.LENS_ID + " VARCHAR(36) REFERENCES " +
                        EquipmentDbSchema.LensTable.NAME +
                        "(" + EquipmentDbSchema.LensTable.Columns.ID + ")" +
                ")";

        String CREATE_BODY_TABLE = "CREATE TABLE " + EquipmentDbSchema.BodyTable.NAME +
                "(" +
                EquipmentDbSchema.BodyTable.Columns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EquipmentDbSchema.BodyTable.Columns.ID + "VARCHAR(36) UNIQUE, " +
                EquipmentDbSchema.BodyTable.Columns.BODY_TYPE_ID + " VARCHAR(36) NOT NULL REFERENCES " +
                        EquipmentDbSchema.BodyTypeTable.NAME +
                        "(" + EquipmentDbSchema.BodyTypeTable.Columns.ID + ")" +
                ")";

        String CREATE_BODY_TYPE_TABLE = "CREATE TABLE " + EquipmentDbSchema.BodyTypeTable.NAME +
                "(" +
                EquipmentDbSchema.BodyTypeTable.Columns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EquipmentDbSchema.BodyTypeTable.Columns.ID + "VARCHAR(36) UNIQUE, " +
                EquipmentDbSchema.BodyTypeTable.Columns.MODEL_NAME + " TEXT NOT NULL, " +
                EquipmentDbSchema.BodyTypeTable.Columns.IMAGE_FORMAT_ID + "VARCHAR(36) NOT NULL REFERENCES " +
                        EquipmentDbSchema.ImageFormatTable.NAME +
                        "(" + EquipmentDbSchema.ImageFormatTable.Columns.ID + ")" +
                ")";

        String CREATE_IMAGE_FORMAT_TABLE = "CREATE TABLE " + EquipmentDbSchema.ImageFormatTable.NAME +
                "(" +
                EquipmentDbSchema.ImageFormatTable.Columns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EquipmentDbSchema.ImageFormatTable.Columns.ID + "VARCHAR(36) UNIQUE, " +
                EquipmentDbSchema.ImageFormatTable.Columns.WIDTH + " REAL NOT NULL, " +
                EquipmentDbSchema.ImageFormatTable.Columns.HEIGHT + "REAL NOT NULL" +
                ")";

        String CREATE_LENS_TABLE = "CREATE TABLE " + EquipmentDbSchema.LensTable.NAME +
                "(" +
                EquipmentDbSchema.LensTable.Columns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EquipmentDbSchema.LensTable.Columns.ID + "VARCHAR(36) UNIQUE, " +
                EquipmentDbSchema.LensTable.Columns.LENS_TYPE_ID + " VARCHAR(36) NOT NULL REFERENCES " +
                        EquipmentDbSchema.LensTypeTable.NAME + ", " +
                        "(" + EquipmentDbSchema.LensTypeTable.Columns.ID + "), " +
                EquipmentDbSchema.LensTable.Columns.APERTURE_VALUE + "REAL NOT NULL, " +
                EquipmentDbSchema.LensTable.Columns.FOCAL_DISTANCE+ "REAL NOT NULL" +
                ")";

        String CREATE_LENS_TYPE_TABLE = "CREATE TABLE " + EquipmentDbSchema.LensTypeTable.NAME +
                "(" +
                EquipmentDbSchema.LensTypeTable.Columns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EquipmentDbSchema.LensTypeTable.Columns.ID + "VARCHAR(36) UNIQUE, " +
                EquipmentDbSchema.LensTypeTable.Columns.MODEL_NAME + " TEXT NOT NULL, " +
                EquipmentDbSchema.LensTypeTable.Columns.F_STOP_SCALE + "TEXT NOT NULL, " +
                EquipmentDbSchema.LensTypeTable.Columns.MIN_APERTURE_VALUE + "REAL NOT NULL, " +
                EquipmentDbSchema.LensTypeTable.Columns.MAX_APERTURE_VALUE + "REAL NOT NULL, " +
                EquipmentDbSchema.LensTypeTable.Columns.MIN_FOCAL_DISTANCE + "REAL NOT NULL, " +
                EquipmentDbSchema.LensTypeTable.Columns.MAX_FOCAL_DISTANCE + "REAL NOT NULL" +
                ")";

        db.execSQL(CREATE_CAMERA_TABLE);
        db.execSQL(CREATE_BODY_TABLE);
        db.execSQL(CREATE_BODY_TYPE_TABLE);
        db.execSQL(CREATE_IMAGE_FORMAT_TABLE);
        db.execSQL(CREATE_LENS_TABLE);
        db.execSQL(CREATE_LENS_TYPE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
