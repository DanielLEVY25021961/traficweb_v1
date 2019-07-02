package levy.daniel.application.model.metier.sections.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.metier.sections.ISectionHit;

/**
 * CLASSE SectionHitTest :<br/>
 * Test JUnit de la classe <br/>
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
	 * " - ".
	 */
	public static final String MOINS_ESPACE = " - ";
	
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
	 * new SectionHit().
	 */
	public static final ISectionHit SECTION_HIT = new SectionHit();
	
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

	
	
} // FIN DE LA CLASSE SectionHitTest.----------------------------------------
