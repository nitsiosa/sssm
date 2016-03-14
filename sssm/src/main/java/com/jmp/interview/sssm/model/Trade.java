package com.jmp.interview.sssm.model;

import java.util.Date;

public interface Trade {
	
	Date getTimeStamp();
	void setTimeStamp(Date timeStamp);
	int getSharesQuantity();
	void setSharesQuantity(int sharesQuantity);
	double getPrice();
	void setPrice(double price);
	Stock getStock();
	void setStock(Stock stock);

}
