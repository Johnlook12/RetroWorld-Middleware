package com.pinguela.retroworld.service;


public interface MailService {
	public void enviar(String para,String asunto, String msg)throws MailException;
}
