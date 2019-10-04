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
 * CLASSE ImportateurDescriptionMappingTest :<br/>
 * Test JUnit de la classe {@link ImportateurDescriptionMapping}.<br/>
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
public class ImportateurDescriptionMappingTest {

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
		= LogFactory.getLog(ImportateurDescriptionMappingTest.class);
	
	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ImportateurDescriptionMappingTest() {
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
			System.out.println("********** CLASSE ImportateurDescriptionMappingTest - méthode testImporterDescriptionUtf8() ********** ");
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
				, "ordreChampsHistonat;intituleHistonat;champJavaHistonat;colonneDebut;colonneFin;longueurCalculee;ordreChampsHit;intituleHit;champJavaHit;ordreChampsDarwin;intituleDarwin;champJavaDarwin;baliseIsidor;isLocalisant;"
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
			System.out.println("********** CLASSE ImportateurDescriptionMappingTest - méthode testFournirEnteteparColonne() ********** ");
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
		final String fournirEnteteparColonne14 
			= importateur.fournirEnteteparColonne(14);
		
		final String fournirEnteteparColonne15 
			= importateur.fournirEnteteparColonne(15);
		
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
			System.out.println("fournirEnteteparColonne14 : " + fournirEnteteparColonne14);
			
			System.out.println("fournirEnteteparColonne15 : " + fournirEnteteparColonne15);
			
		}
		
		/* garantit que fournirEnteteparColonne(0) null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, fournirEnteteparColonne0);
		
		/* garantit que fournirEnteteparColonne(i) retourne la bonne valeur. */
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "ordreChampsHistonat"
						, fournirEnteteparColonne1);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "intituleHistonat"
						, fournirEnteteparColonne2);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "champJavaHistonat"
						, fournirEnteteparColonne3);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "colonneDebut"
						, fournirEnteteparColonne4);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "colonneFin"
						, fournirEnteteparColonne5);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "longueurCalculee"
						, fournirEnteteparColonne6);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "ordreChampsHit"
						, fournirEnteteparColonne7);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "intituleHit"
						, fournirEnteteparColonne8);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "champJavaHit"
						, fournirEnteteparColonne9);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "ordreChampsDarwin"
						, fournirEnteteparColonne10);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "intituleDarwin"
						, fournirEnteteparColonne11);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "champJavaDarwin"
						, fournirEnteteparColonne12);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "baliseIsidor"
						, fournirEnteteparColonne13);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "isLocalisant"
						, fournirEnteteparColonne14);

		
		/* garantit que fournirEnteteparColonne(hors index) retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, fournirEnteteparColonne15);
		
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
			System.out.println("********** CLASSE ImportateurDescriptionMappingTest - méthode testFournirLigneEnTetesCsv() ********** ");
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
					, "ordreChampsHistonat;intituleHistonat;champJavaHistonat;colonneDebut;colonneFin;longueurCalculee;ordreChampsHit;intituleHit;champJavaHit;ordreChampsDarwin;intituleDarwin;champJavaDarwin;baliseIsidor;isLocalisant;"
						, ligneEnTetesCsv);
		assertEquals("l'en-tête doit comporter 14 éléments : "
				, 14
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
			System.out.println("********** CLASSE ImportateurDescriptionMappingTest - méthode testFournirLigneValeursCsv() ********** ");
		}
		
		// METHODE A TESTER.
		final String ligneValeursCsv0 = importateur.fournirLigneValeursCsv(0);
		final String ligneValeursCsv1 = importateur.fournirLigneValeursCsv(1);
		final String ligneValeursCsv2 = importateur.fournirLigneValeursCsv(2);
		final String ligneValeursCsv65 = importateur.fournirLigneValeursCsv(65);
		final String ligneValeursCsv66 = importateur.fournirLigneValeursCsv(66);
		final String ligneValeursCsv110 = importateur.fournirLigneValeursCsv(110);
		
		final String ligneValeursCsv111 = importateur.fournirLigneValeursCsv(111);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("ligneValeursCsv0 : " + ligneValeursCsv0);
			System.out.println("ligneValeursCsv1 : " + ligneValeursCsv1);
			System.out.println("ligneValeursCsv2 : " + ligneValeursCsv2);
			System.out.println("ligneValeursCsv65 : " + ligneValeursCsv65);
			System.out.println("ligneValeursCsv66 : " + ligneValeursCsv66);
			System.out.println("ligneValeursCsv110 : " + ligneValeursCsv110);
			
			System.out.println("ligneValeursCsv111 : " + ligneValeursCsv111);
		}
		
		final String ligneEnTetesCsv = importateur.fournirLigneEnTetesCsv();
		
		/* garantit que fournirLigneValeursCsv(0) retourne l'en-tête de la description de fichier. */
		assertEquals("fournirLigneValeursCsv(0) doit valoir importateur.getDescriptionChamp().fournirLigneEnTetesCsv() : "
				, ligneValeursCsv0
				, ligneEnTetesCsv);
		
		/* garantit que fournirLigneValeursCsv(i) retourne la bonne valeur. */
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "1;Numéro de Département;numDepartement;1;3;3;1;Numéro de Département;numDepartement;null;null;null;numDepartement;false;"
				, ligneValeursCsv1);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "2;Numéro de Section ;numSectionTrafic;4;9;6;2;Numéro de Section;numSection;12;Numéro de la Section de Trafic;numSectionTrafic;numSectionTrafic;false;"
				, ligneValeursCsv2);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "65;Année N-2;anneeNMoins2;306;307;2;65;Année N-2;anneeNMoins2;null;null;null;anneeNMoins2;false;"
				, ligneValeursCsv65);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "66;MJA   N-2;tmja2;308;313;6;66;MJA N-2;mjaNmoins2;21;MJA N-2;mjaNmoins2;tmja2;false;"
				, ligneValeursCsv66);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "110;Zone libre;sans objet;509;520;12;102;Zone libre;sans objet;null;null;null;sans objet;null;"
				, ligneValeursCsv110);
		
		/* garantit que fournirLigneValeursCsv(hors index) retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, ligneValeursCsv111);
		
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
			System.out.println("********** CLASSE ImportateurDescriptionMappingTest - méthode testFournirValeurparLigneColonne() ********** ");
		}
		
		// METHODE A TESTER.
		final String valeurl0c0 = importateur.fournirValeurparLigneColonne(0, 0);
		final String valeurl0c1 = importateur.fournirValeurparLigneColonne(0, 1);
		final String valeurl0c2 = importateur.fournirValeurparLigneColonne(0, 2);
		final String valeurl0c7 = importateur.fournirValeurparLigneColonne(0, 7);
		final String valeurl0c8 = importateur.fournirValeurparLigneColonne(0, 8);
		final String valeurl0c14 = importateur.fournirValeurparLigneColonne(0, 14);
		final String valeurl0c15 = importateur.fournirValeurparLigneColonne(0, 15);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("valeurl0c0 : " + valeurl0c0);
			System.out.println("valeurl0c1 : " + valeurl0c1);
			System.out.println("valeurl0c2 : " + valeurl0c2);
			System.out.println("valeurl0c7 : " + valeurl0c7);
			System.out.println("valeurl0c8 : " + valeurl0c8);
			System.out.println("valeurl0c14 : " + valeurl0c14);
			System.out.println("valeurl0c15 : " + valeurl0c15);
		}
		
		/* garantit que fournirValeurparLigneColonne(0, pCol) 
		 * retourne une valeur de l'en-tête de la description de fichier. */
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "ordreChampsHistonat"
					, valeurl0c1);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "intituleHistonat"
					, valeurl0c2);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "ordreChampsHit"
					, valeurl0c7);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "intituleHit"
					, valeurl0c8);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "isLocalisant"
					, valeurl0c14);
		
		/* garantit que fournirValeurparLigneColonne(0, inexistant) 
		 * retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl0c0);
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl0c15);
		
		
		// METHODE A TESTER.
		final String valeurl27c0 = importateur.fournirValeurparLigneColonne(27, 0);
		final String valeurl27c1 = importateur.fournirValeurparLigneColonne(27, 1);
		final String valeurl27c2 = importateur.fournirValeurparLigneColonne(27, 2);
		final String valeurl27c3 = importateur.fournirValeurparLigneColonne(27, 3);
		final String valeurl27c8 = importateur.fournirValeurparLigneColonne(27, 8);
		final String valeurl27c14 = importateur.fournirValeurparLigneColonne(27, 14);
		final String valeurl27c15 = importateur.fournirValeurparLigneColonne(27, 15);
		final String valeurl111c7 = importateur.fournirValeurparLigneColonne(111, 7);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("valeurl27c0 : " + valeurl27c0);
			System.out.println("valeurl27c1 : " + valeurl27c1);
			System.out.println("valeurl27c2 : " + valeurl27c2);
			System.out.println("valeurl27c3 : " + valeurl27c3);
			System.out.println("valeurl27c8 : " + valeurl27c8);
			System.out.println("valeurl27c14 : " + valeurl27c14);
			System.out.println("valeurl27c15 : " + valeurl27c15);
			System.out.println("valeurl111c7 : " + valeurl111c7);
		}
		
		/* garantit que fournirValeurparLigneColonne(ligne, pCol) 
		 * retourne la bonne valeur de la description de fichier. */
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "27"
					, valeurl27c1);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "MJA"
					, valeurl27c2);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "tmja"
					, valeurl27c3);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "MJA"
					, valeurl27c8);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "false"
					, valeurl27c14);
		
		/* garantit que fournirValeurparLigneColonne(ligne, inexistant) 
		 * retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl27c0);
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl27c15);
		
		/* garantit que fournirValeurparLigneColonne(inexistant, colonne) 
		 * retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl111c7);
		
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
			System.out.println("********** CLASSE ImportateurDescriptionMappingTest - méthode testGenererDescriptionCsvFile() ********** ");
		}
		
		final Path decriptionGenereePath 
			= PATH_ABSOLU_REPERTOIRE_TEMP
				.resolve("description_MAPPING_generee_UTF-8.csv");
		
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
			System.out.println("********** CLASSE ImportateurDescriptionMappingTest - méthode testGenererDescriptionCsvString() ********** ");
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
			System.out.println("********** CLASSE ImportateurDescriptionMappingTest - méthode testGetDescriptionChamp() ********** ");
		}
		
		// METHODE A TESTER.
		final IDescriptionChamp descriptionChamp0 = importateur.getDescriptionChamp(0);
		final IDescriptionChamp descriptionChamp1 = importateur.getDescriptionChamp(1);
		final IDescriptionChamp descriptionChamp2 = importateur.getDescriptionChamp(2);
		final IDescriptionChamp descriptionChamp110 = importateur.getDescriptionChamp(110);
		final IDescriptionChamp descriptionChamp111 = importateur.getDescriptionChamp(111);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("descriptionChamp0 : " + descriptionChamp0);
			System.out.println("descriptionChamp1 : " + descriptionChamp1.fournirLigneValeursCsv());
			System.out.println("descriptionChamp2 : " + descriptionChamp2.fournirLigneValeursCsv());
			System.out.println("descriptionChamp110 : " + descriptionChamp110.fournirLigneValeursCsv());
			System.out.println("descriptionChamp111 : " + descriptionChamp111);
		}
		
		/* garantit que getDescriptionChamp(0) retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, descriptionChamp0);
		
		/* garantit que getDescriptionChamp(i) retourne la bonne valeur. */
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "1;Numéro de Département;numDepartement;1;3;3;1;Numéro de Département;numDepartement;null;null;null;numDepartement;false;"
				, descriptionChamp1.fournirLigneValeursCsv());
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "2;Numéro de Section ;numSectionTrafic;4;9;6;2;Numéro de Section;numSection;12;Numéro de la Section de Trafic;numSectionTrafic;numSectionTrafic;false;"
				, descriptionChamp2.fournirLigneValeursCsv());
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "110;Zone libre;sans objet;509;520;12;102;Zone libre;sans objet;null;null;null;sans objet;null;"
				, descriptionChamp110.fournirLigneValeursCsv());
		
		/* garantit que getDescriptionChamp(hors index) retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, descriptionChamp111);
		
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
			System.out.println("********** CLASSE ImportateurDescriptionMappingTest - méthode testGetDescriptionDuFichierFile() ********** ");
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
			System.out.println("********** CLASSE ImportateurDescriptionMappingTest - méthode testGetLabelDescriptionFichier() ********** ");
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
		importateur = new ImportateurDescriptionMapping();
		
		// IMPORT de la bonne description de fichier encodée en UTF-8 
		// (contenue sous classpath/resources).
		importateur.importerDescriptionUtf8();
		
	} // Fin de instancierImportateur().___________________________________
	
	
	
} // FIN DE LA CLASSE ImportateurDescriptionMappingTest.--------------------
