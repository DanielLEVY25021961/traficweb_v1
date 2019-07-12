package levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring.lecteur.lecteurjpadatasourcespring.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zaxxer.hikari.HikariDataSource;

import levy.daniel.application.model.utilitaires.spring.afficheurcontexte.AfficheurContexteSpring;
import levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring.lecteur.lecteurjpadatasourcespring.ILecteurJPADataSourceSpring;
import levy.daniel.application.model.utilitaires.spring.configurateurspring.ConfigurateurSpringFrmkAnnotationJPAPostgresServer;

/**
 * CLASSE LecteurJPADataSourceSpringTest :<br/>
 * Test JUnit de la classe {@link LecteurJPADataSourceSpring}.<br/>
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
 * @since 11 juil. 2019
 *
 */
@ActiveProfiles("PROFIL_PROD_POSTGRES_SERVER")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes= {ConfigurateurSpringFrmkAnnotationJPAPostgresServer.class})
public class LecteurJPADataSourceSpringTest {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe LecteurJPADataSourceSpringTest".
	 */
	public static final String CLASSE_LECTEURJPA_DATASOURCE_SPRING_TEST 
		= "Classe LecteurJPADataSourceSpringTest";
	
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
	 * Contexte SPRING injecté par SPRING dès que 
	 * la configuration est terminée.<br/>
	 * <ul>
	 * <li><b>injecté via son SETTER</b>.</li>
	 * <li>injecté <i>après</i> avantTests() (BeforeClass) 
	 * et après le Constructeur du Test.</li>
	 * <li>injecté <i>avant</i> avantChaqueTest() (Before).</li>
	 * </ul>
	 */
    private GenericApplicationContext contextInjectable;
    	
	/**
	 * Contexte SPRING pour les tests.<br/>
	 */
	private static transient GenericApplicationContext contexteSpring;
		
	/**
	 * <b>lecteur SPRING du fichier properties 
	 * de configuration de la base</b>.
	 * <ul>
	 * <li>org.springframework.core.env.Environment</li>
	 * </ul>
	 */
	private Environment environmentInjectable;
	
	/**
	 * Environment SPRING pour les tests.<br/>
	 */
	private static transient Environment environmentSpring;
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;
	
	/**
	 * boolean qui stipule si les transactions 
	 * doivent être RollBackées lors des tests.<br/>
	 * Par défaut, Spring met cette valeur à true 
	 * et on ne peut donc pas voir les données dans une table 
	 * en mode FILE après un test.<br/>
	 * Passer cette valeur à false si on souhaite 
	 * consulter la table après un test.
	 */
	public static final boolean VALEUR_ROLLBACK = true;
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";

	/**
	 * ILecteurJPADataSourceSpring.<br/>
	 */
	private static transient ILecteurJPADataSourceSpring lecteurDataSource;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(LecteurJPADataSourceSpringTest.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public LecteurJPADataSourceSpringTest() {
		
		super();
		
//		System.out.println();
//		System.out.println("DANS LE CONSTRUCTEUR DU TEST");
//		System.out.println("CONTEXTE SPRING : " + contexteSpring);
		
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * .<br/>
	 * 
	 * @throws SQLException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConnexion() throws SQLException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE LecteurJPADataSourceSpringTest - méthode testConnexion() ********** ");
		}
		
		final HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/base-traficweb_v1");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres");
		
		final Connection connexion = dataSource.getConnection();
		
		if (AFFICHAGE_GENERAL && affichage) {
			
			if (connexion != null) {
				System.out.println(connexion);
			}
			
		}
		
		dataSource.close();
		
	} // Fin de testConnexion().___________________________________________
	
	
	
	/**
	 * .<br/>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testLecteurJPADataSourceSpring() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE LecteurJPADataSourceSpringTest - méthode testLecteurJPADataSourceSpring() ********** ");
		}

		lecteurDataSource 
			= new LecteurJPADataSourceSpring(this.environmentInjectable);
		
		System.out.println(lecteurDataSource.toString());
		
	} // Fin de testLecteurJPADataSourceSpring().__________________________

	
	/**
	 * Getter du Contexte SPRING injecté par SPRING dès que 
	 * la configuration est terminée.<br/>
	 * <ul>
	 * <li><b>injecté via son SETTER</b>.</li>
	 * <li>injecté <i>après</i> avantTests() (BeforeClass) 
	 * et après le Constructeur du Test.</li>
	 * <li>injecté <i>avant</i> avantChaqueTest() (Before).</li>
	 * </ul>
	 *
	 * @return this.contextInjectable : GenericApplicationContext.<br/>
	 */
	public GenericApplicationContext getContextInjectable() {
		return this.contextInjectable;
	} // Fin de getContextInjectable().____________________________________



	/**
	* Setter du Contexte SPRING injecté par SPRING dès que 
	* la configuration est terminée.<br/>
	* <ul>
	* <li>instancie le <code><b>contexteSpring</b></code> 
	* STATIC la première fois.</li>
	* <li><b>injecté via son SETTER</b> (Autowired).</li>
	* <li>injecté <i>après</i> avantTests() (BeforeClass) 
	* et après le Constructeur du Test.</li>
	* <li>injecté <i>avant</i> avantChaqueTest() (Before).</li>
	* </ul>
	*
	* @param pContextInjectable : GenericApplicationContext : 
	* valeur à passer à this.contextInjectable.<br/>
	*/
	@Autowired
	public void setContextInjectable(
			final GenericApplicationContext pContextInjectable) {
		
//		System.out.println();
//		System.out.println("****************INJECTION DU CONTEXTE DANS LE SETTER setContextInjectable(...)**************");
//		System.out.println(pContextInjectable);
//		System.out.println();
		
		this.contextInjectable = pContextInjectable;
		
		/* instancie le contexteSpring STATIC la première fois. */
		if (contexteSpring == null || !contexteSpring.isActive()) {
			contexteSpring = this.contextInjectable;
		}
		
	} // Fin de setContextInjectable(...)._________________________________
	

		
	/**
	 * Getter du <b>lecteur SPRING du fichier properties 
	 * de configuration de la base</b>.
	 * <ul>
	 * <li>org.springframework.core.env.Environment</li>
	 * </ul>
	 *
	 * @return this.environmentInjectable : Environment.<br/>
	 */
	public final Environment getEnvironmentInjectable() {
		return this.environmentInjectable;
	} // Fin de getEnvironmentInjectable().________________________________


	
	/**
	* Setter du <b>lecteur SPRING du fichier properties 
	* de configuration de la base</b>.
	* <ul>
	* <li>instancie le <code><b>environmentSpring</b></code> 
	* STATIC la première fois.</li>
	* <li>injecté par SPRING dès que la configuration est terminée.</li>
	* <li>org.springframework.core.env.Environment</li>
	* <li><b>injecté via son SETTER</b> (Autowired).</li>
	* <li>injecté <i>après</i> avantTests() (BeforeClass) 
	* et après le Constructeur du Test.</li>
	* <li>injecté <i>avant</i> avantChaqueTest() (Before).</li>
	* </ul>
	*
	* @param pEnvironmentInjectable : Environment : 
	* valeur à passer à this.environmentInjectable.<br/>
	*/
	@Autowired
	public final void setEnvironmentInjectable(
			final Environment pEnvironmentInjectable) {
		
		this.environmentInjectable = pEnvironmentInjectable;
		
		/* instancie le environmentSpring STATIC la première fois. */
		if (environmentSpring == null) {
			environmentSpring = this.environmentInjectable;
		}

	} // Fin de setEnvironmentInjectable(...)._____________________________



	/**
	 * <ul>
	 * <li>instructions exécutées <b>avant l'ensemble des tests</b> 
	 * de la classe JUnit.</li>
	 * <li>exécuté <i>avant</i> le Constructeur de la classe.</li>
	 * <li><b>A REMPLIR A LA MAIN</b></li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		
		/**/
		
	} // Fin de beforeClass()._____________________________________________
	
		

	/**
	 * <ul>
	 * <li>instructions exécutées <b>APRES l'ensemble des tests</b> 
	 * de la classe JUnit.</li>
	 * <li>executé lors de la fermeture de la classe.</li>
	 * <li><b>A REMPLIR A LA MAIN</b></li>
	 * <li>clôt le contexte Spring déclaré par Annotations.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@AfterClass
	public static void apresTests() throws Exception {
		
		if (contexteSpring != null) {
			
			System.out.println();
			System.out.println("*************** apresTest() DANS LE TEST JUNIT ****************************");
			System.out.println("*****************CLOTURE DU CONTEXTE SPRING ******************************************************");
			
			/* clôt le contexte Spring déclaré par Annotations. */
			contexteSpring.close();
			
		}
		
	} // Fin de apresTests().______________________________________________
	
	
	
	/**
	 * <b>affiche les propriétés lues par le EMFactory</b>.<br/>
	 * <br/>
	 * - ne fait rien si pEntityManagerFactory == null.<br/>
	 * <br/>
	 *
	 * @param pEntityManagerFactory : EntityManagerFactory.<br/>
	 */
	private static void afficherEMFactory(
			final EntityManagerFactory pEntityManagerFactory) {
		
		/* ne fait rien si pEntityManagerFactory == null. */
		if (pEntityManagerFactory == null) {
			return;
		}
				
//		final String propsDansEntityManagerFactory 
//		= AfficheurContexteSpring
//			.afficherPrincipalesProperties(pEntityManagerFactory);
		
		final String propsDansEntityManagerFactory 
		= AfficheurContexteSpring
			.afficherProperties(pEntityManagerFactory);
	
		System.out.println(propsDansEntityManagerFactory);
	
	} // Fin de afficherEMFactory(...).____________________________________
	
	
	
	/**
	 * <b>affiche les propriétés lues par le EMFactory</b>.<br/>
	 * <b>affiche les beans contenus dans le contexte SPRING</b>.<br/>
	 */
	private static void afficherContexte() {
		
		String[] beansTableau = null;
		
		if (contexteSpring != null) {
			
			EntityManagerFactory entityManagerFactory = null;
			
			try {
				
				entityManagerFactory 
					= contexteSpring.getBean(
							"entityManagerFactory"
							, EntityManagerFactory.class);
				
			} catch (BeansException e) {
				e.printStackTrace();
			}
			
			if (entityManagerFactory != null) {
				
				System.out.println();
				System.out.println("*********** DANS CLASSE DE TEST - afficherContexte() *****************************");
				System.out.println("Proprietes du Bean EntityManagerFactory : " + entityManagerFactory.getClass());
				System.out.println();
				System.out.println("DANS CLASSE DE TEST - afficherContexte() - PROPRIETES DANS LE BEAN EntityManagerFactory : ");
				System.out.println();
				/* affiche les propriétés lues par le EMFactory. */
				afficherEMFactory(entityManagerFactory);
			}
			
			
			beansTableau = contexteSpring.getBeanDefinitionNames();
			
		} else {
			
			final String message 
				= CLASSE_LECTEURJPA_DATASOURCE_SPRING_TEST 
					+ " - METHODE avantTests() - " 
						+ "LE CONTEXTE N'A PU ETRE INSTANCIE";
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		if (beansTableau != null) {
			
			System.out.println();
			System.out.println("*********** DANS CLASSE DE TEST - afficherContexte() *****************************");
			System.out.println("BEANS CONTENUS DANS le CONTEXTE SPRING (contexteSpring.getBeanDefinitionNames()) : ");
			
			for (int i = 0; i < beansTableau.length; i++) {
				System.out.println(beansTableau[i]);
			}
			
			System.out.println();
			
		}
		
	} // Fin de afficherContexte().________________________________________
	

	
} // FIn DE LA CLASSE LecteurJPADataSourceSpringTest.------------------------
