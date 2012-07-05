package ${package}.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public abstract class EntityFilter<T> implements Serializable{

	private static final long serialVersionUID = 1096996033538138632L;
	private List<SortCriteria> sortCriterias;
	private List<String> fetchList;

	public EntityFilter<T> addSort(final SortCriteria sort) {
		this.sortCriterias.add(sort);
		return this;
	}

	public EntityFilter<T> ddFetch(String fetch) {
		this.fetchList.add(fetch);
		return this;
	}

	public List<SortCriteria> getSortCriterias() {
		return Collections.unmodifiableList(sortCriterias);
	}

	public List<String> getFetchList() {
		return Collections.unmodifiableList(fetchList);
	}
}
