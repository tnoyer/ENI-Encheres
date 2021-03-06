package fr.eni.javaee.eniencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Categorie;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	
	private static final String SELECT_BY_ID = "SELECT no_categorie, libelle FROM CATEGORIES WHERE no_categorie = ?";

	@Override
	public List<Categorie> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie selectById(int idCat) throws BusinessException {
		Categorie cat;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, idCat);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				cat = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				System.out.println("cat : "+cat);
				return cat;
			} else {
				throw new Exception("KO");
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_CAT_ECHEC);
			throw businessException;
		}
	}
	
	

}
