package com.planetsystems.tela.DataStorage;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.planetsystems.tela.Models.SystemUsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "TeLa_DB";

    public static final String TABLE_USERS = "System_users";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATABASE_ID = "database_id";
    public static final String COLUMN_EMPLOYEE_NUMBER = "employeeNumber";
    public static final String COLUMN_FIRST_NAME = "firstName";
    public static final String COLUMN_LAST_NAME = "lastName";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_SCHOOL_ID = "school_id";
    public static final String COLUMN_SCHOOL_NAME = "schoolName";
    public static final String COLUMN_DEVICE_ID = "device_id";

    public static final String CREATE_TABLE =
            "create table " + TABLE_USERS + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DATABASE_ID + " TEXT,"
                    + COLUMN_EMPLOYEE_NUMBER + " TEXT"
                    + COLUMN_FIRST_NAME + "TEXT,"
                    + COLUMN_LAST_NAME + "TEXT,"
                    + COLUMN_ROLE + "TEXT,"
                    + COLUMN_SCHOOL_ID + "TEXT,"
                    + COLUMN_SCHOOL_NAME + "TEXT,"
                    + COLUMN_DEVICE_ID + "INTEGER"
                    + ")";

    private Resources mResources;
    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mResources = context.getResources();
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        Log.d(LOG, "Db created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        // Drop older table if existed
        db.execSQL(" DROP TABLE IF EXISTS " + SystemUsers.TABLE_USERS);

        // Create tables again
        onCreate(db);

    }

//    public long insertSystemUsers(String Database_id, String EmployeeNumber,String FirstName, String LastName, String Role,
//                                  String School_id, String SchoolName, int Device_id) {
//        // get writable database as we want to write data
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//
//        values.put(SystemUsers.COLUMN_DATABASE_ID, Database_id);
//        values.put(SystemUsers.COLUMN_EMPLOYEE_NUMBER, EmployeeNumber);
//        values.put(SystemUsers.COLUMN_FIRST_NAME, FirstName);
//        values.put(SystemUsers.COLUMN_LAST_NAME, LastName);
//        values.put(SystemUsers.COLUMN_ROLE, Role);
//        values.put(SystemUsers.COLUMN_SCHOOL_ID, School_id);
//        values.put(SystemUsers.COLUMN_SCHOOL_NAME, SchoolName);
//        values.put(SystemUsers.COLUMN_DEVICE_ID, Device_id);
//
//        // insert row
//        long id = db.insert(SystemUsers.TABLE_USERS, null, values);
//
//        // close db connection
//        db.close();
//
//        // return newly inserted row id
//        return id;
//    }

//    private void insertSystemUsers(SQLiteDatabase db) throws IOException, JSONException{
//        final String DATABASE_ID = "database_id";
//        final String EMPLOYEE_NUMBER = "employeeNumber";
//        final String FIRST_NAME = "firstName";
//        final String LAST_NAME = "lastName";
//        final String ROLE = "role";
//        final String SCHOOL_ID = "school_id";
//        final String SCHOOL_NAME = "schoolName";
//        final String DEVICE_ID = "device_id";
//
//        try {
//            String jsonDataString = userInfoFromJson();
//            JSONArray usersJsonArray = new JSONArray(jsonDataString);
//
//            for (int i = 0; i < usersJsonArray.length(); i++){
//                String database_id;
//                String employeeNumber;
//                String firstName;
//                String lastName;
//                String role;
//                String school_id;
//                String schoolName;
//                String device_id;
//
//                JSONObject userJsonObject = usersJsonArray.getJSONObject(i);
//
//                database_id = userJsonObject.getString(DATABASE_ID);
//                employeeNumber = userJsonObject.getString(EMPLOYEE_NUMBER);
//                firstName = userJsonObject.getString(FIRST_NAME);
//                lastName = userJsonObject.getString(LAST_NAME);
//                role = userJsonObject.getString(ROLE);
//                school_id = userJsonObject.getString(SCHOOL_ID);
//                schoolName = userJsonObject.getString(SCHOOL_NAME);
//                device_id = userJsonObject.getString(DEVICE_ID);
//
//                ContentValues values = new ContentValues();
//
//                values.put(SystemUsers.COLUMN_DATABASE_ID, database_id);
//                values.put(SystemUsers.COLUMN_EMPLOYEE_NUMBER, employeeNumber);
//                values.put(SystemUsers.COLUMN_FIRST_NAME, firstName);
//                values.put(SystemUsers.COLUMN_LAST_NAME, lastName);
//                values.put(SystemUsers.COLUMN_ROLE, role);
//                values.put(SystemUsers.COLUMN_SCHOOL_ID, school_id);
//                values.put(SystemUsers.COLUMN_SCHOOL_NAME, schoolName);
//                values.put(SystemUsers.COLUMN_DEVICE_ID, device_id);
//
//                // insert row
//                db.insert(SystemUsers.TABLE_USERS, null, values);
//                // close db connection
//                db.close();
//                Log.d(LOG, "inserted successfully");
//            }
//        }catch (JSONException e){
//            Log.e(LOG, e.getMessage(), e);
//            e.printStackTrace();
//        }
//    }
//
//    private String userInfoFromJson() throws IOException{
//        InputStream inputStream = null;
//        StringBuilder builder = new StringBuilder();
//
//        try {
//            String jsonDataString = null;
//            inputStream = new URL("http://tela.planetsystems.co:8080/weca/webapi/tasks/siteemployees/019").openStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//
//            while ((jsonDataString = bufferedReader.readLine()) != null){
//                builder.append(jsonDataString);
//            }
//        }finally {
//            if (inputStream != null){
//                inputStream.close();
//            }
//        }
//
//        return new String(builder);
//    }
//
    public Cursor readFromLocalDatabase(SQLiteDatabase db){

        String[] projection = {SystemUsers.COLUMN_SCHOOL_NAME};

        return (db.query(SystemUsers.TABLE_USERS, projection, null, null, null,
                null, null));
    }

    public Cursor school_Name(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from System_users",null);
        return cursor;
    }
}
