package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.utilisateurs;

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
 * CLASSE UtilisateurCerbereGestionnairePreferencesControlesTest :<br/>
 * Test JUnit de la classe 
 * UtilisateurCerbereGestionnairePreferencesControles.<br/>
 * <ul>
 * <li>préserve le fichier UtilisateurCerbere_CONTROLES.properties 
 * dans ses méthodes beforeClass() et afterClass().</li>
 * </ul>
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
 * @since 18 avr. 2019
 *
 */
public class UtilisateurCerbereGestionnairePreferencesControlesTest {

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
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereGestionnairePreferencesControlesTest.class);

	// *************************METHODES************************************/
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereGestionnairePreferencesControlesTest() {
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
			System.out.println("********** CLASSE UtilisateurCerbereGestionnairePreferencesControlesTest - méthode testAfficherPreferences() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		String prefString = null;
		
		/* récupération des prefs avec un properties inexistant. */
		prefString = UtilisateurCerbereGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefString);
		}
		
		/* garantit que afficherPreferences() crée le fichier 
		 * properties avec des valeurs en dur. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, UtilisateurCerbereGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
		
		/* garantit que afficherPreferences() affiche les preferences 
		 * stockées en dur dans la classe si le properties n'existait pas.*/
		assertNotNull("les preferences affichées ne doivent pas être null : "
				, prefString);
		
		assertFalse("les preferences affichées ne doivent pas être vide : "
				, prefString.isEmpty());
		
		/* modification de certaines valeurs. **********/
		UtilisateurCerbereGestionnairePreferencesControles
			.setMessageUtilisateurPrenomRenseigne01("prénom obligatoire");
		UtilisateurCerbereGestionnairePreferencesControles
			.setMessageUtilisateurNomRenseigne01("nom obligatoire");
		
		/* récupération des prefs dans un properties existant. */
		prefString = UtilisateurCerbereGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefString);
		}
		
		/* garantit que afficherPreferences() lit les preferences 
		 * dans le fichier properties existant. */
		assertTrue("le fichier properties doit exister sur disque : "
				, UtilisateurCerbereGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
		
		assertEquals("la valeur doit être 'prénom obligatoire' : "
				, "prénom obligatoire"
				, UtilisateurCerbereGestionnairePreferencesControles
					.getMessageUtilisateurPrenomRenseigne01());

		assertEquals("la valeur doit être 'nom obligatoire' : "
				, "nom obligatoire"
				, UtilisateurCerbereGestionnairePreferencesControles
					.getMessageUtilisateurNomRenseigne01());
		
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
			System.out.println("********** CLASSE UtilisateurCerbereGestionnairePreferencesControlesTest - méthode testGetPreferences() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();

		Properties prefs = null;
		
		/* récupère preferences lorsque le properties est inexistant. */
		prefs = UtilisateurCerbereGestionnairePreferencesControles.getPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefs);
			System.out.println();
		}
		
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = UtilisateurCerbereGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefString);
			System.out.println();
		}
		
		/* garantit que getPreferences() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, UtilisateurCerbereGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
		
		/* garantit que getPreferences() contient les preferences 
		 * stockées en dur dans la classe si le properties n'existait pas.*/
		assertNotNull("les preferences en dur ne doivent pas être null : "
				, prefs);
		
		assertFalse("les preferences en dur ne doivent pas être vide : "
				, prefs.isEmpty());
		
		/* garantit que getPreferences() lit les preferences 
		 * dans le properties existant. */
		assertEquals("la valeur doit être 'le prénom de l'Utilisateur doit être renseigné' : "
				, UtilisateurCerbereGestionnairePreferencesControles.MESSAGE_UTILISATEUR_PRENOM_RENSEIGNE_01_EN_DUR
				, UtilisateurCerbereGestionnairePreferencesControles
					.getMessageUtilisateurPrenomRenseigne01());

		assertEquals("la valeur doit être 'le nom de l'Utilisateur doit être renseigné' : "
				, UtilisateurCerbereGestionnairePreferencesControles.MESSAGE_UTILISATEUR_NOM_RENSEIGNE_01_EN_DUR
				, UtilisateurCerbereGestionnairePreferencesControles.getMessageUtilisateurNomRenseigne01());

	} // Fin de testGetPreferences().______________________________________
	

	
	/**
	 * teste la méthode getMessageUtilisateurCiviliteRenseigne01().<br/>
	 * <ul>
	 * <li>garantit que getMessageUtilisateurCiviliteRenseigne01() 
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
	public void testGetMessageUtilisateurCiviliteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE UtilisateurCerbereGestionnairePreferencesControlesTest - méthode testGetMessageUtilisateurCiviliteRenseigne01() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String message 
			= UtilisateurCerbereGestionnairePreferencesControles
				.getMessageUtilisateurCiviliteRenseigne01();
		
		/* garantit que getMessageUtilisateurCiviliteRenseigne01() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, UtilisateurCerbereGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
				
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = UtilisateurCerbereGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefString);
			System.out.println();
			System.out.println(MESSAGE + message);
			System.out.println();
		}
		
		/* garantit que le getter fonctionne bien. */
		assertEquals("le message doit valoir : 'la civilité doit obligatoirement être renseignée'"
				, UtilisateurCerbereGestionnairePreferencesControles
				.MESSAGE_UTILISATEUR_CIVILITE_RENSEIGNE_01_EN_DUR
					, message);
		
		/* modification du message/ */
		UtilisateurCerbereGestionnairePreferencesControles
			.setMessageUtilisateurCiviliteRenseigne01(
					"renseignez la civilité !");
		
		final String messageModifie 
		= UtilisateurCerbereGestionnairePreferencesControles
			.getMessageUtilisateurCiviliteRenseigne01();

		String prefStringModifiee = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee = UtilisateurCerbereGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefStringModifiee);
			System.out.println();
			System.out.println(MESSAGE + messageModifie);
			System.out.println();
		}
		
		/* garantit que setMessageUtilisateurCiviliteRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'renseignez la civilité !'"
				, "renseignez la civilité !"
					, messageModifie);

		/* modification du message/ */
		UtilisateurCerbereGestionnairePreferencesControles
			.setMessageUtilisateurCiviliteRenseigne01(
					UtilisateurCerbereGestionnairePreferencesControles
						.MESSAGE_UTILISATEUR_CIVILITE_RENSEIGNE_01_EN_DUR);
		
		final String messageModifie2 
		= UtilisateurCerbereGestionnairePreferencesControles
			.getMessageUtilisateurCiviliteRenseigne01();

		String prefStringModifiee2 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee2 = UtilisateurCerbereGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefStringModifiee2);
			System.out.println();
			System.out.println(MESSAGE + messageModifie2);
			System.out.println();
		}
		
		/* garantit que setMessageUtilisateurCiviliteRenseigne01(...) modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'la civilité doit obligatoirement être renseignée'"
				, UtilisateurCerbereGestionnairePreferencesControles
				.MESSAGE_UTILISATEUR_CIVILITE_RENSEIGNE_01_EN_DUR
					, messageModifie2);

		
	} // Fin de testGetMessageUtilisateurCiviliteRenseigne01().____________

	
	
	/**
	 * teste la méthode getMessageUtilisateurCiviliteLongueur03().<br/>
	 * <ul>
	 * <li>garantit que getMessageUtilisateurCiviliteLongueur03() 
	 * crée le fichier properties avec des valeurs en dur 
	 * si il n'existait pas.</li>
	 * <li>garantit que le getter fonctionne bien.</li>
	 * <li>garantit que le setter fonctionne bien.</li>
	 * <li>garantit que le message et la valeur ne sont pas modifiés 
	 * si le nouveau message n'a pas le bon format.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetMessageUtilisateurCiviliteLongueur03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE UtilisateurCerbereGestionnairePreferencesControlesTest - méthode testGetMessageUtilisateurCiviliteLongueur03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String valeur 
			= UtilisateurCerbereGestionnairePreferencesControles
				.getValeurUtilisateurCiviliteLongueur03();
		
		final String message 
			= UtilisateurCerbereGestionnairePreferencesControles
				.getMessageUtilisateurCiviliteLongueur03();
		
		/* garantit que getMessageUtilisateurCiviliteLongueur03() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, UtilisateurCerbereGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
				
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = UtilisateurCerbereGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefString);
			System.out.println();
			System.out.println(MESSAGE + message);
			System.out.println(VALEUR + valeur);
			System.out.println();
		}
		
		/* garantit que le getter fonctionne bien. */
		assertEquals("la valeur doit valoir : '15'"
				, UtilisateurCerbereGestionnairePreferencesControles
				.VALEUR_UTILISATEUR_CIVILITE_LONGUEUR_03_EN_DUR
					, valeur);
		
		assertEquals("le message doit valoir : 'la civilité de l'Utilisateur ne doit pas excéder 15 caractères'"
				, UtilisateurCerbereGestionnairePreferencesControles
				.MESSAGE_UTILISATEUR_CIVILITE_LONGUEUR_03_EN_DUR
					, message);
		
		
		/* modification du message. */
		UtilisateurCerbereGestionnairePreferencesControles
			.setMessageUtilisateurCiviliteLongueur03(
					"ne marche pas car pas de nombre");
		
		final String messageModifie1 
		= UtilisateurCerbereGestionnairePreferencesControles
			.getMessageUtilisateurCiviliteLongueur03();
		
		final String valeurModifie1 
		= UtilisateurCerbereGestionnairePreferencesControles
			.getValeurUtilisateurCiviliteLongueur03();
		
		String prefStringModifiee1 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee1 = UtilisateurCerbereGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefStringModifiee1);
			System.out.println();
			System.out.println(MESSAGE + messageModifie1);
			System.out.println(VALEUR + valeurModifie1);
			System.out.println();
		}
		
		/* garantit que le message et la valeur ne sont pas modifiés 
		 * si le nouveau message n'a pas le bon format. */
		assertEquals("la valeur doit valoir : '15'"
				, UtilisateurCerbereGestionnairePreferencesControles
				.VALEUR_UTILISATEUR_CIVILITE_LONGUEUR_03_EN_DUR
					, valeurModifie1);
		
		assertEquals("le message doit valoir : 'la civilité de l'Utilisateur ne doit pas excéder 15 caractères'"
				, UtilisateurCerbereGestionnairePreferencesControles
				.MESSAGE_UTILISATEUR_CIVILITE_LONGUEUR_03_EN_DUR
					, messageModifie1);

		/* modification du message2 */
		UtilisateurCerbereGestionnairePreferencesControles
			.setMessageUtilisateurCiviliteLongueur03(
					DOIT_COMPORTER_MOINS_30);
		
		final String messageModifie2 
		= UtilisateurCerbereGestionnairePreferencesControles
			.getMessageUtilisateurCiviliteLongueur03();
		
		final String valeurModifie2 
		= UtilisateurCerbereGestionnairePreferencesControles
			.getValeurUtilisateurCiviliteLongueur03();
		
		String prefStringModifiee2 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee2 = UtilisateurCerbereGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefStringModifiee2);
			System.out.println();
			System.out.println(MESSAGE + messageModifie2);
			System.out.println(VALEUR + valeurModifie2);
			System.out.println();
		}

		
		/* garantit que setMessageUtilisateurCiviliteRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'doit comporter moins de 30 caractères'"
				, DOIT_COMPORTER_MOINS_30
					, messageModifie2);
		
		assertEquals("la valeur doit valoir : '30'"
				, "30"
					, valeurModifie2);
		
	} // Fin de testGetMessageUtilisateurCiviliteLongueur03().____________

	
	
	/**
	 * teste la méthode getMessageUtilisateurPrenomLongueur03().<br/>
	 * <ul>
	 * <li>garantit que getMessageUtilisateurPrenomLongueur03() 
	 * crée le fichier properties avec des valeurs en dur 
	 * si il n'existait pas.</li>
	 * <li>garantit que le getter fonctionne bien.</li>
	 * <li>garantit que le setter fonctionne bien.</li>
	 * <li>garantit que le message et la valeur ne sont pas modifiés 
	 * si le nouveau message n'a pas le bon format.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetMessageUtilisateurPrenomLongueur03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE UtilisateurCerbereGestionnairePreferencesControlesTest - méthode testGetMessageUtilisateurPrenomLongueur03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String valeur 
			= UtilisateurCerbereGestionnairePreferencesControles
				.getValeurUtilisateurPrenomLongueur03();
		
		final String message 
			= UtilisateurCerbereGestionnairePreferencesControles
				.getMessageUtilisateurPrenomLongueur03();
		
		/* garantit que getMessageUtilisateurPrenomLongueur03() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, UtilisateurCerbereGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
				
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = UtilisateurCerbereGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefString);
			System.out.println();
			System.out.println(MESSAGE + message);
			System.out.println(VALEUR + valeur);
			System.out.println();
		}
		
		/* garantit que le getter fonctionne bien. */
		assertEquals(VALEUR_DOIT_VALOIR_50
				, UtilisateurCerbereGestionnairePreferencesControles
				.VALEUR_UTILISATEUR_PRENOM_LONGUEUR_03_EN_DUR
					, valeur);
		
		assertEquals("le message doit valoir : 'le prénom de l'Utilisateur ne doit pas excéder 50 caractères'"
				, UtilisateurCerbereGestionnairePreferencesControles
				.MESSAGE_UTILISATEUR_PRENOM_LONGUEUR_03_EN_DUR
					, message);
		
		
		/* modification du message. */
		UtilisateurCerbereGestionnairePreferencesControles
			.setMessageUtilisateurPrenomLongueur03(
					"ne marche pas car pas de nombre");
		
		final String messageModifie1 
		= UtilisateurCerbereGestionnairePreferencesControles
			.getMessageUtilisateurPrenomLongueur03();
		
		final String valeurModifie1 
		= UtilisateurCerbereGestionnairePreferencesControles
			.getValeurUtilisateurPrenomLongueur03();
		
		String prefStringModifiee1 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee1 = UtilisateurCerbereGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefStringModifiee1);
			System.out.println();
			System.out.println(MESSAGE + messageModifie1);
			System.out.println(VALEUR + valeurModifie1);
			System.out.println();
		}
		
		/* garantit que le message et la valeur ne sont pas modifiés 
		 * si le nouveau message n'a pas le bon format. */
		assertEquals(VALEUR_DOIT_VALOIR_50
				, UtilisateurCerbereGestionnairePreferencesControles
				.VALEUR_UTILISATEUR_PRENOM_LONGUEUR_03_EN_DUR
					, valeurModifie1);
		
		assertEquals("le message doit valoir : 'le prénom de l'Utilisateur ne doit pas excéder 50 caractères'"
				, UtilisateurCerbereGestionnairePreferencesControles
				.MESSAGE_UTILISATEUR_PRENOM_LONGUEUR_03_EN_DUR
					, messageModifie1);

		/* modification du message2 */
		UtilisateurCerbereGestionnairePreferencesControles
			.setMessageUtilisateurPrenomLongueur03(
					DOIT_COMPORTER_MOINS_30);
		
		final String messageModifie2 
		= UtilisateurCerbereGestionnairePreferencesControles
			.getMessageUtilisateurPrenomLongueur03();
		
		final String valeurModifie2 
		= UtilisateurCerbereGestionnairePreferencesControles
			.getValeurUtilisateurPrenomLongueur03();
		
		String prefStringModifiee2 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee2 = UtilisateurCerbereGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefStringModifiee2);
			System.out.println();
			System.out.println(MESSAGE + messageModifie2);
			System.out.println(VALEUR + valeurModifie2);
			System.out.println();
		}

		
		/* garantit que setMessageUtilisateurPrenomRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'doit comporter moins de 30 caractères'"
				, DOIT_COMPORTER_MOINS_30
					, messageModifie2);
		
		assertEquals("la valeur doit valoir : '30'"
				, "30"
					, valeurModifie2);
		
	} // Fin de testGetMessageUtilisateurPrenomLongueur03()._______________

	
	
	/**
	 * teste la méthode getMessageUtilisateurNomLongueur03().<br/>
	 * <ul>
	 * <li>garantit que getMessageUtilisateurNomLongueur03() 
	 * crée le fichier properties avec des valeurs en dur 
	 * si il n'existait pas.</li>
	 * <li>garantit que le getter fonctionne bien.</li>
	 * <li>garantit que le setter fonctionne bien.</li>
	 * <li>garantit que le message et la valeur ne sont pas modifiés 
	 * si le nouveau message n'a pas le bon format.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetMessageUtilisateurNomLongueur03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE UtilisateurCerbereGestionnairePreferencesControlesTest - méthode testGetMessageUtilisateurNomLongueur03() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		final String valeur 
			= UtilisateurCerbereGestionnairePreferencesControles
				.getValeurUtilisateurNomLongueur03();
		
		final String message 
			= UtilisateurCerbereGestionnairePreferencesControles
				.getMessageUtilisateurNomLongueur03();
		
		/* garantit que getMessageUtilisateurNomLongueur03() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue(PROPERTIES_SUR_DISQUE
				, UtilisateurCerbereGestionnairePreferencesControles
				.getFilePreferencesProperties().exists());
				
		String prefString = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefString = UtilisateurCerbereGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefString);
			System.out.println();
			System.out.println(MESSAGE + message);
			System.out.println(VALEUR + valeur);
			System.out.println();
		}
		
		/* garantit que le getter fonctionne bien. */
		assertEquals(VALEUR_DOIT_VALOIR_50
				, UtilisateurCerbereGestionnairePreferencesControles
				.VALEUR_UTILISATEUR_NOM_LONGUEUR_03_EN_DUR
					, valeur);
		
		assertEquals("le message doit valoir : 'le nom de l'Utilisateur ne doit pas excéder 50 caractères'"
				, UtilisateurCerbereGestionnairePreferencesControles
				.MESSAGE_UTILISATEUR_NOM_LONGUEUR_03_EN_DUR
					, message);
		
		
		/* modification du message. */
		UtilisateurCerbereGestionnairePreferencesControles
			.setMessageUtilisateurNomLongueur03(
					"ne marche pas car pas de nombre");
		
		final String messageModifie1 
		= UtilisateurCerbereGestionnairePreferencesControles
			.getMessageUtilisateurNomLongueur03();
		
		final String valeurModifie1 
		= UtilisateurCerbereGestionnairePreferencesControles
			.getValeurUtilisateurNomLongueur03();
		
		String prefStringModifiee1 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee1 = UtilisateurCerbereGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefStringModifiee1);
			System.out.println();
			System.out.println(MESSAGE + messageModifie1);
			System.out.println(VALEUR + valeurModifie1);
			System.out.println();
		}
		
		/* garantit que le message et la valeur ne sont pas modifiés 
		 * si le nouveau message n'a pas le bon format. */
		assertEquals(VALEUR_DOIT_VALOIR_50
				, UtilisateurCerbereGestionnairePreferencesControles
				.VALEUR_UTILISATEUR_NOM_LONGUEUR_03_EN_DUR
					, valeurModifie1);
		
		assertEquals("le message doit valoir : 'le nom de l'Utilisateur ne doit pas excéder 50 caractères'"
				, UtilisateurCerbereGestionnairePreferencesControles
				.MESSAGE_UTILISATEUR_NOM_LONGUEUR_03_EN_DUR
					, messageModifie1);

		/* modification du message2 */
		UtilisateurCerbereGestionnairePreferencesControles
			.setMessageUtilisateurNomLongueur03(
					DOIT_COMPORTER_MOINS_30);
		
		final String messageModifie2 
		= UtilisateurCerbereGestionnairePreferencesControles
			.getMessageUtilisateurNomLongueur03();
		
		final String valeurModifie2 
		= UtilisateurCerbereGestionnairePreferencesControles
			.getValeurUtilisateurNomLongueur03();
		
		String prefStringModifiee2 = null;
		
		/* récupération des prefs (affichage) dans un properties existant. */
		prefStringModifiee2 = UtilisateurCerbereGestionnairePreferencesControles
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefStringModifiee2);
			System.out.println();
			System.out.println(MESSAGE + messageModifie2);
			System.out.println(VALEUR + valeurModifie2);
			System.out.println();
		}

		
		/* garantit que setMessageUtilisateurNomRenseigne01(...) 
		 * modifie le message dans le fichier properties. */
		assertEquals("le message doit valoir : 'doit comporter moins de 30 caractères'"
				, DOIT_COMPORTER_MOINS_30
					, messageModifie2);
		
		assertEquals("la valeur doit valoir : '30'"
				, "30"
					, valeurModifie2);
		
	} // Fin de testGetMessageUtilisateurNomLongueur03().__________________

	
	
	/**
	 * détruit le fichier properties si il existe.<br/>
	 *
	 * @throws Exception
	 */
	private void detruireFichierProperties() throws Exception {
		
		/* détruit le fichier properties si il existe. */
		Files.deleteIfExists(
				UtilisateurCerbereGestionnairePreferencesControles
					.getPathAbsoluPreferencesProperties());
		
		assertFalse("le fichier properties ne doit plus exister : "
				, UtilisateurCerbereGestionnairePreferencesControles
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
		final Path propPath = UtilisateurCerbereGestionnairePreferencesControles
				.getPathAbsoluPreferencesProperties();
		
		final File propFile = propPath.toFile();
		
		if (propFile.exists()) {
			
			final String nomFichier = propFile.getName();
			
			/* Path du fichier properties old à créer. */
			final Path propOldPathParent = propPath.getParent();
			
			final String nomFichierOld = nomFichier + ".old";
			
			final Path propOldPath = propOldPathParent.resolve(nomFichierOld);
			
			// COPIE AVEC REMPLACEMENT ************************
			Files.copy(
					propPath
						, propOldPath
							, StandardCopyOption.REPLACE_EXISTING);
			
			assertTrue(
					"le properties old doit avoir été créé : "
						, propOldPath.toFile().exists());
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
		final Path propPath = UtilisateurCerbereGestionnairePreferencesControles
				.getPathAbsoluPreferencesProperties();
		
		final File propFile = propPath.toFile();
		final String nomFichier = propFile.getName();
		
		/* Path du fichier properties old. */
		final Path propOldPathParent = propPath.getParent();		
		final String nomFichierOld = nomFichier + ".old";		
		final Path propOldPath = propOldPathParent.resolve(nomFichierOld);
			
		final File old = propOldPath.toFile();
		
		if (old.exists()) {
					
			// COPIE AVEC REMPLACEMENT ************************
			Files.copy(
					propOldPath
						, propPath
							, StandardCopyOption.REPLACE_EXISTING);
			
			assertTrue("le properties original doit avoir été recréé : ", propPath.toFile().exists());
			
			/* détruit la copie de sécurité si elle existait. */
			Files.deleteIfExists(propOldPath);
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
		
	
	
} // FIN DE LA CLASSE UtilisateurCerbereGestionnairePreferencesControlesTest.
