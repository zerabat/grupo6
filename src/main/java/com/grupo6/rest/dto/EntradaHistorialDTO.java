package com.grupo6.rest.dto;

import java.util.Date;

import com.grupo6.persistence.model.Entrada;
import com.grupo6.persistence.model.HistorialEntradas;

public class EntradaHistorialDTO {

	private long id;
	
	private int numeroAsiento;
	
	private Date fechaCompra;
	
	private int precio;

	private String nombreEspectaculo;
	
	
	private String descEspectaculo;


	public EntradaHistorialDTO( ) {
	}
	public EntradaHistorialDTO(HistorialEntradas ent) {
		this.setId(ent.getId());
		this.setDescEspectaculo(ent.getEntrada().getEspectaculo().getDescripcion());
		this.setFechaCompra(ent.getFechaCompra());
		this.setNombreEspectaculo(ent.getEntrada().getEspectaculo().getNombre());
		this.setNumeroAsiento(ent.getEntrada().getNumeroAsiento());
		this.setPrecio(ent.getEntrada().getPrecio());
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumeroAsiento() {
		return numeroAsiento;
	}

	public void setNumeroAsiento(int numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getNombreEspectaculo() {
		return nombreEspectaculo;
	}

	public void setNombreEspectaculo(String nombreEspectaculo) {
		this.nombreEspectaculo = nombreEspectaculo;
	}

	public String getDescEspectaculo() {
		return descEspectaculo;
	}

	public void setDescEspectaculo(String descEspectaculo) {
		this.descEspectaculo = descEspectaculo;
	}
}
