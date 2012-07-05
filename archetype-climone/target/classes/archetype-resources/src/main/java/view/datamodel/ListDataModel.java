package ${package}.view.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.DataModelEvent;
import javax.faces.model.DataModelListener;

import org.springframework.util.Assert;

public class ListDataModel<T> extends DataModel<T> implements
		Serializable {

	private static final long serialVersionUID = 1320342101378730664L;

	protected int rowIndex = 0;

	protected List<T> data;

	public ListDataModel() {
		this(new ArrayList<T>());
	}

	/**
	 * Adapt the list to a data model;
	 * 
	 * @param list
	 *            the list
	 */
	public ListDataModel(List<T> list) {
		if (list == null) {
			list = new ArrayList<T>();
		}
		setWrappedData(list);
	}

	public int getRowCount() {
		return data.size();
	}

	public T getRowData() {
		Assert.isTrue(
				isRowAvailable(),
				getClass()
						+ " is in an illegal state - no row is available at the current index.");
		return data.get(rowIndex);
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public Object getWrappedData() {
		return data;
	}

	public boolean isRowAvailable() {
		return rowIndex >= 0 && rowIndex < data.size();
	}

	public void setRowIndex(int newRowIndex) {
		if (newRowIndex < -1) {
			throw new IllegalArgumentException("Illegal row index for "
					+ getClass() + ": " + newRowIndex);
		}
		int oldRowIndex = rowIndex;
		rowIndex = newRowIndex;
		if (data != null && oldRowIndex != rowIndex) {
			Object row = isRowAvailable() ? getRowData() : null;
			DataModelEvent event = new DataModelEvent(this, rowIndex, row);
			DataModelListener[] listeners = getDataModelListeners();
			for (int i = 0; i < listeners.length; i++) {
				listeners[i].rowSelected(event);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void setWrappedData(Object data) {
		if (data == null) {
			data = new ArrayList<T>();
		}
		Assert.isInstanceOf(List.class, data, "The data object for "
				+ getClass() + " must be a List");
		this.data = (List<T>) data;
		int newRowIndex = 0;
		setRowIndex(newRowIndex);
	}

	public String toString() {
		return data.toString();
	}

}
