package com.example.CharityOrganizationSpringApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
public class TokenDTO {
    private Map<String, String> map;
}
