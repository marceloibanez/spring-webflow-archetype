package ${package}.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import ${package}.entities.security.Permission;
import ${package}.entities.security.Role;
import ${package}.entities.security.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsWrapper implements UserDetails {

	private static final long serialVersionUID = 7046137609122509747L;
	private final User user;
	private final Collection<GrantedAuthority> authorities;

	public UserDetailsWrapper(User user) {
		super();
		this.user = user;

		Set<Role> roles = this.user.getRoles();

		this.authorities = new HashSet<GrantedAuthority>();

		for (Role role : roles) {
			Set<Permission> permissions = role.getPermissions();
			for (Permission permission : permissions) {
				authorities.add(new PermissionAuthorityWrapper(permission
						.getName()));
			}
		}
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	public String getCompleteName() {
		return user.getLastName() + "," + user.getFirstName();
	}

	public String getUid() {
		return user.getUid();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
