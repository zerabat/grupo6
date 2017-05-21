package com.grupo6.persistence.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.grupo6.persistence.model.AdministradorTenant;

@Repository
public interface AdministradorTenantRepository extends BaseRepository <AdministradorTenant, Integer>{

	Optional<AdministradorTenant> findByEmail(String email);

}
