package fr.eni.javaee.eniencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String SELECT_BY_LOGIN_AND_PASSWORD = "" + "SELECT no_utilisateur, pseudo, mot_de_passe "
			+ "FROM UTILISATEURS " + "WHERE pseudo = ? AND mot_de_passe = ?";
	private static final String INSERT_UTILISATEUR = ""
			+ "insert into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_BY_ID = ""
			+ "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur "
			+ "FROM UTILISATEURS " + "WHERE no_utilisateur = ?";
	private static final String DELETE_UTILISATEUR = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?";
	private static final String UPDATE_UTILISATEUR = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE no_utilisateur=?";
	private static final String SELECT_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo=? ";
	private static final String SELECT_BY_MAIL = "SELECT * FROM UTILISATEURS WHERE email=? ";
	private static final String UPDATE_CREDIT = "UPDATE UTILISATEURS SET credit=? WHERE no_utilisateur=?";

	@Override
	public Utilisateur selectByLoginAndPassword(String login, String password) throws BusinessException {
		Utilisateur utilisateur;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_LOGIN_AND_PASSWORD);
			pstmt.setString(1, login);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
						rs.getString("mot_de_passe"));
				return utilisateur;
			} else {
				throw new Exception("KO");
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNEXION_UTILISATEUR_ECHEC);
			throw businessException;
		}
	}

	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_UTILISATEUR,
						PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMotDePasse());
				pstmt.setInt(10, utilisateur.getCredit());
				pstmt.setInt(11, utilisateur.getAdmin());
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					utilisateur.setId(rs.getInt(1));
				}
				rs.close();
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

	@Override
	public Utilisateur selectById(int id) throws BusinessException {
		Utilisateur utilisateur;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getInt("administrateur"));
				return utilisateur;
			} else {
				throw new Exception("KO");
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.AFFICHAGE_PROFIL_ERR);
			throw businessException;
		}
	}

	@Override
	public void deleteUtilisateur(int id) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_UTILISATEUR);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_UTILISATEUR_ERR);
			throw businessException;
		}

	}

	@Override
	public void modifyUtilisateur(Utilisateur utilisateur) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_UTILISATEUR);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getId());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_UTILISATEUR_ERR);
			throw businessException;
		}

	}

	@Override
	public boolean isUniquePseudo(String pseudo) throws BusinessException {
		boolean unique = false;
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();

			// on vérifie s'il y a un résultat existant au pseudo taper par l'utilisateur
			if (rs.next()) {
				System.out.println("Ce pseudo est déjà utilisé par un autre utilisateur");
			} else {
				unique = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UNIQUE_PSEUDO_ERR);
			throw businessException;
		}
		return unique;
	}

	@Override
	public boolean isUniqueMail(String email) throws BusinessException {
		boolean unique = false;
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_MAIL);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			// on vérifie s'il y a un résultat existant au pseudo taper par l'utilisateur
			if (rs.next()) {
				System.out.println("Ce mail est déjà utilisé par un autre utilisateur");
			} else {
				unique = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UNIQUE_MAIL_ERR);
			throw businessException;
		}
		return unique;
	}

	@Override
	public void modifyCredit(int id, int credit) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_CREDIT);
			pstmt.setInt(1, credit);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_CREDIT_ERR);
			throw businessException;
		}
		
	}
}
