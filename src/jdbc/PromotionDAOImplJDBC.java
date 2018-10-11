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
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public Promotion selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
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
	public Section selectByIdTest(int idtest) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
