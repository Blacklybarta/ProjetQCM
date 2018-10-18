package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Epreuve;
import bo.Promotion;
import bo.Proposition;
import bo.Question;
import bo.QuestionTirage;
import bo.Section;
import bo.Theme;
import dal.DALException;
import dal.DAO;
import dal.DBConnection;

public class PromotionDAOImplJDBC implements DAO<Promotion> {

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Promotion> listePromotions = new ArrayList<>();

	private static final String SQL_INSERT = "INSERT INTO PROMOTION(libelle)VALUES(?)";
	private static final String SQL_SELECTALL = "SELECT * FROM PROMOTION";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM PROMOTION WHERE codepromo=?";
	private static final String SQL_UPDATE = "UPDATE PROMOTION SET libelle=? WHERE codepromo=?";
	private static final String SQL_DELETE = "DELETE FROM PROMOTION WHERE codepromo=?";

	public void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con = null;
		}
	}

	@Override
	public void insert(Promotion data) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, data.getLibelle());

			int nbRows = pstmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					data.setCodePromo(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new DALException("Insert theme failed - " + data, e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

			} catch (SQLException e) {
				throw new DALException("close failed - ", e);
			}
			closeConnection();
		}
	}

	@Override
	public int insertWithIdReturn(Promotion data) throws DALException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Promotion data) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, data.getLibelle());			
			pstmt.setInt(2, data.getCodePromo());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update theme failed - " + data, e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}
	}

	@Override
	public void delete(int id) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_DELETE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("delete promotion failed - " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}
	}

	@Override
	public Promotion selectById(int id) throws DALException {
		con = null;
		pstmt = null;
		ResultSet rs = null;
		Promotion promotion= null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				promotion = new Promotion();
				promotion.setCodePromo(rs.getInt("codepromo"));
				promotion.setLibelle(rs.getString("libelle"));
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
		return promotion;
	}

	@Override
	public Promotion selectByIdentifiant(String identifiant, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Promotion> selectAll() throws DALException {
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_SELECTALL);

			Promotion promotion = null;
			listePromotions.clear();
			while (rs.next()) {
				promotion = new Promotion();
				promotion.setCodePromo(rs.getInt("codepromo"));
				promotion.setLibelle(rs.getString("libelle"));
				listePromotions.add(promotion);
			}
		} catch (SQLException e) {
			throw new DALException("selectAll failed - ", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		return listePromotions;
	}

	@Override
	public List<Promotion> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Promotion selectByUtilisateur(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Promotion> selectAllById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Epreuve> selectCandidatEpreuve(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<Question> selectRandomQuestions(int idTheme, int nbQuestions, List<Question> questions)
			throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Section> selectByIdTest(int idtest) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionTirage selectByIdEpreuve(int idEpreuve, int nbQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proposition> selectByIdQuestion(int idQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEtat(Promotion data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Proposition> selectByEstBonne(int idQuestion) {
		// TODO Auto-generated method stub
		return null;
	}
		
	public Promotion selectSectionByIdTestAndIdTheme(int idTest, int idTheme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSectionByIdTestAndIdTheme(Promotion data, int idTest, int idTheme) throws DALException {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateNote(int idEpreuve, int note, String niveau) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMarque(boolean mark, int idQuestion, int idEpreuve) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<QuestionTirage> selectAllByIdEpreuve(int idEpreuve) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByIdTestAndIdTheme(int idTest, int idTheme) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
