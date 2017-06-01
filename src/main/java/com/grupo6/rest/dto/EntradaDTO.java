package com.grupo6.rest.dto;

import java.util.Date;

public class EntradaDTO {

	private long id;
	
	private int numeroAsiento;
	
	private Date fechaCompra;
	
	private int precio;

	private Long idRealizacionEspectaculo;
	
	private Long idVendedor;
	
	private Long idUsuario;
	
	private Long idPortero;
	
	private Long idSector;

	private Long idEspectaculo;

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

	public Long getIdRealizacionEspectaculo() {
		return idRealizacionEspectaculo;
	}

	public void setIdRealizacionEspectaculo(Long idRealizacionEspectaculo) {
		this.idRealizacionEspectaculo = idRealizacionEspectaculo;
	}

	public Long getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdPortero() {
		return idPortero;
	}

	public void setIdPortero(Long idPortero) {
		this.idPortero = idPortero;
	}

	public Long getIdSector() {
		return idSector;
	}

	public void setIdSector(Long idSector) {
		this.idSector = idSector;
	}

	public Long getIdEspectaculo() {
		return idEspectaculo;
	}

	public void setIdEspectaculo(Long idEspectaculo) {
		this.idEspectaculo = idEspectaculo;
	}
	
	
}
