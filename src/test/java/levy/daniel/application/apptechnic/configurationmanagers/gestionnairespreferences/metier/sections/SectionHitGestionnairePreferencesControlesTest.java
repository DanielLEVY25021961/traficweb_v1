package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.sections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * CLASSE SectionHitGestionnairePreferencesControlesTest :<br/>
 * Test JUnit de la classe {@link SectionHitGestionnairePreferencesControles}.<br/>
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
 * @since 13 août 2019
 *
 */
public class SectionHitGestionnairePreferencesControlesTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	/**
	 * "doit comporter moins de 30 caractères".
	 */
	public static final String DOIT_COMPORTER_MOINS_30 
		= "doit comporter moins de 30 caractères";
	
	/**
	 * "la valeur doit valoir : '50'".
	 */
	public static final String VALEUR_DOIT_VALOIR_50 
		= "la valeur doit valoir : '50'";
	
	/**
	 * "message = ".
	 */
	public static final String MESSAGE 
		= "message = ";
	
	/**
	 * "le fichier properties doit avoir été généré sur disque : ".
	 */
	public static final String PROPERTIES_SUR_DISQUE 
		= "le fichier properties doit avoir été généré sur disque : ";
	
	/**
	 * "valeur = ".
	 */
	public static final String VALEUR 
		= "valeur = ";

	/**
	 * "3 CHIFFRES SVP".
	 */
	public static final String TROIS_CHIFFRES_SVP = "3 CHIFFRES SVP";

	/**
	 * "le message doit valoir : 'le numéro de département doit obligatoirement être renseigné'".
	 */
	public static final String MESSAGE_DOIT_VALOIR_DEPT_RENSEIGNE 
		= "le message doit valoir : 'le numéro de département doit obligatoirement être renseigné'";
	
	/**
	 * "le message doit valoir : 'modifiez la valeur !'".
	 */
	public static final String MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR 
		= "le message doit valoir : 'modifiez la valeur !'";
	
	/**
	 * "********** PREFERENCES EN DUR : ".
	 */
	public static final String PREFERENCES_EN_DUR 
		= "********** PREFERENCES EN DUR : ";
	
	/**
	 * "********** PREFERENCES APRES MODIFICATION DU MESSAGE : ".
	 */
	public static final String PREFERENCES_APRES_MODIFICATION_MESSAGE 
		= "********** PREFERENCES APRES MODIFICATION DU MESSAGE : ";
	
	/**
	 * "********** PREFERENCES APRES REMISE A L'ETAT INITIAL DU MESSAGE : ".
	 */
	public static final String PREFERENCES_APRES_REMISE_ETAT_INITIAL 
		= "********** PREFERENCES APRES REMISE A L'ETAT INITIAL DU MESSAGE : ";
	
	/**
	 * "modifiez la valeur !".
	 */
	public static final String MODIFIEZ_LA_VALEUR = "modifiez la valeur !";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitGestionnairePreferencesControlesTest.class);

	// *************************METHODES************************************/
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public SectionHitGestionnairePreferencesControlesTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * teste la méthode afficherPreferences().<br/>
	 * <ul>
	 * <li>garantit que afficherPreferences() crée le fichier 
	 * properties avec des valeurs en dur si il n'existait pas.</li>
	 * <li>garantit que afficherPreferences() afiche les preferences 
	 * stockées en dur dans la classe si le properties n'existait pas.</li>
	 * <li>garantit que afficherPreferences() lit les preferences 
	 * dans le fichier properties existant.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testAfficherPreferences() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testAfficherPreferences() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		String prefString = null;
		
		/* récupération des prefs avec un properties inexistant. */
		prefString = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefString);
		}
		
		/* garantit que afficherPreferences() crée le fichier 
		 * properties avec des valeurs en dur. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, SectionHitGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
		
		/* garantit que afficherPreferences() affiche les preferences 
		 * stockées en dur dans la classe si le properties n'existait pas.*/
		assertNotNull("les preferences affichées ne doivent pas être null : "
				, prefString);
		
		assertFalse("les preferences affichées ne doivent pas être vide : "
				, prefString.isEmpty());
		
		/* modification de certaines valeurs. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumDepartementRenseigne01("numDepartement obligatoire");
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumDepartementRegex02("REGEX obligatoire");
		
		/* récupération des prefs dans un properties existant. */
		prefString = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefString);
		}
		
		/* garantit que afficherPreferences() lit les preferences 
		 * dans le fichier properties existant. */
		assertTrue("le fichier properties doit exister sur disque : "
				, SectionHitGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
		
		assertEquals("la valeur doit être 'numDepartement obligatoire' : "
				, "numDepartement obligatoire"
				, SectionHitGestionnairePreferencesControles
					.getMessageSectionHitNumDepartementRenseigne01());

		assertEquals("la valeur doit être 'REGEX obligatoire' : "
				, "REGEX obligatoire"
				, SectionHitGestionnairePreferencesControles
					.getMessageSectionHitNumDepartementRegex02());
		
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumDepartementRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR);
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumDepartementRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR);
		
	} // Fin de testAfficherPreferences()._________________________________
	

	
	/**
	 * teste la méthode getPreferences().<br/>
	 * <ul>
	 * <li>garantit que getPreferences() crée le fichier properties 
	 * avec des valeurs en dur si il n'existait pas.</li>
	 * <li>garantit que getPreferences() retourne les preferences 
	 * stockées en dur dans la classe si le properties n'existait pas.</li>
	 * <li>garantit que getPreferences() lit les preferences 
	 * dans le fichier properties existant.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetPreferences() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetPreferences() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();

		Properties prefs = null;
		
		/* récupère preferences lorsque le properties est inexistant. */
		prefs = SectionHitGestionnairePreferencesControles.getPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefs);
			System.out.println();
		}
		
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefString);
			System.out.println();
		}
		
		/* garantit que getPreferences() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, SectionHitGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
		
		/* garantit que getPreferences() contient les preferences 
		 * stockées en dur dans la classe si le properties n'existait pas.*/
		assertNotNull("les preferences en dur ne doivent pas être null : "
				, prefs);
		
		assertFalse("les preferences en dur ne doivent pas être vide : "
				, prefs.isEmpty());
		
		/* garantit que getPreferences() lit les preferences 
		 * dans le properties existant. */
		assertEquals("la valeur doit être 'le numéro de département doit obligatoirement être renseigné' : "
				, SectionHitGestionnairePreferencesControles.MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR
				, SectionHitGestionnairePreferencesControles
					.getMessageSectionHitNumDepartementRenseigne01());

		assertEquals("le numéro de département doit comporter exactement 3 chiffres' : "
				, SectionHitGestionnairePreferencesControles.MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNumDepartementRegex02());

	} // Fin de testGetPreferences().______________________________________
	
	
	

	
	/* 1 - numDepartement. *******************************/
	/**
	 * teste la méthode getMessageSectionHitNumDepartementRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitNumDepartementRenseigne01() 
	 * crée le fichier properties avec des valeurs en dur 
	 * si il n'existait pas.</li>
	 * <li>garantit que le getter fonctionne bien.</li>
	 * <li>garantit que le setter fonctionne bien.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetMessageSectionHitNumDepartementRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitNumDepartementRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumDepartementRenseigne01();
		
		/* garantit que getMessageSectionHitNumDepartementRenseigne01() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, SectionHitGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
				
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_EN_DUR);
			System.out.println(prefString);
			System.out.println();
			System.out.println(MESSAGE + message);
			System.out.println();
		}
		
		/* garantit que le getter fonctionne bien. */
		assertEquals(MESSAGE_DOIT_VALOIR_DEPT_RENSEIGNE
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR
					, message);

		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumDepartementRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumDepartementRenseigne01();

		String prefStringModifiee = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_MODIFICATION_MESSAGE);
			System.out.println(prefStringModifiee);
			System.out.println();
			System.out.println(MESSAGE + messageModifie);
			System.out.println();
		}
		
		/* garantit que setMessageSectionHitNumDepartementRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumDepartementRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumDepartementRenseigne01();

		String prefStringModifiee2 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee2 = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_REMISE_ETAT_INITIAL);
			System.out.println(prefStringModifiee2);
			System.out.println();
			System.out.println(MESSAGE + messageModifie2);
			System.out.println();
		}
		
		/* garantit que setMessageSectionHitNumDepartementRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_DEPT_RENSEIGNE
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitNumDepartementRenseigne01()._______

	
	
	/**
	 * teste la méthode getMessageSectionHitNumDepartementRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitNumDepartementRegex02() 
	 * crée le fichier properties avec des valeurs en dur 
	 * si il n'existait pas.</li>
	 * <li>garantit que le getter fonctionne bien.</li>
	 * <li>garantit que le setter fonctionne bien.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetMessageSectionHitNumDepartementRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitNumDepartementRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumDepartementRegex02();
		
		/* garantit que getMessageSectionHitNumDepartementRegex02() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, SectionHitGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
				
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_EN_DUR);
			System.out.println(prefString);
			System.out.println();
			System.out.println(MESSAGE + message);
			System.out.println();
		}
				
		assertEquals("le message doit valoir : 'le numéro de département doit comporter exactement 3 chiffres...'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumDepartementRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumDepartementRegex02();
		
		String prefStringModifiee = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_MODIFICATION_MESSAGE);
			System.out.println(prefStringModifiee);
			System.out.println();
			System.out.println(MESSAGE + messageModifie);
			System.out.println();
		}
		
		/* garantit que setMessageSectionHitNumDepartementRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumDepartementRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumDepartementRegex02();

		String prefStringModifiee2 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee2 = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_REMISE_ETAT_INITIAL);
			System.out.println(prefStringModifiee2);
			System.out.println();
			System.out.println(MESSAGE + messageModifie2);
			System.out.println();
		}

		assertEquals("le message doit valoir : 'le numéro de département doit comporter exactement 3 chiffres...'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitNumDepartementRegex02().___________
	

	
	/* 2 - numSection. *******************************/
	/**
	 * teste la méthode getMessageSectionHitNumSectionRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitNumSectionRenseigne01() 
	 * crée le fichier properties avec des valeurs en dur 
	 * si il n'existait pas.</li>
	 * <li>garantit que le getter fonctionne bien.</li>
	 * <li>garantit que le setter fonctionne bien.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetMessageSectionHitNumSectionRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitNumSectionRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumSectionRenseigne01();
		
		/* garantit que getMessageSectionHitNumSectionRenseigne01() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, SectionHitGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
				
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_EN_DUR);
			System.out.println(prefString);
			System.out.println();
			System.out.println(MESSAGE + message);
			System.out.println();
		}
		
		/* garantit que le getter fonctionne bien. */
		assertEquals("le message doit valoir : 'le numéro de section de la section HIT doit être renseigné (obligatoire)'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMSECTION_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumSectionRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumSectionRenseigne01();

		String prefStringModifiee = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_MODIFICATION_MESSAGE);
			System.out.println(prefStringModifiee);
			System.out.println();
			System.out.println(MESSAGE + messageModifie);
			System.out.println();
		}
		
		/* garantit que setMessageSectionHitNumSectionRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumSectionRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NUMSECTION_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumSectionRenseigne01();

		String prefStringModifiee2 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee2 = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_REMISE_ETAT_INITIAL);
			System.out.println(prefStringModifiee2);
			System.out.println();
			System.out.println(MESSAGE + messageModifie2);
			System.out.println();
		}
		
		/* garantit que setMessageSectionHitNumSectionRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le numéro de département doit obligatoirement être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMSECTION_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitNumSectionRenseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitNumSectionRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitNumSectionRegex02() 
	 * crée le fichier properties avec des valeurs en dur 
	 * si il n'existait pas.</li>
	 * <li>garantit que le getter fonctionne bien.</li>
	 * <li>garantit que le setter fonctionne bien.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetMessageSectionHitNumSectionRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitNumSectionRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumSectionRegex02();
		
		/* garantit que getMessageSectionHitNumSectionRegex02() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, SectionHitGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
				
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_EN_DUR);
			System.out.println(prefString);
			System.out.println();
			System.out.println(MESSAGE + message);
			System.out.println();
		}
				
		assertEquals("le message doit valoir : 'le numéro de section doit comporter exactement 6 chiffres...'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMSECTION_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumSectionRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumSectionRegex02();

		String prefStringModifiee = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_MODIFICATION_MESSAGE);
			System.out.println(prefStringModifiee);
			System.out.println();
			System.out.println(MESSAGE + messageModifie);
			System.out.println();
		}
		
		/* garantit que setMessageSectionHitNumSectionRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumSectionRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NUMSECTION_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumSectionRegex02();

		String prefStringModifiee2 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee2 = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_REMISE_ETAT_INITIAL);
			System.out.println(prefStringModifiee2);
			System.out.println();
			System.out.println(MESSAGE + messageModifie2);
			System.out.println();
		}

				
		assertEquals("le message doit valoir : 'le numéro de section doit comporter exactement 6 chiffres...'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMSECTION_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitNumSectionRegex02()._______________
	

	
	/* 3 - sens. *******************************/
	/**
	 * teste la méthode getMessageSectionHitSensRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitSensRenseigne01() 
	 * crée le fichier properties avec des valeurs en dur 
	 * si il n'existait pas.</li>
	 * <li>garantit que le getter fonctionne bien.</li>
	 * <li>garantit que le setter fonctionne bien.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetMessageSectionHitSensRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitSensRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensRenseigne01();
		
		/* garantit que getMessageSectionHitSensRenseigne01() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, SectionHitGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
				
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_EN_DUR);
			System.out.println(prefString);
			System.out.println();
			System.out.println(MESSAGE + message);
			System.out.println();
		}
		
		/* garantit que le getter fonctionne bien. */
		assertEquals("le message doit valoir : 'le sens de la section HIT doit être renseigné (obligatoire)'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENS_RENSEIGNE_01_EN_DUR
					, message);

		
		// ***********************************************
		/* modification du message ***********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitSensRenseigne01();

		String prefStringModifiee = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_MODIFICATION_MESSAGE);
			System.out.println(prefStringModifiee);
			System.out.println();
			System.out.println(MESSAGE + messageModifie);
			System.out.println();
		}
		
		/* garantit que setMessageSectionHitSensRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// *****************************************************
		/* Remise à l'état initial *****************************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_SENS_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitSensRenseigne01();

		String prefStringModifiee2 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee2 = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_REMISE_ETAT_INITIAL);
			System.out.println(prefStringModifiee2);
			System.out.println();
			System.out.println(MESSAGE + messageModifie2);
			System.out.println();
		}
		
		/* garantit que setMessageSectionHitSensRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le sens doit obligatoirement être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENS_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitSensRenseigne01()._________________

	
	
	/**
	 * teste la méthode getMessageSectionHitSensRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitSensRegex02() 
	 * crée le fichier properties avec des valeurs en dur 
	 * si il n'existait pas.</li>
	 * <li>garantit que le getter fonctionne bien.</li>
	 * <li>garantit que le setter fonctionne bien.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetMessageSectionHitSensRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitSensRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensRegex02();
		
		/* garantit que getMessageSectionHitSensRegex02() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, SectionHitGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
				
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_EN_DUR);
			System.out.println(prefString);
			System.out.println();
			System.out.println(MESSAGE + message);
			System.out.println();
		}
				
		assertEquals("le message doit valoir : 'le sens doit comporter exactement 1 chiffre...'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENS_REGEX_02_EN_DUR
					, message);

		
		// ***************************************
		/* modification du message. ****************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensRegex02();

		String prefStringModifiee = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_MODIFICATION_MESSAGE);
			System.out.println(prefStringModifiee);
			System.out.println();
			System.out.println(MESSAGE + messageModifie);
			System.out.println();
		}

		/* garantit que setMessageSectionHitSensRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_SENS_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitSensRegex02();

		String prefStringModifiee2 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee2 = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_REMISE_ETAT_INITIAL);
			System.out.println(prefStringModifiee2);
			System.out.println();
			System.out.println(MESSAGE + messageModifie2);
			System.out.println();
		}

		assertEquals("le message doit valoir : 'le sens doit comporter exactement 1 chiffre...'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENS_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitSensRegex02()._____________________

	
	
	/**
	 * teste la méthode getMessageSectionHitSensNomenclature03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitSensNomenclature03() 
	 * crée le fichier properties avec des valeurs en dur 
	 * si il n'existait pas.</li>
	 * <li>garantit que le getter fonctionne bien.</li>
	 * <li>garantit que le setter fonctionne bien.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetMessageSectionHitSensNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitSensNomenclature03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensNomenclature03();
		
		/* garantit que getMessageSectionHitSensNomenclature03() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, SectionHitGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
				
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_EN_DUR);
			System.out.println(prefString);
			System.out.println();
			System.out.println(MESSAGE + message);
			System.out.println();
		}
				
		assertEquals("le message doit valoir : 'le sens de la section HIT (colonne [10] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]...'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENS_NOMENCLATURE_03_EN_DUR
					, message);

		
		// ***************************************
		/* modification du message. ****************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensNomenclature03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensNomenclature03();

		String prefStringModifiee = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_MODIFICATION_MESSAGE);
			System.out.println(prefStringModifiee);
			System.out.println();
			System.out.println(MESSAGE + messageModifie);
			System.out.println();
		}

		/* garantit que setMessageSectionHitSensRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensNomenclature03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_SENS_NOMENCLATURE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitSensNomenclature03();

		String prefStringModifiee2 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee2 = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_REMISE_ETAT_INITIAL);
			System.out.println(prefStringModifiee2);
			System.out.println();
			System.out.println(MESSAGE + messageModifie2);
			System.out.println();
		}

		assertEquals("le message doit valoir : 'le sens de la section HIT (colonne [10] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]...'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENS_NOMENCLATURE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitSensNomenclature03().______________
	

	
	/* 4 - nature. *******************************/
	/**
	 * teste la méthode getMessageSectionHitNatureRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitNatureRenseigne01() 
	 * crée le fichier properties avec des valeurs en dur 
	 * si il n'existait pas.</li>
	 * <li>garantit que le getter fonctionne bien.</li>
	 * <li>garantit que le setter fonctionne bien.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetMessageSectionHitNatureRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitNatureRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNatureRenseigne01();
		
		/* garantit que getMessageSectionHitNatureRenseigne01() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, SectionHitGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
				
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_EN_DUR);
			System.out.println(prefString);
			System.out.println();
			System.out.println(MESSAGE + message);
			System.out.println();
		}
		
		/* garantit que le getter fonctionne bien. */
		assertEquals("le message doit valoir : 'la nature de la section HIT doit être renseignée (obligatoire)'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NATURE_RENSEIGNE_01_EN_DUR
					, message);

		
		// ***********************************************
		/* modification du message ***********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNatureRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNatureRenseigne01();

		String prefStringModifiee = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_MODIFICATION_MESSAGE);
			System.out.println(prefStringModifiee);
			System.out.println();
			System.out.println(MESSAGE + messageModifie);
			System.out.println();
		}
		
		/* garantit que setMessageSectionHitNatureRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// *****************************************************
		/* Remise à l'état initial *****************************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNatureRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NATURE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNatureRenseigne01();

		String prefStringModifiee2 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee2 = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_REMISE_ETAT_INITIAL);
			System.out.println(prefStringModifiee2);
			System.out.println();
			System.out.println(MESSAGE + messageModifie2);
			System.out.println();
		}
		
		/* garantit que setMessageSectionHitNatureRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la nature doit obligatoirement être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NATURE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitNatureRenseigne01()._________________

	
	
	/**
	 * teste la méthode getMessageSectionHitNatureRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitNatureRegex02() 
	 * crée le fichier properties avec des valeurs en dur 
	 * si il n'existait pas.</li>
	 * <li>garantit que le getter fonctionne bien.</li>
	 * <li>garantit que le setter fonctionne bien.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetMessageSectionHitNatureRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitNatureRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNatureRegex02();
		
		/* garantit que getMessageSectionHitNatureRegex02() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, SectionHitGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
				
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_EN_DUR);
			System.out.println(prefString);
			System.out.println();
			System.out.println(MESSAGE + message);
			System.out.println();
		}
				
		assertEquals("le message doit valoir : 'la nature doit comporter exactement 1 chiffre...'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NATURE_REGEX_02_EN_DUR
					, message);

		
		// ***************************************
		/* modification du message. ****************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNatureRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNatureRegex02();

		String prefStringModifiee = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_MODIFICATION_MESSAGE);
			System.out.println(prefStringModifiee);
			System.out.println();
			System.out.println(MESSAGE + messageModifie);
			System.out.println();
		}

		/* garantit que setMessageSectionHitNatureRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNatureRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NATURE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNatureRegex02();

		String prefStringModifiee2 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee2 = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_REMISE_ETAT_INITIAL);
			System.out.println(prefStringModifiee2);
			System.out.println();
			System.out.println(MESSAGE + messageModifie2);
			System.out.println();
		}

		assertEquals("le message doit valoir : 'la nature doit comporter exactement 1 chiffre...'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NATURE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitNatureRegex02()._____________________

	
	
	/**
	 * teste la méthode getMessageSectionHitNatureNomenclature03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitNatureNomenclature03() 
	 * crée le fichier properties avec des valeurs en dur 
	 * si il n'existait pas.</li>
	 * <li>garantit que le getter fonctionne bien.</li>
	 * <li>garantit que le setter fonctionne bien.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetMessageSectionHitNatureNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitNatureNomenclature03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNatureNomenclature03();
		
		/* garantit que getMessageSectionHitNatureNomenclature03() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, SectionHitGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
				
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_EN_DUR);
			System.out.println(prefString);
			System.out.println();
			System.out.println(MESSAGE + message);
			System.out.println();
		}
				
		assertEquals("le message doit valoir : 'la nature de la section HIT (colonne [11] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]...'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NATURE_NOMENCLATURE_03_EN_DUR
					, message);

		
		// ***************************************
		/* modification du message. ****************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNatureNomenclature03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNatureNomenclature03();

		String prefStringModifiee = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_MODIFICATION_MESSAGE);
			System.out.println(prefStringModifiee);
			System.out.println();
			System.out.println(MESSAGE + messageModifie);
			System.out.println();
		}

		/* garantit que setMessageSectionHitNatureRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNatureNomenclature03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NATURE_NOMENCLATURE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNatureNomenclature03();

		String prefStringModifiee2 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee2 = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_REMISE_ETAT_INITIAL);
			System.out.println(prefStringModifiee2);
			System.out.println();
			System.out.println(MESSAGE + messageModifie2);
			System.out.println();
		}

		assertEquals("le message doit valoir : 'la nature de la section HIT (colonne [11] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]...'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NATURE_NOMENCLATURE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitNatureNomenclature03().______________
	

	
	/* 5 - classe. *******************************/
	/**
	 * teste la méthode getMessageSectionHitClasseRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitClasseRenseigne01() 
	 * crée le fichier properties avec des valeurs en dur 
	 * si il n'existait pas.</li>
	 * <li>garantit que le getter fonctionne bien.</li>
	 * <li>garantit que le setter fonctionne bien.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetMessageSectionHitClasseRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitClasseRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClasseRenseigne01();
		
		/* garantit que getMessageSectionHitClasseRenseigne01() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, SectionHitGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
				
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_EN_DUR);
			System.out.println(prefString);
			System.out.println();
			System.out.println(MESSAGE + message);
			System.out.println();
		}
		
		/* garantit que le getter fonctionne bien. */
		assertEquals("le message doit valoir : 'la classe de la section HIT doit être renseignée (obligatoire)'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClasseRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitClasseRenseigne01();

		String prefStringModifiee = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_MODIFICATION_MESSAGE);
			System.out.println(prefStringModifiee);
			System.out.println();
			System.out.println(MESSAGE + messageModifie);
			System.out.println();
		}
		
		/* garantit que setMessageSectionHitClasseRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClasseRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_CLASSE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitClasseRenseigne01();

		String prefStringModifiee2 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee2 = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_REMISE_ETAT_INITIAL);
			System.out.println(prefStringModifiee2);
			System.out.println();
			System.out.println(MESSAGE + messageModifie2);
			System.out.println();
		}
		
		/* garantit que setMessageSectionHitClasseRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la classe de la section HIT doit être renseignée (obligatoire)'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitClasseRenseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitClasseRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitClasseRegex02() 
	 * crée le fichier properties avec des valeurs en dur 
	 * si il n'existait pas.</li>
	 * <li>garantit que le getter fonctionne bien.</li>
	 * <li>garantit que le setter fonctionne bien.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetMessageSectionHitClasseRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitClasseRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClasseRegex02();
		
		/* garantit que getMessageSectionHitClasseRegex02() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, SectionHitGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
				
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_EN_DUR);
			System.out.println(prefString);
			System.out.println();
			System.out.println(MESSAGE + message);
			System.out.println();
		}
				
		assertEquals("le message doit valoir : 'la classe doit comporter exactement 2 chiffres ('00')...'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClasseRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClasseRegex02();

		String prefStringModifiee = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_MODIFICATION_MESSAGE);
			System.out.println(prefStringModifiee);
			System.out.println();
			System.out.println(MESSAGE + messageModifie);
			System.out.println();
		}
		
		/* garantit que setMessageSectionHitClasseRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClasseRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_CLASSE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitClasseRegex02();

		String prefStringModifiee2 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee2 = SectionHitGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(PREFERENCES_APRES_REMISE_ETAT_INITIAL);
			System.out.println(prefStringModifiee2);
			System.out.println();
			System.out.println(MESSAGE + messageModifie2);
			System.out.println();
		}

				
		assertEquals("le message doit valoir : 'la classe doit comporter exactement 2 chiffres ('00')...'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitClasseRegex02()._______________


		
	/**
	 * détruit le fichier properties si il existe.<br/>
	 *
	 * @throws Exception
	 */
	private void detruireFichierProperties() throws Exception {
		
		/* détruit le fichier properties si il existe. */
		Files.deleteIfExists(
				SectionHitGestionnairePreferencesControles
					.getPathAbsoluPreferencesProperties());
		
		assertFalse("le fichier properties ne doit plus exister : "
				, SectionHitGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
		
	} // Fin de detruireFichierProperties()._______________________________

	
	
	/**
	 * copie un fichier properties et le renomme 
	 * en properties.old si il existe.<br/>
	 *
	 * @throws Exception
	 */
	private static void copierFichierPropertiesOld() throws Exception {
		
		/* path du fichier properties existant. */
		final Path propPath = SectionHitGestionnairePreferencesControles
				.getPathAbsoluPreferencesProperties();
		
		final File propFile = propPath.toFile();
		
		if (propFile.exists()) {
			
			final String nomFichier = propFile.getName();
			
			/* Path du fichier properties old à créer. */
			final Path propOldPathParent = propPath.getParent();
			
			final String nomFichierOld = nomFichier + ".old";
			
			Path propOldPath = null;
			
			if (propOldPathParent != null) {
				propOldPath = propOldPathParent.resolve(nomFichierOld);
			}
						
			// COPIE AVEC REMPLACEMENT ************************			
			if (propOldPath != null) {
				
				Files.copy(
						propPath
							, propOldPath
								, StandardCopyOption.REPLACE_EXISTING);
				assertTrue(
						"le properties old doit avoir été créé : "
							, propOldPath.toFile().exists());
			}
			
		}

	} // Fin de copierFichierPropertiesOld().______________________________
		

	
	/**
	 * recrée le fichier properties initial à partir 
	 * de la copie de sécurité (.old).<br/>
	 * <ul>
	 * <li>détruit la copie de sécurité si elle existait.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void recreerFichierPropertiesInitial() throws Exception {
		
		/* path du fichier properties. */
		final Path propPath = SectionHitGestionnairePreferencesControles
				.getPathAbsoluPreferencesProperties();
		
		final File propFile = propPath.toFile();
		final String nomFichier = propFile.getName();
		
		/* Path du fichier properties old. */
		final Path propOldPathParent = propPath.getParent();		
		final String nomFichierOld = nomFichier + ".old";
		Path propOldPath = null;
		
		if (propOldPathParent != null) {
			
			propOldPath = propOldPathParent.resolve(nomFichierOld);
			
			
			
			final File old = propOldPath.toFile();
			
			if (old.exists()) {
						
				// COPIE AVEC REMPLACEMENT ************************
				Files.copy(
						propOldPath
							, propPath
								, StandardCopyOption.REPLACE_EXISTING);
				
				assertTrue("le properties original doit avoir été recréé : "
							, propPath.toFile().exists());
				
				/* détruit la copie de sécurité si elle existait. */
				Files.deleteIfExists(propOldPath);
			}

		}
		
	} // Fin de recreerFichierPropertiesInitial()._________________________
	
	
	
	/**
	 * fait une copie de sécurité du fichier properties AVANT les tests.<br/>
	 * <ul>
	 * <li>exécuté avant tous les tests de la classe.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		copierFichierPropertiesOld();
	} // Fin de beforeClass()._____________________________________________
		
	
		
	/**
	 * rétablit le properties initial APRES les tests.<br/>
	 * <ul>
	 * <li>exécuté après tous les tests de la classe 
	 * (à la sortie de la classe).</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@AfterClass
	public static void afterClass() throws Exception {
		recreerFichierPropertiesInitial();
	} // Fin de beforeClass()._____________________________________________
		
	
	
} // FIN DE LA CLASSE SectionHitGestionnairePreferencesControlesTest.--------