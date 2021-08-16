package com.rafaelhorochovec.test;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class FilmeTest {

	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8080/api";
	}

	@Test
	public void deveRetornarTodosFilmes() {
		RestAssured
		.given()
		.when()
			.get("/filmes")
		.then()
			.log().all()
			.statusCode(200);
	}
}
