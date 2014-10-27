/**
 * 
 */
package edu.bu.jsonl;

/**
 * @author Jason Lu
 *
 */
public class StockTrade {
	public void buy(String symbol, int amount) {
        System.out.println("Buy " + amount + " " + symbol + " stocks"  );
	}
	
    public void sell(String symbol, int amount) {
    	System.out.println("Sell " + amount + " " + symbol + " stocks"  );
    }
}
