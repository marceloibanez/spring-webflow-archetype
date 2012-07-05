package ${package}.view.datamodel;

public class SelectionDataModelEvent<T> {

	private final T source;
	private final boolean selected;

	public SelectionDataModelEvent(T source, boolean selected) {
		this.selected = selected;
		this.source = source;
	}

	public T getSource() {
		return source;
	}

	public boolean isSelected() {
		return selected;
	}
}
