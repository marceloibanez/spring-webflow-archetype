package ${package}.entities.security;

import ${package}.entities.EntityFilter;

public class UserFilter extends EntityFilter<User> {

	private static final long serialVersionUID = 7903632381338263891L;

	private String name;

	private String username;

	private String email;

	private RoleFilter role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RoleFilter getRole() {
		return role;
	}

	public void setRole(RoleFilter role) {
		this.role = role;
	}

}
