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
@Table(name = "suscripcion_espectaculo")
public class SuscripcionEspectaculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_suscripcion")
	private long id;

	@Column(nullable = false)
	private Date fecha;
		
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_espectaculo")
	private Espectaculo espectaculo;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_realziacion_espectaculo")
	private RealizacionEspectaculo realizacionEspectaculo;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_tipo_espectaculoco")
	private TipoEspectaculo tipoEspectaculo;


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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Espectaculo getEspectaculo() {
		return espectaculo;
	}

	public void setEspectaculo(Espectaculo espectaculo) {
		this.espectaculo = espectaculo;
	}

	public RealizacionEspectaculo getRealizacionEspectaculo() {
		return realizacionEspectaculo;
	}

	public void setRealizacionEspectaculo(RealizacionEspectaculo realizacionEspectaculo) {
		this.realizacionEspectaculo = realizacionEspectaculo;
	}

	public TipoEspectaculo getTipoEspectaculo() {
		return tipoEspectaculo;
	}

	public void setTipoEspectaculo(TipoEspectaculo tipoEspectaculo) {
		this.tipoEspectaculo = tipoEspectaculo;
	}
	
}
