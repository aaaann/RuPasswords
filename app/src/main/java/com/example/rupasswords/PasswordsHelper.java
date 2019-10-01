package com.example.rupasswords;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.example.rupasswords.PasswordQualityEnum.BAD;
import static com.example.rupasswords.PasswordQualityEnum.EXCELLENT;
import static com.example.rupasswords.PasswordQualityEnum.GOOD;
import static com.example.rupasswords.PasswordQualityEnum.NORMAL;
import static com.example.rupasswords.PasswordQualityEnum.VERY_BAD;
import static com.example.rupasswords.PasswordQualityEnum.VERY_GOOD;

public class PasswordsHelper {

    private Map<Integer,String> passSymbols = new HashMap<Integer, String>(){{
        put(0, "abcdefghijklmnopqrstuvwxyz");
        put(1, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        put(2, "1234567890");
        put(3, "!@#$%^&*()_+=-?<>~");
    }};

    private final String[] russians;
    private final String[] latin;

    public PasswordsHelper(String[] russians, String[] latin) {
        this.russians = russians;
        this.latin = latin;

        if (russians.length != latin.length)
            throw new IllegalArgumentException();
    }



    public String convert(CharSequence source) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < source.length(); i++){
            char c = source.charAt(i);
            String s = String.valueOf(c);
            boolean found = false;

            for(int j = 0; j < russians.length; j++){
                if (russians[j].equals(s)){
                    result.append(latin[j]);
                    found = true;
                    break;
                }
            }

            if (!found){
                result.append(s);
            }
        }

        return result.toString();
    }

    public PasswordQualityEnum getPasswordQuality(CharSequence password) {
        if (password.length() < 4) return VERY_BAD;
        else if (password.length() > 4 && password.length() <= 6) return BAD;
        else if (password.length() > 6 && password.length() <= 8) return NORMAL;
        else if (password.length() > 8 && password.length() <= 10) return GOOD;
        else if (password.length() > 10 && password.length() <= 12) return VERY_GOOD;
        else if (password.length() > 12) return EXCELLENT;

        return VERY_BAD;
    }


    public String generatePassword(boolean useCaps, boolean useNumbers, boolean useSymbols, int passLength) {
        StringBuilder result = new StringBuilder();

        Random rnd = new Random();
        String charsGroup;
        int numberOfGroups = 1 + (useCaps ? 1 : 0) + (useNumbers ? 1 : 0) + (useSymbols ? 1 : 0);
        for (int i = 0; i < numberOfGroups; i++){
            charsGroup = passSymbols.get(i);
            result.append(charsGroup.charAt(rnd.nextInt(charsGroup.length())));
        }
        for (int i = 0; i < passLength - (numberOfGroups - 1); i++){
            int whatGroup = rnd.nextInt(numberOfGroups);
            charsGroup = passSymbols.get(whatGroup);
            result.append(charsGroup.charAt(rnd.nextInt(charsGroup.length())));
        }

        return result.toString();
    }



}
