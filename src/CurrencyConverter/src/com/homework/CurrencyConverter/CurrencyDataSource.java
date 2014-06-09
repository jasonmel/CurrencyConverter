package com.homework.CurrencyConverter;

import java.util.Hashtable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class CurrencyDataSource {
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	private String[] allColumns = { DatabaseHelper.COLUMN_ID,
									DatabaseHelper.COLUMN_KEY,
									DatabaseHelper.COLUMN_VALUE };
	private String[] allColumns2 = { DatabaseHelper.COLUMN_ID,
									DatabaseHelper.COLUMN_KEY};
	
	public CurrencyDataSource(Context context) {
		dbHelper = new DatabaseHelper(context);
	}
	
	public void open() throws SQLiteException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public long setCurrency(String key, Float value) throws SQLiteException {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_KEY, key);
		values.put(DatabaseHelper.COLUMN_VALUE, value);
		long id = 0;
		if (getCurrency(key) > 0.0f) {
			id = database.update(DatabaseHelper.TABLE_NAME, values, DatabaseHelper.COLUMN_KEY + " = '" + key + "'", null);
		} else {
			id = database.insert(DatabaseHelper.TABLE_NAME, null, values);
		}
		return id;
	}
	
	public void deleteCurrency(String key) throws SQLiteException {
		database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COLUMN_KEY + " = '" + key + "'", null);
	}
	
	public Float getCurrency(String key) throws SQLiteException {
		Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,
				allColumns, DatabaseHelper.COLUMN_KEY + " = '" + key + "'", null, null, null, null);
		if (cursor.getCount() == 0) {
			return 0.0f;
		}
		cursor.moveToFirst();
		return cursor.getFloat(cursor.getColumnIndex(DatabaseHelper.COLUMN_VALUE));
	}
	
	public Hashtable<String, Float> getCurrencies() throws SQLiteException {
		Hashtable<String, Float> currencies = new Hashtable<String, Float>();
		Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,
				allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			currencies.put(cursor.getString(1), cursor.getFloat(2));
			cursor.moveToNext();
		}
		return currencies;
	}
	
	public void setPersonal(String[] keys) {
		for (int i = 0; i < keys.length; i++) {
			ContentValues values = new ContentValues();
			//values.put(DatabaseHelper.COLUMN_ID, i + 1);
			values.put(DatabaseHelper.COLUMN_KEY, keys[i]);
			database.update(DatabaseHelper.TABLE_NAME2, values, DatabaseHelper.COLUMN_ID + " = '" + (i + 1) + "'", null);
		}
	}
	
	public String[] getPersonal(int length) {
		String[] keys = new String[length];
		Cursor cursor = database.query(DatabaseHelper.TABLE_NAME2,
				allColumns2, null, null, null, null, null);
		cursor.moveToFirst();
		for (int i = 0; i < length && !cursor.isAfterLast(); i++) {
			keys[i] = cursor.getString(1);
			cursor.moveToNext();
		}
		return keys;
	}
}
