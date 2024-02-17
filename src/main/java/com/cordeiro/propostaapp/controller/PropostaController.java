package com.cordeiro.propostaapp.controller;

import com.cordeiro.propostaapp.dto.PropostaRequestDto;
import com.cordeiro.propostaapp.dto.PropostaResponseDto;
import com.cordeiro.propostaapp.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @Autowired
    private PropostaService propostaService;

    //recebe parãmetros pelo PropostaRequestDto, e devolve pelo PropostaResponseDto

    /*
        O que fica dentro do maior que e menor que <>, é o que usaremos para retornar(PropostaResponseDto).

        Mesma situação se da para o que fica antes do "=" na linha abaixo, irá ser salvo o que estará no body
        da requisição, atribuída a requestDto (lógica de negócio do save no banco de dados se encontra no service)
        como foi passado abaixo.

     */
    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto requestDto){
    PropostaResponseDto response =  propostaService.criar(requestDto);
    return ResponseEntity.ok(response);
    }
}
