package com.example.p1api.service;

import com.example.p1api.model.Categoria;
import com.example.p1api.model.Produto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoServicesTest {

    CategoriaService categoriaService = new CategoriaService();
    ProdutoServices produtoServices = new ProdutoServices();

    @Test
    public void deveRetornarListaDeIds() {
        Categoria laticinios = new Categoria(0L, "Laticinios");
        Categoria massas = new Categoria(0L, "Massas");
        categoriaService.criar(massas);
        categoriaService.criar(laticinios);
        Produto leite = new Produto(0L, "Leite", 9.99, 2L);
        Produto macarrao = new Produto(0L, "Macarrao", 5.99, 1L);
        produtoServices.criar(leite);
        produtoServices.criar(macarrao);
        assertEquals("Leite", categoriaService.listarProdutos(2L));
    }

}