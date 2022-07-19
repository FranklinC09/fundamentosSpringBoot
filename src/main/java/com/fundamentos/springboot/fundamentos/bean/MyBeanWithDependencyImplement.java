package com.fundamentos.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);
    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("Hemos ingresado al metodo PrintWithDependency");
        int numero = 5;
        LOGGER.debug("El numero enviado a la dependencia a la operacion es: "+numero);
        System.out.println("Resultado:" + myOperation.sum(numero));
        System.out.println("Hola desde la implementacion desde bean con dependencia");
    }
}
