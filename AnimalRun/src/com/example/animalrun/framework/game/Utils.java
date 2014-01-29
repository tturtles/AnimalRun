package com.example.animalrun.framework.game;

import java.io.BufferedReader;
import java.io.IOException;

import android.content.ContentValues;
import android.content.SharedPreferences;

import com.example.animalrun.framework.FileIO;

public class Utils {
	public SharedPreferences sharedPref;

	public static void load(FileIO files) {
		BufferedReader in = null;
		try {
		String[] mode = { "easy", "normal", "hard" };
		String sql = "create table score_data("
				+ "_id integer primary key autoincrement,"
				+ "name text not null," + "score integer default 0,"
				+ "mode text not null)";
		if (files.CreateDBandTable(sql))
			for (int i = 0; i < mode.length; i++)
				for (int j = 0; j < 5; j++)
					addScore(files, "Noname", 0, mode[i]);
		} catch (Exception e) {
			// デフォルト設定があるのでエラーは無視
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
			}
		}
	}

	public static boolean addScore(FileIO files, String name, int score,
			String mode) {
		if (name.toString().equals(""))
			return false;
		ContentValues val = new ContentValues();
		val.put("name", name);
		val.put("score", score);
		val.put("mode", mode);
		return files.writeFile(val);
	}

	public static String[][] readFile(FileIO files, String mode) {
		String[] columns = { "name", "score" };
		String order = "score desc";
		String where = "mode like ?";
		String value[] = new String[1];
		value[0] = mode;
		return files.readFile(columns, where, value, order, 3);
	}

}
