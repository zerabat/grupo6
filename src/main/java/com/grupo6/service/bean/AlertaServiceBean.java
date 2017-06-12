package com.grupo6.service.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.persistence.model.RealizacionEspectaculo;
import com.grupo6.persistence.repository.EspectaculoRepository;
import com.grupo6.persistence.repository.RealizacionEspectaculoRepository;

@Service
public class AlertaServiceBean {

	@Autowired
	private RealizacionEspectaculoRepository realizacionEspectaculoRepository;
	

	@Autowired
	private EspectaculoRepository espectaculoRepository;
	
	// conr expression que se corra una vez al dia
	public void alertaPorCorre(){
		
		List<Espectaculo>  esp = espectaculoRepository.findAllActivos(new Date());
		for(Espectaculo e :esp){
			for(RealizacionEspectaculo re : e.getRealizacionEspectaculo()){
				Calendar cal1 = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
				cal1.add(Calendar.DATE, 4);
				cal2.add(Calendar.DATE, 5);
				if (re.getFecha().after(cal1.getTime())  && re.getFecha().before(cal2.getTime())){
					//mandar alerta a todos los usuarios suscriptos
				}
				Calendar cal3 = Calendar.getInstance();
				
				
				
			}
		}
	}
	
	public void alertasPush(){
		
	}
}
