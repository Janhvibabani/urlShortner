package com.urlShortner.urlShortner.controller;

import com.urlShortner.urlShortner.models.UrlMapping;
import com.urlShortner.urlShortner.service.UrlMappingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RedirectController {
    private UrlMappingService urlMappingService;
    @GetMapping("/{shortUrl")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        UrlMapping urlMapping = urlMappingService.getOrignalUrl(shortUrl);
        if(urlMapping != null) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Location", urlMapping.getOrginalUrl());
            return ResponseEntity.status(302).headers(httpHeaders).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
