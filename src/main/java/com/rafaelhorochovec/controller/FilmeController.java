package com.rafaelhorochovec.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelhorochovec.model.Filme;
import com.rafaelhorochovec.repository.FilmeRepository;

@RestController
@RequestMapping("/api")
public class FilmeController {

	@Autowired
	FilmeRepository filmeRepository;

	@PostMapping("/filmes")
	public ResponseEntity<Filme> create(@RequestBody Filme filme) {
		try {
			Filme _filme = filmeRepository
					.save(new Filme(filme.getTitulo()));
			return new ResponseEntity<>(_filme, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/filmes")
	public ResponseEntity<List<Filme>> read(@RequestParam(required = false) String titulo) {
		try {
			List<Filme> filmes = new ArrayList<Filme>();
			if (titulo == null) {
				filmeRepository.findAll().forEach(filmes::add);
			} else {
				filmeRepository.findByTitulo(titulo).forEach(filmes::add);
			}
			if (filmes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(filmes, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/filmes/{id}")
	public ResponseEntity<Filme> getById(@PathVariable("id") UUID id) {
		Optional<Filme> filme = filmeRepository.findById(id);
		if (filme.isPresent()) {
			return new ResponseEntity<>(filme.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/filmes/{id}")
	public ResponseEntity<Filme> update(@PathVariable("id") UUID id, @RequestBody Filme filme) {
		Optional<Filme> objFilme = filmeRepository.findById(id);
		if (objFilme.isPresent()) {
			Filme _filme = objFilme.get();
			_filme.setTitulo(filme.getTitulo());
			return new ResponseEntity<>(filmeRepository.save(_filme), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/filmes/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID id) {
		try {
			filmeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/filmes")
	public ResponseEntity<HttpStatus> deleteAll() {
		try {
			filmeRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}