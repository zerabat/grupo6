package com.grupo6.service.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.grupo6.config.TenantContext;
import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.persistence.model.RealizacionEspectaculo;
import com.grupo6.persistence.model.SuscripcionEspectaculo;
import com.grupo6.persistence.repository.EntradaRepository;
import com.grupo6.persistence.repository.EspectaculoRepository;
import com.grupo6.persistence.repository.SuscripcionEspectaculoRepository;
import com.grupo6.persistence.repository.UsuarioRepository;
import com.grupo6.service.EspectaculoService;
import com.grupo6.service.RealizacionEspectaculoService;
import com.grupo6.util.email.EnviarMails;
import com.grupo6.util.page.PageUtils;

@Service
public class AlertaServiceBean {

	@Autowired
	private SuscripcionEspectaculoRepository suscripcionEspectaculoRepository;

	@Autowired
	private EspectaculoRepository espectaculoRepository;

	@Autowired
	private EntradaRepository entradaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RealizacionEspectaculoService realizacionEspectaculoService;
	
	@Autowired
	private EspectaculoService espectaculoService;

	@Autowired
	private EnviarMails enviarMails;

	@Value("${tenantPath}")
	private String tenantPath;
	
	@Value("${qrPath}")
	private String qrPath;

	// cron expression que se corra una vez al dia
	// esta cron expression es todos los días a la una am y un minuto
	 @Scheduled(cron = "0 1 1 * * ?")
	public void alertaPorCorre() {

		List<String> tenants = obtenerTodosLosTenants();
		for (String tenant : tenants) {
			TenantContext.setCurrentTenant(tenant);
			Page<Espectaculo> espPagAux = espectaculoService.findAllActivos(PageUtils.getPageRequest(0, 9999, null, null));
			List<Espectaculo> esp = espPagAux.getContent(); 
			List<SuscripcionEspectaculo> listaSuscripcionEspectaculo = new ArrayList<SuscripcionEspectaculo>();
			for (Espectaculo e : esp) {
				for (RealizacionEspectaculo re : e.getRealizacionEspectaculo()) {
					final Calendar cal1 = Calendar.getInstance();
					final Calendar cal2 = Calendar.getInstance();
					cal1.add(Calendar.DATE, 4);
					cal2.add(Calendar.DATE, 5);
					if (re.getFecha().after(cal1.getTime()) && re.getFecha().before(cal2.getTime())) {
						listaSuscripcionEspectaculo = suscripcionEspectaculoRepository.findByEspectaculo(e);
						listaSuscripcionEspectaculo.stream().forEach(x -> {
							Espectaculo espAux = espectaculoRepository.findOneActive(x.getEspectaculo().getId(),
									new Date());
							espAux.getRealizacionEspectaculo().stream().forEach(y -> {
								if (y.getFecha().after(cal1.getTime()) && y.getFecha().before(cal2.getTime())) {
									realizacionEspectaculoService.suscribirse(y.getId(), x.getUsuario().getEmail());
								}
							});

						});
						listaSuscripcionEspectaculo.clear();

					}
				}

			}
			for (Espectaculo e : esp) {
				for (RealizacionEspectaculo re : e.getRealizacionEspectaculo()) {
					Calendar cal1 = Calendar.getInstance();
					Calendar cal2 = Calendar.getInstance();
					cal1.add(Calendar.DATE, 4);
					cal2.add(Calendar.DATE, 5);
					if (re.getFecha().after(cal1.getTime()) && re.getFecha().before(cal2.getTime())) {
						listaSuscripcionEspectaculo
								.addAll(suscripcionEspectaculoRepository.findByRealizacionEspectaculo(re));
						
					}
					Calendar calb1 = Calendar.getInstance();
					Calendar calb2 = Calendar.getInstance();
					calb2.add(Calendar.DATE, 1);
					if (re.getFecha().after(calb1.getTime()) && re.getFecha().before(calb2.getTime())) {
						listaSuscripcionEspectaculo
								.addAll(suscripcionEspectaculoRepository.findByRealizacionEspectaculo(re));
						entradaRepository.findByRealizacionEspectaculoAndUsuarioIsNotNull(re).stream().forEach(entrada ->{
							String asunto = " usted adquirio entradas para el espectaculo " + e.getNombre() + " que se realizará mañana" ;
							String mensaje = "Usted ha adquirido una entrada para el espectaculo: " + e.getNombre() + "\n"
									+ "que se realizará en la fecha: " + re.getFecha() + "\n"
									+ "en la sala:" + re.getSala().getNombre() + "\n"
									+ "Sector: " + entrada.getSector().getNombre() + "\n"
									+ "Número de asiento: " + entrada.getNumeroAsiento() + "\n"
									+ "ubicada en la dirección " + re.getSala().getDireccion() + "\n";
							
							String textodelQR = (String) TenantContext.getCurrentTenant();
							textodelQR += "." + e.getNombre();
							textodelQR += "." + re.getId();
							textodelQR += "." + entrada.getId();

							String fileType = "png";
							String filePath = qrPath + "\\" + TenantContext.getCurrentTenant();

							filePath += "\\" + e.getId();
							filePath += "\\" + re.getId();
							filePath += "\\" + textodelQR + "." + fileType;
							enviarMails.EnviarCooreoConQR(entrada.getUsuario().getEmail(), asunto, mensaje, filePath);
						});
						
					}
					if (listaSuscripcionEspectaculo != null) {
						for (SuscripcionEspectaculo sesp : listaSuscripcionEspectaculo) {
							String asunto = tenant + " Espectaculos que le podrían interesar";
							String mensaje = "El espectaculo: " + e.getNombre() + "\n" + "se realizará en la fecha: "
									+ re.getFecha() + "\n" + "en la sala:" + re.getSala().getNombre() + "\n"
									+ "ubicada en la dirección " + re.getSala().getDireccion() + "\n";
							enviarMails.EnviarCooreo(
									usuarioRepository.findOne(sesp.getUsuario().getId()).get().getEmail(), asunto,
									mensaje);
						}
						listaSuscripcionEspectaculo.clear();
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
