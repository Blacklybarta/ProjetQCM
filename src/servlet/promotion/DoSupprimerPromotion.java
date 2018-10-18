package servlet.promotion;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Promotion;
import dal.DALException;
import dal.DAOFactory;

public class DoSupprimerPromotion extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (String.valueOf(session.getAttribute("collaborateur")).equals("true")) {		
			try {
				List<Promotion> listePromotion = DAOFactory.getPromotionDAO().selectAll();
				req.setAttribute("listePromotions", listePromotion);
			} catch (DALException e) {
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher("/collaborateur/promotion/removePromotion.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("idPromotion"));
			DAOFactory.getPromotionDAO().delete(id);
			resp.sendRedirect("/ProjetQCM/validerAcces");
		} catch (NumberFormatException e) {			
			e.printStackTrace();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

}
