package com.example.p1api.mapper;

import com.example.p1api.dtos.ProdutoRequestDto;
import com.example.p1api.dtos.ProdutoResponseDto;
import com.example.p1api.model.Produto;

public final class ProdutoMapper {
    private ProdutoMapper() {
    }

    public static Produto toEntity(ProdutoRequestDto dtoP, Long newId) {
        Produto produto = new Produto(newId, dtoP.getNome(), dtoP.getValorUnitario(), dtoP.getCategoriaId());
        return produto;
    }

    public static ProdutoResponseDto toDto(Produto produto) {
        ProdutoResponseDto dtoPResponse = new ProdutoResponseDto();
        dtoPResponse.setId(produto.getId());
        dtoPResponse.setNome(produto.getNome());
        dtoPResponse.setValorUnitario(produto.getValor());
        dtoPResponse.setCategoriaId(produto.getCategoriaId());
        return dtoPResponse;
    }
}
