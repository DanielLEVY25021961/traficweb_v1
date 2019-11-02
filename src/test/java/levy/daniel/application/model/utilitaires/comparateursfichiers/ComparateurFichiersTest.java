package levy.daniel.application.model.utilitaires.comparateursfichiers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * CLASSE ComparateurFichiersTest :<br/>
 * Teste JUnit de la classe {@link ComparateurFichiers}.<br/>
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
 * @since 31 oct. 2019
 *
 */
public class ComparateurFichiersTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * AFFICHAGE_GENERAL : Boolean :<br/>
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;
	
	/**
	 * "unused".
	 */
	public static final String UNUSED 
		= "unused";
	
	/**
	 * "fichiers avec le même contenu : ".
	 */
	public static final String FICHIERS_AVEC_MEME_CONTENU = 
			"fichiers avec le même contenu : ";
	
	/**
	 * "rapport de comparaison : ".
	 */
	public static final String RAPPORT_COMPARAISON 
		= "rapport de comparaison : ";
	
	/**
	 * "la comparaison doit retourner false : ".
	 */
	public static final String COMPARAISON_DOIT_RETOURNER_FALSE 
		= "la comparaison doit retourner false : ";
	
	/**
	 * "la comparaison doit retourner true : ".
	 */
	public static final String COMPARAISON_DOIT_RETOURNER_TRUE 
		= "la comparaison doit retourner true : ";

	/**
	 * "le rapport de comparaison ne doit pas être null : ".
	 */
	public static final String RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL 
		= "le rapport de comparaison ne doit pas être null : ";

	/**
	 * "le rapport de comparaison doit être null : ".
	 */
	public static final String RAPPORT_COMPARAISON_DOIT_ETRE_NULL 
		= "le rapport de comparaison doit être null : ";

	/**
	 * "src/test/resources/model/utilitaires/comparateursfichiers/HITDIRCE2017.txt".
	 */
	public static final String CHEMIN_HIT_DIRCE2017_ANSI 
		= "src/test/resources/model/utilitaires/comparateursfichiers/HITDIRCE2017.txt";

	/**
	 * "src/test/resources/model/utilitaires/comparateursfichiers/HITDIRCE2017_UTF8.txt".
	 */
	public static final String CHEMIN_HIT_DIRCE2017_UTF8 
		= "src/test/resources/model/utilitaires/comparateursfichiers/HITDIRCE2017_UTF8.txt";
	
	/**
	 * "src/test/resources/model/utilitaires/comparateursfichiers/HITDIRCE2017_renomme.txt".
	 */
	public static final String CHEMIN_HIT_DIRCE2017_RENOMME 
		= "src/test/resources/model/utilitaires/comparateursfichiers/HITDIRCE2017_renomme.txt";

	/**
	 * "src/test/resources/model/utilitaires/comparateursfichiers/HITDIRCE2017_transforme.txt".<br/>
	 * DIRCE2017.txt avec une ligne amoindrie d'un caractère.
	 */
	public static final String CHEMIN_HIT_DIRCE2017_TRANSFORME 
		= "src/test/resources/model/utilitaires/comparateursfichiers/HITDIRCE2017_transforme.txt";

	/**
	 * "src/test/resources/model/utilitaires/comparateursfichiers/HITDIRCE2017_transforme2.txt".<br/>
	 * DIRCE2017.txt avec un caractère changé à la première ligne.
	 */
	public static final String CHEMIN_HIT_DIRCE2017_TRANSFORME_2 
		= "src/test/resources/model/utilitaires/comparateursfichiers/HITDIRCE2017_transforme2.txt";
	
	/**
	 * "src/test/resources/model/utilitaires/comparateursfichiers/HITDIRMED2017.txt".
	 */
	public static final String CHEMIN_HIT_DIRMED2017 
		= "src/test/resources/model/utilitaires/comparateursfichiers/HITDIRMED2017.txt";
	
	/**
	 * "src/test/resources/model/utilitaires/comparateursfichiers/foo.txt".
	 */
	public static final String CHEMIN_FICHIER_INEXISTANT 
		= "src/test/resources/model/utilitaires/comparateursfichiers/foo.txt";
	
	/**
	 * "src/test/resources/model/utilitaires/comparateursfichiers".
	 */
	public static final String CHEMIN_REPERTOIRE 
		= "src/test/resources/model/utilitaires/comparateursfichiers";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings(UNUSED)
	private static final Log LOG 
		= LogFactory.getLog(ComparateurFichiersTest.class);

	// *************************METHODES************************************/
	   
	/**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public ComparateurFichiersTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * teste la méthode <code>compareFichiersLigneALigne(
	 * File pFile1, Charset charset1, File pFile2, Charset charset2)</code>.<br/>
	 * file1 = null et file2=HITDIRCE2017.txt
	 * <ul>
	 * <li>garantit que compareFichiersLigneALigne(null, file2) 
	 * retourne false.</li>
	 * <li>garantit que compareFichiersLigneALigne(null, file2) 
	 * génère un rapport de comparaison.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testcompareFichiersLigneALigneAvecNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ComparateurFichiersTest - méthode testcompareFichiersLigneALigneAvecNull() ********** ");
		}
		
		// VALEURS A TESTER **********************************************
		final File file1 = null;
		final File file2 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultat 
			= ComparateurFichiers.compareFichiersLigneALigne(
					file1, null, file2, null);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultat);
			System.out.println(RAPPORT_COMPARAISON + ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareFichiersLigneALigne(null, null, file2, null) 
		 * retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultat);
		
		/* garantit que compareFichiersLigneALigne(null, null, file2, null) 
		 * génère un rapport de comparaison. */
		assertNotNull(RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL
				, ComparateurFichiers.getRapportComparaison());
		
		
		// VALEURS A TESTER **********************************************
		final File file3 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		final File file4 = null;
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultatInverse 
			= ComparateurFichiers
				.compareFichiersLigneALigne(file3, null, file4, null);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultatInverse);
			System.out.println(RAPPORT_COMPARAISON 
					+ ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareFichiersLigneALigne(null, null, file2, null) 
		 * retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultatInverse);
		
		/* garantit que compareContenuFichiers(null, null, file2, null) 
		 * génère un rapport de comparaison. */
		assertNotNull(RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL
				, ComparateurFichiers.getRapportComparaison());
		
	} // Fin de testcompareFichiersLigneALigneAvecNull().__________________
	
	
	
	/**
	 * teste la méthode compareFichiersLigneALigne(
	 * File pFile1, Charset charset1, File pFile2, Charset charset2).<br/>
	 * file1 = foo et file2=HITDIRCE2017.txt
	 * <ul>
	 * <li>garantit que compareFichiersLigneALigne(inexistant, file2) 
	 * retourne false.</li>
	 * <li>garantit que compareFichiersLigneALigne(inexistant, file2) 
	 * génère un rapport de comparaison.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testcompareFichiersLigneALigneInexistant() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ComparateurFichiersTest - méthode testcompareFichiersLigneALigneInexistant() ********** ");
		}
		
		// VALEURS A TESTER **********************************************
		final File file1 = new File(CHEMIN_FICHIER_INEXISTANT);
		final File file2 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultat 
			= ComparateurFichiers.compareFichiersLigneALigne(file1, null, file2, null);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultat);
			System.out.println(RAPPORT_COMPARAISON + ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareFichiersLigneALigne(inexistant, null, file2, null)
		 *  retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultat);
		
		/* garantit que compareFichiersLigneALigne(inexistant, null, file2, null) 
		 * génère un rapport de comparaison. */
		assertNotNull(RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL
				, ComparateurFichiers.getRapportComparaison());
		
		
		// VALEURS A TESTER **********************************************
		final File file3 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		final File file4 = new File(CHEMIN_FICHIER_INEXISTANT);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultatInverse 
			= ComparateurFichiers.compareFichiersLigneALigne(file3, null, file4, null);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultatInverse);
			System.out.println(RAPPORT_COMPARAISON 
					+ ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareFichiersLigneALigne(inexistant, null, file2, null) 
		 * retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultatInverse);
		
		/* garantit que compareFichiersLigneALigne(inexistant, null, file2, null) 
		 * génère un rapport de comparaison. */
		assertNotNull(RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL
				, ComparateurFichiers.getRapportComparaison());
		
	} // Fin de testcompareFichiersLigneALigneInexistant().________________
	
	
	
	/**
	 * teste la méthode compareFichiersLigneALigne(
	 * File pFile1, Charset charset1, File pFile2, Charset charset2).<br/>
	 * file1 = repertoire et file2=HITDIRCE2017.txt
	 * <ul>
	 * <li>garantit que compareFichiersLigneALigne(repertoire, file2) 
	 * retourne false.</li>
	 * <li>garantit que compareFichiersLigneALigne(repertoire, file2) 
	 * génère un rapport de comparaison.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testcompareFichiersLigneALigneRepertoire() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ComparateurFichiersTest - méthode testcompareFichiersLigneALigneRepertoire() ********** ");
		}
		
		// VALEURS A TESTER **********************************************
		final File file1 = new File(CHEMIN_REPERTOIRE);
		final File file2 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultat 
			= ComparateurFichiers.compareFichiersLigneALigne(file1, null, file2, null);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultat);
			System.out.println(RAPPORT_COMPARAISON + ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareFichiersLigneALigne(repertoire, null, file2, null)
		 *  retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultat);
		
		/* garantit que compareFichiersLigneALigne(repertoire, null, file2, null) 
		 * génère un rapport de comparaison. */
		assertNotNull(RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL
				, ComparateurFichiers.getRapportComparaison());
		
		
		// VALEURS A TESTER **********************************************
		final File file3 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		final File file4 = new File(CHEMIN_REPERTOIRE);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultatInverse 
			= ComparateurFichiers.compareFichiersLigneALigne(file3, null, file4, null);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultatInverse);
			System.out.println(RAPPORT_COMPARAISON 
					+ ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareFichiersLigneALigne(inexistant, null, file2, null) 
		 * retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultatInverse);
		
		/* garantit que compareFichiersLigneALigne(inexistant, null, file2, null) 
		 * génère un rapport de comparaison. */
		assertNotNull(RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL
				, ComparateurFichiers.getRapportComparaison());
		
	} // Fin de testcompareFichiersLigneALigneRepertoire().________________

	
	
	/**
	 * teste la méthode compareFichiersLigneALigne(
	 * File pFile1, Charset charset1, File pFile2, Charset charset2).<br/>
	 * file2 = file1
	 * <ul>
	 * <li>garantit que compareFichiersLigneALigne(File pFile, File pFile) 
	 * retourne toujours true.</li>
	 * <li>garantit que compareFichiersLigneALigne(File pFile, File pFile) 
	 * ne génère pas de rapport de comparaison..</li>
	 * </ul>
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testcompareFichiersLigneALigneEquals() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ComparateurFichiersTest - méthode testcompareFichiersLigneALigneEquals() ********** ");
		}
		
		// VALEURS A TESTER **********************************************
		final File file1 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		final File file2 = file1;
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultat 
			= ComparateurFichiers.compareContenuFichiers(file1, file2);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultat);
			System.out.println(RAPPORT_COMPARAISON 
					+ ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareContenuFichiers(file, file) 
		 * retourne true. */
		assertTrue(COMPARAISON_DOIT_RETOURNER_TRUE, resultat);
		
		/* garantit que compareContenuFichiers(file, file) 
		 * ne génère PAS un rapport de comparaison. */
		assertNull(RAPPORT_COMPARAISON_DOIT_ETRE_NULL
				, ComparateurFichiers.getRapportComparaison());

	} // Fin de testcompareFichiersLigneALigneEquals().____________________

	
		
	/**
	 * teste la méthode compareFichiersLigneALigne(
	 * File pFile1, Charset charset1, File pFile2, Charset charset2).<br/>
	 * file2 = file1_renomme
	 * <ul>
	 * <li>garantit que compareFichiersLigneALigne(File pFile1, File pFile2) 
	 * retourne true et ne génère par de rapport de comparaison 
	 * si pFile1 a le même contenu que pFile2.</li>
	 * <li>garantit que compareFichiersLigneALigne(File pFile1, File pFile2) 
	 * ne prend pas en compte les noms des fichiers pour savoir 
	 * si leurs contenus sont égaux.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testcompareFichiersLigneALigne() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ComparateurFichiersTest - méthode testcompareFichiersLigneALigne() ********** ");
		}
		
		// VALEURS A TESTER **********************************************
		final File file1 = new File(CHEMIN_HIT_DIRCE2017_RENOMME);
		final File file2 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultat 
			= ComparateurFichiers.compareFichiersLigneALigne(file1, StandardCharsets.ISO_8859_1, file2, StandardCharsets.ISO_8859_1);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultat);
			System.out.println(RAPPORT_COMPARAISON 
					+ ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareFichiersLigneALigne(file, file_renomme) 
		 * retourne true. */
		assertTrue(COMPARAISON_DOIT_RETOURNER_TRUE, resultat);
		
		/* garantit que compareFichiersLigneALigne(file, file_renomme) 
		 * ne génère PAS un rapport de comparaison. */
		assertNull(RAPPORT_COMPARAISON_DOIT_ETRE_NULL
				, ComparateurFichiers.getRapportComparaison());

	} // Fin de testcompareFichiersLigneALigne().__________________________

	
	
	/**
	 * teste la méthode compareFichiersLigneALigne(
	 * File pFile1, Charset charset1, File pFile2, Charset charset2).<br/>
	 * file1 = HITDIRCE2017.txt et file2=HITDIRMED2017.txt
	 * <ul>
	 * <li>garantit que compareFichiersLigneALigne(file1, file2 différent) 
	 * retourne false si pFile1 n'a PAS le même contenu que pFile2.</li>
	 * <li>garantit que compareFichiersLigneALigne(file1, file2 différent) 
	 * génère un rapport de comparaison.</li>
	 * <li>garantit que compareFichiersLigneALigne(file1, file2 différent) 
	 * détecte un nombre de lignes différent.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testcompareFichiersLigneALigneDifferentsNombreLignes() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ComparateurFichiersTest - méthode testcompareFichiersLigneALigneDifferentsNombreLignes() ********** ");
		}
		
		// VALEURS A TESTER **********************************************
		final File file1 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		final File file2 = new File(CHEMIN_HIT_DIRMED2017);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultat 
			= ComparateurFichiers.compareFichiersLigneALigne(file1, StandardCharsets.ISO_8859_1, file2, StandardCharsets.ISO_8859_1);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultat);
			System.out.println(RAPPORT_COMPARAISON 
					+ ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareFichiersLigneALigne(file1, null, file2 different, null) 
		 * retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultat);
		
		/* garantit que compareFichiersLigneALigne(file1, null, file2 different, null) 
		 * génère un rapport de comparaison. */
		assertNotNull(RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL
				, ComparateurFichiers.getRapportComparaison());

	} // Fin de testcompareFichiersLigneALigneDifferentsNombreLignes().____

	
	
	/**
	 * teste la méthode compareFichiersLigneALigne(
	 * File pFile1, Charset charset1, File pFile2, Charset charset2).<br/>
	 * file2 = file1 mais un caractère enlevé à la ligne 1
	 * <ul>
	 * <li>garantit que compareFichiersLigneALigne(file1, file2 différent) 
	 * retourne false si pFile1 n'a PAS le même contenu que pFile2.</li>
	 * <li>garantit que compareFichiersLigneALigne(file1, file2 différent) 
	 * génère un rapport de comparaison.</li>
	 * <li>garantit que compareFichiersLigneALigne(file1, file2 différent) 
	 * détecte une ligne de longueur différente.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testcompareFichiersLigneALigneDifferentesLignes() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ComparateurFichiersTest - méthode testcompareFichiersLigneALigneDifferentesLignes() ********** ");
		}
		
		// VALEURS A TESTER **********************************************
		final File file1 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		final File file2 = new File(CHEMIN_HIT_DIRCE2017_TRANSFORME);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultat 
			= ComparateurFichiers.compareFichiersLigneALigne(file1, StandardCharsets.ISO_8859_1, file2, StandardCharsets.ISO_8859_1);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultat);
			System.out.println(RAPPORT_COMPARAISON 
					+ ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareFichiersLigneALigne(file1, null, file2 different, null) 
		 * retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultat);
		
		/* garantit que compareFichiersLigneALigne(file1, null, file2 different, null) 
		 * génère un rapport de comparaison. */
		assertNotNull(RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL
				, ComparateurFichiers.getRapportComparaison());

	} // Fin de testcompareFichiersLigneALigneDifferentesLignes()._________

	
	
	/**
	 * teste la méthode compareFichiersLigneALigne(
	 * File pFile1, Charset charset1, File pFile2, Charset charset2).<br/>
	 * file2 = file1 mais un caractère changé à la ligne 1
	 * <ul>
	 * <li>garantit que compareFichiersLigneALigne(file1, file2 différent) 
	 * retourne false si pFile1 n'a PAS le même contenu que pFile2.</li>
	 * <li>garantit que compareFichiersLigneALigne(file1, file2 différent) 
	 * génère un rapport de comparaison.</li>
	 * <li>garantit que compareFichiersLigneALigne(file1, file2 différent) 
	 * détecte une ligne différente.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testcompareFichiersLigneALigneDifferentesLignes2() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ComparateurFichiersTest - méthode testcompareFichiersLigneALigneDifferentesLignes2() ********** ");
		}
		
		// VALEURS A TESTER **********************************************
		final File file1 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		final File file2 = new File(CHEMIN_HIT_DIRCE2017_TRANSFORME_2);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultat 
			= ComparateurFichiers.compareFichiersLigneALigne(file1, StandardCharsets.ISO_8859_1, file2, StandardCharsets.ISO_8859_1);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultat);
			System.out.println(RAPPORT_COMPARAISON 
					+ ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareFichiersLigneALigne(file1, null, file2 different, null) 
		 * retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultat);
		
		/* garantit que compareFichiersLigneALigne(file1, null, file2 different, null) 
		 * génère un rapport de comparaison. */
		assertNotNull(RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL
				, ComparateurFichiers.getRapportComparaison());

	} // Fin de testcompareFichiersLigneALigneDifferentesLignes2().________

	
	
	/**
	 * teste la méthode compareFichiersLigneALigne(
	 * File pFile1, Charset charset1, File pFile2, Charset charset2).<br/>
	 * file1 = HITDIRCE2017.txt et file2 = HITDIRMED2017.txt
	 * <ul>
	 * <li>garantit que compareFichiersLigneALigne(file1, file2 différent) 
	 * retourne false si pFile1 n'a PAS le même contenu que pFile2.</li>
	 * <li>garantit que compareFichiersLigneALigne(file1, file2 différent) 
	 * génère un rapport de comparaison.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testcompareFichiersLigneALigneDifferents() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ComparateurFichiersTest - méthode testcompareFichiersLigneALigneDifferents() ********** ");
		}
		
		// VALEURS A TESTER **********************************************
		final File file1 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		final File file2 = new File(CHEMIN_HIT_DIRMED2017);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultat 
			= ComparateurFichiers.compareFichiersLigneALigne(file1, StandardCharsets.ISO_8859_1, file2, StandardCharsets.ISO_8859_1);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultat);
			System.out.println(RAPPORT_COMPARAISON 
					+ ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareFichiersLigneALigne(file1, null, file2 different, null) 
		 * retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultat);
		
		/* garantit que compareFichiersLigneALigne(file1, null, file2 different, null) 
		 * génère un rapport de comparaison. */
		assertNotNull(RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL
				, ComparateurFichiers.getRapportComparaison());

	} // Fin de testcompareFichiersLigneALigneDifferents().________________

	
	
	/**
	 * teste la méthode compareFichiersLigneALigne(
	 * File pFile1, Charset charset1, File pFile2, Charset charset2).<br/>
	 * file1 en ANSI et file2=file1 en UTF8
	 * <ul>
	 * <li>garantit que compareFichiersLigneALigne(file1, charset1, file1, charset2) 
	 * retourne false .</li>
	 * <li>garantit que compareFichiersLigneALigne(file1, charset1, file1, charset2) 
	 * génère un rapport de comparaison.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testcompareFichiersLigneALigneMauvaisCharset() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ComparateurFichiersTest - méthode testcompareFichiersLigneALigneMauvaisCharset() ********** ");
		}
		
		// VALEURS A TESTER **********************************************
		final File file1 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		final File file2 = new File(CHEMIN_HIT_DIRCE2017_UTF8);
		// ***************************************************************
		
		
		// METHODE A TESTER *************************************************
		final boolean resultat 
			= ComparateurFichiers.compareFichiersLigneALigne(file1, StandardCharsets.ISO_8859_1, file2, StandardCharsets.UTF_8);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("file1.length() : " + file1.length());
			System.out.println("file2.length() : " + file2.length());
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultat);
			System.out.println(RAPPORT_COMPARAISON 
					+ ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareFichiersLigneALigne(file1, null, file2 different, null) 
		 * retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultat);
		
		/* garantit que compareFichiersLigneALigne(file1, null, file2 different, null) 
		 * génère un rapport de comparaison. */
		assertNotNull(RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL
				, ComparateurFichiers.getRapportComparaison());

	} // Fin de testcompareFichiersLigneALigneMauvaisCharset().____________
	
	
	
	/**
	 * teste la méthode compareContenuFichiers(File pFile1, File pFile2).<br/>
	 * file1 = null et file2=HITDIRCE2017.txt
	 * <ul>
	 * <li>garantit que compareContenuFichiers(null, file2) retourne false.</li>
	 * <li>garantit que compareContenuFichiers(null, file2) 
	 * génère un rapport de comparaison.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCompareContenuFichiersAvecNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ComparateurFichiersTest - méthode testCompareContenuFichiersAvecNull() ********** ");
		}
		
		// VALEURS A TESTER **********************************************
		final File file1 = null;
		final File file2 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultat 
			= ComparateurFichiers.compareContenuFichiers(file1, file2);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultat);
			System.out.println(RAPPORT_COMPARAISON + ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareContenuFichiers(null, file2) 
		 * retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultat);
		
		/* garantit que compareContenuFichiers(null, file2) 
		 * génère un rapport de comparaison. */
		assertNotNull(RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL
				, ComparateurFichiers.getRapportComparaison());
		
		
		// VALEURS A TESTER **********************************************
		final File file3 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		final File file4 = null;
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultatInverse 
			= ComparateurFichiers.compareContenuFichiers(file3, file4);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultatInverse);
			System.out.println(RAPPORT_COMPARAISON 
					+ ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareContenuFichiers(null, file2) 
		 * retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultatInverse);
		
		/* garantit que compareContenuFichiers(null, file2) 
		 * génère un rapport de comparaison. */
		assertNotNull(RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL
				, ComparateurFichiers.getRapportComparaison());
		
	} // Fin de testCompareContenuFichiersAvecNull().______________________
	
	
	
	/**
	 * teste la méthode compareContenuFichiers(File pFile1, File pFile2).<br/>
	 * file1 = foo et file2=HITDIRCE2017.txt
	 * <ul>
	 * <li>garantit que compareContenuFichiers(inexistant, file2) retourne false.</li>
	 * <li>garantit que compareContenuFichiers(inexistant, file2) 
	 * génère un rapport de comparaison.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCompareContenuFichiersInexistant() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ComparateurFichiersTest - méthode testCompareContenuFichiersInexistant() ********** ");
		}
		
		// VALEURS A TESTER **********************************************
		final File file1 = new File(CHEMIN_FICHIER_INEXISTANT);
		final File file2 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultat 
			= ComparateurFichiers.compareContenuFichiers(file1, file2);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultat);
			System.out.println(RAPPORT_COMPARAISON + ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareContenuFichiers(inexistant, file2)
		 *  retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultat);
		
		/* garantit que compareContenuFichiers(inexistant, file2) 
		 * génère un rapport de comparaison. */
		assertNotNull(RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL
				, ComparateurFichiers.getRapportComparaison());
		
		
		// VALEURS A TESTER **********************************************
		final File file3 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		final File file4 = new File(CHEMIN_FICHIER_INEXISTANT);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultatInverse 
			= ComparateurFichiers.compareContenuFichiers(file3, file4);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultatInverse);
			System.out.println(RAPPORT_COMPARAISON 
					+ ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareContenuFichiers(inexistant, file2) 
		 * retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultatInverse);
		
		/* garantit que compareContenuFichiers(inexistant, file2) 
		 * génère un rapport de comparaison. */
		assertNotNull(RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL
				, ComparateurFichiers.getRapportComparaison());
		
	} // Fin de testCompareContenuFichiersInexistant().____________________
	
	
	
	/**
	 * teste la méthode compareContenuFichiers(File pFile1, File pFile2).<br/>
	 * file1 = repertoire et file2=HITDIRCE2017.txt
	 * <ul>
	 * <li>garantit que compareContenuFichiers(repertoire, file2) retourne false.</li>
	 * <li>garantit que compareContenuFichiers(repertoire, file2) 
	 * génère un rapport de comparaison.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCompareContenuRepertoire() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ComparateurFichiersTest - méthode testCompareContenuRepertoire() ********** ");
		}
		
		// VALEURS A TESTER **********************************************
		final File file1 = new File(CHEMIN_REPERTOIRE);
		final File file2 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultat 
			= ComparateurFichiers.compareContenuFichiers(file1, file2);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultat);
			System.out.println(RAPPORT_COMPARAISON + ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareContenuFichiers(repertoire, file2)
		 *  retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultat);
		
		/* garantit que compareContenuFichiers(repertoire, file2) 
		 * génère un rapport de comparaison. */
		assertNotNull(RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL
				, ComparateurFichiers.getRapportComparaison());
		
		
		// VALEURS A TESTER **********************************************
		final File file3 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		final File file4 = new File(CHEMIN_REPERTOIRE);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultatInverse 
			= ComparateurFichiers.compareContenuFichiers(file3, file4);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultatInverse);
			System.out.println(RAPPORT_COMPARAISON 
					+ ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareContenuFichiers(inexistant, file2) 
		 * retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultatInverse);
		
		/* garantit que compareContenuFichiers(inexistant, file2) 
		 * génère un rapport de comparaison. */
		assertNotNull(RAPPORT_COMPARAISON_DOIT_ETRE_NON_NULL
				, ComparateurFichiers.getRapportComparaison());
		
	} // Fin de testCompareContenuRepertoire().____________________________

	
	
	/**
	 * teste la méthode compareContenuFichiers(File pFile1, File pFile2).<br/>
	 * file2 = file1
	 * <ul>
	 * <li>garantit que compareContenuFichiers(File pFile, File pFile) 
	 * retourne toujours true.</li>
	 * <li>garantit que compareContenuFichiers(File pFile, File pFile) 
	 * ne génère pas de rapport de comparaison..</li>
	 * </ul>
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCompareContenuFichiersEquals() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ComparateurFichiersTest - méthode testCompareContenuFichiersEquals() ********** ");
		}
		
		// VALEURS A TESTER **********************************************
		final File file1 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		final File file2 = file1;
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultat 
			= ComparateurFichiers.compareContenuFichiers(file1, file2);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultat);
			System.out.println(RAPPORT_COMPARAISON 
					+ ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareContenuFichiers(file, file) 
		 * retourne true. */
		assertTrue(COMPARAISON_DOIT_RETOURNER_TRUE, resultat);
		
		/* garantit que compareContenuFichiers(file, file) 
		 * ne génère PAS un rapport de comparaison. */
		assertNull(RAPPORT_COMPARAISON_DOIT_ETRE_NULL
				, ComparateurFichiers.getRapportComparaison());

	} // Fin de testCompareContenuFichiersEquals().________________________

	
		
	/**
	 * teste la méthode compareContenuFichiers(File pFile1, File pFile2).<br/>
	 * File2 = File1_renomme
	 * <ul>
	 * <li>garantit que compareContenuFichiers(File pFile1, File pFile2) 
	 * retourne true et ne génère par de rapport de comparaison 
	 * si pFile1 a le même contenu que pFile2.</li>
	 * <li>garantit que compareContenuFichiers(File pFile1, File pFile2) 
	 * ne prend pas en compte les noms des fichiers pour savoir 
	 * si leurs contenus sont égaux.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCompareContenuFichiers() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ComparateurFichiersTest - méthode testCompareContenuFichiers() ********** ");
		}
		
		// VALEURS A TESTER **********************************************
		final File file1 = new File(CHEMIN_HIT_DIRCE2017_RENOMME);
		final File file2 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultat 
			= ComparateurFichiers.compareContenuFichiers(file1, file2);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultat);
			System.out.println(RAPPORT_COMPARAISON 
					+ ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareContenuFichiers(file, file_renomme) 
		 * retourne true. */
		assertTrue(COMPARAISON_DOIT_RETOURNER_TRUE, resultat);
		
		/* garantit que compareContenuFichiers(file, file_renomme) 
		 * ne génère PAS un rapport de comparaison. */
		assertNull(RAPPORT_COMPARAISON_DOIT_ETRE_NULL
				, ComparateurFichiers.getRapportComparaison());

	} // Fin de testCompareContenuFichiers().______________________________

	
	
	/**
	 * teste la méthode compareContenuFichiers(File pFile1, File pFile2).<br/>
	 * file1 != file2
	 * <ul>
	 * <li>garantit que compareContenuFichiers(File pFile1, File pFile2) 
	 * retourne false et ne génère par de rapport de comparaison 
	 * si pFile1 n'a PAS le même contenu que pFile2.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCompareContenuFichiersDifferents() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ComparateurFichiersTest - méthode testCompareContenuFichiersDifferents() ********** ");
		}
		
		// VALEURS A TESTER **********************************************
		final File file1 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		final File file2 = new File(CHEMIN_HIT_DIRMED2017);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultat 
			= ComparateurFichiers.compareContenuFichiers(file1, file2);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultat);
			System.out.println(RAPPORT_COMPARAISON 
					+ ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareContenuFichiers(file1, file2 different) 
		 * retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultat);
		
		/* garantit que compareContenuFichiers(file1, file2 different) 
		 * ne génère PAS un rapport de comparaison. */
		assertNull(RAPPORT_COMPARAISON_DOIT_ETRE_NULL
				, ComparateurFichiers.getRapportComparaison());

	} // Fin de testCompareContenuFichiersDifferents().____________________
	
	
	
	/**
	 * teste la méthode compareContenuFichiers(File pFile1, File pFile2).<br/>
	 * file1_ANSI et file2_UTF8 même fichier de départ.
	 * <ul>
	 * <li>garantit que compareContenuFichiers(File pFile1_ANSI, File pFile1_UTF8) 
	 * retourne false et ne génère par de rapport de comparaison 
	 * si pFile1 n'a PAS le même contenu que pFile2.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCompareContenuFichiersCharsetsDifferents() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ComparateurFichiersTest - méthode testCompareContenuFichiersCharsetsDifferents() ********** ");
		}
		
		// VALEURS A TESTER **********************************************
		final File file1 = new File(CHEMIN_HIT_DIRCE2017_ANSI);
		final File file2 = new File(CHEMIN_HIT_DIRCE2017_UTF8);
		// ***************************************************************

		// METHODE A TESTER *************************************************
		final boolean resultat 
			= ComparateurFichiers.compareContenuFichiers(file1, file2);
		// ******************************************************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(FICHIERS_AVEC_MEME_CONTENU + resultat);
			System.out.println(RAPPORT_COMPARAISON 
					+ ComparateurFichiers.getRapportComparaison());
		}
		
		/* garantit que compareContenuFichiers(file1, file2 different) 
		 * retourne false. */
		assertFalse(COMPARAISON_DOIT_RETOURNER_FALSE, resultat);
		
		/* garantit que compareContenuFichiers(file1, file2 different) 
		 * ne génère PAS un rapport de comparaison. */
		assertNull(RAPPORT_COMPARAISON_DOIT_ETRE_NULL
				, ComparateurFichiers.getRapportComparaison());

	} // Fin de testCompareContenuFichiersCharsetsDifferents().____________

	

} // FIN DE LA CLASSE ComparateurFichiersTest.-------------------------------
