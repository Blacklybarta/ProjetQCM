package servlet.section;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Promotion;
import bo.Section;
import bo.Test;
import bo.Theme;
import bo.Utilisateur;
import dal.DALException;
import dal.DAOFactory;

public class DoModifierSection extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (String.valueOf(session.getAttribute("collaborateur")).equals("true")) {
			try {
				List<Test> listeTest = DAOFactory.getTestDAO().selectAll();
				List<Theme> listeTheme = DAOFactory.getThemeDAO().selectAll();
				List<Section> listeSection = DAOFactory.getSectionDAO().selectAll();
				req.setAttribute("listeTests", listeTest);
				req.setAttribute("listeThemes", listeTheme);
				req.setAttribute("listeSections", listeSection);
			} catch (DALException e) {			
				e.printStackTrace();
			}			
			this.getServletContext().getRequestDispatcher("/collaborateur/section/updateSection.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Section section = null;
		try {
			if (String.valueOf(req.getParameter("select")).equals("true")) {
				String[] values = req.getParameterValues("idSection");	
				req.setAttribute("idSection", values[0]);
				section = DAOFactory.getSectionDAO().selectSectionByIdTestAndIdTheme(Integer.parseInt(values[0].charAt(0)+""), Integer.parseInt(values[0].charAt(1)+""));
				req.setAttribute("section", section);
				List<Test> listeTest = DAOFactory.getTestDAO().selectAll();
				List<Theme> listeTheme = DAOFactory.getThemeDAO().selectAll();
				req.setAttribute("listeTests", listeTest);
				req.setAttribute("listeThemes", listeTheme);
				this.getServletContext().getRequestDispatcher("/collaborateur/section/updateSection.jsp")
						.forward(req, resp);
			} else if (String.valueOf(req.getParameter("update")).equals("true")) {
				section = new Section();
				section.setNbQuestionATirer(Integer.parseInt(req.getParameter("nbQuestions")));
				section.setTest(Integer.parseInt(req.getParameter("idTest")));
				section.setTheme(Integer.parseInt(req.getParameter("idTheme")));	
				String idSection = req.getParameter("id");
				
				DAOFactory.getSectionDAO().updateSectionByIdTestAndIdTheme(section, Integer.parseInt(idSection.charAt(0)+""), Integer.parseInt(idSection.charAt(1)+""));
				resp.sendRedirect("/ProjetQCM/validerAcces");
			}
		} catch (NumberFormatException | DALException e) {
			req.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	
	}
}
