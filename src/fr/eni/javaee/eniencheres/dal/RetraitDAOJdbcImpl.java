package fr.eni.javaee.eniencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Retrait;

public class RetraitDAOJdbcImpl implements RetraitDAO {
	
	private static final String INSERT_RETRAIT = ""
			+ "INSERT INTO RETRAITS(rue, code_postal, ville) "
			+ "VALUES(?,?,?)";

	@Override
	public void insertRetrait(Retrait retrait) throws BusinessException {
		if (retrait == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_RETRAIT);
				pstmt.setString(1, retrait.getRue());
				pstmt.setString(2, retrait.getCodePostal());
				pstmt.setString(3, retrait.getVille());
				pstmt.executeUpdate();
				pstmt.close();
				cnx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

}
