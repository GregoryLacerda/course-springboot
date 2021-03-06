package com.gregory.course.entities.enums;

import java.util.Iterator;

public enum OrderStatus {
	
	//Atribuir valor manuelmente para evitar que uma alteração mude os valores de cada status
	
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	//Por colocar valores fixos se torna necessario alguma implementações.
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	//metodo que retorna o valor do codigo informado
	public static OrderStatus valueOf(int code) {
		for(OrderStatus value : OrderStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}
