package com.example.scott.login_register;

/**
 * Created by Scott on 7/18/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDB extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "user";
    private static final String KEY_USERID = "userId";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";

    private static final String DATABASE_NAME = "db.sqlite";
    private static final int DATABASE_VERSION = 1;

    public UserDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" + KEY_USERID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE ," +
                KEY_USERNAME + " VARCHAR NOT NULL ," + KEY_PASSWORD + " VARCHAR NOT NULL ," +
                KEY_NAME + " VARCHAR NOT NULL ," + KEY_AGE + " INTEGER NOT NULL);";

        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Getting User Count
    public int getUserCount(User user) {
        String countQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE username = '" + user.getUsername() + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int result = cursor.getCount();

        cursor.close();

        return result;
    }

    //Add user
    public int addUser(User user) {
        int result = 0;

        if(getUserCount(user) != 0) {
            result = -1;
        } else {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_USERNAME, user.getUsername());
            values.put(KEY_PASSWORD, user.getPassword());
            values.put(KEY_NAME, user.getName());
            values.put(KEY_AGE, user.getAge());

            // Inserting Row
            long ret = db.insert(TABLE_NAME, null, values);

            if(ret == -1) {
                result = 0;
            } else {
                user.setUserType(1);
                result = 1;
            }
        }

        return result;
    }

    //Auth user
    public int authUser(User user) {
        int result = 0;

        if(getUserCount(user) != 1) {
            result = -1;
        } else {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_NAME, KEY_AGE}, KEY_USERNAME + "=? and " + KEY_PASSWORD + "=?",
                    new String[] { user.getUsername(), user.getPassword() }, null, null, null, null);

            if(cursor.getCount() != 1) {
                result = 0;
            } else {
                cursor.moveToFirst();
                user.update(cursor.getString(0), Integer.parseInt(cursor.getString(1)), 1);
                result = 1;
            }

            cursor.close();
        }

        return result;
    }

}
