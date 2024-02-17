package com.cordeiro.propostaapp.service;

import com.cordeiro.propostaapp.dto.PropostaRequestDto;
import com.cordeiro.propostaapp.dto.PropostaResponseDto;
import com.cordeiro.propostaapp.entity.Proposta;
import com.cordeiro.propostaapp.mapper.PropostaMapper;
import com.cordeiro.propostaapp.repository.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PropostaService {

    private PropostaRepository propostaRepository;

    // public PropostaResponseDto: o que retorna + NOME DO MÉTODO(criar) + O QUE VAI SER PASSADO NO BODY
    // RETORNA O RESPONSE NO COMEÇO E RECEBE O REQUEST NO PARÂMETRO.

    public PropostaResponseDto criar(PropostaRequestDto requestDto){
        // CONVERSÃO DE DTO PARA PROPOSTA
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        // MANDO SALVAR
        propostaRepository.save(proposta);
        // CONVERSÃO DA PROPOSTA PARA DTO, PARA RETORNAR A RESPOSTA
        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);

    }
}
