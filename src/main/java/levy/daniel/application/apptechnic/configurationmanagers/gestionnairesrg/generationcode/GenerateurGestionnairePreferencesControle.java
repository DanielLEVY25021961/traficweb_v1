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
			
			System.out.println("********** MESSAGES EN DUR **************");
			System.out.println(resultatMessagesEnDur);
			
			final String resultatAttributsMessages 
			= genererAttributsMessageRGsRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("********** ATTRIBUTS MESSAGES **************");
			System.out.println(resultatAttributsMessages);

			final String resultatAjouterPropertiesEnDur 
			= genererAjouterPropertiesEnDurRGsRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("********** AJOUT METHODE ajouterPropertiesEnDur() **************");
			System.out.println(resultatAjouterPropertiesEnDur);
		
			final String resultatGenererGettersSetters 
			= genererGettersSettersMessagesRGsRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("********** GETTERS ET SETTERS **************");
			System.out.println(resultatGenererGettersSetters);
		
			final String resultatTestJUnit 
			= genererTestsMessagesRGsRenseigneRegexChiffres(
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
		final String numeroChamp = "7";
		final String nomChamp = "zoneLibre1";
		final String nomChampEnClair = "la zone libre";
		final String colonnes = "[16]";
		final String nbreChiffres = "1";
		// *******************************************************
		
		genererCodeRGsRenseigneRegexChiffres(
				numeroChamp, nomChamp, nomChampEnClair, colonnes, nbreChiffres);
		
	} // Fin de main(...)._________________________________________________
	
	
	
} // FIN DE LA CLASSE GenerateurGestionnairePreferencesControle.-------------
