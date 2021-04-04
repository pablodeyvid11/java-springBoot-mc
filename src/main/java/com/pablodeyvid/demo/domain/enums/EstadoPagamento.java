package com.pablodeyvid.demo.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private Integer code;
	private String msg;

	private EstadoPagamento(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public static EstadoPagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (EstadoPagamento e : EstadoPagamento.values()) {
			if (cod.equals(e.getCode())) {
				return e;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
