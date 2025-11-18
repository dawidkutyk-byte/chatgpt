/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson;

import com.google.gson.FieldNamingStrategy;
import java.lang.reflect.Field;
import java.util.Locale;

public enum FieldNamingPolicy implements FieldNamingStrategy
{
    IDENTITY{

        @Override
        public String translateName(Field f) {
            return f.getName();
        }
    }
    ,
    UPPER_CAMEL_CASE{

        @Override
        public String translateName(Field f) {
            return 2.upperCaseFirstLetter(f.getName());
        }
    }
    ,
    UPPER_CAMEL_CASE_WITH_SPACES{

        @Override
        public String translateName(Field f) {
            return 3.upperCaseFirstLetter(3.separateCamelCase((String)f.getName(), (char)' '));
        }
    }
    ,
    LOWER_CASE_WITH_UNDERSCORES{

        @Override
        public String translateName(Field f) {
            return 4.separateCamelCase((String)f.getName(), (char)'_').toUpperCase(Locale.ENGLISH);
        }
    }
    ,
    LOWER_CASE_WITH_DASHES{

        @Override
        public String translateName(Field f) {
            return 5.separateCamelCase((String)f.getName(), (char)'_').toLowerCase(Locale.ENGLISH);
        }
    };


    static String upperCaseFirstLetter(String name) {
        StringBuilder fieldNameBuilder = new StringBuilder();
        int index = 0;
        char firstCharacter = name.charAt(index);
        while (index < name.length() - 1 && !Character.isLetter(firstCharacter)) {
            fieldNameBuilder.append(firstCharacter);
            firstCharacter = name.charAt(++index);
        }
        if (index == name.length()) {
            return fieldNameBuilder.toString();
        }
        if (Character.isUpperCase(firstCharacter)) return name;
        String modifiedTarget = FieldNamingPolicy.modifyString(Character.toUpperCase(firstCharacter), name, ++index);
        return fieldNameBuilder.append(modifiedTarget).toString();
    }

    static String separateCamelCase(String name, String separator) {
        StringBuilder translation = new StringBuilder();
        int i = 0;
        while (i < name.length()) {
            char character = name.charAt(i);
            if (Character.isUpperCase(character) && translation.length() != 0) {
                translation.append(separator);
            }
            translation.append(character);
            ++i;
        }
        return translation.toString();
    }

    private static String modifyString(char firstCharacter, String srcString, int indexOfSubstring) {
        return indexOfSubstring < srcString.length() ? firstCharacter + srcString.substring(indexOfSubstring) : String.valueOf(firstCharacter);
    }
}
