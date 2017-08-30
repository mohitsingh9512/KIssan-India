package farmer.farmer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "MyCrops";

    // Table columns
    public static final String _ID = "_id";
    public static final String KEY_NAME="CropName";
    public static final String KEY_VARIETY="Variety";
    public static final String KEY_DATE_H="DateOfHarvest";
    public static final String KEY_QUANTITY="Quantity";
    public static final String KEY_EXP_PRICE="Price";

    // Database Information
    static final String DB_NAME = "Farmer.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + " TEXT NOT NULL, "
            + KEY_VARIETY + " TEXT NOT NULL, "
            + KEY_DATE_H + " TEXT NOT NULL, "
            + KEY_QUANTITY + " TEXT NOT NULL, "
            + KEY_EXP_PRICE + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
