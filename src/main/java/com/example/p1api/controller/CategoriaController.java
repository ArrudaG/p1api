package com.example.p1api.controller;

import com.example.p1api.dtos.CategoriaRequestDto;
import com.example.p1api.dtos.CategoriaResponseDto;
import com.example.p1api.mapper.CategoriaMapper;
import com.example.p1api.model.Categoria;
import com.example.p1api.model.Produto;
import com.example.p1api.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categoria", produces = "application/json")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    private List<Categoria> categorias;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
        categorias = new ArrayList<>();
        categorias = categoriaService.listarTodos();
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = categoriaService.listarTodos();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping(path = "{id}/produtos")
    public ResponseEntity<List<Produto>> listarProdutos(@PathVariable("id") Long id) {
        List<Produto> produtos = categoriaService.listarProdutos(id);
        return ResponseEntity.ok(produtos);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CategoriaResponseDto> criarCategoria(@RequestBody @Valid CategoriaRequestDto categoriaDto){

        Long newId = (long) (categorias.size() + 1);
        Categoria categoria = CategoriaMapper.toEntity(categoriaDto, newId);
        Categoria novaCategoria = categoriaService.criar(categoria);
        CategoriaResponseDto responseDto = CategoriaMapper.toDto(novaCategoria);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
