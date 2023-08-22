package com.example.CharityOrganizationSpringApp.util;

public class ObjectIsAlreadyUsedException extends RuntimeException{
    public ObjectIsAlreadyUsedException(String msg) {
        super(msg);}
}
