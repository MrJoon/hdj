package com.example.hdj.utils;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Utils {

    private static ModelMapper modelMapper;

    public static void setModelMapper(ModelMapper modelMapper) {
        Utils.modelMapper = modelMapper;
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public static String randomPatientNumber() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 13;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
