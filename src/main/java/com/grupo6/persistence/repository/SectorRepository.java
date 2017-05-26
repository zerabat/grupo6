package com.grupo6.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.grupo6.persistence.model.Sector;

@Repository
public interface SectorRepository extends BaseRepository <Sector, Long>{

	List<Sector> findBySalaId(Long valueOf);

}
