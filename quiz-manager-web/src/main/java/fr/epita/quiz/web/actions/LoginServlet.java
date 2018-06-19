/**
 * 
 */
package fr.epita.quiz.web.actions;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epita.quiz.datamodel.Login;
import fr.epita.quiz.services.AuthenticationService;

/**
 * @author ADEBOWALE
 *
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet  extends SpringServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	AuthenticationService auth;

	/**
	 * Default constructor.
	 */
	public LoginServlet() {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String email = request.getParameter("login");
		final String password = request.getParameter("password");
		System.out.println("login : " + email);
		System.out.println("password" + password);
		
		Login login = new Login(email, password);
		
		final boolean authenticated = auth.authenticate(login);
		request.getSession().setAttribute("authenticated", authenticated);
		request.getSession().setAttribute("userName", login);

		if(authenticated)
		response.sendRedirect("homePage.jsp");
		
		else
			response.sendRedirect("error.jsp");
	}

}
