package com.jmp.interview.sssm.service;

import com.jmp.interview.sssm.model.Trade;

public interface TradeService {
	/**
	 * Calculates the dividend yield |  if Common -> Last Dividend/Price , if Prefered -> Fixed Dividend . Par Value/ Price
	 * @param stockSymbol Stock symbol.
	 * @return The dividend yield.
	 * @throws Java Exception 
	 */
	public double calculateDividendYield(String stockSymbol) throws Exception;
	
	/**
	 * Calculates the P/E Ratio | Price / Dividend
	 * @param stockSymbol Stock symbol.
	 * @return P/E Ratio.
	 * @throws Java Exception.
	 */
	public double calculatePERatio(String stockSymbol) throws Exception;
	
	/**
	 * Record a trade trade, with timestamp, quantity of shares, buy or sell indicator and traded price
	 * @param trade Trade.
	 * @return True, when successful
	 * @throws Java Exception.
	 */
	public boolean recordTrade(Trade trade) throws Exception;
	
	/**
	 * Calculate Volume Weighted Stock Price based on trades in given period
	 * @param stockSymbol Stock symbol.
	 * @return Volume Weighted Stock Price
	 * @throws Java Exception
	 */
	public double calculateVolumeWeightedStockPrice(String stockSymbol,int minutes) throws Exception;
	
	/**
	 * Calculate the GBCE All Share Index using the geometric mean of prices for all stocks
	 * @return GBCE All Share Index 
	 * @throws Java Exception
	 */
	public double calculateGBCEAllShareIndex() throws Exception;
	/**
	 * Calculate Volume Weighted Stock Price based on trades in past 15 minutes
	 * @param stockSymbol Stock symbol.
	 * @return Volume Weighted Stock Price
	 * @throws Java Exception
	 */
	double calculateVolumeWeightedStockPriceLast15min(String stockSymbol) throws Exception;

}
