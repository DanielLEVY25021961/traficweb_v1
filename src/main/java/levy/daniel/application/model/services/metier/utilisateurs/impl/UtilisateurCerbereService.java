package levy.daniel.application.model.services.metier.utilisateurs.impl;

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
	 * DAO.<br/>
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

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbere create(
			final IUtilisateurCerbere pObject) throws Exception {
		
		return this.utilisateurCerbereDAO.create(pObject);
		
	} // Fin de create(...)._______________________________________________
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereService.-----------------------------
