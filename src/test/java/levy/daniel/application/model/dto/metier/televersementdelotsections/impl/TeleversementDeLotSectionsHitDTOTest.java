package levy.daniel.application.model.dto.metier.televersementdelotsections.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

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
import levy.daniel.application.model.dto.metier.televersementdelotsections.ITeleversementDeLotSectionsHitDTO;
import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.dto.metier.utilisateur.impl.UtilisateurCerbereDTO;
import levy.daniel.application.model.metier.anneegestion.IAnneeGestion;
import levy.daniel.application.model.metier.anneegestion.impl.AnneeGestion;
import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.televersement.EnumTypeFichierDonnees;
import levy.daniel.application.model.metier.televersement.ITeleversement;
import levy.daniel.application.model.metier.televersement.impl.Televersement;
import levy.daniel.application.model.metier.utilisateur.EnumGestionnaire;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.metier.utilisateur.impl.UtilisateurCerbere;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl.ImporteurHit;
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
	* "2018-01-13_08_37_43"
	*/
	public static final String DATE_TELEVERSEMENT_2018_01_13_08_37_43_STRING 
		= "2018-01-13_08_37_43";
	
	/**
	 * LocalDateTime.of(2018, 1, 13, 8, 37, 43).
	 */
	public static final LocalDateTime DATE_TELEVERSEMENT_2018_01_13_08_37_43 
		= LocalDateTime.of(2018, 1, 13, 8, 37, 43);
	
	/**
	 * "2018-06-13_08_37_43"
	 */
	public static final String DATE_TELEVERSEMENT_2018_06_13_08_37_43_STRING 
		= "2018-06-13_08_37_43";

	/**
	 * LocalDateTime.of(2018, 6, 13, 8, 37, 43).<br/>
	 */
	public static final LocalDateTime DATE_TELEVERSEMENT_2018_06_13_08_37_43 
		= LocalDateTime.of(2018, 6, 13, 8, 37, 43);
	
	/**
	 * "2019-06-13_08_37_43"
	 */
	public static final String DATE_TELEVERSEMENT_2019_06_03_08_37_43_STRING 
		= "2019-06-13_08_37_43";

	/**
	 * LocalDateTime.of(2019, 6, 13, 8, 37, 43).
	 */
	public static final LocalDateTime DATE_TELEVERSEMENT_2019_06_03_08_37_43 
		= LocalDateTime.of(2019, 6, 13, 8, 37, 43);

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
	public static final String PROFIL_GESTIONNAIRE_STRING = "GESTIONNAIRE";
	
	/**
	 * "ADMINISTRATEUR".<br/>
	 */
	public static final String PROFIL_ADMIN_STRING = "ADMINISTRATEUR";
	
	/**
	 * "DIRA".<br/>
	 */
	public static final String PORTEE_DIRA = "DIRA";
	
	/**
	 * "DIRCE".<br/>
	 */
	public static final String PORTEE_DIRCE = "DIRCE";
	
	/**
	 * "44".<br/>
	 */
	public static final String RESTRICTION_JEANNE_DUPLANTIS_DIRA = "44";
	
	/**
	 * "85".<br/>
	 */
	public static final String RESTRICTION_JEAN_DUPONT_DIRA = "85";
	
	/**
	 * "69".<br/>
	 */
	public static final String RESTRICTION_PAPY_GONZALES_DIRCE = "69";

	/**
	 * DIRA_1_DTO.<br/>.
	 */
	public static final transient IUtilisateurCerbereDTO UTILISATEUR_JEANNE_DUPLANTIS_DIRA_DTO 
		= new UtilisateurCerbereDTO("3"
				, CIVILITE_MLLE_STRING
				, PRENOM_JEANNE, NOM_DUPLANTIS
				, TELEPHONE_JEANNE_DUPLANTIS_DIRA
				, MAIL_JEANNE_DUPLANTIS_DIRA
				, SERVICE_DIRA, UNITE_DIRA_SIEER_CIGT
				, PROFIL_GESTIONNAIRE_STRING
				, PORTEE_DIRA
				, RESTRICTION_JEANNE_DUPLANTIS_DIRA);
	
	/**
	 * DIRA_1.<br/>
	 */
	public static final transient IUtilisateurCerbere UTILISATEUR_JEANNE_DUPLANTIS_DIRA 
	= new UtilisateurCerbere(3L
			, CIVILITE_MLLE_STRING
			, PRENOM_JEANNE, NOM_DUPLANTIS
			, TELEPHONE_JEANNE_DUPLANTIS_DIRA
			, MAIL_JEANNE_DUPLANTIS_DIRA
			, SERVICE_DIRA, UNITE_DIRA_SIEER_CIGT
			, PROFIL_GESTIONNAIRE_STRING
			, PORTEE_DIRA
			, RESTRICTION_JEANNE_DUPLANTIS_DIRA);

	/**
	 * DIRA_2_DTO.<br/>.
	 */
	public static final transient IUtilisateurCerbereDTO UTILISATEUR_JEAN_DUPONT_DIRA_DTO 
		= new UtilisateurCerbereDTO("17"
				, CIVILITE_M_STRING
				, PRENOM_JEAN, NOM_DUPONT
				, TELEPHONE_JEAN_DUPONT_DIRA
				, MAIL_JEAN_DUPONT_DIRA
				, SERVICE_DIRA, UNITE_DIRA_SIEER_CIGT
				, PROFIL_GESTIONNAIRE_STRING
				, PORTEE_DIRA
				, RESTRICTION_JEAN_DUPONT_DIRA);
	
	/**
	 * DIRA_2.<br/>
	 */
	public static final transient IUtilisateurCerbere UTILISATEUR_JEAN_DUPONT_DIRA 
	= new UtilisateurCerbere(17L
			, CIVILITE_M_STRING
			, PRENOM_JEAN, NOM_DUPONT
			, TELEPHONE_JEAN_DUPONT_DIRA
			, MAIL_JEAN_DUPONT_DIRA
			, SERVICE_DIRA, UNITE_DIRA_SIEER_CIGT
			, PROFIL_GESTIONNAIRE_STRING
			, PORTEE_DIRA
			, RESTRICTION_JEAN_DUPONT_DIRA);

	/**
	 * DIRCE_1_DTO.<br/>.
	 */
	public static final transient IUtilisateurCerbereDTO UTILISATEUR_PAPY_GONZALES_DIRCE_DTO 
		= new UtilisateurCerbereDTO("7"
				, CIVILITE_M_STRING
				, PRENOM_PAPY, NOM_GONZALES
				, TELEPHONE_PAPY_GONZALES_DIRCE
				, MAIL_PAPY_GONZALES_DIRCE
				, SERVICE_DIRCE, UNITE_DIRCE_SIEER_CIGT
				, PROFIL_GESTIONNAIRE_STRING
				, PORTEE_DIRCE
				, RESTRICTION_PAPY_GONZALES_DIRCE);
	
	/**
	 * DIRCE_1.<br/>
	 */
	public static final transient IUtilisateurCerbere UTILISATEUR_PAPY_GONZALES_DIRCE
	= new UtilisateurCerbere(7L
			, CIVILITE_M_STRING
			, PRENOM_PAPY, NOM_GONZALES
			, TELEPHONE_PAPY_GONZALES_DIRCE
			, MAIL_PAPY_GONZALES_DIRCE
			, SERVICE_DIRCE, UNITE_DIRCE_SIEER_CIGT
			, PROFIL_GESTIONNAIRE_STRING
			, PORTEE_DIRCE
			, RESTRICTION_PAPY_GONZALES_DIRCE);
	
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
	 * EnumGestionnaire.DIRCE.getNomCourt().
	 */
	public static final String GESTIONNAIRE_DIRCE_STRING 
		= EnumGestionnaire.DIRCE.getNomCourt();

	/**
	 * EnumGestionnaire.DIRCE.
	 */
	public static final EnumGestionnaire GESTIONNAIRE_DIRCE 
		= EnumGestionnaire.DIRCE;

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
	public static final String NOM_FICHIER_TELEVERSE_JEANNE_DUPLANTIS_DIRA 
		= "HITDIRA2017.txt";

	/**
	 * "HITDIRA2017_modifie.txt".
	 */
	public static final String NOM_FICHIER_TELEVERSE_JEAN_DUPONT_DIRA 
		= "HITDIRA2017_modifie.txt";

	/**
	 * "HITDIRCE2012.txt".
	 */
	public static final String NOM_FICHIER_TELEVERSE_PAPY_GONZALES_DIRCE 
		= "HITDIRCE2012.txt";

	/**
	 * "HITDIRA2017.txt".<br/>
	 * NOM_FICHIER_TELEVERSE_JEANNE_DUPLANTIS_DIRA
	 */
	public static final File FICHIER_STOCKE_SERVEUR_JEANNE_DUPLANTIS_DIRA 
		= new File(NOM_FICHIER_TELEVERSE_JEANNE_DUPLANTIS_DIRA);

	/**
	 * FICHIER_STOCKE_SERVEUR_JEANNE_DUPLANTIS_DIRA.getAbsolutePath()
	 */
	public static final String FICHIER_STOCKE_SERVEUR_JEANNE_DUPLANTIS_DIRA_STRING 
		= FICHIER_STOCKE_SERVEUR_JEANNE_DUPLANTIS_DIRA.getAbsolutePath();

	/**
	 * "HITDIRA2017_modifie.txt".<br/>
	 * NOM_FICHIER_TELEVERSE_JEAN_DUPONT_DIRA
	 */
	public static final File FICHIER_STOCKE_SERVEUR_JEAN_DUPONT_DIRA 
		= new File(NOM_FICHIER_TELEVERSE_JEAN_DUPONT_DIRA);

	/**
	 * FICHIER_STOCKE_SERVEUR_JEAN_DUPONT_DIRA.getAbsolutePath()
	 */
	public static final String FICHIER_STOCKE_SERVEUR_JEAN_DUPONT_DIRA_STRING 
		= FICHIER_STOCKE_SERVEUR_JEAN_DUPONT_DIRA.getAbsolutePath();

	/**
	 * "HITDIRCE2012.txt".<br/>
	 * NOM_FICHIER_TELEVERSE_PAPY_GONZALES_DIRCE
	 */
	public static final File FICHIER_STOCKE_SERVEUR_PAPY_GONZALES_DIRCE 
		= new File(NOM_FICHIER_TELEVERSE_PAPY_GONZALES_DIRCE);

	/**
	 * FICHIER_STOCKE_SERVEUR_PAPY_GONZALES_DIRCE.getAbsolutePath()
	 */
	public static final String FICHIER_STOCKE_SERVEUR_PAPY_GONZALES_DIRCE_STRING 
		= FICHIER_STOCKE_SERVEUR_PAPY_GONZALES_DIRCE.getAbsolutePath();

	/**
	 * new AnneeGestionDTO("2012").
	 */
	public static final IAnneeGestionDTO ANNEE_GESTION_2012_DTO 
		= new AnneeGestionDTO("2012");
	
	/**
	 * new AnneeGestion("2012").
	 */
	public static final IAnneeGestion ANNEE_GESTION_2012 
		= new AnneeGestion("2012");
	
	/**
	 * new AnneeGestionDTO("2016").
	 */
	public static final IAnneeGestionDTO ANNEE_GESTION_2016_DTO 
		= new AnneeGestionDTO("2016");
	
	/**
	 * new AnneeGestion("2016").<br/>
	 */
	public static final IAnneeGestion ANNEE_GESTION_2016 
		= new AnneeGestion("2016");

	/**
	 * new AnneeGestionDTO("2017").
	 */
	public static final IAnneeGestionDTO ANNEE_GESTION_2017_DTO 
		= new AnneeGestionDTO("2017");
	
	/**
	 * new AnneeGestion("2017").
	 */
	public static final IAnneeGestion ANNEE_GESTION_2017 
		= new AnneeGestion("2017");
	
	/**
	 * new AnneeGestionDTO("2018").
	 */
	public static final IAnneeGestionDTO ANNEE_GESTION_2018_DTO 
		= new AnneeGestionDTO("2018");
	
	/**
	 * new AnneeGestion("2018").
	 */
	public static final IAnneeGestion ANNEE_GESTION_2018 
		= new AnneeGestion("2018");
	
	/**
	 * new AnneeGestionDTO("2019").
	 */
	public static final IAnneeGestionDTO ANNEE_GESTION_2019_DTO 
		= new AnneeGestionDTO("2019");
	
	/**
	 * new AnneeGestion("2019").
	 */
	public static final IAnneeGestion ANNEE_GESTION_2019 
		= new AnneeGestion("2019");
	
	/**
	 * Téléversement de Jeanne Duplantis (DIRA) en 2018 (données 2017).
	 */
	public static final ITeleversement TELEVERSEMENT_JEANNE_DUPLANTIS_DIRA 
		= new Televersement(
				DATE_TELEVERSEMENT_2018_01_13_08_37_43
				, UTILISATEUR_JEANNE_DUPLANTIS_DIRA
				, GESTIONNAIRE_DIRA
				, TYPE_FICHIER_DONNEES_HIT
				, NOM_FICHIER_TELEVERSE_JEANNE_DUPLANTIS_DIRA
				, FICHIER_STOCKE_SERVEUR_JEANNE_DUPLANTIS_DIRA
				, ANNEE_GESTION_2017);
	
	/**
	 * Téléversement DTO de Jeanne Duplantis (DIRA) en 2018 (données 2017).
	 */
	public static final ITeleversementDTO TELEVERSEMENT_JEANNE_DUPLANTIS_DIRA_DTO 
		= new TeleversementDTO(
				DATE_TELEVERSEMENT_2018_01_13_08_37_43_STRING
				, UTILISATEUR_JEANNE_DUPLANTIS_DIRA_DTO
				, GESTIONNAIRE_DIRA_STRING
				, TYPE_FICHIER_DONNEES_HIT_STRING
				, NOM_FICHIER_TELEVERSE_JEANNE_DUPLANTIS_DIRA
				, FICHIER_STOCKE_SERVEUR_JEANNE_DUPLANTIS_DIRA_STRING
				, ANNEE_GESTION_2017_DTO);
	
	/**
	 * Téléversement de Jean Dupont (DIRA) en 2018 (données 2017).
	 */
	public static final ITeleversement TELEVERSEMENT_JEAN_DUPONT_DIRA 
		= new Televersement(
				DATE_TELEVERSEMENT_2018_06_13_08_37_43
				, UTILISATEUR_JEAN_DUPONT_DIRA
				, GESTIONNAIRE_DIRA
				, TYPE_FICHIER_DONNEES_HIT
				, NOM_FICHIER_TELEVERSE_JEAN_DUPONT_DIRA
				, FICHIER_STOCKE_SERVEUR_JEAN_DUPONT_DIRA
				, ANNEE_GESTION_2017);
	
	/**
	 * Téléversement DTO de Jean Dupont (DIRA) en 2018 (données 2017).
	 */
	public static final ITeleversementDTO TELEVERSEMENT_JEAN_DUPONT_DIRA_DTO 
		= new TeleversementDTO(
				DATE_TELEVERSEMENT_2018_06_13_08_37_43_STRING
				, UTILISATEUR_JEAN_DUPONT_DIRA_DTO
				, GESTIONNAIRE_DIRA_STRING
				, TYPE_FICHIER_DONNEES_HIT_STRING
				, NOM_FICHIER_TELEVERSE_JEAN_DUPONT_DIRA
				, FICHIER_STOCKE_SERVEUR_JEAN_DUPONT_DIRA_STRING
				, ANNEE_GESTION_2017_DTO);
	
	/**
	 * Téléversement de Papy Gonzales (DIRCE) en 2019 (données 2012).
	 */
	public static final ITeleversement TELEVERSEMENT_PAPY_GONZALES_DIRCE
		= new Televersement(
				DATE_TELEVERSEMENT_2019_06_03_08_37_43
				, UTILISATEUR_PAPY_GONZALES_DIRCE
				, GESTIONNAIRE_DIRCE
				, TYPE_FICHIER_DONNEES_HIT
				, NOM_FICHIER_TELEVERSE_PAPY_GONZALES_DIRCE
				, FICHIER_STOCKE_SERVEUR_PAPY_GONZALES_DIRCE
				, ANNEE_GESTION_2012);
	
	/**
	 * Téléversement DTO de Papy Gonzales (DIRCE) en 2019 (données 2012).
	 */
	public static final ITeleversementDTO TELEVERSEMENT_PAPY_GONZALES_DIRCE_DTO 
		= new TeleversementDTO(
				DATE_TELEVERSEMENT_2019_06_03_08_37_43_STRING
				, UTILISATEUR_PAPY_GONZALES_DIRCE_DTO
				, GESTIONNAIRE_DIRCE_STRING
				, TYPE_FICHIER_DONNEES_HIT_STRING
				, NOM_FICHIER_TELEVERSE_PAPY_GONZALES_DIRCE
				, FICHIER_STOCKE_SERVEUR_PAPY_GONZALES_DIRCE_STRING
				, ANNEE_GESTION_2012_DTO);
			
	/**
	 * lotSectionsJeanneDuplantisDIRA.<br/>
	 * NOM_FICHIER_TELEVERSE_JEANNE_DUPLANTIS_DIRA<br/>
	 * "HITDIRA2017.txt"<br/>
	 */
	public static Map<Integer, ISectionHit> lotSectionsJeanneDuplantisDIRA;
	
	/**
	 * lotSectionsJeanneDuplantisDIRADTO.<br/>
	 * NOM_FICHIER_TELEVERSE_JEANNE_DUPLANTIS_DIRA<br/>
	 * "HITDIRA2017.txt"<br/>
	 */
	public static Map<Integer, ISectionHitDTO> lotSectionsJeanneDuplantisDIRADTO;	
		
	/**
	* lotSectionsJeanDupontDIRA.<br/>
	* NOM_FICHIER_TELEVERSE_JEAN_DUPONT_DIRA<br/>
	* "HITDIRA2017_modifie.txt"<br/>
	*/
	public static Map<Integer, ISectionHit> lotSectionsJeanDupontDIRA;
	
	/**
	* lotSectionsJeanDupontDIRADTO.<br/>
	* NOM_FICHIER_TELEVERSE_JEAN_DUPONT_DIRA<br/>
	* "HITDIRA2017_modifie.txt"<br/>
	*/
	public static Map<Integer, ISectionHitDTO> lotSectionsJeanDupontDIRADTO;	
		
	/**
	* lotSectionsPapyGonzalesDIRCE.<br/>
	* NOM_FICHIER_TELEVERSE_PAPY_GONZALES_DIRCE<br/>
	* "HITDIRCE2012.txt"<br/>
	*/
	public static Map<Integer, ISectionHit> lotSectionsPapyGonzalesDIRCE;
	
	/**
	* lotSectionsPapyGonzalesDIRCEDTO.<br/>
	* NOM_FICHIER_TELEVERSE_PAPY_GONZALES_DIRCE<br/>
	* "HITDIRCE2012.txt"<br/>
	*/
	public static Map<Integer, ISectionHitDTO> lotSectionsPapyGonzalesDIRCEDTO;	
	
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

	
	// ******************************************************************
	// OBJETS A TESTER
	// ******************************************************************
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().
	 */
	public static transient ITeleversementDeLotSectionsHitDTO objet1;
	
	/**
	 * objet1MemeInstance doit être la même instance que objet1.<br/>
	 * (objet1MemeInstance = objet1).
	 */
	public static transient ITeleversementDeLotSectionsHitDTO objet1MemeInstance;
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().
	 */
	public static transient ITeleversementDeLotSectionsHitDTO objet2EqualsObj1;
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().
	 */
	public static transient ITeleversementDeLotSectionsHitDTO objet3EqualsObj1;
	
	/**
	 * objetNull1 et objetNull2 doivent être instanciés 
	 * avec le constructeur d'arité nulle ou avoir 
	 * tous les attributs aux valeurs par défaut.<br/>
	 * (ne pas faire objetNull1 = null).
	 */
	public static transient ITeleversementDeLotSectionsHitDTO objetNull1;
	
	/**
	 * objetNull1 et objetNull2 doivent être instanciés 
	 * avec le constructeur d'arité nulle ou avoir 
	 * tous les attributs aux valeurs par défaut.<br/>
	 * (ne pas faire objetNull2 = null).
	 */
	public static transient ITeleversementDeLotSectionsHitDTO objetNull2;
	
	/**
	 * objet1AvecNull et objet2EqualsObjet1AvecNull 
	 * doivent être equals() et avoir certains attributs à null.
	 */
	public static transient ITeleversementDeLotSectionsHitDTO objet1AvecNull;
	
	/**
	 * objet1AvecNull et objet2EqualsObjet1AvecNull 
	 * doivent être equals() et avoir certains attributs à null.
	 */
	public static transient ITeleversementDeLotSectionsHitDTO objet2EqualsObjet1AvecNull;
	
	/**
	 * objetDiff1 doit être différent de objetDiff2
	 */
	public static transient ITeleversementDeLotSectionsHitDTO objetDiff1;
	
	/**
	 * objetDiff2 doit être différent de objetDiff1
	 */
	public static transient ITeleversementDeLotSectionsHitDTO objetDiff2;
	
	/**
	 * objetCompAvant doit être AVANT objetCompApres.
	 */
	public static transient ITeleversementDeLotSectionsHitDTO objetCompAvant;
	
	/**
	 * objetCompApres doit être APRES objetCompAvant.
	 */
	public static transient ITeleversementDeLotSectionsHitDTO objetCompApres;
	
	/**
	 * clone de objetNull1.<br/>
	 */
	public static transient ITeleversementDeLotSectionsHitDTO objetNullClone1;
	
	/**
	 * clone de objet1.<br/>
	 */
	public static transient ITeleversementDeLotSectionsHitDTO objetClone1;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(TeleversementDeLotSectionsHitDTOTest.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public TeleversementDeLotSectionsHitDTOTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * Teste la méthode <b>equals(Object pObject)</b> :
	 * <ul>
	 * <li>garantit le contrat Java reflexif x.equals(x).</li>
	 * <li>garantit le contrat Java symétrique 
	 * x.equals(y) ----> y.equals(x).</li>
	 * <li>garantit le contrat Java transitif 
	 * x.equals(y) et y.equals(z) ----> x.equals(z).</li>
	 * <li>garantit le contrat Java sur les hashcode 
	 * x.equals(y) ----> x.hashcode() == y.hashcode().</li>
	 * <li>garantit que les null sont bien gérés 
	 * dans equals(Object pObj).</li>
	 * <li>garantit que x.equals(null) retourne false 
	 * (avec x non null).</li>
	 * <li>garantit le bon fonctionnement de equals() 
	 * en cas d'égalité métier.</li>
	 * <li>garantit le bon fonctionnement de equals() 
	 * en cas d'inégalité métier.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testEquals() {
					
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE TeleversementDeLotSectionsHitTest - méthode testEquals() ********** ");
		}

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DE 3 Objets equals ----------------");
			System.out.println("objet1.toString() : " + objet1.toString());
			System.out.println(objet1.afficherLotSections());
			System.out.println("objet2EqualsObj1.toString() : " + objet2EqualsObj1.toString());
			System.out.println("objet3EqualsObj1.toString() : " + objet3EqualsObj1.toString());
			System.out.println();
			System.out.println("objet1.equals(objet1) : " + objet1.equals(objet1));
			System.out.println(OBJET1_EQUALS_OBJET2 + objet1.equals(objet2EqualsObj1));
			System.out.println("objet2EqualsObj1.equals(objet1) : " + objet2EqualsObj1.equals(objet1));
			System.out.println("objet2EqualsObj1.equals(objet3EqualsObj1) : " + objet2EqualsObj1.equals(objet3EqualsObj1));
			System.out.println("objet1.equals(objet3EqualsObj1) : " + objet1.equals(objet3EqualsObj1));
			System.out.println("objet1.hashCode() == objet2EqualsObj1.hashCode() : " + (objet1.hashCode() == objet2EqualsObj1.hashCode()));
		}
		
		
		/* garantit le contrat Java reflexif x.equals(x). */
		assertEquals("x.equals(x) : "
				, objet1
					, objet1);
				
		/* garantit le contrat Java symétrique 
		 * x.equals(y) ----> y.equals(x). */
		assertEquals(OBJET1_EQUALS_OBJET2
				, objet1
					, objet2EqualsObj1);
		
		assertEquals("objet2EqualsObj1.equals(objet1) : "
				, objet2EqualsObj1
					, objet1);
		
		/* garantit le contrat Java transitif 
		 * x.equals(y) et y.equals(z) ----> x.equals(z). */
		assertEquals(OBJET1_EQUALS_OBJET2, objet1, objet2EqualsObj1);
		assertEquals("objet2EqualsObj1.equals(objet3EqualsObj1) : ", objet2EqualsObj1, objet3EqualsObj1);
		assertEquals("objet1.equals(objet3EqualsObj1) : ", objet1, objet3EqualsObj1);
		
		/* garantit le contrat Java sur les hashcode 
		 * x.equals(y) ----> x.hashcode() == y.hashcode(). */
		assertEquals("objet1.hashCode().equals(objet2EqualsObj1.hashCode()) : "
				, objet1.hashCode()
					, objet2EqualsObj1.hashCode());

				
		/* garantit que les null sont bien gérés dans equals(...). */
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'OBJETS INSTANCIES AVEC LE CONSTRUCTEUR D'ARITE NULLE (ou attributs par défaut) -------");
			System.out.println("OBJETNULL1 : " 
					+ objetNull1.toString());
			System.out.println("OBJETNULL2 : " 
					+ objetNull2.toString());
			System.out.println();
			System.out.println("objetNull1.equals(objetNull2) : " + objetNull1.equals(objetNull2));
			System.out.println("objetNull1.hashCode().equals(objetNull2.hashCode()) : " + (objetNull1.hashCode() == objetNull2.hashCode()));
		}

		assertEquals("objetNull1.equals(objetNull2) : "
				, objetNull1
					, objetNull2);
		assertEquals("objetNull1.hashCode().equals(objetNull2.hashCode()) : "
				, objetNull1.hashCode()
					, objetNull2.hashCode());
		

		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'OBJETS AVEC CERTAINS ATTRIBUTS NULL -----------");
			System.out.println("objet1AvecNull : " 
					+ objet1AvecNull.toString());
			System.out.println("objet2EqualsObjet1AvecNull : " 
					+ objet2EqualsObjet1AvecNull.toString());
			System.out.println();
			System.out.println("objet1AvecNull.equals(objet2EqualsObjet1AvecNull) : " + objet1AvecNull.equals(objet2EqualsObjet1AvecNull));
			System.out.println("objet1AvecNull.hashCode() == objet2EqualsObjet1AvecNull.hashCode()" + (objet1AvecNull.hashCode() == objet2EqualsObjet1AvecNull.hashCode()));
		}

		assertEquals("objet1AvecNull.equals(objet2EqualsObjet1AvecNull) : "
				, objet1AvecNull
					, objet2EqualsObjet1AvecNull);
		assertEquals("objet1AvecNull.hashCode()"
				+ ".equals(objet2EqualsObjet1AvecNull.hashCode()) : "
				, objet1AvecNull.hashCode()
					, objet2EqualsObjet1AvecNull.hashCode());


		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("OBJET EXISTANT EQUALS null --------------------------");
			System.out.println("objet1.toString() : " + objet1.toString());
			System.out.println();
			System.out.println("objet1.equals(null) : " + (objet1 == null));
		}
		
		/* garantit que x.equals(null) retourne false (avec x non null). */
		assertNotNull("objet1 pas equals(null) : "
				, objet1);

		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'EGALITE METIER --------------------");
			System.out.println(OBJET1 
					+ objet1.toString());
			System.out.println("objet2EqualsObj1 : " 
					+ objet2EqualsObj1.toString());
			System.out.println();
			System.out.println("objet1.equals(objet2EqualsObj1) : " + objet1.equals(objet2EqualsObj1));
		}

		
		/* garantit le bon fonctionnement de equals() 
		 * en cas d'égalité métier. */
		assertEquals(OBJET1_EQUALS_OBJET2
				, objet1
					, objet2EqualsObj1);
		

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DE 2 Objets DIFFERENTS (pas equals) ---------------------");
			System.out.println("objetDiff1 : " 
					+ objetDiff1.toString());
			System.out.println("objetDiff2 : " 
					+ objetDiff2.toString());
			System.out.println();
			System.out.println("objetDiff1.equals(objetDiff2) : " + objetDiff1.equals(objetDiff2));
			System.out.println("objetDiff1.hashcode() == objetDiff2.hashcode() : " + (objetDiff1.hashCode() == objetDiff2.hashCode()));
		}
		
		/* garantit le bon fonctionnement de equals() 
		 * en cas d'inégalité métier. */
		assertFalse("objetDiff1 PAS equals(objetDiff2) : "
				, objetDiff1.equals(objetDiff2));
		assertFalse("objetDiff1.hashCode() "
				+ "PAS equals(objetDiff2.hashCode()) : "
				, objetDiff1.hashCode() == objetDiff2.hashCode());
		
	} // Fin de testEquals().______________________________________________



	/**
	 * Teste la méthode <b>compareTo(...)</b> :
	 * <ul>
	 * <li>garantit que compareTo(memeInstance) retourne 0.</li>
	 * <li>garantit que compareTo(null) retourne un nombre négatif.</li>
	 * <li>garantit le contrat Java Contrat Java : 
	 * x.equals(y) ---> x.compareTo(y) == 0.</li>
	 * <li>garantit que les null sont bien gérés 
	 * dans compareTo(...).</li>
	 * <li>garantit le bon fonctionnement (bon ordre) de compareTo().</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCompareTo() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE TeleversementDeLotSectionsHitTest - méthode testCompareTo() ********** ");
		}

		
		/* garantit que compareTo(memeInstance) retourne 0. */		
		final int compareMemeInstance 
			= objet1.compareTo(objet1MemeInstance);

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE 2 MEMES INSTANCES ------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println("objet1MemeInstance : " + objet1MemeInstance.toString());
			System.out.println();
			System.out.println("COMPARETO() DE LA MEME INSTANCE (objet1.compareTo(objet1MemeInstance)) : " 
					+ compareMemeInstance);
		}
		
		assertTrue("compareTo(memeInstance) doit retourner 0 : "
				, compareMemeInstance == 0);

		
		/* garantit que compareTo(null) retourne -1. */
		final int compareToNull = objet1.compareTo(null);

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE objet1 avec null ---------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println();
			System.out.println("COMPARETO(null) (objet1.compareTo(null)) : " 
					+ compareToNull);
		}
		
		assertTrue("compareTo(null) doit retourner négatif : "
				, compareToNull < 0);

		
		/* garantit le contrat Java Contrat Java : 
		 * x.equals(y) ---> x.compareTo(y) == 0. */		
		final int compareToEquals 
			= objet2EqualsObj1.compareTo(objet3EqualsObj1);

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE 2 objets equals ---------------------");
			System.out.println("objet2EqualsObj1 : " + objet2EqualsObj1.toString());
			System.out.println("objet3EqualsObj1 : " + objet3EqualsObj1.toString());
			System.out.println();
			System.out.println("COMPARETO(2 objets equals)  (objet2EqualsObj1.compareTo(objet3EqualsObj1)) : " 
					+ compareToEquals);
		}

		assertTrue("Instance.compareTo(equalsInstance) doit retourner 0 : "
				, compareToEquals == 0);
		assertTrue("loc10.hashCode() == loc11.hashCode() : "
				, objet2EqualsObj1.hashCode() == objet3EqualsObj1.hashCode());
		
		
		
		/* garantit que les null sont bien gérés dans 
		 * compareTo(...). */		
		final int compareToEqualsNull 
			= objetNull1.compareTo(objetNull2);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE 2 Objets INSTANCIES AVEC LE CONSTRUCTEUR D'ARITE NULLE (ou valeurs par défaut) ------------");
			System.out.println(OBJETNULL1 + objetNull1.toString());
			System.out.println("objetNull2 : " + objetNull2.toString());
			System.out.println();
			System.out.println("COMPARETO(2 objets instanciés avec le constructeur d'arite nulle)  (objetNull1.compareTo(objetNull2)) : " 
					+ compareToEqualsNull);
		}
		
		assertTrue("InstanceNull.compareTo(equalsInstanceNull) doit retourner 0 : "
				, compareToEqualsNull == 0);
		assertTrue("locNull10.hashCode() == locNull11.hashCode() : "
				, objetNull1.hashCode() == objetNull2.hashCode());
		

		
		/* garantit le bon fonctionnement (bon ordre) de compareTo(). */		
		final int compare 
			= objetCompAvant.compareTo(objetCompApres);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE 2 Objets différents -------------");
			System.out.println("objetCompAvant : " + objetCompAvant.toString());
			System.out.println("objetCompApres : " + objetCompApres.toString());
			System.out.println();
			System.out.println("COMPARETO(2 objets différents) (objetCompAvant.compareTo(this.objetCompApres) : " 
					+ compare);
		}
		
		assertTrue("objetCompAvant doit être avant objetCompApres : "
				, compare < 0);
		
	} // Fin de testCompareTo().___________________________________________
	

	
	/**
	 * Teste la méthode <b>clone()</b> :
	 * <ul>
	 * <li>garantit que les null sont bien gérés dans clone().</li>
	 * <li>garantit que clonex.equals(x).</li>
	 * <li>garantit que x et son clone ne sont pas la même instance.</li>
	 * </ul>
	 * @throws CloneNotSupportedException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testClone() throws CloneNotSupportedException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE TeleversementDeLotSectionsHitTest - méthode testClone() ********** ");
		}

		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DU CLONAGE D'OBJET INSTANCIES AVEC LE CONSTRUCTEUR D'ARITE NULLE ---------------------");
			System.out.println(OBJETNULL1 
					+ objetNull1.toString());
			System.out.println("objetNullClone1 : " 
					+ objetNullClone1.toString());
			System.out.println();
			System.out.println("objetNull1.equals(objetNullClone1) : " + objetNull1.equals(objetNullClone1));
		}
		
		/* garantit que les null sont bien gérés dans clone(). */
		assertEquals("objetNull1.equals(objetNullClone1) : "
				, objetNull1
					, objetNullClone1);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DU CLONAGE D'OBJETS ORDINAIREMENT INSTANCIES --------------------");
			System.out.println(OBJET1 
					+ objet1.toString());
			System.out.println("objetClone1 : " 
					+ objetClone1.toString());
			System.out.println();
			System.out.println("objet1.equals(objetClone1) : " + objet1.equals(objetClone1));
		}
		
		/* garantit que clone(x).equals(x). */
		assertEquals("clone(x).equals(x) : "
				, objet1
					, objetClone1);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("INSTANCE ET CLONE DIFFERENTS --------------------");
			System.out.println(OBJET1 
					+ objet1.toString());
			System.out.println("objetClone1 : " 
					+ objetClone1.toString());
			System.out.println();
			System.out.println("objet1 == objetClone1 : " + (objet1 == objetClone1));    
		}
		
		/* garantit que x et son clone ne sont pas la même instance. */
		assertNotSame("x != clonex : "
				, objet1
					, objetClone1);
				
	} // Fin de testClone()._______________________________________________
	
	
		
	/**
	 * Teste la méthode <b>toString()</b> :
	 * <ul>
	 * <li>garantit que les null sont bien gérés dans toString().</li>
	 * <li>garantit le bon affichage de toString().</li>
	 * <li><b>Pour les Développeurs : 
	 * Adapter la chaîne affichée dans les assertEquals</b></li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testToString() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE TeleversementDeLotSectionsHitTest - méthode testToString() ********** ");
		}
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("objetNull1.toString() : " + objetNull1.toString());
		}
		
		/* garantit que les null sont bien gérés dans toString(). */
		assertEquals("objetNull1.toString() retourne une chaine : "
				, "TeleversementDeLotSectionsHitDTO [idString=null, dateTeleversement=null, utilisateur=null, gestionnaire=null, typeFichier=null, nomFichierTeleverse=null, fichierStockeServeur=null, anneeGestion=null]"
						, objetNull1.toString());
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("objet1.toString() : " + objet1.toString());
		}
		
		/* garantit le bon affichage de toString(). */
		assertEquals("affichage : "
				, "TeleversementDeLotSectionsHitDTO [idString=null, dateTeleversement=2018-01-13_08_37_43, utilisateur=Duplantis, gestionnaire=DIRA, typeFichier=HIT, nomFichierTeleverse=HITDIRA2017.txt, fichierStockeServeur=D:\\Donnees\\eclipse\\eclipseworkspace\\traficweb_v1\\HITDIRA2017.txt, anneeGestion=2017]"
						, objet1.toString());
				
	} // Fin de testToString().____________________________________________
	

	
	/**
	 * Teste la méthode <b>fournirEnTeteCsv()</b> :
	 * <ul>
	 * <li>garantit que fournirEnTeteCsv() retourne le bon en-tête csv.</li>
	 * <li><b>Pour les Développeurs : 
	 * Adapter la chaîne affichée dans les assertEquals</b></li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirEnTeteCsv() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE TeleversementDeLotSectionsHitTest - méthode testFournirEnTeteCsv() ********** ");
		}
	

		/* garantit que fournirEnTeteCsv() retourne le bon en-tête csv. */
		final String entete = objet1.fournirEnTeteCsv();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("EN-TETE CSV ------------------------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println();
			System.out.println("En-tête csv : " + entete);
		}
		
		assertEquals("en-tête csv : "
				, "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;nomFichierTeleverse;fichierStockeServeur;anneeGestion;"
					, entete);
				
	} // Fin de testFournirEnTeteCsv().____________________________________
	

	
	/**
	 * Teste la méthode <b>fournirStringCsv()</b> :
	 * <ul>
	 * <li>garantit que les null sont gérés dans fournirStringCsv().</li> 
	 * <li>garantit que fournirStringCsv() retourne la bonne ligne csv.</li>
	 * <li><b>Adapter la chaîne affichée dans les assertEquals</b></li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirStringCsv() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE TeleversementDeLotSectionsHitTest - méthode testFournirStringCsv() ********** ");
		}

		/* garantit que les null sont gérés dans fournirStringCsv(). */
		final String ligneCsvNull = objetNull1.fournirStringCsv();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("STRING CSV D' UN OBJET INSTANCIE AVEC LE CONSTRUCTEUR D'ARITE NULLE -----------------------");
			System.out.println("objetNull1.toString() : " + objetNull1.toString());
			System.out.println();
			System.out.println("objetNull1.fournirStringCsv() = " 
					+ ligneCsvNull);
		}

		assertEquals("ligne csv null = "
				, "null;null;null;null;null;null;null;null;"
					, ligneCsvNull);
		
						
		/* garantit que fournirStringCsv() retourne la bonne ligne csv. */
		final String ligneCsv = objet1.fournirStringCsv();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("STRING CSV D' UN OBJET INSTANCIE AVEC LE CONSTRUCTEUR ORDINAIRE -----------------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println();
			System.out.println("objet1.fournirStringCsv() = " + ligneCsv);
		}

		assertEquals("ligne csv : "
				, "null;2018-01-13_08_37_43;Duplantis;DIRA;HIT;HITDIRA2017.txt;D:\\Donnees\\eclipse\\eclipseworkspace\\traficweb_v1\\HITDIRA2017.txt;2017;"
					, ligneCsv);
				
	} // Fin de testFournirStringCsv().____________________________________
	

	
	/**
	 * Teste la méthode <b>fournirEnTeteColonne(int pI)</b> :
	 * <ul>
	 * <li>garantit que les null sont gérés dans 
	 * fournirEnTeteColonne(int pI).</li> 
	 * <li>garantit que fournirEnTeteColonne(int pI) retourne 
	 * la bonne en-tête de colonne.</li>
	 * <li><b>Compléter les colonnes.</b></li>
	 * <li><b>Adapter la chaîne affichée dans les assertEquals</b></li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirEnTeteColonne() {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE TeleversementDeLotSectionsHitTest - méthode testFournirEnTeteColonne() ********** ");
		}
		
		/* garantit que les null sont gérés 
		 * dans fournirEnTeteColonne(int pI). */		
		final String enteteNull0 = objetNull1.fournirEnTeteColonne(0);
		final String enteteNull1 = objetNull1.fournirEnTeteColonne(1);
		
		/* garantit que fournirEnTeteColonne(int pI) retourne 
		 * la bonne en-tête de colonne. */
		final String entete0 = objet1.fournirEnTeteColonne(0);
		final String entete1 = objet1.fournirEnTeteColonne(1);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET1 + objet1.toString());
			System.out.println(OBJETNULL1 + objetNull1.toString());
			System.out.println();
			System.out.println("EN-TETES JTABLE -----------------------------");
			System.out.println("entete0 (objet1.fournirEnTeteColonne(0)) : " + entete0);
			System.out.println("enteteNull0 (objetNull1.fournirEnTeteColonne(0)) : " + enteteNull0);
			System.out.println();
			System.out.println("entete1 (objet1.fournirEnTeteColonne(1)) : " + entete1);
			System.out.println("enteteNull1 (objetNull1.fournirEnTeteColonne(1)) : " + enteteNull1);
			System.out.println();
			
		}

		assertEquals("entete0 : ", "id", entete0);
		assertEquals("enteteNull0 : ", "id", enteteNull0);
		
		assertEquals("entete1 : ", "dateTeleversement", entete1);
		assertEquals("enteteNull1 : ", "dateTeleversement", enteteNull1);
				
	} // Fin de testFournirEnTeteColonne().________________________________
	
	
	
	/**
	 * Teste la méthode <b>fournirValeurColonne(int pI)</b> :
	 * <ul>
	 * <li>garantit que les null sont gérés dans 
	 * fournirValeurColonne(int pI).</li> 
	 * <li>garantit que fournirValeurColonne(int pI) retourne 
	 * la bonne valeur de colonne.</li>
	 * <li><b>Compléter les colonnes.</b></li>
	 * <li><b>Adapter la chaîne affichée dans les assertEquals</b></li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirValeurColonne() {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE TeleversementDeLotSectionsHitTest - méthode testFournirValeurColonne() ********** ");
		}
		
		/* garantit que les null sont gérés 
		 * dans fournirValeurColonne(int pI). */
		final String valeurNull0 = (String) objetNull1.fournirValeurColonne(0);
		final String valeurNull1 = (String) objetNull1.fournirValeurColonne(1);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'UN OBJET INSTANCIE AVEC LE CONSTRUCTEUR D'ARITE NULLE --------------------");
			System.out.println(OBJETNULL1 + objetNull1.toString());
			System.out.println();
			System.out.println("valeurNull0 ((String) objetNull1.fournirValeurColonne(0)) : " + valeurNull0);
			System.out.println("valeurNull1 ((String) objetNull1.fournirValeurColonne(1)) : " + valeurNull1);
		}

		assertEquals("valeurNull0 ((String) objetNull1.fournirValeurColonne(0)) : ", null, valeurNull0);
		assertEquals("valeurNull1 ((String) objetNull1.fournirValeurColonne(1)) : ", null, valeurNull1);

		
		/* garantit que fournirValeurColonne(int pI) retourne 
		 * la bonne en-tête de colonne. */
		final String valeur0 = (String) objet1.fournirValeurColonne(0);
		final String valeur1 = (String) objet1.fournirValeurColonne(1);

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {	
			System.out.println();
			System.out.println("CAS D'UN OBJET INSTANCIE AVEC LE CONSTRUCTEUR ORDINAIRE -------------------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println();
			System.out.println("valeur0 ((String) objet1.fournirValeurColonne(0)) : " + valeur0);
			System.out.println("valeur1 ((String) objet1.fournirValeurColonne(1)) : " + valeur1);
		}
		
		assertEquals("valeur0 ((String) objet1.fournirValeurColonne(0)) : ", null, valeur0);		
		assertEquals("valeur1 ((String) objet1.fournirValeurColonne(1)) : ", "2018-01-13_08_37_43", valeur1);
		
	} // Fin de testFournirValeurColonne().________________________________
	

	
	/**
	 * Exécuté avant tout test de la méthode.<br/>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		
		System.setProperty("%log4j.skipJansi", "false");
		
		final Charset charsetAnsi = Charset.forName("Windows-1252");
		
		final Path fichierDonneesPathJeanneDuplantisDIRA 
			= PATH_ABSOLU_TEST_JEUX_ESSAI.resolve(NOM_FICHIER_TELEVERSE_JEANNE_DUPLANTIS_DIRA);
		final File fichierDonneesJeanneDuplantisDIRA = fichierDonneesPathJeanneDuplantisDIRA.toFile();

		final Path fichierDonneesPathJeanDupontDIRA 
			= PATH_ABSOLU_TEST_JEUX_ESSAI.resolve(NOM_FICHIER_TELEVERSE_JEAN_DUPONT_DIRA);
		final File fichierDonneesJeanDupontDIRA = fichierDonneesPathJeanDupontDIRA.toFile();

		final Path fichierDonneesPathPapyGonzalesDIRCE 
			= PATH_ABSOLU_TEST_JEUX_ESSAI.resolve(NOM_FICHIER_TELEVERSE_PAPY_GONZALES_DIRCE);
		final File fichierDonneesPapyGonzalesDIRCE = fichierDonneesPathPapyGonzalesDIRCE.toFile();

		
		// IMPORT DES FICHIERS
		final ImporteurHit importeurHIT = new ImporteurHit();
		
		lotSectionsJeanneDuplantisDIRA 
			= importeurHIT.importerObjet(
					fichierDonneesJeanneDuplantisDIRA, charsetAnsi);
		
		lotSectionsJeanneDuplantisDIRADTO 
			= importeurHIT.importerDTO(
					fichierDonneesJeanneDuplantisDIRA, charsetAnsi);
		
		lotSectionsJeanDupontDIRA 
			= importeurHIT.importerObjet(fichierDonneesJeanDupontDIRA, charsetAnsi);
		
		lotSectionsJeanDupontDIRADTO 
			= importeurHIT.importerDTO(fichierDonneesJeanDupontDIRA, charsetAnsi);
		
		lotSectionsPapyGonzalesDIRCE 
			= importeurHIT.importerObjet(fichierDonneesPapyGonzalesDIRCE, charsetAnsi);
		
		lotSectionsPapyGonzalesDIRCEDTO 
			= importeurHIT.importerDTO(fichierDonneesPapyGonzalesDIRCE, charsetAnsi);

		// INSTANCIATION DES OBJETS DE TEST.
		objet1 = new TeleversementDeLotSectionsHitDTO(
				TELEVERSEMENT_JEANNE_DUPLANTIS_DIRA_DTO, lotSectionsJeanneDuplantisDIRADTO);
		
		objet1MemeInstance = objet1;
		
		objet2EqualsObj1 = new TeleversementDeLotSectionsHitDTO(
				TELEVERSEMENT_JEANNE_DUPLANTIS_DIRA_DTO, lotSectionsJeanneDuplantisDIRADTO);
		
		objet3EqualsObj1 = new TeleversementDeLotSectionsHitDTO(
				TELEVERSEMENT_JEANNE_DUPLANTIS_DIRA_DTO, lotSectionsJeanneDuplantisDIRADTO);
		
		objetNull1 = new TeleversementDeLotSectionsHitDTO(null, null);
		
		objetNull2 = new TeleversementDeLotSectionsHitDTO(null, null);
		
		objet1AvecNull = new TeleversementDeLotSectionsHitDTO(null, lotSectionsJeanneDuplantisDIRADTO);
		
		objet2EqualsObjet1AvecNull = new TeleversementDeLotSectionsHitDTO(null, lotSectionsJeanneDuplantisDIRADTO);
		
		objetDiff1 = new TeleversementDeLotSectionsHitDTO(TELEVERSEMENT_JEAN_DUPONT_DIRA_DTO, lotSectionsJeanDupontDIRADTO);
		
		objetDiff2 = new TeleversementDeLotSectionsHitDTO(TELEVERSEMENT_PAPY_GONZALES_DIRCE_DTO, lotSectionsPapyGonzalesDIRCEDTO);
		
		objetCompAvant = new TeleversementDeLotSectionsHitDTO(TELEVERSEMENT_PAPY_GONZALES_DIRCE_DTO, lotSectionsPapyGonzalesDIRCEDTO);
		
		objetCompApres = new TeleversementDeLotSectionsHitDTO(TELEVERSEMENT_JEAN_DUPONT_DIRA_DTO, lotSectionsJeanDupontDIRADTO);
		
		objetNullClone1 = objetNull2.clone();
		
		objetClone1 = objet1.clone();
		
	} // Fin de beforeClass()._____________________________________________
	
	
	
} // FIN DE LA CLASSE TeleversementDeLotSectionsHitDTOTest.------------------
