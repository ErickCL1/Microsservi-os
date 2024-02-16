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
    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto requestDto){
    PropostaResponseDto response =  propostaService.criar(requestDto);
    return ResponseEntity.ok(response);
    }
}
