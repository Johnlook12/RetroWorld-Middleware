package com.pinguela.retroworld.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.MailException;
import com.pinguela.retroworld.service.impl.MailServiceImpl;

public class MailServiceTest {
	private static Logger logger = LogManager.getLogger(MailServiceTest.class);
	private MailService mailService = null;
	
	public MailServiceTest() {
		mailService = new MailServiceImpl();
	}
	
	public void testEnviarEmailSimple() {
		try {
			mailService.enviar("johnnrodriguez0112@gmail.com", "Hola", "Testing de MailService");
		} catch(MailException e) {
			logger.error("Email no válido", e);
		}
	}
	
	public static void main(String args[]) {
		MailServiceTest test = new MailServiceTest();
		test.testEnviarEmailSimple();
	}
}
