package com.example.p1api.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProdutoRequestDto {

    @NotBlank ( message = "O nome não pode estar vazio")
    @Size(min=2)
    private String nome;

    @NotNull ( message = "O valor não pode estar vazio")
    @DecimalMin("0.01")
    private double valor;

    @NotNull( message = "O id da categoria não pode estar vazio")
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

