package ${package}.dao;

import ${package}.entities.security.User;

public interface UserDao extends Dao<User> {

	User getUserByUsername(String username);

}
