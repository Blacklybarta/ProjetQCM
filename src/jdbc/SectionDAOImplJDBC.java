package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Epreuve;
import bo.Section;
import bo.Test;
import bo.Theme;
import dal.DALException;
import dal.DAO;
import dal.DBConnection;

public class SectionDAOImplJDBC implements DAO<Section> {

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Section> listeSections = new ArrayList<>();
	
	private static final String SQL_SELECT_BY_ID_TEST = "SELECT * FROM TEST WHERE idtest=?";
	
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
	public void insert(Section data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insertWithIdReturn(Section data) throws DALException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Section data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Section selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Section selectByIdentifiant(String identifiant, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Section> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Section> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Section selectByUtilisateur(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Section> selectAllById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Epreuve> selectCandidatEpreuve(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Section selectByIdTest(int idtest) throws DALException {
		con = null;
		pstmt = null;
		ResultSet rs = null;
		Section section = null;
		
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID_TEST);
			pstmt.setInt(1, idtest);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				section = new Section();
				
				section.setNbQuestionATirer(rs.getInt("nbquestionsatirer"));
				section.setTest(rs.getInt("idtest"));
				section.setTheme(rs.getInt("idtheme"));
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
		return section;
	}
}
