package de.karlNet.wordayCalculator.repo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import de.karlNet.workdayCalculator.WorkdayCalculator;

public class WorkdayCalculatorTest {
	private WorkdayCalculator workdayCalculator;

	public WorkdayCalculatorTest() throws IOException, ParseException {
		this.workdayCalculator = new WorkdayCalculator(
				"src/test/resources/bankHolidays.csv");
	}

	private Date fromString(String dateString) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException("invalid format");
		}
	}

	@Test
	public void getNextArbeitstagTest() {
		{
			Assert.assertTrue(this.workdayCalculator.getNextArbeitsTag(
					this.fromString("2014-01-01")).equals(
					this.fromString("2014-01-02")));
		}
		{
			Assert.assertTrue(this.workdayCalculator.getNextArbeitsTag(
					this.fromString("2014-12-25")).equals(
					this.fromString("2014-12-29")));
		}
	}

	@Test
	public void addNArbeitstageTest() {
		{
			Date addNArbeitstageResult = this.workdayCalculator
					.addNArbeitstage(this.fromString("2014-12-25"), 2);
			Assert.assertTrue(addNArbeitstageResult.equals(this
					.fromString("2014-12-30")));
		}
		{
			Date addNArbeitstageResult = this.workdayCalculator
					.addNArbeitstage(this.fromString("2014-01-01"), 2);
			Assert.assertTrue(addNArbeitstageResult.equals(this
					.fromString("2014-01-03")));
		}
	}
}
