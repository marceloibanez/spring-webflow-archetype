package ${package}.controller.administration;

import java.util.HashSet;
import java.util.List;

import ${package}.controller.AbstractController;
import ${package}.controller.UseCaseMode;
import ${package}.entities.security.Role;
import ${package}.entities.security.User;
import ${package}.entities.security.UserFilter;
import ${package}.service.MailService;
import ${package}.service.UserService;
import ${package}.view.datamodel.DefaultSelectionDataModelListener;
import ${package}.view.datamodel.ListDataModel;
import ${package}.view.datamodel.SelectableDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("userController")
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserModel userModel;
	
	@Autowired
	private MailService<User> mailConfirmationService;

	public void init() {
		userModel.setFilter(new UserFilter());
		userModel.setMode(UseCaseMode.SEARCH);
	}

	public void search() {	
		List<User> userList = userService.getList(userModel.getFilter());
		userModel.setUserList(new ListDataModel<User>(userList));
		userModel.setPageNumber(1);		
	}

	public void prepareNew() {
		userModel.setMode(UseCaseMode.NEW);
		User editedUser = new User();
		editedUser.setRoles(new HashSet<Role>());
		userModel.setEditedUser(editedUser);
		userModel.setFirstName(null);
		userModel.setLastName(null);
		userModel.setEmail(null);
		userModel.setUsername(null);
		userModel.setPassword(null);

		final DefaultSelectionDataModelListener<Role> selectionListener = new DefaultSelectionDataModelListener<Role>(
				new HashSet<Role>());
		final SelectableDataModel<Role> permissionDataModel = new SelectableDataModel<Role>(
				userService.getAllRoles(), selectionListener);
		userModel.setRolesDataModel(permissionDataModel);
		userModel.setRolesSelectionListener(selectionListener);

	}

	public void prepareEdit() {
		userModel.setMode(UseCaseMode.EDIT);

		User editedUser = userModel.getEditedUser();

		userModel.setFirstName(editedUser.getFirstName());
		userModel.setLastName(editedUser.getLastName());
		userModel.setEmail(editedUser.getEmail());
		userModel.setUsername(editedUser.getUsername());
		userModel.setPassword(editedUser.getPassword());
		DefaultSelectionDataModelListener<Role> selectionListener = new DefaultSelectionDataModelListener<Role>(
				new HashSet<Role>(editedUser.getRoles()));
		SelectableDataModel<Role> permissionDataModel = new SelectableDataModel<Role>(
				userService.getAllRoles(), selectionListener);
		userModel.setRolesDataModel(permissionDataModel);
		userModel.setRolesSelectionListener(selectionListener);

	}

	public void delete() {
		userService.deleteUser(userModel.getEditedUser());
		this.search();
	}

	public void confirmSave() {
		User editedUser = userModel.getEditedUser();
		editedUser.setFirstName(userModel.getFirstName());
		editedUser.setLastName(userModel.getLastName());
		editedUser.setEmail(userModel.getEmail());
		editedUser.setUsername(userModel.getUsername());
		if (userModel.isNewMode()) {
			editedUser.setPassword(userModel.getPassword());
		}

		editedUser.getRoles().clear();
		editedUser.getRoles().addAll(
				userModel.getRolesSelectionListener().getSelectedElements());
		userService.saveUser(editedUser, userModel.isNewMode());

		mailConfirmationService.sendEmail(editedUser);
	}

	public void prepareResetPassword() {
		userModel.setPassword(null);
		userModel.setConfirmPassword(null);
	}

	public void confirmResetPassword() {
		User editedUser = userModel.getEditedUser();
		editedUser.setPassword(userModel.getPassword());
		userService.saveUser(editedUser, userModel.isNewMode());
	}
}
