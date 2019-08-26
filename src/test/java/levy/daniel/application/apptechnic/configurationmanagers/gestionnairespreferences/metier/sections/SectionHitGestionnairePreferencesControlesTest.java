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
