package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.generationcode;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairestemplates.IGestionnaireTemplates;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairestemplates.impl.GestionnaireTemplates;

/**
 * CLASSE GenerateurGestionnairePreferencesRG :<br/>
 * Classe UTILITAIRE (final avec toutes les méthodes static) 
 * chargée de <b>générer le code</b> à insérer dans un 
 * <code><b>OBJETMETIERCestionnairePreferencesRG</b></code>.<br/>
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
 * @since 20 août 2019
 *
 */
public final class GenerateurGestionnairePreferencesRG {

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
		= LogFactory.getLog(GenerateurGestionnairePreferencesRG.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation
	 */
	private GenerateurGestionnairePreferencesRG() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li><b>clés des Booleans des RG</b> 
	 * de contrôle de l'attribut KEY_VALIDER_...</li>
	 * <li><b>Booleans en DUR des RG</b> 
	 * de contrôle de l'attribut MESSAGE_OBJETMETIER_...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * </ul>
	 * Par exemple, pour l'attribut 'anneeTraitement' d'une SectionHit (OBJET METIER) :
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
	 * et BOOLEANS EN DUR du 
	 * <code><b>OBJETMETIERGestionnairePreferencesRG</b></code>.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String genererBooleansEnDurRGsRenseigneRegexChiffres(
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
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencesrgHIT/template_genererBooleansEnDurRGsRenseigneRegexChiffres.txt";
		
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
		
	} // Fin de genererBooleansEnDurRGsRenseigneRegexChiffres(...).________

	
	
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
	private static String genererAttributsBooleanRGsRenseigneRegexChiffres(
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
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencesrgHIT/template_genererAttributsBooleanRGsRenseigneRegexChiffres.txt";
		
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

	} // Fin de genererAttributsBooleanRGsRenseigneRegexChiffres(...)._____

	
	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li><b>ajout des Booleans EN DUR des RG</b> 
	 * de contrôle de l'attribut messageObjetMetierATTRIBUTRGxxx...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * </ul>
	 * Par exemple, pour l'attribut 'anneeTraitement' d'une SectionHit (OBJET METIER) :
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
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencesrgHIT/template_ajouterPropertiesEnDurRGsRenseigneRegexChiffres.txt";
		
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
	 * <li><b>Getters des attributs des booleans des RG</b> 
	 * de contrôle de l'attributvaliderObjetMetierATTRIBUTRGxxx...</li>
	 * <li><b>Setters des attributs des booleans des RG</b> 
	 * de contrôle de l'attributvaliderObjetMetierATTRIBUTRGxxx...</li>
	 * </ul>
	 * S'applique aux attributs contrôlés par :
	 * <ul>
	 * <li>une <b>RG RENSEIGNE</b>.</li>
	 * <li>une <b>RG REGEX portant sur un nombre obligatoire de chiffres</b>.</li>
	 * </ul>
	 * Par exemple, pour l'attribut 'anneeTraitement' d'une SectionHit (OBJET METIER) :
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
	private static String genererGettersSettersBooleansRGsRenseigneRegexChiffres(
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
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencesrgHIT/template_genererGettersSettersRGsRenseigneRegexChiffres.txt";
		
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
	private static String genererTestsBooleansRGsRenseigneRegexChiffres(
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
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencesrgHIT/template_genererTestRGsRenseigneRegexChiffres.txt";
		
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

	} // Fin de genererTestsBooleansRGsRenseigneRegexChiffres(...).________


	
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
			= genererBooleansEnDurRGsRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);
			
			System.out.println("********** BOOLEANS EN DUR **************");
			System.out.println(resultatMessagesEnDur);
			
			final String resultatAttributsBooleans 
			= genererAttributsBooleanRGsRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("********** ATTRIBUTS BOOLEANS **************");
			System.out.println(resultatAttributsBooleans);

			final String resultatAjouterPropertiesEnDur 
			= genererAjouterPropertiesEnDurRGsRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("********** AJOUT METHODE ajouterPropertiesEnDur() **************");
			System.out.println(resultatAjouterPropertiesEnDur);
		
			final String resultatGenererGettersSetters 
			= genererGettersSettersBooleansRGsRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("********** GETTERS ET SETTERS **************");
			System.out.println(resultatGenererGettersSetters);
		
			final String resultatTestJUnit 
			= genererTestsBooleansRGsRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("********** AJOUT POUR LES TESTS JUNIT **************");
			System.out.println(resultatTestJUnit);
		
	} // Fin de genererCodeRGsRenseigneRegexChiffres(...)._________________
	
	
	
	/**
	 * Point d'entrée de l'application.<br/>
	 *
	 * @param pArgs : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	public static void main(final String... pArgs) throws Exception {
		
		// *********** VALEURS ***********************************
		final String numeroChamp = "10";
		final String nomChamp = "indiceLettreRoute";
		final String nomChampEnClair = "l'indice lettre de la route";
		final String colonnes = "[22]";
		final String nbreChiffres = "1";
		// *******************************************************
		
		genererCodeRGsRenseigneRegexChiffres(
				numeroChamp, nomChamp, nomChampEnClair, colonnes, nbreChiffres);
		
	} // Fin de main(...)._________________________________________________
	
	
		
} // FIN DE LA CLASSE GenerateurGestionnairePreferencesRG.-------------------