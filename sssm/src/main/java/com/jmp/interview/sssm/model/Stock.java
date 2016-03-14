package com.jmp.interview.sssm.model;

import com.jmp.interview.sssm.model.enums.StockType;

public interface Stock {
	String getStockSymbol();

	void setStockSymbol(String stockSymbol);

	StockType getStockType();

	void setStockType(StockType stockType);

	double getLastDividend();

	void setLastDividend(double lastDividend);

	double getFixedDividend();

	void setFixedDividend(double fixedDividend);

	double getParValue();

	void setParValue(double parValue);

	double getDividendYield();

	double getPeRatio();

	double getTickerPrice();

	void setTickerPrice(double tickerPrice);
}
