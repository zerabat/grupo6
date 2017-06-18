package com.grupo6.service.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.persistence.model.RealizacionEspectaculo;
import com.grupo6.persistence.model.SuscripcionEspectaculo;
import com.grupo6.persistence.repository.EspectaculoRepository;
import com.grupo6.persistence.repository.RealizacionEspectaculoRepository;
import com.grupo6.persistence.repository.SuscripcionEspectaculoRepository;

@Service
public class AlertaServiceBean {

	@Autowired
	private RealizacionEspectaculoRepository realizacionEspectaculoRepository;
	
	@Autowired
	private SuscripcionEspectaculoRepository suscripcionEspectaculoRepository;

	@Autowired
	private EspectaculoRepository espectaculoRepository;
	
	// cron expression que se corra una vez al dia
	// esta cron expression es todos los días a la una am y un minuto 
//	@Scheduled(cron = "0 1 1 * * ?")
	@PostConstruct
	public void alertaPorCorre(){
		
		List<Espectaculo>  esp = espectaculoRepository.findAllActivos(new Date());
		for(Espectaculo e :esp){
			for(RealizacionEspectaculo re : e.getRealizacionEspectaculo()){
				Calendar cal1 = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
				cal1.add(Calendar.DATE, 4);
				cal2.add(Calendar.DATE, 5);
				if (re.getFecha().after(cal1.getTime())  && re.getFecha().before(cal2.getTime())){
					String asunto = "";
					String mensaje = "";
					List <SuscripcionEspectaculo> listaSuscripcionEspectaculo = suscripcionEspectaculoRepository.findByEspectaculo(e);
					listaSuscripcionEspectaculo.addAll(suscripcionEspectaculoRepository.findByRealizacionEspectaculo(re));
				}
				cal1 = Calendar.getInstance();
				cal2 = Calendar.getInstance();
				cal2.add(Calendar.DATE, 1);
				if (re.getFecha().after(cal1.getTime())  && re.getFecha().before(cal2.getTime())){
					String subject = "";
					String mensaje = "";
					
				}
				
			}
		}
	}
	
	public void alertasPush(){
		
	}
}
