package com.dayuanit.pay.mallenum;

import com.dayuanit.pay.exception.MallBusException;

public enum PayStatus {
	
	UNPAID(0, "未支付"), ALREADY_PAID(1, "已支付"), PAY_FAILURE(2, "支付失敗"), PAY_INVALID(3, "订单失效");
	
	private int status;
	private String msg;
	
	private PayStatus(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	public PayStatus getPayStatus(int status) {
		
		for (PayStatus ps : PayStatus.values()) {
			if (ps.getStatus() == status) {
				return ps;
			}
		}
		
		throw new MallBusException("无效的订单状态");
	}

	public int getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}
	
}
