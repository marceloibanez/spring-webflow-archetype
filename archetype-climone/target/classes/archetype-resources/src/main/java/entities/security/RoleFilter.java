package ${package}.entities.security;

import ${package}.entities.EntityFilter;

public class RoleFilter extends EntityFilter<Role> {

	private static final long serialVersionUID = -136198313817026850L;

	private String name;
	private String description;
	private PermissionFilter permission;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PermissionFilter getPermission() {
		return permission;
	}

	public void setPermission(PermissionFilter permission) {
		this.permission = permission;
	}
}
