package com.jeanlima.inversaodecontrole.service;

public class EmailService {

    public String enviar(String destino, String mensagem){
        return "Notificação enviada via email para " + destino + ": " + mensagem;
    }
    
}
