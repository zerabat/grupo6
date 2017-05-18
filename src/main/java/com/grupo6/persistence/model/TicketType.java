package com.grupo6.persistence.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.grupo6.rest.dto.TicketTypeDTO;

@Entity
@Table(name = "ticket_type")
public class TicketType {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, name = "fecha_evento")
	private Date fechaEvento;

	@Column(nullable = false, name = "nombre_evento")
	private String nombreEvento;

	public TicketType(TicketTypeDTO ttDTO) {
		this.fechaEvento = ttDTO.getFechaEvento();
	}

	public TicketType(String nombreEvento, Date fechaEvento) {
		this.fechaEvento = fechaEvento;
		this.nombreEvento = nombreEvento;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

}
