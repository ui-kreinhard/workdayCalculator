package de.karlNet.workdayCalculator.repo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class WorkdayRepo {
	private HashMap<Date, Date> arbeitsTage = new HashMap<Date, Date>();
	private HashMap<Date, Date> werkTage = new HashMap<Date, Date>();

	public boolean isWerktag(Date werktag) {
		return this.werkTage.containsKey(werktag);
	}

	public boolean isArbeitsTag(Date arbeitstag) {
		return this.arbeitsTage.containsKey(arbeitstag);
	}

	public WorkdayRepo(String fileName) throws IOException, ParseException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = br.readLine()) != null) {
			String[] splittedLine = line.split(",");
			ParsedDay parsedDay = new ParsedDay(splittedLine[0],
					splittedLine[1]);
			if(parsedDay.isArbeitstag()) {
				this.arbeitsTage.put(parsedDay.getDate(), parsedDay.getDate());
			} 
			if(parsedDay.isWerktag()) {
				this.werkTage.put(parsedDay.getDate(), parsedDay.getDate());
			}
		}
		br.close();
	}

	private class ParsedDay {
		private Date date;
		private boolean isBankHoliday;
		private int dayOfWeek;

		public ParsedDay(String rawDay, String isBankHoliday)
				throws ParseException {
			this.date = new SimpleDateFormat("yyyy-MM-dd").parse(rawDay);
			this.isBankHoliday = isBankHoliday.equals("1");
			Calendar c = Calendar.getInstance();
			c.setTime(this.date);
			this.dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		}

		private boolean isSunday() {
			return this.dayOfWeek == Calendar.SUNDAY;
		}

		private boolean isSaturday() {
			return this.dayOfWeek == Calendar.SATURDAY;
		}

		public boolean isArbeitstag() {
			return (!isBankHoliday && !this.isSunday() && !this.isSaturday());
		}

		public boolean isWerktag() {
			return (!isBankHoliday && !this.isSunday());
		}

		public Date getDate() {
			return date;
		}
	}
}
