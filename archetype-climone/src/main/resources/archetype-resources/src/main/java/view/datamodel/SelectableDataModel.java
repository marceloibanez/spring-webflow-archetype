package ${package}.view.datamodel;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.DataModel;

public class SelectableDataModel<T> extends DataModel<SelectableWrapper<T>>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6377925451723972430L;
	private List<T> wrappedData;
	private int rowIndex;
	private final SelectionDataModelListener<T> listener;

	public SelectableDataModel(List<T> data) {
		this(data, null);
	}

	public SelectableDataModel(List<T> data,
			SelectionDataModelListener<T> listener) {
		this.wrappedData = data;
		this.listener = listener;
		this.rowIndex = -1;
	}

	@Override
	public int getRowCount() {
		int result = 0;

		if (null != wrappedData) {
			result = wrappedData.size();
		}

		return result;
	}

	@Override
	public SelectableWrapper<T> getRowData() {
		SelectableWrapper<T> result = null;

		if (null != wrappedData) {
			result = this.getWrapper(wrappedData.get(rowIndex));
		}
		return result;
	}

	@Override
	public int getRowIndex() {
		return rowIndex;
	}

	@Override
	public List<T> getWrappedData() {
		return wrappedData;
	}

	@Override
	public boolean isRowAvailable() {
		return rowIndex != -1 && rowIndex < wrappedData.size();
	}

	@Override
	public void setRowIndex(int index) {
		this.rowIndex = index;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setWrappedData(Object listData) {
		this.wrappedData = (List<T>) listData;
	}

	public void notifyListener(SelectionDataModelEvent<T> event) {
		if (null != listener) {
			if (event.isSelected()) {
				listener.rowSelected(event);
			} else {
				listener.rowUnselected(event);
			}
		}
	}

	private SelectableWrapper<T> getWrapper(T source) {
		SelectableWrapper<T> selectableWrapper = null;

		selectableWrapper = new SelectableWrapper<T>(source, this);

		if (null != listener) {
			selectableWrapper
					.setStealthSelected(listener
							.isRowSelected(new SelectionDataModelEvent<T>(
									source, false)));
		}

		return selectableWrapper;
	}

	public SelectionDataModelListener<T> getListener() {
		return listener;
	}
}
