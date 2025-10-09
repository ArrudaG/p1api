package com.example.p1api.controller;

import com.example.p1api.dtos.ProdutoRequestDto;
import com.example.p1api.dtos.ProdutoResponseDto;
import com.example.p1api.mapper.ProdutoMapper;
import com.example.p1api.model.Produto;
import com.example.p1api.service.ProdutoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping (value = "/produto" , produces = "application/json")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping (consumes = "application/json")
    public ResponseEntity<ProdutoResponseDto> criarProduto(@RequestBody @Valid ProdutoRequestDto produtoDTO){

        Produto produto = ProdutoMapper.toEntity(produtoDTO, null);
        Produto novoProduto = produtoService.criar(produto);
        ProdutoResponseDto responseDTO = ProdutoMapper.toDto(novoProduto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("<id>")
    public Optional<Produto> buscarProduto(@RequestParam Long id) {
        return produtoService.buscarPorId(id);
    }

    @PutMapping("<id>")
    public Optional<Produto> atualizarProduto(@RequestParam Long id, @RequestBody Produto produtoAtualizado) {
        return produtoService.atualizar(id, produtoAtualizado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable("id") Long id) {
        boolean removido = produtoService.deletar(id);
        if (removido) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}

