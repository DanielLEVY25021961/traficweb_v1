package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.sections;

import java.nio.charset.Charset;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.sections.impl.SectionHit;

/**
 * CLASSE SectionHitGestionnairePreferencesRG :<br/>
 * Classe Utilitaire chargée de gérer les 
 * <b>préférences relatives aux REGLES DE GESTION (RG) applicables 
 * à un {@link SectionHit}</b>.<br/>
 * <ul>
 * <li>gère comme des préférences les <b>booleens 
 * qui activent ou non les contrôles des RG</b>.</li>
 * </ul>
 * <br/>
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
	/* 1 - numDepartement. */
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
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitGestionnairePreferencesRG.class);

	// *************************METHODES************************************/
	
	
	
} // FIN DE LA CLASSE SectionHitGestionnairePreferencesRG.-------------------
