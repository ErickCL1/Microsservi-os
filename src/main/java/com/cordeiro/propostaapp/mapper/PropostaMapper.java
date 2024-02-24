package com.cordeiro.propostaapp.mapper;

import com.cordeiro.propostaapp.dto.PropostaRequestDto;
import com.cordeiro.propostaapp.dto.PropostaResponseDto;
import com.cordeiro.propostaapp.entity.Proposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.text.NumberFormat;
import java.util.List;

@Mapper
public interface PropostaMapper {

    PropostaMapper INSTANCE = Mappers.getMapper(PropostaMapper.class);

    //Converte DTO para a class Proposta, usamos como parâmetro o próprio DTO da Proposta.

    /*INSTANCIAMOS A CLASS USUARIO E O CAMPO POIS O PADRÃO DESTA INTERFACE É A CLASS PROPOSTA. */

    @Mapping(target = "usuario.nome", source = "nome")
    @Mapping(target = "usuario.sobrenome", source = "sobrenome")
    @Mapping(target = "usuario.cpf", source = "cpf")
    @Mapping(target = "usuario.telefone", source = "telefone")
    @Mapping(target = "usuario.renda", source = "renda")
    @Mapping(target = "id", ignore = true)         // CAMPO APENAS PRESENTE NO RESPONSE, POR ISSO O IGNORE
    @Mapping(target = "aprovada", ignore = true)   // CAMPO APENAS PRESENTE NO RESPONSE, POR ISSO O IGNORE
    @Mapping(target = "integrada", constant = "true")  // CAMPO APENAS PRESENTE NO RESPONSE, POR ISSO O IGNORE
    @Mapping(target = "observacao", ignore = true) // CAMPO APENAS PRESENTE NO RESPONSE, POR ISSO O IGNORE
    Proposta convertDtoToProposta(PropostaRequestDto propostaRequestDto);

    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "sobrenome", source = "usuario.sobrenome")
    @Mapping(target = "telefone", source = "usuario.telefone")
    @Mapping(target = "cpf", source = "usuario.cpf")
    @Mapping(target = "renda", source = "usuario.renda")
    @Mapping(target = "valorSolicitadoFmt", expression = "java(setValorSolicitadoFmt(proposta))")
    PropostaResponseDto convertEntityToDto(Proposta proposta);

    List<PropostaResponseDto> convertListEntityToListDto(Iterable<Proposta> propostas);

   default String setValorSolicitadoFmt(Proposta proposta){
       return NumberFormat.getCurrencyInstance().format(proposta.getValorSolicitado());
   }


}
