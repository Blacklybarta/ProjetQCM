package dal;

import bo.Epreuve;
import bo.Utilisateur;
import jdbc.EpreuveDAOImplJDBC;
import jdbc.UtilisateurDAOImplJDBC;

public class DAOFactory {
	
	public static DAO<Utilisateur> getUtilisateurDAO(){
		DAO<Utilisateur> utilisateurDAO = null;
		utilisateurDAO = (DAO<Utilisateur>) new UtilisateurDAOImplJDBC();
		return utilisateurDAO;
	}
	
	public static DAO<Epreuve> getEpreuveDAO(){
		DAO<Epreuve> epreuveDAO = null;
		epreuveDAO = (DAO<Epreuve>) new EpreuveDAOImplJDBC();
		return epreuveDAO;
	}
}