package com.uncodeframework.core.plugins.mylog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * 自写Logo类
 *
 */
public class Logger {
	/** DEBUG Level */
	public static int DEBUG = 0;
	/** INFO Level */
	public static int INFO = DEBUG + 1;

	private static final String LOG_LEVEL_CODES[] = { "DEBUG", "INFO" };

	private static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy HH:mm:ss.SSS");
	private static final SimpleDateFormat fileformatter = new SimpleDateFormat("yyMMdd-HHmmss");
	private static String filebaseName = "Logger"; // the default logfile name
	private static String logHomeDir;
	private static String logDir;
	private static File file = null;
	private static long MAX_FILE_SIZE = 2048000; // the maximum size of each
	// log file
	private static long CUR_DATE;
	private static PrintWriter WRITER;
	private static int LOG_LEVEL = INFO; // default loglevel
	
//	public Logger(){
//		String logdir = Configuration.getValue("mylog.logdir");
//		String logName = Configuration.getValue("mylog.logname");
//		int logLevel = Integer.parseInt(Configuration.getValue("mylog.loglevel"));
//		Logger.initialize(logdir, logName, logLevel);
//	}

	public static void initialize(String logdir, String logName, int logLevel) {
		logHomeDir = logdir;
		if (logHomeDir == null) {
			logHomeDir = System.getProperty("user.dir");
		}
		filebaseName = logName;
		LOG_LEVEL = logLevel;
		setWriter();
		writeConfigurations();
	}

	public static void initialize(String logdir, String logName, int logLevel, long max_file_size) {
		logHomeDir = logdir;
		if (logHomeDir == null) {
			logHomeDir = System.getProperty("user.dir");
		}
		filebaseName = logName;
		LOG_LEVEL = logLevel;
		MAX_FILE_SIZE = max_file_size;
		setWriter();
		writeConfigurations();
	}

	/**
	 * Log message with message title.
	 * 
	 * @param logLevel
	 *            log level
	 * @param title
	 *            the title of log message
	 * @param message
	 *            the message of log
	 */
	public static void log(int logLevel, String title, String message) {
		if (WRITER == null || logLevel < LOG_LEVEL) {
			return;
		}
		if (isNewWriterRequired()) {
			setWriter();
		}
		// String date = formatter.format(Calendar.getInstance().getTime());
		// WRITER.println(">> " + date + "; " + "Thr:" +
		// Thread.currentThread().getName() + "; " + "LogLvl:"
		// + LOG_LEVEL_CODES[logLevel] + "; " + getWhere() + "; ");
		WRITER.println(message);
		WRITER.flush();
	}

	/**
	 * Log message to log file
	 * 
	 * @param logLevel
	 *            log level
	 * @param message
	 *            the message of log
	 */
	public static void log(int logLevel, String message) {
		if (logLevel < LOG_LEVEL || WRITER == null) {
			return;
		}
		if (isNewWriterRequired()) {
			setWriter();
		}
		// String date = formatter.format(Calendar.getInstance().getTime());
		// WRITER.println(">> " + date + "; " + "Thr:" +
		// Thread.currentThread().getName() + "; LogLvl:" +
		// LOG_LEVEL_CODES[logLevel]
		// + "; Where:" + getWhere() + "; ");
		WRITER.println(message);
		WRITER.flush();
	}

	/**
	 * Print stack trace
	 * 
	 * @param logLevel
	 *            log level
	 * @param title
	 *            the title of log message
	 * @param throwable
	 *            exceptions and errors object
	 */
	public static void printStackTrace(int logLevel, String title, Throwable throwable) {
		if (WRITER == null || logLevel < LOG_LEVEL) {
			return;
		}
		if (isNewWriterRequired()) {
			setWriter();
		}
		// String date = formatter.format(Calendar.getInstance().getTime());
		// WRITER.println(">> " + date + "; " + title + "; " + "Thr:" +
		// Thread.currentThread().getName() + "; " + "LogLvl:"
		// + LOG_LEVEL_CODES[logLevel] + "; " + getWhere() + "; ");
		throwable.printStackTrace(WRITER);
		WRITER.flush();
	}

	public static synchronized void shutDown() {
		if (WRITER == null) {
			return;
		}
		WRITER.flush();
		WRITER.println("###############################################################");
		WRITER.println("#");
		WRITER.println("#\t  Server Shut Down");
		WRITER.println("#");
		WRITER.println("###############################################################");
		WRITER.println("");
		WRITER.close();
	}

	private static long getCurDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String date = df.format(Calendar.getInstance().getTime());
		long curDate = 0;
		try {
			curDate = Long.parseLong(date);
		} catch (NumberFormatException ex) {
			// eat it
		}
		return curDate;
	}

	private static boolean isNewWriterRequired() {
		return (getCurDate() > CUR_DATE) || (file.length() > MAX_FILE_SIZE);
	}

	/**
	 * Writes the configurations of this running program.
	 */
	private static void writeConfigurations() {
		if (WRITER == null) {
			return;
		}
		String date = formatter.format(Calendar.getInstance().getTime());
		WRITER.println("#-------------------------------------------------------------#");
		// WRITER.println("#");
		WRITER.println("#\t LOG_LEVEL = " + LOG_LEVEL_CODES[LOG_LEVEL] + "	run time : " + date);
		// more configuration can be added here
		// WRITER.println("#");
		WRITER.println("#-------------------------------------------------------------#");
		WRITER.flush();
	}

	private static synchronized void setWriter() {
		if (WRITER != null) {
			WRITER.println("###############################################################");
			WRITER.println("\t End Of the DiagFile:" + filebaseName + ".log");
			WRITER.println("###############################################################");
			WRITER.flush();
			WRITER.close();
		}
		// create log dir and file.
		if (getCurDate() > CUR_DATE) {
			CUR_DATE = getCurDate();
			logDir = logHomeDir + File.separator + CUR_DATE;
			new File(logDir).mkdirs();
		} else {
			String nfile = logDir + File.separator + filebaseName + fileformatter.format(Calendar.getInstance().getTime()) + ".log";
			file.renameTo(new File(nfile));
		}
		String logFile = logDir + File.separator + filebaseName + ".log";
		// create a file writer to write to the log file
		try {
			WRITER = new PrintWriter(new FileWriter(logFile, true));
			file = new File(logFile);
		} catch (IOException ex) {
			System.err.println("Fatal Error [" + ex.toString() + "]!" + "Diagnosis cannot write to the log file [" + logFile + "].");
			System.err.println("System.out is used.");
			WRITER = new PrintWriter(System.out);
		}
	}

	/**
	 * Gets the latest method of the thread stack.
	 */
	private static String getWhere() {
		String where = "";
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream pw = new PrintStream(os);
		System.setErr(pw);
		Thread.dumpStack();
		System.setErr(System.err);
		pw.flush();
		String text = os.toString();
		// System.out.println(text);
		// Find the last method called before Debug's static methods.
		StringTokenizer st = new StringTokenizer(text, "\n");
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			if (s.indexOf("common.framework.log.Logger") == -1 && s.indexOf("java.lang.Thread") == -1 && s.indexOf("at ") > -1) {
				where = s.trim();
				break;
			}
		}
		pw.close();
		return where;
	}
}
