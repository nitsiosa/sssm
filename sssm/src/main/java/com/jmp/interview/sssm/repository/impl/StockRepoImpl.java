package com.jmp.interview.sssm.repository.impl;

import java.util.List;
import java.util.Map;

import com.jmp.interview.sssm.model.Stock;
import com.jmp.interview.sssm.model.Trade;
import com.jmp.interview.sssm.repository.StockRepo;

public class StockRepoImpl implements StockRepo{

	@Override
	public boolean recordTrade(Trade trade) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Trade> getTrades() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stock searchByStockSymbol(String stockSymbol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Stock> getStocks() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
