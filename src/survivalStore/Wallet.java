package survivalStore;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Wallet {
	double money;
	
	Wallet() {
		money = Math.random();
		money = Math.floor(money*1000000)/100;
		
		System.out.println(money);
	}
	
	public String toString() {
		String moneyString = String.format("%.2f", money);
		String walletString = "Your wallet contains:   $" + moneyString;
		return walletString;
	}

}
