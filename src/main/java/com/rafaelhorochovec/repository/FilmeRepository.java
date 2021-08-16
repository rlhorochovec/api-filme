package com.rafaelhorochovec.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelhorochovec.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, UUID> {

	List<Filme> findByTitulo(String titulo);
}
