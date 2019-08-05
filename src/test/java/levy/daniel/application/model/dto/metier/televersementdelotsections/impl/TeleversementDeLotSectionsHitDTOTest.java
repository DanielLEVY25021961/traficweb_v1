package levy.daniel.application.model.dto.metier.televersementdelotsections.impl;

import static org.junit.Assert.fail;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.dto.metier.anneegestion.IAnneeGestionDTO;
import levy.daniel.application.model.dto.metier.anneegestion.impl.AnneeGestionDTO;
import levy.daniel.application.model.dto.metier.sections.ISectionHitDTO;
import levy.daniel.application.model.dto.metier.televersement.ITeleversementDTO;
import levy.daniel.application.model.dto.metier.televersement.impl.TeleversementDTO;
import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.dto.metier.utilisateur.impl.UtilisateurCerbereDTO;
import levy.daniel.application.model.metier.anneegestion.IAnneeGestion;
import levy.daniel.application.model.metier.anneegestion.impl.AnneeGestion;
import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.televersement.EnumTypeFichierDonnees;
import levy.daniel.application.model.metier.televersement.ITeleversement;
import levy.daniel.application.model.metier.televersementdelotsections.ITeleversementDeLotSectionsHit;
import levy.daniel.application.model.metier.utilisateur.EnumGestionnaire;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.metier.utilisateur.impl.UtilisateurCerbere;
import levy.daniel.application.model.services.valideurs.metier.utilisateurs.EnumCivilites;

/**
 * CLASSE TeleversementDeLotSectionsHitDTOTest :<br/>
 * Test JUnit de la classe {@link TeleversementDeLotSectionsHitDTO}.<br/>
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
 * @since 4 août 2019
 *
 */
public class TeleversementDeLotSectionsHitDTOTest {

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
	 * LocalDateTime.of(2019, 6, 13, 8, 37, 43).
	 */
	public static final LocalDateTime DATE_TELEVERSEMENT_1 
		= LocalDateTime.of(2019, 6, 13, 8, 37, 43);
	
	/**
	 * LocalDateTime.of(2019, 6, 13, 8, 37, 43).<br/>
	 * "2019-06-13_08_37_43"
	 */
	public static final String DATE_TELEVERSEMENT_1_STRING 
		= "2019-06-13_08_37_43";

	/**
	 * LocalDateTime.of(2018, 6, 13, 8, 37, 43).
	 */
	public static final LocalDateTime DATE_TELEVERSEMENT_2 
		= LocalDateTime.of(2018, 6, 13, 8, 37, 43);
	
	/**
	 * LocalDateTime.of(2018, 6, 13, 8, 37, 43).<br/>
	 * "2018-06-13_08_37_43"
	 */
	public static final String DATE_TELEVERSEMENT_2_STRING 
		= "2018-06-13_08_37_43";

	/**
	 * LocalDateTime.of(2018, 1, 13, 8, 37, 43).
	 */
	public static final LocalDateTime DATE_TELEVERSEMENT_3 
		= LocalDateTime.of(2018, 1, 13, 8, 37, 43);
			
	/**
	 * LocalDateTime.of(2018, 1, 13, 8, 37, 43).<br/>
	 * "2018-01-13_08_37_43"
	 */
	public static final String DATE_TELEVERSEMENT_3_STRING 
		= "2018-01-13_08_37_43";

	/**
	 * EnumCivilites.MADEMOISELLE.
	 */
	public static final EnumCivilites CIVILITE_MLLE 
		= EnumCivilites.MADEMOISELLE;
	
	/**
	 * EnumCivilites.MADEMOISELLE.getAbreviation().
	 */
	public static final String CIVILITE_MLLE_STRING 
		= EnumCivilites.MADEMOISELLE.getAbreviation();
	
	/**
	 * EnumCivilites.MONSIEUR.
	 */
	public static final EnumCivilites CIVILITE_M 
		= EnumCivilites.MONSIEUR;
	
	/**
	 * EnumCivilites.MONSIEUR.getAbreviation().
	 */
	public static final String CIVILITE_M_STRING 
		= EnumCivilites.MONSIEUR.getAbreviation();
	
	/**
	 * "Jeanne".
	 */
	public static final String PRENOM_JEANNE = "Jeanne";
	
	/**
	 * "Jean".
	 */
	public static final String PRENOM_JEAN = "Jean";
		
	/**
	 * "Papy".
	 */
	public static final String PRENOM_PAPY = "Papy";

	/**
	 * "Duplantis".
	 */
	public static final String NOM_DUPLANTIS = "Duplantis";
	
	/**
	 * "Dupont".
	 */
	public static final String NOM_DUPONT = "Dupont";

	/**
	 * "Gonzales".
	 */
	public static final String NOM_GONZALES = "Gonzales";
	
	/**
	 * "02 69 87 96 54".
	 */
	public static final String TELEPHONE_JEANNE_DUPLANTIS_DIRA 
		= "02 69 87 96 54";
	
	/**
	 * "02 71 65 42 32".
	 */
	public static final String TELEPHONE_JEAN_DUPONT_DIRA 
		= "02 71 65 42 32";
	
	/**
	 * "03 21 75 48 69".
	 */
	public static final String TELEPHONE_PAPY_GONZALES_DIRCE 
		= "03 21 75 48 69";
	
	/**
	 * "jeanne.duplantis@dira.fr".
	 */
	public static final String MAIL_JEANNE_DUPLANTIS_DIRA 
		= "jeanne.duplantis@dira.fr";
	
	/**
	 * "jean.dupont@dira.fr".
	 */
	public static final String MAIL_JEAN_DUPONT_DIRA 
		= "jean.dupont@dira.fr";
	
	/**
	 * "papy.gonzales@dirce.fr".
	 */
	public static final String MAIL_PAPY_GONZALES_DIRCE 
		= "papy.gonzales@dirce.fr";
	
	/**
	 * "DIRA".<br/>
	 */
	public static final String SERVICE_DIRA = "DIRA";	
	
	/**
	 * "DIRCE".<br/>
	 */
	public static final String SERVICE_DIRCE = "DIRCE";	
	
	/**
	 * "DIRA/SIEER/CIGT".<br/>
	 */
	public static final String UNITE_DIRA_SIEER_CIGT = "DIRA/SIEER/CIGT";
	
	/**
	 * "DIRCE/SIEER/CIGT.
	 */
	public static final String UNITE_DIRCE_SIEER_CIGT = "DIRCE/SIEER/CIGT";
	
	/**
	 * "GESTIONNAIRE".<br/>
	 */
	public static final String PROFIL_GESTIONNAIRE = "GESTIONNAIRE";
	

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
	 * "toto".
	 */
	public static final String TOTO = "toto";

	/**
	 * "titi".
	 */
	public static final String TITI = "titi";
	
	/**
	 * "HIT".<br/>
	 */
	public static final String TYPE_FICHIER_HIT_STRING = "HIT";
	
	/**
	 * "HITDIRA2018".
	 */
	public static final String NOM_FICHIER_HITDIRA2018 = "HITDIRA2018";
	
	/**
	 * "ADMINISTRATEUR".<br/>
	 */
	public static final String PROFIL_ADMINISTRATEUR = "ADMINISTRATEUR";

	/**
	 * ADMINISTRATEUR.<br/>
	 */
	public static final IUtilisateurCerbereDTO UTILISATEUR_ADMIN_1 
		= new UtilisateurCerbereDTO(
				EnumCivilites.MONSIEUR.getAbreviation()
				, "papy", "Gonzales"
				, "01 68 97 36 12", "papy.gonzales@free.fr"
				, SERVICETEST, UNITETEST
				, PROFIL_ADMINISTRATEUR, "France entière", null);
		
	/**
	 * DIRA.<br/>
	 */
	public static final transient IUtilisateurCerbereDTO UTILISATEUR_DIRA_1 
	= new UtilisateurCerbereDTO("3"
			, EnumCivilites.MADEMOISELLE.getAbreviation()
			, PRENOMTEST, NOMTEST
			, "05 25 89 87 45"
			, MAILTEST
			, SERVICE_DIRA, UNITE_DIRA_SIEER_CIGT
			, PROFIL_GESTIONNAIRE
			, "DIRA"
			, "44");
	
	/**
	 * 2016.<br/>
	 */
	public static final IAnneeGestionDTO ANNEE_GESTION_2016 
		= new AnneeGestionDTO("2016");
	
	/**
	 * 2017.<br/>
	 */
	public static final IAnneeGestionDTO ANNEE_GESTION_2017 
		= new AnneeGestionDTO("2017");
	
	/**
	 * 2018.<br/>
	 */
	public static final IAnneeGestionDTO ANNEE_GESTION_2018 
		= new AnneeGestionDTO("2018");
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	/**
	 * "objet1 : ".<br/>
	 */
	public static final String OBJET1 = "objet1 : ";
	
	/**
	 * "objetNull1 : ".<br/>
	 */
	public static final String OBJETNULL1 = "objetNull1 : ";
	
	/**
	 * "objet1.equals(objet2EqualsObj1) : ".<br/>
	 */
	public static final String OBJET1_EQUALS_OBJET2 
		= "objet1.equals(objet2EqualsObj1) : ";
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().
	 */
	public static transient ITeleversementDTO objet1 
		= new TeleversementDTO(
					"1"
					, DATE_TELEVERSEMENT_1_STRING
						, UTILISATEUR_DIRA_1
							, GESTIONNAIRE_DIRA_STRING
								, TYPE_FICHIER_HIT_STRING
									, NOM_FICHIER_HITDIRA2018
										, TOTO
											, ANNEE_GESTION_2018);
	
	/**
	 * objet1MemeInstance doit être la même instance que objet1.<br/>
	 */
	public static transient ITeleversementDTO objet1MemeInstance = objet1;
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().
	 */
	public static transient ITeleversementDTO objet2EqualsObj1 
		=  new TeleversementDTO(
				"2"
				, DATE_TELEVERSEMENT_1_STRING
					, UTILISATEUR_DIRA_1
						, GESTIONNAIRE_DIRA_STRING
							, TYPE_FICHIER_HIT_STRING
								, NOM_FICHIER_HITDIRA2018
									, TOTO
										, ANNEE_GESTION_2018);
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().
	 */
	public static transient ITeleversementDTO objet3EqualsObj1 
		= new TeleversementDTO(
				"3"
				, DATE_TELEVERSEMENT_1_STRING
					, UTILISATEUR_DIRA_1
						, GESTIONNAIRE_DIRA_STRING
							, TYPE_FICHIER_HIT_STRING
								, NOM_FICHIER_HITDIRA2018
									, TITI
										, ANNEE_GESTION_2018);
	
	/**
	 * objetNull1 et objetNull2 doivent être instanciés 
	 * avec le constructeur d'arité nulle ou avoir 
	 * tous les attributs aux valeurs par défaut.
	 */
	public static transient ITeleversementDTO objetNull1 
		= new TeleversementDTO(null, null, null, null, null, null, null, null);
	
	/**
	 * objetNull1 et objetNull2 doivent être instanciés 
	 * avec le constructeur d'arité nulle ou avoir 
	 * tous les attributs aux valeurs par défaut.
	 */
	public static transient ITeleversementDTO objetNull2 
		=  new TeleversementDTO(null, null, null, null, null, null, null, null);
	
	/**
	 * objet1AvecNull et objet2EqualsObjet1AvecNull 
	 * doivent être equals() et avoir certains attributs à null.
	 */
	public static transient ITeleversementDTO objet1AvecNull 
		= new TeleversementDTO(
				"1"
				, DATE_TELEVERSEMENT_1_STRING
					, UTILISATEUR_DIRA_1
						, GESTIONNAIRE_DIRA_STRING
							, TYPE_FICHIER_HIT_STRING
								, null
									, TITI
										, ANNEE_GESTION_2018);
	
	/**
	 * objet1AvecNull et objet2EqualsObjet1AvecNull 
	 * doivent être equals() et avoir certains attributs à null.
	 */
	public static transient ITeleversementDTO objet2EqualsObjet1AvecNull 
		= new TeleversementDTO(
				"3"
				, DATE_TELEVERSEMENT_1_STRING
					, UTILISATEUR_DIRA_1
						, GESTIONNAIRE_DIRA_STRING
							, TYPE_FICHIER_HIT_STRING
								, null
									, TITI
										, ANNEE_GESTION_2018);
	
	/**
	 * objetDiff1 doit être différent de objetDiff2
	 */
	public static transient ITeleversementDTO objetDiff1 
		= new TeleversementDTO(
				"3"
				, DATE_TELEVERSEMENT_1_STRING
					, UTILISATEUR_DIRA_1
						, GESTIONNAIRE_DIRA_STRING
							, TYPE_FICHIER_HIT_STRING
								, NOM_FICHIER_HITDIRA2018
									, TITI
										, ANNEE_GESTION_2018);
	
	/**
	 * objetDiff2 doit être différent de objetDiff1
	 */
	public static transient ITeleversementDTO objetDiff2 
		= new TeleversementDTO(
				"3"
				, DATE_TELEVERSEMENT_2_STRING
					, UTILISATEUR_DIRA_1
						,  GESTIONNAIRE_DIRA_STRING
							, TYPE_FICHIER_HIT_STRING
								, NOM_FICHIER_HITDIRA2018
									, TITI
										, ANNEE_GESTION_2018);
	
	/**
	 * objetCompAvant doit être AVANT objetCompApres.
	 */
	public static transient ITeleversementDTO objetCompAvant 
		= new TeleversementDTO(
				"10"
				, DATE_TELEVERSEMENT_1_STRING
					, UTILISATEUR_DIRA_1
						, GESTIONNAIRE_DIRA_STRING
							, TYPE_FICHIER_HIT_STRING
								, NOM_FICHIER_HITDIRA2018
									, TOTO
										, ANNEE_GESTION_2018);
	
	/**
	 * objetCompApres doit être APRES objetCompAvant.
	 */
	public static transient ITeleversementDTO objetCompApres 
		= new TeleversementDTO(
				"12"
				, DATE_TELEVERSEMENT_1_STRING
					, UTILISATEUR_DIRA_1
						, "DIRCE"
							, TYPE_FICHIER_HIT_STRING
								, "HITDIRCE2018"
									, TOTO
										, ANNEE_GESTION_2018);
	
	/**
	 * clone de objetNull1.<br/>
	 */
	public static transient ITeleversementDTO objetNullClone1;
	
	/**
	 * clone de objet1.<br/>
	 */
	public static transient ITeleversementDTO objetClone1;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(TeleversementDeLotSectionsHitDTOTest.class);

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	// *************************METHODES************************************/
	
	
	
} // FIN DE LA CLASSE TeleversementDeLotSectionsHitDTOTest.------------------
