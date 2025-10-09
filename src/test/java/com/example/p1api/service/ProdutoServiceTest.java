package com.example.p1api.service;

import com.example.p1api.model.Categoria;
import com.example.p1api.model.Produto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoServiceTest {

    CategoriaService categoriaService = new CategoriaService();
    ProdutoService produtoService = new ProdutoService();

    @Test
    public void deveRetornarListaDeIds() {
        Categoria laticinios = new Categoria(0L, "Laticinios");
        Categoria massas = new Categoria(0L, "Massas");
        categoriaService.criar(massas);
        categoriaService.criar(laticinios);
        Produto leite = new Produto(0L, "Leite", 9.99, 2L);
        Produto macarrao = new Produto(0L, "Macarrao", 5.99, 1L);
        produtoService.criar(leite);
        produtoService.criar(macarrao);
        assertEquals("Leite", categoriaService.listarProdutos(2L));
    }

}