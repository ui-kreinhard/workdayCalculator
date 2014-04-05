package de.karlNet.workdayCalculator;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import de.karlNet.workdayCalculator.repo.WorkdayRepo;

public class WorkdayCalculator implements IWorkDayCalculator {
	private WorkdayRepo workdayRepo;

	private Date addNAbstractDays(Date sourceDate, Applies applies, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(sourceDate);
		int i = 0;
		while (i < n) {
			c.add(Calendar.DATE, 1);
			if (applies.appliesForDate(c.getTime())) {
				i++;
			}
		}
		return c.getTime();
	}

	public WorkdayCalculator(String fileName) throws IOException,
			ParseException {
		this.workdayRepo = new WorkdayRepo(fileName);
	}

	public Date addNArbeitstageFromToday(int n) {
		Date currentDate = new Date();
		return this.addNArbeitstage(currentDate, n);
	}

	public Date addNArbeitstageFromNextArbeitstagFromToday(int n) {
		return this.addNArbeitstageFromNextArbeitstag(
				getNextArbeitsTagFromToday(), n);
	}

	public Date getNextArbeitsTagFromToday() {
		Date currentDate = new Date();
		return this.getNextArbeitsTag(currentDate);
	}

	public Date addNArbeitstage(Date sourceDate, int n) {
		return this.addNAbstractDays(sourceDate, new Applies() {
			public boolean appliesForDate(Date date) {
				return workdayRepo.isArbeitsTag(date);
			}
		}, n);
	}

	public Date addNArbeitstageFromNextArbeitstag(Date sourceDate, int n) {
		return this.addNArbeitstage(this.getNextArbeitsTag(sourceDate), n);
	}

	public Date getNextArbeitsTag(Date sourceDate) {
		return this.addNArbeitstage(sourceDate, 1);
	}

	public Date addNWerktageFromToday(int n) {
		Date currentDate = new Date();
		return this.addNArbeitstage(currentDate, n);
	}

	public Date addNWerktageFromNextArbeitstagFromToday(int n) {
		Date currentDate = new Date();
		return this.addNWerktage(currentDate, n);
	}

	public Date getNextWerktagFromToday() {
		Date currentDate = new Date();
		return this.addNWerktage(currentDate, 1);
	}

	public Date addNWerktage(Date sourceDate, int n) {
		return this.addNAbstractDays(sourceDate, new Applies() {
			public boolean appliesForDate(Date date) {
				return workdayRepo.isWerktag(date);
			}
		}, n);
	}

	public Date addNWerktageFromNextWerktag(Date sourceDate, int n) {
		return this.addNWerktage(this.getNextWerktag(sourceDate), n);
	}

	public Date getNextWerktag(Date sourceDate) {
		return this.addNArbeitstage(sourceDate, 1);
	}

}
