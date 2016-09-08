package br.ufal.ic.iface_profile.repository.classes;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufal.ic.iface_profile.repository.interfaces.RepositoryInterface;


@Repository
@Transactional
public abstract class GenericHibernateRepository<T, ID extends Serializable> implements RepositoryInterface<T, ID> {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Class<T> persistentClass;
	private Session session;

	@SuppressWarnings("unchecked")
	public GenericHibernateRepository() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void setSession(Session s) {
		this.session = s;
	}

	protected Session getSession() {
		if (this.session == null || !this.session.isOpen()) {

			this.session = this.sessionFactory.getCurrentSession();
			if (this.session == null) {
				this.session = this.sessionFactory.openSession();
			}

			this.session = this.sessionFactory.getCurrentSession();
		}
		return session;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		T entity = (T) getSession().get(persistentClass, id);
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getSession().createCriteria(persistentClass).list();
	}

	public T save(T entity) {
		getSession().save(entity);
		return entity;
	}
	
	public T update(T entity) {
		getSession().update(entity);
		return entity;
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

}