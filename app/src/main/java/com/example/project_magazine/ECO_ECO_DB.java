package com.example.project_magazine;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ECO_ECO_DB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "eco_eco_database";
    public static final Integer DATABASE_VERSION = 1;
    public static final String TABLE_USERS = "USERS";
    public static final String TABLE_ARTICLES = "ARTICLES";
    public static final String USERS_COL_1 = "ID";
    public static final String USERS_COL_2 = "EMAIL";
    public static final String USERS_COL_3 = "PASSWORD";
    public static final String USERS_COL_4 = "PHONE_NUMBER";
    public static final String USERS_COL_5 = "USERNAME";
    public static final String ARTICLE_AUTHOR = "ARTICLE_AUTHOR";
    public static final String ARTICLES_TITLE = "ARTICLE_TITLE";
    public static final String ARTICLE_PARA_A = "ARTICLE_PARA_A";
    public static final String ARTICLE_PARA_B = "ARTICLE_PARA_B";
    public static final String ARTICLES_IMAGE = "ARTICLE_IMAGE";
    public static final String ARTICLES_TYPE = "ARTICLE_TYPE";

    public ECO_ECO_DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTableQuery = "CREATE TABLE " + TABLE_USERS + " (" +
                USERS_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                USERS_COL_2 + " TEXT," +
                USERS_COL_3 + " TEXT," +
                USERS_COL_4 + " TEXT," +
                USERS_COL_5 + " TEXT)";

        db.execSQL(createUserTableQuery);

        String createArticlesTableQuery = "CREATE TABLE " + TABLE_ARTICLES + " (" +
                USERS_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ARTICLE_AUTHOR + " TEXT," +
                ARTICLES_TITLE + " TEXT," +
                ARTICLE_PARA_A + " TEXT," +
                ARTICLE_PARA_B + " TEXT," +
                ARTICLES_TYPE + " TEXT," +
                ARTICLES_IMAGE + " BLOB)";

        db.execSQL(createArticlesTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTICLES);
        onCreate(db);
    }

    public boolean insertUser(String email, String password, String phoneNumber, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COL_2, email);
        contentValues.put(USERS_COL_3, password);
        contentValues.put(USERS_COL_4, phoneNumber);
        contentValues.put(USERS_COL_5, username);
        long result = db.insert(TABLE_USERS, null, contentValues);
        return result != -1;
    }

    public boolean checkIfUserExists(String emailId, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_USERS + " Where " + USERS_COL_2 + "= '" + emailId + "' and " + USERS_COL_3 + "= '" + password + "'";
        try {
            Cursor cursor = db.rawQuery(query, null);
            Log.d("query", "query " + cursor.moveToNext());
            String name = cursor.getString(1);
            String pass = cursor.getString(2);
            Log.d("NAME", "name " + name);
            Log.d("PASS", "pass " + pass);
            return name.equals(emailId) && password.equals(pass);
        } catch (CursorIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean insertArticle(String articleAuthor, String articleTitle, String articleParaA, String articleParaB, byte[] articleImage, String articleType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ARTICLE_AUTHOR, articleAuthor);
        contentValues.put(ARTICLES_TITLE, articleTitle);
        contentValues.put(ARTICLE_PARA_A, articleParaA);
        contentValues.put(ARTICLE_PARA_B, articleParaB);
        contentValues.put(ARTICLES_TYPE, articleType);
        contentValues.put(ARTICLES_IMAGE, articleImage);
        long result = db.insert(TABLE_ARTICLES, null, contentValues);
        return result != -1;
    }

    public Cursor getArticles(String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ARTICLES;
        if (type != null && !type.isEmpty()) {
            query += " WHERE " + ARTICLES_TYPE + " = ?";
        }
        return db.rawQuery(query, (type != null && !type.isEmpty()) ? new String[]{type} : null);
    }

    public Cursor fetchUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {USERS_COL_5, USERS_COL_4, USERS_COL_2};
        String selection = USERS_COL_2 + " = ?";
        String[] selectionArgs = {email};
        return db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
    }




    public Cursor fetchUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {USERS_COL_2, USERS_COL_4, USERS_COL_5};
        String selection = USERS_COL_2 + " = ? AND " + USERS_COL_3 + " = ?";
        String[] selectionArgs = {email, password};
        return db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
    }

    public Cursor fetchUsersArticles(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ARTICLES;
        if (username != null && !username.isEmpty()) {
            query += " WHERE " + ARTICLE_AUTHOR + " = ?";
        }
        return db.rawQuery(query, (username != null && !username.isEmpty()) ? new String[]{username} : null);
    }
}