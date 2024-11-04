package com.venancio.dam.tema2Acceso.ejercicio1;

public class Ciudad {
	private int id;
	private String nombre;
	private String codigoPais;
	private String distrito;
	private int poblacion;

	public Ciudad(int id, String nombre, String codigoPais, String distrito, int poblacion) {
		this.setId(id);
		this.setNombre(nombre);
		this.setCodigoPais(codigoPais);
		this.setDistrito(distrito);
		this.setPoblacion(poblacion);
	}

	public Ciudad(String nombre, String codPais, String distrito, int poblacion) {

		this.setNombre(nombre);
		this.setCodigoPais(codPais);
		this.setDistrito(distrito);
		this.setPoblacion(poblacion);
	}

	@Override
	public String toString() {
		return "Ciudad{" +
	            "\tid='" + getId() + '\'' +
	            ", \tnombre='" + getNombre() + '\'' +
	            ", \tcodPais='" + getCodigoPais() + '\'' +
	            ", \tdistrito='" + getDistrito() + '\'' +
	            ", \tpoblacion='" + getPoblacion() + '\'' +
	            '}';
	}

	public int getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
