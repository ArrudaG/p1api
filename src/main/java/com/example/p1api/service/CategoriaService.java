package com.example.p1api.service;

import com.example.p1api.model.Categoria;
import com.example.p1api.model.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    public static final List<Categoria> categorias = new ArrayList<>();
    private Long nextId = 1L;

    public CategoriaService() {}

    public Categoria criar(Categoria categoria) {
        categoria.setId(nextId++);
        categorias.add(categoria);
        return categoria;
    }


    public Optional<Categoria> buscarPorId(Long id) {
        return categorias.stream()
                .filter(p -> p.getId().equals(id))
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

    public boolean deletar(Long id) {
        categorias.removeIf(p -> p.getId().equals(id));
        return categorias.isEmpty();
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

}



