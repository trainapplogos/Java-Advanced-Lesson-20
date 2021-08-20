package ua.lviv.trainapplogos.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.lviv.trainapplogos.domain.User;
import ua.lviv.trainapplogos.domain.UserRole;
import ua.lviv.trainapplogos.repository.UserRepository;
import ua.lviv.trainapplogos.repository.UserRolesRepository;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;
	private final UserRolesRepository userRolesRepository;
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepository, UserRolesRepository userRolesRepository) {
		this.userRepository = userRepository;
		this.userRolesRepository = userRolesRepository;
	}
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (null == user) {
			throw new UsernameNotFoundException("No user present with username: " + username);
		} else {
			List<String> userRoles = userRolesRepository.findRoleByUserName(username);
			return new CustomUserDetails(user, userRoles);
		}
	}
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public UserRole addUserRole(UserRole role) {
		return userRolesRepository.save(role);
	}

}
