package levy.daniel.application.model.services.metier.utilisateurs.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.persistence.metier.utilisateur.IUtilisateurCerbereDAO;
import levy.daniel.application.model.services.metier.utilisateurs.IUtilisateurCerbereService;

/**
 * CLASSE UtilisateurCerbereService :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 28 févr. 2019
 *
 */
@Service(value="UtilisateurCerbereService")
public class UtilisateurCerbereService implements IUtilisateurCerbereService {

	// ************************ATTRIBUTS************************************/

	
	/**
	 * DAO pour l'objet métier.<br/>
	 * injecté par SPRING.<br/>
	 */
	@Autowired(required = true)
	@Qualifier(value="UtilisateurCerbereDAOJPASpring")
	private transient IUtilisateurCerbereDAO utilisateurCerbereDAO;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereService.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/* CREATE ************/

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbere create(
			final IUtilisateurCerbere pObject) throws Exception {
		
		return this.utilisateurCerbereDAO.create(pObject);
		
	} // Fin de create(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void persist(
			final IUtilisateurCerbere pObject) throws Exception {
		// TODO Auto-generated method stub
		
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long createReturnId(
			final IUtilisateurCerbere pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<IUtilisateurCerbere> saveIterable(
			final Iterable<IUtilisateurCerbere> pList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbere retrieve(
			final IUtilisateurCerbere pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbere findById(
			final Long pId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long retrieveId(
			final IUtilisateurCerbere pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IUtilisateurCerbere> rechercherRapide(
			final String pString) throws Exception {
		
		return this.utilisateurCerbereDAO.rechercherRapide(pString);
		
	} // Fin de rechercherRapide(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IUtilisateurCerbere> findAll() throws Exception {		
		return this.utilisateurCerbereDAO.findAll();
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IUtilisateurCerbere> findAllMax(
			final int pStartPosition
				, final int pMaxResult) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<IUtilisateurCerbere> findAllIterable(
			final Iterable<Long> pIds) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbere update(
			final IUtilisateurCerbere pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbere updateById(
			final Long pId
				, final IUtilisateurCerbere pObjectModifie) 
							throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(
			final IUtilisateurCerbere pObject) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(
			final Long pId) throws Exception {
		// TODO Auto-generated method stub
		
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteByIdBoolean(
			final Long pId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteAllBoolean() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteIterable(
			final Iterable<IUtilisateurCerbere> pList) throws Exception {
		// TODO Auto-generated method stub
		
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteIterableBoolean(
			final Iterable<IUtilisateurCerbere> pList) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(
			final IUtilisateurCerbere pObject) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsId(
			final Long pId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long count() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ecrireStockageDansConsole() throws Exception {
		// TODO Auto-generated method stub
		
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String afficherListeObjetsMetier(
			final List<IUtilisateurCerbere> pList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereService.-----------------------------
