package levy.daniel.application.model.metier.sections.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.model.dto.metier.sections.ISectionHitDTO;
import levy.daniel.application.model.dto.metier.sections.impl.SectionHitDTO;
import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.sections.localisations.ILocalisationHit;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl.ImporteurHit;

/**
 * CLASSE SectionHitTest :<br/>
 * Test JUnit de la classe {@link SectionHit}<br/>
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
 * @since 29 juin 2019
 *
 */
public class SectionHitTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe SectionHit".
	 */
	public static final String CLASSE_SECTION_HIT 
		= "Classe SectionHit";
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;
	
	/**
	 * " - ".
	 */
	public static final String MOINS_ESPACE = " - ";

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
	 * DTO a tester.
	 */
	public static  ISectionHitDTO dto;

	/**
	 * OBJET METIER a tester.
	 */
	public static ISectionHit objetMetier;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(SectionHitTest.class);

	// *************************METHODES************************************/
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public SectionHitTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * teste la méthode toStringASCII().<br/>
	 * <ul>
	 * <li>garantit que toStringASCII() ne retourne pas null.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testToStringASCII() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitTest - méthode testToStringASCII() ********** ");
		}
		
		// METHODE A TESTER.
		String toStringASCIIDTO = null;
		String toStringASCIIObjet = null;
		
		if (objetMetier != null) {
			
			toStringASCIIDTO = dto.toStringASCII();
			toStringASCIIObjet = objetMetier.toStringASCII();
						
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(toStringASCIIDTO);
				System.out.println(toStringASCIIObjet);
			}
			
			/* garantit que toStringASCII() ne retourne pas null. */
			assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, toStringASCIIDTO);
			assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, toStringASCIIObjet);
			
			assertEquals("doit retourner 520 : "
					, 520
						, toStringASCIIDTO.length());
			assertEquals("doit retourner 520 : "
					, 520
						, toStringASCIIObjet.length());

		}
		
	} // Fin de testToStringASCII().____________________________________
	

	
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
			System.out.println("********** CLASSE SectionHitTest - méthode testFournirEnTeteCsv() ********** ");
		}
		
		// METHODE A TESTER.
		String enteteCsv = null;
		
		if (objetMetier != null) {
			
			enteteCsv = objetMetier.fournirEnTeteCsv();
						
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
			System.out.println("********** CLASSE SectionHitTest - méthode testFournirStringCsv() ********** ");
		}
		
		// METHODE A TESTER.
		String stringCsvDTO = null;
		String stringCsvObjet = null;
		
		if (objetMetier != null) {
			
			stringCsvDTO = dto.fournirStringCsv();
			stringCsvObjet = objetMetier.fournirStringCsv();
						
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(stringCsvDTO);
				System.out.println(stringCsvObjet);
			}
			
			/* garantit que fournirStringCsv() ne retourne pas null. */
			assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, stringCsvDTO);
			assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, stringCsvObjet);
			
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
			System.out.println("********** CLASSE SectionHitTest - méthode testFournirEnTeteColonne() ********** ");
		}
		
		// METHODE A TESTER.
		String enteteColonne0 = null;
		String enteteColonne1 = null;
		String enteteColonne2 = null;
		String enteteColonne3 = null;
		String enteteColonne50 = null;
		String enteteColonne133 = null;
		String enteteColonne134 = null;
		
		if (objetMetier != null) {
			
			enteteColonne0 = (String) objetMetier.fournirEnTeteColonne(0);
			enteteColonne1 = (String) objetMetier.fournirEnTeteColonne(1);
			enteteColonne2 = (String) objetMetier.fournirEnTeteColonne(2);
			enteteColonne3 = (String) objetMetier.fournirEnTeteColonne(3);
			enteteColonne50 = (String) objetMetier.fournirEnTeteColonne(50);
			enteteColonne133 = (String) objetMetier.fournirEnTeteColonne(133);
			enteteColonne134 = (String) objetMetier.fournirEnTeteColonne(134);
						
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
			System.out.println("********** CLASSE SectionHitTest - méthode testFournirValeurColonne() ********** ");
		}
		
		// METHODE A TESTER.
		String valeurColonne0 = null;
		String valeurColonne1 = null;
		String valeurColonne2 = null;
		String valeurColonne3 = null;
		String valeurColonne50 = null;
		String valeurColonne133 = null;
		String valeurColonne134 = null;
		
		if (objetMetier != null) {
			
			valeurColonne0 = (String) objetMetier.fournirValeurColonne(0);
			valeurColonne1 = (String) objetMetier.fournirValeurColonne(1);
			valeurColonne2 = (String) objetMetier.fournirValeurColonne(2);
			valeurColonne3 = (String) objetMetier.fournirValeurColonne(3);
			valeurColonne50 = (String) objetMetier.fournirValeurColonne(50);
			valeurColonne133 = (String) objetMetier.fournirValeurColonne(133);
			valeurColonne134 = (String) objetMetier.fournirValeurColonne(134);
						
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
					, objetMetier.getId()
						, valeurColonne0);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, objetMetier.getNumDepartement()
						, valeurColonne1);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, objetMetier.getNumSection()
						, valeurColonne2);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, objetMetier.getSens()
						, valeurColonne3);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, String.valueOf(objetMetier.getMjmNmois04())
						, valeurColonne50);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, objetMetier.getZoneLibre4()
						, valeurColonne133);
			
		}
		
	} // Fin de testFournirValeurColonne().________________________________
	
	
	
	/**
	 * teste la bonne construction de la LocalisationHit 
	 * lors de l'instanciation d'une sectionHit.<br/>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testLocalisation() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitTest - méthode testLocalisation() ********** ");
		}
		
		final ILocalisationHit localisation = objetMetier.getLocalisation();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(localisation.toString());
		}
		
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, localisation);
		
	} // Fin de testLocalisation().________________________________________
	

	
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
		
		// OBJET A TESTER.
		final ImporteurHit importeurHIT = new ImporteurHit();
		
		final Map<Integer, ISectionHit> fichierMapObjet 
			= importeurHIT.importerObjet(fichierDonnees, charsetAnsi);
		
		final SortedMap<Integer, SortedMap<Integer, String>> fichierImporteMap 
			= importeurHIT.getFichierImporteMap();
		
		final SortedMap<Integer, String> ligneMap = fichierImporteMap.get(1);
		final ISectionHit objetMap = new SectionHit(ligneMap);
		
		objetMetier = fichierMapObjet.get(1);
		
		
		final Map<Integer, ISectionHitDTO> fichierMapDTO 
			= importeurHIT.importerDTO(fichierDonnees, charsetAnsi);
		
		final ISectionHitDTO dtoMap = new SectionHitDTO(ligneMap);
		
		dto = fichierMapDTO.get(1);
		
		assertEquals("doivent être égaux : ", dto, dtoMap);

		
		assertEquals("doivent être égaux : ", objetMetier, objetMap);
		
	} // Fin de beforeClass()._____________________________________________


		
	/**
	 * teste fournirDateAvecAnneeSurDeuxChiffres(anneeDeuxChiffres).<br/>
	 * <ul>
	 * <li>garantit que fournirDateAvecAnneeSurDeuxChiffres(incorrect) 
	 * retourne null.</li>
	 * <li>garantit que fournirDateAvecAnneeSurDeuxChiffres(anneeDeuxChiffres) 
	 * retourne la bonne valeur.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirDateAvecAnneeSurDeuxChiffres() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitTest - méthode testFournirDateAvecAnneeSurDeuxChiffres() ********** ");
		}

		final String anneeDeuxChiffresIncorrect = "1a";
		final LocalDate dateIncorrect 
			= this.fournirDateAvecAnneeSurDeuxChiffres(anneeDeuxChiffresIncorrect);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("dateIncorrect : " + dateIncorrect);
		}
		
		/* garantit que fournirDateAvecAnneeSurDeuxChiffres(incorrect) 
		 * retourne null.*/
		assertNull(
				DOIT_RETOURNER_NULL
					, dateIncorrect);
		
		final String anneeDeuxChiffres = "19";
		final LocalDate date 
			= this.fournirDateAvecAnneeSurDeuxChiffres(anneeDeuxChiffres);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("date : " + date);
		}
		
		/* garantit que fournirDateAvecAnneeSurDeuxChiffres(anneeDeuxChiffres)
		 *  retourne la bonne valeur. */
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, LocalDate.of(2019, 1, 1)
						, date);
		
		
	} // Fin de testFournirDateAvecAnneeSurDeuxChiffres()._________________


	
	/**
	 * teste fournirInteger(String).<br/>
	 * <ul>
	 * <li>garantit que fournirInteger(incorrect) 
	 * retourne null.</li>
	 * <li>garantit que fournirInteger(correct) 
	 * retourne la bonne valeur.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirInteger() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitTest - méthode testFournirInteger() ********** ");
		}

		final String incorrect = "1a";
		final Integer integerIncorrect 
			= this.fournirInteger(incorrect);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("integerIncorrect : " + integerIncorrect);
		}
		
		/* garantit que fournirInteger(incorrect) 
		 * retourne null.*/
		assertNull(
				DOIT_RETOURNER_NULL
					, integerIncorrect);

		final String correct = "1235";
		final Integer integerCorrect 
			= this.fournirInteger(correct);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("integerCorrect : " + integerCorrect);
		}
		
		/* garantit que fournirInteger(correct) 
		 * retourne la bonne valeur.*/
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, Integer.valueOf(correct)
						, integerCorrect);
		
		
	} // Fin de testFournirInteger().______________________________________
	
	

	/**
	 * teste fournirAnneeDeuxChiffresAPartirDate(LocalDate).<br/>
	 * <ul>
	 * <li>garantit que fournirAnneeDeuxChiffresAPartirDate(null) 
	 * retourne null.</li>
	 * <li>garantit que fournirAnneeDeuxChiffresAPartirDate(correct) 
	 * retourne la bonne valeur.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirAnneeDeuxChiffresAPartirDate() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitTest - méthode testFournirAnneeDeuxChiffresAPartirDate() ********** ");
		}
		
		/* garantit que fournirAnneeDeuxChiffresAPartirDate(null) 
		 * retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
					, this.fournirAnneeDeuxChiffresAPartirDate(null));
		
		final LocalDate dateCorrect = LocalDate.of(2019, 7, 27);
		final String resultat 
			= this.fournirAnneeDeuxChiffresAPartirDate(dateCorrect);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("resultat : " + resultat);
		}
		
		/* garantit que fournirAnneeDeuxChiffresAPartirDate(correct) 
		 * retourne la bonne valeur. */
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "19"
						, resultat);
		
	} // Fin de testFournirAnneeDeuxChiffresAPartirDate()._________________
	
	
	
	/**
	 * teste la méthode completerAvecZerosAGauche(String, int).<br/>
	 * <ul>
	 * <li>garantit que completerAvecZerosAGauche(String, int) 
	 * retourne le bon résultat.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCompleterAvecZerosAGauche() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitTest - méthode testCompleterAvecZerosAGauche() ********** ");
		}
		
		final String chaine = "25";
		final int taille = 4;
		
		final String resultat = this.completerAvecZerosAGauche(chaine, taille);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("chaine initiale : " + chaine);
			System.out.println("taille souhaitée : " + taille);
			System.out.println("chaine complétée : " + resultat);
		}
		
		/* garantit que completerAvecZerosAGauche(String, int) 
		 * retourne le bon résultat. */
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR, "0025", resultat);
		
	} // Fin de testCompleterAvecZerosAGauche().___________________________
	
	
	
	/**
	 * retourne une LocalDate positionnée au 1er Janvier 20AA 
	 * ou AA est l'année sur deux chiffres fournie dans pString.<br/>
	 * <br/>
	 * - retourne null si pString est blank.<br/>
	 * - retourne null si pString n'est pas homogène 
	 * à une année sur deux chiffres.<br/>
	 * <br/>
	 *
	 * @param pString : String : année sur deux chiffres comme "19" pour 2019.
	 * 
	 * @return : LocalDate : 1er Janvier 20AA.<br/>
	 */
	private LocalDate fournirDateAvecAnneeSurDeuxChiffres(
			final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		LocalDate resultat = null;
		
		final String motifAnneeSurDeuxChiffres = "\\d\\d";
		final Pattern patternAnneeSurDeuxChiffres 
			= Pattern.compile(motifAnneeSurDeuxChiffres); 
		final Matcher matcher = patternAnneeSurDeuxChiffres.matcher(pString);
		
		/* retourne null si pString n'est pas homogène 
		 * à une année sur deux chiffres. */
		if (!matcher.matches()) {
			
			final String message 
				= CLASSE_SECTION_HIT 
				+ MOINS_ESPACE
				+ "méthode fournirDateAvecAnneeSurDeuxChiffres(String)"
				+ MOINS_ESPACE 
				+ "pString passé en paramètre n'est pas homogène à une année sur deux chiffres : " 
				+ pString;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			return null;
		}
		
		final String anneeString = "20" + pString;
		
		final Integer annee = Integer.valueOf(anneeString);
		
		resultat = LocalDate.of(annee, 1, 1);
		
		return resultat;
		
	} // Fin de fournirDateAvecAnneeSurDeuxChiffres(...).__________________

	
	
	/**
	 * retourne un Integer à partir de la chaine fournie dans pString.<br/>
	 * <br/>
	 * - retourne null si pString est blank.<br/>
	 * - retourne null si pString n'est pas homogène 
	 * à un entier.<br/>
	 * <br/>
	 *
	 * @param pString : String : String homogène à un Integer.
	 * 
	 * @return : Integer.<br/>
	 */
	private Integer fournirInteger(final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		Integer resultat = null;
		
		final String motifInteger = "\\d+";
		final Pattern patternInteger 
			= Pattern.compile(motifInteger); 
		final Matcher matcher = patternInteger.matcher(pString);
		
		/* retourne null si pString n'est pas homogène 
		 * à un entier. */
		if (!matcher.matches()) {
			
			final String message 
				= CLASSE_SECTION_HIT 
				+ MOINS_ESPACE
				+ "méthode fournirInteger(String)"
				+ MOINS_ESPACE 
				+ "pString passé en paramètre n'est pas homogène à un entier : " 
				+ pString;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			return null;
		}

		resultat = Integer.valueOf(pString);
		
		return resultat;
		
	} // Fin de fournirInteger(...)._______________________________________

	
	
	/**
	 * retourne sous forme de String les deux derniers chiffres 
	 * de l'année d'une LocalDate.<br/>
	 * Par exemple : "19" pour 2019.<br/>
	 * <br/>
	 *
	 * @param pDate : LocalDate.
	 * 
	 * @return : String : 
	 * deux derniers chiffres de l'année de la LocalDate.<br/>
	 */
	private String fournirAnneeDeuxChiffresAPartirDate(final LocalDate pDate) {
		
		/* retourne null si pDate == null. */
		if (pDate == null) {
			return null;
		}
		
		String resultat = null;
		
		final String motifFormatAnneeSurDeuxChiffres = "yy";
		
		final DateTimeFormatter formatterAnneeSurDeuxChiffres 
			= DateTimeFormatter.ofPattern(motifFormatAnneeSurDeuxChiffres);
		
		resultat = pDate.format(formatterAnneeSurDeuxChiffres);
		
		return resultat;
		
	} // Fin de fournirAnneeDeuxChiffresAPartirDate(...).__________________


	
	/**
	 * <b>retourne la chaine de caractères pString complétée 
	 * avec des zéros à gauche pour atteindre pTaille</b>.<br/>
	 * <ul>
	 * <li>Par exemple : retourne "0025" 
	 * si pString == "25" et pTaille == 4.</li>
	 * <li>retourne pString inchangée si sa longueur >= pTaille.</li>
	 * </ul>
	 * - retourne null si pTaille == 0.<br/>
	 * - retourne null si pString == null.<br/>
	 * <br/>
	 *
	 * @param pString : String : 
	 * chaine de caractères à compléter avec des zéros à gauche.
	 * @param pTaille : 
	 * taille finale de la chaine complétée avec des zéros à gauche.
	 * 
	 * @return : String : 
	 * chaine de caractère pString complétée avec des zéros à gauche 
	 * pour atteindre pTaille.<br/>
	 */
	private String completerAvecZerosAGauche(
			final String pString, final int pTaille) {
		
		/* retourne null si pTaille == 0. */
		if (pTaille == 0) {
			return null;
		}
		
		/* retourne null si pString == null. */
		if (pString == null) {
			return null;
		}
		
		final int tailleString = pString.length();
		
		/* retourne pString inchangée si sa longueur >= pTaille. */
		if (tailleString >= pTaille) {
			return pString;
		}
		
		String resultat = null;
		
		final int nombreZeros = pTaille - tailleString;
		
		resultat = pString;
		
		for (int i = 0; i < nombreZeros; i++) {
			resultat = "0" + resultat;
		}
		
		return resultat;
		
	} // Fin de completerAvecZerosAGauche(...).____________________________

	
	
} // FIN DE LA CLASSE SectionHitTest.----------------------------------------
