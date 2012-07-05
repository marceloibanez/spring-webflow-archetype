package ${package}.security;

import org.springframework.security.core.GrantedAuthority;

public class PermissionAuthorityWrapper implements GrantedAuthority {

	private static final long serialVersionUID = -1532907225206841171L;
	private static final String ROLE_PREFIX = "ROLE_";

	private final String permissionName;

	public PermissionAuthorityWrapper(final String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionName() {
		return permissionName;
	}

	@Override
	public String getAuthority() {
		return ROLE_PREFIX + permissionName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((permissionName == null) ? 0 : permissionName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PermissionAuthorityWrapper)) {
			return false;
		}
		PermissionAuthorityWrapper other = (PermissionAuthorityWrapper) obj;
		if (permissionName == null) {
			if (other.permissionName != null) {
				return false;
			}
		} else if (!permissionName.equals(other.permissionName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "PermissionAuthorityWrapper [permissionName=" + permissionName
				+ "]";
	}

}
