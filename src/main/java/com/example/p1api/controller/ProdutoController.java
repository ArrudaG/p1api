package com.example.p1api.controller;

import com.example.p1api.dtos.ProdutoRequestDto;
import com.example.p1api.dtos.ProdutoResponseDto;
import com.example.p1api.mapper.ProdutoMapper;
import com.example.p1api.model.Produto;
import com.example.p1api.service.ProdutoServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping (value = "/produto" , produces = "application/json")
public class ProdutoController {
    private final ProdutoServices produtoService;
    private List<Produto> produtos;

    public ProdutoController(ProdutoServices produtoService) {
        this.produtoService = produtoService;
        produtos = new ArrayList<>();
        produtos = produtoService.listarTodos();

    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping (consumes = "application/json")
    public ResponseEntity<ProdutoResponseDto> criarProduto(@RequestBody @Valid ProdutoRequestDto produtoDTO){

        Long newId = (long) (produtos.size() + 1);
        Produto produto = ProdutoMapper.toEntity(produtoDTO, newId);
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

    @DeleteMapping("<id>")
    public void deletarProduto(@RequestParam Long id) {
        produtoService.deletar(id);
    }

}

