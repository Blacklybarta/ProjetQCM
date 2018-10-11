package servlet.section;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Section;
import bo.Test;
import bo.Theme;
import dal.DALException;
import dal.DAOFactory;

public class DoCreerSection extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		List<Test> listeTests = null;
		List<Theme> listeThemes = null;
		if (String.valueOf(session.getAttribute("collaborateur")).equals("true")) {
			try {
				listeThemes = DAOFactory.getThemeDAO().selectAll();
				req.setAttribute("listeThemes", listeThemes);
				listeTests = DAOFactory.getTestDAO().selectAll();
				req.setAttribute("listeTests", listeTests);

			} catch (DALException e) {
				e.printStackTrace();
			}

			this.getServletContext().getRequestDispatcher("/collaborateur/section/addSection.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Section section = new Section();
			String[] valuesTheme = req.getParameterValues("idTheme");
			String[] valuesTest = req.getParameterValues("idTest");

			section.setNbQuestionATirer(Integer.parseInt(req.getParameter("nbQuestions")));
			section.setTheme(Integer.parseInt(valuesTheme[0]));
			section.setTest(Integer.parseInt(valuesTest[0]));
			DAOFactory.getSectionDAO().insert(section);
			this.getServletContext().getRequestDispatcher("/collaborateur/gestion.jsp").forward(req, resp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DALException e) {
			e.printStackTrace();
		}

	}

}
