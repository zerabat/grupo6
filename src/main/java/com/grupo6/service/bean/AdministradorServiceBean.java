package com.grupo6.service.bean;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo6.persistence.model.AdministradorTenant;
import com.grupo6.persistence.model.Portero;
import com.grupo6.persistence.model.Vendedor;
import com.grupo6.persistence.repository.AdministradorTenantRepository;
import com.grupo6.persistence.repository.PorteroRepository;
import com.grupo6.persistence.repository.VendedorRepository;
import com.grupo6.service.AdministradorService;

@Service
public class AdministradorServiceBean implements AdministradorService {

	@Autowired
	private AdministradorTenantRepository administradorRepository;

	@Autowired
	private VendedorRepository vendedorRepository;

	@Autowired
	private PorteroRepository porteroRepository;

	@Override
	public void agregarAdministrador(AdministradorTenant dtos) {

		String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(dtos.getPassowd());
		dtos.setPassowd(sha256hex);
		administradorRepository.save(dtos);

	}

	@Override
	public void agregarVendedor(Vendedor vendedor) {
		vendedorRepository.save(vendedor);
	}

	@Override
	public void agregarPortero(Portero portero) {
		String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(portero.getPassword());
		portero.setPassword(sha256hex);
		porteroRepository.save(portero);
		
	}

	@Override
	public Optional<AdministradorTenant> login(String email) {
		return administradorRepository.findByEmail(email);
	}

	@Override
	public List<Portero> obenerPorteros() {
		return  porteroRepository.findAll();
	}

	@Override
	public void modificarPortero(Portero portero) {
		this.porteroRepository.save(portero);
		
	}

	@Override
	public void eliminarPortero(Long idPortero) {
		this.porteroRepository.delete(porteroRepository.findOne(idPortero).get());
	}
}
