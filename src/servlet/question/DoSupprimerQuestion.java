package servlet.question;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Question;
import dal.DALException;
import dal.DAOFactory;

public class DoSupprimerQuestion extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (String.valueOf(session.getAttribute("collaborateur")).equals("true")) {
			try {
				List<Question> listeQuestions = DAOFactory.getQuestionDAO().selectAll();
				req.setAttribute("listeQuestions", listeQuestions);
			} catch (DALException e) {
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher("/collaborateur/question/removeQuestion.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("idQuestion"));
			DAOFactory.getQuestionDAO().delete(id);
			resp.sendRedirect("/ProjetQCM/validerAcces");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

}
