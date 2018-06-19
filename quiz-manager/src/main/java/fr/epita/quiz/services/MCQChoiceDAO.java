/**
 * Ce fichier est la propriété de Thomas BROUSSARD
 * Code application :
 * Composant :
 */
package fr.epita.quiz.services;

import java.util.LinkedHashMap;

import javax.inject.Inject;
import javax.persistence.Query;

import org.hibernate.Session;

import fr.epita.quiz.datamodel.MCQChoice;
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
public class MCQChoiceDAO extends GenericORMDao<MCQChoice> {

	@Inject 
	String deleteAllMCQ;

	/*
	 * (non-Javadoc)
	 * @see fr.epita.quiz.services.GenericORMDao#getWhereClauseBuilder(java.lang.Object)
	 */
//	@Override
//	protected WhereClauseBuilder<MCQChoice> getWhereClauseBuilder(MCQChoice entity) {
//		final WhereClauseBuilder<MCQChoice> whereClauseBuilder = new WhereClauseBuilder<>();
//		whereClauseBuilder.setParameters(new LinkedHashMap<>());
//
//		// TODO : load from configuration
//		final String query = "from MCQChoice";
//
//		whereClauseBuilder.setQueryString(query);
//		return whereClauseBuilder;
//
//	}

	/*
	 * (non-Javadoc)
	 * @see fr.epita.quiz.services.GenericORMDao#beforeCreate(java.lang.Object)
	 */
	@Override
	public boolean beforeCreate(MCQChoice entity) {
		return entity.getChoice() != null && entity.getQuestion() != null;

	}
	
	public boolean deleteAllMCQs(Question question){
		
		final Session session = sf.openSession();
		Query query = session.createQuery("delete from MCQChoice where question:question");
		
		query.setParameter("question", question);
		
		int result = query.executeUpdate();
		
		return result>=1;
		
	}

}
