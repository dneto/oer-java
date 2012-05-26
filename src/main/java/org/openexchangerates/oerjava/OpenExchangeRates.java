package org.openexchangerates.oerjava;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

import org.openexchangerates.oerjava.exceptions.UnavailableExchangeRateException;

/**
 * Open Exchange Rates(http://openexchangerates.org) client
 * @author Demétrio Menezes Neto
 */
public abstract class OpenExchangeRates {

	/**
	 * Generate and get a new Open Exchange Rates client
	 * @return a Open Exchange Rates client
	 */
	public static OpenExchangeRates getClient() {
		return new OpenExchangeRatesJsonClient();
	}

	/**
	 * Get the latest exchange rates from
	 * http://openexchangerates.org/latest.json
	 * 
	 * @return Last updated exchange rates
	 */
	public abstract Map<Currency, BigDecimal> getLatest();

	/**
	 * Get a historical exchange rate from a given date
	 * 
	 * @param date
	 *            Date of desired rates
	 * @return Exchange rates of desired date.
	 * @throws UnavailableExchangeRateException
	 *             when a exchange rate is unavailable
	 */
	public abstract Map<Currency, BigDecimal> getHistorical(Calendar date)
			throws UnavailableExchangeRateException;

	/**
	 * Get the latest exchange rate from a given currency
	 * 
	 * @param currency
	 *            Desired currency
	 * @return Latest value of exchange rate
	 */
	public abstract BigDecimal getCurrencyValue(Currency currency);

	/**
	 * Get a historical exchange rate from a given currency and date
	 * 
	 * @param currency
	 *            Currency of desired rate
	 * @param date
	 *            Date of desired rate
	 * @return Value of exchange rate in desired date
	 * @throws when
	 *             a exchange rate is unavailable
	 */
	public abstract BigDecimal getHistoricalCurrencyValue(Currency currency,
			Calendar date) throws UnavailableExchangeRateException;
}
