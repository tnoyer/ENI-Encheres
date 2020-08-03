package fr.eni.javaee.eniencheres.bll;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Retrait;
import fr.eni.javaee.eniencheres.dal.DAOFactory;
import fr.eni.javaee.eniencheres.dal.RetraitDAO;

public class RetraitManager {
	private RetraitDAO retraitDAO;

	public RetraitManager() {
		this.retraitDAO = DAOFactory.getRetraitDAO();
	}
	
	public void insererRetrait(Retrait retrait) throws BusinessException {
		this.retraitDAO.insertRetrait(retrait);
	}
}
