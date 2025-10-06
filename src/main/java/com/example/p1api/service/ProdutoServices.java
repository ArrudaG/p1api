package com.example.p1api.service;

import com.example.p1api.model.Categoria;
import com.example.p1api.model.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.p1api.service.CategoriaService.categorias;

@Service
public class ProdutoServices {
    private final List<Produto> produtos = new ArrayList<>();
    private List<Produto> produtosList = new ArrayList<>();
    private Long nextId = 1L;

    public ProdutoServices() {}


    public Produto criar(Produto produto) {
        produto.setId(nextId++);
        produtos.add(produto);
        for (Categoria cat : categorias) {
            if (produto.getCategoriaId().equals(cat.getId())) {
                cat.addProduto(produto);
            }
        }
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
        produtos.removeIf(p -> p.getId().equals(id));
        return produtos.isEmpty();
    }
}

