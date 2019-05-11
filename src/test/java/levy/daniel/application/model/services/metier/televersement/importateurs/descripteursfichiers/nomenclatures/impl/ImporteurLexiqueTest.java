package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.NomenclatureMauvaiseRunTimeException;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.IImporteurLexique;

/**
 * CLASSE ImporteurLexiqueTest :<br/>
 * Test JUnit de la classe {@link ImporteurLexique}.<br/>
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
 * @since 6 mai 2019
 *
 */
public class ImporteurLexiqueTest {

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
	 * new ImporteurLexique().<br/>
	 */
	public static final IImporteurLexique IMPORTEUR_LEXIQUE 
		= new ImporteurLexique();

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
	 * "Nomenclature importée : ".
	 */
	public static final String NOMENCLATURE_IMPORTEE 
		= "Nomenclature importée : ";
	
	/**
	 * "NOMENCLATURE GENEREE : ".
	 */
	public static final String NOMENCLATURE_GENEREE 
		= "NOMENCLATURE GENEREE : ";
	
	/**
	 * "AFFICHAGE DE this.nomenclatureMap : ".
	 */
	public static final String AFFICHAGE_NOMENCLATUREMAP 
		= "AFFICHAGE DE this.nomenclatureMap : ";
	
	/**
	 * "Clés possibles : ".
	 */
	public static final String CLES_POSSIBLES 
		= "Clés possibles : ";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(ImporteurLexiqueTest.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ImporteurLexiqueTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * Teste la méthode ImporterLexiqueEnUtf8(null).<br/>
	 * <ul>
	 * <li>garantit que importerLexiqueEnUtf8(null) 
	 * jette une FichierNullException.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testImporterLexiqueEnUtf8Null() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurLexiqueTest - méthode testImporterLexiqueEnUtf8Null() ********** ");
		}
		
		try {
			
			// APPEL DE LA METHODE A TESTER **************
			IMPORTEUR_LEXIQUE.importerLexiqueEnUtf8(null);
			
		} catch (Exception e) {
			
			/* garantit que importerLexiqueEnUtf8(null) 
			 * jette une FichierNullException. */
			assertTrue(
					"importerLexiqueEnUtf8(null) doit jeter une FichierNullException :"
					, e instanceof FichierNullException); 
		}
		
	} // Fin de testImporterLexiqueEnUtf8Null().______________________

	
	
	/**
	 * Teste la méthode ImporterLexiqueEnUtf8(vide).<br/>
	 * <ul>
	 * <li>garantit que importerLexiqueEnUtf8(vide) 
	 * jette une FichierVideException.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testImporterLexiqueEnUtf8Vide() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurLexiqueTest - méthode testImporterLexiqueEnUtf8Vide() ********** ");
		}
		
		try {
			
			final Path nomenclatureVidePath 
			= PATH_ABSOLU_TEST_NOMENCLATURES.resolve("nomenclature_vide.csv");
			
			final File nomenclatureVide = nomenclatureVidePath.toFile();
			
			// APPEL DE LA METHODE A TESTER **************
			// IMPORT
			IMPORTEUR_LEXIQUE.importerLexiqueEnUtf8(nomenclatureVide);
			
		} catch (Exception e) {
			
			/* garantit que importerLexiqueEnUtf8(vide) 
			 * jette une FichierVideException. */
			assertTrue(
					"importerLexiqueEnUtf8(vide) doit jeter une FichierVideException :"
					, e instanceof FichierVideException); 
		}
		
	} // Fin de testImporterLexiqueEnUtf8Vide().______________________

	
	
	/**
	 * Teste la méthode ImporterLexiqueEnUtf8(inexistant).<br/>
	 * <ul>
	 * <li>garantit que importerLexiqueEnUtf8(inexistant) 
	 * jette une FichierInexistantException.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testImporterLexiqueEnUtf8Inexistant() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurLexiqueTest - méthode testImporterLexiqueEnUtf8Inexistant() ********** ");
		}
		
		try {
			
			final Path nomenclatureInexistantPath 
			= PATH_ABSOLU_TEST_NOMENCLATURES.resolve("nomenclature_inexistant.csv");
			
			final File nomenclatureInexistant = nomenclatureInexistantPath.toFile();
			
			// APPEL DE LA METHODE A TESTER **************
			// IMPORT
			IMPORTEUR_LEXIQUE
				.importerLexiqueEnUtf8(nomenclatureInexistant);
			
		} catch (Exception e) {
			
			/* garantit que importerLexiqueEnUtf8(inexistant) 
			 * jette une FichierInexistantException. */
			assertTrue(
					"importerLexiqueEnUtf8(inexistant) doit jeter une FichierInexistantException :"
					, e instanceof FichierInexistantException); 
		}
		
	} // Fin de testImporterLexiqueEnUtf8Inexistant().________________

	
	
	/**
	 * Teste la méthode ImporterLexiqueEnUtf8(repertoire).<br/>
	 * <ul>
	 * <li>garantit que importerLexiqueEnUtf8(repertoire) 
	 * jette une FichierPasNormalException.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testImporterLexiqueEnUtf8Repertoire() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurLexiqueTest - méthode testImporterLexiqueEnUtf8Repertoire() ********** ");
		}
		
		try {
			
			final Path nomenclatureRepertoirePath 
				= PATH_ABSOLU_TEST_NOMENCLATURES;
			
			final File nomenclatureRepertoire 
				= nomenclatureRepertoirePath.toFile();
			
			// APPEL DE LA METHODE A TESTER **************
			// IMPORT
			IMPORTEUR_LEXIQUE
				.importerLexiqueEnUtf8(nomenclatureRepertoire);
			
		} catch (Exception e) {
			
			/* garantit que importerLexiqueEnUtf8(repertoire) 
			 * jette une FichierPasNormalException. */
			assertTrue(
					"importerLexiqueEnUtf8(repertoire) doit jeter une FichierPasNormalException :"
					, e instanceof FichierPasNormalException); 
		}
		
	} // Fin de testImporterLexiqueEnUtf8Repertoire().________________


	
	/**
	 * Teste la méthode ImporterLexiqueEnUtf8(mauvaisenomenclature) 
	 * avec une nomenclature [String - String] non csv.<br/>
	 * <ul>
	 * <li>garantit que importerLexiqueEnUtf8(nomenclature) 
	 * retourne une Map non null.</li>
	 * <li>garantit que importerLexiqueEnUtf8(nomenclature) 
	 * retourne une Map non vide.</li>
	 * <li>garantit que importerLexiqueEnUtf8(nomenclature) 
	 * alimente this.nomenclature.</li>
	 * <li>garantit que importerLexiqueEnUtf8(nomenclature) 
	 * alimente this.clesPossibles.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testImporterMauvaiseNomenclatureEnUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurLexiqueTest - méthode testImporterMauvaiseNomenclatureEnUtf8() ********** ");
		}
		
		final Path mauvaiseNomenclaturePath 
		= PATH_ABSOLU_TEST_NOMENCLATURES.resolve("nomenclature_pas_csv.csv");
	
		final File mauvaiseNomenclature 
			= mauvaiseNomenclaturePath.toFile();
		
		try {
			
			// APPEL DE LA METHODE A TESTER **************
			// IMPORT ******************
			IMPORTEUR_LEXIQUE
				.importerLexiqueEnUtf8(mauvaiseNomenclature);
			
			final File nomenclatureImportee 
				= IMPORTEUR_LEXIQUE.getLexique();
			
			final Set<String> clesPossibles 
				= IMPORTEUR_LEXIQUE.getClesPossiblesSet();
				
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
				System.out.println();
				System.out.println(AFFICHAGE_NOMENCLATUREMAP);
				System.out.println(IMPORTEUR_LEXIQUE.afficherLexiqueMap());
				System.out.println();
				System.out.println(NOMENCLATURE_GENEREE);
				System.out.println(IMPORTEUR_LEXIQUE.genererNomenclatureCsvString());
				System.out.println();
				System.out.println(CLES_POSSIBLES + clesPossibles.toString());
			}
			
		} catch (Exception e) {
			
			/* garantit que importerLexiqueEnUtf8(mauvaisenomenclature) 
			 * jette une NomenclatureMauvaiseRunTimeException. */
			assertTrue(
					"importerLexiqueEnUtf8(mauvaisenomenclature) doit jeter une NomenclatureMauvaiseRunTimeException :"
					, e instanceof NomenclatureMauvaiseRunTimeException); 
		}

	} // Fin de testImporterMauvaiseNomenclatureEnUtf8().__________________

	
	
	/**
	 * Teste la méthode ImporterLexiqueEnUtf8(nomenclature).<br/>
	 * <ul>
	 * <li>garantit que importerLexiqueEnUtf8(nomenclature) 
	 * retourne une Map non null.</li>
	 * <li>garantit que importerLexiqueEnUtf8(nomenclature) 
	 * retourne une Map non vide.</li>
	 * <li>garantit que importerLexiqueEnUtf8(nomenclature) 
	 * alimente this.nomenclature.</li>
	 * <li>garantit que importerLexiqueEnUtf8(nomenclature) 
	 * alimente this.clesPossibles.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 * @throws IOException 
	 * @throws FichierPasNormalException 
	 * @throws FichierInexistantException 
	 * @throws FichierVideException 
	 * @throws FichierNullException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testImporterLexiqueEnUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurLexiqueTest - méthode testImporterLexiqueEnUtf8() ********** ");
		}
					
		final Path nomenclaturePath 
			= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
					"HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ProfilTravers_HistoF07_Utf8.csv");
		
		final File nomenclature 
			= nomenclaturePath.toFile();
		
		// APPEL DE LA METHODE A TESTER **************
		// IMPORT ******************
		final Map<String, String> resultat 
		= IMPORTEUR_LEXIQUE
			.importerLexiqueEnUtf8(nomenclature);
		
		final File nomenclatureImportee 
			= IMPORTEUR_LEXIQUE.getLexique();
		
		final Set<String> clesPossibles 
			= IMPORTEUR_LEXIQUE.getClesPossiblesSet();
			
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println(AFFICHAGE_NOMENCLATUREMAP);
			System.out.println(IMPORTEUR_LEXIQUE.afficherLexiqueMap());
			System.out.println();
			System.out.println(NOMENCLATURE_GENEREE);
			System.out.println(IMPORTEUR_LEXIQUE.genererNomenclatureCsvString());
			System.out.println();
			System.out.println(CLES_POSSIBLES + clesPossibles.toString());
		}
		
		/* garantit que importerLexiqueEnUtf8(nomenclature) 
		 * retourne une Map non null. */
		assertNotNull(
				"importerLexiqueEnUtf8(nomenclature) doit retourner une map non null : "
				, resultat);
		
		/* garantit que importerLexiqueEnUtf8(nomenclature) 
		 * retourne une Map non vide. */
		assertFalse(
				"importerLexiqueEnUtf8(nomenclature) doit retourner une map non vide : "
				, resultat.isEmpty());
		
		/* garantit que importerLexiqueEnUtf8(nomenclature) 
		 * alimente this.nomenclature. */
		assertNotNull(
				"importerLexiqueEnUtf8(nomenclature) doit creer nomenclature non null : "
				, nomenclatureImportee);
		assertTrue(
				"importerLexiqueEnUtf8(nomenclature) doit creer nomenclature existant : "
				, nomenclatureImportee.exists());
		
		/* garantit que importerLexiqueEnUtf8(nomenclature) 
		 * alimente this.clesPossibles. */
		assertNotNull(
				"importerLexiqueEnUtf8(nomenclature) doit creer clesPossibles non null : "
				, clesPossibles);
		assertTrue(
				"importerLexiqueEnUtf8(nomenclature) doit creer clesPossibles avec 12 éléments : "
				, clesPossibles.size() == 12);
		
	} // Fin de testImporterLexiqueEnUtf8().________________


	
	/**
	 * Teste la méthode fournirEnteteParColonne().<br/>
	 * <ul>
	 * <li>garantit que fournirEnteteParColonne(hors indice) retourne null.</li>
	 * <li>garantit que fournirEnteteParColonne(1) retourne 'Clé'.</li>
	 * <li>garantit que fournirEnteteParColonne(2) retourne 'Libellé'.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 * @throws IOException 
	 * @throws FichierPasNormalException 
	 * @throws FichierInexistantException 
	 * @throws FichierVideException 
	 * @throws FichierNullException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirEnteteParColonne() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurLexiqueTest - méthode testFournirEnteteParColonne() ********** ");
		}
		
		final Path nomenclaturePath 
		= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
				"HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv");
	
		final File nomenclature 
			= nomenclaturePath.toFile();
		
		// IMPORT ******************
		IMPORTEUR_LEXIQUE
			.importerLexiqueEnUtf8(nomenclature);
		
		
		final File nomenclatureImportee 
			= IMPORTEUR_LEXIQUE.getLexique();
		
		final Set<String> clesPossibles 
			= IMPORTEUR_LEXIQUE.getClesPossiblesSet();
		
		// APPEL DE LA METHODE A TESTER **************
		final String entete0 = IMPORTEUR_LEXIQUE.fournirEnteteParColonne(0);
		final String entete1 = IMPORTEUR_LEXIQUE.fournirEnteteParColonne(1);
		final String entete2 = IMPORTEUR_LEXIQUE.fournirEnteteParColonne(2);
		final String entete3 = IMPORTEUR_LEXIQUE.fournirEnteteParColonne(3);
			
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println(AFFICHAGE_NOMENCLATUREMAP);
			System.out.println(IMPORTEUR_LEXIQUE.afficherLexiqueMap());
			System.out.println();
			System.out.println(NOMENCLATURE_GENEREE);
			System.out.println(IMPORTEUR_LEXIQUE.genererNomenclatureCsvString());
			System.out.println();
			System.out.println(CLES_POSSIBLES + clesPossibles.toString());
			System.out.println();
			System.out.println("entete0 : " + entete0);
			System.out.println("entete1 : " + entete1);
			System.out.println("entete2 : " + entete2);
			System.out.println("entete3 : " + entete3);
		}

		/* garantit que fournirEnteteParColonne(hors indice) retourne null. */
		assertNull(
				"fournirEnteteParColonne(hors indice) doit retourner null : "
				, entete0);
		assertNull(
				"fournirEnteteParColonne(hors indice) doit retourner null : "
				, entete3);
		
		/* garantit que fournirEnteteParColonne(1) retourne Clé. */
		assertEquals(
				"fournirEnteteParColonne(1) doit retourner 'clé' : "
					, "Clé"
						, entete1);
		
		/* garantit que fournirEnteteParColonne(2) retourne Libellé. */
		assertEquals(
				"fournirEnteteParColonne(2) doit retourner 'Libellé' : "
					, "Libellé"
						, entete2);

	} // Fin de testFournirEnteteParColonne()._____________________________
	

	
	/**
	 * Teste la méthode fournirValeurParLigneColonne(int pL, int pColonne).<br/>
	 * <ul>
	 * <li>garantit que fournirValeurParLigneColonne(hors indice) retourne null.</li>
	 * <li>garantit que fournirValeurParLigneColonne(bonnesValeurs) 
	 * retourne la bonne valeur.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirValeurParLigneColonne() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurLexiqueTest - méthode testFournirValeurParLigneColonne() ********** ");
		}
		
		final Path nomenclaturePath 
		= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
				"HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv");
	
		final File nomenclature 
			= nomenclaturePath.toFile();
		
		// IMPORT ******************
		IMPORTEUR_LEXIQUE
			.importerLexiqueEnUtf8(nomenclature);
		
		
		final File nomenclatureImportee 
			= IMPORTEUR_LEXIQUE.getLexique();
		
		final Set<String> clesPossibles 
			= IMPORTEUR_LEXIQUE.getClesPossiblesSet();
		
		// APPEL DE LA METHODE A TESTER **************
		final String valeurL0C0 
			= IMPORTEUR_LEXIQUE.fournirValeurParLigneColonne(0, 0);
		final String valeurL1C1 
			= IMPORTEUR_LEXIQUE.fournirValeurParLigneColonne(1, 1);
		final String valeurL3C2 
			= IMPORTEUR_LEXIQUE.fournirValeurParLigneColonne(3, 2);
		final String valeurL6C2 
			= IMPORTEUR_LEXIQUE.fournirValeurParLigneColonne(6, 2);
		final String valeurL7C4 
			= IMPORTEUR_LEXIQUE.fournirValeurParLigneColonne(7, 4);
			
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println(AFFICHAGE_NOMENCLATUREMAP);
			System.out.println(IMPORTEUR_LEXIQUE.afficherLexiqueMap());
			System.out.println();
			System.out.println(NOMENCLATURE_GENEREE);
			System.out.println(IMPORTEUR_LEXIQUE.genererNomenclatureCsvString());
			System.out.println();
			System.out.println(CLES_POSSIBLES + clesPossibles.toString());
			System.out.println();
			System.out.println("valeurL0C0 : " + valeurL0C0);
			System.out.println("valeurL1C1 : " + valeurL1C1);
			System.out.println("valeurL3C2 : " + valeurL3C2);
			System.out.println("valeurL6C2 : " + valeurL6C2);
			System.out.println("valeurL7C4 : " + valeurL7C4);
		}
		

		/* garantit que fournirValeurParLigneColonne(hors indice) retourne null. */
		assertNull(
				"fournirValeurParLigneColonne(hors indice) doit retourner null : "
				, valeurL0C0);
		assertNull(
				"fournirValeurParLigneColonne(hors indice) doit retourner null : "
				, valeurL7C4);
		
		/* garantit que fournirValeurParLigneColonne(1,1) retourne '1'. */
		assertEquals(
				"fournirValeurParLigneColonne(1,1) doit retourner '1' : "
					, "1"
						, valeurL1C1);
		
		/* garantit que fournirValeurParLigneColonne(3,2) retourne 
		 * 'Route nationale interurbaine à caractéristiques autoroutières'. */
		assertEquals(
				"fournirValeurParLigneColonne(3,2) doit retourner "
				+ "'Route nationale interurbaine à caractéristiques autoroutières' : "
					, "Route nationale interurbaine à caractéristiques autoroutières"
						, valeurL3C2);

	} // Fin de testFournirValeurParLigneColonne().________________________

	
	
	/**
	 * Teste la méthode fournirLigneEnTetesCsv().<br/>
	 * <ul>
	 * <li>garantit que fournirLigneEnTetesCsv() retourne 'Clé;Libellé;'.</li>
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
			System.out.println("********** CLASSE ImporteurLexiqueTest - méthode testFournirLigneEnTetesCsv() ********** ");
		}

		// APPEL DE LA METHODE A TESTER **************
		final String enTeteCsv 
			= IMPORTEUR_LEXIQUE.fournirLigneEnTetesCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("enTeteCsv : " + enTeteCsv);
		}

		/* garantit que fournirLigneEnTetesCsv() retourne 'Clé;Libellé;'. */
		assertEquals("fournirLigneEnTetesCsv() doit retourner 'Clé;Libellé;' : "
				, "Clé;Libellé;"
				, enTeteCsv);
		
	} // Fin de testFournirLigneEnTetesCsv().______________________________


	
	/**
	 * Teste la méthode fournirLigneValeursCsv(int pL).<br/>
	 * <ul>
	 * <li>garantit que fournirLigneValeursCsv(hors indice) retourne null.</li>
	 * <li>garantit que fournirLigneValeursCsv(bon indice) 
	 * retourne la bonne valeur.</li>
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
			System.out.println("********** CLASSE ImporteurLexiqueTest - méthode testFournirLigneValeursCsv() ********** ");
		}
			
		final Path nomenclaturePath 
		= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
				"HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv");
	
		final File nomenclature 
			= nomenclaturePath.toFile();
		
		// IMPORT ******************
		IMPORTEUR_LEXIQUE
			.importerLexiqueEnUtf8(nomenclature);
		
		
		final File nomenclatureImportee 
			= IMPORTEUR_LEXIQUE.getLexique();
		
		final Set<String> clesPossibles 
			= IMPORTEUR_LEXIQUE.getClesPossiblesSet();
		
		// APPEL DE LA METHODE A TESTER **************
		final String valeurL0 
			= IMPORTEUR_LEXIQUE.fournirLigneValeursCsv(0);
		final String valeurL1
			= IMPORTEUR_LEXIQUE.fournirLigneValeursCsv(1);
		final String valeurL6 
			= IMPORTEUR_LEXIQUE.fournirLigneValeursCsv(6);
		final String valeurL7 
			= IMPORTEUR_LEXIQUE.fournirLigneValeursCsv(7);
			
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println(AFFICHAGE_NOMENCLATUREMAP);
			System.out.println(IMPORTEUR_LEXIQUE.afficherLexiqueMap());
			System.out.println();
			System.out.println(NOMENCLATURE_GENEREE);
			System.out.println(IMPORTEUR_LEXIQUE.genererNomenclatureCsvString());
			System.out.println();
			System.out.println(CLES_POSSIBLES + clesPossibles.toString());
			System.out.println();
			System.out.println("valeurL0 : " + valeurL0);
			System.out.println("valeurL1 : " + valeurL1);
			System.out.println("valeurL6 : " + valeurL6);
			System.out.println("valeurL7 : " + valeurL7);
		}

		/* garantit que fournirLigneValeursCsv(hors indice) retourne null. */
		assertNull(
				"fournirLigneValeursCsv(hors indice) doit retourner null : "
				, valeurL0);
		assertNull(
				"fournirLigneValeursCsv(hors indice) doit retourner null : "
				, valeurL7);
		
		/* garantit que fournirLigneValeursCsv(1) retourne '1;Autoroute interurbaine;'. */
		assertEquals(
				"fournirLigneValeursCsv(1) doit retourner '1;Autoroute interurbaine;' : "
					, "1;Autoroute interurbaine;"
						, valeurL1);
		
		/* garantit que fournirLigneValeursCsv(2) retourne '9;Inconnu ou non renseigné;'. */
		assertEquals(
				"fournirLigneValeursCsv(6) doit retourner '9;Inconnu ou non renseigné;' : "
					, "9;Inconnu ou non renseigné;"
						, valeurL6);
		
	} // Fin de testFournirLigneValeursCsv().______________________________
	
	
	
	/**
	 * Teste la méthode genererNomenclatureCsvString(boolean).<br/>
	 * <ul>
	 * <li>garantit que genererNomenclatureCsvString() ne retourne pas null.</li>
	 * <li>garantit que genererNomenclatureCsvString(true) incorpore l'en-tête.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGenererNomenclatureCsvString() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurLexiqueTest - méthode testGenererNomenclatureCsvString() ********** ");
		}
				
		final Path nomenclaturePath 
		= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
				"HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv");
	
		final File nomenclature 
			= nomenclaturePath.toFile();
		
		// IMPORT ******************
		IMPORTEUR_LEXIQUE
			.importerLexiqueEnUtf8(nomenclature);
		
		
		final File nomenclatureImportee 
			= IMPORTEUR_LEXIQUE.getLexique();
		
		final Set<String> clesPossibles 
			= IMPORTEUR_LEXIQUE.getClesPossiblesSet();
		
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println(AFFICHAGE_NOMENCLATUREMAP);
			System.out.println(IMPORTEUR_LEXIQUE.afficherLexiqueMap());
			System.out.println();
			System.out.println(NOMENCLATURE_GENEREE);
			System.out.println(IMPORTEUR_LEXIQUE.genererNomenclatureCsvString());
			System.out.println();
			System.out.println(CLES_POSSIBLES + clesPossibles.toString());
		}
		
		// APPEL DE LA METHODE A TESTER **************
		final String nomenclatureGenereeSansEntete 
			= IMPORTEUR_LEXIQUE.genererNomenclatureCsvString(false);
		
		final String nomenclatureGenereeAvecEntete 
			= IMPORTEUR_LEXIQUE.genererNomenclatureCsvString(true);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("nomenclature sans en-tête : ");
			System.out.println(nomenclatureGenereeSansEntete);
			System.out.println();
			System.out.println("nomenclature avec en-tête : ");
			System.out.println(nomenclatureGenereeAvecEntete);
		}
		
		/* garantit que genererNomenclatureCsvString() ne retourne pas null. */
		assertNotNull(
				"nomenclatureGenereeSansEntete ne doit pas être null : "
					, nomenclatureGenereeSansEntete);
		assertNotNull(
				"nomenclatureGenereeAvecEntete ne doit pas être null : "
					, nomenclatureGenereeAvecEntete);
		
		/* garantit que genererNomenclatureCsvString(true) incorpore l'en-tête. */
		assertTrue(
				"genererNomenclatureCsvString(true) doit contenir l'en-tête : "
					, nomenclatureGenereeAvecEntete
					.contains(IMPORTEUR_LEXIQUE.fournirLigneEnTetesCsv()));
		
	} // Fin de testGenererNomenclatureCsvString().________________________

	
	
	/**
	 * Teste la méthode genererNomenclatureCsvFileUtf8().<br/>
	 * <ul>
	 * li>garantit que genererNomenclatureCsvFileUtf8(...) 
	 * génère la nomenclature sur disque.</li>
	 * <li>détruit le fichier généré à la fin du test.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGenererNomenclatureCsvFileUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurLexiqueTest - méthode testGenererNomenclatureCsvFileUtf8() ********** ");
		}
				
		final Path nomenclaturePath 
		= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
				"DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv");
		
		final File nomenclature 
			= nomenclaturePath.toFile();
		
		// IMPORT ******************
		IMPORTEUR_LEXIQUE
			.importerLexiqueEnUtf8(nomenclature);
				
		final File nomenclatureImportee 
			= IMPORTEUR_LEXIQUE.getLexique();
		
		final Set<String> clesPossibles 
			= IMPORTEUR_LEXIQUE.getClesPossiblesSet();
		
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println(AFFICHAGE_NOMENCLATUREMAP);
			System.out.println(IMPORTEUR_LEXIQUE.afficherLexiqueMap());
			System.out.println();
			System.out.println(NOMENCLATURE_GENEREE);
			System.out.println(IMPORTEUR_LEXIQUE.genererNomenclatureCsvString());
			System.out.println();
			System.out.println(CLES_POSSIBLES + clesPossibles.toString());
		}
		
		// APPEL DE LA METHODE A TESTER **************
		final File nomenclatureGeneree 
			= IMPORTEUR_LEXIQUE
				.genererNomenclatureCsvFileUtf8();
		
		final Path nomenclatureGenereePath = nomenclatureGeneree.toPath();
	
		/* garantit que genererNomenclatureCsvFile(...) 
		 * génère la nomenclature sur disque. */
		assertTrue(
				"le fichier généré doit exister sur disque : "
					, nomenclatureGeneree.exists());
		
		/* détruit le fichier généré à la fin du test. */
		if (nomenclatureGenereePath.toFile().exists()) {
			Files.delete(nomenclatureGenereePath);
		}
		
		if (PATH_ABSOLU_REPERTOIRE_TEMP.toFile().exists()) {
			Files.delete(PATH_ABSOLU_REPERTOIRE_TEMP);
		}
		
		
		assertFalse("le fichier généré ne doit plus exister sur disque : "
				, nomenclatureGeneree.exists());

	} // Fin de testGenererNomenclatureCsvFileUtf8().______________________

	
	
	/**
	 * Teste la méthode genererNomenclatureCsvFileLatin9().<br/>
	 * <ul>
	 * li>garantit que genererNomenclatureCsvFileLatin9(...) 
	 * génère la nomenclature sur disque.</li>
	 * <li>détruit le fichier généré à la fin du test.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGenererNomenclatureCsvFileLatin9() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurLexiqueTest - méthode testGenererNomenclatureCsvFileLatin9() ********** ");
		}
				
		final Path nomenclaturePath 
		= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
				"DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv");
		
		final File nomenclature 
			= nomenclaturePath.toFile();
		
		// IMPORT ******************
		IMPORTEUR_LEXIQUE
			.importerLexiqueEnUtf8(nomenclature);
				
		final File nomenclatureImportee 
			= IMPORTEUR_LEXIQUE.getLexique();
		
		final Set<String> clesPossibles 
			= IMPORTEUR_LEXIQUE.getClesPossiblesSet();
		
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println(AFFICHAGE_NOMENCLATUREMAP);
			System.out.println(IMPORTEUR_LEXIQUE.afficherLexiqueMap());
			System.out.println();
			System.out.println(NOMENCLATURE_GENEREE);
			System.out.println(IMPORTEUR_LEXIQUE.genererNomenclatureCsvString());
			System.out.println();
			System.out.println(CLES_POSSIBLES + clesPossibles.toString());
		}
		
		// APPEL DE LA METHODE A TESTER **************
		final File nomenclatureGeneree 
			= IMPORTEUR_LEXIQUE
				.genererNomenclatureCsvFileLatin9();
		
		final Path nomenclatureGenereePath = nomenclatureGeneree.toPath();
	
		/* garantit que genererNomenclatureCsvFile(...) 
		 * génère la nomenclature sur disque. */
		assertTrue(
				"le fichier généré doit exister sur disque : "
					, nomenclatureGeneree.exists());
		
		/* détruit le fichier généré à la fin du test. */
		if (nomenclatureGenereePath.toFile().exists()) {
			Files.delete(nomenclatureGenereePath);
		}
		
		if (PATH_ABSOLU_REPERTOIRE_TEMP.toFile().exists()) {
			Files.delete(PATH_ABSOLU_REPERTOIRE_TEMP);
		}
		
		
		assertFalse("le fichier généré ne doit plus exister sur disque : "
				, nomenclatureGeneree.exists());

	} // Fin de testGenererNomenclatureCsvFileLatin9().____________________
	

	
	/**
	 * Teste la méthode genererNomenclatureCsvFile(boolean, File, Charset).<br/>
	 * <ul>
	 * <li>garantit que genererNomenclatureCsvFile(...) 
	 * génère la nomenclature sur disque.</li>
	 * <li>détruit le fichier généré à la fin du test.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGenererNomenclatureCsvFile() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurLexiqueTest - méthode testGenererNomenclatureCsvFile() ********** ");
		}
				
		final Path nomenclaturePath 
		= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
				"HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv");
	
		final File nomenclature 
			= nomenclaturePath.toFile();
		
		// IMPORT ******************
		IMPORTEUR_LEXIQUE
			.importerLexiqueEnUtf8(nomenclature);
		
		
		final File nomenclatureImportee 
			= IMPORTEUR_LEXIQUE.getLexique();
		
		final Set<String> clesPossibles 
			= IMPORTEUR_LEXIQUE.getClesPossiblesSet();
		
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println(AFFICHAGE_NOMENCLATUREMAP);
			System.out.println(IMPORTEUR_LEXIQUE.afficherLexiqueMap());
			System.out.println();
			System.out.println(NOMENCLATURE_GENEREE);
			System.out.println(IMPORTEUR_LEXIQUE.genererNomenclatureCsvString());
			System.out.println();
			System.out.println(CLES_POSSIBLES + clesPossibles.toString());
		}
		
		final Path nomenclatureGenereePath 
			= PATH_ABSOLU_REPERTOIRE_TEMP.resolve("nomenclatureGeneree.csv");
		
		final File nomenclatureGeneree = nomenclatureGenereePath.toFile();
		
		final Charset charsetUtf8 = Charset.forName("UTF-8");
		
		// APPEL DE LA METHODE A TESTER **************
		IMPORTEUR_LEXIQUE
			.genererNomenclatureCsvFile(
					true, nomenclatureGeneree, charsetUtf8);
		
		/* garantit que genererNomenclatureCsvFile(...) 
		 * génère la nomenclature sur disque. */
		assertTrue(
				"le fichier généré doit exister sur disque : "
					, nomenclatureGeneree.exists());
		
		/* détruit le fichier généré à la fin du test. */
		if (nomenclatureGenereePath.toFile().exists()) {
			Files.delete(nomenclatureGenereePath);
		}
		
		if (PATH_ABSOLU_REPERTOIRE_TEMP.toFile().exists()) {
			Files.delete(PATH_ABSOLU_REPERTOIRE_TEMP);
		}

	} // Fin de testGenererNomenclatureCsvFile(...)._______________________
	
	
		
} // FIN DE LA CLASSE ImporteurLexiqueTest.----------------------------------
