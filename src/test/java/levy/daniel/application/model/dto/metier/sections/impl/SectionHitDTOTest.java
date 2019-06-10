package levy.daniel.application.model.dto.metier.sections.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.model.dto.metier.sections.ISectionHitDTO;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl.ImporteurHit;

/**
 * CLASSE SectionHitDTOTest :<br/>
 * Test JUnit de la classe {@link SectionHitDTO}.<br/>
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
 * @since 10 juin 2019
 *
 */
public class SectionHitDTOTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;

	/**
	 * "unused".
	 */
	public static final String UNUSED 
		= "unused";

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
	 * DTO a tester.
	 */
	public static  ISectionHitDTO dto;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(SectionHitDTOTest.class);

	// *************************METHODES************************************/
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public SectionHitDTOTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * teste la méthode fournirEnTeteCsv().<br/>
	 * <ul>
	 * <li>garantit que fournirEnTeteCsv() ne retourne pas null.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirEnTeteCsv() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDTOTest - méthode testFournirEnTeteCsv() ********** ");
		}
		
		// METHODE A TESTER.
		String enteteCsv = null;
		
		if (dto != null) {
			
			enteteCsv = dto.fournirEnTeteCsv();
						
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(enteteCsv);
			}
			
			/* garantit que fournirEnTeteCsv() ne retourne pas null. */
			assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, enteteCsv);

		}
		
	} // Fin de testFournirEnTeteCsv().____________________________________
	

	
	/**
	 * teste la méthode fournirStringCsv().<br/>
	 * <ul>
	 * <li>garantit que fournirStringCsv() ne retourne pas null.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirStringCsv() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDTOTest - méthode testFournirStringCsv() ********** ");
		}
		
		// METHODE A TESTER.
		String stringCsv = null;
		
		if (dto != null) {
			
			stringCsv = dto.fournirStringCsv();
						
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(stringCsv);
			}
			
			/* garantit que fournirStringCsv() ne retourne pas null. */
			assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, stringCsv);
			
		}
		
	} // Fin de testFournirStringCsv().____________________________________
	

	
	/**
	 * teste la méthode fournirEnTeteColonne().<br/>
	 * <ul>
	 * <li>garantit que fournirEnTeteColonne(hors index) retourne "invalide".</li>
	 * <li>garantit que fournirEnTeteColonne(i) ne retourne pas null.</li>
	 * <li>garantit que fournirEnTeteColonne(i) retourne la bonne entete.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirEnTeteColonne() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDTOTest - méthode testFournirEnTeteColonne() ********** ");
		}
		
		// METHODE A TESTER.
		String enteteColonne0 = null;
		String enteteColonne1 = null;
		String enteteColonne2 = null;
		String enteteColonne3 = null;
		String enteteColonne50 = null;
		String enteteColonne133 = null;
		String enteteColonne134 = null;
		
		if (dto != null) {
			
			enteteColonne0 = (String) dto.fournirEnTeteColonne(0);
			enteteColonne1 = (String) dto.fournirEnTeteColonne(1);
			enteteColonne2 = (String) dto.fournirEnTeteColonne(2);
			enteteColonne3 = (String) dto.fournirEnTeteColonne(3);
			enteteColonne50 = (String) dto.fournirEnTeteColonne(50);
			enteteColonne133 = (String) dto.fournirEnTeteColonne(133);
			enteteColonne134 = (String) dto.fournirEnTeteColonne(134);
						
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println("enteteColonne0 : " + enteteColonne0);
				System.out.println("enteteColonne1 : " + enteteColonne1);
				System.out.println("enteteColonne2 : " + enteteColonne2);
				System.out.println("enteteColonne3 : " + enteteColonne3);
				System.out.println("enteteColonne50 : " + enteteColonne50);
				System.out.println("enteteColonne133 : " + enteteColonne133);				
				System.out.println("enteteColonne134 : " + enteteColonne134);
			}
			
			/* garantit que fournirEnTeteColonne() ne retourne pas null. */
			assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, enteteColonne1);
			
			/* garantit que fournirEnTeteColonne(hors index) retourne "invalide". */
			assertEquals(DOIT_RETOURNER_INVALIDE
					, "invalide"
						, enteteColonne134);
			
			/* garantit que fournirEnTeteColonne(i) retourne la bonne entete. */
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, "id"
						, enteteColonne0);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, "numDepartement"
						, enteteColonne1);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, "numSection"
						, enteteColonne2);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, "sens"
						, enteteColonne3);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, "mjmNmois04"
						, enteteColonne50);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, "zoneLibre4"
						, enteteColonne133);
			
		}
		
	} // Fin de testFournirEnTeteColonne().________________________________
	

	
	/**
	 * teste la méthode fournirValeurColonne().<br/>
	 * <ul>
	 * <li>garantit que fournirValeurColonne(hors index) retourne "invalide".</li>
	 * <li>garantit que fournirValeurColonne(i) ne retourne pas null.</li>
	 * <li>garantit que fournirValeurColonne(i) retourne la bonne valeur.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirValeurColonne() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDTOTest - méthode testFournirValeurColonne() ********** ");
		}
		
		// METHODE A TESTER.
		String valeurColonne0 = null;
		String valeurColonne1 = null;
		String valeurColonne2 = null;
		String valeurColonne3 = null;
		String valeurColonne50 = null;
		String valeurColonne133 = null;
		String valeurColonne134 = null;
		
		if (dto != null) {
			
			valeurColonne0 = (String) dto.fournirValeurColonne(0);
			valeurColonne1 = (String) dto.fournirValeurColonne(1);
			valeurColonne2 = (String) dto.fournirValeurColonne(2);
			valeurColonne3 = (String) dto.fournirValeurColonne(3);
			valeurColonne50 = (String) dto.fournirValeurColonne(50);
			valeurColonne133 = (String) dto.fournirValeurColonne(133);
			valeurColonne134 = (String) dto.fournirValeurColonne(134);
						
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println("valeurColonne0 : " + valeurColonne0);
				System.out.println("valeurColonne1 : " + valeurColonne1);
				System.out.println("valeurColonne2 : " + valeurColonne2);
				System.out.println("valeurColonne3 : " + valeurColonne3);
				System.out.println("valeurColonne50 : " + valeurColonne50);
				System.out.println("valeurColonne133 : " + valeurColonne133);				
				System.out.println("valeurColonne134 : " + valeurColonne134);
			}
			
			/* garantit que fournirValeurColonne() ne retourne pas null. */
			assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, valeurColonne1);
			
			/* garantit que fournirValeurColonne(hors index) retourne "invalide". */
			assertEquals(DOIT_RETOURNER_INVALIDE
					, "invalide"
						, valeurColonne134);
			
			/* garantit que fournirValeurColonne(i) retourne la bonne valeur. */
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, dto.getId()
						, valeurColonne0);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, dto.getNumDepartement()
						, valeurColonne1);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, dto.getNumSection()
						, valeurColonne2);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, dto.getSens()
						, valeurColonne3);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, dto.getMjmNmois04()
						, valeurColonne50);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, dto.getZoneLibre4()
						, valeurColonne133);
			
		}
		
	} // Fin de testFournirValeurColonne().________________________________


	
	/**
	 * Exécuté avant tout test de la méthode.<br/>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
				
		final Path fichierDonneesPath 
			= PATH_ABSOLU_TEST_JEUX_ESSAI.resolve("HITDIRA2017.txt");
		final File fichierDonnees = fichierDonneesPath.toFile();
		final Charset charsetAnsi = Charset.forName("Windows-1252");
		
		// METHODE A TESTER.
		final ImporteurHit importeurHIT = new ImporteurHit();
		final Map<Integer, ISectionHitDTO> fichierMapDTO 
			= importeurHIT.importerDTO(fichierDonnees, charsetAnsi);
		
		dto = fichierMapDTO.get(1);
		
	} // Fin de beforeClass()._____________________________________________


	
} // FIN DE LA CLASSE SectionHitDTOTest.-------------------------------------
