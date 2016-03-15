package com.exploreswitzerland.exploreswitzerland;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "textsManager";

	// Texts table name
	private static final String TABLE_TEXTS = "texts";

	// Texts Table Colums names
	private static final String KEY_ID = "id";
	private static final String KEY_TITLE = "title";
	private static final String KEY_TEXT = "text";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_TEXTS_TABLE = "CREATE TABLE " + TABLE_TEXTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
				+ KEY_TEXT + " TEXT" + ")";
		db.execSQL(CREATE_TEXTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP ANY TABLE IF EXISTS " + TABLE_TEXTS);

		onCreate(db);
	}
	
	public void addText(Text text) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, text.get_title());
		values.put(KEY_TEXT, text.get_text());
		
		db.insert(TABLE_TEXTS, null, values);
		db.close();
	}
	
	public Text getText(int id) {
		
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_TEXTS, new String[] {KEY_ID, KEY_TITLE, KEY_TEXT}, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		
		Text text = new Text(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2));
		
		return text;
	}
	
	public List<Text> getAllTexts() {
		
		List<Text> textList = new ArrayList<Text>();
		
		String selectQuery = "SELECT * FROM " + TABLE_TEXTS;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor.moveToFirst()) {
			do {
				Text text = new Text();
				text.set_id(Integer.parseInt(cursor.getString(0)));;
				text.set_title(cursor.getString(1));
				text.set_text(cursor.getString(2));
				textList.add(text);
			} while (cursor.moveToNext());
		}
		
		return textList;
	}
	
	public int getTextsCount () {
		
		String countQuery = "SELECT * FROM " + TABLE_TEXTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		
		return cursor.getCount();
	}
	
	public int updateText(Text text) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, text.get_title());
		values.put(KEY_TEXT, text.get_text());
		
		return db.update(TABLE_TEXTS, values, KEY_ID + " = ?", new String[] { String.valueOf(text.get_id())});
	}
	
	public void deleteText(Text text) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_TEXTS, KEY_ID + " = ?", new String [] { String.valueOf(text.get_id())});
		db.close();
	}

	
}
