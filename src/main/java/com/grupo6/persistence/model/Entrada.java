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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;

@Entity
@Table(name = "entrada")
@NamedEntityGraph(name = "Entrada.Full", attributeNodes = {
       @NamedAttributeNode(value = "usuario"),
       @NamedAttributeNode(value = "realizacionEspectaculo")
    })
public class Entrada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_entrada")
	private long id;
	
	private int numeroAsiento;
	
	private Date fechaCompra;
	
	private int precio;

	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_realizacion_espectaculo")
	private RealizacionEspectaculo realizacionEspectaculo;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_vendedor")
	private Vendedor vendedor;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_portero")
	private Portero portero;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_sector")
	private Sector sector;
	

	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_espectaculo")
	private Espectaculo espectaculo;


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


	public RealizacionEspectaculo getRealizacionEspectaculo() {
		return realizacionEspectaculo;
	}


	public void setRealizacionEspectaculo(RealizacionEspectaculo realizacionEspectaculo) {
		this.realizacionEspectaculo = realizacionEspectaculo;
	}


	public Vendedor getVendedor() {
		return vendedor;
	}


	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Portero getPortero() {
		return portero;
	}


	public void setPortero(Portero portero) {
		this.portero = portero;
	}


	public Sector getSector() {
		return sector;
	}


	public void setSector(Sector sector) {
		this.sector = sector;
	}


	public Espectaculo getEspectaculo() {
		return espectaculo;
	}


	public void setEspectaculo(Espectaculo espectaculo) {
		this.espectaculo = espectaculo;
	}
	
}
