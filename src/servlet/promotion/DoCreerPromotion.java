package servlet.promotion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Promotion;
import dal.DALException;
import dal.DAOFactory;

public class DoCreerPromotion extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (String.valueOf(session.getAttribute("collaborateur")).equals("true")) {			
			this.getServletContext().getRequestDispatcher("/collaborateur/promotion/addPromotion.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Promotion promotion = new Promotion();
			promotion.setLibelle(req.getParameter("nom"));
			DAOFactory.getPromotionDAO().insert(promotion);
			resp.sendRedirect("/ProjetQCM/validerAcces");
		} catch (DALException e) {
			e.printStackTrace();
		}
				
	}

}
