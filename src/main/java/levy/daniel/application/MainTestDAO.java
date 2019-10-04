package levy.daniel.application;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import levy.daniel.application.controllers.desktop.metier.utilisateur.IUtilisateurCerbereController;
import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.persistence.metier.sections.ISectionHitDAO;
import levy.daniel.application.model.persistence.metier.utilisateur.IUtilisateurCerbereDAO;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl.ImporteurHit;
import levy.daniel.application.model.services.metier.utilisateurs.IUtilisateurCerbereService;
import levy.daniel.application.model.utilitaires.connecteurbase.ConnecteurBase;
import levy.daniel.application.model.utilitaires.spring.configurateurspring.ConfigurateurSpringFrmkAnnotationJPAPostgresServer;

/**
 * CLASSE MainTestDAO :<br/>
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
 * @since 17 juil. 2019
 *
 */
public final class MainTestDAO {

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
	 * DAO pour l'objet métier.<br/>
	 * récupéré dans le contexte SPRING ou injecté via son setter.<br/>
	 * UtilisateurCerbereDAOJPASpring.
	 */
	private static transient IUtilisateurCerbereDAO utilisateurCerbereDAO;

	
	/**
	 * SERVICE pour l'objet métier.<br/>
	 * récupéré dans le contexte SPRING ou injecté via son setter.<br/>
	 * UtilisateurCerbereService.
	 */
	private static transient IUtilisateurCerbereService utilisateurCerbereService;
	
	/**
	 * CONTROLLER pour l'objet métier.<br/>
	 * récupéré dans le contexte SPRING.<br/>
	 */
	private static transient IUtilisateurCerbereController utilisateurCerbereController;
	
	/**
	 * ISectionHitDAO.<br/>
	 */
//	@Autowired(required = true)
//	@Qualifier("SectionHitDAOJPASpring")
	private static transient ISectionHitDAO sectionHitDAOJPASpring;

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
	 * Path absolu du présent projet Eclipse.<br/>
	 * <code>Paths.get(".").toAbsolutePath().normalize()</code>.<br/>
	 */
	public static final Path PATH_ABSOLU_PRESENT_PROJET 
		= Paths.get(".").toAbsolutePath().normalize();
	
	/**
	 * path <b>relatif</b> (par rapport au présent projet ECLIPSE) 
	 * des ressources des tests JUnit dans le présent projet ECLIPSE.<br/>
	 * Paths.get("src/test/resources")
	 */
	public static final Path SRC_TEST_RESOURCES_PATH_RELATIF 
		= Paths.get("src/test/resources");

	/**
	 * Path absolu vers les ressources de test.<br/>
	 */
	public static final Path PATH_ABSOLU_TEST_RESOURCES 
		= PATH_ABSOLU_PRESENT_PROJET
			.resolve(SRC_TEST_RESOURCES_PATH_RELATIF)
				.toAbsolutePath().normalize();
	
	 /**
	 * Path absolu vers les nomenclatures de test.<br/>
	 */
	public static final Path PATH_ABSOLU_TEST_NOMENCLATURES 
		= PATH_ABSOLU_TEST_RESOURCES
			.resolve("ressources/Nomenclatures")
				.toAbsolutePath().normalize();
	
	 /**
	 * Path absolu vers les jeux d'essai de test.<br/>
	 * src/test/resources/jeux_essai
	 */
	public static final Path PATH_ABSOLU_TEST_JEUX_ESSAI 
		= PATH_ABSOLU_TEST_RESOURCES
			.resolve("jeux_essai")
				.toAbsolutePath().normalize();
	
	/**
	 * Path absolu vers le répertoire 'temp' sous le présent projet.
	 */
	public static final Path PATH_ABSOLU_REPERTOIRE_TEMP 
		= PATH_ABSOLU_PRESENT_PROJET.resolve("temp");
	
	/**
	 * NEWLINE : String :<br/>
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(MainTestDAO.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private MainTestDAO() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * instancie le contexte SPRING.<br/>
	 * <ul>
	 * <li>vérifie la connexion à la Base de Données.</li>
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
		context.getEnvironment().setActiveProfiles("PROFIL_PROD_POSTGRES_SERVER");
//		context.getEnvironment().setActiveProfiles("PROFIL_TEST_H2_MEMORY");
//		context.getEnvironment().setActiveProfiles("PROFIL_TEST_H2_FILE");

		/* enregistre éventuellement les classes de Config SPRING 
		 * si on veut utiliser une configuration par classe Java. 
		 * La classe de Config Java doit alors être annotée avec 
		 * @ComponentScans({@ComponentScan("packageAScanner")}) 
		 * pour scanner les BEANS SPRING. */
		context.register(ConfigurateurSpringFrmkAnnotationJPAPostgresServer.class);
//		context.register(ConfigurateurSpringFrmkAnnotationJPAH2Memory.class);
//		context.register(ConfigurateurSpringFrmkAnnotationJPAH2File.class);

		/* précise dans quel Package SPRING doit chercher les COMPONENTS 
		 * si on utilise pas de classe de Config SPRING. 
		 * on utilise alors la configuration de SPRING par annotations.*/
//		context.scan("levy.daniel.application");

		/* rafraîchit le CONTEXT SPRING. */
		context.refresh();
		
		// AFFICHAGE DU CONTEXTE SPRING
		afficherInfosContexte();

		// RECUPERATION DES BEANS.
		utilisateurCerbereDAO 
		= (IUtilisateurCerbereDAO) 
			context.getBean("UtilisateurCerbereDAOJPASpring");
	
		utilisateurCerbereService 
		= (IUtilisateurCerbereService) 
			context.getBean("UtilisateurCerbereService");
	
		utilisateurCerbereController 
			= (IUtilisateurCerbereController) 
				context.getBean("UtilisateurCerbereController");
		
		sectionHitDAOJPASpring 
			= (ISectionHitDAO) 
				context.getBean("SectionHitDAOJPASpring");
		
	} // Fin de instancierContexteSpring().________________________________

	
	
	/**
	 * vérifie la connexion à la base et affiche un message à la console.
	 */
	private static void verifierConnexionBase() {

//		final ConnecteurBase connecteurBase 
//			= new ConnecteurBase(
//					JDBC_URL_POSTGRES
//						, USERNAME_POSTGRES
//							, PASSWORD_POSTGRES);
		
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

		final String[] tableauBeans = context.getBeanDefinitionNames();
		
		String afficherBeans = "";
		final int tailleTableau = tableauBeans.length;
		
		if (tailleTableau == 0) {
			afficherBeans = "********* LE CONTENEUR DE BEANS CONTEXT EST VIDE *********** ";
		} else {
			
			for (int i = 0; i < tailleTableau; i++) {
				afficherBeans += tableauBeans[i] + NEWLINE;
			}
		}

		System.out.println();
		System.out.println("**** INFOS SUR LE CONTEXTE SPRING *****");
		System.out.println();
		System.out.println("**** BEANS DANS LE CONTEXTE SPRING *****");
		System.out.println(afficherBeans);
		System.out.println();
		
	} // Fin de afficherInfosContexte().___________________________________
	
	
	
	/**
	 * Point d'entrée de l'application.<br/>
	 * <ul>
	 * <li>instancie le contexte SPRING.</li>
	 * <li>récupère les BEANS dans le CONTEXTE SRING.</li>
	 * </ul>
	 *
	 * @param pArgs : String[] :  .<br/>
	 * 
	 * @throws Exception 
	 */
	public static void main(
			final String... pArgs) throws Exception {

		/* instancie le contexte SPRING. */
		instancierContexteSpring();

		final Path fichierDonneesPath 
			= PATH_ABSOLU_TEST_JEUX_ESSAI.resolve("HITDIRA2017.txt");
		final File fichierDonnees = fichierDonneesPath.toFile();
		final Charset charsetAnsi = Charset.forName("Windows-1252");

		// OBJET A TESTER.
		final ImporteurHit importeurHIT = new ImporteurHit();

		// IMPORT DU FICHIER EN MEMOIRE.
		final Map<Integer, ISectionHit> fichierMapObjet 
			= importeurHIT.importerObjet(fichierDonnees, charsetAnsi);
		
		final Collection<ISectionHit> collectionSections 
			= fichierMapObjet.values();
		
		final Iterator<ISectionHit> ite = collectionSections.iterator();
		
		while (ite.hasNext()) {
			
			final ISectionHit sectionHit = ite.next();
			sectionHitDAOJPASpring.create(sectionHit);
			
		}
		
//		sectionHitDAOJPASpring.saveIterable(collectionSections);

	} // Fin de main(...)._________________________________________________


	
	/**
	 * Getter du IUtilisateurCerbereDAO.<br/>
	 *
	 * @return this.utilisateurCerbereDAO : IUtilisateurCerbereDAO.<br/>
	 */
	public static IUtilisateurCerbereDAO getUtilisateurCerbereDAO() {
		return utilisateurCerbereDAO;
	} // Fin de getUtilisateurCerbereDAO().________________________________


	
	/**
	* Setter du IUtilisateurCerbereDAO.<br/>
	*
	* @param pUtilisateurCerbereDAO : IUtilisateurCerbereDAO : 
	* valeur à passer à this.utilisateurCerbereDAO.<br/>
	*/
//	@Autowired(required = true)
//	@Qualifier("UtilisateurCerbereDAOJPASpring")
	public static void setUtilisateurCerbereDAO(
			final IUtilisateurCerbereDAO pUtilisateurCerbereDAO) {
		
		utilisateurCerbereDAO = pUtilisateurCerbereDAO;
		
		System.out.println("*********** UtilisateurCerbereDAOJPASpring INJECTE DANS SON SETTER **************");
		
	} // Fin de setUtilisateurCerbereDAO(...)._____________________________


	
	/**
	 * Getter du IUtilisateurCerbereService.<br/>
	 *
	 * @return this.utilisateurCerbereService : IUtilisateurCerbereService.<br/>
	 */
	public static IUtilisateurCerbereService getUtilisateurCerbereService() {
		return utilisateurCerbereService;
	} // Fin de getUtilisateurCerbereService().____________________________


	
	/**
	* Setter du IUtilisateurCerbereService.<br/>
	*
	* @param pUtilisateurCerbereService : IUtilisateurCerbereService : 
	* valeur à passer à this.utilisateurCerbereService.<br/>
	*/
//	@Autowired(required = true)
//	@Qualifier("UtilisateurCerbereService")
	public static void setUtilisateurCerbereService(
			final IUtilisateurCerbereService pUtilisateurCerbereService) {
		
		utilisateurCerbereService = pUtilisateurCerbereService;
		
		System.out.println("*********** UtilisateurCerbereService INJECTE DANS SON SETTER **************");
		
	} // Fin de setUtilisateurCerbereService(...)._________________________
	


} // FIN DE LA CLASSE MainTestDAO.-------------------------------------------
