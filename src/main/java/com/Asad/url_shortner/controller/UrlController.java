package com.Asad.url_shortner.controller;

import com.Asad.url_shortner.dto.ShortenUrlRequest;
import com.Asad.url_shortner.dto.ShortenUrlResponse;
import com.Asad.url_shortner.service.UrlShortnerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {

    private final UrlShortnerService urlShortnerService;

    public UrlController(UrlShortnerService urlShortnerService) {

        this.urlShortnerService = urlShortnerService;
    }

    @PostMapping("api/v1/url/shorten")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@Valid @RequestBody ShortenUrlRequest request) {

        String shortcode = urlShortnerService.shortenUrl(request.url());

        String fullShortUrl = "https://localhost:8080/" + shortcode;

        ShortenUrlResponse response = new ShortenUrlResponse(fullShortUrl);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{shortcode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortcode) {

        String originalUrl = urlShortnerService.getOgUrlAndIncrementClicks(shortcode);


        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
    }

}