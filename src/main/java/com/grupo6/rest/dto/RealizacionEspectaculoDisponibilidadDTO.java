package com.grupo6.rest.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.grupo6.persistence.model.RealizacionEspectaculo;

public class RealizacionEspectaculoDisponibilidadDTO {

	private long id;

	private Date fecha;
	
	private long idEspectaculo;
	
	private List<SectorDisponibilidadDTO> sectores; 
	
	private SalaDTO sala;

	public RealizacionEspectaculoDisponibilidadDTO(){
		
	}
	public RealizacionEspectaculoDisponibilidadDTO(RealizacionEspectaculo x) {
		this.id = x.getId();
		this.fecha = x.getFecha();
		this.sala = new SalaDTO (x.getSala());
		this.idEspectaculo = x.getEspectaculo().getId();
		this.sectores = new ArrayList<SectorDisponibilidadDTO>();
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

	public long getIdEspectaculo() {
		return idEspectaculo;
	}

	public void setIdEspectaculo(long idEspectaculo) {
		this.idEspectaculo = idEspectaculo;
	}
	public List<SectorDisponibilidadDTO> getSectores() {
		return sectores;
	}
	public void setSectores(List<SectorDisponibilidadDTO> sectores) {
		this.sectores = sectores;
	}
	
	public SalaDTO getSala() {
		return sala;
	}
	public void setSala(SalaDTO sala) {
		this.sala = sala;
	}
	
}
