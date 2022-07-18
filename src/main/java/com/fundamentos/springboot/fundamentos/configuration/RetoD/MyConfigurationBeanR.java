package com.fundamentos.springboot.fundamentos.configuration.RetoD;

import com.fundamentos.springboot.fundamentos.bean.RetoD.MyBeanImplementR;
import com.fundamentos.springboot.fundamentos.bean.RetoD.MyBeanR;
import com.fundamentos.springboot.fundamentos.bean.RetoD.MyOperationImplementR;
import com.fundamentos.springboot.fundamentos.bean.RetoD.MyOperationR;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBeanR {

    @Bean
    public MyBeanR beanOperationR(MyOperationR myOperationR){
        return new MyBeanImplementR(myOperationR);

    }

    @Bean
    public MyOperationR beanOperationRN(){
        return new MyOperationImplementR();

    }

}
