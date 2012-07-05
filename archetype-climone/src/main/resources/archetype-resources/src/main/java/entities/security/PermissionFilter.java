package ${package}.entities.security;

import ${package}.entities.EntityFilter;

public class PermissionFilter extends EntityFilter<Permission> {
	private static final long serialVersionUID = 3143238145094066214L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
