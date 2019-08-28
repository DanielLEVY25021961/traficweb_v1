package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.sections;

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
 * CLASSE SectionHitGestionnairePreferencesRGTest :<br/>
 * Test JUnit de la classe {@link SectionHitGestionnairePreferencesRG}.<br/>
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
 * @since 13 août 2019
 *
 */
public class SectionHitGestionnairePreferencesRGTest {

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
	 * "Le Boolean doit valoir false : ".
	 */
	public static final String BOOLEAN_DOIT_VALOIR_FALSE 
		= "Le Boolean doit valoir false : ";

	/**
	 * "Le Boolean doit valoir true : ".
	 */
	public static final String BOOLEAN_DOIT_VALOIR_TRUE 
		= "Le Boolean doit valoir true : ";
	
	/**
	 * "la valeur doit être à true : ".
	 */
	public static final String VALEUR_DOIT_ETRE_TRUE 
		= "la valeur doit être à true : ";

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitGestionnairePreferencesRGTest.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public SectionHitGestionnairePreferencesRGTest() {
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
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testAfficherPreferences() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();
		
		String pref = null;
		
		/* récupération des prefs avec un properties inexistant. */
		pref = SectionHitGestionnairePreferencesRG
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(pref);
		}
		
		/* garantit que afficherPreferences() crée le fichier 
		 * properties avec des valeurs en dur. */
		assertTrue("le fichier properties doit avoir été généré sur disque : "
				, SectionHitGestionnairePreferencesRG
				.getFilePreferencesProperties().exists());
		
		/* garantit que afficherPreferences() affiche les preferences 
		 * stockées en dur dans la classe si le properties n'existait pas.*/
		assertNotNull("les preferences affichées ne doivent pas être null : "
				, pref);
		
		assertFalse("les preferences affichées ne doivent pas être vide : "
				, pref.isEmpty());
		
		/* modification de certaines valeurs. **********/
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartement(false);
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementRegex02(false);
		
		/* récupération des prefs dans un properties existant. */
		pref = SectionHitGestionnairePreferencesRG
					.afficherPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(pref);
		}
		
		/* garantit que afficherPreferences() lit les preferences 
		 * dans le fichier properties existant. */
		assertTrue("le fichier properties doit exister sur disque : "
				, SectionHitGestionnairePreferencesRG
				.getFilePreferencesProperties().exists());
		
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE
				, SectionHitGestionnairePreferencesRG
					.getValiderRGSectionHitNumDepartement());

		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE
				, SectionHitGestionnairePreferencesRG
					.getValiderRGSectionHitNumDepartementRegex02());
		
		/* remise des valeurs en DUR. */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartement(
					Boolean.valueOf(
							SectionHitGestionnairePreferencesRG
							.STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_EN_DUR));
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementRegex02(
					Boolean.valueOf(
							SectionHitGestionnairePreferencesRG
							.STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR));
	
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
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetPreferences() ********** ");
		}

		/* détruit le fichier properties si il existe. */
		this.detruireFichierProperties();

		Properties prefs = null;
		
		/* récupère preferences lorsque le properties est inexistant. */
		prefs = SectionHitGestionnairePreferencesRG.getPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefs);
		}
		
		/* garantit que getPreferences() crée le fichier 
		 * properties avec des valeurs en dur si il n'existait pas. */
		assertTrue("le fichier properties doit avoir été généré sur disque : "
				, SectionHitGestionnairePreferencesRG
				.getFilePreferencesProperties().exists());
		
		/* garantit que getPreferences() contient les preferences 
		 * stockées en dur dans la classe si le properties n'existait pas.*/
		assertNotNull("les preferences en dur ne doivent pas être null : "
				, prefs);
		
		assertFalse("les preferences en dur ne doivent pas être vide : "
				, prefs.isEmpty());
		
		/* modification de certaines valeurs. **********/
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartement(false);
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementRegex02(false);
		
		/* récupération des prefs dans un properties existant. */
		prefs = SectionHitGestionnairePreferencesRG.getPreferences();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(prefs);
		}

		/* garantit que getPreferences() lit les preferences 
		 * dans le properties existant. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE
				, SectionHitGestionnairePreferencesRG
					.getValiderRGSectionHitNumDepartement());

		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE
				, SectionHitGestionnairePreferencesRG
					.getValiderRGSectionHitNumDepartementRegex02());
		
		/* remise des valeurs en DUR. */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartement(
					Boolean.valueOf(
							SectionHitGestionnairePreferencesRG
							.STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_EN_DUR));
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementRegex02(
					Boolean.valueOf(
							SectionHitGestionnairePreferencesRG
							.STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR));

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
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetPathAbsoluPreferencesProperties() ********** ");
		}
		
		Path pathAbsoluPreferencesProperties = null;
		
		/* récupère pathAbsoluPreferencesProperties. */
		pathAbsoluPreferencesProperties 
			= SectionHitGestionnairePreferencesRG
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
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetCommentaire() ********** ");
		}
		
		String commentaire = null;
		
		/* récupère commentaire. */
		commentaire 
			= SectionHitGestionnairePreferencesRG
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
			System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetFilePreferencesProperties() ********** ");
		}
		
		File filePreferencesProperties = null;
		
		/* récupère filePreferencesProperties. */
		filePreferencesProperties 
			= SectionHitGestionnairePreferencesRG
				.getFilePreferencesProperties();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(filePreferencesProperties);
		}
		
		/* garantit que getFilePreferencesProperties() fournit le properties. */
		assertNotNull("filePreferencesProperties ne doit pas être null : "
				, filePreferencesProperties);
		
	} // Fin de testGetFilePreferencesProperties().________________________

	
	
	/* 1 - numDepartement. ************/
	/**
	 * teste la méthode getValiderRGSectionHitNumDepartement().<br/>
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
	public void testGetValiderRGSectionHitNumDepartement() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumDepartement() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartement();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartement dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartement(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartement();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartement après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a true dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartement(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumDepartement();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartement après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitNumDepartement().________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitNumDepartementRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitNumDepartementRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumDepartementRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumDepartementRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitNumDepartementRenseigne01()._____

	
	
	/**
	 * teste la méthode getValiderRGSectionHitNumDepartementRegex02().<br/>
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
	public void testGetValiderRGSectionHitNumDepartementRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumDepartementRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumDepartementRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitNumDepartementRegex02()._________

	
	
	/* 2 - numSection. ************/
	/**
	 * teste la méthode getValiderRGSectionHitNumSection().<br/>
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
	public void testGetValiderRGSectionHitNumSection() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumSection() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSection();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSection dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSection(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSection();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSection après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a true dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSection(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumSection();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSection après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitNumSection().____________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitNumSectionRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitNumSectionRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumSectionRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSectionRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSectionRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumSectionRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitNumSectionRenseigne01()._________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitNumSectionRegex02().<br/>
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
	public void testGetValiderRGSectionHitNumSectionRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumSectionRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSectionRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSectionRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumSectionRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitNumSectionRegex02()._____________

	
	
	/* 3 - sens. ************/
	/**
	 * teste la méthode getValiderRGSectionHitSens().<br/>
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
	public void testGetValiderRGSectionHitSens() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitSens() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSens();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSens dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSens(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSens();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSens après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a true dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSens(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitSens();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSens après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitSens().__________________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitSensRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitSensRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitSensRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitSensRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitSensRenseigne01()._______________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitSensRegex02().<br/>
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
	public void testGetValiderRGSectionHitSensRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitSensRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitSensRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitSensRegex02().___________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitSensNomenclature03().<br/>
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
	public void testGetValiderRGSectionHitSensNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitSensNomenclature03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensNomenclature03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensNomenclature03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensNomenclature03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensNomenclature03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensNomenclature03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensNomenclature03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitSensNomenclature03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensNomenclature03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitSensNomenclature03().____________

	
	
	/* 4 - nature. ************/
	/**
	 * teste la méthode getValiderRGSectionHitNature().<br/>
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
	public void testGetValiderRGSectionHitNature() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNature() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNature();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNature dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNature(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNature();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNature après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a true dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNature(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNature();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNature après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitNature().__________________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitNatureRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitNatureRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNatureRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNatureRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNatureRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNatureRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNatureRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNatureRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNatureRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNatureRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNatureRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitNatureRenseigne01()._______________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitNatureRegex02().<br/>
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
	public void testGetValiderRGSectionHitNatureRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNatureRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNatureRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNatureRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNatureRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNatureRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNatureRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNatureRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNatureRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNatureRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitNatureRegex02().___________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitNatureNomenclature03().<br/>
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
	public void testGetValiderRGSectionHitNatureNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNatureNomenclature03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNatureNomenclature03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNatureNomenclature03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNatureNomenclature03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNatureNomenclature03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNatureNomenclature03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNatureNomenclature03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNatureNomenclature03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNatureNomenclature03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitNatureNomenclature03().____________

	
	
	/* 5 - classe. ************/
	/**
	 * teste la méthode getValiderRGSectionHitClasse().<br/>
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
	public void testGetValiderRGSectionHitClasse() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitClasse() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasse();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasse dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasse(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasse();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasse après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a true dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasse(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClasse();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasse après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitClasse().____________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitClasseRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitClasseRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitClasseRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClasseRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitClasseRenseigne01()._________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitClasseRegex02().<br/>
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
	public void testGetValiderRGSectionHitClasseRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitClasseRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClasseRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitClasseRegex02()._____________

	
	
	/* 6 - anneeTraitement. **************/
	/**
	 * teste la méthode getValiderRGSectionHitAnneeTraitement().<br/>
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
	public void testGetValiderRGSectionHitAnneeTraitement() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAnneeTraitement() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAnneeTraitement();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeTraitement dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAnneeTraitement(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAnneeTraitement();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeTraitement après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAnneeTraitement(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAnneeTraitement();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeTraitement après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitAnneeTraitement().____________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitAnneeTraitementRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitAnneeTraitementRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAnneeTraitementRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAnneeTraitementRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeTraitementRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAnneeTraitementRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAnneeTraitementRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeTraitementRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAnneeTraitementRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAnneeTraitementRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeTraitementRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitAnneeTraitementRenseigne01()._________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitAnneeTraitementRegex02().<br/>
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
	public void testGetValiderRGSectionHitAnneeTraitementRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAnneeTraitementRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAnneeTraitementRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeTraitementRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAnneeTraitementRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAnneeTraitementRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeTraitementRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAnneeTraitementRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAnneeTraitementRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeTraitementRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitAnneeTraitementRegex02()._____________

	
	
	/* 7 - zoneLibre1. **************/
	/**
	 * teste la méthode getValiderRGSectionHitZoneLibre1().<br/>
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
	public void testGetValiderRGSectionHitZoneLibre1() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitZoneLibre1() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitZoneLibre1();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre1 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitZoneLibre1(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitZoneLibre1();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre1 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitZoneLibre1(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitZoneLibre1();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre1 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitZoneLibre1().____________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitZoneLibre1Renseigne01().<br/>
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
	public void testGetValiderRGSectionHitZoneLibre1Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitZoneLibre1Renseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitZoneLibre1Renseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre1Renseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitZoneLibre1Renseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitZoneLibre1Renseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre1Renseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitZoneLibre1Renseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitZoneLibre1Renseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre1Renseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitZoneLibre1Renseigne01()._________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitZoneLibre1Regex02().<br/>
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
	public void testGetValiderRGSectionHitZoneLibre1Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitZoneLibre1Regex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitZoneLibre1Regex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre1Regex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitZoneLibre1Regex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitZoneLibre1Regex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre1Regex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitZoneLibre1Regex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitZoneLibre1Regex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre1Regex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitZoneLibre1Regex02()._____________

	
	
	/* 8 - numRoute. **************/
	/**
	 * teste la méthode getValiderRGSectionHitNumRoute().<br/>
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
	public void testGetValiderRGSectionHitNumRoute() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumRoute() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumRoute();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumRoute dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumRoute(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumRoute();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumRoute après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumRoute(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumRoute();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumRoute après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitNumRoute().______________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitNumRouteRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitNumRouteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumRouteRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumRouteRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumRouteRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumRouteRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumRouteRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumRouteRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumRouteRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumRouteRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumRouteRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitNumRouteRenseigne01().___________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitNumRouteRegex02().<br/>
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
	public void testGetValiderRGSectionHitNumRouteRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumRouteRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumRouteRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumRouteRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumRouteRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumRouteRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumRouteRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumRouteRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumRouteRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumRouteRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitNumRouteRegex02()._______________

	
	
	/* 9 - indiceNumRoute. **************/
	/**
	 * teste la méthode getValiderRGSectionHitIndiceNumRoute().<br/>
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
	public void testGetValiderRGSectionHitIndiceNumRoute() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitIndiceNumRoute() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceNumRoute();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceNumRoute dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceNumRoute(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceNumRoute();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceNumRoute après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceNumRoute(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitIndiceNumRoute();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceNumRoute après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitIndiceNumRoute().________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitIndiceNumRouteRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitIndiceNumRouteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitIndiceNumRouteRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceNumRouteRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceNumRouteRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceNumRouteRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceNumRouteRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceNumRouteRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceNumRouteRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitIndiceNumRouteRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceNumRouteRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitIndiceNumRouteRenseigne01()._____

	
	
	/**
	 * teste la méthode getValiderRGSectionHitIndiceNumRouteRegex02().<br/>
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
	public void testGetValiderRGSectionHitIndiceNumRouteRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitIndiceNumRouteRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceNumRouteRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceNumRouteRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceNumRouteRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceNumRouteRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceNumRouteRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceNumRouteRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitIndiceNumRouteRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceNumRouteRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitIndiceNumRouteRegex02()._________	

	
	
	/* 10 - indiceLettreRoute. **************/
	/**
	 * teste la méthode getValiderRGSectionHitIndiceLettreRoute().<br/>
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
	public void testGetValiderRGSectionHitIndiceLettreRoute() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitIndiceLettreRoute() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceLettreRoute();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceLettreRoute dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceLettreRoute(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceLettreRoute();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceLettreRoute après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceLettreRoute(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitIndiceLettreRoute();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceLettreRoute après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitIndiceLettreRoute().____________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitIndiceLettreRouteRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitIndiceLettreRouteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitIndiceLettreRouteRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceLettreRouteRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceLettreRouteRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceLettreRouteRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceLettreRouteRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceLettreRouteRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceLettreRouteRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitIndiceLettreRouteRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceLettreRouteRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitIndiceLettreRouteRenseigne01().__

	
	
	/**
	 * teste la méthode getValiderRGSectionHitIndiceLettreRouteRegex02().<br/>
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
	public void testGetValiderRGSectionHitIndiceLettreRouteRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitIndiceLettreRouteRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceLettreRouteRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceLettreRouteRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceLettreRouteRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceLettreRouteRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceLettreRouteRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceLettreRouteRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitIndiceLettreRouteRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceLettreRouteRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitIndiceLettreRouteRegex02().______

	
	
	/* 11 - categorieAdminRoute. **************/
	/**
	 * teste la méthode getValiderRGSectionHitCategorieAdminRoute().<br/>
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
	public void testGetValiderRGSectionHitCategorieAdminRoute() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitCategorieAdminRoute() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitCategorieAdminRoute();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitCategorieAdminRoute dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitCategorieAdminRoute(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitCategorieAdminRoute();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitCategorieAdminRoute après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitCategorieAdminRoute(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitCategorieAdminRoute();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitCategorieAdminRoute après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitCategorieAdminRoute().____________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitCategorieAdminRouteRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitCategorieAdminRouteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitCategorieAdminRouteRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitCategorieAdminRouteRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitCategorieAdminRouteRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitCategorieAdminRouteRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitCategorieAdminRouteRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitCategorieAdminRouteRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitCategorieAdminRouteRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitCategorieAdminRouteRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitCategorieAdminRouteRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitCategorieAdminRouteRenseigne01()._________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitCategorieAdminRouteRegex02().<br/>
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
	public void testGetValiderRGSectionHitCategorieAdminRouteRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitCategorieAdminRouteRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitCategorieAdminRouteRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitCategorieAdminRouteRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitCategorieAdminRouteRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitCategorieAdminRouteRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitCategorieAdminRouteRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitCategorieAdminRouteRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitCategorieAdminRouteRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitCategorieAdminRouteRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitCategorieAdminRouteRegex02()._____________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitCategorieAdminRouteNomenclature03().<br/>
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
	public void testGetValiderRGSectionHitCategorieAdminRouteNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitCategorieAdminRouteNomenclature03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitCategorieAdminRouteNomenclature03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitCategorieAdminRouteNomenclature03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitCategorieAdminRouteNomenclature03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitCategorieAdminRouteNomenclature03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitCategorieAdminRouteNomenclature03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitCategorieAdminRouteNomenclature03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitCategorieAdminRouteNomenclature03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitCategorieAdminRouteNomenclature03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitCategorieAdminRouteNomenclature03().____________

	
	
	/* 12 - typeComptage. **************/
	/**
	 * teste la méthode getValiderRGSectionHitTypeComptage().<br/>
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
	public void testGetValiderRGSectionHitTypeComptage() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitTypeComptage() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeComptage();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeComptage dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitTypeComptage(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeComptage();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeComptage après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitTypeComptage(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitTypeComptage();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeComptage après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitTypeComptage().__________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitTypeComptageRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitTypeComptageRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitTypeComptageRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeComptageRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeComptageRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitTypeComptageRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeComptageRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeComptageRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitTypeComptageRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitTypeComptageRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeComptageRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitTypeComptageRenseigne01()._______

	
	
	/**
	 * teste la méthode getValiderRGSectionHitTypeComptageRegex02().<br/>
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
	public void testGetValiderRGSectionHitTypeComptageRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitTypeComptageRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeComptageRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeComptageRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitTypeComptageRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeComptageRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeComptageRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitTypeComptageRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitTypeComptageRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeComptageRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitTypeComptageRegex02().___________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitTypeComptageNomenclature03().<br/>
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
	public void testGetValiderRGSectionHitTypeComptageNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitTypeComptageNomenclature03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeComptageNomenclature03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeComptageNomenclature03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitTypeComptageNomenclature03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeComptageNomenclature03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeComptageNomenclature03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitTypeComptageNomenclature03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitTypeComptageNomenclature03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeComptageNomenclature03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitTypeComptageNomenclature03().____

	
	
	/* 13 - classementRoute. **************/
	/**
	 * teste la méthode getValiderRGSectionHitClassementRoute().<br/>
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
	public void testGetValiderRGSectionHitClassementRoute() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitClassementRoute() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClassementRoute();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClassementRoute dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClassementRoute(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClassementRoute();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClassementRoute après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClassementRoute(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClassementRoute();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClassementRoute après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitClassementRoute()._______________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitClassementRouteRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitClassementRouteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitClassementRouteRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClassementRouteRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClassementRouteRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClassementRouteRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClassementRouteRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClassementRouteRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClassementRouteRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClassementRouteRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClassementRouteRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitClassementRouteRenseigne01().____

	
	
	/**
	 * teste la méthode getValiderRGSectionHitClassementRouteRegex02().<br/>
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
	public void testGetValiderRGSectionHitClassementRouteRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitClassementRouteRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClassementRouteRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClassementRouteRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClassementRouteRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClassementRouteRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClassementRouteRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClassementRouteRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClassementRouteRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClassementRouteRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitClassementRouteRegex02().________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitClassementRouteNomenclature03().<br/>
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
	public void testGetValiderRGSectionHitClassementRouteNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitClassementRouteNomenclature03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClassementRouteNomenclature03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClassementRouteNomenclature03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClassementRouteNomenclature03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClassementRouteNomenclature03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClassementRouteNomenclature03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClassementRouteNomenclature03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClassementRouteNomenclature03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClassementRouteNomenclature03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitClassementRouteNomenclature03()._

	
	
	/* 14 - classeLargeurChausseeU. **************/
	/**
	 * teste la méthode getValiderRGSectionHitClasseLargeurChausseeU().<br/>
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
	public void testGetValiderRGSectionHitClasseLargeurChausseeU() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitClasseLargeurChausseeU() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseeU();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseeU dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseLargeurChausseeU(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseeU();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseeU après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseLargeurChausseeU(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClasseLargeurChausseeU();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseeU après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitClasseLargeurChausseeU().________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitClasseLargeurChausseeURenseigne01().<br/>
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
	public void testGetValiderRGSectionHitClasseLargeurChausseeURenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitClasseLargeurChausseeURenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseeURenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseeURenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseLargeurChausseeURenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseeURenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseeURenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseLargeurChausseeURenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClasseLargeurChausseeURenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseeURenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitClasseLargeurChausseeURenseigne01().

	
	
	/**
	 * teste la méthode getValiderRGSectionHitClasseLargeurChausseeURegex02().<br/>
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
	public void testGetValiderRGSectionHitClasseLargeurChausseeURegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitClasseLargeurChausseeURegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseeURegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseeURegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseLargeurChausseeURegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseeURegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseeURegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseLargeurChausseeURegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClasseLargeurChausseeURegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseeURegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitClasseLargeurChausseeURegex02()._

	
	
	/**
	 * teste la méthode getValiderRGSectionHitClasseLargeurChausseeUNomenclature03().<br/>
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
	public void testGetValiderRGSectionHitClasseLargeurChausseeUNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitClasseLargeurChausseeUNomenclature03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseeUNomenclature03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseeUNomenclature03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseLargeurChausseeUNomenclature03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseeUNomenclature03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseeUNomenclature03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseLargeurChausseeUNomenclature03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClasseLargeurChausseeUNomenclature03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseeUNomenclature03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitClasseLargeurChausseeUNomenclature03().

	
	
	/* 15 - classeLargeurChausseesS. **************/
	/**
	 * teste la méthode getValiderRGSectionHitClasseLargeurChausseesS().<br/>
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
	public void testGetValiderRGSectionHitClasseLargeurChausseesS() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitClasseLargeurChausseesS() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseesS();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseesS dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseLargeurChausseesS(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseesS();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseesS après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseLargeurChausseesS(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClasseLargeurChausseesS();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseesS après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitClasseLargeurChausseesS()._______

	
	
	/**
	 * teste la méthode getValiderRGSectionHitClasseLargeurChausseesSRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitClasseLargeurChausseesSRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitClasseLargeurChausseesSRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseesSRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseesSRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseLargeurChausseesSRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseesSRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseesSRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseLargeurChausseesSRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClasseLargeurChausseesSRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseesSRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitClasseLargeurChausseesSRenseigne01().

	
	
	/**
	 * teste la méthode getValiderRGSectionHitClasseLargeurChausseesSRegex02().<br/>
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
	public void testGetValiderRGSectionHitClasseLargeurChausseesSRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitClasseLargeurChausseesSRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseesSRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseesSRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseLargeurChausseesSRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseesSRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseesSRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseLargeurChausseesSRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClasseLargeurChausseesSRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseesSRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitClasseLargeurChausseesSRegex02().

	
	
	/**
	 * teste la méthode getValiderRGSectionHitClasseLargeurChausseesSNomenclature03().<br/>
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
	public void testGetValiderRGSectionHitClasseLargeurChausseesSNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitClasseLargeurChausseesSNomenclature03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseesSNomenclature03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseesSNomenclature03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseLargeurChausseesSNomenclature03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseesSNomenclature03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseesSNomenclature03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitClasseLargeurChausseesSNomenclature03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClasseLargeurChausseesSNomenclature03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitClasseLargeurChausseesSNomenclature03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitClasseLargeurChausseesSNomenclature03().

	
	
	/* 16 - typeReseau. **************/
	/**
	 * teste la méthode getValiderRGSectionHitTypeReseau().<br/>
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
	public void testGetValiderRGSectionHitTypeReseau() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitTypeReseau() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeReseau();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeReseau dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitTypeReseau(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeReseau();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeReseau après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitTypeReseau(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitTypeReseau();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeReseau après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitTypeReseau().____________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitTypeReseauRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitTypeReseauRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitTypeReseauRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeReseauRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeReseauRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitTypeReseauRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeReseauRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeReseauRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitTypeReseauRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitTypeReseauRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeReseauRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitTypeReseauRenseigne01()._________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitTypeReseauRegex02().<br/>
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
	public void testGetValiderRGSectionHitTypeReseauRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitTypeReseauRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeReseauRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeReseauRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitTypeReseauRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeReseauRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeReseauRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitTypeReseauRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitTypeReseauRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeReseauRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitTypeReseauRegex02()._____________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitTypeReseauNomenclature03().<br/>
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
	public void testGetValiderRGSectionHitTypeReseauNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitTypeReseauNomenclature03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeReseauNomenclature03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeReseauNomenclature03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitTypeReseauNomenclature03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeReseauNomenclature03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeReseauNomenclature03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitTypeReseauNomenclature03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitTypeReseauNomenclature03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitTypeReseauNomenclature03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitTypeReseauNomenclature03().______

	
	
	/* 17 - pRoupK. **************/
	/**
	 * teste la méthode getValiderRGSectionHitPRoupK().<br/>
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
	public void testGetValiderRGSectionHitPRoupK() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPRoupK() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPRoupK();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPRoupK dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPRoupK(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPRoupK();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPRoupK après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPRoupK(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPRoupK();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPRoupK après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitPRoupK().________________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPRoupKRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitPRoupKRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPRoupKRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPRoupKRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPRoupKRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPRoupKRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPRoupKRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPRoupKRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPRoupKRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPRoupKRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPRoupKRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPRoupKRenseigne01()._____________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPRoupKRegex02().<br/>
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
	public void testGetValiderRGSectionHitPRoupKRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPRoupKRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPRoupKRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPRoupKRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPRoupKRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPRoupKRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPRoupKRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPRoupKRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPRoupKRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPRoupKRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPRoupKRegex02()._________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPRoupKNomenclature03().<br/>
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
	public void testGetValiderRGSectionHitPRoupKNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPRoupKNomenclature03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPRoupKNomenclature03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPRoupKNomenclature03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPRoupKNomenclature03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPRoupKNomenclature03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPRoupKNomenclature03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPRoupKNomenclature03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPRoupKNomenclature03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPRoupKNomenclature03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPRoupKNomenclature03().__________

	
	
	/* 18 - lieuDitOrigine. **************/
	/**
	 * teste la méthode getValiderRGSectionHitLieuDitOrigine().<br/>
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
	public void testGetValiderRGSectionHitLieuDitOrigine() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitLieuDitOrigine() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitOrigine();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitOrigine dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitOrigine(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitOrigine();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitOrigine après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitOrigine(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLieuDitOrigine();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitOrigine après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitLieuDitOrigine().________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitLieuDitOrigineRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitLieuDitOrigineRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitLieuDitOrigineRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitOrigineRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitOrigineRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitOrigineRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitOrigineRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitOrigineRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitOrigineRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLieuDitOrigineRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitOrigineRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitLieuDitOrigineRenseigne01()._____

	
	
	/**
	 * teste la méthode getValiderRGSectionHitLieuDitOrigineRegex02().<br/>
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
	public void testGetValiderRGSectionHitLieuDitOrigineRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitLieuDitOrigineRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitOrigineRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitOrigineRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitOrigineRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitOrigineRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitOrigineRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitOrigineRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLieuDitOrigineRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitOrigineRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitLieuDitOrigineRegex02()._________

	
	
	/* 19 - prOrigine. **************/
	/**
	 * teste la méthode getValiderRGSectionHitPrOrigine().<br/>
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
	public void testGetValiderRGSectionHitPrOrigine() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPrOrigine() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrOrigine();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrOrigine dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrOrigine(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrOrigine();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrOrigine après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrOrigine(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPrOrigine();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrOrigine après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitPrOrigine()._____________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPrOrigineRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitPrOrigineRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPrOrigineRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrOrigineRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrOrigineRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrOrigineRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrOrigineRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrOrigineRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrOrigineRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPrOrigineRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrOrigineRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPrOrigineRenseigne01().__________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPrOrigineRegex02().<br/>
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
	public void testGetValiderRGSectionHitPrOrigineRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPrOrigineRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrOrigineRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrOrigineRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrOrigineRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrOrigineRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrOrigineRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrOrigineRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPrOrigineRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrOrigineRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPrOrigineRegex02().______________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPrOrigineNumerique03().<br/>
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
	public void testGetValiderRGSectionHitPrOrigineNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPrOrigineNumerique03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrOrigineNumerique03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrOrigineNumerique03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrOrigineNumerique03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrOrigineNumerique03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrOrigineNumerique03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrOrigineNumerique03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPrOrigineNumerique03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrOrigineNumerique03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPrOrigineNumerique03().__________

	
			
	/**
	 * détruit le fichier properties si il existe.<br/>
	 *
	 * @throws Exception
	 */
	private void detruireFichierProperties() throws Exception {
		
		/* détruit le fichier properties si il existe. */
		Files.deleteIfExists(
				SectionHitGestionnairePreferencesRG
					.getPathAbsoluPreferencesProperties());
		
		assertFalse("le fichier properties ne doit plus exister : "
				, SectionHitGestionnairePreferencesRG
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
		final Path propPath = SectionHitGestionnairePreferencesRG
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
				
				// COPIE AVEC REMPLACEMENT ************************
				Files.copy(
						propPath
							, propOldPath
								, StandardCopyOption.REPLACE_EXISTING);
				
				assertTrue("le properties old doit avoir été créé : "
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
		final Path propPath = SectionHitGestionnairePreferencesRG
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
		
	
		
} // FIN DE LA CLASSE SectionHitGestionnairePreferencesRGTest.---------------
