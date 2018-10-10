package servlet.theme;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Theme;
import dal.DALException;
import dal.DAOFactory;

public class DoCreerTheme extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(String.valueOf(session.getAttribute("collaborateur")).equals("true")){
			this.getServletContext().getRequestDispatcher("/collaborateur/theme/addTheme.jsp").forward(req, resp);
		}else{
			req.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String libelle = req.getParameter("libelle");
		Theme theme = new Theme();
		theme.setLibelle(libelle);
		try {
			DAOFactory.getThemeDAO().insert(theme);
			this.getServletContext().getRequestDispatcher("/collaborateur/gestion.jsp").forward(req, resp);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
	}
}
