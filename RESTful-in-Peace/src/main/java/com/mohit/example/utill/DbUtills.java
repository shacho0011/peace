package com.mohit.example.utill;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DbUtills {

	public static String formatDateForDisplay(String date) {

		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat outputformat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			date = outputformat.format(inputFormat.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return date;
	}

	public static String formatDateForSave(String date) {

		DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			date = outputFormat.format(inputFormat.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return date;
	}

}
