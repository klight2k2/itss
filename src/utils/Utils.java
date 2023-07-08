package utils;

import java.util.logging.Logger;

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
		case "Từ chối":
			return "REJECTED";
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
