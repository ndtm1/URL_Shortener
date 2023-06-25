package com.url.shortener.service;

import com.url.shortener.dto.UrlLongRequest;
import com.url.shortener.entity.Url;
import com.url.shortener.repository.UrlRepo;
import jakarta.persistence.EntityNotFoundException;

import java.util.Date;

public class UrlService {
    private final UrlRepo repo;
    private final BaseService baseService;

    public UrlService(UrlRepo repo, BaseService baseService) {
        this.repo = repo;
        this.baseService = baseService;
    }

    public String convertToShort(UrlLongRequest longUrl) {
        Url url = new Url();
        url.setLongUrl(longUrl.getLongUrl());
        url.setExpiresDate(longUrl.getExpiresDate());
        url.setCreatedDate(new Date());

        Url entity = repo.save(url);

        System.out.println(entity.getId());

        return baseService.encode(entity.getId());
    }

    public String getLongUrl(String shortUrl) {
        int id = baseService.decode(shortUrl);
        Url longUrl = repo.findById(id).orElseThrow(() ->new EntityNotFoundException("No entity found " + shortUrl));

        return longUrl.getLongUrl();
    }
}
