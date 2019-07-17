package levy.daniel.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import levy.daniel.application.controllers.desktop.metier.utilisateur.IUtilisateurCerbereController;
import levy.daniel.application.model.utilitaires.spring.configurateurspring.ConfigurateurSpringFrmkAnnotationJPAH2File;
import levy.daniel.application.vues.desktop.metier.utilisateur.UtilisateurCerbereAccueilVue;

/**
 * CLASSE ApplicationJavaFxMain :<br/>
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
 * @since 28 févr. 2019
 *
 */
public class ApplicationJavaFxMain extends Application {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "PROFIL_TEST_H2_FILE".
	 */
	public static final String PROFIL_TEST_H2_FILE = "PROFIL_TEST_H2_FILE";
	
	/**
	 * "PROFIL_TEST_H2_MEMORY".
	 */
	public static final String PROFIL_TEST_H2_MEMORY = "PROFIL_TEST_H2_MEMORY";

	/**
	 * "PROFIL_PROD_POSTGRES_SERVER".
	 */
	public static final String PROFIL_PROD_POSTGRES_SERVER 
		= "PROFIL_PROD_POSTGRES_SERVER";

	/**
	 * contexte SPRING.<br/>
	 */
//	private static transient ApplicationContext context;
	private static transient AnnotationConfigApplicationContext context;
	

	/**
	 * CONTROLLER pour l'objet métier.<br/>
	 * récupéré dans le contexte SPRING.<br/>
	 */
	private static transient IUtilisateurCerbereController utilisateurCerbereController;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(ApplicationJavaFxMain.class);
	

	// *************************METHODES************************************/
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ApplicationJavaFxMain() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(
			final Stage pPrimaryStage) throws Exception {
				
		/* vue à afficher. */
		final UtilisateurCerbereAccueilVue vue 
			= new UtilisateurCerbereAccueilVue(utilisateurCerbereController);
		
        /* Titre du théâtre. */
        pPrimaryStage.setTitle("UtilisateurCerbereAccueilVue");
        
        /* PASSE LA VUE à afficher à la SCENE 
         * et dimensionne la SCENE. */
        final Scene scene = new Scene(vue, 1200d, 650d);
        
        /* ajoute la feuille de style à la Scene. */
        scene.getStylesheets().add("static/css/dan_style.css");
        
        /* passe la SCENE au THEATRE (stage). */
        pPrimaryStage.setScene(scene);
        
        /* affiche le THEATRE. */
        pPrimaryStage.show();

	} // Fin de start(...).________________________________________________
	

	
	/**
	 * instancie le contexte SPRING.<br/>
	 * <ul>
	 * <li>appelé dans le Thread MAIN (avant le Thread Javafx).</li>
	 * <li>récupère le CONTROLLER dans le CONTEXTE SPRING.</li>
	 * </ul>
	 */
	private static void instancierContexteSpring() {
		
		// INSTANCIATION DU CONTEXTE SPRING. 
		context = new AnnotationConfigApplicationContext();
		
		context.getEnvironment().setActiveProfiles("PROFIL_TEST_H2_FILE");
		
		context.register(ConfigurateurSpringFrmkAnnotationJPAH2File.class);
		
		context.refresh();
		
		// RECUPERATION DES BEANS.
		utilisateurCerbereController 
			= (IUtilisateurCerbereController) 
				context.getBean("UtilisateurCerbereController");
		
	} // Fin de instancierContexteSpring().________________________________



	/**
	 * Point d'entrée de l'application.<br/>
	 * <ul>
	 * <li>instancie le contexte SPRING.</li>
	 * <li>lance l'application Javafx.</li>
	 * </ul>
	 *
	 * @param pArgs : String[].<br/>
	 */
	public static void main(
			final String... pArgs) {
		
		/* instancie le contexte SPRING. */
		instancierContexteSpring();
		
		/* lance l'application Javafx. */
		 Application.launch(pArgs);
		 
    } // Fin de main(...)._________________________________________________

	

} // FIN DE LA CLASSE ApplicationJavaFxMain.---------------------------------
