package com.grupo6.service;

import java.util.List;

import com.grupo6.persistence.model.Sector;

public interface SectorService {

	void altaSector(Sector sector);

	List<Sector> obtenerSectoresSala(String salaId);

}
