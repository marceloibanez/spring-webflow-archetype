package ${package}.service;

import java.util.List;

import ${package}.entities.security.Role;
import ${package}.entities.security.User;
import ${package}.entities.security.UserFilter;
import org.springframework.transaction.annotation.Transactional;

public interface UserService extends Service {

	User getUserByUsername(final String username);

	List<Role> getAllRoles();

	List<User> getList(UserFilter filter);
	
	@Transactional
	void saveUser(User user, boolean encodePassword);

	@Transactional
	void deleteUser(User user);
}
