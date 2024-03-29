package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.generationcode;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairestemplates.IGestionnaireTemplates;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairestemplates.impl.GestionnaireTemplates;

/**
 * CLASSE GenerateurGestionnairePreferencesControle :<br/>
 * Classe UTILITAIRE (final avec toutes les méthodes static) 
 * chargée de <b>générer le code</b> à insérer dans un 
 * <code><b>OBJETMETIERCestionnairePreferencesControle</b></code>.<br/>
 * utilise des <b>Templates</b> pour générer le code.<br/>
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
 * @since 19 août 2019
 *
 */
public final class GenerateurGestionnairePreferencesControle {

	// ************************ATTRIBUTS************************************/

	/**
	 * "SectionHit".
	 */
	public static final String OBJET_METIER = "SectionHit";
	
	/**
	 * "la section HIT".
	 */
	public static final String OBJET_METIER_EN_CLAIR = "la section HIT";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(GenerateurGestionnairePreferencesControle.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation
	 */
	private GenerateurGestionnairePreferencesControle() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li><b>clés des messages des RG</b> 
	 * de contrôle de l'attribut KEY_MESSAGE_...</li>
	 * <li><b>messages en DUR des RG</b> 
	 * de contrôle de l'attribut MESSAGE_OBJETMETIER_...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * </ul>
	 * Par exemple, pour l'attribut 'anneeTraitement' d'une SectionHit (OBJET METIER) :
	 * <pre>
	 * public static final String KEY_MESSAGE_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01 
	 * 		= "message.SectionHit.anneeTraitement.renseigne";
	 * 
	 * public static final String MESSAGE_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01_EN_DUR 
	 * 		= "l'année de traitement de la section HIT (colonnes [14-15] du HIT) doit être renseigné";
	 * 		
	 * public static final String KEY_MESSAGE_SECTIONHIT_ANNEETRAITEMENT_REGEX_02 
	 * 		= "message.SectionHit.anneeTraitement.regex";
	 * 
	 * public static final String MESSAGE_SECTIONHIT_ANNEETRAITEMENT_REGEX_02_EN_DUR 
	 * 		= "l'année de traitement de la section HIT (colonnes [14-15] du HIT) doit comporter exactement 2 chiffres";
	 * </pre>
	 *
	 * @param pObjetMetier : String : nom de l'objet métier comme 'SectionHit'.
	 * @param pObjetMetierEnClair : String : nom de l'objet métier en clair avec  article comme 'la section HIT'
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * 
	 * @return : String : lignes de code à insérer dans les attributs KEYS 
	 * et MESSAGES EN DUR du 
	 * <code><b>OBJETMETIERGestionnairePreferencesControle</b></code>.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String genererMessagesEnDurRGsRenseigneRegexChiffres(
			final String pObjetMetier
				, final String pObjetMetierEnClair
					, final String pNumeroChamp
						, final String pNomChamp
							, final String pNomChampEnClair
								, final String pColonnes
									, final String pNbreChiffres) throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_genererMessagesEnDurRGsRenseigneRegexChiffres.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pObjetMetier}"
				, "{$pObjetMetierEnClair}"
				, "{$ObjetMetierCapitalise}"
				, "{$pNumeroChamp}"
				, "{$pNomChamp}"
				, "{$NomChampCamelise}"
				, "{$NomChampCapitalise}"
				, "{$pNomChampEnClair}"
				, "{$pColonnes}"
				, "{$pNbreChiffres}"};
		
		final String objetMetierASubstituer = pObjetMetier;
		final String objetMetierEnClairASubstituer = pObjetMetierEnClair;
		final String objetMetierCapitaliseASubstituer 
			= StringUtils.upperCase(
					pObjetMetier, LocaleManager.getLocaleApplication());
		final String numeroChampASubstituer = pNumeroChamp;
		final String nomChampASubstituer = pNomChamp;
		final String nomChampCameliseASubstituer = StringUtils.capitalize(pNomChamp);
		final String nomChampCapitaliseASubstituer 
			= StringUtils.upperCase(
					pNomChamp, LocaleManager.getLocaleApplication());
		final String nomChampEnClairASubstituer = pNomChampEnClair;
		final String colonnesASubstituer = pColonnes;
		final String nombreChiffresASubstituer = pNbreChiffres;
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {objetMetierASubstituer
				, objetMetierEnClairASubstituer
				, objetMetierCapitaliseASubstituer
				, numeroChampASubstituer
				, nomChampASubstituer
				, nomChampCameliseASubstituer
				, nomChampCapitaliseASubstituer
				, nomChampEnClairASubstituer
				, colonnesASubstituer
				, nombreChiffresASubstituer};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;
		
	} // Fin de genererMessagesEnDurRGsRenseigneRegexChiffres(...).________
	

	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li><b>clés des messages des RG</b> 
	 * de contrôle de l'attribut KEY_MESSAGE_...</li>
	 * <li><b>messages en DUR des RG</b> 
	 * de contrôle de l'attribut MESSAGE_OBJETMETIER_...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * <li>une <b>RG NOMENCLATURE</b>.</i>
	 * </ul>
	 * Par exemple, pour l'attribut '' d'une SectionHit (OBJET METIER) :
	 * <pre>
	 * 
	 * </pre>
	 *
	 * @param pObjetMetier : String : nom de l'objet métier comme 'SectionHit'.
	 * @param pObjetMetierEnClair : String : nom de l'objet métier en clair avec  article comme 'la section HIT'
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * @param pNomenclature : String : nomenclature entre crochets comme [1, 2, 3].
	 * 
	 * @return : String : lignes de code à insérer dans les attributs KEYS 
	 * et MESSAGES EN DUR du 
	 * <code><b>OBJETMETIERGestionnairePreferencesControle</b></code>.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String genererMessagesEnDurRGsRenseigneRegexChiffresNomenclature(
			final String pObjetMetier
				, final String pObjetMetierEnClair
					, final String pNumeroChamp
						, final String pNomChamp
							, final String pNomChampEnClair
								, final String pColonnes
									, final String pNbreChiffres
										, final String pNomenclature) throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_genererMessagesEnDurRGsRenseigneRegexChiffresNomenclature.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pObjetMetier}"
				, "{$pObjetMetierEnClair}"
				, "{$ObjetMetierCapitalise}"
				, "{$pNumeroChamp}"
				, "{$pNomChamp}"
				, "{$NomChampCamelise}"
				, "{$NomChampCapitalise}"
				, "{$pNomChampEnClair}"
				, "{$pColonnes}"
				, "{$pNbreChiffres}"
				, "{$pNomenclature}"};
		
		final String objetMetierASubstituer = pObjetMetier;
		final String objetMetierEnClairASubstituer = pObjetMetierEnClair;
		final String objetMetierCapitaliseASubstituer 
			= StringUtils.upperCase(
					pObjetMetier, LocaleManager.getLocaleApplication());
		final String numeroChampASubstituer = pNumeroChamp;
		final String nomChampASubstituer = pNomChamp;
		final String nomChampCameliseASubstituer = StringUtils.capitalize(pNomChamp);
		final String nomChampCapitaliseASubstituer 
			= StringUtils.upperCase(
					pNomChamp, LocaleManager.getLocaleApplication());
		final String nomChampEnClairASubstituer = pNomChampEnClair;
		final String colonnesASubstituer = pColonnes;
		final String nombreChiffresASubstituer = pNbreChiffres;
		final String nomenclature = pNomenclature;
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {objetMetierASubstituer
				, objetMetierEnClairASubstituer
				, objetMetierCapitaliseASubstituer
				, numeroChampASubstituer
				, nomChampASubstituer
				, nomChampCameliseASubstituer
				, nomChampCapitaliseASubstituer
				, nomChampEnClairASubstituer
				, colonnesASubstituer
				, nombreChiffresASubstituer
				, nomenclature};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;
		
	} // Fin de genererMessagesEnDurRGsRenseigneRegexChiffresNomenclature(...).
	

	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li><b>clés des messages des RG</b> 
	 * de contrôle de l'attribut KEY_MESSAGE_...</li>
	 * <li><b>messages en DUR des RG</b> 
	 * de contrôle de l'attribut MESSAGE_OBJETMETIER_...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * <li>une <b>RG NUMERIQUE</b>.</i>
	 * </ul>
	 * Par exemple, pour l'attribut '' d'une SectionHit (OBJET METIER) :
	 * <pre>
	 * 
	 * </pre>
	 *
	 * @param pObjetMetier : String : nom de l'objet métier comme 'SectionHit'.
	 * @param pObjetMetierEnClair : String : nom de l'objet métier en clair avec  article comme 'la section HIT'
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * 
	 * @return : String : lignes de code à insérer dans les attributs KEYS 
	 * et MESSAGES EN DUR du 
	 * <code><b>OBJETMETIERGestionnairePreferencesControle</b></code>.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String genererMessagesEnDurRGsRenseigneRegexChiffresNumerique(
			final String pObjetMetier
				, final String pObjetMetierEnClair
					, final String pNumeroChamp
						, final String pNomChamp
							, final String pNomChampEnClair
								, final String pColonnes
									, final String pNbreChiffres) throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_genererMessagesEnDurRGsRenseigneRegexChiffresNumerique.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pObjetMetier}"
				, "{$pObjetMetierEnClair}"
				, "{$ObjetMetierCapitalise}"
				, "{$pNumeroChamp}"
				, "{$pNomChamp}"
				, "{$NomChampCamelise}"
				, "{$NomChampCapitalise}"
				, "{$pNomChampEnClair}"
				, "{$pColonnes}"
				, "{$pNbreChiffres}"};
		
		final String objetMetierASubstituer = pObjetMetier;
		final String objetMetierEnClairASubstituer = pObjetMetierEnClair;
		final String objetMetierCapitaliseASubstituer 
			= StringUtils.upperCase(
					pObjetMetier, LocaleManager.getLocaleApplication());
		final String numeroChampASubstituer = pNumeroChamp;
		final String nomChampASubstituer = pNomChamp;
		final String nomChampCameliseASubstituer = StringUtils.capitalize(pNomChamp);
		final String nomChampCapitaliseASubstituer 
			= StringUtils.upperCase(
					pNomChamp, LocaleManager.getLocaleApplication());
		final String nomChampEnClairASubstituer = pNomChampEnClair;
		final String colonnesASubstituer = pColonnes;
		final String nombreChiffresASubstituer = pNbreChiffres;
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {objetMetierASubstituer
				, objetMetierEnClairASubstituer
				, objetMetierCapitaliseASubstituer
				, numeroChampASubstituer
				, nomChampASubstituer
				, nomChampCameliseASubstituer
				, nomChampCapitaliseASubstituer
				, nomChampEnClairASubstituer
				, colonnesASubstituer
				, nombreChiffresASubstituer};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;
		
	} // Fin de genererMessagesEnDurRGsRenseigneRegexChiffresNumerique(...).

	
	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li><b>attributs messages des RG</b> 
	 * de contrôle de l'attribut messageObjetMetierATTRIBUTRGxxx...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * </ul>
	 * Par exemple, pour l'attribut 'anneeTraitement' d'une SectionHit (OBJET METIER) :
	 * <pre>
	 * private static String messageSectionHitAnneeTraitementRenseigne01;
	 * 
	 * private static String messageSectionHitAnneeTraitementRegex02;
	 * </pre>
	 *
	 * @param pObjetMetier : String : nom de l'objet métier comme 'SectionHit'.
	 * @param pObjetMetierEnClair : String : nom de l'objet métier en clair avec  article comme 'la section HIT'
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * 
	 * @return : String : lignes de code à insérer dans les attributs 
	 * messageObjetMetierATTRIBUTxxx du 
	 * <code><b>OBJETMETIERGestionnairePreferencesControle</b></code>.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String genererAttributsMessageRGsRenseigneRegexChiffres(
			final String pObjetMetier
				, final String pObjetMetierEnClair
					, final String pNumeroChamp
						, final String pNomChamp
							, final String pNomChampEnClair
								, final String pColonnes
									, final String pNbreChiffres) throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_genererAttributsMessageRGsRenseigneRegexChiffres.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pObjetMetier}"
				, "{$pObjetMetierEnClair}"
				, "{$ObjetMetierCapitalise}"
				, "{$pNumeroChamp}"
				, "{$pNomChamp}"
				, "{$NomChampCamelise}"
				, "{$NomChampCapitalise}"
				, "{$pNomChampEnClair}"
				, "{$pColonnes}"
				, "{$pNbreChiffres}"};
		
		final String objetMetierASubstituer = pObjetMetier;
		final String objetMetierEnClairASubstituer = pObjetMetierEnClair;
		final String objetMetierCapitaliseASubstituer 
			= StringUtils.upperCase(
					pObjetMetier, LocaleManager.getLocaleApplication());
		final String numeroChampASubstituer = pNumeroChamp;
		final String nomChampASubstituer = pNomChamp;
		final String nomChampCameliseASubstituer = StringUtils.capitalize(pNomChamp);
		final String nomChampCapitaliseASubstituer 
			= StringUtils.upperCase(
					pNomChamp, LocaleManager.getLocaleApplication());
		final String nomChampEnClairASubstituer = pNomChampEnClair;
		final String colonnesASubstituer = pColonnes;
		final String nombreChiffresASubstituer = pNbreChiffres;
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {objetMetierASubstituer
				, objetMetierEnClairASubstituer
				, objetMetierCapitaliseASubstituer
				, numeroChampASubstituer
				, nomChampASubstituer
				, nomChampCameliseASubstituer
				, nomChampCapitaliseASubstituer
				, nomChampEnClairASubstituer
				, colonnesASubstituer
				, nombreChiffresASubstituer};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de genererAttributsMessageRGsRenseigneRegexChiffres(...)._____

	
	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li><b>attributs messages des RG</b> 
	 * de contrôle de l'attribut messageObjetMetierATTRIBUTRGxxx...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * <li>une <b>RG NOMENCLATURE</b>.</i>
	 * </ul>
	 * Par exemple, pour l'attribut '' d'une SectionHit (OBJET METIER) :
	 * <pre>
	 * 
	 * </pre>
	 *
	 * @param pObjetMetier : String : nom de l'objet métier comme 'SectionHit'.
	 * @param pObjetMetierEnClair : String : nom de l'objet métier en clair avec  article comme 'la section HIT'
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * @param pNomenclature : String : nomenclature entre crochets comme [1, 2, 3].
	 * 
	 * @return : String : lignes de code à insérer dans les attributs 
	 * messageObjetMetierATTRIBUTxxx du 
	 * <code><b>OBJETMETIERGestionnairePreferencesControle</b></code>.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String genererAttributsMessageRGsRenseigneRegexChiffresNomenclature(
			final String pObjetMetier
				, final String pObjetMetierEnClair
					, final String pNumeroChamp
						, final String pNomChamp
							, final String pNomChampEnClair
								, final String pColonnes
									, final String pNbreChiffres
										, final String pNomenclature) throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_genererAttributsMessageRGsRenseigneRegexChiffresNomenclature.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pObjetMetier}"
				, "{$pObjetMetierEnClair}"
				, "{$ObjetMetierCapitalise}"
				, "{$pNumeroChamp}"
				, "{$pNomChamp}"
				, "{$NomChampCamelise}"
				, "{$NomChampCapitalise}"
				, "{$pNomChampEnClair}"
				, "{$pColonnes}"
				, "{$pNbreChiffres}"
				, "{$pNomenclature}"};
		
		final String objetMetierASubstituer = pObjetMetier;
		final String objetMetierEnClairASubstituer = pObjetMetierEnClair;
		final String objetMetierCapitaliseASubstituer 
			= StringUtils.upperCase(
					pObjetMetier, LocaleManager.getLocaleApplication());
		final String numeroChampASubstituer = pNumeroChamp;
		final String nomChampASubstituer = pNomChamp;
		final String nomChampCameliseASubstituer = StringUtils.capitalize(pNomChamp);
		final String nomChampCapitaliseASubstituer 
			= StringUtils.upperCase(
					pNomChamp, LocaleManager.getLocaleApplication());
		final String nomChampEnClairASubstituer = pNomChampEnClair;
		final String colonnesASubstituer = pColonnes;
		final String nombreChiffresASubstituer = pNbreChiffres;
		final String nomenclature = pNomenclature;
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {objetMetierASubstituer
				, objetMetierEnClairASubstituer
				, objetMetierCapitaliseASubstituer
				, numeroChampASubstituer
				, nomChampASubstituer
				, nomChampCameliseASubstituer
				, nomChampCapitaliseASubstituer
				, nomChampEnClairASubstituer
				, colonnesASubstituer
				, nombreChiffresASubstituer
				, nomenclature};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de genererAttributsMessageRGsRenseigneRegexChiffresNomenclature(...).

	
	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li><b>attributs messages des RG</b> 
	 * de contrôle de l'attribut messageObjetMetierATTRIBUTRGxxx...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * <li>une <b>RG NUMERIQUE</b>.</i>
	 * </ul>
	 * Par exemple, pour l'attribut '' d'une SectionHit (OBJET METIER) :
	 * <pre>
	 * 
	 * </pre>
	 *
	 * @param pObjetMetier : String : nom de l'objet métier comme 'SectionHit'.
	 * @param pObjetMetierEnClair : String : nom de l'objet métier en clair avec  article comme 'la section HIT'
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * 
	 * @return : String : lignes de code à insérer dans les attributs 
	 * messageObjetMetierATTRIBUTxxx du 
	 * <code><b>OBJETMETIERGestionnairePreferencesControle</b></code>.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String genererAttributsMessageRGsRenseigneRegexChiffresNumerique(
			final String pObjetMetier
				, final String pObjetMetierEnClair
					, final String pNumeroChamp
						, final String pNomChamp
							, final String pNomChampEnClair
								, final String pColonnes
									, final String pNbreChiffres) throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_genererAttributsMessageRGsRenseigneRegexChiffresNumerique.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pObjetMetier}"
				, "{$pObjetMetierEnClair}"
				, "{$ObjetMetierCapitalise}"
				, "{$pNumeroChamp}"
				, "{$pNomChamp}"
				, "{$NomChampCamelise}"
				, "{$NomChampCapitalise}"
				, "{$pNomChampEnClair}"
				, "{$pColonnes}"
				, "{$pNbreChiffres}"};
		
		final String objetMetierASubstituer = pObjetMetier;
		final String objetMetierEnClairASubstituer = pObjetMetierEnClair;
		final String objetMetierCapitaliseASubstituer 
			= StringUtils.upperCase(
					pObjetMetier, LocaleManager.getLocaleApplication());
		final String numeroChampASubstituer = pNumeroChamp;
		final String nomChampASubstituer = pNomChamp;
		final String nomChampCameliseASubstituer = StringUtils.capitalize(pNomChamp);
		final String nomChampCapitaliseASubstituer 
			= StringUtils.upperCase(
					pNomChamp, LocaleManager.getLocaleApplication());
		final String nomChampEnClairASubstituer = pNomChampEnClair;
		final String colonnesASubstituer = pColonnes;
		final String nombreChiffresASubstituer = pNbreChiffres;
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {objetMetierASubstituer
				, objetMetierEnClairASubstituer
				, objetMetierCapitaliseASubstituer
				, numeroChampASubstituer
				, nomChampASubstituer
				, nomChampCameliseASubstituer
				, nomChampCapitaliseASubstituer
				, nomChampEnClairASubstituer
				, colonnesASubstituer
				, nombreChiffresASubstituer};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de genererAttributsMessageRGsRenseigneRegexChiffresNumerique(...).

	
	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li><b>ajout des messages EN DUR des RG</b> 
	 * de contrôle de l'attribut messageObjetMetierATTRIBUTRGxxx...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * </ul>
	 * Par exemple, pour l'attribut 'anneeTraitement' d'une SectionHit (OBJET METIER) :
	 * <pre>
	 * 		preferences.setProperty(
	 * 			KEY_MESSAGE_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01
	 * 				, MESSAGE_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01_EN_DUR);
	 * 
	 * 		preferences.setProperty(
	 * 			KEY_MESSAGE_SECTIONHIT_ANNEETRAITEMENT_REGEX_02
	 * 				, MESSAGE_SECTIONHIT_ANNEETRAITEMENT_REGEX_02_EN_DUR);
	 * </pre>
	 *
	 * @param pObjetMetier : String : nom de l'objet métier comme 'SectionHit'.
	 * @param pObjetMetierEnClair : String : nom de l'objet métier en clair avec  article comme 'la section HIT'
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * 
	 * @return : String : lignes de code à insérer dans la méthode 
	 * <code>ajouterPropertiesEnDur()</code> du 
	 * <code><b>OBJETMETIERGestionnairePreferencesControle</b></code>.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String genererAjouterPropertiesEnDurRGsRenseigneRegexChiffres(
			final String pObjetMetier
				, final String pObjetMetierEnClair
					, final String pNumeroChamp
						, final String pNomChamp
							, final String pNomChampEnClair
								, final String pColonnes
									, final String pNbreChiffres) throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_ajouterPropertiesEnDurRGsRenseigneRegexChiffres.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pObjetMetier}"
				, "{$pObjetMetierEnClair}"
				, "{$ObjetMetierCapitalise}"
				, "{$pNumeroChamp}"
				, "{$pNomChamp}"
				, "{$NomChampCamelise}"
				, "{$NomChampCapitalise}"
				, "{$pNomChampEnClair}"
				, "{$pColonnes}"
				, "{$pNbreChiffres}"};
		
		final String objetMetierASubstituer = pObjetMetier;
		final String objetMetierEnClairASubstituer = pObjetMetierEnClair;
		final String objetMetierCapitaliseASubstituer 
			= StringUtils.upperCase(
					pObjetMetier, LocaleManager.getLocaleApplication());
		final String numeroChampASubstituer = pNumeroChamp;
		final String nomChampASubstituer = pNomChamp;
		final String nomChampCameliseASubstituer = StringUtils.capitalize(pNomChamp);
		final String nomChampCapitaliseASubstituer 
			= StringUtils.upperCase(
					pNomChamp, LocaleManager.getLocaleApplication());
		final String nomChampEnClairASubstituer = pNomChampEnClair;
		final String colonnesASubstituer = pColonnes;
		final String nombreChiffresASubstituer = pNbreChiffres;
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {objetMetierASubstituer
				, objetMetierEnClairASubstituer
				, objetMetierCapitaliseASubstituer
				, numeroChampASubstituer
				, nomChampASubstituer
				, nomChampCameliseASubstituer
				, nomChampCapitaliseASubstituer
				, nomChampEnClairASubstituer
				, colonnesASubstituer
				, nombreChiffresASubstituer};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de genererAjouterPropertiesEnDurRGsRenseigneRegexChiffres(...)

	
	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li><b>ajout des messages EN DUR des RG</b> 
	 * de contrôle de l'attribut messageObjetMetierATTRIBUTRGxxx...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * <li>une <b>RG NOMENCLATURE</b>.</i>
	 * </ul>
	 * Par exemple, pour l'attribut '' d'une SectionHit (OBJET METIER) :
	 * <pre>
	 * 
	 * </pre>
	 *
	 * @param pObjetMetier : String : nom de l'objet métier comme 'SectionHit'.
	 * @param pObjetMetierEnClair : String : nom de l'objet métier en clair avec  article comme 'la section HIT'
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * @param pNomenclature : String : nomenclature entre crochets comme [1, 2, 3].
	 * 
	 * @return : String : lignes de code à insérer dans la méthode 
	 * <code>ajouterPropertiesEnDur()</code> du 
	 * <code><b>OBJETMETIERGestionnairePreferencesControle</b></code>.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String genererAjouterPropertiesEnDurRGsRenseigneRegexChiffresNomenclature(
			final String pObjetMetier
				, final String pObjetMetierEnClair
					, final String pNumeroChamp
						, final String pNomChamp
							, final String pNomChampEnClair
								, final String pColonnes
									, final String pNbreChiffres
										, final String pNomenclature) throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_ajouterPropertiesEnDurRGsRenseigneRegexChiffresNomenclature.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pObjetMetier}"
				, "{$pObjetMetierEnClair}"
				, "{$ObjetMetierCapitalise}"
				, "{$pNumeroChamp}"
				, "{$pNomChamp}"
				, "{$NomChampCamelise}"
				, "{$NomChampCapitalise}"
				, "{$pNomChampEnClair}"
				, "{$pColonnes}"
				, "{$pNbreChiffres}"
				, "{$pNomenclature}"};
		
		final String objetMetierASubstituer = pObjetMetier;
		final String objetMetierEnClairASubstituer = pObjetMetierEnClair;
		final String objetMetierCapitaliseASubstituer 
			= StringUtils.upperCase(
					pObjetMetier, LocaleManager.getLocaleApplication());
		final String numeroChampASubstituer = pNumeroChamp;
		final String nomChampASubstituer = pNomChamp;
		final String nomChampCameliseASubstituer = StringUtils.capitalize(pNomChamp);
		final String nomChampCapitaliseASubstituer 
			= StringUtils.upperCase(
					pNomChamp, LocaleManager.getLocaleApplication());
		final String nomChampEnClairASubstituer = pNomChampEnClair;
		final String colonnesASubstituer = pColonnes;
		final String nombreChiffresASubstituer = pNbreChiffres;
		final String nomenclature = pNomenclature;
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {objetMetierASubstituer
				, objetMetierEnClairASubstituer
				, objetMetierCapitaliseASubstituer
				, numeroChampASubstituer
				, nomChampASubstituer
				, nomChampCameliseASubstituer
				, nomChampCapitaliseASubstituer
				, nomChampEnClairASubstituer
				, colonnesASubstituer
				, nombreChiffresASubstituer
				, nomenclature};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de genererAjouterPropertiesEnDurRGsRenseigneRegexChiffresNomenclature(...)

	
	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li><b>ajout des messages EN DUR des RG</b> 
	 * de contrôle de l'attribut messageObjetMetierATTRIBUTRGxxx...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * <li>une <b>RG NUMERIQUE</b>.</i>
	 * </ul>
	 * Par exemple, pour l'attribut '' d'une SectionHit (OBJET METIER) :
	 * <pre>
	 * 
	 * </pre>
	 *
	 * @param pObjetMetier : String : nom de l'objet métier comme 'SectionHit'.
	 * @param pObjetMetierEnClair : String : nom de l'objet métier en clair avec  article comme 'la section HIT'
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * 
	 * @return : String : lignes de code à insérer dans la méthode 
	 * <code>ajouterPropertiesEnDur()</code> du 
	 * <code><b>OBJETMETIERGestionnairePreferencesControle</b></code>.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String genererAjouterPropertiesEnDurRGsRenseigneRegexChiffresNumerique(
			final String pObjetMetier
				, final String pObjetMetierEnClair
					, final String pNumeroChamp
						, final String pNomChamp
							, final String pNomChampEnClair
								, final String pColonnes
									, final String pNbreChiffres) throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_ajouterPropertiesEnDurRGsRenseigneRegexChiffresNumerique.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pObjetMetier}"
				, "{$pObjetMetierEnClair}"
				, "{$ObjetMetierCapitalise}"
				, "{$pNumeroChamp}"
				, "{$pNomChamp}"
				, "{$NomChampCamelise}"
				, "{$NomChampCapitalise}"
				, "{$pNomChampEnClair}"
				, "{$pColonnes}"
				, "{$pNbreChiffres}"};
		
		final String objetMetierASubstituer = pObjetMetier;
		final String objetMetierEnClairASubstituer = pObjetMetierEnClair;
		final String objetMetierCapitaliseASubstituer 
			= StringUtils.upperCase(
					pObjetMetier, LocaleManager.getLocaleApplication());
		final String numeroChampASubstituer = pNumeroChamp;
		final String nomChampASubstituer = pNomChamp;
		final String nomChampCameliseASubstituer = StringUtils.capitalize(pNomChamp);
		final String nomChampCapitaliseASubstituer 
			= StringUtils.upperCase(
					pNomChamp, LocaleManager.getLocaleApplication());
		final String nomChampEnClairASubstituer = pNomChampEnClair;
		final String colonnesASubstituer = pColonnes;
		final String nombreChiffresASubstituer = pNbreChiffres;
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {objetMetierASubstituer
				, objetMetierEnClairASubstituer
				, objetMetierCapitaliseASubstituer
				, numeroChampASubstituer
				, nomChampASubstituer
				, nomChampCameliseASubstituer
				, nomChampCapitaliseASubstituer
				, nomChampEnClairASubstituer
				, colonnesASubstituer
				, nombreChiffresASubstituer};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de genererAjouterPropertiesEnDurRGsRenseigneRegexChiffresNumerique(...)

	
	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li><b>Getters des attributs des messages des RG</b> 
	 * de contrôle de l'attribut messageObjetMetierATTRIBUTRGxxx...</li>
	 * <li><b>Setters des attributs des messages des RG</b> 
	 * de contrôle de l'attribut messageObjetMetierATTRIBUTRGxxx...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * </ul>
	 * Par exemple, pour l'attribut 'anneeTraitement' d'une SectionHit (OBJET METIER) :
	 * <pre>
	 * private static String fournirMessageSectionHitAnneeTraitementRenseigne01() throws Exception {
	 * 
	 * 	synchronized (SectionHitGestionnairePreferencesControles.class) {
	 * 
	 * 		return fournirAttribut(
	 * 				messageSectionHitAnneeTraitementRenseigne01
	 * 				, fournirKeyMessageSectionHitAnneeTraitementRenseigne01()
	 * 				, MESSAGE_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01_EN_DUR);
	 * 
	 * 	} // Fin du bloc synchronized.__________________
	 * 
	 * } // Fin de fournirMessageSectionHitAnneeTraitementRenseigne01().______
	 * 
	 * etc..
	 * </pre>
	 *
	 * @param pObjetMetier : String : nom de l'objet métier comme 'SectionHit'.
	 * @param pObjetMetierEnClair : String : nom de l'objet métier en clair avec  article comme 'la section HIT'
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * 
	 * @return : String : lignes de code à insérer dans la méthode 
	 * <code>ajouterPropertiesEnDur()</code> du 
	 * <code><b>OBJETMETIERGestionnairePreferencesControle</b></code>.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String genererGettersSettersMessagesRGsRenseigneRegexChiffres(
			final String pObjetMetier
				, final String pObjetMetierEnClair
					, final String pNumeroChamp
						, final String pNomChamp
							, final String pNomChampEnClair
								, final String pColonnes
									, final String pNbreChiffres) throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_genererGettersSettersRGsRenseigneRegexChiffres.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pObjetMetier}"
				, "{$pObjetMetierEnClair}"
				, "{$ObjetMetierCapitalise}"
				, "{$pNumeroChamp}"
				, "{$pNomChamp}"
				, "{$NomChampCamelise}"
				, "{$NomChampCapitalise}"
				, "{$pNomChampEnClair}"
				, "{$pColonnes}"
				, "{$pNbreChiffres}"};
		
		final String objetMetierASubstituer = pObjetMetier;
		final String objetMetierEnClairASubstituer = pObjetMetierEnClair;
		final String objetMetierCapitaliseASubstituer 
			= StringUtils.upperCase(
					pObjetMetier, LocaleManager.getLocaleApplication());
		final String numeroChampASubstituer = pNumeroChamp;
		final String nomChampASubstituer = pNomChamp;
		final String nomChampCameliseASubstituer = StringUtils.capitalize(pNomChamp);
		final String nomChampCapitaliseASubstituer 
			= StringUtils.upperCase(
					pNomChamp, LocaleManager.getLocaleApplication());
		final String nomChampEnClairASubstituer = pNomChampEnClair;
		final String colonnesASubstituer = pColonnes;
		final String nombreChiffresASubstituer = pNbreChiffres;
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {objetMetierASubstituer
				, objetMetierEnClairASubstituer
				, objetMetierCapitaliseASubstituer
				, numeroChampASubstituer
				, nomChampASubstituer
				, nomChampCameliseASubstituer
				, nomChampCapitaliseASubstituer
				, nomChampEnClairASubstituer
				, colonnesASubstituer
				, nombreChiffresASubstituer};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de genererGettersSettersMessagesRGsRenseigneRegexChiffres(...)

	
	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li><b>Getters des attributs des messages des RG</b> 
	 * de contrôle de l'attribut messageObjetMetierATTRIBUTRGxxx...</li>
	 * <li><b>Setters des attributs des messages des RG</b> 
	 * de contrôle de l'attribut messageObjetMetierATTRIBUTRGxxx...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * <li>une <b>RG NOMENCLATURE</b>.</i>
	 * </ul>
	 * Par exemple, pour l'attribut '' d'une SectionHit (OBJET METIER) :
	 * <pre>
	 * 
	 * </pre>
	 *
	 * @param pObjetMetier : String : nom de l'objet métier comme 'SectionHit'.
	 * @param pObjetMetierEnClair : String : nom de l'objet métier en clair avec  article comme 'la section HIT'
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * @param pNomenclature : String : nomenclature entre crochets comme [1, 2, 3].
	 * 
	 * @return : String : lignes de code à insérer dans la méthode 
	 * <code>ajouterPropertiesEnDur()</code> du 
	 * <code><b>OBJETMETIERGestionnairePreferencesControle</b></code>.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String genererGettersSettersMessagesRGsRenseigneRegexChiffresNomenclature(
			final String pObjetMetier
				, final String pObjetMetierEnClair
					, final String pNumeroChamp
						, final String pNomChamp
							, final String pNomChampEnClair
								, final String pColonnes
									, final String pNbreChiffres
										, final String pNomenclature) throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_genererGettersSettersRGsRenseigneRegexChiffresNomenclature.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pObjetMetier}"
				, "{$pObjetMetierEnClair}"
				, "{$ObjetMetierCapitalise}"
				, "{$pNumeroChamp}"
				, "{$pNomChamp}"
				, "{$NomChampCamelise}"
				, "{$NomChampCapitalise}"
				, "{$pNomChampEnClair}"
				, "{$pColonnes}"
				, "{$pNbreChiffres}"
				, "{$pNomenclature}"};
		
		final String objetMetierASubstituer = pObjetMetier;
		final String objetMetierEnClairASubstituer = pObjetMetierEnClair;
		final String objetMetierCapitaliseASubstituer 
			= StringUtils.upperCase(
					pObjetMetier, LocaleManager.getLocaleApplication());
		final String numeroChampASubstituer = pNumeroChamp;
		final String nomChampASubstituer = pNomChamp;
		final String nomChampCameliseASubstituer = StringUtils.capitalize(pNomChamp);
		final String nomChampCapitaliseASubstituer 
			= StringUtils.upperCase(
					pNomChamp, LocaleManager.getLocaleApplication());
		final String nomChampEnClairASubstituer = pNomChampEnClair;
		final String colonnesASubstituer = pColonnes;
		final String nombreChiffresASubstituer = pNbreChiffres;
		final String nomenclature = pNomenclature;
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {objetMetierASubstituer
				, objetMetierEnClairASubstituer
				, objetMetierCapitaliseASubstituer
				, numeroChampASubstituer
				, nomChampASubstituer
				, nomChampCameliseASubstituer
				, nomChampCapitaliseASubstituer
				, nomChampEnClairASubstituer
				, colonnesASubstituer
				, nombreChiffresASubstituer
				, nomenclature};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de genererGettersSettersMessagesRGsRenseigneRegexChiffresNomenclature(...)

	
	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li><b>Getters des attributs des messages des RG</b> 
	 * de contrôle de l'attribut messageObjetMetierATTRIBUTRGxxx...</li>
	 * <li><b>Setters des attributs des messages des RG</b> 
	 * de contrôle de l'attribut messageObjetMetierATTRIBUTRGxxx...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * <li>une <b>RG NUMERIQUE</b>.</i>
	 * </ul>
	 * Par exemple, pour l'attribut '' d'une SectionHit (OBJET METIER) :
	 * <pre>
	 * 
	 * </pre>
	 *
	 * @param pObjetMetier : String : nom de l'objet métier comme 'SectionHit'.
	 * @param pObjetMetierEnClair : String : nom de l'objet métier en clair avec  article comme 'la section HIT'
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * @param pNumerique : String : nomenclature entre crochets comme [1, 2, 3].
	 * 
	 * @return : String : lignes de code à insérer dans la méthode 
	 * <code>ajouterPropertiesEnDur()</code> du 
	 * <code><b>OBJETMETIERGestionnairePreferencesControle</b></code>.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String genererGettersSettersMessagesRGsRenseigneRegexChiffresNumerique(
			final String pObjetMetier
				, final String pObjetMetierEnClair
					, final String pNumeroChamp
						, final String pNomChamp
							, final String pNomChampEnClair
								, final String pColonnes
									, final String pNbreChiffres) throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_genererGettersSettersRGsRenseigneRegexChiffresNumerique.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pObjetMetier}"
				, "{$pObjetMetierEnClair}"
				, "{$ObjetMetierCapitalise}"
				, "{$pNumeroChamp}"
				, "{$pNomChamp}"
				, "{$NomChampCamelise}"
				, "{$NomChampCapitalise}"
				, "{$pNomChampEnClair}"
				, "{$pColonnes}"
				, "{$pNbreChiffres}"};
		
		final String objetMetierASubstituer = pObjetMetier;
		final String objetMetierEnClairASubstituer = pObjetMetierEnClair;
		final String objetMetierCapitaliseASubstituer 
			= StringUtils.upperCase(
					pObjetMetier, LocaleManager.getLocaleApplication());
		final String numeroChampASubstituer = pNumeroChamp;
		final String nomChampASubstituer = pNomChamp;
		final String nomChampCameliseASubstituer = StringUtils.capitalize(pNomChamp);
		final String nomChampCapitaliseASubstituer 
			= StringUtils.upperCase(
					pNomChamp, LocaleManager.getLocaleApplication());
		final String nomChampEnClairASubstituer = pNomChampEnClair;
		final String colonnesASubstituer = pColonnes;
		final String nombreChiffresASubstituer = pNbreChiffres;
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {objetMetierASubstituer
				, objetMetierEnClairASubstituer
				, objetMetierCapitaliseASubstituer
				, numeroChampASubstituer
				, nomChampASubstituer
				, nomChampCameliseASubstituer
				, nomChampCapitaliseASubstituer
				, nomChampEnClairASubstituer
				, colonnesASubstituer
				, nombreChiffresASubstituer};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de genererGettersSettersMessagesRGsRenseigneRegexChiffresNumerique(...)

	
	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li><b>Tests JUnit des messages des RG</b> 
	 * de contrôle de l'attribut messageObjetMetierATTRIBUTRGxxx...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * </ul>
	 * Par exemple, pour l'attribut 'anneeTraitement' d'une SectionHit (OBJET METIER) :
	 * <pre>
	 * public void testGetMessageSectionHitAnneeTraitementRenseigne01() throws Exception {...}
	 * 
	 * public void testGetMessageSectionHitAnneeTraitementRegex02() throws Exception {...}
	 * </pre>
	 *
	 * @param pObjetMetier : String : nom de l'objet métier comme 'SectionHit'.
	 * @param pObjetMetierEnClair : String : nom de l'objet métier en clair avec  article comme 'la section HIT'
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * 
	 * @return : String : lignes de code à insérer dans la méthode 
	 * <code>ajouterPropertiesEnDur()</code> du 
	 * <code><b>OBJETMETIERGestionnairePreferencesControle</b></code>.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String genererTestsMessagesRGsRenseigneRegexChiffres(
			final String pObjetMetier
				, final String pObjetMetierEnClair
					, final String pNumeroChamp
						, final String pNomChamp
							, final String pNomChampEnClair
								, final String pColonnes
									, final String pNbreChiffres) throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_genererTestRGsRenseigneRegexChiffres.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pObjetMetier}"
				, "{$pObjetMetierEnClair}"
				, "{$ObjetMetierCapitalise}"
				, "{$pNumeroChamp}"
				, "{$pNomChamp}"
				, "{$NomChampCamelise}"
				, "{$NomChampCapitalise}"
				, "{$pNomChampEnClair}"
				, "{$pColonnes}"
				, "{$pNbreChiffres}"};
		
		final String objetMetierASubstituer = pObjetMetier;
		final String objetMetierEnClairASubstituer = pObjetMetierEnClair;
		final String objetMetierCapitaliseASubstituer 
			= StringUtils.upperCase(
					pObjetMetier, LocaleManager.getLocaleApplication());
		final String numeroChampASubstituer = pNumeroChamp;
		final String nomChampASubstituer = pNomChamp;
		final String nomChampCameliseASubstituer = StringUtils.capitalize(pNomChamp);
		final String nomChampCapitaliseASubstituer 
			= StringUtils.upperCase(
					pNomChamp, LocaleManager.getLocaleApplication());
		final String nomChampEnClairASubstituer = pNomChampEnClair;
		final String colonnesASubstituer = pColonnes;
		final String nombreChiffresASubstituer = pNbreChiffres;
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {objetMetierASubstituer
				, objetMetierEnClairASubstituer
				, objetMetierCapitaliseASubstituer
				, numeroChampASubstituer
				, nomChampASubstituer
				, nomChampCameliseASubstituer
				, nomChampCapitaliseASubstituer
				, nomChampEnClairASubstituer
				, colonnesASubstituer
				, nombreChiffresASubstituer};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de genererTestsMessagesRGsRenseigneRegexChiffres(...).________

	
	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li><b>Tests JUnit des messages des RG</b> 
	 * de contrôle de l'attribut messageObjetMetierATTRIBUTRGxxx...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * <li>une <b>RG NOMENCLATURE</b>.</i>
	 * </ul>
	 * Par exemple, pour l'attribut '' d'une SectionHit (OBJET METIER) :
	 * <pre>
	 * 
	 * </pre>
	 *
	 * @param pObjetMetier : String : nom de l'objet métier comme 'SectionHit'.
	 * @param pObjetMetierEnClair : String : nom de l'objet métier en clair avec  article comme 'la section HIT'
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * @param pNomenclature : String : nomenclature entre crochets comme [1, 2, 3].
	 * 
	 * @return : String : lignes de code à insérer dans la méthode 
	 * <code>ajouterPropertiesEnDur()</code> du 
	 * <code><b>OBJETMETIERGestionnairePreferencesControle</b></code>.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String genererTestsMessagesRGsRenseigneRegexChiffresNomenclature(
			final String pObjetMetier
				, final String pObjetMetierEnClair
					, final String pNumeroChamp
						, final String pNomChamp
							, final String pNomChampEnClair
								, final String pColonnes
									, final String pNbreChiffres
										, final String pNomenclature) throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_genererTestRGsRenseigneRegexChiffresNomenclature.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pObjetMetier}"
				, "{$pObjetMetierEnClair}"
				, "{$ObjetMetierCapitalise}"
				, "{$pNumeroChamp}"
				, "{$pNomChamp}"
				, "{$NomChampCamelise}"
				, "{$NomChampCapitalise}"
				, "{$pNomChampEnClair}"
				, "{$pColonnes}"
				, "{$pNbreChiffres}"
				, "{$pNomenclature}"};
		
		final String objetMetierASubstituer = pObjetMetier;
		final String objetMetierEnClairASubstituer = pObjetMetierEnClair;
		final String objetMetierCapitaliseASubstituer 
			= StringUtils.upperCase(
					pObjetMetier, LocaleManager.getLocaleApplication());
		final String numeroChampASubstituer = pNumeroChamp;
		final String nomChampASubstituer = pNomChamp;
		final String nomChampCameliseASubstituer = StringUtils.capitalize(pNomChamp);
		final String nomChampCapitaliseASubstituer 
			= StringUtils.upperCase(
					pNomChamp, LocaleManager.getLocaleApplication());
		final String nomChampEnClairASubstituer = pNomChampEnClair;
		final String colonnesASubstituer = pColonnes;
		final String nombreChiffresASubstituer = pNbreChiffres;
		final String nomenclature = pNomenclature;
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {objetMetierASubstituer
				, objetMetierEnClairASubstituer
				, objetMetierCapitaliseASubstituer
				, numeroChampASubstituer
				, nomChampASubstituer
				, nomChampCameliseASubstituer
				, nomChampCapitaliseASubstituer
				, nomChampEnClairASubstituer
				, colonnesASubstituer
				, nombreChiffresASubstituer
				, nomenclature};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de genererTestsMessagesRGsRenseigneRegexChiffresNomenclature(...).

	
	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li><b>Tests JUnit des messages des RG</b> 
	 * de contrôle de l'attribut messageObjetMetierATTRIBUTRGxxx...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * <li>une <b>RG NUMERIQUE</b>.</i>
	 * </ul>
	 * Par exemple, pour l'attribut '' d'une SectionHit (OBJET METIER) :
	 * <pre>
	 * 
	 * </pre>
	 *
	 * @param pObjetMetier : String : nom de l'objet métier comme 'SectionHit'.
	 * @param pObjetMetierEnClair : String : nom de l'objet métier en clair avec  article comme 'la section HIT'
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * 
	 * @return : String : lignes de code à insérer dans la méthode 
	 * <code>ajouterPropertiesEnDur()</code> du 
	 * <code><b>OBJETMETIERGestionnairePreferencesControle</b></code>.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String genererTestsMessagesRGsRenseigneRegexChiffresNumerique(
			final String pObjetMetier
				, final String pObjetMetierEnClair
					, final String pNumeroChamp
						, final String pNomChamp
							, final String pNomChampEnClair
								, final String pColonnes
									, final String pNbreChiffres) throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_genererTestRGsRenseigneRegexChiffresNumerique.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pObjetMetier}"
				, "{$pObjetMetierEnClair}"
				, "{$ObjetMetierCapitalise}"
				, "{$pNumeroChamp}"
				, "{$pNomChamp}"
				, "{$NomChampCamelise}"
				, "{$NomChampCapitalise}"
				, "{$pNomChampEnClair}"
				, "{$pColonnes}"
				, "{$pNbreChiffres}"};
		
		final String objetMetierASubstituer = pObjetMetier;
		final String objetMetierEnClairASubstituer = pObjetMetierEnClair;
		final String objetMetierCapitaliseASubstituer 
			= StringUtils.upperCase(
					pObjetMetier, LocaleManager.getLocaleApplication());
		final String numeroChampASubstituer = pNumeroChamp;
		final String nomChampASubstituer = pNomChamp;
		final String nomChampCameliseASubstituer = StringUtils.capitalize(pNomChamp);
		final String nomChampCapitaliseASubstituer 
			= StringUtils.upperCase(
					pNomChamp, LocaleManager.getLocaleApplication());
		final String nomChampEnClairASubstituer = pNomChampEnClair;
		final String colonnesASubstituer = pColonnes;
		final String nombreChiffresASubstituer = pNbreChiffres;
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {objetMetierASubstituer
				, objetMetierEnClairASubstituer
				, objetMetierCapitaliseASubstituer
				, numeroChampASubstituer
				, nomChampASubstituer
				, nomChampCameliseASubstituer
				, nomChampCapitaliseASubstituer
				, nomChampEnClairASubstituer
				, colonnesASubstituer
				, nombreChiffresASubstituer};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de genererTestsMessagesRGsRenseigneRegexChiffresNumerique(...).


	
	/**
	 * génère l'ensemble des lignes de code <i>commentées</i> 
	 * relatives à un attribut à insérer dans un :
	 * <ul>
	 * <li>OBJETMETIERGestionnairePreferencesControles</li>
	 * <li>OBJETMETIERGestionnairePreferencesControlesTest</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * </ul>
	 *
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * 
	 * @throws Exception 
	 */
	public static void genererCodeRGsRenseigneRegexChiffres(
			final String pNumeroChamp
				, final String pNomChamp
					, final String pNomChampEnClair
						, final String pColonnes
							, final String pNbreChiffres) throws Exception {
			
			final String resultatMessagesEnDur 
			= genererMessagesEnDurRGsRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("****************************************************************************************************************");
			System.out.println("***************************************************** MESSAGES EN DUR ********************************************");
			System.out.println(resultatMessagesEnDur);
			
			final String resultatAttributsMessages 
			= genererAttributsMessageRGsRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("****************************************************************************************************************");
			System.out.println("******************************************************* ATTRIBUTS MESSAGES **************************************");
			System.out.println(resultatAttributsMessages);

			final String resultatAjouterPropertiesEnDur 
			= genererAjouterPropertiesEnDurRGsRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("****************************************************************************************************************");
			System.out.println("************************************************** AJOUT METHODE ajouterPropertiesEnDur() *******************************");
			System.out.println(resultatAjouterPropertiesEnDur);
		
			final String resultatGenererGettersSetters 
			= genererGettersSettersMessagesRGsRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("****************************************************************************************************************");
			System.out.println("*************************************************************** GETTERS ET SETTERS *************************************");
			System.out.println(resultatGenererGettersSetters);
		
			final String resultatTestJUnit 
			= genererTestsMessagesRGsRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("****************************************************************************************************************");
			System.out.println("*********************************************************** AJOUT POUR LES TESTS JUNIT ************************************");
			System.out.println(resultatTestJUnit);
		
	} // Fin de genererCodeRGsRenseigneRegexChiffres(...)._________________


	
	/**
	 * génère l'ensemble des lignes de code <i>commentées</i> 
	 * relatives à un attribut à insérer dans un :
	 * <ul>
	 * <li>OBJETMETIERGestionnairePreferencesControles</li>
	 * <li>OBJETMETIERGestionnairePreferencesControlesTest</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * <li>une <b>RG NOMENCLATURE</b>.</i>
	 * </ul>
	 *
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * @param pNomenclature : String : nomenclature entre crochets comme [1, 2, 3].
	 * 
	 * @throws Exception 
	 */
	public static void genererCodeRGsRenseigneRegexChiffresNomenclature(
			final String pNumeroChamp
				, final String pNomChamp
					, final String pNomChampEnClair
						, final String pColonnes
							, final String pNbreChiffres
								, final String pNomenclature) throws Exception {
			
			final String resultatMessagesEnDur 
			= genererMessagesEnDurRGsRenseigneRegexChiffresNomenclature(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres, pNomenclature);

			System.out.println("****************************************************************************************************************");
			System.out.println("***************************************************** MESSAGES EN DUR ***********************************************");
			System.out.println(resultatMessagesEnDur);
			
			final String resultatAttributsMessages 
			= genererAttributsMessageRGsRenseigneRegexChiffresNomenclature(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres, pNomenclature);

			System.out.println("****************************************************************************************************************");
			System.out.println("****************************************************** ATTRIBUTS MESSAGES *******************************************");
			System.out.println(resultatAttributsMessages);

			final String resultatAjouterPropertiesEnDur 
			= genererAjouterPropertiesEnDurRGsRenseigneRegexChiffresNomenclature(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres, pNomenclature);

			System.out.println("****************************************************************************************************************");
			System.out.println("************************************************ AJOUT METHODE ajouterPropertiesEnDur() ********************************");
			System.out.println(resultatAjouterPropertiesEnDur);
		
			final String resultatGenererGettersSetters 
			= genererGettersSettersMessagesRGsRenseigneRegexChiffresNomenclature(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres, pNomenclature);

			System.out.println("****************************************************************************************************************");
			System.out.println("********************************************************** GETTERS ET SETTERS ********************************************");
			System.out.println(resultatGenererGettersSetters);
		
			final String resultatTestJUnit 
			= genererTestsMessagesRGsRenseigneRegexChiffresNomenclature(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres, pNomenclature);

			System.out.println("****************************************************************************************************************");
			System.out.println("******************************************************** AJOUT POUR LES TESTS JUNIT *****************************************");
			System.out.println(resultatTestJUnit);
		
	} // Fin de genererCodeRGsRenseigneRegexChiffresNomenclature(...)._____


	
	/**
	 * génère l'ensemble des lignes de code <i>commentées</i> 
	 * relatives à un attribut à insérer dans un :
	 * <ul>
	 * <li>OBJETMETIERGestionnairePreferencesControles</li>
	 * <li>OBJETMETIERGestionnairePreferencesControlesTest</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * <li>une <b>RG NUMERIQUE</b>.</i>
	 * </ul>
	 *
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pNomChampEnClair : String : nom de l'attribut en clair avec article comme 'la classe' ou 'le numéro de section'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * 
	 * @throws Exception 
	 */
	public static void genererCodeRGsRenseigneRegexChiffresNumerique(
			final String pNumeroChamp
				, final String pNomChamp
					, final String pNomChampEnClair
						, final String pColonnes
							, final String pNbreChiffres) throws Exception {
			
			final String resultatMessagesEnDur 
			= genererMessagesEnDurRGsRenseigneRegexChiffresNumerique(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("****************************************************************************************************************");
			System.out.println("***************************************************** MESSAGES EN DUR ***********************************************");
			System.out.println(resultatMessagesEnDur);
			
			final String resultatAttributsMessages 
			= genererAttributsMessageRGsRenseigneRegexChiffresNumerique(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("****************************************************************************************************************");
			System.out.println("****************************************************** ATTRIBUTS MESSAGES *******************************************");
			System.out.println(resultatAttributsMessages);

			final String resultatAjouterPropertiesEnDur 
			= genererAjouterPropertiesEnDurRGsRenseigneRegexChiffresNumerique(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("****************************************************************************************************************");
			System.out.println("************************************************ AJOUT METHODE ajouterPropertiesEnDur() ********************************");
			System.out.println(resultatAjouterPropertiesEnDur);
		
			final String resultatGenererGettersSetters 
			= genererGettersSettersMessagesRGsRenseigneRegexChiffresNumerique(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("****************************************************************************************************************");
			System.out.println("********************************************************** GETTERS ET SETTERS ********************************************");
			System.out.println(resultatGenererGettersSetters);
		
			final String resultatTestJUnit 
			= genererTestsMessagesRGsRenseigneRegexChiffresNumerique(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("****************************************************************************************************************");
			System.out.println("******************************************************** AJOUT POUR LES TESTS JUNIT *****************************************");
			System.out.println(resultatTestJUnit);
		
	} // Fin de genererCodeRGsRenseigneRegexChiffresNumerique(...)._____
	
	
	
	/**
	 * Point d'entrée de l'application.<br/>
	 *
	 * @param pArgs : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	public static void main(final String... pArgs) throws Exception {
		
		// *********** VALEURS ***********************************
		final String numeroChamp = "71";
		final String nomChamp = "typeComptageNmoins1";
		final String nomChampEnClair = "le type de comptage de l'année n-1";
		final String colonnes = "[296]";
		final String nbreChiffres = "1";
		final String nomenclature = "[1, 2, 3, 4, 5, 6, 7, 8, 9]";
		// *******************************************************
		
//		genererCodeRGsRenseigneRegexChiffres(
//				numeroChamp, nomChamp, nomChampEnClair, colonnes, nbreChiffres);

		genererCodeRGsRenseigneRegexChiffresNomenclature(
				numeroChamp, nomChamp, nomChampEnClair, colonnes, nbreChiffres, nomenclature);
		
//		genererCodeRGsRenseigneRegexChiffresNumerique(
//				numeroChamp, nomChamp, nomChampEnClair, colonnes, nbreChiffres);
		
	} // Fin de main(...)._________________________________________________
	
	
	
} // FIN DE LA CLASSE GenerateurGestionnairePreferencesControle.-------------
