package com.springboot.cmp.util;

public class NameValidator {

    public static boolean isNameValid(String name) {
        for (char ch : name.toCharArray())
            if (!Character.isLetter(ch)) return false;
        return true;
    }
    
}
