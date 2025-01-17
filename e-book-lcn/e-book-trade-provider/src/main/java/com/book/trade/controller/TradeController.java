package com.book.trade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.trade.pojo.Trade;
import com.book.trade.service.TradeService;
import com.book.trade.service.TradeServiceImpl;

@RestController
public class TradeController implements TradeService {

	@Autowired
	private TradeServiceImpl tradeServiceImpl;
	
	@Override
	public List<Trade> findAll() {
		return this.tradeServiceImpl.findTradeAll();
	}

	@Override
	public void addTrade(@RequestBody Trade trade) {
		//添加交易信息
		this.tradeServiceImpl.addTrade(trade);
	}

}
