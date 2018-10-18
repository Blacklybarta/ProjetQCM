package servlet.section;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Section;
import dal.DALException;
import dal.DAOFactory;

public class DoSupprimerSection extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (String.valueOf(session.getAttribute("collaborateur")).equals("true")) {	
			try {
				List<Section> listeSection = DAOFactory.getSectionDAO().selectAll();
				req.setAttribute("listeSections", listeSection);
			} catch (DALException e) {
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher("/collaborateur/section/removeSection.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String[] values = req.getParameterValues("idSection");
			DAOFactory.getSectionDAO().deleteByIdTestAndIdTheme(Integer.parseInt(values[0].charAt(0)+""), Integer.parseInt(values[0].charAt(1)+""));
			resp.sendRedirect("/ProjetQCM/validerAcces");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

}
