package com.pinguela.retroworld.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public interface ImageService {
	public List<BufferedImage> getImages(Long id, String subdirectory);
	public List<BufferedImage> getVideojuegoImages(Long id);
	public List<BufferedImage> getAnuncioImages(Long id);
	public void saveImages(Long id,List<File> imageFiles, String subdirectory);
	public void saveVideojuegoImages(Long id, List<File>imageFiles);
	public void saveAnuncioImages(Long id, List<File>imageFiles);
}
