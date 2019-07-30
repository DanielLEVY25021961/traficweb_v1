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
	 * <ol>
	 * <li>instancie un AnnotationConfigApplicationContext SPRING.
	 * <br/><code><b>context = 
	 * new AnnotationConfigApplicationContext();</b></code></li>
	 * <li>déclare éventuellement le profil actif. (PROD, DEV, TEST, ...).
	 * <br/><code><b>context.getEnvironment()
	 * .setActiveProfiles("PROFIL_PROD_POSTGRES_SERVER");</b></code>
	 * <br/>ATTENTION : Il est INDISPENSABLE de déclarer un Profile ACTIF 
	 * si il existe plusieurs classes de Config avec différents profils 
	 * dans le code.<br/>
	 * ATTENTION : toujours déclarer les profils actifs AVANT 
	 * de register les éventuelles classes de Config SPRING.</li>
	 * <li>enregistre éventuellement les classes de <i>Config SPRING</i> 
	 * si on veut utiliser une <b>configuration par classe Java</b>.<br/> 
	 * La classe de Config Java doit alors être annotée avec 
	 * <code>ComponentScans({ComponentScan("packageAScanner")})</code> 
	 * pour scanner les BEANS SPRING.
	 * <br/>ATTENTION : si les classes de Config SPRING 
	 * ne sont pas register ici, leurs annotations 
	 * <code>ComponentScan(basePackages = "packageAScanner")</code> 
	 * seront inopérantes.
	 * <ul>
	 * <li>Soit on fait ici 
	 * <code><b>context.register(classeConfig.class);</b></code> 
	 * sur des classes de Config Spring <i>annotées avec 
	 * "ComponentScan(basePackages = "packageAScanner")"</i> 
	 * et on n'a pas à insérer 
	 * <code><b>context.scan("packageAScanner");</b></code> 
	 * dans cette présente méthode d'instanciation du CONTEXTE SPRING.</li>
	 * <li>Soit on ne déclare ici aucune des classes de Config SPRING 
	 * en laissant SPRING trouver seul les classe de Config à l'aide du SCAN 
	 * mais <i>il faut déclarer dans la présente méthode les Packages 
	 * contenant les BEANS SPRING à scanner.</i> 
	 * <br/> en insérant dans la présente méthode : 
	 * <code><b>context.scan("packageAScanner")</b></code></li>
	 * </ul>
	 * <li>précise éventuellement dans quel Package SPRING 
	 * doit chercher les COMPONENTS 
	 * si on utilise pas de classe de Config SPRING.
	 * <br/>on utilise alors la <b>configuration de SPRING par annotations.</b>
	 * <br/><code><b>context.scan(packageAScanner);</b></code></li>
	 * <li>rafraîchit le CONTEXT SPRING.</li>
	 * </ol>
	 */
	private static void instancierContexteSpring() {
		
		// VERIFICATION DE LA CONNEXION A LA BASE
		verifierConnexionBase();
		
		// INSTANCIATION DU CONTEXTE SPRING. 
		/* instancie un AnnotationConfigApplicationContext. */
		context = new AnnotationConfigApplicationContext();	

		/* déclare éventuellement le profil actif. */
		// ATTENTION : doit être AVANT la déclaration de la classe 
		// de configuration (context.register(.Class)).
		context.getEnvironment().setActiveProfiles("PROFIL_TEST_H2_FILE");
//		context.getEnvironment().setActiveProfiles("PROFIL_TEST_H2_MEMORY");
//		context.getEnvironment().setActiveProfiles("PROFIL_PROD_POSTGRES_SERVER");		
		
		/* enregistre éventuellement les classes de Config SPRING 
		 * si on veut utiliser une configuration par classe Java. 
		 * La classe de Config Java doit alors être annotée avec 
		 * @ComponentScans({@ComponentScan("packageAScanner")}) 
		 * pour scanner les BEANS SPRING. */
		context.register(levy.daniel.application.model.utilitaires.spring.configurateurspring.ConfigurateurSpringFrmkAnnotationJPAH2File.class);
//		context.register(levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring.ConfigurateurSpringJPAH2File.class);

		/* précise dans quel Package SPRING doit chercher les COMPONENTS 
		 * si on utilise pas de classe de Config SPRING. 
		 * on utilise alors la configuration de SPRING par annotations.*/
//		context.scan("levy.daniel.application");
		
		/* rafraîchit le CONTEXT SPRING. */
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
		
		System.out.println("****************** INJECTION DANS ApplicationJavaFxMain.setUtilisateurCerbereController(...) ************* ");
	} // Fin de setUtilisateurCerbereController(...).______________________

	

} // FIN DE LA CLASSE ApplicationJavaFxMain.---------------------------------
