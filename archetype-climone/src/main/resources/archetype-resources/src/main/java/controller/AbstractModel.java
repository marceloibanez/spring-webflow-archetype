package ${package}.controller;

import java.io.Serializable;

public abstract class AbstractModel implements Serializable {

	private static final long serialVersionUID = 8873499141448716342L;

	protected UseCaseMode mode;
	protected Integer pageNumber;
	
	protected String email;
	

	public boolean isNewMode() {
		return UseCaseMode.NEW.equals(mode);
	}

	public boolean isConsultMode() {
		return UseCaseMode.CONSULT.equals(mode);
	}

	public boolean isSearchMode() {
		return UseCaseMode.SEARCH.equals(mode);
	}

	public boolean isEditMode() {
		return UseCaseMode.EDIT.equals(mode);
	}

	public UseCaseMode getMode() {
		return mode;
	}

	public void setMode(UseCaseMode mode) {
		this.mode = mode;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
