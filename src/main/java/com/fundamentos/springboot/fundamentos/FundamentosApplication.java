package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.POJO.UserPojo;
import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.bean.RetoD.MyBeanR;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log logger = LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanR myBeanR;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;

	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanR myBeanR, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo,UserRepository userRepository) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanR = myBeanR;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
	}

	private void getInformationJpqlFromUser(){
		logger.info("Usuario con el metodo FindByUserEmail "+
				userRepository.findByUserEmail("john@domain.com")
						.orElseThrow(()->new RuntimeException("No se encuentra el usuario")));

		userRepository.findAndSort("user", Sort.by("id").descending())
				.stream().forEach(user -> logger.info("User con metodo Sort"+user));
	}

	private void saveUsersInDataBase(){
		User user1 = new User("John", "john@domain.com", LocalDate.of(2021, 3, 13));
		User user2 = new User("Marco", "marco@domain.com", LocalDate.of(2021, 12, 8));
		User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 9, 8));
		User user4 = new User("Marisol", "marisol@domain.com", LocalDate.of(2021, 6, 18));
		User user5 = new User("Karen", "karen@domain.com", LocalDate.of(2021, 1, 1));
		User user6 = new User("Carlos", "carlos@domain.com", LocalDate.of(2021, 7, 7));
		User user7 = new User("Enrique", "enrique@domain.com", LocalDate.of(2021, 11, 12));
		User user8 = new User("Luis", "luis@domain.com", LocalDate.of(2021, 2, 27));
		User user9 = new User("Paola", "paola@domain.com", LocalDate.of(2021, 4, 10));
		User user10 = new User("Franklin","franklin@domain.com",LocalDate.of(2021,10,05));
		List<User> listUsers = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10);
		listUsers.forEach(userRepository::save);
		//userRepository.saveAll(listUsers);
	}

	private void ejemplosAnteriores(){
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
