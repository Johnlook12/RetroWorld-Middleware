package com.pinguela.retroworld.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.MailException;
import com.pinguela.retroworld.config.ConfigurationParametersManager;
import com.pinguela.retroworld.service.MailService;

public class MailServiceImpl implements MailService{
	private static Logger logger = LogManager.getLogger(MailServiceImpl.class);
	public static String SERVER_PNAME = "mail.server.url";
	public static String PORT_PNAME = "mail.server.port";
	public static String USER_PNAME = "mail.server.user";
	public static String PASSWORD_PNAME = "mail.server.password";
	
	public MailServiceImpl() {
		
	}

	
	public void enviar(String para,String asunto, String msg) throws MailException{
		try {
			Email email = new SimpleEmail();
			email.setHostName(ConfigurationParametersManager.getValue(SERVER_PNAME));
			email.setSmtpPort(Integer.valueOf(ConfigurationParametersManager.getValue(PORT_PNAME)));
			email.setAuthenticator(new DefaultAuthenticator(ConfigurationParametersManager.getValue(USER_PNAME), ConfigurationParametersManager.getValue(PASSWORD_PNAME)));
			//email.setSSLOnConnect(true);
			email.setStartTLSEnabled(true);
			email.setFrom(ConfigurationParametersManager.getValue(USER_PNAME));
			email.setSubject(asunto);
			email.setMsg("Estoy trabajando en el ciclo de Desarrollo de Aplicaciones Web, esto es una prueba.");
			email.addTo(para);
			email.send();
		} catch(EmailException e) {
			logger.info("Enviado email desde"+ConfigurationParametersManager.getValue(USER_PNAME)+" para "+para+": "+e.getMessage());
			logger.error(e);
			throw new MailException("Enviado email a"+para, e);
		}
	}
	
}
