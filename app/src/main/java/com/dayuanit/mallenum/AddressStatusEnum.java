package com.dayuanit.mallenum;

import com.dayuanit.exception.MallBusException;

public enum AddressStatusEnum {
	
	INVALID(1), NORMAL(0);
	
	private int status;
	
	public int getStatus() {
		return status;
	}

	private AddressStatusEnum(int status) {
		this.status = status;
	}
	
	public static AddressStatusEnum getAddStatus(int status) {
		
		for (AddressStatusEnum ae : AddressStatusEnum.values()) {
			if (status == ae.getStatus()) {
				return ae;
			}
		}
		
		throw new MallBusException("地址状态异常");
	}
}
