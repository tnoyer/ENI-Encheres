package fr.eni.javaee.eniencheres.bll;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Categorie;
import fr.eni.javaee.eniencheres.dal.CategorieDAO;
import fr.eni.javaee.eniencheres.dal.DAOFactory;

public class CategorieManager {

	private CategorieDAO categorieDAO;
	
	public CategorieManager() {
		this.categorieDAO = DAOFactory.getCategorieDAO();
	}
	
	public Categorie selectionnerCategorie(int idCat) throws BusinessException {
		return this.categorieDAO.selectById(idCat);
	}
}
