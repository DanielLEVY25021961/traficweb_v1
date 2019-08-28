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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesbundles.BundleConfigurationProjetManager;
import levy.daniel.application.model.metier.sections.impl.SectionHit;

/**
 * CLASSE SectionHitGestionnairePreferencesControles :<br/>
 * Classe UTILITAIRE (<i>final avec toutes les méthodes static</i>) chargée de :
 * <ul>
 * <li><b>gérer les <i>messages</i> de validation des RG comme des "préférences"</b> 
 * (paramétrées 1 fois par l'Administrateur puis stockées 
 * dans un fichier .properties) qui conservent la même valeur 
 * tant qu'elles sont inchangées.</li>
 * <li>permettre à l'ADMINISTRATEUR DE DONNEES de la MOA 
 * de <b>modifier ces messages à l'attention des utilisateurs</b>.</li>
 * <li><b>gérer les préférences relatives aux REGLES DE GESTION (RG) applicables 
 * à un OBJET METIER : {@link SectionHit}</b>.</li>
 * <li>fournir à toute l'application des <b>SINGLETONS</b> des 
 * <b>messages de validation par défaut 
 * (actuellement stockés dans un .properties) des Règles de Gestion (RG)</b>
 *  d'un OBJET METIER pour chaque attribut et chaque RG de l'attribut</li>
 * <li>gérer comme des préférences les <b>messages 
 * émis par les contrôles de validation des RG</b>.</li>
 * <li><b>créer un fichier .properties initial</b> avec des valeurs (messages) 
 * codées en dur dans la classe en cas de défaut de livraison du 
 * <code><b>ressources_externes/preferences/metier/sections/
 * OBJETMETIER_CONTROLES.properties</b></code></li>
 * </ul>
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
public final class SectionHitGestionnairePreferencesControles {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe SectionHitGestionnairePreferencesControles".<br/>
	 */
	public static final String CLASSE_SECTIONHIT_GESTIONNAIRE_PREFS_CONTROLES 
		= "Classe SectionHitGestionnairePreferencesControles";

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
	 * '='.<br/>
	 */
	public static final char EGAL = '=';
	
	/**
	 * "méthode lireStringsDansFile(File pFile, Charset pCharset)".<br/>
	 */
	public static final String METHODE_LIRE_STRINGS_DANS_FILE 
		= "méthode lireStringsDansFile(File pFile, Charset pCharset)";

	// CONTROLES ***************************************************
	/* 1 - numDepartement. **************/
	/**
	 * clé de messageSectionHitNumDepartementRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.numdepartement.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01 
		= "message.SectionHit.numdepartement.renseigne";
	
	/**
	 * messageSectionHitNumDepartementRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitNumDepartementRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le numéro de département de la section HIT (colonnes [1-3] du HIT) 
	 * doit être renseigné (obligatoire)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR 
		= "le numéro de département de la section HIT (colonnes [1-3] du HIT) doit être renseigné (obligatoire)";
		
	/**
	 * clé de messageSectionHitNumDepartementRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.numdepartement.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02 
		= "message.SectionHit.numdepartement.regex";
	
	/**
	 * messageSectionHitNumDepartementRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitNumDepartementRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le numéro de département doit comporter exactement 
	 * 3 chiffres dans les colonnes [1-3] du HIT
	 * ('030' pour 03, '300' pour 30, '972' pour 972)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR 
		= "le numéro de département doit comporter exactement 3 chiffres dans les colonnes [1-3] du HIT ('030' pour 03, '300' pour 30, '972' pour 972)";
	
	/* 2 - numSection. **************/
	/**
	 * clé de messageSectionHitNumSectionRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.numsection.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_NUMSECTION_RENSEIGNE_01 
		= "message.SectionHit.numsection.renseigne";
	
	/**
	 * messageSectionHitNumSectionRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitNumSectionRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le numéro de section de la section HIT (colonnes [4-9] du HIT) doit être renseigné (obligatoire)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_NUMSECTION_RENSEIGNE_01_EN_DUR 
		= "le numéro de section de la section HIT (colonnes [4-9] du HIT) doit être renseigné (obligatoire)";
		
	/**
	 * clé de messageSectionHitNumSectionRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.numsection.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_NUMSECTION_REGEX_02 
		= "message.SectionHit.numsection.regex";
	
	/**
	 * messageSectionHitNumSectionRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitNumSectionRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le numéro de section doit comporter exactement 6 chiffres 
	 * (Numéro de section sur 4 chiffres significatifs maxi complété 
	 * éventuellement avec des 0 à gauche et indice sur 2 chiffres 
	 * significatifs maxi complété éventuellement avec des 0 à gauche. 
	 * Exemples : section 26 indice 4 = 002604, section 1 indice 0 = 000100
	 * , section 162 indice 65 = 016265) - (colonnes [4-9] du HIT)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_NUMSECTION_REGEX_02_EN_DUR 
		= "le numéro de section doit comporter exactement 6 chiffres "
				+ "(Numéro de section sur 4 chiffres significatifs maxi "
				+ "complété éventuellement avec des 0 à gauche et indice "
				+ "sur 2 chiffres significatifs maxi complété éventuellement "
				+ "avec des 0 à gauche. "
				+ "Exemples : section 26 indice 4 = 002604"
				+ ", section 1 indice 0 = 000100"
				+ ", section 162 indice 65 = 016265) - (colonnes [4-9] du HIT)";
	
	/* 3 - sens. ***********/
	/**
	 * clé de messageSectionHitSensRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.sens.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_SENS_RENSEIGNE_01 
		= "message.SectionHit.sens.renseigne";
	
	/**
	 * messageSectionHitSensRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitSensRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le sens de la section HIT (colonne [10] du HIT) doit être renseigné (obligatoire)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_SENS_RENSEIGNE_01_EN_DUR 
		= "le sens de la section HIT (colonne [10] du HIT) doit être renseigné (obligatoire)";
		
	/**
	 * clé de messageSectionHitSensRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.sens.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_SENS_REGEX_02 
		= "message.SectionHit.sens.regex";
	
	/**
	 * messageSectionHitSensRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitSensRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le sens de la section HIT (colonne [10] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_SENS_REGEX_02_EN_DUR 
		= "le sens de la section HIT (colonne [10] du HIT) doit comporter exactement 1 chiffre";
		
	/**
	 * clé de messageSectionHitSensNomenclature03 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.sens.nomenclature"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_SENS_NOMENCLATURE_03 
		= "message.SectionHit.sens.nomenclature";
	
	/**
	 * messageSectionHitSensNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitSensNomenclature03 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le sens de la section HIT (colonne [10] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_SENS_NOMENCLATURE_03_EN_DUR 
		= "le sens de la section HIT (colonne [10] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]";
	
	/* 4 - nature. **************/
	/**
	 * clé de messageSectionHitNatureRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.nature.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_NATURE_RENSEIGNE_01 
		= "message.SectionHit.nature.renseigne";
	
	/**
	 * messageSectionHitNatureRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitNatureRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "la nature de la section HIT (colonne [11] du HIT) doit être renseignée (obligatoire)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_NATURE_RENSEIGNE_01_EN_DUR 
		= "la nature de la section HIT (colonne [11] du HIT) doit être renseignée (obligatoire)";
		
	/**
	 * clé de messageSectionHitNatureRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.nature.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_NATURE_REGEX_02 
		= "message.SectionHit.nature.regex";
	
	/**
	 * messageSectionHitNatureRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitNatureRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "la nature de la section HIT (colonne [10] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_NATURE_REGEX_02_EN_DUR 
		= "la nature de la section HIT (colonne [11] du HIT) doit comporter exactement 1 chiffre";
		
	/**
	 * clé de messageSectionHitNatureNomenclature03 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.nature.nomenclature"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_NATURE_NOMENCLATURE_03 
		= "message.SectionHit.nature.nomenclature";
	
	/**
	 * messageSectionHitNatureNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitNatureNomenclature03 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "la nature de la section HIT (colonne [11] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_NATURE_NOMENCLATURE_03_EN_DUR 
		= "la nature de la section HIT (colonne [11] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]";
	
	/* 5 - classe. **************/
	/**
	 * clé de messageSectionHitClasseRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.classe.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_CLASSE_RENSEIGNE_01 
		= "message.SectionHit.classe.renseigne";
	
	/**
	 * messageSectionHitClasseRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitClasseRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "la classe de la section HIT (colonnes [12-13] du HIT) doit être renseignée".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_CLASSE_RENSEIGNE_01_EN_DUR 
		= "la classe de la section HIT (colonnes [12-13] du HIT) doit être renseignée";
		
	/**
	 * clé de messageSectionHitClasseRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.classe.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_CLASSE_REGEX_02 
		= "message.SectionHit.classe.regex";
	
	/**
	 * messageSectionHitClasseRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitClasseRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "la classe de la section HIT (colonnes [12-13] du HIT) doit comporter exactement 2 chiffres ('00')".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_CLASSE_REGEX_02_EN_DUR 
		= "la classe de la section HIT (colonnes [12-13] du HIT) doit comporter exactement 2 chiffres ('00')";
	
	/* 6 - anneeTraitement. **************/
	/**
	 * clé de messageSectionHitAnneeTraitementRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.anneeTraitement.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01 
		= "message.SectionHit.anneeTraitement.renseigne";
	
	/**
	 * messageSectionHitAnneeTraitementRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitAnneeTraitementRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "l'année de traitement de la section HIT (colonnes [14-15] du HIT) doit être renseignée (obligatoire)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01_EN_DUR 
		= "l'année de traitement de la section HIT (colonnes [14-15] du HIT) doit être renseignée (obligatoire)";
		
	/**
	 * clé de messageSectionHitAnneeTraitementRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.anneeTraitement.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_ANNEETRAITEMENT_REGEX_02 
		= "message.SectionHit.anneeTraitement.regex";
	
	/**
	 * messageSectionHitAnneeTraitementRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitAnneeTraitementRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "l'année de traitement de la section HIT (colonnes [14-15] du HIT) doit comporter exactement 2 chiffres".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_ANNEETRAITEMENT_REGEX_02_EN_DUR 
		= "l'année de traitement de la section HIT (colonnes [14-15] du HIT) doit comporter exactement 2 chiffres";
	
	/* 7 - zoneLibre1. *******/
	/**
	 * clé de messageSectionHitZoneLibre1Renseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.zoneLibre1.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01 
		= "message.SectionHit.zoneLibre1.renseigne";
	
	/**
	 * messageSectionHitZoneLibre1Renseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitZoneLibre1Renseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "la zone libre de la section HIT (colonnes [16] du HIT) doit être renseignée (1 espace)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01_EN_DUR 
		= "la zone libre de la section HIT (colonnes [16] du HIT) doit être renseignée (1 espace)";
		
	/**
	 * clé de messageSectionHitZoneLibre1Regex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.zoneLibre1.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_ZONELIBRE1_REGEX_02 
		= "message.SectionHit.zoneLibre1.regex";
	
	/**
	 * messageSectionHitZoneLibre1Regex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitZoneLibre1Regex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "la zone libre de la section HIT (colonnes [16] du HIT) doit comporter exactement 1 espace".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_ZONELIBRE1_REGEX_02_EN_DUR 
		= "la zone libre de la section HIT (colonnes [16] du HIT) doit comporter exactement 1 espace";
	
	/* 8 - numRoute. *******/
	/**
	 * clé de messageSectionHitNumRouteRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.numRoute.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_NUMROUTE_RENSEIGNE_01 
		= "message.SectionHit.numRoute.renseigne";
	
	/**
	 * messageSectionHitNumRouteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitNumRouteRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le numéro de la route de la section HIT (colonnes [17-20] du HIT) doit être renseigné (obligatoire)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_NUMROUTE_RENSEIGNE_01_EN_DUR 
		= "le numéro de la route de la section HIT (colonnes [17-20] du HIT) doit être renseigné (obligatoire)";
		
	/**
	 * clé de messageSectionHitNumRouteRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.numRoute.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_NUMROUTE_REGEX_02 
		= "message.SectionHit.numRoute.regex";
	
	/**
	 * messageSectionHitNumRouteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitNumRouteRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le numéro de la route de la section HIT (colonnes [17-20] du HIT) doit comporter exactement 4 chiffres".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_NUMROUTE_REGEX_02_EN_DUR 
		= "le numéro de la route de la section HIT (colonnes [17-20] du HIT) doit comporter exactement 4 chiffres";
	
	/* 9 - indiceNumRoute. *******/
	/**
	 * clé de messageSectionHitIndiceNumRouteRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.indiceNumRoute.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01 
		= "message.SectionHit.indiceNumRoute.renseigne";
	
	/**
	 * messageSectionHitIndiceNumRouteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitIndiceNumRouteRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "l'indice numérique de la route de la section HIT (colonnes [21] du HIT) doit être renseigné".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01_EN_DUR 
		= "l'indice numérique de la route de la section HIT (colonnes [21] du HIT) doit être renseigné";
		
	/**
	 * clé de messageSectionHitIndiceNumRouteRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.indiceNumRoute.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_INDICENUMROUTE_REGEX_02 
		= "message.SectionHit.indiceNumRoute.regex";
	
	/**
	 * messageSectionHitIndiceNumRouteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitIndiceNumRouteRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "l'indice numérique de la route de la section HIT (colonnes [21] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_INDICENUMROUTE_REGEX_02_EN_DUR 
		= "l'indice numérique de la route de la section HIT (colonnes [21] du HIT) doit comporter exactement 1 chiffre";
	
	/* 10 - indiceLettreRoute. *******/
	/**
	 * clé de messageSectionHitIndiceLettreRouteRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.indiceLettreRoute.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01 
		= "message.SectionHit.indiceLettreRoute.renseigne";
	
	/**
	 * messageSectionHitIndiceLettreRouteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitIndiceLettreRouteRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "l'indice lettre de la route de la section HIT (colonnes [22] du HIT) doit être renseigné (ou espace pour pas d'indice)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01_EN_DUR 
		= "l'indice lettre de la route de la section HIT (colonnes [22] du HIT) doit être renseigné (ou espace pour pas d'indice)";
		
	/**
	 * clé de messageSectionHitIndiceLettreRouteRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.indiceLettreRoute.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_INDICELETTREROUTE_REGEX_02 
		= "message.SectionHit.indiceLettreRoute.regex";
	
	/**
	 * messageSectionHitIndiceLettreRouteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitIndiceLettreRouteRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "l'indice lettre de la route de la section HIT (colonnes [22] du HIT) doit comporter exactement 1 lettre (ou espace pour pas d'indice)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_INDICELETTREROUTE_REGEX_02_EN_DUR 
		= "l'indice lettre de la route de la section HIT (colonnes [22] du HIT) doit comporter exactement 1 lettre (ou espace pour pas d'indice)";
	
	/* 11 - categorieAdminRoute. *******/
	/**
	 * clé de messageSectionHitCategorieAdminRouteRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.categorieAdminRoute.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01 
		= "message.SectionHit.categorieAdminRoute.renseigne";
	
	/**
	 * messageSectionHitCategorieAdminRouteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitCategorieAdminRouteRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "la catégorie administrative de la route de la section HIT (colonne [23] du HIT) doit être renseignée (obligatoire)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01_EN_DUR 
		= "la catégorie administrative de la route de la section HIT (colonne [23] du HIT) doit être renseignée (obligatoire)";
		
	/**
	 * clé de messageSectionHitCategorieAdminRouteRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.categorieAdminRoute.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02 
		= "message.SectionHit.categorieAdminRoute.regex";
	
	/**
	 * messageSectionHitCategorieAdminRouteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitCategorieAdminRouteRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "la catégorie administrative de la route de la section HIT (colonne [23] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02_EN_DUR 
		= "la catégorie administrative de la route de la section HIT (colonne [23] du HIT) doit comporter exactement 1 chiffre";
				
	/**
	 * clé de messageSectionHitCategorieAdminRouteNomenclature03 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.categorieAdminRoute.nomenclature"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03 
		= "message.SectionHit.categorieAdminRoute.nomenclature";
	
	/**
	 * messageSectionHitCategorieAdminRouteNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitCategorieAdminRouteNomenclature03 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "la catégorie administrative de la route de la section HIT (colonne [23] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03_EN_DUR 
		= "la catégorie administrative de la route de la section HIT (colonne [23] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]";
	
	/* 12 - typeComptage. *******/
	/**
	 * clé de messageSectionHitTypeComptageRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.typeComptage.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01 
		= "message.SectionHit.typeComptage.renseigne";
	
	/**
	 * messageSectionHitTypeComptageRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitTypeComptageRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le type de comptage de la section HIT (colonne [24] du HIT) doit être renseigné".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01_EN_DUR 
		= "le type de comptage de la section HIT (colonne [24] du HIT) doit être renseigné";
		
	/**
	 * clé de messageSectionHitTypeComptageRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.typeComptage.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_TYPECOMPTAGE_REGEX_02 
		= "message.SectionHit.typeComptage.regex";
	
	/**
	 * messageSectionHitTypeComptageRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitTypeComptageRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le type de comptage de la section HIT (colonne [24] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_TYPECOMPTAGE_REGEX_02_EN_DUR 
		= "le type de comptage de la section HIT (colonne [24] du HIT) doit comporter exactement 1 chiffre";
				
	/**
	 * clé de messageSectionHitTypeComptageNomenclature03 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.typeComptage.nomenclature"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03 
		= "message.SectionHit.typeComptage.nomenclature";
	
	/**
	 * messageSectionHitTypeComptageNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitTypeComptageNomenclature03 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le type de comptage de la section HIT (colonne [24] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03_EN_DUR 
		= "le type de comptage de la section HIT (colonne [24] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]";
	
	/* 13 - classementRoute. *******/
	/**
	 * clé de messageSectionHitClassementRouteRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.classementRoute.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01 
		= "message.SectionHit.classementRoute.renseigne";
	
	/**
	 * messageSectionHitClassementRouteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitClassementRouteRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le classement de la route de la section HIT (colonne [25] du HIT) doit être renseigné".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01_EN_DUR 
		= "le classement de la route de la section HIT (colonne [25] du HIT) doit être renseigné";
		
	/**
	 * clé de messageSectionHitClassementRouteRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.classementRoute.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_CLASSEMENTROUTE_REGEX_02 
		= "message.SectionHit.classementRoute.regex";
	
	/**
	 * messageSectionHitClassementRouteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitClassementRouteRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le classement de la route de la section HIT (colonne [25] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_CLASSEMENTROUTE_REGEX_02_EN_DUR 
		= "le classement de la route de la section HIT (colonne [25] du HIT) doit comporter exactement 1 chiffre";
				
	/**
	 * clé de messageSectionHitClassementRouteNomenclature03 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.classementRoute.nomenclature"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03 
		= "message.SectionHit.classementRoute.nomenclature";
	
	/**
	 * messageSectionHitClassementRouteNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitClassementRouteNomenclature03 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le classement de la route de la section HIT (colonne [25] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03_EN_DUR 
		= "le classement de la route de la section HIT (colonne [25] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]";
	
	/* 14 - classeLargeurChausseeU. *******/
	/**
	 * clé de messageSectionHitClasseLargeurChausseeURenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.classeLargeurChausseeU.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01 
		= "message.SectionHit.classeLargeurChausseeU.renseigne";
	
	/**
	 * messageSectionHitClasseLargeurChausseeURenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitClasseLargeurChausseeURenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit être renseignée (0 si sans objet)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01_EN_DUR 
		= "la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit être renseignée (0 si sans objet)";
		
	/**
	 * clé de messageSectionHitClasseLargeurChausseeURegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.classeLargeurChausseeU.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02 
		= "message.SectionHit.classeLargeurChausseeU.regex";
	
	/**
	 * messageSectionHitClasseLargeurChausseeURegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitClasseLargeurChausseeURegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02_EN_DUR 
		= "la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit comporter exactement 1 chiffre";
				
	/**
	 * clé de messageSectionHitClasseLargeurChausseeUNomenclature03 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.classeLargeurChausseeU.nomenclature"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03 
		= "message.SectionHit.classeLargeurChausseeU.nomenclature";
	
	/**
	 * messageSectionHitClasseLargeurChausseeUNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitClasseLargeurChausseeUNomenclature03 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7, 8]".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03_EN_DUR 
		= "la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7, 8]";
	
	/* 15 - classeLargeurChausseesS. *******/
	/**
	 * clé de messageSectionHitClasseLargeurChausseesSRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.classeLargeurChausseesS.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01 
		= "message.SectionHit.classeLargeurChausseesS.renseigne";
	
	/**
	 * messageSectionHitClasseLargeurChausseesSRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitClasseLargeurChausseesSRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit être renseignée (0 si sans objet)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01_EN_DUR 
		= "la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit être renseignée (0 si sans objet)";
		
	/**
	 * clé de messageSectionHitClasseLargeurChausseesSRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.classeLargeurChausseesS.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02 
		= "message.SectionHit.classeLargeurChausseesS.regex";
	
	/**
	 * messageSectionHitClasseLargeurChausseesSRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitClasseLargeurChausseesSRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02_EN_DUR 
		= "la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit comporter exactement 1 chiffre";
				
	/**
	 * clé de messageSectionHitClasseLargeurChausseesSNomenclature03 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.classeLargeurChausseesS.nomenclature"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03 
		= "message.SectionHit.classeLargeurChausseesS.nomenclature";
	
	/**
	 * messageSectionHitClasseLargeurChausseesSNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitClasseLargeurChausseesSNomenclature03 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7]".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03_EN_DUR 
		= "la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7]";
	
	/* 16 - typeReseau. *******/
	/**
	 * clé de messageSectionHitTypeReseauRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.typeReseau.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_TYPERESEAU_RENSEIGNE_01 
		= "message.SectionHit.typeReseau.renseigne";
	
	/**
	 * messageSectionHitTypeReseauRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitTypeReseauRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le type de réseau de la section HIT (colonne [28] du HIT) doit être renseigné (0 si autre que autoroute)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_TYPERESEAU_RENSEIGNE_01_EN_DUR 
		= "le type de réseau de la section HIT (colonne [28] du HIT) doit être renseigné (0 si autre que autoroute)";
		
	/**
	 * clé de messageSectionHitTypeReseauRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.typeReseau.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_TYPERESEAU_REGEX_02 
		= "message.SectionHit.typeReseau.regex";
	
	/**
	 * messageSectionHitTypeReseauRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitTypeReseauRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le type de réseau de la section HIT (colonne [28] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_TYPERESEAU_REGEX_02_EN_DUR 
		= "le type de réseau de la section HIT (colonne [28] du HIT) doit comporter exactement 1 chiffre";
				
	/**
	 * clé de messageSectionHitTypeReseauNomenclature03 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.typeReseau.nomenclature"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03 
		= "message.SectionHit.typeReseau.nomenclature";
	
	/**
	 * messageSectionHitTypeReseauNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitTypeReseauNomenclature03 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le type de réseau de la section HIT (colonne [28] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4]".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03_EN_DUR 
		= "le type de réseau de la section HIT (colonne [28] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4]";
	
	/* 17 - pRoupK. *******/
	/**
	 * clé de messageSectionHitPRoupKRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.pRoupK.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_PROUPK_RENSEIGNE_01 
		= "message.SectionHit.pRoupK.renseigne";
	
	/**
	 * messageSectionHitPRoupKRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitPRoupKRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "PR ou PK de la section HIT (colonne [29] du HIT) doit être renseigné".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_PROUPK_RENSEIGNE_01_EN_DUR 
		= "PR ou PK de la section HIT (colonne [29] du HIT) doit être renseigné";
		
	/**
	 * clé de messageSectionHitPRoupKRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.pRoupK.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_PROUPK_REGEX_02 
		= "message.SectionHit.pRoupK.regex";
	
	/**
	 * messageSectionHitPRoupKRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitPRoupKRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "PR ou PK de la section HIT (colonne [29] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_PROUPK_REGEX_02_EN_DUR 
		= "PR ou PK de la section HIT (colonne [29] du HIT) doit comporter exactement 1 chiffre";
				
	/**
	 * clé de messageSectionHitPRoupKNomenclature03 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.pRoupK.nomenclature"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_PROUPK_NOMENCLATURE_03 
		= "message.SectionHit.pRoupK.nomenclature";
	
	/**
	 * messageSectionHitPRoupKNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitPRoupKNomenclature03 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "PR ou PK de la section HIT (colonne [29] du HIT) doit respecter une nomenclature [1, 2]".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_PROUPK_NOMENCLATURE_03_EN_DUR 
		= "PR ou PK de la section HIT (colonne [29] du HIT) doit respecter une nomenclature [1, 2]";
	
	/* 18 - lieuDitOrigine. *******/
	/**
	 * clé de messageSectionHitLieuDitOrigineRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.lieuDitOrigine.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01 
		= "message.SectionHit.lieuDitOrigine.renseigne";
	
	/**
	 * messageSectionHitLieuDitOrigineRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitLieuDitOrigineRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le lieu-dit origine de la section HIT (colonnes [30-49] du HIT) doit être renseigné".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01_EN_DUR 
		= "le lieu-dit origine de la section HIT (colonnes [30-49] du HIT) doit être renseigné";
		
	/**
	 * clé de messageSectionHitLieuDitOrigineRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.lieuDitOrigine.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_LIEUDITORIGINE_REGEX_02 
		= "message.SectionHit.lieuDitOrigine.regex";
	
	/**
	 * messageSectionHitLieuDitOrigineRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitLieuDitOrigineRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le lieu-dit origine de la section HIT (colonnes [30-49] du HIT) doit comporter exactement 20 caractères".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_LIEUDITORIGINE_REGEX_02_EN_DUR 
		= "le lieu-dit origine de la section HIT (colonnes [30-49] du HIT) doit comporter exactement 20 caractères";
	
	/* 19 - prOrigine. *******/
	/**
	 * clé de messageSectionHitPrOrigineRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.prOrigine.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_PRORIGINE_RENSEIGNE_01 
		= "message.SectionHit.prOrigine.renseigne";
	
	/**
	 * messageSectionHitPrOrigineRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitPrOrigineRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le PR origine de la section HIT (colonnes [50-52] du HIT) doit être renseigné (obligatoire)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_PRORIGINE_RENSEIGNE_01_EN_DUR 
		= "le PR origine de la section HIT (colonnes [50-52] du HIT) doit être renseigné (obligatoire)";
		
	/**
	 * clé de messageSectionHitPrOrigineRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.prOrigine.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_PRORIGINE_REGEX_02 
		= "message.SectionHit.prOrigine.regex";
	
	/**
	 * messageSectionHitPrOrigineRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitPrOrigineRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le PR origine de la section HIT (colonnes [50-52] du HIT) doit comporter exactement 3 chiffres".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_PRORIGINE_REGEX_02_EN_DUR 
		= "le PR origine de la section HIT (colonnes [50-52] du HIT) doit comporter exactement 3 chiffres";
		
	/**
	 * clé de messageSectionHitPrOrigineNumerique03 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.prOrigine.numerique"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_PRORIGINE_NUMERIQUE_03 
		= "message.SectionHit.prOrigine.numerique";
	
	/**
	 * messageSectionHitPrOrigineNumerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitPrOrigineNumerique03 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le PR origine de la section HIT (colonnes [50-52] du HIT) doit être homogène à un entier".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_PRORIGINE_NUMERIQUE_03_EN_DUR 
		= "le PR origine de la section HIT (colonnes [50-52] du HIT) doit être homogène à un entier";
	
	/* 20 - absOrigine. *******/
	/**
	 * clé de messageSectionHitAbsOrigineRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.absOrigine.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_ABSORIGINE_RENSEIGNE_01 
		= "message.SectionHit.absOrigine.renseigne";
	
	/**
	 * messageSectionHitAbsOrigineRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitAbsOrigineRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit être renseignée (obligatoire)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_ABSORIGINE_RENSEIGNE_01_EN_DUR 
		= "l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit être renseignée (obligatoire)";
		
	/**
	 * clé de messageSectionHitAbsOrigineRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.absOrigine.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_ABSORIGINE_REGEX_02 
		= "message.SectionHit.absOrigine.regex";
	
	/**
	 * messageSectionHitAbsOrigineRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitAbsOrigineRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit comporter exactement 4 chiffres".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_ABSORIGINE_REGEX_02_EN_DUR 
		= "l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit comporter exactement 4 chiffres";
		
	/**
	 * clé de messageSectionHitAbsOrigineNumerique03 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.absOrigine.numerique"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_ABSORIGINE_NUMERIQUE_03 
		= "message.SectionHit.absOrigine.numerique";
	
	/**
	 * messageSectionHitAbsOrigineNumerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitAbsOrigineNumerique03 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit être homogène à un entier".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_ABSORIGINE_NUMERIQUE_03_EN_DUR 
		= "l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit être homogène à un entier";
	
	/* 21 - lieuDitExtremite. *******/
	/**
	 * clé de messageSectionHitLieuDitExtremiteRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.lieuDitExtremite.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01 
		= "message.SectionHit.lieuDitExtremite.renseigne";
	
	/**
	 * messageSectionHitLieuDitExtremiteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitLieuDitExtremiteRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le lieu-dit extremité de la section HIT (colonnes [57-76] du HIT) doit être renseigné".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01_EN_DUR 
		= "le lieu-dit extremité de la section HIT (colonnes [57-76] du HIT) doit être renseigné";
		
	/**
	 * clé de messageSectionHitLieuDitExtremiteRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.lieuDitExtremite.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02 
		= "message.SectionHit.lieuDitExtremite.regex";
	
	/**
	 * messageSectionHitLieuDitExtremiteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitLieuDitExtremiteRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le lieu-dit extremité de la section HIT (colonnes [57-76] du HIT) doit comporter exactement 20 caractères".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02_EN_DUR 
		= "le lieu-dit extremité de la section HIT (colonnes [57-76] du HIT) doit comporter exactement 20 caractères";
	
	/* 22 - prExtremite. *******/
	/**
	 * clé de messageSectionHitPrExtremiteRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.prExtremite.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_PREXTREMITE_RENSEIGNE_01 
		= "message.SectionHit.prExtremite.renseigne";
	
	/**
	 * messageSectionHitPrExtremiteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitPrExtremiteRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le PR extremité de la section HIT (colonnes [77-79] du HIT) doit être renseigné (obligatoire)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_PREXTREMITE_RENSEIGNE_01_EN_DUR 
		= "le PR extremité de la section HIT (colonnes [77-79] du HIT) doit être renseigné (obligatoire)";
		
	/**
	 * clé de messageSectionHitPrExtremiteRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.prExtremite.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_PREXTREMITE_REGEX_02 
		= "message.SectionHit.prExtremite.regex";
	
	/**
	 * messageSectionHitPrExtremiteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitPrExtremiteRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le PR extremité de la section HIT (colonnes [77-79] du HIT) doit comporter exactement 3 chiffres".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_PREXTREMITE_REGEX_02_EN_DUR 
		= "le PR extremité de la section HIT (colonnes [77-79] du HIT) doit comporter exactement 3 chiffres";
		
	/**
	 * clé de messageSectionHitPrExtremiteNumerique03 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.prExtremite.numerique"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_PREXTREMITE_NUMERIQUE_03 
		= "message.SectionHit.prExtremite.numerique";
	
	/**
	 * messageSectionHitPrExtremiteNumerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitPrExtremiteNumerique03 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le PR extremité de la section HIT (colonnes [77-79] du HIT) doit être homogène à un entier".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_PREXTREMITE_NUMERIQUE_03_EN_DUR 
		= "le PR extremité de la section HIT (colonnes [77-79] du HIT) doit être homogène à un entier";
	
	/* 23 - absExtremite. *******/
	/**
	 * clé de messageSectionHitAbsExtremiteRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.absExtremite.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01 
		= "message.SectionHit.absExtremite.renseigne";
	
	/**
	 * messageSectionHitAbsExtremiteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitAbsExtremiteRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit être renseignée (obligatoire)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01_EN_DUR 
		= "l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit être renseignée (obligatoire)";
		
	/**
	 * clé de messageSectionHitAbsExtremiteRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.absExtremite.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_ABSEXTREMITE_REGEX_02 
		= "message.SectionHit.absExtremite.regex";
	
	/**
	 * messageSectionHitAbsExtremiteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitAbsExtremiteRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit comporter exactement 4 chiffres".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_ABSEXTREMITE_REGEX_02_EN_DUR 
		= "l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit comporter exactement 4 chiffres";
		
	/**
	 * clé de messageSectionHitAbsExtremiteNumerique03 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.absExtremite.numerique"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03 
		= "message.SectionHit.absExtremite.numerique";
	
	/**
	 * messageSectionHitAbsExtremiteNumerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitAbsExtremiteNumerique03 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit être homogène à un entier".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03_EN_DUR 
		= "l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit être homogène à un entier";
	
	/* 24 - lieuDitComptage. *******/
	/**
	 * clé de messageSectionHitLieuDitComptageRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.lieuDitComptage.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01 
		= "message.SectionHit.lieuDitComptage.renseigne";
	
	/**
	 * messageSectionHitLieuDitComptageRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitLieuDitComptageRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le lieu-dit de comptage de la section HIT (colonnes [84-103] du HIT) doit être renseigné".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01_EN_DUR 
		= "le lieu-dit de comptage de la section HIT (colonnes [84-103] du HIT) doit être renseigné";
		
	/**
	 * clé de messageSectionHitLieuDitComptageRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.lieuDitComptage.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02 
		= "message.SectionHit.lieuDitComptage.regex";
	
	/**
	 * messageSectionHitLieuDitComptageRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitLieuDitComptageRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le lieu-dit de comptage de la section HIT (colonnes [84-103] du HIT) doit comporter exactement 20 caractères".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02_EN_DUR 
		= "le lieu-dit de comptage de la section HIT (colonnes [84-103] du HIT) doit comporter exactement 20 caractères";
	
	/* 25 - prComptage. *******/
	/**
	 * clé de messageSectionHitPrComptageRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.prComptage.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01 
		= "message.SectionHit.prComptage.renseigne";
	
	/**
	 * messageSectionHitPrComptageRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitPrComptageRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le PR du comptage de la section HIT (colonnes [104-106] du HIT) doit être renseigné (obligatoire)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01_EN_DUR 
		= "le PR du comptage de la section HIT (colonnes [104-106] du HIT) doit être renseigné (obligatoire)";
		
	/**
	 * clé de messageSectionHitPrComptageRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.prComptage.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_PRCOMPTAGE_REGEX_02 
		= "message.SectionHit.prComptage.regex";
	
	/**
	 * messageSectionHitPrComptageRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitPrComptageRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le PR du comptage de la section HIT (colonnes [104-106] du HIT) doit comporter exactement 3 chiffres".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_PRCOMPTAGE_REGEX_02_EN_DUR 
		= "le PR du comptage de la section HIT (colonnes [104-106] du HIT) doit comporter exactement 3 chiffres";
		
	/**
	 * clé de messageSectionHitPrComptageNumerique03 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.prComptage.numerique"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03 
		= "message.SectionHit.prComptage.numerique";
	
	/**
	 * messageSectionHitPrComptageNumerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitPrComptageNumerique03 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le PR du comptage de la section HIT (colonnes [104-106] du HIT) doit être homogène à un entier".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03_EN_DUR 
		= "le PR du comptage de la section HIT (colonnes [104-106] du HIT) doit être homogène à un entier";
	
	/* 26 - absComptage. *******/
	/**
	 * clé de messageSectionHitAbsComptageRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.absComptage.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01 
		= "message.SectionHit.absComptage.renseigne";
	
	/**
	 * messageSectionHitAbsComptageRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitAbsComptageRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "l'abscisse du comptage de la section HIT (colonnes [107-110] du HIT) doit être renseignée (obligatoire)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01_EN_DUR 
		= "l'abscisse du comptage de la section HIT (colonnes [107-110] du HIT) doit être renseignée (obligatoire)";
		
	/**
	 * clé de messageSectionHitAbsComptageRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.absComptage.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_ABSCOMPTAGE_REGEX_02 
		= "message.SectionHit.absComptage.regex";
	
	/**
	 * messageSectionHitAbsComptageRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitAbsComptageRegex02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "l'abscisse du comptage de la section HIT (colonnes [107-110] du HIT) doit comporter exactement 4 chiffres".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_ABSCOMPTAGE_REGEX_02_EN_DUR 
		= "l'abscisse du comptage de la section HIT (colonnes [107-110] du HIT) doit comporter exactement 4 chiffres";
		
	/**
	 * clé de messageSectionHitAbsComptageNumerique03 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.absComptage.numerique"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03 
		= "message.SectionHit.absComptage.numerique";
	
	/**
	 * messageSectionHitAbsComptageNumerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitAbsComptageNumerique03 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "l'abscisse du comptage de la section HIT (colonnes [107-110] du HIT) doit être homogène à un entier".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03_EN_DUR 
		= "l'abscisse du comptage de la section HIT (colonnes [107-110] du HIT) doit être homogène à un entier";
							
	/**
	* java.util.Properties encapsulant les préférences.<br/>
	*/
	private static Properties preferences = new Properties();
	
	/**
	* Path absolu vers le fichier properties contenant les preferences
	* <code>SectionHit_CONTROLES.properties</code>.<br/>
	*/
	private static Path pathAbsoluPreferencesProperties;
	
	/**
	* Chemin relatif (par rapport à ressources_externes) 
	* du fichier properties contenant les preferences
	* <code>SectionHit_CONTROLES.properties</code>.<br/>
	* "preferences/metier/sections/SectionHit_CONTROLES.properties"
	*/
	private static final String CHEMIN_RELATIF_PREFERENCES_PROPERTIES_STRING 
	= "preferences/metier/sections/SectionHit_CONTROLES.properties";
	
	/**
	* Modélisation Java du fichier properties contenant les preferences
	* <code>SectionHit_CONTROLES.properties</code>.<br/>
	*/
	private static File filePreferencesProperties;
	
	/**
	* commentaire à ajouter en haut du fichier properties 
	* contenant les preferences 
	* <code>SectionHit_CONTROLES.properties</code>.<br/>
	*/
	private static String commentaire;
	
	/**
	* Chemin relatif (par rapport à src/main/resources) 
	* du template contenant le commentataire à ajouter 
	* en haut du fichier properties contenant les preferences 
	* <code>SectionHit_CONTROLES.properties</code>.<br/>
	* "commentaires_properties/metier/sections/SectionHit_CONTROLES_properties_commentaires.txt"
	*/
	private static final String CHEMIN_RELATIF_TEMPLATE_COMMENTAIRE 
	= "commentaires_properties/metier/sections/SectionHit_CONTROLES_properties_commentaires.txt";

	// MESSAGES ************************************************
	/* 1 - numDepartement. ***********/
	/**
	* message émis par la RG-SectionHit-NumDepartement-01 : 
	* "le numéro de département de la section HIT (colonnes [1-3] du HIT) doit être renseigné (obligatoire)".<br/>
	*/
	private static String messageSectionHitNumDepartementRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-NumDepartement-02 : 
	* "le numéro de département doit comporter exactement 3 chiffres dans les colonnes [1-3] du HIT ('030' pour 03, '300' pour 30, '972' pour 972)".<br/>
	*/
	private static String messageSectionHitNumDepartementRegex02;

	/* 2 - numSection. ***************/
	/**
	* message émis par la RG-SectionHit-NumSection-01 : 
	* "le numéro de section de la section HIT (colonnes [4-9] du HIT) doit être renseigné (obligatoire)".<br/>
	*/
	private static String messageSectionHitNumSectionRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-NumSection-02 : 
	* "le numéro de section doit comporter exactement 6 chiffres 
	 * (Numéro de section sur 4 chiffres significatifs maxi complété 
	 * éventuellement avec des 0 à gauche et indice sur 2 chiffres 
	 * significatifs maxi complété éventuellement avec des 0 à gauche. 
	 * Exemples : section 26 indice 4 = 002604, section 1 indice 0 = 000100
	 * , section 162 indice 65 = 016265)".<br/>
	*/
	private static String messageSectionHitNumSectionRegex02;

	/* 3 - sens. **********************/
	/**
	* message émis par la RG-SectionHit-Sens-01 : 
	* "le sens de la section HIT doit être renseigné (obligatoire)".<br/>
	*/
	private static String messageSectionHitSensRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-Sens-02 : 
	* "le sens de la section HIT (colonne [10] du HIT) doit comporter exactement 1 chiffre".<br/>
	*/
	private static String messageSectionHitSensRegex02;

	/**
	 * message émis par la RG-SectionHit-Sens-03 : 
	 * "le sens de la section HIT (colonne [10] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]"
	 */
	private static String messageSectionHitSensNomenclature03;

	/* 4 - nature. ***************/
	/**
	* message émis par la RG-SectionHit-Nature-01 : 
	* "la nature de la section HIT (colonne [11] du HIT) doit être renseignée (obligatoire)".<br/>
	*/
	private static String messageSectionHitNatureRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-Nature-02 : 
	* "la nature de la section HIT (colonne [11] du HIT) doit comporter exactement 1 chiffre" .<br/>
	*/
	private static String messageSectionHitNatureRegex02;

	/**
	 * message émis par la RG-SectionHit-Nature-03 : 
	 * "la nature de la section HIT (colonne [11] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]"
	 */
	private static String messageSectionHitNatureNomenclature03;

	/* 5 - classe. ************/
	/**
	* message émis par la RG-SectionHit-Classe-01 : 
	* "la classe de la section HIT (colonnes [12-13] du HIT) doit être renseignée".<br/>
	*/
	private static String messageSectionHitClasseRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-Classe-02 : 
	* "la classe de la section HIT (colonnes [12-13] du HIT) doit comporter exactement 2 chiffres ('00')".<br/>
	*/
	private static String messageSectionHitClasseRegex02;

	/* 6 - anneeTraitement. *******/
	/**
	* message émis par la RG-SectionHit-AnneeTraitement-01 : 
	* "l'année de traitement de la section HIT (colonnes [14-15] du HIT) doit être renseignée (obligatoire)".<br/>
	*/
	private static String messageSectionHitAnneeTraitementRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-AnneeTraitement-02 : 
	* "l'année de traitement de la section HIT (colonnes [14-15] du HIT) doit comporter exactement 2 chiffres".<br/>
	*/
	private static String messageSectionHitAnneeTraitementRegex02;

	/* 7 - zoneLibre1. *******/
	/**
	* message émis par la RG-SectionHit-ZoneLibre1-01 : 
	* "la zone libre de la section HIT (colonnes [16] du HIT) doit être renseignée (1 espace)".<br/>
	*/
	private static String messageSectionHitZoneLibre1Renseigne01;
	
	/**
	* message émis par la RG-SectionHit-ZoneLibre1-02 : 
	* "la zone libre de la section HIT (colonnes [16] du HIT) doit comporter exactement 1 espace".<br/>
	*/
	private static String messageSectionHitZoneLibre1Regex02;

	/* 8 - numRoute. *******/
	/**
	* message émis par la RG-SectionHit-NumRoute-01 : 
	* "le numéro de la route de la section HIT (colonnes [17-20] du HIT) doit être renseigné (obligatoire)".<br/>
	*/
	private static String messageSectionHitNumRouteRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-NumRoute-02 : 
	* "le numéro de la route de la section HIT (colonnes [17-20] du HIT) doit comporter exactement 4 chiffres".<br/>
	*/
	private static String messageSectionHitNumRouteRegex02;

	/* 9 - indiceNumRoute. *******/
	/**
	* message émis par la RG-SectionHit-IndiceNumRoute-01 : 
	* "l'indice numérique de la route de la section HIT (colonnes [21] du HIT) doit être renseigné".<br/>
	*/
	private static String messageSectionHitIndiceNumRouteRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-IndiceNumRoute-02 : 
	* "l'indice numérique de la route de la section HIT (colonnes [21] du HIT) doit comporter exactement 1 chiffre".<br/>
	*/
	private static String messageSectionHitIndiceNumRouteRegex02;

	/* 10 - indiceLettreRoute. *******/
	/**
	* message émis par la RG-SectionHit-IndiceLettreRoute-01 : 
	* "l'indice lettre de la route de la section HIT (colonnes [22] du HIT) doit être renseigné (ou espace si pas d'indice)".<br/>
	*/
	private static String messageSectionHitIndiceLettreRouteRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-IndiceLettreRoute-02 : 
	* "l'indice lettre de la route de la section HIT (colonnes [22] du HIT) doit comporter exactement 1 lettre (ou espace si pas d'indice)".<br/>
	*/
	private static String messageSectionHitIndiceLettreRouteRegex02;

	/* 11 - categorieAdminRoute. *******/
	/**
	* message émis par la RG-SectionHit-CategorieAdminRoute-01 : 
	* "la catégorie administrative de la route de la section HIT (colonne [23] du HIT) doit être renseignée (obligatoire)".<br/>
	*/
	private static String messageSectionHitCategorieAdminRouteRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-CategorieAdminRoute-02 : 
	* "la catégorie administrative de la route de la section HIT (colonne [23] du HIT) doit comporter exactement 1 chiffre".<br/>
	*/
	private static String messageSectionHitCategorieAdminRouteRegex02;

	/**
	 * message émis par la RG-SectionHit-CategorieAdminRoute-03 : 
	 * "la catégorie administrative de la route de la section HIT (colonne [23] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]"
	 */
	private static String messageSectionHitCategorieAdminRouteNomenclature03;

	/* 12 - typeComptage. *******/
	/**
	* message émis par la RG-SectionHit-TypeComptage-01 : 
	* "le type de comptage de la section HIT (colonne [24] du HIT) doit être renseigné".<br/>
	*/
	private static String messageSectionHitTypeComptageRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-TypeComptage-02 : 
	* "le type de comptage de la section HIT (colonne [24] du HIT) doit comporter exactement 1 chiffre".<br/>
	*/
	private static String messageSectionHitTypeComptageRegex02;

	/**
	 * message émis par la RG-SectionHit-TypeComptage-03 : 
	 * "le type de comptage de la section HIT (colonne [24] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]"
	 */
	private static String messageSectionHitTypeComptageNomenclature03;

	/* 13 - classementRoute. *******/
	/**
	* message émis par la RG-SectionHit-ClassementRoute-01 : 
	* "le classement de la route de la section HIT (colonne [25] du HIT) doit être renseigné".<br/>
	*/
	private static String messageSectionHitClassementRouteRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-ClassementRoute-02 : 
	* "le classement de la route de la section HIT (colonne [25] du HIT) doit comporter exactement 1 chiffre".<br/>
	*/
	private static String messageSectionHitClassementRouteRegex02;

	/**
	 * message émis par la RG-SectionHit-ClassementRoute-03 : 
	 * "le classement de la route de la section HIT (colonne [25] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]"
	 */
	private static String messageSectionHitClassementRouteNomenclature03;

	/* 14 - classeLargeurChausseeU. *******/
	/**
	* message émis par la RG-SectionHit-ClasseLargeurChausseeU-01 : 
	* "la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit être renseignée (0 si sans objet)".<br/>
	*/
	private static String messageSectionHitClasseLargeurChausseeURenseigne01;
	
	/**
	* message émis par la RG-SectionHit-ClasseLargeurChausseeU-02 : 
	* "la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit comporter exactement 1 chiffre".<br/>
	*/
	private static String messageSectionHitClasseLargeurChausseeURegex02;

	/**
	 * message émis par la RG-SectionHit-ClasseLargeurChausseeU-03 : 
	 * "la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7, 8]"
	 */
	private static String messageSectionHitClasseLargeurChausseeUNomenclature03;

	/* 15 - classeLargeurChausseesS. *******/
	/**
	* message émis par la RG-SectionHit-ClasseLargeurChausseesS-01 : 
	* "la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit être renseignée".<br/>
	*/
	private static String messageSectionHitClasseLargeurChausseesSRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-ClasseLargeurChausseesS-02 : 
	* "la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit comporter exactement 1 chiffre".<br/>
	*/
	private static String messageSectionHitClasseLargeurChausseesSRegex02;

	/**
	 * message émis par la RG-SectionHit-ClasseLargeurChausseesS-03 : 
	 * "la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7]"
	 */
	private static String messageSectionHitClasseLargeurChausseesSNomenclature03;

	/* 16 - typeReseau. *******/
	/**
	* message émis par la RG-SectionHit-TypeReseau-01 : 
	* "le type de réseau de la section HIT (colonne [28] du HIT) doit être renseigné (0 si autre que autoroute)".<br/>
	*/
	private static String messageSectionHitTypeReseauRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-TypeReseau-02 : 
	* "le type de réseau de la section HIT (colonne [28] du HIT) doit comporter exactement 1 chiffre".<br/>
	*/
	private static String messageSectionHitTypeReseauRegex02;

	/**
	 * message émis par la RG-SectionHit-TypeReseau-03 : 
	 * "le type de réseau de la section HIT (colonne [28] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4]"
	 */
	private static String messageSectionHitTypeReseauNomenclature03;

	/* 17 - pRoupK. *******/
	/**
	* message émis par la RG-SectionHit-PRoupK-01 : 
	* "PR ou PK de la section HIT (colonne [29] du HIT) doit être renseigné".<br/>
	*/
	private static String messageSectionHitPRoupKRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-PRoupK-02 : 
	* "PR ou PK de la section HIT (colonne [29] du HIT) doit comporter exactement 1 chiffre".<br/>
	*/
	private static String messageSectionHitPRoupKRegex02;

	/**
	 * message émis par la RG-SectionHit-PRoupK-03 : 
	 * "PR ou PK de la section HIT (colonne [29] du HIT) doit respecter une nomenclature [1, 2]"
	 */
	private static String messageSectionHitPRoupKNomenclature03;

	/* 18 - lieuDitOrigine. *******/
	/**
	* message émis par la RG-SectionHit-LieuDitOrigine-01 : 
	* "le lieu-dit origine de la section HIT (colonnes [30-49] du HIT) doit être renseigné".<br/>
	*/
	private static String messageSectionHitLieuDitOrigineRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-LieuDitOrigine-02 : 
	* "le lieu-dit origine de la section HIT (colonnes [30-49] du HIT) doit comporter exactement 20 caractères".<br/>
	*/
	private static String messageSectionHitLieuDitOrigineRegex02;		

	/* 19 - prOrigine. *******/
	/**
	* message émis par la RG-SectionHit-PrOrigine-01 : 
	* "le PR origine de la section HIT (colonnes [50-52] du HIT) doit être renseigné (obligatoire)".<br/>
	*/
	private static String messageSectionHitPrOrigineRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-PrOrigine-02 : 
	* "le PR origine de la section HIT (colonnes [50-52] du HIT) doit comporter exactement 3 chiffres".<br/>
	*/
	private static String messageSectionHitPrOrigineRegex02;
	
	/**
	* message émis par la RG-SectionHit-PrOrigine-03 : 
	* "le PR origine de la section HIT (colonnes [50-52] du HIT) doit être homogène à un entier".<br/>
	*/
	private static String messageSectionHitPrOrigineNumerique03;

	/* 20 - absOrigine. *******/
	/**
	* message émis par la RG-SectionHit-AbsOrigine-01 : 
	* "l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit être renseignée (obligatoire)".<br/>
	*/
	private static String messageSectionHitAbsOrigineRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-AbsOrigine-02 : 
	* "l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit comporter exactement 4 chiffres".<br/>
	*/
	private static String messageSectionHitAbsOrigineRegex02;
	
	/**
	* message émis par la RG-SectionHit-AbsOrigine-03 : 
	* "l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit être homogène à un entier".<br/>
	*/
	private static String messageSectionHitAbsOrigineNumerique03;

	/* 21 - lieuDitExtremite. *******/
	/**
	* message émis par la RG-SectionHit-LieuDitExtremite-01 : 
	* "le lieu-dit extremité de la section HIT (colonnes [57-76] du HIT) doit être renseigné".<br/>
	*/
	private static String messageSectionHitLieuDitExtremiteRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-LieuDitExtremite-02 : 
	* "le lieu-dit extremité de la section HIT (colonnes [57-76] du HIT) doit comporter exactement 20 caractères".<br/>
	*/
	private static String messageSectionHitLieuDitExtremiteRegex02;		

	/* 22 - prExtremite. *******/
	/**
	* message émis par la RG-SectionHit-PrExtremite-01 : 
	* "le PR extremité de la section HIT (colonnes [77-79] du HIT) doit être renseigné (obligatoire)".<br/>
	*/
	private static String messageSectionHitPrExtremiteRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-PrExtremite-02 : 
	* "le PR extremité de la section HIT (colonnes [77-79] du HIT) doit comporter exactement 3 chiffres".<br/>
	*/
	private static String messageSectionHitPrExtremiteRegex02;
	
	/**
	* message émis par la RG-SectionHit-PrExtremite-03 : 
	* "le PR extremité de la section HIT (colonnes [77-79] du HIT) doit être homogène à un entier".<br/>
	*/
	private static String messageSectionHitPrExtremiteNumerique03;

	/* 23 - absExtremite. *******/
	/**
	* message émis par la RG-SectionHit-AbsExtremite-01 : 
	* "l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit être renseignée (obligatoire)".<br/>
	*/
	private static String messageSectionHitAbsExtremiteRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-AbsExtremite-02 : 
	* "l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit comporter exactement 4 chiffres".<br/>
	*/
	private static String messageSectionHitAbsExtremiteRegex02;
	
	/**
	* message émis par la RG-SectionHit-AbsExtremite-03 : 
	* "l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit être homogène à un entier".<br/>
	*/
	private static String messageSectionHitAbsExtremiteNumerique03;

	/* 24 - lieuDitComptage. *******/
	/**
	* message émis par la RG-SectionHit-LieuDitComptage-01 : 
	* "le lieu-dit de comptage de la section HIT (colonnes [84-103] du HIT) doit être renseigné".<br/>
	*/
	private static String messageSectionHitLieuDitComptageRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-LieuDitComptage-02 : 
	* "le lieu-dit de comptage de la section HIT (colonnes [84-103] du HIT) doit comporter exactement 20 caractères".<br/>
	*/
	private static String messageSectionHitLieuDitComptageRegex02;		

	/* 25 - prComptage. *******/
	/**
	* message émis par la RG-SectionHit-PrComptage-01 : 
	* "le PR du comptage de la section HIT (colonnes [104-106] du HIT) doit être renseigné (obligatoire)".<br/>
	*/
	private static String messageSectionHitPrComptageRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-PrComptage-02 : 
	* "le PR du comptage de la section HIT (colonnes [104-106] du HIT) doit comporter exactement 3 chiffres".<br/>
	*/
	private static String messageSectionHitPrComptageRegex02;
	
	/**
	* message émis par la RG-SectionHit-PrComptage-03 : 
	* "le PR du comptage de la section HIT (colonnes [104-106] du HIT) doit être homogène à un entier".<br/>
	*/
	private static String messageSectionHitPrComptageNumerique03;

	/* 26 - absComptage. *******/
	/**
	* message émis par la RG-SectionHit-AbsComptage-01 : 
	* "l'abscisse du comptage de la section HIT (colonnes [107-110] du HIT) doit être renseignée (obligatoire)".<br/>
	*/
	private static String messageSectionHitAbsComptageRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-AbsComptage-02 : 
	* "l'abscisse du comptage de la section HIT (colonnes [107-110] du HIT) doit comporter exactement 4 chiffres".<br/>
	*/
	private static String messageSectionHitAbsComptageRegex02;
	
	/**
	* message émis par la RG-SectionHit-AbsComptage-03 : 
	* "l'abscisse du comptage de la section HIT (colonnes [107-110] du HIT) doit être homogène à un entier".<br/>
	*/
	private static String messageSectionHitAbsComptageNumerique03;
		
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitGestionnairePreferencesControles.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation<br/>
	 */
	private SectionHitGestionnairePreferencesControles() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * <b>sauvegarde sur disque un fichier
	 * <code>ressources_externes/preferences/metier/sections/ 
	 * SectionHit_CONTROLES.properties</code> initial</b> alimenté par des 
	 * propriétés [clé-valeur] <b>écrites en dur</b> 
	 * dans la présente classe.<br/>
	 * <ul>
	 * <li>remplit le java.util.Properties <code>preferences</code> 
	 * avec des [clé-valeur] stockées en dur dans la classe.</li>
	 * <li>crée le fichier properties contenant les préférences 
	 * <code>filePreferencesProperties</code> VIDE 
	 * sur le disque si il n'existe pas.</li>
	 * <li>remplit le fichier <code>filePreferencesProperties</code> 
	 * (SectionHit_CONTROLES.properties) 
	 * avec le contenu de <code>preferences</code> 
	 * ([clé-valeur] stockées en dur dans la classe).</li>
	 * <li>Ecrit en UTF8 le Properties <code>preferences</code> dans 
	 * le File <code>filePreferencesProperties</code> 
	 * modélisant le fichier SectionHit_CONTROLES.properties en positionnant 
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			/* 1 - numDepartement. ***********/		
			/* ajoute le messageSectionHitNumDepartementRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01
						, MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le messageSectionHitNumDepartementRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02
						, MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR);
						
			/* 2 - numSection. **************/		
			/* ajoute le messageSectionHitNumSectionRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_MESSAGE_SECTIONHIT_NUMSECTION_RENSEIGNE_01
						, MESSAGE_SECTIONHIT_NUMSECTION_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le messageSectionHitNumSectionRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_MESSAGE_SECTIONHIT_NUMSECTION_REGEX_02
						, MESSAGE_SECTIONHIT_NUMSECTION_REGEX_02_EN_DUR);
					
		/* 3 - sens. **************/		
		/* ajoute le messageSectionHitSensRenseigne01 
		 * par défaut stocké en dur.*/
		preferences.setProperty(
				KEY_MESSAGE_SECTIONHIT_SENS_RENSEIGNE_01
					, MESSAGE_SECTIONHIT_SENS_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitSensRegex02 
		 * par défaut stocké en dur.*/
		preferences.setProperty(
				KEY_MESSAGE_SECTIONHIT_SENS_REGEX_02
					, MESSAGE_SECTIONHIT_SENS_REGEX_02_EN_DUR);
		
		/* ajoute le messageSectionHitSensNomenclature03 
		 * par défaut stocké en dur.*/
		preferences.setProperty(
				KEY_MESSAGE_SECTIONHIT_SENS_NOMENCLATURE_03
					, MESSAGE_SECTIONHIT_SENS_NOMENCLATURE_03_EN_DUR);
				
		/* 4 - nature. **************/		
		/* ajoute le messageSectionHitNatureRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_NATURE_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_NATURE_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitNatureRegex02 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_NATURE_REGEX_02
				, MESSAGE_SECTIONHIT_NATURE_REGEX_02_EN_DUR);
		
		/* ajoute le messageSectionHitNatureNomenclature03 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_NATURE_NOMENCLATURE_03
				, MESSAGE_SECTIONHIT_NATURE_NOMENCLATURE_03_EN_DUR);
				
		/* 5 - classe. **************/		
		/* ajoute le messageSectionHitClasseRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_CLASSE_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_CLASSE_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitClasseRegex02 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_CLASSE_REGEX_02
				, MESSAGE_SECTIONHIT_CLASSE_REGEX_02_EN_DUR);
				
		/* 6 - anneeTraitement. *******/		
		/* ajoute le messageSectionHitAnneeTraitementRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitAnneeTraitementRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_ANNEETRAITEMENT_REGEX_02
				, MESSAGE_SECTIONHIT_ANNEETRAITEMENT_REGEX_02_EN_DUR);
				
		/* 7 - zoneLibre1. *******/		
		/* ajoute le messageSectionHitZoneLibre1Renseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitZoneLibre1Regex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_ZONELIBRE1_REGEX_02
				, MESSAGE_SECTIONHIT_ZONELIBRE1_REGEX_02_EN_DUR);
				
		/* 8 - numRoute. *******/		
		/* ajoute le messageSectionHitNumRouteRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_NUMROUTE_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_NUMROUTE_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitNumRouteRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_NUMROUTE_REGEX_02
				, MESSAGE_SECTIONHIT_NUMROUTE_REGEX_02_EN_DUR);
				
		/* 9 - indiceNumRoute. *******/		
		/* ajoute le messageSectionHitIndiceNumRouteRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitIndiceNumRouteRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_INDICENUMROUTE_REGEX_02
				, MESSAGE_SECTIONHIT_INDICENUMROUTE_REGEX_02_EN_DUR);
				
		/* 10 - indiceLettreRoute. *******/		
		/* ajoute le messageSectionHitIndiceLettreRouteRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitIndiceLettreRouteRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_INDICELETTREROUTE_REGEX_02
				, MESSAGE_SECTIONHIT_INDICELETTREROUTE_REGEX_02_EN_DUR);
				
		/* 11 - categorieAdminRoute. *******/		
		/* ajoute le messageSectionHitCategorieAdminRouteRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitCategorieAdminRouteRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02
				, MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02_EN_DUR);
		
		/* ajoute le messageSectionHitCategorieAdminRouteNomenclature03 
		 * par défaut stocké en dur.*/
		preferences.setProperty(
				KEY_MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03
					, MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03_EN_DUR);
				
		/* 12 - typeComptage. *******/		
		/* ajoute le messageSectionHitTypeComptageRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitTypeComptageRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_TYPECOMPTAGE_REGEX_02
				, MESSAGE_SECTIONHIT_TYPECOMPTAGE_REGEX_02_EN_DUR);
		
		/* ajoute le messageSectionHitTypeComptageNomenclature03 
		 * par défaut stocké en dur.*/
		preferences.setProperty(
				KEY_MESSAGE_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03
					, MESSAGE_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03_EN_DUR);
				
		/* 13 - classementRoute. *******/		
		/* ajoute le messageSectionHitClassementRouteRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitClassementRouteRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_CLASSEMENTROUTE_REGEX_02
				, MESSAGE_SECTIONHIT_CLASSEMENTROUTE_REGEX_02_EN_DUR);
		
		/* ajoute le messageSectionHitClassementRouteNomenclature03 
		 * par défaut stocké en dur.*/
		preferences.setProperty(
				KEY_MESSAGE_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03
					, MESSAGE_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03_EN_DUR);
				
		/* 14 - classeLargeurChausseeU. *******/		
		/* ajoute le messageSectionHitClasseLargeurChausseeURenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitClasseLargeurChausseeURegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02
				, MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02_EN_DUR);
		
		/* ajoute le messageSectionHitClasseLargeurChausseeUNomenclature03 
		 * par défaut stocké en dur.*/
		preferences.setProperty(
				KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03
					, MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03_EN_DUR);
				
		/* 15 - classeLargeurChausseesS. *******/		
		/* ajoute le messageSectionHitClasseLargeurChausseesSRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitClasseLargeurChausseesSRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02
				, MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02_EN_DUR);
		
		/* ajoute le messageSectionHitClasseLargeurChausseesSNomenclature03 
		 * par défaut stocké en dur.*/
		preferences.setProperty(
				KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03
					, MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03_EN_DUR);
				
		/* 16 - typeReseau. *******/		
		/* ajoute le messageSectionHitTypeReseauRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_TYPERESEAU_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_TYPERESEAU_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitTypeReseauRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_TYPERESEAU_REGEX_02
				, MESSAGE_SECTIONHIT_TYPERESEAU_REGEX_02_EN_DUR);
		
		/* ajoute le messageSectionHitTypeReseauNomenclature03 
		 * par défaut stocké en dur.*/
		preferences.setProperty(
				KEY_MESSAGE_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03
					, MESSAGE_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03_EN_DUR);
				
		/* 17 - pRoupK. *******/		
		/* ajoute le messageSectionHitPRoupKRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_PROUPK_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_PROUPK_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitPRoupKRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_PROUPK_REGEX_02
				, MESSAGE_SECTIONHIT_PROUPK_REGEX_02_EN_DUR);
		
		/* ajoute le messageSectionHitPRoupKNomenclature03 
		 * par défaut stocké en dur.*/
		preferences.setProperty(
				KEY_MESSAGE_SECTIONHIT_PROUPK_NOMENCLATURE_03
					, MESSAGE_SECTIONHIT_PROUPK_NOMENCLATURE_03_EN_DUR);
				
		/* 18 - lieuDitOrigine. *******/		
		/* ajoute le messageSectionHitLieuDitOrigineRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitLieuDitOrigineRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_LIEUDITORIGINE_REGEX_02
				, MESSAGE_SECTIONHIT_LIEUDITORIGINE_REGEX_02_EN_DUR);
				
		/* 19 - prOrigine. *******/		
		/* ajoute le messageSectionHitPrOrigineRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_PRORIGINE_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_PRORIGINE_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitPrOrigineRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_PRORIGINE_REGEX_02
				, MESSAGE_SECTIONHIT_PRORIGINE_REGEX_02_EN_DUR);
		
		/* ajoute le messageSectionHitPrOrigineNumerique03
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_PRORIGINE_NUMERIQUE_03
				, MESSAGE_SECTIONHIT_PRORIGINE_NUMERIQUE_03_EN_DUR);
				
		/* 20 - absOrigine. *******/		
		/* ajoute le messageSectionHitAbsOrigineRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_ABSORIGINE_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_ABSORIGINE_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitAbsOrigineRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_ABSORIGINE_REGEX_02
				, MESSAGE_SECTIONHIT_ABSORIGINE_REGEX_02_EN_DUR);
		
		/* ajoute le messageSectionHitAbsOrigineNumerique03
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_ABSORIGINE_NUMERIQUE_03
				, MESSAGE_SECTIONHIT_ABSORIGINE_NUMERIQUE_03_EN_DUR);
				
		/* 21 - lieuDitExtremite. *******/		
		/* ajoute le messageSectionHitLieuDitExtremiteRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitLieuDitExtremiteRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02
				, MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02_EN_DUR);
				
		/* 22 - prExtremite. *******/		
		/* ajoute le messageSectionHitPrExtremiteRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_PREXTREMITE_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_PREXTREMITE_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitPrExtremiteRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_PREXTREMITE_REGEX_02
				, MESSAGE_SECTIONHIT_PREXTREMITE_REGEX_02_EN_DUR);
		
		/* ajoute le messageSectionHitPrExtremiteNumerique03
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_PREXTREMITE_NUMERIQUE_03
				, MESSAGE_SECTIONHIT_PREXTREMITE_NUMERIQUE_03_EN_DUR);
				
		/* 23 - absExtremite. *******/		
		/* ajoute le messageSectionHitAbsExtremiteRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitAbsExtremiteRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_ABSEXTREMITE_REGEX_02
				, MESSAGE_SECTIONHIT_ABSEXTREMITE_REGEX_02_EN_DUR);
		
		/* ajoute le messageSectionHitAbsExtremiteNumerique03
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03
				, MESSAGE_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03_EN_DUR);
				
		/* 24 - lieuDitComptage. *******/		
		/* ajoute le messageSectionHitLieuDitComptageRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitLieuDitComptageRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02
				, MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02_EN_DUR);
				
		/* 25 - prComptage. *******/		
		/* ajoute le messageSectionHitPrComptageRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitPrComptageRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_PRCOMPTAGE_REGEX_02
				, MESSAGE_SECTIONHIT_PRCOMPTAGE_REGEX_02_EN_DUR);
		
		/* ajoute le messageSectionHitPrComptageNumerique03
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03
				, MESSAGE_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03_EN_DUR);
				
		/* 26 - absComptage. *******/		
		/* ajoute le messageSectionHitAbsComptageRenseigne01 
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01
				, MESSAGE_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01_EN_DUR);
		
		/* ajoute le messageSectionHitAbsComptageRegex02
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_ABSCOMPTAGE_REGEX_02
				, MESSAGE_SECTIONHIT_ABSCOMPTAGE_REGEX_02_EN_DUR);
		
		/* ajoute le messageSectionHitAbsComptageNumerique03
		* par défaut stocké en dur.*/
		preferences.setProperty(
			KEY_MESSAGE_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03
				, MESSAGE_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03_EN_DUR);
																
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
	 * <li>calcule le path du SectionHit_CONTROLES.properties 
	 * via un resolve par rapport au path des ressources externes.</li>
	 * <li>alimente <code>pathAbsoluPreferencesProperties</code></li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void instancierPathAbsoluPreferencesProperties() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			/* ne fait rien si pathAbsoluPreferencesProperties 
			 * n'est pas null. */
			if (pathAbsoluPreferencesProperties == null) {
				
				/* obtient le path du properties dans les 
				 * ressources externes auprès du 
				 * ConfigurationApplicationManager. */
				final Path pathRessourcesExternes 
				= Paths.get(ConfigurationApplicationManager
						.getPathRessourcesExternes());
				
				/* calcule le path du SectionHit_CONTROLES.properties 
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			/* ne fait rien si commentaire n'est pas null. */
			if (commentaire == null) {
				
				/* lit dans un template le commentaire à ajouter au début du 
				 * SectionHit_CONTROLES.properties et le stocke 
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
	 * <code>SectionHit_CONTROLES.properties</code> <b>VIDE</b> 
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
	 * SectionHit_CONTROLES.properties</b></code> 
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

		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
	 * SectionHit_CONTROLES.properties</b></code>.<br/>
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
				loggerError(CLASSE_SECTIONHIT_GESTIONNAIRE_PREFS_CONTROLES
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
				loggerError(CLASSE_SECTIONHIT_GESTIONNAIRE_PREFS_CONTROLES
						, METHODE_LIRE_STRINGS_DANS_FILE
						, ioe);
				
				final String message 
				= CLASSE_SECTIONHIT_GESTIONNAIRE_PREFS_CONTROLES 
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
	 * retourne la valeur du String pAttribut 
	 * dans le fichier properties.<br/>
	 * <ul>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li><b>alimente l'attribut pAttribut avec sa valeur 
	 * dans le java.util.Properties <code>preferences</code>.</b></li>
	 * <ul>
	 * <li>nettoie la valeur lue dans le properties avec un trim().</li>
	 * <li>affecte la valeur nettoyée lue dans le properties à pAttribut 
	 * si le properties est accessible.</li>
	 * <li>affecte la valeur en dur à pAttribut si problème.</li>
	 * </ul>
	 * <li><b>retourne la valeur de l'attribut 
	 * dans le fichier properties</b>.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * un attribut de la classe (SINGLETON) comme 
	 * <code>messageSectionHitNumDepartementRenseigne01</code>
	 * @param pFournirKey : String : 
	 * clé de l'attribut String pAttribut dans le fichier properties.
	 * @param pValeurEnDur : String : 
	 * valeur initiale stockée en dur dans la classe pour pAttribut.
	 * 
	 * @return String : 
	 * l'attribut String passé en paramètre tel qu'il est stocké 
	 * dans le fichier properties.<br/>
	 * 
	 * @throws Exception
	 */
	private static String fournirAttribut(
			String pAttribut
				, final String pFournirKey
					, final String pValeurEnDur) 
									throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			/*  alimente le java.util.Properties preferences. */
			alimenterPreferences();
			
			/* alimente l'attribut pAttribut avec sa valeur 
			 * dans le fichier properties. */
			if (pAttribut == null) {
				
				/* lecture dans le properties. */
				final String valeurStringSale 
					= preferences
						.getProperty(pFournirKey);
				
				if (!StringUtils.isBlank(valeurStringSale)) {
					
					/* nettoie la valeur lue dans le properties 
					 * avec un trim(). */
					pAttribut 
						= valeurStringSale.trim();
					
				}
				else {
					
					/* prend valeur en dur si problème 
					 * et l'affecte à pAttribut. */
					pAttribut 
						= pValeurEnDur.trim();
				}
			}
			
			/* retourne la valeur de l'attribut dans 
			 * le fichier properties. */
			return pAttribut;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirAttribut(...).______________________________________

	
	
	/**
	 * Méthod générique permettant de factoriser 
	 * les Getters des attributs possédant une valeur numérique à changer.<br/>
	 * retourne la valeur du String pAttribut 
	 * dans le fichier properties.<br/>
	 * Par exemple :<br/>
	 * si la valeur dans pAttribut vaut "la civilite de l'SectionHit 
	 * ne doit pas excéder 15 caractères", 
	 * et  pValeurAInjecter vaut "127", la méthode retourne 
	 * "la civilite de l'SectionHit ne doit pas excéder 127 caractères".<br/>
	 * <ul>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li><b>alimente l'attribut pAttribut avec sa valeur 
	 * dans le java.util.Properties <code>preferences</code>.</b></li>
	 * <ul>
	 * <li>nettoie la valeur lue dans le properties avec un trim().</li>
	 * <li>remplace le nombre dans la valeur lue dans le properties 
	 * par la valeur à injecter.</li>
	 * <li>affecte la valeur nettoyée lue dans le properties à pAttribut 
	 * si le properties est accessible.</li>
	 * <li>affecte la valeur en dur à pAttribut si problème.</li>
	 * </ul>
	 * <li><b>retourne la valeur de l'attribut 
	 * dans le fichier properties</b>.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * un attribut de la classe (SINGLETON) comme 
	 * <code>messageSectionHitNumDepartementLongueur03</code>
	 * @param pFournirKey : String : 
	 * clé de l'attribut String pAttribut dans le fichier properties.
	 * @param pValeurAInjecter : String : 
	 * valeur numérique à substituer dans pAttribut.
	 * @param pValeurEnDur : String : 
	 * valeur initiale stockée en dur dans la classe pour pAttribut.
	 * 
	 * @return String : 
	 * l'attribut String passé en paramètre tel qu'il est stocké 
	 * dans le fichier properties.<br/>
	 * 
	 * @throws Exception
	 */
	private static String fournirAttributSubstitue(
			String pAttribut
				, final String pFournirKey
					, final String pValeurAInjecter
						, final String pValeurEnDur) 
										throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			/*  alimente le java.util.Properties preferences. */
			alimenterPreferences();
			
			/* alimente l'attribut pAttribut avec sa valeur 
			 * dans le fichier properties. */
			if (pAttribut == null) {
				
				/* lecture dans le properties. */
				final String valeurStringSale 
					= preferences
						.getProperty(pFournirKey);
				
				if (!StringUtils.isBlank(valeurStringSale)) {
					
					/* nettoie la valeur lue dans le properties 
					 * avec un trim(). */
					pAttribut 
						= valeurStringSale.trim();
					
					/* remplace le nombre par la valeur à injecter. */
					pAttribut = remplacerNombreParValeur(
							pAttribut, pValeurAInjecter);
				}
				else {
					
					/* prend valeur en dur si problème 
					 * et l'affecte à pAttribut. */
					pAttribut 
						= pValeurEnDur.trim();
				}
			}
			
			/* retourne la valeur de l'attribut dans 
			 * le fichier properties. */
			return pAttribut;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirAttributSubstitue(...)._____________________________


	
	/**
	 * remplace le premier nombre rencontré dans une String pString 
	 * par la valeur pValeurAInjecter.<br/>
	 * Par exemple :<br/>
	 * <code>remplacerNombreParValeur("la civilite de l'SectionHit 
	 * ne doit pas excéder 15 caractères", "127")</code> retourne 
	 * "la civilite de l'SectionHit ne doit pas excéder 127 caractères".<br/>
	 * <ul>
	 * <li>utilise une REGEX avec le motif "(\\d+)" 
	 * pour trouver le nombre à substituer.</li>
	 * <li>utilise <code>matcher.replaceFirst(pValeurAInjecter)</code> 
	 * pour substituer la valeur à injecter au nombre trouvé.</li>
	 * </ul>
	 *
	 * @param pString : String : 
	 * chaine de caractères comportant un nombre à substituer.
	 * @param pValeurAInjecter : String : valeur de substitution.
	 * 
	 * @return : String : chaine substituée.<br/>
	 */
	private static String remplacerNombreParValeur(
			final String pString
				, final String pValeurAInjecter) {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			String resultat = null;
			
			final String motif = "(\\d+)";
			
			final Pattern pattern = Pattern.compile(motif);
			
			final Matcher matcher = pattern.matcher(pString);
			
			if (matcher.find()) {
								
				resultat = matcher.replaceFirst(pValeurAInjecter);
				
			}
			
			return resultat;

		} // Fin du bloc synchronized.__________________
		
	} // Fin de remplacerNombreParValeur(...)._____________________________
	

	
	/**
	 * Retourne un boolean à true si pString 
	 * est de la forme "[1 Nombre]" comme 
	 * <i>"15"</i>.<br/>
	 * <ul>
	 * <li>utilise la Regex "^(\\d+)$" qui matche si pString 
	 * est <b>un nombre</b>.</li>
	 * </ul>
	 *
	 * @param pString : String
	 * 
	 * @return : boolean : 
	 * true si la chaine est un nombre.<br/>
	 */
	private static boolean est1Nombre(
			final String pString) {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			boolean resultat = false;
			
			/* motif : chaine de caractères est un nombre. */
			final String motif = "^(\\d+)$";
			
			final Pattern pattern = Pattern.compile(motif);
			
			final Matcher matcher = pattern.matcher(pString);
			
			if (matcher.matches()) {
				resultat = true;
			} 
			
			return resultat;
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de est1Nombre(...).___________________________________________

	
	
	/**
	 * Retourne un boolean à true si pString 
	 * est de la forme "....[1 Nombre] ..." comme 
	 * <i>"la civilite de l'SectionHit ne doit pas 
	 * excéder 15 caractères"</i>.<br/>
	 * <ul>
	 * <li>utilise la Regex "^(\\D*)(\\d+)(\\D*)$" qui matche si pString 
	 * ne comporte <b>qu'un et un seul nombre</b>.</li>
	 * </ul>
	 *
	 * @param pString : String
	 * 
	 * @return : boolean : 
	 * true si la chaine de caractères ne comporte 
	 * qu'un et un seul nombre.<br/>
	 */
	private static boolean respecteFormat1Nombre(
			final String pString) {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			boolean resultat = false;
			
			/* motif : chaine de caractères ne comporte 
			 * qu'un et un seul nombre*/
			final String motif = "^(\\D*)(\\d+)(\\D*)$";
			
			final Pattern pattern = Pattern.compile(motif);
			
			final Matcher matcher = pattern.matcher(pString);
			
			if (matcher.matches()) {
				resultat = true;
			} 
			
			return resultat;
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de respecteFormat1Nombre(...).________________________________


	
	/**
	 * Extrait et Retourne un nombre contenu dans pString si pString 
	 * est de la forme "....[1 Nombre] ..." comme 
	 * <i>"la civilite de l'SectionHit ne doit pas 
	 * excéder 15 caractères"</i>.<br/>
	 * <ul>
	 * <li>utilise la Regex "^(\\D*)(\\d+)(\\D*)$" qui matche si pString 
	 * ne comporte <b>qu'un et un seul nombre</b>.</li>
	 * <li><code>extraire1Nombre("ne doit pas excéder 15 caractères")</code> 
	 * retourne 15.</li>
	 * </ul>
	 *
	 * @param pString : String
	 * 
	 * @return : String : le nombre unique contenu dans pString.<br/>
	 */
	private static String extraire1Nombre(
			final String pString) {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			String resultat = null;
			
			/* motif : chaine de caractères ne comporte 
			 * qu'un et un seul nombre*/
			final String motif = "^(\\D*)(\\d+)(\\D*)$";
			
			final Pattern pattern = Pattern.compile(motif);
			
			final Matcher matcher = pattern.matcher(pString);
			
			if (matcher.matches()) {
				resultat = matcher.group(2);				
			}
			
			return resultat;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de extraire1Nombre(...).______________________________________
		
	
	
	/**
	 * Méthod générique permettant de factoriser 
	 * les Setters des attributs.<br/>
	 * change la valeur du String pAttribut en pValue
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
	 * @param pValue : String : 
	 * nouvelle valeur à passer à pAttribut et à stocker 
	 * dans le fichier properties de preferences.
	 * @param pAttribut : String : 
	 * un attribut de la classe (SINGLETON) comme 
	 * <code>messageRGSectionHitNumDepartement</code> 
	 * @param pFournirKey : String : 
	 * clé de l'attribut String pAttribut dans le fichier properties.
	 * 
	 * @throws Exception
	 */
	private static void setterAttribut(
			final String pValue
				, String pAttribut
					, final String pFournirKey) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			/* ne fait rien si le paramètre pValue est null
			 * ou ne modifie pas la valeur existante de pAttribut. */
			if (pValue != null 
					&& !pValue
						.equals(pAttribut)) {
				
				/* affecte la nouvelle valeur pValue à 
				 * l'attribut pAttribut. */
				pAttribut = pValue;
				
				/* alimente le java.util.Properties preferences. */
				alimenterPreferences();
				
				/* modifie le java.util.Properties preferences 
				 * avec la nouvelle valeur pValue passée en paramètre. */
				creerOuModifierProperty(
						pFournirKey
							, pAttribut);
				
				/* ré-écrit entièrement le fichier properties mis à jour 
				 * avec les nouvelles valeurs dans le 
				 * java.util.Properties preferences. */
				enregistrerPreferencesDansFichierProperties();

			}

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setterAttribut(...)._______________________________________
	

	
	/**
	* Méthod générique permettant de factoriser 
	* les Setters des attributs valeur liés à un unique attribut message.<br/>
	* Par exemple, l'attribut message 
	* <code>messageSectionHitNumDepartementLongueur03</code> 
	* susceptible de contenir 
	* "la civilite de l'SectionHit ne doit pas excéder 15 caractères" 
	* est lié à l'attribut valeur 
	* <code>valeurSectionHitNumDepartementLongueur03</code> 
	* qui doit alors contenir "15".<br/>
	* Setter du <b>SINGLETON de pAttributValeur comme 
	* <code>valeurSectionHitNumDepartementLongueur03</code> 
	* par défaut dans l'application</b>.<br/>
	* pAttributValeur est la valeur d'un attribut message 
	* <i>à une seule valeur</i> lié comme 
	* <code>messageSectionHitNumDepartementLongueur03</code>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* de l'attribut lié au présent.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si pValue n'est pas un nombre.<br/>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à pAttributValeur.<br/>
	* @param pAttributValeur : String : 
	* un attribut de la classe (SINGLETON) lié à 
	* un unique attribut message comme 
	* <code>valeurSectionHitNumDepartementLongueur03</code> 
	* @param pKeyAttributValeur : String : 
	* clé de l'attribut String pAttributValeur dans le fichier properties.
	* @param pAttributMessageLie : String : 
	* un attribut de la classe (SINGLETON) lié à 
	* un unique attribut valeur comme 
	* <code>messageSectionHitNumDepartementLongueur03</code> 
	* @param pKeyAttributMessageLie : String : 
	* clé de l'attribut String pAttributMessageLie dans le fichier properties.
	* 
	 * @throws Exception 
	*/
	private static void setterValeurAttributLieAUnAttribut(
			final String pValue
				, String pAttributValeur
					, final String pKeyAttributValeur
						, String pAttributMessageLie
							, final String pKeyAttributMessageLie) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			/* ne fait rien si pValue n'est pas un nombre. */
			if (!est1Nombre(pValue)) {
				return;
			}
			
			/* ne fait rien si le paramètre pValue est null
			 * ou ne modifie pas la valeur existante de pAttribut. */
			if (pValue != null 
					&& !pValue
						.equals(pAttributValeur)) {
				
				/* affecte la nouvelle valeur pValue à 
				 * l'attribut pAttribut. */
				pAttributValeur = pValue;
				
				/* affecte la nouvelle valeur à l'attribut lié 
				 * au présent pAttribut. */
				pAttributMessageLie 
				= remplacerNombreParValeur(
						pAttributMessageLie
							, pAttributValeur);
				
				/* alimente le java.util.Properties preferences. */
				alimenterPreferences();
				
				/* modifie le java.util.Properties preferences 
				 * avec la nouvelle valeur pValue passée en paramètre. */
				creerOuModifierProperty(
						pKeyAttributValeur
							, pAttributValeur);
				
				/* ré-écrit entièrement le fichier properties mis à jour 
				 * avec les nouvelles valeurs dans le 
				 * java.util.Properties preferences. */
				enregistrerPreferencesDansFichierProperties();
				
				/* modifie le java.util.Properties preferences 
				 * avec la nouvelle valeur de l'attribut lié 
				 * au présent pAttribut. */
				creerOuModifierProperty(
						pKeyAttributMessageLie
							, pAttributMessageLie);
				
				/* ré-écrit entièrement le fichier properties mis à jour 
				 * avec les nouvelles valeurs dans le 
				 * java.util.Properties preferences. */
				enregistrerPreferencesDansFichierProperties();
				
			}
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setterValeurAttributLieAUnAttribut(...).___________________
	

	
	/**
	* Méthod générique permettant de factoriser 
	* les Setters des attributs message liés à un unique attribut valeur.<br/>
	* Par exemple, l'attribut message 
	* <code>messageSectionHitNumDepartementLongueur03</code> 
	* susceptible de contenir 
	* "la civilite de l'SectionHit ne doit pas excéder 15 caractères" 
	* est lié à l'attribut valeur 
	* <code>valeurSectionHitNumDepartementLongueur03</code> 
	* qui doit alors contenir "15".<br/>
	* Setter du <b>SINGLETON de pAttributMessage comme 
	* <code>messageSectionHitNumDepartementLongueur03</code> 
	* par défaut dans l'application</b>.<br/>
	* pAttributMessage est un attribut message 
	* <i>à une seule valeur</i> liée comme 
	* <code>valeurSectionHitNumDepartementLongueur03</code>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>affecte la nouvelle valeur à l'attribut 
	* lié au présent pAttribut.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si la nouvelle valeur pValue 
	* ne respecte pas le bon format.<br/>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à pAttributMessage.<br/>
	*  @param pAttributValeurLiee : String : 
	* un attribut de la classe (SINGLETON) lié à 
	* un unique attribut message comme 
	* <code>valeurSectionHitNumDepartementLongueur03</code> 
	* @param pKeyAttributValeurLiee : String : 
	* clé de l'attribut String pAttributValeurLiee dans le fichier properties.
	* @param pAttributMessage : String : 
	* un attribut de la classe (SINGLETON) lié à 
	* un unique attribut valeur comme 
	* <code>messageSectionHitNumDepartementLongueur03</code> 
	* @param pKeyAttributMessage : String : 
	* clé de l'attribut String pAttributMessageLie dans le fichier properties.
	* 
	* @throws Exception 
	*/
	private static void setMessageAttributLieAUnAttribut(
			final String pValue
				, String pAttributValeurLiee
					, final String pKeyAttributValeurLiee
				, String pAttributMessage
					, final String pKeyAttributMessage) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			/* ne fait rien si la nouvelle valeur pValue 
			 * ne respecte pas le bon format. */
			if (!respecteFormat1Nombre(pValue)) {
				return;
			}
			
			/* ne fait rien si le paramètre pValue est null
			 * ou ne modifie pas la valeur existante de pAttribut. */
			if (pValue != null 
					&& !pValue
						.equals(pAttributMessage)) {
				
				/* affecte la nouvelle valeur pValue à 
				 * l'attribut pAttribut. */
				pAttributMessage = pValue;
				
				/* affecte la nouvelle valeur à l'attribut lié 
				 * au présent pAttribut. */
				pAttributValeurLiee 
				= extraire1Nombre(
						pAttributMessage);
				
				/* alimente le java.util.Properties preferences. */
				alimenterPreferences();
				
				/* modifie le java.util.Properties preferences 
				 * avec la nouvelle valeur pValue passée en paramètre. */
				creerOuModifierProperty(
						pKeyAttributValeurLiee
							, pAttributValeurLiee);
				
				/* ré-écrit entièrement le fichier properties mis à jour 
				 * avec les nouvelles valeurs dans le 
				 * java.util.Properties preferences. */
				enregistrerPreferencesDansFichierProperties();
				
				/* modifie le java.util.Properties preferences 
				 * avec la nouvelle valeur de l'attribut lié 
				 * au présent pAttribut. */
				creerOuModifierProperty(
						pKeyAttributMessage
							, pAttributMessage);
				
				/* ré-écrit entièrement le fichier properties mis à jour 
				 * avec les nouvelles valeurs dans le 
				 * java.util.Properties preferences. */
				enregistrerPreferencesDansFichierProperties();
				
			}
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageAttributLieAUnAttribut(...)._____________________


	
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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

		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
	 * <code>SectionHit_CONTROLES.properties</code>.<br/>
	 * SINGLETON.<br/>
	 *
	 * @return pathAbsoluPreferencesProperties : Path.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Path getPathAbsoluPreferencesProperties() 
											throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			if (pathAbsoluPreferencesProperties == null) {
				instancierPathAbsoluPreferencesProperties();
			}
			
			return pathAbsoluPreferencesProperties;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de getPathAbsoluPreferencesProperties().______________________


		
	/**
	 * Getter du Chemin relatif (par rapport à ressources_externes) 
	 * du fichier properties contenant les preferences
	 * <code>SectionHit_CONTROLES.properties</code>.<br/>
	 * "preferences/metier/utilisateurs/SectionHit_CONTROLES.properties"
	 *
	 * @return CHEMIN_RELATIF_PREFERENCES_PROPERTIES_STRING : String.<br/>
	 */
	public static String getCheminRelatifPreferencesPropertiesString() {
		return CHEMIN_RELATIF_PREFERENCES_PROPERTIES_STRING;
	} // Fin de getCheminRelatifPreferencesPropertiesString()._____________



	/**
	 * Getter de la  Modélisation Java du fichier properties 
	 * contenant les preferences 
	 * <code>SectionHit_CONTROLES.properties</code>.<br/>
	 * SINGLETON.<br/>
	 *
	 * @return filePreferencesProperties : File.<br/>
	 * 
	 * @throws Exception 
	 */
	public static File getFilePreferencesProperties() throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			if (filePreferencesProperties == null) {				
				instancierFilePreferencesProperties();
			}
			
			return filePreferencesProperties;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de getFilePreferencesProperties().____________________________
	
	
	
	/**
	 * Getter du commentaire à ajouter en haut du fichier properties 
	 * contenant les preferences 
	 * <code>SectionHit_CONTROLES.properties</code>.<br/>
	 * SINGLETON.<br/>
	 *
	 * @return commentaire : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getCommentaire() throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			if (commentaire == null) {
				instancierCommentaire();
			}
			
			return commentaire;
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de getCommentaire().__________________________________________
	
	
	
	/**
	* Setter du commentaire à ajouter en haut du fichier properties 
	* contenant les preferences 
	* <code>SectionHit_CONTROLES.properties</code>.<br/>
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
	 * au dessus de SectionHit_CONTROLES.properties.<br/>
	 * "commentaires_properties/metier/utilisateurs/SectionHit_RG_properties_commentaires.txt"
	 * <br/>
	 *
	 * @return CHEMIN_RELATIF_TEMPLATE_COMMENTAIRE : String.<br/>
	 */
	public static String getCheminRelatifTemplateCommentaire() {
		return CHEMIN_RELATIF_TEMPLATE_COMMENTAIRE;
	} // Fin de getCheminRelatifTemplateCommentaire()._____________________



	/* 1 - numDepartement. ********************/
	/**
	 * retourne le messageSectionHitNumDepartementRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitNumDepartementRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitNumDepartementRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitNumDepartementRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitNumDepartementRenseigne01
					, fournirKeyMessageSectionHitNumDepartementRenseigne01()
					, MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitNumDepartementRenseigne01()._______
	

	
	/**
	 * Getter de la clé du messageSectionHitNumDepartementRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.numdepartement.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitNumDepartementRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitNumDepartementRenseigne01().____



	/**
	 * Getter du <b>SINGLETON de messageSectionHitNumDepartementRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitNumDepartementRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitNumDepartementRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitNumDepartementRenseigne01() 
													throws Exception {
		return fournirMessageSectionHitNumDepartementRenseigne01();
	} // Fin de getMessageSectionHitNumDepartementRenseigne01().___________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitNumDepartementRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitNumDepartementRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitNumDepartementRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitNumDepartementRenseigne01
							, fournirKeyMessageSectionHitNumDepartementRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitNumDepartementRenseigne01(...).________



	/**
	 * retourne le messageSectionHitNumDepartementRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitNumDepartementRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitNumDepartementRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitNumDepartementRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitNumDepartementRegex02
					, fournirKeyMessageSectionHitNumDepartementRegex02()
					, MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitNumDepartementRegex02().___________
	

	
	/**
	 * Getter de la clé du messageSectionHitNumDepartementRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.numdepartement.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitNumDepartementRegex02() {
		return KEY_MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitNumDepartementRegex02().________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitNumDepartementRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitNumDepartementRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitNumDepartementRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitNumDepartementRegex02() 
													throws Exception {
		return fournirMessageSectionHitNumDepartementRegex02();
	} // Fin de getMessageSectionHitNumDepartementRegex02()._______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitNumDepartementRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitNumDepartementRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitNumDepartementRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitNumDepartementRegex02
							, fournirKeyMessageSectionHitNumDepartementRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitNumDepartementRegex02(...).____________


	
	/* 2 - numSection. ***************************/
	/**
	 * retourne le messageSectionHitNumSectionRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitNumSectionRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitNumSectionRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitNumSectionRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitNumSectionRenseigne01
					, fournirKeyMessageSectionHitNumSectionRenseigne01()
					, MESSAGE_SECTIONHIT_NUMSECTION_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitNumSectionRenseigne01()._______
	

	
	/**
	 * Getter de la clé du messageSectionHitNumSectionRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.numsection.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_NUMSECTION_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitNumSectionRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_NUMSECTION_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitNumSectionRenseigne01().____



	/**
	 * Getter du <b>SINGLETON de messageSectionHitNumSectionRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitNumSectionRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitNumSectionRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitNumSectionRenseigne01() 
													throws Exception {
		return fournirMessageSectionHitNumSectionRenseigne01();
	} // Fin de getMessageSectionHitNumSectionRenseigne01().___________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitNumSectionRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitNumSectionRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitNumSectionRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitNumSectionRenseigne01
							, fournirKeyMessageSectionHitNumSectionRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitNumSectionRenseigne01(...).________



	/**
	 * retourne le messageSectionHitNumSectionRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitNumSectionRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitNumSectionRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitNumSectionRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitNumSectionRegex02
					, fournirKeyMessageSectionHitNumSectionRegex02()
					, MESSAGE_SECTIONHIT_NUMSECTION_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitNumSectionRegex02().___________
	

	
	/**
	 * Getter de la clé du messageSectionHitNumSectionRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.numsection.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_NUMSECTION_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitNumSectionRegex02() {
		return KEY_MESSAGE_SECTIONHIT_NUMSECTION_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitNumSectionRegex02().________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitNumSectionRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitNumSectionRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitNumSectionRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitNumSectionRegex02() 
													throws Exception {
		return fournirMessageSectionHitNumSectionRegex02();
	} // Fin de getMessageSectionHitNumSectionRegex02()._______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitNumSectionRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitNumSectionRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitNumSectionRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitNumSectionRegex02
							, fournirKeyMessageSectionHitNumSectionRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitNumSectionRegex02(...).____________


	
	/* 3 - sens. ***************************/
	/**
	 * retourne le messageSectionHitSensRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitSensRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitSensRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitSensRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitSensRenseigne01
					, fournirKeyMessageSectionHitSensRenseigne01()
					, MESSAGE_SECTIONHIT_SENS_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitSensRenseigne01()._________________
	

	
	/**
	 * Getter de la clé du messageSectionHitSensRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.sens.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_SENS_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitSensRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_SENS_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitSensRenseigne01().______________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitSensRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitSensRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitSensRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitSensRenseigne01() 
													throws Exception {
		return fournirMessageSectionHitSensRenseigne01();
	} // Fin de getMessageSectionHitSensRenseigne01().___________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitSensRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitSensRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitSensRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitSensRenseigne01
							, fournirKeyMessageSectionHitSensRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitSensRenseigne01(...).________



	/**
	 * retourne le messageSectionHitSensRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitSensRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitSensRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitSensRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitSensRegex02
					, fournirKeyMessageSectionHitSensRegex02()
					, MESSAGE_SECTIONHIT_SENS_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitSensRegex02().___________
	

	
	/**
	 * Getter de la clé du messageSectionHitSensRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.sens.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_SENS_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitSensRegex02() {
		return KEY_MESSAGE_SECTIONHIT_SENS_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitSensRegex02().________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitSensRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitSensRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitSensRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitSensRegex02() 
													throws Exception {
		return fournirMessageSectionHitSensRegex02();
	} // Fin de getMessageSectionHitSensRegex02()._______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitSensRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitSensRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitSensRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitSensRegex02
							, fournirKeyMessageSectionHitSensRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitSensRegex02(...).____________



	/**
	 * retourne le messageSectionHitSensNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitSensNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitSensNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitSensNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitSensNomenclature03
					, fournirKeyMessageSectionHitSensNomenclature03()
					, MESSAGE_SECTIONHIT_SENS_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitSensNomenclature03().___________
	

	
	/**
	 * Getter de la clé du messageSectionHitSensNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.sens.nomenclature".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_SENS_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitSensNomenclature03() {
		return KEY_MESSAGE_SECTIONHIT_SENS_NOMENCLATURE_03;
	} // Fin de fournirKeyMessageSectionHitSensNomenclature03().________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitSensNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitSensNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitSensNomenclature03 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitSensNomenclature03() 
													throws Exception {
		return fournirMessageSectionHitSensNomenclature03();
	} // Fin de getMessageSectionHitSensNomenclature03()._______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitSensNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitSensNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitSensNomenclature03(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitSensNomenclature03
							, fournirKeyMessageSectionHitSensNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitSensNomenclature03(...).____________


	
	/* 4 - nature. ***************************/
	/**
	 * retourne le messageSectionHitNatureRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitNatureRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitNatureRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitNatureRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitNatureRenseigne01
					, fournirKeyMessageSectionHitNatureRenseigne01()
					, MESSAGE_SECTIONHIT_NATURE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitNatureRenseigne01()._______________
	

	
	/**
	 * Getter de la clé du messageSectionHitNatureRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.nature.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_NATURE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitNatureRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_NATURE_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitNatureRenseigne01().____________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitNatureRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitNatureRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitNatureRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitNatureRenseigne01() 
													throws Exception {
		return fournirMessageSectionHitNatureRenseigne01();
	} // Fin de getMessageSectionHitNatureRenseigne01().___________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitNatureRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitNatureRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitNatureRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitNatureRenseigne01
							, fournirKeyMessageSectionHitNatureRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitNatureRenseigne01(...).________________



	/**
	 * retourne le messageSectionHitNatureRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitNatureRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitNatureRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitNatureRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitNatureRegex02
					, fournirKeyMessageSectionHitNatureRegex02()
					, MESSAGE_SECTIONHIT_NATURE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitNatureRegex02().___________________
	

	
	/**
	 * Getter de la clé du messageSectionHitNatureRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.nature.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_NATURE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitNatureRegex02() {
		return KEY_MESSAGE_SECTIONHIT_NATURE_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitNatureRegex02().________________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitNatureRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitNatureRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitNatureRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitNatureRegex02() 
													throws Exception {
		return fournirMessageSectionHitNatureRegex02();
	} // Fin de getMessageSectionHitNatureRegex02()._______________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitNatureRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitNatureRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitNatureRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitNatureRegex02
							, fournirKeyMessageSectionHitNatureRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitNatureRegex02(...).____________________



	/**
	 * retourne le messageSectionHitNatureNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitNatureNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitNatureNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitNatureNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitNatureNomenclature03
					, fournirKeyMessageSectionHitNatureNomenclature03()
					, MESSAGE_SECTIONHIT_NATURE_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitNatureNomenclature03().____________
	

	
	/**
	 * Getter de la clé du messageSectionHitNatureNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.nature.nomenclature".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_NATURE_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitNatureNomenclature03() {
		return KEY_MESSAGE_SECTIONHIT_NATURE_NOMENCLATURE_03;
	} // Fin de fournirKeyMessageSectionHitNatureNomenclature03()._________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitNatureNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitNatureNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitNatureNomenclature03 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitNatureNomenclature03() 
													throws Exception {
		return fournirMessageSectionHitNatureNomenclature03();
	} // Fin de getMessageSectionHitNatureNomenclature03().________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitNatureNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitNatureNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitNatureNomenclature03(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitNatureNomenclature03
							, fournirKeyMessageSectionHitNatureNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitNatureNomenclature03(...)._____________


	
	/* 5 - classe. ***************************/
	/**
	 * retourne le messageSectionHitClasseRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitClasseRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitClasseRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitClasseRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitClasseRenseigne01
					, fournirKeyMessageSectionHitClasseRenseigne01()
					, MESSAGE_SECTIONHIT_CLASSE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitClasseRenseigne01()._______________
	

	
	/**
	 * Getter de la clé du messageSectionHitClasseRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.classe.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_CLASSE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitClasseRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_CLASSE_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitClasseRenseigne01().____________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitClasseRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitClasseRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitClasseRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitClasseRenseigne01() 
													throws Exception {
		return fournirMessageSectionHitClasseRenseigne01();
	} // Fin de getMessageSectionHitClasseRenseigne01().___________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitClasseRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitClasseRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitClasseRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitClasseRenseigne01
							, fournirKeyMessageSectionHitClasseRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitClasseRenseigne01(...).________________



	/**
	 * retourne le messageSectionHitClasseRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitClasseRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitClasseRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitClasseRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitClasseRegex02
					, fournirKeyMessageSectionHitClasseRegex02()
					, MESSAGE_SECTIONHIT_CLASSE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitClasseRegex02().___________________
	

	
	/**
	 * Getter de la clé du messageSectionHitClasseRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.classe.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_CLASSE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitClasseRegex02() {
		return KEY_MESSAGE_SECTIONHIT_CLASSE_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitClasseRegex02().________________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitClasseRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitClasseRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitClasseRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitClasseRegex02() 
													throws Exception {
		return fournirMessageSectionHitClasseRegex02();
	} // Fin de getMessageSectionHitClasseRegex02()._______________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitClasseRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitClasseRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitClasseRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitClasseRegex02
							, fournirKeyMessageSectionHitClasseRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitClasseRegex02(...).____________________


	
	/* 6 - anneeTraitement. ****************/
	/**
	 * retourne le messageSectionHitAnneeTraitementRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitAnneeTraitementRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitAnneeTraitementRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitAnneeTraitementRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitAnneeTraitementRenseigne01
					, fournirKeyMessageSectionHitAnneeTraitementRenseigne01()
					, MESSAGE_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitAnneeTraitementRenseigne01().______
	

	
	/**
	 * Getter de la clé du messageSectionHitAnneeTraitementRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.anneeTraitement.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitAnneeTraitementRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitAnneeTraitementRenseigne01().___



	/**
	 * Getter du <b>SINGLETON de messageSectionHitAnneeTraitementRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitAnneeTraitementRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitAnneeTraitementRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitAnneeTraitementRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitAnneeTraitementRenseigne01();
	} // Fin de getMessageSectionHitAnneeTraitementRenseigne01().__________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitAnneeTraitementRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitAnneeTraitementRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitAnneeTraitementRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitAnneeTraitementRenseigne01
							, fournirKeyMessageSectionHitAnneeTraitementRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitAnneeTraitementRenseigne01(...)._______



	/**
	 * retourne le messageSectionHitAnneeTraitementRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitAnneeTraitementRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitAnneeTraitementRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitAnneeTraitementRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitAnneeTraitementRegex02
					, fournirKeyMessageSectionHitAnneeTraitementRegex02()
					, MESSAGE_SECTIONHIT_ANNEETRAITEMENT_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitAnneeTraitementRegex02().__________
	

	
	/**
	 * Getter de la clé du messageSectionHitAnneeTraitementRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.anneeTraitement.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_ANNEETRAITEMENT_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitAnneeTraitementRegex02() {
		return KEY_MESSAGE_SECTIONHIT_ANNEETRAITEMENT_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitAnneeTraitementRegex02()._______



	/**
	 * Getter du <b>SINGLETON de messageSectionHitAnneeTraitementRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitAnneeTraitementRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitAnneeTraitementRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitAnneeTraitementRegex02() 
													throws Exception {
		return fournirMessageSectionHitAnneeTraitementRegex02();
	} // Fin de getMessageSectionHitAnneeTraitementRegex02().______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitAnneeTraitementRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitAnneeTraitementRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitAnneeTraitementRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitAnneeTraitementRegex02
							, fournirKeyMessageSectionHitAnneeTraitementRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitAnneeTraitementRegex02(...).___________


	
	/* 7 - zoneLibre1. ****************/
	/**
	 * retourne le messageSectionHitZoneLibre1Renseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitZoneLibre1Renseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitZoneLibre1Renseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitZoneLibre1Renseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitZoneLibre1Renseigne01
					, fournirKeyMessageSectionHitZoneLibre1Renseigne01()
					, MESSAGE_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitZoneLibre1Renseigne01().___________
	

	
	/**
	 * Getter de la clé du messageSectionHitZoneLibre1Renseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.zoneLibre1.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitZoneLibre1Renseigne01() {
		return KEY_MESSAGE_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitZoneLibre1Renseigne01().________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitZoneLibre1Renseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitZoneLibre1Renseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitZoneLibre1Renseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitZoneLibre1Renseigne01() 
											throws Exception {
		return fournirMessageSectionHitZoneLibre1Renseigne01();
	} // Fin de getMessageSectionHitZoneLibre1Renseigne01()._______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitZoneLibre1Renseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitZoneLibre1Renseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitZoneLibre1Renseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitZoneLibre1Renseigne01
							, fournirKeyMessageSectionHitZoneLibre1Renseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitZoneLibre1Renseigne01(...).____________



	/**
	 * retourne le messageSectionHitZoneLibre1Regex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitZoneLibre1Regex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitZoneLibre1Regex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitZoneLibre1Regex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitZoneLibre1Regex02
					, fournirKeyMessageSectionHitZoneLibre1Regex02()
					, MESSAGE_SECTIONHIT_ZONELIBRE1_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitZoneLibre1Regex02()._______________
	

	
	/**
	 * Getter de la clé du messageSectionHitZoneLibre1Regex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.zoneLibre1.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_ZONELIBRE1_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitZoneLibre1Regex02() {
		return KEY_MESSAGE_SECTIONHIT_ZONELIBRE1_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitZoneLibre1Regex02().____________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitZoneLibre1Regex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitZoneLibre1Regex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitZoneLibre1Regex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitZoneLibre1Regex02() 
													throws Exception {
		return fournirMessageSectionHitZoneLibre1Regex02();
	} // Fin de getMessageSectionHitZoneLibre1Regex02().___________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitZoneLibre1Regex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitZoneLibre1Regex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitZoneLibre1Regex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitZoneLibre1Regex02
							, fournirKeyMessageSectionHitZoneLibre1Regex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitZoneLibre1Regex02(...).________________


	
	/* 8 - numRoute. ****************/
	/**
	 * retourne le messageSectionHitNumRouteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitNumRouteRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitNumRouteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitNumRouteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitNumRouteRenseigne01
					, fournirKeyMessageSectionHitNumRouteRenseigne01()
					, MESSAGE_SECTIONHIT_NUMROUTE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitNumRouteRenseigne01()._____________
	

	
	/**
	 * Getter de la clé du messageSectionHitNumRouteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.numRoute.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_NUMROUTE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitNumRouteRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_NUMROUTE_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitNumRouteRenseigne01().__________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitNumRouteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitNumRouteRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitNumRouteRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitNumRouteRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitNumRouteRenseigne01();
	} // Fin de getMessageSectionHitNumRouteRenseigne01()._________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitNumRouteRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitNumRouteRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitNumRouteRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitNumRouteRenseigne01
							, fournirKeyMessageSectionHitNumRouteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitNumRouteRenseigne01(...).______________



	/**
	 * retourne le messageSectionHitNumRouteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitNumRouteRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitNumRouteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitNumRouteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitNumRouteRegex02
					, fournirKeyMessageSectionHitNumRouteRegex02()
					, MESSAGE_SECTIONHIT_NUMROUTE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitNumRouteRegex02()._________________
	

	
	/**
	 * Getter de la clé du messageSectionHitNumRouteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.numRoute.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_NUMROUTE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitNumRouteRegex02() {
		return KEY_MESSAGE_SECTIONHIT_NUMROUTE_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitNumRouteRegex02().______________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitNumRouteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitNumRouteRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitNumRouteRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitNumRouteRegex02() 
													throws Exception {
		return fournirMessageSectionHitNumRouteRegex02();
	} // Fin de getMessageSectionHitNumRouteRegex02()._____________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitNumRouteRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitNumRouteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitNumRouteRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitNumRouteRegex02
							, fournirKeyMessageSectionHitNumRouteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitNumRouteRegex02(...).__________________


	
	/* 9 - indiceNumRoute. ****************/
	/**
	 * retourne le messageSectionHitIndiceNumRouteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitIndiceNumRouteRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitIndiceNumRouteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitIndiceNumRouteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitIndiceNumRouteRenseigne01
					, fournirKeyMessageSectionHitIndiceNumRouteRenseigne01()
					, MESSAGE_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitIndiceNumRouteRenseigne01()._______
	

	
	/**
	 * Getter de la clé du messageSectionHitIndiceNumRouteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.indiceNumRoute.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitIndiceNumRouteRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitIndiceNumRouteRenseigne01().____



	/**
	 * Getter du <b>SINGLETON de messageSectionHitIndiceNumRouteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitIndiceNumRouteRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitIndiceNumRouteRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitIndiceNumRouteRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitIndiceNumRouteRenseigne01();
	} // Fin de getMessageSectionHitIndiceNumRouteRenseigne01().___________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitIndiceNumRouteRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitIndiceNumRouteRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitIndiceNumRouteRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitIndiceNumRouteRenseigne01
							, fournirKeyMessageSectionHitIndiceNumRouteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitIndiceNumRouteRenseigne01(...).________



	/**
	 * retourne le messageSectionHitIndiceNumRouteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitIndiceNumRouteRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitIndiceNumRouteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitIndiceNumRouteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitIndiceNumRouteRegex02
					, fournirKeyMessageSectionHitIndiceNumRouteRegex02()
					, MESSAGE_SECTIONHIT_INDICENUMROUTE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitIndiceNumRouteRegex02().___________
	

	
	/**
	 * Getter de la clé du messageSectionHitIndiceNumRouteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.indiceNumRoute.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_INDICENUMROUTE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitIndiceNumRouteRegex02() {
		return KEY_MESSAGE_SECTIONHIT_INDICENUMROUTE_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitIndiceNumRouteRegex02().________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitIndiceNumRouteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitIndiceNumRouteRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitIndiceNumRouteRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitIndiceNumRouteRegex02() 
													throws Exception {
		return fournirMessageSectionHitIndiceNumRouteRegex02();
	} // Fin de getMessageSectionHitIndiceNumRouteRegex02()._______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitIndiceNumRouteRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitIndiceNumRouteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitIndiceNumRouteRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitIndiceNumRouteRegex02
							, fournirKeyMessageSectionHitIndiceNumRouteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitIndiceNumRouteRegex02(...).____________


	
	/* 10 - indiceLettreRoute. ****************/
	/**
	 * retourne le messageSectionHitIndiceLettreRouteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitIndiceLettreRouteRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitIndiceLettreRouteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitIndiceLettreRouteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitIndiceLettreRouteRenseigne01
					, fournirKeyMessageSectionHitIndiceLettreRouteRenseigne01()
					, MESSAGE_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitIndiceLettreRouteRenseigne01().____
	

	
	/**
	 * Getter de la clé du messageSectionHitIndiceLettreRouteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.indiceLettreRoute.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitIndiceLettreRouteRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitIndiceLettreRouteRenseigne01()._



	/**
	 * Getter du <b>SINGLETON de messageSectionHitIndiceLettreRouteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitIndiceLettreRouteRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitIndiceLettreRouteRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitIndiceLettreRouteRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitIndiceLettreRouteRenseigne01();
	} // Fin de getMessageSectionHitIndiceLettreRouteRenseigne01().________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitIndiceLettreRouteRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitIndiceLettreRouteRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitIndiceLettreRouteRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitIndiceLettreRouteRenseigne01
							, fournirKeyMessageSectionHitIndiceLettreRouteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitIndiceLettreRouteRenseigne01(...)._____



	/**
	 * retourne le messageSectionHitIndiceLettreRouteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitIndiceLettreRouteRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitIndiceLettreRouteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitIndiceLettreRouteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitIndiceLettreRouteRegex02
					, fournirKeyMessageSectionHitIndiceLettreRouteRegex02()
					, MESSAGE_SECTIONHIT_INDICELETTREROUTE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitIndiceLettreRouteRegex02().________
	

	
	/**
	 * Getter de la clé du messageSectionHitIndiceLettreRouteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.indiceLettreRoute.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_INDICELETTREROUTE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitIndiceLettreRouteRegex02() {
		return KEY_MESSAGE_SECTIONHIT_INDICELETTREROUTE_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitIndiceLettreRouteRegex02()._____



	/**
	 * Getter du <b>SINGLETON de messageSectionHitIndiceLettreRouteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitIndiceLettreRouteRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitIndiceLettreRouteRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitIndiceLettreRouteRegex02() 
													throws Exception {
		return fournirMessageSectionHitIndiceLettreRouteRegex02();
	} // Fin de getMessageSectionHitIndiceLettreRouteRegex02().____________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitIndiceLettreRouteRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitIndiceLettreRouteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitIndiceLettreRouteRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitIndiceLettreRouteRegex02
							, fournirKeyMessageSectionHitIndiceLettreRouteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitIndiceLettreRouteRegex02(...)._________


	
	/* 11 - categorieAdminRoute. ****************/
	/**
	 * retourne le messageSectionHitCategorieAdminRouteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitCategorieAdminRouteRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitCategorieAdminRouteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitCategorieAdminRouteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitCategorieAdminRouteRenseigne01
					, fournirKeyMessageSectionHitCategorieAdminRouteRenseigne01()
					, MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitCategorieAdminRouteRenseigne01().__
	

	
	/**
	 * Getter de la clé du messageSectionHitCategorieAdminRouteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.categorieAdminRoute.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitCategorieAdminRouteRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitCategorieAdminRouteRenseigne01().



	/**
	 * Getter du <b>SINGLETON de messageSectionHitCategorieAdminRouteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitCategorieAdminRouteRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitCategorieAdminRouteRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitCategorieAdminRouteRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitCategorieAdminRouteRenseigne01();
	} // Fin de getMessageSectionHitCategorieAdminRouteRenseigne01().______
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitCategorieAdminRouteRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitCategorieAdminRouteRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitCategorieAdminRouteRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitCategorieAdminRouteRenseigne01
							, fournirKeyMessageSectionHitCategorieAdminRouteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitCategorieAdminRouteRenseigne01(...).___



	/**
	 * retourne le messageSectionHitCategorieAdminRouteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitCategorieAdminRouteRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitCategorieAdminRouteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitCategorieAdminRouteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitCategorieAdminRouteRegex02
					, fournirKeyMessageSectionHitCategorieAdminRouteRegex02()
					, MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitCategorieAdminRouteRegex02().______
	

	
	/**
	 * Getter de la clé du messageSectionHitCategorieAdminRouteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.categorieAdminRoute.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitCategorieAdminRouteRegex02() {
		return KEY_MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitCategorieAdminRouteRegex02().___



	/**
	 * Getter du <b>SINGLETON de messageSectionHitCategorieAdminRouteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitCategorieAdminRouteRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitCategorieAdminRouteRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitCategorieAdminRouteRegex02() 
													throws Exception {
		return fournirMessageSectionHitCategorieAdminRouteRegex02();
	} // Fin de getMessageSectionHitCategorieAdminRouteRegex02().__________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitCategorieAdminRouteRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitCategorieAdminRouteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitCategorieAdminRouteRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitCategorieAdminRouteRegex02
							, fournirKeyMessageSectionHitCategorieAdminRouteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitCategorieAdminRouteRegex02(...)._______
	


	/**
	 * retourne le messageSectionHitCategorieAdminRouteNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitCategorieAdminRouteNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitCategorieAdminRouteNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitCategorieAdminRouteNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitCategorieAdminRouteNomenclature03
					, fournirKeyMessageSectionHitCategorieAdminRouteNomenclature03()
					, MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitCategorieAdminRouteNomenclature03().
	

	
	/**
	 * Getter de la clé du messageSectionHitCategorieAdminRouteNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.categorieAdminRoute.nomenclature".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitCategorieAdminRouteNomenclature03() {
		return KEY_MESSAGE_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03;
	} // Fin de fournirKeyMessageSectionHitCategorieAdminRouteNomenclature03().



	/**
	 * Getter du <b>SINGLETON de messageSectionHitCategorieAdminRouteNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitCategorieAdminRouteNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitCategorieAdminRouteNomenclature03 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitCategorieAdminRouteNomenclature03() 
													throws Exception {
		return fournirMessageSectionHitCategorieAdminRouteNomenclature03();
	} // Fin de getMessageSectionHitCategorieAdminRouteNomenclature03().___
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitCategorieAdminRouteNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitCategorieAdminRouteNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitCategorieAdminRouteNomenclature03(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitCategorieAdminRouteNomenclature03
							, fournirKeyMessageSectionHitCategorieAdminRouteNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitCategorieAdminRouteNomenclature03(...).


	
	/* 12 - typeComptage. ****************/
	/**
	 * retourne le messageSectionHitTypeComptageRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitTypeComptageRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitTypeComptageRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitTypeComptageRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitTypeComptageRenseigne01
					, fournirKeyMessageSectionHitTypeComptageRenseigne01()
					, MESSAGE_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitTypeComptageRenseigne01()._________
	

	
	/**
	 * Getter de la clé du messageSectionHitTypeComptageRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.typeComptage.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitTypeComptageRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitTypeComptageRenseigne01().______



	/**
	 * Getter du <b>SINGLETON de messageSectionHitTypeComptageRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitTypeComptageRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitTypeComptageRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitTypeComptageRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitTypeComptageRenseigne01();
	} // Fin de getMessageSectionHitTypeComptageRenseigne01()._____________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitTypeComptageRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitTypeComptageRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitTypeComptageRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitTypeComptageRenseigne01
							, fournirKeyMessageSectionHitTypeComptageRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitTypeComptageRenseigne01(...).__________



	/**
	 * retourne le messageSectionHitTypeComptageRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitTypeComptageRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitTypeComptageRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitTypeComptageRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitTypeComptageRegex02
					, fournirKeyMessageSectionHitTypeComptageRegex02()
					, MESSAGE_SECTIONHIT_TYPECOMPTAGE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitTypeComptageRegex02()._____________
	

	
	/**
	 * Getter de la clé du messageSectionHitTypeComptageRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.typeComptage.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_TYPECOMPTAGE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitTypeComptageRegex02() {
		return KEY_MESSAGE_SECTIONHIT_TYPECOMPTAGE_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitTypeComptageRegex02().__________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitTypeComptageRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitTypeComptageRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitTypeComptageRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitTypeComptageRegex02() 
													throws Exception {
		return fournirMessageSectionHitTypeComptageRegex02();
	} // Fin de getMessageSectionHitTypeComptageRegex02()._________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitTypeComptageRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitTypeComptageRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitTypeComptageRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitTypeComptageRegex02
							, fournirKeyMessageSectionHitTypeComptageRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitTypeComptageRegex02(...).______________
	


	/**
	 * retourne le messageSectionHitTypeComptageNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitTypeComptageNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitTypeComptageNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitTypeComptageNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitTypeComptageNomenclature03
					, fournirKeyMessageSectionHitTypeComptageNomenclature03()
					, MESSAGE_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitTypeComptageNomenclature03().______
	

	
	/**
	 * Getter de la clé du messageSectionHitTypeComptageNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.typeComptage.nomenclature".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitTypeComptageNomenclature03() {
		return KEY_MESSAGE_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03;
	} // Fin de fournirKeyMessageSectionHitTypeComptageNomenclature03().________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitTypeComptageNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitTypeComptageNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitTypeComptageNomenclature03 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitTypeComptageNomenclature03() 
													throws Exception {
		return fournirMessageSectionHitTypeComptageNomenclature03();
	} // Fin de getMessageSectionHitTypeComptageNomenclature03().__________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitTypeComptageNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitTypeComptageNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitTypeComptageNomenclature03(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitTypeComptageNomenclature03
							, fournirKeyMessageSectionHitTypeComptageNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitTypeComptageNomenclature03(...)._______


	
	/* 13 - classementRoute. ****************/
	/**
	 * retourne le messageSectionHitClassementRouteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitClassementRouteRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitClassementRouteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitClassementRouteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitClassementRouteRenseigne01
					, fournirKeyMessageSectionHitClassementRouteRenseigne01()
					, MESSAGE_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitClassementRouteRenseigne01().______
	

	
	/**
	 * Getter de la clé du messageSectionHitClassementRouteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.classementRoute.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitClassementRouteRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitClassementRouteRenseigne01().___



	/**
	 * Getter du <b>SINGLETON de messageSectionHitClassementRouteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitClassementRouteRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitClassementRouteRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitClassementRouteRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitClassementRouteRenseigne01();
	} // Fin de getMessageSectionHitClassementRouteRenseigne01().__________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitClassementRouteRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitClassementRouteRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitClassementRouteRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitClassementRouteRenseigne01
							, fournirKeyMessageSectionHitClassementRouteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitClassementRouteRenseigne01(...)._______



	/**
	 * retourne le messageSectionHitClassementRouteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitClassementRouteRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitClassementRouteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitClassementRouteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitClassementRouteRegex02
					, fournirKeyMessageSectionHitClassementRouteRegex02()
					, MESSAGE_SECTIONHIT_CLASSEMENTROUTE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitClassementRouteRegex02().__________
	

	
	/**
	 * Getter de la clé du messageSectionHitClassementRouteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.classementRoute.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_CLASSEMENTROUTE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitClassementRouteRegex02() {
		return KEY_MESSAGE_SECTIONHIT_CLASSEMENTROUTE_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitClassementRouteRegex02()._______



	/**
	 * Getter du <b>SINGLETON de messageSectionHitClassementRouteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitClassementRouteRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitClassementRouteRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitClassementRouteRegex02() 
													throws Exception {
		return fournirMessageSectionHitClassementRouteRegex02();
	} // Fin de getMessageSectionHitClassementRouteRegex02().______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitClassementRouteRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitClassementRouteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitClassementRouteRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitClassementRouteRegex02
							, fournirKeyMessageSectionHitClassementRouteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitClassementRouteRegex02(...).___________
	


	/**
	 * retourne le messageSectionHitClassementRouteNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitClassementRouteNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitClassementRouteNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitClassementRouteNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitClassementRouteNomenclature03
					, fournirKeyMessageSectionHitClassementRouteNomenclature03()
					, MESSAGE_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitClassementRouteNomenclature03().___
	

	
	/**
	 * Getter de la clé du messageSectionHitClassementRouteNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.classementRoute.nomenclature".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitClassementRouteNomenclature03() {
		return KEY_MESSAGE_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03;
	} // Fin de fournirKeyMessageSectionHitClassementRouteNomenclature03().



	/**
	 * Getter du <b>SINGLETON de messageSectionHitClassementRouteNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitClassementRouteNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitClassementRouteNomenclature03 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitClassementRouteNomenclature03() 
													throws Exception {
		return fournirMessageSectionHitClassementRouteNomenclature03();
	} // Fin de getMessageSectionHitClassementRouteNomenclature03()._______
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitClassementRouteNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitClassementRouteNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitClassementRouteNomenclature03(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitClassementRouteNomenclature03
							, fournirKeyMessageSectionHitClassementRouteNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitClassementRouteNomenclature03(...).____


	
	/* 14 - classeLargeurChausseeU. ****************/
	/**
	 * retourne le messageSectionHitClasseLargeurChausseeURenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitClasseLargeurChausseeURenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitClasseLargeurChausseeURenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitClasseLargeurChausseeURenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitClasseLargeurChausseeURenseigne01
					, fournirKeyMessageSectionHitClasseLargeurChausseeURenseigne01()
					, MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitClasseLargeurChausseeURenseigne01().
	

	
	/**
	 * Getter de la clé du messageSectionHitClasseLargeurChausseeURenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.classeLargeurChausseeU.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitClasseLargeurChausseeURenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitClasseLargeurChausseeURenseigne01().



	/**
	 * Getter du <b>SINGLETON de messageSectionHitClasseLargeurChausseeURenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitClasseLargeurChausseeURenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitClasseLargeurChausseeURenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitClasseLargeurChausseeURenseigne01() 
											throws Exception {
		return fournirMessageSectionHitClasseLargeurChausseeURenseigne01();
	} // Fin de getMessageSectionHitClasseLargeurChausseeURenseigne01().___
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitClasseLargeurChausseeURenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitClasseLargeurChausseeURenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitClasseLargeurChausseeURenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitClasseLargeurChausseeURenseigne01
							, fournirKeyMessageSectionHitClasseLargeurChausseeURenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitClasseLargeurChausseeURenseigne01(...).



	/**
	 * retourne le messageSectionHitClasseLargeurChausseeURegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitClasseLargeurChausseeURegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitClasseLargeurChausseeURegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitClasseLargeurChausseeURegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitClasseLargeurChausseeURegex02
					, fournirKeyMessageSectionHitClasseLargeurChausseeURegex02()
					, MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitClasseLargeurChausseeURegex02().___
	

	
	/**
	 * Getter de la clé du messageSectionHitClasseLargeurChausseeURegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.classeLargeurChausseeU.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitClasseLargeurChausseeURegex02() {
		return KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitClasseLargeurChausseeURegex02().



	/**
	 * Getter du <b>SINGLETON de messageSectionHitClasseLargeurChausseeURegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitClasseLargeurChausseeURegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitClasseLargeurChausseeURegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitClasseLargeurChausseeURegex02() 
													throws Exception {
		return fournirMessageSectionHitClasseLargeurChausseeURegex02();
	} // Fin de getMessageSectionHitClasseLargeurChausseeURegex02()._______
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitClasseLargeurChausseeURegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitClasseLargeurChausseeURegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitClasseLargeurChausseeURegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitClasseLargeurChausseeURegex02
							, fournirKeyMessageSectionHitClasseLargeurChausseeURegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitClasseLargeurChausseeURegex02(...).____
	


	/**
	 * retourne le messageSectionHitClasseLargeurChausseeUNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitClasseLargeurChausseeUNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitClasseLargeurChausseeUNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitClasseLargeurChausseeUNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitClasseLargeurChausseeUNomenclature03
					, fournirKeyMessageSectionHitClasseLargeurChausseeUNomenclature03()
					, MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitClasseLargeurChausseeUNomenclature03().
	

	
	/**
	 * Getter de la clé du messageSectionHitClasseLargeurChausseeUNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.classeLargeurChausseeU.nomenclature".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitClasseLargeurChausseeUNomenclature03() {
		return KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03;
	} // Fin de fournirKeyMessageSectionHitClasseLargeurChausseeUNomenclature03().



	/**
	 * Getter du <b>SINGLETON de messageSectionHitClasseLargeurChausseeUNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitClasseLargeurChausseeUNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitClasseLargeurChausseeUNomenclature03 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitClasseLargeurChausseeUNomenclature03() 
													throws Exception {
		return fournirMessageSectionHitClasseLargeurChausseeUNomenclature03();
	} // Fin de getMessageSectionHitClasseLargeurChausseeUNomenclature03().
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitClasseLargeurChausseeUNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitClasseLargeurChausseeUNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitClasseLargeurChausseeUNomenclature03(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitClasseLargeurChausseeUNomenclature03
							, fournirKeyMessageSectionHitClasseLargeurChausseeUNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitClasseLargeurChausseeUNomenclature03(...).


	
	/* 15 - classeLargeurChausseesS. ****************/
	/**
	 * retourne le messageSectionHitClasseLargeurChausseesSRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitClasseLargeurChausseesSRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitClasseLargeurChausseesSRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitClasseLargeurChausseesSRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitClasseLargeurChausseesSRenseigne01
					, fournirKeyMessageSectionHitClasseLargeurChausseesSRenseigne01()
					, MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitClasseLargeurChausseesSRenseigne01().
	

	
	/**
	 * Getter de la clé du messageSectionHitClasseLargeurChausseesSRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.classeLargeurChausseesS.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitClasseLargeurChausseesSRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitClasseLargeurChausseesSRenseigne01().



	/**
	 * Getter du <b>SINGLETON de messageSectionHitClasseLargeurChausseesSRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitClasseLargeurChausseesSRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitClasseLargeurChausseesSRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitClasseLargeurChausseesSRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitClasseLargeurChausseesSRenseigne01();
	} // Fin de getMessageSectionHitClasseLargeurChausseesSRenseigne01().__
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitClasseLargeurChausseesSRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitClasseLargeurChausseesSRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitClasseLargeurChausseesSRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitClasseLargeurChausseesSRenseigne01
							, fournirKeyMessageSectionHitClasseLargeurChausseesSRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitClasseLargeurChausseesSRenseigne01(...).



	/**
	 * retourne le messageSectionHitClasseLargeurChausseesSRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitClasseLargeurChausseesSRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitClasseLargeurChausseesSRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitClasseLargeurChausseesSRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitClasseLargeurChausseesSRegex02
					, fournirKeyMessageSectionHitClasseLargeurChausseesSRegex02()
					, MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitClasseLargeurChausseesSRegex02().__
	

	
	/**
	 * Getter de la clé du messageSectionHitClasseLargeurChausseesSRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.classeLargeurChausseesS.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitClasseLargeurChausseesSRegex02() {
		return KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitClasseLargeurChausseesSRegex02().



	/**
	 * Getter du <b>SINGLETON de messageSectionHitClasseLargeurChausseesSRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitClasseLargeurChausseesSRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitClasseLargeurChausseesSRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitClasseLargeurChausseesSRegex02() 
													throws Exception {
		return fournirMessageSectionHitClasseLargeurChausseesSRegex02();
	} // Fin de getMessageSectionHitClasseLargeurChausseesSRegex02().______
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitClasseLargeurChausseesSRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitClasseLargeurChausseesSRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitClasseLargeurChausseesSRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitClasseLargeurChausseesSRegex02
							, fournirKeyMessageSectionHitClasseLargeurChausseesSRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitClasseLargeurChausseesSRegex02(...).___
	


	/**
	 * retourne le messageSectionHitClasseLargeurChausseesSNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitClasseLargeurChausseesSNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitClasseLargeurChausseesSNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitClasseLargeurChausseesSNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitClasseLargeurChausseesSNomenclature03
					, fournirKeyMessageSectionHitClasseLargeurChausseesSNomenclature03()
					, MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitClasseLargeurChausseesSNomenclature03().
	

	
	/**
	 * Getter de la clé du messageSectionHitClasseLargeurChausseesSNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.classeLargeurChausseesS.nomenclature".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitClasseLargeurChausseesSNomenclature03() {
		return KEY_MESSAGE_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03;
	} // Fin de fournirKeyMessageSectionHitClasseLargeurChausseesSNomenclature03().



	/**
	 * Getter du <b>SINGLETON de messageSectionHitClasseLargeurChausseesSNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitClasseLargeurChausseesSNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitClasseLargeurChausseesSNomenclature03 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitClasseLargeurChausseesSNomenclature03() 
													throws Exception {
		return fournirMessageSectionHitClasseLargeurChausseesSNomenclature03();
	} // Fin de getMessageSectionHitClasseLargeurChausseesSNomenclature03().
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitClasseLargeurChausseesSNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitClasseLargeurChausseesSNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitClasseLargeurChausseesSNomenclature03(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitClasseLargeurChausseesSNomenclature03
							, fournirKeyMessageSectionHitClasseLargeurChausseesSNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitClasseLargeurChausseesSNomenclature03(...).


	
	/* 16 - typeReseau. ****************/
	/**
	 * retourne le messageSectionHitTypeReseauRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitTypeReseauRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitTypeReseauRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitTypeReseauRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitTypeReseauRenseigne01
					, fournirKeyMessageSectionHitTypeReseauRenseigne01()
					, MESSAGE_SECTIONHIT_TYPERESEAU_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitTypeReseauRenseigne01().___________
	

	
	/**
	 * Getter de la clé du messageSectionHitTypeReseauRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.typeReseau.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_TYPERESEAU_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitTypeReseauRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_TYPERESEAU_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitTypeReseauRenseigne01().________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitTypeReseauRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitTypeReseauRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitTypeReseauRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitTypeReseauRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitTypeReseauRenseigne01();
	} // Fin de getMessageSectionHitTypeReseauRenseigne01()._______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitTypeReseauRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitTypeReseauRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitTypeReseauRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitTypeReseauRenseigne01
							, fournirKeyMessageSectionHitTypeReseauRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitTypeReseauRenseigne01(...).____________



	/**
	 * retourne le messageSectionHitTypeReseauRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitTypeReseauRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitTypeReseauRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitTypeReseauRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitTypeReseauRegex02
					, fournirKeyMessageSectionHitTypeReseauRegex02()
					, MESSAGE_SECTIONHIT_TYPERESEAU_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitTypeReseauRegex02()._______________
	

	
	/**
	 * Getter de la clé du messageSectionHitTypeReseauRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.typeReseau.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_TYPERESEAU_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitTypeReseauRegex02() {
		return KEY_MESSAGE_SECTIONHIT_TYPERESEAU_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitTypeReseauRegex02().____________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitTypeReseauRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitTypeReseauRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitTypeReseauRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitTypeReseauRegex02() 
													throws Exception {
		return fournirMessageSectionHitTypeReseauRegex02();
	} // Fin de getMessageSectionHitTypeReseauRegex02().___________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitTypeReseauRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitTypeReseauRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitTypeReseauRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitTypeReseauRegex02
							, fournirKeyMessageSectionHitTypeReseauRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitTypeReseauRegex02(...).________________
	


	/**
	 * retourne le messageSectionHitTypeReseauNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitTypeReseauNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitTypeReseauNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitTypeReseauNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitTypeReseauNomenclature03
					, fournirKeyMessageSectionHitTypeReseauNomenclature03()
					, MESSAGE_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitTypeReseauNomenclature03().________
	

	
	/**
	 * Getter de la clé du messageSectionHitTypeReseauNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.typeReseau.nomenclature".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitTypeReseauNomenclature03() {
		return KEY_MESSAGE_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03;
	} // Fin de fournirKeyMessageSectionHitTypeReseauNomenclature03()._____



	/**
	 * Getter du <b>SINGLETON de messageSectionHitTypeReseauNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitTypeReseauNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitTypeReseauNomenclature03 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitTypeReseauNomenclature03() 
													throws Exception {
		return fournirMessageSectionHitTypeReseauNomenclature03();
	} // Fin de getMessageSectionHitTypeReseauNomenclature03().____________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitTypeReseauNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitTypeReseauNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitTypeReseauNomenclature03(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitTypeReseauNomenclature03
							, fournirKeyMessageSectionHitTypeReseauNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitTypeReseauNomenclature03(...)._________


	
	/* 17 - pRoupK. ****************/
	/**
	 * retourne le messageSectionHitPRoupKRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitPRoupKRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitPRoupKRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitPRoupKRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitPRoupKRenseigne01
					, fournirKeyMessageSectionHitPRoupKRenseigne01()
					, MESSAGE_SECTIONHIT_PROUPK_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitPRoupKRenseigne01()._______________
	

	
	/**
	 * Getter de la clé du messageSectionHitPRoupKRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.pRoupK.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_PROUPK_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitPRoupKRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_PROUPK_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitPRoupKRenseigne01().____________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitPRoupKRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitPRoupKRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitPRoupKRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitPRoupKRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitPRoupKRenseigne01();
	} // Fin de getMessageSectionHitPRoupKRenseigne01().___________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitPRoupKRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitPRoupKRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitPRoupKRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitPRoupKRenseigne01
							, fournirKeyMessageSectionHitPRoupKRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitPRoupKRenseigne01(...).________________



	/**
	 * retourne le messageSectionHitPRoupKRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitPRoupKRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitPRoupKRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitPRoupKRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitPRoupKRegex02
					, fournirKeyMessageSectionHitPRoupKRegex02()
					, MESSAGE_SECTIONHIT_PROUPK_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitPRoupKRegex02().___________________
	

	
	/**
	 * Getter de la clé du messageSectionHitPRoupKRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.pRoupK.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_PROUPK_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitPRoupKRegex02() {
		return KEY_MESSAGE_SECTIONHIT_PROUPK_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitPRoupKRegex02().________________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitPRoupKRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitPRoupKRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitPRoupKRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitPRoupKRegex02() 
													throws Exception {
		return fournirMessageSectionHitPRoupKRegex02();
	} // Fin de getMessageSectionHitPRoupKRegex02()._______________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitPRoupKRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitPRoupKRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitPRoupKRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitPRoupKRegex02
							, fournirKeyMessageSectionHitPRoupKRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitPRoupKRegex02(...).____________________
	


	/**
	 * retourne le messageSectionHitPRoupKNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitPRoupKNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitPRoupKNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitPRoupKNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitPRoupKNomenclature03
					, fournirKeyMessageSectionHitPRoupKNomenclature03()
					, MESSAGE_SECTIONHIT_PROUPK_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitPRoupKNomenclature03().____________
	

	
	/**
	 * Getter de la clé du messageSectionHitPRoupKNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.pRoupK.nomenclature".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_PROUPK_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitPRoupKNomenclature03() {
		return KEY_MESSAGE_SECTIONHIT_PROUPK_NOMENCLATURE_03;
	} // Fin de fournirKeyMessageSectionHitPRoupKNomenclature03()._________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitPRoupKNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitPRoupKNomenclature03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitPRoupKNomenclature03 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitPRoupKNomenclature03() 
													throws Exception {
		return fournirMessageSectionHitPRoupKNomenclature03();
	} // Fin de getMessageSectionHitPRoupKNomenclature03().________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitPRoupKNomenclature03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitPRoupKNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitPRoupKNomenclature03(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitPRoupKNomenclature03
							, fournirKeyMessageSectionHitPRoupKNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitPRoupKNomenclature03(...)._____________


	
	/* 18 - lieuDitOrigine. ****************/
	/**
	 * retourne le messageSectionHitLieuDitOrigineRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitLieuDitOrigineRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitLieuDitOrigineRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitLieuDitOrigineRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitLieuDitOrigineRenseigne01
					, fournirKeyMessageSectionHitLieuDitOrigineRenseigne01()
					, MESSAGE_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitLieuDitOrigineRenseigne01()._______
	

	
	/**
	 * Getter de la clé du messageSectionHitLieuDitOrigineRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.lieuDitOrigine.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitLieuDitOrigineRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitLieuDitOrigineRenseigne01().____



	/**
	 * Getter du <b>SINGLETON de messageSectionHitLieuDitOrigineRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitLieuDitOrigineRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitLieuDitOrigineRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitLieuDitOrigineRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitLieuDitOrigineRenseigne01();
	} // Fin de getMessageSectionHitLieuDitOrigineRenseigne01().___________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitLieuDitOrigineRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitLieuDitOrigineRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitLieuDitOrigineRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitLieuDitOrigineRenseigne01
							, fournirKeyMessageSectionHitLieuDitOrigineRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitLieuDitOrigineRenseigne01(...).________



	/**
	 * retourne le messageSectionHitLieuDitOrigineRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitLieuDitOrigineRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitLieuDitOrigineRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitLieuDitOrigineRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitLieuDitOrigineRegex02
					, fournirKeyMessageSectionHitLieuDitOrigineRegex02()
					, MESSAGE_SECTIONHIT_LIEUDITORIGINE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitLieuDitOrigineRegex02().___________
	

	
	/**
	 * Getter de la clé du messageSectionHitLieuDitOrigineRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.lieuDitOrigine.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_LIEUDITORIGINE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitLieuDitOrigineRegex02() {
		return KEY_MESSAGE_SECTIONHIT_LIEUDITORIGINE_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitLieuDitOrigineRegex02().________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitLieuDitOrigineRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitLieuDitOrigineRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitLieuDitOrigineRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitLieuDitOrigineRegex02() 
													throws Exception {
		return fournirMessageSectionHitLieuDitOrigineRegex02();
	} // Fin de getMessageSectionHitLieuDitOrigineRegex02()._______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitLieuDitOrigineRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitLieuDitOrigineRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitLieuDitOrigineRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitLieuDitOrigineRegex02
							, fournirKeyMessageSectionHitLieuDitOrigineRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitLieuDitOrigineRegex02(...).____________


	
	/* 19 - prOrigine. ****************/
	/**
	 * retourne le messageSectionHitPrOrigineRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitPrOrigineRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitPrOrigineRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitPrOrigineRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitPrOrigineRenseigne01
					, fournirKeyMessageSectionHitPrOrigineRenseigne01()
					, MESSAGE_SECTIONHIT_PRORIGINE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitPrOrigineRenseigne01().____________
	

	
	/**
	 * Getter de la clé du messageSectionHitPrOrigineRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.prOrigine.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_PRORIGINE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitPrOrigineRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_PRORIGINE_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitPrOrigineRenseigne01()._________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitPrOrigineRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitPrOrigineRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitPrOrigineRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitPrOrigineRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitPrOrigineRenseigne01();
	} // Fin de getMessageSectionHitPrOrigineRenseigne01().________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitPrOrigineRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitPrOrigineRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitPrOrigineRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitPrOrigineRenseigne01
							, fournirKeyMessageSectionHitPrOrigineRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitPrOrigineRenseigne01(...)._____________



	/**
	 * retourne le messageSectionHitPrOrigineRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitPrOrigineRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitPrOrigineRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitPrOrigineRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitPrOrigineRegex02
					, fournirKeyMessageSectionHitPrOrigineRegex02()
					, MESSAGE_SECTIONHIT_PRORIGINE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitPrOrigineRegex02().________________
	

	
	/**
	 * Getter de la clé du messageSectionHitPrOrigineRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.prOrigine.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_PRORIGINE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitPrOrigineRegex02() {
		return KEY_MESSAGE_SECTIONHIT_PRORIGINE_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitPrOrigineRegex02()._____________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitPrOrigineRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitPrOrigineRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitPrOrigineRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitPrOrigineRegex02() 
													throws Exception {
		return fournirMessageSectionHitPrOrigineRegex02();
	} // Fin de getMessageSectionHitPrOrigineRegex02().____________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitPrOrigineRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitPrOrigineRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitPrOrigineRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitPrOrigineRegex02
							, fournirKeyMessageSectionHitPrOrigineRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitPrOrigineRegex02(...)._________________



	/**
	 * retourne le messageSectionHitPrOrigineNumerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitPrOrigineNumerique03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitPrOrigineNumerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitPrOrigineNumerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitPrOrigineNumerique03
					, fournirKeyMessageSectionHitPrOrigineNumerique03()
					, MESSAGE_SECTIONHIT_PRORIGINE_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitPrOrigineNumerique03().____________
	

	
	/**
	 * Getter de la clé du messageSectionHitPrOrigineNumerique03 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.prOrigine.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_PRORIGINE_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitPrOrigineNumerique03() {
		return KEY_MESSAGE_SECTIONHIT_PRORIGINE_NUMERIQUE_03;
	} // Fin de fournirKeyMessageSectionHitPrOrigineNumerique03()._________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitPrOrigineNumerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitPrOrigineNumerique03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitPrOrigineNumerique03 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitPrOrigineNumerique03() 
													throws Exception {
		return fournirMessageSectionHitPrOrigineNumerique03();
	} // Fin de getMessageSectionHitPrOrigineNumerique03().________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitPrOrigineNumerique03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitPrOrigineNumerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitPrOrigineNumerique03(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitPrOrigineNumerique03
							, fournirKeyMessageSectionHitPrOrigineNumerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitPrOrigineNumerique03(...)._____________	


	
	/* 20 - absOrigine. ****************/
	/**
	 * retourne le messageSectionHitAbsOrigineRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitAbsOrigineRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitAbsOrigineRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitAbsOrigineRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitAbsOrigineRenseigne01
					, fournirKeyMessageSectionHitAbsOrigineRenseigne01()
					, MESSAGE_SECTIONHIT_ABSORIGINE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitAbsOrigineRenseigne01().___________
	

	
	/**
	 * Getter de la clé du messageSectionHitAbsOrigineRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.absOrigine.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_ABSORIGINE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitAbsOrigineRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_ABSORIGINE_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitAbsOrigineRenseigne01().________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitAbsOrigineRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitAbsOrigineRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitAbsOrigineRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitAbsOrigineRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitAbsOrigineRenseigne01();
	} // Fin de getMessageSectionHitAbsOrigineRenseigne01()._______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitAbsOrigineRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitAbsOrigineRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitAbsOrigineRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitAbsOrigineRenseigne01
							, fournirKeyMessageSectionHitAbsOrigineRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitAbsOrigineRenseigne01(...).____________



	/**
	 * retourne le messageSectionHitAbsOrigineRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitAbsOrigineRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitAbsOrigineRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitAbsOrigineRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitAbsOrigineRegex02
					, fournirKeyMessageSectionHitAbsOrigineRegex02()
					, MESSAGE_SECTIONHIT_ABSORIGINE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitAbsOrigineRegex02()._______________
	

	
	/**
	 * Getter de la clé du messageSectionHitAbsOrigineRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.absOrigine.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_ABSORIGINE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitAbsOrigineRegex02() {
		return KEY_MESSAGE_SECTIONHIT_ABSORIGINE_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitAbsOrigineRegex02().____________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitAbsOrigineRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitAbsOrigineRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitAbsOrigineRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitAbsOrigineRegex02() 
													throws Exception {
		return fournirMessageSectionHitAbsOrigineRegex02();
	} // Fin de getMessageSectionHitAbsOrigineRegex02().___________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitAbsOrigineRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitAbsOrigineRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitAbsOrigineRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitAbsOrigineRegex02
							, fournirKeyMessageSectionHitAbsOrigineRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitAbsOrigineRegex02(...).________________



	/**
	 * retourne le messageSectionHitAbsOrigineNumerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitAbsOrigineNumerique03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitAbsOrigineNumerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitAbsOrigineNumerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitAbsOrigineNumerique03
					, fournirKeyMessageSectionHitAbsOrigineNumerique03()
					, MESSAGE_SECTIONHIT_ABSORIGINE_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitAbsOrigineNumerique03().___________
	

	
	/**
	 * Getter de la clé du messageSectionHitAbsOrigineNumerique03 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.prOrigine.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_ABSORIGINE_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitAbsOrigineNumerique03() {
		return KEY_MESSAGE_SECTIONHIT_ABSORIGINE_NUMERIQUE_03;
	} // Fin de fournirKeyMessageSectionHitAbsOrigineNumerique03().________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitAbsOrigineNumerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitAbsOrigineNumerique03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitAbsOrigineNumerique03 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitAbsOrigineNumerique03() 
													throws Exception {
		return fournirMessageSectionHitAbsOrigineNumerique03();
	} // Fin de getMessageSectionHitAbsOrigineNumerique03()._______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitAbsOrigineNumerique03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitAbsOrigineNumerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitAbsOrigineNumerique03(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitAbsOrigineNumerique03
							, fournirKeyMessageSectionHitAbsOrigineNumerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitAbsOrigineNumerique03(...).____________	


	
	/* 21 - lieuDitExtremite. ****************/
	/**
	 * retourne le messageSectionHitLieuDitExtremiteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitLieuDitExtremiteRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitLieuDitExtremiteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitLieuDitExtremiteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitLieuDitExtremiteRenseigne01
					, fournirKeyMessageSectionHitLieuDitExtremiteRenseigne01()
					, MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitLieuDitExtremiteRenseigne01()._____
	

	
	/**
	 * Getter de la clé du messageSectionHitLieuDitExtremiteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.lieuDitExtremite.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitLieuDitExtremiteRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitLieuDitExtremiteRenseigne01().__



	/**
	 * Getter du <b>SINGLETON de messageSectionHitLieuDitExtremiteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitLieuDitExtremiteRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitLieuDitExtremiteRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitLieuDitExtremiteRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitLieuDitExtremiteRenseigne01();
	} // Fin de getMessageSectionHitLieuDitExtremiteRenseigne01()._________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitLieuDitExtremiteRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitLieuDitExtremiteRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitLieuDitExtremiteRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitLieuDitExtremiteRenseigne01
							, fournirKeyMessageSectionHitLieuDitExtremiteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitLieuDitExtremiteRenseigne01(...).______



	/**
	 * retourne le messageSectionHitLieuDitExtremiteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitLieuDitExtremiteRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitLieuDitExtremiteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitLieuDitExtremiteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitLieuDitExtremiteRegex02
					, fournirKeyMessageSectionHitLieuDitExtremiteRegex02()
					, MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitLieuDitExtremiteRegex02()._________
	

	
	/**
	 * Getter de la clé du messageSectionHitLieuDitExtremiteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.lieuDitExtremite.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitLieuDitExtremiteRegex02() {
		return KEY_MESSAGE_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitLieuDitExtremiteRegex02().______



	/**
	 * Getter du <b>SINGLETON de messageSectionHitLieuDitExtremiteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitLieuDitExtremiteRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitLieuDitExtremiteRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitLieuDitExtremiteRegex02() 
													throws Exception {
		return fournirMessageSectionHitLieuDitExtremiteRegex02();
	} // Fin de getMessageSectionHitLieuDitExtremiteRegex02()._____________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitLieuDitExtremiteRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitLieuDitExtremiteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitLieuDitExtremiteRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitLieuDitExtremiteRegex02
							, fournirKeyMessageSectionHitLieuDitExtremiteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitLieuDitExtremiteRegex02(...).__________


	
	/* 22 - prExtremite. ****************/
	/**
	 * retourne le messageSectionHitPrExtremiteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitPrExtremiteRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitPrExtremiteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitPrExtremiteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitPrExtremiteRenseigne01
					, fournirKeyMessageSectionHitPrExtremiteRenseigne01()
					, MESSAGE_SECTIONHIT_PREXTREMITE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitPrExtremiteRenseigne01().__________
	

	
	/**
	 * Getter de la clé du messageSectionHitPrExtremiteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.prExtremite.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_PREXTREMITE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitPrExtremiteRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_PREXTREMITE_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitPrExtremiteRenseigne01()._______



	/**
	 * Getter du <b>SINGLETON de messageSectionHitPrExtremiteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitPrExtremiteRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitPrExtremiteRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitPrExtremiteRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitPrExtremiteRenseigne01();
	} // Fin de getMessageSectionHitPrExtremiteRenseigne01().______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitPrExtremiteRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitPrExtremiteRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitPrExtremiteRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitPrExtremiteRenseigne01
							, fournirKeyMessageSectionHitPrExtremiteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitPrExtremiteRenseigne01(...).___________



	/**
	 * retourne le messageSectionHitPrExtremiteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitPrExtremiteRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitPrExtremiteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitPrExtremiteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitPrExtremiteRegex02
					, fournirKeyMessageSectionHitPrExtremiteRegex02()
					, MESSAGE_SECTIONHIT_PREXTREMITE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitPrExtremiteRegex02().______________
	

	
	/**
	 * Getter de la clé du messageSectionHitPrExtremiteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.prExtremite.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_PREXTREMITE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitPrExtremiteRegex02() {
		return KEY_MESSAGE_SECTIONHIT_PREXTREMITE_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitPrExtremiteRegex02().___________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitPrExtremiteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitPrExtremiteRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitPrExtremiteRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitPrExtremiteRegex02() 
													throws Exception {
		return fournirMessageSectionHitPrExtremiteRegex02();
	} // Fin de getMessageSectionHitPrExtremiteRegex02().__________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitPrExtremiteRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitPrExtremiteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitPrExtremiteRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitPrExtremiteRegex02
							, fournirKeyMessageSectionHitPrExtremiteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitPrExtremiteRegex02(...)._______________



	/**
	 * retourne le messageSectionHitPrExtremiteNumerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitPrExtremiteNumerique03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitPrExtremiteNumerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitPrExtremiteNumerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitPrExtremiteNumerique03
					, fournirKeyMessageSectionHitPrExtremiteNumerique03()
					, MESSAGE_SECTIONHIT_PREXTREMITE_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitPrExtremiteNumerique03().__________
	

	
	/**
	 * Getter de la clé du messageSectionHitPrExtremiteNumerique03 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.prExtremite.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_PREXTREMITE_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitPrExtremiteNumerique03() {
		return KEY_MESSAGE_SECTIONHIT_PREXTREMITE_NUMERIQUE_03;
	} // Fin de fournirKeyMessageSectionHitPrExtremiteNumerique03()._______



	/**
	 * Getter du <b>SINGLETON de messageSectionHitPrExtremiteNumerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitPrExtremiteNumerique03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitPrExtremiteNumerique03 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitPrExtremiteNumerique03() 
													throws Exception {
		return fournirMessageSectionHitPrExtremiteNumerique03();
	} // Fin de getMessageSectionHitPrExtremiteNumerique03().______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitPrExtremiteNumerique03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitPrExtremiteNumerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitPrExtremiteNumerique03(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitPrExtremiteNumerique03
							, fournirKeyMessageSectionHitPrExtremiteNumerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitPrExtremiteNumerique03(...).___________	


	
	/* 23 - absExtremite. ****************/
	/**
	 * retourne le messageSectionHitAbsExtremiteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitAbsExtremiteRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitAbsExtremiteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitAbsExtremiteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitAbsExtremiteRenseigne01
					, fournirKeyMessageSectionHitAbsExtremiteRenseigne01()
					, MESSAGE_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitAbsExtremiteRenseigne01()._________
	

	
	/**
	 * Getter de la clé du messageSectionHitAbsExtremiteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.absExtremite.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitAbsExtremiteRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitAbsExtremiteRenseigne01().______



	/**
	 * Getter du <b>SINGLETON de messageSectionHitAbsExtremiteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitAbsExtremiteRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitAbsExtremiteRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitAbsExtremiteRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitAbsExtremiteRenseigne01();
	} // Fin de getMessageSectionHitAbsExtremiteRenseigne01()._____________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitAbsExtremiteRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitAbsExtremiteRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitAbsExtremiteRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitAbsExtremiteRenseigne01
							, fournirKeyMessageSectionHitAbsExtremiteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitAbsExtremiteRenseigne01(...).__________



	/**
	 * retourne le messageSectionHitAbsExtremiteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitAbsExtremiteRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitAbsExtremiteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitAbsExtremiteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitAbsExtremiteRegex02
					, fournirKeyMessageSectionHitAbsExtremiteRegex02()
					, MESSAGE_SECTIONHIT_ABSEXTREMITE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitAbsExtremiteRegex02()._____________
	

	
	/**
	 * Getter de la clé du messageSectionHitAbsExtremiteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.absExtremite.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_ABSEXTREMITE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitAbsExtremiteRegex02() {
		return KEY_MESSAGE_SECTIONHIT_ABSEXTREMITE_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitAbsExtremiteRegex02().__________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitAbsExtremiteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitAbsExtremiteRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitAbsExtremiteRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitAbsExtremiteRegex02() 
													throws Exception {
		return fournirMessageSectionHitAbsExtremiteRegex02();
	} // Fin de getMessageSectionHitAbsExtremiteRegex02()._________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitAbsExtremiteRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitAbsExtremiteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitAbsExtremiteRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitAbsExtremiteRegex02
							, fournirKeyMessageSectionHitAbsExtremiteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitAbsExtremiteRegex02(...).______________



	/**
	 * retourne le messageSectionHitAbsExtremiteNumerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitAbsExtremiteNumerique03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitAbsExtremiteNumerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitAbsExtremiteNumerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitAbsExtremiteNumerique03
					, fournirKeyMessageSectionHitAbsExtremiteNumerique03()
					, MESSAGE_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitAbsExtremiteNumerique03()._________
	

	
	/**
	 * Getter de la clé du messageSectionHitAbsExtremiteNumerique03 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.prExtremite.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitAbsExtremiteNumerique03() {
		return KEY_MESSAGE_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03;
	} // Fin de fournirKeyMessageSectionHitAbsExtremiteNumerique03().________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitAbsExtremiteNumerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitAbsExtremiteNumerique03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitAbsExtremiteNumerique03 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitAbsExtremiteNumerique03() 
													throws Exception {
		return fournirMessageSectionHitAbsExtremiteNumerique03();
	} // Fin de getMessageSectionHitAbsExtremiteNumerique03()._____________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitAbsExtremiteNumerique03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitAbsExtremiteNumerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitAbsExtremiteNumerique03(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitAbsExtremiteNumerique03
							, fournirKeyMessageSectionHitAbsExtremiteNumerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitAbsExtremiteNumerique03(...).__________	


	
	/* 24 - lieuDitComptage. ****************/
	/**
	 * retourne le messageSectionHitLieuDitComptageRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitLieuDitComptageRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitLieuDitComptageRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitLieuDitComptageRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitLieuDitComptageRenseigne01
					, fournirKeyMessageSectionHitLieuDitComptageRenseigne01()
					, MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitLieuDitComptageRenseigne01().______
	

	
	/**
	 * Getter de la clé du messageSectionHitLieuDitComptageRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.lieuDitComptage.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitLieuDitComptageRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitLieuDitComptageRenseigne01().___



	/**
	 * Getter du <b>SINGLETON de messageSectionHitLieuDitComptageRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitLieuDitComptageRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitLieuDitComptageRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitLieuDitComptageRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitLieuDitComptageRenseigne01();
	} // Fin de getMessageSectionHitLieuDitComptageRenseigne01().__________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitLieuDitComptageRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitLieuDitComptageRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitLieuDitComptageRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitLieuDitComptageRenseigne01
							, fournirKeyMessageSectionHitLieuDitComptageRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitLieuDitComptageRenseigne01(...)._______



	/**
	 * retourne le messageSectionHitLieuDitComptageRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitLieuDitComptageRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitLieuDitComptageRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitLieuDitComptageRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitLieuDitComptageRegex02
					, fournirKeyMessageSectionHitLieuDitComptageRegex02()
					, MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitLieuDitComptageRegex02().__________
	

	
	/**
	 * Getter de la clé du messageSectionHitLieuDitComptageRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.lieuDitComptage.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitLieuDitComptageRegex02() {
		return KEY_MESSAGE_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitLieuDitComptageRegex02()._______



	/**
	 * Getter du <b>SINGLETON de messageSectionHitLieuDitComptageRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitLieuDitComptageRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitLieuDitComptageRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitLieuDitComptageRegex02() 
													throws Exception {
		return fournirMessageSectionHitLieuDitComptageRegex02();
	} // Fin de getMessageSectionHitLieuDitComptageRegex02().______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitLieuDitComptageRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitLieuDitComptageRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitLieuDitComptageRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitLieuDitComptageRegex02
							, fournirKeyMessageSectionHitLieuDitComptageRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitLieuDitComptageRegex02(...).___________


	
	/* 25 - prComptage. ****************/
	/**
	 * retourne le messageSectionHitPrComptageRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitPrComptageRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitPrComptageRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitPrComptageRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitPrComptageRenseigne01
					, fournirKeyMessageSectionHitPrComptageRenseigne01()
					, MESSAGE_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitPrComptageRenseigne01().___________
	

	
	/**
	 * Getter de la clé du messageSectionHitPrComptageRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.prComptage.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitPrComptageRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitPrComptageRenseigne01().________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitPrComptageRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitPrComptageRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitPrComptageRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitPrComptageRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitPrComptageRenseigne01();
	} // Fin de getMessageSectionHitPrComptageRenseigne01()._______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitPrComptageRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitPrComptageRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitPrComptageRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitPrComptageRenseigne01
							, fournirKeyMessageSectionHitPrComptageRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitPrComptageRenseigne01(...).____________



	/**
	 * retourne le messageSectionHitPrComptageRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitPrComptageRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitPrComptageRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitPrComptageRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitPrComptageRegex02
					, fournirKeyMessageSectionHitPrComptageRegex02()
					, MESSAGE_SECTIONHIT_PRCOMPTAGE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitPrComptageRegex02()._______________
	

	
	/**
	 * Getter de la clé du messageSectionHitPrComptageRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.prComptage.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_PRCOMPTAGE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitPrComptageRegex02() {
		return KEY_MESSAGE_SECTIONHIT_PRCOMPTAGE_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitPrComptageRegex02().____________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitPrComptageRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitPrComptageRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitPrComptageRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitPrComptageRegex02() 
													throws Exception {
		return fournirMessageSectionHitPrComptageRegex02();
	} // Fin de getMessageSectionHitPrComptageRegex02().___________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitPrComptageRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitPrComptageRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitPrComptageRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitPrComptageRegex02
							, fournirKeyMessageSectionHitPrComptageRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitPrComptageRegex02(...).________________



	/**
	 * retourne le messageSectionHitPrComptageNumerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitPrComptageNumerique03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitPrComptageNumerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitPrComptageNumerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitPrComptageNumerique03
					, fournirKeyMessageSectionHitPrComptageNumerique03()
					, MESSAGE_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitPrComptageNumerique03().___________
	

	
	/**
	 * Getter de la clé du messageSectionHitPrComptageNumerique03 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.prComptage.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitPrComptageNumerique03() {
		return KEY_MESSAGE_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03;
	} // Fin de fournirKeyMessageSectionHitPrComptageNumerique03().________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitPrComptageNumerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitPrComptageNumerique03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitPrComptageNumerique03 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitPrComptageNumerique03() 
													throws Exception {
		return fournirMessageSectionHitPrComptageNumerique03();
	} // Fin de getMessageSectionHitPrComptageNumerique03()._______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitPrComptageNumerique03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitPrComptageNumerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitPrComptageNumerique03(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitPrComptageNumerique03
							, fournirKeyMessageSectionHitPrComptageNumerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitPrComptageNumerique03(...).____________	


	
	/* 26 - absComptage. ****************/
	/**
	 * retourne le messageSectionHitAbsComptageRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitAbsComptageRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitAbsComptageRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitAbsComptageRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitAbsComptageRenseigne01
					, fournirKeyMessageSectionHitAbsComptageRenseigne01()
					, MESSAGE_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitAbsComptageRenseigne01().__________
	

	
	/**
	 * Getter de la clé du messageSectionHitAbsComptageRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.absComptage.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitAbsComptageRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitAbsComptageRenseigne01()._______



	/**
	 * Getter du <b>SINGLETON de messageSectionHitAbsComptageRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitAbsComptageRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitAbsComptageRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitAbsComptageRenseigne01() 
											throws Exception {
		return fournirMessageSectionHitAbsComptageRenseigne01();
	} // Fin de getMessageSectionHitAbsComptageRenseigne01().______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitAbsComptageRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitAbsComptageRenseigne01.<br/>
	* 
	* @throws Exception 
	*/
	public static void setMessageSectionHitAbsComptageRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitAbsComptageRenseigne01
							, fournirKeyMessageSectionHitAbsComptageRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitAbsComptageRenseigne01(...).___________



	/**
	 * retourne le messageSectionHitAbsComptageRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitAbsComptageRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitAbsComptageRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitAbsComptageRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitAbsComptageRegex02
					, fournirKeyMessageSectionHitAbsComptageRegex02()
					, MESSAGE_SECTIONHIT_ABSCOMPTAGE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitAbsComptageRegex02().______________
	

	
	/**
	 * Getter de la clé du messageSectionHitAbsComptageRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.absComptage.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_ABSCOMPTAGE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitAbsComptageRegex02() {
		return KEY_MESSAGE_SECTIONHIT_ABSCOMPTAGE_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitAbsComptageRegex02().___________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitAbsComptageRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitAbsComptageRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitAbsComptageRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitAbsComptageRegex02() 
													throws Exception {
		return fournirMessageSectionHitAbsComptageRegex02();
	} // Fin de getMessageSectionHitAbsComptageRegex02().__________________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitAbsComptageRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitAbsComptageRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitAbsComptageRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitAbsComptageRegex02
							, fournirKeyMessageSectionHitAbsComptageRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitAbsComptageRegex02(...)._______________



	/**
	 * retourne le messageSectionHitAbsComptageNumerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitAbsComptageNumerique03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitAbsComptageNumerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitAbsComptageNumerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitAbsComptageNumerique03
					, fournirKeyMessageSectionHitAbsComptageNumerique03()
					, MESSAGE_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitAbsComptageNumerique03().__________
	

	
	/**
	 * Getter de la clé du messageSectionHitAbsComptageNumerique03 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.prComptage.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitAbsComptageNumerique03() {
		return KEY_MESSAGE_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03;
	} // Fin de fournirKeyMessageSectionHitAbsComptageNumerique03()._______



	/**
	 * Getter du <b>SINGLETON de messageSectionHitAbsComptageNumerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitAbsComptageNumerique03 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitAbsComptageNumerique03 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitAbsComptageNumerique03() 
													throws Exception {
		return fournirMessageSectionHitAbsComptageNumerique03();
	} // Fin de getMessageSectionHitAbsComptageNumerique03().______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitAbsComptageNumerique03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitAbsComptageNumerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitAbsComptageNumerique03(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitAbsComptageNumerique03
							, fournirKeyMessageSectionHitAbsComptageNumerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitAbsComptageNumerique03(...).___________	
	
			
			
} // FIN DE LA CLASSE SectionHitGestionnairePreferencesControles.------------
