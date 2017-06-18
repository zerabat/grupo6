package com.grupo6.rest.dto;

import java.util.Date;

import com.grupo6.persistence.model.SuscripcionEspectaculo;

public class SuscripcionEspectaculoDTO {

	private long id;

	private Date fecha;
		
	private String  nombreUsuario;
	private long  idUsuario;

	private String nombreEspectaculo;
	private long idEspectaculo;
	
	private long  idRealizacionEspectaculo;
	
	private String nombreTipoEspectaculo;
	private long idTipoEspectaculo;
	
	public SuscripcionEspectaculoDTO(SuscripcionEspectaculo se) {
		this.setFecha( se.getFecha());
		this.setId(se.getId());
		if (se.getEspectaculo() != null){
			this.setIdEspectaculo(se.getEspectaculo().getId());
			this.setNombreEspectaculo(se.getEspectaculo().getNombre());
		}
		if (se.getRealizacionEspectaculo() != null){
			this.setIdRealizacionEspectaculo(se.getRealizacionEspectaculo().getId());
		}
		if (se.getTipoEspectaculo() != null ){
			this.setIdTipoEspectaculo(se.getTipoEspectaculo().getId());
			this.setNombreTipoEspectaculo(se.getTipoEspectaculo().getNombre());
		}
		this.setIdUsuario(se.getUsuario() == null ? null : se.getUsuario().getId());
		this.setNombreUsuario(se.getUsuario().getNombre());
		
	}

	public SuscripcionEspectaculoDTO() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombreEspectaculo() {
		return nombreEspectaculo;
	}
	public void setNombreEspectaculo(String nombreEspectaculo) {
		this.nombreEspectaculo = nombreEspectaculo;
	}
	public long getIdEspectaculo() {
		return idEspectaculo;
	}
	public void setIdEspectaculo(long idEspectaculo) {
		this.idEspectaculo = idEspectaculo;
	}

	public long getIdRealizacionEspectaculo() {
		return idRealizacionEspectaculo;
	}

	public void setIdRealizacionEspectaculo(long idRealizacionEspectaculo) {
		this.idRealizacionEspectaculo = idRealizacionEspectaculo;
	}

	public String getNombreTipoEspectaculo() {
		return nombreTipoEspectaculo;
	}

	public void setNombreTipoEspectaculo(String nombreTipoEspectaculo) {
		this.nombreTipoEspectaculo = nombreTipoEspectaculo;
	}

	public long getIdTipoEspectaculo() {
		return idTipoEspectaculo;
	}

	public void setIdTipoEspectaculo(long idTipoEspectaculo) {
		this.idTipoEspectaculo = idTipoEspectaculo;
	}

	
	
}
