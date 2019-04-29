package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesnomenclatures;

import java.io.File;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierRepertoireRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideRunTimeException;


/**
 * CLASSE ConfigurationNomenclaturesDarwinManager :<br/>
 * Classe UTILITAIRE 
 * chargée de gérer la configuration des 
 * NOMENCLATURES DES CHAMPS A NOMENCLATURE DU FICHIER DARWIN_CSV.<br/>
 * Met à disposition de l'ensemble de l'application 
 * des <b>Singletons</b>.<br/>
 * <ul>
 * <li>La méthode getCheminNomenclaturesDarwinUtf8 fournit un Singleton 
 * du chemin vers les nomenclatures encodées en UTF-8 
 * des champs à nomenclature 
 * du fichier DARWIN.</li>
 * <li>Les méthodes getNomNomenclatureXXX fournissent un singleton  
 * du nom du fichier de nomenclature du champXXX 
 * encodé en UTF-8 dans le DARWIN.</li>
 * <li>Les méthodes getFichierNomenclatureXXX fournissent un singleton  
 * du fichier de nomenclature du champXXX 
 * encodé en UTF-8 dans le DARWIN.</li>
 * </ul>
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
 * @since 16 avr. 2016
 *
 */
public final class ConfigurationNomenclaturesDarwinManager {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * CLASSE_CONFIGURATIONNOMENCLATURESDARWIN : String :<br/>
	 * "Classe ConfigurationNomenclaturesDarwinManager".<br/>
	 */
	public static final String CLASSE_CONFIGURATIONNOMENCLATURESDARWIN 
		= "Classe ConfigurationNomenclaturesDarwinManager";
	
	
	/**
	 * METHODE_GET_CHEMINNOMENCLATURES_DARWIN : String :<br/>
	 * "Méthode getCheminNomenclaturesDarwinUtf8".<br/>
	 */
	public static final String METHODE_GET_CHEMINNOMENCLATURES_DARWIN 
		= "Méthode getCheminNomenclaturesDarwinUtf8";

	
	/**
	 * METHODE_GET_NOMNOMENCLATURE_DARWIN_SENS : String :<br/>
	 * "Méthode getNomNomenclatureDarwinSens()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_DARWIN_SENS 
		= "Méthode getNomNomenclatureDarwinSens()";
	
	/**
	 * METHODE_GET_NOMNOMENCLATURE_DARWIN_TYPECOMPTAGE : String :<br/>
	 * "Méthode getNomNomenclatureDarwinTypeComptage()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_DARWIN_TYPECOMPTAGE 
		= "Méthode getNomNomenclatureDarwinTypeComptage()";

	
	/**
	 * METHODE_GET_NOMNOMENCLATURE_DARWIN_CLASSEMENTROUTE : String :<br/>
	 * "Méthode getNomNomenclatureDarwinClassementRoute()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_DARWIN_CLASSEMENTROUTE 
		= "Méthode getNomNomenclatureDarwinClassementRoute()";


	
	//*****************************************************************/
	//**************************** BOM_UTF-8 **************************/
	//*****************************************************************/
	/**
	 * BOM_UTF : char :<br/>
	 * BOM UTF-8 pour forcer Excel 2010 à lire en UTF-8.<br/>
	 */
	public static final char BOM_UTF_8 = '\uFEFF';

	
	//*****************************************************************/
	//**************************** SEPARATEURS ************************/
	//*****************************************************************/
	/**
	 * SEP_PV : String :<br/>
	 * Séparateur pour les CSV ";".<br/>
	 */
	public static final String SEP_PV = ";";

    
	/**
	 * SEPARATEUR_MOINS_AERE : String :<br/>
	 * " - ".<br/>
	 */
	public static final String SEPARATEUR_MOINS_AERE = " - ";
	
	
	/**
	 * UNDERSCORE : String :<br/>
	 * "_".<br/>
	 */
	public static final String UNDERSCORE = "_";


	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/	
	/**
	 * NEWLINE : String :<br/>
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");

	
	//*****************************************************************/
	//**************************** LOCALE *****************************/
	//*****************************************************************/
	/**
	 * LOCALE_FR : Locale : <br/>
	 * Locale France.<br/>
	 */
	public static final Locale LOCALE_FR = new Locale("fr", "FR");

	
	// ******************************************************************
	// NOMENCLATURES.****************************************************
	// ******************************************************************
		
	// DARWIN.*********************
	
	/**
	 * cheminNomenclaturesDarwinUtf8 : String :<br/>
	 * Chemin des nomenclatures en UTF-8 des champs pour les DARWIN
	 * stocké dans application.properties.<br/>
	 * ".\\ressources\\Nomenclatures\\Darwin\\Nomenclatures en UTF-8\\".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.darwin.utf8".<br/>
	 */
	private static transient String cheminNomenclaturesDarwinUtf8;
	
	
	/**
	 * nomNomenclatureDarwinSens : String :<br/>
	 * Nom du fichier de nomenclature du sens pour les DARWIN en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Sens_Darwin_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sens.darwin".<br/>
	 */
	private static transient String nomNomenclatureDarwinSens;

	
	/**
	 * fichierNomenclatureDarwinSensUtf8 : File :<br/>
	 * Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le SENS
	 * dans un DARWIN.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Darwin\\Nomenclatures en UTF-8\\
	 * 2014-07-15_Nomenclature_Sens_Darwin_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureDarwinSensUtf8;


	/**
	 * nomNomenclatureDarwinTypeComptage : String :<br/>
	 * Nom du fichier de nomenclature du type de comptage 
	 * pour les DARWIN en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Type_Comptage_Darwin_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typecomptage.darwin"<br/>
	 */
	private static transient String nomNomenclatureDarwinTypeComptage;

		
	/**
	 * fichierNomenclatureDarwinTypeComptageUtf8 : File :<br/>
	 * Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le TYPE DE COMPTAGE 
	 * dans un DARWIN.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Darwin\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Type_Comptage_Darwin_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureDarwinTypeComptageUtf8;
	

	/**
	 * nomNomenclatureDarwinClassementRoute : String :<br/>
	 * Nom du fichier de nomenclature du classement de la route 
	 * pour les DARWIN en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Classement_Route_Darwin_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classementroute.darwin"<br/>
	 */
	private static transient String nomNomenclatureDarwinClassementRoute;

		
	/**
	 * fichierNomenclatureDarwinClassementRouteUtf8 : File :<br/>
	 * Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le CLASSEMENT DE LA ROUTE 
	 * dans un DARWIN.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Darwin\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Classement_Route_Darwin_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureDarwinClassementRouteUtf8;
	

	/**
	 * rapportConfigurationCsv : String :<br/>
	 * Rapport du chargement de la configuration au format csv.<br/>
	 * Le rapport est null si il n'y a eu aucun 
	 * problème d'initialisation de l'application.<br/>
	 */
	private static transient String rapportConfigurationCsv;

	
	/**
	 * messageIndividuelRapport : String :<br/>
	 * Message pour le Rapport du chargement de la configuration au format csv 
	 * généré par chaque méthode individuellement.<br/>
	 */
	private static transient String messageIndividuelRapport;
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(ConfigurationNomenclaturesDarwinManager.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR ConfigurationNomenclaturesDarwinManager() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour interdire l'instanciation.<br/>
	 * <br/>
	 */
	private ConfigurationNomenclaturesDarwinManager() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________

	

	/**
	 * method getCheminNomenclaturesDarwinUtf8() :<br/>
	 * Getter du Chemin des nomenclatures en UTF-8 des champs pour les DARWIN
	 * stocké dans application.properties.<br/>
	 * ".\\ressources\\Nomenclatures\\Darwin\\Nomenclatures en UTF-8\\".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirCheminNomenclaturesDarwinUtf8EnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.chemin.darwin.utf8".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirCheminNomenclaturesDarwinUtf8EnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirCheminNomenclaturesDarwinUtf8EnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return cheminNomenclaturesDarwinUtf8 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getCheminNomenclaturesDarwinUtf8() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (cheminNomenclaturesDarwinUtf8 == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleCheminNomenclaturesDarwinUtf8());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_CHEMINNOMENCLATURES_DARWIN,
								fournirCleCheminNomenclaturesDarwinUtf8(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							cheminNomenclaturesDarwinUtf8 
								= fournirCheminNomenclaturesDarwinUtf8EnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							cheminNomenclaturesDarwinUtf8 = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_CHEMINNOMENCLATURES_DARWIN,
								fournirCleCheminNomenclaturesDarwinUtf8(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						cheminNomenclaturesDarwinUtf8 
							= fournirCheminNomenclaturesDarwinUtf8EnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					cheminNomenclaturesDarwinUtf8 
						= fournirCheminNomenclaturesDarwinUtf8EnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (cheminNomenclaturesDarwinUtf8 == null)._________

			return cheminNomenclaturesDarwinUtf8;

		} // Fin de synchronized.________________________________________

	} // Fin de getCheminNomenclaturesDarwinUtf8().___________________________

	
	
	/**
	 * method fournirCleCheminNomenclaturesDarwinUtf8() :<br/>
	 * clé du chemin des chemins des nomenclatures en UTF-8 
	 * des DARWIN dans 
	 * application_fr_FR.properties.<br/>
	 * ".\\ressources\\Nomenclatures\\Darwin\\Nomenclatures en UTF-8\\".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.darwin.utf8".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.chemin.darwin.utf8".<br/>
	 */
	private static String fournirCleCheminNomenclaturesDarwinUtf8() {
		return "application.repertoire.ressources.nomenclatures.chemin.darwin.utf8";
	} // Fin de fournirCleCheminNomenclaturesDarwinUtf8().____________________
	

	
	/**
	 * method fournirCheminNomenclaturesDarwinUtf8EnDur() :<br/>
	 * Fournit une valeur stockée en dur 
	 * dans la classe pour chemins des nomenclatures en UTF-8 
	 * des DARWIN.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Darwin\\Nomenclatures en UTF-8\\".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * ".\\ressources\\Nomenclatures\\Darwin\\Nomenclatures en UTF-8\\".<br/>
	 */
	private static String fournirCheminNomenclaturesDarwinUtf8EnDur() {
		return "ressources/Nomenclatures/Darwin/Nomenclatures en UTF-8";
	} // Fin de fournirCheminNomenclaturesDarwinUtf8EnDur().__________________
	


	/**
	 * method getNomNomenclatureDarwinSens() :<br/>
	 * Getter du Nom du fichier de nomenclature 
	 * du SENS 
	 * pour les DARWIN en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Sens_Darwin_Utf8.csv".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureDarwinSensEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.sens.darwin".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinSensEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinSensEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureDarwinSens : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureDarwinSens() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureDarwinSens == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleNomNomenclatureDarwinSens());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_SENS,
								fournirCleNomNomenclatureDarwinSens(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							nomNomenclatureDarwinSens 
								= fournirNomNomenclatureDarwinSensEnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							nomNomenclatureDarwinSens = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_SENS,
								fournirCleNomNomenclatureDarwinSens(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						nomNomenclatureDarwinSens 
							= fournirNomNomenclatureDarwinSensEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureDarwinSens 
						= fournirNomNomenclatureDarwinSensEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureDarwinSens == null)._________

			return nomNomenclatureDarwinSens;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureDarwinSens()._______________________________


	
	/**
	 * method fournirCleNomNomenclatureDarwinSens() :<br/>
	 * clé du nom de la nomenclature en UTF-8 
	 * du SENS 
	 * dans le DARWIN 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_Sens_Darwin_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sens.darwin".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.sens.darwin".<br/>
	 */
	private static String fournirCleNomNomenclatureDarwinSens() {
		return "application.repertoire.ressources.nomenclatures.sens.darwin";
	} // Fin de fournirCleNomNomenclatureDarwinSens().________________________
	

	
	/**
	 * method fournirNomNomenclatureDarwinSensEnDur() :<br/>
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le Nom du fichier de nomenclature en UTF-8 
	 * concernant le SENS 
	 * dans un DARWIN.<br/>
	 * <br/>
	 *
	 * @return : String : "2014-07-15_Nomenclature_Sens_Darwin_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureDarwinSensEnDur() {
		return "2014-07-15_Nomenclature_Sens_Darwin_Utf8.csv";
	} // Fin de fournirNomNomenclatureDarwinSensEnDur().______________________

	
	
	/**
	 * method getFichierNomenclatureDarwinSensUtf8() :<br/>
	 * Fournit le Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le SENS
	 * dans un DARWIN.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Darwin\\Nomenclatures en UTF-8\\
	 * 2014-07-15_Nomenclature_Sens_Darwin_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureDarwinSensUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureDarwinSensUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesDarwinManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureDarwinSensUtf8 == null) {
								
				fichierNomenclatureDarwinSensUtf8 
				= new File(getCheminNomenclaturesDarwinUtf8() 
						+ getNomNomenclatureDarwinSens());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureDarwinSensUtf8
						, "Méthode getFichierNomenclatureDarwinSensUtf8()");
			}
			
			return fichierNomenclatureDarwinSensUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureDarwinSensUtf8()._______________________
	

	
	/**
	 * method getNomNomenclatureDarwinTypeComptage() :<br/>
	 * Getter du Nom du fichier de nomenclature 
	 * du TYPE DE COMPTAGE 
	 * pour les DARWIN en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Type_Comptage_Darwin_Utf8.csv".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureDarwinTypeComptageEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.typecomptage.darwin".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinTypeComptageEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinTypeComptageEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureDarwinTypeComptage : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureDarwinTypeComptage() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureDarwinTypeComptage == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleNomNomenclatureDarwinTypeComptage());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_TYPECOMPTAGE,
								fournirCleNomNomenclatureDarwinTypeComptage(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							nomNomenclatureDarwinTypeComptage 
								= fournirNomNomenclatureDarwinTypeComptageEnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							nomNomenclatureDarwinTypeComptage = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_TYPECOMPTAGE,
								fournirCleNomNomenclatureDarwinTypeComptage(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						nomNomenclatureDarwinTypeComptage 
							= fournirNomNomenclatureDarwinTypeComptageEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureDarwinTypeComptage 
						= fournirNomNomenclatureDarwinTypeComptageEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureDarwinTypeComptage == null)._________

			return nomNomenclatureDarwinTypeComptage;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureDarwinTypeComptage()._______________________


	
	/**
	 * method fournirCleNomNomenclatureDarwinTypeComptage() :<br/>
	 * clé du nom de la nomenclature en UTF-8 
	 * de TYPE DE COMPTAGE 
	 * dans le DARWIN 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_Type_Comptage_Darwin_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typecomptage.darwin".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.typecomptage.darwin".<br/>
	 */
	private static String fournirCleNomNomenclatureDarwinTypeComptage() {
		return "application.repertoire.ressources.nomenclatures.typecomptage.darwin";
	} // Fin de fournirCleNomNomenclatureDarwinTypeComptage().________________
	

	
	/**
	 * method fournirNomNomenclatureDarwinTypeComptageEnDur() :<br/>
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le Nom du fichier de nomenclature en UTF-8 
	 * concernant le TYPE DE COMPTAGE
	 * dans un DARWIN.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_Type_Comptage_Darwin_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureDarwinTypeComptageEnDur() {
		return "2014-07-15_Nomenclature_Type_Comptage_Darwin_Utf8.csv";
	} // Fin de fournirNomNomenclatureDarwinTypeComptageEnDur().______________


	
	/**
	 * method getFichierNomenclatureDarwinTypeComptageUtf8() :<br/>
	 * Fournit le Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le TYPE DE COMPTAGE 
	 * dans un DARWIN.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Darwin\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Type_Comptage_Darwin_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureDarwinTypeComptageUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureDarwinTypeComptageUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesDarwinManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureDarwinTypeComptageUtf8 == null) {
								
				fichierNomenclatureDarwinTypeComptageUtf8 
				= new File(getCheminNomenclaturesDarwinUtf8() 
						+ getNomNomenclatureDarwinTypeComptage());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureDarwinTypeComptageUtf8
						, "Méthode getFichierNomenclatureDarwinTypeComptageUtf8()");
			}
			
			return fichierNomenclatureDarwinTypeComptageUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureDarwinTypeComptageUtf8()._______________
	

	
	/**
	 * method getNomNomenclatureDarwinClassementRoute() :<br/>
	 * Getter du Nom du fichier de nomenclature 
	 * du CLASSEMENT DE LA ROUTE 
	 * pour les DARWIN en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Classement_Route_Darwin_Utf8.csv".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureDarwinClassementRouteEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.classementroute.darwin".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinClassementRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinClassementRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureDarwinClassementRoute : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureDarwinClassementRoute() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureDarwinClassementRoute == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleNomNomenclatureDarwinClassementRoute());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_CLASSEMENTROUTE,
								fournirCleNomNomenclatureDarwinClassementRoute(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							nomNomenclatureDarwinClassementRoute 
								= fournirNomNomenclatureDarwinClassementRouteEnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							nomNomenclatureDarwinClassementRoute = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_CLASSEMENTROUTE,
								fournirCleNomNomenclatureDarwinClassementRoute(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						nomNomenclatureDarwinClassementRoute 
							= fournirNomNomenclatureDarwinClassementRouteEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureDarwinClassementRoute 
						= fournirNomNomenclatureDarwinClassementRouteEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureDarwinClassementRoute == null)._________

			return nomNomenclatureDarwinClassementRoute;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureDarwinClassementRoute().____________________



	/**
	 * method fournirCleNomNomenclatureDarwinClassementRoute() :<br/>
	 * clé du nom de la nomenclature en UTF-8 
	 * du CLASSEMENT DE LA ROUTE
	 * dans le DARWIN 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_Classement_Route_Darwin_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classementroute.darwin".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.typecomptage.darwin".<br/>
	 */
	private static String fournirCleNomNomenclatureDarwinClassementRoute() {
		return "application.repertoire.ressources.nomenclatures.classementroute.darwin";
	} // Fin de fournirCleNomNomenclatureDarwinClassementRoute()._____________
	

	
	/**
	 * method fournirNomNomenclatureDarwinClassementRouteEnDur() :<br/>
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le Nom du fichier de nomenclature en UTF-8 
	 * concernant le CLASSEMENT DE LA ROUTE
	 * dans un DARWIN.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_Classement_Route_Darwin_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureDarwinClassementRouteEnDur() {
		return "2014-07-15_Nomenclature_Classement_Route_Darwin_Utf8.csv";
	} // Fin de fournirNomNomenclatureDarwinClassementRouteEnDur().___________


	
	/**
	 * method getFichierNomenclatureDarwinClassementRouteUtf8() :<br/>
	 * Fournit le Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le CLASSEMENT DE LA ROUTE 
	 * dans un DARWIN.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Darwin\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Classement_Route_Darwin_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureDarwinClassementRouteUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureDarwinClassementRouteUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesDarwinManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureDarwinClassementRouteUtf8 == null) {
								
				fichierNomenclatureDarwinClassementRouteUtf8 
				= new File(getCheminNomenclaturesDarwinUtf8() 
						+ getNomNomenclatureDarwinClassementRoute());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureDarwinClassementRouteUtf8
						, "Méthode getFichierNomenclatureDarwinClassementRouteUtf8()");
			}
			
			return fichierNomenclatureDarwinClassementRouteUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureDarwinClassementRouteUtf8()._______________
	


	/**
	 * method getRapportConfigurationCsv() :<br/>
	 * Getter du Rapport du chargement de la configuration au format csv.<br/>
	 * <br/>
	 * - Le rapport est null si il n'y a eu aucun 
	 * problème d'initialisation de l'application.<br/>
	 * <br/>
	 *
	 * @return rapportConfigurationCsv : String.<br/>
	 */
	public static String getRapportConfigurationCsv() {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinManager.class) {
			
			return rapportConfigurationCsv;
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de getRapportConfigurationCsv().______________________________


	
	/**
	 * method getMessageIndividuelRapport() :<br/>
	 * Getter du Message pour le 
	 * Rapport du chargement de la configuration au format csv 
	 * généré par chaque méthode individuellement.<br/>
	 * <br/>
	 *
	 * @return messageIndividuelRapport : String.<br/>
	 */
	public static String getMessageIndividuelRapport() {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinManager.class) {
			return messageIndividuelRapport;
		} // Fin de synchronized.________________________________________
		
	} // Fin de getMessageIndividuelRapport()._____________________________


	
	/**
	 * method creerMessageManqueCle(
	 * String pMethode
	 * , String pCle
	 * , ResourceBundle pBundle) :<br/>
	 * Crée un message pour le LOG et le rapport de configuration csv 
	 * si une clé est absente dans un ResourceBundle.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * "Classe ConfigurationApplicationManager 
	 * - Méthode getStatsActivees() 
	 * - La clé 'abstractdao.statsactivees' 
	 * n'existe pas dans messagestechniquesfr_FR.properties".<br/>
	 * <br/>
	 *
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * @param pCle : String : Clé dans le ResourceBundle.<br/>
	 * @param pBundle : ResourceBundle.<br/>
	 * 
	 * @return : String : message pour le LOG 
	 * et le rapport de configuration csv.<br/>
	 */
	private static String creerMessageManqueCle(
			final String pMethode
			 	, final String pCle
			 		, final ResourceBundle pBundle) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESDARWIN);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMethode);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append("La clé '");
			stb.append(pCle);
			stb.append("' n'existe pas dans ");
			stb.append(pBundle.getBaseBundleName());
			stb.append("fr_FR.properties");
			
			return stb.toString();
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de creerMessageManqueCle(
	 // String pMethode
	 // , String pCle
	 // , ResourceBundle pBundle)._________________________________________
	

	
	/**
	 * method creerMessageManqueValeur(
	 * String pMethode
	 * , String pCle
	 * , ResourceBundle pBundle) :<br/>
	 * Crée un message pour le LOG et le rapport de configuration csv 
	 * si une valeur en face d'une clé est absente 
	 * dans un ResourceBundle.<br/>
	 * <br/>
	 *
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * @param pCle : String : Clé dans le ResourceBundle.<br/>
	 * @param pBundle : ResourceBundle.<br/>
	 * 
	 * @return : String : message pour le LOG 
	 * et le rapport de configuration csv.<br/>
	 */
	private static String creerMessageManqueValeur(
			final String pMethode
			 	, final String pCle
			 		, final ResourceBundle pBundle) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESDARWIN);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMethode);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append("La valeur associée à la clé '");
			stb.append(pCle);
			stb.append("' n'existe pas (null ou vide) dans ");
			stb.append(pBundle.getBaseBundleName());
			stb.append("fr_FR.properties");
			
			return stb.toString();
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de creerMessageManqueValeur(
	 // String pMethode
	 // , String pCle
	 // , ResourceBundle pBundle)._________________________________________


	
	/**
	 * method ajouterMessageAuRapportConfigurationCsv(
	 * String pMessage) :<br/>
	 * Rajoute le message pMessage au rapport 
	 * de chargement de la configuration au format csv (à la ligne).<br/>
	 * <br/>
	 * - Ne fait rien si pMessage est blank.<br/>
	 * - Ne Rajoute PAS l'en-tête (avec BOM_UTF-8) 
	 * pour le rapport de chargement de la configuration si nécessaire.<br/>
	 * <br/>
	 *
	 * @param pMessage : String : Message à rajouter 
	 * au rapport de chargement de la configuration.<br/>
	 */
	private static void ajouterMessageAuRapportConfigurationCsv(
			final String pMessage) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinManager.class) {
			
			/* Ne fait rien si pMessage est blank. */
			if (StringUtils.isBlank(pMessage)) {
				return;
			}
			
			final StringBuilder stb = new StringBuilder();
						
			/* Rajoute le message au rapport de 
			 * chargement de la configuration au format csv (à la ligne). */
			if (!StringUtils.isBlank(rapportConfigurationCsv)) {
				stb.append(rapportConfigurationCsv);
				stb.append(NEWLINE);
			}
			
			stb.append(pMessage);
			stb.append(SEP_PV);
			
			rapportConfigurationCsv = stb.toString();
			
		} // Fin de synchronized.________________________________________
			
	} // Fin de ajouterMessageAuRapportConfigurationCsv(
	 // String pMessage).__________________________________________________
	

	
	/**
	 * method traiterFichier(
	 * File pFile
	 * , String pMethode) :<br/>
	 * LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 * <ul>
	 * <li>LOG.FATAL, rapporte et jette 
	 * une FichierNullRunTimeException si pFile est null.</li><br/>
	 * <li>LOG.FATAL, rapporte et jette 
	 * une FichierInexistantRunTimeException si pFile est inexistant.</li><br/>
	 * <li>LOG.FATAL, rapporte et jette 
	 * une FichierRepertoireRunTimeException si pFile est un répertoire.</li><br/>
	 * <li>LOG.FATAL, rapporte et jette 
	 * une FichierVideRunTimeException si pFile est vide.</li><br/>
	 * </ul>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : Nom de la méthode appelante.<br/>
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	private static void traiterFichier(
			final File pFile
				, final String pMethode) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinManager.class) {
			
			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;
			
			/* si pFile est null.*******/
			if (pFile == null) {
				
				messageIndividuelRapport 
				= creerMessageMauvaisFichier(
						pMethode
						, "Le Fichier passé en paramètre est null"); 

				/* LOG.FATAL. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(messageIndividuelRapport);
				}
				
				/* Rapport. */
				ajouterMessageAuRapportConfigurationCsv(
						messageIndividuelRapport);
				
				/* Jette une FichierNullRunTimeException. */
				throw new FichierNullRunTimeException(
						messageIndividuelRapport);
				
			} // Fin de if (pFile == null).__________

			
			/* si pFile est inexistant. *******************/
			if (!pFile.exists()) {
				
				messageIndividuelRapport 
				= creerMessageMauvaisFichier(
						pMethode
						, "Le Fichier passé en paramètre est inexistant : " 
						+ pFile.getAbsolutePath() 
						+ " - Ce fichier est INDISPENSABLE à l'application");
				
				/* LOG.FATAL. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(messageIndividuelRapport);
				}
				
				/* Rapport. */
				ajouterMessageAuRapportConfigurationCsv(
						messageIndividuelRapport);
				
				/* Jette une FichierInexistantRunTimeException. */
				throw new FichierInexistantRunTimeException(
						messageIndividuelRapport);
				
			} // Fin de if (!pFile.exists()).____________

			
			/* si pFile est un répertoire. *******************/
			if (pFile.isDirectory()) {
				
				messageIndividuelRapport 
				= creerMessageMauvaisFichier(
						pMethode
						, "Le Fichier passé en paramètre est un répertoire : " 
						+ pFile.getAbsolutePath() 
						+ " - Ce fichier est INDISPENSABLE à l'application");
				
				/* LOG.FATAL. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(messageIndividuelRapport);
				}
				
				/* Rapport. */
				ajouterMessageAuRapportConfigurationCsv(
						messageIndividuelRapport);
				
				/* Jette une FichierRepertoireRunTimeException. */
				throw new FichierRepertoireRunTimeException(
						messageIndividuelRapport);
				
			} // Fin de if (pFile.isDirectory()).__________

			
			/* si pFile est vide. ***********************/
			if (pFile.length() == 0) {
				
				messageIndividuelRapport 
				= creerMessageMauvaisFichier(
						pMethode
						, "Le Fichier passé en paramètre est vide : " 
						+ pFile.getAbsolutePath() 
						+ " - Ce fichier est INDISPENSABLE à l'application");
				
				/* LOG.FATAL. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(messageIndividuelRapport);
				}
				
				/* Rapport. */
				ajouterMessageAuRapportConfigurationCsv(
						messageIndividuelRapport);
				
				/* Jette une FichierVideRunTimeException. */
				throw new FichierVideRunTimeException(
						messageIndividuelRapport);
				
			} // Fin de if (pFile.length() == 0)._________________
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de traiterFichier(
	 // File pFile
	 // , String pMethode).________________________________________________
	

	
	/**
	 * method creerMessageMauvaisFichier(
	 * String pMethode
	 * , String pMessage) :<br/>
	 * Crée un message pour le LOG et le rapport de configuration csv 
	 * si un Fichier de ressources (Description de fichier, nomenclature, ...) 
	 * est introuvable.<br/>
	 * <br/>
	 *
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * @param pMessage : String : message ciconstancié 
	 * de la méthode appelante.<br/>
	 * 
	 * @return : String : message pour le LOG 
	 * et le rapport de configuration csv.<br/>
	 */
	private static String creerMessageMauvaisFichier(
				final String pMethode
					, final String pMessage) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESDARWIN);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMethode);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMessage);
			
			return stb.toString();
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de creerMessageMauvaisFichier(
	 // String pMethode
	// , String pMessage)._________________________________________________
	

	
} // FIN DE LA CLASSE ConfigurationNomenclaturesDarwinManager.------------------
