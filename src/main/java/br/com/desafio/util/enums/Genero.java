package br.com.desafio.util.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Genero {

	MASCULINO("Masculino"), FEMININO("Feminino");
	
	private String valor;
	Genero(String valor){
		this.valor = valor;
	}

	@JsonValue
	public String getValor() {
		return valor;
	}
	
	@JsonCreator
    public static Genero converterValor(String valor) {
        if(valor != null){
            for (Genero genero : values()) {
                if (genero.valor.equalsIgnoreCase(valor)) {
                    return genero;
                }
            }
        }
        throw new IllegalArgumentException(
                "Tipo de enumeração desconhecido '" + valor + "', Os valores permitidos para 'Genero' são " + Arrays.toString(values()));
    }

    @Override
    public String toString() { return valor; }
}

