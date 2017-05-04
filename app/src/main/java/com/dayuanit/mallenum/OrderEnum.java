package com.dayuanit.mallenum;

import com.dayuanit.exception.MallBusException;

public enum OrderEnum {
	未支付(0), 已支付(1), 失效(2), 删除(3);
	
	private int status;
	
	public int getStatus() {
		return status;
	}

	private OrderEnum(int status) {
		this.status = status;
	}
	
	public static OrderEnum getOrderStatus(int statu) {
		
		for (OrderEnum om : OrderEnum.values()) {
			if (statu == om.getStatus()) {
				return om;
			}
		}
		
		throw new MallBusException("订单状态错误");
	}

}
