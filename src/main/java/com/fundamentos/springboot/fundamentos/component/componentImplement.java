package com.fundamentos.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class componentImplement implements ComponentDependency{

    @Override
    public void Saludar() {
        System.out.println("Hola mundo componente");
    }
}
