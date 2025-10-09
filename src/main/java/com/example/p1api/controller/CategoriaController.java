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

import java.util.*;

@RestController
@RequestMapping(value = "/categoria", produces = "application/json")
public class CategoriaController {

    @Autowired
    private final CategoriaService categoriaService;

    private List<Categoria> categorias;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<Object> listarOuFiltrarCategorias(@RequestParam(value = "nome", required = false) String nome) {
        try {
            Object categoriasRetorno;
            List<Categoria> categoriaList;
            if (nome != null && !nome.trim().isEmpty()) {
                categoriasRetorno = categoriaService.buscarPorNome(nome);
            }
            else {
                categoriasRetorno = categoriaService.listarTodos();
            }
            return ResponseEntity.ok(categoriasRetorno);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "{id}/produtos")
    public ResponseEntity<List<Produto>> listarProdutosPorIdCategoria(@PathVariable("id") Long id) {
        List<Produto> produtos = categoriaService.listarProdutos(id);
        return ResponseEntity.ok(produtos);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CategoriaResponseDto> criarCategoria(@RequestBody @Valid CategoriaRequestDto categoriaDto){

        Categoria categoria = CategoriaMapper.toEntity(categoriaDto, null);
        Categoria novaCategoria = categoriaService.criar(categoria);
        CategoriaResponseDto responseDto = CategoriaMapper.toDto(novaCategoria);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
