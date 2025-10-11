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
    public ResponseEntity<?> listarProdutos() {
        List<Produto> retorno = produtoService.listarTodos();
        if (retorno.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Não foi encontrado nenhum produto");
        }
        return ResponseEntity.ok(retorno);
    }

    @PostMapping (consumes = "application/json")
    public ResponseEntity<ProdutoResponseDto> criarProduto(@RequestBody @Valid ProdutoRequestDto produtoDTO){
        Produto produto = ProdutoMapper.toEntity(produtoDTO);
        Produto novoProduto = produtoService.criar(produto);
        ProdutoResponseDto responseDTO = ProdutoMapper.toDto(novoProduto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("<id>")
    public ResponseEntity<?> buscarProduto(@RequestParam Long id) {
        Optional<Produto> retorno = produtoService.buscarPorId(id);
        if (retorno.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Não foi encontrado o produto com id = " + id);
        }
        return ResponseEntity.ok(retorno);
    }

    @PutMapping(value = "<id>", consumes = "application/json")
    public ResponseEntity<?> atualizarProduto(@RequestParam Long id, @RequestBody @Valid Produto produtoAtualizado) {
        Optional<Produto> retorno = produtoService.atualizar(id, produtoAtualizado);
        if (retorno.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível atualizar o produto de id = " + id);
        }
        return ResponseEntity.ok(retorno);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable("id") Long id) {
        boolean removido = produtoService.deletar(id);
        if (removido) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

