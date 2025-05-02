package com.jeanlima.inversaodecontrole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeanlima.inversaodecontrole.service.MensagemService;

@RestController
public class NotificationController {

    @Autowired
    @Qualifier("SMSService")
    private MensagemService mensagemService;

    @GetMapping("/notificar")
    public String notificar(@RequestParam String destino) {
        return mensagemService.enviar(destino, "Temos condições especiais para você!");
    }
    
}
