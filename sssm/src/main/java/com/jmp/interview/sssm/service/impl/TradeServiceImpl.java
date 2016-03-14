package com.jmp.interview.sssm.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.jmp.interview.sssm.model.Stock;
import com.jmp.interview.sssm.model.Trade;
import com.jmp.interview.sssm.repository.StockRepo;
import com.jmp.interview.sssm.service.TradeService;

public class TradeServiceImpl implements TradeService{
	private Logger logger = Logger.getLogger(TradeServiceImpl.class);
	private StockRepo stocksRepository = null;

	
	public TradeServiceImpl(StockRepo stocksRepository ){
		this.stocksRepository = stocksRepository;
	}
	
	private Stock checkStock(String  stockSymbol) throws Exception{
		Stock stock = stocksRepository.searchByStockSymbol(stockSymbol);
		if(stock==null){
			throw new Exception("The stock with ticker ("+stockSymbol+") can not be found ");
		}else if(stock.getTickerPrice() <= 0.0){
			throw new Exception("The stock with ticker ("+stockSymbol+") does not have a valiud price, this will cause a div by zero error");
		}
		return stock;
	}
	
	private void checkTrade(Trade trade) throws Exception{
		if(trade==null || trade.getStock()==null || trade.getSharesQuantity()<=0 || trade.getPrice()<=0.0 ){
			throw new Exception("Trade is invalid plase check the details"+trade);
		}
	}
	
	@Override
	public double calculateDividendYield(String stockSymbol) throws Exception {
		double dividendYield = 0.0;
		try{
			dividendYield = checkStock(stockSymbol).getDividendYield();
		}catch(Exception exception){
			String errorMsg = "Error calculating Dividend Yield for the stock wotht ticker : "+stockSymbol;
			logger.error(errorMsg, exception);
			throw new Exception(errorMsg, exception);
		}
		return dividendYield;
	}

	@Override
	public double calculatePERatio(String stockSymbol) throws Exception {
		double peRatio = -1.0;
		try{
			peRatio = checkStock(stockSymbol).getPeRatio();
		}catch(Exception exception){
			String errorMsg = "Error calculating  P/E Ratio for the stock wotht ticker : "+stockSymbol;
			logger.error(errorMsg, exception);
			throw new Exception(errorMsg, exception);
		}
		return peRatio;
	}

	@Override
	public boolean recordTrade(Trade trade) throws Exception {
		try{
			checkTrade(trade);
			if(stocksRepository.recordTrade(trade)){
				trade.getStock().setTickerPrice(trade.getPrice());
			}
			return true;
		}catch(Exception exception){
			String errorMsg = "Error recording trade -> "+trade;
			logger.error(errorMsg, exception);
			throw new Exception(errorMsg, exception);
		}
	}

	
	
	
	@Override
	public double calculateVolumeWeightedStockPrice(String stockSymbol,int minutes) throws Exception {
		
		Calendar baseDate = Calendar.getInstance();
		baseDate.add(Calendar.MINUTE, -minutes);
		
		List<Trade> filteredTrades = stocksRepository.getTrades().stream().filter(t -> t.getStock().getStockSymbol().equals(stockSymbol)).filter(t -> t.getTimeStamp().compareTo(baseDate.getTime())>0).collect(Collectors.toList());
		
		double tradePriceSum = 0.0;
		double shareQuantitySum = 0.0;
		
		for (Trade filteredTrade : filteredTrades) {
			tradePriceSum += (filteredTrade.getPrice() * filteredTrade.getSharesQuantity());
			shareQuantitySum += filteredTrade.getSharesQuantity();
		}
		
		if(shareQuantitySum > 0.0)
			return tradePriceSum / shareQuantitySum;	

		return 0.0;
	}
	
	@Override
	public double calculateVolumeWeightedStockPriceLast15min(String stockSymbol) throws Exception {
		return calculateVolumeWeightedStockPrice(stockSymbol,15);
	}

	@Override
	public double calculateGBCEAllShareIndex() throws Exception {
		Map<String, Stock> stocks = stocksRepository.getStocks();

		List<Double> stockPrices = new ArrayList<Double>();
		for(String stockSymbol: stocks.keySet() ){
			double stockPrice = calculateVolumeWeightedStockPrice(stockSymbol, 0);
			if(stockPrice > 0){
				stockPrices.add(stockPrice);
			}
		}
		
		if(stockPrices.size()>=1){
			double[] stockPricesArray = new double[stockPrices.size()];
			
			for(int i=0; i<=(stockPrices.size()-1); i++){
				stockPricesArray[i] = stockPrices.get(i).doubleValue();
			}
			return geometricMean(stockPricesArray);
		}
		return 0.0;
	}
	 private  double geometricMean(double[] x) {
	        int n = x.length;
	        double GM_log = 0.0d;
	        for (int i = 0; i < n; ++i) {
	            if (x[i] == 0L) {
	                return 0.0d;
	            }
	            GM_log += Math.log(x[i]);
	        }
	        return Math.exp(GM_log / n);
	    }
}
