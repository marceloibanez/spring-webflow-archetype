package ${package}.controller.administration;

import javax.faces.model.DataModel;

import ${package}.controller.AbstractModel;
import ${package}.entities.security.Permission;
import ${package}.entities.security.Role;
import ${package}.entities.security.RoleFilter;
import ${package}.view.datamodel.DefaultSelectionDataModelListener;
import ${package}.view.datamodel.SelectableDataModel;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;

@Controller("roleModel")
@Scope(value = "flow", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RoleModel extends AbstractModel {

	private static final long serialVersionUID = -835255549908549635L;
	private RoleFilter filter;
	private DataModel<Role> roleList;
	private Role editedRole;

	private String name;
	private String description;
	private SelectableDataModel<Permission> permissionsDataModel;
	private DefaultSelectionDataModelListener<Permission> permissionSelectionListener;

	public RoleFilter getFilter() {
		return filter;
	}

	public void setFilter(RoleFilter filter) {
		this.filter = filter;
	}

	public DataModel<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(DataModel<Role> roleList) {
		this.roleList = roleList;
	}

	public Role getEditedRole() {
		return editedRole;
	}

	public void setEditedRole(Role editedRole) {
		this.editedRole = editedRole;
	}

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

	public SelectableDataModel<Permission> getPermissionsDataModel() {
		return permissionsDataModel;
	}

	public void setPermissionsDataModel(SelectableDataModel<Permission> permissionsDataModel) {
		this.permissionsDataModel = permissionsDataModel;
	}

	public DefaultSelectionDataModelListener<Permission> getPermissionSelectionListener() {
		return permissionSelectionListener;
	}

	public void setPermissionSelectionListener(DefaultSelectionDataModelListener<Permission> permissionSelectionListener) {
		this.permissionSelectionListener = permissionSelectionListener;
	}

}
