package br.ufal.ic.iface_profile.repository.interfaces;

import java.util.List;

public interface RepositoryInterface<T, ID> {
	public T findById(ID id);

	public List<T> findAll();

	public T save(T entity);
	
	public T update(T entity);

	public void delete(T entity);

	public void clear();
}
