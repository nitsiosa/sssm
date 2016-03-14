package com.jmp.interview.sssm.model.impl;

import java.util.Date;

import com.jmp.interview.sssm.model.Stock;
import com.jmp.interview.sssm.model.Trade;
import com.jmp.interview.sssm.model.enums.TradeType;

public class CommonTrade implements Trade{
	private Date timeStamp = null;
	private Stock stock = null;
	private TradeType tradeType = null;
	private int sharesQuantity = 0;
	private double price = 0.0;
	
	public CommonTrade(TradeType tradeType){
		 this.tradeType = tradeType;
	}
	
	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#getTimeStamp()
	 */
	@Override
	public Date getTimeStamp() {
		return timeStamp;
	}
	
	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#setTimeStamp(java.util.Date)
	 */
	@Override
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#getSharesQuantity()
	 */
	@Override
	public int getSharesQuantity() {
		return sharesQuantity;
	}
	
	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#setSharesQuantity(int)
	 */
	@Override
	public void setSharesQuantity(int sharesQuantity) {
		this.sharesQuantity = sharesQuantity;
	}

	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#getPrice()
	 */
	@Override
	public double getPrice() {
		return price;
	}
	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#setPrice(double)
	 */
	@Override
	public void setPrice(double price) {
		this.price = price;
	}
	
	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#getStock()
	 */
	@Override
	public Stock getStock() {
		return stock;
	}
	/* (non-Javadoc)
	 * @see com.jmp.interview.sssm.model.impl.Stock#setStock(com.jmp.interview.sssm.model.Stock)
	 */
	@Override
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		String pattern = "Trade Action: %s  -> timeStamp: %tB | stock: %s | quantity: %10d | price: %10.4f ";
		return String.format(pattern, tradeType, timeStamp,timeStamp, stock,  sharesQuantity, price);
	}
}
