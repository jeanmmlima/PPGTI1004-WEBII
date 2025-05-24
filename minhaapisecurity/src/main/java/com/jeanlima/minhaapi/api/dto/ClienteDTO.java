package com.jeanlima.minhaapi.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
    private String cpf;
    
}
