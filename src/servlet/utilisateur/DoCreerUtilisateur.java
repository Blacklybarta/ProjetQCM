package servlet.utilisateur;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import bo.Promotion;
import bo.Utilisateur;
import dal.DALException;
import dal.DAO;
import dal.DAOFactory;

public class DoCreerUtilisateur extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		List<Promotion> listePromotion = null;
		if (String.valueOf(session.getAttribute("collaborateur")).equals("true")) {	
			try {
				listePromotion = DAOFactory.getPromotionDAO().selectAll();
				req.setAttribute("listePromotions", listePromotion);
			} catch (DALException e) {
				e.printStackTrace();
			}
			
			this.getServletContext().getRequestDispatcher("/collaborateur/utilisateur/addCandidat.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNom(req.getParameter("nom"));
			utilisateur.setPrenom(req.getParameter("prenom"));
			utilisateur.setEmail(req.getParameter("email"));
			utilisateur.setPassword(req.getParameter("password"));
			
			String choix = req.getParameter("role");
			if(choix.equals("candidat")){
				utilisateur.setCandidat(true);
			}else{
				utilisateur.setCollaborateur(true);
			}	
			utilisateur.setCodeProfil(1);
			String[] values = req.getParameterValues("idPromotion");
			utilisateur.setCodePromo(Integer.parseInt(values[0]));
			DAOFactory.getUtilisateurDAO().insert(utilisateur);
			resp.sendRedirect("/ProjetQCM/validerAcces");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
