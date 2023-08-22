package com.example.CharityOrganizationSpringApp.util;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String msg) {
        super(msg); // передали сообщение в RuntimeException
    }
}
