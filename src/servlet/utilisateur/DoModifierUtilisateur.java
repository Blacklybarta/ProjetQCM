package servlet.utilisateur;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Promotion;
import bo.Theme;
import bo.Utilisateur;
import dal.DALException;
import dal.DAOFactory;

public class DoModifierUtilisateur extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (String.valueOf(session.getAttribute("collaborateur")).equals("true")) {
			try {
				List<Utilisateur> listeUtilisateur = DAOFactory.getUtilisateurDAO().selectAll();
				req.setAttribute("listeUtilisateurs", listeUtilisateur);
			} catch (DALException e) {
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher("/collaborateur/utilisateur/updateCandidat.jsp").forward(req,
					resp);
		} else {
			req.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Utilisateur utilisateur = null;
		try {
			if (String.valueOf(req.getParameter("select")).equals("true")) {
				String[] values = req.getParameterValues("idUser");
				utilisateur = DAOFactory.getUtilisateurDAO().selectById(Integer.parseInt(values[0]));
				req.setAttribute("utilisateur", utilisateur);
				List<Promotion> listePromotion = DAOFactory.getPromotionDAO().selectAll();
				req.setAttribute("listePromotions", listePromotion);
				this.getServletContext().getRequestDispatcher("/collaborateur/utilisateur/updateCandidat.jsp")
						.forward(req, resp);
			} else if (String.valueOf(req.getParameter("update")).equals("true")) {
				utilisateur = new Utilisateur();
				utilisateur.setNom(req.getParameter("nom"));
				utilisateur.setPrenom(req.getParameter("prenom"));
				utilisateur.setIdUtilistaeur(Integer.parseInt(req.getParameter("id")));
				utilisateur.setEmail(req.getParameter("email"));
				utilisateur.setPassword(req.getParameter("password"));
				String choix = req.getParameter("role");
				if (choix.equals("candidat")) {
					utilisateur.setCandidat(true);
				} else {
					utilisateur.setCollaborateur(true);
				}
				String[] values = req.getParameterValues("idPromotion");
				utilisateur.setCodePromo(Integer.parseInt(values[0]));
				DAOFactory.getUtilisateurDAO().update(utilisateur);
				resp.sendRedirect("/ProjetQCM/validerAcces");
			}
		} catch (NumberFormatException | DALException e) {
			req.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
}
