package com.pinguela.retroworld.service.impl;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.config.ConfigurationParametersManager;
import com.pinguela.retroworld.service.ImageService;

public class ImageServiceImpl implements ImageService{
	private static Logger logger = LogManager.getLogger(ImageServiceImpl.class);
	private static final String IMAGE_DIRECTORY="images.directory";
	private static final String VIDEOJUEGO_SUBDIRECTORY="videojuegos";
	private static final String ANUNCIO_SUBDIRECTORY="anuncios";
	
	
	public List<BufferedImage> getImages(Long id, String subdirectory) {
		List<BufferedImage>imagenes = new ArrayList<BufferedImage>();

		File imagesDirectory = getDirectory(id, subdirectory);
		File[] imageFiles = imagesDirectory.listFiles();

		if(imageFiles!=null) {
			for(File imageFile:imageFiles) {
				if(imageFile.isFile()) {
					BufferedImage imagen = null;
					try {
						imagen = ImageIO.read(imageFile);
						imagenes.add(imagen);
					} catch(IOException ex) {
						logger.error(ex.getMessage(), ex);
					}
				}
			}
		}
		return imagenes;
	}

	
	public List<BufferedImage> getVideojuegoImages(Long id) {
		return getImages(id, VIDEOJUEGO_SUBDIRECTORY);
	}
	
	
	public List<BufferedImage> getAnuncioImages(Long id) {
		return getImages(id, ANUNCIO_SUBDIRECTORY);
	}
	
	private File getDirectory(Long id, String subdirectory) {
		String path = new StringBuilder(ConfigurationParametersManager.getValue(IMAGE_DIRECTORY))
				.append(File.separator)
				.append(subdirectory)
				.append(File.separatorChar)
				.append(id)
				.toString();
		return new File(path);
	}

	
	public void saveImages(Long id, List<File> imageFiles, String subdirectory) {
		File imagesDirectory = getDirectory(id, subdirectory);
		if(!imagesDirectory.exists()) {
			boolean dirCreated = imagesDirectory.mkdir();
			if(!dirCreated) {
				logger.error("Fallo al crear el directorio: "+imagesDirectory.getAbsolutePath());
			}
		}
		for(File imageFile:imageFiles) {
			String newFileName = getNextFileName(imagesDirectory);
			File destinationFile = new File(imagesDirectory, newFileName);
			try {
				logger.info("leyendo la imagen: "+imageFile.getAbsolutePath());
				BufferedImage image = ImageIO.read(imageFile);
				if(ImageIO.write(image, getFileExtension(imageFile), destinationFile)) {
					logger.info("imagen guardada en: "+destinationFile.getAbsolutePath());
				} else {
					logger.info("Error al guardar la imagen, la escritura en el archivo ha fallado");
				}
			
			}catch(IOException ex) {
				logger.error("Error al guardar la imagen: "+ex.getMessage(), ex);
			}
		}
	}
	
	public void saveVideojuegoImages(Long id, List<File>imageFiles) {
		saveImages(id, imageFiles, VIDEOJUEGO_SUBDIRECTORY);
	}
	
	public void saveAnuncioImages(Long id, List<File>imageFiles) {
		saveImages(id, imageFiles, ANUNCIO_SUBDIRECTORY);
	}
	
	private String getFileExtension(File file) {
	    String fileName = file.getName();
	    int dotIndex = fileName.lastIndexOf('.');
	    if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
	        return fileName.substring(dotIndex + 1).toUpperCase();
	    } else {
	        return "";
	    }
	}

	private String getNextFileName(File directory) {
		File[]files = directory.listFiles();
		int maxNumber = 0;
		if(files!=null) {
			for(File file:files) {
				String fileName = file.getName();
				if(fileName.startsWith("g") && fileName.lastIndexOf('.')>1) {
					String numberPart = fileName.substring(1, fileName.lastIndexOf('.'));
					int fileNumber = Integer.valueOf(numberPart);
					if(fileNumber>maxNumber) {
						maxNumber=fileNumber;
					}
				}
			}
		}
		return "g"+(maxNumber+1)+".jpg";
	}


}
