package ua.lviv.trainapplogos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.trainapplogos.domain.UserRole;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {

	@Query("SELECT a.role FROM UserRole a, User b WHERE b.userName = ?1 AND a.userId = b.userId")
	public List<String> findRoleByUserName(String userName);
}
