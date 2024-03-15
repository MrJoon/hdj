package com.example.hdj.utils;

import org.modelmapper.ModelMapper;

import java.util.List;
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
}
