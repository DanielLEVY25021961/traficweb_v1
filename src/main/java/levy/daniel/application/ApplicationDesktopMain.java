package levy.daniel.application;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import levy.daniel.application.controllers.desktop.metier.utilisateur.IUtilisateurCerbereController;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.persistence.metier.utilisateur.IUtilisateurCerbereDAO;
import levy.daniel.application.model.services.metier.utilisateurs.IUtilisateurCerbereService;
import levy.daniel.application.model.utilitaires.spring.configurateurspring.ConfigurateurSpringFrmkAnnotationJPAH2File;


/**
 * CLASSE ApplicationDesktopMain :<br/>
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
 * @since 3 mars 2019
 *
 */
public final class ApplicationDesktopMain {

	// ************************ATTRIBUTS************************************/

	/**
	 * contexte SPRING.<br/>
	 */
	private static transient ApplicationContext context;
	
	/**
	 * DAO pour l'objet métier.<br/>
	 * récupéré dans le contexte SPRING.<br/>
	 */
	private static transient IUtilisateurCerbereDAO utilisateurCerbereDAO;

	
	/**
	 * SERVICE pour l'objet métier.<br/>
	 * récupéré dans le contexte SPRING.<br/>
	 */
	private static transient IUtilisateurCerbereService utilisateurCerbereService;
	
	/**
	 * CONTROLLER pour l'objet métier.<br/>
	 * récupéré dans le contexte SPRING.<br/>
	 */
	private static transient IUtilisateurCerbereController utilisateurCerbereController;
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(ApplicationDesktopMain.class);
	

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	private ApplicationDesktopMain() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * instancie le contexte SPRING.<br/>
	 */
	private static void instancierContexteSpring() {
		
		context = new AnnotationConfigApplicationContext(
				ConfigurateurSpringFrmkAnnotationJPAH2File.class);
		
		utilisateurCerbereDAO 
		= (IUtilisateurCerbereDAO) 
			context.getBean("UtilisateurCerbereDAOJPASpring");
	
		utilisateurCerbereService 
		= (IUtilisateurCerbereService) 
			context.getBean("UtilisateurCerbereService");
	
		utilisateurCerbereController 
			= (IUtilisateurCerbereController) 
				context.getBean("UtilisateurCerbereController");
		
	} // Fin de instancierContexteSpring().________________________________



	/**
	 * Point d'entrée de l'application.<br/>
	 * <ul>
	 * <li>instancie le contexte SPRING.</li>
	 * <li>récupère le CONTROLLER dans le CONTEXTE SRING.</li>
	 * </ul>
	 *
	 * @param pArgs : String[].<br/>
	 * @throws Exception 
	 */
	public static void main(
			final String... pArgs) throws Exception {
		
		/* instancie le contexte SPRING. */
		instancierContexteSpring();
		
		final List<IUtilisateurCerbere> liste 
			= utilisateurCerbereDAO.findAll();
		
		System.out.println(liste);
		
    } // Fin de main(...)._________________________________________________

	

} // FIN DE LA CLASSE ApplicationDesktopMain.--------------------------------
