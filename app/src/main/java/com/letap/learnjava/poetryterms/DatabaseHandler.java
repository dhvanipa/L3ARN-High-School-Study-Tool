package com.letap.learnjava.poetryterms;

/**
 * Created by dhvani on 2016-06-04.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dhvani on 30/04/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "l3arn_data";

    // Contacts table name
    private static final String TABLE_CONTACTS = "data_l3arn";

    public static final String TABLE_PHY = "data_phy_l3arn";
    public static final String TABLE_BIO = "data_bio_l3arn";
    public static final String TABLE_CHEM = "data_chem_l3arn";
    public static final String TABLE_GEO = "data_geo_l3arn";
    public static final String TABLE_COMPSCI = "data_compsci_l3arn";
    public static final String TABLE_MATH = "data_math_l3arn";
    public static final String TABLE_SOCIAL = "data_social_l3arn";
    public static final String TABLE_POET = "data_poet_l3arn";


    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "title";
    private static final String KEY_PH_NO = "thumbnail_url";
    private static final String KEY_DESC = "desc";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT," + KEY_DESC + " DESC" + ")";

        String CREATE_PHY_TABLE = "CREATE TABLE " + TABLE_PHY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT," + KEY_DESC + " DESC" + ")";

        String CREATE_CHEM_TABLE = "CREATE TABLE " + TABLE_CHEM + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT," + KEY_DESC + " DESC" + ")";

        String CREATE_BIO_TABLE = "CREATE TABLE " + TABLE_BIO + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT," + KEY_DESC + " DESC" + ")";

        String CREATE_GEO_TABLE = "CREATE TABLE " + TABLE_GEO + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT," + KEY_DESC + " DESC" + ")";

        String CREATE_COMPSCI_TABLE = "CREATE TABLE " + TABLE_COMPSCI + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT," + KEY_DESC + " DESC" + ")";

        String CREATE_SOCIAL_TABLE = "CREATE TABLE " + TABLE_SOCIAL + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT," + KEY_DESC + " DESC" + ")";

        String CREATE_MATH_TABLE = "CREATE TABLE " + TABLE_MATH + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT," + KEY_DESC + " DESC" + ")";

        String CREATE_POET_TABLE = "CREATE TABLE " + TABLE_POET + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT," + KEY_DESC + " DESC" + ")";


        db.execSQL(CREATE_CONTACTS_TABLE);

        db.execSQL(CREATE_PHY_TABLE);
        db.execSQL(CREATE_CHEM_TABLE);
        db.execSQL(CREATE_BIO_TABLE);
        db.execSQL(CREATE_GEO_TABLE);
        db.execSQL(CREATE_COMPSCI_TABLE);
        db.execSQL(CREATE_SOCIAL_TABLE);
        db.execSQL(CREATE_MATH_TABLE);
        db.execSQL(CREATE_POET_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GEO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPSCI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOCIAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POET);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addContact(Movie movie, String section) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, movie.getTitle()); // Contact Name
        values.put(KEY_PH_NO, movie.getThumbnailUrl()); // Contact Phone
        values.put(KEY_DESC, movie.getDesc());

        // Inserting Row
        db.insert(section, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Movie getContact(int id, String section) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(section, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO, KEY_DESC }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Movie contact = new Movie(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return contact
        return contact;
    }

    // Getting All Contacts
    public List<Movie> getAllContacts(String section) {
        List<Movie> contactList = new ArrayList<Movie>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + section;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Movie contact = new Movie();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setTitle(cursor.getString(1));
                contact.setThumbnailUrl(cursor.getString(2));
                contact.setDesc(cursor.getString(3));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
    public int updateContact(Movie contact, String section) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getTitle());
        values.put(KEY_PH_NO, contact.getThumbnailUrl());
        values.put(KEY_DESC, contact.getDesc());

        // updating row
        return db.update(section, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteContact(Movie contact, String section) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(section, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount(String section) {
        String countQuery = "SELECT  * FROM " + section;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
