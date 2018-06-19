/**
 * Ce fichier est la propriété de Thomas BROUSSARD
 * Code application :
 * Composant :
 */
package fr.epita.quiz.services;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Repository;

/**
 * <h3>Description</h3>
 * <p>This class allows to ...</p>
 *
 * <h3>Usage</h3>
 * <p>This class should be used as follows:
 *   <pre><code>${type_name} instance = new ${type_name}();</code></pre>
 * </p>
 *
 * @since $${version}
 * @see See also $${link}
 * @author ${user}
 *
 * ${tags}
 */
@Repository
public abstract class GenericORMDao<T> {

	@Inject
	SessionFactory sf;

	public final void create(T entity) {
		if (!beforeCreate(entity)) {
			return;
		}

		final Session session = sf.openSession();
		session.saveOrUpdate(entity);

	}

	protected boolean beforeCreate(T entity) {
		return entity != null;
	}

	public final void update(T entity) {
		final Session session = sf.openSession();
		session.saveOrUpdate(entity);
	}

	public final void delete(T entity) {
		final Session session = sf.openSession();
		session.delete(entity);
	}

	public final List<T> search(T entity) {
		final Session session = sf.openSession();
		final WhereClauseBuilder<T> wcb = new WhereClauseBuilder<>();
		
		
		wcb.setQueryString(generateQuery(entity));
		wcb.setParameters(linkedListBuilder(entity));
		
		final Query searchQuery = session.createQuery(wcb.getQueryString());
		for (final Entry<String, Object> parameterEntry : wcb.getParameters().entrySet()) {
			searchQuery.setParameter(parameterEntry.getKey(), parameterEntry.getValue());
		}

		return searchQuery.list();
	}

	/**
	 * @param entity
	 * @return
	 */
	public Map linkedListBuilder(T entity) {

		final BeanWrapper sourceBean = new BeanWrapperImpl(entity.getClass());
		final PropertyDescriptor[] propertyDescriptors = sourceBean.getPropertyDescriptors();
		final Map<String, Object> parameters = new LinkedHashMap<>();

		for (final PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			Method method = propertyDescriptor.getReadMethod();

			try {

				Object o = method.invoke(entity);

				if (o != null && !method.getName().contains("Class")) {
					parameters.put(propertyDescriptor.getDisplayName(), o);
					System.out.println("Inserting Key: " + propertyDescriptor.getName() + " with Value: " + o);
				}

			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return parameters;
	}
	
	public String generateQuery(T entity) {
		final BeanWrapper sourceBean = new BeanWrapperImpl(entity.getClass());
		final PropertyDescriptor[] propertyDescriptors = sourceBean.getPropertyDescriptors();
		int i = 0;

		String simpleName = entity.getClass().getSimpleName();
		String baseName = "from " + simpleName + " as " + simpleName.toLowerCase() + " where ";

		for (final PropertyDescriptor propertyDescriptor : propertyDescriptors) {

			if (propertyDescriptor.getName() != null && !propertyDescriptor.getName().contains("class")) {
				baseName += createQueryString(simpleName, propertyDescriptor.getName(), i);
				i++;
			}
		}
		return baseName;
	}

	public String createQueryString(String simpleName, String name, int i) {
		String str = null;
		
		if(i == 0)
		{
			str = simpleName.toLowerCase() + "." + name + " = :" + name;
		}
		
		else{
			str = " and "+simpleName.toLowerCase() + "." + name + " = :" + name;
		}
		return str;

	}

	public SessionFactory getSf() {
		return sf;
	}
	
}
