package com.grupo6.service.bean;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.grupo6.config.TenantContext;
import com.grupo6.persistence.model.Entrada;
import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.persistence.model.HistorialEntradas;
import com.grupo6.persistence.model.RealizacionEspectaculo;
import com.grupo6.persistence.model.Sala;
import com.grupo6.persistence.model.Sector;
import com.grupo6.persistence.model.SuscripcionEspectaculo;
import com.grupo6.persistence.model.Usuario;
import com.grupo6.persistence.repository.EntradaRepository;
import com.grupo6.persistence.repository.EspectaculoRepository;
import com.grupo6.persistence.repository.HistorialEntradasRepository;
import com.grupo6.persistence.repository.RealizacionEspectaculoRepository;
import com.grupo6.persistence.repository.SalaRepository;
import com.grupo6.persistence.repository.SectorRepository;
import com.grupo6.persistence.repository.SuscripcionEspectaculoRepository;
import com.grupo6.persistence.repository.UsuarioRepository;
import com.grupo6.rest.dto.RealizacionEspectaculoDTO;
import com.grupo6.rest.dto.RealizacionEspectaculoDisponibilidadDTO;
import com.grupo6.rest.dto.SectorDTO;
import com.grupo6.rest.dto.SectorDisponibilidadDTO;
import com.grupo6.rest.dto.SuscripcionEspectaculoDTO;
import com.grupo6.service.RealizacionEspectaculoService;
import com.grupo6.util.email.EnviarMails;

@Service
public class RealizacionEspectaculoServiceBean implements RealizacionEspectaculoService {

	@Autowired
	SalaRepository salaRepository;

	@Autowired
	EspectaculoRepository espectaculoRepository;

	@Autowired
	RealizacionEspectaculoRepository realizacionEspectaculoRepository;

	@Autowired
	SectorRepository sectorRepository;

	@Autowired
	EntradaRepository entradaRepository;

	@Autowired
	UsuarioRepository usuarioRepository ;
	
	@Autowired
	HistorialEntradasRepository historialEntradasRepository;
	
	@Autowired
	SuscripcionEspectaculoRepository suscripcionEspectaculoRepository;
	
	@Autowired
	EnviarMails enviarMails;
	
	@Value("${qrPath}")
	private String qrPath;

	
	@Override
	@Async
	public void altaRealizacionEspectaculo(RealizacionEspectaculoDTO realizacionEspectaculoDTO) {
		
		// obtengo sala y espectaculo por los id del dto y guardo la realización
		// en la base
		Optional<Sala> s = salaRepository.findOne(realizacionEspectaculoDTO.getSala().getId());
		Optional<Espectaculo> e = espectaculoRepository.findOne(realizacionEspectaculoDTO.getIdEspectaculo());
		RealizacionEspectaculo re = new RealizacionEspectaculo();
		re.setFecha(realizacionEspectaculoDTO.getFecha());
		re.setId(realizacionEspectaculoDTO.getId());
		re.setEspectaculo(e.get());
		re.setSala(s.get());
		realizacionEspectaculoRepository.save(re);
		
		// genero todas las entradas de acuerdo a la capacidad de los sectores
		// de las salas
		List<Sector> sectores = sectorRepository.findBySalaId(realizacionEspectaculoDTO.getSala().getId());
		String pathCarpetaQR = qrPath + "\\" + TenantContext.getCurrentTenant();

		pathCarpetaQR += "\\" + e.get().getId();
		pathCarpetaQR += "\\" + re.getId();
		File eventoPath = new File(pathCarpetaQR);
		if (!eventoPath.exists()) {
			eventoPath.mkdirs();
		}
		// se crea la carpeta con el id del espectaculo y de la realización
		// donde van a ir las entradas

		sectores.stream().forEach(sector -> { 
			int precio = 0;
			SectorDTO secDTO2 = null;
			for (SectorDTO secDTO : realizacionEspectaculoDTO.getSectores()){
				if (secDTO.getId()==sector.getId()){
					precio = secDTO.getPrecio();
					secDTO2 = secDTO;
				}
			}
			if (secDTO2 == null){
				System.out.println("Estan mal ingresados los sectores");
				return;
			}
			for (int i = 0; i < secDTO2.getCapacidad(); i++) {
				Entrada entrada = new Entrada();
				entrada.setId(0);
				entrada.setEspectaculo(e.get());
				entrada.setNumeroAsiento(i+1);
				entrada.setPrecio(precio);
				entrada.setRealizacionEspectaculo(re);
				entrada.setSector(sector);
				// hasta ahí gurde la entrada
				entradaRepository.save(entrada);

				// Se arma el nombre de la ruta a donde va el qr y se arman los
				// nombres de cada uno
				// de los códigos que se van a guardar
				String textodelQR = (String) TenantContext.getCurrentTenant();
				textodelQR += "." + e.get().getNombre();
				textodelQR += "." + re.getId();
				textodelQR += "." + entrada.getId();

				String fileType = "png";
				String filePath = qrPath + "\\" + TenantContext.getCurrentTenant();

				filePath += "\\" + e.get().getId();
				filePath += "\\" + re.getId();
				filePath += "\\" + textodelQR + "." + fileType;
				
//				textodelQR = org.apache.commons.codec.digest.DigestUtils.sha256Hex(textodelQR);
				int size = 250;
				File myFile = new File(filePath);
				try {

					Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
					hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

					hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
					hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

					QRCodeWriter qrCodeWriter = new QRCodeWriter();
					BitMatrix byteMatrix = qrCodeWriter.encode(textodelQR, BarcodeFormat.QR_CODE, size, size, hintMap);
					int CrunchifyWidth = byteMatrix.getWidth();
					BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);
					image.createGraphics();

					Graphics2D graphics = (Graphics2D) image.getGraphics();
					graphics.setColor(Color.WHITE);
					graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
					graphics.setColor(Color.BLACK);

					for (int k = 0; k < CrunchifyWidth; k++) {
						for (int j = 0; j < CrunchifyWidth; j++) {
							if (byteMatrix.get(k, j)) {
								graphics.fillRect(k, j, 1, 1);
							}
						}
					}
					ImageIO.write(image, fileType, myFile);
				} catch (WriterException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		});

	}


	@Override
	@Transactional
	public List<RealizacionEspectaculoDTO> verRealizacionesDeEspectaculo(String id) {

		List<RealizacionEspectaculo> list = this.realizacionEspectaculoRepository.findByEspectaculoAndFechaAfter(espectaculoRepository.findOne(Long.parseLong(id)).get(), new Date());
		List<RealizacionEspectaculoDTO> ret = new ArrayList<RealizacionEspectaculoDTO>();
		list.stream().forEach(x -> {
			RealizacionEspectaculoDTO respDTO = new RealizacionEspectaculoDTO(x);
			List<Sector>  sectores = sectorRepository.findBySalaId(x.getSala().getId());
			sectores.stream().forEach(sec ->{
				Optional<Entrada> ent  = entradaRepository.findByRealizacionEspectaculoAndSector(x,sec).findFirst();
				SectorDTO sectorDTO = new SectorDTO();
				sectorDTO.setPrecio(ent.get().getPrecio());
				sectorDTO.setCapacidad(sec.getCapacidad());
				sectorDTO.setId(sec.getId());
				sectorDTO.setNombre(sec.getNombre());
				respDTO.getSectores().add(sectorDTO);
			});
			
			ret.add(respDTO);
			
		});
		return ret;
	}


	@Override
	@Transactional
	public Optional<Entrada> comprarEntradaEspectaculo(Long idRealizacion, String idSector, String email) {
		
		Optional<RealizacionEspectaculo> re = realizacionEspectaculoRepository.findOne(idRealizacion);
		Optional<Espectaculo> e = espectaculoRepository.findOne(re.get().getEspectaculo().getId());
		
		Optional <Sector> sec = sectorRepository.findOne(Long.parseLong(idSector));
		Optional<Entrada> ent  = entradaRepository.findByRealizacionEspectaculoAndSectorAndUsuarioIsNull(re.get(),sec.get()).stream().findFirst();
		if (ent.isPresent()){
			Optional<Usuario> u = usuarioRepository.findByEmail(email);
			if (u.isPresent()){
				ent.get().setUsuario(u.get());
				ent.get().setFechaCompra(new Date());
				ent.get().setUsuario(u.get());
				entradaRepository.save(ent.get());
				historialEntradasRepository.save(new HistorialEntradas(ent.get()));
				// hasta ahi guardo la entrada en la base con el usuario 
				// que la comprpo y la fecha de compra 
				
				String textodelQR = (String) TenantContext.getCurrentTenant();
				textodelQR += "." + e.get().getNombre();
				textodelQR += "." + re.get().getId();
				textodelQR += "." + ent.get().getId();

				String fileType = "png";
				String filePath = qrPath + "\\" + TenantContext.getCurrentTenant();

				filePath += "\\" + e.get().getId();
				filePath += "\\" + re.get().getId();
				filePath += "\\" + textodelQR + "." + fileType;
				
				String asunto = "Sus entrada para el espectaculo " + e.get().getNombre();
				String mensaje = "Usted ha adquirido una entrada para el espectaculo: " + e.get().getNombre() + "\n"
						+ "que se realizará en la fecha: " + re.get().getFecha() + "\n"
						+ "en la sala:" + re.get().getSala().getNombre() + "\n"
						+ "Sector: " + ent.get().getSector().getNombre() + "\n"
						+ "Número de asiento: " + ent.get().getNumeroAsiento() + "\n"
						+ "ubicada en la dirección " + re.get().getSala().getDireccion() + "\n";
				enviarMails.EnviarCooreoConQR(email, asunto, mensaje, filePath);
			}else{
				return null;
			}
			return ent;
		}else{
			return null;
		}
			
	}


	@Override
	public void desSuscribirse(Long idTrealizacionEspectaculo, String email) {
		RealizacionEspectaculo re = realizacionEspectaculoRepository.findOne(idTrealizacionEspectaculo).get();
		Usuario u = usuarioRepository.findByEmail(email).get();
		Optional <SuscripcionEspectaculo> se = suscripcionEspectaculoRepository.findByUsuarioAndRealizacionEspectaculo(u, re);
		if (se.isPresent()){
			suscripcionEspectaculoRepository.delete(se.get());
		}
	}


	@Override
	@Transactional
	public void suscribirse(Long idRealizacionEspectaculo, String email) {
		RealizacionEspectaculo re = realizacionEspectaculoRepository.findOne(idRealizacionEspectaculo).get();
		Usuario u = usuarioRepository.findByEmail(email).get();
		Optional <SuscripcionEspectaculo> se = suscripcionEspectaculoRepository.findByUsuarioAndRealizacionEspectaculo(u, re);
		if (!se.isPresent()){
			SuscripcionEspectaculo s = new SuscripcionEspectaculo();
			s.setRealizacionEspectaculo(re);
			s.setUsuario(u);
			s.setFecha(new Date());
			suscripcionEspectaculoRepository.save(s);
		}
		
	}


	@Override
	public RealizacionEspectaculoDisponibilidadDTO consultaDisponibilidadDeLocalidades(Long idRealizacion) {
		Optional<RealizacionEspectaculo> re = realizacionEspectaculoRepository.findOne(idRealizacion);
		List<Sector> sectores = sectorRepository.findBySalaId(re.get().getSala().getId());
		RealizacionEspectaculoDisponibilidadDTO reD = new RealizacionEspectaculoDisponibilidadDTO(re.get()); 
		
		for (Sector sec: sectores){
			SectorDisponibilidadDTO  sdDTO = new SectorDisponibilidadDTO();
			List <Entrada> entradasPorSector = entradaRepository.findByRealizacionEspectaculoAndSectorAndUsuarioIsNull(re.get(), sec);
			Stream<Entrada> s  = entradaRepository.findByRealizacionEspectaculoAndSector(re.get(),sec);
			sdDTO.setCapacidad((int) s.count());
			sdDTO.setDisponibilidad(entradasPorSector.size());
			sdDTO.setId(sec.getId());
			sdDTO.setNombre(sec.getNombre());
			sdDTO.setPrecio(s.findFirst().get().getPrecio());
			reD.getSectores().add(sdDTO);
		}
		return reD;
		
	}


	@Override
	public List<SuscripcionEspectaculoDTO>  verSuscripcionUsuario(String email) {
		Optional <Usuario> user = usuarioRepository.findByEmail(email);
		List<SuscripcionEspectaculo> l= suscripcionEspectaculoRepository.findByUsuario(user.get());
		List<SuscripcionEspectaculoDTO> ret = new ArrayList<SuscripcionEspectaculoDTO>();
		for(SuscripcionEspectaculo se :l){
			SuscripcionEspectaculoDTO seDTO  = new SuscripcionEspectaculoDTO(se);
			ret.add(seDTO);
		}
		return ret;
	}

	@Override
	public Boolean verificarQR(String sha256hex, String email) {
		Boolean ok = false;
		String[] parts = sha256hex.split("\\.");
		String path = this.qrPath;
		for (int i=0; i<parts.length-1;i++){
			if (i==1){
				path  += "//" + espectaculoRepository.findByNombre(parts[i]).getId();
			}else {
				path  += "//" + parts[i];
			}
		}
		path += "//" + sha256hex + ".png";
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(path);
			Optional<Usuario> u = usuarioRepository.findByEmail(email);
			Optional<Entrada> ent =entradaRepository.findOne(Long.parseLong(parts[parts.length-1]));
			if (ent.isPresent()){
				List <Entrada> entradasUsuario = entradaRepository.findByUsuario(u.get());
				for (Entrada e : entradasUsuario){
					if (e.getId()==ent.get().getId()){
						ok = true;
						break;
					}
				}
			}
			
			return ok;
			
		} catch (IOException e) {
			
		} finally {
			try {
				if (fis != null)
					fis.close();
				
			} catch (IOException ex) {
			}
			
		}
		return false;
	}

	
}
