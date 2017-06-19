package com.grupo6.persistence.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "espectaculo")
@NamedEntityGraph(name = "Espectaculo.Full", attributeNodes = {
        @NamedAttributeNode(value = "realizacionEspectaculo", subgraph = "Realizaciones")
       ,@NamedAttributeNode(value = "tipoEspectaculo")
    }, subgraphs = {
        @NamedSubgraph(name = "Realizaciones", attributeNodes = {
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
	
	@ManyToMany
	@JoinTable(name = "espectaculo_tipo_espectaculo",
			joinColumns=@JoinColumn(name="id_espectaculo", referencedColumnName="id_espectaculo"),
			inverseJoinColumns=@JoinColumn(name="id_tipo_espectaculo", referencedColumnName="id_tipo_espectaculo"))
	private Set<TipoEspectaculo> tipoEspectaculo;

	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_espectaculo")
	@Fetch (FetchMode.SELECT)
	private Set<RealizacionEspectaculo> realizacionEspectaculo;

	public Espectaculo(){
		this.tipoEspectaculo = new HashSet<TipoEspectaculo>();
		this.realizacionEspectaculo = new HashSet<RealizacionEspectaculo>();
	}
	
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

	public Set<TipoEspectaculo> getTipoEspectaculo() {
		return tipoEspectaculo;
	}

	public void setTipoEspectaculo(Set<TipoEspectaculo> tipoEspectaculo) {
		this.tipoEspectaculo = tipoEspectaculo;
	}

	public Set<RealizacionEspectaculo> getRealizacionEspectaculo() {
		return realizacionEspectaculo;
	}

	public void setRealizacionEspectaculo(Set<RealizacionEspectaculo> realizacionEspectaculo) {
		this.realizacionEspectaculo = realizacionEspectaculo;
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
		Espectaculo other = (Espectaculo) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
