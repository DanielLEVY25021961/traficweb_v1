package levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.IImporteur;
import levy.daniel.application.model.utilitaires.comparateursfichiers.ComparateurFichiers;

/**
 * CLASSE ImporteurHitTest :<br/>
 * Test JUnit de la classe {@link ImporteurHit}.<br/>
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
 * @since 6 juin 2019
 *
 */
public class ImporteurHitTest {

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
	 * IImporteur.<br/>
	 */
	public static IImporteur importeur;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(ImporteurHitTest.class);

	// *************************METHODES************************************/
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ImporteurHitTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
		
	/**
	 * Teste la méthode importer(File, Charset).<br/>
	 * <ul>
	 * <li>garantit que importer(...) retourne le fichier sous forme de Map.</li>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 * 
	 *  @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testImporter() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImporteurHitTest - méthode testImporter() ********** ");
		}
		
		final Path fichierDonneesPath = PATH_ABSOLU_TEST_JEUX_ESSAI.resolve("HITDIRA2017.txt");
		final File fichierDonnees = fichierDonneesPath.toFile();
		final Charset charsetAnsi = Charset.forName("Windows-1252");
		final Charset charsetUtf8 = StandardCharsets.UTF_8;
		
		// METHODE A TESTER.
		final SortedMap<Integer, SortedMap<Integer, String>> fichierMap 
			= importeur.importer(fichierDonnees, charsetAnsi);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(importeur.afficherFichierImporteMap());
		}
		
		/* garantit que importer(...) retourne le fichier sous forme de Map. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, fichierMap);
		
		final SortedMap<Integer, String> ligne1 = fichierMap.get(1);
		final SortedMap<Integer, String> ligne2 = fichierMap.get(2);
		final SortedMap<Integer, String> ligne3 = fichierMap.get(3);
		
		final File fileReconstitueAnsi = importeur.reconstituerFichierAnsi();
		final File fileReconstitueUtf8 = importeur.reconstituerFichierUtf8();
		
		final boolean memeFichiersAnsi 
			= ComparateurFichiers.compareFichiersLigneALigne(
					fichierDonnees, charsetAnsi
					, fileReconstitueAnsi, charsetAnsi);
		
		final String rapportComparaison 
			= ComparateurFichiers.getRapportComparaison();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println();
			System.out.println("fichierDonnees : " + fichierDonnees.getAbsolutePath());
			System.out.println("fileReconstitueAnsi : " + fileReconstitueAnsi.getAbsolutePath());
			
			System.out.println("memeFichiersAnsi : " + memeFichiersAnsi);
			
			if (rapportComparaison != null) {
				System.out.println(rapportComparaison);
			}
			
		}
		
		final boolean memeFichiersAnsiUtf8 
			= ComparateurFichiers.compareFichiersLigneALigne(
					fichierDonnees, charsetAnsi
					, fileReconstitueUtf8, charsetUtf8);
		
		final String rapportComparaisonAnsiUtf8 
			= ComparateurFichiers.getRapportComparaison();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println();
			System.out.println("fichierDonnees : " + fichierDonnees.getAbsolutePath());
			System.out.println("fileReconstitueUtf8 : " + fileReconstitueUtf8.getAbsolutePath());
			
			System.out.println("memeFichiersAnsiUtf8 : " + memeFichiersAnsiUtf8);
			
			if (rapportComparaisonAnsiUtf8 != null) {
				System.out.println(rapportComparaisonAnsiUtf8);
			}
			
		}
		
		
		
	} // Fin de testImporter().____________________________________________


	
	/**
	 * Exécuté avant tout test de la méthode.<br/>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		
		importeur = new ImporteurHit();
		
	} // Fin de beforeClass()._____________________________________________
	
	
	
} // FIN DE LA CLASSE ImporteurHitTest.--------------------------------------