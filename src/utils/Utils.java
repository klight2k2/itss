package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;

public class Utils {

	private static Logger LOGGER = getLogger(Utils.class.getName());
	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-4s] [%1$tF %1$tT] [%2$-7s] %5$s %n");
	}

	public static String convertBorrowStatus(String status) {
		switch (status) {
			case "Đang chờ":
				return "PENDING";
			case "Chấp nhận":
				return "APPROVED";
			case "Đang mượn":
				return "BORROWING";
			case "Đã trả":
				return "PAID";
			case "REJECTED":
				return "Từ chối";
			default:
				break;
		}
		return "UNKNOWN";
	}
	public static String convertReverseBorrowStatus(String status) {
		switch (status) {
			case "PENDING":
				return "Đang chờ";
			case "APPROVED":
				return "Chấp nhận";
			case "BORROWING":
				return "Đang mượn";
			case "PAID":
				return "Đã trả";
			case "REJECTED":
				return "Từ chối";
			default:
				break;
		}
		return "UNKNOWN";
	}
	public static Logger getLogger(String className) {
		return Logger.getLogger(className);
	}


}
