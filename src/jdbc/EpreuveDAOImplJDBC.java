package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Epreuve;
import bo.Utilisateur;
import dal.DALException;
import dal.DAO;
import dal.DBConnection;

public class EpreuveDAOImplJDBC implements DAO<Epreuve>{

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Epreuve> listeEpreuves = new ArrayList<>();
	
	private static final String SQL_SELECT_BY_CANDIDAT = "SELECT * FROM EPREUVE WHERE idUtilisateur=?";
	
	public void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = null;
		}
	}
	
	@Override
	public void insert(Epreuve data) throws DALException {
		// TODO Auto-generated method stub	
	}

	@Override
	public void update(Epreuve data) throws DALException {
		// TODO Auto-generated method stub		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub	
	}

	@Override
	public Epreuve selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Epreuve> selectCandidatEpreuve(int idUtilistaeur){
		// TODO Auto-generated method stub
				con = null;
				pstmt = null;
				ResultSet rs = null;
				try {
					con = DBConnection.getConnection();
					pstmt = con.prepareStatement(SQL_SELECT_BY_CANDIDAT);
					pstmt.setInt(1, idUtilistaeur);

					rs = pstmt.executeQuery();
					while (rs.next())
					{
						if (rs.next()) {
							Epreuve epreuve = new Epreuve();
							
							epreuve.setIdEpreuve(rs.getInt("idepreuve"));
							epreuve.setDateDebutValidite(rs.getDate("dateDebutValidite"));
							epreuve.setDateFinValidite(rs.getDate("dateFinValidite"));
							epreuve.setTempsEcoule(rs.getInt("tempsEcoule"));
							epreuve.setEtat(rs.getString("etat"));
							epreuve.setNoteObtenu(rs.getFloat("note_obtenu"));
							epreuve.setNiveauObtenu(rs.getString("niveau_obtenu"));
							
							listeEpreuves.add(epreuve);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (pstmt != null) {
							pstmt.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					closeConnection();
				}
				return listeEpreuves;
	}

	@Override
	public Epreuve selectByIdentifiant(String identifiant, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Epreuve> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Epreuve> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Epreuve selectByUtilisateur(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Epreuve> selectAllById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
