package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Epreuve;
import bo.Proposition;
import bo.Question;
import bo.QuestionTirage;
import bo.Section;
import bo.Theme;
import dal.DALException;
import dal.DAO;
import dal.DBConnection;

public class PropositionDAOImplJDBC implements DAO<Proposition> {

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Proposition> listePropositions = new ArrayList<>();

	private static final String SQL_INSERT = "INSERT INTO PROPOSITION(enonce,estBonne,idquestion)VALUES(?,?,?)";
	private static final String SQL_SELECT_BY_ID_QUESTION = "SELECT * FROM PROPOSITION WHERE idQuestion=?";
	private static final String SQL_SELECT_BY_EST_BONNE = "SELECT * FROM PROPOSITION WHERE idQuestion=? AND estBonne=1";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM PROPOSITION WHERE idproposition=?";

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
	public void insert(Proposition data) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, data.getEnonce());
			pstmt.setBoolean(2, data.isEstBonne());
			pstmt.setInt(3, data.getQuestion().getIdQuestion());

			int nbRows = pstmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					data.setIdProposition(rs.getInt(1));
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
	public int insertWithIdReturn(Proposition data) throws DALException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Proposition data) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public Proposition selectById(int id) throws DALException {
		con = null;
		pstmt = null;
		ResultSet rs = null;
		Proposition proposition = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				proposition.setEstBonne(rs.getBoolean("estBonne"));
				proposition.setIdProposition(rs.getInt("idproposition"));
				proposition.setEnonce(rs.getString("enonce"));
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
		return proposition;
	}

	@Override
	public Proposition selectByIdentifiant(String identifiant, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proposition> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proposition> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Proposition selectByUtilisateur(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proposition> selectAllById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Epreuve> selectCandidatEpreuve(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Section> selectByIdTest(int idtest) throws DALException {
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
	public QuestionTirage selectByIdEpreuve(int idEpreuve, int nbQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proposition> selectByIdQuestion(int idQuestion) {
		con = null;
		pstmt = null;
		ResultSet rs = null;

		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID_QUESTION);
			pstmt.setInt(1, idQuestion);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Proposition proposition = new Proposition();

				proposition.setEstBonne(rs.getBoolean("estBonne"));
				proposition.setIdProposition(rs.getInt("idproposition"));
				proposition.setEnonce(rs.getString("enonce"));
				// proposition.setQuestion(rs.getBoolean("estBonne"));

				listePropositions.add(proposition);
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
		return listePropositions;
	}

	@Override
	public List<Proposition> selectByEstBonne(int idQuestion) {
		con = null;
		pstmt = null;
		ResultSet rs = null;

		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_EST_BONNE);
			pstmt.setInt(1, idQuestion);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Proposition proposition = new Proposition();

				proposition.setEstBonne(rs.getBoolean("estBonne"));
				proposition.setIdProposition(rs.getInt("idproposition"));
				proposition.setEnonce(rs.getString("enonce"));
				proposition.setQuestion(rs.getInt("idquestion"));

				listePropositions.add(proposition);
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
		return listePropositions;
	}

	@Override
	public void updateEtat(Proposition data) throws DALException {
		// TODO Auto-generated method stub	
	}

	@Override
	public void updateNote(int idEpreuve, int note) throws DALException {
		// TODO Auto-generated method stub
	}

	@Override
	public Proposition selectSectionByIdTestAndIdTheme(int idTest, int idTheme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSectionByIdTestAndIdTheme(Proposition data, int idTest, int idTheme) throws DALException {
		// TODO Auto-generated method stub
	}

}
