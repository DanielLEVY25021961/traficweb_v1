package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.sections;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesbundles.BundleConfigurationProjetManager;
import levy.daniel.application.model.metier.sections.impl.SectionHit;

/**
 * CLASSE SectionHitGestionnairePreferencesRG :<br/>
 * Classe UTILITAIRE (<i>final avec toutes les méthodes static</i>) chargée de :
 * <ul>
 * <li><b>gérer les <i>Booleans</i> de validation des RG comme des "préférences"</b> 
 * (paramétrées 1 fois par l'Administrateur puis stockées 
 * dans un fichier .properties) qui conservent la même valeur 
 * tant qu'elles sont inchangées.</li>
 * <li>permettre à l'ADMINISTRATEUR DE DONNEES de la MOA 
 * de <b>modifier ces Booleans</b>.</li>
 * <li><b>gérer les préférences relatives aux REGLES DE GESTION (RG) applicables 
 * à un OBJET METIER : {@link SectionHit}</b>.</li>
 * <li>fournir à toute l'application des <b>SINGLETONS</b> des 
 * <b>Booleans de validation par défaut 
 * (actuellement stockés dans un .properties) des Règles de Gestion (RG)</b>
 *  d'un OBJET METIER pour chaque attribut et chaque RG de l'attribut</li>
 * <li>gérer comme des préférences les <b>booleens 
 * qui activent ou non les contrôles des RG</b>.</li>
 * <li><b>créer un fichier .properties initial</b> avec des valeurs (Booleans) 
 * codées en dur dans la classe en cas de défaut de livraison du 
 * <code><b>ressources_externes/preferences/metier/sections/
 * OBJETMETIER_RG.properties</b></code></li>
 * </ul>
 * 
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * répertoire du projet, System.getProperty("user.dir"),<br/>
 * Properties, préférences, Preferences, <br/>
 * Template, template, lire dans fichier,<br/>
 * enregistrer des préférences dans un Properties, fichier properties, <br/>
 * commentaire dans un fichier properties, écrire commentaire, <br/>
 * lire dans un fichier properties, écrire dans un fichier properties, <br/>
 * créer une String à partir d'une liste de lignes, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 13 août 2019
 *
 */
public final class SectionHitGestionnairePreferencesRG {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe SectionHitGestionnairePreferencesRG".
	 */
	public static final String CLASSE_SECTIONHIT_GESTIONNAIRE_PREFS_RG 
		= "Classe SectionHitGestionnairePreferencesRG";

	//*****************************************************************/
	//**************************** SEPARATEURS ************************/
	//*****************************************************************/

	/**
	 * System.getProperty("file.separator")
	 */
	public static final String SEPARATEUR_FICHIER 
		= System.getProperty("file.separator");
		
	/**
	 * " - ".<br/>
	 */
	public static final String SEPARATEUR_MOINS_AERE = " - ";

	
	//*****************************************************************/
	//**************************** CHARSETS ***************************/
	//*****************************************************************/
	/**
	 * Charset.forName("UTF-8").<br/>
	 * Eight-bit Unicode (or UCS) Transformation Format.<br/> 
	 */
	public static final Charset CHARSET_UTF8 
		= Charset.forName("UTF-8");
	
	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/	
	/**
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE 
		= System.getProperty("line.separator");

	/**
	 * Saut de ligne JAVA
	 * '\n'.<br/>
	 */
	public static final char SAUT_LIGNE_JAVA = '\n';

	/**
	 * "&lt;br/&gt;".<br/>
	 */
	public static final String SAUT_LIGNE_HTML = "<br/>";
	
	/**
	 * "méthode lireStringsDansFile(File pFile, Charset pCharset)".<br/>
	 */
	public static final String METHODE_LIRE_STRINGS_DANS_FILE 
		= "méthode lireStringsDansFile(File pFile, Charset pCharset)";
		
	/**
	 * '='.<br/>
	 */
	public static final char EGAL = '=';
	
	// RGs ***************************************************
	/* 1 - numDepartement. ***************/
	/**
	 * clé de validerRGSectionHitNumDepartement dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numdepartement"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT 
		= "valider.SectionHit.numdepartement";
	
	/**
	 * validerRGSectionHitNumDepartement par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumDepartement 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitNumDepartementRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numdepartement.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01 
		= "valider.SectionHit.numdepartement.renseigne";
	
	/**
	 * validerRGSectionHitNumDepartementRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumDepartementRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitNumDepartementRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numdepartement.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02 
		= "valider.SectionHit.numdepartement.regex";
	
	/**
	 * validerRGSectionHitNumDepartementRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumDepartementRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR 
		= "true";
	
	/* 2 - numSection. ************/
	/**
	 * clé de validerRGSectionHitNumSection dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numsection"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMSECTION 
		= "valider.SectionHit.numsection";
	
	/**
	 * validerRGSectionHitNumSection par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumSection 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMSECTION_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitNumSectionRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numsection.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMSECTION_RENSEIGNE_01 
		= "valider.SectionHit.numsection.renseigne";
	
	/**
	 * validerRGSectionHitNumSectionRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumSectionRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMSECTION_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitNumSectionRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numsection.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMSECTION_REGEX_02 
		= "valider.SectionHit.numsection.regex";
	
	/**
	 * validerRGSectionHitNumSectionRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumSectionRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMSECTION_REGEX_02_EN_DUR 
		= "true";
	
	/* 3 - sens. *******************/
	/**
	 * clé de validerRGSectionHitSens dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.sens"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_SENS 
		= "valider.SectionHit.sens";
	
	/**
	 * validerRGSectionHitSens par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitSens 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_SENS_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitSensRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.sens.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_SENS_RENSEIGNE_01 
		= "valider.SectionHit.sens.renseigne";
	
	/**
	 * validerRGSectionHitSensRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitSensRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_SENS_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitSensRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.sens.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_SENS_REGEX_02 
		= "valider.SectionHit.sens.regex";
	
	/**
	 * validerRGSectionHitSensRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitSensRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_SENS_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitSensNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.sens.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_SENS_NOMENCLATURE_03 
		= "valider.SectionHit.sens.nomenclature";
	
	/**
	 * validerRGSectionHitSensNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitSensNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_SENS_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 4 - nature. *******************/
	/**
	 * clé de validerRGSectionHitNature dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.nature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NATURE 
		= "valider.SectionHit.nature";
	
	/**
	 * validerRGSectionHitNature par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNature 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NATURE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitNatureRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.nature.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NATURE_RENSEIGNE_01 
		= "valider.SectionHit.nature.renseigne";
	
	/**
	 * validerRGSectionHitNatureRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNatureRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NATURE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitNatureRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.nature.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NATURE_REGEX_02 
		= "valider.SectionHit.nature.regex";
	
	/**
	 * validerRGSectionHitNatureRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNatureRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NATURE_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitNatureNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.nature.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NATURE_NOMENCLATURE_03 
		= "valider.SectionHit.nature.nomenclature";
	
	/**
	 * validerRGSectionHitNatureNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNatureNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NATURE_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 5 - classe. ****************/
	/**
	 * clé de validerRGSectionHitClasse dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classe"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSE 
		= "valider.SectionHit.classe";
	
	/**
	 * validerRGSectionHitClasse par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasse 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitClasseRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classe.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSE_RENSEIGNE_01 
		= "valider.SectionHit.classe.renseigne";
	
	/**
	 * validerRGSectionHitClasseRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitClasseRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classe.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSE_REGEX_02 
		= "valider.SectionHit.classe.regex";
	
	/**
	 * validerRGSectionHitClasseRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSE_REGEX_02_EN_DUR 
		= "true";
	
	/* 6 - anneeTraitement. *******/
	/**
	 * clé de validerRGSectionHitAnneeTraitement dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.anneeTraitement"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT 
		= "valider.SectionHit.anneeTraitement";
	
	/**
	 * validerRGSectionHitAnneeTraitement par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAnneeTraitement 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitAnneeTraitementRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.anneeTraitement.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01 
		= "valider.SectionHit.anneeTraitement.renseigne";
	
	/**
	 * validerRGSectionHitAnneeTraitementRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAnneeTraitementRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitAnneeTraitementRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.anneeTraitement.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT_REGEX_02 
		= "valider.SectionHit.anneeTraitement.regex";
	
	/**
	 * validerRGSectionHitAnneeTraitementRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAnneeTraitementRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_REGEX_02_EN_DUR 
		= "true";	
	
	/* 7 - zoneLibre1. *******/
	/**
	 * clé de validerRGSectionHitZoneLibre1 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.zoneLibre1"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ZONELIBRE1 
		= "valider.SectionHit.zoneLibre1";
	
	/**
	 * validerRGSectionHitZoneLibre1 par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitZoneLibre1 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ZONELIBRE1_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitZoneLibre1Renseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.zoneLibre1.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01 
		= "valider.SectionHit.zoneLibre1.renseigne";
	
	/**
	 * validerRGSectionHitZoneLibre1Renseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitZoneLibre1Renseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitZoneLibre1Regex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.zoneLibre1.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ZONELIBRE1_REGEX_02 
		= "valider.SectionHit.zoneLibre1.regex";
	
	/**
	 * validerRGSectionHitZoneLibre1Regex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitZoneLibre1Regex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ZONELIBRE1_REGEX_02_EN_DUR 
		= "true";
	
	/* 8 - numRoute. *******/
	/**
	 * clé de validerRGSectionHitNumRoute dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numRoute"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMROUTE 
		= "valider.SectionHit.numRoute";
	
	/**
	 * validerRGSectionHitNumRoute par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumRoute 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMROUTE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitNumRouteRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numRoute.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMROUTE_RENSEIGNE_01 
		= "valider.SectionHit.numRoute.renseigne";
	
	/**
	 * validerRGSectionHitNumRouteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumRouteRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMROUTE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitNumRouteRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numRoute.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMROUTE_REGEX_02 
		= "valider.SectionHit.numRoute.regex";
	
	/**
	 * validerRGSectionHitNumRouteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumRouteRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMROUTE_REGEX_02_EN_DUR 
		= "true";
	
	/* 9 - indiceNumRoute. *******/
	/**
	 * clé de validerRGSectionHitIndiceNumRoute dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.indiceNumRoute"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_INDICENUMROUTE 
		= "valider.SectionHit.indiceNumRoute";
	
	/**
	 * validerRGSectionHitIndiceNumRoute par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitIndiceNumRoute 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitIndiceNumRouteRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.indiceNumRoute.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01 
		= "valider.SectionHit.indiceNumRoute.renseigne";
	
	/**
	 * validerRGSectionHitIndiceNumRouteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitIndiceNumRouteRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitIndiceNumRouteRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.indiceNumRoute.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_INDICENUMROUTE_REGEX_02 
		= "valider.SectionHit.indiceNumRoute.regex";
	
	/**
	 * validerRGSectionHitIndiceNumRouteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitIndiceNumRouteRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_REGEX_02_EN_DUR 
		= "true";
	
	/* 10 - indiceLettreRoute. *******/
	/**
	 * clé de validerRGSectionHitIndiceLettreRoute dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.indiceLettreRoute"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE 
		= "valider.SectionHit.indiceLettreRoute";
	
	/**
	 * validerRGSectionHitIndiceLettreRoute par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitIndiceLettreRoute 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitIndiceLettreRouteRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.indiceLettreRoute.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01 
		= "valider.SectionHit.indiceLettreRoute.renseigne";
	
	/**
	 * validerRGSectionHitIndiceLettreRouteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitIndiceLettreRouteRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitIndiceLettreRouteRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.indiceLettreRoute.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE_REGEX_02 
		= "valider.SectionHit.indiceLettreRoute.regex";
	
	/**
	 * validerRGSectionHitIndiceLettreRouteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitIndiceLettreRouteRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_REGEX_02_EN_DUR 
		= "true";
	
	/* 11 - categorieAdminRoute. *******/
	/**
	 * clé de validerRGSectionHitCategorieAdminRoute dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.categorieAdminRoute"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE 
		= "valider.SectionHit.categorieAdminRoute";
	
	/**
	 * validerRGSectionHitCategorieAdminRoute par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitCategorieAdminRoute 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitCategorieAdminRouteRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.categorieAdminRoute.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01 
		= "valider.SectionHit.categorieAdminRoute.renseigne";
	
	/**
	 * validerRGSectionHitCategorieAdminRouteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitCategorieAdminRouteRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitCategorieAdminRouteRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.categorieAdminRoute.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02 
		= "valider.SectionHit.categorieAdminRoute.regex";
	
	/**
	 * validerRGSectionHitCategorieAdminRouteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitCategorieAdminRouteRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitCategorieAdminRouteNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.categorieAdminRoute.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03 
		= "valider.SectionHit.categorieAdminRoute.nomenclature";
	
	/**
	 * validerRGSectionHitCategorieAdminRouteNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitCategorieAdminRouteNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 12 - typeComptage. *******/
	/**
	 * clé de validerRGSectionHitTypeComptage dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.typeComptage"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE 
		= "valider.SectionHit.typeComptage";
	
	/**
	 * validerRGSectionHitTypeComptage par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitTypeComptage 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitTypeComptageRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.typeComptage.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01 
		= "valider.SectionHit.typeComptage.renseigne";
	
	/**
	 * validerRGSectionHitTypeComptageRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitTypeComptageRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitTypeComptageRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.typeComptage.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_REGEX_02 
		= "valider.SectionHit.typeComptage.regex";
	
	/**
	 * validerRGSectionHitTypeComptageRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitTypeComptageRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitTypeComptageNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.typeComptage.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03 
		= "valider.SectionHit.typeComptage.nomenclature";
	
	/**
	 * validerRGSectionHitTypeComptageNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitTypeComptageNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 13 - classementRoute. *******/
	/**
	 * clé de validerRGSectionHitClassementRoute dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classementRoute"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE 
		= "valider.SectionHit.classementRoute";
	
	/**
	 * validerRGSectionHitClassementRoute par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClassementRoute 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitClassementRouteRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classementRoute.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01 
		= "valider.SectionHit.classementRoute.renseigne";
	
	/**
	 * validerRGSectionHitClassementRouteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClassementRouteRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitClassementRouteRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classementRoute.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_REGEX_02 
		= "valider.SectionHit.classementRoute.regex";
	
	/**
	 * validerRGSectionHitClassementRouteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClassementRouteRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitClassementRouteNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classementRoute.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03 
		= "valider.SectionHit.classementRoute.nomenclature";
	
	/**
	 * validerRGSectionHitClassementRouteNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClassementRouteNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 14 - classeLargeurChausseeU. *******/
	/**
	 * clé de validerRGSectionHitClasseLargeurChausseeU dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classeLargeurChausseeU"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU 
		= "valider.SectionHit.classeLargeurChausseeU";
	
	/**
	 * validerRGSectionHitClasseLargeurChausseeU par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseLargeurChausseeU 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitClasseLargeurChausseeURenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classeLargeurChausseeU.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01 
		= "valider.SectionHit.classeLargeurChausseeU.renseigne";
	
	/**
	 * validerRGSectionHitClasseLargeurChausseeURenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseLargeurChausseeURenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitClasseLargeurChausseeURegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classeLargeurChausseeU.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02 
		= "valider.SectionHit.classeLargeurChausseeU.regex";
	
	/**
	 * validerRGSectionHitClasseLargeurChausseeURegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseLargeurChausseeURegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitClasseLargeurChausseeUNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classeLargeurChausseeU.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03 
		= "valider.SectionHit.classeLargeurChausseeU.nomenclature";
	
	/**
	 * validerRGSectionHitClasseLargeurChausseeUNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseLargeurChausseeUNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 15 - classeLargeurChausseesS. *******/
	/**
	 * clé de validerRGSectionHitClasseLargeurChausseesS dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classeLargeurChausseesS"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS 
		= "valider.SectionHit.classeLargeurChausseesS";
	
	/**
	 * validerRGSectionHitClasseLargeurChausseesS par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseLargeurChausseesS 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitClasseLargeurChausseesSRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classeLargeurChausseesS.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01 
		= "valider.SectionHit.classeLargeurChausseesS.renseigne";
	
	/**
	 * validerRGSectionHitClasseLargeurChausseesSRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseLargeurChausseesSRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitClasseLargeurChausseesSRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classeLargeurChausseesS.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02 
		= "valider.SectionHit.classeLargeurChausseesS.regex";
	
	/**
	 * validerRGSectionHitClasseLargeurChausseesSRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseLargeurChausseesSRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitClasseLargeurChausseesSNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classeLargeurChausseesS.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03 
		= "valider.SectionHit.classeLargeurChausseesS.nomenclature";
	
	/**
	 * validerRGSectionHitClasseLargeurChausseesSNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseLargeurChausseesSNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 16 - typeReseau. *******/
	/**
	 * clé de validerRGSectionHitTypeReseau dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.typeReseau"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_TYPERESEAU 
		= "valider.SectionHit.typeReseau";
	
	/**
	 * validerRGSectionHitTypeReseau par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitTypeReseau 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_TYPERESEAU_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitTypeReseauRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.typeReseau.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_TYPERESEAU_RENSEIGNE_01 
		= "valider.SectionHit.typeReseau.renseigne";
	
	/**
	 * validerRGSectionHitTypeReseauRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitTypeReseauRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_TYPERESEAU_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitTypeReseauRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.typeReseau.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_TYPERESEAU_REGEX_02 
		= "valider.SectionHit.typeReseau.regex";
	
	/**
	 * validerRGSectionHitTypeReseauRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitTypeReseauRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_TYPERESEAU_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitTypeReseauNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.typeReseau.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03 
		= "valider.SectionHit.typeReseau.nomenclature";
	
	/**
	 * validerRGSectionHitTypeReseauNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitTypeReseauNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 17 - pRoupK. *******/
	/**
	 * clé de validerRGSectionHitPRoupK dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pRoupK"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PROUPK 
		= "valider.SectionHit.pRoupK";
	
	/**
	 * validerRGSectionHitPRoupK par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPRoupK 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PROUPK_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitPRoupKRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pRoupK.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PROUPK_RENSEIGNE_01 
		= "valider.SectionHit.pRoupK.renseigne";
	
	/**
	 * validerRGSectionHitPRoupKRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPRoupKRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PROUPK_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPRoupKRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pRoupK.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PROUPK_REGEX_02 
		= "valider.SectionHit.pRoupK.regex";
	
	/**
	 * validerRGSectionHitPRoupKRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPRoupKRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PROUPK_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPRoupKNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pRoupK.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PROUPK_NOMENCLATURE_03 
		= "valider.SectionHit.pRoupK.nomenclature";
	
	/**
	 * validerRGSectionHitPRoupKNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPRoupKNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PROUPK_NOMENCLATURE_03_EN_DUR 
		= "true";				
	
	/* 18 - lieuDitOrigine. *******/
	/**
	 * clé de validerRGSectionHitLieuDitOrigine dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.lieuDitOrigine"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE 
		= "valider.SectionHit.lieuDitOrigine";
	
	/**
	 * validerRGSectionHitLieuDitOrigine par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLieuDitOrigine 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitLieuDitOrigineRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.lieuDitOrigine.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01 
		= "valider.SectionHit.lieuDitOrigine.renseigne";
	
	/**
	 * validerRGSectionHitLieuDitOrigineRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLieuDitOrigineRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitLieuDitOrigineRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.lieuDitOrigine.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE_REGEX_02 
		= "valider.SectionHit.lieuDitOrigine.regex";
	
	/**
	 * validerRGSectionHitLieuDitOrigineRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLieuDitOrigineRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_REGEX_02_EN_DUR 
		= "true";
	
	/* 19 - prOrigine. *******/
	/**
	 * clé de validerRGSectionHitPrOrigine dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.prOrigine"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PRORIGINE 
		= "valider.SectionHit.prOrigine";
	
	/**
	 * validerRGSectionHitPrOrigine par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPrOrigine 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PRORIGINE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitPrOrigineRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.prOrigine.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PRORIGINE_RENSEIGNE_01 
		= "valider.SectionHit.prOrigine.renseigne";
	
	/**
	 * validerRGSectionHitPrOrigineRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPrOrigineRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PRORIGINE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPrOrigineRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.prOrigine.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PRORIGINE_REGEX_02 
		= "valider.SectionHit.prOrigine.regex";
	
	/**
	 * validerRGSectionHitPrOrigineRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPrOrigineRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PRORIGINE_REGEX_02_EN_DUR 
		= "true";
	
	/**
	* java.util.Properties encapsulant les préférences.<br/>
	*/
	private static Properties preferences = new Properties();
	
	/**
	* Path absolu vers le fichier properties contenant les preferences
	* <code><b>ressources_externes/preferences/metier/sections/
	* SectionHit_RG.properties</b></code>.<br/>
	*/
	private static Path pathAbsoluPreferencesProperties;
	
	/**
	* Chemin relatif (par rapport à ressources_externes) 
	* du fichier properties contenant les preferences
	* <code>SectionHit_RG.properties</code>.<br/>
	* "preferences/metier/sections/SectionHit_RG.properties"
	*/
	private static final String CHEMIN_RELATIF_PREFERENCES_PROPERTIES_STRING 
	= "preferences/metier/sections/SectionHit_RG.properties";
	
	/**
	* Modélisation Java du fichier properties contenant les preferences
	* <code>SectionHit_RG.properties</code>.<br/>
	*/
	private static File filePreferencesProperties;
	
	/**
	* commentaire à ajouter en haut du fichier properties 
	* contenant les preferences 
	* <code>SectionHit_RG.properties</code>.<br/>
	*/
	private static String commentaire;
	
	/**
	* Chemin relatif (par rapport à src/main/resources) 
	* du template contenant le commentataire à ajouter 
	* en haut du fichier properties contenant les preferences 
	* <code>SectionHit_RG.properties</code>.<br/>
	* "commentaires_properties/metier/sections/SectionHit_RG_properties_commentaires.txt"
	*/
	private static final String CHEMIN_RELATIF_TEMPLATE_COMMENTAIRE 
	= "commentaires_properties/metier/sections/SectionHit_RG_properties_commentaires.txt";

	// BOOLEANS *************************************
	/* 1 - numDepartement. ***************/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur le <i>numDepartement</i> de la SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numDepartement</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitNumDepartement;
	
	/**
	 * Boolean activant la RG-SectionHit-NumDepartement-01 : 
	 * "le numéro de département de la section HIT (colonnes [1-3] du HIT) doit être renseigné (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitNumDepartementRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-NumDepartement-02 : 
	 * "le numéro de département doit comporter exactement 3 chiffres 
	 * dans les colonnes [1-3] du HIT 
	 * ('030' pour 03, '300' pour 30, '972' pour 972)".<br/>
	 */
	private static Boolean validerRGSectionHitNumDepartementRegex02;
	
	/* 2 - numSection. *****************/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur le <i>numSection</i> de la SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numSection</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitNumSection;
	
	/**
	 * Boolean activant la RG-SectionHit-NumSection-01 : 
	 * "le numéro de section de la section HIT (colonnes [4-9] du HIT) 
	 * doit être renseigné (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitNumSectionRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-NumSection-02 : 
	 * "le numéro de section doit comporter exactement 6 chiffres 
	 * (Numéro de section sur 4 chiffres significatifs maxi complété 
	 * éventuellement avec des 0 à gauche et indice sur 2 chiffres 
	 * significatifs maxi complété éventuellement avec des 0 à gauche. 
	 * Exemples : section 26 indice 4 = 002604, section 1 indice 0 = 000100
	 * , section 162 indice 65 = 016265) - (colonnes [4-9] du HIT)".<br/>
	 */
	private static Boolean validerRGSectionHitNumSectionRegex02;
	
	/* 3 - sens. ******************/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur le <i>sens</i> de la SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>sens</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitSens;
	
	/**
	 * Boolean activant la RG-SectionHit-Sens-01 : 
	 * "le sens de la section HIT (colonne [10] du HIT) doit être renseigné (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitSensRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-Sens-02 : 
	 * "le sens de la section HIT (colonne [10] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitSensRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-Sens-03 :
	 * "le sens de la section HIT (colonne [10] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]".<br/>
	 */
	private static Boolean validerRGSectionHitSensNomenclature03;
	
	/* 4 - nature. *****************/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur le <i>nature</i> de la SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>nature</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitNature;
	
	/**
	 * Boolean activant la RG-SectionHit-Nature-01 : 
	 * "la nature de la section HIT (colonne [11] du HIT) doit être renseignée (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitNatureRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-Nature-02 : 
	 * "la nature de la section HIT (colonne [11] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitNatureRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-Nature-03 :
	 * "la nature de la section HIT (colonne [11] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]".<br/>
	 */
	private static Boolean validerRGSectionHitNatureNomenclature03;
	
	/* 5 - classe. *************/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur le <i>classe</i> de la SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>classe</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitClasse;
	
	/**
	 * Boolean activant la RG-SectionHit-Classe-01 : 
	 * "la classe de la section HIT (colonnes [12-13] du HIT) doit être renseignée".<br/>
	 */
	private static Boolean validerRGSectionHitClasseRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-Classe-02 : 
	 * "la classe de la section HIT (colonnes [12-13] du HIT) doit comporter exactement 2 chiffres ('00')".<br/>
	 */
	private static Boolean validerRGSectionHitClasseRegex02;
	
	/* 6 - anneeTraitement. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>anneeTraitement</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>anneeTraitement</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitAnneeTraitement;
	
	/**
	 * Boolean activant la RG-SectionHit-AnneeTraitement-01 : 
	 * "l'année de traitement de la section HIT (colonnes [14-15] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitAnneeTraitementRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-AnneeTraitement-02 : 
	 * "l'année de traitement de la section HIT (colonnes [14-15] du HIT) doit comporter exactement 2 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitAnneeTraitementRegex02;
	
	/* 7 - zoneLibre1. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>zoneLibre1</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>zoneLibre1</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitZoneLibre1;
	
	/**
	 * Boolean activant la RG-SectionHit-ZoneLibre1-01 : 
	 * "la zone libre de la section HIT (colonne [16] du HIT) doit être renseignée (1 espace)".<br/>
	 */
	private static Boolean validerRGSectionHitZoneLibre1Renseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-ZoneLibre1-02 : 
	 * "la zone libre de la section HIT (colonne [16] du HIT) doit comporter exactement 1 espace".<br/>
	 */
	private static Boolean validerRGSectionHitZoneLibre1Regex02;
	
	/* 8 - numRoute. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>numRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numRoute</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitNumRoute;
	
	/**
	 * Boolean activant la RG-SectionHit-NumRoute-01 : 
	 * "le numéro de la route de la section HIT (colonnes [17-20] du HIT) doit être renseigné (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitNumRouteRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-NumRoute-02 : 
	 * "le numéro de la route de la section HIT (colonnes [17-20] du HIT) doit comporter exactement 4 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitNumRouteRegex02;
	
	/* 9 - indiceNumRoute. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>indiceNumRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>indiceNumRoute</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitIndiceNumRoute;
	
	/**
	 * Boolean activant la RG-SectionHit-IndiceNumRoute-01 : 
	 * "l'indice numérique de la route de la section HIT (colonnes [21] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitIndiceNumRouteRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-IndiceNumRoute-02 : 
	 * "l'indice numérique de la route de la section HIT (colonnes [21] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitIndiceNumRouteRegex02;
	
	/* 10 - indiceLettreRoute. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>indiceLettreRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>indiceLettreRoute</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitIndiceLettreRoute;
	
	/**
	 * Boolean activant la RG-SectionHit-IndiceLettreRoute-01 : 
	 * "l'indice lettre de la route de la section HIT (colonnes [22] du HIT) doit être renseigné (ou espace si pas d'indice)".<br/>
	 */
	private static Boolean validerRGSectionHitIndiceLettreRouteRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-IndiceLettreRoute-02 : 
	 * "l'indice lettre de la route de la section HIT (colonnes [22] du HIT) doit comporter exactement 1 lettre (ou espace si pas d'indice)".<br/>
	 */
	private static Boolean validerRGSectionHitIndiceLettreRouteRegex02;
	
	/* 11 - categorieAdminRoute. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>categorieAdminRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>categorieAdminRoute</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitCategorieAdminRoute;
	
	/**
	 * Boolean activant la RG-SectionHit-CategorieAdminRoute-01 : 
	 * "la catégorie administrative de la route de la section HIT (colonnes [23] du HIT) doit être renseignée (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitCategorieAdminRouteRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-CategorieAdminRoute-02 : 
	 * "la catégorie administrative de la route de la section HIT (colonnes [23] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitCategorieAdminRouteRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-CategorieAdminRoute-03 :
	 * "la catégorie administrative de la route de la section HIT (colonne [23] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]".<br/>
	 */
	private static Boolean validerRGSectionHitCategorieAdminRouteNomenclature03;
	
	/* 12 - typeComptage. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>typeComptage</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>typeComptage</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitTypeComptage;
	
	/**
	 * Boolean activant la RG-SectionHit-TypeComptage-01 : 
	 * "le type de comptage de la section HIT (colonne [24] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitTypeComptageRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-TypeComptage-02 : 
	 * "le type de comptage de la section HIT (colonne [24] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitTypeComptageRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-TypeComptage-03 :
	 * "le type de comptage de la section HIT (colonne [24] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]".<br/>
	 */
	private static Boolean validerRGSectionHitTypeComptageNomenclature03;
	
	/* 13 - classementRoute. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>classementRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>classementRoute</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitClassementRoute;
	
	/**
	 * Boolean activant la RG-SectionHit-ClassementRoute-01 : 
	 * "le classement de la route de la section HIT (colonne [25] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitClassementRouteRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-ClassementRoute-02 : 
	 * "le classement de la route de la section HIT (colonne [25] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitClassementRouteRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-ClassementRoute-03 :
	 * "le classement de la route de la section HIT (colonne [25] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]".<br/>
	 */
	private static Boolean validerRGSectionHitClassementRouteNomenclature03;	
	
	/* 14 - classeLargeurChausseeU. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>classeLargeurChausseeU</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>classeLargeurChausseeU</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitClasseLargeurChausseeU;
	
	/**
	 * Boolean activant la RG-SectionHit-ClasseLargeurChausseeU-01 : 
	 * "la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit être renseignée (0 si sans objet)".<br/>
	 */
	private static Boolean validerRGSectionHitClasseLargeurChausseeURenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-ClasseLargeurChausseeU-02 : 
	 * "la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitClasseLargeurChausseeURegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-ClasseLargeurChausseeU-03 :
	 * "la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7, 8]".<br/>
	 */
	private static Boolean validerRGSectionHitClasseLargeurChausseeUNomenclature03;
	
	/* 15 - classeLargeurChausseesS. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>classeLargeurChausseesS</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>classeLargeurChausseesS</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitClasseLargeurChausseesS;
	
	/**
	 * Boolean activant la RG-SectionHit-ClasseLargeurChausseesS-01 : 
	 * "la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit être renseignée (0 si sans objet)".<br/>
	 */
	private static Boolean validerRGSectionHitClasseLargeurChausseesSRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-ClasseLargeurChausseesS-02 : 
	 * "la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitClasseLargeurChausseesSRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-ClasseLargeurChausseesS-03 :
	 * "la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7]".<br/>
	 */
	private static Boolean validerRGSectionHitClasseLargeurChausseesSNomenclature03;
	
	/* 16 - typeReseau. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>typeReseau</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>typeReseau</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitTypeReseau;
	
	/**
	 * Boolean activant la RG-SectionHit-TypeReseau-01 : 
	 * "le type de réseau de la section HIT (colonne [28] du HIT) doit être renseigné (0 si autre que autoroute)".<br/>
	 */
	private static Boolean validerRGSectionHitTypeReseauRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-TypeReseau-02 : 
	 * "le type de réseau de la section HIT (colonne [28] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitTypeReseauRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-TypeReseau-03 :
	 * "le type de réseau de la section HIT (colonne [28] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4]".<br/>
	 */
	private static Boolean validerRGSectionHitTypeReseauNomenclature03;
	
	/* 17 - pRoupK. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>pRoupK</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>pRoupK</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitPRoupK;
	
	/**
	 * Boolean activant la RG-SectionHit-PRoupK-01 : 
	 * "PR ou PK de la section HIT (colonne [29] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitPRoupKRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-PRoupK-02 : 
	 * "PR ou PK de la section HIT (colonne [29] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitPRoupKRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-PRoupK-03 :
	 * "PR ou PK de la section HIT (colonne [29] du HIT) doit respecter une nomenclature [1, 2]".<br/>
	 */
	private static Boolean validerRGSectionHitPRoupKNomenclature03;
	
	/* 18 - lieuDitOrigine. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>lieuDitOrigine</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>lieuDitOrigine</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitLieuDitOrigine;
	
	/**
	 * Boolean activant la RG-SectionHit-LieuDitOrigine-01 : 
	 * "le lieu-dit origine de la section HIT (colonnes [30-49] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitLieuDitOrigineRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-LieuDitOrigine-02 : 
	 * "le lieu-dit origine de la section HIT (colonnes [30-49] du HIT) doit comporter exactement 20 caractères".<br/>
	 */
	private static Boolean validerRGSectionHitLieuDitOrigineRegex02;	
	
	/* 19 - prOrigine. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>prOrigine</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>prOrigine</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitPrOrigine;
	
	/**
	 * Boolean activant la RG-SectionHit-PrOrigine-01 : 
	 * "le PR origine de la section HIT (colonnes [50-52] du HIT) doit être renseigné (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitPrOrigineRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-PrOrigine-02 : 
	 * "le PR origine de la section HIT (colonnes [50-52] du HIT) doit comporter exactement 3 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitPrOrigineRegex02;
	
		
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitGestionnairePreferencesRG.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation<br/>
	 */
	private SectionHitGestionnairePreferencesRG() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * <b>sauvegarde sur disque un fichier 
	 * SectionHit_RG.properties initial</b> alimenté par des 
	 * propriétés [clé-valeur] <b>écrites en dur</b> 
	 * dans la présente classe.<br/>
	 * <ul>
	 * <li>remplit le java.util.Properties <code>preferences</code> 
	 * avec des [clé-valeur] stockées en dur dans la classe.</li>
	 * <li>crée le fichier properties contenant les préférences 
	 * <code>filePreferencesProperties</code> VIDE 
	 * sur le disque si il n'existe pas.</li>
	 * <li>remplit le fichier <code>filePreferencesProperties</code> 
	 * (SectionHit_RG.properties) 
	 * avec le contenu de <code>preferences</code> 
	 * ([clé-valeur] stockées en dur dans la classe).</li>
	 * <li>Ecrit en UTF8 le Properties <code>preferences</code> dans 
	 * le File <code>filePreferencesProperties</code> 
	 * modélisant le fichier SectionHit_RG.properties en positionnant 
	 * le <code>commentaire</code> au dessus.</li>
	 * <li>Utilise <code>preferences.store(writer, commentaire);</code> 
	 * avec un try-with-resource.</li>
	 * <li>ré-écrit (écrase) tout le fichier à chaque appel.</li>
	 * <li>trace EX_TEC_INITIALISATION_07.</li>
	 * </ul>
	 * <p>
	 * <img src="../../../../../../../../../../../javadoc/images/apptechnic/preferences/methode_creerFichierPropertiesInitial_activites.png" 
	 * alt="diagramme d'activités de la méthode creerFichierPropertiesInitial()" />
	 * </p>
	 * 
	 * @throws Exception 
	 */
	private static void creerFichierPropertiesInitial() 
											throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* remplit le Properties Java <code>preferences</code> 
			 * avec des [clé-valeur] stockées en dur dans la classe. */
			ajouterPropertiesEnDur();
			
			/* crée le fichier properties contenant les préférences 
			 * filePreferencesProperties VIDE sur le disque 
			 * si il n'existe pas. */
			creerFichierPreferencesPropertiesVide();
			
			/* remplit le fichier filePreferencesProperties avec 
			 * preferences. */
			enregistrerPreferencesDansFichierProperties();
						
		} // Fin du bloc synchronized.__________________
		
	} // Fin de creerFichierPropertiesInitial().___________________________

	

	/**
	 * <b>Ajoute des propriétés initiales stockées en dur</b> 
	 * dans la classe au java.util.Properties <b>preferences</b>.<br/>
	 */
	private static void ajouterPropertiesEnDur() {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* 1 - numDepartement. **********/
			/* ajoute le validerRGSectionHitNumDepartement 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT
						, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumDepartementRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumDepartementRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02
						, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR);
			
			/* 2 - numSection. ***********/
			/* ajoute le validerRGSectionHitNumSection 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMSECTION
						, STRING_VALIDER_SECTIONHIT_NUMSECTION_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumSectionRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMSECTION_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_NUMSECTION_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumSectionRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMSECTION_REGEX_02
						, STRING_VALIDER_SECTIONHIT_NUMSECTION_REGEX_02_EN_DUR);
			
			/* 3 - sens. ***********/
			/* ajoute le validerRGSectionHitSens 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_SENS
						, STRING_VALIDER_SECTIONHIT_SENS_EN_DUR);
			
			/* ajoute le validerRGSectionHitSensRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_SENS_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_SENS_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitSensRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_SENS_REGEX_02
						, STRING_VALIDER_SECTIONHIT_SENS_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitSensNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_SENS_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_SENS_NOMENCLATURE_03_EN_DUR);
			
			/* 4 - nature. ***********/
			/* ajoute le validerRGSectionHitNature 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NATURE
						, STRING_VALIDER_SECTIONHIT_NATURE_EN_DUR);
			
			/* ajoute le validerRGSectionHitNatureRenseigne01 
			 * par défaut stockée en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NATURE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_NATURE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitNatureRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NATURE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_NATURE_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitNatureNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NATURE_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_NATURE_NOMENCLATURE_03_EN_DUR);
			
			/* 5 - classe. ***********/
			/* ajoute le validerRGSectionHitClasse 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSE
						, STRING_VALIDER_SECTIONHIT_CLASSE_EN_DUR);
			
			/* ajoute le validerRGSectionHitClasseRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_CLASSE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitClasseRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_CLASSE_REGEX_02_EN_DUR);
			
			/* 6 - anneeTraitement. *******/
			/* ajoute le validerRGSectionHitAnneeTraitement 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT
						, STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_EN_DUR);
			
			/* ajoute le validerRGSectionHitAnneeTraitementRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitAnneeTraitementRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT_REGEX_02
						, STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_REGEX_02_EN_DUR);
			
			/* 7 - zoneLibre1. *******/
			/* ajoute le validerRGSectionHitZoneLibre1 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ZONELIBRE1
						, STRING_VALIDER_SECTIONHIT_ZONELIBRE1_EN_DUR);
			
			/* ajoute le validerRGSectionHitZoneLibre1Renseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitZoneLibre1Regex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ZONELIBRE1_REGEX_02
						, STRING_VALIDER_SECTIONHIT_ZONELIBRE1_REGEX_02_EN_DUR);
			
			/* 8 - numRoute. *******/
			/* ajoute le validerRGSectionHitNumRoute 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMROUTE
						, STRING_VALIDER_SECTIONHIT_NUMROUTE_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumRouteRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMROUTE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_NUMROUTE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumRouteRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMROUTE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_NUMROUTE_REGEX_02_EN_DUR);
			
			/* 9 - indiceNumRoute. *******/
			/* ajoute le validerRGSectionHitIndiceNumRoute 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_INDICENUMROUTE
						, STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_EN_DUR);
			
			/* ajoute le validerRGSectionHitIndiceNumRouteRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitIndiceNumRouteRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_INDICENUMROUTE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_REGEX_02_EN_DUR);
			
			/* 10 - indiceLettreRoute. *******/
			/* ajoute le validerRGSectionHitIndiceLettreRoute 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE
						, STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_EN_DUR);
			
			/* ajoute le validerRGSectionHitIndiceLettreRouteRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitIndiceLettreRouteRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_REGEX_02_EN_DUR);
			
			/* 11 - categorieAdminRoute. *******/
			/* ajoute le validerRGSectionHitCategorieAdminRoute 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE
						, STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_EN_DUR);
			
			/* ajoute le validerRGSectionHitCategorieAdminRouteRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitCategorieAdminRouteRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitCategorieAdminRouteNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03_EN_DUR);
			
			/* 12 - typeComptage. *******/
			/* ajoute le validerRGSectionHitTypeComptage 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE
						, STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_EN_DUR);
			
			/* ajoute le validerRGSectionHitTypeComptageRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitTypeComptageRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitTypeComptageNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03_EN_DUR);
			
			/* 13 - classementRoute. *******/
			/* ajoute le validerRGSectionHitClassementRoute 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE
						, STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_EN_DUR);
			
			/* ajoute le validerRGSectionHitClassementRouteRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitClassementRouteRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitClassementRouteNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03_EN_DUR);
			
			/* 14 - classeLargeurChausseeU. *******/
			/* ajoute le validerRGSectionHitClasseLargeurChausseeU 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU
						, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_EN_DUR);
			
			/* ajoute le validerRGSectionHitClasseLargeurChausseeURenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitClasseLargeurChausseeURegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02
						, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitClasseLargeurChausseeUNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03_EN_DUR);
			
			/* 15 - classeLargeurChausseesS. *******/
			/* ajoute le validerRGSectionHitClasseLargeurChausseesS 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS
						, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_EN_DUR);
			
			/* ajoute le validerRGSectionHitClasseLargeurChausseesSRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitClasseLargeurChausseesSRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02
						, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitClasseLargeurChausseesSNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03_EN_DUR);
			
			/* 16 - typeReseau. *******/
			/* ajoute le validerRGSectionHitTypeReseau 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_TYPERESEAU
						, STRING_VALIDER_SECTIONHIT_TYPERESEAU_EN_DUR);
			
			/* ajoute le validerRGSectionHitTypeReseauRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_TYPERESEAU_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_TYPERESEAU_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitTypeReseauRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_TYPERESEAU_REGEX_02
						, STRING_VALIDER_SECTIONHIT_TYPERESEAU_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitTypeReseauNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03_EN_DUR);
			
			/* 17 - pRoupK. *******/
			/* ajoute le validerRGSectionHitPRoupK 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PROUPK
						, STRING_VALIDER_SECTIONHIT_PROUPK_EN_DUR);
			
			/* ajoute le validerRGSectionHitPRoupKRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PROUPK_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_PROUPK_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitPRoupKRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PROUPK_REGEX_02
						, STRING_VALIDER_SECTIONHIT_PROUPK_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitPRoupKNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PROUPK_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_PROUPK_NOMENCLATURE_03_EN_DUR);
			
			/* 18 - lieuDitOrigine. *******/
			/* ajoute le validerRGSectionHitLieuDitOrigine 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE
						, STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_EN_DUR);
			
			/* ajoute le validerRGSectionHitLieuDitOrigineRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitLieuDitOrigineRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_REGEX_02_EN_DUR);
			
			/* 19 - prOrigine. *******/
			/* ajoute le validerRGSectionHitPrOrigine 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PRORIGINE
						, STRING_VALIDER_SECTIONHIT_PRORIGINE_EN_DUR);
			
			/* ajoute le validerRGSectionHitPrOrigineRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PRORIGINE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_PRORIGINE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitPrOrigineRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PRORIGINE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_PRORIGINE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de ajouterProperties()._______________________________________
	

	
	/**
	 * <b>Instancie tous les attributs</b> relatifs 
	 * au fichier de preferences <b>si ils sont null</b>.<br/>
	 * <ul>
	 * <li>instancie <code>pathAbsoluPreferencesProperties</code> 
	 * si nécessaire.</li>
	 * <li>instancie <code>filePreferencesProperties</code> 
	 * si nécessaire.</li>
	 * <li>instancie <code>commentaire</code> si nécessaire.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void instancierAttributsStatiques() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* instancie pathAbsoluPreferencesProperties si nécessaire. */
			instancierPathAbsoluPreferencesProperties();
			
			/* instancie filePreferencesProperties si nécessaire. */
			instancierFilePreferencesProperties();
			
			/* instancie commentaire si nécesaire. */
			instancierCommentaire();
						
		} // Fin du bloc synchronized.__________________
		
	} // Fin de instancierAttributsFichierProperties().____________________
	

	
	/**
	 * instancie <code>pathAbsoluPreferencesProperties</code>.<br/>
	 * <ul>
	 * <li>ne fait rien si pathAbsoluPreferencesProperties 
	 * n'est pas null.</li>
	 * <li>obtient le path des ressources externes auprès 
	 * du ConfigurationApplicationManager.</li>
	 * <li>calcule le path du SectionHit_RG.properties 
	 * via un resolve par rapport au path des ressources externes.</li>
	 * <li>alimente <code>pathAbsoluPreferencesProperties</code></li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void instancierPathAbsoluPreferencesProperties() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* ne fait rien si pathAbsoluPreferencesProperties 
			 * n'est pas null. */
			if (pathAbsoluPreferencesProperties == null) {
				
				/* obtient le path du properties dans les 
				 * ressources externes auprès du 
				 * ConfigurationApplicationManager. */
				final Path pathRessourcesExternes 
				= Paths.get(ConfigurationApplicationManager
						.getPathRessourcesExternes());
				
				/* calcule le path du SectionHit_RG.properties 
				 * via un resolve par rapport au path 
				 * des ressources externes. */
				pathAbsoluPreferencesProperties 
				= pathRessourcesExternes
					.resolve(CHEMIN_RELATIF_PREFERENCES_PROPERTIES_STRING)
						.toAbsolutePath()
							.normalize();
			}

		} // Fin du bloc synchronized.__________________
			
	} // Fin de instancierPathAbsoluPreferencesProperties()._______________
	

	
	/**
	 * instancie <code>filePreferencesProperties</code>.<br/>
	 * <ul>
	 * <li>ne fait rien si filePreferencesProperties n'est pas null.</li>
	 * <li>instancie <code>pathAbsoluPreferencesProperties</code> 
	 * si nécessaire.</li>
	 * <li>alimente <code>filePreferencesProperties</code>.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void instancierFilePreferencesProperties() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* ne fait rien si filePreferencesProperties 
			 * n'est pas null. */
			if (filePreferencesProperties == null) {
				
				/* instancie pathAbsoluPreferencesProperties 
				 * si nécessaire. */
				instancierPathAbsoluPreferencesProperties();
				
				/* alimente filePreferencesProperties. */
				filePreferencesProperties 
					= pathAbsoluPreferencesProperties.toFile();
				
			}
						
		} // Fin du bloc synchronized.__________________		
		
	} // Fin de instancierFilePreferencesProperties()._____________________
	

	
	/**
	 * instancie <code>commentaire</code>.<br/>
	 * <ul>
	 * <li>ne fait rien si commentaire n'est pas null.</li>
	 * <li>lit dans un template le commentaire à ajouter 
	 * au début du fichier properties contenant les preferences.</li>
	 * <li>alimente <code>commentaire</code>.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void instancierCommentaire() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* ne fait rien si commentaire n'est pas null. */
			if (commentaire == null) {
				
				/* lit dans un template le commentaire à ajouter au début du 
				 * SectionHit_RG.properties et le stocke 
				 * dans commentaire.*/
				commentaire 
					= lireTemplateString(CHEMIN_RELATIF_TEMPLATE_COMMENTAIRE);
			}
		} // Fin du bloc synchronized.__________________	
		
	} // Fin de instancierCommentaire().___________________________________
	
	
	
	/**
	 * Crée sur disque dur l'arborescence des répertoires 
	 * parent de pFile si elle n'existe pas déjà.<br/>
	 * <ul>
	 * <li><code>Files.createDirectories(pathParent);</code></li>
	 * </ul>
	 * - ne fait rien si pFile == null.<br/>
	 * - ne fait rien si pFile est une racine (pas de parent).<br/>
	 * </br/>
	 *
	 * @param pFile : File : 
	 * fichier dont on veut créer l'arborescence sur disque dur.<br/>
	 * 
	 * @throws IOException
	 */
	private static void creerRepertoiresArbo(
			final File pFile) throws IOException {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* ne fait rien si pFile == null. */
			if (pFile == null) {
				return;
			}
			
			final Path pathFile = pFile.toPath();
			final Path pathParent = pathFile.getParent();
			
			/* ne fait rien si pFile est une racine (pas de parent). */
			if (pathParent != null) {
				Files.createDirectories(pathParent);
			}

		} // Fin du bloc synchronized.__________________
						
	} // Fin de creerRepertoiresArbo(...)._________________________________
	

	
	/**
	 * crée sur disque le fichier properties contenant les preferences
	 * <code>SectionHit_RG.properties</code> <b>VIDE</b> 
	 * <i>si il n'existe pas déjà</i>.<br/>
	 * <ul>
	 * <li>crée l'arboresence parente du fichier properties 
	 * si elle n'existe pas déjà.</li>
	 * <li>crée le fichier properties VIDE 
	 * (<code>Files.createFile(Path ...)</code>).</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void creerFichierPreferencesPropertiesVide() 
													throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* Crée sur le disque dur l'arborescence et le fichier 
			 * filePreferencesProperties VIDE si nécessaire.*/
			if (!filePreferencesProperties.exists()) {
				
				/* crée l'arboresence parente du fichier properties 
				 * si elle n'existe pas déjà. */
				creerRepertoiresArbo(filePreferencesProperties);
				
				/* crée le fichier properties VIDE. */
				Files.createFile(pathAbsoluPreferencesProperties);
			}
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de creerFichierPreferencesPropertiesVide().___________________
	
	
	
	/**
	 * <b>lit en UTF-8 le fichier properties contenant les preferences 
	 * <code>ressources_externes/preferences/metier/utilisateurs/
	 * SectionHit_RG.properties</b></code> 
	 * et alimente le <i>java.util.Properties</i> <b>preferences</b>.<br/>
	 * <ul>
	 * <li>décode le fichier .properties en UTF8 et le charge 
	 * dans le java.util.Properties <code>preferences</code>.</li>
	 * <li><code>preferences.load(BufferedReader);</code></li>
	 * <li>trace EX_TEC_PARAMETRAGE_02.</li>
	 * </ul>
	 * @throws Exception 
	 */
	private static void lireFichierPreferencesProperties() 
												throws Exception {

		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* try-with-resource qui se charge du close(). */
			try (Reader reader = Files.newBufferedReader(
					pathAbsoluPreferencesProperties, CHARSET_UTF8)) {
				
				/* décode le fichier .properties en UTF8 
				 * et le charge dans le Properties preferences. */
				preferences.load(reader);
		
			}

		} // Fin du bloc synchronized.__________________
				
	} // Fin de lireFichierPreferences().__________________________________


	
	/**
	 * <b>Enregistre en UTF8</b> le <i>java.util.Properties</i> 
	 * preferences dans le <i>fichier</i> properties 
	 * contenant les preferences
	 * <code><b>ressources_externes/preferences/metier/utilisateurs/
	 * SectionHit_RG.properties</b></code>.<br/>
	 * <ul>
	 * <li>enregistre le <i>java.util.Properties</i> <b>preferences</b> 
	 * sur disque dur dans le <i>fichier</i> 
	 * .properties correspondant.</li>
	 * <li>ajoute le commentaire au début de preferences.properties.</li>
	 * <li>Prise en compte (stockage) 
	 * d'une modification d'une Property.</li>
	 * <li><code>preferences.store(writer, null);</code></li>
	 * <li>trace EX_FONCT_MEMORISATION_05.</li>
	 * <li>trace EX_TEC_MEMORISATION_06.</li>
	 * </ul>
	 * - ne fait rien si le fichier properties n'existe pas.<br/>
	 * - ne fait rien si preferences est vide.<br/>
	 * <br/>
	 * 
	 * @throws Exception 
	 */
	private static void enregistrerPreferencesDansFichierProperties() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* ne fait rien si le fichier properties n'existe pas. */
			if (filePreferencesProperties == null 
					|| !filePreferencesProperties.exists()) {
				return;
			}
			
			/* ne fait rien si preferences est vide. */
			if (preferences.isEmpty()) {
				return;
			}
			
			/* try-with-resource qui se charge du close(). */
			try (Writer writer = Files.newBufferedWriter(
					pathAbsoluPreferencesProperties, CHARSET_UTF8)) {
				
				/* enregistre le Properties preferences sur disque dur 
				 * dans le fichier .properties correspondant. */
				preferences.store(writer, commentaire);
				
			}

		} // Fin du bloc synchronized.__________________
		
	} // Fin de enregistrerFichierPreferences().___________________________
	

	
	/**
	 * <b>Lit un template situé à 
	 * <code>cheminAbsoluMainResources/pCheminRelatifTemplate</code> 
	 * et retourne une String unique 
	 * incorporant les sauts de lignes</b>.
	 * <ul>
	 * <li>lit le fichier template avec le Charset UTF8.</li>
	 * <li>utilise la méthode lireStringsDansFile(
	 * templateFile, CHARSET_UTF8).</li>
	 * <li><b>Ne fait aucune substitution de variables</b>. 
	 * Lit le template et le retourne sous forme 
	 * de String.</li>
	 * <li>incorpore dans la String résultat les 
	 * sauts de ligne de la plateforme.</li>
	 * </ul>
	 * - retourne null si pCheminRelatifTemplate est blank.<br/>
	 * - retourne null si le fichier template n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pCheminRelatifTemplate : String : 
	 * chemin relatif du template à lire par rapport à 
	 * cheminAbsoluMainResources (src/main/resources).<br/>
	 * 
	 * @return String : 
	 * template sous forme de String unique 
	 * incorporant les sauts de lignes.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String lireTemplateString(
			final String pCheminRelatifTemplate) 
									throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* retourne null si pCheminRelatifTemplate est blank. */
			if (StringUtils.isBlank(pCheminRelatifTemplate)) {
				return null;
			}
			
			final List<String> templateListe 
				= lireTemplate(pCheminRelatifTemplate);
			
			final String resultat 
				= creerStringAPartirDeListe(templateListe);
			
			return resultat;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de lireTemplateString(...).___________________________________

	
	
	/**
	 * <b>Lit un template situé à 
	 * <code>cheminAbsoluMainResources/pCheminRelatifTemplate</code> 
	 * et retourne une liste de lignes</b>.
	 * <ul>
	 * <li>lit le fichier template avec le Charset UTF8.</li>
	 * <li>utilise la méthode lireStringsDansFile(
	 * templateFile, CHARSET_UTF8).</li>
	 * <li><b>Ne fait aucune substitution de variables</b>. 
	 * Lit le template et le retourne sous forme 
	 * de List&lt;String&gt;.</li>
	 * </ul>
	 * - retourne null si pCheminRelatifTemplate est blank.<br/>
	 * - retourne null si le fichier template n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pCheminRelatifTemplate : String : 
	 * chemin relatif du template à lire par rapport à 
	 * cheminAbsoluMainResources (src/main/resources).<br/>
	 * 
	 * @return List&lt;String&gt; : 
	 * template sous forme de liste de lignes.<br/>
	 * 
	 * @throws Exception 
	 */
	private static List<String> lireTemplate(
			final String pCheminRelatifTemplate) 
									throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* retourne null si pCheminRelatifTemplate est blank. */
			if (StringUtils.isBlank(pCheminRelatifTemplate)) {
				return null;
			}
			
			final String cheminAbsoluTemplate 
				= BundleConfigurationProjetManager.getRacineMainResources() 
					+ SEPARATEUR_FICHIER 
						+ pCheminRelatifTemplate;
			
			final File templateFile = new File(cheminAbsoluTemplate);
			
			/* retourne null si le fichier template n'existe pas. */
			if (!templateFile.exists()) {
				return null;
			}
			
			/* utilise la méthode 
			 * lireStringsDansFile(templateFile, CHARSET_UTF8). */
			final List<String> templateListe 
				= lireStringsDansFile(templateFile, CHARSET_UTF8);
			
			return templateListe;

		} // Fin du bloc synchronized.__________________
		
	} // Fin de lireTemplate(...)._________________________________________
	
	
	
	/**
	 * <b>Lit le contenu d'un fichier texte avec pCharset 
	 * et retourne une Liste de lignes</b>. 
	 * <ul>
	 * <li><b>Lit le contenu</b> d'un fichier texte 
	 * (fichier simple contenant du texte) pFile.</li>
	 * <li>Décode le contenu d'un fichier texte 
	 * (fichier simple contenant du texte) pFile 
	 * avec le Charset pCharset</li>
	 * <li><b>Retourne la liste des lignes</b> 
	 * du fichier simple texte pFile 
	 * lues avec le Charset pCharset.</li>
	 * <ul>
	 * <li>Utilise automatiquement le CHARSET_UTF8 
	 * si pCharset est null.</li>
	 * </ul>
	 * </ul>
	 * - Retourne null si pFile est null.<br/>
	 * - Retourne null si pFile n'existe pas.<br/>
	 * - Retourne null si pFile est un répertoire.<br/>
	 * - Retourne null en cas d'Exception loggée 
	 * (MalformedInputException, ...).<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier simple textuel à lire.<br/>
	 * @param pCharset : Charset : le Charset à utiliser pour 
	 * lire le fichier pFile.<br/>
	 * 
	 * @return : List&lt;String&gt; : Liste des lignes lues.<br/>
	 * 
	 * @throws Exception en cas d'Exception loggée 
	 * (IOException, MalformedInputException, ...).<br/>
	 */
	private static List<String> lireStringsDansFile(
			final File pFile
				, final Charset pCharset) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* Retourne null si pFile est null. */
			if (pFile == null) {
				return null;
			}
			
			/* Retourne null si pFile n'existe pas. */
			if (!pFile.exists()) {
				return null;
			}
			
			/* Retourne null si pFile est un répertoire. */
			if (pFile.isDirectory()) {
				return null;
			}
			
			/* Utilise automatiquement le CHARSET_UTF8 si pCharset est null. */
			Charset charset = null;
			
			if (pCharset == null) {
				charset = CHARSET_UTF8;
			}
			else {
				charset = pCharset;
			}
			
			/* Récupère le Path de pFile. */
			final Path pathFichier = pFile.toPath();
			
			try {
				
				// *****************************************************
				/* Retourne la liste des lignes lues avec le charset. */
				return Files.readAllLines(pathFichier, charset);
				
			} 
			
			catch (MalformedInputException malformedInputException) {
				
				final String message 
				=  "Impossible de lire le contenu du fichier '" 
				+ pFile.getName() 
				+ "' avec le Charset " 
				+ charset.displayName(Locale.getDefault()) 
				+ " à cause d'un caractère qui ne peut être "
				+ "écrit dans ce Charset (MalformedInputException)";
				
				/* LOG de niveau Error. */
				loggerError(CLASSE_SECTIONHIT_GESTIONNAIRE_PREFS_RG
						, METHODE_LIRE_STRINGS_DANS_FILE 
						+ SEPARATEUR_MOINS_AERE 
						+ message
						, malformedInputException);
				
				/* retourne null en cas d'Exception loggée 
				 * (IOException, MalformedInputException, ...). */
				return null;
		
			}
			
			catch (IOException ioe) {
				
				/* LOG de niveau Error. */
				loggerError(CLASSE_SECTIONHIT_GESTIONNAIRE_PREFS_RG
						, METHODE_LIRE_STRINGS_DANS_FILE
						, ioe);
				
				final String message 
				= CLASSE_SECTIONHIT_GESTIONNAIRE_PREFS_RG 
				+ SEPARATEUR_MOINS_AERE 
				+ METHODE_LIRE_STRINGS_DANS_FILE 
				+ SEPARATEUR_MOINS_AERE 
				+ "Impossible de lire le contenu du fichier '" 
				+ pFile.getName() 
				+ "' avec le Charset " 
				+ charset.displayName(Locale.getDefault());
				
				/* jette une Exception en cas d'Exception loggée 
				 * (IOException, MalformedInputException, ...). */
				throw new Exception(message, ioe);
			
			}
			
		} // Fin du bloc synchronized.__________________
			
	} // Fin de lireStringsDansFile(...).__________________________________
	

	
	/**
	 * <b>Crée une String à partir d'une liste de Strings</b>.
	 * <ul>
	 * <li>ajoute un saut de ligne de la plateforme 
	 * NEWLINE à chaque ligne.</li>
	 * </ul>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;String&gt;.<br/>
	 * 
	 * @return : String.<br/>
	 */
	private static String creerStringAPartirDeListe(
			final List<String> pList) {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* retourne null si pList == null. */
			if (pList == null) {
				return null;
			}
			
			final StringBuilder stb = new StringBuilder();
			
			for (final String ligne : pList) {
				
				stb.append(ligne);
				
				/* ajoute un saut de ligne de la plateforme 
				 * NEWLINE à chaque ligne. */
				stb.append(NEWLINE);
			}
			
			return stb.toString();

		} // Fin du bloc synchronized.__________________
		
	} // Fin de creerStringAPartirDeListe(...).____________________________
	
	
	
	/**
	 * method loggerInfo(
	 * String pClasse
	 * , String pMethode
	 * , String pMessage) :<br/>
	 * <ul>
	 * <li>Crée un message de niveau INFO dans le LOG.</li>
	 * </ul>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pMessage : String : Message particulier de la méthode.<br/>
	 */
	private static void loggerInfo(
			final String pClasse
				, final String pMethode
					, final String pMessage) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null || pMessage == null) {
			return;
		}
		
		/* LOG de niveau INFO. */			
		if (LOG.isInfoEnabled()) {
			
			final String message 
			= pClasse 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ pMessage;
			
			LOG.info(message);
		}
		
	} // Fin de la classe loggerInfo(...)._________________________________
	
	
	
	/**
	 * method loggerInfo(
	 * String pClasse
	 * , String pMethode
	 * , String pMessage
	 * , String pComplement) :<br/>
	 * <ul>
	 * <li>Créée un message de niveau INFO dans le LOG.</li>
	 * </ul>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pMessage : String : Message particulier de la méthode.<br/>
	 * @param pComplement : String : Complément au message de la méthode.<br/>
	 */
	private static void loggerInfo(
			final String pClasse
				, final String pMethode
					, final String pMessage
						, final String pComplement) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null 
				|| pMessage == null || pComplement == null) {
			return;
		}
		
		/* LOG de niveau INFO. */			
		if (LOG.isInfoEnabled()) {
			
			final String message 
			= pClasse 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ pMessage
			+ pComplement;
			
			LOG.info(message);
		}
		
	} // Fin de loggerInfo(...).___________________________________________
	
	
	
	/**
	 * method loggerError(
	 * String pClasse
	 * , String pMethode
	 * , Exception pException) :<br/>
	 * <ul>
	 * <li>Crée un message de niveau ERROR dans le LOG.</li>
	 * </ul>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pException : Exception : Exception transmise 
	 * par la méthode appelante.<br/>
	 */
	private static void loggerError(
			final String pClasse
				, final String pMethode
					, final Exception pException) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null || pException == null) {
			return;
		}
		
		/* LOG de niveau ERROR. */			
		if (LOG.isErrorEnabled()) {
			
			final String message 
			= pClasse 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE 
			+ pException.getMessage();
			
			LOG.error(message, pException);
			
		}
		
	} // Fin de loggerError(...).__________________________________________


	
	/**
	 * Méthod générique permettant de factoriser 
	 * les Getters des attributs.<br/>
	 * retourne la valeur du Boolean pAttribut 
	 * dans le fichier properties.<br/>
	 * <ul>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li><b>alimente l'attribut pAttribut avec sa valeur 
	 * dans le java.util.Properties <code>preferences</code>.</b></li>
	 * <ul>
	 * <li>nettoie la valeur lue dans le properties avec un trim().</li>
	 * <li>parse la valeur nettoyée lue dans le properties 
	 * et l'affecte à pAttribut.</li>
	 * <li>parse la valeur en dur si problème 
	 * et l'affecte à pAttribut.</li>
	 * </ul>
	 * <li><b>retourne la valeur de l'attribut 
	 * dans le fichier properties</b>.</li>
	 * </ul>
	 *
	 * @param pAttribut : Boolean : 
	 * un attribut de la classe (SINGLETON) comme 
	 * <code>validerRGSectionHitNumDepartement</code>
	 * @param pFournirKey : String : 
	 * clé de l'attribut Boolean pAttribut dans le fichier properties.
	 * @param pValeurEnDur : String : 
	 * valeur initiale stockée en dur dans la classe pour pAttribut.
	 * 
	 * @return Boolean : 
	 * l'attribut Boolean passé en paramètre tel qu'il est stocké 
	 * dans le fichier properties.<br/>
	 * 
	 * @throws Exception
	 */
	private static Boolean fournirAttribut(
			Boolean pAttribut
				, final String pFournirKey
					, final String pValeurEnDur) 
									throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/*  alimente le java.util.Properties preferences. */
			alimenterPreferences();
			
			/* alimente l'attribut pAttribut avec sa valeur 
			 * dans le fichier properties. */
			if (pAttribut == null) {
				
				/* lecture dans le properties. */
				final String valeurStringSale 
					= preferences
						.getProperty(pFournirKey);
				
				String valeurString = null;
				
				if (!StringUtils.isBlank(valeurStringSale)) {
					
					/* nettoie la valeur lue dans le properties 
					 * avec un trim(). */
					valeurString 
						= valeurStringSale.trim();
					
					try {
						
						/* parse la valeur nettoyée lue dans 
						 * le properties et l'affecte à pAttribut. */
						pAttribut 
							= Boolean.parseBoolean(valeurString);
						
					} catch (Exception e) {
						
						/* parse la valeur en dur si problème 
						 * et l'affecte à pAttribut. */
						pAttribut 
							= Boolean.parseBoolean(pValeurEnDur);
						
					}
					
				}
				else {
					
					/* parse la valeur en dur si problème 
					 * et l'affecte à pAttribut. */
					pAttribut 
						= Boolean.parseBoolean(pValeurEnDur);
				}
			}
			
			/* retourne la valeur de l'attribut dans 
			 * le fichier properties. */
			return pAttribut;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirAttribut(...).______________________________________
	

	
	/**
	 * Méthod générique permettant de factoriser 
	 * les Setters des attributs.<br/>
	 * change la valeur du Boolean pAttribut en pValue
	 * et l'écrit sur disque dans le fichier properties.<br/>
	 * <ul>
	 * <li>ne fait rien si le paramètre pValue est null
	 * ou ne modifie pas la valeur existante de pAttribut.</li>
	 * <li>affecte la nouvelle valeur pValue à l'attribut pAttribut.</li>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li>modifie le java.util.Properties <code>preferences</code></b> 
	 * avec la nouvelle valeur pValue passée en paramètre</li>
	 * <li>ré-écrit entièrement le fichier properties mis à jour 
	 * avec les nouvelles valeurs dans le java.util.Properties 
	 * <code>preferences</code>.</li>
	 * </ul>
	 *
	 * @param pValue : Boolean : 
	 * nouvelle valeur à passer à pAttribut et à stocker 
	 * dans le fichier properties de preferences.
	 * @param pAttribut : Boolean : 
	 * un attribut de la classe (SINGLETON) comme 
	 * <code>validerRGSectionHitNumDepartement</code> 
	 * @param pFournirKey : String : 
	 * clé de l'attribut Boolean pAttribut dans le fichier properties.
	 * 
	 * @throws Exception
	 */
	private static void setterAttribut(
			final Boolean pValue
				, Boolean pAttribut
					, final String pFournirKey) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* ne fait rien si le paramètre pValue est null
			 * ou ne modifie pas la valeur existante de pAttribut. */
			if (pValue != null 
					&& !pValue
						.equals(pAttribut)) {
				
				/* affecte la nouvelle valeur pValue à 
				 * l'attribut pAttribut. */
				pAttribut = pValue;
				
				final String valeurString 
					= pAttribut.toString();
				
				/* alimente le java.util.Properties preferences. */
				alimenterPreferences();
				
				/* modifie le java.util.Properties preferences 
				 * avec la nouvelle valeur pValue passée en paramètre. */
				creerOuModifierProperty(
						pFournirKey
							, valeurString);
				
				/* ré-écrit entièrement le fichier properties mis à jour 
				 * avec les nouvelles valeurs dans le 
				 * java.util.Properties preferences. */
				enregistrerPreferencesDansFichierProperties();

			}

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setterAttribut(...)._______________________________________


	
	/**
	 * alimente le java.util.Properties <code>preferences</code>.<br/>
	 * <ul>
	 * <li>instancie tous les attributs statiques si nécessaire.</li>
	 * <li>crée le fichier properties si il n'existe pas 
	 * (la première fois).</li>
	 * <li>lit le contenu du fichier properties si il existe.</li>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * </ul>
	 * <p>
	 * <img src="../../../../../../../../../../../javadoc/images/apptechnic/preferences/methode_alimenterPreferences_activites.png" 
	 * alt="diagramme d'activités de la méthode alimenterPreferences()" />
	 * </p>
	 * 
	 * @throws Exception
	 */
	private static void alimenterPreferences() throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* instancie tous les attributs statiques si nécessaire. */
			instancierAttributsStatiques();
			
			/* crée le fichier properties si il n'existe pas 
			 * (la première fois). */
			if (filePreferencesProperties == null 
					|| !filePreferencesProperties.exists()) {
				creerFichierPropertiesInitial();
			} else {
				
				/* lit le contenu du fichier properties si il existe. */
				lireFichierPreferencesProperties();
			}
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de alimenterPreferences().____________________________________
	

	
	/**
	 * <b>Crée ou met à jour une Property</b> dans 
	 * le <i>Properties</i> <b>preferences</b>.<br/>
	 * <ul>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li>Crée ou maj dans le java.util.Properties <b>preferences</b> 
	 * <i>sans enregistrer la modification sur le disque dur</i>.</li>
	 * <li><code>preferences.setProperty(pKey, pValue);</code></li>
	 * <li>trace EX_FONCT_PARAMETRAGE_03.</li>
	 * </ul>
	 * - retourne false si pKey == null.<br/>
	 * - retourne false si pValue == null.<br/>
	 * <br/>
	 *
	 * @param pKey : String : Clé.<br/>
	 * @param pValue : String : Valeur.<br/>
	 * 
	 * @return : boolean : true si la property a été créée.<br/>
	 * 
	 * @throws Exception 
	 */
	private static boolean creerOuModifierProperty(
			final String pKey
				, final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/*  alimente le java.util.Properties preferences. */
			alimenterPreferences();
			
			/* retourne false si pKey == null. */
			if (pKey == null) {
				return false;
			}
			
			/* retourne false si pValue == null. */
			if (pValue == null) {
				return false;
			}
			
			/* crée ou met à jour la Property dans preferences. */
			preferences.setProperty(pKey, pValue);
			
			return true;
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de creerOuModifierProperty(...).______________________________

	
	
	/**
	 * <b>Retire une Property</b> dans 
	 * le <i>Properties</i> <b>preferences</b>.<br/>
	 * <ul>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li>retire dans l'objet Properties <b>preferences</b> 
	 * <i>sans enregistrer la modification sur le disque dur 
	 * (.properties)</i>.</li>
	 * <li><code>preferences.remove(pKey);</code>.</li>
	 * </ul>
	 * - retourne false si pKey == null.<br/>
	 * <br/>
	 *
	 * @param pKey : String : Clé.<br/>
	 * 
	 * @return : boolean : true si la property a été retirée.<br/>
	 * 
	 * @throws Exception 
	 */
	private static boolean retirerProperty(
			final String pKey) 
					throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/*  alimente le java.util.Properties preferences. */
			alimenterPreferences();
			
			/* retourne false si pKey == null. */
			if (pKey == null) {
				return false;
			}
			
			/* retire la Property de preferences. */
			preferences.remove(pKey);
			
			return true;
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de retirerProperty(...).______________________________________
	

	
	/**
	 * vide le <i>Properties</i> <b>preferences</b>.<br/>
	 * <ul>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li>vide l'objet <i>Properties</i> <b>preferences</b> 
	 * sans vider le <i>fichier</i> .properties correspondant 
	 * sur le disque dur.</li>
	 * <li><code>preferences.remove(cle);</code>.</li>
	 * </ul>
	 * - retourne false si l'ensemble des clés du 
	 * Properties preferences est null.<br/>
	 * <br/>
	 *
	 * @return : boolean : true si preferences a été vidée.<br/>
	 * 
	 * @throws Exception 
	 */
	private static boolean viderPreferences() throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/*  alimente le java.util.Properties preferences. */
			alimenterPreferences();
				
			final Set<String> clesSet 
				= preferences.stringPropertyNames();
			
			/* retourne false si l'ensemble des clés 
			 * du Properties preferences est null. */
			if (clesSet == null) {
				return false;
			}
			
			/* vidage du Properties preferences. */
			for (final String cle : clesSet) {
				preferences.remove(cle);
			}
			
			return true;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de viderPreferences().________________________________________



	/**
	 * Getter du java.util.Properties encapsulant les préférences.<br/>
	 * SINGLETON.<br/>
	 * <ul>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li>retourne le java.util.Properties <code>preferences</code>.</li>
	 * <li>trace EX_FONCT_PARAMETRAGE_01</li>
	 * </ul>
	 *
	 * @return preferences : Properties.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Properties getPreferences() throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/*  alimente le java.util.Properties preferences. */
			alimenterPreferences();
			
			return preferences;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de getPreferences().__________________________________________
	
	
	
	/**
	 * fournit une String pour l'affichage de preferences.properties.<br/>
	 * <ul>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li>affiche le contenu de preferences</li>
	 * <li>trace EX_FONCT_PARAMETRAGE_01</li>
	 * <li>trie un Set&lt;String&gt;.</li>
	 * </ul>
	 *
	 * @return : String.<br/>
	 * 
	 * @throws Exception
	 */
	public static String afficherPreferences() throws Exception {

		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/*  alimente le java.util.Properties preferences. */
			alimenterPreferences();
			
			final StringBuffer stb = new StringBuffer();
			
			/* trie un Set<String>. */
			final TreeSet<String> setTrie 
				= new TreeSet<String>(preferences.stringPropertyNames());
			
			for (final String key : setTrie) {
				stb.append(key);
				stb.append(EGAL);
				stb.append(preferences.getProperty(key));
				stb.append(SAUT_LIGNE_JAVA);
			}
			
			return stb.toString();

		} // Fin du bloc synchronized.__________________
		
	} // Fin de afficherPreferences()._____________________________________


	
	/**
	 * Getter du Path absolu vers le fichier properties 
	 * contenant les preferences 
	 * <code>SectionHit_RG.properties</code>.<br/>
	 * SINGLETON.<br/>
	 *
	 * @return pathAbsoluPreferencesProperties : Path.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Path getPathAbsoluPreferencesProperties() 
											throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			if (pathAbsoluPreferencesProperties == null) {
				instancierPathAbsoluPreferencesProperties();
			}
			
			return pathAbsoluPreferencesProperties;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de getPathAbsoluPreferencesProperties().______________________


		
	/**
	 * Getter du Chemin relatif (par rapport à ressources_externes) 
	 * du fichier properties contenant les preferences
	 * <code>SectionHit_RG.properties</code>.<br/>
	 * "preferences/metier/utilisateurs/SectionHit_RG.properties"
	 *
	 * @return CHEMIN_RELATIF_PREFERENCES_PROPERTIES_STRING : String.<br/>
	 */
	public static String getCheminRelatifPreferencesPropertiesString() {
		return CHEMIN_RELATIF_PREFERENCES_PROPERTIES_STRING;
	} // Fin de getCheminRelatifPreferencesPropertiesString()._____________



	/**
	 * Getter de la  Modélisation Java du fichier properties 
	 * contenant les preferences 
	 * <code>SectionHit_RG.properties</code>.<br/>
	 * SINGLETON.<br/>
	 *
	 * @return filePreferencesProperties : File.<br/>
	 * 
	 * @throws Exception 
	 */
	public static File getFilePreferencesProperties() throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			if (filePreferencesProperties == null) {				
				instancierFilePreferencesProperties();
			}
			
			return filePreferencesProperties;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de getFilePreferencesProperties().____________________________
	
	
	
	/**
	 * Getter du commentaire à ajouter en haut du fichier properties 
	 * contenant les preferences 
	 * <code>SectionHit_RG.properties</code>.<br/>
	 * SINGLETON.<br/>
	 *
	 * @return commentaire : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getCommentaire() throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			if (commentaire == null) {
				instancierCommentaire();
			}
			
			return commentaire;
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de getCommentaire().__________________________________________
	
	
	
	/**
	* Setter du commentaire à ajouter en haut du fichier properties 
	* contenant les preferences 
	* <code>SectionHit_RG.properties</code>.<br/>
	*
	* @param pCommentaire : String : 
	* valeur à passer à commentaire.<br/>
	*/
	public static void setCommentaire(
			final String pCommentaire) {
		commentaire = pCommentaire;
	} // Fin de setCommentaire(...)._______________________________________
	

			
	/**
	 * Getter du Chemin relatif (par rapport à src/main/resources) 
	 * du template contenant le commentaire à ajouter 
	 * au dessus de SectionHit_RG.properties.<br/>
	 * "commentaires_properties/commentaires_preferences_properties.txt"
	 * <br/>
	 *
	 * @return CHEMIN_RELATIF_TEMPLATE_COMMENTAIRE : String.<br/>
	 */
	public static String getCheminRelatifTemplateCommentaire() {
		return CHEMIN_RELATIF_TEMPLATE_COMMENTAIRE;
	} // Fin de getCheminRelatifTemplateCommentaire()._____________________
	
	
	
	/* 1 - numDepartement. **********/
	/**
	 * retourne le <code>validerRGSectionHitNumDepartement</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>numDepartement</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numDepartement</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartement stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumDepartement 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumDepartement() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitNumDepartement
					, fournirKeyValiderRGSectionHitNumDepartement()
					, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumDepartement().________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumDepartement 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numdepartement".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumDepartement() {
		return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT;
	} // Fin de fournirKeyValiderRGSectionHitNumDepartement()._____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumDepartement par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitNumDepartement 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumDepartement stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumDepartement : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumDepartement() 
													throws Exception {
		return fournirValiderRGSectionHitNumDepartement();
	} // Fin de getValiderRGSectionHitNumDepartement().____________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumDepartement par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitNumDepartement.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumDepartement(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitNumDepartement
							, fournirKeyValiderRGSectionHitNumDepartement());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumDepartement(...)._________________



	/**
	 * retourne le validerRGSectionHitNumDepartementRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumDepartementRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumDepartementRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumDepartementRenseigne01
					, fournirKeyValiderRGSectionHitNumDepartementRenseigne01()
					, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumDepartementRenseigne01()._______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumDepartementRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numdepartement.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumDepartementRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitNumDepartementRenseigne01().__



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumDepartementRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumDepartementRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumDepartementRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumDepartementRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitNumDepartementRenseigne01();
	} // Fin de getValiderRGSectionHitNumDepartementRenseigne01()._________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumDepartementRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitNumDepartementRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumDepartementRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumDepartementRenseigne01
							, fournirKeyValiderRGSectionHitNumDepartementRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumDepartementRenseigne01(...).______



	/**
	 * retourne le validerRGSectionHitNumDepartementRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumDepartementRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumDepartementRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumDepartementRegex02
					, fournirKeyValiderRGSectionHitNumDepartementRegex02()
					, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumDepartementRegex02()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumDepartementRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numdepartement.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumDepartementRegex02() {
		return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitNumDepartementRegex02().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumDepartementRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumDepartementRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumDepartementRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumDepartementRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitNumDepartementRegex02();
	} // Fin de getValiderRGSectionHitNumDepartementRegex02()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumDepartementRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitNumDepartementRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumDepartementRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumDepartementRegex02
							, fournirKeyValiderRGSectionHitNumDepartementRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumDepartementRegex02(...).__________
	
	
	
	/* 2 - numSection. ****************/
	/**
	 * retourne le <code>validerRGSectionHitNumSection</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>numSection</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numSection</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSection stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumSection 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumSection() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitNumSection
					, fournirKeyValiderRGSectionHitNumSection()
					, STRING_VALIDER_SECTIONHIT_NUMSECTION_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumSection().________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumSection 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numsection".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMSECTION : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumSection() {
		return KEY_VALIDER_SECTIONHIT_NUMSECTION;
	} // Fin de fournirKeyValiderRGSectionHitNumSection()._____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumSection par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitNumSection 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumSection stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumSection : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumSection() 
													throws Exception {
		return fournirValiderRGSectionHitNumSection();
	} // Fin de getValiderRGSectionHitNumSection().____________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumSection par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitNumSection.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumSection(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitNumSection
							, fournirKeyValiderRGSectionHitNumSection());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumSection(...)._________________



	/**
	 * retourne le validerRGSectionHitNumSectionRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumSectionRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumSectionRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumSectionRenseigne01
					, fournirKeyValiderRGSectionHitNumSectionRenseigne01()
					, STRING_VALIDER_SECTIONHIT_NUMSECTION_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumSectionRenseigne01()._______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumSectionRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numsection.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMSECTION_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumSectionRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_NUMSECTION_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitNumSectionRenseigne01().__



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumSectionRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumSectionRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumSectionRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumSectionRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitNumSectionRenseigne01();
	} // Fin de getValiderRGSectionHitNumSectionRenseigne01()._________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumSectionRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitNumSectionRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumSectionRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumSectionRenseigne01
							, fournirKeyValiderRGSectionHitNumSectionRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumSectionRenseigne01(...).______



	/**
	 * retourne le validerRGSectionHitNumSectionRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumSectionRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumSectionRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumSectionRegex02
					, fournirKeyValiderRGSectionHitNumSectionRegex02()
					, STRING_VALIDER_SECTIONHIT_NUMSECTION_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumSectionRegex02()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumSectionRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numsection.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMSECTION_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumSectionRegex02() {
		return KEY_VALIDER_SECTIONHIT_NUMSECTION_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitNumSectionRegex02().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumSectionRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumSectionRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumSectionRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumSectionRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitNumSectionRegex02();
	} // Fin de getValiderRGSectionHitNumSectionRegex02()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumSectionRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitNumSectionRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumSectionRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumSectionRegex02
							, fournirKeyValiderRGSectionHitNumSectionRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumSectionRegex02(...).__________
	
	
	
	/* 3 - sens. */
	/**
	 * retourne le <code>validerRGSectionHitSens</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>sens</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>sens</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitSens stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitSens 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitSens() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitSens
					, fournirKeyValiderRGSectionHitSens()
					, STRING_VALIDER_SECTIONHIT_SENS_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitSens().________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitSens 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.sens".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_SENS : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitSens() {
		return KEY_VALIDER_SECTIONHIT_SENS;
	} // Fin de fournirKeyValiderRGSectionHitSens()._____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitSens par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitSens 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitSens stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitSens : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitSens() 
													throws Exception {
		return fournirValiderRGSectionHitSens();
	} // Fin de getValiderRGSectionHitSens().____________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitSens par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitSens.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitSens(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitSens
							, fournirKeyValiderRGSectionHitSens());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitSens(...)._________________



	/**
	 * retourne le validerRGSectionHitSensRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitSensRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitSensRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitSensRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitSensRenseigne01
					, fournirKeyValiderRGSectionHitSensRenseigne01()
					, STRING_VALIDER_SECTIONHIT_SENS_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitSensRenseigne01()._______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitSensRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.sens.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_SENS_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitSensRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_SENS_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitSensRenseigne01().__



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitSensRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitSensRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitSensRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitSensRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitSensRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitSensRenseigne01();
	} // Fin de getValiderRGSectionHitSensRenseigne01()._________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitSensRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitSensRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitSensRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitSensRenseigne01
							, fournirKeyValiderRGSectionHitSensRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitSensRenseigne01(...).______



	/**
	 * retourne le validerRGSectionHitSensRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitSensRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitSensRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitSensRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitSensRegex02
					, fournirKeyValiderRGSectionHitSensRegex02()
					, STRING_VALIDER_SECTIONHIT_SENS_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitSensRegex02()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitSensRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.sens.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_SENS_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitSensRegex02() {
		return KEY_VALIDER_SECTIONHIT_SENS_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitSensRegex02().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitSensRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitSensRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitSensRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitSensRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitSensRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitSensRegex02();
	} // Fin de getValiderRGSectionHitSensRegex02()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitSensRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitSensRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitSensRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitSensRegex02
							, fournirKeyValiderRGSectionHitSensRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitSensRegex02(...).__________



	/**
	 * retourne le validerRGSectionHitSensNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitSensNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitSensNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitSensNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitSensNomenclature03
					, fournirKeyValiderRGSectionHitSensNomenclature03()
					, STRING_VALIDER_SECTIONHIT_SENS_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitSensNomenclature03().____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitSensNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.sens.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_SENS_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitSensNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_SENS_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitSensNomenclature03()._________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitSensNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitSensNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitSensNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitSensNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitSensNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitSensNomenclature03();
	} // Fin de getValiderRGSectionHitSensNomenclature03().________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitSensNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitSensNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitSensNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitSensNomenclature03
							, fournirKeyValiderRGSectionHitSensNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitSensNomenclature03(...)._____________
	
	
	
	/* 3 - nature. */
	/**
	 * retourne le <code>validerRGSectionHitNature</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>nature</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>nature</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNature stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNature 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNature() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitNature
					, fournirKeyValiderRGSectionHitNature()
					, STRING_VALIDER_SECTIONHIT_NATURE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNature().________________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNature 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.nature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NATURE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNature() {
		return KEY_VALIDER_SECTIONHIT_NATURE;
	} // Fin de fournirKeyValiderRGSectionHitNature()._____________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNature par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitNature 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNature stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNature : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNature() 
													throws Exception {
		return fournirValiderRGSectionHitNature();
	} // Fin de getValiderRGSectionHitNature().____________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNature par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitNature.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNature(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitNature
							, fournirKeyValiderRGSectionHitNature());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNature(...)._________________________



	/**
	 * retourne le validerRGSectionHitNatureRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNatureRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNatureRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNatureRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNatureRenseigne01
					, fournirKeyValiderRGSectionHitNatureRenseigne01()
					, STRING_VALIDER_SECTIONHIT_NATURE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNatureRenseigne01()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNatureRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.nature.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NATURE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNatureRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_NATURE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitNatureRenseigne01().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNatureRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNatureRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNatureRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNatureRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNatureRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitNatureRenseigne01();
	} // Fin de getValiderRGSectionHitNatureRenseigne01()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNatureRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitNatureRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNatureRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNatureRenseigne01
							, fournirKeyValiderRGSectionHitNatureRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNatureRenseigne01(...).______________



	/**
	 * retourne le validerRGSectionHitNatureRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNatureRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNatureRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNatureRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNatureRegex02
					, fournirKeyValiderRGSectionHitNatureRegex02()
					, STRING_VALIDER_SECTIONHIT_NATURE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNatureRegex02()._________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNatureRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.nature.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NATURE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNatureRegex02() {
		return KEY_VALIDER_SECTIONHIT_NATURE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitNatureRegex02().______________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNatureRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNatureRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNatureRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNatureRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNatureRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitNatureRegex02();
	} // Fin de getValiderRGSectionHitNatureRegex02()._____________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNatureRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitNatureRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNatureRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNatureRegex02
							, fournirKeyValiderRGSectionHitNatureRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNatureRegex02(...).__________________



	/**
	 * retourne le validerRGSectionHitNatureNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNatureNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNatureNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNatureNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNatureNomenclature03
					, fournirKeyValiderRGSectionHitNatureNomenclature03()
					, STRING_VALIDER_SECTIONHIT_NATURE_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNatureNomenclature03().__________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNatureNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.nature.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NATURE_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNatureNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_NATURE_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitNatureNomenclature03()._______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNatureNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNatureNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNatureNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNatureNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNatureNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitNatureNomenclature03();
	} // Fin de getValiderRGSectionHitNatureNomenclature03().______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNatureNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitNatureNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNatureNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNatureNomenclature03
							, fournirKeyValiderRGSectionHitNatureNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNatureNomenclature03(...).___________
	
	
	
	/* 5 - classe. ****************/
	/**
	 * retourne le <code>validerRGSectionHitClasse</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>classe</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>classe</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasse stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasse 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasse() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitClasse
					, fournirKeyValiderRGSectionHitClasse()
					, STRING_VALIDER_SECTIONHIT_CLASSE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasse().________________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasse 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classe".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasse() {
		return KEY_VALIDER_SECTIONHIT_CLASSE;
	} // Fin de fournirKeyValiderRGSectionHitClasse()._____________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasse par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitClasse 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasse stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasse : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasse() 
													throws Exception {
		return fournirValiderRGSectionHitClasse();
	} // Fin de getValiderRGSectionHitClasse().____________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasse par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitClasse.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasse(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitClasse
							, fournirKeyValiderRGSectionHitClasse());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasse(...)._________________________



	/**
	 * retourne le validerRGSectionHitClasseRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClasseRenseigne01
					, fournirKeyValiderRGSectionHitClasseRenseigne01()
					, STRING_VALIDER_SECTIONHIT_CLASSE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseRenseigne01()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classe.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_CLASSE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitClasseRenseigne01().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitClasseRenseigne01();
	} // Fin de getValiderRGSectionHitClasseRenseigne01()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitClasseRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClasseRenseigne01
							, fournirKeyValiderRGSectionHitClasseRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseRenseigne01(...).______________



	/**
	 * retourne le validerRGSectionHitClasseRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClasseRegex02
					, fournirKeyValiderRGSectionHitClasseRegex02()
					, STRING_VALIDER_SECTIONHIT_CLASSE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseRegex02()._________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classe.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseRegex02() {
		return KEY_VALIDER_SECTIONHIT_CLASSE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitClasseRegex02().______________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitClasseRegex02();
	} // Fin de getValiderRGSectionHitClasseRegex02()._____________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitClasseRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClasseRegex02
							, fournirKeyValiderRGSectionHitClasseRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseRegex02(...).__________________
	
	
	
	/* 6 - anneeTraitement. **************/
	/**
	 * retourne le <code>validerRGSectionHitAnneeTraitement</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>anneeTraitement</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>anneeTraitement</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAnneeTraitement stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAnneeTraitement 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAnneeTraitement() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitAnneeTraitement
					, fournirKeyValiderRGSectionHitAnneeTraitement()
					, STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAnneeTraitement().________________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAnneeTraitement 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.anneeTraitement".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAnneeTraitement() {
		return KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT;
	} // Fin de fournirKeyValiderRGSectionHitAnneeTraitement()._____________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAnneeTraitement par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitAnneeTraitement 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAnneeTraitement stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAnneeTraitement : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAnneeTraitement() 
													throws Exception {
		return fournirValiderRGSectionHitAnneeTraitement();
	} // Fin de getValiderRGSectionHitAnneeTraitement().____________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAnneeTraitement par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitAnneeTraitement.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAnneeTraitement(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitAnneeTraitement
							, fournirKeyValiderRGSectionHitAnneeTraitement());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAnneeTraitement(...)._________________________



	/**
	 * retourne le validerRGSectionHitAnneeTraitementRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAnneeTraitementRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAnneeTraitementRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAnneeTraitementRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitAnneeTraitementRenseigne01
					, fournirKeyValiderRGSectionHitAnneeTraitementRenseigne01()
					, STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAnneeTraitementRenseigne01()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAnneeTraitementRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.anneeTraitement.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAnneeTraitementRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitAnneeTraitementRenseigne01().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAnneeTraitementRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitAnneeTraitementRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAnneeTraitementRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAnneeTraitementRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAnneeTraitementRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitAnneeTraitementRenseigne01();
	} // Fin de getValiderRGSectionHitAnneeTraitementRenseigne01()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAnneeTraitementRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitAnneeTraitementRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAnneeTraitementRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitAnneeTraitementRenseigne01
							, fournirKeyValiderRGSectionHitAnneeTraitementRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAnneeTraitementRenseigne01(...).______________



	/**
	 * retourne le validerRGSectionHitAnneeTraitementRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAnneeTraitementRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAnneeTraitementRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAnneeTraitementRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitAnneeTraitementRegex02
					, fournirKeyValiderRGSectionHitAnneeTraitementRegex02()
					, STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAnneeTraitementRegex02()._________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAnneeTraitementRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.anneeTraitement.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAnneeTraitementRegex02() {
		return KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitAnneeTraitementRegex02().______________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAnneeTraitementRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitAnneeTraitementRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAnneeTraitementRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAnneeTraitementRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAnneeTraitementRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitAnneeTraitementRegex02();
	} // Fin de getValiderRGSectionHitAnneeTraitementRegex02()._____________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAnneeTraitementRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitAnneeTraitementRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAnneeTraitementRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitAnneeTraitementRegex02
							, fournirKeyValiderRGSectionHitAnneeTraitementRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAnneeTraitementRegex02(...).__________________
	
	
	
	/* 7 - zoneLibre1. **************/
	/**
	 * retourne le <code>validerRGSectionHitZoneLibre1</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>zoneLibre1</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>zoneLibre1</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitZoneLibre1 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitZoneLibre1 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitZoneLibre1() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitZoneLibre1
					, fournirKeyValiderRGSectionHitZoneLibre1()
					, STRING_VALIDER_SECTIONHIT_ZONELIBRE1_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitZoneLibre1().____________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitZoneLibre1 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.zoneLibre1".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ZONELIBRE1 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitZoneLibre1() {
		return KEY_VALIDER_SECTIONHIT_ZONELIBRE1;
	} // Fin de fournirKeyValiderRGSectionHitZoneLibre1()._________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitZoneLibre1 par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitZoneLibre1 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitZoneLibre1 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitZoneLibre1 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitZoneLibre1() 
													throws Exception {
		return fournirValiderRGSectionHitZoneLibre1();
	} // Fin de getValiderRGSectionHitZoneLibre1().________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitZoneLibre1 par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitZoneLibre1.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitZoneLibre1(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitZoneLibre1
							, fournirKeyValiderRGSectionHitZoneLibre1());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitZoneLibre1(...)._____________________



	/**
	 * retourne le validerRGSectionHitZoneLibre1Renseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitZoneLibre1Renseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitZoneLibre1Renseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitZoneLibre1Renseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitZoneLibre1Renseigne01
					, fournirKeyValiderRGSectionHitZoneLibre1Renseigne01()
					, STRING_VALIDER_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitZoneLibre1Renseigne01()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitZoneLibre1Renseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.zoneLibre1.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitZoneLibre1Renseigne01() {
		return KEY_VALIDER_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitZoneLibre1Renseigne01().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitZoneLibre1Renseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitZoneLibre1Renseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitZoneLibre1Renseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitZoneLibre1Renseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitZoneLibre1Renseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitZoneLibre1Renseigne01();
	} // Fin de getValiderRGSectionHitZoneLibre1Renseigne01()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitZoneLibre1Renseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitZoneLibre1Renseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitZoneLibre1Renseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitZoneLibre1Renseigne01
							, fournirKeyValiderRGSectionHitZoneLibre1Renseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitZoneLibre1Renseigne01(...).__________



	/**
	 * retourne le validerRGSectionHitZoneLibre1Regex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitZoneLibre1Regex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitZoneLibre1Regex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitZoneLibre1Regex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitZoneLibre1Regex02
					, fournirKeyValiderRGSectionHitZoneLibre1Regex02()
					, STRING_VALIDER_SECTIONHIT_ZONELIBRE1_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitZoneLibre1Regex02()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitZoneLibre1Regex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.zoneLibre1.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ZONELIBRE1_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitZoneLibre1Regex02() {
		return KEY_VALIDER_SECTIONHIT_ZONELIBRE1_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitZoneLibre1Regex02().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitZoneLibre1Regex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitZoneLibre1Regex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitZoneLibre1Regex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitZoneLibre1Regex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitZoneLibre1Regex02() 
													throws Exception {
		return fournirValiderRGSectionHitZoneLibre1Regex02();
	} // Fin de getValiderRGSectionHitZoneLibre1Regex02()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitZoneLibre1Regex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitZoneLibre1Regex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitZoneLibre1Regex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitZoneLibre1Regex02
							, fournirKeyValiderRGSectionHitZoneLibre1Regex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitZoneLibre1Regex02(...).______________
	
	
	
	/* 8 - numRoute. **************/
	/**
	 * retourne le <code>validerRGSectionHitNumRoute</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>numRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numRoute</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumRoute stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumRoute 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumRoute() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitNumRoute
					, fournirKeyValiderRGSectionHitNumRoute()
					, STRING_VALIDER_SECTIONHIT_NUMROUTE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumRoute().______________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumRoute 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numRoute".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMROUTE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumRoute() {
		return KEY_VALIDER_SECTIONHIT_NUMROUTE;
	} // Fin de fournirKeyValiderRGSectionHitNumRoute().___________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumRoute par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitNumRoute 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumRoute stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumRoute : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumRoute() 
													throws Exception {
		return fournirValiderRGSectionHitNumRoute();
	} // Fin de getValiderRGSectionHitNumRoute().__________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumRoute par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitNumRoute.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumRoute(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitNumRoute
							, fournirKeyValiderRGSectionHitNumRoute());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumRoute(...)._______________________



	/**
	 * retourne le validerRGSectionHitNumRouteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumRouteRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumRouteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumRouteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumRouteRenseigne01
					, fournirKeyValiderRGSectionHitNumRouteRenseigne01()
					, STRING_VALIDER_SECTIONHIT_NUMROUTE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumRouteRenseigne01().___________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumRouteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numRoute.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMROUTE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumRouteRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_NUMROUTE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitNumRouteRenseigne01().________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumRouteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumRouteRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumRouteRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumRouteRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumRouteRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitNumRouteRenseigne01();
	} // Fin de getValiderRGSectionHitNumRouteRenseigne01()._______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumRouteRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitNumRouteRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumRouteRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumRouteRenseigne01
							, fournirKeyValiderRGSectionHitNumRouteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumRouteRenseigne01(...).____________



	/**
	 * retourne le validerRGSectionHitNumRouteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumRouteRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumRouteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumRouteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumRouteRegex02
					, fournirKeyValiderRGSectionHitNumRouteRegex02()
					, STRING_VALIDER_SECTIONHIT_NUMROUTE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumRouteRegex02()._______________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumRouteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numRoute.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMROUTE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumRouteRegex02() {
		return KEY_VALIDER_SECTIONHIT_NUMROUTE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitNumRouteRegex02().____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumRouteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumRouteRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumRouteRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumRouteRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumRouteRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitNumRouteRegex02();
	} // Fin de getValiderRGSectionHitNumRouteRegex02().___________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumRouteRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitNumRouteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumRouteRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumRouteRegex02
							, fournirKeyValiderRGSectionHitNumRouteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumRouteRegex02(...).________________
	
	
	
	/* 9 - indiceNumRoute. **************/
	/**
	 * retourne le <code>validerRGSectionHitIndiceNumRoute</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>indiceNumRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>indiceNumRoute</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceNumRoute stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitIndiceNumRoute 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitIndiceNumRoute() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitIndiceNumRoute
					, fournirKeyValiderRGSectionHitIndiceNumRoute()
					, STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitIndiceNumRoute().________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitIndiceNumRoute 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.indiceNumRoute".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_INDICENUMROUTE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitIndiceNumRoute() {
		return KEY_VALIDER_SECTIONHIT_INDICENUMROUTE;
	} // Fin de fournirKeyValiderRGSectionHitIndiceNumRoute()._____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitIndiceNumRoute par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitIndiceNumRoute 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitIndiceNumRoute stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitIndiceNumRoute : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitIndiceNumRoute() 
													throws Exception {
		return fournirValiderRGSectionHitIndiceNumRoute();
	} // Fin de getValiderRGSectionHitIndiceNumRoute().____________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitIndiceNumRoute par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitIndiceNumRoute.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitIndiceNumRoute(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitIndiceNumRoute
							, fournirKeyValiderRGSectionHitIndiceNumRoute());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitIndiceNumRoute(...)._________________



	/**
	 * retourne le validerRGSectionHitIndiceNumRouteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceNumRouteRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitIndiceNumRouteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitIndiceNumRouteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitIndiceNumRouteRenseigne01
					, fournirKeyValiderRGSectionHitIndiceNumRouteRenseigne01()
					, STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitIndiceNumRouteRenseigne01()._____
	

	
	/**
	 * Getter de la clé du validerRGSectionHitIndiceNumRouteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.indiceNumRoute.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitIndiceNumRouteRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitIndiceNumRouteRenseigne01().__



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitIndiceNumRouteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceNumRouteRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitIndiceNumRouteRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitIndiceNumRouteRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitIndiceNumRouteRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitIndiceNumRouteRenseigne01();
	} // Fin de getValiderRGSectionHitIndiceNumRouteRenseigne01()._________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitIndiceNumRouteRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitIndiceNumRouteRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitIndiceNumRouteRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitIndiceNumRouteRenseigne01
							, fournirKeyValiderRGSectionHitIndiceNumRouteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitIndiceNumRouteRenseigne01(...).______



	/**
	 * retourne le validerRGSectionHitIndiceNumRouteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceNumRouteRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitIndiceNumRouteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitIndiceNumRouteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitIndiceNumRouteRegex02
					, fournirKeyValiderRGSectionHitIndiceNumRouteRegex02()
					, STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitIndiceNumRouteRegex02()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitIndiceNumRouteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.indiceNumRoute.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_INDICENUMROUTE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitIndiceNumRouteRegex02() {
		return KEY_VALIDER_SECTIONHIT_INDICENUMROUTE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitIndiceNumRouteRegex02().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitIndiceNumRouteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceNumRouteRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitIndiceNumRouteRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitIndiceNumRouteRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitIndiceNumRouteRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitIndiceNumRouteRegex02();
	} // Fin de getValiderRGSectionHitIndiceNumRouteRegex02()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitIndiceNumRouteRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitIndiceNumRouteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitIndiceNumRouteRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitIndiceNumRouteRegex02
							, fournirKeyValiderRGSectionHitIndiceNumRouteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitIndiceNumRouteRegex02(...).__________
	
	
	
	/* 10 - indiceLettreRoute. **************/
	/**
	 * retourne le <code>validerRGSectionHitIndiceLettreRoute</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>indiceLettreRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>indiceLettreRoute</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceLettreRoute stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitIndiceLettreRoute 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitIndiceLettreRoute() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitIndiceLettreRoute
					, fournirKeyValiderRGSectionHitIndiceLettreRoute()
					, STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitIndiceLettreRoute()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitIndiceLettreRoute 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.indiceLettreRoute".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitIndiceLettreRoute() {
		return KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE;
	} // Fin de fournirKeyValiderRGSectionHitIndiceLettreRoute().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitIndiceLettreRoute par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitIndiceLettreRoute 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitIndiceLettreRoute stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitIndiceLettreRoute : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitIndiceLettreRoute() 
													throws Exception {
		return fournirValiderRGSectionHitIndiceLettreRoute();
	} // Fin de getValiderRGSectionHitIndiceLettreRoute()._________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitIndiceLettreRoute par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitIndiceLettreRoute.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitIndiceLettreRoute(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitIndiceLettreRoute
							, fournirKeyValiderRGSectionHitIndiceLettreRoute());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitIndiceLettreRoute(...).______________



	/**
	 * retourne le validerRGSectionHitIndiceLettreRouteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceLettreRouteRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitIndiceLettreRouteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitIndiceLettreRouteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitIndiceLettreRouteRenseigne01
					, fournirKeyValiderRGSectionHitIndiceLettreRouteRenseigne01()
					, STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitIndiceLettreRouteRenseigne01().__
	

	
	/**
	 * Getter de la clé du validerRGSectionHitIndiceLettreRouteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.indiceLettreRoute.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitIndiceLettreRouteRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitIndiceLettreRouteRenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitIndiceLettreRouteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceLettreRouteRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitIndiceLettreRouteRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitIndiceLettreRouteRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitIndiceLettreRouteRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitIndiceLettreRouteRenseigne01();
	} // Fin de getValiderRGSectionHitIndiceLettreRouteRenseigne01().______
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitIndiceLettreRouteRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitIndiceLettreRouteRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitIndiceLettreRouteRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitIndiceLettreRouteRenseigne01
							, fournirKeyValiderRGSectionHitIndiceLettreRouteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitIndiceLettreRouteRenseigne01(...).___



	/**
	 * retourne le validerRGSectionHitIndiceLettreRouteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceLettreRouteRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitIndiceLettreRouteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitIndiceLettreRouteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitIndiceLettreRouteRegex02
					, fournirKeyValiderRGSectionHitIndiceLettreRouteRegex02()
					, STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitIndiceLettreRouteRegex02().______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitIndiceLettreRouteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.indiceLettreRoute.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitIndiceLettreRouteRegex02() {
		return KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitIndiceLettreRouteRegex02().___



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitIndiceLettreRouteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceLettreRouteRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitIndiceLettreRouteRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitIndiceLettreRouteRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitIndiceLettreRouteRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitIndiceLettreRouteRegex02();
	} // Fin de getValiderRGSectionHitIndiceLettreRouteRegex02().__________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitIndiceLettreRouteRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitIndiceLettreRouteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitIndiceLettreRouteRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitIndiceLettreRouteRegex02
							, fournirKeyValiderRGSectionHitIndiceLettreRouteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitIndiceLettreRouteRegex02(...)._______
	
	
	
	/* 11 - categorieAdminRoute. **************/
	/**
	 * retourne le <code>validerRGSectionHitCategorieAdminRoute</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>categorieAdminRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>categorieAdminRoute</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitCategorieAdminRoute stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitCategorieAdminRoute 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitCategorieAdminRoute() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitCategorieAdminRoute
					, fournirKeyValiderRGSectionHitCategorieAdminRoute()
					, STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitCategorieAdminRoute().___________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitCategorieAdminRoute 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.categorieAdminRoute".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitCategorieAdminRoute() {
		return KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE;
	} // Fin de fournirKeyValiderRGSectionHitCategorieAdminRoute().________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitCategorieAdminRoute par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitCategorieAdminRoute 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitCategorieAdminRoute stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitCategorieAdminRoute : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitCategorieAdminRoute() 
													throws Exception {
		return fournirValiderRGSectionHitCategorieAdminRoute();
	} // Fin de getValiderRGSectionHitCategorieAdminRoute()._______________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitCategorieAdminRoute par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitCategorieAdminRoute.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitCategorieAdminRoute(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitCategorieAdminRoute
							, fournirKeyValiderRGSectionHitCategorieAdminRoute());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitCategorieAdminRoute(...).____________



	/**
	 * retourne le validerRGSectionHitCategorieAdminRouteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitCategorieAdminRouteRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitCategorieAdminRouteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitCategorieAdminRouteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitCategorieAdminRouteRenseigne01
					, fournirKeyValiderRGSectionHitCategorieAdminRouteRenseigne01()
					, STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitCategorieAdminRouteRenseigne01().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitCategorieAdminRouteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.categorieAdminRoute.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitCategorieAdminRouteRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitCategorieAdminRouteRenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitCategorieAdminRouteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitCategorieAdminRouteRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitCategorieAdminRouteRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitCategorieAdminRouteRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitCategorieAdminRouteRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitCategorieAdminRouteRenseigne01();
	} // Fin de getValiderRGSectionHitCategorieAdminRouteRenseigne01().____
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitCategorieAdminRouteRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitCategorieAdminRouteRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitCategorieAdminRouteRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitCategorieAdminRouteRenseigne01
							, fournirKeyValiderRGSectionHitCategorieAdminRouteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitCategorieAdminRouteRenseigne01(...)._



	/**
	 * retourne le validerRGSectionHitCategorieAdminRouteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitCategorieAdminRouteRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitCategorieAdminRouteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitCategorieAdminRouteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitCategorieAdminRouteRegex02
					, fournirKeyValiderRGSectionHitCategorieAdminRouteRegex02()
					, STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitCategorieAdminRouteRegex02().____
	

	
	/**
	 * Getter de la clé du validerRGSectionHitCategorieAdminRouteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.categorieAdminRoute.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitCategorieAdminRouteRegex02() {
		return KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitCategorieAdminRouteRegex02()._



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitCategorieAdminRouteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitCategorieAdminRouteRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitCategorieAdminRouteRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitCategorieAdminRouteRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitCategorieAdminRouteRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitCategorieAdminRouteRegex02();
	} // Fin de getValiderRGSectionHitCategorieAdminRouteRegex02().________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitCategorieAdminRouteRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitCategorieAdminRouteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitCategorieAdminRouteRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitCategorieAdminRouteRegex02
							, fournirKeyValiderRGSectionHitCategorieAdminRouteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitCategorieAdminRouteRegex02(...)._____



	/**
	 * retourne le validerRGSectionHitCategorieAdminRouteNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitCategorieAdminRouteNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitCategorieAdminRouteNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitCategorieAdminRouteNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitCategorieAdminRouteNomenclature03
					, fournirKeyValiderRGSectionHitCategorieAdminRouteNomenclature03()
					, STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitCategorieAdminRouteNomenclature03().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitCategorieAdminRouteNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.categorieAdminRoute.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitCategorieAdminRouteNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitCategorieAdminRouteNomenclature03().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitCategorieAdminRouteNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitCategorieAdminRouteNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitCategorieAdminRouteNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitCategorieAdminRouteNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitCategorieAdminRouteNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitCategorieAdminRouteNomenclature03();
	} // Fin de getValiderRGSectionHitCategorieAdminRouteNomenclature03()._
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitCategorieAdminRouteNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitCategorieAdminRouteNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitCategorieAdminRouteNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitCategorieAdminRouteNomenclature03
							, fournirKeyValiderRGSectionHitCategorieAdminRouteNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitCategorieAdminRouteNomenclature03(...).
	
	
	
	/* 12 - typeComptage. **************/
	/**
	 * retourne le <code>validerRGSectionHitTypeComptage</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>typeComptage</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>typeComptage</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeComptage stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitTypeComptage 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitTypeComptage() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitTypeComptage
					, fournirKeyValiderRGSectionHitTypeComptage()
					, STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitTypeComptage().__________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitTypeComptage 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.typeComptage".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitTypeComptage() {
		return KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE;
	} // Fin de fournirKeyValiderRGSectionHitTypeComptage()._______________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitTypeComptage par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitTypeComptage 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitTypeComptage stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitTypeComptage : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitTypeComptage() 
													throws Exception {
		return fournirValiderRGSectionHitTypeComptage();
	} // Fin de getValiderRGSectionHitTypeComptage().______________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitTypeComptage par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitTypeComptage.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitTypeComptage(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitTypeComptage
							, fournirKeyValiderRGSectionHitTypeComptage());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitTypeComptage(...).___________________



	/**
	 * retourne le validerRGSectionHitTypeComptageRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeComptageRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitTypeComptageRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitTypeComptageRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitTypeComptageRenseigne01
					, fournirKeyValiderRGSectionHitTypeComptageRenseigne01()
					, STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitTypeComptageRenseigne01()._______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitTypeComptageRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.typeComptage.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitTypeComptageRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitTypeComptageRenseigne01().____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitTypeComptageRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeComptageRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitTypeComptageRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitTypeComptageRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitTypeComptageRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitTypeComptageRenseigne01();
	} // Fin de getValiderRGSectionHitTypeComptageRenseigne01().___________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitTypeComptageRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitTypeComptageRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitTypeComptageRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitTypeComptageRenseigne01
							, fournirKeyValiderRGSectionHitTypeComptageRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitTypeComptageRenseigne01(...).________



	/**
	 * retourne le validerRGSectionHitTypeComptageRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeComptageRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitTypeComptageRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitTypeComptageRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitTypeComptageRegex02
					, fournirKeyValiderRGSectionHitTypeComptageRegex02()
					, STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitTypeComptageRegex02().___________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitTypeComptageRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.typeComptage.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitTypeComptageRegex02() {
		return KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitTypeComptageRegex02().________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitTypeComptageRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeComptageRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitTypeComptageRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitTypeComptageRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitTypeComptageRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitTypeComptageRegex02();
	} // Fin de getValiderRGSectionHitTypeComptageRegex02()._______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitTypeComptageRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitTypeComptageRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitTypeComptageRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitTypeComptageRegex02
							, fournirKeyValiderRGSectionHitTypeComptageRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitTypeComptageRegex02(...).____________



	/**
	 * retourne le validerRGSectionHitTypeComptageNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeComptageNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitTypeComptageNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitTypeComptageNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitTypeComptageNomenclature03
					, fournirKeyValiderRGSectionHitTypeComptageNomenclature03()
					, STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitTypeComptageNomenclature03().____
	

	
	/**
	 * Getter de la clé du validerRGSectionHitTypeComptageNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.typeComptage.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitTypeComptageNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitTypeComptageNomenclature03()._



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitTypeComptageNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeComptageNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitTypeComptageNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitTypeComptageNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitTypeComptageNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitTypeComptageNomenclature03();
	} // Fin de getValiderRGSectionHitTypeComptageNomenclature03().________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitTypeComptageNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitTypeComptageNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitTypeComptageNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitTypeComptageNomenclature03
							, fournirKeyValiderRGSectionHitTypeComptageNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitTypeComptageNomenclature03(...)._____
	
	
	
	/* 13 - classementRoute. **************/
	/**
	 * retourne le <code>validerRGSectionHitClassementRoute</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>classementRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>classementRoute</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClassementRoute stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClassementRoute 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClassementRoute() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitClassementRoute
					, fournirKeyValiderRGSectionHitClassementRoute()
					, STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClassementRoute()._______________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClassementRoute 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classementRoute".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClassementRoute() {
		return KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE;
	} // Fin de fournirKeyValiderRGSectionHitClassementRoute().____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClassementRoute par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitClassementRoute 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClassementRoute stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClassementRoute : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClassementRoute() 
													throws Exception {
		return fournirValiderRGSectionHitClassementRoute();
	} // Fin de getValiderRGSectionHitClassementRoute().___________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClassementRoute par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitClassementRoute.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClassementRoute(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitClassementRoute
							, fournirKeyValiderRGSectionHitClassementRoute());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClassementRoute(...).__________________



	/**
	 * retourne le validerRGSectionHitClassementRouteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClassementRouteRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClassementRouteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClassementRouteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClassementRouteRenseigne01
					, fournirKeyValiderRGSectionHitClassementRouteRenseigne01()
					, STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClassementRouteRenseigne01().____
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClassementRouteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classementRoute.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClassementRouteRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitClassementRouteRenseigne01()._



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClassementRouteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClassementRouteRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClassementRouteRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClassementRouteRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClassementRouteRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitClassementRouteRenseigne01();
	} // Fin de getValiderRGSectionHitClassementRouteRenseigne01().________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClassementRouteRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitClassementRouteRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClassementRouteRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClassementRouteRenseigne01
							, fournirKeyValiderRGSectionHitClassementRouteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClassementRouteRenseigne01(...)._____



	/**
	 * retourne le validerRGSectionHitClassementRouteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClassementRouteRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClassementRouteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClassementRouteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClassementRouteRegex02
					, fournirKeyValiderRGSectionHitClassementRouteRegex02()
					, STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClassementRouteRegex02().________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClassementRouteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classementRoute.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClassementRouteRegex02() {
		return KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitClassementRouteRegex02()._____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClassementRouteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClassementRouteRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClassementRouteRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClassementRouteRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClassementRouteRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitClassementRouteRegex02();
	} // Fin de getValiderRGSectionHitClassementRouteRegex02().____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClassementRouteRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitClassementRouteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClassementRouteRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClassementRouteRegex02
							, fournirKeyValiderRGSectionHitClassementRouteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClassementRouteRegex02(...)._________



	/**
	 * retourne le validerRGSectionHitClassementRouteNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClassementRouteNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClassementRouteNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClassementRouteNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClassementRouteNomenclature03
					, fournirKeyValiderRGSectionHitClassementRouteNomenclature03()
					, STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClassementRouteNomenclature03()._
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClassementRouteNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classementRoute.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClassementRouteNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitClassementRouteNomenclature03().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClassementRouteNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClassementRouteNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClassementRouteNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClassementRouteNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClassementRouteNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitClassementRouteNomenclature03();
	} // Fin de getValiderRGSectionHitClassementRouteNomenclature03()._____
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClassementRouteNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitClassementRouteNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClassementRouteNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClassementRouteNomenclature03
							, fournirKeyValiderRGSectionHitClassementRouteNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClassementRouteNomenclature03(...).__
	
	
	
	/* 14 - classeLargeurChausseeU. **************/
	/**
	 * retourne le <code>validerRGSectionHitClasseLargeurChausseeU</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>classeLargeurChausseeU</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>classeLargeurChausseeU</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseeU stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseLargeurChausseeU 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseLargeurChausseeU() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitClasseLargeurChausseeU
					, fournirKeyValiderRGSectionHitClasseLargeurChausseeU()
					, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseLargeurChausseeU().________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseLargeurChausseeU 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classeLargeurChausseeU".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseLargeurChausseeU() {
		return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU;
	} // Fin de fournirKeyValiderRGSectionHitClasseLargeurChausseeU()._____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseeU par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseeU 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseLargeurChausseeU stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseLargeurChausseeU : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseLargeurChausseeU() 
													throws Exception {
		return fournirValiderRGSectionHitClasseLargeurChausseeU();
	} // Fin de getValiderRGSectionHitClasseLargeurChausseeU().____________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseeU par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitClasseLargeurChausseeU.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseLargeurChausseeU(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitClasseLargeurChausseeU
							, fournirKeyValiderRGSectionHitClasseLargeurChausseeU());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseLargeurChausseeU(...)._________



	/**
	 * retourne le validerRGSectionHitClasseLargeurChausseeURenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseeURenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseLargeurChausseeURenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseLargeurChausseeURenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClasseLargeurChausseeURenseigne01
					, fournirKeyValiderRGSectionHitClasseLargeurChausseeURenseigne01()
					, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseLargeurChausseeURenseigne01().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseLargeurChausseeURenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classeLargeurChausseeU.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseLargeurChausseeURenseigne01() {
		return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitClasseLargeurChausseeURenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseeURenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseeURenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseLargeurChausseeURenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseLargeurChausseeURenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseLargeurChausseeURenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitClasseLargeurChausseeURenseigne01();
	} // Fin de getValiderRGSectionHitClasseLargeurChausseeURenseigne01()._
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseeURenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitClasseLargeurChausseeURenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseLargeurChausseeURenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClasseLargeurChausseeURenseigne01
							, fournirKeyValiderRGSectionHitClasseLargeurChausseeURenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseLargeurChausseeURenseigne01(...).



	/**
	 * retourne le validerRGSectionHitClasseLargeurChausseeURegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseeURegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseLargeurChausseeURegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseLargeurChausseeURegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClasseLargeurChausseeURegex02
					, fournirKeyValiderRGSectionHitClasseLargeurChausseeURegex02()
					, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseLargeurChausseeURegex02()._
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseLargeurChausseeURegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classeLargeurChausseeU.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseLargeurChausseeURegex02() {
		return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitClasseLargeurChausseeURegex02().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseeURegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseeURegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseLargeurChausseeURegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseLargeurChausseeURegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseLargeurChausseeURegex02() 
													throws Exception {
		return fournirValiderRGSectionHitClasseLargeurChausseeURegex02();
	} // Fin de getValiderRGSectionHitClasseLargeurChausseeURegex02()._____
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseeURegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitClasseLargeurChausseeURegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseLargeurChausseeURegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClasseLargeurChausseeURegex02
							, fournirKeyValiderRGSectionHitClasseLargeurChausseeURegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseLargeurChausseeURegex02(...).__



	/**
	 * retourne le validerRGSectionHitClasseLargeurChausseeUNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseeUNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseLargeurChausseeUNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseLargeurChausseeUNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClasseLargeurChausseeUNomenclature03
					, fournirKeyValiderRGSectionHitClasseLargeurChausseeUNomenclature03()
					, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseLargeurChausseeUNomenclature03().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseLargeurChausseeUNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classeLargeurChausseeU.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseLargeurChausseeUNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitClasseLargeurChausseeUNomenclature03().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseeUNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseeUNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseLargeurChausseeUNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseLargeurChausseeUNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseLargeurChausseeUNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitClasseLargeurChausseeUNomenclature03();
	} // Fin de getValiderRGSectionHitClasseLargeurChausseeUNomenclature03().
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseeUNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitClasseLargeurChausseeUNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseLargeurChausseeUNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClasseLargeurChausseeUNomenclature03
							, fournirKeyValiderRGSectionHitClasseLargeurChausseeUNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseLargeurChausseeUNomenclature03(...).
	
	
	
	/* 15 - classeLargeurChausseesS. **************/
	/**
	 * retourne le <code>validerRGSectionHitClasseLargeurChausseesS</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>classeLargeurChausseesS</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>classeLargeurChausseesS</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseesS stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseLargeurChausseesS 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseLargeurChausseesS() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitClasseLargeurChausseesS
					, fournirKeyValiderRGSectionHitClasseLargeurChausseesS()
					, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseLargeurChausseesS()._______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseLargeurChausseesS 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classeLargeurChausseesS".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseLargeurChausseesS() {
		return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS;
	} // Fin de fournirKeyValiderRGSectionHitClasseLargeurChausseesS().____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseesS par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseesS 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseLargeurChausseesS stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseLargeurChausseesS : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseLargeurChausseesS() 
													throws Exception {
		return fournirValiderRGSectionHitClasseLargeurChausseesS();
	} // Fin de getValiderRGSectionHitClasseLargeurChausseesS().___________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseesS par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitClasseLargeurChausseesS.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseLargeurChausseesS(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitClasseLargeurChausseesS
							, fournirKeyValiderRGSectionHitClasseLargeurChausseesS());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseLargeurChausseesS(...).________



	/**
	 * retourne le validerRGSectionHitClasseLargeurChausseesSRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseesSRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseLargeurChausseesSRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseLargeurChausseesSRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClasseLargeurChausseesSRenseigne01
					, fournirKeyValiderRGSectionHitClasseLargeurChausseesSRenseigne01()
					, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseLargeurChausseesSRenseigne01().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseLargeurChausseesSRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classeLargeurChausseesS.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseLargeurChausseesSRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitClasseLargeurChausseesSRenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseesSRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseesSRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseLargeurChausseesSRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseLargeurChausseesSRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseLargeurChausseesSRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitClasseLargeurChausseesSRenseigne01();
	} // Fin de getValiderRGSectionHitClasseLargeurChausseesSRenseigne01().
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseesSRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitClasseLargeurChausseesSRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseLargeurChausseesSRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClasseLargeurChausseesSRenseigne01
							, fournirKeyValiderRGSectionHitClasseLargeurChausseesSRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseLargeurChausseesSRenseigne01(...).



	/**
	 * retourne le validerRGSectionHitClasseLargeurChausseesSRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseesSRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseLargeurChausseesSRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseLargeurChausseesSRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClasseLargeurChausseesSRegex02
					, fournirKeyValiderRGSectionHitClasseLargeurChausseesSRegex02()
					, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseLargeurChausseesSRegex02().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseLargeurChausseesSRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classeLargeurChausseesS.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseLargeurChausseesSRegex02() {
		return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitClasseLargeurChausseesSRegex02().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseesSRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseesSRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseLargeurChausseesSRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseLargeurChausseesSRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseLargeurChausseesSRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitClasseLargeurChausseesSRegex02();
	} // Fin de getValiderRGSectionHitClasseLargeurChausseesSRegex02().____
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseesSRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitClasseLargeurChausseesSRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseLargeurChausseesSRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClasseLargeurChausseesSRegex02
							, fournirKeyValiderRGSectionHitClasseLargeurChausseesSRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseLargeurChausseesSRegex02(...)._



	/**
	 * retourne le validerRGSectionHitClasseLargeurChausseesSNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseesSNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseLargeurChausseesSNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseLargeurChausseesSNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClasseLargeurChausseesSNomenclature03
					, fournirKeyValiderRGSectionHitClasseLargeurChausseesSNomenclature03()
					, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseLargeurChausseesSNomenclature03().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseLargeurChausseesSNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classeLargeurChausseesS.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseLargeurChausseesSNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitClasseLargeurChausseesSNomenclature03().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseesSNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseesSNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseLargeurChausseesSNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseLargeurChausseesSNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseLargeurChausseesSNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitClasseLargeurChausseesSNomenclature03();
	} // Fin de getValiderRGSectionHitClasseLargeurChausseesSNomenclature03().
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseesSNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitClasseLargeurChausseesSNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseLargeurChausseesSNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClasseLargeurChausseesSNomenclature03
							, fournirKeyValiderRGSectionHitClasseLargeurChausseesSNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseLargeurChausseesSNomenclature03(...).
	
	
	
	/* 16 - typeReseau. **************/
	/**
	 * retourne le <code>validerRGSectionHitTypeReseau</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>typeReseau</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>typeReseau</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeReseau stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitTypeReseau 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitTypeReseau() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitTypeReseau
					, fournirKeyValiderRGSectionHitTypeReseau()
					, STRING_VALIDER_SECTIONHIT_TYPERESEAU_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitTypeReseau().____________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitTypeReseau 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.typeReseau".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_TYPERESEAU : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitTypeReseau() {
		return KEY_VALIDER_SECTIONHIT_TYPERESEAU;
	} // Fin de fournirKeyValiderRGSectionHitTypeReseau()._________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitTypeReseau par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitTypeReseau 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitTypeReseau stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitTypeReseau : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitTypeReseau() 
													throws Exception {
		return fournirValiderRGSectionHitTypeReseau();
	} // Fin de getValiderRGSectionHitTypeReseau().________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitTypeReseau par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitTypeReseau.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitTypeReseau(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitTypeReseau
							, fournirKeyValiderRGSectionHitTypeReseau());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitTypeReseau(...)._____________________



	/**
	 * retourne le validerRGSectionHitTypeReseauRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeReseauRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitTypeReseauRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitTypeReseauRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitTypeReseauRenseigne01
					, fournirKeyValiderRGSectionHitTypeReseauRenseigne01()
					, STRING_VALIDER_SECTIONHIT_TYPERESEAU_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitTypeReseauRenseigne01()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitTypeReseauRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.typeReseau.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_TYPERESEAU_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitTypeReseauRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_TYPERESEAU_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitTypeReseauRenseigne01().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitTypeReseauRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeReseauRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitTypeReseauRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitTypeReseauRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitTypeReseauRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitTypeReseauRenseigne01();
	} // Fin de getValiderRGSectionHitTypeReseauRenseigne01()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitTypeReseauRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitTypeReseauRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitTypeReseauRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitTypeReseauRenseigne01
							, fournirKeyValiderRGSectionHitTypeReseauRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitTypeReseauRenseigne01(...).__________



	/**
	 * retourne le validerRGSectionHitTypeReseauRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeReseauRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitTypeReseauRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitTypeReseauRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitTypeReseauRegex02
					, fournirKeyValiderRGSectionHitTypeReseauRegex02()
					, STRING_VALIDER_SECTIONHIT_TYPERESEAU_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitTypeReseauRegex02()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitTypeReseauRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.typeReseau.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_TYPERESEAU_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitTypeReseauRegex02() {
		return KEY_VALIDER_SECTIONHIT_TYPERESEAU_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitTypeReseauRegex02().___________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitTypeReseauRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeReseauRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitTypeReseauRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitTypeReseauRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitTypeReseauRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitTypeReseauRegex02();
	} // Fin de getValiderRGSectionHitTypeReseauRegex02()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitTypeReseauRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitTypeReseauRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitTypeReseauRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitTypeReseauRegex02
							, fournirKeyValiderRGSectionHitTypeReseauRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitTypeReseauRegex02(...).______________



	/**
	 * retourne le validerRGSectionHitTypeReseauNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeReseauNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitTypeReseauNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitTypeReseauNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitTypeReseauNomenclature03
					, fournirKeyValiderRGSectionHitTypeReseauNomenclature03()
					, STRING_VALIDER_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitTypeReseauNomenclature03().______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitTypeReseauNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.typeReseau.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitTypeReseauNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitTypeReseauNomenclature03().___



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitTypeReseauNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeReseauNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitTypeReseauNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitTypeReseauNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitTypeReseauNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitTypeReseauNomenclature03();
	} // Fin de getValiderRGSectionHitTypeReseauNomenclature03().__________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitTypeReseauNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitTypeReseauNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitTypeReseauNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitTypeReseauNomenclature03
							, fournirKeyValiderRGSectionHitTypeReseauNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitTypeReseauNomenclature03(...)._______
	
	
	
	/* 17 - pRoupK. **************/
	/**
	 * retourne le <code>validerRGSectionHitPRoupK</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>pRoupK</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>pRoupK</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPRoupK stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPRoupK 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPRoupK() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitPRoupK
					, fournirKeyValiderRGSectionHitPRoupK()
					, STRING_VALIDER_SECTIONHIT_PROUPK_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPRoupK().________________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPRoupK 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pRoupK".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PROUPK : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPRoupK() {
		return KEY_VALIDER_SECTIONHIT_PROUPK;
	} // Fin de fournirKeyValiderRGSectionHitPRoupK()._____________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPRoupK par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitPRoupK 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPRoupK stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPRoupK : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPRoupK() 
													throws Exception {
		return fournirValiderRGSectionHitPRoupK();
	} // Fin de getValiderRGSectionHitPRoupK().____________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPRoupK par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitPRoupK.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPRoupK(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitPRoupK
							, fournirKeyValiderRGSectionHitPRoupK());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPRoupK(...)._________________________



	/**
	 * retourne le validerRGSectionHitPRoupKRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPRoupKRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPRoupKRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPRoupKRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPRoupKRenseigne01
					, fournirKeyValiderRGSectionHitPRoupKRenseigne01()
					, STRING_VALIDER_SECTIONHIT_PROUPK_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPRoupKRenseigne01()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPRoupKRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pRoupK.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PROUPK_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPRoupKRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_PROUPK_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitPRoupKRenseigne01().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPRoupKRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPRoupKRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPRoupKRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPRoupKRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPRoupKRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitPRoupKRenseigne01();
	} // Fin de getValiderRGSectionHitPRoupKRenseigne01()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPRoupKRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitPRoupKRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPRoupKRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPRoupKRenseigne01
							, fournirKeyValiderRGSectionHitPRoupKRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPRoupKRenseigne01(...).______________



	/**
	 * retourne le validerRGSectionHitPRoupKRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPRoupKRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPRoupKRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPRoupKRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPRoupKRegex02
					, fournirKeyValiderRGSectionHitPRoupKRegex02()
					, STRING_VALIDER_SECTIONHIT_PROUPK_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPRoupKRegex02()._________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPRoupKRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pRoupK.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PROUPK_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPRoupKRegex02() {
		return KEY_VALIDER_SECTIONHIT_PROUPK_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitPRoupKRegex02().______________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPRoupKRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPRoupKRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPRoupKRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPRoupKRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPRoupKRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitPRoupKRegex02();
	} // Fin de getValiderRGSectionHitPRoupKRegex02()._____________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPRoupKRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitPRoupKRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPRoupKRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPRoupKRegex02
							, fournirKeyValiderRGSectionHitPRoupKRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPRoupKRegex02(...).__________________



	/**
	 * retourne le validerRGSectionHitPRoupKNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPRoupKNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPRoupKNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPRoupKNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPRoupKNomenclature03
					, fournirKeyValiderRGSectionHitPRoupKNomenclature03()
					, STRING_VALIDER_SECTIONHIT_PROUPK_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPRoupKNomenclature03().__________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPRoupKNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pRoupK.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PROUPK_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPRoupKNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_PROUPK_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitPRoupKNomenclature03()._______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPRoupKNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPRoupKNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPRoupKNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPRoupKNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPRoupKNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitPRoupKNomenclature03();
	} // Fin de getValiderRGSectionHitPRoupKNomenclature03().______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPRoupKNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitPRoupKNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPRoupKNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPRoupKNomenclature03
							, fournirKeyValiderRGSectionHitPRoupKNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPRoupKNomenclature03(...).___________
	
	
	
	/* 18 - lieuDitOrigine. **************/
	/**
	 * retourne le <code>validerRGSectionHitLieuDitOrigine</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>lieuDitOrigine</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>lieuDitOrigine</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitOrigine stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLieuDitOrigine 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLieuDitOrigine() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitLieuDitOrigine
					, fournirKeyValiderRGSectionHitLieuDitOrigine()
					, STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLieuDitOrigine().________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLieuDitOrigine 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.lieuDitOrigine".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLieuDitOrigine() {
		return KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE;
	} // Fin de fournirKeyValiderRGSectionHitLieuDitOrigine()._____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLieuDitOrigine par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitLieuDitOrigine 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLieuDitOrigine stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLieuDitOrigine : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLieuDitOrigine() 
													throws Exception {
		return fournirValiderRGSectionHitLieuDitOrigine();
	} // Fin de getValiderRGSectionHitLieuDitOrigine().____________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLieuDitOrigine par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitLieuDitOrigine.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLieuDitOrigine(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitLieuDitOrigine
							, fournirKeyValiderRGSectionHitLieuDitOrigine());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLieuDitOrigine(...)._________________



	/**
	 * retourne le validerRGSectionHitLieuDitOrigineRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitOrigineRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLieuDitOrigineRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLieuDitOrigineRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitLieuDitOrigineRenseigne01
					, fournirKeyValiderRGSectionHitLieuDitOrigineRenseigne01()
					, STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLieuDitOrigineRenseigne01()._____
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLieuDitOrigineRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.lieuDitOrigine.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLieuDitOrigineRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitLieuDitOrigineRenseigne01().__



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLieuDitOrigineRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitOrigineRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLieuDitOrigineRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLieuDitOrigineRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLieuDitOrigineRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitLieuDitOrigineRenseigne01();
	} // Fin de getValiderRGSectionHitLieuDitOrigineRenseigne01()._________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLieuDitOrigineRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitLieuDitOrigineRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLieuDitOrigineRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitLieuDitOrigineRenseigne01
							, fournirKeyValiderRGSectionHitLieuDitOrigineRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLieuDitOrigineRenseigne01(...).______



	/**
	 * retourne le validerRGSectionHitLieuDitOrigineRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitOrigineRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLieuDitOrigineRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLieuDitOrigineRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitLieuDitOrigineRegex02
					, fournirKeyValiderRGSectionHitLieuDitOrigineRegex02()
					, STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLieuDitOrigineRegex02()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLieuDitOrigineRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.lieuDitOrigine.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLieuDitOrigineRegex02() {
		return KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitLieuDitOrigineRegex02().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLieuDitOrigineRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitOrigineRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLieuDitOrigineRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLieuDitOrigineRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLieuDitOrigineRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitLieuDitOrigineRegex02();
	} // Fin de getValiderRGSectionHitLieuDitOrigineRegex02()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLieuDitOrigineRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitLieuDitOrigineRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLieuDitOrigineRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitLieuDitOrigineRegex02
							, fournirKeyValiderRGSectionHitLieuDitOrigineRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLieuDitOrigineRegex02(...).__________
	
	
	
	/* 19 - prOrigine. **************/
	/**
	 * retourne le <code>validerRGSectionHitPrOrigine</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>prOrigine</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>prOrigine</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPrOrigine stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPrOrigine 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPrOrigine() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitPrOrigine
					, fournirKeyValiderRGSectionHitPrOrigine()
					, STRING_VALIDER_SECTIONHIT_PRORIGINE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPrOrigine()._____________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPrOrigine 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.prOrigine".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PRORIGINE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPrOrigine() {
		return KEY_VALIDER_SECTIONHIT_PRORIGINE;
	} // Fin de fournirKeyValiderRGSectionHitPrOrigine().__________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPrOrigine par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitPrOrigine 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPrOrigine stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPrOrigine : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPrOrigine() 
													throws Exception {
		return fournirValiderRGSectionHitPrOrigine();
	} // Fin de getValiderRGSectionHitPrOrigine()._________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPrOrigine par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitPrOrigine.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPrOrigine(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitPrOrigine
							, fournirKeyValiderRGSectionHitPrOrigine());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPrOrigine(...).______________________



	/**
	 * retourne le validerRGSectionHitPrOrigineRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPrOrigineRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPrOrigineRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPrOrigineRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPrOrigineRenseigne01
					, fournirKeyValiderRGSectionHitPrOrigineRenseigne01()
					, STRING_VALIDER_SECTIONHIT_PRORIGINE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPrOrigineRenseigne01().__________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPrOrigineRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.prOrigine.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PRORIGINE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPrOrigineRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_PRORIGINE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitPrOrigineRenseigne01()._______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPrOrigineRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPrOrigineRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPrOrigineRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPrOrigineRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPrOrigineRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitPrOrigineRenseigne01();
	} // Fin de getValiderRGSectionHitPrOrigineRenseigne01().______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPrOrigineRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitPrOrigineRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPrOrigineRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPrOrigineRenseigne01
							, fournirKeyValiderRGSectionHitPrOrigineRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPrOrigineRenseigne01(...).___________



	/**
	 * retourne le validerRGSectionHitPrOrigineRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPrOrigineRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPrOrigineRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPrOrigineRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPrOrigineRegex02
					, fournirKeyValiderRGSectionHitPrOrigineRegex02()
					, STRING_VALIDER_SECTIONHIT_PRORIGINE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPrOrigineRegex02().______________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPrOrigineRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.prOrigine.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PRORIGINE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPrOrigineRegex02() {
		return KEY_VALIDER_SECTIONHIT_PRORIGINE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitPrOrigineRegex02().___________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPrOrigineRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPrOrigineRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPrOrigineRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPrOrigineRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPrOrigineRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitPrOrigineRegex02();
	} // Fin de getValiderRGSectionHitPrOrigineRegex02().__________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPrOrigineRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitPrOrigineRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPrOrigineRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPrOrigineRegex02
							, fournirKeyValiderRGSectionHitPrOrigineRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPrOrigineRegex02(...)._______________
		
			
	
} // FIN DE LA CLASSE SectionHitGestionnairePreferencesRG.-------------------
