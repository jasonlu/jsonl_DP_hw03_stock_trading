/**
 * 
 */
package edu.bu.jsonl;

/**
 * @author Jason Lu
 *
 */
public class OrderDetail {
	int amount;
	String symbol;
	
	public OrderDetail(String symbol, int amount) {
		this.symbol = symbol;
		this.amount = amount;
	}

}
