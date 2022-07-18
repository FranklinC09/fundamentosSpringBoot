package com.fundamentos.springboot.fundamentos.bean.RetoD;

import java.util.Scanner;

public class MyBeanImplementR implements MyBeanR{

    private MyOperationR myOperationR;

    public MyBeanImplementR(MyOperationR myOperationR) {
        this.myOperationR = myOperationR;
    }

    @Override
    public void print() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese el apellido: ");
        String apellido = sc.nextLine();
        myOperationR.registro(nombre,apellido);
        System.out.println("Se realizo la inyeccion del bean");
    }
}
