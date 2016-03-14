package com.jmp.interview.sssm.model.impl;

import org.apache.log4j.Logger;

import com.jmp.interview.sssm.model.Stock;
import com.jmp.interview.sssm.model.enums.StockType;

public class SimpleStock implements Stock {
	private Logger logger = Logger.getLogger(SimpleStock.class);
	private String stockSymbol = null;
	private StockType stockType = null;
	private double lastDividend = 0.0;
	private double fixedDividend = 0.0;
	private double parValue = 0.0;
	private double tickerPrice = 0.0; 
	
	public SimpleStock(StockType stockType ){
		this.stockType = stockType;
	}

	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#getStockSymbol()
	 */
	@Override
	public String getStockSymbol() {
		return stockSymbol;
	}
	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#setStockSymbol(java.lang.String)
	 */
	@Override
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#getStockType()
	 */
	@Override
	public StockType getStockType() {
		return stockType;
	}
	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#setStockType(com.jmp.interview.sssm.model.enums.StockType)
	 */
	@Override
	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}

	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#getLastDividend()
	 */
	@Override
	public double getLastDividend() {
		return lastDividend;
	}
	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#setLastDividend(double)
	 */
	@Override
	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#getFixedDividend()
	 */
	@Override
	public double getFixedDividend() {
		return fixedDividend;
	}
	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#setFixedDividend(double)
	 */
	@Override
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#getParValue()
	 */
	@Override
	public double getParValue() {
		return parValue;
	}
	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#setParValue(double)
	 */
	@Override
	public void setParValue(double parValue) {
		this.parValue = parValue;
	}

	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#getDividendYield()
	 */
	@Override
	public double getDividendYield() {
		double dividendYield = -1.0;

		if(tickerPrice > 0.0)
			switch (stockType){
			case PREFERRED:
				dividendYield = (fixedDividend * parValue ) / tickerPrice;
				break;
			default:
			//	if Stock is of type common 
				dividendYield = lastDividend / tickerPrice;
			}
		return dividendYield;
	}

	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#getPeRatio()
	 */
	@Override
	public double getPeRatio() {
		double peRatio = -1.0;
		
		if(tickerPrice > 0.0){
			peRatio = tickerPrice / getDividendYield();	
		}
		
		return peRatio;
	}

	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#getTickerPrice()
	 */
	@Override
	public double getTickerPrice() {
		return tickerPrice;
	}
	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#setTickerPrice(double)
	 */
	@Override
	public void setTickerPrice(double tickerPrice) {
		logger.debug("Changing ticker price to: "+tickerPrice);
		this.tickerPrice = tickerPrice;
		logger.debug("Ticker price for "+stockSymbol+": "+tickerPrice);

	}
	
	@Override
	public String toString() {
		String pattern = "Stock (%s) ->  stockSymbol: %s | lastDividend: %10.2f | fixedDividend: %10.4f | parValue: %10.4f";
		return String.format(pattern, stockType, stockSymbol, lastDividend, fixedDividend, parValue);
	}
}
