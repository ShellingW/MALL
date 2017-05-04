package com.dayuanit.mallenum;

import com.dayuanit.exception.MallBusException;

public enum AddressDefualtEnum {
	
	ADD_NON_DEFAULT(0), ADD_DEFAULT(1);
	
	private int flag;
	
	private AddressDefualtEnum(int flag) {
		this.flag = flag;
	}
	
	public int getFlag() {
		return this.flag;
	}
	
	public static AddressDefualtEnum getAddDefulat(int flag) {
		
		for (AddressDefualtEnum ae : AddressDefualtEnum.values()) {
			if (flag == ae.getFlag()) {
				return ae;
			}
		}
		
		throw new MallBusException("地址默认值错误");
	}

}
