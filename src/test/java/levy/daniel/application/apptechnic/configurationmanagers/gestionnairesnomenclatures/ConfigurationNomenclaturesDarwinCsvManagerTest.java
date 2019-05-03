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
 * CLASSE ConfigurationNomenclaturesDarwinCsvManagerTest :<br/>
 * Test JUnit de la classe {@link ConfigurationNomenclaturesDarwinCsvManager}.<br/>
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
public class ConfigurationNomenclaturesDarwinCsvManagerTest {

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
		= LogFactory.getLog(ConfigurationNomenclaturesDarwinCsvManagerTest.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ConfigurationNomenclaturesDarwinCsvManagerTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
		
	/**
	 * teste la méthode getCheminNomenclaturesDarwinCsvUtf8().<br/>
	 * <ul>
	 * <li>garantit que getCheminNomenclaturesDarwinCsvUtf8() ne retourne pas null.</li>
	 * <li>garantit que getCheminNomenclaturesDarwinCsvUtf8() retourne "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.chemin.darwincsv.utf8 = ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetCheminNomenclaturesDarwinCsvUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesDarwinCsvManagerTest - méthode testGetCheminNomenclaturesDarwinCsvUtf8() ********** ");
		}

		final String cheminNomenclatures 
			= ConfigurationNomenclaturesDarwinCsvManager.getCheminNomenclaturesDarwinCsvUtf8();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesDarwinCsvManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesDarwinCsvManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatures : " 
					+ cheminNomenclatures);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getCheminNomenclaturesDarwinCsvUtf8() ne retourne pas null. */
		assertNotNull(
				"Le chemin des descriptions (String) ne doit pas être null : "
					, cheminNomenclatures);
		
		/* garantit que getCheminNomenclaturesDarwinCsvUtf8() retourne "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8". */
		assertEquals("le chemin des nomenclatures doit valoir 'ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8' : "
				, "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8"
					, cheminNomenclatures);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetCheminNomenclaturesDarwinCsvUtf8()._______________________________

	
	
	/**
	 * teste la méthode getNomNomenclatureDarwinCsvSens().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureDarwinCsvSens() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureDarwinCsvSens() retourne "2014-07-15_Nomenclature_Sens_DarwinCsv_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.sens.darwincsv = 2014-07-15_Nomenclature_Sens_DarwinCsv_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureDarwinCsvSens() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesDarwinCsvManagerTest - méthode testGetNomNomenclatureDarwinCsvSens() ********** ");
		}

		final String cheminNomenclatureDarwinCsvSens 
			= ConfigurationNomenclaturesDarwinCsvManager.getNomNomenclatureDarwinCsvSens();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesDarwinCsvManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesDarwinCsvManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureDarwinCsvSens : " 
					+ cheminNomenclatureDarwinCsvSens);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureDarwinCsvSens() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null"
					, cheminNomenclatureDarwinCsvSens);
		
		/* garantit que getNomNomenclatureDarwinCsvSens() retourne "2014-07-15_Nomenclature_Sens_DarwinCsv_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_Sens_DarwinCsv_Utf8.csv' : "
				, "2014-07-15_Nomenclature_Sens_DarwinCsv_Utf8.csv"
					, cheminNomenclatureDarwinCsvSens);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureDarwinCsvSens().______________________


	
	/**
	 * teste la méthode getFichierNomenclatureDarwinCsvSensUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureDarwinCsvSensUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureDarwinCsvSensUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureDarwinCsvSensUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureDarwinCsvSensUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Sens_DarwinCsv_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureDarwinCsvSensUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesDarwinCsvManagerTest - méthode testGetFichierNomenclatureDarwinCsvSensUtf8() ********** ");
		}

		final File fichierNomenclatureDarwinCsvSens 
			= ConfigurationNomenclaturesDarwinCsvManager.getFichierNomenclatureDarwinCsvSensUtf8();

		final File fichierNomenclatureDarwinCsvSens1 
			= ConfigurationNomenclaturesDarwinCsvManager.getFichierNomenclatureDarwinCsvSensUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesDarwinCsvManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesDarwinCsvManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureDarwinCsvSens : " 
					+ fichierNomenclatureDarwinCsvSens);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureDarwinCsvSensUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature DARWIN_CSV SENS ne doit pas être null : "
					, fichierNomenclatureDarwinCsvSens);
		
		/* garantit que getFichierNomenclatureDarwinCsvSensUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureDarwinCsvSens doit exister : "
				, fichierNomenclatureDarwinCsvSens.exists());
		
		/* garantit que getFichierNomenclatureDarwinCsvSensUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureDarwinCsvSens, fichierNomenclatureDarwinCsvSens1);
		
		/* garantit que getFichierNomenclatureDarwinCsvSensUtf8() retourne "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Sens_DarwinCsv_Utf8.csv". */
		assertEquals("le fichier de nomenclature DARWIN_CSV SENS doit être situé sous le contexte sous 'ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Sens_DarwinCsv_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_Sens_DarwinCsv_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureDarwinCsvSens));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureDarwinCsvSensUtf8().______________

	
	
	/**
	 * teste la méthode getNomNomenclatureDarwinCsvTypeComptage().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureDarwinCsvTypeComptage() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureDarwinCsvTypeComptage() retourne "2014-07-15_Nomenclature_TypeComptage_DarwinCsv_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.typecomptage.darwincsv = 2014-07-15_Nomenclature_TypeComptage_DarwinCsv_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureDarwinCsvTypeComptage() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesDarwinCsvManagerTest - méthode testGetNomNomenclatureDarwinCsvTypeComptage() ********** ");
		}

		final String cheminNomenclatureDarwinCsvTypeComptage 
			= ConfigurationNomenclaturesDarwinCsvManager.getNomNomenclatureDarwinCsvTypeComptage();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesDarwinCsvManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesDarwinCsvManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureDarwinCsvTypeComptage : " 
					+ cheminNomenclatureDarwinCsvTypeComptage);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureDarwinCsvTypeComptage() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureDarwinCsvTypeComptage);
		
		/* garantit que getNomNomenclatureDarwinCsvTypeComptage() retourne "2014-07-15_Nomenclature_TypeComptage_DarwinCsv_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_TypeComptage_DarwinCsv_Utf8.csv' : "
				, "2014-07-15_Nomenclature_TypeComptage_DarwinCsv_Utf8.csv"
					, cheminNomenclatureDarwinCsvTypeComptage);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureDarwinCsvTypeComptage().___________________


	
	/**
	 * teste la méthode getFichierNomenclatureDarwinCsvTypeComptageUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureDarwinCsvTypeComptageUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureDarwinCsvTypeComptageUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureDarwinCsvTypeComptageUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureDarwinCsvTypeComptageUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_DarwinCsv_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureDarwinCsvTypeComptageUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesDarwinCsvManagerTest - méthode testGetFichierNomenclatureDarwinCsvTypeComptageUtf8() ********** ");
		}

		final File fichierNomenclatureDarwinCsvTypeComptage 
			= ConfigurationNomenclaturesDarwinCsvManager.getFichierNomenclatureDarwinCsvTypeComptageUtf8();

		final File fichierNomenclatureDarwinCsvTypeComptage1 
			= ConfigurationNomenclaturesDarwinCsvManager.getFichierNomenclatureDarwinCsvTypeComptageUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesDarwinCsvManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesDarwinCsvManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureDarwinCsvTypeComptage : " 
					+ fichierNomenclatureDarwinCsvTypeComptage);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureDarwinCsvTypeComptageUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature DARWIN_CSV TYPE DE COMPTAGE ne doit pas être null : "
					, fichierNomenclatureDarwinCsvTypeComptage);
		
		/* garantit que getFichierNomenclatureDarwinCsvTypeComptageUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureDarwinCsvTypeComptage doit exister : "
				, fichierNomenclatureDarwinCsvTypeComptage.exists());
		
		/* garantit que getFichierNomenclatureDarwinCsvTypeComptageUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureDarwinCsvTypeComptage, fichierNomenclatureDarwinCsvTypeComptage1);
		
		/* garantit que getFichierNomenclatureDarwinCsvTypeComptageUtf8() retourne "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_DarwinCsv_Utf8.csv". */
		assertEquals("le fichier de nomenclature DARWIN_CSV TYPE DE COMPTAGE doit être situé sous le contexte sous 'ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_DarwinCsv_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_TypeComptage_DarwinCsv_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureDarwinCsvTypeComptage));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureDarwinCsvTypeComptageUtf8().___________

	
	
	/**
	 * teste la méthode getNomNomenclatureDarwinCsvClassementRoute().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureDarwinCsvClassementRoute() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureDarwinCsvClassementRoute() retourne "2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.classementroute.darwincsv = 2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureDarwinCsvClassementRoute() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesDarwinCsvManagerTest - méthode testGetNomNomenclatureDarwinCsvClassementRoute() ********** ");
		}

		final String cheminNomenclatureDarwinCsvClassementRoute 
			= ConfigurationNomenclaturesDarwinCsvManager.getNomNomenclatureDarwinCsvClassementRoute();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesDarwinCsvManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesDarwinCsvManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureDarwinCsvClassementRoute : " 
					+ cheminNomenclatureDarwinCsvClassementRoute);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureDarwinCsvClassementRoute() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureDarwinCsvClassementRoute);
		
		/* garantit que getNomNomenclatureDarwinCsvClassementRoute() retourne "2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv' : "
				, "2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv"
					, cheminNomenclatureDarwinCsvClassementRoute);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureDarwinCsvClassementRoute().________________


	
	/**
	 * teste la méthode getFichierNomenclatureDarwinCsvClassementRouteUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureDarwinCsvClassementRouteUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureDarwinCsvClassementRouteUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureDarwinCsvClassementRouteUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureDarwinCsvClassementRouteUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureDarwinCsvClassementRouteUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesDarwinCsvManagerTest - méthode testGetFichierNomenclatureDarwinCsvClassementRouteUtf8() ********** ");
		}

		final File fichierNomenclatureDarwinCsvClassementRoute 
			= ConfigurationNomenclaturesDarwinCsvManager.getFichierNomenclatureDarwinCsvClassementRouteUtf8();

		final File fichierNomenclatureDarwinCsvClassementRoute1 
			= ConfigurationNomenclaturesDarwinCsvManager.getFichierNomenclatureDarwinCsvClassementRouteUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesDarwinCsvManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesDarwinCsvManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureDarwinCsvClassementRoute : " 
					+ fichierNomenclatureDarwinCsvClassementRoute);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureDarwinCsvClassementRouteUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature DARWIN_CSV CLASSEMENT DE LA ROUTE ne doit pas être null : "
					, fichierNomenclatureDarwinCsvClassementRoute);
		
		/* garantit que getFichierNomenclatureDarwinCsvClassementRouteUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureDarwinCsvClassementRoute doit exister : "
				, fichierNomenclatureDarwinCsvClassementRoute.exists());
		
		/* garantit que getFichierNomenclatureDarwinCsvClassementRouteUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureDarwinCsvClassementRoute, fichierNomenclatureDarwinCsvClassementRoute1);
		
		/* garantit que getFichierNomenclatureDarwinCsvClassementRouteUtf8() retourne "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv". */
		assertEquals("le fichier de nomenclature DARWIN_CSV CLASSEMENT DE LA ROUTE doit être situé sous le contexte sous 'ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureDarwinCsvClassementRoute));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureDarwinCsvClassementRouteUtf8().________

	
	
	/**
	 * teste la méthode getNomNomenclatureDarwinCsvProfilTravers().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureDarwinCsvProfilTravers() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureDarwinCsvProfilTravers() retourne "2014-07-15_Nomenclature_ProfilTravers_DarwinCsv_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.profiltravers.darwincsv = 2014-07-15_Nomenclature_ProfilTravers_DarwinCsv_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureDarwinCsvProfilTravers() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesDarwinCsvManagerTest - méthode testGetNomNomenclatureDarwinCsvProfilTravers() ********** ");
		}

		final String cheminNomenclatureDarwinCsvProfilTravers 
			= ConfigurationNomenclaturesDarwinCsvManager.getNomNomenclatureDarwinCsvProfilTravers();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesDarwinCsvManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesDarwinCsvManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureDarwinCsvProfilTravers : " 
					+ cheminNomenclatureDarwinCsvProfilTravers);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureDarwinCsvProfilTravers() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureDarwinCsvProfilTravers);
		
		/* garantit que getNomNomenclatureDarwinCsvProfilTravers() retourne "2014-07-15_Nomenclature_ProfilTravers_DarwinCsv_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_ProfilTravers_DarwinCsv_Utf8.csv' : "
				, "2014-07-15_Nomenclature_ProfilTravers_DarwinCsv_Utf8.csv"
					, cheminNomenclatureDarwinCsvProfilTravers);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureDarwinCsvProfilTravers()._____________________


	
	/**
	 * teste la méthode getFichierNomenclatureDarwinCsvProfilTraversUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureDarwinCsvProfilTraversUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureDarwinCsvProfilTraversUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureDarwinCsvProfilTraversUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureDarwinCsvProfilTraversUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ProfilTravers_DarwinCsv_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureDarwinCsvProfilTraversUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesDarwinCsvManagerTest - méthode testGetFichierNomenclatureDarwinCsvProfilTraversUtf8() ********** ");
		}

		final File fichierNomenclatureDarwinCsvProfilTravers 
			= ConfigurationNomenclaturesDarwinCsvManager.getFichierNomenclatureDarwinCsvProfilTraversUtf8();

		final File fichierNomenclatureDarwinCsvProfilTravers1 
			= ConfigurationNomenclaturesDarwinCsvManager.getFichierNomenclatureDarwinCsvProfilTraversUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesDarwinCsvManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesDarwinCsvManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureDarwinCsvProfilTravers : " 
					+ fichierNomenclatureDarwinCsvProfilTravers);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureDarwinCsvProfilTraversUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature DARWIN_CSV PROFIL EN TRAVERS ne doit pas être null : "
					, fichierNomenclatureDarwinCsvProfilTravers);
		
		/* garantit que getFichierNomenclatureDarwinCsvProfilTraversUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureDarwinCsvProfilTravers doit exister : "
				, fichierNomenclatureDarwinCsvProfilTravers.exists());
		
		/* garantit que getFichierNomenclatureDarwinCsvProfilTraversUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureDarwinCsvProfilTravers, fichierNomenclatureDarwinCsvProfilTravers1);
		
		/* garantit que getFichierNomenclatureDarwinCsvProfilTraversUtf8() retourne "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ProfilTravers_DarwinCsv_Utf8.csv". */
		assertEquals("le fichier de nomenclature DARWIN_CSV PROFIL EN TRAVERS doit être situé sous le contexte sous 'ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ProfilTravers_DarwinCsv_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_ProfilTravers_DarwinCsv_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureDarwinCsvProfilTravers));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureDarwinCsvProfilTraversUtf8()._____________

	
	
	/**
	 * teste la méthode getNomNomenclatureDarwinCsvSousReseauIndice().<br/>
	 * <ul>
	 * <li>garantit que getNomNomenclatureDarwinCsvSousReseauIndice() ne retourne pas null.</li>
	 * <li>garantit que getNomNomenclatureDarwinCsvSousReseauIndice() retourne "2014-07-15_Nomenclature_SousReseauIndice_DarwinCsv_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.nomenclatures.sousreseauindice.darwincsv = 2014-07-15_Nomenclature_SousReseauIndice_DarwinCsv_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomNomenclatureDarwinCsvSousReseauIndice() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesDarwinCsvManagerTest - méthode testGetNomNomenclatureDarwinCsvSousReseauIndice() ********** ");
		}

		final String cheminNomenclatureDarwinCsvSousReseauIndice 
			= ConfigurationNomenclaturesDarwinCsvManager.getNomNomenclatureDarwinCsvSousReseauIndice();
		
		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesDarwinCsvManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesDarwinCsvManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminNomenclatureDarwinCsvSousReseauIndice : " 
					+ cheminNomenclatureDarwinCsvSousReseauIndice);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomNomenclatureDarwinCsvSousReseauIndice() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la nomenclature (String) ne doit pas être null : "
					, cheminNomenclatureDarwinCsvSousReseauIndice);
		
		/* garantit que getNomNomenclatureDarwinCsvSousReseauIndice() retourne "2014-07-15_Nomenclature_SousReseauIndice_DarwinCsv_Utf8.csv". */
		assertEquals("le nom de la nomenclature doit valoir '2014-07-15_Nomenclature_SousReseauIndice_DarwinCsv_Utf8.csv' : "
				, "2014-07-15_Nomenclature_SousReseauIndice_DarwinCsv_Utf8.csv"
					, cheminNomenclatureDarwinCsvSousReseauIndice);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomNomenclatureDarwinCsvSousReseauIndice()._____________________


	
	/**
	 * teste la méthode getFichierNomenclatureDarwinCsvSousReseauIndiceUtf8().<br/>
	 * <ul>
	 * <li>garantit que getFichierNomenclatureDarwinCsvSousReseauIndiceUtf8() ne retourne pas null.</li>
	 * <li>garantit que getFichierNomenclatureDarwinCsvSousReseauIndiceUtf8() retourne un fichier existant.</li>
	 * <li>garantit que getFichierNomenclatureDarwinCsvSousReseauIndiceUtf8() retourne un singleton.</li>
	 * <li>garantit que getFichierNomenclatureDarwinCsvSousReseauIndiceUtf8() retourne le fichier sous le contexte à
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_SousReseauIndice_DarwinCsv_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierNomenclatureDarwinCsvSousReseauIndiceUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationNomenclaturesDarwinCsvManagerTest - méthode testGetFichierNomenclatureDarwinCsvSousReseauIndiceUtf8() ********** ");
		}

		final File fichierNomenclatureDarwinCsvSousReseauIndice 
			= ConfigurationNomenclaturesDarwinCsvManager.getFichierNomenclatureDarwinCsvSousReseauIndiceUtf8();

		final File fichierNomenclatureDarwinCsvSousReseauIndice1 
			= ConfigurationNomenclaturesDarwinCsvManager.getFichierNomenclatureDarwinCsvSousReseauIndiceUtf8();

		final String messageIndividuelRapport 
			= ConfigurationNomenclaturesDarwinCsvManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationNomenclaturesDarwinCsvManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierNomenclatureDarwinCsvSousReseauIndice : " 
					+ fichierNomenclatureDarwinCsvSousReseauIndice);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierNomenclatureDarwinCsvSousReseauIndiceUtf8() ne retourne pas null. */
		assertNotNull(
				"Le fichier de nomenclature DARWIN_CSV SOUS RESEAU INDICE ne doit pas être null : "
					, fichierNomenclatureDarwinCsvSousReseauIndice);
		
		/* garantit que getFichierNomenclatureDarwinCsvSousReseauIndiceUtf8() retourne un fichier existant. */
		assertTrue("fichierNomenclatureDarwinCsvSousReseauIndice doit exister : "
				, fichierNomenclatureDarwinCsvSousReseauIndice.exists());
		
		/* garantit que getFichierNomenclatureDarwinCsvSousReseauIndiceUtf8() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierNomenclatureDarwinCsvSousReseauIndice, fichierNomenclatureDarwinCsvSousReseauIndice1);
		
		/* garantit que getFichierNomenclatureDarwinCsvSousReseauIndiceUtf8() retourne "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_SousReseauIndice_DarwinCsv_Utf8.csv". */
		assertEquals("le fichier de nomenclature DARWIN_CSV SOUS RESEAU INDICE doit être situé sous le contexte sous 'ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_SousReseauIndice_DarwinCsv_Utf8.csv' : "
				, Paths.get("ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/2014-07-15_Nomenclature_SousReseauIndice_DarwinCsv_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierNomenclatureDarwinCsvSousReseauIndice));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierNomenclatureDarwinCsvSousReseauIndiceUtf8()._____________


	
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
		= ConfigurationNomenclaturesDarwinCsvManager
			.retournerClassesSousTarget();
	
		final Path pathClasses = Paths.get(pathClassesString);
		
		final Path pathPFile = pFile.toPath();
		
		final Path pathRelatifPFile = pathClasses.relativize(pathPFile);
		
		return pathRelatifPFile;
		
	} // Fin de fournirPathRelatifSousTargetClasses(File pFile).___________


	
} // FIN DE LA CLASSE ConfigurationNomenclaturesDarwinCsvManagerTest.---------
