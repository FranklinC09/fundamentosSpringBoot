package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.POJO.UserPojo;
import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.bean.RetoD.MyBeanR;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private final ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanR myBeanR;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;

	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanR myBeanR, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo,UserRepository userRepository, UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanR = myBeanR;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}


	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void saveWithErrorTransactional(){

		User test1 = new User("TestTransac1", "TestTransac1@domain.com", LocalDate.of(2020,04,15));
		User test2 = new User("Test2Transac1", "Test2Transac1@domain.com", LocalDate.of(2020,10,22));
		User test3 = new User("Test3Transac1", "Test3Transac1@domain.com", LocalDate.of(2020,11,02));
		User test4 = new User("Test4Transac1", "Test4Transac1@domain.com", LocalDate.of(2020,01,07));

		List<User> users = Arrays.asList(test1,test2,test3,test4);
		try {
			userService.saveTransactional(users);
		}catch (Exception e){
			LOGGER.error("ESTA ES UNA EXCEPCION DENTRO DEL METODO TRANSACCIONAL "+e);
		}


		userService.getAllUsers().stream().forEach(user -> LOGGER.info("USUARIO DENTRO DEL METODO TRANSACCIONAL: "+user));

	}

	private void getInformationJpqlFromUser(){
		/*LOGGER.info("Usuario con el metodo FindByUserEmail "+
				userRepository.findByUserEmail("marco@domain.com")
						.orElseThrow(()->new RuntimeException("No se encuentra el usuario")));

		userRepository.findAndSort("Mar", Sort.by("id").descending())
				.stream().forEach(user -> LOGGER.info("USUARIO CON EL METODO SORT "+user));

		userRepository.findByName("John").stream().forEach(user -> LOGGER.info("Usuario con query method " + user));

		LOGGER.info("USUARIO CON QUERY METHOD findByEmailAndName "+userRepository.findByEmailAndName("marisol@domain.com","Marisol")
				.orElseThrow(()-> new RuntimeException("USUARIO NO ENCONTRADO")));

		userRepository.findByNameLike("%M%")
				.stream().forEach(user -> LOGGER.info("USUARIO CON METHOD findByNameLike "+user));

		userRepository.findByNameOrEmail("Carlos",null)
				.stream().forEach(user -> LOGGER.info("USUARIO CON METHOD findByNameOrEmail "+user));

		userRepository.findByBirthDateBetween(LocalDate.of(2021,04,01), LocalDate.of(2021,10,29))
				.stream().forEach(user -> LOGGER.info("USUARIO CON INTERVALO DE FECHAS: "+user));

		userRepository.findByNameContainingOrderByIdDesc("John")
				.stream().forEach(user -> LOGGER.info("USUARIO CON LIKE Y ORDER BY: "+user));

		userRepository.findByNameContainingOrEmailAndBirthDateOrderByIdAsc("J","john@domain.com", LocalDate.of(2021,03,13))
				.stream().forEach(user -> LOGGER.info("USUARIO METODO RETO: "+user));*/

		LOGGER.info("USUARIO A PARTIR DEL NAMED PARAMETER ES: "+userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021, 1, 1),"karen@domain.com")
				.orElseThrow(()-> new RuntimeException("No se encontro el usuario a partir del Named Parameter")));
	}

	private void saveUsersInDataBase(){
		User user1 = new User("John", "john@domain.com", LocalDate.of(2021, 3, 13));
		User user2 = new User("Marco", "marco@domain.com", LocalDate.of(2021, 12, 8));
		User user3 = new User("John", "daniela@domain.com", LocalDate.of(2021, 9, 8));
		User user4 = new User("Marisol", "marisol@domain.com", LocalDate.of(2021, 6, 18));
		User user5 = new User("Karen", "karen@domain.com", LocalDate.of(2021, 1, 1));
		User user6 = new User("Carlos", "carlos@domain.com", LocalDate.of(2021, 7, 7));
		User user7 = new User("Enrique", "enrique@domain.com", LocalDate.of(2021, 11, 12));
		User user8 = new User("Luis", "luis@domain.com", LocalDate.of(2021, 2, 27));
		User user9 = new User("Mateo", "mateo@domain.com", LocalDate.of(2021, 4, 10));
		User user10 = new User("Franklin","franklin@domain.com",LocalDate.of(2021,10,05));
		List<User> listUsers = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10);
		listUsers.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriores(){
		componentDependency.Saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		//myBeanR.print();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail()+" - "+userPojo.getPassword()+" - "+userPojo.getAge());

		try{

			int valor = 10/1;
			LOGGER.debug("Mi valor "+valor);

		}catch (Exception e){
			LOGGER.error("Esto es un error al dividir por cero "+e.getStackTrace());
		}
	}
}
