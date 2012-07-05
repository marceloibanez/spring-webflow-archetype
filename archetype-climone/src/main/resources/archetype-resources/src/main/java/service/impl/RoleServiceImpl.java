package ${package}.service.impl;

import java.util.List;

import ${package}.dao.PermissionDao;
import ${package}.dao.RoleDao;
import ${package}.entities.security.Permission;
import ${package}.entities.security.PermissionFilter;
import ${package}.entities.security.Role;
import ${package}.entities.security.RoleFilter;
import ${package}.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl extends AbstractService implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public List<Role> getList(Role example) {
		return roleDao.getList(new RoleFilter());
	}

	@Override
	public List<Permission> getAllPermissions() {
		return permissionDao.getList(new PermissionFilter());
	}

	@Override
	public void saveRole(Role editedRole) {
		roleDao.save(editedRole);
	}

	@Override
	public void deleteRole(Role editedRole) {
		roleDao.delete(editedRole);
	}

	@Override
	public List<Role> getList(RoleFilter filter) {
		return roleDao.getList(filter);
	}

}
