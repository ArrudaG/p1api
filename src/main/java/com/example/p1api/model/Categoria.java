package com.example.p1api.model;


import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private Long id;
    private String nome;
    private List<Produto> produtoList;

    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
        this.produtoList = new ArrayList<>();
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

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void addProduto(Produto produto) {
        this.produtoList.add(produto);
    }
}






