package com.url.shortener.controller;

import com.url.shortener.entity.Url;
import com.url.shortener.dto.UrlLongRequest;
import com.url.shortener.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

@Controller
public class UrlController {

    private UrlService urlService;

    @PostMapping("create-short")
    public String convertToShort(@RequestBody UrlLongRequest request) {
        return urlService.convertToShort(request);
    }

    @GetMapping(value = "{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        String url = urlService.getLongUrl(shortUrl);

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
    }

}
