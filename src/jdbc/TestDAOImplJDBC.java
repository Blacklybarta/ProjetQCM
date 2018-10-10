package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Epreuve;
import bo.Test;
import bo.Utilisateur;
import dal.DALException;
import dal.DAO;
import dal.DBConnection;

public class TestDAOImplJDBC  implements DAO<Test>{
	
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Epreuve> listeEpreuves = new ArrayList<>();
	
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM TEST WHERE idtest=?";

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
	public void insert(Test data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Test data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Test selectById(int id) throws DALException {
				con = null;
				pstmt = null;
				ResultSet rs = null;
				Test test = null;
				
				try {
					con = DBConnection.getConnection();
					pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
					pstmt.setInt(1, id);

					rs = pstmt.executeQuery();
					if (rs.next()) {
						test = new Test();
						test.setIdTest(rs.getInt("idtest"));
						test.setDescription(rs.getString("description"));
						test.setDuree(rs.getInt("duree"));
						test.setLibelle(rs.getString("libelle"));
						test.setSeuilBas(rs.getInt("seuil_bas"));
						test.setSeuilHaut(rs.getInt("seuil_haut"));
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
				return test;
	}

	@Override
	public Test selectByIdentifiant(String identifiant, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Test> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Test> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Test selectByUtilisateur(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Test> selectAllById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Epreuve> selectCandidatEpreuve(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertWithIdReturn(Test data) throws DALException {
		// TODO Auto-generated method stub
		return 0;
	}
	
}