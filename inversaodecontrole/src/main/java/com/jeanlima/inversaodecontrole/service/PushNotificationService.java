package com.jeanlima.inversaodecontrole.service;

import org.springframework.stereotype.Service;

@Service
public class PushNotificationService implements MensagemService{

    @Override
    public String enviar(String destino, String mensagem) {
        return "Notificação enviada via Push Notification para " + destino + ": " + mensagem;
    }
    
}
