package servlet.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Test;
import bo.Theme;
import dal.DALException;
import dal.DAOFactory;

public class DoCreerTest extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (String.valueOf(session.getAttribute("collaborateur")).equals("true")) {			
			this.getServletContext().getRequestDispatcher("/collaborateur/test/addTest.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Test test = null;
		try {
			test = new Test();
			test.setLibelle(req.getParameter("libelle"));
			test.setDescription(req.getParameter("description"));
			test.setDuree(Integer.parseInt(req.getParameter("duree")));
			test.setSeuilHaut(Integer.parseInt(req.getParameter("seuilHaut")));
			test.setSeuilBas(Integer.parseInt(req.getParameter("seuilBas")));
			DAOFactory.getTestDAO().insert(test);
			resp.sendRedirect("/ProjetQCM/validerAcces");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
}
