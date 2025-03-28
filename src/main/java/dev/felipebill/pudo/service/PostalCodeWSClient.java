package dev.felipebill.pudo.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.felipebill.pudo.model.PostalCode;

@Service
public class PostalCodeWSClient {

	private final RestTemplate restTemplate;

	@Autowired
	public PostalCodeWSClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.setReadTimeout(Duration.ofMinutes(1))
				.setConnectTimeout(Duration.ofSeconds(30)).build();
	}

	public CepData buscarCep(PostalCode postalCode) {
		String url = String.format("http://viacep.com.br/ws/%s/json/", postalCode.toString());
		return restTemplate.getForObject(url, CepData.class);
	}
}