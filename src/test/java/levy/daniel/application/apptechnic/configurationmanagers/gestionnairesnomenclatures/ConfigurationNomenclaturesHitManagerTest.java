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
