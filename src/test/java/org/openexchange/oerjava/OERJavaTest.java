/* Copyright 2012 Demétrio de Castro Menezes Neto
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package org.openexchange.oerjava;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

import org.openexchangerates.oerjava.Currency;
import org.openexchangerates.oerjava.OpenExchangeRates;
import org.openexchangerates.oerjava.exceptions.UnavailableExchangeRateException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class OERJavaTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public OERJavaTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(OERJavaTest.class);
	}

	/**
	 * Test method
	 * 
	 * @throws UnavailableExchangeRateException
	 */
	public void testApp() throws UnavailableExchangeRateException {
		OpenExchangeRates client = OpenExchangeRates.getClient();

		for (Map.Entry<Currency, BigDecimal> entry : client.getLatest()
				.entrySet()) {
			assertTrue(entry.getValue().compareTo(new BigDecimal(0)) >= 0);
		}

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2008);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 01);

		for (Map.Entry<Currency, BigDecimal> entry : client.getHistorical(cal)
				.entrySet()) {
			assertTrue(entry.getValue().compareTo(new BigDecimal(0)) >= 0);
		}

		assertTrue(client.getCurrencyValue(Currency.USD).compareTo(
				new BigDecimal(1)) == 0);

		assertTrue(client.getHistoricalCurrencyValue(Currency.USD, cal)
				.compareTo(new BigDecimal(1)) == 0);

		cal.set(Calendar.YEAR, 1990);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 01);

		try {
			assertTrue(client.getHistoricalCurrencyValue(Currency.USD, cal)
					.compareTo(new BigDecimal(1)) == 0);
		} catch (UnavailableExchangeRateException e) {

		}

	}
}
