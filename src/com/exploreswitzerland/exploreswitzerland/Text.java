package com.exploreswitzerland.exploreswitzerland;

public class Text {

	int _id;
	String _title;
	String _text;
	
	
	public Text() {
	}


	/**
	 * @param _id
	 * @param _title
	 * @param _text
	 */
	public Text(int _id, String _title, String _text) {
		this._id = _id;
		this._title = _title;
		this._text = _text;
	}


	/**
	 * @param _title
	 * @param _text
	 */
	public Text(String _title, String _text) {
		super();
		this._title = _title;
		this._text = _text;
	}


	public int get_id() {
		return _id;
	}


	public void set_id(int _id) {
		this._id = _id;
	}


	public String get_title() {
		return _title;
	}


	public void set_title(String _title) {
		this._title = _title;
	}


	public String get_text() {
		return _text;
	}


	public void set_text(String _text) {
		this._text = _text;
	}
	
	
}
