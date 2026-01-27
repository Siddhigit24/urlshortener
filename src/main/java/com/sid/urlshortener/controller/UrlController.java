package com.sid.urlshortener.controller;

import com.sid.urlshortener.service.UrlService;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    // Shorten long URL
    @GetMapping("/shorten")
    public String shortenUrl(@RequestParam String url) {
        return urlService.shortenUrl(url);
    }

    // Redirect short URL to original URL
    @GetMapping("/{code}")
    public void redirect(
            @PathVariable String code,
            HttpServletResponse response
    ) throws IOException {
        String originalUrl = urlService.getOriginalUrl(code);
        response.sendRedirect(originalUrl);
    }
}
