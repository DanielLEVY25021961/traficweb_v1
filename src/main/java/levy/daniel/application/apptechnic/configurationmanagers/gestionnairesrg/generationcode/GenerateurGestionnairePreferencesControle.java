package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.generationcode;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairestemplates.IGestionnaireTemplates;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairestemplates.impl.GestionnaireTemplates;

/**
 * CLASSE GenerateurGestionnairePreferencesControle :<br/>
 * .<br/>
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
	 * .<br/>
	 * <br/>
	 *
	 * @param pObjetMetier : String : nom de l'objet métier comme 'SectionHit'.
	 * @param pObjetMetierEnClair : String : nom de l'objet métier en clair avec  article comme 'la section HIT'
	 * @param pNumeroChamp : String : numéro d'ordre du champ dans le HIT comme '5' pour l'attribut 'classe'.
	 * @param pNomChamp : String : nom de l'attribut comme 'classe' ou 'numSection'.
	 * @param pColonnes : String : colonnes de l'attribut dans le HIT comme '[12-13]' pour l'attribut 'classe'.
	 * @param pNbreChiffres : String : nombre de chiffres exact (REGEX) de l'attribut.
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String genererMessagesEnDurRGsRenseigneRegexChiffres(
			final String pObjetMetier
				, final String pObjetMetierEnClair
					, final String pNumeroChamp
						, final String pNomChamp
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
	 * Point d'entrée de l'application.<br/>
	 *
	 * @param pArgs : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	public static void main(String... pArgs) throws Exception {
		
		final String resultat 
			= genererMessagesEnDurRGsRenseigneRegexChiffres(
					OBJET_METIER
					, OBJET_METIER_EN_CLAIR
					, "6", "anneeTraitement", "[14-15]", "2");
		
		System.out.println(resultat);
		
	} // Fin de main(...)._________________________________________________
	
	
} // FIN DE LA CLASSE GenerateurGestionnairePreferencesControle.-------------
