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

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<?> listarOuFiltrarCategorias(@RequestParam(value = "nome", required = false) String nome) {
        List<Categoria> retorno = categoriaService.listarOuFiltrar(nome);
        if (retorno.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Categoria não encontrada: " + nome);
        }
        return ResponseEntity.ok(retorno);
    }

    @GetMapping(path = "{id}/produtos")
    public ResponseEntity<?> listarProdutosPorIdCategoria(@PathVariable("id") Long id) {
        List<Produto> retorno = categoriaService.listarProdutos(id);
        if (retorno.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Não foi encontrado nenhum produto na categoria: " + categoriaService.buscarPorId(id));
        }
        return ResponseEntity.ok(retorno);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CategoriaResponseDto> criarCategoria(@RequestBody @Valid CategoriaRequestDto categoriaDto){
        Categoria categoria = CategoriaMapper.toEntity(categoriaDto);
        Categoria nova = categoriaService.criar(categoria);
        CategoriaResponseDto response = CategoriaMapper.toDto(nova);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
