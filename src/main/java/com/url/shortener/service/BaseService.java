package com.url.shortener.service;

public class BaseService {
    private static final String ALLOWEDSTRINGS = "0123456789abcdefghijklmnopqrstuvwxyzABCDIFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = 62;

    public String encode(int x) {
        int remainder = 0;
        StringBuilder result = new StringBuilder();

        if(x < 0) {
            String msg = String.format("x is a negative, have '%d'", x);
            throw new BaseException(msg);
        } else if(x == 0) {
            return "0";
        } else {
            while(x != 0) {
                remainder = x % BASE;
                result.append(ALLOWEDSTRINGS.charAt(remainder));
                x = x / BASE;
            }
        }

        return result.reverse().toString();
    }

    public int decode(String shortUrl) {
        char[] charUrl = shortUrl.toCharArray();
        long result = 0;
        int symbol;
        int i = 0;

        while( i < charUrl.length) {
            symbol = ALLOWEDSTRINGS.indexOf(charUrl[i]);

            if(symbol == -1) {
                String msg = String.format("value '%s' is not a base62 number", shortUrl);
                throw new BaseException(msg);
            }

            result = result + symbol * ((int) Math.pow(62, charUrl.length - (i+1)));
            i++;
        }

        if(result > Integer.MAX_VALUE) {
            String msg = String.format("value '%s' greater than MAX_VALUE", shortUrl);
            throw new BaseException(msg);
        }

        return (int) result;
    }
}
