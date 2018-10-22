package com.pujianto131.catalogmovieuiuxpakeko.utils;

import java.util.Locale;

public class Language {
    public static String getCountry(){
        String country = Locale.getDefault().getCountry().toLowerCase();
        switch (country){
            case "en":
                break;
            default:
                country = "id";
                break;
        }
        return country;
    }
}
