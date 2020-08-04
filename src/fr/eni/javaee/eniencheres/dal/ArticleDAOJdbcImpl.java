package fr.eni.javaee.eniencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.ArticleVendu;
import fr.eni.javaee.eniencheres.bo.Categorie;
import fr.eni.javaee.eniencheres.bo.Utilisateur;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private static final String INSERT_ARTICLE = ""
			+ "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) "
			+ "VALUES(?,?,?,?,?,?,?,?)";
	private static final String SELECT_ALL = ""
			+ "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, pseudo, rue, code_postal, ville, libelle "
			+ "FROM ARTICLES_VENDUS av "
			+ "INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie";

	@Override
	public void insertArticle(ArticleVendu art) throws BusinessException {
		if (art == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE,
						PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, art.getNom());
				pstmt.setString(2, art.getDescription());
				pstmt.setDate(3, java.sql.Date.valueOf(art.getDateDebut()));
				pstmt.setDate(4, java.sql.Date.valueOf(art.getDateFin()));
				pstmt.setInt(5, art.getPrixInitial());
				pstmt.setInt(6, art.getPrixInitial());
				pstmt.setInt(7, art.getUtilisateur().getId());
				pstmt.setInt(8, art.getCategorie().getId());
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					art.setId(rs.getInt(1));
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
	public List<ArticleVendu> selectAll() throws BusinessException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			
			ArticleVendu unArticle;
			Utilisateur unUtilisateur;
			Categorie uneCategorie;
			
			while(rs.next())
			{
				unArticle = new ArticleVendu(
						rs.getString("nom_article"), 
						rs.getString("description"), 
						rs.getDate("date_debut_encheres").toLocalDate(), 
						rs.getDate("date_fin_encheres").toLocalDate(), 
						rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"));
				
				unUtilisateur = new Utilisateur(rs.getString("pseudo"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"));

				uneCategorie = new Categorie(rs.getString("libelle"));

				unArticle.setUtilisateur(unUtilisateur);
				unArticle.setCategorie(uneCategorie);

				listeArticles.add(unArticle);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
			throw businessException;
		}
		return listeArticles;
	}

}
