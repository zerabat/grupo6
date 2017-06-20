package com.grupo6.service.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;

import com.grupo6.config.TenantContext;
import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.persistence.model.RealizacionEspectaculo;
import com.grupo6.persistence.model.SuscripcionEspectaculo;
import com.grupo6.persistence.repository.EspectaculoRepository;
import com.grupo6.persistence.repository.RealizacionEspectaculoRepository;
import com.grupo6.persistence.repository.SuscripcionEspectaculoRepository;
import com.grupo6.persistence.repository.UsuarioRepository;
import com.grupo6.util.email.EnviarMails;

@Service
public class AlertaServiceBean {

	@Autowired
	private RealizacionEspectaculoRepository realizacionEspectaculoRepository;

	@Autowired
	private SuscripcionEspectaculoRepository suscripcionEspectaculoRepository;

	@Autowired
	private EspectaculoRepository espectaculoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EnviarMails enviarMails;

	@Value("${tenantPath}")
	private String tenantPath;

	// cron expression que se corra una vez al dia
	// esta cron expression es todos los días a la una am y un minuto
	// @Scheduled(cron = "0 1 1 * * ?")
//	@PostConstruct
	public void alertaPorCorre() {

		List<String> tenants = obtenerTodosLosTenants();
		for (String tenant : tenants) {
			TenantContext.setCurrentTenant(tenant);
			List<Espectaculo> esp = espectaculoRepository.findAllActivos(new Date());
			List<SuscripcionEspectaculo> listaSuscripcionEspectaculo = new ArrayList<SuscripcionEspectaculo>();
			Set<Long> idEspetactulosSet = new HashSet<Long>();
			for (Espectaculo e : esp) {
				System.out.println(e.getNombre());
				for (RealizacionEspectaculo re : e.getRealizacionEspectaculo()) {
					System.out.println("               " + re.getId());
					Calendar cal1 = Calendar.getInstance();
					Calendar cal2 = Calendar.getInstance();
					cal1.add(Calendar.DATE, 4);
					cal2.add(Calendar.DATE, 5);
					if (re.getFecha().after(cal1.getTime()) && re.getFecha().before(cal2.getTime())) {
						listaSuscripcionEspectaculo = suscripcionEspectaculoRepository.findByEspectaculo(e);
						listaSuscripcionEspectaculo
								.addAll(suscripcionEspectaculoRepository.findByRealizacionEspectaculo(re));

					}
					cal1 = Calendar.getInstance();
					cal2 = Calendar.getInstance();
					cal2.add(Calendar.DATE, 1);
					if (re.getFecha().after(cal1.getTime()) && re.getFecha().before(cal2.getTime())) {
						listaSuscripcionEspectaculo = suscripcionEspectaculoRepository.findByEspectaculo(e);
						listaSuscripcionEspectaculo
								.addAll(suscripcionEspectaculoRepository.findByRealizacionEspectaculo(re));

					}
					// limpio los repetidos
					if (listaSuscripcionEspectaculo != null) {
						Set<SuscripcionEspectaculo> hs = new HashSet<>();
						hs.addAll(listaSuscripcionEspectaculo);
						listaSuscripcionEspectaculo.clear();
						listaSuscripcionEspectaculo.addAll(hs);
						List<SuscripcionEspectaculo> listaSuscripcionEspectaculoAux = new ArrayList<SuscripcionEspectaculo>();

						for (SuscripcionEspectaculo sesp : listaSuscripcionEspectaculo) {
							System.out.println(sesp);
							String asunto = tenant +" Espectaculos que le podrían interesar";
							String mensaje = "El espectaculo: " + e.getNombre() + "\n" + "se realizará en la fecha: "
									+ re.getFecha() + "\n" + "en la sala:" + re.getSala().getNombre() + "\n"
									+ "ubicada en la dirección " + re.getSala().getDireccion() + "\n";
							enviarMails.EnviarCooreo(
									usuarioRepository.findOne(sesp.getUsuario().getId()).get().getEmail(), asunto,
									mensaje);

						}

					}

				}

			}
		}
	}

	public void alertasPush() {

	}

	private List<String> obtenerTodosLosTenants() {
		List<String> tenants = new ArrayList<String>();
		File[] files = Paths.get(tenantPath).toFile().listFiles();

		for (File propertyFile : files) {
			Properties tenantProperties = new Properties();
			DataSourceBuilder dataSourceBuilder = new DataSourceBuilder(this.getClass().getClassLoader());

			try {
				tenantProperties.load(new FileInputStream(propertyFile));
				String tenantId = tenantProperties.getProperty("name");
				tenants.add(tenantId);
			} catch (IOException e) {
				e.printStackTrace();

				return null;
			}

		}
		return tenants;
	}

}
