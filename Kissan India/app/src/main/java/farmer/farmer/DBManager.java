package farmer.farmer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name ,String variety,String dateH ,String quantity, String price) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.KEY_NAME, name);
        contentValue.put(DatabaseHelper.KEY_VARIETY, variety);
        contentValue.put(DatabaseHelper.KEY_DATE_H, dateH);
        contentValue.put(DatabaseHelper.KEY_QUANTITY, quantity);
        contentValue.put(DatabaseHelper.KEY_EXP_PRICE, price);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.KEY_NAME, DatabaseHelper.KEY_VARIETY, DatabaseHelper.KEY_DATE_H , DatabaseHelper.KEY_QUANTITY , DatabaseHelper.KEY_EXP_PRICE  };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id,String name,String variety ,String dateH ,String quantity, String price) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_NAME, name);
        contentValues.put(DatabaseHelper.KEY_VARIETY, variety);
        contentValues.put(DatabaseHelper.KEY_DATE_H, dateH);
        contentValues.put(DatabaseHelper.KEY_QUANTITY, quantity);
        contentValues.put(DatabaseHelper.KEY_EXP_PRICE, price);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}
