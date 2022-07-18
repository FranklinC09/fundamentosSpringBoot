package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.*;
import com.fundamentos.springboot.fundamentos.bean.RetoD.MyBeanImplementR;
import com.fundamentos.springboot.fundamentos.bean.RetoD.MyBeanR;
import com.fundamentos.springboot.fundamentos.bean.RetoD.MyOperationR;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    @Bean
    public MyBean beanOperation(){
        return new MyBean2Implement();
    }

    @Bean
    public MyOperation beanOperationOperation(){
        return new MyOperationImplement();
    }

    @Bean
    public MyBeanWithDependency beanOperationOperationWithDependency(MyOperation myOperation){
        return new MyBeanWithDependencyImplement(myOperation);
    }

}
