package levy.daniel.application;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import levy.daniel.application.controllers.desktop.metier.utilisateur.IUtilisateurCerbereController;
import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.persistence.metier.sections.ISectionHitDAO;
import levy.daniel.application.model.persistence.metier.utilisateur.IUtilisateurCerbereDAO;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl.ImporteurHit;
import levy.daniel.application.model.services.metier.utilisateurs.IUtilisateurCerbereService;
import levy.daniel.application.model.utilitaires.connecteurbase.ConnecteurBase;
import levy.daniel.application.model.utilitaires.spring.afficheurcontexte.AfficheurContexteSpring;
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
//@Profile("PROFIL_PROD_POSTGRES_SERVER")
//@EnableAspectJAutoProxy
//@EnableTransactionManagement
//@ComponentScans({@ComponentScan("levy.daniel.application")})
//@Transactional
public final class MainTestDAO {

	// ************************ATTRIBUTS************************************/

	/**
	 * contexte SPRING.<br/>
	 */
	private static transient ApplicationContext context;
	
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
	@Autowired(required = true)
	@Qualifier("SectionHitDAOJPASpring")
	private static transient ISectionHitDAO sectionHitDAOJPASpring;

	/**
	 * URL DE CONNEXION à la base de données.<br/>
	 * "jdbc:postgresql://localhost:5432/base-traficweb_v1"
	 */
	public static final String JDBC_URL 
		= "jdbc:postgresql://localhost:5432/base-traficweb_v1";
	
	/**
	 * Login de connexion à la base.<br/>
	 * "postgres"
	 */
	public static final String USERNAME = "postgres";
	
	/**
	 * Mot de passe de connexion à la base.<br/>
	 * "postgres"
	 */
	public static final String PASSWORD = "postgres";
	
	/**
	 * Paths.get(".").toAbsolutePath().normalize().<br/>
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
	 */
	private static void instancierContexteSpring() {
		
		// VERIFICATION DE LA CONNEXION A LA BASE
		final ConnecteurBase connecteurBase 
			= new ConnecteurBase(JDBC_URL, USERNAME, PASSWORD);
		
		final boolean connecte 
			= connecteurBase.connecterABaseHikariDataSource();
		
		if (!connecte) {
			System.out.println("LE SERVEUR DE BASE DE DONNEES NE REPOND PAS");
		} else {
			System.out.println("********* LE SERVEUR DE BASE DE DONNEES REPOND BIEN ****************");
		}
		
		context = new AnnotationConfigApplicationContext(
				ConfigurateurSpringFrmkAnnotationJPAPostgresServer.class);
		
		if (context == null) {
			
			System.out.println();
			System.out.println("**** LE CONTEXTE SPRING EST NULL *****");
			System.out.println();
			
		} else {
			
			System.out.println();
			System.out.println("**** LE CONTEXTE SPRING N'EST PAS NULL *****");
			System.out.println();
		}
		
		final String afficherBeans 
			= AfficheurContexteSpring
				.afficherContenuContexteSpring(
						(GenericApplicationContext) context);
		
		System.out.println();
		System.out.println("**** BEANS DANS LE CONTEXTE SPRING *****");
		System.out.println(afficherBeans);
		System.out.println();
		
//		utilisateurCerbereDAO 
//		= (IUtilisateurCerbereDAO) 
//			context.getBean("UtilisateurCerbereDAOJPASpring");
//	
//		utilisateurCerbereService 
//		= (IUtilisateurCerbereService) 
//			context.getBean("UtilisateurCerbereService");
//	
//		utilisateurCerbereController 
//			= (IUtilisateurCerbereController) 
//				context.getBean("UtilisateurCerbereController");
		
	} // Fin de instancierContexteSpring().________________________________
	
	
	
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
		
		sectionHitDAOJPASpring.saveIterable(fichierMapObjet.values());

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
	@Autowired(required = true)
	@Qualifier("UtilisateurCerbereDAOJPASpring")
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
	@Autowired(required = true)
	@Qualifier("UtilisateurCerbereService")
	public static void setUtilisateurCerbereService(
			final IUtilisateurCerbereService pUtilisateurCerbereService) {
		
		utilisateurCerbereService = pUtilisateurCerbereService;
		
		System.out.println("*********** UtilisateurCerbereService INJECTE DANS SON SETTER **************");
		
	} // Fin de setUtilisateurCerbereService(...)._________________________
	


} // FIN DE LA CLASSE MainTestDAO.-------------------------------------------
