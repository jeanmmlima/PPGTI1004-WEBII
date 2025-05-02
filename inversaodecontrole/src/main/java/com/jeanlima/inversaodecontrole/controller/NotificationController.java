package com.jeanlima.inversaodecontrole.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeanlima.inversaodecontrole.service.EmailService;

@RestController
public class NotificationController {

    private EmailService emailService = new EmailService();

    @GetMapping("/notificar")
    public String notificar(@RequestParam String destino) {
        return emailService.enviar(destino, "Temos condições especiais para você!");
    }
    
}
