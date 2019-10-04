package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.IImportateurDescription;

/**
 * CLASSE ImportateurDescriptionDarwinCsvTest :<br/>
 * Test JUnit de la classe {@link ImportateurDescriptionDarwinCsv}.<br/>
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
 * @since 14 mai 2019
 *
 */
public class ImportateurDescriptionDarwinCsvTest {

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
	 * Path absolu vers le répertoire 'temp' sous le présent projet.
	 */
	public static final Path PATH_ABSOLU_REPERTOIRE_TEMP 
		= PATH_ABSOLU_PRESENT_PROJET.resolve("temp");
	
	/**
	 * IImportateurDescription à tester.<br/>
	 * instancié dans instancierImportateur (beforeClass).
	 */
	public static transient IImportateurDescription importateur;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings(UNUSED)
	private static final Log LOG 
		= LogFactory.getLog(ImportateurDescriptionDarwinCsvTest.class);
	
	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ImportateurDescriptionDarwinCsvTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * Teste la méthode importerDescriptionUtf8().<br/>
	 * <ul>
	 * <li>garantit que importerDescriptionUtf8() importe 
	 * la description de fichier.</li>
	 * <i>après l'import</i> : <br/>
	 * <li>garantit que getSpecificationChampsMap() retourne 
	 * <code><b>this.specificationChampsMap</b></code>.</li>
	 * <li>garantit le bon fonctionnement de fournirLigneEnTetesCsv().</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testImporterDescriptionUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionDarwinCsvTest - méthode testImporterDescriptionUtf8() ********** ");
		}
		
		// METHODE A TESTER.
		final Map<Integer, IDescriptionChamp> resultat = 
				importateur.getSpecificationChampsMap();
			
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(importateur.toStringFormate());
		}
		
		/* garantit que getSpecificationChampsMap() retourne 
		 * this.specificationChampsMap. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultat);
		
		final String ligneEnTetesToString = importateur.fournirLigneEnTetestoString();
		final String ligneEnTetesCsv = importateur.fournirLigneEnTetesCsv();
		final String ligneEnTetesCsvDesc = importateur.getDescriptionChamp().fournirLigneEnTetesCsv();
		
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("ligneEnTetesToString : " + ligneEnTetesToString);
			System.out.println("ligneEnTetesCsv : " + ligneEnTetesCsv);
			System.out.println("ligneEnTetesCsvDesc : " + ligneEnTetesCsvDesc);
		}

		/* garantit le bon fonctionnement de fournirLigneEnTetesCsv(). */
		assertEquals("lignesEnTetesCsv doit valoir importateur.getDescriptionChamp().fournirLigneEnTetesCsv() : "
				, "ordreChamps;intitulé;nomenclature;champJava;typeJava;aNomenclature;aLexique;"
				, ligneEnTetesCsv);
		
		assertEquals("lignesEnTetesCsv doit valoir importateur.getDescriptionChamp().fournirLigneEnTetesCsv() : "
				, ligneEnTetesCsvDesc
				, ligneEnTetesCsv);
		
	} // Fin de testImporterDescriptionUtf8()._______________________________
	
	
	
	/**
	 * Teste la méthode fournirEnteteparColonne(int pI).<br/>
	 * <i>après import.</i>
	 * <ul>
	 * <li>garantit que fournirEnteteparColonne(0) null.</li>
	 * <li>garantit que fournirEnteteparColonne(i) retourne la bonne valeur.</li>
	 * <li>garantit que fournirEnteteparColonne(hors index) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirEnteteparColonne() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionDarwinCsvTest - méthode testFournirEnteteparColonne() ********** ");
		}
		
		// METHODE A TESTER.
		final String fournirEnteteparColonne0 
			= importateur.fournirEnteteparColonne(0);
		
		final String fournirEnteteparColonne1 
			= importateur.fournirEnteteparColonne(1);
		final String fournirEnteteparColonne2 
			= importateur.fournirEnteteparColonne(2);
		final String fournirEnteteparColonne3 
			= importateur.fournirEnteteparColonne(3);
		final String fournirEnteteparColonne4 
			= importateur.fournirEnteteparColonne(4);
		final String fournirEnteteparColonne5 
			= importateur.fournirEnteteparColonne(5);
		final String fournirEnteteparColonne6 
			= importateur.fournirEnteteparColonne(6);
		final String fournirEnteteparColonne7 
			= importateur.fournirEnteteparColonne(7);
		
		final String fournirEnteteparColonne8 
			= importateur.fournirEnteteparColonne(8);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("fournirEnteteparColonne0 : " + fournirEnteteparColonne0);
			
			System.out.println("fournirEnteteparColonne1 : " + fournirEnteteparColonne1);
			System.out.println("fournirEnteteparColonne2 : " + fournirEnteteparColonne2);
			System.out.println("fournirEnteteparColonne3 : " + fournirEnteteparColonne3);
			System.out.println("fournirEnteteparColonne4 : " + fournirEnteteparColonne4);
			System.out.println("fournirEnteteparColonne5 : " + fournirEnteteparColonne5);
			System.out.println("fournirEnteteparColonne6 : " + fournirEnteteparColonne6);
			System.out.println("fournirEnteteparColonne7 : " + fournirEnteteparColonne7);
			
			System.out.println("fournirEnteteparColonne8 : " + fournirEnteteparColonne8);
		}
		
		/* garantit que fournirEnteteparColonne(0) null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, fournirEnteteparColonne0);
		
		/* garantit que fournirEnteteparColonne(i) retourne la bonne valeur. */
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "ordreChamps"
						, fournirEnteteparColonne1);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "intitulé"
						, fournirEnteteparColonne2);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "nomenclature"
						, fournirEnteteparColonne3);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "champJava"
						, fournirEnteteparColonne4);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "typeJava"
						, fournirEnteteparColonne5);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "aNomenclature"
						, fournirEnteteparColonne6);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "aLexique"
						, fournirEnteteparColonne7);
		
		/* garantit que fournirEnteteparColonne(hors index) retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, fournirEnteteparColonne8);
		
	} // Fin de testFournirEnteteparColonne()._____________________________


	
	/**
	 * Test la méthode fournirLigneEnTetesCsv().<br/>
	 * <ul>
	 * <li>garantit que fournirLigneEnTetesCsv() retourne la bonne valeur.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirLigneEnTetesCsv() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionDarwinCsvTest - méthode testFournirLigneEnTetesCsv() ********** ");
		}
		
		// METHODE A TESTER.
		final String ligneEnTetesCsv = importateur.fournirLigneEnTetesCsv();
		final String[] tokens =importateur.stringCsvToTokens(ligneEnTetesCsv);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("ligneEnTetesCsv : " + ligneEnTetesCsv);
		}
		
		/* garantit que fournirLigneEnTetesCsv() retourne la bonne valeur. */
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "ordreChamps;intitulé;nomenclature;champJava;typeJava;aNomenclature;aLexique;"
						, ligneEnTetesCsv);
		assertEquals("l'en-tête doit comporter 7 éléments : "
				, 7
					, tokens.length);
		
	} // Fin de testFournirLigneEnTetesCsv().______________________________
	
	
	
	/**
	 * Teste la méthode fournirLigneValeursCsv(int pL).<br/>
	 * <i>après import.</i>
	 * <ul>
	 * <li>garantit que fournirLigneValeursCsv(0) retourne l'en-tête 
	 * de la description de fichier.</li>
	 * <li>garantit que fournirLigneValeursCsv(i) retourne la bonne valeur.</li>
	 * <li>garantit que fournirLigneValeursCsv(hors index) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirLigneValeursCsv() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionDarwinCsvTest - méthode testFournirLigneValeursCsv() ********** ");
		}
		
		// METHODE A TESTER.
		final String ligneValeursCsv0 = importateur.fournirLigneValeursCsv(0);
		final String ligneValeursCsv1 = importateur.fournirLigneValeursCsv(1);
		final String ligneValeursCsv2 = importateur.fournirLigneValeursCsv(2);
		final String ligneValeursCsv65 = importateur.fournirLigneValeursCsv(65);
		final String ligneValeursCsv66 = importateur.fournirLigneValeursCsv(66);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("ligneValeursCsv0 : " + ligneValeursCsv0);
			System.out.println("ligneValeursCsv1 : " + ligneValeursCsv1);
			System.out.println("ligneValeursCsv2 : " + ligneValeursCsv2);
			System.out.println("ligneValeursCsv65 : " + ligneValeursCsv65);
			System.out.println("ligneValeursCsv66 : " + ligneValeursCsv66);
		}
		
		final String ligneEnTetesCsv = importateur.fournirLigneEnTetesCsv();
		
		/* garantit que fournirLigneValeursCsv(0) retourne l'en-tête de la description de fichier. */
		assertEquals("fournirLigneValeursCsv(0) doit valoir importateur.getDescriptionChamp().fournirLigneEnTetesCsv() : "
				, ligneValeursCsv0
				, ligneEnTetesCsv);
		
		/* garantit que fournirLigneValeursCsv(i) retourne la bonne valeur. */
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "1;Identifiant de la section;Identifiant de la section;objetID;Integer;false;false;"
				, ligneValeursCsv1);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "2;route;Route au format Isidor (ex : A0034b1 ou A0006);route;String;false;false;"
				, ligneValeursCsv2);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "65;Sous-réseau Indice;1 – Autoroute Interurbaine [sep] 2 – Autoroute et voie rapide urbaine [sep] 3 – Route nationale interurbaine à caractéristiques autoroutières [sep] 4 – Autre route nationale [sep] 5 – Autoroute concédée [sep] 9 – Inconnu ou non renseigné;sousReseauIndice;Integer;true;false;"
				, ligneValeursCsv65);
		
		/* garantit que fournirLigneValeursCsv(hors index) retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, ligneValeursCsv66);
		
	} // Fin de testFournirLigneValeursCsv().______________________________

	
		
	/**
	 * Teste la méthode fournirValeurparLigneColonne(int pLigne, int pColonne).<br/>
	 * <i>après import.</i>
	 * <ul>
	 * <li>garantit que fournirValeurparLigneColonne(0, pCol) retourne 
	 * une valeur de l'en-tête de la description de fichier.</li>
	 * li>garantit que fournirValeurparLigneColonne(0, inexistant) retourne null.</li>
	 * <li>garantit que fournirValeurparLigneColonne(ligne, pCol) 
	 * retourne la bonne valeur de la description de fichier.</li>
	 * <li>garantit que garantit que fournirValeurparLigneColonne(ligne, inexistant) 
	 * retourne null.</li>
	 * <li>garantit que fournirValeurparLigneColonne(inexistant, colonne)
	 *  retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirValeurparLigneColonne() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionDarwinCsvTest - méthode testFournirValeurparLigneColonne() ********** ");
		}
		
		// METHODE A TESTER.
		final String valeurl0c0 = importateur.fournirValeurparLigneColonne(0, 0);
		final String valeurl0c1 = importateur.fournirValeurparLigneColonne(0, 1);
		final String valeurl0c2 = importateur.fournirValeurparLigneColonne(0, 2);
		final String valeurl0c7 = importateur.fournirValeurparLigneColonne(0, 7);
		final String valeurl0c8 = importateur.fournirValeurparLigneColonne(0, 8);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("valeurl0c0 : " + valeurl0c0);
			System.out.println("valeurl0c1 : " + valeurl0c1);
			System.out.println("valeurl0c2 : " + valeurl0c2);
			System.out.println("valeurl0c7 : " + valeurl0c7);
			System.out.println("valeurl0c8 : " + valeurl0c8);
		}
		
		/* garantit que fournirValeurparLigneColonne(0, pCol) 
		 * retourne une valeur de l'en-tête de la description de fichier. */
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "ordreChamps"
					, valeurl0c1);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "intitulé"
					, valeurl0c2);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "aLexique"
					, valeurl0c7);
		
		/* garantit que fournirValeurparLigneColonne(0, inexistant) 
		 * retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl0c0);
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl0c8);
		
		
		// METHODE A TESTER.
		final String valeurl27c0 = importateur.fournirValeurparLigneColonne(27, 0);
		final String valeurl27c1 = importateur.fournirValeurparLigneColonne(27, 1);
		final String valeurl27c2 = importateur.fournirValeurparLigneColonne(27, 2);
		final String valeurl27c3 = importateur.fournirValeurparLigneColonne(27, 3);
		final String valeurl27c8 = importateur.fournirValeurparLigneColonne(27, 8);
		final String valeurl103c7 = importateur.fournirValeurparLigneColonne(103, 7);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("valeurl27c0 : " + valeurl27c0);
			System.out.println("valeurl27c1 : " + valeurl27c1);
			System.out.println("valeurl27c2 : " + valeurl27c2);
			System.out.println("valeurl27c3 : " + valeurl27c3);
			System.out.println("valeurl27c8 : " + valeurl27c8);
			System.out.println("valeurl103c7 : " + valeurl103c7);
		}
		
		/* garantit que fournirValeurparLigneColonne(ligne, pCol) 
		 * retourne la bonne valeur de la description de fichier. */
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "27"
					, valeurl27c1);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "MJA N-4"
					, valeurl27c2);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "Moyenne journalière annuelle de l'année de traitement – 4  (n-4) en véhicules/jour"
					, valeurl27c3);
		
		/* garantit que fournirValeurparLigneColonne(ligne, inexistant) 
		 * retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl27c0);
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl27c8);
		
		/* garantit que fournirValeurparLigneColonne(inexistant, colonne) 
		 * retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl103c7);
		
	} // Fin de testFournirValeurparLigneColonne().________________________
	
	
	
	/**
	 * Teste la méthode genererDescriptionCsvFile(...).<br/>
	 * <i>après import.</i>
	 * <ul>
	 * <li>garantit que genererDescriptionCsvFile(...) 
	 * génère la description sur disque.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGenererDescriptionCsvFile() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionDarwinCsvTest - méthode testGenererDescriptionCsvFile() ********** ");
		}
		
		final Path decriptionGenereePath 
			= PATH_ABSOLU_REPERTOIRE_TEMP
				.resolve("description_DARWIN_CSV_generee_UTF-8.csv");
		
		final File descriptionGeneree = decriptionGenereePath.toFile();
		
		final Charset charsetUtf8 = Charset.forName("UTF-8");
		final boolean avecLigneEnTete = true;
		
		// APPEL DE LA METHODE A TESTER **************
		importateur
			.genererDescriptionCsvFile(
					avecLigneEnTete
						, descriptionGeneree
							, charsetUtf8);
		
		/* garantit que genererDescriptionCsvFile(...) 
		 * génère la description sur disque. */
		assertTrue(
				"le fichier généré doit exister sur disque : "
					, descriptionGeneree.exists());
		
		/* détruit le fichier généré à la fin du test. */
		if (decriptionGenereePath.toFile().exists()) {
			Files.delete(decriptionGenereePath);
		}
		
		if (PATH_ABSOLU_REPERTOIRE_TEMP.toFile().exists()) {
			Files.delete(PATH_ABSOLU_REPERTOIRE_TEMP);
		}


	} // Fin de testGenererDescriptionCsvFile().___________________________


	
	/**
	 * Teste la méthode genererDescriptionCsvString().<br/>
	 * <i>après import.</i>
	 * <ul>
	 * <li>garantit que genererDescriptionCsvString() 
	 * génère une String contenant la description.</li>
	 * </ul>
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGenererDescriptionCsvString() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionDarwinCsvTest - méthode testGenererDescriptionCsvString() ********** ");
		}

		// APPEL DE LA METHODE A TESTER **************
		final boolean avecLigneEntetes = true;
		final String resultat 
			= importateur.genererDescriptionCsvString(avecLigneEntetes);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(resultat);
		}
		
		/* garantit que genererDescriptionCsvString() 
		 * génère une String contenant la description.*/
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultat);
		
	} // Fin de testGenererDescriptionCsvString()._________________________
	
	
	
	/**
	 * Teste la méthode getDescriptionChamp(int pI).<br/>
	 * <i>après import.</i>
	 * <ul>
	 * <li>garantit que getDescriptionChamp(0) null.</li>
	 * <li>garantit que getDescriptionChamp(i) retourne la bonne valeur.</li>
	 * <li>garantit que getDescriptionChamp(hors index) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetDescriptionChamp() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionDarwinCsvTest - méthode testGetDescriptionChamp() ********** ");
		}
		
		// METHODE A TESTER.
		final IDescriptionChamp descriptionChamp0 = importateur.getDescriptionChamp(0);
		final IDescriptionChamp descriptionChamp1 = importateur.getDescriptionChamp(1);
		final IDescriptionChamp descriptionChamp2 = importateur.getDescriptionChamp(2);
		final IDescriptionChamp descriptionChamp65 = importateur.getDescriptionChamp(65);
		final IDescriptionChamp descriptionChamp66 = importateur.getDescriptionChamp(66);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("descriptionChamp0 : " + descriptionChamp0);
			System.out.println("descriptionChamp1 : " + descriptionChamp1.fournirLigneValeursCsv());
			System.out.println("descriptionChamp2 : " + descriptionChamp2.fournirLigneValeursCsv());
			System.out.println("descriptionChamp65 : " + descriptionChamp65.fournirLigneValeursCsv());
			System.out.println("descriptionChamp66 : " + descriptionChamp66);
		}
		
		/* garantit que getDescriptionChamp(0) retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, descriptionChamp0);
		
		/* garantit que getDescriptionChamp(i) retourne la bonne valeur. */
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "1;Identifiant de la section;Identifiant de la section;objetID;Integer;false;false;"
				, descriptionChamp1.fournirLigneValeursCsv());
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "2;route;Route au format Isidor (ex : A0034b1 ou A0006);route;String;false;false;"
				, descriptionChamp2.fournirLigneValeursCsv());
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "65;Sous-réseau Indice;1 – Autoroute Interurbaine [sep] 2 – Autoroute et voie rapide urbaine [sep] 3 – Route nationale interurbaine à caractéristiques autoroutières [sep] 4 – Autre route nationale [sep] 5 – Autoroute concédée [sep] 9 – Inconnu ou non renseigné;sousReseauIndice;Integer;true;false;"
				, descriptionChamp65.fournirLigneValeursCsv());
		
		/* garantit que getDescriptionChamp(hors index) retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, descriptionChamp66);
		
	} // Fin de testGetDescriptionChamp()._________________________________

	
		
	/**
	 * Teste la méthode getDescriptionDuFichierFile().<br/>
	 * <i>après import.</i>
	 * <ul>
	 * <li>garantit que getDescriptionDuFichierFile() 
	 * retourne la bonne valeur.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetDescriptionDuFichierFile() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionDarwinCsvTest - méthode testGetDescriptionDuFichierFile() ********** ");
		}
		
		// METHODE A TESTER.
		final File resultat = importateur.getDescriptionDuFichierFile();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("DescriptionDuFichierFile : " + resultat.getAbsolutePath());
		}
		
		/* garantit que getDescriptionDuFichierFile() retourne la bonne valeur. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultat);
		assertTrue("Doit exister : ", resultat.exists());
		
	} // Fin de testGetDescriptionDuFichierFile()._________________________

	
	
	/**
	 * Teste la méthode getLabelDescriptionFichier().<br/>
	 * <i>après import.</i>
	 * <ul>
	 * <li>garantit que getLabelDescriptionFichier() 
	 * retourne la bonne valeur.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetLabelDescriptionFichier() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionDarwinCsvTest - méthode testGetLabelDescriptionFichier() ********** ");
		}
		
		// METHODE A TESTER.
		final String resultat = importateur.getLabelDescriptionFichier();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("LabelDescriptionFichier : " + resultat);
		}
		
		/* garantit que getLabelDescriptionFichier() retourne la bonne valeur. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultat);
		
	} // Fin de testGetLabelDescriptionFichier().__________________________
	
	

	/**
	 * Instancie un IImportateurDescription 
	 * avant tout test de la classe.<br/>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void instancierImportateur() throws Exception {
		
		// instanciation d'un ImportateurDescription.
		importateur = new ImportateurDescriptionDarwinCsv();
		
		// IMPORT de la bonne description de fichier encodée en UTF-8 
		// (contenue sous classpath/resources).
		importateur.importerDescriptionUtf8();
		
	} // Fin de instancierImportateur().___________________________________
	
	
	
} // FIN DE LA CLASSE ImportateurDescriptionDarwinCsvTest.--------------------
