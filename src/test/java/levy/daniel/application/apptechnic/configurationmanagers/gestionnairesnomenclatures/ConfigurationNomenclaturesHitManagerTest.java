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
 * CLASSE ConfigurationNomenclaturesHitManagerTest :<br/>
 * Test JUnit de la classe ConfigurationNomenclaturesHitManager.<br/>
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
public class ConfigurationNomenclaturesHitManagerTest {

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
	= LogFactory.getLog(ConfigurationNomenclaturesHitManagerTest.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ConfigurationNomenclaturesHitManagerTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * teste la méthode getCheminNomenclaturesHitUtf8().<br/>
	 * <ul>
	 * <li>garantit que getCheminNomenclaturesHitUtf8() ne retourne pas null.</li>
	 * <li>garantit que getCheminNomenclaturesHitUtf8() retourne "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.chemin.hit.utf8 = ressources/Nomenclatures/Hit/Nomenclatures en UTF-8"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetCheminNomenclaturesHitUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetCheminNomenclaturesHitUtf8() ********** ");
		}

		final String cheminNomenclatures 
			= ConfigurationNomenclaturesHitManager.getCheminNomenclaturesHitUtf8();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatures : " 
					+ cheminNomenclatures);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getCheminNomenclaturesHitUtf8() ne retourne pas null. */
		assertNotNull(
				"Le chemin des descriptions (String) ne doit pas être null : "
					, cheminNomenclatures);
		
		/* garantit que getCheminNomenclaturesHitUtf8() retourne "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8". */
		assertEquals("le chemin des nomenclatures doit valoir 'ressources/Nomenclatures/Hit/Nomenclatures en UTF-8' : "
				, "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8"
					, cheminNomenclatures);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetCheminNomenclaturesHitUtf8()._______________________________

	
		
	/**
	 * teste la méthode getNomNomenclatureHitSens().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHitSens() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHitSens() retourne "2014-07-15_Nomenclature_Sens_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.sens.hit = 2014-07-15_Nomenclature_Sens_Hit_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHitSens() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetNomNomenclatureHitSens() ********** ");
		}

		final String cheminNomenclatureHitSens 
			= ConfigurationNomenclaturesHitManager.getNomNomenclatureHitSens();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHitSens : " 
					+ cheminNomenclatureHitSens);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHitSens() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null"
					, cheminNomenclatureHitSens);
		
		/* garantit que getNomNomenclatureHitSens() retourne "2014-07-15_Nomenclature_Sens_Hit_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_Sens_Hit_Utf8.csv' : "
				, "2014-07-15_Nomenclature_Sens_Hit_Utf8.csv"
					, cheminNomenclatureHitSens);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHitSens().___________________________


	
	/**
	 * teste la méthode getFichierNomenclatureHitSensUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHitSensUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHitSensUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHitSensUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHitSensUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Sens_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHitSensUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetFichierNomenclatureHitSensUtf8() ********** ");
		}

		final File fichierNomenclatureHitSens 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitSensUtf8();

		final File fichierNomenclatureHitSens1 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitSensUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHitSens : " 
					+ fichierNomenclatureHitSens);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHitSensUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HIT SENS ne doit pas être null : "
					, fichierNomenclatureHitSens);
		
		/* garantit que getFichierNomenclatureHitSensUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHitSens doit exister : "
				, fichierNomenclatureHitSens.exists());
		
		/* garantit que getFichierNomenclatureHitSensUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHitSens, fichierNomenclatureHitSens1);
		
		/* garantit que getFichierNomenclatureHitSensUtf8() retourne "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Sens_Hit_Utf8.csv". */
		assertEquals("le fichier de nomenclature HIT SENS doit être situé sous le contexte sous 'ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Sens_Hit_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Sens_Hit_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHitSens));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHitSensUtf8().___________________

	
	
	/**
	 * teste la méthode getNomNomenclatureHitNature().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHitNature() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHitNature() retourne "2014-07-15_Nomenclature_Nature_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.nature.hit = 2014-07-15_Nomenclature_Nature_Hit_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHitNature() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetNomNomenclatureHitNature() ********** ");
		}

		final String cheminNomenclatureHitNature 
			= ConfigurationNomenclaturesHitManager.getNomNomenclatureHitNature();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHitNature : " 
					+ cheminNomenclatureHitNature);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHitNature() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHitNature);
		
		/* garantit que getNomNomenclatureHitNature() retourne "2014-07-15_Nomenclature_Nature_Hit_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_Nature_Hit_Utf8.csv' : "
				, "2014-07-15_Nomenclature_Nature_Hit_Utf8.csv"
					, cheminNomenclatureHitNature);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHitNature()._________________________


	
	/**
	 * teste la méthode getFichierNomenclatureHitNatureUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHitNatureUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHitNatureUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHitNatureUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHitNatureUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Nature_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHitNatureUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetFichierNomenclatureHitNatureUtf8() ********** ");
		}

		final File fichierNomenclatureHitNature 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitNatureUtf8();

		final File fichierNomenclatureHitNature1 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitNatureUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHitNature : " 
					+ fichierNomenclatureHitNature);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHitNatureUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HIT NATURE ne doit pas être null : "
					, fichierNomenclatureHitNature);
		
		/* garantit que getFichierNomenclatureHitNatureUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHitNature doit exister : "
				, fichierNomenclatureHitNature.exists());
		
		/* garantit que getFichierNomenclatureHitNatureUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHitNature, fichierNomenclatureHitNature1);
		
		/* garantit que getFichierNomenclatureHitNatureUtf8() retourne "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Nature_Hit_Utf8.csv". */
		assertEquals("le fichier de nomenclature HIT NATURE doit être situé sous le contexte sous 'ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Nature_Hit_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Nature_Hit_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHitNature));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHitNatureUtf8().___________________

	
	
	/**
	 * teste la méthode getNomNomenclatureHitCatAdminRoute().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHitCatAdminRoute() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHitCatAdminRoute() retourne "2014-07-15_Nomenclature_CatAdminRoute_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.catadminroute.hit = 2014-07-15_Nomenclature_CatAdminRoute_Hit_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHitCatAdminRoute() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetNomNomenclatureHitCatAdminRoute() ********** ");
		}

		final String cheminNomenclatureHitCatAdminRoute 
			= ConfigurationNomenclaturesHitManager.getNomNomenclatureHitCatAdminRoute();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHitCatAdminRoute : " 
					+ cheminNomenclatureHitCatAdminRoute);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHitCatAdminRoute() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHitCatAdminRoute);
		
		/* garantit que getNomNomenclatureHitCatAdminRoute() retourne "2014-07-15_Nomenclature_CatAdminRoute_Hit_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_CatAdminRoute_Hit_Utf8.csv' : "
				, "2014-07-15_Nomenclature_CatAdminRoute_Hit_Utf8.csv"
					, cheminNomenclatureHitCatAdminRoute);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHitCatAdminRoute()._________________________


	
	/**
	 * teste la méthode getFichierNomenclatureHitCatAdminRouteUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHitCatAdminRouteUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHitCatAdminRouteUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHitCatAdminRouteUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHitCatAdminRouteUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_CatAdminRoute_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHitCatAdminRouteUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetFichierNomenclatureHitCatAdminRouteUtf8() ********** ");
		}

		final File fichierNomenclatureHitCatAdminRoute 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitCatAdminRouteUtf8();

		final File fichierNomenclatureHitCatAdminRoute1 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitCatAdminRouteUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHitCatAdminRoute : " 
					+ fichierNomenclatureHitCatAdminRoute);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHitCatAdminRouteUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HIT CAT ADMIN ROUTE ne doit pas être null : "
					, fichierNomenclatureHitCatAdminRoute);
		
		/* garantit que getFichierNomenclatureHitCatAdminRouteUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHitCatAdminRoute doit exister : "
				, fichierNomenclatureHitCatAdminRoute.exists());
		
		/* garantit que getFichierNomenclatureHitCatAdminRouteUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHitCatAdminRoute, fichierNomenclatureHitCatAdminRoute1);
		
		/* garantit que getFichierNomenclatureHitCatAdminRouteUtf8() retourne "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_CatAdminRoute_Hit_Utf8.csv". */
		assertEquals("le fichier de nomenclature HIT CAT ADMIN ROUTE doit être situé sous le contexte sous 'ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_CatAdminRoute_Hit_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_CatAdminRoute_Hit_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHitCatAdminRoute));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHitCatAdminRouteUtf8().___________________

	
	
	/**
	 * teste la méthode getNomNomenclatureHitTypeComptage().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHitTypeComptage() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHitTypeComptage() retourne "2014-07-15_Nomenclature_TypeComptage_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.typecomptage.hit = 2014-07-15_Nomenclature_TypeComptage_Hit_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHitTypeComptage() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetNomNomenclatureHitTypeComptage() ********** ");
		}

		final String cheminNomenclatureHitTypeComptage 
			= ConfigurationNomenclaturesHitManager.getNomNomenclatureHitTypeComptage();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHitTypeComptage : " 
					+ cheminNomenclatureHitTypeComptage);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHitTypeComptage() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHitTypeComptage);
		
		/* garantit que getNomNomenclatureHitTypeComptage() retourne "2014-07-15_Nomenclature_TypeComptage_Hit_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_TypeComptage_Hit_Utf8.csv' : "
				, "2014-07-15_Nomenclature_TypeComptage_Hit_Utf8.csv"
					, cheminNomenclatureHitTypeComptage);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHitTypeComptage().___________________


	
	/**
	 * teste la méthode getFichierNomenclatureHitTypeComptageUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHitTypeComptageUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHitTypeComptageUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHitTypeComptageUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHitTypeComptageUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHitTypeComptageUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetFichierNomenclatureHitTypeComptageUtf8() ********** ");
		}

		final File fichierNomenclatureHitTypeComptage 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitTypeComptageUtf8();

		final File fichierNomenclatureHitTypeComptage1 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitTypeComptageUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHitTypeComptage : " 
					+ fichierNomenclatureHitTypeComptage);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHitTypeComptageUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HIT TYPE DE COMPTAGE ne doit pas être null : "
					, fichierNomenclatureHitTypeComptage);
		
		/* garantit que getFichierNomenclatureHitTypeComptageUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHitTypeComptage doit exister : "
				, fichierNomenclatureHitTypeComptage.exists());
		
		/* garantit que getFichierNomenclatureHitTypeComptageUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHitTypeComptage, fichierNomenclatureHitTypeComptage1);
		
		/* garantit que getFichierNomenclatureHitTypeComptageUtf8() retourne "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_Hit_Utf8.csv". */
		assertEquals("le fichier de nomenclature HIT TYPE DE COMPTAGE doit être situé sous le contexte sous 'ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_Hit_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_Hit_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHitTypeComptage));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHitTypeComptageUtf8().___________

	
	
	/**
	 * teste la méthode getNomNomenclatureHitClassementRoute().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHitClassementRoute() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHitClassementRoute() retourne "2014-07-15_Nomenclature_ClassementRoute_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.classementroute.hit = 2014-07-15_Nomenclature_ClassementRoute_Hit_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHitClassementRoute() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetNomNomenclatureHitClassementRoute() ********** ");
		}

		final String cheminNomenclatureHitClassementRoute 
			= ConfigurationNomenclaturesHitManager.getNomNomenclatureHitClassementRoute();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHitClassementRoute : " 
					+ cheminNomenclatureHitClassementRoute);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHitClassementRoute() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHitClassementRoute);
		
		/* garantit que getNomNomenclatureHitClassementRoute() retourne "2014-07-15_Nomenclature_ClassementRoute_Hit_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_ClassementRoute_Hit_Utf8.csv' : "
				, "2014-07-15_Nomenclature_ClassementRoute_Hit_Utf8.csv"
					, cheminNomenclatureHitClassementRoute);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHitClassementRoute().________________


	
	/**
	 * teste la méthode getFichierNomenclatureHitClassementRouteUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHitClassementRouteUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHitClassementRouteUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHitClassementRouteUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHitClassementRouteUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHitClassementRouteUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetFichierNomenclatureHitClassementRouteUtf8() ********** ");
		}

		final File fichierNomenclatureHitClassementRoute 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitClassementRouteUtf8();

		final File fichierNomenclatureHitClassementRoute1 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitClassementRouteUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHitClassementRoute : " 
					+ fichierNomenclatureHitClassementRoute);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHitClassementRouteUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HIT CLASSEMENT DE LA ROUTE ne doit pas être null : "
					, fichierNomenclatureHitClassementRoute);
		
		/* garantit que getFichierNomenclatureHitClassementRouteUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHitClassementRoute doit exister : "
				, fichierNomenclatureHitClassementRoute.exists());
		
		/* garantit que getFichierNomenclatureHitClassementRouteUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHitClassementRoute, fichierNomenclatureHitClassementRoute1);
		
		/* garantit que getFichierNomenclatureHitClassementRouteUtf8() retourne "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_Hit_Utf8.csv". */
		assertEquals("le fichier de nomenclature HIT CLASSEMENT DE LA ROUTE doit être situé sous le contexte sous 'ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_Hit_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_Hit_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHitClassementRoute));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHitClassementRouteUtf8().________

	
	
	/**
	 * teste la méthode getNomNomenclatureHitClasseLargeurChausseeU().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHitClasseLargeurChausseeU() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHitClasseLargeurChausseeU() retourne "2014-07-15_Nomenclature_ClasseLargeurChausseeU_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.hit = 2014-07-15_Nomenclature_ClasseLargeurChausseeU_Hit_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHitClasseLargeurChausseeU() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetNomNomenclatureHitClasseLargeurChausseeU() ********** ");
		}

		final String cheminNomenclatureHitClasseLargeurChausseeU 
			= ConfigurationNomenclaturesHitManager.getNomNomenclatureHitClasseLargeurChausseeU();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHitClasseLargeurChausseeU : " 
					+ cheminNomenclatureHitClasseLargeurChausseeU);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHitClasseLargeurChausseeU() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHitClasseLargeurChausseeU);
		
		/* garantit que getNomNomenclatureHitClasseLargeurChausseeU() retourne "2014-07-15_Nomenclature_ClasseLargeurChausseeU_Hit_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_ClasseLargeurChausseeU_Hit_Utf8.csv' : "
				, "2014-07-15_Nomenclature_ClasseLargeurChausseeU_Hit_Utf8.csv"
					, cheminNomenclatureHitClasseLargeurChausseeU);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHitClasseLargeurChausseeU()._________


	
	/**
	 * teste la méthode getFichierNomenclatureHitClasseLargeurChausseeUUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHitClasseLargeurChausseeUUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHitClasseLargeurChausseeUUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHitClasseLargeurChausseeUUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHitClasseLargeurChausseeUUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClasseLargeurChausseeU_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHitClasseLargeurChausseeUUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetFichierNomenclatureHitClasseLargeurChausseeUUtf8() ********** ");
		}

		final File fichierNomenclatureHitClasseLargeurChausseeU 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitClasseLargeurChausseeUUtf8();

		final File fichierNomenclatureHitClasseLargeurChausseeU1 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitClasseLargeurChausseeUUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHitClasseLargeurChausseeU : " 
					+ fichierNomenclatureHitClasseLargeurChausseeU);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHitClasseLargeurChausseeUUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HIT CLASSE DE LARGEUR DE CHAUSSEE UNIQUE ne doit pas être null : "
					, fichierNomenclatureHitClasseLargeurChausseeU);
		
		/* garantit que getFichierNomenclatureHitClasseLargeurChausseeUUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHitClasseLargeurChausseeU doit exister : "
				, fichierNomenclatureHitClasseLargeurChausseeU.exists());
		
		/* garantit que getFichierNomenclatureHitClasseLargeurChausseeUUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHitClasseLargeurChausseeU, fichierNomenclatureHitClasseLargeurChausseeU1);
		
		/* garantit que getFichierNomenclatureHitClasseLargeurChausseeUUtf8() retourne "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClasseLargeurChausseeU_Hit_Utf8.csv". */
		assertEquals("le fichier de nomenclature HIT CLASSE DE LARGEUR DE CHAUSSEE UNIQUE doit être situé sous le contexte sous 'ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClasseLargeurChausseeU_Hit_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClasseLargeurChausseeU_Hit_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHitClasseLargeurChausseeU));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHitClasseLargeurChausseeUUtf8()._

	
	
	/**
	 * teste la méthode getNomNomenclatureHitClasseLargeurChausseesS().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHitClasseLargeurChausseesS() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHitClasseLargeurChausseesS() retourne "2014-07-15_Nomenclature_ClasseLargeurChausseesS_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.classelargeurchausseess.hit = 2014-07-15_Nomenclature_ClasseLargeurChausseesS_Hit_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHitClasseLargeurChausseesS() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetNomNomenclatureHitClasseLargeurChausseesS() ********** ");
		}

		final String cheminNomenclatureHitClasseLargeurChausseesS 
			= ConfigurationNomenclaturesHitManager.getNomNomenclatureHitClasseLargeurChausseesS();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHitClasseLargeurChausseesS : " 
					+ cheminNomenclatureHitClasseLargeurChausseesS);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHitClasseLargeurChausseesS() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHitClasseLargeurChausseesS);
		
		/* garantit que getNomNomenclatureHitClasseLargeurChausseesS() retourne "2014-07-15_Nomenclature_ClasseLargeurChausseesS_Hit_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_ClasseLargeurChausseesS_Hit_Utf8.csv' : "
				, "2014-07-15_Nomenclature_ClasseLargeurChausseesS_Hit_Utf8.csv"
					, cheminNomenclatureHitClasseLargeurChausseesS);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHitClasseLargeurChausseesS().________


	
	/**
	 * teste la méthode getFichierNomenclatureHitClasseLargeurChausseesSUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHitClasseLargeurChausseesSUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHitClasseLargeurChausseesSUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHitClasseLargeurChausseesSUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHitClasseLargeurChausseesSUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClasseLargeurChausseesS_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHitClasseLargeurChausseesSUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetFichierNomenclatureHitClasseLargeurChausseesSUtf8() ********** ");
		}

		final File fichierNomenclatureHitClasseLargeurChausseesS 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitClasseLargeurChausseesSUtf8();

		final File fichierNomenclatureHitClasseLargeurChausseesS1 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitClasseLargeurChausseesSUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHitClasseLargeurChausseesS : " 
					+ fichierNomenclatureHitClasseLargeurChausseesS);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHitClasseLargeurChausseesSUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HIT CLASSE DE LARGEUR DE CHAUSSEES SEPAREES ne doit pas être null : "
					, fichierNomenclatureHitClasseLargeurChausseesS);
		
		/* garantit que getFichierNomenclatureHitClasseLargeurChausseesSUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHitClasseLargeurChausseesS doit exister : "
				, fichierNomenclatureHitClasseLargeurChausseesS.exists());
		
		/* garantit que getFichierNomenclatureHitClasseLargeurChausseesSUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHitClasseLargeurChausseesS, fichierNomenclatureHitClasseLargeurChausseesS1);
		
		/* garantit que getFichierNomenclatureHitClasseLargeurChausseesSUtf8() retourne "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClasseLargeurChausseesS_Hit_Utf8.csv". */
		assertEquals("le fichier de nomenclature HIT CLASSE DE LARGEUR DE CHAUSSEES SEPAREES doit être situé sous le contexte sous 'ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClasseLargeurChausseesS_Hit_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClasseLargeurChausseesS_Hit_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHitClasseLargeurChausseesS));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHitClasseLargeurChausseesSUtf8()._

	
	
	/**
	 * teste la méthode getNomNomenclatureHitTypeReseau().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHitTypeReseau() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHitTypeReseau() retourne "2014-07-15_Nomenclature_TypeReseau_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.typereseau.hit = 2014-07-15_Nomenclature_TypeReseau_Hit_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHitTypeReseau() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetNomNomenclatureHitTypeReseau() ********** ");
		}

		final String cheminNomenclatureHitTypeReseau 
			= ConfigurationNomenclaturesHitManager.getNomNomenclatureHitTypeReseau();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHitTypeReseau : " 
					+ cheminNomenclatureHitTypeReseau);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHitTypeReseau() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHitTypeReseau);
		
		/* garantit que getNomNomenclatureHitTypeReseau() retourne "2014-07-15_Nomenclature_TypeReseau_Hit_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_TypeReseau_Hit_Utf8.csv' : "
				, "2014-07-15_Nomenclature_TypeReseau_Hit_Utf8.csv"
					, cheminNomenclatureHitTypeReseau);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHitTypeReseau()._____________________


	
	/**
	 * teste la méthode getFichierNomenclatureHitTypeReseauUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHitTypeReseauUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHitTypeReseauUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHitTypeReseauUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHitTypeReseauUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeReseau_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHitTypeReseauUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetFichierNomenclatureHitTypeReseauUtf8() ********** ");
		}

		final File fichierNomenclatureHitTypeReseau 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitTypeReseauUtf8();

		final File fichierNomenclatureHitTypeReseau1 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitTypeReseauUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHitTypeReseau : " 
					+ fichierNomenclatureHitTypeReseau);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHitTypeReseauUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HIT TYPE DE RESEAU ne doit pas être null : "
					, fichierNomenclatureHitTypeReseau);
		
		/* garantit que getFichierNomenclatureHitTypeReseauUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHitTypeReseau doit exister : "
				, fichierNomenclatureHitTypeReseau.exists());
		
		/* garantit que getFichierNomenclatureHitTypeReseauUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHitTypeReseau, fichierNomenclatureHitTypeReseau1);
		
		/* garantit que getFichierNomenclatureHitTypeReseauUtf8() retourne "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeReseau_Hit_Utf8.csv". */
		assertEquals("le fichier de nomenclature HIT TYPE DE RESEAU doit être situé sous le contexte sous 'ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeReseau_Hit_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeReseau_Hit_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHitTypeReseau));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHitTypeReseauUtf8()._____________

	
	
	/**
	 * teste la méthode getNomNomenclatureHitPrPk().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureHitPrPk() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureHitPrPk() retourne "2014-07-15_Nomenclature_PrPk_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.prpk.hit = 2014-07-15_Nomenclature_PrPk_Hit_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureHitPrPk() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetNomNomenclatureHitPrPk() ********** ");
		}

		final String cheminNomenclatureHitPrPk 
			= ConfigurationNomenclaturesHitManager.getNomNomenclatureHitPrPk();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureHitPrPk : " 
					+ cheminNomenclatureHitPrPk);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureHitPrPk() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureHitPrPk);
		
		/* garantit que getNomNomenclatureHitPrPk() retourne "2014-07-15_Nomenclature_PrPk_Hit_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_PrPk_Hit_Utf8.csv' : "
				, "2014-07-15_Nomenclature_PrPk_Hit_Utf8.csv"
					, cheminNomenclatureHitPrPk);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureHitPrPk().___________________________


	
	/**
	 * teste la méthode getFichierNomenclatureHitPrPkUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureHitPrPkUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureHitPrPkUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureHitPrPkUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureHitPrPkUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_PrPk_Hit_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureHitPrPkUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesHitManagerTest - méthode testGetFichierNomenclatureHitPrPkUtf8() ********** ");
		}

		final File fichierNomenclatureHitPrPk 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitPrPkUtf8();

		final File fichierNomenclatureHitPrPk1 
			= ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitPrPkUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureHitPrPk : " 
					+ fichierNomenclatureHitPrPk);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureHitPrPkUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature HIT PR-PK ne doit pas être null : "
					, fichierNomenclatureHitPrPk);
		
		/* garantit que getFichierNomenclatureHitPrPkUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureHitPrPk doit exister : "
				, fichierNomenclatureHitPrPk.exists());
		
		/* garantit que getFichierNomenclatureHitPrPkUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureHitPrPk, fichierNomenclatureHitPrPk1);
		
		/* garantit que getFichierNomenclatureHitPrPkUtf8() retourne "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_PrPk_Hit_Utf8.csv". */
		assertEquals("le fichier de nomenclature HIT PR-PK doit être situé sous le contexte sous 'ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_PrPk_Hit_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/2014-07-15_Nomenclature_PrPk_Hit_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureHitPrPk));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureHitPrPkUtf8().___________________


	
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
		= ConfigurationNomenclaturesHitManager
			.retournerClassesSousTarget();
	
		final Path pathClasses = Paths.get(pathClassesString);
		
		final Path pathPFile = pFile.toPath();
		
		final Path pathRelatifPFile = pathClasses.relativize(pathPFile);
		
		return pathRelatifPFile;
		
	} // Fin de fournirPathRelatifSousTargetClasses(File pFile).___________
	

	
} // FIN DE LA CLASSE ConfigurationNomenclaturesHitManagerTest.--------------
