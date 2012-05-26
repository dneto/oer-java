Open Exchange Rates Java Client (oer-java)
==========================================

Open Exchange Rates is a free, hourly-updating API that provides rates for about 120 currencies using USD as base.

For more information see:

*    [GitHub Project][1]
*    [Official Site][2]

[1]:https://github.com/currencybot/open-exchange-rates
[2]:http://openexchangerates.org/

##How to Use##

Let's do a simple example. Let's get the latest rates from BRL(Brazilian Real)
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

For more information see the [documentation][1]
