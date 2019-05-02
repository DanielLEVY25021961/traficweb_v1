package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesnomenclatures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * CLASSE ConfigurationNomenclaturesHistoF08ManagerTest :<br/>
 * Test JUnit de la classe {@link ConfigurationNomenclaturesHistoF08Manager}.<br/>
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
 * @since 1 mai 2019
 *
 */
public class ConfigurationNomenclaturesHistoF08ManagerTest {

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
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(ConfigurationNomenclaturesHistoF08ManagerTest.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ConfigurationNomenclaturesHistoF08ManagerTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
		
	/**
	 * teste la méthode getCheminNomenclaturesHistoF08Utf8().<br/>
	 * <ul>
	 * <li>garantit que getCheminNomenclaturesHistoF08Utf8() ne retourne pas null.</li>
	 * <li>garantit que getCheminNomenclaturesHistoF08Utf8() retourne "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.chemin.histof08.utf8 = ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetCheminNomenclaturesHistoF08Utf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetCheminNomenclaturesHistoF08Utf8() ********** ");
		}

		final String cheminNomenclatures 
			= ConfigurationNomenclaturesHistoF08Manager.getCheminNomenclaturesHistoF08Utf8();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatures : " 
					+ cheminNomenclatures);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getCheminNomenclaturesHistoF08Utf8() ne retourne pas null. */
		assertNotNull(
				"Le chemin des descriptions (String) ne doit pas être null : "
					, cheminNomenclatures);
		
		/* garantit que getCheminNomenclaturesHistoF08Utf8() retourne "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8". */
		assertEquals("le chemin des nomenclatures doit valoir 'ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8' : "
				, "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8"
					, cheminNomenclatures);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetCheminNomenclaturesHistoF08Utf8()._______________________________

	
	
	/**
	 * teste la méthode getNomNomenclatureHistoF08Sens().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHistoF08Sens() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHistoF08Sens() retourne "2014-07-15_Nomenclature_Sens_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.sens.histof08 = 2014-07-15_Nomenclature_Sens_HistoF08_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHistoF08Sens() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetNomNomenclatureHistoF08Sens() ********** ");
		}

		final String cheminNomenclatureHistoF08Sens 
			= ConfigurationNomenclaturesHistoF08Manager.getNomNomenclatureHistoF08Sens();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHistoF08Sens : " 
					+ cheminNomenclatureHistoF08Sens);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHistoF08Sens() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null"
					, cheminNomenclatureHistoF08Sens);
		
		/* garantit que getNomNomenclatureHistoF08Sens() retourne "2014-07-15_Nomenclature_Sens_HistoF08_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_Sens_HistoF08_Utf8.csv' : "
				, "2014-07-15_Nomenclature_Sens_HistoF08_Utf8.csv"
					, cheminNomenclatureHistoF08Sens);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHistoF08Sens().______________________


	
	/**
	 * teste la méthode getFichierNomenclatureHistoF08SensUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHistoF08SensUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08SensUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08SensUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08SensUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Sens_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHistoF08SensUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetFichierNomenclatureHistoF08SensUtf8() ********** ");
		}

		final File fichierNomenclatureHistoF08Sens 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08SensUtf8();

		final File fichierNomenclatureHistoF08Sens1 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08SensUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHistoF08Sens : " 
					+ fichierNomenclatureHistoF08Sens);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHistoF08SensUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HISTO_F08 SENS ne doit pas être null : "
					, fichierNomenclatureHistoF08Sens);
		
		/* garantit que getFichierNomenclatureHistoF08SensUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHistoF08Sens doit exister : "
				, fichierNomenclatureHistoF08Sens.exists());
		
		/* garantit que getFichierNomenclatureHistoF08SensUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHistoF08Sens, fichierNomenclatureHistoF08Sens1);
		
		/* garantit que getFichierNomenclatureHistoF08SensUtf8() retourne "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Sens_HistoF08_Utf8.csv". */
		assertEquals("le fichier de nomenclature HISTO_F08 SENS doit être situé sous le contexte sous 'ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Sens_HistoF08_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Sens_HistoF08_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHistoF08Sens));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHistoF08SensUtf8().______________

	
	
	/**
	 * teste la méthode getNomNomenclatureHistoF08Nature().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHistoF08Nature() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHistoF08Nature() retourne "2014-07-15_Nomenclature_Nature_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.nature.histof08 = 2014-07-15_Nomenclature_Nature_HistoF08_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHistoF08Nature() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetNomNomenclatureHistoF08Nature() ********** ");
		}

		final String cheminNomenclatureHistoF08Nature 
			= ConfigurationNomenclaturesHistoF08Manager.getNomNomenclatureHistoF08Nature();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHistoF08Nature : " 
					+ cheminNomenclatureHistoF08Nature);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHistoF08Nature() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHistoF08Nature);
		
		/* garantit que getNomNomenclatureHistoF08Nature() retourne "2014-07-15_Nomenclature_Nature_HistoF08_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_Nature_HistoF08_Utf8.csv' : "
				, "2014-07-15_Nomenclature_Nature_HistoF08_Utf8.csv"
					, cheminNomenclatureHistoF08Nature);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHistoF08Nature()._________________________


	
	/**
	 * teste la méthode getFichierNomenclatureHistoF08NatureUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHistoF08NatureUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08NatureUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08NatureUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08NatureUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Nature_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHistoF08NatureUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetFichierNomenclatureHistoF08NatureUtf8() ********** ");
		}

		final File fichierNomenclatureHistoF08Nature 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08NatureUtf8();

		final File fichierNomenclatureHistoF08Nature1 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08NatureUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHistoF08Nature : " 
					+ fichierNomenclatureHistoF08Nature);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHistoF08NatureUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HISTO_F08 NATURE ne doit pas être null : "
					, fichierNomenclatureHistoF08Nature);
		
		/* garantit que getFichierNomenclatureHistoF08NatureUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHistoF08Nature doit exister : "
				, fichierNomenclatureHistoF08Nature.exists());
		
		/* garantit que getFichierNomenclatureHistoF08NatureUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHistoF08Nature, fichierNomenclatureHistoF08Nature1);
		
		/* garantit que getFichierNomenclatureHistoF08NatureUtf8() retourne "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Nature_HistoF08_Utf8.csv". */
		assertEquals("le fichier de nomenclature HISTO_F08 NATURE doit être situé sous le contexte sous 'ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Nature_HistoF08_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Nature_HistoF08_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHistoF08Nature));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHistoF08NatureUtf8().___________________

	
	
	/**
	 * teste la méthode getNomNomenclatureHistoF08CatAdminRoute().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHistoF08CatAdminRoute() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHistoF08CatAdminRoute() retourne "2014-07-15_Nomenclature_CatAdminRoute_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.catadminroute.histof08 = 2014-07-15_Nomenclature_CatAdminRoute_HistoF08_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHistoF08CatAdminRoute() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetNomNomenclatureHistoF08CatAdminRoute() ********** ");
		}

		final String cheminNomenclatureHistoF08CatAdminRoute 
			= ConfigurationNomenclaturesHistoF08Manager.getNomNomenclatureHistoF08CatAdminRoute();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHistoF08CatAdminRoute : " 
					+ cheminNomenclatureHistoF08CatAdminRoute);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHistoF08CatAdminRoute() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHistoF08CatAdminRoute);
		
		/* garantit que getNomNomenclatureHistoF08CatAdminRoute() retourne "2014-07-15_Nomenclature_CatAdminRoute_HistoF08_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_CatAdminRoute_HistoF08_Utf8.csv' : "
				, "2014-07-15_Nomenclature_CatAdminRoute_HistoF08_Utf8.csv"
					, cheminNomenclatureHistoF08CatAdminRoute);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHistoF08CatAdminRoute()._____________


	
	/**
	 * teste la méthode getFichierNomenclatureHistoF08CatAdminRouteUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHistoF08CatAdminRouteUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08CatAdminRouteUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08CatAdminRouteUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08CatAdminRouteUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_CatAdminRoute_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHistoF08CatAdminRouteUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetFichierNomenclatureHistoF08CatAdminRouteUtf8() ********** ");
		}

		final File fichierNomenclatureHistoF08CatAdminRoute 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08CatAdminRouteUtf8();

		final File fichierNomenclatureHistoF08CatAdminRoute1 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08CatAdminRouteUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHistoF08CatAdminRoute : " 
					+ fichierNomenclatureHistoF08CatAdminRoute);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHistoF08CatAdminRouteUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HISTO_F08 CAT ADMIN ROUTE ne doit pas être null : "
					, fichierNomenclatureHistoF08CatAdminRoute);
		
		/* garantit que getFichierNomenclatureHistoF08CatAdminRouteUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHistoF08CatAdminRoute doit exister : "
				, fichierNomenclatureHistoF08CatAdminRoute.exists());
		
		/* garantit que getFichierNomenclatureHistoF08CatAdminRouteUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHistoF08CatAdminRoute, fichierNomenclatureHistoF08CatAdminRoute1);
		
		/* garantit que getFichierNomenclatureHistoF08CatAdminRouteUtf8() retourne "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_CatAdminRoute_HistoF08_Utf8.csv". */
		assertEquals("le fichier de nomenclature HISTO_F08 CAT ADMIN ROUTE doit être situé sous le contexte sous 'ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_CatAdminRoute_HistoF08_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_CatAdminRoute_HistoF08_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHistoF08CatAdminRoute));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHistoF08CatAdminRouteUtf8()._____

	
	
	/**
	 * teste la méthode getNomNomenclatureHistoF08TypeComptage().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHistoF08TypeComptage() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHistoF08TypeComptage() retourne "2014-07-15_Nomenclature_TypeComptage_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.typecomptage.histof08 = 2014-07-15_Nomenclature_TypeComptage_HistoF08_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHistoF08TypeComptage() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetNomNomenclatureHistoF08TypeComptage() ********** ");
		}

		final String cheminNomenclatureHistoF08TypeComptage 
			= ConfigurationNomenclaturesHistoF08Manager.getNomNomenclatureHistoF08TypeComptage();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHistoF08TypeComptage : " 
					+ cheminNomenclatureHistoF08TypeComptage);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHistoF08TypeComptage() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHistoF08TypeComptage);
		
		/* garantit que getNomNomenclatureHistoF08TypeComptage() retourne "2014-07-15_Nomenclature_TypeComptage_HistoF08_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_TypeComptage_HistoF08_Utf8.csv' : "
				, "2014-07-15_Nomenclature_TypeComptage_HistoF08_Utf8.csv"
					, cheminNomenclatureHistoF08TypeComptage);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHistoF08TypeComptage().___________________


	
	/**
	 * teste la méthode getFichierNomenclatureHistoF08TypeComptageUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHistoF08TypeComptageUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08TypeComptageUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08TypeComptageUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08TypeComptageUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHistoF08TypeComptageUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetFichierNomenclatureHistoF08TypeComptageUtf8() ********** ");
		}

		final File fichierNomenclatureHistoF08TypeComptage 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08TypeComptageUtf8();

		final File fichierNomenclatureHistoF08TypeComptage1 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08TypeComptageUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHistoF08TypeComptage : " 
					+ fichierNomenclatureHistoF08TypeComptage);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHistoF08TypeComptageUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HISTO_F08 TYPE DE COMPTAGE ne doit pas être null : "
					, fichierNomenclatureHistoF08TypeComptage);
		
		/* garantit que getFichierNomenclatureHistoF08TypeComptageUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHistoF08TypeComptage doit exister : "
				, fichierNomenclatureHistoF08TypeComptage.exists());
		
		/* garantit que getFichierNomenclatureHistoF08TypeComptageUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHistoF08TypeComptage, fichierNomenclatureHistoF08TypeComptage1);
		
		/* garantit que getFichierNomenclatureHistoF08TypeComptageUtf8() retourne "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_HistoF08_Utf8.csv". */
		assertEquals("le fichier de nomenclature HISTO_F08 TYPE DE COMPTAGE doit être situé sous le contexte sous 'ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_HistoF08_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_HistoF08_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHistoF08TypeComptage));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHistoF08TypeComptageUtf8().___________

	
	
	/**
	 * teste la méthode getNomNomenclatureHistoF08ClassementRoute().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHistoF08ClassementRoute() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHistoF08ClassementRoute() retourne "2014-07-15_Nomenclature_ClassementRoute_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.classementroute.histof08 = 2014-07-15_Nomenclature_ClassementRoute_HistoF08_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHistoF08ClassementRoute() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetNomNomenclatureHistoF08ClassementRoute() ********** ");
		}

		final String cheminNomenclatureHistoF08ClassementRoute 
			= ConfigurationNomenclaturesHistoF08Manager.getNomNomenclatureHistoF08ClassementRoute();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHistoF08ClassementRoute : " 
					+ cheminNomenclatureHistoF08ClassementRoute);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHistoF08ClassementRoute() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHistoF08ClassementRoute);
		
		/* garantit que getNomNomenclatureHistoF08ClassementRoute() retourne "2014-07-15_Nomenclature_ClassementRoute_HistoF08_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_ClassementRoute_HistoF08_Utf8.csv' : "
				, "2014-07-15_Nomenclature_ClassementRoute_HistoF08_Utf8.csv"
					, cheminNomenclatureHistoF08ClassementRoute);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHistoF08ClassementRoute().________________


	
	/**
	 * teste la méthode getFichierNomenclatureHistoF08ClassementRouteUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHistoF08ClassementRouteUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08ClassementRouteUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08ClassementRouteUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08ClassementRouteUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHistoF08ClassementRouteUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetFichierNomenclatureHistoF08ClassementRouteUtf8() ********** ");
		}

		final File fichierNomenclatureHistoF08ClassementRoute 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08ClassementRouteUtf8();

		final File fichierNomenclatureHistoF08ClassementRoute1 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08ClassementRouteUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHistoF08ClassementRoute : " 
					+ fichierNomenclatureHistoF08ClassementRoute);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHistoF08ClassementRouteUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HISTO_F08 CLASSEMENT DE LA ROUTE ne doit pas être null : "
					, fichierNomenclatureHistoF08ClassementRoute);
		
		/* garantit que getFichierNomenclatureHistoF08ClassementRouteUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHistoF08ClassementRoute doit exister : "
				, fichierNomenclatureHistoF08ClassementRoute.exists());
		
		/* garantit que getFichierNomenclatureHistoF08ClassementRouteUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHistoF08ClassementRoute, fichierNomenclatureHistoF08ClassementRoute1);
		
		/* garantit que getFichierNomenclatureHistoF08ClassementRouteUtf8() retourne "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_HistoF08_Utf8.csv". */
		assertEquals("le fichier de nomenclature HISTO_F08 CLASSEMENT DE LA ROUTE doit être situé sous le contexte sous 'ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_HistoF08_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_HistoF08_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHistoF08ClassementRoute));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHistoF08ClassementRouteUtf8().________

	
	
	/**
	 * teste la méthode getNomNomenclatureHistoF08ClasseLargeurChausseeU().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHistoF08ClasseLargeurChausseeU() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHistoF08ClasseLargeurChausseeU() retourne "2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.histof08 = 2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF08_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHistoF08ClasseLargeurChausseeU() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetNomNomenclatureHistoF08ClasseLargeurChausseeU() ********** ");
		}

		final String cheminNomenclatureHistoF08ClasseLargeurChausseeU 
			= ConfigurationNomenclaturesHistoF08Manager.getNomNomenclatureHistoF08ClasseLargeurChausseeU();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHistoF08ClasseLargeurChausseeU : " 
					+ cheminNomenclatureHistoF08ClasseLargeurChausseeU);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHistoF08ClasseLargeurChausseeU() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHistoF08ClasseLargeurChausseeU);
		
		/* garantit que getNomNomenclatureHistoF08ClasseLargeurChausseeU() retourne "2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF08_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF08_Utf8.csv' : "
				, "2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF08_Utf8.csv"
					, cheminNomenclatureHistoF08ClasseLargeurChausseeU);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHistoF08ClasseLargeurChausseeU()._________


	
	/**
	 * teste la méthode getFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8() ********** ");
		}

		final File fichierNomenclatureHistoF08ClasseLargeurChausseeU 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8();

		final File fichierNomenclatureHistoF08ClasseLargeurChausseeU1 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHistoF08ClasseLargeurChausseeU : " 
					+ fichierNomenclatureHistoF08ClasseLargeurChausseeU);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HISTO_F08 CLASSE DE LARGEUR DE CHAUSSEE UNIQUE ne doit pas être null : "
					, fichierNomenclatureHistoF08ClasseLargeurChausseeU);
		
		/* garantit que getFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHistoF08ClasseLargeurChausseeU doit exister : "
				, fichierNomenclatureHistoF08ClasseLargeurChausseeU.exists());
		
		/* garantit que getFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHistoF08ClasseLargeurChausseeU, fichierNomenclatureHistoF08ClasseLargeurChausseeU1);
		
		/* garantit que getFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8() retourne "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF08_Utf8.csv". */
		assertEquals("le fichier de nomenclature HISTO_F08 CLASSE DE LARGEUR DE CHAUSSEE UNIQUE doit être situé sous le contexte sous 'ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF08_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF08_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHistoF08ClasseLargeurChausseeU));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8()._

	
	
	/**
	 * teste la méthode getNomNomenclatureHistoF08ClasseLargeurChausseesS().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHistoF08ClasseLargeurChausseesS() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHistoF08ClasseLargeurChausseesS() retourne "2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.classelargeurchausseess.histof08 = 2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF08_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHistoF08ClasseLargeurChausseesS() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetNomNomenclatureHistoF08ClasseLargeurChausseesS() ********** ");
		}

		final String cheminNomenclatureHistoF08ClasseLargeurChausseesS 
			= ConfigurationNomenclaturesHistoF08Manager.getNomNomenclatureHistoF08ClasseLargeurChausseesS();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHistoF08ClasseLargeurChausseesS : " 
					+ cheminNomenclatureHistoF08ClasseLargeurChausseesS);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHistoF08ClasseLargeurChausseesS() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHistoF08ClasseLargeurChausseesS);
		
		/* garantit que getNomNomenclatureHistoF08ClasseLargeurChausseesS() retourne "2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF08_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF08_Utf8.csv' : "
				, "2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF08_Utf8.csv"
					, cheminNomenclatureHistoF08ClasseLargeurChausseesS);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHistoF08ClasseLargeurChausseesS().________


	
	/**
	 * teste la méthode getFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8() ********** ");
		}

		final File fichierNomenclatureHistoF08ClasseLargeurChausseesS 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8();

		final File fichierNomenclatureHistoF08ClasseLargeurChausseesS1 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHistoF08ClasseLargeurChausseesS : " 
					+ fichierNomenclatureHistoF08ClasseLargeurChausseesS);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HISTO_F08 CLASSE DE LARGEUR DE CHAUSSEES SEPAREES ne doit pas être null : "
					, fichierNomenclatureHistoF08ClasseLargeurChausseesS);
		
		/* garantit que getFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHistoF08ClasseLargeurChausseesS doit exister : "
				, fichierNomenclatureHistoF08ClasseLargeurChausseesS.exists());
		
		/* garantit que getFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHistoF08ClasseLargeurChausseesS, fichierNomenclatureHistoF08ClasseLargeurChausseesS1);
		
		/* garantit que getFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8() retourne "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF08_Utf8.csv". */
		assertEquals("le fichier de nomenclature HISTO_F08 CLASSE DE LARGEUR DE CHAUSSEES SEPAREES doit être situé sous le contexte sous 'ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF08_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF08_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHistoF08ClasseLargeurChausseesS));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8()._

	
	
	/**
	 * teste la méthode getNomNomenclatureHistoF08TypeReseau().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHistoF08TypeReseau() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHistoF08TypeReseau() retourne "2014-07-15_Nomenclature_TypeReseau_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.typereseau.histof08 = 2014-07-15_Nomenclature_TypeReseau_HistoF08_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHistoF08TypeReseau() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetNomNomenclatureHistoF08TypeReseau() ********** ");
		}

		final String cheminNomenclatureHistoF08TypeReseau 
			= ConfigurationNomenclaturesHistoF08Manager.getNomNomenclatureHistoF08TypeReseau();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHistoF08TypeReseau : " 
					+ cheminNomenclatureHistoF08TypeReseau);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHistoF08TypeReseau() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHistoF08TypeReseau);
		
		/* garantit que getNomNomenclatureHistoF08TypeReseau() retourne "2014-07-15_Nomenclature_TypeReseau_HistoF08_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_TypeReseau_HistoF08_Utf8.csv' : "
				, "2014-07-15_Nomenclature_TypeReseau_HistoF08_Utf8.csv"
					, cheminNomenclatureHistoF08TypeReseau);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHistoF08TypeReseau()._____________________


	
	/**
	 * teste la méthode getFichierNomenclatureHistoF08TypeReseauUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHistoF08TypeReseauUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08TypeReseauUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08TypeReseauUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08TypeReseauUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeReseau_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHistoF08TypeReseauUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetFichierNomenclatureHistoF08TypeReseauUtf8() ********** ");
		}

		final File fichierNomenclatureHistoF08TypeReseau 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08TypeReseauUtf8();

		final File fichierNomenclatureHistoF08TypeReseau1 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08TypeReseauUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHistoF08TypeReseau : " 
					+ fichierNomenclatureHistoF08TypeReseau);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHistoF08TypeReseauUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HISTO_F08 TYPE DE RESEAU ne doit pas être null : "
					, fichierNomenclatureHistoF08TypeReseau);
		
		/* garantit que getFichierNomenclatureHistoF08TypeReseauUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHistoF08TypeReseau doit exister : "
				, fichierNomenclatureHistoF08TypeReseau.exists());
		
		/* garantit que getFichierNomenclatureHistoF08TypeReseauUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHistoF08TypeReseau, fichierNomenclatureHistoF08TypeReseau1);
		
		/* garantit que getFichierNomenclatureHistoF08TypeReseauUtf8() retourne "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeReseau_HistoF08_Utf8.csv". */
		assertEquals("le fichier de nomenclature HISTO_F08 TYPE DE RESEAU doit être situé sous le contexte sous 'ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeReseau_HistoF08_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeReseau_HistoF08_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHistoF08TypeReseau));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHistoF08TypeReseauUtf8()._____________

	
	
	/**
	 * teste la méthode getNomNomenclatureHistoF08CodeConcession().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHistoF08CodeConcession() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHistoF08CodeConcession() retourne "2014-07-15_Nomenclature_CodeConcession_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.codeconcession.histof08 = 2014-07-15_Nomenclature_CodeConcession_HistoF08_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHistoF08CodeConcession() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetNomNomenclatureHistoF08CodeConcession() ********** ");
		}

		final String cheminNomenclatureHistoF08CodeConcession 
			= ConfigurationNomenclaturesHistoF08Manager.getNomNomenclatureHistoF08CodeConcession();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHistoF08CodeConcession : " 
					+ cheminNomenclatureHistoF08CodeConcession);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHistoF08CodeConcession() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHistoF08CodeConcession);
		
		/* garantit que getNomNomenclatureHistoF08CodeConcession() retourne "2014-07-15_Nomenclature_CodeConcession_HistoF08_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_CodeConcession_HistoF08_Utf8.csv' : "
				, "2014-07-15_Nomenclature_CodeConcession_HistoF08_Utf8.csv"
					, cheminNomenclatureHistoF08CodeConcession);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHistoF08CodeConcession()._____________________


	
	/**
	 * teste la méthode getFichierNomenclatureHistoF08CodeConcessionUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHistoF08CodeConcessionUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08CodeConcessionUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08CodeConcessionUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08CodeConcessionUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_CodeConcession_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHistoF08CodeConcessionUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetFichierNomenclatureHistoF08CodeConcessionUtf8() ********** ");
		}

		final File fichierNomenclatureHistoF08CodeConcession 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08CodeConcessionUtf8();

		final File fichierNomenclatureHistoF08CodeConcession1 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08CodeConcessionUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHistoF08CodeConcession : " 
					+ fichierNomenclatureHistoF08CodeConcession);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHistoF08CodeConcessionUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HISTO_F08 CODE CONCESSION ne doit pas être null : "
					, fichierNomenclatureHistoF08CodeConcession);
		
		/* garantit que getFichierNomenclatureHistoF08CodeConcessionUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHistoF08CodeConcession doit exister : "
				, fichierNomenclatureHistoF08CodeConcession.exists());
		
		/* garantit que getFichierNomenclatureHistoF08CodeConcessionUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHistoF08CodeConcession, fichierNomenclatureHistoF08CodeConcession1);
		
		/* garantit que getFichierNomenclatureHistoF08CodeConcessionUtf8() retourne "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_CodeConcession_HistoF08_Utf8.csv". */
		assertEquals("le fichier de nomenclature HISTO_F08 CODE CONCESSION doit être situé sous le contexte sous 'ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_CodeConcession_HistoF08_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_CodeConcession_HistoF08_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHistoF08CodeConcession));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHistoF08CodeConcessionUtf8()._____________

	
	
	/**
	 * teste la méthode getNomNomenclatureHistoF08ProfilTravers().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHistoF08ProfilTravers() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHistoF08ProfilTravers() retourne "2014-07-15_Nomenclature_ProfilTravers_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.profiltravers.histof08 = 2014-07-15_Nomenclature_ProfilTravers_HistoF08_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHistoF08ProfilTravers() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetNomNomenclatureHistoF08ProfilTravers() ********** ");
		}

		final String cheminNomenclatureHistoF08ProfilTravers 
			= ConfigurationNomenclaturesHistoF08Manager.getNomNomenclatureHistoF08ProfilTravers();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHistoF08ProfilTravers : " 
					+ cheminNomenclatureHistoF08ProfilTravers);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHistoF08ProfilTravers() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHistoF08ProfilTravers);
		
		/* garantit que getNomNomenclatureHistoF08ProfilTravers() retourne "2014-07-15_Nomenclature_ProfilTravers_HistoF08_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_ProfilTravers_HistoF08_Utf8.csv' : "
				, "2014-07-15_Nomenclature_ProfilTravers_HistoF08_Utf8.csv"
					, cheminNomenclatureHistoF08ProfilTravers);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHistoF08ProfilTravers()._____________________


	
	/**
	 * teste la méthode getFichierNomenclatureHistoF08ProfilTraversUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHistoF08ProfilTraversUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08ProfilTraversUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08ProfilTraversUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08ProfilTraversUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ProfilTravers_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHistoF08ProfilTraversUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetFichierNomenclatureHistoF08ProfilTraversUtf8() ********** ");
		}

		final File fichierNomenclatureHistoF08ProfilTravers 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08ProfilTraversUtf8();

		final File fichierNomenclatureHistoF08ProfilTravers1 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08ProfilTraversUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHistoF08ProfilTravers : " 
					+ fichierNomenclatureHistoF08ProfilTravers);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHistoF08ProfilTraversUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HISTO_F08 PROFIL EN TRAVERS ne doit pas être null : "
					, fichierNomenclatureHistoF08ProfilTravers);
		
		/* garantit que getFichierNomenclatureHistoF08ProfilTraversUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHistoF08ProfilTravers doit exister : "
				, fichierNomenclatureHistoF08ProfilTravers.exists());
		
		/* garantit que getFichierNomenclatureHistoF08ProfilTraversUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHistoF08ProfilTravers, fichierNomenclatureHistoF08ProfilTravers1);
		
		/* garantit que getFichierNomenclatureHistoF08ProfilTraversUtf8() retourne "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ProfilTravers_HistoF08_Utf8.csv". */
		assertEquals("le fichier de nomenclature HISTO_F08 PROFIL EN TRAVERS doit être situé sous le contexte sous 'ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ProfilTravers_HistoF08_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ProfilTravers_HistoF08_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHistoF08ProfilTravers));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHistoF08ProfilTraversUtf8()._____________

	
	
	/**
	 * teste la méthode getNomNomenclatureHistoF08SousReseauIndice().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHistoF08SousReseauIndice() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHistoF08SousReseauIndice() retourne "2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.sousreseauindice.histof08 = 2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHistoF08SousReseauIndice() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetNomNomenclatureHistoF08SousReseauIndice() ********** ");
		}

		final String cheminNomenclatureHistoF08SousReseauIndice 
			= ConfigurationNomenclaturesHistoF08Manager.getNomNomenclatureHistoF08SousReseauIndice();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHistoF08SousReseauIndice : " 
					+ cheminNomenclatureHistoF08SousReseauIndice);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHistoF08SousReseauIndice() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHistoF08SousReseauIndice);
		
		/* garantit que getNomNomenclatureHistoF08SousReseauIndice() retourne "2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv' : "
				, "2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv"
					, cheminNomenclatureHistoF08SousReseauIndice);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHistoF08SousReseauIndice()._____________________


	
	/**
	 * teste la méthode getFichierNomenclatureHistoF08SousReseauIndiceUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHistoF08SousReseauIndiceUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08SousReseauIndiceUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08SousReseauIndiceUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHistoF08SousReseauIndiceUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHistoF08SousReseauIndiceUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF08ManagerTest - méthode testGetFichierNomenclatureHistoF08SousReseauIndiceUtf8() ********** ");
		}

		final File fichierNomenclatureHistoF08SousReseauIndice 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08SousReseauIndiceUtf8();

		final File fichierNomenclatureHistoF08SousReseauIndice1 
			= ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08SousReseauIndiceUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHistoF08SousReseauIndice : " 
					+ fichierNomenclatureHistoF08SousReseauIndice);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHistoF08SousReseauIndiceUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HISTO_F08 SOUS RESEAU INDICE ne doit pas être null : "
					, fichierNomenclatureHistoF08SousReseauIndice);
		
		/* garantit que getFichierNomenclatureHistoF08SousReseauIndiceUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHistoF08SousReseauIndice doit exister : "
				, fichierNomenclatureHistoF08SousReseauIndice.exists());
		
		/* garantit que getFichierNomenclatureHistoF08SousReseauIndiceUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHistoF08SousReseauIndice, fichierNomenclatureHistoF08SousReseauIndice1);
		
		/* garantit que getFichierNomenclatureHistoF08SousReseauIndiceUtf8() retourne "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv". */
		assertEquals("le fichier de nomenclature HISTO_F08 SOUS RESEAU INDICE doit être situé sous le contexte sous 'ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHistoF08SousReseauIndice));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHistoF08SousReseauIndiceUtf8()._____________


	
	/**
	 * retourne le 
	 * <b>path relatif de pFile par rapport à target/classes</b>.<br/>
	 * <ul>
	 * <li>Par exemple :<br/>
	 * <code>fournirPathRelatifSousTargetClasses(fichierDescriptionHit)</code> 
	 * retourne 
	 * 'ressources/Descriptions de fichier/Hit/Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv'
	 * </li>
	 * </ul>
	 *
	 * @param pFile : File : ressource dans le classpath.<br/>
	 * 
	 * @return Path : path relatif de pFile par rapport à target/classes.<br/>
	 * 
	 * @throws URISyntaxException
	 */
	private Path fournirPathRelatifSousTargetClasses(final File pFile) 
												throws URISyntaxException {
		
		final String pathClassesString 
		= ConfigurationNomenclaturesHistoF08Manager
			.retournerClassesSousTarget();
	
		final Path pathClasses = Paths.get(pathClassesString);
		
		final Path pathPFile = pFile.toPath();
		
		final Path pathRelatifPFile = pathClasses.relativize(pathPFile);
		
		return pathRelatifPFile;
		
	} // Fin de fournirPathRelatifSousTargetClasses(File pFile).___________


	
} // FIN DE LA CLASSE ConfigurationNomenclaturesHistoF08ManagerTest.---------
