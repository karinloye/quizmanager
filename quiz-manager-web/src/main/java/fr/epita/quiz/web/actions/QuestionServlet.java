/**
 * 
 */
package fr.epita.quiz.web.actions;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.QuestionType;
import fr.epita.quiz.services.MCQChoiceDAO;
import fr.epita.quiz.services.QuestionDAO;
import fr.epita.quiz.web.constants.Constants;

/**
 * @author ADEBOWALE
 *
 */
public class QuestionServlet extends SpringServlet {

	private static final long serialVersionUID = -3131943414389127397L;
	@Inject
	QuestionDAO questionDAO;
	
	@Inject
	MCQChoiceDAO mcqDAO;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if ((req.getRequestURL() + "").contains(Constants.CREATE_OPERATION)) {
			createData(req, resp);
		} else if ((req.getRequestURL() + "").contains(Constants.SEARCH_OPERATION)) {
			searchData(req, resp);
		} else if ((req.getRequestURL() + "").contains(Constants.UPDATE_OPERATION)) {
			updateData(req, resp);
		} else if ((req.getRequestURL() + "").contains(Constants.DELETE_OPERATION)) {
			deleteData(req, resp);
		}

	}

	private void deleteData(HttpServletRequest req, HttpServletResponse resp) {

		Question question = new Question();

		question.setQuestion(req.getParameter("questionString"));
		question.setType(type(req));
		if(question.getType()!=null) {
			questionDAO.delete(question);
			mcqDAO.deleteAllMCQs(question);
		}
	

	}

	private void updateData(HttpServletRequest req, HttpServletResponse resp) {

		Question question = new Question();

		question.setQuestion(req.getParameter("questionString"));
		question.setType(type(req));

		questionDAO.search(question);

		List<Question> questionList = questionDAO.search(question);

		Question fetchedQuestion = questionList.get(0);

		if (!fetchedQuestion.getQuestion().equalsIgnoreCase(req.getParameter("questionString"))
				|| !fetchedQuestion.getType().toString().equalsIgnoreCase(req.getParameter("type"))) {

			question.setQuestion(req.getParameter("questionString"));
			question.setType(type(req));
			if (question.getType() != null)
				questionDAO.update(question);
		}

		// mcq update

		// HttpSession session= req.getSession();
		// session.setAttribute("", value);
		//
		// resp.sendRedirect("");;
	}

	private void searchData(HttpServletRequest req, HttpServletResponse resp) {

		Question question = new Question();
		question.setQuestion(req.getParameter("questionString"));

		question.setType(type(req));
		questionDAO.search(question);
	}

	private void createData(HttpServletRequest req, HttpServletResponse resp) {
		Question question = new Question();

		question.setQuestion(req.getParameter("questionString"));

		question.setType(type(req));

		if (question.getType() != null)
			questionDAO.create(question);
	}

	private QuestionType type(HttpServletRequest req) {
		if (req.getParameter("type").contains("OPEN")) {
			return QuestionType.OPEN;
		} else if (req.getParameter("type").contains("MCQ")) {
			return QuestionType.MCQ;
		} else if (req.getParameter("type").contains("ASSOCIATIVE")) {
			return QuestionType.ASSOCIATIVE;
		}
		return null;
	}

}
