package servlet.question;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Proposition;
import bo.Question;
import bo.Theme;
import dal.DALException;
import dal.DAOFactory;

public class DoCreerQuestion extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (String.valueOf(session.getAttribute("collaborateur")).equals("true")) {
			List<Theme> listeThemes;
			try {
				listeThemes = DAOFactory.getThemeDAO().selectAll();
				req.setAttribute("listeThemes", listeThemes);
			} catch (DALException e) {
				req.setAttribute("error", e.getMessage());
				this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
			}

			this.getServletContext().getRequestDispatcher("/collaborateur/question/addQuestion.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Question question = null;
		Theme theme = null;
		Proposition prop1 = null;
		Proposition prop2 = null;
		Proposition prop3 = null;
		Proposition prop4 = null;
		try {
			question = new Question();
			prop1 = new Proposition();
			prop2 = new Proposition();
			prop3 = new Proposition();
			prop4 = new Proposition();
			question.setEnonce(req.getParameter("enonce"));
			question.setPoints(Integer.parseInt(req.getParameter("points")));
			String[] values = req.getParameterValues("idTheme");
			theme = DAOFactory.getThemeDAO().selectById(Integer.parseInt(values[0]));
			question.setTheme(theme);
			int idQuestion = DAOFactory.getQuestionDAO().insertWithIdReturn(question);
			question.setIdQuestion(idQuestion);

			prop1.setEnonce(req.getParameter("proposition1"));
			String chk1 = req.getParameter("chkProposition1");
			if (chk1 != null) {
				prop1.setEstBonne(true);
			} else {
				prop1.setEstBonne(false);
			}
			prop1.setQuestion(question);
			DAOFactory.getPropositionDAO().insert(prop1);

			prop2.setEnonce(req.getParameter("proposition2"));
			String chk2 = req.getParameter("chkProposition2");
			if (chk2 != null) {
				prop2.setEstBonne(true);
			} else {
				prop2.setEstBonne(false);
			}
			prop2.setQuestion(question);
			DAOFactory.getPropositionDAO().insert(prop2);

			prop3.setEnonce(req.getParameter("proposition3"));
			String chk3 = req.getParameter("chkProposition3");
			if (chk3 != null) {
				prop3.setEstBonne(true);
			} else {
				prop3.setEstBonne(false);
			}
			prop3.setQuestion(question);
			DAOFactory.getPropositionDAO().insert(prop3);

			prop4.setEnonce(req.getParameter("proposition4"));
			String chk4 = req.getParameter("chkProposition4");
			if (chk4 != null) {
				prop4.setEstBonne(true);
			} else {
				prop4.setEstBonne(false);
			}
			prop4.setQuestion(question);
			DAOFactory.getPropositionDAO().insert(prop4);

			this.getServletContext().getRequestDispatcher("/collaborateur/gestion.jsp").forward(req, resp);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DALException e) {
			e.printStackTrace();
		}

	}
}
