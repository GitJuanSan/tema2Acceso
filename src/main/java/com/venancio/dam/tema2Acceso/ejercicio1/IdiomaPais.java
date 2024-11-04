package com.venancio.dam.tema2Acceso.ejercicio1;

public class IdiomaPais {
	private String codigoPais;
	private String idioma;
	private boolean isOficial;
	private float porcentaje;

	public IdiomaPais(String codPais, String idioma, boolean isOficial, float porcentaje) {
		this.codigoPais = codPais;
		this.idioma = idioma;
		this.isOficial = isOficial;
		this.porcentaje = porcentaje;
	}
	
	@Override
	public String toString() {
	    return "IdiomaPais{" +
	            "codigoPais='" + codigoPais + '\'' +
	            ", idioma='" + idioma + '\'' +
	            ", isOficial=" + isOficial +
	            ", porcentaje=" + porcentaje +
	            '}';
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codPais) {
		this.codigoPais = codPais;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public boolean isOficial() {
		return isOficial;
	}

	public void setOficial(boolean isOficial) {
		this.isOficial = isOficial;
	}

	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}
}
