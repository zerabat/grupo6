package com.grupo6.persistence.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "realizacion_espectaculo")
public class RealizacionEspectaculo {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_realizacion_espectaculo")
	private long id;

	@Column(length = 50, nullable = false)
	private Date fecha;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_sala")
	private Sala sala;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_espectaculo")
	private Espectaculo espectaculo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Espectaculo getTipoEspectaculo() {
		return espectaculo;
	}

	public void setTipoEspectaculo(Espectaculo espectaculo) {
		this.espectaculo = espectaculo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Espectaculo getEspectaculo() {
		return espectaculo;
	}

	public void setEspectaculo(Espectaculo espectaculo) {
		this.espectaculo = espectaculo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RealizacionEspectaculo other = (RealizacionEspectaculo) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
