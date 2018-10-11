package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Epreuve;
import bo.Question;
import bo.Section;
import dal.DALException;
import dal.DAO;
import dal.DBConnection;

public class QuestionDAOImplJDBC implements DAO<Question>{

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Question> listeQuestions = new ArrayList<>();
	
	private static final String SQL_INSERT = "INSERT INTO QUESTION(enonce,points,idtheme)VALUES(?,?,?)";
	
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
	public void insert(Question data) throws DALException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int insertWithIdReturn(Question data) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, data.getEnonce());
			//pstmt.setBytes(2, data.getImage());
			pstmt.setInt(2, data.getPoints());
			pstmt.setInt(3, data.getTheme().getIdTheme());
			
			

			int nbRows = pstmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					data.setIdQuestion(rs.getInt(1));
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
		return data.getIdQuestion();
	}

	@Override
	public void update(Question data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Question selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question selectByIdentifiant(String identifiant, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question selectByUtilisateur(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> selectAllById(int id) throws DALException {
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
		// TODO Auto-generated method stub
		return null;
	}

	

}
