package levy.daniel.application.model.dto.metier.televersementdelotsections;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.model.dto.metier.anneegestion.IAnneeGestionDTO;
import levy.daniel.application.model.dto.metier.anneegestion.impl.AnneeGestionDTO;
import levy.daniel.application.model.dto.metier.sections.ISectionHitDTO;
import levy.daniel.application.model.dto.metier.televersement.ITeleversementDTO;
import levy.daniel.application.model.dto.metier.televersement.impl.TeleversementDTO;
import levy.daniel.application.model.dto.metier.televersementdelotsections.impl.TeleversementDeLotSectionsHitDTO;
import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.dto.metier.utilisateur.impl.UtilisateurCerbereDTO;
import levy.daniel.application.model.metier.anneegestion.IAnneeGestion;
import levy.daniel.application.model.metier.anneegestion.impl.AnneeGestion;
import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.televersement.EnumTypeFichierDonnees;
import levy.daniel.application.model.metier.televersement.ITeleversement;
import levy.daniel.application.model.metier.televersement.impl.Televersement;
import levy.daniel.application.model.metier.televersementdelotsections.ITeleversementDeLotSectionsHit;
import levy.daniel.application.model.metier.televersementdelotsections.impl.TeleversementDeLotSectionsHit;
import levy.daniel.application.model.metier.utilisateur.EnumGestionnaire;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.metier.utilisateur.impl.UtilisateurCerbere;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl.ImporteurHit;
import levy.daniel.application.model.services.valideurs.metier.utilisateurs.EnumCivilites;

/**
 * CLASSE TeleversementDeLotSectionsHitConvertisseurMetierDTOTest :<br/>
 * Test JUnit de la classe {@link TeleversementDeLotSectionsHitConvertisseurMetierDTO}.<br/>
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
 * @since 2 août 2019
 *
 */
public class TeleversementDeLotSectionsHitConvertisseurMetierDTOTest {

	// ************************ATTRIBUTS************************************/
	
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
	 * "doit retourner invalide : ".
	 */
	public static final String DOIT_RETOURNER_INVALIDE 
		= "doit retourner invalide : ";
	
	/**
	 * Path absolu du présent projet Eclipse.<br/>
	 * <code>Paths.get(".").toAbsolutePath().normalize()</code>.<br/>
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
	 * DTO a tester.
	 */
	public static  ITeleversementDeLotSectionsHitDTO dto;
	
	/**
	 * 2018-06-13_08_37_43
	 */
	public static final String DATE_TELEVERSEMENT_2018_2_STRING 
		= "2018-06-13_08_37_43";

	/**
	 * LocalDateTime.of(2018, 6, 13, 8, 37, 43).<br/>
	 */
	public static final LocalDateTime DATE_TELEVERSEMENT_2018_2 
		= LocalDateTime.of(2018, 6, 13, 8, 37, 43);

	/**
	 * OBJET METIER a tester.
	 */
	public static ITeleversementDeLotSectionsHit objetMetier;

	
	/**
	 * "DIRA".<br/>
	 */
	public static final String SERVICE_DIRA = "DIRA";	
	
	/**
	 * "DIRA/SIEER/CIGT".<br/>
	 */
	public static final String UNITE_DIRA_SIEER_CIGT = "DIRA/SIEER/CIGT";
	
	/**
	 * "GESTIONNAIRE".<br/>
	 */
	public static final String PROFIL_GESTIONNAIRE = "GESTIONNAIRE";

	/**
	 * DIRA_1_DTO.<br/>.
	 */
	public static final transient IUtilisateurCerbereDTO UTILISATEUR_DIRA_1_DTO 
		= new UtilisateurCerbereDTO(
				EnumCivilites.MADEMOISELLE.getAbreviation()
				, "Jeanne", "Paoli"
				, "05 25 89 87 45"
				, "jeanne.paoli@free.fr"
				, SERVICE_DIRA, UNITE_DIRA_SIEER_CIGT
				, PROFIL_GESTIONNAIRE
				, "DIRA"
				, "44");
	
	/**
	 * DIRA_1.<br/>
	 */
	public static final transient IUtilisateurCerbere UTILISATEUR_DIRA_1 
	= new UtilisateurCerbere(3L
			, EnumCivilites.MADEMOISELLE.getAbreviation()
			, "Jeanne", "Paoli"
			, "05 25 89 87 45"
			, "jeanne.paoli@free.fr"
			, SERVICE_DIRA, UNITE_DIRA_SIEER_CIGT
			, PROFIL_GESTIONNAIRE
			, "DIRA"
			, "44");
	
	/**
	 * lotSectionsDIRA1.<br/>
	 * NOM_FICHIER_TELEVERSE_DIRA_1<br/>
	 * "HITDIRA2017.txt"<br/>
	 */
	public static Map<Integer, ISectionHit> lotSectionsDIRA1;
	
	/**
	 * lotSectionsDIRA1DTO.<br/>
	 * NOM_FICHIER_TELEVERSE_DIRA_1<br/>
	 * "HITDIRA2017.txt"<br/>
	 */
	public static Map<Integer, ISectionHitDTO> lotSectionsDIRA1DTO;	

	/**
	 * EnumGestionnaire.DIRA.getNomCourt().
	 */
	public static final String GESTIONNAIRE_DIRA_STRING 
		= EnumGestionnaire.DIRA.getNomCourt();

	/**
	 * EnumGestionnaire.DIRA.
	 */
	public static final EnumGestionnaire GESTIONNAIRE_DIRA 
		= EnumGestionnaire.DIRA;

	/**
	 * EnumTypeFichierDonnees.HIT.getNomCourt().
	 */
	public static final String TYPE_FICHIER_DONNEES_HIT_STRING 
		= EnumTypeFichierDonnees.HIT.getNomCourt();

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
	 * "HITDIRA2017.txt".<br/>
	 * NOM_FICHIER_TELEVERSE_DIRA_1
	 */
	public static final File FICHIER_STOCKE_SERVEUR_DIRA_1 
		= new File(NOM_FICHIER_TELEVERSE_DIRA_1);

	/**
	 * "2017".
	 */
	public static final IAnneeGestionDTO ANNEE_GESTION_2017_DTO 
		= new AnneeGestionDTO("2017");
	
	/**
	 * new AnneeGestion("2017").
	 */
	public static final IAnneeGestion ANNEE_GESTION_2017 
		= new AnneeGestion("2017");
	
	/**
	 * Téléversement de la DIRA en 2017.
	 */
	public static ITeleversement televersementDIRA1;

	
	/**
	 * Téléversement DTO de la DIRA en 2017.
	 */
	public static ITeleversementDTO televersementDIRA1DTO;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(TeleversementDeLotSectionsHitConvertisseurMetierDTOTest.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public TeleversementDeLotSectionsHitConvertisseurMetierDTOTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * teste la méthode convertirDTOEnObjetMetier(DTO).<br/>
	 * <ul>
	 * <li>garantit le bon fonctionnement de la méthode.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConvertirDTOEnObjetMetier() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE TeleversementDeLotSectionsHitConvertisseurMetierDTOTest - méthode testConvertirDTOEnObjetMetier() ********** ");
		}
		
		
		// CONVERSION
		final ITeleversementDeLotSectionsHit objet 
			= TeleversementDeLotSectionsHitConvertisseurMetierDTO
				.convertirDTOEnObjetMetier(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(dto.toString());
//			System.out.println(dto.afficherLotSections());
			System.out.println();
			System.out.println(objet.toString());
//			System.out.println(objet.afficherLotSections());
			
			System.out.println();
			System.out.println("objet.getLotSections()");
			System.out.println(objet.afficherLotSections());
			
			System.out.println();
			System.out.println("objetMetier.getLotSections()");
			System.out.println(objetMetier.afficherLotSections());
			
			System.out.println("objetMetier.getLotSections().equals(objet.getLotSections()) : " + objetMetier.getLotSections().equals(objet.getLotSections()));
		}
	
				
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, objetMetier
					, objet);
	
	} // Fin de testConvertirDTOEnObjetMetier().___________________________
	

	
	/**
	 * teste la méthode testConvertirObjetMetierEnDTO(ObjetMetier).<br/>
	 * <ul>
	 * <li>garantit le bon fonctionnement de la méthode.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConvertirObjetMetierEnDTO() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE TeleversementDeLotSectionsHitConvertisseurMetierDTOTest - méthode testConvertirObjetMetierEnDTO() ********** ");
		}
				
		
		// CONVERSION
		final ITeleversementDeLotSectionsHitDTO dtoLocal 
			= TeleversementDeLotSectionsHitConvertisseurMetierDTO
				.convertirObjetMetierEnDTO(objetMetier);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(objetMetier.toString());
			System.out.println(dtoLocal.toString());
		}
		
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, dto
					, dtoLocal);
	
	} // Fin de testConvertirObjetMetierEnDTO().___________________________
	
	
	
	/**
	 * Exécuté avant tout test de la méthode.<br/>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		
		final Path fichierDonneesPath 
			= PATH_ABSOLU_TEST_JEUX_ESSAI.resolve("HITDIRA2017.txt");
		final File fichierDonnees = fichierDonneesPath.toFile();
		final Charset charsetAnsi = Charset.forName("Windows-1252");
		
		final ImporteurHit importeurHIT = new ImporteurHit();
		
		lotSectionsDIRA1
			= importeurHIT.importerObjet(fichierDonnees, charsetAnsi);
		
		lotSectionsDIRA1DTO 
			= importeurHIT.importerDTO(fichierDonnees, charsetAnsi);
		
		televersementDIRA1 = new Televersement(
				DATE_TELEVERSEMENT_2018_2
				, UTILISATEUR_DIRA_1
				, GESTIONNAIRE_DIRA
				, TYPE_FICHIER_DONNEES_HIT
				, NOM_FICHIER_TELEVERSE_DIRA_1
				, FICHIER_STOCKE_SERVEUR_DIRA_1
				, ANNEE_GESTION_2017);

		// OBJET A TESTER.
		objetMetier 
			= new TeleversementDeLotSectionsHit(
					televersementDIRA1, lotSectionsDIRA1);
		
		televersementDIRA1DTO 
			= new TeleversementDTO(
					DATE_TELEVERSEMENT_2018_2_STRING
					, UTILISATEUR_DIRA_1_DTO
					, GESTIONNAIRE_DIRA_STRING
					, TYPE_FICHIER_DONNEES_HIT_STRING
					, NOM_FICHIER_TELEVERSE_DIRA_1
					, NOM_FICHIER_TELEVERSE_DIRA_1
					, ANNEE_GESTION_2017_DTO);
		
		// DTO A TESTER.
		dto = new TeleversementDeLotSectionsHitDTO(
				televersementDIRA1DTO, lotSectionsDIRA1DTO);
		
	} // Fin de beforeClass()._____________________________________________



} // FIN DE LA CLASSE TeleversementDeLotSectionsHitConvertisseurMetierDTOTest.
