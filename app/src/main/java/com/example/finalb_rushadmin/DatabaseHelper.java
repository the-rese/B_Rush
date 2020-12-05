package com.example.finalb_rushadmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "B-rushDatabase.db";
    private static int DB_VERSION = 1;

    //list of tables in B-rush database
    private static final String TABLE_PERSON = "Person";
    private static final String TABLE_DRIVER = "Driver";
    private static final String TABLE_USER = "User";
    private static final String TABLE_ADMIN = "Admin";
    private static final String TABLE_BUS = "Bus";
    private static final String TABLE_BUS_SCHEDULE = "Bus_Schedule" ;
    private static final String TABLE_BUS_SEAT ="Bus_Seat";
    private static final String TABLE_BUS_STOP = "Bus_Stop";
    private static final String TABLE_GCASH = "Gcash" ;
    private static final String TABLE_PAYMENT = "Payment";
    private static final String  TABLE_TICKET = "Ticket" ;

    //columm list of all foreign keys
    private static final String COLUMN_FK_PERSON = "PersonID";
    private static final String COLUMN_FK_DRIVER = "DriverID";
    private static final String COLUMN_FK_ROUTE = "RouteID";
    private static final String COLUMN_FK_SEAT = "SeatID";
    private static final String COLUMN_FK_USER = "UserID";
    private static final String COLUMN_FK_PAYMENT = "PaymentID";

    //list of common columns for all table
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_ADDRESS = "Address";


    //column list of Person table
    private static final String COLUMN_FNAME = "FirstName";
    private static final String COLUMN_MNAME = "MiddleName";
    private static final String COLUMN_LNAME = "LastName";
    private static final String COLUMN_BDAY = "Birthday";
    private static final String COLUMN_CONTACT_NUM = "Contact_Number";

    //column list of User & Admin Table
    private static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_PASSWORD = "Password";

    //column list of Bus Table
    private static final String COLUMN_PLATE_NUMBER = "Plate_number";

    //column list of Bus Seat
    private static final String COLUMN_SEAT_NUMBER = "Seat_Number";
    private static final String COLUMN_STATUS = "Status";

    //column list of Bus Stop
    private static final String COLUMN_DESTINATION = "Destination";

    //column list of Bus Schedule
    private static final String COLUMN_TIME = "Time";

    //column list of Gcash
    private static final String COLUMN_PHONE_NUMBER = "Phone_number";
    private static final String COLUMN_REFERENCE_NUMBER = "Reference_number";

    //column list of Payments
    private static final String COLUMN_AMOUNT = "Amount";

    //column list of Tickets
    //private static final String COLUMN_SEAT_NUMBER = "Seat Number";
    // private static final String COLUMN_STATUS = "Status";
    private static final String COLUMN_ISCANCELLED = "IsCancelled";

    //Strings to create the tables
    private static final String CREATE_TABLE_PERSON ="CREATE TABLE "+TABLE_PERSON+"("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +COLUMN_FNAME+" TEXT, "
            +COLUMN_MNAME+" TEXT, "+COLUMN_LNAME+" TEXT, "+COLUMN_ADDRESS+" TEXT, "+COLUMN_BDAY+" NUMERIC, "+COLUMN_CONTACT_NUM+"TEXT)";
    private static final String CREATE_TABLE_DRIVER = "CREATE TABLE "+TABLE_DRIVER+" ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_FK_PERSON+
            " INTEGER,FOREIGN KEY("+COLUMN_FK_PERSON+") REFERENCES "+TABLE_PERSON+"("+COLUMN_ID+"))";
    private static final String CREATE_TABLE_USER = "CREATE TABLE "+TABLE_USER+" ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_USERNAME+
            " TEXT, "+COLUMN_PASSWORD+ " TEXT, "+COLUMN_FK_PERSON+" INTEGER,FOREIGN KEY("+COLUMN_FK_PERSON+") REFERENCES "+TABLE_PERSON+"("+COLUMN_ID+"))";
    public static final String CREATE_TABLE_ADMIN ="CREATE TABLE "+TABLE_ADMIN+"("+COLUMN_ID+"	INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_FK_PERSON+
            " INTEGER,"+COLUMN_USERNAME+"	TEXT,"+COLUMN_PASSWORD+"	TEXT,FOREIGN KEY("+COLUMN_FK_PERSON+") REFERENCES "+TABLE_PERSON+"("+COLUMN_ID+"))";
    public static final String CREATE_TABLE_BUS ="CREATE TABLE "+TABLE_BUS+" ("+COLUMN_ID+"	INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_FK_DRIVER+
            "	INTEGER ,"+COLUMN_FK_ROUTE+" INTEGER, "+COLUMN_PLATE_NUMBER+"INTEGER , FOREIGN KEY("+COLUMN_FK_ROUTE+") REFERENCES "+TABLE_BUS_STOP+
            "("+COLUMN_ID+") , FOREIGN KEY("+COLUMN_FK_DRIVER+") REFERENCES "+TABLE_DRIVER+"("+COLUMN_ID+"))";
    public static final String CREATE_TABLE_BUS_SCHEDULE =" CREATE TABLE "+TABLE_BUS_SCHEDULE+ "( "+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_TIME+" INTEGER, "+COLUMN_FK_ROUTE+" INTEGER ,FOREIGN KEY("+COLUMN_FK_ROUTE+" )REFERENCES "+TABLE_BUS_STOP+"("+COLUMN_ID+"))";
    public static final String CREATE_TABLE_BUS_SEAT="CREATE TABLE "+TABLE_BUS_SEAT+"("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_SEAT_NUMBER+
            "INTEGER,"+COLUMN_STATUS+"TEXT)";
    public static final String CREATE_TABLE_BUS_STOP = "CREATE TABLE "+TABLE_BUS_STOP+"("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_DESTINATION+"TEXT)";
    public static final String CREATE_TABLE_GCASH = "CREATE TABLE "+TABLE_GCASH+" ("+COLUMN_ID+"	INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_PHONE_NUMBER+ "INTEGER,"+COLUMN_REFERENCE_NUMBER+"INTEGER)";
    public static final String CREATE_TABLE_PAYMENT ="CREATE TABLE "+TABLE_PAYMENT+" ("+COLUMN_ID+"	INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_AMOUNT+"	INTEGER)";
    public static final String CREATE_TABLE_TICKET = "CREATE TABLE "+TABLE_TICKET+" ("+COLUMN_ID+"	INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_FK_USER+" INTEGER,"
            +COLUMN_FK_PAYMENT+" INTEGER,"+COLUMN_FK_SEAT+"	INTEGER,"+COLUMN_SEAT_NUMBER+"INTEGER,"+COLUMN_STATUS+"	TEXT,"+COLUMN_ISCANCELLED+"	Boolean," +
            "FOREIGN KEY("+COLUMN_FK_PAYMENT+") REFERENCES "+TABLE_PAYMENT+"("+COLUMN_ID+"),FOREIGN KEY("+COLUMN_FK_USER+") REFERENCES "+TABLE_USER+
            "("+COLUMN_ID+"),FOREIGN KEY("+COLUMN_FK_SEAT+") REFERENCES " +TABLE_BUS_SEAT+ " ("+COLUMN_ID+"))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSON);
        db.execSQL(CREATE_TABLE_DRIVER);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_BUS);
        db.execSQL(CREATE_TABLE_ADMIN);
        db.execSQL(CREATE_TABLE_BUS_SCHEDULE);
        db.execSQL(CREATE_TABLE_BUS_SEAT);
        db.execSQL(CREATE_TABLE_BUS_STOP);
        db.execSQL(CREATE_TABLE_GCASH );
        db.execSQL(CREATE_TABLE_PAYMENT);
        db.execSQL(CREATE_TABLE_TICKET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PERSON);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_DRIVER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BUS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BUS_SCHEDULE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BUS_SEAT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BUS_STOP);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_GCASH );
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PAYMENT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TICKET);
        onCreate(db);
    }

    // Insert, Update, get , and delete kay example methods para nila
   /* public boolean insertData(String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }*/

    public boolean insertPerson(String fname, String mname, String lname, String add, String bday, String num)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FNAME, fname);
        contentValues.put(COLUMN_MNAME, mname);
        contentValues.put(COLUMN_LNAME, lname);
        contentValues.put(COLUMN_ADDRESS, add);
        contentValues.put(COLUMN_BDAY, bday);
        contentValues.put(COLUMN_CONTACT_NUM, num);
        long res = db.insert(TABLE_PERSON, null, contentValues);
        if(res == -1){ return false; }
        else { return true; }
    }
    /*public boolean insertBusDriver(String fname, String mname, String lname, String add, String bday, String num)
    {
        long personID = insertPerson(fname, mname, lname, add, bday, num);
        if(personID == -1) {return false; }
        else
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues value = new ContentValues();
            value.put(COLUMN_FK_PERSON, personID);
            long result = db.insert(TABLE_DRIVER, null, value);
            if(result == -1) { return false; }
            else { return true; }
        }
    }*/

   /* public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }*/

}
