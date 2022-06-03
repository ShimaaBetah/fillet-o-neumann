package logger.services;

public class ColorStringService {
	public static final String RESET       = "\033[0m";

	public static final String RED         = "\033[0;31m";
	public static final String GREEN       = "\033[0;32m";
	public static final String YELLOW      = "\033[0;33m";
	public static final String BLUE        = "\033[0;34m";

	public static final String BLACK_BOLD  = "\033[1;30m";
	public static final String RED_BOLD    = "\033[1;31m";
	public static final String GREEN_BOLD  = "\033[1;32m";
	public static final String YELLOW_BOLD = "\033[1;33m";
	public static final String BLUE_BOLD   = "\033[1;34m";

	private ColorStringService() {
		throw new IllegalStateException("Utility class");
	}

	public static String color(String str, String color) {
		return color + str + RESET;
	}
}
