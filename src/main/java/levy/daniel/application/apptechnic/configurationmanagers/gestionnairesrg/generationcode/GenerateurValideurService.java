package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.generationcode;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairestemplates.IGestionnaireTemplates;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairestemplates.impl.GestionnaireTemplates;

/**
 * CLASSE GenerateurValideurService :<br/>
 * Classe UTILITAIRE (final avec toutes les méthodes static) 
 * chargée de <b>générer le code</b> à insérer dans un 
 * <code><b>OBJETMETIERValideurService</b></code>.<br/>
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
public final class GenerateurValideurService {

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
		= LogFactory.getLog(GenerateurValideurService.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation
	 */
	private GenerateurValideurService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * génère les lignes de code <i>commentées</i> relatives aux :
	 * <ul>
	 * <li>méthodes de validation d'un attribut dans un OBJETMETIERValideurService</li>
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
	private static String genererValiderAttributRGsRenseigneRegexChiffres(
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
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurvalideurservice/template_genererValiderAttributRGsRenseigneRegexChiffres.txt";
		
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
		
	} // Fin de genererValiderAttributRGsRenseigneRegexChiffres(...).______
	

	
	/**
	 * génère les lignes de code <i>commentées</i> relatives au :
	 * <ul>
	 * <li>complément à la méthode valider() de validation 
	 * d'un OBJET METIER dans un OBJETMETIERValideurService</li>
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
	private static String genererValiderGsRenseigneRegexChiffres(
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
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurvalideurservice/template_genererValiderRGsRenseigneRegexChiffres.txt";
		
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
		
	} // Fin de genererValiderGsRenseigneRegexChiffres(...)._______________
	

	
	/**
	 * génère les lignes de code <i>commentées</i> relatives au :
	 * <ul>
	 * <li>tests JUnit de la méthode valider() de l'attribut 
	 * d'un OBJET METIER dans un OBJETMETIERValideurService</li>
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
	private static String genererTestRenseigneRegexChiffres(
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
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurvalideurservice/template_genererTestRGsRenseigneRegexChiffres.txt";
		
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
		
	} // Fin de genererTestRenseigneRegexChiffres(...).____________________
	

	
	/**
	 * génère les lignes de code <i>commentées</i> relatives à la :
	 * <ul>
	 * <li>méthode ajouterToutesRG() du Test Junit</li>
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
	private static String genererAjouterToutesRGRenseigneRegexChiffres(
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
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurvalideurservice/template_genererTestactivertoutesRGsRenseigneRegexChiffres";
		
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
		
	} // Fin de genererAjouterToutesRGRenseigneRegexChiffres(...)._________


	
	/**
	 * génère l'ensemble des lignes de code <i>commentées</i> 
	 * relatives à un attribut à insérer dans un :
	 * <ul>
	 * <li>OBJETMETIERServiceValideur</li>
	 * <li>OBJETMETIERServiceValideurTest</li>
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
			
			final String resultatValiderAttribut 
			= genererValiderAttributRGsRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);
			
			System.out.println("********** METHODES VALIDER ATTRIBUT **************");
			System.out.println(resultatValiderAttribut);
			
			final String resultatAjoutValider 
			= genererValiderGsRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("********** RAJOUTER DANS VALIDER **************");
			System.out.println(resultatAjoutValider);

			final String resultatAjouterTestValiderAttribut 
			= genererTestRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("********** AJOUT DE METHODES DANS TEST JUNIT **************");
			System.out.println(resultatAjouterTestValiderAttribut);
		
			final String resultatGenererAjouterToutesRG 
			= genererAjouterToutesRGRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, pNumeroChamp, pNomChamp, pNomChampEnClair, pColonnes, pNbreChiffres);

			System.out.println("********** A RAJOUTER DANS activerToutesRG() du Test JUNIT **************");
			System.out.println(resultatGenererAjouterToutesRG);
				
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
	
	
			
} // FIN DE LA CLASSE GenerateurValideurService/-----------------------------