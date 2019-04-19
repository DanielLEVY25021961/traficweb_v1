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
		assertTrue("le fichier properties doit avoir été généré sur disque : "
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
		assertTrue("le fichier properties doit avoir été généré sur disque : "
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
			
			assertTrue("le properties old doit avoir été créé : ", propOldPath.toFile().exists());
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
