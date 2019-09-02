package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.generationcode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairestemplates.IGestionnaireTemplates;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairestemplates.impl.GestionnaireTemplates;

/**
 * CLASSE GenerateurMjmPcnuitMensuels :<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 2 sept. 2019
 *
 */
public final class GenerateurMjmPcnuitMensuels {

	// ************************ATTRIBUTS************************************/

	/**
	 * map contenant le nom des mois en clair.<br/>
	 */
	public static final Map<Integer, String> MOIS_MAP;
	
	/**
	 * map contenant les colonnes des MJM.<br/>
	 */
	public static final Map<Integer, String> COLONNES_MAP;
	
	
	/**
	 * map contenant les colonnes des PCNuit.<br/>
	 */
	public static final Map<Integer, String> COLONNESPLUSUN_MAP;

	
	static {
		
		MOIS_MAP = new ConcurrentHashMap<Integer, String>();
		
		MOIS_MAP.put(1, "janvier");
		MOIS_MAP.put(2, "février");
		MOIS_MAP.put(3, "mars");
		MOIS_MAP.put(4, "avril");
		MOIS_MAP.put(5, "mai");
		MOIS_MAP.put(6, "juin");
		MOIS_MAP.put(7, "juillet");
		MOIS_MAP.put(8, "août");
		MOIS_MAP.put(9, "septembre");
		MOIS_MAP.put(10, "octobre");
		MOIS_MAP.put(11, "novembre");
		MOIS_MAP.put(12, "décembre");
		
		COLONNES_MAP = new ConcurrentHashMap<Integer, String>();
		
		COLONNES_MAP.put(1, "[168-173]");
		COLONNES_MAP.put(2, "[177-182]");
		COLONNES_MAP.put(3, "[186-191]");
		COLONNES_MAP.put(4, "[195-200]");
		COLONNES_MAP.put(5, "[204-209]");
		COLONNES_MAP.put(6, "[213-218]");
		COLONNES_MAP.put(7, "[222-227]");
		COLONNES_MAP.put(8, "[231-236]");
		COLONNES_MAP.put(9, "[240-245]");
		COLONNES_MAP.put(10, "[249-254]");
		COLONNES_MAP.put(11, "[258-263]");
		COLONNES_MAP.put(12, "[267-272]");
		
		COLONNESPLUSUN_MAP = new ConcurrentHashMap<Integer, String>();
		
		COLONNESPLUSUN_MAP.put(1, "[174-176]");
		COLONNESPLUSUN_MAP.put(2, "[183-185]");
		COLONNESPLUSUN_MAP.put(3, "[192-194]");
		COLONNESPLUSUN_MAP.put(4, "[201-203]");
		COLONNESPLUSUN_MAP.put(5, "[210-212]");
		COLONNESPLUSUN_MAP.put(6, "[219-221]");
		COLONNESPLUSUN_MAP.put(7, "[228-230]");
		COLONNESPLUSUN_MAP.put(8, "[237-239]");
		COLONNESPLUSUN_MAP.put(9, "[246-248]");
		COLONNESPLUSUN_MAP.put(10, "[255-257]");
		COLONNESPLUSUN_MAP.put(11, "[264-266]");
		COLONNESPLUSUN_MAP.put(12, "[273-275]");
		
	}
	
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(GenerateurMjmPcnuitMensuels.class);

	// *************************METHODES************************************/	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation
	 */
	private GenerateurMjmPcnuitMensuels() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * .<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String preferencesControlesMessagesEnDur(final int pN) 
			throws Exception {
		
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_message_MJMN_PCNUITN_mois.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$mois}"
				, "{$MOIS}"
				, "{$moisEnClair}"
				, "{$numeroMois}"
				, "{$pColonnes}"
				, "{$pColonnes+1}"};
		
		final int numeroChampInt = 44 + 2 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String mois = fournirMoisSurDeuxChiffres(pN);
		final String moisCapitalise = fournirMoisCapitaliseSurDeuxChiffres(pN);
		final String moisEnClair = MOIS_MAP.get(pN);
		final String numeroMois = fournirNumeroMoisSurDeuxChiffres(pN);
		final String colonnes = COLONNES_MAP.get(pN);
		final String colonnesPlusUn = COLONNESPLUSUN_MAP.get(pN);
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, mois
				, moisCapitalise
				, moisEnClair
				, numeroMois
				, colonnes
				, colonnesPlusUn};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de preferencesControlesMessagesEnDur(...).____________________


	
	/**
	 * .<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	private static String preferencesControlesAttributsEnDur(final int pN) 
			throws Exception {
		
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_attribut_MJMN_PCNUITN_mois.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$mois}"
				, "{$MOIS}"
				, "{$moisEnClair}"
				, "{$numeroMois}"
				, "{$pColonnes}"
				, "{$pColonnes+1}"};
		
		final int numeroChampInt = 44 + 2 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String mois = fournirMoisSurDeuxChiffres(pN);
		final String moisCapitalise = fournirMoisCapitaliseSurDeuxChiffres(pN);
		final String moisEnClair = MOIS_MAP.get(pN);
		final String numeroMois = fournirNumeroMoisSurDeuxChiffres(pN);
		final String colonnes = COLONNES_MAP.get(pN);
		final String colonnesPlusUn = COLONNESPLUSUN_MAP.get(pN);
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, mois
				, moisCapitalise
				, moisEnClair
				, numeroMois
				, colonnes
				, colonnesPlusUn};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de preferencesControlesAttributsEnDur(...).___________________


	
	/**
	 * .<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	private static String preferencesControlesAjouterPropsEnDur(final int pN) 
			throws Exception {
		
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_ajouterProps_MJMN_PCNUITN_mois.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$mois}"
				, "{$MOIS}"
				, "{$moisEnClair}"
				, "{$numeroMois}"
				, "{$pColonnes}"
				, "{$pColonnes+1}"};
		
		final int numeroChampInt = 44 + 2 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String mois = fournirMoisSurDeuxChiffres(pN);
		final String moisCapitalise = fournirMoisCapitaliseSurDeuxChiffres(pN);
		final String moisEnClair = MOIS_MAP.get(pN);
		final String numeroMois = fournirNumeroMoisSurDeuxChiffres(pN);
		final String colonnes = COLONNES_MAP.get(pN);
		final String colonnesPlusUn = COLONNESPLUSUN_MAP.get(pN);
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, mois
				, moisCapitalise
				, moisEnClair
				, numeroMois
				, colonnes
				, colonnesPlusUn};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de preferencesControlesAjouterPropsEnDur(...).________________


	
	/**
	 * .<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	private static String preferencesControlesGettersSetters(final int pN) 
			throws Exception {
		
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_getter_setter_Controles_MJMN_PCNUITN_mois.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$mois}"
				, "{$MOIS}"
				, "{$moisEnClair}"
				, "{$numeroMois}"
				, "{$pColonnes}"
				, "{$pColonnes+1}"};
		
		final int numeroChampInt = 44 + 2 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String mois = fournirMoisSurDeuxChiffres(pN);
		final String moisCapitalise = fournirMoisCapitaliseSurDeuxChiffres(pN);
		final String moisEnClair = MOIS_MAP.get(pN);
		final String numeroMois = fournirNumeroMoisSurDeuxChiffres(pN);
		final String colonnes = COLONNES_MAP.get(pN);
		final String colonnesPlusUn = COLONNESPLUSUN_MAP.get(pN);
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, mois
				, moisCapitalise
				, moisEnClair
				, numeroMois
				, colonnes
				, colonnesPlusUn};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de preferencesControlesGettersSetters(...).________________


	
	/**
	 * .<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	private static String preferencesRGGettersSetters(final int pN) 
			throws Exception {
		
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencesrgHIT/template_getter_setter_RG_MJMN_PCNUITN_mois.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$mois}"
				, "{$MOIS}"
				, "{$moisEnClair}"
				, "{$numeroMois}"
				, "{$pColonnes}"
				, "{$pColonnes+1}"};
		
		final int numeroChampInt = 44 + 2 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String mois = fournirMoisSurDeuxChiffres(pN);
		final String moisCapitalise = fournirMoisCapitaliseSurDeuxChiffres(pN);
		final String moisEnClair = MOIS_MAP.get(pN);
		final String numeroMois = fournirNumeroMoisSurDeuxChiffres(pN);
		final String colonnes = COLONNES_MAP.get(pN);
		final String colonnesPlusUn = COLONNESPLUSUN_MAP.get(pN);
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, mois
				, moisCapitalise
				, moisEnClair
				, numeroMois
				, colonnes
				, colonnesPlusUn};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de preferencesRGGettersSetters(...).__________________________


	
	/**
	 * .<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	private static String preferencesControlesTestJunit(final int pN) 
			throws Exception {
		
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_TestJunit_Controles_MJMN_PCNUITN_mois.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$mois}"
				, "{$MOIS}"
				, "{$moisEnClair}"
				, "{$numeroMois}"
				, "{$pColonnes}"
				, "{$pColonnes+1}"};
		
		final int numeroChampInt = 44 + 2 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String mois = fournirMoisSurDeuxChiffres(pN);
		final String moisCapitalise = fournirMoisCapitaliseSurDeuxChiffres(pN);
		final String moisEnClair = MOIS_MAP.get(pN);
		final String numeroMois = fournirNumeroMoisSurDeuxChiffres(pN);
		final String colonnes = COLONNES_MAP.get(pN);
		final String colonnesPlusUn = COLONNESPLUSUN_MAP.get(pN);
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, mois
				, moisCapitalise
				, moisEnClair
				, numeroMois
				, colonnes
				, colonnesPlusUn};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de preferencesControlesTestJunit(...).________________________

	
	
	/**
	 * .<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String preferencesRGBooleansEnDur(final int pN) 
			throws Exception {
		
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencesrgHIT/template_booleans_MJMN_PBNUITN_mois.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$mois}"
				, "{$MOIS}"
				, "{$moisEnClair}"
				, "{$numeroMois}"
				, "{$pColonnes}"
				, "{$pColonnes+1}"};
		
		final int numeroChampInt = 44 + 2 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String mois = fournirMoisSurDeuxChiffres(pN);
		final String moisCapitalise = fournirMoisCapitaliseSurDeuxChiffres(pN);
		final String moisEnClair = MOIS_MAP.get(pN);
		final String numeroMois = fournirNumeroMoisSurDeuxChiffres(pN);
		final String colonnes = COLONNES_MAP.get(pN);
		final String colonnesPlusUn = COLONNESPLUSUN_MAP.get(pN);
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, mois
				, moisCapitalise
				, moisEnClair
				, numeroMois
				, colonnes
				, colonnesPlusUn};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de preferencesRGBooleansEnDur(...).____________________


	
	/**
	 * .<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	private static String preferencesRGAttributs(final int pN) 
			throws Exception {
		
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencesrgHIT/template_attributs_MJMN_PCNUITN_mois.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$mois}"
				, "{$MOIS}"
				, "{$moisEnClair}"
				, "{$numeroMois}"
				, "{$pColonnes}"
				, "{$pColonnes+1}"};
		
		final int numeroChampInt = 44 + 2 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String mois = fournirMoisSurDeuxChiffres(pN);
		final String moisCapitalise = fournirMoisCapitaliseSurDeuxChiffres(pN);
		final String moisEnClair = MOIS_MAP.get(pN);
		final String numeroMois = fournirNumeroMoisSurDeuxChiffres(pN);
		final String colonnes = COLONNES_MAP.get(pN);
		final String colonnesPlusUn = COLONNESPLUSUN_MAP.get(pN);
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, mois
				, moisCapitalise
				, moisEnClair
				, numeroMois
				, colonnes
				, colonnesPlusUn};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de preferencesRGAttributs(...)._______________________________


	
	/**
	 * .<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	private static String preferencesRGAjouterPropsEnDur(final int pN) 
			throws Exception {
		
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencesrgHIT/template_ajouterProps_MJMN_PCNUITN_mois.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$mois}"
				, "{$MOIS}"
				, "{$moisEnClair}"
				, "{$numeroMois}"
				, "{$pColonnes}"
				, "{$pColonnes+1}"};
		
		final int numeroChampInt = 44 + 2 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String mois = fournirMoisSurDeuxChiffres(pN);
		final String moisCapitalise = fournirMoisCapitaliseSurDeuxChiffres(pN);
		final String moisEnClair = MOIS_MAP.get(pN);
		final String numeroMois = fournirNumeroMoisSurDeuxChiffres(pN);
		final String colonnes = COLONNES_MAP.get(pN);
		final String colonnesPlusUn = COLONNESPLUSUN_MAP.get(pN);
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, mois
				, moisCapitalise
				, moisEnClair
				, numeroMois
				, colonnes
				, colonnesPlusUn};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de preferencesRGAjouterPropsEnDur(...)._______________________


	
	/**
	 * .<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	private static String preferencesRGTestJunit(final int pN) 
			throws Exception {
		
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencesrgHIT/template_TestJUnit_RG_MJMN_PCNUITN_mois.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$mois}"
				, "{$MOIS}"
				, "{$moisEnClair}"
				, "{$numeroMois}"
				, "{$pColonnes}"
				, "{$pColonnes+1}"};
		
		final int numeroChampInt = 44 + 2 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String mois = fournirMoisSurDeuxChiffres(pN);
		final String moisCapitalise = fournirMoisCapitaliseSurDeuxChiffres(pN);
		final String moisEnClair = MOIS_MAP.get(pN);
		final String numeroMois = fournirNumeroMoisSurDeuxChiffres(pN);
		final String colonnes = COLONNES_MAP.get(pN);
		final String colonnesPlusUn = COLONNESPLUSUN_MAP.get(pN);
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, mois
				, moisCapitalise
				, moisEnClair
				, numeroMois
				, colonnes
				, colonnesPlusUn};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de preferencesRGTestJunit(...)._______________________________


	
	/**
	 * .<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	private static String methodesValiderAttributValideurService(final int pN) 
			throws Exception {
		
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurvalideurservice/template_valider_attribut_MJMN_PCNUITN_mois.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$mois}"
				, "{$MOIS}"
				, "{$moisEnClair}"
				, "{$numeroMois}"
				, "{$pColonnes}"
				, "{$pColonnes+1}"};
		
		final int numeroChampInt = 44 + 2 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String mois = fournirMoisSurDeuxChiffres(pN);
		final String moisCapitalise = fournirMoisCapitaliseSurDeuxChiffres(pN);
		final String moisEnClair = MOIS_MAP.get(pN);
		final String numeroMois = fournirNumeroMoisSurDeuxChiffres(pN);
		final String colonnes = COLONNES_MAP.get(pN);
		final String colonnesPlusUn = COLONNESPLUSUN_MAP.get(pN);
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, mois
				, moisCapitalise
				, moisEnClair
				, numeroMois
				, colonnes
				, colonnesPlusUn};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de methodesValiderAttributValideurService(...)._______________


	
	/**
	 * .<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	private static String methodeAjouterAValiderValideurService(final int pN) 
			throws Exception {
		
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "/templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurvalideurservice/template_ajouterAValider_MJMN_PCNUITN_mois.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$mois}"
				, "{$MOIS}"
				, "{$moisEnClair}"
				, "{$numeroMois}"
				, "{$pColonnes}"
				, "{$pColonnes+1}"};
		
		final int numeroChampInt = 44 + 2 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String mois = fournirMoisSurDeuxChiffres(pN);
		final String moisCapitalise = fournirMoisCapitaliseSurDeuxChiffres(pN);
		final String moisEnClair = MOIS_MAP.get(pN);
		final String numeroMois = fournirNumeroMoisSurDeuxChiffres(pN);
		final String colonnes = COLONNES_MAP.get(pN);
		final String colonnesPlusUn = COLONNESPLUSUN_MAP.get(pN);
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, mois
				, moisCapitalise
				, moisEnClair
				, numeroMois
				, colonnes
				, colonnesPlusUn};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de methodeAjouterAValiderValideurService(...)._______________


	
	/**
	 * .<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	private static String valideurServiceMethodesTestJunit(final int pN) 
			throws Exception {
		
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurvalideurservice/template_testJUnit_MJMN_PCNUITN_mois.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$mois}"
				, "{$MOIS}"
				, "{$moisEnClair}"
				, "{$numeroMois}"
				, "{$pColonnes}"
				, "{$pColonnes+1}"};
		
		final int numeroChampInt = 44 + 2 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String mois = fournirMoisSurDeuxChiffres(pN);
		final String moisCapitalise = fournirMoisCapitaliseSurDeuxChiffres(pN);
		final String moisEnClair = MOIS_MAP.get(pN);
		final String numeroMois = fournirNumeroMoisSurDeuxChiffres(pN);
		final String colonnes = COLONNES_MAP.get(pN);
		final String colonnesPlusUn = COLONNESPLUSUN_MAP.get(pN);
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, mois
				, moisCapitalise
				, moisEnClair
				, numeroMois
				, colonnes
				, colonnesPlusUn};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de valideurServiceMethodesTestJunit(...)._____________________


	
	/**
	 * .<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	private static String valideurServiceAjouterAActiverToutesRGTestJunit(final int pN) 
			throws Exception {
		
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurvalideurservice/template_ajouterAActiverToutesRG_MJMN_PCNUITN_mois.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$mois}"
				, "{$MOIS}"
				, "{$moisEnClair}"
				, "{$numeroMois}"
				, "{$pColonnes}"
				, "{$pColonnes+1}"};
		
		final int numeroChampInt = 44 + 2 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String mois = fournirMoisSurDeuxChiffres(pN);
		final String moisCapitalise = fournirMoisCapitaliseSurDeuxChiffres(pN);
		final String moisEnClair = MOIS_MAP.get(pN);
		final String numeroMois = fournirNumeroMoisSurDeuxChiffres(pN);
		final String colonnes = COLONNES_MAP.get(pN);
		final String colonnesPlusUn = COLONNESPLUSUN_MAP.get(pN);
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, mois
				, moisCapitalise
				, moisEnClair
				, numeroMois
				, colonnes
				, colonnesPlusUn};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de valideurServiceAjouterAActiverToutesRGTestJunit(...).______


	
	/**
	 * .<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	private static String methodesAttributsGestionnaireRG(final int pN) 
			throws Exception {
		
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/gestionnaireRG/template_Attributs_GestionnaireRG_MJMN_PCNUITN_mois.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$mois}"
				, "{$MOIS}"
				, "{$moisEnClair}"
				, "{$numeroMois}"
				, "{$pColonnes}"
				, "{$pColonnes+1}"};
		
		final int numeroChampInt = 44 + 2 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String mois = fournirMoisSurDeuxChiffres(pN);
		final String moisCapitalise = fournirMoisCapitaliseSurDeuxChiffres(pN);
		final String moisEnClair = MOIS_MAP.get(pN);
		final String numeroMois = fournirNumeroMoisSurDeuxChiffres(pN);
		final String colonnes = COLONNES_MAP.get(pN);
		final String colonnesPlusUn = COLONNESPLUSUN_MAP.get(pN);
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, mois
				, moisCapitalise
				, moisEnClair
				, numeroMois
				, colonnes
				, colonnesPlusUn};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de methodesAttributsGestionnaireRG(...).______________________


	
	/**
	 * .<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	private static String methodeAjouterAMapRGGestionnaireRG(final int pN) 
			throws Exception {
		
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/gestionnaireRG/template_AjouterARemplirMapRG_MJMN_PCNUITN_mois.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$mois}"
				, "{$MOIS}"
				, "{$moisEnClair}"
				, "{$numeroMois}"
				, "{$pColonnes}"
				, "{$pColonnes+1}"};
		
		final int numeroChampInt = 44 + 2 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String mois = fournirMoisSurDeuxChiffres(pN);
		final String moisCapitalise = fournirMoisCapitaliseSurDeuxChiffres(pN);
		final String moisEnClair = MOIS_MAP.get(pN);
		final String numeroMois = fournirNumeroMoisSurDeuxChiffres(pN);
		final String colonnes = COLONNES_MAP.get(pN);
		final String colonnesPlusUn = COLONNESPLUSUN_MAP.get(pN);
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, mois
				, moisCapitalise
				, moisEnClair
				, numeroMois
				, colonnes
				, colonnesPlusUn};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de methodeAjouterAMapRGGestionnaireRG(...).___________________


	
	/**
	 * .<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	private static String methodePutMapRGGestionnaireRG(final int pN) 
			throws Exception {
		
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/gestionnaireRG/template_putMapRG_MJMN_PCNUITN_mois.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$mois}"
				, "{$MOIS}"
				, "{$moisEnClair}"
				, "{$numeroMois}"
				, "{$pColonnes}"
				, "{$pColonnes+1}"};
		
		final int numeroChampInt = 44 + 2 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String mois = fournirMoisSurDeuxChiffres(pN);
		final String moisCapitalise = fournirMoisCapitaliseSurDeuxChiffres(pN);
		final String moisEnClair = MOIS_MAP.get(pN);
		final String numeroMois = fournirNumeroMoisSurDeuxChiffres(pN);
		final String colonnes = COLONNES_MAP.get(pN);
		final String colonnesPlusUn = COLONNESPLUSUN_MAP.get(pN);
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, mois
				, moisCapitalise
				, moisEnClair
				, numeroMois
				, colonnes
				, colonnesPlusUn};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de methodePutMapRGGestionnaireRG(...).___________________

	
	
	/**
	 * fournit moisNN comme 'mois01' pour janvier ou 
	 * 'mois10' pour octobre.<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return : String :  .<br/>
	 */
	private static String fournirMoisSurDeuxChiffres(final int pN) {
		
		String resultat = "";
		
		if (pN < 10) {
			resultat = "mois0" + pN;
		} else {
			resultat = "mois" + pN;
		}
		
		return resultat;
		
	} // Fin de fournirMoisSurDeuxChiffres(...).___________________________
	
	
	
	/**
	 * fournit MOISNN comme 'MOIS01' pour janvier ou 
	 * 'MOIS10' pour octobre.<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return : String :  .<br/>
	 */
	private static String fournirMoisCapitaliseSurDeuxChiffres(final int pN) {
		
		String resultat = "";
		
		if (pN < 10) {
			resultat = "MOIS0" + pN;
		} else {
			resultat = "MOIS" + pN;
		}
		
		return resultat;
		
	} // Fin de fournirMoisCapitaliseSurDeuxChiffres(...)._________________
	
	
	
	/**
	 * fournit (NN) comme '(01)' pour janvier ou 
	 * '(10)' pour octobre.<br/>
	 *
	 * @param pN : int : le mois (1 pour janvier, 10 pour octobre).<br/>
	 * 
	 * @return : String :  .<br/>
	 */
	private static String fournirNumeroMoisSurDeuxChiffres(final int pN) {
		
		String resultat = "";
		
		if (pN < 10) {
			resultat = "(0" + pN + ")";
		} else {
			resultat = "(" + pN + ")";
		}
		
		return resultat;
		
	} // Fin de fournirNumeroMoisSurDeuxChiffres(...)._____________________

	
	
	/**
	 * point d'entrée de l'application.<br/>
	 *
	 * @param pArgs : String[] :  .<br/>
	 * 
	 * @throws Exception 
	 */
	public static void main(
			final String... pArgs) throws Exception {
		
		for (int i = 4; i < 13; i++) {
			System.out.println(methodePutMapRGGestionnaireRG(i));
		}

	} // Fin de main(...)._________________________________________________
	


} // FIn DE LA CLASSE GenerateurMjmPcnuitMensuels.---------------------------
