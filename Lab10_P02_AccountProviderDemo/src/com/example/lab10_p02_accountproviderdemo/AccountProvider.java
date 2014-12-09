package com.example.lab10_p02_accountproviderdemo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class AccountProvider extends ContentProvider {

	// Khai bao Database de luu tru thong tin cac Account
	public static final String DATABASE_NAME = "AccountDB";
	public static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_ACCOUNT = "Account";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "Username";
	public static final String COLUMN_ADDRESS = "Address";
	public static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_ACCOUNT + " ( " 
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_NAME + " TEXT NOT NULL, "
			+ COLUMN_ADDRESS + " TEXT NOT NULL);";
	
	private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
			onCreate(db);
		}
	}

	SQLiteDatabase db;
	@Override
	public boolean onCreate() {
		Context c = getContext();
		DatabaseHelper dbHelper = new DatabaseHelper(c);
		db = dbHelper.getWritableDatabase();
		return db != null;
	}

	// Khai bao ContentProvider
	private static final String PROVIDER_NAME = "com.example.provider.Accounts";
	public static final Uri ACCOUNT_URI = Uri.parse("content://" + PROVIDER_NAME + "/accounts");
	private static final int ACCOUNT_ALL = 1;
	private static final int ACCOUNT_ID = 2;
	private static final UriMatcher uriMatcher;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(PROVIDER_NAME, "accounts", ACCOUNT_ALL);
		uriMatcher.addURI(PROVIDER_NAME, "accounts/#", ACCOUNT_ID);
	}
	
	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
			case ACCOUNT_ALL:
				return "vnd.android.cursor.dir/";
			case ACCOUNT_ID:
				return "vnd.android.cursor.item";	
			default: return "Khong ho tro Uri: " + uri;
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(TABLE_ACCOUNT);
		if(uriMatcher.match(uri) == ACCOUNT_ID) {
			queryBuilder.appendWhere(COLUMN_ID + " = " + uri.getPathSegments().get(1));
		}
		Cursor c = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public Uri insert(Uri uri, ContentValues cv) {
		// tra ve id cua ban ghi vua insert vao Bang, neu insert that bai, tra ve -1
		long rowID = db.insert(TABLE_ACCOUNT, null, cv);
		if(rowID > 0) {
			Uri _uri = ContentUris.withAppendedId(ACCOUNT_URI, rowID);
			getContext().getContentResolver().notifyChange(_uri, null);
			return _uri;
		}
		throw new SQLException("Loi khi them tai khoan: " + uri);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int count = 0;
		switch (uriMatcher.match(uri)) {
			case ACCOUNT_ALL:
				count = db.delete(TABLE_ACCOUNT, selection, selectionArgs);
				break;
			case ACCOUNT_ID:
				String sel = COLUMN_ID + " = " + uri.getPathSegments().get(1);
				selection += (selection == null || selection.trim().isEmpty())? "" : " AND (" + sel + ")";
				count = db.delete(TABLE_ACCOUNT, selection, selectionArgs);
				break;	
			default: throw new IllegalArgumentException("Tham so khong hop le: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		int count = 0;
		switch (uriMatcher.match(uri)) {
			case ACCOUNT_ALL:
				count = db.update(TABLE_ACCOUNT, values, selection, selectionArgs);
				break;
			case ACCOUNT_ID:
				String sel = COLUMN_ID + " = " + uri.getPathSegments().get(1);
				selection += (selection == null || selection.trim().isEmpty())? "" : " AND (" + sel + ")";
				count = db.update(TABLE_ACCOUNT, values, selection, selectionArgs);
				break;	
			default: throw new IllegalArgumentException("Tham so khong hop le: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
}
