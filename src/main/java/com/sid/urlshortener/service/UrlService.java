package com.sid.urlshortener.service;

import com.sid.urlshortener.entity.UrlMapping;
import com.sid.urlshortener.repository.UrlMappingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlService {

    private final UrlMappingRepository urlMappingRepository;

    public UrlService(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    // Generate short code for a long URL
    public String shortenUrl(String originalUrl) {
        String shortCode = generateShortCode();

        UrlMapping urlMapping = new UrlMapping(originalUrl, shortCode);
        urlMappingRepository.save(urlMapping);

        return shortCode;
    }

    // Retrieve original URL for a short code
    public String getOriginalUrl(String code) {
        UrlMapping mapping = urlMappingRepository.findByShortCode(code);
        if (mapping == null) {
            throw new RuntimeException("Short URL not found: " + code);
        }
        return mapping.getOriginalUrl();
    }

    private String generateShortCode() {
        return UUID.randomUUID().toString().substring(0, 6);
    }
}
