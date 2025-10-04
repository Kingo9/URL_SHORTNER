// This line declares that our new class belongs to the 'controller' package.
// This completes our primary layered architecture: controller, service, repository, and model.
package com.Asad.url_shortner.controller;

import com.Asad.url_shortner.dto.ShortenUrlRequest;
import com.Asad.url_shortner.dto.ShortenUrlResponse;
import com.Asad.url_shortner.service.UrlShortnerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class will serve as the REST Controller for all URL-related operations.
 * A controller in Spring is responsible for handling incoming web requests.
 *
 * In the upcoming tasks, we will annotate this class with @RestController. This will
 * mark it as a special type of controller for building RESTful web services, where
 * method return values are automatically written directly to the HTTP response body,
 * typically as JSON.
 *
 * This class will act as the entry point for our API, receiving requests from users,
 * delegating the business logic to the UrlShortenerService, and returning the
 * result.
 */

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

        return  null;
    }

}