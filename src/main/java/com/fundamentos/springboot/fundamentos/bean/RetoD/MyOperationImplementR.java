package com.fundamentos.springboot.fundamentos.bean.RetoD;

import java.util.Scanner;

public class MyOperationImplementR implements MyOperationR{
    @Override
    public void registro(String nombre, String apellido) {
        System.out.println("Su nombre es: "+nombre+"\n"+"Su apellido es: "+apellido);
    }
}
