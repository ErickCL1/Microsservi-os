package com.cordeiro.propostaapp.service;

import com.cordeiro.propostaapp.dto.PropostaRequestDto;
import com.cordeiro.propostaapp.dto.PropostaResponseDto;
import com.cordeiro.propostaapp.entity.Proposta;
import com.cordeiro.propostaapp.mapper.PropostaMapper;
import com.cordeiro.propostaapp.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PropostaService {

    private  PropostaRepository propostaRepository;

    private  NotificacaoRabbitService notificacaoRabbitService;


    private  String exchange;

    public PropostaService(PropostaRepository propostaRepository,
                           NotificacaoRabbitService notificacaoRabbitService,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.exchange = exchange;
    }
    // public PropostaResponseDto: o que retorna + NOME DO MÉTODO(criar) + O QUE VAI SER PASSADO NO BODY
    // RETORNA O RESPONSE NO COMEÇO E RECEBE O REQUEST NO PARÂMETRO.

    public PropostaResponseDto criar(PropostaRequestDto requestDto){

        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        propostaRepository.save(proposta);

        notificarRabbitMQ(proposta);

        return  PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }

    public void notificarRabbitMQ(Proposta proposta){
        try{
        notificacaoRabbitService.notificar(proposta,exchange);
        } catch (RuntimeException ex){ //deve ser usada quando a exceção pode ser prevenida, por ex, RabbitMQ fora do ar.
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }


    public List<PropostaResponseDto> obterProposta() {
        return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());
    }
}
