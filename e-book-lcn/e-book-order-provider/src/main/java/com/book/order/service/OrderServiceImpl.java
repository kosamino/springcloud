package com.book.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.order.mapper.OrdersMapper;
import com.book.order.pojo.Orders;
import com.book.order.pojo.OrdersExample;
import com.codingapi.tx.annotation.TxTransaction;

@Service
public class OrderServiceImpl {

	@Autowired
	private OrdersMapper orderMapper;
	
	/**
	 * 查询所有订单
	 */
	public List<Orders> findOrderAll(){
		OrdersExample example = new OrdersExample();
		return this.orderMapper.selectByExample(example);
	}
	
	/**
	 * 添加订单
	 */
	public Integer createOrder(Orders order){
		this.orderMapper.insert(order);
		return order.getId();
	}
	
	/**
	 * 根据ID查询订单
	 */
	public Orders findOrderById(Integer id){
		return this.orderMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 更新订单
	 */
	@Transactional
	@TxTransaction
	public void updateOrder(Orders order){
		this.orderMapper.updateByPrimaryKey(order);
		System.out.println(1 / 0);
	}
}
