package ${package}.service;

import java.util.List;

import ${package}.entities.security.Permission;
import ${package}.entities.security.Role;
import ${package}.entities.security.RoleFilter;
import org.springframework.transaction.annotation.Transactional;

public interface RoleService extends Service {

	@Transactional(readOnly = true)
	List<Role> getList(Role example);

	List<Permission> getAllPermissions();

	@Transactional
	void saveRole(Role editedRole);

	@Transactional
	void deleteRole(Role editedRole);

	List<Role> getList(RoleFilter filter);

}
