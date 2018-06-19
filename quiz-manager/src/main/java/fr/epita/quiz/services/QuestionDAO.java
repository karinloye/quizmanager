/**
 * Ce fichier est la propriété de Thomas BROUSSARD
 * Code application :
 * Composant :
 */
package fr.epita.quiz.services;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import fr.epita.quiz.datamodel.Question;

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
public class QuestionDAO extends GenericORMDao<Question> {
	


	@Inject
	@Named("questionQuery")
	String query;

//	@Override
//	protected WhereClauseBuilder<Question> getWhereClauseBuilder(Question entity) {
//		final WhereClauseBuilder<Question> wcb = new WhereClauseBuilder<>();
//		wcb.setQueryString(query);
//
//		// TODO as bonus : let the whereclausebuilder generate this map thanks to introspection
//		final Map<String, Object> parameters = new LinkedHashMap<>();
//		parameters.put("type", entity.getType());
//		parameters.put("question", entity.getQuestion());
//		wcb.setParameters(parameters);
//		return wcb;
//
//	}

	// @Override
	// protected String getSearchQuery(Question question) {
	// return query;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see fr.epita.quiz.services.GenericHibernateDao#completeQuery(org.hibernate.query.Query)
	// */
	// @Override
	// protected void completeQuery(Question question, Query toBeCompleted) {
	//
	// toBeCompleted.setParameter("type", question.getType());
	// toBeCompleted.setParameter("question", question.getQuestion());
	// }

	/*
	 * (non-Javadoc)
	 * @see fr.epita.quiz.services.GenericHibernateDao#getWhereClauseBuilder(java.lang.Object)
	 */

}
