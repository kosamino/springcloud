package com.book.trade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.order.pojo.Orders;
import com.book.trade.mapper.TradeMapper;
import com.book.trade.pojo.Trade;
import com.book.trade.pojo.TradeExample;
import com.codingapi.tx.annotation.TxTransaction;

@Service
public class TradeServiceImpl {

	@Autowired
	private TradeMapper tradeMapper;
	
	@Autowired
	private ProviderOrderService providerOrderService;
	
	/**
	 * 查询所有交易信息
	 */
	public List<Trade> findTradeAll(){
		TradeExample example = new TradeExample();
		return this.tradeMapper.selectByExample(example);
	}
	
	/**
	 * 添加交易信息
	 * @TxTransaction - LCN提供的分布式事务管理注解。代表当前方法需要进入LCN事务组。
	 * 如果当前方法是分布式事务的起始端，则要增加属性isStart=true。属性默认值为false。
	 * isStart属性代表，当前是否是事务的开始，如果是，则创建事务组。如果不是事务的开始，则加入事务组。
	 */
	@Transactional
	@TxTransaction(isStart=true)
	public void addTrade(Trade trade){
		this.tradeMapper.insert(trade);
		// 根据ID查询订单
		Orders order = this.providerOrderService.findOrderById(trade.getOrderId());
		order.setTradeId(trade.getId());
		// 更新订单
		this.providerOrderService.updateOrder(order);
	}
}
