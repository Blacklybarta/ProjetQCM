package servlet.theme;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Theme;
import bo.exceptions.ListException;
import dal.DALException;
import dal.DAOFactory;

public class DoModifierTheme extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(String.valueOf(session.getAttribute("collaborateur")).equals("true")){
			try {
				List<Theme> listeThemes = DAOFactory.getThemeDAO().selectAll();
				req.setAttribute("listeThemes", listeThemes);
			} catch (DALException e) {
				req.setAttribute("error", e.getMessage());
				this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
			}
			
			this.getServletContext().getRequestDispatcher("/collaborateur/theme/updateTheme.jsp").forward(req, resp);
		}else{
			req.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Theme theme = null;
		try {
			if(String.valueOf(req.getParameter("select")).equals("true")){
				String[] values = req.getParameterValues("idTheme");
				theme = DAOFactory.getThemeDAO().selectById(Integer.parseInt(values[0]));
				req.setAttribute("theme", theme);
				this.getServletContext().getRequestDispatcher("/collaborateur/theme/updateTheme.jsp").forward(req, resp);
			}else if(String.valueOf(req.getParameter("update")).equals("true")){			
				String libelle = String.valueOf(req.getParameter("libelle"));				
				theme = new Theme();
				theme.setLibelle(libelle);
				theme.setIdTheme(Integer.parseInt(req.getParameter("id")));
				DAOFactory.getThemeDAO().update(theme);
				this.getServletContext().getRequestDispatcher("/collaborateur/gestion.jsp").forward(req, resp);
			}
		} catch (NumberFormatException | DALException e) {
			req.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
	
}
