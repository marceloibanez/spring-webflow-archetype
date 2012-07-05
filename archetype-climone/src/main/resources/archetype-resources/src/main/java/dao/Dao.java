package ${package}.dao;

import java.io.Serializable;
import java.util.List;

import ${package}.entities.EntityFilter;

public interface Dao<T> {
	
	public T getById(Serializable id);

	public int getCount(EntityFilter<T> filter);
	
	public List<T> getList(EntityFilter<T> filter);
	
	public List<T> getList(EntityFilter<T> filter, int from, int quantity);

	public void save(T obj);

	public void delete(T obj);
}
