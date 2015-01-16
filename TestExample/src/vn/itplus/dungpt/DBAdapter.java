package vn.itplus.dungpt;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DBAdapter {
        // Khai bao CSDL
        static final String DATABASE_NAME = "MyDB";
        static final int DATABASE_VERSION = 1;
        // Khai bao ten Bang se tao ra
        static final String TABLE_ACCOUNT = "YeuThich";
        // Khai bao cac cot trong bang
        static final String NAME = "name";
        static final String PHONE = "phone";
        // Khai bao lenh tao ra CAC bang trong CSDL
        static final String DATABASE_CREATE 
                = "CREATE TABLE " + TABLE_ACCOUNT
                        + "name     TEXT NOT NULL,"
                        + "phone     TEXT NOT NULL";
 
        String[] allColumns = {NAME, PHONE};

		// Lop Helper
		private static class DatabaseHelper extends SQLiteOpenHelper {
			DatabaseHelper(Context context) {
				super(context, DATABASE_NAME, null, DATABASE_VERSION);
			}
	
			@Override
			public void onCreate(SQLiteDatabase db) {
				try {
					db.execSQL(DATABASE_CREATE);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	
			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
				onCreate(db);
			}
		}
 
        final Context context;
        DatabaseHelper DBHelper;
        SQLiteDatabase db;
        // Constructor
        public DBAdapter(Context ctx) {
            this.context = ctx;
            DBHelper = new DatabaseHelper(context);
        }
 
        // ---opens the database---
        public DBAdapter open() throws SQLException {
            db = DBHelper.getWritableDatabase();
            return this;
        }
 
        // ---closes the database---
        public void close() {
            DBHelper.close();
        }
 
        // ---insert a Account into the database---
        public long insertAccount(String name, String phone) {
            ContentValues c = new ContentValues();
            c.put(NAME, name);
            c.put(PHONE, phone);
            return db.insert(TABLE_ACCOUNT, null, c);
        }
 
        // ---Lay tat ca Accounts---
        public Cursor getAllAccount() {
            return db.query(TABLE_ACCOUNT, allColumns, null, null, null, null, null);
        }
}
 
