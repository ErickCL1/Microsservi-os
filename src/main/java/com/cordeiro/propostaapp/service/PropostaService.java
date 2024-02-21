package com.cordeiro.propostaapp.service;

import com.cordeiro.propostaapp.dto.PropostaRequestDto;
import com.cordeiro.propostaapp.dto.PropostaResponseDto;
import com.cordeiro.propostaapp.entity.Proposta;
import com.cordeiro.propostaapp.mapper.PropostaMapper;
import com.cordeiro.propostaapp.repository.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PropostaService {

    private  PropostaRepository propostaRepository;

    private NotificacaoService notificacaoService;


    private String exchange;

    public PropostaService(PropostaRepository propostaRepository,
                           NotificacaoService notificacaoService,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.propostaRepository = propostaRepository;
        this.notificacaoService = notificacaoService;
        this.exchange = exchange;
    }
    // public PropostaResponseDto: o que retorna + NOME DO MÉTODO(criar) + O QUE VAI SER PASSADO NO BODY
    // RETORNA O RESPONSE NO COMEÇO E RECEBE O REQUEST NO PARÂMETRO.

    public PropostaResponseDto criar(PropostaRequestDto requestDto){

        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        propostaRepository.save(proposta);

       PropostaResponseDto response = PropostaMapper.INSTANCE.convertEntityToDto(proposta);
        notificacaoService.notificar(response,exchange);

        return response ;
    }


    public List<PropostaResponseDto> obterProposta() {
        return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());
    }
}
