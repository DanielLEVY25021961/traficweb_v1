package levy.daniel.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import levy.daniel.application.controllers.desktop.metier.utilisateur.IUtilisateurCerbereController;
import levy.daniel.application.model.utilitaires.connecteurbase.ConnecteurBase;
import levy.daniel.application.model.utilitaires.spring.afficheurcontexte.AfficheurContexteSpring;
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
	 * URL DE CONNEXION à la base de données POSTGRES.<br/>
	 * "jdbc:postgresql://localhost:5432/base-traficweb_v1"
	 */
	public static final String JDBC_URL_POSTGRES 
		= "jdbc:postgresql://localhost:5432/base-traficweb_v1";
	
	/**
	 * Login de connexion à la base POSTGRES.<br/>
	 * "postgres"
	 */
	public static final String USERNAME_POSTGRES = "postgres";
	
	/**
	 * Mot de passe de connexion à la base POSTGRES.<br/>
	 * "postgres"
	 */
	public static final String PASSWORD_POSTGRES = "postgres";

	/**
	 * URL DE CONNEXION à la base de données H2 en MODE FILE.<br/>
	 * "jdbc:h2:file:./data/base-traficweb_v1-h2/base-traficweb_v1"
	 */
	public static final String JDBC_URL_H2_FILE 
		= "jdbc:h2:file:./data/base-traficweb_v1-h2/base-traficweb_v1";
	
	/**
	 * URL DE CONNEXION à la base de données H2 en MODE MEMORY.<br/>
	 * "jdbc:h2:mem:base-traficweb_v1"
	 */
	public static final String JDBC_URL_H2_MEMORY 
		= "jdbc:h2:mem:base-traficweb_v1";	
	
	/**
	 * Login de connexion à la base H2 (MODE FILE ou MEMORY).<br/>
	 * "sa"
	 */
	public static final String USERNAME_H2 = "sa";
	
	/**
	 * Mot de passe de connexion à la base H2 (MODE FILE ou MEMORY).<br/>
	 * "sa"
	 */
	public static final String PASSWORD_H2 = "sa";

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
		
		// VERIFICATION DE LA CONNEXION A LA BASE
		verifierConnexionBase();
		
		// INSTANCIATION DU CONTEXTE SPRING. 
		context = new AnnotationConfigApplicationContext();	
		context.register(levy.daniel.application.model.utilitaires.spring.configurateurspring.ConfigurateurSpringFrmkAnnotationJPAH2File.class);
//		context.register(levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring.ConfigurateurSpringJPAH2File.class);
		context.getEnvironment().setActiveProfiles("PROFIL_TEST_H2_FILE");
//		context.scan("levy.daniel.application");
		context.refresh();
		
		// AFFICHAGE DU CONTEXTE SPRING
		afficherInfosContexte();
		
		// RECUPERATION DES BEANS.
//		utilisateurCerbereController 
//			= (IUtilisateurCerbereController) 
//				context.getBean("UtilisateurCerbereController");
		
	} // Fin de instancierContexteSpring().________________________________

	
	
	/**
	 * vérifie la connexion à la base et affiche un message à la console.
	 */
	private static void verifierConnexionBase() {

		final ConnecteurBase connecteurBase 
			= new ConnecteurBase(
					JDBC_URL_H2_FILE
						, USERNAME_H2
							, PASSWORD_H2);

		final boolean connecte = connecteurBase.connecterABaseHikariDataSource();

		if (!connecte) {
			System.out.println("LE SERVEUR DE BASE DE DONNEES NE REPOND PAS");
		} else {
			System.out.println("********* LE SERVEUR DE BASE DE DONNEES REPOND BIEN ****************");
		}

	} // Fin de verifierConnexionBase().___________________________________
	

	
	/**
	 * affiche des informations sur le contexte SPRING.
	 * <ul>
	 * <li>affiche les Beans contenus dans le contexte SPRING.</li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private static void afficherInfosContexte() {

		final String afficherBeans 
			= AfficheurContexteSpring
				.afficherContenuContexteSpring(
						(GenericApplicationContext) context);

		System.out.println();
		System.out.println("**** INFOS SUR LE CONTEXTE SPRING *****");
		System.out.println();
		System.out.println("**** BEANS DANS LE CONTEXTE SPRING *****");
		System.out.println(afficherBeans);
		System.out.println();
		
		final ConfigurableEnvironment configurableEnvironment 
			= context.getEnvironment();
		final String[] tableauActiveProfiles 
			= configurableEnvironment.getActiveProfiles();
		
		System.out.println();
		System.out.println("PROFILS ACTIFS DANS LE CONTEXTE : ");
		for (int i = 0; i < tableauActiveProfiles.length; i++) {
			System.out.println(tableauActiveProfiles[i]);
		}
		
		
	} // Fin de afficherInfosContexte().___________________________________



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


	
	/**
	 * Getter du CONTROLLER pour l'objet métier.<br/>
	 * récupéré dans le contexte SPRING.<br/>
	 *
	 * @return this.utilisateurCerbereController : 
	 * IUtilisateurCerbereController.<br/>
	 */
	public static final IUtilisateurCerbereController getUtilisateurCerbereController() {
		return utilisateurCerbereController;
	} // Fin de getUtilisateurCerbereController()._________________________


	
	/**
	* Setter du CONTROLLER pour l'objet métier.<br/>
	* récupéré dans le contexte SPRING.<br/>
	*
	* @param pUtilisateurCerbereController : IUtilisateurCerbereController : 
	* valeur à passer à this.utilisateurCerbereController.<br/>
	*/
	@Autowired(required = true)
	@Qualifier("UtilisateurCerbereController")
	public static final void setUtilisateurCerbereController(
			final IUtilisateurCerbereController pUtilisateurCerbereController) {
		utilisateurCerbereController = pUtilisateurCerbereController;
		
		System.out.println("INJECTION DANS ApplicationJavaFxMain.setUtilisateurCerbereController(...)");
	} // Fin de setUtilisateurCerbereController(...).______________________

	

} // FIN DE LA CLASSE ApplicationJavaFxMain.---------------------------------
