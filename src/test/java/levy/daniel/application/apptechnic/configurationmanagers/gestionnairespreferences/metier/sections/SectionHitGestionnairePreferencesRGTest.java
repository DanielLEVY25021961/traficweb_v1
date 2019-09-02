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

	
	
	/* 20 - absOrigine. **************/
	/**
	 * teste la méthode getValiderRGSectionHitAbsOrigine().<br/>
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
	public void testGetValiderRGSectionHitAbsOrigine() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAbsOrigine() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsOrigine();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsOrigine dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsOrigine(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsOrigine();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsOrigine après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsOrigine(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAbsOrigine();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsOrigine après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitAbsOrigine().____________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitAbsOrigineRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitAbsOrigineRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAbsOrigineRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsOrigineRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsOrigineRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsOrigineRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsOrigineRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsOrigineRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsOrigineRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAbsOrigineRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsOrigineRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitAbsOrigineRenseigne01()._________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitAbsOrigineRegex02().<br/>
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
	public void testGetValiderRGSectionHitAbsOrigineRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAbsOrigineRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsOrigineRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsOrigineRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsOrigineRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsOrigineRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsOrigineRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsOrigineRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAbsOrigineRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsOrigineRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitAbsOrigineRegex02()._____________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitAbsOrigineNumerique03().<br/>
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
	public void testGetValiderRGSectionHitAbsOrigineNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAbsOrigineNumerique03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsOrigineNumerique03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsOrigineNumerique03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsOrigineNumerique03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsOrigineNumerique03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsOrigineNumerique03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsOrigineNumerique03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAbsOrigineNumerique03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsOrigineNumerique03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitAbsOrigineNumerique03()._________

	
	
	/* 21 - lieuDitExtremite. **************/
	/**
	 * teste la méthode getValiderRGSectionHitLieuDitExtremite().<br/>
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
	public void testGetValiderRGSectionHitLieuDitExtremite() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitLieuDitExtremite() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitExtremite();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitExtremite dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitExtremite(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitExtremite();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitExtremite après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitExtremite(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLieuDitExtremite();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitExtremite après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitLieuDitExtremite().______________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitLieuDitExtremiteRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitLieuDitExtremiteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitLieuDitExtremiteRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitExtremiteRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitExtremiteRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitExtremiteRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitExtremiteRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitExtremiteRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitExtremiteRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLieuDitExtremiteRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitExtremiteRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitLieuDitExtremiteRenseigne01().___

	
	
	/**
	 * teste la méthode getValiderRGSectionHitLieuDitExtremiteRegex02().<br/>
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
	public void testGetValiderRGSectionHitLieuDitExtremiteRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitLieuDitExtremiteRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitExtremiteRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitExtremiteRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitExtremiteRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitExtremiteRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitExtremiteRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitExtremiteRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLieuDitExtremiteRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitExtremiteRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitLieuDitExtremiteRegex02()._______

	
	
	/* 22 - prExtremite. **************/
	/**
	 * teste la méthode getValiderRGSectionHitPrExtremite().<br/>
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
	public void testGetValiderRGSectionHitPrExtremite() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPrExtremite() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrExtremite();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrExtremite dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrExtremite(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrExtremite();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrExtremite après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrExtremite(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPrExtremite();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrExtremite après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitPrExtremite().___________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPrExtremiteRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitPrExtremiteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPrExtremiteRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrExtremiteRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrExtremiteRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrExtremiteRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrExtremiteRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrExtremiteRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrExtremiteRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPrExtremiteRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrExtremiteRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPrExtremiteRenseigne01().________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPrExtremiteRegex02().<br/>
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
	public void testGetValiderRGSectionHitPrExtremiteRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPrExtremiteRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrExtremiteRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrExtremiteRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrExtremiteRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrExtremiteRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrExtremiteRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrExtremiteRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPrExtremiteRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrExtremiteRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPrExtremiteRegex02().____________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPrExtremiteNumerique03().<br/>
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
	public void testGetValiderRGSectionHitPrExtremiteNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPrExtremiteNumerique03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrExtremiteNumerique03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrExtremiteNumerique03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrExtremiteNumerique03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrExtremiteNumerique03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrExtremiteNumerique03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrExtremiteNumerique03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPrExtremiteNumerique03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrExtremiteNumerique03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPrExtremiteNumerique03().________

	
	
	/* 23 - absExtremite. **************/
	/**
	 * teste la méthode getValiderRGSectionHitAbsExtremite().<br/>
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
	public void testGetValiderRGSectionHitAbsExtremite() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAbsExtremite() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsExtremite();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsExtremite dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsExtremite(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsExtremite();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsExtremite après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsExtremite(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAbsExtremite();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsExtremite après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitAbsExtremite().__________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitAbsExtremiteRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitAbsExtremiteRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAbsExtremiteRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsExtremiteRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsExtremiteRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsExtremiteRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsExtremiteRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsExtremiteRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsExtremiteRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAbsExtremiteRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsExtremiteRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitAbsExtremiteRenseigne01()._______

	
	
	/**
	 * teste la méthode getValiderRGSectionHitAbsExtremiteRegex02().<br/>
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
	public void testGetValiderRGSectionHitAbsExtremiteRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAbsExtremiteRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsExtremiteRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsExtremiteRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsExtremiteRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsExtremiteRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsExtremiteRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsExtremiteRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAbsExtremiteRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsExtremiteRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitAbsExtremiteRegex02().___________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitAbsExtremiteNumerique03().<br/>
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
	public void testGetValiderRGSectionHitAbsExtremiteNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAbsExtremiteNumerique03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsExtremiteNumerique03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsExtremiteNumerique03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsExtremiteNumerique03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsExtremiteNumerique03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsExtremiteNumerique03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsExtremiteNumerique03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAbsExtremiteNumerique03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsExtremiteNumerique03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitAbsExtremiteNumerique03()._______

	
	
	/* 24 - lieuDitComptage. **************/
	/**
	 * teste la méthode getValiderRGSectionHitLieuDitComptage().<br/>
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
	public void testGetValiderRGSectionHitLieuDitComptage() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitLieuDitComptage() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitComptage();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitComptage dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitComptage(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitComptage();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitComptage après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitComptage(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLieuDitComptage();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitComptage après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitLieuDitComptage()._______________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitLieuDitComptageRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitLieuDitComptageRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitLieuDitComptageRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitComptageRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitComptageRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitComptageRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitComptageRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitComptageRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitComptageRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLieuDitComptageRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitComptageRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitLieuDitComptageRenseigne01().____

	
	
	/**
	 * teste la méthode getValiderRGSectionHitLieuDitComptageRegex02().<br/>
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
	public void testGetValiderRGSectionHitLieuDitComptageRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitLieuDitComptageRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitComptageRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitComptageRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitComptageRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitComptageRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitComptageRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLieuDitComptageRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLieuDitComptageRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLieuDitComptageRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitLieuDitComptageRegex02().________

	
	
	/* 25 - prComptage. **************/
	/**
	 * teste la méthode getValiderRGSectionHitPrComptage().<br/>
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
	public void testGetValiderRGSectionHitPrComptage() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPrComptage() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrComptage();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrComptage dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrComptage(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrComptage();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrComptage après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrComptage(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPrComptage();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrComptage après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitPrComptage().____________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPrComptageRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitPrComptageRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPrComptageRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrComptageRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrComptageRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrComptageRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrComptageRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrComptageRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrComptageRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPrComptageRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrComptageRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPrComptageRenseigne01()._________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPrComptageRegex02().<br/>
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
	public void testGetValiderRGSectionHitPrComptageRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPrComptageRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrComptageRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrComptageRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrComptageRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrComptageRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrComptageRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrComptageRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPrComptageRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrComptageRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPrComptageRegex02()._____________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPrComptageNumerique03().<br/>
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
	public void testGetValiderRGSectionHitPrComptageNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPrComptageNumerique03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrComptageNumerique03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrComptageNumerique03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrComptageNumerique03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrComptageNumerique03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrComptageNumerique03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPrComptageNumerique03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPrComptageNumerique03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPrComptageNumerique03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPrComptageNumerique03()._________

	
	
	/* 26 - absComptage. **************/
	/**
	 * teste la méthode getValiderRGSectionHitAbsComptage().<br/>
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
	public void testGetValiderRGSectionHitAbsComptage() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAbsComptage() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsComptage();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsComptage dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsComptage(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsComptage();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsComptage après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsComptage(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAbsComptage();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsComptage après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitAbsComptage().___________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitAbsComptageRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitAbsComptageRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAbsComptageRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsComptageRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsComptageRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsComptageRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsComptageRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsComptageRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsComptageRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAbsComptageRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsComptageRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitAbsComptageRenseigne01().________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitAbsComptageRegex02().<br/>
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
	public void testGetValiderRGSectionHitAbsComptageRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAbsComptageRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsComptageRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsComptageRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsComptageRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsComptageRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsComptageRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsComptageRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAbsComptageRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsComptageRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitAbsComptageRegex02().____________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitAbsComptageNumerique03().<br/>
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
	public void testGetValiderRGSectionHitAbsComptageNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAbsComptageNumerique03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsComptageNumerique03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsComptageNumerique03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsComptageNumerique03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsComptageNumerique03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsComptageNumerique03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAbsComptageNumerique03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAbsComptageNumerique03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAbsComptageNumerique03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitAbsComptageNumerique03().________

	
	
	/* 27 - longueurSection. **************/
	/**
	 * teste la méthode getValiderRGSectionHitLongueurSection().<br/>
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
	public void testGetValiderRGSectionHitLongueurSection() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitLongueurSection() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurSection();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurSection dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLongueurSection(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurSection();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurSection après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLongueurSection(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLongueurSection();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurSection après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitLongueurSection()._______________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitLongueurSectionRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitLongueurSectionRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitLongueurSectionRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurSectionRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurSectionRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLongueurSectionRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurSectionRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurSectionRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLongueurSectionRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLongueurSectionRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurSectionRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitLongueurSectionRenseigne01().____

	
	
	/**
	 * teste la méthode getValiderRGSectionHitLongueurSectionRegex02().<br/>
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
	public void testGetValiderRGSectionHitLongueurSectionRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitLongueurSectionRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurSectionRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurSectionRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLongueurSectionRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurSectionRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurSectionRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLongueurSectionRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLongueurSectionRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurSectionRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitLongueurSectionRegex02().________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitLongueurSectionNumerique03().<br/>
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
	public void testGetValiderRGSectionHitLongueurSectionNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitLongueurSectionNumerique03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurSectionNumerique03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurSectionNumerique03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLongueurSectionNumerique03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurSectionNumerique03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurSectionNumerique03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLongueurSectionNumerique03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLongueurSectionNumerique03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurSectionNumerique03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitLongueurSectionNumerique03().____

	
	
	/* 28 - longueurRaseCampagne. **************/
	/**
	 * teste la méthode getValiderRGSectionHitLongueurRaseCampagne().<br/>
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
	public void testGetValiderRGSectionHitLongueurRaseCampagne() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitLongueurRaseCampagne() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurRaseCampagne();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurRaseCampagne dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLongueurRaseCampagne(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurRaseCampagne();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurRaseCampagne après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLongueurRaseCampagne(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLongueurRaseCampagne();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurRaseCampagne après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitLongueurRaseCampagne().__________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitLongueurRaseCampagneRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitLongueurRaseCampagneRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitLongueurRaseCampagneRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurRaseCampagneRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurRaseCampagneRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLongueurRaseCampagneRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurRaseCampagneRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurRaseCampagneRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLongueurRaseCampagneRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLongueurRaseCampagneRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurRaseCampagneRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitLongueurRaseCampagneRenseigne01().

	
	
	/**
	 * teste la méthode getValiderRGSectionHitLongueurRaseCampagneRegex02().<br/>
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
	public void testGetValiderRGSectionHitLongueurRaseCampagneRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitLongueurRaseCampagneRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurRaseCampagneRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurRaseCampagneRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLongueurRaseCampagneRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurRaseCampagneRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurRaseCampagneRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLongueurRaseCampagneRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLongueurRaseCampagneRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurRaseCampagneRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitLongueurRaseCampagneRegex02().___

	
	
	/**
	 * teste la méthode getValiderRGSectionHitLongueurRaseCampagneNumerique03().<br/>
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
	public void testGetValiderRGSectionHitLongueurRaseCampagneNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitLongueurRaseCampagneNumerique03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurRaseCampagneNumerique03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurRaseCampagneNumerique03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLongueurRaseCampagneNumerique03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurRaseCampagneNumerique03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurRaseCampagneNumerique03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitLongueurRaseCampagneNumerique03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLongueurRaseCampagneNumerique03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitLongueurRaseCampagneNumerique03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitLongueurRaseCampagneNumerique03().

	
	
	/* 29 - numDepartementRattachement. **************/
	/**
	 * teste la méthode getValiderRGSectionHitNumDepartementRattachement().<br/>
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
	public void testGetValiderRGSectionHitNumDepartementRattachement() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumDepartementRattachement() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementRattachement();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementRattachement dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementRattachement(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementRattachement();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementRattachement après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementRattachement(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumDepartementRattachement();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementRattachement après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitNumDepartementRattachement().____

	
	
	/**
	 * teste la méthode getValiderRGSectionHitNumDepartementRattachementRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitNumDepartementRattachementRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumDepartementRattachementRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementRattachementRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementRattachementRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementRattachementRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementRattachementRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementRattachementRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementRattachementRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumDepartementRattachementRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementRattachementRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitNumDepartementRattachementRenseigne01().

	
	
	/**
	 * teste la méthode getValiderRGSectionHitNumDepartementRattachementRegex02().<br/>
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
	public void testGetValiderRGSectionHitNumDepartementRattachementRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumDepartementRattachementRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementRattachementRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementRattachementRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementRattachementRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementRattachementRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementRattachementRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementRattachementRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumDepartementRattachementRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementRattachementRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitNumDepartementRattachementRegex02().

	
	
	/* 30 - numSectionRattachement. **************/
	/**
	 * teste la méthode getValiderRGSectionHitNumSectionRattachement().<br/>
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
	public void testGetValiderRGSectionHitNumSectionRattachement() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumSectionRattachement() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionRattachement();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionRattachement dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSectionRattachement(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionRattachement();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionRattachement après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSectionRattachement(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumSectionRattachement();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionRattachement après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitNumSectionRattachement().________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitNumSectionRattachementRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitNumSectionRattachementRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumSectionRattachementRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionRattachementRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionRattachementRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSectionRattachementRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionRattachementRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionRattachementRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSectionRattachementRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumSectionRattachementRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionRattachementRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitNumSectionRattachementRenseigne01().

	
	
	/**
	 * teste la méthode getValiderRGSectionHitNumSectionRattachementRegex02().<br/>
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
	public void testGetValiderRGSectionHitNumSectionRattachementRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumSectionRattachementRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionRattachementRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionRattachementRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSectionRattachementRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionRattachementRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionRattachementRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSectionRattachementRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumSectionRattachementRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionRattachementRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitNumSectionRattachementRegex02()._

	
	
	/* 31 - sensRattachement. **************/
	/**
	 * teste la méthode getValiderRGSectionHitSensRattachement().<br/>
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
	public void testGetValiderRGSectionHitSensRattachement() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitSensRattachement() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensRattachement();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRattachement dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensRattachement(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensRattachement();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRattachement après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensRattachement(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitSensRattachement();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRattachement après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitSensRattachement().______________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitSensRattachementRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitSensRattachementRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitSensRattachementRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensRattachementRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRattachementRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensRattachementRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensRattachementRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRattachementRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensRattachementRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitSensRattachementRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRattachementRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitSensRattachementRenseigne01().___

	
	
	/**
	 * teste la méthode getValiderRGSectionHitSensRattachementRegex02().<br/>
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
	public void testGetValiderRGSectionHitSensRattachementRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitSensRattachementRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensRattachementRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRattachementRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensRattachementRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensRattachementRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRattachementRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensRattachementRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitSensRattachementRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRattachementRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitSensRattachementRegex02()._______

	
	
	/**
	 * teste la méthode getValiderRGSectionHitSensRattachementNomenclature03().<br/>
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
	public void testGetValiderRGSectionHitSensRattachementNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitSensRattachementNomenclature03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensRattachementNomenclature03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRattachementNomenclature03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensRattachementNomenclature03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensRattachementNomenclature03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRattachementNomenclature03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensRattachementNomenclature03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitSensRattachementNomenclature03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensRattachementNomenclature03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitSensRattachementNomenclature03().

	
	
	/* 29 - numDepartementLimitrophe. **************/
	/**
	 * teste la méthode getValiderRGSectionHitNumDepartementLimitrophe().<br/>
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
	public void testGetValiderRGSectionHitNumDepartementLimitrophe() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumDepartementLimitrophe() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementLimitrophe();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementLimitrophe dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementLimitrophe(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementLimitrophe();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementLimitrophe après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementLimitrophe(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumDepartementLimitrophe();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementLimitrophe après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitNumDepartementLimitrophe().____

	
	
	/**
	 * teste la méthode getValiderRGSectionHitNumDepartementLimitropheRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitNumDepartementLimitropheRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumDepartementLimitropheRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementLimitropheRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementLimitropheRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementLimitropheRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementLimitropheRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementLimitropheRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementLimitropheRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumDepartementLimitropheRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementLimitropheRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitNumDepartementLimitropheRenseigne01().

	
	
	/**
	 * teste la méthode getValiderRGSectionHitNumDepartementLimitropheRegex02().<br/>
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
	public void testGetValiderRGSectionHitNumDepartementLimitropheRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumDepartementLimitropheRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementLimitropheRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementLimitropheRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementLimitropheRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementLimitropheRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementLimitropheRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumDepartementLimitropheRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumDepartementLimitropheRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumDepartementLimitropheRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitNumDepartementLimitropheRegex02().

	
	
	/* 30 - numSectionLimitrophe. **************/
	/**
	 * teste la méthode getValiderRGSectionHitNumSectionLimitrophe().<br/>
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
	public void testGetValiderRGSectionHitNumSectionLimitrophe() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumSectionLimitrophe() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionLimitrophe();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionLimitrophe dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSectionLimitrophe(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionLimitrophe();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionLimitrophe après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSectionLimitrophe(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumSectionLimitrophe();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionLimitrophe après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitNumSectionLimitrophe().________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitNumSectionLimitropheRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitNumSectionLimitropheRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumSectionLimitropheRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionLimitropheRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionLimitropheRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSectionLimitropheRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionLimitropheRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionLimitropheRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSectionLimitropheRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumSectionLimitropheRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionLimitropheRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitNumSectionLimitropheRenseigne01().

	
	
	/**
	 * teste la méthode getValiderRGSectionHitNumSectionLimitropheRegex02().<br/>
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
	public void testGetValiderRGSectionHitNumSectionLimitropheRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitNumSectionLimitropheRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionLimitropheRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionLimitropheRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSectionLimitropheRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionLimitropheRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionLimitropheRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitNumSectionLimitropheRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumSectionLimitropheRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitNumSectionLimitropheRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitNumSectionLimitropheRegex02()._

	
	
	/* 31 - sensLimitrophe. **************/
	/**
	 * teste la méthode getValiderRGSectionHitSensLimitrophe().<br/>
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
	public void testGetValiderRGSectionHitSensLimitrophe() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitSensLimitrophe() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensLimitrophe();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensLimitrophe dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensLimitrophe(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensLimitrophe();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensLimitrophe après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensLimitrophe(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitSensLimitrophe();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensLimitrophe après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitSensLimitrophe().______________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitSensLimitropheRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitSensLimitropheRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitSensLimitropheRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensLimitropheRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensLimitropheRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensLimitropheRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensLimitropheRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensLimitropheRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensLimitropheRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitSensLimitropheRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensLimitropheRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitSensLimitropheRenseigne01().___

	
	
	/**
	 * teste la méthode getValiderRGSectionHitSensLimitropheRegex02().<br/>
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
	public void testGetValiderRGSectionHitSensLimitropheRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitSensLimitropheRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensLimitropheRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensLimitropheRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensLimitropheRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensLimitropheRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensLimitropheRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensLimitropheRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitSensLimitropheRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensLimitropheRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitSensLimitropheRegex02()._______

	
	
	/**
	 * teste la méthode getValiderRGSectionHitSensLimitropheNomenclature03().<br/>
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
	public void testGetValiderRGSectionHitSensLimitropheNomenclature03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitSensLimitropheNomenclature03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensLimitropheNomenclature03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensLimitropheNomenclature03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensLimitropheNomenclature03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensLimitropheNomenclature03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensLimitropheNomenclature03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitSensLimitropheNomenclature03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitSensLimitropheNomenclature03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitSensLimitropheNomenclature03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitSensLimitropheNomenclature03().

	
	
	/* 35 - moisSectionnement. **************/
	/**
	 * teste la méthode getValiderRGSectionHitMoisSectionnement().<br/>
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
	public void testGetValiderRGSectionHitMoisSectionnement() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitMoisSectionnement() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMoisSectionnement();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMoisSectionnement dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMoisSectionnement(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMoisSectionnement();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMoisSectionnement après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMoisSectionnement(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitMoisSectionnement();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMoisSectionnement après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitMoisSectionnement()._____________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitMoisSectionnementRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitMoisSectionnementRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitMoisSectionnementRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMoisSectionnementRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMoisSectionnementRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMoisSectionnementRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMoisSectionnementRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMoisSectionnementRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMoisSectionnementRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitMoisSectionnementRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMoisSectionnementRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitMoisSectionnementRenseigne01().__

	
	
	/**
	 * teste la méthode getValiderRGSectionHitMoisSectionnementRegex02().<br/>
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
	public void testGetValiderRGSectionHitMoisSectionnementRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitMoisSectionnementRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMoisSectionnementRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMoisSectionnementRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMoisSectionnementRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMoisSectionnementRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMoisSectionnementRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMoisSectionnementRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitMoisSectionnementRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMoisSectionnementRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitMoisSectionnementRegex02().______

	
	
	/* 36 - anneeSectionnement. **************/
	/**
	 * teste la méthode getValiderRGSectionHitAnneeSectionnement().<br/>
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
	public void testGetValiderRGSectionHitAnneeSectionnement() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAnneeSectionnement() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAnneeSectionnement();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeSectionnement dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAnneeSectionnement(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAnneeSectionnement();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeSectionnement après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAnneeSectionnement(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAnneeSectionnement();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeSectionnement après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitAnneeSectionnement().____________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitAnneeSectionnementRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitAnneeSectionnementRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAnneeSectionnementRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAnneeSectionnementRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeSectionnementRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAnneeSectionnementRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAnneeSectionnementRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeSectionnementRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAnneeSectionnementRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAnneeSectionnementRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeSectionnementRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitAnneeSectionnementRenseigne01()._

	
	
	/**
	 * teste la méthode getValiderRGSectionHitAnneeSectionnementRegex02().<br/>
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
	public void testGetValiderRGSectionHitAnneeSectionnementRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitAnneeSectionnementRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAnneeSectionnementRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeSectionnementRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAnneeSectionnementRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAnneeSectionnementRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeSectionnementRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitAnneeSectionnementRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAnneeSectionnementRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitAnneeSectionnementRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitAnneeSectionnementRegex02()._____

	
	
	/* 37 - zoneLibre2. **************/
	/**
	 * teste la méthode getValiderRGSectionHitZoneLibre2().<br/>
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
	public void testGetValiderRGSectionHitZoneLibre2() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitZoneLibre2() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitZoneLibre2();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre2 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitZoneLibre2(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitZoneLibre2();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre2 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitZoneLibre2(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitZoneLibre2();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre2 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitZoneLibre2().____________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitZoneLibre2Renseigne01().<br/>
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
	public void testGetValiderRGSectionHitZoneLibre2Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitZoneLibre2Renseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitZoneLibre2Renseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre2Renseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitZoneLibre2Renseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitZoneLibre2Renseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre2Renseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitZoneLibre2Renseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitZoneLibre2Renseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre2Renseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitZoneLibre2Renseigne01()._________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitZoneLibre2Regex02().<br/>
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
	public void testGetValiderRGSectionHitZoneLibre2Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitZoneLibre2Regex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitZoneLibre2Regex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre2Regex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitZoneLibre2Regex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitZoneLibre2Regex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre2Regex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitZoneLibre2Regex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitZoneLibre2Regex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitZoneLibre2Regex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitZoneLibre2Regex02()._____________

	
	
	/* 38 - mjaN. **************/
	/**
	 * teste la méthode getValiderRGSectionHitMjaN().<br/>
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
	public void testGetValiderRGSectionHitMjaN() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitMjaN() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjaN();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjaN dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjaN(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjaN();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjaN après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjaN(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitMjaN();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjaN après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitMjaN().____________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitMjaNRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitMjaNRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitMjaNRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjaNRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjaNRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjaNRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjaNRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjaNRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjaNRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitMjaNRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjaNRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitMjaNRenseigne01()._______________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitMjaNRegex02().<br/>
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
	public void testGetValiderRGSectionHitMjaNRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitMjaNRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjaNRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjaNRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjaNRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjaNRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjaNRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjaNRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitMjaNRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjaNRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitMjaNRegex02().___________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitMjaNNumerique03().<br/>
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
	public void testGetValiderRGSectionHitMjaNNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitMjaNNumerique03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjaNNumerique03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjaNNumerique03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjaNNumerique03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjaNNumerique03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjaNNumerique03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjaNNumerique03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitMjaNNumerique03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjaNNumerique03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitMjaNNumerique03()._______________

	
	
	/* 39 - modeCalculN. **************/
	/**
	 * teste la méthode getValiderRGSectionHitModeCalculN().<br/>
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
	public void testGetValiderRGSectionHitModeCalculN() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitModeCalculN() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitModeCalculN();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitModeCalculN dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitModeCalculN(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitModeCalculN();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitModeCalculN après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitModeCalculN(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitModeCalculN();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitModeCalculN après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitModeCalculN().___________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitModeCalculNRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitModeCalculNRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitModeCalculNRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitModeCalculNRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitModeCalculNRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitModeCalculNRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitModeCalculNRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitModeCalculNRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitModeCalculNRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitModeCalculNRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitModeCalculNRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitModeCalculNRenseigne01().________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitModeCalculNRegex02().<br/>
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
	public void testGetValiderRGSectionHitModeCalculNRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitModeCalculNRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitModeCalculNRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitModeCalculNRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitModeCalculNRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitModeCalculNRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitModeCalculNRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitModeCalculNRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitModeCalculNRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitModeCalculNRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitModeCalculNRegex02().____________

	
	
	/* 40 - pcPLN. **************/
	/**
	 * teste la méthode getValiderRGSectionHitPcPLN().<br/>
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
	public void testGetValiderRGSectionHitPcPLN() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPcPLN() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcPLN();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcPLN dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcPLN(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcPLN();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcPLN après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcPLN(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcPLN();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcPLN après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitPcPLN()._________________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPcPLNRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitPcPLNRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPcPLNRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcPLNRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcPLNRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcPLNRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcPLNRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcPLNRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcPLNRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcPLNRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcPLNRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPcPLNRenseigne01().______________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPcPLNRegex02().<br/>
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
	public void testGetValiderRGSectionHitPcPLNRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPcPLNRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcPLNRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcPLNRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcPLNRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcPLNRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcPLNRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcPLNRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcPLNRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcPLNRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPcPLNRegex02().__________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPcPLNNumerique03().<br/>
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
	public void testGetValiderRGSectionHitPcPLNNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPcPLNNumerique03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcPLNNumerique03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcPLNNumerique03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcPLNNumerique03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcPLNNumerique03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcPLNNumerique03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcPLNNumerique03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcPLNNumerique03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcPLNNumerique03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPcPLNNumerique03().______________

	
	
	/* 41 - evaluationPLN. **************/
	/**
	 * teste la méthode getValiderRGSectionHitEvaluationPLN().<br/>
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
	public void testGetValiderRGSectionHitEvaluationPLN() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitEvaluationPLN() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitEvaluationPLN();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitEvaluationPLN dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitEvaluationPLN(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitEvaluationPLN();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitEvaluationPLN après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitEvaluationPLN(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitEvaluationPLN();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitEvaluationPLN après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitEvaluationPLN().___________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitEvaluationPLNRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitEvaluationPLNRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitEvaluationPLNRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitEvaluationPLNRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitEvaluationPLNRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitEvaluationPLNRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitEvaluationPLNRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitEvaluationPLNRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitEvaluationPLNRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitEvaluationPLNRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitEvaluationPLNRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitEvaluationPLNRenseigne01().________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitEvaluationPLNRegex02().<br/>
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
	public void testGetValiderRGSectionHitEvaluationPLNRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitEvaluationPLNRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitEvaluationPLNRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitEvaluationPLNRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitEvaluationPLNRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitEvaluationPLNRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitEvaluationPLNRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitEvaluationPLNRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitEvaluationPLNRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitEvaluationPLNRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitEvaluationPLNRegex02().____________

	
	
	/* 42 - pcNuitAnnuelN. **************/
	/**
	 * teste la méthode getValiderRGSectionHitPcNuitAnnuelN().<br/>
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
	public void testGetValiderRGSectionHitPcNuitAnnuelN() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPcNuitAnnuelN() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitAnnuelN();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitAnnuelN dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitAnnuelN(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitAnnuelN();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitAnnuelN après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitAnnuelN(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcNuitAnnuelN();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitAnnuelN après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitPcNuitAnnuelN()._________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPcNuitAnnuelNRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitPcNuitAnnuelNRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPcNuitAnnuelNRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitAnnuelNRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitAnnuelNRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitAnnuelNRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitAnnuelNRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitAnnuelNRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitAnnuelNRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcNuitAnnuelNRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitAnnuelNRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPcNuitAnnuelNRenseigne01().______

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPcNuitAnnuelNRegex02().<br/>
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
	public void testGetValiderRGSectionHitPcNuitAnnuelNRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPcNuitAnnuelNRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitAnnuelNRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitAnnuelNRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitAnnuelNRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitAnnuelNRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitAnnuelNRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitAnnuelNRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcNuitAnnuelNRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitAnnuelNRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPcNuitAnnuelNRegex02().__________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPcNuitAnnuelNNumerique03().<br/>
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
	public void testGetValiderRGSectionHitPcNuitAnnuelNNumerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPcNuitAnnuelNNumerique03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitAnnuelNNumerique03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitAnnuelNNumerique03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitAnnuelNNumerique03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitAnnuelNNumerique03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitAnnuelNNumerique03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitAnnuelNNumerique03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcNuitAnnuelNNumerique03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitAnnuelNNumerique03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPcNuitAnnuelNNumerique03().______

	
	
	/* 43 - indiceFiabiliteMjaN. **************/
	/**
	 * teste la méthode getValiderRGSectionHitIndiceFiabiliteMjaN().<br/>
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
	public void testGetValiderRGSectionHitIndiceFiabiliteMjaN() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitIndiceFiabiliteMjaN() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceFiabiliteMjaN();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceFiabiliteMjaN dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceFiabiliteMjaN(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceFiabiliteMjaN();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceFiabiliteMjaN après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceFiabiliteMjaN(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitIndiceFiabiliteMjaN();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceFiabiliteMjaN après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitIndiceFiabiliteMjaN().___________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01().<br/>
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
	public void testGetValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceFiabiliteMjaNRenseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceFiabiliteMjaNRenseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceFiabiliteMjaNRenseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01().

	
	
	/**
	 * teste la méthode getValiderRGSectionHitIndiceFiabiliteMjaNRegex02().<br/>
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
	public void testGetValiderRGSectionHitIndiceFiabiliteMjaNRegex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitIndiceFiabiliteMjaNRegex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceFiabiliteMjaNRegex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceFiabiliteMjaNRegex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceFiabiliteMjaNRegex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceFiabiliteMjaNRegex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceFiabiliteMjaNRegex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitIndiceFiabiliteMjaNRegex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitIndiceFiabiliteMjaNRegex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitIndiceFiabiliteMjaNRegex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitIndiceFiabiliteMjaNRegex02().____

	
	
	/* 44 - mjmNmois01. **************/
	/**
	 * teste la méthode getValiderRGSectionHitMjmNmois01().<br/>
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
	public void testGetValiderRGSectionHitMjmNmois01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitMjmNmois01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjmNmois01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjmNmois01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjmNmois01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjmNmois01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitMjmNmois01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitMjmNmois01().____________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitMjmNmois01Renseigne01().<br/>
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
	public void testGetValiderRGSectionHitMjmNmois01Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitMjmNmois01Renseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjmNmois01Renseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois01Renseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjmNmois01Renseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjmNmois01Renseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois01Renseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjmNmois01Renseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitMjmNmois01Renseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois01Renseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitMjmNmois01Renseigne01()._________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitMjmNmois01Regex02().<br/>
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
	public void testGetValiderRGSectionHitMjmNmois01Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitMjmNmois01Regex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjmNmois01Regex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois01Regex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjmNmois01Regex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjmNmois01Regex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois01Regex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjmNmois01Regex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitMjmNmois01Regex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois01Regex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitMjmNmois01Regex02()._____________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitMjmNmois01Numerique03().<br/>
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
	public void testGetValiderRGSectionHitMjmNmois01Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitMjmNmois01Numerique03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjmNmois01Numerique03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois01Numerique03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjmNmois01Numerique03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjmNmois01Numerique03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois01Numerique03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjmNmois01Numerique03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitMjmNmois01Numerique03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois01Numerique03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitMjmNmois01Numerique03()._________

	
	
	/* 45 - pcNuitNmois01. **************/
	/**
	 * teste la méthode getValiderRGSectionHitPcNuitNmois01().<br/>
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
	public void testGetValiderRGSectionHitPcNuitNmois01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPcNuitNmois01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitNmois01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitNmois01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitNmois01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitNmois01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcNuitNmois01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitPcNuitNmois01()._________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPcNuitNmois01Renseigne01().<br/>
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
	public void testGetValiderRGSectionHitPcNuitNmois01Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPcNuitNmois01Renseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitNmois01Renseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois01Renseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitNmois01Renseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitNmois01Renseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois01Renseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitNmois01Renseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcNuitNmois01Renseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois01Renseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPcNuitNmois01Renseigne01().______

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPcNuitNmois01Regex02().<br/>
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
	public void testGetValiderRGSectionHitPcNuitNmois01Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPcNuitNmois01Regex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitNmois01Regex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois01Regex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitNmois01Regex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitNmois01Regex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois01Regex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitNmois01Regex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcNuitNmois01Regex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois01Regex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPcNuitNmois01Regex02().__________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPcNuitNmois01Numerique03().<br/>
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
	public void testGetValiderRGSectionHitPcNuitNmois01Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPcNuitNmois01Numerique03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitNmois01Numerique03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois01Numerique03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitNmois01Numerique03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitNmois01Numerique03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois01Numerique03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitNmois01Numerique03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcNuitNmois01Numerique03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois01Numerique03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPcNuitNmois01Numerique03().______

	
	
	/* 46 - mjmNmois02. **************/
	/**
	 * teste la méthode getValiderRGSectionHitMjmNmois02().<br/>
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
	public void testGetValiderRGSectionHitMjmNmois02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitMjmNmois02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjmNmois02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjmNmois02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjmNmois02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjmNmois02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitMjmNmois02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitMjmNmois02().____________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitMjmNmois02Renseigne01().<br/>
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
	public void testGetValiderRGSectionHitMjmNmois02Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitMjmNmois02Renseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjmNmois02Renseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois02Renseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjmNmois02Renseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjmNmois02Renseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois02Renseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjmNmois02Renseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitMjmNmois02Renseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois02Renseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitMjmNmois02Renseigne01()._________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitMjmNmois02Regex02().<br/>
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
	public void testGetValiderRGSectionHitMjmNmois02Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitMjmNmois02Regex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjmNmois02Regex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois02Regex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjmNmois02Regex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjmNmois02Regex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois02Regex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjmNmois02Regex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitMjmNmois02Regex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois02Regex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitMjmNmois02Regex02()._____________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitMjmNmois02Numerique03().<br/>
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
	public void testGetValiderRGSectionHitMjmNmois02Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitMjmNmois02Numerique03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjmNmois02Numerique03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois02Numerique03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjmNmois02Numerique03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjmNmois02Numerique03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois02Numerique03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitMjmNmois02Numerique03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitMjmNmois02Numerique03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitMjmNmois02Numerique03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitMjmNmois02Numerique03()._________

	
	
	/* 47 - pcNuitNmois02. **************/
	/**
	 * teste la méthode getValiderRGSectionHitPcNuitNmois02().<br/>
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
	public void testGetValiderRGSectionHitPcNuitNmois02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPcNuitNmois02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitNmois02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitNmois02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitNmois02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitNmois02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcNuitNmois02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);
		
	} // Fin de testGetValiderRGSectionHitPcNuitNmois02()._________________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPcNuitNmois02Renseigne01().<br/>
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
	public void testGetValiderRGSectionHitPcNuitNmois02Renseigne01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPcNuitNmois02Renseigne01() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitNmois02Renseigne01();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois02Renseigne01 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitNmois02Renseigne01(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitNmois02Renseigne01();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois02Renseigne01 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitNmois02Renseigne01(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcNuitNmois02Renseigne01();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois02Renseigne01 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPcNuitNmois02Renseigne01().______

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPcNuitNmois02Regex02().<br/>
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
	public void testGetValiderRGSectionHitPcNuitNmois02Regex02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPcNuitNmois02Regex02() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitNmois02Regex02();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois02Regex02 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitNmois02Regex02(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitNmois02Regex02();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois02Regex02 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitNmois02Regex02(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcNuitNmois02Regex02();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois02Regex02 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPcNuitNmois02Regex02().__________

	
	
	/**
	 * teste la méthode getValiderRGSectionHitPcNuitNmois02Numerique03().<br/>
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
	public void testGetValiderRGSectionHitPcNuitNmois02Numerique03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitGestionnairePreferencesRGTest - méthode testGetValiderRGSectionHitPcNuitNmois02Numerique03() ********** ");
		}
		
		/* récupère le Boolean dans le properties. */
		Boolean validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitNmois02Numerique03();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois02Numerique03 dans Properties : " + validerRGBoolean);
		}
		
		/* passe le Boolean à false. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitNmois02Numerique03(false);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitNmois02Numerique03();
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois02Numerique03 après passage à false : " + validerRGBoolean);
		}
		
		/* garantit que le Boolean a bien été passé a false dans le properties. */
		assertFalse(BOOLEAN_DOIT_VALOIR_FALSE, validerRGBoolean);
		
		/* passe le Boolean à true. */
		/* ***************************************** */
		SectionHitGestionnairePreferencesRG
			.setValiderRGSectionHitPcNuitNmois02Numerique03(true);
		/* ***************************************** */
		
		/* récupère le Boolean dans le properties. */
		validerRGBoolean 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcNuitNmois02Numerique03();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGSectionHitPcNuitNmois02Numerique03 après passage à true : " + validerRGBoolean);
		}
		
		assertTrue(BOOLEAN_DOIT_VALOIR_TRUE, validerRGBoolean);

	} // Fin de testGetValiderRGSectionHitPcNuitNmois02Numerique03().______
	

			
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
