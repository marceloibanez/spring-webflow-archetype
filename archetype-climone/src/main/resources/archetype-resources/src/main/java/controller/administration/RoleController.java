package ${package}.controller.administration;

import java.util.HashSet;
import java.util.List;

import javax.faces.model.DataModel;

import ${package}.entities.security.Permission;
import ${package}.entities.security.Role;
import ${package}.entities.security.RoleFilter;
import ${package}.service.RoleService;
import ${package}.view.datamodel.DefaultSelectionDataModelListener;
import ${package}.view.datamodel.ListDataModel;
import ${package}.view.datamodel.SelectableDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller("roleController")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleModel roleModel;

	public void init() {
		roleModel.setFilter(new RoleFilter());
	}

	public void search() {
		List<Role> roles = roleService.getList(roleModel.getFilter());

		DataModel<Role> dataModel = new ListDataModel<Role>(roles);

		roleModel.setRoleList(dataModel);
		roleModel.setPageNumber(1);
	}

	public void prepareNew() {
		Role editedRole = new Role();
		editedRole.setPermissions(new HashSet<Permission>());
		roleModel.setEditedRole(editedRole);
		roleModel.setName(null);
		roleModel.setDescription(null);

		final DefaultSelectionDataModelListener<Permission> selectionListener = new DefaultSelectionDataModelListener<Permission>(
				new HashSet<Permission>());
		final SelectableDataModel<Permission> permissionDataModel = new SelectableDataModel<Permission>(
				roleService.getAllPermissions(), selectionListener);
		roleModel.setPermissionsDataModel(permissionDataModel);
		roleModel.setPermissionSelectionListener(selectionListener);

	}

	public void prepareEdit() {
		Role editedRole = roleModel.getEditedRole();
		roleModel.setName(editedRole.getName());
		roleModel.setDescription(editedRole.getDescription());

		DefaultSelectionDataModelListener<Permission> selectionListener = new DefaultSelectionDataModelListener<Permission>(
				new HashSet<Permission>(editedRole.getPermissions()));
		SelectableDataModel<Permission> permissionDataModel = new SelectableDataModel<Permission>(
				roleService.getAllPermissions(), selectionListener);
		roleModel.setPermissionsDataModel(permissionDataModel);
		roleModel.setPermissionSelectionListener(selectionListener);

	}

	public void delete() {
		roleService.deleteRole(roleModel.getEditedRole());
		this.search();
	}

	@Transactional
	public void confirmSave() {
		Role editedRole = roleModel.getEditedRole();
		editedRole.setName(roleModel.getName());
		editedRole.setDescription(roleModel.getDescription());
		editedRole.getPermissions().clear();
		editedRole.getPermissions().addAll(
				roleModel.getPermissionSelectionListener()
						.getSelectedElements());
		roleService.saveRole(editedRole);
	}
}
