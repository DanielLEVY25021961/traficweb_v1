package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
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
			
			Map<Integer, String> resultat 
			= IMPORTEUR_NOMENCLATURE.importerNomenclatureEnUtf8(null);
			
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
			
			Map<Integer, String> resultat 
			= IMPORTEUR_NOMENCLATURE.importerNomenclatureEnUtf8(nomenclatureVide);
			
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
			
			Map<Integer, String> resultat 
			= IMPORTEUR_NOMENCLATURE.importerNomenclatureEnUtf8(nomenclatureInexistant);
			
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
			
			Map<Integer, String> resultat 
			= IMPORTEUR_NOMENCLATURE
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
			
			// IMPORT ******************
			final Map<Integer, String> resultat 
			= IMPORTEUR_NOMENCLATURE
				.importerNomenclatureEnUtf8(mauvaiseNomenclature);
			
			final File nomenclatureImportee 
				= IMPORTEUR_NOMENCLATURE.getNomenclature();
			
			final Set<Integer> clesPossibles 
				= IMPORTEUR_NOMENCLATURE.getClesPossiblesSet();
				
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("Nomenclature importée : " + nomenclatureImportee.getAbsolutePath());
				System.out.println();
				System.out.println("AFFICHAGE DE this.nomenclatureMap : ");
				System.out.println(IMPORTEUR_NOMENCLATURE.afficherNomenclatureMap());
				System.out.println();
				System.out.println("NOMENCLATURE GENEREE : ");
				System.out.println(IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvString());
				System.out.println();
				System.out.println("Clés possibles : " + clesPossibles.toString());
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
			
			// IMPORT ******************
			final Map<Integer, String> resultat 
			= IMPORTEUR_NOMENCLATURE
				.importerNomenclatureEnUtf8(mauvaiseNomenclature);
			
			final File nomenclatureImportee 
				= IMPORTEUR_NOMENCLATURE.getNomenclature();
			
			final Set<Integer> clesPossibles 
				= IMPORTEUR_NOMENCLATURE.getClesPossiblesSet();
				
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("Nomenclature importée : " + nomenclatureImportee.getAbsolutePath());
				System.out.println();
				System.out.println("AFFICHAGE DE this.nomenclatureMap : ");
				System.out.println(IMPORTEUR_NOMENCLATURE.afficherNomenclatureMap());
				System.out.println();
				System.out.println("NOMENCLATURE GENEREE : ");
				System.out.println(IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvString());
				System.out.println();
				System.out.println("Clés possibles : " + clesPossibles.toString());
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
			System.out.println("Nomenclature importée : " + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println("AFFICHAGE DE this.nomenclatureMap : ");
			System.out.println(IMPORTEUR_NOMENCLATURE.afficherNomenclatureMap());
			System.out.println();
			System.out.println("NOMENCLATURE GENEREE : ");
			System.out.println(IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvString());
			System.out.println();
			System.out.println("Clés possibles : " + clesPossibles.toString());
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
		
		final String entete0 = IMPORTEUR_NOMENCLATURE.fournirEnteteParColonne(0);
		final String entete1 = IMPORTEUR_NOMENCLATURE.fournirEnteteParColonne(1);
		final String entete2 = IMPORTEUR_NOMENCLATURE.fournirEnteteParColonne(2);
		
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
		final boolean affichage = true;
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
		final Map<Integer, String> resultat 
		= IMPORTEUR_NOMENCLATURE
			.importerNomenclatureEnUtf8(nomenclature);
		
		
		final File nomenclatureImportee 
			= IMPORTEUR_NOMENCLATURE.getNomenclature();
		
		final Set<Integer> clesPossibles 
			= IMPORTEUR_NOMENCLATURE.getClesPossiblesSet();
		
		final String entete0 = IMPORTEUR_NOMENCLATURE.fournirEnteteParColonne(0);
		final String entete1 = IMPORTEUR_NOMENCLATURE.fournirEnteteParColonne(1);
		final String entete2 = IMPORTEUR_NOMENCLATURE.fournirEnteteParColonne(2);
		final String entete3 = IMPORTEUR_NOMENCLATURE.fournirEnteteParColonne(3);
			
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("Nomenclature importée : " + nomenclatureImportee.getAbsolutePath());
			System.out.println();
			System.out.println("AFFICHAGE DE this.nomenclatureMap : ");
			System.out.println(IMPORTEUR_NOMENCLATURE.afficherNomenclatureMap());
			System.out.println();
			System.out.println("NOMENCLATURE GENEREE : ");
			System.out.println(IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvString());
			System.out.println();
			System.out.println("Clés possibles : " + clesPossibles.toString());
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
	
	
		
} // FIN DE LA CLASSE ImporteurNomenclatureTest.-----------------------------
