package com.raghdak.wardm.smartcourier.SQL;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.raghdak.wardm.smartcourier.model.Courier;
import com.raghdak.wardm.smartcourier.model.Shipment;
import com.raghdak.wardm.smartcourier.protocol.response.LoginResponse;
import com.raghdak.wardm.smartcourier.protocol.response.RegionResponse;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wardm on 4/10/2018.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper mDatabaseHelper;
    private static Context mContext;
    private Courier currentCourier;

    ///  private int ShipmentID = 2;
    public Courier getCurrentCourier() {
        return currentCourier;
    }

    public void setCurrentCourier(Courier currentCourier) {
        this.currentCourier = currentCourier;
    }

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "SmartCourier.db";

    //Courier table name
    private static final String TABLE_COURIER = "couriers";
    //Courier Table Columns names
    private static final String COLUMN_COURIER_EMAIL = "email";
    private static final String COLUMN_COURIER_FIRSTNAME = "first_name";
    private static final String COLUMN_COURIER_LASTNAME = "last_name";
    private static final String COLUMN_COURIER_PASSWORD = "password";
    private static final String COLUMN_COURIER_ADDRESS = "address";
    private static final String COLUMN_COURIER_TELEPHONE = "telephone";
    //Shipments table name
    private static final String TABLE_SHIPMENT = "shipments";
    //Shipments Table Columns names
    private static final String COLUMN_SHIPMENT_ID = "id";
    private static final String COLUMN_SHIPMENT_LAT = "lat";
    private static final String COLUMN_SHIPMENT_LNG = "lng";
    private static final String COLUMN_SHIPMENT_SUBAREA = "subarea";
    private static final String COLUMN_SHIPMENT_ADDRESS = "address";
    private static final String COLUMN_SHIPMENT_AREA = "area";
    private static final String COLUMN_SHIPMENT_STATUS = "status";
    private static final String COLUMN_SHIPMENT_URGENT = "urgent";
    private static final String COLUMN_SHIPMENT_CLAIMANT = "claimant";
    private static final String COLUMN_SHIPMENT_NAME = "name";
    private static final String COLUMN_SHIPMENT_PHONE = "phone";
    private static final String COLUMN_SHIPMENT_BOX = "box";
    private static final String COLUMN_SHIPMENT_DUEDATE = "duedate";
    private static final String COLUMN_SHIPMENT_DATE = "date";
    private static final String COLUMN_SHIPMENT_NOTFOUND = "notfound";
    private static final String COLUMN_SHIPMENT_RECEIVERNAME = "receivername";
    private static final String COLUMN_SHIPMENT_FLOOR = "floor";
    private static final String COLUMN_SHIPMENT_ENTRANCE = "entrance";
    private static final String COLUMN_SHIPMENT_NUMOFFLOORS = "numoffloors";
    private static final String COLUMN_SHIPMENT_PRIVATEHOUSE = "privatehouse";
    private static final String COLUMN_SHIPMENT_SIGNED = "signed";
    private static final String COLUMN_SHIPMENT_PASTEDONDOOR = "pastedondoor";
    private static final String COLUMN_SHIPMENT_TEXT = "text";
    private static final String COLUMN_SHIPMENT_COURIERID = "courierid";
    //Image table name
    private static final String TABLE_IMAGE = "images";
    //Image Table Columns names
    private static final String COLUMN_IMAGE_SHIPMENTID = "shipmentid";
    private static final String COLUMN_IMAGE_PATH = "path";
    private static final String COLUMN_IMAGE_TEXT = "text";

    // create courier table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_COURIER + "("
            + COLUMN_COURIER_EMAIL + " TEXT PRIMARY KEY ," + COLUMN_COURIER_FIRSTNAME + " TEXT,"
            + COLUMN_COURIER_LASTNAME + " TEXT," + COLUMN_COURIER_PASSWORD + " TEXT,"
            + COLUMN_COURIER_ADDRESS + " TEXT," + COLUMN_COURIER_TELEPHONE + " TEXT" + ")";

    // drop courier table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_COURIER;
    // create shipments table sql query
    private String CREATE_SHIPMENTS_TABLE = "CREATE TABLE " + TABLE_SHIPMENT + "("
            + COLUMN_SHIPMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COLUMN_SHIPMENT_LAT + " INTEGER,"
            + COLUMN_SHIPMENT_LNG + " INTEGER,"
            + COLUMN_SHIPMENT_SUBAREA + " TEXT,"
            + COLUMN_SHIPMENT_ADDRESS + " TEXT,"
            + COLUMN_SHIPMENT_AREA + " TEXT,"
            + COLUMN_SHIPMENT_STATUS + " TEXT,"
            + COLUMN_SHIPMENT_URGENT + " TEXT,"
            + COLUMN_SHIPMENT_CLAIMANT + " TEXT,"
            + COLUMN_SHIPMENT_NAME + " TEXT,"
            + COLUMN_SHIPMENT_PHONE + " TEXT,"
            + COLUMN_SHIPMENT_BOX + " TEXT,"
            + COLUMN_SHIPMENT_DUEDATE + " INTEGER,"
            + COLUMN_SHIPMENT_DATE + " INTEGER,"
            + COLUMN_SHIPMENT_NOTFOUND + " TEXT,"
            + COLUMN_SHIPMENT_RECEIVERNAME + " TEXT,"
            + COLUMN_SHIPMENT_FLOOR + " TEXT,"
            + COLUMN_SHIPMENT_ENTRANCE + " TEXT,"
            + COLUMN_SHIPMENT_NUMOFFLOORS + " TEXT,"
            + COLUMN_SHIPMENT_PRIVATEHOUSE + " TEXT,"
            + COLUMN_SHIPMENT_SIGNED + " TEXT,"
            + COLUMN_SHIPMENT_PASTEDONDOOR + " TEXT,"
            + COLUMN_SHIPMENT_TEXT + " TEXT,"
            + COLUMN_SHIPMENT_COURIERID + " TEXT,"
            + " FOREIGN KEY(" + COLUMN_SHIPMENT_COURIERID + ") REFERENCES " + TABLE_COURIER + "(" + COLUMN_COURIER_EMAIL + ")"
            + ")";

    // drop shipments table sql query
    private String DROP_SHIPMENTS_TABLE = "DROP TABLE IF EXISTS " + TABLE_SHIPMENT;
    // create image table sql query
    private String CREATE_IMAGE_TABLE = "CREATE TABLE " + TABLE_IMAGE + "("
            + COLUMN_IMAGE_SHIPMENTID + " TEXT,"
            + COLUMN_IMAGE_PATH + " TEXT,"
            + COLUMN_IMAGE_TEXT + " TEXT,"
            + " FOREIGN KEY(" + COLUMN_IMAGE_SHIPMENTID + ") REFERENCES " + TABLE_SHIPMENT + "(" + COLUMN_SHIPMENT_ID + ")"
            + ")";

    // drop image table sql query
    private String DROP_IMAGE_TABLE = "DROP TABLE IF EXISTS " + TABLE_IMAGE;

    /**
     * getInstance - Since this is a singelton class we need it
     *
     * @param context
     * @return the singleton object of this class
     */

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (mDatabaseHelper == null) {
            mDatabaseHelper = new DatabaseHelper(context);
        }
        return mDatabaseHelper;
    }

    /**
     * Constructor
     *
     * @param context
     */
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_SHIPMENTS_TABLE);
        db.execSQL(CREATE_IMAGE_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_SHIPMENTS_TABLE);
        db.execSQL(DROP_IMAGE_TABLE);
        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param courier
     */
    public void addCourier(Courier courier) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Log.i("DataBase", "addCourier function called with Email: " + courier.getEmail() + ".");
        values.put(COLUMN_COURIER_EMAIL, courier.getEmail());
        values.put(COLUMN_COURIER_FIRSTNAME, courier.getFirstName());
        values.put(COLUMN_COURIER_LASTNAME, courier.getLastName());
        values.put(COLUMN_COURIER_PASSWORD, courier.getPassword());
        values.put(COLUMN_COURIER_ADDRESS, courier.getAddress());
        values.put(COLUMN_COURIER_TELEPHONE, courier.getTelephone());
        // Inserting Row
        db.insert(TABLE_COURIER, null, values);
        db.close();
    }

    /**
     * This method is to create shipment record
     *
     * @param shipment
     */
    public void addShipment(Shipment shipment) {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL(CREATE_SHIPMENTS_TABLE);
//        db.execSQL(DROP_USER_TABLE);
//        db.execSQL(DROP_SHIPMENTS_TABLE);
//        db.execSQL(DROP_IMAGE_TABLE);
        // Create tables again
//        onCreate(db);
        ContentValues values = new ContentValues();
        Log.i("DataBase", "addShipment function called with Shipment area: " + shipment.getArea() + ".");
        values.put(COLUMN_SHIPMENT_ID, shipment.getId());
        values.put(COLUMN_SHIPMENT_LAT, shipment.getLat());
        values.put(COLUMN_SHIPMENT_LNG, shipment.getLng());
        values.put(COLUMN_SHIPMENT_SUBAREA, shipment.getSubArea());
        values.put(COLUMN_SHIPMENT_ADDRESS, shipment.getAddress());
        values.put(COLUMN_SHIPMENT_AREA, shipment.getArea());
        values.put(COLUMN_SHIPMENT_URGENT, shipment.getUrgent());
        values.put(COLUMN_SHIPMENT_CLAIMANT, shipment.getClaimant());
        values.put(COLUMN_SHIPMENT_NAME, shipment.getName());
        values.put(COLUMN_SHIPMENT_PHONE, shipment.getPhone());
        values.put(COLUMN_SHIPMENT_BOX, shipment.getBox());
        values.put(COLUMN_SHIPMENT_DUEDATE, shipment.getDueDate().getTime());
        values.put(COLUMN_SHIPMENT_COURIERID, shipment.getCourierID());
        values.put(COLUMN_SHIPMENT_STATUS, "0");
        // Inserting Row
        db.insert(TABLE_SHIPMENT, null, values);
        db.close();
    }

    public void addImages(Shipment shipment) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        Log.i("DataBase", "add Images function called");
        for (int i = 0; i < shipment.getImages_path().size(); i++) {
            values.put(COLUMN_IMAGE_SHIPMENTID, shipment.getId());
            values.put(COLUMN_IMAGE_PATH, shipment.getImages_path().get(i));
            values.put(COLUMN_IMAGE_TEXT, shipment.getImages_text().get(i));
            db.insert(TABLE_IMAGE, null, values);
        }
        db.close();
    }

    public void addReport(Shipment shipment) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        Log.i("DataBase", "add Report function called");
        if (shipment.getNotFound().equals("1"))
            values.put(COLUMN_SHIPMENT_STATUS, "2");
        else
            values.put(COLUMN_SHIPMENT_STATUS, "1");
        values.put(COLUMN_SHIPMENT_NOTFOUND, shipment.getNotFound());
        values.put(COLUMN_SHIPMENT_RECEIVERNAME, shipment.getReceiverName());
        values.put(COLUMN_SHIPMENT_FLOOR, shipment.getFloor());
        values.put(COLUMN_SHIPMENT_ENTRANCE, shipment.getEntrance());
        values.put(COLUMN_SHIPMENT_NUMOFFLOORS, shipment.getNumOfFloors());
        values.put(COLUMN_SHIPMENT_PRIVATEHOUSE, shipment.getPrivateHouse());
        values.put(COLUMN_SHIPMENT_SIGNED, shipment.getSigned());
        values.put(COLUMN_SHIPMENT_PASTEDONDOOR, shipment.getPastedOnDoor());
        // Update Row
        ////     db.update(TABLE_SHIPMENT,values, "id="+COLUMN_SHIPMENT_ID,null );
        db.update(TABLE_SHIPMENT, values, "id= " + shipment.getId(), null);
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public LoginResponse checkUser(String email, String password) {
        Log.i("DataBase", "PATH:" + mContext.getDatabasePath("SmartCourier.db"));
        Log.i("DataBase", "checkUser function called with Email: " + email + ",Password: " + password + ".");
        // array of columns to fetch
        String[] columns = {
                COLUMN_COURIER_EMAIL, COLUMN_COURIER_FIRSTNAME, COLUMN_COURIER_LASTNAME, COLUMN_COURIER_PASSWORD,
                COLUMN_COURIER_ADDRESS, COLUMN_COURIER_TELEPHONE
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_COURIER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_COURIER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        if (cursorCount == 0) {
            return LoginResponse.NO_User();
        } else {
            cursor.moveToFirst();
            String pass = cursor.getString(cursor.getColumnIndex(COLUMN_COURIER_PASSWORD));
            if (!password.equals(pass)) {
                return LoginResponse.WRONG_PASSWORD();
            } else {
                Courier courier = new Courier(email, password);
                courier.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_COURIER_FIRSTNAME)));
                courier.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_COURIER_LASTNAME)));
                courier.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_COURIER_ADDRESS)));
                courier.setTelephone(cursor.getString(cursor.getColumnIndex(COLUMN_COURIER_TELEPHONE)));
                cursor.close();
                db.close();
                LoginResponse response = LoginResponse.OK();
                response.setCourier(courier);
                setCurrentCourier(courier);
                return response;
            }
        }
    }

    public RegionResponse getRegionShipments(String region) {
        Log.i("DataBase", "getRegionShipments function called with Region: " + region + ".");
        ArrayList<Shipment> shipments = new ArrayList<Shipment>();
        // array of columns to fetch
        String[] columns = {
                COLUMN_SHIPMENT_ID, COLUMN_SHIPMENT_LAT, COLUMN_SHIPMENT_LNG, COLUMN_SHIPMENT_AREA,
                COLUMN_SHIPMENT_SUBAREA, COLUMN_SHIPMENT_ADDRESS, COLUMN_SHIPMENT_STATUS,
                COLUMN_SHIPMENT_COURIERID, COLUMN_SHIPMENT_CLAIMANT, COLUMN_SHIPMENT_URGENT,
                COLUMN_SHIPMENT_NAME, COLUMN_SHIPMENT_PHONE, COLUMN_SHIPMENT_BOX, COLUMN_SHIPMENT_DUEDATE
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_SHIPMENT_STATUS + " = '0' AND " + COLUMN_SHIPMENT_AREA + " = ?";

        // selection argument
        String[] selectionArgs = {region};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_SHIPMENT, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        if (cursorCount == 0) {
            return RegionResponse.NO_Shipment();
        } else {
            RegionResponse response = RegionResponse.OK();
            cursor.moveToFirst();
            for (int i = 0; i < cursorCount; i++) {
                Shipment shipment = new Shipment(
                        cursor.getDouble(cursor.getColumnIndex(COLUMN_SHIPMENT_LAT)),
                        cursor.getDouble(cursor.getColumnIndex(COLUMN_SHIPMENT_LNG)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_AREA)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_SUBAREA)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_ADDRESS)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_URGENT)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_CLAIMANT)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_PHONE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_BOX)),
                        new Date(cursor.getInt(cursor.getColumnIndex(COLUMN_SHIPMENT_DUEDATE))),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_COURIERID)));
                shipment.setId(cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_ID)));
                shipments.add(shipment);
                cursor.moveToNext();
            }
            response.setShipments(shipments);
            return response;
        }
    }

    public int countAllShipments() {
        Log.i("DataBase", "countAllShipments!");
        ArrayList<Shipment> shipments = new ArrayList<Shipment>();
        // array of columns to fetch
        String[] columns = {
                COLUMN_SHIPMENT_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_SHIPMENT, //Table to query
                columns,                    //columns to return
                null,                  //columns for the WHERE clause
                null,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        return cursorCount;
    }

    public int countDeliveredShipments() {
        Log.i("DataBase", "countDeliveredShipments!");
        ArrayList<Shipment> shipments = new ArrayList<Shipment>();
        // array of columns to fetch
        String[] columns = {
                COLUMN_SHIPMENT_ID, COLUMN_SHIPMENT_STATUS
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_SHIPMENT_STATUS + " = '1'";
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_SHIPMENT, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                null,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        return cursorCount;
    }

    public ArrayList<Shipment> getAllShipments() {

        Log.i("DataBase", "getAllShipments!");
        ArrayList<Shipment> shipments = new ArrayList<Shipment>();
        // array of columns to fetch
        String[] columns = {
                COLUMN_SHIPMENT_ID, COLUMN_SHIPMENT_LAT, COLUMN_SHIPMENT_LNG, COLUMN_SHIPMENT_AREA,
                COLUMN_SHIPMENT_SUBAREA, COLUMN_SHIPMENT_ADDRESS, COLUMN_SHIPMENT_STATUS,
                COLUMN_SHIPMENT_COURIERID, COLUMN_SHIPMENT_CLAIMANT, COLUMN_SHIPMENT_URGENT,
                COLUMN_SHIPMENT_NAME, COLUMN_SHIPMENT_PHONE, COLUMN_SHIPMENT_BOX, COLUMN_SHIPMENT_DUEDATE
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_SHIPMENT_STATUS + " = '0'";
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_SHIPMENT, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                null,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        if (cursorCount == 0) {
            return null;
        } else {
            cursor.moveToFirst();
            for (int i = 0; i < cursorCount; i++) {
                Shipment shipment = new Shipment(
                        cursor.getDouble(cursor.getColumnIndex(COLUMN_SHIPMENT_LAT)),
                        cursor.getDouble(cursor.getColumnIndex(COLUMN_SHIPMENT_LNG)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_AREA)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_SUBAREA)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_ADDRESS)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_URGENT)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_CLAIMANT)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_PHONE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_BOX)),
                        new Date(cursor.getInt(cursor.getColumnIndex(COLUMN_SHIPMENT_DUEDATE))),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_COURIERID)));
                shipment.setId(cursor.getString(cursor.getColumnIndex(COLUMN_SHIPMENT_ID)));
                shipments.add(shipment);
                cursor.moveToNext();
            }
            return shipments;
        }
    }
}
