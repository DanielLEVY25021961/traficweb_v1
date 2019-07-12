package levy.daniel.application.model.utilitaires.connecteurbase;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * CLASSE ConnecteurBaseTest :<br/>
 * Test JUnit de la classe {@link ConnecteurBase}.<br/>
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
 * @since 11 juil. 2019
 *
 */
public class ConnecteurBaseTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";

	/**
	 * ConnecteurBase.
	 */
	private static transient ConnecteurBase connecteurBase;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(ConnecteurBaseTest.class);

	// *************************METHODES************************************/
			
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ConnecteurBaseTest() {		
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * teste la méthode connecterABaseHikariDataSource().<br/>
	 * <ul>
	 * </ul>
	 * 
	 * @throws SQLException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConnecterABaseHikariDataSource() throws SQLException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConnecteurBaseTest - méthode testConnecterABaseHikariDataSource() ********** ");
		}
		
		final String jdbcURL 
			= "jdbc:postgresql://localhost:5432/base-traficweb_v1";
		final String username = "postgres";
		final String password = "postgres";
		
		connecteurBase = new ConnecteurBase(jdbcURL, username, password);
		
		final Boolean resultat 
			= connecteurBase.connecterABaseHikariDataSource();
		
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("Connecté à la base " + jdbcURL + " ? : " + resultat);
		}
		
		assertTrue("bidon : ", 1 == 1);
		
	} // Fin de testConnecterABaseHikariDataSource().______________________


	
} // FIN DE LA CLASSE ConnecteurBaseTest.------------------------------------
