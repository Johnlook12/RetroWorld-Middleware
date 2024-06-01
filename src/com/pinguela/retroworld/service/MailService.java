package com.pinguela.retroworld.service;

import com.pinguela.MailException;

public interface MailService {
	public void enviar(String para,String asunto, String msg)throws MailException;
}
