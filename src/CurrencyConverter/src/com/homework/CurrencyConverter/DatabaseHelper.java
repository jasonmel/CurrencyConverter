package com.homework.CurrencyConverter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	final static String DB_NAME      = "currency.db";
	final static int    DB_VERSION   = 1;
	final static String TABLE_NAME   = "currency";
	final static String TABLE_NAME2  = "personal";
	final static String COLUMN_ID    = "_id";
	final static String COLUMN_KEY   = "key";
	final static String COLUMN_VALUE = "value";
	
	public DatabaseHelper(final Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(final SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				   + COLUMN_KEY + " TEXT, " + COLUMN_VALUE + " REAL);");
		db.execSQL("CREATE TABLE " + TABLE_NAME2 + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				   + COLUMN_KEY + " TEXT);");
		for (String key : new String[] {"TWD", "USD", "JPY"}) {
			ContentValues values = new ContentValues();
			values.put(DatabaseHelper.COLUMN_KEY, key);
			db.insert(DatabaseHelper.TABLE_NAME2, null, values);
		}
	}
	
	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldV, final int newV) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
		onCreate(db);
	}
}
