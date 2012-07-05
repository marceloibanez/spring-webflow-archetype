package ${package}.entities.security;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ForeignKey;
import ${package}.entities.IdentificableEntity;
import ${package}.entities.MailEntity;

@Entity
public class User extends IdentificableEntity implements MailEntity{

	private static final long serialVersionUID = -2778827691900581276L;

	@Column(length = 255)
	private String firstName;

	@Column(length = 255)
	private String lastName;

	@Column(length = 255)
	private String username;

	@Column(length = 255)
	private String password;

	@Column(length = 255)
	private String email;

	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@ForeignKey(name = "fk_user_role_user", inverseName = "fk_user_role_role")
	private Set<Role> roles;

	public User() {
		super();
	}

	public String getCompleteName() {
		return lastName + ", " + firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", uid=" + uid + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", roles="
				+ roles + "]";
	}

}
