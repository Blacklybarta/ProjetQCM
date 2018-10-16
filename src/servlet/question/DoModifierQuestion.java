package servlet.question;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Promotion;
import bo.Proposition;
import bo.Question;
import bo.Theme;
import bo.Utilisateur;
import dal.DALException;
import dal.DAOFactory;

public class DoModifierQuestion extends HttpServlet {

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
			this.getServletContext().getRequestDispatcher("/collaborateur/question/updateQuestion.jsp").forward(req,
					resp);
		} else {
			req.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Question question = null;
		try {
			if (String.valueOf(req.getParameter("select")).equals("true")) {
				String[] values = req.getParameterValues("idQuestion");
				question = DAOFactory.getQuestionDAO().selectById(Integer.parseInt(values[0]));
				req.setAttribute("question", question);
				List<Theme> listeTheme = DAOFactory.getThemeDAO().selectAll();
				req.setAttribute("listeThemes", listeTheme);
				this.getServletContext().getRequestDispatcher("/collaborateur/question/updateQuestion.jsp").forward(req,
						resp);
			} else if (String.valueOf(req.getParameter("update")).equals("true")) {
				question = new Question();
				Theme theme = null;
				Proposition prop1 = new Proposition();
				Proposition prop2 = new Proposition();
				Proposition prop3 = new Proposition();
				Proposition prop4 = new Proposition();
				question.setEnonce(req.getParameter("enonce"));
				question.setPoints(Integer.parseInt(req.getParameter("points")));
				String[] values = req.getParameterValues("idTheme");
				theme = DAOFactory.getThemeDAO().selectById(Integer.parseInt(values[0]));
				question.setTheme(theme);
				question.setIdQuestion(Integer.parseInt(req.getParameter("id")));
				
				DAOFactory.getQuestionDAO().update(question);


				prop1.setEnonce(req.getParameter("proposition1"));
				prop1.setIdProposition(Integer.parseInt(req.getParameter("idProposition1")));
				String chk1 = req.getParameter("chkProposition1");
				if (chk1 != null) {
					prop1.setEstBonne(true);
				} else {
					prop1.setEstBonne(false);
				}
				prop1.setQuestion(question);
				DAOFactory.getPropositionDAO().update(prop1);

				prop2.setEnonce(req.getParameter("proposition2"));
				prop2.setIdProposition(Integer.parseInt(req.getParameter("idProposition2")));
				String chk2 = req.getParameter("chkProposition2");
				if (chk2 != null) {
					prop2.setEstBonne(true);
				} else {
					prop2.setEstBonne(false);
				}
				prop2.setQuestion(question);
				DAOFactory.getPropositionDAO().update(prop2);

				prop3.setEnonce(req.getParameter("proposition3"));
				prop3.setIdProposition(Integer.parseInt(req.getParameter("idProposition3")));
				String chk3 = req.getParameter("chkProposition3");
				if (chk3 != null) {
					prop3.setEstBonne(true);
				} else {
					prop3.setEstBonne(false);
				}
				prop3.setQuestion(question);
				DAOFactory.getPropositionDAO().update(prop3);

				prop4.setEnonce(req.getParameter("proposition4"));
				prop4.setIdProposition(Integer.parseInt(req.getParameter("idProposition4")));
				String chk4 = req.getParameter("chkProposition4");
				if (chk4 != null) {
					prop4.setEstBonne(true);
				} else {
					prop4.setEstBonne(false);
				}
				prop4.setQuestion(question);
				DAOFactory.getPropositionDAO().update(prop4);
				
				resp.sendRedirect("/ProjetQCM/validerAcces");
			}
		} catch (NumberFormatException | DALException e) {
			req.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}

}
