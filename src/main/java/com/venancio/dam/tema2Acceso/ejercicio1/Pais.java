package com.venancio.dam.tema2Acceso.ejercicio1;

public class Pais {
	private String codigo;
	private String nombre;
	private String continente;
	private String region;
	private int poblacion;

	public Pais(String codigo, String nombre, String continente, String region, int poblacion) {
		this.setCodigo(codigo);
		this.setNombre(nombre);
		this.setContinente(continente);
		this.setRegion(region);
		this.setPoblacion(poblacion);
	}
	
	@Override
	public String toString() {
	    return "Pais{" +
	            "\tcodigo='" + getCodigo() + '\'' +
	            ", \tnombre='" + getNombre() + '\'' +
	            ", \tcontinente='" + getContinente() + '\'' +
	            ", \tregion='" + getRegion() + '\'' +
	            ", \tpoblacion='" + getPoblacion() + '\'' +
	            '}';
	}

	public int getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	

}
