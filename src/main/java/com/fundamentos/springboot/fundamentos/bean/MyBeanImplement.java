package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanImplement implements MyBean{
    @Override
    public void print() {
        System.out.println("Hola implementacion propia del bean");
    }
}
