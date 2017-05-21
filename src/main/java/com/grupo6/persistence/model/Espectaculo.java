package com.grupo6.persistence.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "espectaculo")
public class Espectaculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 50, nullable = false)
	private String nombre;
	
	@Column(length = 50, nullable = false)
	private String descripcion;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_tipo_espectaculo")
	private TipoEspectaculo tipoEspectaculo;

	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
//	@JoinColumn(name = "id_realizacion_espectaculo")
	private List<RealizacionEspectaculo> realizacionEspectaculo;
	
//	@Exclude
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Contact> contacts;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoEspectaculo getTipoEspectaculo() {
		return tipoEspectaculo;
	}

	public void setTipoEspectaculo(TipoEspectaculo tipoEspectaculo) {
		this.tipoEspectaculo = tipoEspectaculo;
	}
	
}
