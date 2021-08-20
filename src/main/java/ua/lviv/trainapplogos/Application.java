package ua.lviv.trainapplogos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import ua.lviv.trainapplogos.domain.User;
import ua.lviv.trainapplogos.domain.UserRole;
import ua.lviv.trainapplogos.security.CustomUserDetailsService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		PasswordEncoder client = (PasswordEncoder) ctx.getBean("passwordEncoder");
		
		CustomUserDetailsService service = (CustomUserDetailsService) ctx.getBean(CustomUserDetailsService.class);
		
		//add admin
		User userAdmin = new User();
		userAdmin.setUserId(Long.valueOf(0));
		userAdmin.setUserName("admin");
		userAdmin.setPassword(client.encode("Password"));
		userAdmin.setEmail("admin@gmail.com");
		userAdmin.setEnabled(true);
		
		User saved = null;
		try {
			saved = service.addUser(userAdmin);
		} catch (Exception e) {
			System.out.println("Exception: ---> [" + e + "]");
		}
		
		UserRole role = null;
		
		if (saved != null) {
			role = new UserRole();
			role.setRole("ROLE_ADMIN");
			role.setUserId(saved.getUserId());
			role.setUserRoleId(Long.valueOf(0));
			
			service.addUserRole(role);
		}
		
		//add user
		User user = new User();
		user.setUserId(Long.valueOf(0));
		user.setUserName("user");
		user.setPassword(client.encode("Password"));
		user.setEmail("user@gmail.com");
		user.setEnabled(true);
		
		try {
			saved = service.addUser(user);
		} catch (Exception e) {
			System.out.println("Exception: ---> [" + e + "]");
		}
		
		if (saved != null) {
			role = new UserRole();
			role.setRole("ROLE_USER");
			role.setUserId(saved.getUserId());
			role.setUserRoleId(Long.valueOf(0));
			
			service.addUserRole(role);
		}

	
		
	}

}
