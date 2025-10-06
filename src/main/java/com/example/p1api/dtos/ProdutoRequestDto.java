package com.example.p1api.dtos;

import jakarta.validation.constraints.NotBlank;

public class ProdutoRequestDto {

    @NotBlank ( message = "O nome não pode estar vazio")
    private String nome;

    @NotBlank ( message = "O valor não pode estar vazio")
    private double valor;

    @NotBlank ( message = "O id da categoria não pode estar vazio")
    private Long categoriaId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorUnitario() {
        return valor;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valor = valorUnitario;
    }


    public void setCategoriaId() {
        this.categoriaId = categoriaId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }
}

