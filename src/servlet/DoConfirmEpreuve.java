package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Epreuve;
import bo.Utilisateur;
import dal.DALException;
import dal.DAOFactory;

public class DoConfirmEpreuve extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/candidat/epreuve/epreuveconfirm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int idEpreuve = Integer.valueOf(req.getParameter("idEpreuve"));
		Epreuve epreuve = null;
		HttpSession session = req.getSession();
		
		try {
			epreuve = DAOFactory.getEpreuveDAO().selectById(idEpreuve);
			
			session.setAttribute("epreuve", epreuve);
			
		} catch (DALException e) {
			req.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
		
		this.getServletContext().getRequestDispatcher("/candidat/epreuve/epreuveconfirm.jsp").forward(req, resp);
	}
	
}
