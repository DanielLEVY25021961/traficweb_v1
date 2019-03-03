package levy.daniel.application.controllers.desktop.metier.utilisateur.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import levy.daniel.application.controllers.desktop.metier.utilisateur.IUtilisateurCerbereController;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.services.metier.utilisateurs.IUtilisateurCerbereService;

/**
 * CLASSE UtilisateurCerbereController :<br/>
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
 * @author dan Lévy
 * @version 1.0
 * @since 2 mars 2019
 *
 */
@Controller(value="UtilisateurCerbereController")
public class UtilisateurCerbereController 
							implements IUtilisateurCerbereController {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * SERVICE pour l'objet métier.<br/>
	 * injecté par SPRING.<br/>
	 */
	@Autowired(required = true)
	@Qualifier(value="UtilisateurCerbereService")
	private transient IUtilisateurCerbereService utilisateurCerbereService;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereController.class);
	

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereController() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/* CREATE ************/

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbere create(
			final IUtilisateurCerbere pObject) throws Exception {
		
		return this.utilisateurCerbereService.create(pObject);
		
	} // Fin de create(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void persist(IUtilisateurCerbere pObject) throws Exception {
		// TODO Auto-generated method stub
		
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long createReturnId(IUtilisateurCerbere pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<IUtilisateurCerbere> saveIterable(Iterable<IUtilisateurCerbere> pList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbere retrieve(IUtilisateurCerbere pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbere findById(Long pId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long retrieveId(IUtilisateurCerbere pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IUtilisateurCerbere> findAll() throws Exception {
		
		if (this.utilisateurCerbereService != null) {
			return this.utilisateurCerbereService.findAll();
		} 
		
		System.out.println(" ***** utilisateurCerbereService est NULL ****");
		return null;
				
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IUtilisateurCerbere> findAllMax(int pStartPosition, int pMaxResult) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<IUtilisateurCerbere> findAllIterable(Iterable<Long> pIds) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbere update(IUtilisateurCerbere pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbere updateById(Long pId, IUtilisateurCerbere pObjectModifie) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(IUtilisateurCerbere pObject) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(Long pId) throws Exception {
		// TODO Auto-generated method stub
		
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteByIdBoolean(Long pId) throws Exception {
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
	public void deleteIterable(Iterable<IUtilisateurCerbere> pList) throws Exception {
		// TODO Auto-generated method stub
		
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteIterableBoolean(Iterable<IUtilisateurCerbere> pList) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(IUtilisateurCerbere pObject) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsId(Long pId) throws Exception {
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
	public String afficherListeObjetsMetier(List<IUtilisateurCerbere> pList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereController.--------------------------
