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
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.IImporteurNomenclature;

/**
 * CLASSE ImporteurNomenclatureTest :<br/>
 * Test JUnit de la classe {@link ImporteurNomenclature}.<br/>
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
public class ImporteurNomenclatureTest {

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
	 * new ImporteurNomenclature().<br/>
	 */
	public static final IImporteurNomenclature IMPORTEUR_NOMENCLATURE 
		= new ImporteurNomenclature();

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
		= LogFactory.getLog(ImporteurNomenclatureTest.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ImporteurNomenclatureTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * Teste la méthode ImporterNomenclatureEnUtf8(null).<br/>
	 * <ul>
	 * <li>garantit que importerNomenclatureEnUtf8(null) 
	 * jette une FichierNullException.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testImporterNomenclatureEnUtf8Null() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurNomenclatureTest - méthode testImporterNomenclatureEnUtf8Null() ********** ");
		}
		
		try {
			
			// APPEL DE LA METHODE A TESTER **************
			IMPORTEUR_NOMENCLATURE.importerNomenclatureEnUtf8(null);
			
		} catch (Exception e) {
			
			/* garantit que importerNomenclatureEnUtf8(null) 
			 * jette une FichierNullException. */
			assertTrue(
					"importerNomenclatureEnUtf8(null) doit jeter une FichierNullException :"
					, e instanceof FichierNullException); 
		}
		
	} // Fin de testImporterNomenclatureEnUtf8Null().______________________

	
	
	/**
	 * Teste la méthode ImporterNomenclatureEnUtf8(vide).<br/>
	 * <ul>
	 * <li>garantit que importerNomenclatureEnUtf8(vide) 
	 * jette une FichierVideException.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testImporterNomenclatureEnUtf8Vide() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurNomenclatureTest - méthode testImporterNomenclatureEnUtf8Vide() ********** ");
		}
		
		try {
			
			final Path nomenclatureVidePath 
			= PATH_ABSOLU_TEST_NOMENCLATURES.resolve("nomenclature_vide.csv");
			
			final File nomenclatureVide = nomenclatureVidePath.toFile();
			
			// APPEL DE LA METHODE A TESTER **************
			// IMPORT
			IMPORTEUR_NOMENCLATURE.importerNomenclatureEnUtf8(nomenclatureVide);
			
		} catch (Exception e) {
			
			/* garantit que importerNomenclatureEnUtf8(vide) 
			 * jette une FichierVideException. */
			assertTrue(
					"importerNomenclatureEnUtf8(vide) doit jeter une FichierVideException :"
					, e instanceof FichierVideException); 
		}
		
	} // Fin de testImporterNomenclatureEnUtf8Vide().______________________

	
	
	/**
	 * Teste la méthode ImporterNomenclatureEnUtf8(inexistant).<br/>
	 * <ul>
	 * <li>garantit que importerNomenclatureEnUtf8(inexistant) 
	 * jette une FichierInexistantException.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testImporterNomenclatureEnUtf8Inexistant() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurNomenclatureTest - méthode testImporterNomenclatureEnUtf8Inexistant() ********** ");
		}
		
		try {
			
			final Path nomenclatureInexistantPath 
			= PATH_ABSOLU_TEST_NOMENCLATURES.resolve("nomenclature_inexistant.csv");
			
			final File nomenclatureInexistant = nomenclatureInexistantPath.toFile();
			
			// APPEL DE LA METHODE A TESTER **************
			// IMPORT
			IMPORTEUR_NOMENCLATURE
				.importerNomenclatureEnUtf8(nomenclatureInexistant);
			
		} catch (Exception e) {
			
			/* garantit que importerNomenclatureEnUtf8(inexistant) 
			 * jette une FichierInexistantException. */
			assertTrue(
					"importerNomenclatureEnUtf8(inexistant) doit jeter une FichierInexistantException :"
					, e instanceof FichierInexistantException); 
		}
		
	} // Fin de testImporterNomenclatureEnUtf8Inexistant().________________

	
	
	/**
	 * Teste la méthode ImporterNomenclatureEnUtf8(repertoire).<br/>
	 * <ul>
	 * <li>garantit que importerNomenclatureEnUtf8(repertoire) 
	 * jette une FichierPasNormalException.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testImporterNomenclatureEnUtf8Repertoire() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurNomenclatureTest - méthode testImporterNomenclatureEnUtf8Repertoire() ********** ");
		}
		
		try {
			
			final Path nomenclatureRepertoirePath 
				= PATH_ABSOLU_TEST_NOMENCLATURES;
			
			final File nomenclatureRepertoire 
				= nomenclatureRepertoirePath.toFile();
			
			// APPEL DE LA METHODE A TESTER **************
			// IMPORT
			IMPORTEUR_NOMENCLATURE
				.importerNomenclatureEnUtf8(nomenclatureRepertoire);
			
		} catch (Exception e) {
			
			/* garantit que importerNomenclatureEnUtf8(repertoire) 
			 * jette une FichierPasNormalException. */
			assertTrue(
					"importerNomenclatureEnUtf8(repertoire) doit jeter une FichierPasNormalException :"
					, e instanceof FichierPasNormalException); 
		}
		
	} // Fin de testImporterNomenclatureEnUtf8Repertoire().________________


	
	/**
	 * Teste la méthode ImporterNomenclatureEnUtf8(mauvaisenomenclature) 
	 * avec une nomenclature [String - String] au lieu de [Integer - String].<br/>
	 * <ul>
	 * <li>garantit que importerNomenclatureEnUtf8(nomenclature) 
	 * retourne une Map non null.</li>
	 * <li>garantit que importerNomenclatureEnUtf8(nomenclature) 
	 * retourne une Map non vide.</li>
	 * <li>garantit que importerNomenclatureEnUtf8(nomenclature) 
	 * alimente this.nomenclature.</li>
	 * <li>garantit que importerNomenclatureEnUtf8(nomenclature) 
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
			System.out.println("********** CLASSE ImporteurNomenclatureTest - méthode testImporterMauvaiseNomenclatureEnUtf8() ********** ");
		}
		
		final Path mauvaiseNomenclaturePath 
		= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
				"HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ProfilTravers_HistoF08_Utf8.csv");
	
		final File mauvaiseNomenclature 
			= mauvaiseNomenclaturePath.toFile();
		
		try {
			
			// APPEL DE LA METHODE A TESTER **************
			// IMPORT ******************
			IMPORTEUR_NOMENCLATURE
				.importerNomenclatureEnUtf8(mauvaiseNomenclature);
			
			final File nomenclatureImportee 
				= IMPORTEUR_NOMENCLATURE.getNomenclature();
			
			final Set<Integer> clesPossibles 
				= IMPORTEUR_NOMENCLATURE.getClesPossiblesSet();
				
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
				System.out.println();
				System.out.println(AFFICHAGE_NOMENCLATUREMAP);
				System.out.println(IMPORTEUR_NOMENCLATURE.afficherNomenclatureMap());
				System.out.println();
				System.out.println(NOMENCLATURE_GENEREE);
				System.out.println(IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvString());
				System.out.println();
				System.out.println(CLES_POSSIBLES + clesPossibles.toString());
			}
			
		} catch (Exception e) {
			
			/* garantit que importerNomenclatureEnUtf8(mauvaisenomenclature) 
			 * jette une NomenclatureMauvaiseRunTimeException. */
			assertTrue(
					"importerNomenclatureEnUtf8(mauvaisenomenclature) doit jeter une NomenclatureMauvaiseRunTimeException :"
					, e instanceof NomenclatureMauvaiseRunTimeException); 
		}

	} // Fin de testImporterMauvaiseNomenclatureEnUtf8().__________________


	
	/**
	 * Teste la méthode ImporterNomenclatureEnUtf8(mauvaisenomenclature) 
	 * avec une nomenclature [String - String] au lieu de [Integer - String].<br/>
	 * <ul>
	 * <li>garantit que importerNomenclatureEnUtf8(nomenclature) 
	 * retourne une Map non null.</li>
	 * <li>garantit que importerNomenclatureEnUtf8(nomenclature) 
	 * retourne une Map non vide.</li>
	 * <li>garantit que importerNomenclatureEnUtf8(nomenclature) 
	 * alimente this.nomenclature.</li>
	 * <li>garantit que importerNomenclatureEnUtf8(nomenclature) 
	 * alimente this.clesPossibles.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testImporterMauvaiseNomenclatureEnUtf82() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurNomenclatureTest - méthode testImporterMauvaiseNomenclatureEnUtf82() ********** ");
		}
		
		final Path mauvaiseNomenclaturePath 
		= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
				"HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ProfilTravers_HistoF07_Utf8.csv");
	
		final File mauvaiseNomenclature 
			= mauvaiseNomenclaturePath.toFile();
		
		try {
			
			// APPEL DE LA METHODE A TESTER **************
			// IMPORT ******************
			IMPORTEUR_NOMENCLATURE
				.importerNomenclatureEnUtf8(mauvaiseNomenclature);
			
			final File nomenclatureImportee 
				= IMPORTEUR_NOMENCLATURE.getNomenclature();
			
			final Set<Integer> clesPossibles 
				= IMPORTEUR_NOMENCLATURE.getClesPossiblesSet();
				
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
				System.out.println();
				System.out.println(AFFICHAGE_NOMENCLATUREMAP);
				System.out.println(IMPORTEUR_NOMENCLATURE.afficherNomenclatureMap());
				System.out.println();
				System.out.println(NOMENCLATURE_GENEREE);
				System.out.println(IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvString());
				System.out.println();
				System.out.println(CLES_POSSIBLES + clesPossibles.toString());
			}
			
		} catch (Exception e) {
			
			/* garantit que importerNomenclatureEnUtf8(mauvaisenomenclature) 
			 * jette une NomenclatureMauvaiseRunTimeException. */
			assertTrue(
					"importerNomenclatureEnUtf8(mauvaisenomenclature) doit jeter une NomenclatureMauvaiseRunTimeException :"
					, e instanceof NomenclatureMauvaiseRunTimeException); 
		}

	} // Fin de testImporterMauvaiseNomenclatureEnUtf82().__________________

	
	
	/**
	 * Teste la méthode ImporterNomenclatureEnUtf8(nomenclature).<br/>
	 * <ul>
	 * <li>garantit que importerNomenclatureEnUtf8(nomenclature) 
	 * retourne une Map non null.</li>
	 * <li>garantit que importerNomenclatureEnUtf8(nomenclature) 
	 * retourne une Map non vide.</li>
	 * <li>garantit que importerNomenclatureEnUtf8(nomenclature) 
	 * alimente this.nomenclature.</li>
	 * <li>garantit que importerNomenclatureEnUtf8(nomenclature) 
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
	public void testImporterNomenclatureEnUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurNomenclatureTest - méthode testImporterNomenclatureEnUtf8() ********** ");
		}
					
		final Path nomenclaturePath 
			= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
					"Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeReseau_Hit_Utf8.csv");
		
		final File nomenclature 
			= nomenclaturePath.toFile();
		
		// APPEL DE LA METHODE A TESTER **************
		// IMPORT ******************
		final Map<Integer, String> resultat 
		= IMPORTEUR_NOMENCLATURE
			.importerNomenclatureEnUtf8(nomenclature);
		
		final File nomenclatureImportee 
			= IMPORTEUR_NOMENCLATURE.getNomenclature();
		
		final Set<Integer> clesPossibles 
			= IMPORTEUR_NOMENCLATURE.getClesPossiblesSet();
			
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println(AFFICHAGE_NOMENCLATUREMAP);
			System.out.println(IMPORTEUR_NOMENCLATURE.afficherNomenclatureMap());
			System.out.println();
			System.out.println(NOMENCLATURE_GENEREE);
			System.out.println(IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvString());
			System.out.println();
			System.out.println(CLES_POSSIBLES + clesPossibles.toString());
		}
		
		/* garantit que importerNomenclatureEnUtf8(nomenclature) 
		 * retourne une Map non null. */
		assertNotNull(
				"importerNomenclatureEnUtf8(nomenclature) doit retourner une map non null : "
				, resultat);
		
		/* garantit que importerNomenclatureEnUtf8(nomenclature) 
		 * retourne une Map non vide. */
		assertFalse(
				"importerNomenclatureEnUtf8(nomenclature) doit retourner une map non vide : "
				, resultat.isEmpty());
		
		/* garantit que importerNomenclatureEnUtf8(nomenclature) 
		 * alimente this.nomenclature. */
		assertNotNull(
				"importerNomenclatureEnUtf8(nomenclature) doit creer nomenclature non null : "
				, nomenclatureImportee);
		assertTrue(
				"importerNomenclatureEnUtf8(nomenclature) doit creer nomenclature existant : "
				, nomenclatureImportee.exists());
		
		/* garantit que importerNomenclatureEnUtf8(nomenclature) 
		 * alimente this.clesPossibles. */
		assertNotNull(
				"importerNomenclatureEnUtf8(nomenclature) doit creer clesPossibles non null : "
				, clesPossibles);
		assertTrue(
				"importerNomenclatureEnUtf8(nomenclature) doit creer clesPossibles avec 5 éléments : "
				, clesPossibles.size() == 5);
		
	} // Fin de testImporterNomenclatureEnUtf8().________________


	
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
			System.out.println("********** CLASSE ImporteurNomenclatureTest - méthode testFournirEnteteParColonne() ********** ");
		}
		
		final Path nomenclaturePath 
		= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
				"HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv");
	
		final File nomenclature 
			= nomenclaturePath.toFile();
		
		// IMPORT ******************
		IMPORTEUR_NOMENCLATURE
			.importerNomenclatureEnUtf8(nomenclature);
		
		
		final File nomenclatureImportee 
			= IMPORTEUR_NOMENCLATURE.getNomenclature();
		
		final Set<Integer> clesPossibles 
			= IMPORTEUR_NOMENCLATURE.getClesPossiblesSet();
		
		// APPEL DE LA METHODE A TESTER **************
		final String entete0 = IMPORTEUR_NOMENCLATURE.fournirEnteteParColonne(0);
		final String entete1 = IMPORTEUR_NOMENCLATURE.fournirEnteteParColonne(1);
		final String entete2 = IMPORTEUR_NOMENCLATURE.fournirEnteteParColonne(2);
		final String entete3 = IMPORTEUR_NOMENCLATURE.fournirEnteteParColonne(3);
			
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println(AFFICHAGE_NOMENCLATUREMAP);
			System.out.println(IMPORTEUR_NOMENCLATURE.afficherNomenclatureMap());
			System.out.println();
			System.out.println(NOMENCLATURE_GENEREE);
			System.out.println(IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvString());
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
			System.out.println("********** CLASSE ImporteurNomenclatureTest - méthode testFournirValeurParLigneColonne() ********** ");
		}
		
		final Path nomenclaturePath 
		= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
				"HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv");
	
		final File nomenclature 
			= nomenclaturePath.toFile();
		
		// IMPORT ******************
		IMPORTEUR_NOMENCLATURE
			.importerNomenclatureEnUtf8(nomenclature);
		
		
		final File nomenclatureImportee 
			= IMPORTEUR_NOMENCLATURE.getNomenclature();
		
		final Set<Integer> clesPossibles 
			= IMPORTEUR_NOMENCLATURE.getClesPossiblesSet();
		
		// APPEL DE LA METHODE A TESTER **************
		final String valeurL0C0 
			= IMPORTEUR_NOMENCLATURE.fournirValeurParLigneColonne(0, 0);
		final String valeurL1C1 
			= IMPORTEUR_NOMENCLATURE.fournirValeurParLigneColonne(1, 1);
		final String valeurL3C2 
			= IMPORTEUR_NOMENCLATURE.fournirValeurParLigneColonne(3, 2);
		final String valeurL6C2 
			= IMPORTEUR_NOMENCLATURE.fournirValeurParLigneColonne(6, 2);
		final String valeurL7C4 
			= IMPORTEUR_NOMENCLATURE.fournirValeurParLigneColonne(7, 4);
			
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println(AFFICHAGE_NOMENCLATUREMAP);
			System.out.println(IMPORTEUR_NOMENCLATURE.afficherNomenclatureMap());
			System.out.println();
			System.out.println(NOMENCLATURE_GENEREE);
			System.out.println(IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvString());
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
			System.out.println("********** CLASSE ImporteurNomenclatureTest - méthode testFournirLigneEnTetesCsv() ********** ");
		}

		// APPEL DE LA METHODE A TESTER **************
		final String enTeteCsv 
			= IMPORTEUR_NOMENCLATURE.fournirLigneEnTetesCsv();
		
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
			System.out.println("********** CLASSE ImporteurNomenclatureTest - méthode testFournirLigneValeursCsv() ********** ");
		}
			
		final Path nomenclaturePath 
		= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
				"HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv");
	
		final File nomenclature 
			= nomenclaturePath.toFile();
		
		// IMPORT ******************
		IMPORTEUR_NOMENCLATURE
			.importerNomenclatureEnUtf8(nomenclature);
		
		
		final File nomenclatureImportee 
			= IMPORTEUR_NOMENCLATURE.getNomenclature();
		
		final Set<Integer> clesPossibles 
			= IMPORTEUR_NOMENCLATURE.getClesPossiblesSet();
		
		// APPEL DE LA METHODE A TESTER **************
		final String valeurL0 
			= IMPORTEUR_NOMENCLATURE.fournirLigneValeursCsv(0);
		final String valeurL1
			= IMPORTEUR_NOMENCLATURE.fournirLigneValeursCsv(1);
		final String valeurL6 
			= IMPORTEUR_NOMENCLATURE.fournirLigneValeursCsv(6);
		final String valeurL7 
			= IMPORTEUR_NOMENCLATURE.fournirLigneValeursCsv(7);
			
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println(AFFICHAGE_NOMENCLATUREMAP);
			System.out.println(IMPORTEUR_NOMENCLATURE.afficherNomenclatureMap());
			System.out.println();
			System.out.println(NOMENCLATURE_GENEREE);
			System.out.println(IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvString());
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
			System.out.println("********** CLASSE ImporteurNomenclatureTest - méthode testGenererNomenclatureCsvString() ********** ");
		}
				
		final Path nomenclaturePath 
		= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
				"HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv");
	
		final File nomenclature 
			= nomenclaturePath.toFile();
		
		// IMPORT ******************
		IMPORTEUR_NOMENCLATURE
			.importerNomenclatureEnUtf8(nomenclature);
		
		
		final File nomenclatureImportee 
			= IMPORTEUR_NOMENCLATURE.getNomenclature();
		
		final Set<Integer> clesPossibles 
			= IMPORTEUR_NOMENCLATURE.getClesPossiblesSet();
		
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println(AFFICHAGE_NOMENCLATUREMAP);
			System.out.println(IMPORTEUR_NOMENCLATURE.afficherNomenclatureMap());
			System.out.println();
			System.out.println(NOMENCLATURE_GENEREE);
			System.out.println(IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvString());
			System.out.println();
			System.out.println(CLES_POSSIBLES + clesPossibles.toString());
		}
		
		// APPEL DE LA METHODE A TESTER **************
		final String nomenclatureGenereeSansEntete 
			= IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvString(false);
		
		final String nomenclatureGenereeAvecEntete 
			= IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvString(true);
		
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
					.contains(IMPORTEUR_NOMENCLATURE.fournirLigneEnTetesCsv()));
		
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
			System.out.println("********** CLASSE ImporteurNomenclatureTest - méthode testGenererNomenclatureCsvFileUtf8() ********** ");
		}
				
		final Path nomenclaturePath 
		= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
				"DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv");
		
		final File nomenclature 
			= nomenclaturePath.toFile();
		
		// IMPORT ******************
		IMPORTEUR_NOMENCLATURE
			.importerNomenclatureEnUtf8(nomenclature);
				
		final File nomenclatureImportee 
			= IMPORTEUR_NOMENCLATURE.getNomenclature();
		
		final Set<Integer> clesPossibles 
			= IMPORTEUR_NOMENCLATURE.getClesPossiblesSet();
		
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println(AFFICHAGE_NOMENCLATUREMAP);
			System.out.println(IMPORTEUR_NOMENCLATURE.afficherNomenclatureMap());
			System.out.println();
			System.out.println(NOMENCLATURE_GENEREE);
			System.out.println(IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvString());
			System.out.println();
			System.out.println(CLES_POSSIBLES + clesPossibles.toString());
		}
		
		// APPEL DE LA METHODE A TESTER **************
		final File nomenclatureGeneree 
			= IMPORTEUR_NOMENCLATURE
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
			System.out.println("********** CLASSE ImporteurNomenclatureTest - méthode testGenererNomenclatureCsvFileLatin9() ********** ");
		}
				
		final Path nomenclaturePath 
		= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
				"DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv");
		
		final File nomenclature 
			= nomenclaturePath.toFile();
		
		// IMPORT ******************
		IMPORTEUR_NOMENCLATURE
			.importerNomenclatureEnUtf8(nomenclature);
				
		final File nomenclatureImportee 
			= IMPORTEUR_NOMENCLATURE.getNomenclature();
		
		final Set<Integer> clesPossibles 
			= IMPORTEUR_NOMENCLATURE.getClesPossiblesSet();
		
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println(AFFICHAGE_NOMENCLATUREMAP);
			System.out.println(IMPORTEUR_NOMENCLATURE.afficherNomenclatureMap());
			System.out.println();
			System.out.println(NOMENCLATURE_GENEREE);
			System.out.println(IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvString());
			System.out.println();
			System.out.println(CLES_POSSIBLES + clesPossibles.toString());
		}
		
		// APPEL DE LA METHODE A TESTER **************
		final File nomenclatureGeneree 
			= IMPORTEUR_NOMENCLATURE
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
			System.out.println("********** CLASSE ImporteurNomenclatureTest - méthode testGenererNomenclatureCsvFile() ********** ");
		}
				
		final Path nomenclaturePath 
		= PATH_ABSOLU_TEST_NOMENCLATURES.resolve(
				"HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv");
	
		final File nomenclature 
			= nomenclaturePath.toFile();
		
		// IMPORT ******************
		IMPORTEUR_NOMENCLATURE
			.importerNomenclatureEnUtf8(nomenclature);
		
		
		final File nomenclatureImportee 
			= IMPORTEUR_NOMENCLATURE.getNomenclature();
		
		final Set<Integer> clesPossibles 
			= IMPORTEUR_NOMENCLATURE.getClesPossiblesSet();
		
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_IMPORTEE + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println(AFFICHAGE_NOMENCLATUREMAP);
			System.out.println(IMPORTEUR_NOMENCLATURE.afficherNomenclatureMap());
			System.out.println();
			System.out.println(NOMENCLATURE_GENEREE);
			System.out.println(IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvString());
			System.out.println();
			System.out.println(CLES_POSSIBLES + clesPossibles.toString());
		}
		
		final Path nomenclatureGenereePath 
			= PATH_ABSOLU_REPERTOIRE_TEMP.resolve("nomenclatureGeneree.csv");
		
		final File nomenclatureGeneree = nomenclatureGenereePath.toFile();
		
		final Charset charsetUtf8 = Charset.forName("UTF-8");
		
		// APPEL DE LA METHODE A TESTER **************
		IMPORTEUR_NOMENCLATURE
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
	
	
		
} // FIN DE LA CLASSE ImporteurNomenclatureTest.-----------------------------
