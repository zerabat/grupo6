package com.grupo6.service.bean;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.grupo6.config.TenantContext;
import com.grupo6.persistence.model.Entrada;
import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.persistence.model.RealizacionEspectaculo;
import com.grupo6.persistence.model.Sala;
import com.grupo6.persistence.model.Sector;
import com.grupo6.persistence.repository.EntradaRepository;
import com.grupo6.persistence.repository.EspectaculoRepository;
import com.grupo6.persistence.repository.RealizacionEspectaculoRepository;
import com.grupo6.persistence.repository.SalaRepository;
import com.grupo6.persistence.repository.SectorRepository;
import com.grupo6.rest.dto.EntradaDTO;
import com.grupo6.rest.dto.RealizacionEspectaculoDTO;
import com.grupo6.service.RealizacionEspectaculoService;

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

	@Value("${qrPath}")
	private String qrPath;

	
//	String qrCodeData = "student3232_2015_12_15_10_29_46_123";
//    String filePath = "F:\\Opulent_ProjectsDirectory_2015-2016\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\AttendanceUsingQRCode\\QRCodes\\student3232_2015_12_15_10_29_46_123";
//    String charset = "UTF-8"; // or "ISO-8859-1"
//    Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
//    hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//
//    createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);
//    System.out.println("QR Code image created successfully!");
//
//    System.out.println("Data read from QR Code: "
//        + readQRCode(filePath, charset, hintMap));

    
    
	@Override
	public void altaRealizacionEspectaculo(RealizacionEspectaculoDTO realizacionEspectaculoDTO) {
		// obtengo sala y espectaculo por los id del dto y guardo la realización
		// en la base
		Optional<Sala> s = salaRepository.findOne(realizacionEspectaculoDTO.getIdSala());
		Optional<Espectaculo> e = espectaculoRepository.findOne(realizacionEspectaculoDTO.getIdEspectaculo());
		RealizacionEspectaculo re = new RealizacionEspectaculo();
		re.setFecha(realizacionEspectaculoDTO.getFecha());
		re.setId(realizacionEspectaculoDTO.getId());
		re.setEspectaculo(e.get());
		re.setSala(s.get());
		realizacionEspectaculoRepository.save(re);
		// genero todas las entradas de acuerdo a la capacidad de los sectores
		// de las salas
		List<Sector> sectores = sectorRepository.findBySalaId(realizacionEspectaculoDTO.getIdSala());
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
			for (int i = 0; i < sector.getCapacidad(); i++) {
				Entrada entrada = new Entrada();
				entrada.setId(0);
				entrada.setEspectaculo(e.get());
				entrada.setNumeroAsiento(i);
				// hay que pasar los precios por sector
				entrada.setPrecio(0);
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
	public List<RealizacionEspectaculoDTO> verRealizacionesDeEspectaculo(String id) {

		List<RealizacionEspectaculo> list = this.realizacionEspectaculoRepository.findByEspectaculo(espectaculoRepository.findOne(Long.parseLong(id)).get());
		List<RealizacionEspectaculoDTO> ret = new ArrayList<RealizacionEspectaculoDTO>();
		list.stream().forEach(x -> {
			ret.add(new RealizacionEspectaculoDTO(x));
		});
		return ret;
	}


	@Override
	public EntradaDTO comprarEntradaEspectaculo(Long idRealizacion, String idSector, String email) {
		
		return null;
	}

}
