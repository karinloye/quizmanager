/**
 * Ce fichier est la propriété de Thomas BROUSSARD
 * Code application :
 * Composant :
 */
package fr.epita.quiz.services;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.Login;

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
public class AuthenticationService {
	
	@Inject
	private LoginDAO loginDao;
	
	public boolean authenticate(Login login) {
		
		return loginDao.search(login).size()>=1;
	}

}
