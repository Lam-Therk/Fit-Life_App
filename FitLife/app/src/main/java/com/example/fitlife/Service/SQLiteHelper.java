package com.example.fitlife.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fitlife.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fitness.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_EXERCISES = "exercises";
    private static final String TABLE_USERS = "users";

    private static final String COLUMN_EXERCISE_ID = "id";
    private static final String COLUMN_EXERCISE_NAME = "name";
    private static final String COLUMN_EXERCISE_REPEAT_TIMES = "repeat_times";
    private static final String COLUMN_EXERCISE_DURATION = "duration";
    private static final String COLUMN_EXERCISE_IMAGE_RES_ID = "image_res_id";
    private static final String COLUMN_EXERCISE_TYPE_ID = "typeId";

    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USER_USERNAME = "username";
    private static final String COLUMN_USER_PASSWORD = "password";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_USERNAME + " TEXT, "
                + COLUMN_USER_PASSWORD + " TEXT)");

        String CREATE_EXERCISES_TABLE = "CREATE TABLE " + TABLE_EXERCISES + "("
                + COLUMN_EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_EXERCISE_NAME + " TEXT,"
                + COLUMN_EXERCISE_REPEAT_TIMES + " INTEGER,"
                + COLUMN_EXERCISE_DURATION + " TEXT,"
                + COLUMN_EXERCISE_IMAGE_RES_ID + " INTEGER,"
                + COLUMN_EXERCISE_TYPE_ID + " INTEGER" + ")";
        db.execSQL(CREATE_EXERCISES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_EXERCISES + " ADD COLUMN " + COLUMN_EXERCISE_TYPE_ID + " INTEGER DEFAULT 0");
            db.execSQL("CREATE TABLE " + TABLE_USERS + " ("
                    + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_USER_USERNAME + " TEXT, "
                    + COLUMN_USER_PASSWORD + " TEXT)");
        }
    }
    public void addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_USERNAME, username);
        values.put(COLUMN_USER_PASSWORD, password);
        db.insert(TABLE_USERS, null, values);
        db.close();
    }
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_USER_USERNAME + "=? AND " + COLUMN_USER_PASSWORD + "=?",
                new String[]{username, password}, null, null, null);
        boolean userExists = cursor.moveToFirst();
        cursor.close();
        db.close();
        return userExists;
    }

    public void addExercise(Exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EXERCISE_NAME, exercise.getName());
        values.put(COLUMN_EXERCISE_REPEAT_TIMES, exercise.getRepeatTimes());
        values.put(COLUMN_EXERCISE_DURATION, exercise.getDuration());
        values.put(COLUMN_EXERCISE_IMAGE_RES_ID, exercise.getImageResId());
        values.put(COLUMN_EXERCISE_TYPE_ID, exercise.getTypeId());
        db.insert(TABLE_EXERCISES, null, values);
        db.close();
    }

    public List<Exercise> getAllExercises() {
        List<Exercise> exerciseList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EXERCISES, null);
        if (cursor.moveToFirst()) {
            do {
                Exercise exercise = new Exercise();
                exercise.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_ID)));
                exercise.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_NAME)));
                exercise.setRepeatTimes(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_REPEAT_TIMES)));
                exercise.setDuration(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_DURATION)));
                exercise.setImageResId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_IMAGE_RES_ID)));
                exercise.setTypeId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_TYPE_ID))); // Set typeId
                exerciseList.add(exercise);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return exerciseList;
    }

    public Exercise getExerciseById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_EXERCISES, null, COLUMN_EXERCISE_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Exercise exercise = new Exercise(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_NAME)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_REPEAT_TIMES)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_DURATION)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_IMAGE_RES_ID)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_TYPE_ID))
            );
            exercise.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_ID)));
            cursor.close();
            db.close();
            return exercise;
        } else {
            db.close();
            return null;
        }
    }

    public List<Integer> getAllExerciseIds() {
        List<Integer> exerciseIds = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_EXERCISE_ID + " FROM " + TABLE_EXERCISES, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_ID));
                exerciseIds.add(id);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return exerciseIds;
    }
    public List<Exercise> getExercisesByTypeId(int typeId) {
        List<Exercise> exerciseList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_EXERCISES, null, COLUMN_EXERCISE_TYPE_ID + "=?",
                new String[]{String.valueOf(typeId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Exercise exercise = new Exercise();
                exercise.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_ID)));
                exercise.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_NAME)));
                exercise.setRepeatTimes(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_REPEAT_TIMES)));
                exercise.setDuration(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_DURATION)));
                exercise.setImageResId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_IMAGE_RES_ID)));
                exercise.setTypeId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_TYPE_ID)));
                exerciseList.add(exercise);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return exerciseList;
    }

}
