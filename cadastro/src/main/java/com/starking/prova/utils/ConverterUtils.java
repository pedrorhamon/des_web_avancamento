package com.starking.prova.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public record ConverterUtils<T>(ModelMapperConfiguration modelMapperConfiguration) {

    public List<T> convertToListResponse(List<T> responses, Class<T> type){
        return responses
                .stream()
                .map(response -> modelMapperConfiguration.modelMapper().map(response, type))
                .collect(Collectors.toList());
    }

    public T convertRequestToEntity(T request, Class<T> type) {
        return modelMapperConfiguration.modelMapper().map(request, type);
    }

    public T convertEntityToResponse(T entity, Class<T> type){
        return modelMapperConfiguration.modelMapper().map(entity, type);
    }


}
