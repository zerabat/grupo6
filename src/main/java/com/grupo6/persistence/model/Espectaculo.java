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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.grupo6.rest.dto.EspectaculoDTO;

@Entity
@Table(name = "espectaculo")
@NamedEntityGraph(name = "Espectaculo.Full", attributeNodes = {
        @NamedAttributeNode(value = "realizacionEspectaculo", subgraph = "Relizaciones"),
        @NamedAttributeNode(value = "tipoEspectaculo")
    }, subgraphs = {
        @NamedSubgraph(name = "Relizaciones", attributeNodes = {
            @NamedAttributeNode(value = "sala")
        })
    })
public class Espectaculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_espectaculo")
	private long id;

	@Column(length = 50, nullable = false)
	private String nombre;
	
	@Column(length = 50, nullable = false)
	private String descripcion;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_tipo_espectaculo")
	private TipoEspectaculo tipoEspectaculo;

	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_realizacion_espectaculo")
	private List<RealizacionEspectaculo> realizacionEspectaculo;// = new ArrayList<>();
	
//	@Exclude
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Contact> contacts;
//	Espectaculo(){
//		this.realizacionEspectaculo = new ArrayList<RealizacionEspectaculo>();
//	}

	
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

	public List<RealizacionEspectaculo> getRealizacionEspectaculo() {
		return realizacionEspectaculo;
	}

	public void setRealizacionEspectaculo(List<RealizacionEspectaculo> realizacionEspectaculo) {
		this.realizacionEspectaculo = realizacionEspectaculo;
	}
	
}
