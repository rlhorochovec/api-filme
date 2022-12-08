package com.rafaelhorochovec.controller;

import com.rafaelhorochovec.model.Filme;
import com.rafaelhorochovec.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class FilmeController {

	@Autowired
	FilmeService filmeService;

	// POST
	@PostMapping("/filmes")
	public Filme createFilme(@RequestBody Filme filme) {
		return filmeService.createFilme(filme);
	}

	// GET
	@GetMapping("/filmes")
	public List<Filme> listFilmes() {
		return filmeService.getFilmes();
	}

	// PUT
	@PutMapping("/filmes/{id}")
	public Filme updateFilme(@PathVariable(value = "id") UUID id, @RequestBody Filme filmeDetails) {
		return filmeService.updateFilme(id, filmeDetails);
	}

	// GET BY ID
	@GetMapping("/filmes/{id}")
	public Filme getFilme(@PathVariable(value = "id") UUID id) {
		return filmeService.getById(id);
	}

	// DELETE
	@DeleteMapping("/filmes/{id}")
	public void deleteFilme(@PathVariable(value = "id") UUID id) {
		filmeService.deleteFilme(id);
	}
}