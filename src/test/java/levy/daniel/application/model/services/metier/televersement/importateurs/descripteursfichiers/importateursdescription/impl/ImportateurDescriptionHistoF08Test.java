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
 * CLASSE ImportateurDescriptionHistoF08Test :<br/>
 * Test JUnit de la classe {@link ImportateurDescriptionHistoF08}.<br/>
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
public class ImportateurDescriptionHistoF08Test {

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
		= LogFactory.getLog(ImportateurDescriptionHistoF08Test.class);
	
	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ImportateurDescriptionHistoF08Test() {
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
			System.out.println("********** CLASSE ImportateurDescriptionHistoF08Test - méthode testImporterDescriptionUtf8() ********** ");
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
				, "ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;aNomenclature;aLexique;colonneDebut;colonneFin;longueurCalculee;"
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
			System.out.println("********** CLASSE ImportateurDescriptionHistoF08Test - méthode testFournirEnteteparColonne() ********** ");
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
		final String fournirEnteteparColonne9 
			= importateur.fournirEnteteparColonne(9);
		final String fournirEnteteparColonne10 
			= importateur.fournirEnteteparColonne(10);
		final String fournirEnteteparColonne11 
			= importateur.fournirEnteteparColonne(11);
		final String fournirEnteteparColonne12 
			= importateur.fournirEnteteparColonne(12);

		final String fournirEnteteparColonne13 
			= importateur.fournirEnteteparColonne(13);
		
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
			System.out.println("fournirEnteteparColonne9 : " + fournirEnteteparColonne9);
			System.out.println("fournirEnteteparColonne10 : " + fournirEnteteparColonne10);
			System.out.println("fournirEnteteparColonne11 : " + fournirEnteteparColonne11);
			System.out.println("fournirEnteteparColonne12 : " + fournirEnteteparColonne12);
			
			System.out.println("fournirEnteteparColonne13 : " + fournirEnteteparColonne13);
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
					, "colonnes"
						, fournirEnteteparColonne2);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "longueur"
						, fournirEnteteparColonne3);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "intitule"
						, fournirEnteteparColonne4);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "nomenclature"
						, fournirEnteteparColonne5);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "champJava"
						, fournirEnteteparColonne6);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "typeJava"
						, fournirEnteteparColonne7);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "aNomenclature"
						, fournirEnteteparColonne8);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "aLexique"
						, fournirEnteteparColonne9);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "colonneDebut"
						, fournirEnteteparColonne10);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "colonneFin"
						, fournirEnteteparColonne11);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "longueurCalculee"
						, fournirEnteteparColonne12);
		
		/* garantit que fournirEnteteparColonne(hors index) retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, fournirEnteteparColonne13);
		
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
			System.out.println("********** CLASSE ImportateurDescriptionHistoF08Test - méthode testFournirLigneEnTetesCsv() ********** ");
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
					, "ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;aNomenclature;aLexique;colonneDebut;colonneFin;longueurCalculee;"
						, ligneEnTetesCsv);
		assertEquals("l'en-tête doit comporter 12 éléments : "
				, 12
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
			System.out.println("********** CLASSE ImportateurDescriptionHistoF08Test - méthode testFournirLigneValeursCsv() ********** ");
		}
		
		// METHODE A TESTER.
		final String ligneValeursCsv0 = importateur.fournirLigneValeursCsv(0);
		final String ligneValeursCsv1 = importateur.fournirLigneValeursCsv(1);
		final String ligneValeursCsv2 = importateur.fournirLigneValeursCsv(2);
		final String ligneValeursCsv102 = importateur.fournirLigneValeursCsv(102);
		final String ligneValeursCsv104 = importateur.fournirLigneValeursCsv(104);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("ligneValeursCsv0 : " + ligneValeursCsv0);
			System.out.println("ligneValeursCsv1 : " + ligneValeursCsv1);
			System.out.println("ligneValeursCsv2 : " + ligneValeursCsv2);
			System.out.println("ligneValeursCsv102 : " + ligneValeursCsv102);
			System.out.println("ligneValeursCsv104 : " + ligneValeursCsv104);
		}
		
		final String ligneEnTetesCsv = importateur.fournirLigneEnTetesCsv();
		
		/* garantit que fournirLigneValeursCsv(0) retourne l'en-tête de la description de fichier. */
		assertEquals("fournirLigneValeursCsv(0) doit valoir importateur.getDescriptionChamp().fournirLigneEnTetesCsv() : "
				, ligneValeursCsv0
				, ligneEnTetesCsv);
		
		/* garantit que fournirLigneValeursCsv(i) retourne la bonne valeur. */
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "1;1-3;3;Numéro de Département;cadré à gauche. Ex: dept 13 = 130;numDepartement;Integer;false;false;1;3;3;"
				, ligneValeursCsv1);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "2;4-9;6;Numéro de Section;;numSection;String;false;false;4;9;6;"
				, ligneValeursCsv2);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "102;509-520;12;Zone libre;12 espaces;sans objet;sans objet;false;false;509;520;12;"
				, ligneValeursCsv102);
		
		/* garantit que fournirLigneValeursCsv(hors index) retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, ligneValeursCsv104);
		
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
			System.out.println("********** CLASSE ImportateurDescriptionHistoF08Test - méthode testFournirValeurparLigneColonne() ********** ");
		}
		
		// METHODE A TESTER.
		final String valeurl0c0 = importateur.fournirValeurparLigneColonne(0, 0);
		final String valeurl0c1 = importateur.fournirValeurparLigneColonne(0, 1);
		final String valeurl0c6 = importateur.fournirValeurparLigneColonne(0, 6);
		final String valeurl0c12 = importateur.fournirValeurparLigneColonne(0, 12);
		final String valeurl0c13 = importateur.fournirValeurparLigneColonne(0, 13);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("valeurl0c0 : " + valeurl0c0);
			System.out.println("valeurl0c1 : " + valeurl0c1);
			System.out.println("valeurl0c6 : " + valeurl0c6);
			System.out.println("valeurl0c12 : " + valeurl0c12);
			System.out.println("valeurl0c13 : " + valeurl0c13);
		}
		
		/* garantit que fournirValeurparLigneColonne(0, pCol) 
		 * retourne une valeur de l'en-tête de la description de fichier. */
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "ordreChamps"
					, valeurl0c1);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "champJava"
					, valeurl0c6);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "longueurCalculee"
					, valeurl0c12);
		
		/* garantit que fournirValeurparLigneColonne(0, inexistant) 
		 * retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl0c0);
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl0c13);
		
		
		// METHODE A TESTER.
		final String valeurl27c0 = importateur.fournirValeurparLigneColonne(27, 0);
		final String valeurl27c1 = importateur.fournirValeurparLigneColonne(27, 1);
		final String valeurl27c6 = importateur.fournirValeurparLigneColonne(27, 6);
		final String valeurl27c12 = importateur.fournirValeurparLigneColonne(27, 12);
		final String valeurl27c13 = importateur.fournirValeurparLigneColonne(27, 13);
		final String valeurl103c7 = importateur.fournirValeurparLigneColonne(103, 7);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("valeurl27c0 : " + valeurl27c0);
			System.out.println("valeurl27c1 : " + valeurl27c1);
			System.out.println("valeurl27c6 : " + valeurl27c6);
			System.out.println("valeurl27c12 : " + valeurl27c12);
			System.out.println("valeurl27c13 : " + valeurl27c13);
			System.out.println("valeurl103c7 : " + valeurl103c7);
		}
		
		/* garantit que fournirValeurparLigneColonne(ligne, pCol) 
		 * retourne la bonne valeur de la description de fichier. */
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "27"
					, valeurl27c1);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "mjaN"
					, valeurl27c6);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "6"
					, valeurl27c12);
		
		/* garantit que fournirValeurparLigneColonne(ligne, inexistant) 
		 * retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl27c0);
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl27c13);
		
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
			System.out.println("********** CLASSE ImportateurDescriptionHistoF08Test - méthode testGenererDescriptionCsvFile() ********** ");
		}
		
		final Path decriptionGenereePath 
			= PATH_ABSOLU_REPERTOIRE_TEMP
				.resolve("description_HISTO_F08_generee_UTF-8.csv");
		
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
			System.out.println("********** CLASSE ImportateurDescriptionHistoF08Test - méthode testGenererDescriptionCsvString() ********** ");
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
			System.out.println("********** CLASSE ImportateurDescriptionHistoF08Test - méthode testGetDescriptionChamp() ********** ");
		}
		
		// METHODE A TESTER.
		final IDescriptionChamp descriptionChamp0 = importateur.getDescriptionChamp(0);
		final IDescriptionChamp descriptionChamp1 = importateur.getDescriptionChamp(1);
		final IDescriptionChamp descriptionChamp2 = importateur.getDescriptionChamp(2);
		final IDescriptionChamp descriptionChamp102 = importateur.getDescriptionChamp(102);
		final IDescriptionChamp descriptionChamp103 = importateur.getDescriptionChamp(103);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("descriptionChamp0 : " + descriptionChamp0);
			System.out.println("descriptionChamp1 : " + descriptionChamp1.fournirLigneValeursCsv());
			System.out.println("descriptionChamp2 : " + descriptionChamp2.fournirLigneValeursCsv());
			System.out.println("descriptionChamp102 : " + descriptionChamp102.fournirLigneValeursCsv());
			System.out.println("descriptionChamp103 : " + descriptionChamp103);
		}
		
		/* garantit que getDescriptionChamp(0) retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, descriptionChamp0);
		
		/* garantit que getDescriptionChamp(i) retourne la bonne valeur. */
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "1;1-3;3;Numéro de Département;cadré à gauche. Ex: dept 13 = 130;numDepartement;Integer;false;false;1;3;3;"
				, descriptionChamp1.fournirLigneValeursCsv());
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "2;4-9;6;Numéro de Section;;numSection;String;false;false;4;9;6;"
				, descriptionChamp2.fournirLigneValeursCsv());
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "102;509-520;12;Zone libre;12 espaces;sans objet;sans objet;false;false;509;520;12;"
				, descriptionChamp102.fournirLigneValeursCsv());
		
		/* garantit que getDescriptionChamp(hors index) retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, descriptionChamp103);
		
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
			System.out.println("********** CLASSE ImportateurDescriptionHistoF08Test - méthode testGetDescriptionDuFichierFile() ********** ");
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
			System.out.println("********** CLASSE ImportateurDescriptionHistoF08Test - méthode testGetLabelDescriptionFichier() ********** ");
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
		importateur = new ImportateurDescriptionHistoF08();
		
		// IMPORT de la bonne description de fichier encodée en UTF-8 
		// (contenue sous classpath/resources).
		importateur.importerDescriptionUtf8();
		
	} // Fin de instancierImportateur().___________________________________
	
	
	
} // FIN DE LA CLASSE ImportateurDescriptionHistoF08Test.--------------------
