package com.url.shortener.dto;

import java.time.LocalDate;
import java.util.Date;

public class UrlLongRequest {
    private int id;

    private String longUrl;

    private Date expiresDate;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Date getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(Date expiresDate) {
        this.expiresDate = expiresDate;
    }
}
