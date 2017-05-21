package com.grupo6.persistence.repository;

import org.springframework.stereotype.Repository;

import com.grupo6.persistence.model.Sala;
import com.grupo6.persistence.model.Sector;

@Repository
public interface SectorRepository extends BaseRepository <Sector, Integer>{

	void findBySala(Sala sala);

}
