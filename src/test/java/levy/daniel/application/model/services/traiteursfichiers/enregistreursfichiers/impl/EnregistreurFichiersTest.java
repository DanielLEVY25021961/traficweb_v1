package levy.daniel.application.model.services.traiteursfichiers.enregistreursfichiers.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.assertj.core.util.Files;
import org.junit.Test;

import levy.daniel.application.model.services.traiteursfichiers.enregistreursfichiers.IEnregistreurFichiers;

/**
 * CLASSE EnregistreurFichiersTest :<br/>
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
 * @since 24 sept. 2019
 *
 */
public class EnregistreurFichiersTest {

	// ************************ATTRIBUTS************************************/
	
	//*************************************************************/
	//*********************CHEMINS ********************************/
	//*************************************************************/
	
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
	 * "doit retourner null : ".
	 */
	public static final String DOIT_RETOURNER_NULL 
		= "doit retourner null : ";
	
	/**
	 * "ne doit pas retourner null : ".
	 */
	public static final String NE_DOIT_PAS_RETOURNER_NULL 
		= "ne doit pas retourner null : ";

	/**
	 * "doit retourner la bonne valeur : ".<br/>
	 */
	public static final String DOIT_RETOURNER_BONNE_VALEUR 
		= "doit retourner la bonne valeur : ";

	/**
	 * "Doit retourner la même instance : ".
	 */
	public static final String DOIT_RETOURNER_MEME_INSTANCE 
		= "Doit retourner la même instance : ";

	/**
	 * Paths.get(".").toAbsolutePath().normalize().<br/>
	 */
	public static final Path PATH_ABSOLU_PRESENT_PROJET 
		= Paths.get(".").toAbsolutePath().normalize();
	
	/**
	 * path <b>relatif</b> (par rapport au présent projet ECLIPSE) 
	 * des ressources des tests JUnit dans le présent projet ECLIPSE.<br/>
	 * Paths.get("src/test/resources")
	 */
	public static final Path SRC_TEST_RESOURCES_PATH_RELATIF 
		= Paths.get("src/test/resources");

	/**
	 * Path absolu vers les ressources de test.<br/>
	 */
	public static final Path PATH_ABSOLU_TEST_RESOURCES 
		= PATH_ABSOLU_PRESENT_PROJET
			.resolve(SRC_TEST_RESOURCES_PATH_RELATIF)
				.toAbsolutePath().normalize();
	
	 /**
	 * Path absolu vers les nomenclatures de test.<br/>
	 */
	public static final Path PATH_ABSOLU_TEST_NOMENCLATURES 
		= PATH_ABSOLU_TEST_RESOURCES
			.resolve("ressources/Nomenclatures")
				.toAbsolutePath().normalize();
	
	 /**
	 * Path absolu vers les jeux d'essai de test.<br/>
	 * src/test/resources/jeux_essai
	 */
	public static final Path PATH_ABSOLU_TEST_JEUX_ESSAI 
		= PATH_ABSOLU_TEST_RESOURCES
			.resolve("jeux_essai")
				.toAbsolutePath().normalize();
	
	/**
	 * Path absolu vers le répertoire 'temp' sous le présent projet.
	 */
	public static final Path PATH_ABSOLU_REPERTOIRE_TEMP 
		= PATH_ABSOLU_PRESENT_PROJET.resolve("temp");
	
	 /**
	 * Path absolu vers les differentstypesfichiers de test.<br/>
	 * src/test/resources/differentstypesfichiers
	 */
	public static final Path PATH_ABSOLU_TEST_DIFFERENTSTYPESFICHIERS 
		= PATH_ABSOLU_TEST_RESOURCES
			.resolve("differentstypesfichiers")
				.toAbsolutePath().normalize();
	
	/**
	 * Chemin des fichiers de test dans differentstypesfichiers
	 * relativement à la racine du projet courant.<br/>
	 * ".src/test/resources/differentstypesfichiers".<br/>
	 */
	public static final String CHEMIN_DIFFERENTSTYPESFICHIERS 
		= PATH_ABSOLU_TEST_DIFFERENTSTYPESFICHIERS.toString();

	/**
	 * Chemin d'un fichier inexistant.<br/>
	 * "/inexistant.txt".<br/>
	 */
	public static final String CHEMIN_INEXISTANT 
		= "/inexistant.txt";
		
	/**
	 * Chemin d'un répertoire (pas un simple fichier).<br/>
	 * "/dessin"
	 */
	public static final String CHEMIN_REPERTOIRE 
		= "/dessin";
	

	//*****************************************************************/
	//**************************FICHIERS ******************************/
	//*****************************************************************/
	
	/**
	 * Fichier null.<br/>
	 */
	public static final File FILE_NULL = null;
	
	/**
	 * Fichier inexistant.<br/>
	 */
	public static final File FILE_INEXISTANT 
		= new File(CHEMIN_DIFFERENTSTYPESFICHIERS + CHEMIN_INEXISTANT);
	
	/**
	 * Répertoire.<br/>
	 */
	public static final File FILE_REPERTOIRE 
		= new File(CHEMIN_DIFFERENTSTYPESFICHIERS + CHEMIN_REPERTOIRE);
	
	 /**
	 * Path absolu vers un fichier de test à détruire après les tests.<br/>
	 * src/test/resources/differentstypesfichiers/fileadetruire.txt
	 */
	public static final Path PATH_ABSOLU_TEST_FICHIERADETRUIRE 
		= PATH_ABSOLU_TEST_DIFFERENTSTYPESFICHIERS
			.resolve("fileadetruire.txt")
				.toAbsolutePath().normalize();


	//*****************************************************************/
	//**************************CHARSETS ******************************/
	//*****************************************************************/

	/**
	 * Charset.forName("UTF-8").<br/>
	 * Eight-bit Unicode (or UCS) Transformation Format.<br/> 
	 */
	public static final Charset CHARSET_UTF8 
		= Charset.forName("UTF-8");
	
	/**
	 * Charset.forName("US-ASCII").<br/>
	 * Seven-bit ASCII, a.k.a. ISO646-US, 
	 * a.k.a. the Basic Latin block of the Unicode character set.<br/>
	 * standard américain.<br/>
	 * American Standard Code for Information Interchange.<br/> 
	 * 128 caractères imprimables.<br/> 
	 * Sert à écrire l’anglo-américain.<br/> 
	 * Ne permet pas d’écrire les langues européennes 
	 * qui utilisent des lettres avec diacritiques (accents, cédille, ...).<br/> 
	 * On ne peut pas écrire en français avec de l’ASCII.<br/>
	 */
	public static final Charset CHARSET_US_ASCII 
		= Charset.forName("US-ASCII");
	
	/**
	 * Charset.forName("ISO-8859-1").<br/>
	 * Latin1.<br/>
	 * 191 caractères imprimables.<br/> 
	 * Permet d’écrire la plupart des langues d’Europe de l’Ouest.<br/> 
	 * Presque tous les caractères du français y sont (manquent le œ et €).<br/>
	 */
	public static final Charset CHARSET_ISO_8859_1 
		= Charset.forName("ISO-8859-1");
	
	/**
	 * Charset.forName("ISO-8859-1").<br/>
	 * Latin1.<br/>
	 * 191 caractères imprimables.<br/> 
	 * Permet d’écrire la plupart des langues d’Europe de l’Ouest.<br/> 
	 * Presque tous les caractères du français y sont (manquent le œ et €).<br/>
	 */
	public static final Charset CHARSET_LATIN1 
		= Charset.forName("ISO-8859-1");
	
	/**
	 * Charset.forName("ISO-8859-2").<br/>
	 * <br/>
	 */
	public static final Charset CHARSET_ISO_8859_2 
		= Charset.forName("ISO-8859-2");
		
	/**
	 * Charset.forName("ISO-8859-9").<br/>
	 * Latin Alphabet No. 5<br/>
	 */
	public static final Charset CHARSET_ISO_8859_9 
		= Charset.forName("ISO-8859-9");
	
	/**
	 * Charset.forName("ISO-8859-15").<br/>
	 * Latin9, Latin Alphabet No. 9.<br/>
	 * modifie légèrement ISO-8859-1.<br/> 
	 * Ajout du caractère œ et du symbole monétaire € (Euro) entre autres.<br/>
	 */
	public static final Charset CHARSET_ISO_8859_15 
		= Charset.forName("ISO-8859-15");
		
	/**
	 * Charset.forName("ISO-8859-15").<br/>
	 * Latin9, Latin Alphabet No. 9.<br/>
	 * modifie légèrement ISO-8859-1.<br/> 
	 * Ajout du caractère œ et du symbole monétaire € (Euro) entre autres.<br/>
	 */
	public static final Charset CHARSET_LATIN9 
		= Charset.forName("ISO-8859-15");
		
	/**
	 * Charset.forName("windows-1252").<br/>
	 * ANSI, CP1252.<br/>
	 * 218 caractères imprimables.<br/>
	 * extension d’ISO-8859-1, qui rajoute quelques caractères: œ, € (euro), 
	 * guillemets anglais (« »), points de suspension (...)
	 * , signe «pour mille» (‰), 
	 * tirets cadratin (— = \u2014 en unicode ) et demi-cadratin (–), ...<br/>
	 */
	public static final Charset CHARSET_WINDOWS_1252 
		= Charset.forName("windows-1252");
	
	/**
	 * Charset.forName("windows-1252").<br/>
	 * ANSI, CP1252.<br/>
	 * 218 caractères imprimables.<br/>
	 * extension d’ISO-8859-1, qui rajoute quelques caractères: œ, € (euro), 
	 * guillemets anglais (« »), points de suspension (...)
	 * , signe «pour mille» (‰), 
	 * tirets cadratin (— = \u2014 en unicode ) et demi-cadratin (–), ...<br/>
	 */
	public static final Charset CHARSET_ANSI
		= Charset.forName("windows-1252");
	
	/**
	 * Charset.forName("windows-1252").<br/>
	 * ANSI, CP1252.<br/>
	 * 218 caractères imprimables.<br/>
	 * extension d’ISO-8859-1, qui rajoute quelques caractères: œ, € (euro), 
	 * guillemets anglais (« »), points de suspension (...)
	 * , signe «pour mille» (‰), 
	 * tirets cadratin (— = \u2014 en unicode ) et demi-cadratin (–), ...<br/>
	 */
	public static final Charset CHARSET_CP1252
		= Charset.forName("windows-1252");
	
	/**
	 * Charset IBM-850.<br/>
	 * Cp850, MS-DOS Latin-1.<br/>
	 */
	public static final Charset CHARSET_IBM850
		= Charset.forName("IBM-850");
	

	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/

	/**
	 * SAUTDELIGNE_UNIX : String :<br/>
	 * Saut de ligne généré par les éditeurs Unix.<br/>
	 * "\n" (Retour Ligne = LINE FEED (LF)).
	 */
	public static final String SAUTDELIGNE_UNIX = "\n";
	
	/**
	 * SAUTDELIGNE_MAC : String :<br/>
	 * Saut de ligne généré par les éditeurs Mac.<br/>
	 * "\r" (Retour Chariot RC = CARRIAGE RETURN (CR))
	 */
	public static final String SAUTDELIGNE_MAC = "\r";
	
	/**
	 * SAUTDELIGNE_DOS_WINDOWS : String :<br/>
	 * Saut de ligne généré par les éditeurs DOS/Windows.<br/>
	 * "\r\n" (Retour Chariot RC + Retour Ligne Line Feed LF).
	 */
	public static final String SAUTDELIGNE_DOS_WINDOWS = "\r\n";
	
	/**
	 * NEWLINE : String :<br/>
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(EnregistreurFichiersTest.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public EnregistreurFichiersTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * Teste la méthode ecrireStringDansFile(.....).<br/>
	 * - vérifie que ecrireStringDansFile(null, ...) 
	 * retourne null, LOG de niveau INFO 
	 * et rapport si pFile est null.<br/>
	 * <br/>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testEcrireStringDansFileNull() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE EnregistreurFichiersTest - méthode testEcrireStringDansFileNull() ********** ");
		}
		
		/* Instanciation d'un EnregistreurFichiers. */
		final IEnregistreurFichiers enregistreur 
			= new EnregistreurFichiers(
					new Date(), "Administrateur", "Test", null);
		
		final File resultat 
		= enregistreur.ecrireStringDansFile(
				null, "toto", CHARSET_UTF8, NEWLINE);
		
		/* vérifie que ecrireStringDansFile(null, ...) a retourné null. */
		assertNull("doit retourner null : "
					, resultat);
		
		/* vérifie que ecrireStringDansFile(null, ...) 
		 * a retourné un rapport non null. */
		assertNotNull("le rapport ne doit pas être null : "
					, enregistreur.getRapport());
		
		/* vérifie que ecrireStringDansFile(null, ...) 
		 * a retourné un rapport non vide. */
		assertFalse("le rapport ne doit pas être vide : "
				, enregistreur.getRapport().isEmpty());
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(enregistreur.afficherRapportCsvAvecEntete());
		}
				
	} // Fin de testEcrireStringDansFileNull().____________________________
	
	
	
	/**
	 * method testEcrireStringDansFileInexistant() :<br/>
	 * Teste la méthode ecrireStringDansFile(.....).<br/>
	 * - vérifie que ecrireStringDansFile(inexistant, ...) 
	 * retourne null, LOG de niveau INFO 
	 * et rapport si pFile est inexistant.<br/>
	 * <br/>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testEcrireStringDansFileInexistant() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE EnregistreurFichiersTest - méthode testEcrireStringDansFileInexistant() ********** ");
		}
		
		/* Instanciation d'un EnregistreurFichiers. */
		final IEnregistreurFichiers enregistreur 
			= new EnregistreurFichiers(
					new Date(), "Administrateur", "Test", null);
		
		final File resultat 
		= enregistreur.ecrireStringDansFile(
				FILE_INEXISTANT, "toto", CHARSET_UTF8, NEWLINE);
		
		/* vérifie que ecrireStringDansFile(inexistant, ...) a retourné null. */
		assertNull("doit retourner null : "
					, resultat);
		
		/* vérifie que ecrireStringDansFile(inexistant, ...) 
		 * a retourné un rapport non null. */
		assertNotNull("le rapport ne doit pas être null : "
					, enregistreur.getRapport());
		
		/* vérifie que ecrireStringDansFile(inexistant, ...) 
		 * a retourné un rapport non vide. */
		assertFalse("le rapport ne doit pas être vide : "
				, enregistreur.getRapport().isEmpty());
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(enregistreur.afficherRapportCsvAvecEntete());
		}
		
	} // Fin de testEcrireStringDansFileInexistant().______________________
	

	
	/**
	 * method testEcrireStringDansFileRepertoire() :<br/>
	 * Teste la méthode ecrireStringDansFile(.....).<br/>
	 * - vérifie que ecrireStringDansFile(repertoire, ...) 
	 * retourne null, LOG de niveau INFO 
	 * et rapport si pFile est un répertoire.<br/>
	 * <br/>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testEcrireStringDansFileRepertoire() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE EnregistreurFichiersTest - méthode testEcrireStringDansFileRepertoire() ********** ");
		}
		
		/* Instanciation d'un EnregistreurFichiers. */
		final IEnregistreurFichiers enregistreur 
			= new EnregistreurFichiers(
					new Date(), "Administrateur", "Test", null);
		
		final File resultat 
		= enregistreur.ecrireStringDansFile(
				FILE_REPERTOIRE, "toto", CHARSET_UTF8, NEWLINE);
		
		/* vérifie que ecrireStringDansFile(repertoire, ...) a retourné null. */
		assertNull("doit retourner null : "
					, resultat);
		
		/* vérifie que ecrireStringDansFile(repertoire, ...) 
		 * a retourné un rapport non null. */
		assertNotNull("le rapport ne doit pas être null : "
					, enregistreur.getRapport());
		
		/* vérifie que ecrireStringDansFile(repertoire, ...) 
		 * a retourné un rapport non vide. */
		assertFalse("le rapport ne doit pas être vide : "
				, enregistreur.getRapport().isEmpty());
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(enregistreur.afficherRapportCsvAvecEntete());
		}
		
	} // Fin de testEcrireStringDansFileRepertoire().______________________
	

	
	/**
	 * method testEcrireStringDansFileStringBlank() :<br/>
	 * Teste la méthode ecrireStringDansFile(.....).<br/>
	 * - vérifie que ecrireStringDansFile(fichier, String blank, ...) 
	 * retourne null, LOG de niveau INFO 
	 * et rapport si pFile est un répertoire.<br/>
	 * <br/>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testEcrireStringDansFileStringBlank() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE EnregistreurFichiersTest - méthode testEcrireStringDansFileStringBlank() ********** ");
		}
		
		/* Instanciation d'un fichier. */
		final File fileADetruire 
			= PATH_ABSOLU_TEST_FICHIERADETRUIRE.toFile();
		
		/* Création du fichier. */
		try {
			fileADetruire.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/* Instanciation d'une String blank à écrire dans le fichier. */
		final String aEcrire = "   ";
		
		/* Instanciation d'un EnregistreurFichiers. */
		final IEnregistreurFichiers enregistreur 
			= new EnregistreurFichiers(
					new Date(), "Administrateur", "Test", null);
		
		final File resultat 
		= enregistreur.ecrireStringDansFile(
				fileADetruire, aEcrire, CHARSET_UTF8, NEWLINE);
		
		/* vérifie que ecrireStringDansFile(fichier, String blank, ...) 
		 * a retourné null. */
		assertNull("doit retourner null : "
					, resultat);
		
		/* vérifie que ecrireStringDansFile(fichier, String blank, ...) 
		 * a retourné un rapport non null. */
		assertNotNull("le rapport ne doit pas être null : "
					, enregistreur.getRapport());
		
		/* vérifie que ecrireStringDansFile(fichier, String blank, ...) 
		 * a retourné un rapport non vide. */
		assertFalse("le rapport ne doit pas être vide : "
				, enregistreur.getRapport().isEmpty());
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(enregistreur.afficherRapportCsvAvecEntete());
		}
		
		if (fileADetruire.exists()) {
			Files.delete(fileADetruire);
		}
		
	} // Fin de testEcrireStringDansFileStringBlank()._____________________
	
	
	
	/**
	 * method testEcrireStringDansFile() :<br/>
	 * Teste la méthode ecrireStringDansFile(.....).<br/>
	 * - vérifie que ecrireStringDansFile(fichier, String, ...) 
	 * enregistre convenablement la String dans le fichier, LOG de niveau INFO 
	 * et rapport si pFile est un fichier valide.<br/>
	 * <br/>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testEcrireStringDansFile() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE EnregistreurFichiersTest - méthode testEcrireStringDansFile() ********** ");
		}
		
		/* Instanciation d'un fichier. */
		final File fileADetruire 
			= PATH_ABSOLU_TEST_FICHIERADETRUIRE.toFile();
		
		/* Création du fichier vide. */
		try {
			fileADetruire.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/* Instanciation d'une String à écrire dans le fichier. */
//		final String aEcrire 
//			= "bien joué cher être, c'est incroyable ce manque d'idées" 
//					+ "\n" 
//					+ "n'est-il pas bêêële";
		
		final String aEcrire 
		= "bien joué cher être, c'est incroyable ce manque d'idées";
		
		/* Instanciation d'un EnregistreurFichiers. */
		final IEnregistreurFichiers enregistreur 
			= new EnregistreurFichiers(
					new Date(), "Administrateur", "Test", null);
		
		/* Récupération du fichier écrit sur disque. */
		final File resultat 
		= enregistreur.ecrireStringDansFile(
				fileADetruire, aEcrire, CHARSET_UTF8, NEWLINE);
		
		assertNotNull("Le fichier enregistré ne doit pas être null : "
				, resultat);
		
		/* vérifie que ecrireStringDansFile(fichier, String, ...) 
		 * a retourné un rapport non null. */
		assertNotNull("le rapport ne doit pas être null : "
					, enregistreur.getRapport());
		
		/* vérifie que ecrireStringDansFile(fichier, String, ...) 
		 * a retourné un rapport non vide. */
		assertFalse("le rapport ne doit pas être vide : "
				, enregistreur.getRapport().isEmpty());
						
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(enregistreur.afficherRapportCsvAvecEntete());
		}

//		final String stringLueUTF8 
//			=  enregistreur.lireFichierCaractereParCaractere(fileADetruire, CHARSET_UTF8);
//		final String stringLueANSI 
//			=  enregistreur.lireFichierCaractereParCaractere(fileADetruire, CHARSET_ANSI);
		final String stringLueUTF8 
			=  enregistreur.lireFichierLigneParLigne(fileADetruire, CHARSET_UTF8);
		final String stringLueANSI 
			=  enregistreur.lireFichierLigneParLigne(fileADetruire, CHARSET_ANSI);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("stringLueUTF8 : " + stringLueUTF8);
			System.out.println("stringLueANSI : " + stringLueANSI);
		}

		assertEquals("la String lue en UTF-8 doit valoir la String enregistrée en UTF-8 : "
				, stringLueUTF8, aEcrire);
		assertNotEquals("la String lue en ANSI ne doit PAS valoir la String enregistrée en UTF-8 : "
				, stringLueANSI, aEcrire);
				
		if (fileADetruire.exists()) {
			Files.delete(fileADetruire);
		}
		
	} // Fin de testEcrireStringDansFile().________________________________

	
	
} // FIN DE LA CLASSE EnregistreurFichiersTest.------------------------------
