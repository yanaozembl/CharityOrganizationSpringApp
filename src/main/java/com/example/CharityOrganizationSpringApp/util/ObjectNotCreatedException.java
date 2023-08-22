package com.example.CharityOrganizationSpringApp.util;

public class ObjectNotCreatedException extends RuntimeException{
    public ObjectNotCreatedException(String msg) {
        super(msg); // передали сообщение в RuntimeException
    }
}
