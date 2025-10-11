package com.example.p1api.service;

import com.example.p1api.model.Categoria;
import com.example.p1api.model.Produto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.io.File;

import static com.example.p1api.service.CategoriaService.categorias;

@Service
public class ProdutoService {
    private static final String dadosProduto = "produtos.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Produto> produtos = new ArrayList<>();
    private Long nextId = 1L;
    private final CategoriaService categoriaService = new CategoriaService();

    public ProdutoService() {
        carregarDados();
    }

    public Produto criar(Produto produto) {
        produto.setId(nextId++);
        produtos.add(produto);
        for (Categoria cat : categorias) {
            if (produto.getCategoriaId().equals(cat.getId())) {
                categoriaService.addProduto(produto);
            }
        }
        salvarDados();
        return produto;
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public List<Produto> listarTodos() {
        return produtos;
    }

    public Optional<Produto> atualizar(Long id, Produto produtoAtualizado) {
        Optional<Produto> produtoExistenteOpt = buscarPorId(id);

        if (produtoExistenteOpt.isPresent()) {
            Produto produtoExistente = produtoExistenteOpt.get();
            produtoAtualizado.setId(id);

            int index = produtos.indexOf(produtoExistente);
            produtos.set(index, produtoAtualizado);

            return Optional.of(produtoAtualizado);
        }

        return Optional.empty();
    }

    public boolean deletar(Long id) {
        Optional<Produto> produtoOpt = buscarPorId(id);
        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            boolean removido = produtos.removeIf(p -> p.getId().equals(id));
            if (removido) {
                salvarDados();
                categoriaService.removerProdutoCategoria(produto);
            }
            return removido;
        }
        return false;
    }

    private void carregarDados() {
        try {
            File file = new File(dadosProduto);
            if (file.exists()) {
                produtos = mapper.readValue(file, new TypeReference<List<Produto>>() {});
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarDados() {
        try {
            mapper.writeValue(new File(dadosProduto), produtos);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

