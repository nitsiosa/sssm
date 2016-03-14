package com.jmp.interview.sssm.repository;

import java.util.List;
import java.util.Map;

import com.jmp.interview.sssm.model.Stock;
import com.jmp.interview.sssm.model.Trade;

public interface StockRepo {

	/**
	 * Record a trade, with timestamp, quantity of shares, buy or sell indicator and traded price
	 * @param trade Trade.
	 * @return True, when successful
	 * @throws Java Exception.
	 */
	public boolean recordTrade(Trade trade) throws Exception;
	
	/**
	 * Get the all stocks in a List 
	 * 
	 * @return The array list that contains all the trades in the Super Simple Stocks application.
	 */
	public List<Trade> getTrades();
	
	/**
	 * Search by ticker
	 * @param stockSymbol
	 * @return
	 */
	public Stock searchByStockSymbol(String stockSymbol);
	
	/**
	 * Get the all stocks in a Map
	 * 
	 * @return The Map with all the stocks
	 */
	public Map<String, Stock> getStocks();
}
