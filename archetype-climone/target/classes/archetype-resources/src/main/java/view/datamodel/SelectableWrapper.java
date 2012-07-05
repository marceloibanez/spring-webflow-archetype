package ${package}.view.datamodel;

public class SelectableWrapper<T> {

	private T wrapped;
	private boolean selected;
	private SelectableDataModel<T> dataModel;

	public SelectableWrapper(T wrapped, SelectableDataModel<T> dataModel) {
		this.wrapped = wrapped;
		this.dataModel = dataModel;
	}

	public void setStealthSelected(boolean value) {
		this.selected = value;
	}

	public void setSelected(boolean value) {
		this.selected = value;
		SelectionDataModelEvent<T> event = new SelectionDataModelEvent<T>(
				wrapped, selected);
		dataModel.notifyListener(event);
	}

	public boolean isSelected() {
		return selected;
	}

	public T getWrapped() {
		return wrapped;
	}

	public void setWrapped(T wrapped) {
		this.wrapped = wrapped;
	}
}
