package com.pinguela.retroworld.junit;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.pinguela.retroworld.service.MailException;
import com.pinguela.retroworld.service.MailService;
import com.pinguela.retroworld.service.impl.MailServiceImpl;

public class MailServiceTest {
	private MailService mailService=null;
	
	public MailServiceTest() {
		mailService=new MailServiceImpl();
	}
	
	@Test
	public void testEnviar() {
		try {
			String email= "johnnrodriguez0112@gmail.com";
			mailService.enviar(email, "Hola", "Testing de MailService");			
		} catch(MailException me) {
			fail("no se pudo enviar el email");
		}
	}

}
