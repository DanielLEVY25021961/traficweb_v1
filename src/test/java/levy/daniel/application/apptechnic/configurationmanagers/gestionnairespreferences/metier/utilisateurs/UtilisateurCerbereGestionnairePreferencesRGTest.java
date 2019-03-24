package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.utilisateurs;

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
 * CLASSE UtilisateurCerbereGestionnairePreferencesRGTest :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * détruire fichier, detruire fichier, <br/>
 * renommer fichier en old, renommer fichier properties en properties.old,<br/>
 * copier fichier en old, copier fichier properties en properties.old,<br/>
 * recopier fichier en old, recopier fichier properties en properties.old,<br/>
 * creer fichier.old à côté de fichier, creer fichier.old a cote de fichier,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 22 mars 2019
 *
 */
public class UtilisateurCerbereGestionnairePreferencesRGTest {

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
		= LogFactory.getLog(
				UtilisateurCerbereGestionnairePreferencesRGTest.class);
	

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereGestionnairePreferencesRGTest() {
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
			System.out.println("********** CLASSE UtilisateurCerbereGestionnairePreferencesRGTest - méthode testAfficherPreferences() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		String pref = null;
		
		/* récupération des prefs avec un properties inexistant. */
		pref = UtilisateurCerbereGestionnairePreferencesRG
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(pref);
		}
		
		/* garantit que afficherPreferences() crée le fichier 
		 * properties avec des valeurs en dur. */
		assertTrue("le fichier properties doit avoir été généré sur disque : "
				, UtilisateurCerbereGestionnairePreferencesRG
				.getFilePreferencesProperties().exists());
		
		/* garantit que afficherPreferences() affiche les preferences 
		 * stockées en dur dans la classe si le properties n'existait pas.*/
		assertNotNull("les preferences affichées ne doivent pas être null : "
				, pref);
		
		assertFalse("les preferences affichées ne doivent pas être vide : "
				, pref.isEmpty());
		
		/* modification de certaines valeurs. **********/
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurCivilite(true);
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurCiviliteLitteral02(true);
		
		/* récupération des prefs dans un properties existant. */
		pref = UtilisateurCerbereGestionnairePreferencesRG
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(pref);
		}
		
		/* garantit que afficherPreferences() lit les preferences 
		 * dans le fichier properties existant. */
		assertTrue("le fichier properties doit exister sur disque : "
				, UtilisateurCerbereGestionnairePreferencesRG
				.getFilePreferencesProperties().exists());
		
		assertTrue("la valeur doit être à true : "
				, UtilisateurCerbereGestionnairePreferencesRG
					.getValiderRGUtilisateurCivilite());

		assertTrue("la valeur doit être à true : "
				, UtilisateurCerbereGestionnairePreferencesRG
					.getValiderRGUtilisateurCiviliteLitteral02());
		
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
			System.out.println("********** CLASSE UtilisateurCerbereGestionnairePreferencesRGTest - méthode testGetPreferences() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();

		Properties prefs = null;
		
		/* récupère preferences lorsque le properties est inexistant. */
		prefs = UtilisateurCerbereGestionnairePreferencesRG.getPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefs);
		}
		
		/* garantit que getPreferences() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue("le fichier properties doit avoir été généré sur disque : "
				, UtilisateurCerbereGestionnairePreferencesRG
				.getFilePreferencesProperties().exists());
		
		/* garantit que getPreferences() contient les preferences 
		 * stockées en dur dans la classe si le properties n'existait pas.*/
		assertNotNull("les preferences en dur ne doivent pas être null : "
				, prefs);
		
		assertFalse("les preferences en dur ne doivent pas être vide : "
				, prefs.isEmpty());
		
		/* modification de certaines valeurs. **********/
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurCivilite(true);
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurCiviliteLitteral02(true);
		
		/* récupération des prefs dans un properties existant. */
		prefs = UtilisateurCerbereGestionnairePreferencesRG.getPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefs);
		}

		/* garantit que getPreferences() lit les preferences 
		 * dans le properties existant. */
		assertTrue("la valeur doit être à true : "
				, UtilisateurCerbereGestionnairePreferencesRG
					.getValiderRGUtilisateurCivilite());

		assertTrue("la valeur doit être à true : "
				, UtilisateurCerbereGestionnairePreferencesRG
					.getValiderRGUtilisateurCiviliteLitteral02());

	} // Fin de testGetPreferences().______________________________________
	

	
	/**
	 * teste la méthode getPathAbsoluPreferencesProperties().<br/>
	 * <ul>
	 * <li>garantit que getPathAbsoluPreferencesProperties() 
	 * fournit le Path du properties.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetPathAbsoluPreferencesProperties() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE UtilisateurCerbereGestionnairePreferencesRGTest - méthode testGetPathAbsoluPreferencesProperties() ********** ");
		}
		
		Path pathAbsoluPreferencesProperties = null;
		
		/* récupère pathAbsoluPreferencesProperties. */
		pathAbsoluPreferencesProperties 
			= UtilisateurCerbereGestionnairePreferencesRG
				.getPathAbsoluPreferencesProperties();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(pathAbsoluPreferencesProperties);
		}
		
		/* garantit que getPathAbsoluPreferencesProperties() fournit le Path du properties. */
		assertNotNull("pathAbsoluPreferencesProperties ne doit pas être null : "
				, pathAbsoluPreferencesProperties);
		
	} // Fin de testGetPathAbsoluPreferencesProperties().__________________
	

	
	/**
	 * teste la méthode getCommentaire().<br/>
	 * <ul>
	 * <li>garantit que getCommentaire() 
	 * fournit le commentaire.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetCommentaire() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE UtilisateurCerbereGestionnairePreferencesRGTest - méthode testGetCommentaire() ********** ");
		}
		
		String commentaire = null;
		
		/* récupère commentaire. */
		commentaire 
			= UtilisateurCerbereGestionnairePreferencesRG
				.getCommentaire();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(commentaire);
		}
		
		/* garantit que getCommentaire() fournit le commentaire. */
		assertNotNull("commentaire ne doit pas être null : "
				, commentaire);
		
	} // Fin de testGetCommentaire().______________________________________

	
	
	/**
	 * teste la méthode getFilePreferencesProperties().<br/>
	 * <ul>
	 * <li>garantit que getFilePreferencesProperties() 
	 * fournit le properties.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetFilePreferencesProperties() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE UtilisateurCerbereGestionnairePreferencesRGTest - méthode testGetFilePreferencesProperties() ********** ");
		}
		
		File filePreferencesProperties = null;
		
		/* récupère filePreferencesProperties. */
		filePreferencesProperties 
			= UtilisateurCerbereGestionnairePreferencesRG
				.getFilePreferencesProperties();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(filePreferencesProperties);
		}
		
		/* garantit que getFilePreferencesProperties() fournit le properties. */
		assertNotNull("filePreferencesProperties ne doit pas être null : "
				, filePreferencesProperties);
		
	} // Fin de testGetFilePreferencesProperties().________________________

	
	
	/**
	 * teste la méthode getValiderRGUtilisateurCivilite().<br/>
	 * <ul>
	 * <li>récupère le Boolean dans le properties.</li>
	 * <li>passe le Boolean à true en utilisant son Setter.</li>
	 * <li>garantit que le Boolean a bien été passé a true 
	 * dans le properties.</li>
	 * <li>passe le Boolean à false en utilisant son Setter.</li>
	 * <li>garantit que le Boolean a bien été passé a false 
	 * dans le properties.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetValiderRGUtilisateurCivilite() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE UtilisateurCerbereGestionnairePreferencesRGTest - méthode testGetValiderRGUtilisateurCivilite() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= UtilisateurCerbereGestionnairePreferencesRG
				.getValiderRGUtilisateurCivilite();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGUtilisateurCivilite dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurCivilite(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= UtilisateurCerbereGestionnairePreferencesRG
				.getValiderRGUtilisateurCivilite();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGUtilisateurCivilite après passage à true : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a true dans le properties. */
		assertTrue("Le Boolean doit valoir true : ", validerRGBoolean);
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurCivilite(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= UtilisateurCerbereGestionnairePreferencesRG
			.getValiderRGUtilisateurCivilite();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGUtilisateurCivilite après passage à false : " + validerRGBoolean);
		}
		
		assertFalse("Le Boolean doit valoir false : ", validerRGBoolean);

	} // Fin de testGetValiderRGUtilisateurCivilite()._____________________

	
	
	/**
	 * teste la méthode getValiderRGUtilisateurCiviliteRenseigne01().<br/>
	 * <ul>
	 * <li>récupère le Boolean dans le properties.</li>
	 * <li>passe le Boolean à true en utilisant son Setter.</li>
	 * <li>garantit que le Boolean a bien été passé a true 
	 * dans le properties.</li>
	 * <li>passe le Boolean à false en utilisant son Setter.</li>
	 * <li>garantit que le Boolean a bien été passé a false 
	 * dans le properties.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetValiderRGUtilisateurCiviliteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE UtilisateurCerbereGestionnairePreferencesRGTest - méthode testGetValiderRGUtilisateurCiviliteRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= UtilisateurCerbereGestionnairePreferencesRG
				.getValiderRGUtilisateurCiviliteRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGUtilisateurCiviliteRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurCiviliteRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= UtilisateurCerbereGestionnairePreferencesRG
				.getValiderRGUtilisateurCiviliteRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGUtilisateurCiviliteRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a true dans le properties. */
		assertTrue("Le Boolean doit valoir true : ", validerRGBoolean);
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurCiviliteRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= UtilisateurCerbereGestionnairePreferencesRG
			.getValiderRGUtilisateurCiviliteRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGUtilisateurCiviliteRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		assertFalse("Le Boolean doit valoir false : ", validerRGBoolean);

	} // Fin de testGetValiderRGUtilisateurCiviliteRenseigne01().__________

	
	
	/**
	 * teste la méthode getValiderRGUtilisateurCiviliteLitteral02().<br/>
	 * <ul>
	 * <li>récupère le Boolean dans le properties.</li>
	 * <li>passe le Boolean à true en utilisant son Setter.</li>
	 * <li>garantit que le Boolean a bien été passé a true 
	 * dans le properties.</li>
	 * <li>passe le Boolean à false en utilisant son Setter.</li>
	 * <li>garantit que le Boolean a bien été passé a false 
	 * dans le properties.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetValiderRGUtilisateurCiviliteLitteral02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE UtilisateurCerbereGestionnairePreferencesRGTest - méthode testGetValiderRGUtilisateurCiviliteLitteral02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= UtilisateurCerbereGestionnairePreferencesRG
				.getValiderRGUtilisateurCiviliteLitteral02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGUtilisateurCiviliteLitteral02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurCiviliteLitteral02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= UtilisateurCerbereGestionnairePreferencesRG
				.getValiderRGUtilisateurCiviliteLitteral02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGUtilisateurCiviliteLitteral02 après passage à true : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a true dans le properties. */
		assertTrue("Le Boolean doit valoir true : ", validerRGBoolean);
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurCiviliteLitteral02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= UtilisateurCerbereGestionnairePreferencesRG
			.getValiderRGUtilisateurCiviliteLitteral02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGUtilisateurCiviliteLitteral02 après passage à false : " + validerRGBoolean);
		}
		
		assertFalse("Le Boolean doit valoir false : ", validerRGBoolean);

	} // Fin de testGetValiderRGUtilisateurCiviliteLitteral02().___________
	

	
	/**
	 * détruit le fichier properties si il existe.<br/>
	 *
	 * @throws Exception
	 */
	private void detruireFichierProperties() throws Exception {
		
		/* détruit le fichier properties si il existe. */
		Files.deleteIfExists(
				UtilisateurCerbereGestionnairePreferencesRG
					.getPathAbsoluPreferencesProperties());
		
		assertFalse("le fichier properties ne doit plus exister : "
				, UtilisateurCerbereGestionnairePreferencesRG
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
		final Path propPath = UtilisateurCerbereGestionnairePreferencesRG
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
		final Path propPath = UtilisateurCerbereGestionnairePreferencesRG
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
		
	
	
} // FIN DE LA CLASSE CLASSE UtilisateurCerbereGestionnairePreferencesRGTest.
