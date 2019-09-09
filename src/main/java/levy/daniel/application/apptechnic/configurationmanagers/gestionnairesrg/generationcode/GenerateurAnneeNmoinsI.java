package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.generationcode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairestemplates.IGestionnaireTemplates;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairestemplates.impl.GestionnaireTemplates;

/**
 * CLASSE GenerateurAnneeNmoinsI :<br/>
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
 * @since 7 sept. 2019
 *
 */
public final class GenerateurAnneeNmoinsI {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(GenerateurAnneeNmoinsI.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation
	 */
	private GenerateurAnneeNmoinsI() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * .<br/>
	 *
	 * @param pN : int :le moins de l'année (1 pour année n-1).<br/>
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
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_ajouterProps_MJANmoinsx.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$pNumeroChamp + 2}"
				, "{$pNumeroChamp + 3}"
				, "{$pNumeroChamp + 4}"
				, "{$pNumeroChamp + 5}"
				, "{$pNumeroChamp + 6}"
				, "{$pNumeroChamp + 7}"
				, "{$moinsi}"
				, "{$MOINSi}"
				, "{$n-i}"
				, "{$colonnesAnnee}"
				, "{$colonnesMja}"
				, "{$colonneTypeComptage}"
				, "{$colonneModeCalcul}"
				, "{$pcPLNmoinsi}"
				, "{$evaluationPL}"
				, "{$colonnesPcNuitAnnuel}"
				, "{$colonneIndiceFiabiliteMjaN}"};
		
		final int numeroChampInt = 69 + 8 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		final int numeroChampPlusDeuxInt = numeroChampInt + 2;
		final int numeroChampPlusTroisInt = numeroChampInt + 3;
		final int numeroChampPlusQuatreInt = numeroChampInt + 4;
		final int numeroChampPlusCinqInt = numeroChampInt + 5;
		final int numeroChampPlusSixInt = numeroChampInt + 6;
		final int numeroChampPlusSeptInt = numeroChampInt + 7;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String numeroChampPlusDeux = String.valueOf(numeroChampPlusDeuxInt);
		final String numeroChampPlusTrois = String.valueOf(numeroChampPlusTroisInt);
		final String numeroChampPlusQuatre = String.valueOf(numeroChampPlusQuatreInt);
		final String numeroChampPlusCinq = String.valueOf(numeroChampPlusCinqInt);
		final String numeroChampPlusSix = String.valueOf(numeroChampPlusSixInt);
		final String numeroChampPlusSept = String.valueOf(numeroChampPlusSeptInt);
		final String moinsi = "moins" + pN;
		final String mOINSI = "MOINS" + pN;
		final String nMoinsI = "n-" + pN;
		final String colonnesAnnee = "[" + (288 + 18 * (pN-1)) + "-" + (289 + 18 * (pN-1)) + "]";
		final String colonnesMja = "[" + (290 + 18 * (pN-1)) + "-" + (295 + 18 * (pN-1)) + "]";
		final String colonneTypeComptage = "[" + (296 + 18 * (pN-1)) + "]";
		final String colonneModeCalcul = "[" + (297 + 18 * (pN-1)) + "]";
		final String colonnesPcPl = "[" + (298 + 18 * (pN-1)) + "-" + (300 + 18 * (pN-1)) + "]";
		final String colonneEvaluationPL = "[" + (301 + 18 * (pN-1)) + "]";
		final String colonnesPcNuitAnnuel = "[" + (302 + 18 * (pN-1)) + "-" + (304 + 18 * (pN-1)) + "]";
		final String colonneIndiceFiabiliteMjaN = "[" + (305 + 18 * (pN-1)) + "]";
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, numeroChampPlusDeux
				, numeroChampPlusTrois
				, numeroChampPlusQuatre
				, numeroChampPlusCinq
				, numeroChampPlusSix
				, numeroChampPlusSept
				, moinsi
				, mOINSI
				, nMoinsI
				, colonnesAnnee
				, colonnesMja
				, colonneTypeComptage
				, colonneModeCalcul
				, colonnesPcPl
				, colonneEvaluationPL
				, colonnesPcNuitAnnuel
				, colonneIndiceFiabiliteMjaN};
		
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
	 * @param pN : int :le moins de l'année (1 pour année n-1).<br/>
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
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencesrgHIT/template_properties_dur_MJANmoinsx.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$pNumeroChamp + 2}"
				, "{$pNumeroChamp + 3}"
				, "{$pNumeroChamp + 4}"
				, "{$pNumeroChamp + 5}"
				, "{$pNumeroChamp + 6}"
				, "{$pNumeroChamp + 7}"
				, "{$moinsi}"
				, "{$MOINSi}"
				, "{$n-i}"
				, "{$colonnesAnnee}"
				, "{$colonnesMja}"
				, "{$colonneTypeComptage}"
				, "{$colonneModeCalcul}"
				, "{$pcPLNmoinsi}"
				, "{$evaluationPL}"
				, "{$colonnesPcNuitAnnuel}"
				, "{$colonneIndiceFiabiliteMjaN}"};
		
		final int numeroChampInt = 69 + 8 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		final int numeroChampPlusDeuxInt = numeroChampInt + 2;
		final int numeroChampPlusTroisInt = numeroChampInt + 3;
		final int numeroChampPlusQuatreInt = numeroChampInt + 4;
		final int numeroChampPlusCinqInt = numeroChampInt + 5;
		final int numeroChampPlusSixInt = numeroChampInt + 6;
		final int numeroChampPlusSeptInt = numeroChampInt + 7;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String numeroChampPlusDeux = String.valueOf(numeroChampPlusDeuxInt);
		final String numeroChampPlusTrois = String.valueOf(numeroChampPlusTroisInt);
		final String numeroChampPlusQuatre = String.valueOf(numeroChampPlusQuatreInt);
		final String numeroChampPlusCinq = String.valueOf(numeroChampPlusCinqInt);
		final String numeroChampPlusSix = String.valueOf(numeroChampPlusSixInt);
		final String numeroChampPlusSept = String.valueOf(numeroChampPlusSeptInt);
		final String moinsi = "moins" + pN;
		final String mOINSI = "MOINS" + pN;
		final String nMoinsI = "n-" + pN;
		final String colonnesAnnee = "[" + (288 + 18 * (pN-1)) + "-" + (289 + 18 * (pN-1)) + "]";
		final String colonnesMja = "[" + (290 + 18 * (pN-1)) + "-" + (295 + 18 * (pN-1)) + "]";
		final String colonneTypeComptage = "[" + (296 + 18 * (pN-1)) + "]";
		final String colonneModeCalcul = "[" + (297 + 18 * (pN-1)) + "]";
		final String colonnesPcPl = "[" + (298 + 18 * (pN-1)) + "-" + (300 + 18 * (pN-1)) + "]";
		final String colonneEvaluationPL = "[" + (301 + 18 * (pN-1)) + "]";
		final String colonnesPcNuitAnnuel = "[" + (302 + 18 * (pN-1)) + "-" + (304 + 18 * (pN-1)) + "]";
		final String colonneIndiceFiabiliteMjaN = "[" + (305 + 18 * (pN-1)) + "]";
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, numeroChampPlusDeux
				, numeroChampPlusTrois
				, numeroChampPlusQuatre
				, numeroChampPlusCinq
				, numeroChampPlusSix
				, numeroChampPlusSept
				, moinsi
				, mOINSI
				, nMoinsI
				, colonnesAnnee
				, colonnesMja
				, colonneTypeComptage
				, colonneModeCalcul
				, colonnesPcPl
				, colonneEvaluationPL
				, colonnesPcNuitAnnuel
				, colonneIndiceFiabiliteMjaN};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de preferencesRGBooleansEnDur(...).___________________________
	
	
	
	/**
	 * .<br/>
	 *
	 * @param pN : int :le moins de l'année (1 pour année n-1).<br/>
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String preferencesControlesAttributs(final int pN) 
			throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_attributs_MJANMoinsx.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$pNumeroChamp + 2}"
				, "{$pNumeroChamp + 3}"
				, "{$pNumeroChamp + 4}"
				, "{$pNumeroChamp + 5}"
				, "{$pNumeroChamp + 6}"
				, "{$pNumeroChamp + 7}"
				, "{$moinsi}"
				, "{$MOINSi}"
				, "{$n-i}"
				, "{$colonnesAnnee}"
				, "{$colonnesMja}"
				, "{$colonneTypeComptage}"
				, "{$colonneModeCalcul}"
				, "{$pcPLNmoinsi}"
				, "{$evaluationPL}"
				, "{$colonnesPcNuitAnnuel}"
				, "{$colonneIndiceFiabiliteMjaN}"};
		
		final int numeroChampInt = 69 + 8 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		final int numeroChampPlusDeuxInt = numeroChampInt + 2;
		final int numeroChampPlusTroisInt = numeroChampInt + 3;
		final int numeroChampPlusQuatreInt = numeroChampInt + 4;
		final int numeroChampPlusCinqInt = numeroChampInt + 5;
		final int numeroChampPlusSixInt = numeroChampInt + 6;
		final int numeroChampPlusSeptInt = numeroChampInt + 7;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String numeroChampPlusDeux = String.valueOf(numeroChampPlusDeuxInt);
		final String numeroChampPlusTrois = String.valueOf(numeroChampPlusTroisInt);
		final String numeroChampPlusQuatre = String.valueOf(numeroChampPlusQuatreInt);
		final String numeroChampPlusCinq = String.valueOf(numeroChampPlusCinqInt);
		final String numeroChampPlusSix = String.valueOf(numeroChampPlusSixInt);
		final String numeroChampPlusSept = String.valueOf(numeroChampPlusSeptInt);
		final String moinsi = "moins" + pN;
		final String mOINSI = "MOINS" + pN;
		final String nMoinsI = "n-" + pN;
		final String colonnesAnnee = "[" + (288 + 18 * (pN-1)) + "-" + (289 + 18 * (pN-1)) + "]";
		final String colonnesMja = "[" + (290 + 18 * (pN-1)) + "-" + (295 + 18 * (pN-1)) + "]";
		final String colonneTypeComptage = "[" + (296 + 18 * (pN-1)) + "]";
		final String colonneModeCalcul = "[" + (297 + 18 * (pN-1)) + "]";
		final String colonnesPcPl = "[" + (298 + 18 * (pN-1)) + "-" + (300 + 18 * (pN-1)) + "]";
		final String colonneEvaluationPL = "[" + (301 + 18 * (pN-1)) + "]";
		final String colonnesPcNuitAnnuel = "[" + (302 + 18 * (pN-1)) + "-" + (304 + 18 * (pN-1)) + "]";
		final String colonneIndiceFiabiliteMjaN = "[" + (305 + 18 * (pN-1)) + "]";
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, numeroChampPlusDeux
				, numeroChampPlusTrois
				, numeroChampPlusQuatre
				, numeroChampPlusCinq
				, numeroChampPlusSix
				, numeroChampPlusSept
				, moinsi
				, mOINSI
				, nMoinsI
				, colonnesAnnee
				, colonnesMja
				, colonneTypeComptage
				, colonneModeCalcul
				, colonnesPcPl
				, colonneEvaluationPL
				, colonnesPcNuitAnnuel
				, colonneIndiceFiabiliteMjaN};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de preferencesControlesAttributs(...).____________________
	
	
	
	/**
	 * .<br/>
	 *
	 * @param pN : int :le moins de l'année (1 pour année n-1).<br/>
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String preferencesRGAttributs(final int pN) 
			throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencesrgHIT/template_attributs_MJANmoinsx.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$pNumeroChamp + 2}"
				, "{$pNumeroChamp + 3}"
				, "{$pNumeroChamp + 4}"
				, "{$pNumeroChamp + 5}"
				, "{$pNumeroChamp + 6}"
				, "{$pNumeroChamp + 7}"
				, "{$moinsi}"
				, "{$MOINSi}"
				, "{$n-i}"
				, "{$colonnesAnnee}"
				, "{$colonnesMja}"
				, "{$colonneTypeComptage}"
				, "{$colonneModeCalcul}"
				, "{$pcPLNmoinsi}"
				, "{$evaluationPL}"
				, "{$colonnesPcNuitAnnuel}"
				, "{$colonneIndiceFiabiliteMjaN}"};
		
		final int numeroChampInt = 69 + 8 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		final int numeroChampPlusDeuxInt = numeroChampInt + 2;
		final int numeroChampPlusTroisInt = numeroChampInt + 3;
		final int numeroChampPlusQuatreInt = numeroChampInt + 4;
		final int numeroChampPlusCinqInt = numeroChampInt + 5;
		final int numeroChampPlusSixInt = numeroChampInt + 6;
		final int numeroChampPlusSeptInt = numeroChampInt + 7;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String numeroChampPlusDeux = String.valueOf(numeroChampPlusDeuxInt);
		final String numeroChampPlusTrois = String.valueOf(numeroChampPlusTroisInt);
		final String numeroChampPlusQuatre = String.valueOf(numeroChampPlusQuatreInt);
		final String numeroChampPlusCinq = String.valueOf(numeroChampPlusCinqInt);
		final String numeroChampPlusSix = String.valueOf(numeroChampPlusSixInt);
		final String numeroChampPlusSept = String.valueOf(numeroChampPlusSeptInt);
		final String moinsi = "moins" + pN;
		final String mOINSI = "MOINS" + pN;
		final String nMoinsI = "n-" + pN;
		final String colonnesAnnee = "[" + (288 + 18 * (pN-1)) + "-" + (289 + 18 * (pN-1)) + "]";
		final String colonnesMja = "[" + (290 + 18 * (pN-1)) + "-" + (295 + 18 * (pN-1)) + "]";
		final String colonneTypeComptage = "[" + (296 + 18 * (pN-1)) + "]";
		final String colonneModeCalcul = "[" + (297 + 18 * (pN-1)) + "]";
		final String colonnesPcPl = "[" + (298 + 18 * (pN-1)) + "-" + (300 + 18 * (pN-1)) + "]";
		final String colonneEvaluationPL = "[" + (301 + 18 * (pN-1)) + "]";
		final String colonnesPcNuitAnnuel = "[" + (302 + 18 * (pN-1)) + "-" + (304 + 18 * (pN-1)) + "]";
		final String colonneIndiceFiabiliteMjaN = "[" + (305 + 18 * (pN-1)) + "]";
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, numeroChampPlusDeux
				, numeroChampPlusTrois
				, numeroChampPlusQuatre
				, numeroChampPlusCinq
				, numeroChampPlusSix
				, numeroChampPlusSept
				, moinsi
				, mOINSI
				, nMoinsI
				, colonnesAnnee
				, colonnesMja
				, colonneTypeComptage
				, colonneModeCalcul
				, colonnesPcPl
				, colonneEvaluationPL
				, colonnesPcNuitAnnuel
				, colonneIndiceFiabiliteMjaN};
		
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
	 * @param pN : int :le moins de l'année (1 pour année n-1).<br/>
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String preferencesControlesAjouterMessages(final int pN) 
			throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_ajouterPropsEnDurMJANmoinsI.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$pNumeroChamp + 2}"
				, "{$pNumeroChamp + 3}"
				, "{$pNumeroChamp + 4}"
				, "{$pNumeroChamp + 5}"
				, "{$pNumeroChamp + 6}"
				, "{$pNumeroChamp + 7}"
				, "{$moinsi}"
				, "{$MOINSi}"
				, "{$n-i}"
				, "{$colonnesAnnee}"
				, "{$colonnesMja}"
				, "{$colonneTypeComptage}"
				, "{$colonneModeCalcul}"
				, "{$pcPLNmoinsi}"
				, "{$evaluationPL}"
				, "{$colonnesPcNuitAnnuel}"
				, "{$colonneIndiceFiabiliteMjaN}"};
		
		final int numeroChampInt = 69 + 8 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		final int numeroChampPlusDeuxInt = numeroChampInt + 2;
		final int numeroChampPlusTroisInt = numeroChampInt + 3;
		final int numeroChampPlusQuatreInt = numeroChampInt + 4;
		final int numeroChampPlusCinqInt = numeroChampInt + 5;
		final int numeroChampPlusSixInt = numeroChampInt + 6;
		final int numeroChampPlusSeptInt = numeroChampInt + 7;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String numeroChampPlusDeux = String.valueOf(numeroChampPlusDeuxInt);
		final String numeroChampPlusTrois = String.valueOf(numeroChampPlusTroisInt);
		final String numeroChampPlusQuatre = String.valueOf(numeroChampPlusQuatreInt);
		final String numeroChampPlusCinq = String.valueOf(numeroChampPlusCinqInt);
		final String numeroChampPlusSix = String.valueOf(numeroChampPlusSixInt);
		final String numeroChampPlusSept = String.valueOf(numeroChampPlusSeptInt);
		final String moinsi = "moins" + pN;
		final String mOINSI = "MOINS" + pN;
		final String nMoinsI = "n-" + pN;
		final String colonnesAnnee = "[" + (288 + 18 * (pN-1)) + "-" + (289 + 18 * (pN-1)) + "]";
		final String colonnesMja = "[" + (290 + 18 * (pN-1)) + "-" + (295 + 18 * (pN-1)) + "]";
		final String colonneTypeComptage = "[" + (296 + 18 * (pN-1)) + "]";
		final String colonneModeCalcul = "[" + (297 + 18 * (pN-1)) + "]";
		final String colonnesPcPl = "[" + (298 + 18 * (pN-1)) + "-" + (300 + 18 * (pN-1)) + "]";
		final String colonneEvaluationPL = "[" + (301 + 18 * (pN-1)) + "]";
		final String colonnesPcNuitAnnuel = "[" + (302 + 18 * (pN-1)) + "-" + (304 + 18 * (pN-1)) + "]";
		final String colonneIndiceFiabiliteMjaN = "[" + (305 + 18 * (pN-1)) + "]";
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, numeroChampPlusDeux
				, numeroChampPlusTrois
				, numeroChampPlusQuatre
				, numeroChampPlusCinq
				, numeroChampPlusSix
				, numeroChampPlusSept
				, moinsi
				, mOINSI
				, nMoinsI
				, colonnesAnnee
				, colonnesMja
				, colonneTypeComptage
				, colonneModeCalcul
				, colonnesPcPl
				, colonneEvaluationPL
				, colonnesPcNuitAnnuel
				, colonneIndiceFiabiliteMjaN};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de preferencesControlesAjouterMessages(...).__________________
	
	
	
	/**
	 * .<br/>
	 *
	 * @param pN : int :le moins de l'année (1 pour année n-1).<br/>
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String preferencesRGAjouterPropsEnDur(final int pN) 
			throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencesrgHIT/template_ajouterPropsEnDur_MJMNmoinsx.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$pNumeroChamp + 2}"
				, "{$pNumeroChamp + 3}"
				, "{$pNumeroChamp + 4}"
				, "{$pNumeroChamp + 5}"
				, "{$pNumeroChamp + 6}"
				, "{$pNumeroChamp + 7}"
				, "{$moinsi}"
				, "{$MOINSi}"
				, "{$n-i}"
				, "{$colonnesAnnee}"
				, "{$colonnesMja}"
				, "{$colonneTypeComptage}"
				, "{$colonneModeCalcul}"
				, "{$pcPLNmoinsi}"
				, "{$evaluationPL}"
				, "{$colonnesPcNuitAnnuel}"
				, "{$colonneIndiceFiabiliteMjaN}"};
		
		final int numeroChampInt = 69 + 8 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		final int numeroChampPlusDeuxInt = numeroChampInt + 2;
		final int numeroChampPlusTroisInt = numeroChampInt + 3;
		final int numeroChampPlusQuatreInt = numeroChampInt + 4;
		final int numeroChampPlusCinqInt = numeroChampInt + 5;
		final int numeroChampPlusSixInt = numeroChampInt + 6;
		final int numeroChampPlusSeptInt = numeroChampInt + 7;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String numeroChampPlusDeux = String.valueOf(numeroChampPlusDeuxInt);
		final String numeroChampPlusTrois = String.valueOf(numeroChampPlusTroisInt);
		final String numeroChampPlusQuatre = String.valueOf(numeroChampPlusQuatreInt);
		final String numeroChampPlusCinq = String.valueOf(numeroChampPlusCinqInt);
		final String numeroChampPlusSix = String.valueOf(numeroChampPlusSixInt);
		final String numeroChampPlusSept = String.valueOf(numeroChampPlusSeptInt);
		final String moinsi = "moins" + pN;
		final String mOINSI = "MOINS" + pN;
		final String nMoinsI = "n-" + pN;
		final String colonnesAnnee = "[" + (288 + 18 * (pN-1)) + "-" + (289 + 18 * (pN-1)) + "]";
		final String colonnesMja = "[" + (290 + 18 * (pN-1)) + "-" + (295 + 18 * (pN-1)) + "]";
		final String colonneTypeComptage = "[" + (296 + 18 * (pN-1)) + "]";
		final String colonneModeCalcul = "[" + (297 + 18 * (pN-1)) + "]";
		final String colonnesPcPl = "[" + (298 + 18 * (pN-1)) + "-" + (300 + 18 * (pN-1)) + "]";
		final String colonneEvaluationPL = "[" + (301 + 18 * (pN-1)) + "]";
		final String colonnesPcNuitAnnuel = "[" + (302 + 18 * (pN-1)) + "-" + (304 + 18 * (pN-1)) + "]";
		final String colonneIndiceFiabiliteMjaN = "[" + (305 + 18 * (pN-1)) + "]";
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, numeroChampPlusDeux
				, numeroChampPlusTrois
				, numeroChampPlusQuatre
				, numeroChampPlusCinq
				, numeroChampPlusSix
				, numeroChampPlusSept
				, moinsi
				, mOINSI
				, nMoinsI
				, colonnesAnnee
				, colonnesMja
				, colonneTypeComptage
				, colonneModeCalcul
				, colonnesPcPl
				, colonneEvaluationPL
				, colonnesPcNuitAnnuel
				, colonneIndiceFiabiliteMjaN};
		
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
	 * @param pN : int :le moins de l'année (1 pour année n-1).<br/>
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String preferencesControlesGettersSetters(final int pN) 
			throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencescontrolesHIT/template_getters_setters_MJANmoinsx.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$pNumeroChamp + 2}"
				, "{$pNumeroChamp + 3}"
				, "{$pNumeroChamp + 4}"
				, "{$pNumeroChamp + 5}"
				, "{$pNumeroChamp + 6}"
				, "{$pNumeroChamp + 7}"
				, "{$moinsi}"
				, "{$MOINSi}"
				, "{$n-i}"
				, "{$colonnesAnnee}"
				, "{$colonnesMja}"
				, "{$colonneTypeComptage}"
				, "{$colonneModeCalcul}"
				, "{$pcPLNmoinsi}"
				, "{$evaluationPL}"
				, "{$colonnesPcNuitAnnuel}"
				, "{$colonneIndiceFiabiliteMjaN}"};
		
		final int numeroChampInt = 69 + 8 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		final int numeroChampPlusDeuxInt = numeroChampInt + 2;
		final int numeroChampPlusTroisInt = numeroChampInt + 3;
		final int numeroChampPlusQuatreInt = numeroChampInt + 4;
		final int numeroChampPlusCinqInt = numeroChampInt + 5;
		final int numeroChampPlusSixInt = numeroChampInt + 6;
		final int numeroChampPlusSeptInt = numeroChampInt + 7;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String numeroChampPlusDeux = String.valueOf(numeroChampPlusDeuxInt);
		final String numeroChampPlusTrois = String.valueOf(numeroChampPlusTroisInt);
		final String numeroChampPlusQuatre = String.valueOf(numeroChampPlusQuatreInt);
		final String numeroChampPlusCinq = String.valueOf(numeroChampPlusCinqInt);
		final String numeroChampPlusSix = String.valueOf(numeroChampPlusSixInt);
		final String numeroChampPlusSept = String.valueOf(numeroChampPlusSeptInt);
		final String moinsi = "moins" + pN;
		final String mOINSI = "MOINS" + pN;
		final String nMoinsI = "n-" + pN;
		final String colonnesAnnee = "[" + (288 + 18 * (pN-1)) + "-" + (289 + 18 * (pN-1)) + "]";
		final String colonnesMja = "[" + (290 + 18 * (pN-1)) + "-" + (295 + 18 * (pN-1)) + "]";
		final String colonneTypeComptage = "[" + (296 + 18 * (pN-1)) + "]";
		final String colonneModeCalcul = "[" + (297 + 18 * (pN-1)) + "]";
		final String colonnesPcPl = "[" + (298 + 18 * (pN-1)) + "-" + (300 + 18 * (pN-1)) + "]";
		final String colonneEvaluationPL = "[" + (301 + 18 * (pN-1)) + "]";
		final String colonnesPcNuitAnnuel = "[" + (302 + 18 * (pN-1)) + "-" + (304 + 18 * (pN-1)) + "]";
		final String colonneIndiceFiabiliteMjaN = "[" + (305 + 18 * (pN-1)) + "]";
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, numeroChampPlusDeux
				, numeroChampPlusTrois
				, numeroChampPlusQuatre
				, numeroChampPlusCinq
				, numeroChampPlusSix
				, numeroChampPlusSept
				, moinsi
				, mOINSI
				, nMoinsI
				, colonnesAnnee
				, colonnesMja
				, colonneTypeComptage
				, colonneModeCalcul
				, colonnesPcPl
				, colonneEvaluationPL
				, colonnesPcNuitAnnuel
				, colonneIndiceFiabiliteMjaN};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de preferencesControlesGettersSetters(...).___________________
	
	
	
	/**
	 * .<br/>
	 *
	 * @param pN : int :le moins de l'année (1 pour année n-1).<br/>
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String preferencesRGGettersSetters(final int pN) 
			throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencesrgHIT/template_getters_setters_MJANmoinsx.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$pNumeroChamp + 2}"
				, "{$pNumeroChamp + 3}"
				, "{$pNumeroChamp + 4}"
				, "{$pNumeroChamp + 5}"
				, "{$pNumeroChamp + 6}"
				, "{$pNumeroChamp + 7}"
				, "{$moinsi}"
				, "{$MOINSi}"
				, "{$n-i}"
				, "{$colonnesAnnee}"
				, "{$colonnesMja}"
				, "{$colonneTypeComptage}"
				, "{$colonneModeCalcul}"
				, "{$pcPLNmoinsi}"
				, "{$evaluationPL}"
				, "{$colonnesPcNuitAnnuel}"
				, "{$colonneIndiceFiabiliteMjaN}"};
		
		final int numeroChampInt = 69 + 8 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		final int numeroChampPlusDeuxInt = numeroChampInt + 2;
		final int numeroChampPlusTroisInt = numeroChampInt + 3;
		final int numeroChampPlusQuatreInt = numeroChampInt + 4;
		final int numeroChampPlusCinqInt = numeroChampInt + 5;
		final int numeroChampPlusSixInt = numeroChampInt + 6;
		final int numeroChampPlusSeptInt = numeroChampInt + 7;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String numeroChampPlusDeux = String.valueOf(numeroChampPlusDeuxInt);
		final String numeroChampPlusTrois = String.valueOf(numeroChampPlusTroisInt);
		final String numeroChampPlusQuatre = String.valueOf(numeroChampPlusQuatreInt);
		final String numeroChampPlusCinq = String.valueOf(numeroChampPlusCinqInt);
		final String numeroChampPlusSix = String.valueOf(numeroChampPlusSixInt);
		final String numeroChampPlusSept = String.valueOf(numeroChampPlusSeptInt);
		final String moinsi = "moins" + pN;
		final String mOINSI = "MOINS" + pN;
		final String nMoinsI = "n-" + pN;
		final String colonnesAnnee = "[" + (288 + 18 * (pN-1)) + "-" + (289 + 18 * (pN-1)) + "]";
		final String colonnesMja = "[" + (290 + 18 * (pN-1)) + "-" + (295 + 18 * (pN-1)) + "]";
		final String colonneTypeComptage = "[" + (296 + 18 * (pN-1)) + "]";
		final String colonneModeCalcul = "[" + (297 + 18 * (pN-1)) + "]";
		final String colonnesPcPl = "[" + (298 + 18 * (pN-1)) + "-" + (300 + 18 * (pN-1)) + "]";
		final String colonneEvaluationPL = "[" + (301 + 18 * (pN-1)) + "]";
		final String colonnesPcNuitAnnuel = "[" + (302 + 18 * (pN-1)) + "-" + (304 + 18 * (pN-1)) + "]";
		final String colonneIndiceFiabiliteMjaN = "[" + (305 + 18 * (pN-1)) + "]";
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, numeroChampPlusDeux
				, numeroChampPlusTrois
				, numeroChampPlusQuatre
				, numeroChampPlusCinq
				, numeroChampPlusSix
				, numeroChampPlusSept
				, moinsi
				, mOINSI
				, nMoinsI
				, colonnesAnnee
				, colonnesMja
				, colonneTypeComptage
				, colonneModeCalcul
				, colonnesPcPl
				, colonneEvaluationPL
				, colonnesPcNuitAnnuel
				, colonneIndiceFiabiliteMjaN};
		
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
	 * @param pN : int :le moins de l'année (1 pour année n-1).<br/>
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String preferencesControlesTestJUnit(final int pN) 
			throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencesrgHIT/template_ajouterPropsEnDur_MJMNmoinsx.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$pNumeroChamp + 2}"
				, "{$pNumeroChamp + 3}"
				, "{$pNumeroChamp + 4}"
				, "{$pNumeroChamp + 5}"
				, "{$pNumeroChamp + 6}"
				, "{$pNumeroChamp + 7}"
				, "{$moinsi}"
				, "{$MOINSi}"
				, "{$n-i}"
				, "{$colonnesAnnee}"
				, "{$colonnesMja}"
				, "{$colonneTypeComptage}"
				, "{$colonneModeCalcul}"
				, "{$pcPLNmoinsi}"
				, "{$evaluationPL}"
				, "{$colonnesPcNuitAnnuel}"
				, "{$colonneIndiceFiabiliteMjaN}"};
		
		final int numeroChampInt = 69 + 8 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		final int numeroChampPlusDeuxInt = numeroChampInt + 2;
		final int numeroChampPlusTroisInt = numeroChampInt + 3;
		final int numeroChampPlusQuatreInt = numeroChampInt + 4;
		final int numeroChampPlusCinqInt = numeroChampInt + 5;
		final int numeroChampPlusSixInt = numeroChampInt + 6;
		final int numeroChampPlusSeptInt = numeroChampInt + 7;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String numeroChampPlusDeux = String.valueOf(numeroChampPlusDeuxInt);
		final String numeroChampPlusTrois = String.valueOf(numeroChampPlusTroisInt);
		final String numeroChampPlusQuatre = String.valueOf(numeroChampPlusQuatreInt);
		final String numeroChampPlusCinq = String.valueOf(numeroChampPlusCinqInt);
		final String numeroChampPlusSix = String.valueOf(numeroChampPlusSixInt);
		final String numeroChampPlusSept = String.valueOf(numeroChampPlusSeptInt);
		final String moinsi = "moins" + pN;
		final String mOINSI = "MOINS" + pN;
		final String nMoinsI = "n-" + pN;
		final String colonnesAnnee = "[" + (288 + 18 * (pN-1)) + "-" + (289 + 18 * (pN-1)) + "]";
		final String colonnesMja = "[" + (290 + 18 * (pN-1)) + "-" + (295 + 18 * (pN-1)) + "]";
		final String colonneTypeComptage = "[" + (296 + 18 * (pN-1)) + "]";
		final String colonneModeCalcul = "[" + (297 + 18 * (pN-1)) + "]";
		final String colonnesPcPl = "[" + (298 + 18 * (pN-1)) + "-" + (300 + 18 * (pN-1)) + "]";
		final String colonneEvaluationPL = "[" + (301 + 18 * (pN-1)) + "]";
		final String colonnesPcNuitAnnuel = "[" + (302 + 18 * (pN-1)) + "-" + (304 + 18 * (pN-1)) + "]";
		final String colonneIndiceFiabiliteMjaN = "[" + (305 + 18 * (pN-1)) + "]";
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, numeroChampPlusDeux
				, numeroChampPlusTrois
				, numeroChampPlusQuatre
				, numeroChampPlusCinq
				, numeroChampPlusSix
				, numeroChampPlusSept
				, moinsi
				, mOINSI
				, nMoinsI
				, colonnesAnnee
				, colonnesMja
				, colonneTypeComptage
				, colonneModeCalcul
				, colonnesPcPl
				, colonneEvaluationPL
				, colonnesPcNuitAnnuel
				, colonneIndiceFiabiliteMjaN};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de preferencesControlesTestJUnit(...).________________________

	
	
	/**
	 * .<br/>
	 *
	 * @param pN : int :le moins de l'année (1 pour année n-1).<br/>
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String preferencesRGTestJUnit(final int pN) 
			throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurgestionnairepreferencesrgHIT/template_TestJUnit_MJANmoinsx.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$pNumeroChamp + 2}"
				, "{$pNumeroChamp + 3}"
				, "{$pNumeroChamp + 4}"
				, "{$pNumeroChamp + 5}"
				, "{$pNumeroChamp + 6}"
				, "{$pNumeroChamp + 7}"
				, "{$moinsi}"
				, "{$MOINSi}"
				, "{$n-i}"
				, "{$colonnesAnnee}"
				, "{$colonnesMja}"
				, "{$colonneTypeComptage}"
				, "{$colonneModeCalcul}"
				, "{$pcPLNmoinsi}"
				, "{$evaluationPL}"
				, "{$colonnesPcNuitAnnuel}"
				, "{$colonneIndiceFiabiliteMjaN}"};
		
		final int numeroChampInt = 69 + 8 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		final int numeroChampPlusDeuxInt = numeroChampInt + 2;
		final int numeroChampPlusTroisInt = numeroChampInt + 3;
		final int numeroChampPlusQuatreInt = numeroChampInt + 4;
		final int numeroChampPlusCinqInt = numeroChampInt + 5;
		final int numeroChampPlusSixInt = numeroChampInt + 6;
		final int numeroChampPlusSeptInt = numeroChampInt + 7;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String numeroChampPlusDeux = String.valueOf(numeroChampPlusDeuxInt);
		final String numeroChampPlusTrois = String.valueOf(numeroChampPlusTroisInt);
		final String numeroChampPlusQuatre = String.valueOf(numeroChampPlusQuatreInt);
		final String numeroChampPlusCinq = String.valueOf(numeroChampPlusCinqInt);
		final String numeroChampPlusSix = String.valueOf(numeroChampPlusSixInt);
		final String numeroChampPlusSept = String.valueOf(numeroChampPlusSeptInt);
		final String moinsi = "moins" + pN;
		final String mOINSI = "MOINS" + pN;
		final String nMoinsI = "n-" + pN;
		final String colonnesAnnee = "[" + (288 + 18 * (pN-1)) + "-" + (289 + 18 * (pN-1)) + "]";
		final String colonnesMja = "[" + (290 + 18 * (pN-1)) + "-" + (295 + 18 * (pN-1)) + "]";
		final String colonneTypeComptage = "[" + (296 + 18 * (pN-1)) + "]";
		final String colonneModeCalcul = "[" + (297 + 18 * (pN-1)) + "]";
		final String colonnesPcPl = "[" + (298 + 18 * (pN-1)) + "-" + (300 + 18 * (pN-1)) + "]";
		final String colonneEvaluationPL = "[" + (301 + 18 * (pN-1)) + "]";
		final String colonnesPcNuitAnnuel = "[" + (302 + 18 * (pN-1)) + "-" + (304 + 18 * (pN-1)) + "]";
		final String colonneIndiceFiabiliteMjaN = "[" + (305 + 18 * (pN-1)) + "]";
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, numeroChampPlusDeux
				, numeroChampPlusTrois
				, numeroChampPlusQuatre
				, numeroChampPlusCinq
				, numeroChampPlusSix
				, numeroChampPlusSept
				, moinsi
				, mOINSI
				, nMoinsI
				, colonnesAnnee
				, colonnesMja
				, colonneTypeComptage
				, colonneModeCalcul
				, colonnesPcPl
				, colonneEvaluationPL
				, colonnesPcNuitAnnuel
				, colonneIndiceFiabiliteMjaN};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de preferencesRGTestJUnit(...)._______________________________

	
	
	/**
	 * .<br/>
	 *
	 * @param pN : int :le moins de l'année (1 pour année n-1).<br/>
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String valideurServiceValiderAttributs(final int pN) 
			throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurvalideurservice/template_validerAttributs_MJANmoinsx.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$pNumeroChamp + 2}"
				, "{$pNumeroChamp + 3}"
				, "{$pNumeroChamp + 4}"
				, "{$pNumeroChamp + 5}"
				, "{$pNumeroChamp + 6}"
				, "{$pNumeroChamp + 7}"
				, "{$moinsi}"
				, "{$MOINSi}"
				, "{$n-i}"
				, "{$colonnesAnnee}"
				, "{$colonnesMja}"
				, "{$colonneTypeComptage}"
				, "{$colonneModeCalcul}"
				, "{$pcPLNmoinsi}"
				, "{$evaluationPL}"
				, "{$colonnesPcNuitAnnuel}"
				, "{$colonneIndiceFiabiliteMjaN}"};
		
		final int numeroChampInt = 69 + 8 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		final int numeroChampPlusDeuxInt = numeroChampInt + 2;
		final int numeroChampPlusTroisInt = numeroChampInt + 3;
		final int numeroChampPlusQuatreInt = numeroChampInt + 4;
		final int numeroChampPlusCinqInt = numeroChampInt + 5;
		final int numeroChampPlusSixInt = numeroChampInt + 6;
		final int numeroChampPlusSeptInt = numeroChampInt + 7;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String numeroChampPlusDeux = String.valueOf(numeroChampPlusDeuxInt);
		final String numeroChampPlusTrois = String.valueOf(numeroChampPlusTroisInt);
		final String numeroChampPlusQuatre = String.valueOf(numeroChampPlusQuatreInt);
		final String numeroChampPlusCinq = String.valueOf(numeroChampPlusCinqInt);
		final String numeroChampPlusSix = String.valueOf(numeroChampPlusSixInt);
		final String numeroChampPlusSept = String.valueOf(numeroChampPlusSeptInt);
		final String moinsi = "moins" + pN;
		final String mOINSI = "MOINS" + pN;
		final String nMoinsI = "n-" + pN;
		final String colonnesAnnee = "[" + (288 + 18 * (pN-1)) + "-" + (289 + 18 * (pN-1)) + "]";
		final String colonnesMja = "[" + (290 + 18 * (pN-1)) + "-" + (295 + 18 * (pN-1)) + "]";
		final String colonneTypeComptage = "[" + (296 + 18 * (pN-1)) + "]";
		final String colonneModeCalcul = "[" + (297 + 18 * (pN-1)) + "]";
		final String colonnesPcPl = "[" + (298 + 18 * (pN-1)) + "-" + (300 + 18 * (pN-1)) + "]";
		final String colonneEvaluationPL = "[" + (301 + 18 * (pN-1)) + "]";
		final String colonnesPcNuitAnnuel = "[" + (302 + 18 * (pN-1)) + "-" + (304 + 18 * (pN-1)) + "]";
		final String colonneIndiceFiabiliteMjaN = "[" + (305 + 18 * (pN-1)) + "]";
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, numeroChampPlusDeux
				, numeroChampPlusTrois
				, numeroChampPlusQuatre
				, numeroChampPlusCinq
				, numeroChampPlusSix
				, numeroChampPlusSept
				, moinsi
				, mOINSI
				, nMoinsI
				, colonnesAnnee
				, colonnesMja
				, colonneTypeComptage
				, colonneModeCalcul
				, colonnesPcPl
				, colonneEvaluationPL
				, colonnesPcNuitAnnuel
				, colonneIndiceFiabiliteMjaN};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de valideurServiceValiderAttributs(...).______________________

	
	
	/**
	 * .<br/>
	 *
	 * @param pN : int :le moins de l'année (1 pour année n-1).<br/>
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String valideurServiceAjouterAValider(final int pN) 
			throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurvalideurservice/template_ajouterAValider_MJANmoinsx.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$pNumeroChamp + 2}"
				, "{$pNumeroChamp + 3}"
				, "{$pNumeroChamp + 4}"
				, "{$pNumeroChamp + 5}"
				, "{$pNumeroChamp + 6}"
				, "{$pNumeroChamp + 7}"
				, "{$moinsi}"
				, "{$MOINSi}"
				, "{$n-i}"
				, "{$colonnesAnnee}"
				, "{$colonnesMja}"
				, "{$colonneTypeComptage}"
				, "{$colonneModeCalcul}"
				, "{$pcPLNmoinsi}"
				, "{$evaluationPL}"
				, "{$colonnesPcNuitAnnuel}"
				, "{$colonneIndiceFiabiliteMjaN}"};
		
		final int numeroChampInt = 69 + 8 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		final int numeroChampPlusDeuxInt = numeroChampInt + 2;
		final int numeroChampPlusTroisInt = numeroChampInt + 3;
		final int numeroChampPlusQuatreInt = numeroChampInt + 4;
		final int numeroChampPlusCinqInt = numeroChampInt + 5;
		final int numeroChampPlusSixInt = numeroChampInt + 6;
		final int numeroChampPlusSeptInt = numeroChampInt + 7;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String numeroChampPlusDeux = String.valueOf(numeroChampPlusDeuxInt);
		final String numeroChampPlusTrois = String.valueOf(numeroChampPlusTroisInt);
		final String numeroChampPlusQuatre = String.valueOf(numeroChampPlusQuatreInt);
		final String numeroChampPlusCinq = String.valueOf(numeroChampPlusCinqInt);
		final String numeroChampPlusSix = String.valueOf(numeroChampPlusSixInt);
		final String numeroChampPlusSept = String.valueOf(numeroChampPlusSeptInt);
		final String moinsi = "moins" + pN;
		final String mOINSI = "MOINS" + pN;
		final String nMoinsI = "n-" + pN;
		final String colonnesAnnee = "[" + (288 + 18 * (pN-1)) + "-" + (289 + 18 * (pN-1)) + "]";
		final String colonnesMja = "[" + (290 + 18 * (pN-1)) + "-" + (295 + 18 * (pN-1)) + "]";
		final String colonneTypeComptage = "[" + (296 + 18 * (pN-1)) + "]";
		final String colonneModeCalcul = "[" + (297 + 18 * (pN-1)) + "]";
		final String colonnesPcPl = "[" + (298 + 18 * (pN-1)) + "-" + (300 + 18 * (pN-1)) + "]";
		final String colonneEvaluationPL = "[" + (301 + 18 * (pN-1)) + "]";
		final String colonnesPcNuitAnnuel = "[" + (302 + 18 * (pN-1)) + "-" + (304 + 18 * (pN-1)) + "]";
		final String colonneIndiceFiabiliteMjaN = "[" + (305 + 18 * (pN-1)) + "]";
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, numeroChampPlusDeux
				, numeroChampPlusTrois
				, numeroChampPlusQuatre
				, numeroChampPlusCinq
				, numeroChampPlusSix
				, numeroChampPlusSept
				, moinsi
				, mOINSI
				, nMoinsI
				, colonnesAnnee
				, colonnesMja
				, colonneTypeComptage
				, colonneModeCalcul
				, colonnesPcPl
				, colonneEvaluationPL
				, colonnesPcNuitAnnuel
				, colonneIndiceFiabiliteMjaN};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de valideurServiceAjouterAValider(...)._______________________

	
	
	/**
	 * .<br/>
	 *
	 * @param pN : int :le moins de l'année (1 pour année n-1).<br/>
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String valideurServiceTestsJUnit(final int pN) 
			throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurvalideurservice/template_TestsJUnit_MJANmoinsx.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$pNumeroChamp + 2}"
				, "{$pNumeroChamp + 3}"
				, "{$pNumeroChamp + 4}"
				, "{$pNumeroChamp + 5}"
				, "{$pNumeroChamp + 6}"
				, "{$pNumeroChamp + 7}"
				, "{$moinsi}"
				, "{$MOINSi}"
				, "{$n-i}"
				, "{$colonnesAnnee}"
				, "{$colonnesMja}"
				, "{$colonneTypeComptage}"
				, "{$colonneModeCalcul}"
				, "{$pcPLNmoinsi}"
				, "{$evaluationPL}"
				, "{$colonnesPcNuitAnnuel}"
				, "{$colonneIndiceFiabiliteMjaN}"};
		
		final int numeroChampInt = 69 + 8 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		final int numeroChampPlusDeuxInt = numeroChampInt + 2;
		final int numeroChampPlusTroisInt = numeroChampInt + 3;
		final int numeroChampPlusQuatreInt = numeroChampInt + 4;
		final int numeroChampPlusCinqInt = numeroChampInt + 5;
		final int numeroChampPlusSixInt = numeroChampInt + 6;
		final int numeroChampPlusSeptInt = numeroChampInt + 7;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String numeroChampPlusDeux = String.valueOf(numeroChampPlusDeuxInt);
		final String numeroChampPlusTrois = String.valueOf(numeroChampPlusTroisInt);
		final String numeroChampPlusQuatre = String.valueOf(numeroChampPlusQuatreInt);
		final String numeroChampPlusCinq = String.valueOf(numeroChampPlusCinqInt);
		final String numeroChampPlusSix = String.valueOf(numeroChampPlusSixInt);
		final String numeroChampPlusSept = String.valueOf(numeroChampPlusSeptInt);
		final String moinsi = "moins" + pN;
		final String mOINSI = "MOINS" + pN;
		final String nMoinsI = "n-" + pN;
		final String colonnesAnnee = "[" + (288 + 18 * (pN-1)) + "-" + (289 + 18 * (pN-1)) + "]";
		final String colonnesMja = "[" + (290 + 18 * (pN-1)) + "-" + (295 + 18 * (pN-1)) + "]";
		final String colonneTypeComptage = "[" + (296 + 18 * (pN-1)) + "]";
		final String colonneModeCalcul = "[" + (297 + 18 * (pN-1)) + "]";
		final String colonnesPcPl = "[" + (298 + 18 * (pN-1)) + "-" + (300 + 18 * (pN-1)) + "]";
		final String colonneEvaluationPL = "[" + (301 + 18 * (pN-1)) + "]";
		final String colonnesPcNuitAnnuel = "[" + (302 + 18 * (pN-1)) + "-" + (304 + 18 * (pN-1)) + "]";
		final String colonneIndiceFiabiliteMjaN = "[" + (305 + 18 * (pN-1)) + "]";
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, numeroChampPlusDeux
				, numeroChampPlusTrois
				, numeroChampPlusQuatre
				, numeroChampPlusCinq
				, numeroChampPlusSix
				, numeroChampPlusSept
				, moinsi
				, mOINSI
				, nMoinsI
				, colonnesAnnee
				, colonnesMja
				, colonneTypeComptage
				, colonneModeCalcul
				, colonnesPcPl
				, colonneEvaluationPL
				, colonnesPcNuitAnnuel
				, colonneIndiceFiabiliteMjaN};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de valideurServiceTestsJUnit(...).____________________________

	
	
	/**
	 * .<br/>
	 *
	 * @param pN : int :le moins de l'année (1 pour année n-1).<br/>
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String valideurServiceActiverToutesRG(final int pN) 
			throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/generateurvalideurservice/template_activerToutesRG_TestJUnit_MJANmoinsx.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$pNumeroChamp + 2}"
				, "{$pNumeroChamp + 3}"
				, "{$pNumeroChamp + 4}"
				, "{$pNumeroChamp + 5}"
				, "{$pNumeroChamp + 6}"
				, "{$pNumeroChamp + 7}"
				, "{$moinsi}"
				, "{$MOINSi}"
				, "{$n-i}"
				, "{$colonnesAnnee}"
				, "{$colonnesMja}"
				, "{$colonneTypeComptage}"
				, "{$colonneModeCalcul}"
				, "{$pcPLNmoinsi}"
				, "{$evaluationPL}"
				, "{$colonnesPcNuitAnnuel}"
				, "{$colonneIndiceFiabiliteMjaN}"};
		
		final int numeroChampInt = 69 + 8 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		final int numeroChampPlusDeuxInt = numeroChampInt + 2;
		final int numeroChampPlusTroisInt = numeroChampInt + 3;
		final int numeroChampPlusQuatreInt = numeroChampInt + 4;
		final int numeroChampPlusCinqInt = numeroChampInt + 5;
		final int numeroChampPlusSixInt = numeroChampInt + 6;
		final int numeroChampPlusSeptInt = numeroChampInt + 7;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String numeroChampPlusDeux = String.valueOf(numeroChampPlusDeuxInt);
		final String numeroChampPlusTrois = String.valueOf(numeroChampPlusTroisInt);
		final String numeroChampPlusQuatre = String.valueOf(numeroChampPlusQuatreInt);
		final String numeroChampPlusCinq = String.valueOf(numeroChampPlusCinqInt);
		final String numeroChampPlusSix = String.valueOf(numeroChampPlusSixInt);
		final String numeroChampPlusSept = String.valueOf(numeroChampPlusSeptInt);
		final String moinsi = "moins" + pN;
		final String mOINSI = "MOINS" + pN;
		final String nMoinsI = "n-" + pN;
		final String colonnesAnnee = "[" + (288 + 18 * (pN-1)) + "-" + (289 + 18 * (pN-1)) + "]";
		final String colonnesMja = "[" + (290 + 18 * (pN-1)) + "-" + (295 + 18 * (pN-1)) + "]";
		final String colonneTypeComptage = "[" + (296 + 18 * (pN-1)) + "]";
		final String colonneModeCalcul = "[" + (297 + 18 * (pN-1)) + "]";
		final String colonnesPcPl = "[" + (298 + 18 * (pN-1)) + "-" + (300 + 18 * (pN-1)) + "]";
		final String colonneEvaluationPL = "[" + (301 + 18 * (pN-1)) + "]";
		final String colonnesPcNuitAnnuel = "[" + (302 + 18 * (pN-1)) + "-" + (304 + 18 * (pN-1)) + "]";
		final String colonneIndiceFiabiliteMjaN = "[" + (305 + 18 * (pN-1)) + "]";
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, numeroChampPlusDeux
				, numeroChampPlusTrois
				, numeroChampPlusQuatre
				, numeroChampPlusCinq
				, numeroChampPlusSix
				, numeroChampPlusSept
				, moinsi
				, mOINSI
				, nMoinsI
				, colonnesAnnee
				, colonnesMja
				, colonneTypeComptage
				, colonneModeCalcul
				, colonnesPcPl
				, colonneEvaluationPL
				, colonnesPcNuitAnnuel
				, colonneIndiceFiabiliteMjaN};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de valideurServiceActiverToutesRG(...)._______________________

	
	
	/**
	 * .<br/>
	 *
	 * @param pN : int :le moins de l'année (1 pour année n-1).<br/>
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String gestionnaireRGattributs(final int pN) 
			throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/gestionnaireRG/template_Attributs_GestionnaireRG_MJANmoinsx.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$pNumeroChamp + 2}"
				, "{$pNumeroChamp + 3}"
				, "{$pNumeroChamp + 4}"
				, "{$pNumeroChamp + 5}"
				, "{$pNumeroChamp + 6}"
				, "{$pNumeroChamp + 7}"
				, "{$moinsi}"
				, "{$MOINSi}"
				, "{$n-i}"
				, "{$colonnesAnnee}"
				, "{$colonnesMja}"
				, "{$colonneTypeComptage}"
				, "{$colonneModeCalcul}"
				, "{$pcPLNmoinsi}"
				, "{$evaluationPL}"
				, "{$colonnesPcNuitAnnuel}"
				, "{$colonneIndiceFiabiliteMjaN}"};
		
		final int numeroChampInt = 69 + 8 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		final int numeroChampPlusDeuxInt = numeroChampInt + 2;
		final int numeroChampPlusTroisInt = numeroChampInt + 3;
		final int numeroChampPlusQuatreInt = numeroChampInt + 4;
		final int numeroChampPlusCinqInt = numeroChampInt + 5;
		final int numeroChampPlusSixInt = numeroChampInt + 6;
		final int numeroChampPlusSeptInt = numeroChampInt + 7;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String numeroChampPlusDeux = String.valueOf(numeroChampPlusDeuxInt);
		final String numeroChampPlusTrois = String.valueOf(numeroChampPlusTroisInt);
		final String numeroChampPlusQuatre = String.valueOf(numeroChampPlusQuatreInt);
		final String numeroChampPlusCinq = String.valueOf(numeroChampPlusCinqInt);
		final String numeroChampPlusSix = String.valueOf(numeroChampPlusSixInt);
		final String numeroChampPlusSept = String.valueOf(numeroChampPlusSeptInt);
		final String moinsi = "moins" + pN;
		final String mOINSI = "MOINS" + pN;
		final String nMoinsI = "n-" + pN;
		final String colonnesAnnee = "[" + (288 + 18 * (pN-1)) + "-" + (289 + 18 * (pN-1)) + "]";
		final String colonnesMja = "[" + (290 + 18 * (pN-1)) + "-" + (295 + 18 * (pN-1)) + "]";
		final String colonneTypeComptage = "[" + (296 + 18 * (pN-1)) + "]";
		final String colonneModeCalcul = "[" + (297 + 18 * (pN-1)) + "]";
		final String colonnesPcPl = "[" + (298 + 18 * (pN-1)) + "-" + (300 + 18 * (pN-1)) + "]";
		final String colonneEvaluationPL = "[" + (301 + 18 * (pN-1)) + "]";
		final String colonnesPcNuitAnnuel = "[" + (302 + 18 * (pN-1)) + "-" + (304 + 18 * (pN-1)) + "]";
		final String colonneIndiceFiabiliteMjaN = "[" + (305 + 18 * (pN-1)) + "]";
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, numeroChampPlusDeux
				, numeroChampPlusTrois
				, numeroChampPlusQuatre
				, numeroChampPlusCinq
				, numeroChampPlusSix
				, numeroChampPlusSept
				, moinsi
				, mOINSI
				, nMoinsI
				, colonnesAnnee
				, colonnesMja
				, colonneTypeComptage
				, colonneModeCalcul
				, colonnesPcPl
				, colonneEvaluationPL
				, colonnesPcNuitAnnuel
				, colonneIndiceFiabiliteMjaN};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de gestionnaireRGattributs(...).______________________________

	
	
	/**
	 * .<br/>
	 *
	 * @param pN : int :le moins de l'année (1 pour année n-1).<br/>
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String gestionnaireRGremplirMapRG(final int pN) 
			throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/gestionnaireRG/template_remplirMapRG_MJANmoinsx.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$pNumeroChamp + 2}"
				, "{$pNumeroChamp + 3}"
				, "{$pNumeroChamp + 4}"
				, "{$pNumeroChamp + 5}"
				, "{$pNumeroChamp + 6}"
				, "{$pNumeroChamp + 7}"
				, "{$moinsi}"
				, "{$MOINSi}"
				, "{$n-i}"
				, "{$colonnesAnnee}"
				, "{$colonnesMja}"
				, "{$colonneTypeComptage}"
				, "{$colonneModeCalcul}"
				, "{$pcPLNmoinsi}"
				, "{$evaluationPL}"
				, "{$colonnesPcNuitAnnuel}"
				, "{$colonneIndiceFiabiliteMjaN}"};
		
		final int numeroChampInt = 69 + 8 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		final int numeroChampPlusDeuxInt = numeroChampInt + 2;
		final int numeroChampPlusTroisInt = numeroChampInt + 3;
		final int numeroChampPlusQuatreInt = numeroChampInt + 4;
		final int numeroChampPlusCinqInt = numeroChampInt + 5;
		final int numeroChampPlusSixInt = numeroChampInt + 6;
		final int numeroChampPlusSeptInt = numeroChampInt + 7;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String numeroChampPlusDeux = String.valueOf(numeroChampPlusDeuxInt);
		final String numeroChampPlusTrois = String.valueOf(numeroChampPlusTroisInt);
		final String numeroChampPlusQuatre = String.valueOf(numeroChampPlusQuatreInt);
		final String numeroChampPlusCinq = String.valueOf(numeroChampPlusCinqInt);
		final String numeroChampPlusSix = String.valueOf(numeroChampPlusSixInt);
		final String numeroChampPlusSept = String.valueOf(numeroChampPlusSeptInt);
		final String moinsi = "moins" + pN;
		final String mOINSI = "MOINS" + pN;
		final String nMoinsI = "n-" + pN;
		final String colonnesAnnee = "[" + (288 + 18 * (pN-1)) + "-" + (289 + 18 * (pN-1)) + "]";
		final String colonnesMja = "[" + (290 + 18 * (pN-1)) + "-" + (295 + 18 * (pN-1)) + "]";
		final String colonneTypeComptage = "[" + (296 + 18 * (pN-1)) + "]";
		final String colonneModeCalcul = "[" + (297 + 18 * (pN-1)) + "]";
		final String colonnesPcPl = "[" + (298 + 18 * (pN-1)) + "-" + (300 + 18 * (pN-1)) + "]";
		final String colonneEvaluationPL = "[" + (301 + 18 * (pN-1)) + "]";
		final String colonnesPcNuitAnnuel = "[" + (302 + 18 * (pN-1)) + "-" + (304 + 18 * (pN-1)) + "]";
		final String colonneIndiceFiabiliteMjaN = "[" + (305 + 18 * (pN-1)) + "]";
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, numeroChampPlusDeux
				, numeroChampPlusTrois
				, numeroChampPlusQuatre
				, numeroChampPlusCinq
				, numeroChampPlusSix
				, numeroChampPlusSept
				, moinsi
				, mOINSI
				, nMoinsI
				, colonnesAnnee
				, colonnesMja
				, colonneTypeComptage
				, colonneModeCalcul
				, colonnesPcPl
				, colonneEvaluationPL
				, colonnesPcNuitAnnuel
				, colonneIndiceFiabiliteMjaN};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de gestionnaireRGremplirMapRG(...).___________________________

	
	
	/**
	 * .<br/>
	 *
	 * @param pN : int :le moins de l'année (1 pour année n-1).<br/>
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private static String gestionnaireRGputMapRG(final int pN) 
			throws Exception {
		
		// Instanciation d'un GestionnaireTemplates.
		final IGestionnaireTemplates gestionnaireTemplates = new GestionnaireTemplates();
		
		// récupération du template grâce à son chemin relatif par rapport aux resources internes.
		final String cheminRelatifTemplate = "templates/apptechnic/configurationmanagers/gestionnairesrg/generationcode/gestionnaireRG/template_putMapRG_MJANmoinsx.txt";
		
		// variables incorporées dans le template à lire.
		final String[] variables = {"{$pNumeroChamp}"
				, "{$pNumeroChamp + 1}"
				, "{$pNumeroChamp + 2}"
				, "{$pNumeroChamp + 3}"
				, "{$pNumeroChamp + 4}"
				, "{$pNumeroChamp + 5}"
				, "{$pNumeroChamp + 6}"
				, "{$pNumeroChamp + 7}"
				, "{$moinsi}"
				, "{$MOINSi}"
				, "{$n-i}"
				, "{$colonnesAnnee}"
				, "{$colonnesMja}"
				, "{$colonneTypeComptage}"
				, "{$colonneModeCalcul}"
				, "{$pcPLNmoinsi}"
				, "{$evaluationPL}"
				, "{$colonnesPcNuitAnnuel}"
				, "{$colonneIndiceFiabiliteMjaN}"};
		
		final int numeroChampInt = 69 + 8 * (pN - 1);
		final int numeroChampPlusUnInt = numeroChampInt + 1;
		final int numeroChampPlusDeuxInt = numeroChampInt + 2;
		final int numeroChampPlusTroisInt = numeroChampInt + 3;
		final int numeroChampPlusQuatreInt = numeroChampInt + 4;
		final int numeroChampPlusCinqInt = numeroChampInt + 5;
		final int numeroChampPlusSixInt = numeroChampInt + 6;
		final int numeroChampPlusSeptInt = numeroChampInt + 7;
		
		final String numeroChamp = String.valueOf(numeroChampInt);
		final String numeroChampPlusUn = String.valueOf(numeroChampPlusUnInt);
		final String numeroChampPlusDeux = String.valueOf(numeroChampPlusDeuxInt);
		final String numeroChampPlusTrois = String.valueOf(numeroChampPlusTroisInt);
		final String numeroChampPlusQuatre = String.valueOf(numeroChampPlusQuatreInt);
		final String numeroChampPlusCinq = String.valueOf(numeroChampPlusCinqInt);
		final String numeroChampPlusSix = String.valueOf(numeroChampPlusSixInt);
		final String numeroChampPlusSept = String.valueOf(numeroChampPlusSeptInt);
		final String moinsi = "moins" + pN;
		final String mOINSI = "MOINS" + pN;
		final String nMoinsI = "n-" + pN;
		final String colonnesAnnee = "[" + (288 + 18 * (pN-1)) + "-" + (289 + 18 * (pN-1)) + "]";
		final String colonnesMja = "[" + (290 + 18 * (pN-1)) + "-" + (295 + 18 * (pN-1)) + "]";
		final String colonneTypeComptage = "[" + (296 + 18 * (pN-1)) + "]";
		final String colonneModeCalcul = "[" + (297 + 18 * (pN-1)) + "]";
		final String colonnesPcPl = "[" + (298 + 18 * (pN-1)) + "-" + (300 + 18 * (pN-1)) + "]";
		final String colonneEvaluationPL = "[" + (301 + 18 * (pN-1)) + "]";
		final String colonnesPcNuitAnnuel = "[" + (302 + 18 * (pN-1)) + "-" + (304 + 18 * (pN-1)) + "]";
		final String colonneIndiceFiabiliteMjaN = "[" + (305 + 18 * (pN-1)) + "]";
		
		// valeurs à substituer aux variables dépendant d'une Locale paramétrée pLocale.
		final String[] substituants = {numeroChamp
				, numeroChampPlusUn
				, numeroChampPlusDeux
				, numeroChampPlusTrois
				, numeroChampPlusQuatre
				, numeroChampPlusCinq
				, numeroChampPlusSix
				, numeroChampPlusSept
				, moinsi
				, mOINSI
				, nMoinsI
				, colonnesAnnee
				, colonnesMja
				, colonneTypeComptage
				, colonneModeCalcul
				, colonnesPcPl
				, colonneEvaluationPL
				, colonnesPcNuitAnnuel
				, colonneIndiceFiabiliteMjaN};
		
		// Récupération du template lu/substitué sous forme de String.
		final String resultat 
			= gestionnaireTemplates
				.fournirTemplateSubstitueSousFormeString(
						cheminRelatifTemplate, variables, substituants);
		
		return resultat;

	} // Fin de gestionnaireRGputMapRG(...)._______________________________

	
	
	/**
	 * point d'entrée de l'application.<br/>
	 *
	 * @param pArgs : String[] :  .<br/>
	 * 
	 * @throws Exception 
	 */
	public static void main(
			final String... pArgs) throws Exception {
		
		for (int i = 2; i < 6; i++) {
			System.out.println(gestionnaireRGputMapRG(i));
		}

	} // Fin de main(...)._________________________________________________

	
}
