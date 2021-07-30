package com.br.vinicius.Teste.model;

public enum Sexo {
	
	MASCULINO("Masculino"),
	FEMININO("Feminino");

	private String tipo;

	private Sexo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
}
