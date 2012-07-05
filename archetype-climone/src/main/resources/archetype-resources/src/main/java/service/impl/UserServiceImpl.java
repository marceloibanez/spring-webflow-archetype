package ${package}.service.impl;

import java.util.List;

import ${package}.dao.RoleDao;
import ${package}.dao.UserDao;
import ${package}.entities.security.Role;
import ${package}.entities.security.RoleFilter;
import ${package}.entities.security.User;
import ${package}.entities.security.UserFilter;
import ${package}.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends AbstractService implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleDao.getList(new RoleFilter());
	}

	@Override
	public void saveUser(User user, boolean encodePassword) {
		if (encodePassword) {
			final String encodedPassword = passwordEncoder.encodePassword(user.getPassword(), user.getUid());
			user.setPassword(encodedPassword);
		}
		userDao.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@Override
	public List<User> getList(UserFilter filter) {
		return userDao.getList(filter);
	}
}
