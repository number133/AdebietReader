package kz.adebiet.setting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import android.content.Context;

public class StorageHelper {
	private Context ctx;
	private static final String SETTINGS_FILE_NAME = "adebiet_settings";
	private static final String BOOK_INFO_FILE_NAME = "book_info_settings";

	public StorageHelper(Context context) {
		this.ctx = context;
	}

	public void writeSettings(Settings settings) {
		FileOutputStream fos;
		ObjectOutputStream oos;
		try {
			fos = ctx.openFileOutput(SETTINGS_FILE_NAME, Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(settings);
			oos.flush();
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Settings readSettings() {
		Settings settings = new Settings();
		FileInputStream fis;
		ObjectInputStream oin;
		try {
			fis = ctx.openFileInput(SETTINGS_FILE_NAME);
			oin = new ObjectInputStream(fis);
			settings = (Settings) oin.readObject();
			oin.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return settings;
	}
	
	public void writeBookInfo(BookInfo bookInfo) {
		FileOutputStream fos;
		ObjectOutputStream oos;
		try {
			fos = ctx.openFileOutput(BOOK_INFO_FILE_NAME, Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(bookInfo);
			oos.flush();
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BookInfo readBookInfo() {
		BookInfo bookInfo = new BookInfo();
		FileInputStream fis;
		ObjectInputStream oin;
		try {
			fis = ctx.openFileInput(BOOK_INFO_FILE_NAME);
			oin = new ObjectInputStream(fis);
			bookInfo = (BookInfo) oin.readObject();
			oin.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookInfo;
	}
}
