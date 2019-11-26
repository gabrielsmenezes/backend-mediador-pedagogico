package com.example.backapi.utils.mapper;

import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    public org.modelmapper.ModelMapper modelMapper(){
        return new org.modelmapper.ModelMapper();
    }

}
