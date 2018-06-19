/**
 * Ce fichier est la propriété de Thomas BROUSSARD
 * Code application :
 * Composant :
 */
package fr.epita.quiz.tests;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.QuestionType;
import fr.epita.quiz.services.MCQChoiceDAO;
import fr.epita.quiz.services.QuestionDAO;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestMCQAndQuestion {

	@Inject
	QuestionDAO questDAO;

	@Inject
	MCQChoiceDAO mcqDAO;

	@Inject
	SessionFactory factory;

	@Test
	public void testSave() {
		final Session session = factory.openSession();
		final Transaction tx = session.beginTransaction();
		final Question question = new Question();
		question.setQuestion("How to configure Hibernate?");
		question.setType(QuestionType.MCQ);

		questDAO.create(question);

		final MCQChoice choice = new MCQChoice();
		choice.setValid(true);
		choice.setChoice("thanks to a LocalSessionFactoryBean instance");
		choice.setOrder(0);
		choice.setQuestion(question);
	
		mcqDAO.create(choice);
		tx.commit();

	}

}
