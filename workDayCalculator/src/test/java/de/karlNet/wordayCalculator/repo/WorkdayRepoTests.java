package de.karlNet.wordayCalculator.repo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import de.karlNet.workdayCalculator.repo.WorkdayRepo;

public class WorkdayRepoTests {
	WorkdayRepo workdayRepo;
	
	public WorkdayRepoTests() throws IOException, ParseException {
		this.workdayRepo = new WorkdayRepo("src/test/resources/bankHolidays.csv");
	}
	
	@Test
	public void testReadinFalse() {
		try {
			WorkdayRepo workdayRepo = new WorkdayRepo("nonexistingtest.csv");
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testIsArbeitstagPositive() throws IOException, ParseException {
		boolean abreitsTag = workdayRepo.isArbeitsTag(this.fromString("2014-03-31"));
		
		Assert.assertTrue(abreitsTag);
	}

	@Test
	public void testIsArbeitstagNegative() throws IOException, ParseException {
		boolean abreitsTag = workdayRepo.isArbeitsTag(this.fromString("2014-03-30"));
		Assert.assertFalse(workdayRepo.isArbeitsTag(this.fromString("2014-04-05")));
		Assert.assertFalse(abreitsTag);
	}
	
	@Test
	public void testIsWerktagPositive() {
		Assert.assertTrue(workdayRepo.isWerktag(this.fromString("2014-04-05")));
		Assert.assertTrue(workdayRepo.isWerktag(this.fromString("2014-04-07")));
	}
	
	@Test
	public void testIsWerktagNegative() {
		Assert.assertFalse(workdayRepo.isWerktag(this.fromString("2014-04-06")));
	}
	
	@Test
	public void testBankHolidaysWerktagePositive() {
		Assert.assertFalse(workdayRepo.isWerktag(this.fromString("2014-01-01")));
		Assert.assertFalse(workdayRepo.isWerktag(this.fromString("2014-06-01")));
		Assert.assertFalse(workdayRepo.isWerktag(this.fromString("2014-10-03")));
		Assert.assertFalse(workdayRepo.isWerktag(this.fromString("2014-12-25")));
		Assert.assertFalse(workdayRepo.isWerktag(this.fromString("2014-12-26")));
	}
	
	@Test
	public void testBankHolidaysArbeitstagePositive() {
		Assert.assertFalse(workdayRepo.isArbeitsTag(this.fromString("2014-01-01")));
		Assert.assertFalse(workdayRepo.isArbeitsTag(this.fromString("2014-06-01")));
		Assert.assertFalse(workdayRepo.isArbeitsTag(this.fromString("2014-10-03")));
		Assert.assertFalse(workdayRepo.isArbeitsTag(this.fromString("2014-12-25")));
		Assert.assertFalse(workdayRepo.isArbeitsTag(this.fromString("2014-12-26")));
	}
	
	private Date fromString(String dateString) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException("invalid format");
		}
	}
}
