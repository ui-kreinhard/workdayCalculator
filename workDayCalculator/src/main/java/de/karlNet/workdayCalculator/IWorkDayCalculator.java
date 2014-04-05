package de.karlNet.workdayCalculator;

import java.util.Date;

/**
 * I know i know, german in code is very bad but I will stick to the german defintions in the code, 
 * because there very precise
 * 
 * Werktage = all weekdays except sunday are workingdays
 * Arbeitstag = all weekdays except sunday and saturday are workingdays
 * @author k4l
 *
 */
public interface IWorkDayCalculator {
	public Date addNArbeitstageFromToday(int n);
	public Date addNArbeitstageFromNextArbeitstagFromToday(int n);
	public Date getNextArbeitsTagFromToday();
	
	public Date addNArbeitstage(Date sourceDate, int n);
	public Date addNArbeitstageFromNextArbeitstag(Date sourceDate, int n);
	public Date getNextArbeitsTag(Date sourceDate);
	
	
	public Date addNWerktageFromToday(int n);
	public Date addNWerktageFromNextArbeitstagFromToday(int n);
	public Date getNextWerktagFromToday();
	
	public Date addNWerktage(Date sourceDate, int n);
	public Date addNWerktageFromNextWerktag(Date sourceDate, int n);
	public Date getNextWerktag(Date sourceDate);
	
}
