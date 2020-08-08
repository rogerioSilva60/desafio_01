package br.com.desafio.util.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sexo {

	MASCULINO("Masculino"), FEMININO("Feminino");
	
	private String valor;
	Sexo(String valor){
		this.valor = valor;
	}

	@JsonValue
	public String getValor() {
		return valor;
	}
	
	@JsonCreator
    public static Sexo converterValor(String valor) {
        if(valor != null){
            for (Sexo sexo : values()) {
                if (sexo.valor.equalsIgnoreCase(valor)) {
                    return sexo;
                }
            }
        }
        throw new IllegalArgumentException(
                "Tipo de enumeração desconhecido '" + valor + "', Os valores permitidos para 'Sexo' são " + Arrays.toString(values()));
    }

    @Override
    public String toString() { return valor; }
}

