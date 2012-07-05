package ${package}.view.datamodel;

import java.io.Serializable;
import java.util.Set;

public class DefaultSelectionDataModelListener<T> implements
		SelectionDataModelListener<T>, Serializable {

	private static final long serialVersionUID = -2295181896998078746L;
	private final Set<T> selectedElements;

	/**
	 * @param roleController
	 */
	public DefaultSelectionDataModelListener(final Set<T> selectedElements) {
		this.selectedElements = selectedElements;
	}

	@Override
	public void rowUnselected(SelectionDataModelEvent<T> event) {
		selectedElements.remove(event.getSource());
	}

	@Override
	public void rowSelected(SelectionDataModelEvent<T> event) {
		selectedElements.add(event.getSource());
	}

	@Override
	public boolean isRowSelected(SelectionDataModelEvent<T> event) {
		return selectedElements.contains(event.getSource());
	}

	public Set<T> getSelectedElements() {
		return selectedElements;
	}

}
