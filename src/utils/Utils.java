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

	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
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

	public static String getCurrencyFormat(int num) {
		Locale vietname = new Locale("vi", "VN");
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(vietname);
		return defaultFormat.format(num);
	}

	/**
	 * Return a {@link java.lang.String String} that represents the current time in
	 * the format of yyyy-MM-dd HH:mm:ss.
	 * 
	 * @author hieudm
	 * @return the current time as {@link java.lang.String String}.
	 */
	public static String getToday() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * Return a {@link java.lang.String String} that represents the cipher text
	 * encrypted by md5 algorithm.
	 * 
	 * @author hieudm vnpay
	 * @param message - plain text as {@link java.lang.String String}.
	 * @return cipher text as {@link java.lang.String String}.
	 */
	public static String md5(String message) {
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(message.getBytes("UTF-8"));
			// converting byte array to Hexadecimal String
			StringBuilder sb = new StringBuilder(2 * hash.length);
			for (byte b : hash) {
				sb.append(String.format("%02x", b & 0xff));
			}
			digest = sb.toString();
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
			Utils.getLogger(Utils.class.getName());
			digest = "";
		}
		return digest;
	}

	public static LocalDate LOCAL_DATE(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(dateString, formatter);
	}

	public static String hashPassword(String password) {
		String generatedPassword = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(password.getBytes());
			byte[] resultByteArray = messageDigest.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : resultByteArray) {
				sb.append(Integer.toString((b & 0xff) + 0x100, 16));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	public static void createDialog(Alert.AlertType type, String title, String headerText, String contentText) {
		Alert warning = new Alert(type);
		warning.setTitle(title);
		warning.setHeaderText(headerText);
		warning.setContentText(contentText);
		warning.showAndWait();
	}

	public static String convertDate(String date) {
		String result;
		String[] date_split = date.split("-");
		result = date_split[2] + "/" + date_split[1] + "/" + date_split[0];
		return result;
	}

	public static String convertTime(String time) {
		String result;
		String[] timeArr = time.split(" ");
		String[] date = timeArr[0].split("-");
		result = timeArr[1] + " " + date[2] + "/" + date[1] + "/" + date[0];
		return result;
	}

	public static boolean isValidTime(String time) {
		// String regex = "^(2[0-3]{2}|[01]?[0-9]{2}):([0-5]?[0-9]{2})$";
		String regex = "^(2[0-3]|[1][0-9]|[0][0-9]):(0[0-9]|[1-5][0-9]):(0[0-9]|[1-5][0-9])$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(time);
		return m.matches();
	}

	public static boolean greaterTime(String startDate, String startTime, String endDate, String endTime) {
		String[] startDateArr = startDate.split("-");
		String[] startTimeArr = startTime.split(":");
		String[] endDateArr = endDate.split("-");
		String[] endTimeArr = endTime.split(":");
		String starttime = startDateArr[0] + startDateArr[1] + startDateArr[2] + startTimeArr[0] + startTimeArr[1]
				+ startTimeArr[2];
		String endtime = endDateArr[0] + endDateArr[1] + endDateArr[2] + endTimeArr[0] + endTimeArr[1] + endTimeArr[2];
		if (Long.parseLong(endtime) > Long.parseLong(starttime))
			return true;
		else
			return false;
	}

	public static boolean isCccd(String cccd) {
		String regex = "^\\d{12}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(cccd);
		return !m.matches();
	}

	public static String toUpperFirstLetter(String text) {
		return text.substring(0, 1).toUpperCase() + text.substring(1);
	}

	public static String generateRandomNumber(int num_length) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num_length; i++) {
			sb.append(r.nextInt(10));
		}
		return sb.toString();
	}

	public static String convertDateWhenAddLichHD(String dateString) throws ParseException {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = inputFormat.parse(dateString);
		SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss.0 dd/MM/yyyy");
		return outputFormat.format(date);
	}
}
