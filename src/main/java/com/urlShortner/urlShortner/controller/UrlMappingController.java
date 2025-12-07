package com.urlShortner.urlShortner.controller;

import com.urlShortner.urlShortner.dtos.UrlMappingDTO;
import com.urlShortner.urlShortner.models.User;
import com.urlShortner.urlShortner.service.UrlMappingService;
import com.urlShortner.urlShortner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
@AllArgsConstructor
public class UrlMappingController {
    private UrlMappingService urlMappingService;
    private UserService userService;
    //{key:value}
    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UrlMappingDTO> createShortUrl(@RequestBody Map<String, String> request, Principal principal) {
        String orignalUrl = request.get("orignalUrl");
        User user = userService.findByUsername(principal.getName());
        UrlMappingDTO urlMappingDTO = urlMappingService.createShortUrl(orignalUrl, user);

        return ResponseEntity.ok(urlMappingDTO);

    }

    @GetMapping("/myurls")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UrlMappingDTO>> getUserUrls(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<UrlMappingDTO> urls = urlMappingService.getUrlsByUser(user);
        return ResponseEntity.ok(urls);
    }
}
