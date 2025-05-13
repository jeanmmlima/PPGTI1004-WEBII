package com.jeanlima.inversaodecontrole.service;

import org.springframework.stereotype.Service;

@Service
public class SMSService implements MensagemService{

    @Override
    public String enviar(String destino, String mensagem) {
        return "Notificação enviada via SMS para " + destino + ": " + mensagem;
    }
    
}
