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
	

	
	/* 6 - anneeTraitement. ************************/
	/**
	 * teste la méthode getMessageSectionHitAnneeTraitementRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitAnneeTraitementRenseigne01() 
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
	public void testGetMessageSectionHitAnneeTraitementRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitAnneeTraitementRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAnneeTraitementRenseigne01();
		
		/* garantit que getMessageSectionHitAnneeTraitementRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'l'année de traitement de la section HIT (colonnes [14-15] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAnneeTraitementRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAnneeTraitementRenseigne01();

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
		
		/* garantit que setMessageSectionHitAnneeTraitementRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAnneeTraitementRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAnneeTraitementRenseigne01();

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
		
		/* garantit que setMessageSectionHitAnneeTraitementRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'l'année de traitement de la section HIT (colonnes [14-15] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitAnneeTraitementRenseigne01().______

	
	
	/**
	 * teste la méthode getMessageSectionHitAnneeTraitementRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitAnneeTraitementRegex02() 
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
	public void testGetMessageSectionHitAnneeTraitementRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitAnneeTraitementRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAnneeTraitementRegex02();
		
		/* garantit que getMessageSectionHitAnneeTraitementRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'l'année de traitement de la section HIT (colonnes [14-15] du HIT) doit comporter exactement 2 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ANNEETRAITEMENT_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAnneeTraitementRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAnneeTraitementRegex02();

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
		
		/* garantit que setMessageSectionHitAnneeTraitementRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAnneeTraitementRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ANNEETRAITEMENT_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAnneeTraitementRegex02();

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

				
		assertEquals("le message doit valoir : 'l'année de traitement de la section HIT (colonnes [14-15] du HIT) doit comporter exactement 2 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ANNEETRAITEMENT_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitAnneeTraitementRegex02()._______________
	

	
	/* 7 - zoneLibre1. ************************/
	/**
	 * teste la méthode getMessageSectionHitZoneLibre1Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitZoneLibre1Renseigne01() 
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
	public void testGetMessageSectionHitZoneLibre1Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitZoneLibre1Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitZoneLibre1Renseigne01();
		
		/* garantit que getMessageSectionHitZoneLibre1Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la zone libre de la section HIT (colonnes [16] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitZoneLibre1Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitZoneLibre1Renseigne01();

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
		
		/* garantit que setMessageSectionHitZoneLibre1Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitZoneLibre1Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitZoneLibre1Renseigne01();

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
		
		/* garantit que setMessageSectionHitZoneLibre1Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la zone libre de la section HIT (colonnes [16] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitZoneLibre1Renseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitZoneLibre1Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitZoneLibre1Regex02() 
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
	public void testGetMessageSectionHitZoneLibre1Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitZoneLibre1Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitZoneLibre1Regex02();
		
		/* garantit que getMessageSectionHitZoneLibre1Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la zone libre de la section HIT (colonnes [16] du HIT) doit comporter exactement 1 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ZONELIBRE1_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitZoneLibre1Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitZoneLibre1Regex02();

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
		
		/* garantit que setMessageSectionHitZoneLibre1Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitZoneLibre1Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ZONELIBRE1_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitZoneLibre1Regex02();

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

				
		assertEquals("le message doit valoir : 'la zone libre de la section HIT (colonnes [16] du HIT) doit comporter exactement 1 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ZONELIBRE1_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitZoneLibre1Regex02()._______________
	

	
	/* 8 - numRoute. ************************/
	/**
	 * teste la méthode getMessageSectionHitNumRouteRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitNumRouteRenseigne01() 
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
	public void testGetMessageSectionHitNumRouteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitNumRouteRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumRouteRenseigne01();
		
		/* garantit que getMessageSectionHitNumRouteRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le numéro de la route de la section HIT (colonnes [17-20] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMROUTE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumRouteRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumRouteRenseigne01();

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
		
		/* garantit que setMessageSectionHitNumRouteRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumRouteRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NUMROUTE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumRouteRenseigne01();

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
		
		/* garantit que setMessageSectionHitNumRouteRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le numéro de la route de la section HIT (colonnes [17-20] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMROUTE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitNumRouteRenseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitNumRouteRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitNumRouteRegex02() 
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
	public void testGetMessageSectionHitNumRouteRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitNumRouteRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumRouteRegex02();
		
		/* garantit que getMessageSectionHitNumRouteRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le numéro de la route de la section HIT (colonnes [17-20] du HIT) doit comporter exactement 4 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMROUTE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumRouteRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumRouteRegex02();

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
		
		/* garantit que setMessageSectionHitNumRouteRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumRouteRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NUMROUTE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumRouteRegex02();

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

				
		assertEquals("le message doit valoir : 'le numéro de la route de la section HIT (colonnes [17-20] du HIT) doit comporter exactement 4 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMROUTE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitNumRouteRegex02()._______________
	

	
	/* 9 - indiceNumRoute. ************************/
	/**
	 * teste la méthode getMessageSectionHitIndiceNumRouteRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitIndiceNumRouteRenseigne01() 
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
	public void testGetMessageSectionHitIndiceNumRouteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitIndiceNumRouteRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitIndiceNumRouteRenseigne01();
		
		/* garantit que getMessageSectionHitIndiceNumRouteRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'l'indice numérique de la route de la section HIT (colonnes [21] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitIndiceNumRouteRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitIndiceNumRouteRenseigne01();

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
		
		/* garantit que setMessageSectionHitIndiceNumRouteRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitIndiceNumRouteRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitIndiceNumRouteRenseigne01();

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
		
		/* garantit que setMessageSectionHitIndiceNumRouteRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'l'indice numérique de la route de la section HIT (colonnes [21] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitIndiceNumRouteRenseigne01()._______

	
	
	/**
	 * teste la méthode getMessageSectionHitIndiceNumRouteRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitIndiceNumRouteRegex02() 
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
	public void testGetMessageSectionHitIndiceNumRouteRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitIndiceNumRouteRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitIndiceNumRouteRegex02();
		
		/* garantit que getMessageSectionHitIndiceNumRouteRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'l'indice numérique de la route de la section HIT (colonnes [21] du HIT) doit comporter exactement 1 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_INDICENUMROUTE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitIndiceNumRouteRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitIndiceNumRouteRegex02();

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
		
		/* garantit que setMessageSectionHitIndiceNumRouteRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitIndiceNumRouteRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_INDICENUMROUTE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitIndiceNumRouteRegex02();

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

				
		assertEquals("le message doit valoir : 'l'indice numérique de la route de la section HIT (colonnes [21] du HIT) doit comporter exactement 1 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_INDICENUMROUTE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitIndiceNumRouteRegex02().___________
	

	
	/* 10 - indiceLettreRoute. ************************/
	/**
	 * teste la méthode getMessageSectionHitIndiceLettreRouteRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitIndiceLettreRouteRenseigne01() 
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
	public void testGetMessageSectionHitIndiceLettreRouteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitIndiceLettreRouteRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitIndiceLettreRouteRenseigne01();
		
		/* garantit que getMessageSectionHitIndiceLettreRouteRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'l'indice lettre de la route de la section HIT (colonnes [22] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitIndiceLettreRouteRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitIndiceLettreRouteRenseigne01();

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
		
		/* garantit que setMessageSectionHitIndiceLettreRouteRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitIndiceLettreRouteRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitIndiceLettreRouteRenseigne01();

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
		
		/* garantit que setMessageSectionHitIndiceLettreRouteRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'l'indice lettre de la route de la section HIT (colonnes [22] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitIndiceLettreRouteRenseigne01().____

	
	
	/**
	 * teste la méthode getMessageSectionHitIndiceLettreRouteRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitIndiceLettreRouteRegex02() 
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
	public void testGetMessageSectionHitIndiceLettreRouteRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitIndiceLettreRouteRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitIndiceLettreRouteRegex02();
		
		/* garantit que getMessageSectionHitIndiceLettreRouteRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'l'indice lettre de la route de la section HIT (colonnes [22] du HIT) doit comporter exactement 1 lettre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_INDICELETTREROUTE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitIndiceLettreRouteRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitIndiceLettreRouteRegex02();

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
		
		/* garantit que setMessageSectionHitIndiceLettreRouteRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitIndiceLettreRouteRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_INDICELETTREROUTE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitIndiceLettreRouteRegex02();

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

				
		assertEquals("le message doit valoir : 'l'indice lettre de la route de la section HIT (colonnes [22] du HIT) doit comporter exactement 1 lettre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_INDICELETTREROUTE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitIndiceLettreRouteRegex02().________
	

	
	/* 11 - categorieAdminRoute. ************************/
	/**
	 * teste la méthode getMessageSectionHitCategorieAdminRouteRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitCategorieAdminRouteRenseigne01() 
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
	public void testGetMessageSectionHitCategorieAdminRouteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitCategorieAdminRouteRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitCategorieAdminRouteRenseigne01();
		
		/* garantit que getMessageSectionHitCategorieAdminRouteRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la catégorie administrative de la route de la section HIT (colonnes [23] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitCategorieAdminRouteRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitCategorieAdminRouteRenseigne01();

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
		
		/* garantit que setMessageSectionHitCategorieAdminRouteRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitCategorieAdminRouteRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitCategorieAdminRouteRenseigne01();

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
		
		/* garantit que setMessageSectionHitCategorieAdminRouteRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la catégorie administrative de la route de la section HIT (colonnes [23] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitCategorieAdminRouteRenseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitCategorieAdminRouteRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitCategorieAdminRouteRegex02() 
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
	public void testGetMessageSectionHitCategorieAdminRouteRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitCategorieAdminRouteRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitCategorieAdminRouteRegex02();
		
		/* garantit que getMessageSectionHitCategorieAdminRouteRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la catégorie administrative de la route de la section HIT (colonnes [23] du HIT) doit comporter exactement 1 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitCategorieAdminRouteRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitCategorieAdminRouteRegex02();

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
		
		/* garantit que setMessageSectionHitCategorieAdminRouteRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitCategorieAdminRouteRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitCategorieAdminRouteRegex02();

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

				
		assertEquals("le message doit valoir : 'la catégorie administrative de la route de la section HIT (colonnes [23] du HIT) doit comporter exactement 1 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitCategorieAdminRouteRegex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitCategorieAdminRouteNomenclature03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitCategorieAdminRouteNomenclature03() 
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
	public void testGetMessageSectionHitCategorieAdminRouteNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitCategorieAdminRouteNomenclature03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitCategorieAdminRouteNomenclature03();
		
		/* garantit que getMessageSectionHitCategorieAdminRouteNomenclature03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la catégorie administrative de la route de la section HIT (colonne [23] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03_EN_DUR
					, message);

		
		// ***************************************
		/* modification du message. ****************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitCategorieAdminRouteNomenclature03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitCategorieAdminRouteNomenclature03();

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

		/* garantit que setMessageSectionHitCategorieAdminRouteRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitCategorieAdminRouteNomenclature03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitCategorieAdminRouteNomenclature03();

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

		assertEquals("le message doit valoir : 'la catégorie administrative de la route de la section HIT (colonne [23] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitCategorieAdminRouteNomenclature03().______________
	

	
	/* 12 - typeComptage. ************************/
	/**
	 * teste la méthode getMessageSectionHitTypeComptageRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitTypeComptageRenseigne01() 
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
	public void testGetMessageSectionHitTypeComptageRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitTypeComptageRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitTypeComptageRenseigne01();
		
		/* garantit que getMessageSectionHitTypeComptageRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le type de comptage de la section HIT (colonne [24] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeComptageRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitTypeComptageRenseigne01();

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
		
		/* garantit que setMessageSectionHitTypeComptageRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeComptageRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitTypeComptageRenseigne01();

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
		
		/* garantit que setMessageSectionHitTypeComptageRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le type de comptage de la section HIT (colonne [24] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitTypeComptageRenseigne01()._________

	
	
	/**
	 * teste la méthode getMessageSectionHitTypeComptageRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitTypeComptageRegex02() 
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
	public void testGetMessageSectionHitTypeComptageRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitTypeComptageRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitTypeComptageRegex02();
		
		/* garantit que getMessageSectionHitTypeComptageRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le type de comptage de la section HIT (colonne [24] du HIT) doit comporter exactement 1 chiffre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPECOMPTAGE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeComptageRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitTypeComptageRegex02();

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
		
		/* garantit que setMessageSectionHitTypeComptageRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeComptageRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_TYPECOMPTAGE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitTypeComptageRegex02();

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

				
		assertEquals("le message doit valoir : 'le type de comptage de la section HIT (colonne [24] du HIT) doit comporter exactement 1 chiffre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPECOMPTAGE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitTypeComptageRegex02()._____________

	
	
	/**
	 * teste la méthode getMessageSectionHitTypeComptageNomenclature03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitTypeComptageNomenclature03() 
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
	public void testGetMessageSectionHitTypeComptageNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitTypeComptageNomenclature03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitTypeComptageNomenclature03();
		
		/* garantit que getMessageSectionHitTypeComptageNomenclature03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le type de comptage de la section HIT (colonne [24] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03_EN_DUR
					, message);

		
		// ***************************************
		/* modification du message. ****************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeComptageNomenclature03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitTypeComptageNomenclature03();

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

		/* garantit que setMessageSectionHitTypeComptageRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeComptageNomenclature03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitTypeComptageNomenclature03();

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

		assertEquals("le message doit valoir : 'le type de comptage de la section HIT (colonne [24] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitTypeComptageNomenclature03().______
	

	
	/* 13 - classementRoute. ************************/
	/**
	 * teste la méthode getMessageSectionHitClassementRouteRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitClassementRouteRenseigne01() 
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
	public void testGetMessageSectionHitClassementRouteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitClassementRouteRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClassementRouteRenseigne01();
		
		/* garantit que getMessageSectionHitClassementRouteRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le classement de la route de la section HIT (colonne [25] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClassementRouteRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitClassementRouteRenseigne01();

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
		
		/* garantit que setMessageSectionHitClassementRouteRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClassementRouteRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitClassementRouteRenseigne01();

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
		
		/* garantit que setMessageSectionHitClassementRouteRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le classement de la route de la section HIT (colonne [25] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitClassementRouteRenseigne01().______

	
	
	/**
	 * teste la méthode getMessageSectionHitClassementRouteRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitClassementRouteRegex02() 
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
	public void testGetMessageSectionHitClassementRouteRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitClassementRouteRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClassementRouteRegex02();
		
		/* garantit que getMessageSectionHitClassementRouteRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le classement de la route de la section HIT (colonne [25] du HIT) doit comporter exactement 1 chiffre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSEMENTROUTE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClassementRouteRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClassementRouteRegex02();

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
		
		/* garantit que setMessageSectionHitClassementRouteRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClassementRouteRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_CLASSEMENTROUTE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitClassementRouteRegex02();

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

				
		assertEquals("le message doit valoir : 'le classement de la route de la section HIT (colonne [25] du HIT) doit comporter exactement 1 chiffre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSEMENTROUTE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitClassementRouteRegex02().__________

	
	
	/**
	 * teste la méthode getMessageSectionHitClassementRouteNomenclature03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitClassementRouteNomenclature03() 
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
	public void testGetMessageSectionHitClassementRouteNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitClassementRouteNomenclature03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClassementRouteNomenclature03();
		
		/* garantit que getMessageSectionHitClassementRouteNomenclature03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le classement de la route de la section HIT (colonne [25] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03_EN_DUR
					, message);

		
		// ***************************************
		/* modification du message. ****************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClassementRouteNomenclature03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClassementRouteNomenclature03();

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

		/* garantit que setMessageSectionHitClassementRouteRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClassementRouteNomenclature03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitClassementRouteNomenclature03();

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

		assertEquals("le message doit valoir : 'le classement de la route de la section HIT (colonne [25] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitClassementRouteNomenclature03().___
	

	
	/* 14 - classeLargeurChausseeU. ************************/
	/**
	 * teste la méthode getMessageSectionHitClasseLargeurChausseeURenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitClasseLargeurChausseeURenseigne01() 
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
	public void testGetMessageSectionHitClasseLargeurChausseeURenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitClasseLargeurChausseeURenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClasseLargeurChausseeURenseigne01();
		
		/* garantit que getMessageSectionHitClasseLargeurChausseeURenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClasseLargeurChausseeURenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitClasseLargeurChausseeURenseigne01();

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
		
		/* garantit que setMessageSectionHitClasseLargeurChausseeURenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClasseLargeurChausseeURenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitClasseLargeurChausseeURenseigne01();

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
		
		/* garantit que setMessageSectionHitClasseLargeurChausseeURenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitClasseLargeurChausseeURenseigne01().

	
	
	/**
	 * teste la méthode getMessageSectionHitClasseLargeurChausseeURegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitClasseLargeurChausseeURegex02() 
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
	public void testGetMessageSectionHitClasseLargeurChausseeURegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitClasseLargeurChausseeURegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClasseLargeurChausseeURegex02();
		
		/* garantit que getMessageSectionHitClasseLargeurChausseeURegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit comporter exactement 1 chiffre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClasseLargeurChausseeURegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClasseLargeurChausseeURegex02();

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
		
		/* garantit que setMessageSectionHitClasseLargeurChausseeURegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClasseLargeurChausseeURegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitClasseLargeurChausseeURegex02();

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

				
		assertEquals("le message doit valoir : 'la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit comporter exactement 1 chiffre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitClasseLargeurChausseeURegex02().___

	
	
	/**
	 * teste la méthode getMessageSectionHitClasseLargeurChausseeUNomenclature03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitClasseLargeurChausseeUNomenclature03() 
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
	public void testGetMessageSectionHitClasseLargeurChausseeUNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitClasseLargeurChausseeUNomenclature03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClasseLargeurChausseeUNomenclature03();
		
		/* garantit que getMessageSectionHitClasseLargeurChausseeUNomenclature03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7, 8]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03_EN_DUR
					, message);

		
		// ***************************************
		/* modification du message. ****************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClasseLargeurChausseeUNomenclature03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClasseLargeurChausseeUNomenclature03();

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

		/* garantit que setMessageSectionHitClasseLargeurChausseeURenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClasseLargeurChausseeUNomenclature03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitClasseLargeurChausseeUNomenclature03();

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

		assertEquals("le message doit valoir : 'la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7, 8]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitClasseLargeurChausseeUNomenclature03().
	

	
	/* 15 - classeLargeurChausseesS. ************************/
	/**
	 * teste la méthode getMessageSectionHitClasseLargeurChausseesSRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitClasseLargeurChausseesSRenseigne01() 
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
	public void testGetMessageSectionHitClasseLargeurChausseesSRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitClasseLargeurChausseesSRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClasseLargeurChausseesSRenseigne01();
		
		/* garantit que getMessageSectionHitClasseLargeurChausseesSRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClasseLargeurChausseesSRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitClasseLargeurChausseesSRenseigne01();

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
		
		/* garantit que setMessageSectionHitClasseLargeurChausseesSRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClasseLargeurChausseesSRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitClasseLargeurChausseesSRenseigne01();

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
		
		/* garantit que setMessageSectionHitClasseLargeurChausseesSRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitClasseLargeurChausseesSRenseigne01().

	
	
	/**
	 * teste la méthode getMessageSectionHitClasseLargeurChausseesSRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitClasseLargeurChausseesSRegex02() 
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
	public void testGetMessageSectionHitClasseLargeurChausseesSRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitClasseLargeurChausseesSRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClasseLargeurChausseesSRegex02();
		
		/* garantit que getMessageSectionHitClasseLargeurChausseesSRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit comporter exactement 1 chiffre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClasseLargeurChausseesSRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClasseLargeurChausseesSRegex02();

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
		
		/* garantit que setMessageSectionHitClasseLargeurChausseesSRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClasseLargeurChausseesSRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitClasseLargeurChausseesSRegex02();

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

				
		assertEquals("le message doit valoir : 'la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit comporter exactement 1 chiffre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitClasseLargeurChausseesSRegex02().__

	
	
	/**
	 * teste la méthode getMessageSectionHitClasseLargeurChausseesSNomenclature03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitClasseLargeurChausseesSNomenclature03() 
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
	public void testGetMessageSectionHitClasseLargeurChausseesSNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitClasseLargeurChausseesSNomenclature03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClasseLargeurChausseesSNomenclature03();
		
		/* garantit que getMessageSectionHitClasseLargeurChausseesSNomenclature03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03_EN_DUR
					, message);

		
		// ***************************************
		/* modification du message. ****************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClasseLargeurChausseesSNomenclature03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClasseLargeurChausseesSNomenclature03();

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

		/* garantit que setMessageSectionHitClasseLargeurChausseesSRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitClasseLargeurChausseesSNomenclature03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitClasseLargeurChausseesSNomenclature03();

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

		assertEquals("le message doit valoir : 'la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitClasseLargeurChausseesSNomenclature03().
	

	
	/* 16 - typeReseau. ************************/
	/**
	 * teste la méthode getMessageSectionHitTypeReseauRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitTypeReseauRenseigne01() 
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
	public void testGetMessageSectionHitTypeReseauRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitTypeReseauRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitTypeReseauRenseigne01();
		
		/* garantit que getMessageSectionHitTypeReseauRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le type de réseau de la section HIT (colonne [28] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPERESEAU_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeReseauRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitTypeReseauRenseigne01();

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
		
		/* garantit que setMessageSectionHitTypeReseauRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeReseauRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_TYPERESEAU_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitTypeReseauRenseigne01();

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
		
		/* garantit que setMessageSectionHitTypeReseauRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le type de réseau de la section HIT (colonne [28] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPERESEAU_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitTypeReseauRenseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitTypeReseauRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitTypeReseauRegex02() 
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
	public void testGetMessageSectionHitTypeReseauRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitTypeReseauRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitTypeReseauRegex02();
		
		/* garantit que getMessageSectionHitTypeReseauRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le type de réseau de la section HIT (colonne [28] du HIT) doit comporter exactement 1 chiffre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPERESEAU_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeReseauRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitTypeReseauRegex02();

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
		
		/* garantit que setMessageSectionHitTypeReseauRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeReseauRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_TYPERESEAU_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitTypeReseauRegex02();

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

				
		assertEquals("le message doit valoir : 'le type de réseau de la section HIT (colonne [28] du HIT) doit comporter exactement 1 chiffre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPERESEAU_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitTypeReseauRegex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitTypeReseauNomenclature03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitTypeReseauNomenclature03() 
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
	public void testGetMessageSectionHitTypeReseauNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitTypeReseauNomenclature03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitTypeReseauNomenclature03();
		
		/* garantit que getMessageSectionHitTypeReseauNomenclature03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le type de réseau de la section HIT (colonnes [28] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03_EN_DUR
					, message);

		
		// ***************************************
		/* modification du message. ****************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeReseauNomenclature03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitTypeReseauNomenclature03();

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

		/* garantit que setMessageSectionHitTypeReseauRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeReseauNomenclature03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitTypeReseauNomenclature03();

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

		assertEquals("le message doit valoir : 'le type de réseau de la section HIT (colonnes [28] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitTypeReseauNomenclature03().______________
	

	
	/* 17 - pRoupK. ************************/
	/**
	 * teste la méthode getMessageSectionHitPRoupKRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPRoupKRenseigne01() 
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
	public void testGetMessageSectionHitPRoupKRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPRoupKRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPRoupKRenseigne01();
		
		/* garantit que getMessageSectionHitPRoupKRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'PR ou PK de la section HIT (colonne [29] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PROUPK_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPRoupKRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPRoupKRenseigne01();

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
		
		/* garantit que setMessageSectionHitPRoupKRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPRoupKRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PROUPK_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPRoupKRenseigne01();

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
		
		/* garantit que setMessageSectionHitPRoupKRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'PR ou PK de la section HIT (colonne [29] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PROUPK_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPRoupKRenseigne01()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitPRoupKRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPRoupKRegex02() 
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
	public void testGetMessageSectionHitPRoupKRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPRoupKRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPRoupKRegex02();
		
		/* garantit que getMessageSectionHitPRoupKRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'PR ou PK de la section HIT (colonne [29] du HIT) doit comporter exactement 1 chiffre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PROUPK_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPRoupKRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPRoupKRegex02();

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
		
		/* garantit que setMessageSectionHitPRoupKRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPRoupKRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PROUPK_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPRoupKRegex02();

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

				
		assertEquals("le message doit valoir : 'PR ou PK de la section HIT (colonne [29] du HIT) doit comporter exactement 1 chiffre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PROUPK_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPRoupKRegex02().___________________

	
	
	/**
	 * teste la méthode getMessageSectionHitPRoupKNomenclature03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPRoupKNomenclature03() 
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
	public void testGetMessageSectionHitPRoupKNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPRoupKNomenclature03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPRoupKNomenclature03();
		
		/* garantit que getMessageSectionHitPRoupKNomenclature03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'PR ou PK de la section HIT (colonne [29] du HIT) doit respecter une nomenclature [1, 2]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PROUPK_NOMENCLATURE_03_EN_DUR
					, message);

		
		// ***************************************
		/* modification du message. ****************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPRoupKNomenclature03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPRoupKNomenclature03();

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

		/* garantit que setMessageSectionHitPRoupKRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPRoupKNomenclature03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PROUPK_NOMENCLATURE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPRoupKNomenclature03();

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

		assertEquals("le message doit valoir : 'PR ou PK de la section HIT (colonne [29] du HIT) doit respecter une nomenclature [1, 2]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PROUPK_NOMENCLATURE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPRoupKNomenclature03().____________
	

	
	/* 18 - lieuDitOrigine. ************************/
	/**
	 * teste la méthode getMessageSectionHitLieuDitOrigineRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitLieuDitOrigineRenseigne01() 
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
	public void testGetMessageSectionHitLieuDitOrigineRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitLieuDitOrigineRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLieuDitOrigineRenseigne01();
		
		/* garantit que getMessageSectionHitLieuDitOrigineRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le lieu-dit origine de la section HIT (colonnes [30-49] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLieuDitOrigineRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitLieuDitOrigineRenseigne01();

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
		
		/* garantit que setMessageSectionHitLieuDitOrigineRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLieuDitOrigineRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitLieuDitOrigineRenseigne01();

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
		
		/* garantit que setMessageSectionHitLieuDitOrigineRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le lieu-dit origine de la section HIT (colonnes [30-49] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitLieuDitOrigineRenseigne01()._______

	
	
	/**
	 * teste la méthode getMessageSectionHitLieuDitOrigineRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitLieuDitOrigineRegex02() 
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
	public void testGetMessageSectionHitLieuDitOrigineRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitLieuDitOrigineRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLieuDitOrigineRegex02();
		
		/* garantit que getMessageSectionHitLieuDitOrigineRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le lieu-dit origine de la section HIT (colonnes [30-49] du HIT) doit comporter exactement 20 caractères'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LIEUDITORIGINE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLieuDitOrigineRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLieuDitOrigineRegex02();

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
		
		/* garantit que setMessageSectionHitLieuDitOrigineRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLieuDitOrigineRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_LIEUDITORIGINE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitLieuDitOrigineRegex02();

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

				
		assertEquals("le message doit valoir : 'le lieu-dit origine de la section HIT (colonnes [30-49] du HIT) doit comporter exactement 20 caractères'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LIEUDITORIGINE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitLieuDitOrigineRegex02().___________
	

	
	/* 19 - prOrigine. ************************/
	/**
	 * teste la méthode getMessageSectionHitPrOrigineRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPrOrigineRenseigne01() 
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
	public void testGetMessageSectionHitPrOrigineRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPrOrigineRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPrOrigineRenseigne01();
		
		/* garantit que getMessageSectionHitPrOrigineRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le PR origine de la section HIT (colonnes [50-52] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PRORIGINE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrOrigineRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPrOrigineRenseigne01();

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
		
		/* garantit que setMessageSectionHitPrOrigineRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrOrigineRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PRORIGINE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPrOrigineRenseigne01();

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
		
		/* garantit que setMessageSectionHitPrOrigineRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le PR origine de la section HIT (colonnes [50-52] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PRORIGINE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPrOrigineRenseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitPrOrigineRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPrOrigineRegex02() 
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
	public void testGetMessageSectionHitPrOrigineRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPrOrigineRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPrOrigineRegex02();
		
		/* garantit que getMessageSectionHitPrOrigineRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le PR origine de la section HIT (colonnes [50-52] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PRORIGINE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrOrigineRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPrOrigineRegex02();

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
		
		/* garantit que setMessageSectionHitPrOrigineRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrOrigineRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PRORIGINE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPrOrigineRegex02();

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

				
		assertEquals("le message doit valoir : 'le PR origine de la section HIT (colonnes [50-52] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PRORIGINE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPrOrigineRegex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitPrOrigineNumerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPrOrigineNumerique03() 
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
	public void testGetMessageSectionHitPrOrigineNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPrOrigineNumerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPrOrigineNumerique03();
		
		/* garantit que getMessageSectionHitPrOrigineNumerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le PR origine de la section HIT (colonnes [50-52] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PRORIGINE_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrOrigineNumerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPrOrigineNumerique03();

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
		
		/* garantit que setMessageSectionHitPrOrigineNumerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrOrigineNumerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PRORIGINE_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPrOrigineNumerique03();

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

				
		assertEquals("le message doit valoir : 'le PR origine de la section HIT (colonnes [50-52] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PRORIGINE_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPrOrigineNumerique03().____________
	

	
	/* 20 - absOrigine. ************************/
	/**
	 * teste la méthode getMessageSectionHitAbsOrigineRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitAbsOrigineRenseigne01() 
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
	public void testGetMessageSectionHitAbsOrigineRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitAbsOrigineRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAbsOrigineRenseigne01();
		
		/* garantit que getMessageSectionHitAbsOrigineRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSORIGINE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsOrigineRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAbsOrigineRenseigne01();

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
		
		/* garantit que setMessageSectionHitAbsOrigineRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsOrigineRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ABSORIGINE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAbsOrigineRenseigne01();

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
		
		/* garantit que setMessageSectionHitAbsOrigineRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSORIGINE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitAbsOrigineRenseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitAbsOrigineRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitAbsOrigineRegex02() 
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
	public void testGetMessageSectionHitAbsOrigineRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitAbsOrigineRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAbsOrigineRegex02();
		
		/* garantit que getMessageSectionHitAbsOrigineRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit comporter exactement 4 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSORIGINE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsOrigineRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAbsOrigineRegex02();

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
		
		/* garantit que setMessageSectionHitAbsOrigineRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsOrigineRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ABSORIGINE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAbsOrigineRegex02();

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

				
		assertEquals("le message doit valoir : 'l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit comporter exactement 4 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSORIGINE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitAbsOrigineRegex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitAbsOrigineNumerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitAbsOrigineNumerique03() 
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
	public void testGetMessageSectionHitAbsOrigineNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitAbsOrigineNumerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAbsOrigineNumerique03();
		
		/* garantit que getMessageSectionHitAbsOrigineNumerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSORIGINE_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsOrigineNumerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAbsOrigineNumerique03();

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
		
		/* garantit que setMessageSectionHitAbsOrigineNumerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsOrigineNumerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ABSORIGINE_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAbsOrigineNumerique03();

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

				
		assertEquals("le message doit valoir : 'l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSORIGINE_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitAbsOrigineNumerique03().___________
	

	
	/* 21 - lieuDitExtremite. ************************/
	/**
	 * teste la méthode getMessageSectionHitLieuDitExtremiteRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitLieuDitExtremiteRenseigne01() 
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
	public void testGetMessageSectionHitLieuDitExtremiteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitLieuDitExtremiteRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLieuDitExtremiteRenseigne01();
		
		/* garantit que getMessageSectionHitLieuDitExtremiteRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le lieu-dit extremité de la section HIT (colonnes [57-76] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLieuDitExtremiteRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitLieuDitExtremiteRenseigne01();

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
		
		/* garantit que setMessageSectionHitLieuDitExtremiteRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLieuDitExtremiteRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitLieuDitExtremiteRenseigne01();

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
		
		/* garantit que setMessageSectionHitLieuDitExtremiteRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le lieu-dit extremité de la section HIT (colonnes [57-76] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitLieuDitExtremiteRenseigne01()._____

	
	
	/**
	 * teste la méthode getMessageSectionHitLieuDitExtremiteRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitLieuDitExtremiteRegex02() 
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
	public void testGetMessageSectionHitLieuDitExtremiteRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitLieuDitExtremiteRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLieuDitExtremiteRegex02();
		
		/* garantit que getMessageSectionHitLieuDitExtremiteRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le lieu-dit extremité de la section HIT (colonnes [57-76] du HIT) doit comporter exactement 20 caractères'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLieuDitExtremiteRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLieuDitExtremiteRegex02();

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
		
		/* garantit que setMessageSectionHitLieuDitExtremiteRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLieuDitExtremiteRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitLieuDitExtremiteRegex02();

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

				
		assertEquals("le message doit valoir : 'le lieu-dit extremité de la section HIT (colonnes [57-76] du HIT) doit comporter exactement 20 caractères'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitLieuDitExtremiteRegex02()._________
	

	
	/* 22 - prExtremite. ************************/
	/**
	 * teste la méthode getMessageSectionHitPrExtremiteRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPrExtremiteRenseigne01() 
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
	public void testGetMessageSectionHitPrExtremiteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPrExtremiteRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPrExtremiteRenseigne01();
		
		/* garantit que getMessageSectionHitPrExtremiteRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le PR extremité de la section HIT (colonnes [77-79] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PREXTREMITE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrExtremiteRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPrExtremiteRenseigne01();

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
		
		/* garantit que setMessageSectionHitPrExtremiteRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrExtremiteRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PREXTREMITE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPrExtremiteRenseigne01();

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
		
		/* garantit que setMessageSectionHitPrExtremiteRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le PR extremité de la section HIT (colonnes [77-79] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PREXTREMITE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPrExtremiteRenseigne01().__________

	
	
	/**
	 * teste la méthode getMessageSectionHitPrExtremiteRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPrExtremiteRegex02() 
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
	public void testGetMessageSectionHitPrExtremiteRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPrExtremiteRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPrExtremiteRegex02();
		
		/* garantit que getMessageSectionHitPrExtremiteRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le PR extremité de la section HIT (colonnes [77-79] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PREXTREMITE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrExtremiteRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPrExtremiteRegex02();

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
		
		/* garantit que setMessageSectionHitPrExtremiteRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrExtremiteRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PREXTREMITE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPrExtremiteRegex02();

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

				
		assertEquals("le message doit valoir : 'le PR extremité de la section HIT (colonnes [77-79] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PREXTREMITE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPrExtremiteRegex02().______________

	
	
	/**
	 * teste la méthode getMessageSectionHitPrExtremiteNumerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPrExtremiteNumerique03() 
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
	public void testGetMessageSectionHitPrExtremiteNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPrExtremiteNumerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPrExtremiteNumerique03();
		
		/* garantit que getMessageSectionHitPrExtremiteNumerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le PR extremité de la section HIT (colonnes [77-79] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PREXTREMITE_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrExtremiteNumerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPrExtremiteNumerique03();

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
		
		/* garantit que setMessageSectionHitPrExtremiteNumerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrExtremiteNumerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PREXTREMITE_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPrExtremiteNumerique03();

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

				
		assertEquals("le message doit valoir : 'le PR extremité de la section HIT (colonnes [77-79] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PREXTREMITE_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPrExtremiteNumerique03().__________
	

	
	/* 23 - absExtremite. ************************/
	/**
	 * teste la méthode getMessageSectionHitAbsExtremiteRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitAbsExtremiteRenseigne01() 
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
	public void testGetMessageSectionHitAbsExtremiteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitAbsExtremiteRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAbsExtremiteRenseigne01();
		
		/* garantit que getMessageSectionHitAbsExtremiteRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsExtremiteRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAbsExtremiteRenseigne01();

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
		
		/* garantit que setMessageSectionHitAbsExtremiteRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsExtremiteRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAbsExtremiteRenseigne01();

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
		
		/* garantit que setMessageSectionHitAbsExtremiteRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitAbsExtremiteRenseigne01()._________

	
	
	/**
	 * teste la méthode getMessageSectionHitAbsExtremiteRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitAbsExtremiteRegex02() 
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
	public void testGetMessageSectionHitAbsExtremiteRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitAbsExtremiteRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAbsExtremiteRegex02();
		
		/* garantit que getMessageSectionHitAbsExtremiteRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit comporter exactement 4 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSEXTREMITE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsExtremiteRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAbsExtremiteRegex02();

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
		
		/* garantit que setMessageSectionHitAbsExtremiteRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsExtremiteRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ABSEXTREMITE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAbsExtremiteRegex02();

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

				
		assertEquals("le message doit valoir : 'l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit comporter exactement 4 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSEXTREMITE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitAbsExtremiteRegex02()._____________

	
	
	/**
	 * teste la méthode getMessageSectionHitAbsExtremiteNumerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitAbsExtremiteNumerique03() 
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
	public void testGetMessageSectionHitAbsExtremiteNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitAbsExtremiteNumerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAbsExtremiteNumerique03();
		
		/* garantit que getMessageSectionHitAbsExtremiteNumerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsExtremiteNumerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAbsExtremiteNumerique03();

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
		
		/* garantit que setMessageSectionHitAbsExtremiteNumerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsExtremiteNumerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAbsExtremiteNumerique03();

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

				
		assertEquals("le message doit valoir : 'l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitAbsExtremiteNumerique03()._________
	

	
	/* 24 - lieuDitComptage. ************************/
	/**
	 * teste la méthode getMessageSectionHitLieuDitComptageRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitLieuDitComptageRenseigne01() 
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
	public void testGetMessageSectionHitLieuDitComptageRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitLieuDitComptageRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLieuDitComptageRenseigne01();
		
		/* garantit que getMessageSectionHitLieuDitComptageRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le lieu-dit comptage de la section HIT (colonnes [84-103] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLieuDitComptageRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitLieuDitComptageRenseigne01();

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
		
		/* garantit que setMessageSectionHitLieuDitComptageRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLieuDitComptageRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitLieuDitComptageRenseigne01();

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
		
		/* garantit que setMessageSectionHitLieuDitComptageRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le lieu-dit comptage de la section HIT (colonnes [84-103] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitLieuDitComptageRenseigne01().______

	
	
	/**
	 * teste la méthode getMessageSectionHitLieuDitComptageRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitLieuDitComptageRegex02() 
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
	public void testGetMessageSectionHitLieuDitComptageRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitLieuDitComptageRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLieuDitComptageRegex02();
		
		/* garantit que getMessageSectionHitLieuDitComptageRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le lieu-dit comptage de la section HIT (colonnes [84-103] du HIT) doit comporter exactement 20 caractères'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLieuDitComptageRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLieuDitComptageRegex02();

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
		
		/* garantit que setMessageSectionHitLieuDitComptageRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLieuDitComptageRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitLieuDitComptageRegex02();

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

				
		assertEquals("le message doit valoir : 'le lieu-dit comptage de la section HIT (colonnes [84-103] du HIT) doit comporter exactement 20 caractères'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitLieuDitComptageRegex02().__________
	

	
	/* 25 - prComptage. ************************/
	/**
	 * teste la méthode getMessageSectionHitPrComptageRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPrComptageRenseigne01() 
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
	public void testGetMessageSectionHitPrComptageRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPrComptageRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPrComptageRenseigne01();
		
		/* garantit que getMessageSectionHitPrComptageRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le PR comptage de la section HIT (colonnes [104-106] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrComptageRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPrComptageRenseigne01();

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
		
		/* garantit que setMessageSectionHitPrComptageRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrComptageRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPrComptageRenseigne01();

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
		
		/* garantit que setMessageSectionHitPrComptageRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le PR comptage de la section HIT (colonnes [104-106] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPrComptageRenseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitPrComptageRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPrComptageRegex02() 
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
	public void testGetMessageSectionHitPrComptageRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPrComptageRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPrComptageRegex02();
		
		/* garantit que getMessageSectionHitPrComptageRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le PR comptage de la section HIT (colonnes [104-106] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PRCOMPTAGE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrComptageRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPrComptageRegex02();

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
		
		/* garantit que setMessageSectionHitPrComptageRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrComptageRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PRCOMPTAGE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPrComptageRegex02();

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

				
		assertEquals("le message doit valoir : 'le PR comptage de la section HIT (colonnes [104-106] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PRCOMPTAGE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPrComptageRegex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitPrComptageNumerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPrComptageNumerique03() 
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
	public void testGetMessageSectionHitPrComptageNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPrComptageNumerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPrComptageNumerique03();
		
		/* garantit que getMessageSectionHitPrComptageNumerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le PR comptage de la section HIT (colonnes [104-106] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrComptageNumerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPrComptageNumerique03();

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
		
		/* garantit que setMessageSectionHitPrComptageNumerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPrComptageNumerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPrComptageNumerique03();

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

				
		assertEquals("le message doit valoir : 'le PR comptage de la section HIT (colonnes [104-106] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPrComptageNumerique03().___________
	

	
	/* 26 - absComptage. ************************/
	/**
	 * teste la méthode getMessageSectionHitAbsComptageRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitAbsComptageRenseigne01() 
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
	public void testGetMessageSectionHitAbsComptageRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitAbsComptageRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAbsComptageRenseigne01();
		
		/* garantit que getMessageSectionHitAbsComptageRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'l'abscisse comptage de la section HIT (colonnes [107-110] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsComptageRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAbsComptageRenseigne01();

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
		
		/* garantit que setMessageSectionHitAbsComptageRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsComptageRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAbsComptageRenseigne01();

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
		
		/* garantit que setMessageSectionHitAbsComptageRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'l'abscisse comptage de la section HIT (colonnes [107-110] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitAbsComptageRenseigne01().__________

	
	
	/**
	 * teste la méthode getMessageSectionHitAbsComptageRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitAbsComptageRegex02() 
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
	public void testGetMessageSectionHitAbsComptageRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitAbsComptageRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAbsComptageRegex02();
		
		/* garantit que getMessageSectionHitAbsComptageRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'l'abscisse comptage de la section HIT (colonnes [107-110] du HIT) doit comporter exactement 4 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSCOMPTAGE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsComptageRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAbsComptageRegex02();

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
		
		/* garantit que setMessageSectionHitAbsComptageRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsComptageRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ABSCOMPTAGE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAbsComptageRegex02();

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

				
		assertEquals("le message doit valoir : 'l'abscisse comptage de la section HIT (colonnes [107-110] du HIT) doit comporter exactement 4 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSCOMPTAGE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitAbsComptageRegex02().______________

	
	
	/**
	 * teste la méthode getMessageSectionHitAbsComptageNumerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitAbsComptageNumerique03() 
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
	public void testGetMessageSectionHitAbsComptageNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitAbsComptageNumerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAbsComptageNumerique03();
		
		/* garantit que getMessageSectionHitAbsComptageNumerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'l'abscisse comptage de la section HIT (colonnes [107-110] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsComptageNumerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAbsComptageNumerique03();

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
		
		/* garantit que setMessageSectionHitAbsComptageNumerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAbsComptageNumerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAbsComptageNumerique03();

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

				
		assertEquals("le message doit valoir : 'l'abscisse comptage de la section HIT (colonnes [107-110] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitAbsComptageNumerique03().__________
	

	
	/* 27 - longueurSection. ************************/
	/**
	 * teste la méthode getMessageSectionHitLongueurSectionRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitLongueurSectionRenseigne01() 
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
	public void testGetMessageSectionHitLongueurSectionRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitLongueurSectionRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLongueurSectionRenseigne01();
		
		/* garantit que getMessageSectionHitLongueurSectionRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la longueur de la section HIT (colonnes [111-116] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LONGUEURSECTION_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLongueurSectionRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitLongueurSectionRenseigne01();

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
		
		/* garantit que setMessageSectionHitLongueurSectionRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLongueurSectionRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_LONGUEURSECTION_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitLongueurSectionRenseigne01();

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
		
		/* garantit que setMessageSectionHitLongueurSectionRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la longueur de la section HIT (colonnes [111-116] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LONGUEURSECTION_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitLongueurSectionRenseigne01().______

	
	
	/**
	 * teste la méthode getMessageSectionHitLongueurSectionRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitLongueurSectionRegex02() 
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
	public void testGetMessageSectionHitLongueurSectionRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitLongueurSectionRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLongueurSectionRegex02();
		
		/* garantit que getMessageSectionHitLongueurSectionRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la longueur de la section HIT (colonnes [111-116] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LONGUEURSECTION_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLongueurSectionRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLongueurSectionRegex02();

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
		
		/* garantit que setMessageSectionHitLongueurSectionRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLongueurSectionRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_LONGUEURSECTION_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitLongueurSectionRegex02();

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

				
		assertEquals("le message doit valoir : 'la longueur de la section HIT (colonnes [111-116] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LONGUEURSECTION_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitLongueurSectionRegex02().__________

	
	
	/**
	 * teste la méthode getMessageSectionHitLongueurSectionNumerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitLongueurSectionNumerique03() 
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
	public void testGetMessageSectionHitLongueurSectionNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitLongueurSectionNumerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLongueurSectionNumerique03();
		
		/* garantit que getMessageSectionHitLongueurSectionNumerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la longueur de la section HIT (colonnes [111-116] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LONGUEURSECTION_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLongueurSectionNumerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLongueurSectionNumerique03();

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
		
		/* garantit que setMessageSectionHitLongueurSectionNumerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLongueurSectionNumerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_LONGUEURSECTION_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitLongueurSectionNumerique03();

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

				
		assertEquals("le message doit valoir : 'la longueur de la section HIT (colonnes [111-116] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LONGUEURSECTION_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitLongueurSectionNumerique03().______
	

	
	/* 28 - longueurRaseCampagne. ************************/
	/**
	 * teste la méthode getMessageSectionHitLongueurRaseCampagneRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitLongueurRaseCampagneRenseigne01() 
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
	public void testGetMessageSectionHitLongueurRaseCampagneRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitLongueurRaseCampagneRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLongueurRaseCampagneRenseigne01();
		
		/* garantit que getMessageSectionHitLongueurRaseCampagneRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la longueur en rase campagne de la section HIT (colonnes [117-122] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LONGUEURRASECAMPAGNE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLongueurRaseCampagneRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitLongueurRaseCampagneRenseigne01();

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
		
		/* garantit que setMessageSectionHitLongueurRaseCampagneRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLongueurRaseCampagneRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_LONGUEURRASECAMPAGNE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitLongueurRaseCampagneRenseigne01();

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
		
		/* garantit que setMessageSectionHitLongueurRaseCampagneRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la longueur en rase campagne de la section HIT (colonnes [117-122] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LONGUEURRASECAMPAGNE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitLongueurRaseCampagneRenseigne01()._

	
	
	/**
	 * teste la méthode getMessageSectionHitLongueurRaseCampagneRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitLongueurRaseCampagneRegex02() 
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
	public void testGetMessageSectionHitLongueurRaseCampagneRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitLongueurRaseCampagneRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLongueurRaseCampagneRegex02();
		
		/* garantit que getMessageSectionHitLongueurRaseCampagneRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la longueur en rase campagne de la section HIT (colonnes [117-122] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LONGUEURRASECAMPAGNE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLongueurRaseCampagneRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLongueurRaseCampagneRegex02();

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
		
		/* garantit que setMessageSectionHitLongueurRaseCampagneRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLongueurRaseCampagneRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_LONGUEURRASECAMPAGNE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitLongueurRaseCampagneRegex02();

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

				
		assertEquals("le message doit valoir : 'la longueur en rase campagne de la section HIT (colonnes [117-122] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LONGUEURRASECAMPAGNE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitLongueurRaseCampagneRegex02()._____

	
	
	/**
	 * teste la méthode getMessageSectionHitLongueurRaseCampagneNumerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitLongueurRaseCampagneNumerique03() 
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
	public void testGetMessageSectionHitLongueurRaseCampagneNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitLongueurRaseCampagneNumerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLongueurRaseCampagneNumerique03();
		
		/* garantit que getMessageSectionHitLongueurRaseCampagneNumerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la longueur en rase campagne de la section HIT (colonnes [117-122] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LONGUEURRASECAMPAGNE_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLongueurRaseCampagneNumerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitLongueurRaseCampagneNumerique03();

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
		
		/* garantit que setMessageSectionHitLongueurRaseCampagneNumerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitLongueurRaseCampagneNumerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_LONGUEURRASECAMPAGNE_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitLongueurRaseCampagneNumerique03();

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

				
		assertEquals("le message doit valoir : 'la longueur en rase campagne de la section HIT (colonnes [117-122] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_LONGUEURRASECAMPAGNE_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitLongueurRaseCampagneNumerique03()._
	

	
	/* 29 - numDepartementRattachement. ************************/
	/**
	 * teste la méthode getMessageSectionHitNumDepartementRattachementRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitNumDepartementRattachementRenseigne01() 
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
	public void testGetMessageSectionHitNumDepartementRattachementRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitNumDepartementRattachementRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumDepartementRattachementRenseigne01();
		
		/* garantit que getMessageSectionHitNumDepartementRattachementRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le numéro de département de rattachement de la section HIT (colonnes [123-125] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumDepartementRattachementRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumDepartementRattachementRenseigne01();

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
		
		/* garantit que setMessageSectionHitNumDepartementRattachementRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumDepartementRattachementRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumDepartementRattachementRenseigne01();

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
		
		/* garantit que setMessageSectionHitNumDepartementRattachementRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le numéro de département de rattachement de la section HIT (colonnes [123-125] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitNumDepartementRattachementRenseigne01().

	
	
	/**
	 * teste la méthode getMessageSectionHitNumDepartementRattachementRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitNumDepartementRattachementRegex02() 
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
	public void testGetMessageSectionHitNumDepartementRattachementRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitNumDepartementRattachementRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumDepartementRattachementRegex02();
		
		/* garantit que getMessageSectionHitNumDepartementRattachementRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le numéro de département de rattachement de la section HIT (colonnes [123-125] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumDepartementRattachementRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumDepartementRattachementRegex02();

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
		
		/* garantit que setMessageSectionHitNumDepartementRattachementRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumDepartementRattachementRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumDepartementRattachementRegex02();

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

				
		assertEquals("le message doit valoir : 'le numéro de département de rattachement de la section HIT (colonnes [123-125] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitNumDepartementRattachementRegex02().
	

	
	/* 30 - numSectionRattachement. ************************/
	/**
	 * teste la méthode getMessageSectionHitNumSectionRattachementRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitNumSectionRattachementRenseigne01() 
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
	public void testGetMessageSectionHitNumSectionRattachementRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitNumSectionRattachementRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumSectionRattachementRenseigne01();
		
		/* garantit que getMessageSectionHitNumSectionRattachementRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le numéro de section de rattachement de la section HIT (colonnes [126-131] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMSECTIONRATTACHEMENT_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumSectionRattachementRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumSectionRattachementRenseigne01();

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
		
		/* garantit que setMessageSectionHitNumSectionRattachementRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumSectionRattachementRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NUMSECTIONRATTACHEMENT_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumSectionRattachementRenseigne01();

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
		
		/* garantit que setMessageSectionHitNumSectionRattachementRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le numéro de section de rattachement de la section HIT (colonnes [126-131] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMSECTIONRATTACHEMENT_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitNumSectionRattachementRenseigne01().

	
	
	/**
	 * teste la méthode getMessageSectionHitNumSectionRattachementRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitNumSectionRattachementRegex02() 
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
	public void testGetMessageSectionHitNumSectionRattachementRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitNumSectionRattachementRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumSectionRattachementRegex02();
		
		/* garantit que getMessageSectionHitNumSectionRattachementRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le numéro de section de rattachement de la section HIT (colonnes [126-131] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMSECTIONRATTACHEMENT_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumSectionRattachementRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumSectionRattachementRegex02();

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
		
		/* garantit que setMessageSectionHitNumSectionRattachementRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumSectionRattachementRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NUMSECTIONRATTACHEMENT_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumSectionRattachementRegex02();

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

				
		assertEquals("le message doit valoir : 'le numéro de section de rattachement de la section HIT (colonnes [126-131] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMSECTIONRATTACHEMENT_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitNumSectionRattachementRegex02().___
	

	
	/* 31 - sensRattachement. ************************/
	/**
	 * teste la méthode getMessageSectionHitSensRattachementRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitSensRattachementRenseigne01() 
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
	public void testGetMessageSectionHitSensRattachementRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitSensRattachementRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensRattachementRenseigne01();
		
		/* garantit que getMessageSectionHitSensRattachementRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le sens de la section de rattachement de la section HIT (colonne [132] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENSRATTACHEMENT_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensRattachementRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitSensRattachementRenseigne01();

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
		
		/* garantit que setMessageSectionHitSensRattachementRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensRattachementRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_SENSRATTACHEMENT_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitSensRattachementRenseigne01();

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
		
		/* garantit que setMessageSectionHitSensRattachementRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le sens de la section de rattachement de la section HIT (colonne [132] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENSRATTACHEMENT_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitSensRattachementRenseigne01()._____

	
	
	/**
	 * teste la méthode getMessageSectionHitSensRattachementRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitSensRattachementRegex02() 
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
	public void testGetMessageSectionHitSensRattachementRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitSensRattachementRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensRattachementRegex02();
		
		/* garantit que getMessageSectionHitSensRattachementRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le sens de la section de rattachement de la section HIT (colonne [132] du HIT) doit comporter exactement 1 chiffre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENSRATTACHEMENT_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensRattachementRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensRattachementRegex02();

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
		
		/* garantit que setMessageSectionHitSensRattachementRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensRattachementRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_SENSRATTACHEMENT_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitSensRattachementRegex02();

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

				
		assertEquals("le message doit valoir : 'le sens de la section de rattachement de la section HIT (colonne [132] du HIT) doit comporter exactement 1 chiffre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENSRATTACHEMENT_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitSensRattachementRegex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitSensRattachementNomenclature03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitSensRattachementNomenclature03() 
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
	public void testGetMessageSectionHitSensRattachementNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitSensRattachementNomenclature03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensRattachementNomenclature03();
		
		/* garantit que getMessageSectionHitSensRattachementNomenclature03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le sens de la section de rattachement de la section HIT (colonne [132] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENSRATTACHEMENT_NOMENCLATURE_03_EN_DUR
					, message);

		
		// ***************************************
		/* modification du message. ****************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensRattachementNomenclature03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensRattachementNomenclature03();

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

		/* garantit que setMessageSectionHitSensRattachementRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensRattachementNomenclature03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_SENSRATTACHEMENT_NOMENCLATURE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitSensRattachementNomenclature03();

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

		assertEquals("le message doit valoir : 'le sens de la section de rattachement de la section HIT (colonne [132] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENSRATTACHEMENT_NOMENCLATURE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitSensRattachementNomenclature03().______________
	

	
	/* 32 - numDepartementLimitrophe. ************************/
	/**
	 * teste la méthode getMessageSectionHitNumDepartementLimitropheRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitNumDepartementLimitropheRenseigne01() 
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
	public void testGetMessageSectionHitNumDepartementLimitropheRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitNumDepartementLimitropheRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumDepartementLimitropheRenseigne01();
		
		/* garantit que getMessageSectionHitNumDepartementLimitropheRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le numéro de département limitrophe de la section HIT (colonnes [133-135] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumDepartementLimitropheRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumDepartementLimitropheRenseigne01();

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
		
		/* garantit que setMessageSectionHitNumDepartementLimitropheRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumDepartementLimitropheRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumDepartementLimitropheRenseigne01();

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
		
		/* garantit que setMessageSectionHitNumDepartementLimitropheRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le numéro de département limitrophe de la section HIT (colonnes [133-135] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitNumDepartementLimitropheRenseigne01().

	
	
	/**
	 * teste la méthode getMessageSectionHitNumDepartementLimitropheRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitNumDepartementLimitropheRegex02() 
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
	public void testGetMessageSectionHitNumDepartementLimitropheRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitNumDepartementLimitropheRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumDepartementLimitropheRegex02();
		
		/* garantit que getMessageSectionHitNumDepartementLimitropheRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le numéro de département limitrophe de la section HIT (colonnes [133-135] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumDepartementLimitropheRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumDepartementLimitropheRegex02();

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
		
		/* garantit que setMessageSectionHitNumDepartementLimitropheRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumDepartementLimitropheRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumDepartementLimitropheRegex02();

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

				
		assertEquals("le message doit valoir : 'le numéro de département limitrophe de la section HIT (colonnes [133-135] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitNumDepartementLimitropheRegex02().
	

	
	/* 33 - numSectionLimitrophe. ************************/
	/**
	 * teste la méthode getMessageSectionHitNumSectionLimitropheRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitNumSectionLimitropheRenseigne01() 
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
	public void testGetMessageSectionHitNumSectionLimitropheRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitNumSectionLimitropheRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumSectionLimitropheRenseigne01();
		
		/* garantit que getMessageSectionHitNumSectionLimitropheRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le numéro de section limitrophe de la section HIT (colonnes [136-141] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMSECTIONLIMITROPHE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumSectionLimitropheRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumSectionLimitropheRenseigne01();

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
		
		/* garantit que setMessageSectionHitNumSectionLimitropheRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumSectionLimitropheRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NUMSECTIONLIMITROPHE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumSectionLimitropheRenseigne01();

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
		
		/* garantit que setMessageSectionHitNumSectionLimitropheRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le numéro de section limitrophe de la section HIT (colonnes [136-141] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMSECTIONLIMITROPHE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitNumSectionLimitropheRenseigne01().

	
	
	/**
	 * teste la méthode getMessageSectionHitNumSectionLimitropheRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitNumSectionLimitropheRegex02() 
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
	public void testGetMessageSectionHitNumSectionLimitropheRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitNumSectionLimitropheRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumSectionLimitropheRegex02();
		
		/* garantit que getMessageSectionHitNumSectionLimitropheRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le numéro de section limitrophe de la section HIT (colonnes [136-141] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMSECTIONLIMITROPHE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumSectionLimitropheRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumSectionLimitropheRegex02();

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
		
		/* garantit que setMessageSectionHitNumSectionLimitropheRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitNumSectionLimitropheRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_NUMSECTIONLIMITROPHE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitNumSectionLimitropheRegex02();

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

				
		assertEquals("le message doit valoir : 'le numéro de section limitrophe de la section HIT (colonnes [136-141] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_NUMSECTIONLIMITROPHE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitNumSectionLimitropheRegex02().___
	

	
	/* 34 - sensLimitrophe. ************************/
	/**
	 * teste la méthode getMessageSectionHitSensLimitropheRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitSensLimitropheRenseigne01() 
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
	public void testGetMessageSectionHitSensLimitropheRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitSensLimitropheRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensLimitropheRenseigne01();
		
		/* garantit que getMessageSectionHitSensLimitropheRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le sens de la section limitrophe de la section HIT (colonne [142] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENSLIMITROPHE_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensLimitropheRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitSensLimitropheRenseigne01();

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
		
		/* garantit que setMessageSectionHitSensLimitropheRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensLimitropheRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_SENSLIMITROPHE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitSensLimitropheRenseigne01();

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
		
		/* garantit que setMessageSectionHitSensLimitropheRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le sens de la section limitrophe de la section HIT (colonne [142] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENSLIMITROPHE_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitSensLimitropheRenseigne01()._____

	
	
	/**
	 * teste la méthode getMessageSectionHitSensLimitropheRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitSensLimitropheRegex02() 
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
	public void testGetMessageSectionHitSensLimitropheRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitSensLimitropheRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensLimitropheRegex02();
		
		/* garantit que getMessageSectionHitSensLimitropheRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le sens de la section limitrophe de la section HIT (colonne [142] du HIT) doit comporter exactement 1 chiffre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENSLIMITROPHE_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensLimitropheRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensLimitropheRegex02();

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
		
		/* garantit que setMessageSectionHitSensLimitropheRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensLimitropheRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_SENSLIMITROPHE_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitSensLimitropheRegex02();

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

				
		assertEquals("le message doit valoir : 'le sens de la section limitrophe de la section HIT (colonne [142] du HIT) doit comporter exactement 1 chiffre'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENSLIMITROPHE_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitSensLimitropheRegex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitSensLimitropheNomenclature03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitSensLimitropheNomenclature03() 
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
	public void testGetMessageSectionHitSensLimitropheNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitSensLimitropheNomenclature03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensLimitropheNomenclature03();
		
		/* garantit que getMessageSectionHitSensLimitropheNomenclature03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le sens de la section limitrophe de la section HIT (colonne [142] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENSLIMITROPHE_NOMENCLATURE_03_EN_DUR
					, message);

		
		// ***************************************
		/* modification du message. ****************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensLimitropheNomenclature03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensLimitropheNomenclature03();

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

		/* garantit que setMessageSectionHitSensLimitropheRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitSensLimitropheNomenclature03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_SENSLIMITROPHE_NOMENCLATURE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitSensLimitropheNomenclature03();

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

		assertEquals("le message doit valoir : 'le sens de la section limitrophe de la section HIT (colonne [142] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_SENSLIMITROPHE_NOMENCLATURE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitSensLimitropheNomenclature03().______________
	

	
	/* 35 - moisSectionnement. ************************/
	/**
	 * teste la méthode getMessageSectionHitMoisSectionnementRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMoisSectionnementRenseigne01() 
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
	public void testGetMessageSectionHitMoisSectionnementRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMoisSectionnementRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMoisSectionnementRenseigne01();
		
		/* garantit que getMessageSectionHitMoisSectionnementRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le mois de sectionnement de la section HIT (colonnes [143-144] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MOISSECTIONNEMENT_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMoisSectionnementRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMoisSectionnementRenseigne01();

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
		
		/* garantit que setMessageSectionHitMoisSectionnementRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMoisSectionnementRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MOISSECTIONNEMENT_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMoisSectionnementRenseigne01();

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
		
		/* garantit que setMessageSectionHitMoisSectionnementRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le mois de sectionnement de la section HIT (colonnes [143-144] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MOISSECTIONNEMENT_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitMoisSectionnementRenseigne01().____

	
	
	/**
	 * teste la méthode getMessageSectionHitMoisSectionnementRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMoisSectionnementRegex02() 
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
	public void testGetMessageSectionHitMoisSectionnementRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMoisSectionnementRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMoisSectionnementRegex02();
		
		/* garantit que getMessageSectionHitMoisSectionnementRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le mois de sectionnement de la section HIT (colonnes [143-144] du HIT) doit comporter exactement 2 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MOISSECTIONNEMENT_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMoisSectionnementRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMoisSectionnementRegex02();

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
		
		/* garantit que setMessageSectionHitMoisSectionnementRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMoisSectionnementRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MOISSECTIONNEMENT_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMoisSectionnementRegex02();

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

				
		assertEquals("le message doit valoir : 'le mois de sectionnement de la section HIT (colonnes [143-144] du HIT) doit comporter exactement 2 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MOISSECTIONNEMENT_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMoisSectionnementRegex02().________
	

	
	/* 36 - anneeSectionnement. ************************/
	/**
	 * teste la méthode getMessageSectionHitAnneeSectionnementRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitAnneeSectionnementRenseigne01() 
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
	public void testGetMessageSectionHitAnneeSectionnementRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitAnneeSectionnementRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAnneeSectionnementRenseigne01();
		
		/* garantit que getMessageSectionHitAnneeSectionnementRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'l'année de sectionnement de la section HIT (colonnes [145-146] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ANNEESECTIONNEMENT_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAnneeSectionnementRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAnneeSectionnementRenseigne01();

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
		
		/* garantit que setMessageSectionHitAnneeSectionnementRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAnneeSectionnementRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ANNEESECTIONNEMENT_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAnneeSectionnementRenseigne01();

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
		
		/* garantit que setMessageSectionHitAnneeSectionnementRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'l'année de sectionnement de la section HIT (colonnes [145-146] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ANNEESECTIONNEMENT_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitAnneeSectionnementRenseigne01().___

	
	
	/**
	 * teste la méthode getMessageSectionHitAnneeSectionnementRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitAnneeSectionnementRegex02() 
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
	public void testGetMessageSectionHitAnneeSectionnementRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitAnneeSectionnementRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAnneeSectionnementRegex02();
		
		/* garantit que getMessageSectionHitAnneeSectionnementRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'l'année de sectionnement de la section HIT (colonnes [145-146] du HIT) doit comporter exactement 2 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ANNEESECTIONNEMENT_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAnneeSectionnementRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAnneeSectionnementRegex02();

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
		
		/* garantit que setMessageSectionHitAnneeSectionnementRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAnneeSectionnementRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ANNEESECTIONNEMENT_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAnneeSectionnementRegex02();

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

				
		assertEquals("le message doit valoir : 'l'année de sectionnement de la section HIT (colonnes [145-146] du HIT) doit comporter exactement 2 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ANNEESECTIONNEMENT_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitAnneeSectionnementRegex02()._______
	

	
	/* 37 - zoneLibre2. ************************/
	/**
	 * teste la méthode getMessageSectionHitZoneLibre2Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitZoneLibre2Renseigne01() 
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
	public void testGetMessageSectionHitZoneLibre2Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitZoneLibre2Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitZoneLibre2Renseigne01();
		
		/* garantit que getMessageSectionHitZoneLibre2Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la zone libre de la section HIT (colonnes [147-152] du HIT) doit être renseignée (avec des espaces)'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ZONELIBRE2_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitZoneLibre2Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitZoneLibre2Renseigne01();

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
		
		/* garantit que setMessageSectionHitZoneLibre2Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitZoneLibre2Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ZONELIBRE2_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitZoneLibre2Renseigne01();

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
		
		/* garantit que setMessageSectionHitZoneLibre2Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la zone libre de la section HIT (colonnes [147-152] du HIT) doit être renseignée (avec des espaces)'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ZONELIBRE2_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitZoneLibre2Renseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitZoneLibre2Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitZoneLibre2Regex02() 
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
	public void testGetMessageSectionHitZoneLibre2Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitZoneLibre2Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitZoneLibre2Regex02();
		
		/* garantit que getMessageSectionHitZoneLibre2Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la zone libre de la section HIT (colonnes [147-152] du HIT) doit comporter exactement 6 espaces'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ZONELIBRE2_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitZoneLibre2Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitZoneLibre2Regex02();

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
		
		/* garantit que setMessageSectionHitZoneLibre2Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitZoneLibre2Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ZONELIBRE2_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitZoneLibre2Regex02();

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

				
		assertEquals("le message doit valoir : 'la zone libre de la section HIT (colonnes [147-152] du HIT) doit comporter exactement 6 espaces'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ZONELIBRE2_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitZoneLibre2Regex02()._______________
	

	
	/* 38 - mjaN. ************************/
	/**
	 * teste la méthode getMessageSectionHitMjaNRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjaNRenseigne01() 
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
	public void testGetMessageSectionHitMjaNRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjaNRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjaNRenseigne01();
		
		/* garantit que getMessageSectionHitMjaNRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le trafic moyen journalier annuel de l'année n de la section HIT (colonnes [153-158] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJAN_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjaNRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjaNRenseigne01();

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
		
		/* garantit que setMessageSectionHitMjaNRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjaNRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJAN_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjaNRenseigne01();

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
		
		/* garantit que setMessageSectionHitMjaNRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le trafic moyen journalier annuel de l'année n de la section HIT (colonnes [153-158] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJAN_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitMjaNRenseigne01()._________________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjaNRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjaNRegex02() 
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
	public void testGetMessageSectionHitMjaNRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjaNRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjaNRegex02();
		
		/* garantit que getMessageSectionHitMjaNRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le trafic moyen journalier annuel de l'année n de la section HIT (colonnes [153-158] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJAN_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjaNRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjaNRegex02();

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
		
		/* garantit que setMessageSectionHitMjaNRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjaNRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJAN_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjaNRegex02();

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

				
		assertEquals("le message doit valoir : 'le trafic moyen journalier annuel de l'année n de la section HIT (colonnes [153-158] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJAN_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjaNRegex02()._____________________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjaNNumerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjaNNumerique03() 
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
	public void testGetMessageSectionHitMjaNNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjaNNumerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjaNNumerique03();
		
		/* garantit que getMessageSectionHitMjaNNumerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le trafic moyen journalier annuel de l'année n de la section HIT (colonnes [153-158] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJAN_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjaNNumerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjaNNumerique03();

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
		
		/* garantit que setMessageSectionHitMjaNNumerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjaNNumerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJAN_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjaNNumerique03();

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

				
		assertEquals("le message doit valoir : 'le trafic moyen journalier annuel de l'année n de la section HIT (colonnes [153-158] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJAN_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjaNNumerique03()._________________
	

	
	/* 39 - modeCalculN. ************************/
	/**
	 * teste la méthode getMessageSectionHitModeCalculNRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitModeCalculNRenseigne01() 
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
	public void testGetMessageSectionHitModeCalculNRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitModeCalculNRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitModeCalculNRenseigne01();
		
		/* garantit que getMessageSectionHitModeCalculNRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le mode de calcul des trafics de l'année n de la section HIT (colonne [159] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MODECALCULN_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitModeCalculNRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitModeCalculNRenseigne01();

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
		
		/* garantit que setMessageSectionHitModeCalculNRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitModeCalculNRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MODECALCULN_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitModeCalculNRenseigne01();

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
		
		/* garantit que setMessageSectionHitModeCalculNRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le mode de calcul des trafics de l'année n de la section HIT (colonne [159] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MODECALCULN_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitModeCalculNRenseigne01().__________

	
	
	/**
	 * teste la méthode getMessageSectionHitModeCalculNRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitModeCalculNRegex02() 
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
	public void testGetMessageSectionHitModeCalculNRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitModeCalculNRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitModeCalculNRegex02();
		
		/* garantit que getMessageSectionHitModeCalculNRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le mode de calcul des trafics de l'année n de la section HIT (colonne [159] du HIT) doit comporter exactement 1 caractère (ou espace)'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MODECALCULN_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitModeCalculNRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitModeCalculNRegex02();

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
		
		/* garantit que setMessageSectionHitModeCalculNRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitModeCalculNRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MODECALCULN_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitModeCalculNRegex02();

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

				
		assertEquals("le message doit valoir : 'le mode de calcul des trafics de l'année n de la section HIT (colonne [159] du HIT) doit comporter exactement 1 caractère (ou espace)'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MODECALCULN_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitModeCalculNRegex02().______________
	

	
	/* 40 - pcPLN. ************************/
	/**
	 * teste la méthode getMessageSectionHitPcPLNRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcPLNRenseigne01() 
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
	public void testGetMessageSectionHitPcPLNRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcPLNRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcPLNRenseigne01();
		
		/* garantit que getMessageSectionHitPcPLNRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le pourcentage de trafic poids lourds annuel de l'année n de la section HIT (colonnes [160-162] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCPLN_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcPLNRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcPLNRenseigne01();

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
		
		/* garantit que setMessageSectionHitPcPLNRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcPLNRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCPLN_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcPLNRenseigne01();

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
		
		/* garantit que setMessageSectionHitPcPLNRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le pourcentage de trafic poids lourds annuel de l'année n de la section HIT (colonnes [160-162] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCPLN_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPcPLNRenseigne01().________________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcPLNRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcPLNRegex02() 
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
	public void testGetMessageSectionHitPcPLNRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcPLNRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcPLNRegex02();
		
		/* garantit que getMessageSectionHitPcPLNRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic poids lourds annuel de l'année n de la section HIT (colonnes [160-162] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCPLN_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcPLNRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcPLNRegex02();

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
		
		/* garantit que setMessageSectionHitPcPLNRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcPLNRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCPLN_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcPLNRegex02();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic poids lourds annuel de l'année n de la section HIT (colonnes [160-162] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCPLN_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcPLNRegex02().____________________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcPLNNumerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcPLNNumerique03() 
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
	public void testGetMessageSectionHitPcPLNNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcPLNNumerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcPLNNumerique03();
		
		/* garantit que getMessageSectionHitPcPLNNumerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic poids lourds annuel de l'année n de la section HIT (colonnes [160-162] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCPLN_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcPLNNumerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcPLNNumerique03();

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
		
		/* garantit que setMessageSectionHitPcPLNNumerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcPLNNumerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCPLN_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcPLNNumerique03();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic poids lourds annuel de l'année n de la section HIT (colonnes [160-162] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCPLN_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcPLNNumerique03().________________
	

	
	/* 41 - evaluationPLN. ************************/
	/**
	 * teste la méthode getMessageSectionHitEvaluationPLNRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitEvaluationPLNRenseigne01() 
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
	public void testGetMessageSectionHitEvaluationPLNRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitEvaluationPLNRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitEvaluationPLNRenseigne01();
		
		/* garantit que getMessageSectionHitEvaluationPLNRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'l'évaluation des pourcentages de trafic poids lourds de l'année n de la section HIT (colonne [163] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_EVALUATIONPLN_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitEvaluationPLNRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitEvaluationPLNRenseigne01();

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
		
		/* garantit que setMessageSectionHitEvaluationPLNRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitEvaluationPLNRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_EVALUATIONPLN_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitEvaluationPLNRenseigne01();

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
		
		/* garantit que setMessageSectionHitEvaluationPLNRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'l'évaluation des pourcentages de trafic poids lourds de l'année n de la section HIT (colonne [163] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_EVALUATIONPLN_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitEvaluationPLNRenseigne01().__________

	
	
	/**
	 * teste la méthode getMessageSectionHitEvaluationPLNRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitEvaluationPLNRegex02() 
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
	public void testGetMessageSectionHitEvaluationPLNRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitEvaluationPLNRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitEvaluationPLNRegex02();
		
		/* garantit que getMessageSectionHitEvaluationPLNRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'l'évaluation des pourcentages de trafic poids lourds de l'année n de la section HIT (colonne [163] du HIT) doit comporter exactement 1 caractère (ou espace)'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_EVALUATIONPLN_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitEvaluationPLNRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitEvaluationPLNRegex02();

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
		
		/* garantit que setMessageSectionHitEvaluationPLNRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitEvaluationPLNRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_EVALUATIONPLN_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitEvaluationPLNRegex02();

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

				
		assertEquals("le message doit valoir : 'l'évaluation des pourcentages de trafic poids lourds de l'année n de la section HIT (colonne [163] du HIT) doit comporter exactement 1 caractère (ou espace)'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_EVALUATIONPLN_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitEvaluationPLNRegex02().______________
	

	
	/* 42 - pcNuitAnnuelN. ************************/
	/**
	 * teste la méthode getMessageSectionHitPcNuitAnnuelNRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitAnnuelNRenseigne01() 
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
	public void testGetMessageSectionHitPcNuitAnnuelNRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitAnnuelNRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitAnnuelNRenseigne01();
		
		/* garantit que getMessageSectionHitPcNuitAnnuelNRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules annuel de l'année n de la section HIT (colonnes [164-166] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITANNUELN_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitAnnuelNRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitAnnuelNRenseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitAnnuelNRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitAnnuelNRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITANNUELN_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitAnnuelNRenseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitAnnuelNRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules annuel de l'année n de la section HIT (colonnes [164-166] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITANNUELN_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPcNuitAnnuelNRenseigne01().________________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitAnnuelNRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitAnnuelNRegex02() 
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
	public void testGetMessageSectionHitPcNuitAnnuelNRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitAnnuelNRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitAnnuelNRegex02();
		
		/* garantit que getMessageSectionHitPcNuitAnnuelNRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules annuel de l'année n de la section HIT (colonnes [164-166] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITANNUELN_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitAnnuelNRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitAnnuelNRegex02();

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
		
		/* garantit que setMessageSectionHitPcNuitAnnuelNRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitAnnuelNRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITANNUELN_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitAnnuelNRegex02();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules annuel de l'année n de la section HIT (colonnes [164-166] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITANNUELN_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitAnnuelNRegex02().____________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitAnnuelNNumerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitAnnuelNNumerique03() 
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
	public void testGetMessageSectionHitPcNuitAnnuelNNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitAnnuelNNumerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitAnnuelNNumerique03();
		
		/* garantit que getMessageSectionHitPcNuitAnnuelNNumerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules annuel de l'année n de la section HIT (colonnes [164-166] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITANNUELN_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitAnnuelNNumerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitAnnuelNNumerique03();

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
		
		/* garantit que setMessageSectionHitPcNuitAnnuelNNumerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitAnnuelNNumerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITANNUELN_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitAnnuelNNumerique03();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules annuel de l'année n de la section HIT (colonnes [164-166] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITANNUELN_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitAnnuelNNumerique03().________
	

	
	/* 43 - indiceFiabiliteMjaN. ************************/
	/**
	 * teste la méthode getMessageSectionHitIndiceFiabiliteMjaNRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitIndiceFiabiliteMjaNRenseigne01() 
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
	public void testGetMessageSectionHitIndiceFiabiliteMjaNRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitIndiceFiabiliteMjaNRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitIndiceFiabiliteMjaNRenseigne01();
		
		/* garantit que getMessageSectionHitIndiceFiabiliteMjaNRenseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'l'indice de fiabilité de la TMJA de l'année n de la section HIT (colonne [167] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_INDICEFIABLITEMJAN_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitIndiceFiabiliteMjaNRenseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitIndiceFiabiliteMjaNRenseigne01();

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
		
		/* garantit que setMessageSectionHitIndiceFiabiliteMjaNRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitIndiceFiabiliteMjaNRenseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_INDICEFIABLITEMJAN_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitIndiceFiabiliteMjaNRenseigne01();

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
		
		/* garantit que setMessageSectionHitIndiceFiabiliteMjaNRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'l'indice de fiabilité de la TMJA de l'année n de la section HIT (colonne [167] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_INDICEFIABLITEMJAN_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitIndiceFiabiliteMjaNRenseigne01().__

	
	
	/**
	 * teste la méthode getMessageSectionHitIndiceFiabiliteMjaNRegex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitIndiceFiabiliteMjaNRegex02() 
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
	public void testGetMessageSectionHitIndiceFiabiliteMjaNRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitIndiceFiabiliteMjaNRegex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitIndiceFiabiliteMjaNRegex02();
		
		/* garantit que getMessageSectionHitIndiceFiabiliteMjaNRegex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'l'indice de fiabilité de la TMJA de l'année n de la section HIT (colonne [167] du HIT) doit comporter exactement 1 caractère (ou espace)'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_INDICEFIABLITEMJAN_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitIndiceFiabiliteMjaNRegex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitIndiceFiabiliteMjaNRegex02();

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
		
		/* garantit que setMessageSectionHitIndiceFiabiliteMjaNRegex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitIndiceFiabiliteMjaNRegex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_INDICEFIABLITEMJAN_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitIndiceFiabiliteMjaNRegex02();

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

				
		assertEquals("le message doit valoir : 'l'indice de fiabilité de la TMJA de l'année n de la section HIT (colonne [167] du HIT) doit comporter exactement 1 caractère (ou espace)'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_INDICEFIABLITEMJAN_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitIndiceFiabiliteMjaNRegex02().______
	

	
	/* 44 - mjmNmois01. ************************/
	/**
	 * teste la méthode getMessageSectionHitMjmNmois01Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois01Renseigne01() 
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
	public void testGetMessageSectionHitMjmNmois01Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois01Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois01Renseigne01();
		
		/* garantit que getMessageSectionHitMjmNmois01Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de janvier (01) de l'année n en véhicules/jour de la section HIT (colonnes [168-173] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS01_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois01Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois01Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois01Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois01Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS01_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois01Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois01Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de janvier (01) de l'année n en véhicules/jour de la section HIT (colonnes [168-173] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS01_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitMjmNmois01Renseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois01Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois01Regex02() 
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
	public void testGetMessageSectionHitMjmNmois01Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois01Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois01Regex02();
		
		/* garantit que getMessageSectionHitMjmNmois01Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de janvier (01) de l'année n en véhicules/jour de la section HIT (colonnes [168-173] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS01_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois01Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois01Regex02();

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
		
		/* garantit que setMessageSectionHitMjmNmois01Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois01Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS01_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois01Regex02();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de janvier (01) de l'année n en véhicules/jour de la section HIT (colonnes [168-173] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS01_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois01Regex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois01Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois01Numerique03() 
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
	public void testGetMessageSectionHitMjmNmois01Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois01Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois01Numerique03();
		
		/* garantit que getMessageSectionHitMjmNmois01Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de janvier (01) de l'année n en véhicules/jour de la section HIT (colonnes [168-173] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS01_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois01Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois01Numerique03();

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
		
		/* garantit que setMessageSectionHitMjmNmois01Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois01Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS01_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois01Numerique03();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de janvier (01) de l'année n en véhicules/jour de la section HIT (colonnes [168-173] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS01_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois01Numerique03().___________	
	

	
	/* 45 - pcNuitNmois01. ************************/
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois01Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois01Renseigne01() 
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
	public void testGetMessageSectionHitPcNuitNmois01Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois01Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois01Renseigne01();
		
		/* garantit que getMessageSectionHitPcNuitNmois01Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de janvier (01) de l'année n de la section HIT (colonnes [174-176] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS01_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois01Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois01Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois01Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois01Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS01_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois01Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois01Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de janvier (01) de l'année n de la section HIT (colonnes [174-176] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS01_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPcNuitNmois01Renseigne01().________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois01Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois01Regex02() 
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
	public void testGetMessageSectionHitPcNuitNmois01Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois01Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois01Regex02();
		
		/* garantit que getMessageSectionHitPcNuitNmois01Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de janvier (01) de l'année n de la section HIT (colonnes [174-176] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS01_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois01Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois01Regex02();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois01Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois01Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS01_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois01Regex02();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de janvier (01) de l'année n de la section HIT (colonnes [174-176] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS01_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois01Regex02().____________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois01Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois01Numerique03() 
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
	public void testGetMessageSectionHitPcNuitNmois01Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois01Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois01Numerique03();
		
		/* garantit que getMessageSectionHitPcNuitNmois01Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de janvier (01) de l'année n de la section HIT (colonnes [174-176] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS01_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois01Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois01Numerique03();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois01Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois01Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS01_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois01Numerique03();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de janvier (01) de l'année n de la section HIT (colonnes [174-176] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS01_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois01Numerique03().________
	

	
	/* 46 - mjmNmois02. ************************/
	/**
	 * teste la méthode getMessageSectionHitMjmNmois02Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois02Renseigne01() 
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
	public void testGetMessageSectionHitMjmNmois02Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois02Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois02Renseigne01();
		
		/* garantit que getMessageSectionHitMjmNmois02Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de février (02) de l'année n en véhicules/jour de la section HIT (colonnes [177-182] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS02_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois02Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois02Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois02Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois02Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS02_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois02Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois02Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de février (02) de l'année n en véhicules/jour de la section HIT (colonnes [177-182] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS02_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitMjmNmois02Renseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois02Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois02Regex02() 
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
	public void testGetMessageSectionHitMjmNmois02Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois02Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois02Regex02();
		
		/* garantit que getMessageSectionHitMjmNmois02Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de février (02) de l'année n en véhicules/jour de la section HIT (colonnes [177-182] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS02_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois02Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois02Regex02();

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
		
		/* garantit que setMessageSectionHitMjmNmois02Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois02Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS02_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois02Regex02();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de février (02) de l'année n en véhicules/jour de la section HIT (colonnes [177-182] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS02_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois02Regex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois02Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois02Numerique03() 
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
	public void testGetMessageSectionHitMjmNmois02Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois02Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois02Numerique03();
		
		/* garantit que getMessageSectionHitMjmNmois02Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de février (02) de l'année n en véhicules/jour de la section HIT (colonnes [177-182] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS02_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois02Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois02Numerique03();

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
		
		/* garantit que setMessageSectionHitMjmNmois02Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois02Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS02_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois02Numerique03();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de février (02) de l'année n en véhicules/jour de la section HIT (colonnes [177-182] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS02_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois02Numerique03().___________	
	

	
	/* 47 - pcNuitNmois02. ************************/
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois02Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois02Renseigne01() 
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
	public void testGetMessageSectionHitPcNuitNmois02Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois02Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois02Renseigne01();
		
		/* garantit que getMessageSectionHitPcNuitNmois02Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de février (02) de l'année n de la section HIT (colonnes [183-185] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS02_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois02Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois02Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois02Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois02Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS02_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois02Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois02Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de février (02) de l'année n de la section HIT (colonnes [183-185] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS02_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPcNuitNmois02Renseigne01().________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois02Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois02Regex02() 
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
	public void testGetMessageSectionHitPcNuitNmois02Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois02Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois02Regex02();
		
		/* garantit que getMessageSectionHitPcNuitNmois02Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de février (02) de l'année n de la section HIT (colonnes [183-185] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS02_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois02Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois02Regex02();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois02Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois02Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS02_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois02Regex02();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de février (02) de l'année n de la section HIT (colonnes [183-185] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS02_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois02Regex02().____________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois02Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois02Numerique03() 
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
	public void testGetMessageSectionHitPcNuitNmois02Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois02Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois02Numerique03();
		
		/* garantit que getMessageSectionHitPcNuitNmois02Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de février (02) de l'année n de la section HIT (colonnes [183-185] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS02_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois02Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois02Numerique03();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois02Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois02Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS02_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois02Numerique03();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de février (02) de l'année n de la section HIT (colonnes [183-185] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS02_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois02Numerique03().________
	

	
	/* 48 - mjmNmois03. ************************/
	/**
	 * teste la méthode getMessageSectionHitMjmNmois03Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois03Renseigne01() 
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
	public void testGetMessageSectionHitMjmNmois03Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois03Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois03Renseigne01();
		
		/* garantit que getMessageSectionHitMjmNmois03Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de mars (03) de l'année n en véhicules/jour de la section HIT (colonnes [186-191] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS03_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois03Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois03Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois03Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois03Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS03_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois03Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois03Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de mars (03) de l'année n en véhicules/jour de la section HIT (colonnes [186-191] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS03_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitMjmNmois03Renseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois03Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois03Regex02() 
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
	public void testGetMessageSectionHitMjmNmois03Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois03Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois03Regex02();
		
		/* garantit que getMessageSectionHitMjmNmois03Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de mars (03) de l'année n en véhicules/jour de la section HIT (colonnes [186-191] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS03_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois03Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois03Regex02();

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
		
		/* garantit que setMessageSectionHitMjmNmois03Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois03Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS03_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois03Regex02();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de mars (03) de l'année n en véhicules/jour de la section HIT (colonnes [186-191] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS03_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois03Regex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois03Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois03Numerique03() 
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
	public void testGetMessageSectionHitMjmNmois03Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois03Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois03Numerique03();
		
		/* garantit que getMessageSectionHitMjmNmois03Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de mars (03) de l'année n en véhicules/jour de la section HIT (colonnes [186-191] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS03_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois03Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois03Numerique03();

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
		
		/* garantit que setMessageSectionHitMjmNmois03Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois03Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS03_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois03Numerique03();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de mars (03) de l'année n en véhicules/jour de la section HIT (colonnes [186-191] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS03_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois03Numerique03().___________	
	

	
	/* 49 - pcNuitNmois03. ************************/
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois03Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois03Renseigne01() 
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
	public void testGetMessageSectionHitPcNuitNmois03Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois03Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois03Renseigne01();
		
		/* garantit que getMessageSectionHitPcNuitNmois03Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de mars (03) de l'année n de la section HIT (colonnes [192-194] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS03_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois03Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois03Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois03Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois03Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS03_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois03Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois03Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de mars (03) de l'année n de la section HIT (colonnes [192-194] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS03_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPcNuitNmois03Renseigne01().________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois03Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois03Regex02() 
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
	public void testGetMessageSectionHitPcNuitNmois03Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois03Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois03Regex02();
		
		/* garantit que getMessageSectionHitPcNuitNmois03Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de mars (03) de l'année n de la section HIT (colonnes [192-194] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS03_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois03Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois03Regex02();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois03Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois03Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS03_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois03Regex02();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de mars (03) de l'année n de la section HIT (colonnes [192-194] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS03_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois03Regex02().____________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois03Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois03Numerique03() 
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
	public void testGetMessageSectionHitPcNuitNmois03Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois03Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois03Numerique03();
		
		/* garantit que getMessageSectionHitPcNuitNmois03Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de mars (03) de l'année n de la section HIT (colonnes [192-194] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS03_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois03Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois03Numerique03();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois03Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois03Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS03_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois03Numerique03();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de mars (03) de l'année n de la section HIT (colonnes [192-194] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS03_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois03Numerique03().________
	

	
	/* 50 - mjmNmois04. ************************/
	/**
	 * teste la méthode getMessageSectionHitMjmNmois04Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois04Renseigne01() 
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
	public void testGetMessageSectionHitMjmNmois04Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois04Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois04Renseigne01();
		
		/* garantit que getMessageSectionHitMjmNmois04Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de avril (04) de l'année n en véhicules/jour de la section HIT (colonnes [195-200] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS04_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois04Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois04Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois04Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois04Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS04_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois04Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois04Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de avril (04) de l'année n en véhicules/jour de la section HIT (colonnes [195-200] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS04_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitMjmNmois04Renseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois04Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois04Regex02() 
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
	public void testGetMessageSectionHitMjmNmois04Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois04Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois04Regex02();
		
		/* garantit que getMessageSectionHitMjmNmois04Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de avril (04) de l'année n en véhicules/jour de la section HIT (colonnes [195-200] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS04_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois04Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois04Regex02();

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
		
		/* garantit que setMessageSectionHitMjmNmois04Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois04Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS04_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois04Regex02();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de avril (04) de l'année n en véhicules/jour de la section HIT (colonnes [195-200] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS04_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois04Regex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois04Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois04Numerique03() 
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
	public void testGetMessageSectionHitMjmNmois04Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois04Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois04Numerique03();
		
		/* garantit que getMessageSectionHitMjmNmois04Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de avril (04) de l'année n en véhicules/jour de la section HIT (colonnes [195-200] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS04_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois04Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois04Numerique03();

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
		
		/* garantit que setMessageSectionHitMjmNmois04Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois04Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS04_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois04Numerique03();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de avril (04) de l'année n en véhicules/jour de la section HIT (colonnes [195-200] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS04_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois04Numerique03().___________	
	

	
	/* 51 - pcNuitNmois04. ************************/
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois04Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois04Renseigne01() 
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
	public void testGetMessageSectionHitPcNuitNmois04Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois04Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois04Renseigne01();
		
		/* garantit que getMessageSectionHitPcNuitNmois04Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de avril (04) de l'année n de la section HIT (colonnes [201-203] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS04_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois04Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois04Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois04Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois04Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS04_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois04Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois04Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de avril (04) de l'année n de la section HIT (colonnes [201-203] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS04_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPcNuitNmois04Renseigne01().________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois04Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois04Regex02() 
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
	public void testGetMessageSectionHitPcNuitNmois04Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois04Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois04Regex02();
		
		/* garantit que getMessageSectionHitPcNuitNmois04Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de avril (04) de l'année n de la section HIT (colonnes [201-203] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS04_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois04Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois04Regex02();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois04Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois04Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS04_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois04Regex02();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de avril (04) de l'année n de la section HIT (colonnes [201-203] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS04_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois04Regex02().____________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois04Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois04Numerique03() 
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
	public void testGetMessageSectionHitPcNuitNmois04Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois04Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois04Numerique03();
		
		/* garantit que getMessageSectionHitPcNuitNmois04Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de avril (04) de l'année n de la section HIT (colonnes [201-203] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS04_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois04Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois04Numerique03();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois04Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois04Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS04_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois04Numerique03();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de avril (04) de l'année n de la section HIT (colonnes [201-203] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS04_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois04Numerique03().________

	

	
	/* 52 - mjmNmois05. ************************/
	/**
	 * teste la méthode getMessageSectionHitMjmNmois05Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois05Renseigne01() 
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
	public void testGetMessageSectionHitMjmNmois05Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois05Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois05Renseigne01();
		
		/* garantit que getMessageSectionHitMjmNmois05Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de mai (05) de l'année n en véhicules/jour de la section HIT (colonnes [204-209] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS05_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois05Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois05Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois05Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois05Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS05_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois05Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois05Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de mai (05) de l'année n en véhicules/jour de la section HIT (colonnes [204-209] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS05_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitMjmNmois05Renseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois05Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois05Regex02() 
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
	public void testGetMessageSectionHitMjmNmois05Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois05Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois05Regex02();
		
		/* garantit que getMessageSectionHitMjmNmois05Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de mai (05) de l'année n en véhicules/jour de la section HIT (colonnes [204-209] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS05_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois05Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois05Regex02();

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
		
		/* garantit que setMessageSectionHitMjmNmois05Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois05Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS05_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois05Regex02();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de mai (05) de l'année n en véhicules/jour de la section HIT (colonnes [204-209] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS05_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois05Regex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois05Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois05Numerique03() 
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
	public void testGetMessageSectionHitMjmNmois05Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois05Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois05Numerique03();
		
		/* garantit que getMessageSectionHitMjmNmois05Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de mai (05) de l'année n en véhicules/jour de la section HIT (colonnes [204-209] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS05_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois05Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois05Numerique03();

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
		
		/* garantit que setMessageSectionHitMjmNmois05Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois05Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS05_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois05Numerique03();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de mai (05) de l'année n en véhicules/jour de la section HIT (colonnes [204-209] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS05_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois05Numerique03().___________	
	

	
	/* 53 - pcNuitNmois05. ************************/
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois05Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois05Renseigne01() 
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
	public void testGetMessageSectionHitPcNuitNmois05Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois05Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois05Renseigne01();
		
		/* garantit que getMessageSectionHitPcNuitNmois05Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de mai (05) de l'année n de la section HIT (colonnes [210-212] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS05_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois05Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois05Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois05Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois05Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS05_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois05Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois05Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de mai (05) de l'année n de la section HIT (colonnes [210-212] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS05_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPcNuitNmois05Renseigne01().________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois05Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois05Regex02() 
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
	public void testGetMessageSectionHitPcNuitNmois05Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois05Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois05Regex02();
		
		/* garantit que getMessageSectionHitPcNuitNmois05Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de mai (05) de l'année n de la section HIT (colonnes [210-212] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS05_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois05Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois05Regex02();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois05Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois05Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS05_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois05Regex02();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de mai (05) de l'année n de la section HIT (colonnes [210-212] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS05_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois05Regex02().____________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois05Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois05Numerique03() 
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
	public void testGetMessageSectionHitPcNuitNmois05Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois05Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois05Numerique03();
		
		/* garantit que getMessageSectionHitPcNuitNmois05Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de mai (05) de l'année n de la section HIT (colonnes [210-212] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS05_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois05Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois05Numerique03();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois05Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois05Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS05_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois05Numerique03();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de mai (05) de l'année n de la section HIT (colonnes [210-212] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS05_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois05Numerique03().________

	

	
	/* 54 - mjmNmois06. ************************/
	/**
	 * teste la méthode getMessageSectionHitMjmNmois06Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois06Renseigne01() 
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
	public void testGetMessageSectionHitMjmNmois06Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois06Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois06Renseigne01();
		
		/* garantit que getMessageSectionHitMjmNmois06Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de juin (06) de l'année n en véhicules/jour de la section HIT (colonnes [213-218] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS06_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois06Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois06Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois06Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois06Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS06_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois06Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois06Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de juin (06) de l'année n en véhicules/jour de la section HIT (colonnes [213-218] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS06_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitMjmNmois06Renseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois06Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois06Regex02() 
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
	public void testGetMessageSectionHitMjmNmois06Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois06Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois06Regex02();
		
		/* garantit que getMessageSectionHitMjmNmois06Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de juin (06) de l'année n en véhicules/jour de la section HIT (colonnes [213-218] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS06_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois06Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois06Regex02();

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
		
		/* garantit que setMessageSectionHitMjmNmois06Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois06Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS06_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois06Regex02();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de juin (06) de l'année n en véhicules/jour de la section HIT (colonnes [213-218] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS06_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois06Regex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois06Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois06Numerique03() 
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
	public void testGetMessageSectionHitMjmNmois06Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois06Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois06Numerique03();
		
		/* garantit que getMessageSectionHitMjmNmois06Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de juin (06) de l'année n en véhicules/jour de la section HIT (colonnes [213-218] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS06_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois06Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois06Numerique03();

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
		
		/* garantit que setMessageSectionHitMjmNmois06Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois06Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS06_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois06Numerique03();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de juin (06) de l'année n en véhicules/jour de la section HIT (colonnes [213-218] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS06_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois06Numerique03().___________	
	

	
	/* 55 - pcNuitNmois06. ************************/
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois06Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois06Renseigne01() 
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
	public void testGetMessageSectionHitPcNuitNmois06Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois06Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois06Renseigne01();
		
		/* garantit que getMessageSectionHitPcNuitNmois06Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de juin (06) de l'année n de la section HIT (colonnes [219-221] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS06_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois06Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois06Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois06Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois06Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS06_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois06Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois06Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de juin (06) de l'année n de la section HIT (colonnes [219-221] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS06_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPcNuitNmois06Renseigne01().________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois06Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois06Regex02() 
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
	public void testGetMessageSectionHitPcNuitNmois06Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois06Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois06Regex02();
		
		/* garantit que getMessageSectionHitPcNuitNmois06Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de juin (06) de l'année n de la section HIT (colonnes [219-221] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS06_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois06Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois06Regex02();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois06Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois06Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS06_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois06Regex02();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de juin (06) de l'année n de la section HIT (colonnes [219-221] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS06_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois06Regex02().____________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois06Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois06Numerique03() 
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
	public void testGetMessageSectionHitPcNuitNmois06Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois06Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois06Numerique03();
		
		/* garantit que getMessageSectionHitPcNuitNmois06Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de juin (06) de l'année n de la section HIT (colonnes [219-221] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS06_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois06Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois06Numerique03();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois06Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois06Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS06_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois06Numerique03();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de juin (06) de l'année n de la section HIT (colonnes [219-221] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS06_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois06Numerique03().________

	

	
	/* 56 - mjmNmois07. ************************/
	/**
	 * teste la méthode getMessageSectionHitMjmNmois07Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois07Renseigne01() 
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
	public void testGetMessageSectionHitMjmNmois07Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois07Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois07Renseigne01();
		
		/* garantit que getMessageSectionHitMjmNmois07Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de juillet (07) de l'année n en véhicules/jour de la section HIT (colonnes [222-227] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS07_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois07Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois07Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois07Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois07Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS07_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois07Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois07Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de juillet (07) de l'année n en véhicules/jour de la section HIT (colonnes [222-227] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS07_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitMjmNmois07Renseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois07Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois07Regex02() 
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
	public void testGetMessageSectionHitMjmNmois07Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois07Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois07Regex02();
		
		/* garantit que getMessageSectionHitMjmNmois07Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de juillet (07) de l'année n en véhicules/jour de la section HIT (colonnes [222-227] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS07_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois07Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois07Regex02();

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
		
		/* garantit que setMessageSectionHitMjmNmois07Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois07Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS07_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois07Regex02();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de juillet (07) de l'année n en véhicules/jour de la section HIT (colonnes [222-227] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS07_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois07Regex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois07Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois07Numerique03() 
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
	public void testGetMessageSectionHitMjmNmois07Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois07Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois07Numerique03();
		
		/* garantit que getMessageSectionHitMjmNmois07Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de juillet (07) de l'année n en véhicules/jour de la section HIT (colonnes [222-227] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS07_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois07Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois07Numerique03();

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
		
		/* garantit que setMessageSectionHitMjmNmois07Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois07Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS07_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois07Numerique03();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de juillet (07) de l'année n en véhicules/jour de la section HIT (colonnes [222-227] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS07_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois07Numerique03().___________	
	

	
	/* 57 - pcNuitNmois07. ************************/
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois07Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois07Renseigne01() 
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
	public void testGetMessageSectionHitPcNuitNmois07Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois07Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois07Renseigne01();
		
		/* garantit que getMessageSectionHitPcNuitNmois07Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de juillet (07) de l'année n de la section HIT (colonnes [228-230] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS07_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois07Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois07Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois07Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois07Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS07_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois07Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois07Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de juillet (07) de l'année n de la section HIT (colonnes [228-230] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS07_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPcNuitNmois07Renseigne01().________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois07Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois07Regex02() 
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
	public void testGetMessageSectionHitPcNuitNmois07Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois07Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois07Regex02();
		
		/* garantit que getMessageSectionHitPcNuitNmois07Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de juillet (07) de l'année n de la section HIT (colonnes [228-230] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS07_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois07Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois07Regex02();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois07Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois07Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS07_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois07Regex02();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de juillet (07) de l'année n de la section HIT (colonnes [228-230] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS07_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois07Regex02().____________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois07Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois07Numerique03() 
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
	public void testGetMessageSectionHitPcNuitNmois07Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois07Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois07Numerique03();
		
		/* garantit que getMessageSectionHitPcNuitNmois07Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de juillet (07) de l'année n de la section HIT (colonnes [228-230] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS07_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois07Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois07Numerique03();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois07Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois07Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS07_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois07Numerique03();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de juillet (07) de l'année n de la section HIT (colonnes [228-230] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS07_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois07Numerique03().________

	

	
	/* 58 - mjmNmois08. ************************/
	/**
	 * teste la méthode getMessageSectionHitMjmNmois08Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois08Renseigne01() 
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
	public void testGetMessageSectionHitMjmNmois08Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois08Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois08Renseigne01();
		
		/* garantit que getMessageSectionHitMjmNmois08Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de août (08) de l'année n en véhicules/jour de la section HIT (colonnes [231-236] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS08_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois08Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois08Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois08Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois08Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS08_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois08Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois08Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de août (08) de l'année n en véhicules/jour de la section HIT (colonnes [231-236] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS08_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitMjmNmois08Renseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois08Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois08Regex02() 
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
	public void testGetMessageSectionHitMjmNmois08Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois08Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois08Regex02();
		
		/* garantit que getMessageSectionHitMjmNmois08Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de août (08) de l'année n en véhicules/jour de la section HIT (colonnes [231-236] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS08_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois08Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois08Regex02();

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
		
		/* garantit que setMessageSectionHitMjmNmois08Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois08Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS08_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois08Regex02();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de août (08) de l'année n en véhicules/jour de la section HIT (colonnes [231-236] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS08_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois08Regex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois08Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois08Numerique03() 
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
	public void testGetMessageSectionHitMjmNmois08Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois08Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois08Numerique03();
		
		/* garantit que getMessageSectionHitMjmNmois08Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de août (08) de l'année n en véhicules/jour de la section HIT (colonnes [231-236] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS08_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois08Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois08Numerique03();

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
		
		/* garantit que setMessageSectionHitMjmNmois08Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois08Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS08_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois08Numerique03();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de août (08) de l'année n en véhicules/jour de la section HIT (colonnes [231-236] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS08_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois08Numerique03().___________	
	

	
	/* 59 - pcNuitNmois08. ************************/
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois08Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois08Renseigne01() 
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
	public void testGetMessageSectionHitPcNuitNmois08Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois08Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois08Renseigne01();
		
		/* garantit que getMessageSectionHitPcNuitNmois08Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de août (08) de l'année n de la section HIT (colonnes [237-239] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS08_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois08Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois08Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois08Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois08Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS08_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois08Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois08Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de août (08) de l'année n de la section HIT (colonnes [237-239] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS08_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPcNuitNmois08Renseigne01().________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois08Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois08Regex02() 
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
	public void testGetMessageSectionHitPcNuitNmois08Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois08Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois08Regex02();
		
		/* garantit que getMessageSectionHitPcNuitNmois08Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de août (08) de l'année n de la section HIT (colonnes [237-239] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS08_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois08Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois08Regex02();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois08Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois08Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS08_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois08Regex02();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de août (08) de l'année n de la section HIT (colonnes [237-239] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS08_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois08Regex02().____________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois08Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois08Numerique03() 
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
	public void testGetMessageSectionHitPcNuitNmois08Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois08Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois08Numerique03();
		
		/* garantit que getMessageSectionHitPcNuitNmois08Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de août (08) de l'année n de la section HIT (colonnes [237-239] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS08_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois08Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois08Numerique03();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois08Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois08Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS08_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois08Numerique03();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de août (08) de l'année n de la section HIT (colonnes [237-239] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS08_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois08Numerique03().________

	

	
	/* 60 - mjmNmois09. ************************/
	/**
	 * teste la méthode getMessageSectionHitMjmNmois09Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois09Renseigne01() 
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
	public void testGetMessageSectionHitMjmNmois09Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois09Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois09Renseigne01();
		
		/* garantit que getMessageSectionHitMjmNmois09Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de septembre (09) de l'année n en véhicules/jour de la section HIT (colonnes [240-245] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS09_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois09Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois09Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois09Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois09Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS09_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois09Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois09Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de septembre (09) de l'année n en véhicules/jour de la section HIT (colonnes [240-245] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS09_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitMjmNmois09Renseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois09Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois09Regex02() 
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
	public void testGetMessageSectionHitMjmNmois09Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois09Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois09Regex02();
		
		/* garantit que getMessageSectionHitMjmNmois09Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de septembre (09) de l'année n en véhicules/jour de la section HIT (colonnes [240-245] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS09_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois09Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois09Regex02();

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
		
		/* garantit que setMessageSectionHitMjmNmois09Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois09Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS09_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois09Regex02();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de septembre (09) de l'année n en véhicules/jour de la section HIT (colonnes [240-245] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS09_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois09Regex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois09Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois09Numerique03() 
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
	public void testGetMessageSectionHitMjmNmois09Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois09Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois09Numerique03();
		
		/* garantit que getMessageSectionHitMjmNmois09Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de septembre (09) de l'année n en véhicules/jour de la section HIT (colonnes [240-245] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS09_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois09Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois09Numerique03();

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
		
		/* garantit que setMessageSectionHitMjmNmois09Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois09Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS09_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois09Numerique03();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de septembre (09) de l'année n en véhicules/jour de la section HIT (colonnes [240-245] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS09_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois09Numerique03().___________	
	

	
	/* 61 - pcNuitNmois09. ************************/
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois09Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois09Renseigne01() 
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
	public void testGetMessageSectionHitPcNuitNmois09Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois09Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois09Renseigne01();
		
		/* garantit que getMessageSectionHitPcNuitNmois09Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de septembre (09) de l'année n de la section HIT (colonnes [246-248] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS09_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois09Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois09Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois09Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois09Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS09_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois09Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois09Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de septembre (09) de l'année n de la section HIT (colonnes [246-248] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS09_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPcNuitNmois09Renseigne01().________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois09Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois09Regex02() 
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
	public void testGetMessageSectionHitPcNuitNmois09Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois09Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois09Regex02();
		
		/* garantit que getMessageSectionHitPcNuitNmois09Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de septembre (09) de l'année n de la section HIT (colonnes [246-248] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS09_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois09Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois09Regex02();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois09Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois09Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS09_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois09Regex02();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de septembre (09) de l'année n de la section HIT (colonnes [246-248] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS09_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois09Regex02().____________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois09Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois09Numerique03() 
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
	public void testGetMessageSectionHitPcNuitNmois09Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois09Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois09Numerique03();
		
		/* garantit que getMessageSectionHitPcNuitNmois09Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de septembre (09) de l'année n de la section HIT (colonnes [246-248] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS09_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois09Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois09Numerique03();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois09Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois09Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS09_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois09Numerique03();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de septembre (09) de l'année n de la section HIT (colonnes [246-248] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS09_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois09Numerique03().________

	

	
	/* 62 - mjmNmois10. ************************/
	/**
	 * teste la méthode getMessageSectionHitMjmNmois10Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois10Renseigne01() 
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
	public void testGetMessageSectionHitMjmNmois10Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois10Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois10Renseigne01();
		
		/* garantit que getMessageSectionHitMjmNmois10Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de octobre (10) de l'année n en véhicules/jour de la section HIT (colonnes [249-254] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS10_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois10Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois10Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois10Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois10Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS10_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois10Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois10Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de octobre (10) de l'année n en véhicules/jour de la section HIT (colonnes [249-254] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS10_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitMjmNmois10Renseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois10Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois10Regex02() 
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
	public void testGetMessageSectionHitMjmNmois10Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois10Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois10Regex02();
		
		/* garantit que getMessageSectionHitMjmNmois10Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de octobre (10) de l'année n en véhicules/jour de la section HIT (colonnes [249-254] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS10_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois10Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois10Regex02();

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
		
		/* garantit que setMessageSectionHitMjmNmois10Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois10Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS10_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois10Regex02();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de octobre (10) de l'année n en véhicules/jour de la section HIT (colonnes [249-254] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS10_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois10Regex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois10Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois10Numerique03() 
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
	public void testGetMessageSectionHitMjmNmois10Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois10Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois10Numerique03();
		
		/* garantit que getMessageSectionHitMjmNmois10Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de octobre (10) de l'année n en véhicules/jour de la section HIT (colonnes [249-254] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS10_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois10Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois10Numerique03();

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
		
		/* garantit que setMessageSectionHitMjmNmois10Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois10Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS10_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois10Numerique03();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de octobre (10) de l'année n en véhicules/jour de la section HIT (colonnes [249-254] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS10_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois10Numerique03().___________	
	

	
	/* 63 - pcNuitNmois10. ************************/
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois10Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois10Renseigne01() 
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
	public void testGetMessageSectionHitPcNuitNmois10Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois10Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois10Renseigne01();
		
		/* garantit que getMessageSectionHitPcNuitNmois10Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de octobre (10) de l'année n de la section HIT (colonnes [255-257] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS10_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois10Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois10Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois10Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois10Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS10_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois10Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois10Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de octobre (10) de l'année n de la section HIT (colonnes [255-257] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS10_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPcNuitNmois10Renseigne01().________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois10Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois10Regex02() 
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
	public void testGetMessageSectionHitPcNuitNmois10Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois10Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois10Regex02();
		
		/* garantit que getMessageSectionHitPcNuitNmois10Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de octobre (10) de l'année n de la section HIT (colonnes [255-257] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS10_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois10Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois10Regex02();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois10Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois10Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS10_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois10Regex02();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de octobre (10) de l'année n de la section HIT (colonnes [255-257] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS10_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois10Regex02().____________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois10Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois10Numerique03() 
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
	public void testGetMessageSectionHitPcNuitNmois10Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois10Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois10Numerique03();
		
		/* garantit que getMessageSectionHitPcNuitNmois10Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de octobre (10) de l'année n de la section HIT (colonnes [255-257] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS10_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois10Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois10Numerique03();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois10Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois10Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS10_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois10Numerique03();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de octobre (10) de l'année n de la section HIT (colonnes [255-257] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS10_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois10Numerique03().________

	

	
	/* 64 - mjmNmois11. ************************/
	/**
	 * teste la méthode getMessageSectionHitMjmNmois11Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois11Renseigne01() 
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
	public void testGetMessageSectionHitMjmNmois11Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois11Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois11Renseigne01();
		
		/* garantit que getMessageSectionHitMjmNmois11Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de novembre (11) de l'année n en véhicules/jour de la section HIT (colonnes [258-263] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS11_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois11Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois11Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois11Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois11Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS11_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois11Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois11Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de novembre (11) de l'année n en véhicules/jour de la section HIT (colonnes [258-263] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS11_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitMjmNmois11Renseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois11Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois11Regex02() 
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
	public void testGetMessageSectionHitMjmNmois11Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois11Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois11Regex02();
		
		/* garantit que getMessageSectionHitMjmNmois11Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de novembre (11) de l'année n en véhicules/jour de la section HIT (colonnes [258-263] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS11_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois11Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois11Regex02();

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
		
		/* garantit que setMessageSectionHitMjmNmois11Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois11Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS11_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois11Regex02();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de novembre (11) de l'année n en véhicules/jour de la section HIT (colonnes [258-263] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS11_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois11Regex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois11Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois11Numerique03() 
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
	public void testGetMessageSectionHitMjmNmois11Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois11Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois11Numerique03();
		
		/* garantit que getMessageSectionHitMjmNmois11Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de novembre (11) de l'année n en véhicules/jour de la section HIT (colonnes [258-263] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS11_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois11Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois11Numerique03();

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
		
		/* garantit que setMessageSectionHitMjmNmois11Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois11Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS11_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois11Numerique03();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de novembre (11) de l'année n en véhicules/jour de la section HIT (colonnes [258-263] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS11_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois11Numerique03().___________	
	

	
	/* 65 - pcNuitNmois11. ************************/
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois11Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois11Renseigne01() 
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
	public void testGetMessageSectionHitPcNuitNmois11Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois11Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois11Renseigne01();
		
		/* garantit que getMessageSectionHitPcNuitNmois11Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de novembre (11) de l'année n de la section HIT (colonnes [264-266] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS11_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois11Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois11Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois11Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois11Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS11_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois11Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois11Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de novembre (11) de l'année n de la section HIT (colonnes [264-266] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS11_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPcNuitNmois11Renseigne01().________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois11Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois11Regex02() 
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
	public void testGetMessageSectionHitPcNuitNmois11Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois11Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois11Regex02();
		
		/* garantit que getMessageSectionHitPcNuitNmois11Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de novembre (11) de l'année n de la section HIT (colonnes [264-266] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS11_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois11Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois11Regex02();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois11Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois11Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS11_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois11Regex02();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de novembre (11) de l'année n de la section HIT (colonnes [264-266] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS11_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois11Regex02().____________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois11Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois11Numerique03() 
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
	public void testGetMessageSectionHitPcNuitNmois11Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois11Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois11Numerique03();
		
		/* garantit que getMessageSectionHitPcNuitNmois11Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de novembre (11) de l'année n de la section HIT (colonnes [264-266] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS11_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois11Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois11Numerique03();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois11Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois11Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS11_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois11Numerique03();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de novembre (11) de l'année n de la section HIT (colonnes [264-266] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS11_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois11Numerique03().________

	

	
	/* 66 - mjmNmois12. ************************/
	/**
	 * teste la méthode getMessageSectionHitMjmNmois12Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois12Renseigne01() 
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
	public void testGetMessageSectionHitMjmNmois12Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois12Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois12Renseigne01();
		
		/* garantit que getMessageSectionHitMjmNmois12Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de décembre (12) de l'année n en véhicules/jour de la section HIT (colonnes [267-272] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS12_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois12Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois12Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois12Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois12Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS12_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois12Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjmNmois12Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de décembre (12) de l'année n en véhicules/jour de la section HIT (colonnes [267-272] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS12_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitMjmNmois12Renseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois12Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois12Regex02() 
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
	public void testGetMessageSectionHitMjmNmois12Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois12Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois12Regex02();
		
		/* garantit que getMessageSectionHitMjmNmois12Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de décembre (12) de l'année n en véhicules/jour de la section HIT (colonnes [267-272] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS12_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois12Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois12Regex02();

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
		
		/* garantit que setMessageSectionHitMjmNmois12Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois12Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS12_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois12Regex02();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de décembre (12) de l'année n en véhicules/jour de la section HIT (colonnes [267-272] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS12_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois12Regex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjmNmois12Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjmNmois12Numerique03() 
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
	public void testGetMessageSectionHitMjmNmois12Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjmNmois12Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois12Numerique03();
		
		/* garantit que getMessageSectionHitMjmNmois12Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de décembre (12) de l'année n en véhicules/jour de la section HIT (colonnes [267-272] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS12_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois12Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjmNmois12Numerique03();

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
		
		/* garantit que setMessageSectionHitMjmNmois12Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjmNmois12Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJMNMOIS12_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjmNmois12Numerique03();

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

				
		assertEquals("le message doit valoir : 'la moyenne journalière mensuelle du mois de décembre (12) de l'année n en véhicules/jour de la section HIT (colonnes [267-272] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJMNMOIS12_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjmNmois12Numerique03().___________	
	

	
	/* 67 - pcNuitNmois12. ************************/
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois12Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois12Renseigne01() 
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
	public void testGetMessageSectionHitPcNuitNmois12Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois12Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois12Renseigne01();
		
		/* garantit que getMessageSectionHitPcNuitNmois12Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de décembre (12) de l'année n de la section HIT (colonnes [273-275] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS12_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois12Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois12Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois12Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois12Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS12_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois12Renseigne01();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois12Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de décembre (12) de l'année n de la section HIT (colonnes [273-275] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS12_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitPcNuitNmois12Renseigne01().________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois12Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois12Regex02() 
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
	public void testGetMessageSectionHitPcNuitNmois12Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois12Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois12Regex02();
		
		/* garantit que getMessageSectionHitPcNuitNmois12Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de décembre (12) de l'année n de la section HIT (colonnes [273-275] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS12_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois12Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois12Regex02();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois12Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois12Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS12_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois12Regex02();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de décembre (12) de l'année n de la section HIT (colonnes [273-275] du HIT) doit comporter exactement 3 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS12_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois12Regex02().____________

	
	
	/**
	 * teste la méthode getMessageSectionHitPcNuitNmois12Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitPcNuitNmois12Numerique03() 
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
	public void testGetMessageSectionHitPcNuitNmois12Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitPcNuitNmois12Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois12Numerique03();
		
		/* garantit que getMessageSectionHitPcNuitNmois12Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de décembre (12) de l'année n de la section HIT (colonnes [273-275] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS12_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois12Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitPcNuitNmois12Numerique03();

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
		
		/* garantit que setMessageSectionHitPcNuitNmois12Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitPcNuitNmois12Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_PCNUITNMOIS12_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitPcNuitNmois12Numerique03();

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

				
		assertEquals("le message doit valoir : 'le pourcentage de trafic de nuit tous véhicules mensuel du mois de décembre (12) de l'année n de la section HIT (colonnes [273-275] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_PCNUITNMOIS12_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitPcNuitNmois12Numerique03().________
	

	
	/* 68 - zoneLibre3. ************************/
	/**
	 * teste la méthode getMessageSectionHitZoneLibre3Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitZoneLibre3Renseigne01() 
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
	public void testGetMessageSectionHitZoneLibre3Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitZoneLibre3Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitZoneLibre3Renseigne01();
		
		/* garantit que getMessageSectionHitZoneLibre3Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'la zone libre de la section HIT (colonnes [276-287] du HIT) doit être renseignée (avec des espaces)'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ZONELIBRE3_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitZoneLibre3Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitZoneLibre3Renseigne01();

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
		
		/* garantit que setMessageSectionHitZoneLibre3Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitZoneLibre3Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ZONELIBRE3_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitZoneLibre3Renseigne01();

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
		
		/* garantit que setMessageSectionHitZoneLibre3Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la zone libre de la section HIT (colonnes [276-287] du HIT) doit être renseignée (avec des espaces)'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ZONELIBRE3_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitZoneLibre3Renseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitZoneLibre3Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitZoneLibre3Regex02() 
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
	public void testGetMessageSectionHitZoneLibre3Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitZoneLibre3Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitZoneLibre3Regex02();
		
		/* garantit que getMessageSectionHitZoneLibre3Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'la zone libre de la section HIT (colonnes [276-287] du HIT) doit comporter exactement 12 espaces'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ZONELIBRE3_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitZoneLibre3Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitZoneLibre3Regex02();

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
		
		/* garantit que setMessageSectionHitZoneLibre3Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitZoneLibre3Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ZONELIBRE3_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitZoneLibre3Regex02();

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

				
		assertEquals("le message doit valoir : 'la zone libre de la section HIT (colonnes [276-287] du HIT) doit comporter exactement 12 espaces'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ZONELIBRE3_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitZoneLibre3Regex02()._______________
	

	
	/* 69 - anneeNmoins1. ************************/
	/**
	 * teste la méthode getMessageSectionHitAnneeNmoins1Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitAnneeNmoins1Renseigne01() 
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
	public void testGetMessageSectionHitAnneeNmoins1Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitAnneeNmoins1Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAnneeNmoins1Renseigne01();
		
		/* garantit que getMessageSectionHitAnneeNmoins1Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'l'année n-1 de la section HIT (colonnes [288-289] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ANNEENMOINS1_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAnneeNmoins1Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAnneeNmoins1Renseigne01();

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
		
		/* garantit que setMessageSectionHitAnneeNmoins1Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAnneeNmoins1Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ANNEENMOINS1_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAnneeNmoins1Renseigne01();

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
		
		/* garantit que setMessageSectionHitAnneeNmoins1Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'l'année n-1 de la section HIT (colonnes [288-289] du HIT) doit être renseignée'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ANNEENMOINS1_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitAnneeNmoins1Renseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitAnneeNmoins1Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitAnneeNmoins1Regex02() 
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
	public void testGetMessageSectionHitAnneeNmoins1Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitAnneeNmoins1Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAnneeNmoins1Regex02();
		
		/* garantit que getMessageSectionHitAnneeNmoins1Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'l'année n-1 de la section HIT (colonnes [288-289] du HIT) doit comporter exactement 2 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ANNEENMOINS1_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAnneeNmoins1Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAnneeNmoins1Regex02();

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
		
		/* garantit que setMessageSectionHitAnneeNmoins1Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitAnneeNmoins1Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_ANNEENMOINS1_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitAnneeNmoins1Regex02();

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

				
		assertEquals("le message doit valoir : 'l'année n-1 de la section HIT (colonnes [288-289] du HIT) doit comporter exactement 2 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_ANNEENMOINS1_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitAnneeNmoins1Regex02()._______________
	

	
	/* 70 - mjaNmoins1. ************************/
	/**
	 * teste la méthode getMessageSectionHitMjaNmoins1Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjaNmoins1Renseigne01() 
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
	public void testGetMessageSectionHitMjaNmoins1Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjaNmoins1Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjaNmoins1Renseigne01();
		
		/* garantit que getMessageSectionHitMjaNmoins1Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le trafic moyen journalier annuel de l'année n-1 de la section HIT (colonnes [290-295] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJANMOINS1_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjaNmoins1Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjaNmoins1Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjaNmoins1Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjaNmoins1Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJANMOINS1_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjaNmoins1Renseigne01();

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
		
		/* garantit que setMessageSectionHitMjaNmoins1Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le trafic moyen journalier annuel de l'année n-1 de la section HIT (colonnes [290-295] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJANMOINS1_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitMjaNmoins1Renseigne01().___________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjaNmoins1Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjaNmoins1Regex02() 
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
	public void testGetMessageSectionHitMjaNmoins1Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjaNmoins1Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjaNmoins1Regex02();
		
		/* garantit que getMessageSectionHitMjaNmoins1Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le trafic moyen journalier annuel de l'année n-1 de la section HIT (colonnes [290-295] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJANMOINS1_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjaNmoins1Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjaNmoins1Regex02();

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
		
		/* garantit que setMessageSectionHitMjaNmoins1Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjaNmoins1Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJANMOINS1_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjaNmoins1Regex02();

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

				
		assertEquals("le message doit valoir : 'le trafic moyen journalier annuel de l'année n-1 de la section HIT (colonnes [290-295] du HIT) doit comporter exactement 6 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJANMOINS1_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjaNmoins1Regex02()._______________

	
	
	/**
	 * teste la méthode getMessageSectionHitMjaNmoins1Numerique03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitMjaNmoins1Numerique03() 
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
	public void testGetMessageSectionHitMjaNmoins1Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitMjaNmoins1Numerique03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjaNmoins1Numerique03();
		
		/* garantit que getMessageSectionHitMjaNmoins1Numerique03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le trafic moyen journalier annuel de l'année n-1 de la section HIT (colonnes [290-295] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJANMOINS1_NUMERIQUE_03_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjaNmoins1Numerique03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitMjaNmoins1Numerique03();

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
		
		/* garantit que setMessageSectionHitMjaNmoins1Numerique03(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitMjaNmoins1Numerique03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_MJANMOINS1_NUMERIQUE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitMjaNmoins1Numerique03();

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

				
		assertEquals("le message doit valoir : 'le trafic moyen journalier annuel de l'année n-1 de la section HIT (colonnes [290-295] du HIT) doit être homogène à un entier'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_MJANMOINS1_NUMERIQUE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitMjaNmoins1Numerique03().___________
	

	
	/* 71 - typeComptageNmoins1. ************************/
	/**
	 * teste la méthode getMessageSectionHitTypeComptageNmoins1Renseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitTypeComptageNmoins1Renseigne01() 
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
	public void testGetMessageSectionHitTypeComptageNmoins1Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitTypeComptageNmoins1Renseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitTypeComptageNmoins1Renseigne01();
		
		/* garantit que getMessageSectionHitTypeComptageNmoins1Renseigne01() crée le fichier 
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
		assertEquals("le message doit valoir : 'le type de comptage de l'année n-1 de la section HIT (colonnes [296] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPECOMPTAGENMOINS1_RENSEIGNE_01_EN_DUR
					, message);
		
				
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeComptageNmoins1Renseigne01(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitTypeComptageNmoins1Renseigne01();

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
		
		/* garantit que setMessageSectionHitTypeComptageNmoins1Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeComptageNmoins1Renseigne01(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_TYPECOMPTAGENMOINS1_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitTypeComptageNmoins1Renseigne01();

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
		
		/* garantit que setMessageSectionHitTypeComptageNmoins1Renseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'le type de comptage de l'année n-1 de la section HIT (colonnes [296] du HIT) doit être renseigné'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPECOMPTAGENMOINS1_RENSEIGNE_01_EN_DUR
					, messageModifie2);
		
	} // Fin de testGetMessageSectionHitTypeComptageNmoins1Renseigne01().__

	
	
	/**
	 * teste la méthode getMessageSectionHitTypeComptageNmoins1Regex02().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitTypeComptageNmoins1Regex02() 
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
	public void testGetMessageSectionHitTypeComptageNmoins1Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitTypeComptageNmoins1Regex02() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitTypeComptageNmoins1Regex02();
		
		/* garantit que getMessageSectionHitTypeComptageNmoins1Regex02() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le type de comptage de l'année n-1 de la section HIT (colonnes [296] du HIT) doit comporter exactement 1 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPECOMPTAGENMOINS1_REGEX_02_EN_DUR
					, message);
		
		
		// **********************************************
		/* modification du message *********************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeComptageNmoins1Regex02(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitTypeComptageNmoins1Regex02();

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
		
		/* garantit que setMessageSectionHitTypeComptageNmoins1Regex02(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);
		
		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeComptageNmoins1Regex02(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_TYPECOMPTAGENMOINS1_REGEX_02_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitTypeComptageNmoins1Regex02();

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

				
		assertEquals("le message doit valoir : 'le type de comptage de l'année n-1 de la section HIT (colonnes [296] du HIT) doit comporter exactement 1 chiffres'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPECOMPTAGENMOINS1_REGEX_02_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitTypeComptageNmoins1Regex02().______

	
	
	/**
	 * teste la méthode getMessageSectionHitTypeComptageNmoins1Nomenclature03().<br/>
	 * <ul>
	 * <li>garantit que getMessageSectionHitTypeComptageNmoins1Nomenclature03() 
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
	public void testGetMessageSectionHitTypeComptageNmoins1Nomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesControlesTest - méthode testGetMessageSectionHitTypeComptageNmoins1Nomenclature03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitTypeComptageNmoins1Nomenclature03();
		
		/* garantit que getMessageSectionHitTypeComptageNmoins1Nomenclature03() crée le fichier 
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
				
		assertEquals("le message doit valoir : 'le type de comptage de l'année n-1 de la section HIT (colonnes [296] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPECOMPTAGENMOINS1_NOMENCLATURE_03_EN_DUR
					, message);

		
		// ***************************************
		/* modification du message. ****************/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeComptageNmoins1Nomenclature03(
					MODIFIEZ_LA_VALEUR);
		
		final String messageModifie 
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitTypeComptageNmoins1Nomenclature03();

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

		/* garantit que setMessageSectionHitTypeComptageNmoins1Renseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals(MESSAGE_DOIT_VALOIR_MODIFIER_VALEUR
				, MODIFIEZ_LA_VALEUR
					, messageModifie);

		
		// ***************************************
		/* remise des valeurs en DUR. **********/
		SectionHitGestionnairePreferencesControles
			.setMessageSectionHitTypeComptageNmoins1Nomenclature03(
					SectionHitGestionnairePreferencesControles
						.MESSAGE_SECTIONHIT_TYPECOMPTAGENMOINS1_NOMENCLATURE_03_EN_DUR);
		
		final String messageModifie2 
		= SectionHitGestionnairePreferencesControles
			.getMessageSectionHitTypeComptageNmoins1Nomenclature03();

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

		assertEquals("le message doit valoir : 'le type de comptage de l'année n-1 de la section HIT (colonnes [296] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]'"
				, SectionHitGestionnairePreferencesControles
				.MESSAGE_SECTIONHIT_TYPECOMPTAGENMOINS1_NOMENCLATURE_03_EN_DUR
					, messageModifie2);

	} // Fin de testGetMessageSectionHitTypeComptageNmoins1Nomenclature03().

	
	
				
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
