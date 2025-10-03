package com.Asad.url_shortner.service;


import com.Asad.url_shortner.Repository.UrlMappingRepository;
import com.Asad.url_shortner.model.UrlMapping;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class UrlShortnerService {

    private static final String BASE62_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final UrlMappingRepository UrlMappingRepository;

    public UrlShortnerService (UrlMappingRepository UrlMappingRepository) {
        this.UrlMappingRepository = UrlMappingRepository;
    }

    @Transactional
    public String shortenUrl (String Og_Url) {

        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOg_URL(Og_Url);
        urlMapping.setCreation_time(LocalDateTime.now());

        UrlMapping savedEntity = UrlMappingRepository.save(urlMapping);

        String shortcode = encodeBase62(savedEntity.getId());

        savedEntity.setShortcode(shortcode);

        UrlMappingRepository.save(savedEntity);


        return shortcode;
    }

    private String encodeBase62 (Long number) {

        if (number == 0) {

            return String.valueOf(BASE62_CHARS.charAt(0));
        }

        StringBuilder sb = new StringBuilder();
        long num = number;

        while (num > 0) {

            int remainder = (int) (num % 62);

            sb.append(BASE62_CHARS.charAt(remainder));

            num /= 62;
        }

        return sb.reverse().toString();
    }
}
