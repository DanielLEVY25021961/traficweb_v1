package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.metier.sections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.nio.file.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.IGestionnaireRG;

/**
 * CLASSE SectionHitGestionnaireRGTest :<br/>
 * Test JUnit de la classe {@link SectionHitGestionnaireRG}.<br/>
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
 * @since 15 août 2019
 *
 */
public class SectionHitGestionnaireRGTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;

	/**
	 * "doit retourner null : ".
	 */
	public static final String DOIT_RETOURNER_NULL 
		= "doit retourner null : ";
	
	/**
	 * "ne doit pas retourner null : ".
	 */
	public static final String NE_DOIT_PAS_RETOURNER_NULL 
		= "ne doit pas retourner null : ";

	/**
	 * "doit retourner la bonne valeur : ".<br/>
	 */
	public static final String DOIT_RETOURNER_BONNE_VALEUR 
		= "doit retourner la bonne valeur : ";

	/**
	 * "Doit retourner la même instance : ".
	 */
	public static final String DOIT_RETOURNER_MEME_INSTANCE 
		= "Doit retourner la même instance : ";

	/**
	 * "doit retourner invalide : ".
	 */
	public static final String DOIT_RETOURNER_INVALIDE 
		= "doit retourner invalide : ";
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";

	/**
	 * IGestionnaireRG.
	 */
	public static IGestionnaireRG gestionnaireRG;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitGestionnaireRGTest.class);
	
	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public SectionHitGestionnaireRGTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * Teste la méthode getEnTeteCsv().<br/>
	 * <ul>
	 * <li>garantit que getEnTeteCsv() ne retourne pas null.</li>
	 * <li>garantit que getEnTeteCsv() retourne la bonne valeur.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetEnTeteCsv() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnaireRGTest - méthode testGetEnTeteCsv() ********** ");
		}
		
		// TEST DE LA METHODE. ***********************
		final String enTeteCsv = gestionnaireRG.getEnTeteCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("enTeteCsv : " + enTeteCsv);
			System.out.println();
		}

		/* garantit que getEnTeteCsv() ne retourne pas null. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, enTeteCsv);
		
		/* garantit que getEnTeteCsv() retourne la bonne valeur. */
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
				, "id;Actif;activité des contrôles sur l'attribut;activité de la RG;RG implémentée;clé du type de contrôle;type de contrôle;Message d'erreur;Objet Métier concerné;Attribut concerné;Classe implémentant la RG;Méthode implémentant la RG;properties;clé;"
				, enTeteCsv);
		
	} // Fin de testGetEnTeteCsv().________________________________________

	
	
	/**
	 * Teste la méthode afficherListeRGImplementeesCsv().<br/>
	 * <ul>
	 * <li>garantit que afficherListeRGImplementeesCsv() ne retourne pas null.</li>
	 * <li>garantit que afficherListeRGImplementeesCsv() retourne la bonne valeur.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testAfficherListeRGImplementeesCsv() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnaireRGTest - méthode testAfficherListeRGImplementeesCsv() ********** ");
		}

		// TEST DE LA METHODE. ***********************
		final String listeRGImplementeesString 
			= gestionnaireRG.afficherListeRGImplementeesCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println();
			System.out.println("LISTE DES RG IMPLEMENTEES : ");
			System.out.println(listeRGImplementeesString);
			
		}

		/* garantit que afficherListeRGImplementeesCsv() ne retourne pas null. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, listeRGImplementeesString);

	} // Fin de testAfficherListeRGImplementeesCsv().______________________
	

	
	/**
	 * Teste la méthode getPathAbsoluPropertiesRG().<br/>
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetPathAbsoluPropertiesRG() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnaireRGTest - méthode testGetPathAbsoluPropertiesRG() ********** ");
		}

		// TEST DE LA METHODE. ***********************
		final Path pathAbsoluPropertiesRG
			= gestionnaireRG.getPathAbsoluPropertiesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println();
			System.out.println("PATH DU PROPERTIES CONTENANT LES RG :");
			System.out.println(gestionnaireRG.getPathAbsoluPropertiesRG());	
			
		}
		
		/* garantit que getPathAbsoluPropertiesRG() ne retourne pas null. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, pathAbsoluPropertiesRG);
				
	} // Fin de testGetPathAbsoluPropertiesRG().___________________________
	

	
	/**
	 * Exécuté avant tout test de la méthode.<br/>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		
		gestionnaireRG = new SectionHitGestionnaireRG();
		
	} // Fin de beforeClass()._____________________________________________


	
} // FIN DE LA CLASSE SectionHitGestionnaireRGTest.--------------------------
