package ua.lviv.trainapplogos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.trainapplogos.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	public User findByUserName(String userName);
}
