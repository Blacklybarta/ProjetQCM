package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Epreuve;
import bo.Theme;
import dal.DALException;
import dal.DAO;
import dal.DBConnection;

public class ThemeDAOImplJDBC implements DAO<Theme>{

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Theme> listeThemes = new ArrayList<>();
	
	private static final String SQL_INSERT = "INSERT INTO THEME(libelle)VALUES(?)";
	private static final String SQL_SELECTALL = "SELECT * FROM THEME";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM THEME WHERE idTheme=?";
	private static final String SQL_UPDATE = "UPDATE THEME SET libelle=? WHERE idtheme=?";
	
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
	public void insert(Theme data) throws DALException {
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
					data.setIdTheme(rs.getInt(1));
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
	public void update(Theme data) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, data.getLibelle());			
			pstmt.setInt(2, data.getIdTheme());
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Theme selectById(int id) throws DALException {
		con = null;
		pstmt = null;
		ResultSet rs = null;
		Theme theme = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				theme = new Theme();
				theme.setIdTheme(rs.getInt("idtheme"));
				theme.setLibelle(rs.getString("libelle"));
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
		return theme;
	}

	@Override
	public Theme selectByIdentifiant(String identifiant, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Theme> selectAll() throws DALException {
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_SELECTALL);

			Theme theme = null;
			listeThemes.clear();
			while (rs.next()) {
				theme = new Theme();
				theme.setIdTheme(rs.getInt("idtheme"));
				theme.setLibelle(rs.getString("libelle"));			
				
				listeThemes.add(theme);
			}
		} catch (SQLException e) {
			throw new DALException("selectAll failed - ", e);
		} finally {
			try {

				if (stmt != null) {
					stmt.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection();
		}
		return listeThemes;
	}

	@Override
	public List<Theme> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Theme selectByUtilisateur(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Theme> selectAllById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Epreuve> selectCandidatEpreuve(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int insertWithIdReturn(Theme data) throws DALException {
		// TODO Auto-generated method stub
		return 0;
	}

}
