package com.rafaelhorochovec.service;

import com.rafaelhorochovec.model.Filme;
import com.rafaelhorochovec.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FilmeService {

    @Autowired
    FilmeRepository filmeRepository;

    // CREATE 
    public Filme createFilme(Filme filme) {
        return filmeRepository.save(filme);
    }

    // READ
    public List<Filme> getFilmes() {
        return filmeRepository.findAll();
    }

    // UPDATE
    public Filme updateFilme(UUID id, Filme filmeDetails) {
        Filme filme = filmeRepository.findById(id).get();
        filme.setTitulo(filmeDetails.getTitulo());
        return filmeRepository.save(filme);
    }

    // GET BY ID
    public Filme getById(UUID id) {
        Filme filme = filmeRepository.findById(id).get();
        return filmeRepository.getOne(id);
    }

    // DELETE
    public void deleteFilme(UUID id) {
        filmeRepository.deleteById(id);
    }
}
