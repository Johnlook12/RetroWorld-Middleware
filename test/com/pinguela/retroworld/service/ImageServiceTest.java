package com.pinguela.retroworld.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.service.impl.ImageServiceImpl;

public class ImageServiceTest {
	
	private static Logger logger = LogManager.getLogger(ImageServiceTest.class);
	private ImageService imageService;
	
	public ImageServiceTest() {
		imageService = new ImageServiceImpl();
	}
	
	public void testSaveImage() {
		File fileImage = new File("C:\\Users\\Dimit\\Documents\\DAW\\Lenguajes de marcas\\html\\Proyecto personal\\img\\floor.jpg");
		File fileImage2 = new File("C:\\Users\\Dimit\\Documents\\DAW\\Lenguajes de marcas\\html\\Proyecto personal\\img\\heart.png");
		
		File fileImage3 = new File("C:\\Users\\Dimit\\Documents\\DAW\\Lenguajes de marcas\\html\\Proyecto personal\\img\\shopping-cart.png");
		List<File> imageFiles = new ArrayList<File>();
		imageFiles.add(fileImage2);
		imageFiles.add(fileImage3);
		imageFiles.add(fileImage);
		imageService.saveVideojuegoImages(47l, imageFiles);
	}
	
	public static void main(String args[]) {
		ImageServiceTest test = new ImageServiceTest();
		test.testSaveImage();
	}
}
