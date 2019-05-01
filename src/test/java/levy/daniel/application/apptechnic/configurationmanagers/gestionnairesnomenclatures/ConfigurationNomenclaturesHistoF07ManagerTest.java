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
 * CLASSE ConfigurationNomenclaturesHistoF07ManagerTest :<br/>
 * .<br/>
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
public class ConfigurationNomenclaturesHistoF07ManagerTest {

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
		= LogFactory.getLog(ConfigurationNomenclaturesHistoF07ManagerTest.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ConfigurationNomenclaturesHistoF07ManagerTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
		
	/**
	 * teste la méthode getCheminNomenclaturesHistoF07Utf8().<br/>
	 * <ul>
	 * <li>garantit que getCheminNomenclaturesHistoF07Utf8() ne retourne pas null.</li>
	 * <li>garantit que getCheminNomenclaturesHistoF07Utf8() retourne "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.chemin.histof07.utf8 = ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetCheminNomenclaturesHistoF07Utf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF07ManagerTest - méthode testGetCheminNomenclaturesHistoF07Utf8() ********** ");
		}

		final String cheminNomenclatures 
			= ConfigurationNomenclaturesHistoF07Manager.getCheminNomenclaturesHistoF07Utf8();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF07Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF07Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatures : " 
					+ cheminNomenclatures);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getCheminNomenclaturesHistoF07Utf8() ne retourne pas null. */
		assertNotNull(
				"Le chemin des descriptions (String) ne doit pas être null : "
					, cheminNomenclatures);
		
		/* garantit que getCheminNomenclaturesHistoF07Utf8() retourne "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8". */
		assertEquals("le chemin des nomenclatures doit valoir 'ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8' : "
				, "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8"
					, cheminNomenclatures);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetCheminNomenclaturesHistoF07Utf8()._______________________________

	
	
	/**
	 * teste la méthode getNomNomenclatureHistoF07Sens().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHistoF07Sens() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHistoF07Sens() retourne "2014-07-15_Nomenclature_Sens_HistoF07_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.sens.histof07 = 2014-07-15_Nomenclature_Sens_HistoF07_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHistoF07Sens() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF07ManagerTest - méthode testGetNomNomenclatureHistoF07Sens() ********** ");
		}

		final String cheminNomenclatureHistoF07Sens 
			= ConfigurationNomenclaturesHistoF07Manager.getNomNomenclatureHistoF07Sens();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF07Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF07Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHistoF07Sens : " 
					+ cheminNomenclatureHistoF07Sens);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHistoF07Sens() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null"
					, cheminNomenclatureHistoF07Sens);
		
		/* garantit que getNomNomenclatureHistoF07Sens() retourne "2014-07-15_Nomenclature_Sens_HistoF07_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_Sens_HistoF07_Utf8.csv' : "
				, "2014-07-15_Nomenclature_Sens_HistoF07_Utf8.csv"
					, cheminNomenclatureHistoF07Sens);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHistoF07Sens().______________________


	
	/**
	 * teste la méthode getFichierNomenclatureHistoF07SensUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHistoF07SensUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHistoF07SensUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHistoF07SensUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHistoF07SensUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Sens_HistoF07_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHistoF07SensUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF07ManagerTest - méthode testGetFichierNomenclatureHistoF07SensUtf8() ********** ");
		}

		final File fichierNomenclatureHistoF07Sens 
			= ConfigurationNomenclaturesHistoF07Manager.getFichierNomenclatureHistoF07SensUtf8();

		final File fichierNomenclatureHistoF07Sens1 
			= ConfigurationNomenclaturesHistoF07Manager.getFichierNomenclatureHistoF07SensUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF07Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF07Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHistoF07Sens : " 
					+ fichierNomenclatureHistoF07Sens);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHistoF07SensUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HISTO_F07 SENS ne doit pas être null : "
					, fichierNomenclatureHistoF07Sens);
		
		/* garantit que getFichierNomenclatureHistoF07SensUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHistoF07Sens doit exister : "
				, fichierNomenclatureHistoF07Sens.exists());
		
		/* garantit que getFichierNomenclatureHistoF07SensUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHistoF07Sens, fichierNomenclatureHistoF07Sens1);
		
		/* garantit que getFichierNomenclatureHistoF07SensUtf8() retourne "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Sens_HistoF07_Utf8.csv". */
		assertEquals("le fichier de nomenclature HISTO_F07 SENS doit être situé sous le contexte sous 'ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Sens_HistoF07_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Sens_HistoF07_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHistoF07Sens));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHistoF07SensUtf8().______________

	
	
	/**
	 * teste la méthode getNomNomenclatureHistoF07Nature().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHistoF07Nature() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHistoF07Nature() retourne "2014-07-15_Nomenclature_Nature_HistoF07_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.nature.histof07 = 2014-07-15_Nomenclature_Nature_HistoF07_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHistoF07Nature() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF07ManagerTest - méthode testGetNomNomenclatureHistoF07Nature() ********** ");
		}

		final String cheminNomenclatureHistoF07Nature 
			= ConfigurationNomenclaturesHistoF07Manager.getNomNomenclatureHistoF07Nature();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF07Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF07Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHistoF07Nature : " 
					+ cheminNomenclatureHistoF07Nature);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHistoF07Nature() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHistoF07Nature);
		
		/* garantit que getNomNomenclatureHistoF07Nature() retourne "2014-07-15_Nomenclature_Nature_HistoF07_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_Nature_HistoF07_Utf8.csv' : "
				, "2014-07-15_Nomenclature_Nature_HistoF07_Utf8.csv"
					, cheminNomenclatureHistoF07Nature);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHistoF07Nature()._________________________


	
	/**
	 * teste la méthode getFichierNomenclatureHistoF07NatureUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHistoF07NatureUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHistoF07NatureUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHistoF07NatureUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHistoF07NatureUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Nature_HistoF07_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHistoF07NatureUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF07ManagerTest - méthode testGetFichierNomenclatureHistoF07NatureUtf8() ********** ");
		}

		final File fichierNomenclatureHistoF07Nature 
			= ConfigurationNomenclaturesHistoF07Manager.getFichierNomenclatureHistoF07NatureUtf8();

		final File fichierNomenclatureHistoF07Nature1 
			= ConfigurationNomenclaturesHistoF07Manager.getFichierNomenclatureHistoF07NatureUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF07Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF07Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHistoF07Nature : " 
					+ fichierNomenclatureHistoF07Nature);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHistoF07NatureUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HISTO_F07 NATURE ne doit pas être null : "
					, fichierNomenclatureHistoF07Nature);
		
		/* garantit que getFichierNomenclatureHistoF07NatureUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHistoF07Nature doit exister : "
				, fichierNomenclatureHistoF07Nature.exists());
		
		/* garantit que getFichierNomenclatureHistoF07NatureUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHistoF07Nature, fichierNomenclatureHistoF07Nature1);
		
		/* garantit que getFichierNomenclatureHistoF07NatureUtf8() retourne "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Nature_HistoF07_Utf8.csv". */
		assertEquals("le fichier de nomenclature HISTO_F07 NATURE doit être situé sous le contexte sous 'ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Nature_HistoF07_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Nature_HistoF07_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHistoF07Nature));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHistoF07NatureUtf8().___________________

	
	
	/**
	 * teste la méthode getNomNomenclatureHistoF07CatAdminRoute().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHistoF07CatAdminRoute() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHistoF07CatAdminRoute() retourne "2014-07-15_Nomenclature_CatAdminRoute_HistoF07_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.catadminroute.histof07 = 2014-07-15_Nomenclature_CatAdminRoute_HistoF07_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHistoF07CatAdminRoute() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF07ManagerTest - méthode testGetNomNomenclatureHistoF07CatAdminRoute() ********** ");
		}

		final String cheminNomenclatureHistoF07CatAdminRoute 
			= ConfigurationNomenclaturesHistoF07Manager.getNomNomenclatureHistoF07CatAdminRoute();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF07Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF07Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHistoF07CatAdminRoute : " 
					+ cheminNomenclatureHistoF07CatAdminRoute);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHistoF07CatAdminRoute() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHistoF07CatAdminRoute);
		
		/* garantit que getNomNomenclatureHistoF07CatAdminRoute() retourne "2014-07-15_Nomenclature_CatAdminRoute_HistoF07_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_CatAdminRoute_HistoF07_Utf8.csv' : "
				, "2014-07-15_Nomenclature_CatAdminRoute_HistoF07_Utf8.csv"
					, cheminNomenclatureHistoF07CatAdminRoute);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHistoF07CatAdminRoute()._____________


	
	/**
	 * teste la méthode getFichierNomenclatureHistoF07CatAdminRouteUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHistoF07CatAdminRouteUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHistoF07CatAdminRouteUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHistoF07CatAdminRouteUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHistoF07CatAdminRouteUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_CatAdminRoute_HistoF07_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHistoF07CatAdminRouteUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF07ManagerTest - méthode testGetFichierNomenclatureHistoF07CatAdminRouteUtf8() ********** ");
		}

		final File fichierNomenclatureHistoF07CatAdminRoute 
			= ConfigurationNomenclaturesHistoF07Manager.getFichierNomenclatureHistoF07CatAdminRouteUtf8();

		final File fichierNomenclatureHistoF07CatAdminRoute1 
			= ConfigurationNomenclaturesHistoF07Manager.getFichierNomenclatureHistoF07CatAdminRouteUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF07Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF07Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHistoF07CatAdminRoute : " 
					+ fichierNomenclatureHistoF07CatAdminRoute);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHistoF07CatAdminRouteUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HISTO_F07 CAT ADMIN ROUTE ne doit pas être null : "
					, fichierNomenclatureHistoF07CatAdminRoute);
		
		/* garantit que getFichierNomenclatureHistoF07CatAdminRouteUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHistoF07CatAdminRoute doit exister : "
				, fichierNomenclatureHistoF07CatAdminRoute.exists());
		
		/* garantit que getFichierNomenclatureHistoF07CatAdminRouteUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHistoF07CatAdminRoute, fichierNomenclatureHistoF07CatAdminRoute1);
		
		/* garantit que getFichierNomenclatureHistoF07CatAdminRouteUtf8() retourne "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_CatAdminRoute_HistoF07_Utf8.csv". */
		assertEquals("le fichier de nomenclature HISTO_F07 CAT ADMIN ROUTE doit être situé sous le contexte sous 'ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_CatAdminRoute_HistoF07_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_CatAdminRoute_HistoF07_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHistoF07CatAdminRoute));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHistoF07CatAdminRouteUtf8()._____

	
	
	/**
	 * teste la méthode getNomNomenclatureHistoF07TypeComptage().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHistoF07TypeComptage() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHistoF07TypeComptage() retourne "2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.typecomptage.histof07 = 2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHistoF07TypeComptage() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF07ManagerTest - méthode testGetNomNomenclatureHistoF07TypeComptage() ********** ");
		}

		final String cheminNomenclatureHistoF07TypeComptage 
			= ConfigurationNomenclaturesHistoF07Manager.getNomNomenclatureHistoF07TypeComptage();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF07Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF07Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHistoF07TypeComptage : " 
					+ cheminNomenclatureHistoF07TypeComptage);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHistoF07TypeComptage() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHistoF07TypeComptage);
		
		/* garantit que getNomNomenclatureHistoF07TypeComptage() retourne "2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv' : "
				, "2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv"
					, cheminNomenclatureHistoF07TypeComptage);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHistoF07TypeComptage().___________________


	
	/**
	 * teste la méthode getFichierNomenclatureHistoF07TypeComptageUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHistoF07TypeComptageUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHistoF07TypeComptageUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHistoF07TypeComptageUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHistoF07TypeComptageUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHistoF07TypeComptageUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF07ManagerTest - méthode testGetFichierNomenclatureHistoF07TypeComptageUtf8() ********** ");
		}

		final File fichierNomenclatureHistoF07TypeComptage 
			= ConfigurationNomenclaturesHistoF07Manager.getFichierNomenclatureHistoF07TypeComptageUtf8();

		final File fichierNomenclatureHistoF07TypeComptage1 
			= ConfigurationNomenclaturesHistoF07Manager.getFichierNomenclatureHistoF07TypeComptageUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF07Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF07Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHistoF07TypeComptage : " 
					+ fichierNomenclatureHistoF07TypeComptage);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHistoF07TypeComptageUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HISTO_F07 TYPE DE COMPTAGE ne doit pas être null : "
					, fichierNomenclatureHistoF07TypeComptage);
		
		/* garantit que getFichierNomenclatureHistoF07TypeComptageUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHistoF07TypeComptage doit exister : "
				, fichierNomenclatureHistoF07TypeComptage.exists());
		
		/* garantit que getFichierNomenclatureHistoF07TypeComptageUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHistoF07TypeComptage, fichierNomenclatureHistoF07TypeComptage1);
		
		/* garantit que getFichierNomenclatureHistoF07TypeComptageUtf8() retourne "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv". */
		assertEquals("le fichier de nomenclature HISTO_F07 TYPE DE COMPTAGE doit être situé sous le contexte sous 'ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHistoF07TypeComptage));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHistoF07TypeComptageUtf8().___________

	
	
	/**
	 * teste la méthode getNomNomenclatureHistoF07ClassementRoute().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHistoF07ClassementRoute() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHistoF07ClassementRoute() retourne "2014-07-15_Nomenclature_ClassementRoute_HistoF07_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.classementroute.histof07 = 2014-07-15_Nomenclature_ClassementRoute_HistoF07_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHistoF07ClassementRoute() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF07ManagerTest - méthode testGetNomNomenclatureHistoF07ClassementRoute() ********** ");
		}

		final String cheminNomenclatureHistoF07ClassementRoute 
			= ConfigurationNomenclaturesHistoF07Manager.getNomNomenclatureHistoF07ClassementRoute();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF07Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF07Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHistoF07ClassementRoute : " 
					+ cheminNomenclatureHistoF07ClassementRoute);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHistoF07ClassementRoute() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHistoF07ClassementRoute);
		
		/* garantit que getNomNomenclatureHistoF07ClassementRoute() retourne "2014-07-15_Nomenclature_ClassementRoute_HistoF07_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_ClassementRoute_HistoF07_Utf8.csv' : "
				, "2014-07-15_Nomenclature_ClassementRoute_HistoF07_Utf8.csv"
					, cheminNomenclatureHistoF07ClassementRoute);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHistoF07ClassementRoute().________________


	
	/**
	 * teste la méthode getFichierNomenclatureHistoF07ClassementRouteUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHistoF07ClassementRouteUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHistoF07ClassementRouteUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHistoF07ClassementRouteUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHistoF07ClassementRouteUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_HistoF07_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHistoF07ClassementRouteUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHistoF07ManagerTest - méthode testGetFichierNomenclatureHistoF07ClassementRouteUtf8() ********** ");
		}

		final File fichierNomenclatureHistoF07ClassementRoute 
			= ConfigurationNomenclaturesHistoF07Manager.getFichierNomenclatureHistoF07ClassementRouteUtf8();

		final File fichierNomenclatureHistoF07ClassementRoute1 
			= ConfigurationNomenclaturesHistoF07Manager.getFichierNomenclatureHistoF07ClassementRouteUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHistoF07Manager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHistoF07Manager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHistoF07ClassementRoute : " 
					+ fichierNomenclatureHistoF07ClassementRoute);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHistoF07ClassementRouteUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HISTO_F07 CLASSEMENT DE LA ROUTE ne doit pas être null : "
					, fichierNomenclatureHistoF07ClassementRoute);
		
		/* garantit que getFichierNomenclatureHistoF07ClassementRouteUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHistoF07ClassementRoute doit exister : "
				, fichierNomenclatureHistoF07ClassementRoute.exists());
		
		/* garantit que getFichierNomenclatureHistoF07ClassementRouteUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHistoF07ClassementRoute, fichierNomenclatureHistoF07ClassementRoute1);
		
		/* garantit que getFichierNomenclatureHistoF07ClassementRouteUtf8() retourne "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_HistoF07_Utf8.csv". */
		assertEquals("le fichier de nomenclature HISTO_F07 CLASSEMENT DE LA ROUTE doit être situé sous le contexte sous 'ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_HistoF07_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_HistoF07_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHistoF07ClassementRoute));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHistoF07ClassementRouteUtf8().________


	
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
		= ConfigurationNomenclaturesHistoF07Manager
			.retournerClassesSousTarget();
	
		final Path pathClasses = Paths.get(pathClassesString);
		
		final Path pathPFile = pFile.toPath();
		
		final Path pathRelatifPFile = pathClasses.relativize(pathPFile);
		
		return pathRelatifPFile;
		
	} // Fin de fournirPathRelatifSousTargetClasses(File pFile).___________


	
} // FIN DE LA CLASSE ConfigurationNomenclaturesHistoF07ManagerTest.---------
