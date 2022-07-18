package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.POJO.UserPojo;
import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.bean.RetoD.MyBeanR;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import org.apache.catalina.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log logger = LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanR myBeanR;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;

	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanR myBeanR, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanR = myBeanR;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
	}


	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		componentDependency.Saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		//myBeanR.print();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail()+" - "+userPojo.getPassword()+" - "+userPojo.getAge());

		try{

			int valor = 10/0;
			logger.debug("Mi valor "+valor);

		}catch (Exception e){
			logger.error("Esto es un error al dividir por cero "+e.getStackTrace());
		}

	}
}
