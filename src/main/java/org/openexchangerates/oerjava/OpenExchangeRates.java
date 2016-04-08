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
package org.openexchangerates.oerjava;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.openexchangerates.oerjava.exceptions.UnavailableExchangeRateException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Java Open Exchange Rates(http://openexchangerates.org) client
 * 
 * @author Demétrio Menezes Neto
 */
public class OpenExchangeRates {
	private final static String OER_URL = "http://openexchangerates.org/api/";
	private static final String LATEST = "latest.json?app_id=%s";
	private static final String HISTORICAL = "historical/%04d-%02d-%02d.json?app_id=%s";
	private final String appId;

	private final static ObjectMapper mapper = new ObjectMapper();

	/**
	 * Constructor for a new OpenExchangeRates
	 *
	 * @param appId The API key to Open Exchange Rates
	 */
	public OpenExchangeRates(String appId) {
		this.appId = appId;
	}

	/**
	 * Downloads the exchanges rates from given json path
	 * 
	 * @param downloadPath
	 *            Path to json file
	 * @return Map containing all rates of json file as key, and their exchange as value
	 * @throws UnavailableExchangeRateException
	 */
	private Map<String, BigDecimal> updateExchangeRates(
			String downloadPath) throws UnavailableExchangeRateException {
		try {
			Map<String, BigDecimal> exchangeRates = new HashMap<>();
			String urlString = String.format(OER_URL + downloadPath, this.appId);
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			JsonNode node = mapper.readTree(conn.getInputStream());
			Iterator<Map.Entry<String, JsonNode>> fieldNames = node.get("rates").getFields();
			fieldNames.forEachRemaining(e -> exchangeRates.put(e.getKey(), e.getValue().getDecimalValue()));
			return exchangeRates;
		} catch (IOException e) {
			throw new UnavailableExchangeRateException(e.getMessage());
		}
	}

	/**
	 * Get the latest exchange rates
	 * 
	 * @return Last updated exchange rates
	 * @throws UnavailableExchangeRateException
	 */
	public Map<String, BigDecimal> latest() throws UnavailableExchangeRateException {
			return updateExchangeRates(LATEST);
	}

	public Map<String, BigDecimal> historical(Calendar date)
			throws UnavailableExchangeRateException {

		int day = date.get(Calendar.DAY_OF_MONTH);
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);

		String historical = String.format(HISTORICAL, year, month, day, appId);
		return updateExchangeRates(historical);

	}

	public BigDecimal currency(String currency) throws UnavailableExchangeRateException {
		return latest().get(currency);
	}

	public BigDecimal historicalCurrency(String currency,
										 Calendar date) throws UnavailableExchangeRateException {
		return historical(date).get(currency);
	}
}
