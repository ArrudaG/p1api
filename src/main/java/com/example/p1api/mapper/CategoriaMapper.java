package com.example.p1api.mapper;


import com.example.p1api.dtos.CategoriaRequestDto;
import com.example.p1api.dtos.CategoriaResponseDto;
import com.example.p1api.model.Categoria;

public final class CategoriaMapper {
    private CategoriaMapper() {}

    public static Categoria toEntity (CategoriaRequestDto dtoC){
        Categoria categoria = new Categoria(null, dtoC.getNome());
        return categoria;
    }

    public static CategoriaResponseDto toDto (Categoria categoria){
        CategoriaResponseDto dtoCResponse = new CategoriaResponseDto();
        dtoCResponse.setId(categoria.getId());
        dtoCResponse.setNome(categoria.getNome());
        return dtoCResponse;
    }
}
