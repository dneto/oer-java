Open Exchange Rates Java Client (oer-java)
==========================================

Open-source Java Client for [Open Exchange Rates][2].

##About Open Exchange Rates##
Open Exchange Rates is a free, hourly-updating API that provides rates for about 120 currencies using USD as base.

For more information about Open Exchange Rates see:

*    [Open Exchange Rates GitHub Project][1]
*    [Open Exchange Rates Official Site][2]
*    [Open Exchange Rates Official Doc][3]

[1]:https://github.com/currencybot/open-exchange-rates
[2]:http://openexchangerates.org/
[3]:http://josscrowcroft.github.com/open-exchange-rates/

##How to Use##

For example let's get the latest rates from BRL(Brazilian Real):

```java
import java.math.BigDecimal;
import org.openexchangerates.oerjava.Currency;
import org.openexchangerates.oerjava.OpenExchangeRates;

public class BRLRate {
	public static void main(String[] args) {
		OpenExchangeRates oer = OpenExchangeRates.getClient();
		BigDecimal BRLValue = oer.getCurrencyValue(Currency.BRL);
		System.out.println(BRLValue);
	}
}
```

For more information about this API see the [documentation][4]
[4]:http://dneto.github.com/oer-java/documentation/
