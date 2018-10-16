package servlet.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Promotion;
import bo.Test;
import bo.Utilisateur;
import dal.DALException;
import dal.DAOFactory;

public class DoModifierTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (String.valueOf(session.getAttribute("collaborateur")).equals("true")) {
			try {
				List<Test> listeTests = DAOFactory.getTestDAO().selectAll();
				req.setAttribute("listeTests", listeTests);
			} catch (DALException e) {
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher("/collaborateur/test/updateTest.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Test test = null;
		try {
			if (String.valueOf(req.getParameter("select")).equals("true")) {
				String[] values = req.getParameterValues("idTest");
				test = DAOFactory.getTestDAO().selectById(Integer.parseInt(values[0]));
				req.setAttribute("test", test);			
				this.getServletContext().getRequestDispatcher("/collaborateur/test/updateTest.jsp")
						.forward(req, resp);
			} else if (String.valueOf(req.getParameter("update")).equals("true")) {
				test = new Test();
				test.setLibelle(req.getParameter("libelle"));
				test.setDescription(req.getParameter("description"));
				test.setDuree(Integer.parseInt(req.getParameter("duree")));
				test.setSeuilHaut(Integer.parseInt(req.getParameter("seuilHaut")));
				test.setSeuilBas(Integer.parseInt(req.getParameter("seuilBas")));
				test.setIdTest(Integer.parseInt(req.getParameter("id")));			
				DAOFactory.getTestDAO().update(test);
				resp.sendRedirect("/ProjetQCM/validerAcces");
			}
		} catch (NumberFormatException | DALException e) {
			req.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
	
}

