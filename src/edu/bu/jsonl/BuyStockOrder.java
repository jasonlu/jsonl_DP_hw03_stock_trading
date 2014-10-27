/**
 * 
 */
package edu.bu.jsonl;

/**
 * @author Jason Lu
 *
 */
public class BuyStockOrder implements Order {
	private StockTrade m_stockTrade;
	private String m_symbol;
	private int m_amount;
	public BuyStockOrder (StockTrade st, OrderDetail detail) {
    	m_stockTrade = st;
    	this.m_symbol = detail.symbol;
    	this.m_amount = detail.amount;
    }
    
    public void execute() throws WrongOrderException {
    	if(m_amount < 1 || m_symbol == "") {
    		// Exception...
    		throw new WrongOrderException("Wrong order");
    	} else {
    		m_stockTrade.buy(m_symbol, m_amount);
    	}
    }
}
