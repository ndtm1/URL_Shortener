package com.url.shortener.controller;

import com.url.shortener.dto.UrlLongRequest;
import com.url.shortener.service.UrlService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {

    private UrlService urlService;

    @PostMapping("create-short")
    public String convertToShort(@RequestBody UrlLongRequest request) {
        return urlService.convertToShort(request);
    }

    @Cacheable(value = "links", key = "#shortUrl", sync = true)
    @GetMapping(value = "{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        String url = urlService.getLongUrl(shortUrl);

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
    }

}
