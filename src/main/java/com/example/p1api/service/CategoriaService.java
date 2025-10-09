package com.example.p1api.service;

import com.example.p1api.model.Categoria;
import com.example.p1api.model.Produto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoriaService {
    private static final String dadosCategoria = "categorias.json";
    private final ObjectMapper mapper = new ObjectMapper();
    public static List<Categoria> categorias = new ArrayList<>();
    private Long nextId = 1L;

    public CategoriaService() {
        carregarDados();
    }

    public Categoria criar(Categoria categoria) {
        categoria.setId(nextId++);
        categorias.add(categoria);
        salvarDados();
        return categoria;
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categorias.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public Optional<Categoria> buscarPorNome(String nome) {
        return categorias.stream()
                .filter(p -> p.getNome().equals(nome))
                .findFirst();
    }

    public List<Categoria> listarTodos() {
        return categorias;
    }

    public Optional<Categoria> atualizar(Long id, Categoria categoriaAtualizado) {
        Optional<Categoria> categoriaExistenteOpt = buscarPorId(id);
        if (categoriaExistenteOpt.isPresent()) {
            Categoria categoriaExistente = categoriaExistenteOpt.get();
            categoriaAtualizado.setId(id);
            int index = categorias.indexOf(categoriaExistente);
            categorias.set(index, categoriaAtualizado);
            return Optional.of(categoriaAtualizado);
        }
        return Optional.empty();
    }

    public void addProduto(Produto produto) {
        for (Categoria cat : categorias) {
            if (Objects.equals(cat.getId(), produto.getCategoriaId())) {
                if (cat.getProdutoList() == null) {
                    cat.setProdutoList(new ArrayList<>());
                }
                cat.getProdutoList().add(produto);
                salvarDados();
                break;
            }
        }
    }

    public List<Produto> listarProdutos(Long id) {
        List<Produto> retorno = new ArrayList<>();
        for (Categoria cat : categorias) {
            if (cat.getId().equals(id)){
                retorno = cat.getProdutoList();
            }
        }
        return retorno;
    }

    public void removerProdutoCategoria(Produto produto){
        for (Categoria cat : categorias) {
            if (Objects.equals(cat.getId(), produto.getCategoriaId())) {
                cat.getProdutoList().removeIf(p -> Objects.equals(p.getId(), produto.getCategoriaId()));
                salvarDados();
            }
            break;
        }
    }

    private void carregarDados() {
        try {
            File file = new File(dadosCategoria);
            if(file.exists()) {
                categorias = mapper.readValue(file, new TypeReference<List<Categoria>>() {});
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarDados() {
        try {
            mapper.writeValue(new File(dadosCategoria), categorias);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}