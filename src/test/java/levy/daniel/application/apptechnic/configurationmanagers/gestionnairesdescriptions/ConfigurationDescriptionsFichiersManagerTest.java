package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesdescriptions;

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
 * CLASSE ConfigurationDescriptionsFichiersManagerTest :<br/>
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
 * @since 27 avr. 2019
 *
 */
public class ConfigurationDescriptionsFichiersManagerTest {

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
		= LogFactory.getLog(ConfigurationDescriptionsFichiersManagerTest.class);
	

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ConfigurationDescriptionsFichiersManagerTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * teste la méthode getCheminDescriptions().<br/>
	 * <ul>
	 * <li>garantit que getCheminDescriptions() ne retourne pas null.</li>
	 * <li>garantit que getCheminDescriptions() retourne "ressources/Descriptions de fichier".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.descriptions = ressources/Descriptions de fichier"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetCheminDescriptions() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationDescriptionsFichiersManagerTest - méthode testGetCheminDescriptions() ********** ");
		}

		final String cheminDescriptions 
			= ConfigurationDescriptionsFichiersManager.getCheminDescriptions();
		
		final String messageIndividuelRapport 
			= ConfigurationDescriptionsFichiersManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationDescriptionsFichiersManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminDescriptions : " 
					+ cheminDescriptions);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getCheminDescriptions() ne retourne pas null. */
		assertNotNull(
				"Le chemin des descriptions (String) ne doit pas être null : "
					, cheminDescriptions);
		
		/* garantit que getCheminDescriptions() retourne "ressources/Descriptions de fichier". */
		assertEquals("le chemin des descriptions doit valoir 'ressources/Descriptions de fichier' : "
				, "ressources/Descriptions de fichier"
					, cheminDescriptions);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetCheminDescriptions()._______________________________


	
	/**
	 * teste la méthode getNomDescriptionHit().<br/>
	 * <ul>
	 * <li>garantit que getNomDescriptionHit() ne retourne pas null.</li>
	 * <li>garantit que getNomDescriptionHit() retourne "Hit/Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv".</li>
	 * <li>garantit que getNomDescriptionHit() retourne un singleton.</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.descriptions.hit = Hit/Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomDescriptionHit() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationDescriptionsFichiersManagerTest - méthode testGetNomDescriptionHit() ********** ");
		}

		final String cheminDescriptionHit 
			= ConfigurationDescriptionsFichiersManager.getNomDescriptionHit();
		
		final String cheminDescriptionHit2 
		= ConfigurationDescriptionsFichiersManager.getNomDescriptionHit();
		
		final String messageIndividuelRapport 
			= ConfigurationDescriptionsFichiersManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationDescriptionsFichiersManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminDescriptionHit : " 
					+ cheminDescriptionHit);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomDescriptionHit() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la description HIT (String) ne doit pas être null : "
					, cheminDescriptionHit);
		
		/* garantit que getNomDescriptionHit() retourne "Hit/Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv". */
		assertEquals("le chemin de la description HIT doit valoir 'Hit/Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv' : "
				, "Hit/Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv"
					, cheminDescriptionHit);
		
		/* garantit que getNomDescriptionHit() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, cheminDescriptionHit, cheminDescriptionHit2);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomDescriptionHit().________________________________ 


	
	/**
	 * teste la méthode getFichierDescriptionHit().<br/>
	 * <ul>
	 * <li>garantit que getFichierDescriptionHit() ne retourne pas null.</li>
	 * <li>garantit que getFichierDescriptionHit() retourne un fichier existant.</li>
	 * <li>garantit que getFichierDescriptionHit() retourne un singleton.</li>
	 * <li>garantit que getFichierDescriptionHit() retourne le fichier sous le contexte à
	 * "ressources/Descriptions de fichier/Hit/Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierDescriptionHit() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationDescriptionsFichiersManagerTest - méthode testGetFichierDescriptionHit() ********** ");
		}

		final File fichierDescriptionHit 
			= ConfigurationDescriptionsFichiersManager.getFichierDescriptionHit();

		final File fichierDescriptionHit_1 
			= ConfigurationDescriptionsFichiersManager.getFichierDescriptionHit();

		final String messageIndividuelRapport 
			= ConfigurationDescriptionsFichiersManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationDescriptionsFichiersManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierDescriptionHit : " 
					+ fichierDescriptionHit);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierDescriptionHit() ne retourne pas null. */
		assertNotNull(
				"Le fichier de la description HIT (String) ne doit pas être null : "
					, fichierDescriptionHit);
		
		/* garantit que getFichierDescriptionHit() retourne un fichier existant. */
		assertTrue("fichierDescriptionHit doit exister : "
				, fichierDescriptionHit.exists());
		
		/* garantit que getFichierDescriptionHit() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierDescriptionHit, fichierDescriptionHit_1);
		
		/* garantit que getFichierDescriptionHit() retourne "ressources\Descriptions de fichier\Hit\Descriptions en UTF-8\2014-07-19_Description_HIT_Utf8.csv". */
		assertEquals("le fichier de la description HIT doit être situé sous le contexte sous 'ressources/Descriptions de fichier/Hit/Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv' : "
				, Paths.get("ressources/Descriptions de fichier/Hit/Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierDescriptionHit));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierDescriptionHit().____________________________ 


	
	/**
	 * teste la méthode getNomDescriptionHistoF07().<br/>
	 * <ul>
	 * <li>garantit que getNomDescriptionHistoF07() ne retourne pas null.</li>
	 * <li>garantit que getNomDescriptionHistoF07() retourne "HistonatF07/Descriptions en UTF-8/2014-07-19_Description_HistoF07_Utf8.csv".</li>
	 * <li>garantit que getNomDescriptionHistoF07() retourne un singleton.</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.descriptions.histof07 = HistonatF07/Descriptions en UTF-8/2014-07-19_Description_HistoF07_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomDescriptionHistoF07() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationDescriptionsFichiersManagerTest - méthode testGetNomDescriptionHistoF07() ********** ");
		}

		final String cheminDescriptionHistoF07 
			= ConfigurationDescriptionsFichiersManager.getNomDescriptionHistoF07();
		
		final String cheminDescriptionHistoF07_1 
			= ConfigurationDescriptionsFichiersManager.getNomDescriptionHistoF07();
		
		final String messageIndividuelRapport 
			= ConfigurationDescriptionsFichiersManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationDescriptionsFichiersManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminDescriptionHistoF07 : " 
					+ cheminDescriptionHistoF07);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomDescriptionHistoF07() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la description HISTOF07 (String) ne doit pas être null : "
					, cheminDescriptionHistoF07);
		
		/* garantit que getNomDescriptionHistoF07() retourne "HistonatF07/Descriptions en UTF-8/2014-07-19_Description_HistoF07_Utf8.csv". */
		assertEquals("le chemin de la description HISTOF07 doit valoir 'HistonatF07/Descriptions en UTF-8/2014-07-19_Description_HistoF07_Utf8.csv' : "
				, "HistonatF07/Descriptions en UTF-8/2014-07-19_Description_HistoF07_Utf8.csv"
					, cheminDescriptionHistoF07);
		
		/* garantit que getNomDescriptionHistoF07() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, cheminDescriptionHistoF07, cheminDescriptionHistoF07_1);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomDescriptionHistoF07().___________________________ 


	
	/**
	 * teste la méthode getFichierDescriptionHistoF07().<br/>
	 * <ul>
	 * <li>garantit que getFichierDescriptionHistoF07() ne retourne pas null.</li>
	 * <li>garantit que getFichierDescriptionHistoF07() retourne un fichier existant.</li>
	 * <li>garantit que getFichierDescriptionHistoF07() retourne un singleton.</li>
	 * <li>garantit que getFichierDescriptionHistoF07() retourne le fichier sous le contexte à
	 * "ressources/Descriptions de fichier/HistonatF07/Descriptions en UTF-8/2014-07-19_Description_HistoF07_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierDescriptionHistoF07() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationDescriptionsFichiersManagerTest - méthode testGetFichierDescriptionHistoF07() ********** ");
		}

		final File fichierDescriptionHistoF07 
			= ConfigurationDescriptionsFichiersManager.getFichierDescriptionHistoF07();
		
		final File fichierDescriptionHistoF07_1 
			= ConfigurationDescriptionsFichiersManager.getFichierDescriptionHistoF07();
		
		final String messageIndividuelRapport 
			= ConfigurationDescriptionsFichiersManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationDescriptionsFichiersManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierDescriptionHistoF07 : " 
					+ fichierDescriptionHistoF07);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierDescriptionHistoF07() ne retourne pas null. */
		assertNotNull(
				"Le fichier de la description HISTOF07 (String) ne doit pas être null : "
					, fichierDescriptionHistoF07);
		
		/* garantit que getFichierDescriptionHistoF07() retourne un fichier existant. */
		assertTrue("fichierDescriptionHistoF07 doit exister : "
				, fichierDescriptionHistoF07.exists());
		
		/* garantit que getFichierDescriptionHistoF07() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierDescriptionHistoF07, fichierDescriptionHistoF07_1);
		
		/* garantit que getFichierDescriptionHistoF07() retourne "ressources/Descriptions de fichier/HistonatF07/Descriptions en UTF-8/2014-07-19_Description_HistoF07_Utf8.csv". */
		assertEquals("le fichier de la description HISTOF07 doit être situé sous le contexte sous 'ressources/Descriptions de fichier/HistonatF07/Descriptions en UTF-8/2014-07-19_Description_HistoF07_Utf8.csv' : "
				, Paths.get("ressources/Descriptions de fichier/HistonatF07/Descriptions en UTF-8/2014-07-19_Description_HistoF07_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierDescriptionHistoF07));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierDescriptionHistoF07()._______________________ 


	
	/**
	 * teste la méthode getNomDescriptionDarwinCsv().<br/>
	 * <ul>
	 * <li>garantit que getNomDescriptionDarwinCsv() ne retourne pas null.</li>
	 * <li>garantit que getNomDescriptionDarwinCsv() retourne "Darwin csv/Descriptions en UTF-8/2014-07-19_Description_DarwinCsv_Utf8.csv".</li>
	 * <li>garantit que getNomDescriptionDarwinCsv() retourne un singleton.</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.descriptions.darwincsv = Darwin csv/Descriptions en UTF-8/2014-07-19_Description_DarwinCsv_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomDescriptionDarwinCsv() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationDescriptionsFichiersManagerTest - méthode testGetNomDescriptionDarwinCsv() ********** ");
		}

		final String cheminDescriptionDarwinCsv 
			= ConfigurationDescriptionsFichiersManager.getNomDescriptionDarwinCsv();
		
		final String cheminDescriptionDarwinCsv_1 
			= ConfigurationDescriptionsFichiersManager.getNomDescriptionDarwinCsv();
		
		final String messageIndividuelRapport 
			= ConfigurationDescriptionsFichiersManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationDescriptionsFichiersManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminDescriptionDarwinCsv : " 
					+ cheminDescriptionDarwinCsv);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomDescriptionDarwinCsv() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la description DARWIN_CSV (String) ne doit pas être null : "
					, cheminDescriptionDarwinCsv);
		
		/* garantit que getNomDescriptionDarwinCsv() retourne "Darwin csv/Descriptions en UTF-8/2014-07-19_Description_DarwinCsv_Utf8.csv". */
		assertEquals("le chemin de la description DARWIN_CSV doit valoir 'Darwin csv/Descriptions en UTF-8/2014-07-19_Description_DarwinCsv_Utf8.csv' : "
				, "Darwin csv/Descriptions en UTF-8/2014-07-19_Description_DarwinCsv_Utf8.csv"
					, cheminDescriptionDarwinCsv);
		
		/* garantit que getNomDescriptionDarwinCsv() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, cheminDescriptionDarwinCsv, cheminDescriptionDarwinCsv_1);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomDescriptionDarwinCsv().__________________________ 


	
	/**
	 * teste la méthode getFichierDescriptionDarwinCsv().<br/>
	 * <ul>
	 * <li>garantit que getFichierDescriptionDarwinCsv() ne retourne pas null.</li>
	 * <li>garantit que getFichierDescriptionDarwinCsv() retourne un fichier existant.</li>
	 * <li>garantit que getFichierDescriptionDarwinCsv() retourne un singleton.</li>
	 * <li>garantit que getFichierDescriptionDarwinCsv() retourne le fichier sous le contexte à
	 * "ressources/Descriptions de fichier/Darwin csv/Descriptions en UTF-8/2014-07-19_Description_DarwinCsv_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierDescriptionDarwinCsv() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationDescriptionsFichiersManagerTest - méthode testGetFichierDescriptionDarwinCsv() ********** ");
		}

		final File fichierDescriptionDarwinCsv 
			= ConfigurationDescriptionsFichiersManager.getFichierDescriptionDarwinCsv();
		
		final File fichierDescriptionDarwinCsv_1 
			= ConfigurationDescriptionsFichiersManager.getFichierDescriptionDarwinCsv();
		
		final String messageIndividuelRapport 
			= ConfigurationDescriptionsFichiersManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationDescriptionsFichiersManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierDescriptionDarwinCsv : " 
					+ fichierDescriptionDarwinCsv);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierDescriptionDarwinCsv() ne retourne pas null. */
		assertNotNull(
				"Le fichier de la description DARWIN_CSV (String) ne doit pas être null : "
					, fichierDescriptionDarwinCsv);
		
		/* garantit que getFichierDescriptionDarwinCsv() retourne un fichier existant. */
		assertTrue("fichierDescriptionDarwinCsv doit exister : "
				, fichierDescriptionDarwinCsv.exists());
		
		/* garantit que getFichierDescriptionDarwinCsv() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierDescriptionDarwinCsv, fichierDescriptionDarwinCsv_1);
		
		/* garantit que getFichierDescriptionDarwinCsv() retourne "ressources/Descriptions de fichier/Darwin csv/Descriptions en UTF-8/2014-07-19_Description_DarwinCsv_Utf8.csv". */
		assertEquals("le fichier de la description DARWIN_CSV doit être situé sous le contexte sous 'ressources/Descriptions de fichier/Darwin csv/Descriptions en UTF-8/2014-07-19_Description_DarwinCsv_Utf8.csv' : "
				, Paths.get("ressources/Descriptions de fichier/Darwin csv/Descriptions en UTF-8/2014-07-19_Description_DarwinCsv_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierDescriptionDarwinCsv));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierDescriptionDarwinCsv().______________________ 


	
	/**
	 * teste la méthode getNomDescriptionMapping().<br/>
	 * <ul>
	 * <li>garantit que getNomDescriptionMapping() ne retourne pas null.</li>
	 * <li>garantit que getNomDescriptionMapping() retourne "Mapping/Descriptions en UTF-8/2014-07-19_Description_Mapping_Utf8.csv".</li>
	 * <li>garantit que getNomDescriptionMapping() retourne un singleton.</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * <p>
	 * commenter la ligne 
	 * "application.repertoire.ressources.descriptions.mapping = Mapping/Descriptions en UTF-8/2014-07-19_Description_Mapping_Utf8.csv"
	 * dans le fichier <code>application_fr_FR.properties</code> 
	 * sous les resources 
	 * pour faire apparaitre les message et rapport d'erreur.
	 * </p>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomDescriptionMapping() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationDescriptionsFichiersManagerTest - méthode testGetNomDescriptionMapping() ********** ");
		}

		final String cheminDescriptionMapping 
			= ConfigurationDescriptionsFichiersManager.getNomDescriptionMapping();
		
		final String cheminDescriptionMapping_1 
			= ConfigurationDescriptionsFichiersManager.getNomDescriptionMapping();
		
		final String messageIndividuelRapport 
			= ConfigurationDescriptionsFichiersManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationDescriptionsFichiersManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("cheminDescriptionMapping : " 
					+ cheminDescriptionMapping);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
		
		/* garantit que getNomDescriptionMapping() ne retourne pas null. */
		assertNotNull(
				"Le chemin de la description MAPPING (String) ne doit pas être null : "
					, cheminDescriptionMapping);
		
		/* garantit que getNomDescriptionMapping() retourne "Mapping/Descriptions en UTF-8/2014-07-19_Description_Mapping_Utf8.csv". */
		assertEquals("le chemin de la description MAPPING doit valoir 'Mapping/Descriptions en UTF-8/2014-07-19_Description_Mapping_Utf8.csv' : "
				, "Mapping/Descriptions en UTF-8/2014-07-19_Description_Mapping_Utf8.csv"
					, cheminDescriptionMapping);
		
		/* garantit que getNomDescriptionMapping() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, cheminDescriptionMapping, cheminDescriptionMapping_1);
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetNomDescriptionMapping().____________________________ 


	
	/**
	 * teste la méthode getFichierDescriptionMapping().<br/>
	 * <ul>
	 * <li>garantit que getFichierDescriptionMapping() ne retourne pas null.</li>
	 * <li>garantit que getFichierDescriptionMapping() retourne un fichier existant.</li>
	 * <li>garantit que getFichierDescriptionMapping() retourne un singleton.</li>
	 * <li>garantit que getFichierDescriptionMapping() retourne le fichier sous le contexte à
	 * "ressources/Descriptions de fichier/Mapping/Descriptions en UTF-8/2014-07-19_Description_Mapping_Utf8.csv".</li>
	 * <li>garantit que le message d'erreur de configuration de la méthode est null.</li>
	 * <li>garantit que le rapport d'erreur de configuration de la classe est null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFichierDescriptionMapping() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationDescriptionsFichiersManagerTest - méthode testGetFichierDescriptionMapping() ********** ");
		}

		final File fichierDescriptionMapping 
			= ConfigurationDescriptionsFichiersManager.getFichierDescriptionMapping();
		
		final File fichierDescriptionMapping_1 
			= ConfigurationDescriptionsFichiersManager.getFichierDescriptionMapping();
		
		final String messageIndividuelRapport 
			= ConfigurationDescriptionsFichiersManager.getMessageIndividuelRapport();
		
		final String rapportConfigurationCsv 
			= ConfigurationDescriptionsFichiersManager.getRapportConfigurationCsv();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("fichierDescriptionMapping : " 
					+ fichierDescriptionMapping);
			System.out.println("message émis par la méthode : " 
					+ messageIndividuelRapport);
			System.out.println("rapport d'erreurs de configuration : " 
					+ rapportConfigurationCsv);
		}
			
		/* garantit que getFichierDescriptionMapping() ne retourne pas null. */
		assertNotNull(
				"Le fichier de la description MAPPING (String) ne doit pas être null : "
					, fichierDescriptionMapping);
		
		/* garantit que getFichierDescriptionMapping() retourne un fichier existant. */
		assertTrue("fichierDescriptionMapping doit exister : "
				, fichierDescriptionMapping.exists());
		
		/* garantit que getFichierDescriptionMapping() retourne un singleton. */
		assertSame("doit retourner un singleton : "
				, fichierDescriptionMapping, fichierDescriptionMapping_1);
		
		/* garantit que getFichierDescriptionMapping() retourne "ressources/Descriptions de fichier/Mapping/Descriptions en UTF-8/2014-07-19_Description_Mapping_Utf8.csv". */
		assertEquals("le fichier de la description DARWIN_CSV doit être situé sous le contexte sous 'ressources/Descriptions de fichier/Mapping/Descriptions en UTF-8/2014-07-19_Description_Mapping_Utf8.csv' : "
				, Paths.get("ressources/Descriptions de fichier/Mapping/Descriptions en UTF-8/2014-07-19_Description_Mapping_Utf8.csv")
					, fournirPathRelatifSousTargetClasses(fichierDescriptionMapping));
		
		/* garantit que le message d'erreur de configuration de la méthode est null. */
		assertNull("le message d'erreur de la configuration doit être null : "
				, messageIndividuelRapport);
		
		/* garantit que le rapport d'erreur de configuration de la classe est null. */
		assertNull("le rapport d'erreur de la configuration de la classe doit être null : "
				, rapportConfigurationCsv);

	} // Fin de testGetFichierDescriptionMapping().________________________ 


	
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
		= ConfigurationDescriptionsFichiersManager
			.retournerClassesSousTarget();
	
		final Path pathClasses = Paths.get(pathClassesString);
		
		final Path pathPFile = pFile.toPath();
		
		final Path pathRelatifPFile = pathClasses.relativize(pathPFile);
		
		return pathRelatifPFile;
		
	} // Fin de fournirPathRelatifSousTargetClasses(File pFile).___________
	
	
	
} // FIN DE LA CLASSE ConfigurationDescriptionsFichiersManagerTest.----------
