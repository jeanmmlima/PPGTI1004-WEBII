package com.jeanlima.inversaodecontrole.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService implements MensagemService{

    @Override
    public String enviar(String destino, String mensagem) {
        return "Notificação enviada via email para " + destino + ": " + mensagem;
    }

}
