package com.example.p1api.dtos;


import jakarta.validation.constraints.NotBlank;

public class CategoriaRequestDto {


    @NotBlank ( message = "O nome não pode estar vazio")
    private String nome;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
