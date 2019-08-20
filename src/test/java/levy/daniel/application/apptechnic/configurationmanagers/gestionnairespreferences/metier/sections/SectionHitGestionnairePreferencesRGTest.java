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
