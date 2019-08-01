package levy.daniel.application.model.metier.televersementdelotsections.impl;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;

import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.televersement.EnumTypeFichierDonnees;
import levy.daniel.application.model.metier.utilisateur.EnumGestionnaire;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.metier.utilisateur.impl.UtilisateurCerbere;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl.ImporteurHit;
import levy.daniel.application.model.services.valideurs.metier.utilisateurs.EnumCivilites;

/**
 * CLASSE TeleversementDeLotSectionsHitTest :<br/>
 * Test JUnit de la classe {@link TeleversementDeLotSectionsHit}.<br/>
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
 * @since 1 août 2019
 *
 */
public class TeleversementDeLotSectionsHitTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * LocalDateTime.of(2019, 6, 13, 8, 37, 43).<br/>
	 */
	public static final LocalDateTime DATE_TELEVERSEMENT_2019_1 
		= LocalDateTime.of(2019, 6, 13, 8, 37, 43);

	/**
	 * LocalDateTime.of(2018, 6, 13, 8, 37, 43).<br/>
	 */
	public static final LocalDateTime DATE_TELEVERSEMENT_2018_2 
		= LocalDateTime.of(2018, 6, 13, 8, 37, 43);
	
	/**
	 * LocalDateTime.of(2018, 1, 13, 8, 37, 43).<br/>
	 */
	public static final LocalDateTime DATE_TELEVERSEMENT_2018_3 
		= LocalDateTime.of(2018, 1, 13, 8, 37, 43);
	
	/**
	 * "prenomTest".<br/>
	 */
	public static final String PRENOMTEST = "prenomTest";
	
	/**
	 * "nomTest".<br/>
	 */
	public static final String NOMTEST = "nomTest";
	
	/**
	 * "test.test@yahoo.fr".<br/>
	 */
	public static final String MAILTEST = "test.test@yahoo.fr";
	
	/**
	 * "CEREMA".<br/>
	 */
	public static final String SERVICETEST = "CEREMA";
	
	/**
	 * "DIRA".<br/>
	 */
	public static final String SERVICE_DIRA = "DIRA";	
	
	/**
	 * "DIRCE".<br/>
	 */
	public static final String SERVICE_DIRCE = "DIRCE";	
	
	/**
	 * "CEREMA/DTecITM/CITS/DACSI".<br/>
	 */
	public static final String UNITETEST = "CEREMA/DTecITM/CITS/DACSI";
	
	/**
	 * "DIRA/SIEER/CIGT".<br/>
	 */
	public static final String UNITE_DIRA_SIEER_CIGT = "DIRA/SIEER/CIGT";
	
	/**
	 * "ADMINISTRATEUR".<br/>
	 */
	public static final String PROFIL_ADMINISTRATEUR = "ADMINISTRATEUR";
	
	/**
	 * "GESTIONNAIRE".<br/>
	 */
	public static final String PROFIL_GESTIONNAIRE = "GESTIONNAIRE";

	/**
	 * ADMINISTRATEUR.<br/>
	 */
	public static final IUtilisateurCerbere UTILISATEUR_ADMIN_1 
		= new UtilisateurCerbere(
				EnumCivilites.MONSIEUR.getAbreviation()
				, "papy", "Gonzales"
				, "01 68 97 36 12", "papy.gonzales@free.fr"
				, SERVICETEST, UNITETEST
				, PROFIL_ADMINISTRATEUR, "France entière", null);
	
	/**
	 * DIRA_1.<br/>
	 */
	public static final transient IUtilisateurCerbere UTILISATEUR_DIRA_1 
	= new UtilisateurCerbere(3L
			, EnumCivilites.MADEMOISELLE.getAbreviation()
			, PRENOMTEST, NOMTEST
			, "05 25 89 87 45"
			, MAILTEST
			, SERVICE_DIRA, UNITE_DIRA_SIEER_CIGT
			, PROFIL_GESTIONNAIRE
			, "DIRA"
			, "44");
	
	/**
	 * DIRA_2.<br/>
	 */
	public static final transient IUtilisateurCerbere UTILISATEUR_DIRA_2 
	= new UtilisateurCerbere(3L
			, EnumCivilites.MONSIEUR.getAbreviation()
			, "Sylvain", "Dupont"
			, "05 25 89 87 65"
			, "sylvain.dupont@dira.fr"
			, SERVICE_DIRA, UNITE_DIRA_SIEER_CIGT
			, PROFIL_GESTIONNAIRE
			, "DIRA"
			, "44");
	
	/**
	 * DIRCE_1.<br/>
	 */
	public static final transient IUtilisateurCerbere UTILISATEUR_DIRCE_1 
	= new UtilisateurCerbere(3L
			, EnumCivilites.MONSIEUR.getAbreviation()
			, "Jean", "Lavoire"
			, "03 37 90 03 07"
			, "jean.lavoiret@dirce.fr"
			, SERVICE_DIRCE, "DIRCE/SIEER/CIGT"
			, PROFIL_GESTIONNAIRE
			, "DIRCE"
			, "69");
	
	/**
	 * EnumGestionnaire.DIRA.<br/>
	 */
	public static final EnumGestionnaire GESTIONNAIRE_DIRA 
		= EnumGestionnaire.DIRA;
	
	/**
	 * GESTIONNAIRE_DIRCE.<br/>
	 */
	public static final EnumGestionnaire GESTIONNAIRE_DIRCE 
		= EnumGestionnaire.DIRCE;
	
	/**
	 * EnumTypeFichierDonnees.HIT.<br/>
	 */
	public static final EnumTypeFichierDonnees TYPE_FICHIER_DONNEES_HIT 
		= EnumTypeFichierDonnees.HIT;

	/**
	 * "HITDIRA2017.txt".
	 */
	public static final String NOM_FICHIER_TELEVERSE_DIRA_1 = "HITDIRA2017.txt";
	
	/**
	 * "PROFIL_TEST_H2_FILE".
	 */
	public static final String PROFIL_TEST_H2_FILE = "PROFIL_TEST_H2_FILE";
	
	/**
	 * lotSections_DIRA_1.
	 */
	public static Map<Integer, ISectionHit> lotSections_DIRA_1;
	
	/**
	 * "PROFIL_TEST_H2_MEMORY".
	 */
	public static final String PROFIL_TEST_H2_MEMORY = "PROFIL_TEST_H2_MEMORY";

	/**
	 * "PROFIL_PROD_POSTGRES_SERVER".
	 */
	public static final String PROFIL_PROD_POSTGRES_SERVER 
		= "PROFIL_PROD_POSTGRES_SERVER";
	
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
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(TeleversementDeLotSectionsHitTest.class);

	
	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public TeleversementDeLotSectionsHitTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * Exécuté avant tout test de la méthode.<br/>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		
		System.setProperty("%log4j.skipJansi", "false");
		
		final Path fichierDonneesPathDIRA1 
			= PATH_ABSOLU_TEST_JEUX_ESSAI.resolve(NOM_FICHIER_TELEVERSE_DIRA_1);
		final File fichierDonneesDIRA1 = fichierDonneesPathDIRA1.toFile();
		final Charset charsetAnsi = Charset.forName("Windows-1252");
		
		final ImporteurHit importeurHIT = new ImporteurHit();
		
		lotSections_DIRA_1 
			= importeurHIT.importerObjet(fichierDonneesDIRA1, charsetAnsi);
		
		
		
				
	} // Fin de beforeClass()._____________________________________________

	
} // FIN DE LA CLASSE TeleversementDeLotSectionsHitTest.---------------------
