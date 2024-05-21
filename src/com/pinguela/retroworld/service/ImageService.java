package com.pinguela.retroworld.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public interface ImageService {
	public List<BufferedImage> getVideojuegoImages(Long id);
	public void saveVideojuegoImages(Long id,List<File> imageFiles);
}
