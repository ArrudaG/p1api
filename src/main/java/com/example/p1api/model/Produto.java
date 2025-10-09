package com.example.p1api.model;

public class Produto {
    private Long id;
    private String nome;
    private double valor;
    private Long categoriaId;

    public Produto() {}

    public Produto(Long id, String nome, double valor, Long categoriaId) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.categoriaId = categoriaId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }
}

