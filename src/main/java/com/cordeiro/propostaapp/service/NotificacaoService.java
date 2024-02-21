package com.cordeiro.propostaapp.service;

import com.cordeiro.propostaapp.dto.PropostaResponseDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificacaoService {

    private RabbitTemplate rabbitTemplate;

    public void notificar(PropostaResponseDto proposta, String exchange){
        rabbitTemplate.convertAndSend(exchange, "",proposta);
    }


}
