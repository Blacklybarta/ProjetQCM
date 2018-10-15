package servlet.promotion;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Promotion;
import bo.Utilisateur;
import dal.DALException;
import dal.DAOFactory;

public class DoModifierPromotion extends HttpServlet{

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
			
			this.getServletContext().getRequestDispatcher("/collaborateur/promotion/updatePromotion.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Promotion promotion = null;
		try {
			if (String.valueOf(req.getParameter("select")).equals("true")) {			
				String[] values = req.getParameterValues("idPromotion");
				promotion = DAOFactory.getPromotionDAO().selectById(Integer.parseInt(values[0]));
				req.setAttribute("promotion", promotion);
				this.getServletContext().getRequestDispatcher("/collaborateur/promotion/updatePromotion.jsp")
						.forward(req, resp);
			} else if (String.valueOf(req.getParameter("update")).equals("true")) {
				
				promotion = new Promotion();
				promotion.setCodePromo(Integer.parseInt(req.getParameter("id")));
				promotion.setLibelle(req.getParameter("libelle"));
				DAOFactory.getPromotionDAO().update(promotion);
				resp.sendRedirect("/ProjetQCM/validerAcces");
			}
		} catch (NumberFormatException | DALException e) {
			req.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}

}
