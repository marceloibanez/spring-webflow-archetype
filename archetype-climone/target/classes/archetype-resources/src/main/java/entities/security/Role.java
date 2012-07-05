package ${package}.entities.security;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ForeignKey;
import ${package}.entities.IdentificableEntity;

@Entity
public class Role extends IdentificableEntity {

	private static final long serialVersionUID = 4003755335812490960L;

	@Column(length = 60)
	private String name;

	@Column(length = 1024)
	private String description;

	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "per_name"))
	@ForeignKey(name = "fk_role_perm_role", inverseName = "fk_role_perm_perm")
	private Set<Permission> permissions;

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

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", uid=" + uid + ", name=" + name
				+ ", description=" + description + ", permissions="
				+ permissions + "]";
	}
}
