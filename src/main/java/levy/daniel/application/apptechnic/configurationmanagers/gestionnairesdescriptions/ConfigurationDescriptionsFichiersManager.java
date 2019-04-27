package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesdescriptions;

import java.io.File;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierRepertoireRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideRunTimeException;


/**
 * class ConfigurationDescriptionsFichiersManager :<br/>
 * Classe UTILITAIRE 
 * Chargée de gérer la configuration des 
 * CHEMINS DES DESCRIPTIONS DES FICHIERS 
 * de l'application.<br/>
 * Met à disposition de l'ensemble de l'application 
 * des Singletons.<br/>
 * <br/>
 * <ul>
 * <li>La méthode getCheminDescriptions fournit un Singleton 
 * du chemin vers les descriptions des fichiers 
 * (HIT, HISTO_F07, Darwin.csv, ...).</li><br/>
 * <li>Les méthodes getNomDescriptionXXX fournissent un singleton  
 * du nom du fichier de description du fichierXXX 
 * (HIT, HISTO_F07, Darwin.csv, ...).</li><br/>
 * <li>Les méthodes getFichierDescriptionXXX fournissent un singleton  
 * du fichier de description du fichierXXX 
 * (HIT, HISTO_F07, Darwin.csv, ...).</li><br/>
 * </ul>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * pattern délégation, DELEGATION, <br/>
 * pattern Singleton, singleton, <br/>
 * Rapport du chargement de la configuration au format csv,<br/>
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
public final class ConfigurationDescriptionsFichiersManager {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_CONFIGURATIONDESCRIPTIONSFICHIERSMANAGER : String :<br/>
	 * "Classe ConfigurationDescriptionsFichiersManager".<br/>
	 */
	public static final String CLASSE_CONFIGURATIONDESCRIPTIONSFICHIERSMANAGER 
		= "Classe ConfigurationDescriptionsFichiersManager";
	
	
	/**
	 * METHODE_GET_CHEMINSDESCRIPTIONS : String :<br/>
	 * "Méthode getCheminDescriptions()".<br/>
	 */
	public static final String METHODE_GET_CHEMINSDESCRIPTIONS 
		= "Méthode getCheminDescriptions()";
	
	
	/**
	 * METHODE_GET_NOMDESCRIPTIONHIT : String :<br/>
	 * "Méthode getNomDescriptionHit()".<br/>
	 */
	public static final String METHODE_GET_NOMDESCRIPTIONHIT 
		= "Méthode getNomDescriptionHit()";

	
	/**
	 * METHODE_GET_FICHIERDESCRIPTIONHIT : String :<br/>
	 * "Méthode getFichierDescriptionHit()".<br/>
	 */
	public static final String METHODE_GET_FICHIERDESCRIPTIONHIT 
		= "Méthode getFichierDescriptionHit()";
	
	
	/**
	 * METHODE_GET_NOMDESCRIPTIONHISTOF07 : String :<br/>
	 * "Méthode getNomDescriptionHistoF07()".<br/>
	 */
	public static final String METHODE_GET_NOMDESCRIPTIONHISTOF07 
		= "Méthode getNomDescriptionHistoF07()";

	
	/**
	 * METHODE_GET_FICHIERDESCRIPTIONHISTOF07 : String :<br/>
	 * "Méthode getFichierDescriptionHistoF07()".<br/>
	 */
	public static final String METHODE_GET_FICHIERDESCRIPTIONHISTOF07 
		= "Méthode getFichierDescriptionHistoF07()";
	
	
	/**
	 * METHODE_GET_NOMDESCRIPTIONDARWINCSV : String :<br/>
	 * "Méthode getNomDescriptionDarwinCsv()".<br/>
	 */
	public static final String METHODE_GET_NOMDESCRIPTIONDARWINCSV 
		= "Méthode getNomDescriptionDarwinCsv()";

	
	/**
	 * METHODE_GET_FICHIERDESCRIPTIONDARWIN : String :<br/>
	 * "Méthode getFichierDescriptionDarwinCsv()".<br/>
	 */
	public static final String METHODE_GET_FICHIERDESCRIPTIONDARWIN 
		= "Méthode getFichierDescriptionDarwinCsv()";
	
	
	/**
	 * METHODE_GET_NOMDESCRIPTIONMAPPING : String :<br/>
	 * "Méthode getNomDescriptionMapping()".<br/>
	 */
	public static final String METHODE_GET_NOMDESCRIPTIONMAPPING 
		= "Méthode getNomDescriptionMapping()";

	
	/**
	 * METHODE_GET_FICHIERDESCRIPTIONMAPPING : String :<br/>
	 * "Méthode getFichierDescriptionMapping()".<br/>
	 */
	public static final String METHODE_GET_FICHIERDESCRIPTIONMAPPING 
		= "Méthode getFichierDescriptionMapping()";
	
	
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
	// CHEMINS, NOMS DE FICHIERS ET FICHIERS DE DESCRIPTION.*************
	// ******************************************************************
	
	/**
	 * cheminDescriptions : String :<br/>
	 * Chemin des descriptions de fichier 
	 * stocké dans application.properties.<br/>
	 * ".\\ressources\\descriptions\\".<br/>
	 * Clé = "application.repertoire.ressources.descriptions".<br/>
	 */
	private static transient String cheminDescriptions;

	
	/**
	 * nomDescriptionHit : String :<br/>
	 * Nom du fichier de description du HIT
	 * stocké dans application.properties.<br/>
	 * "2014-07-19_Description_HIT_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.descriptions.hit".<br/>
	 */
	private static transient String nomDescriptionHit;
	
	
	/**
	 * fichierDescriptionHIT : File :<br/>
	 * Fichier sur disque au format csv 
	 * encodé en UTF-8
	 * contenant la description du HIT.<br/>
	 */
	private static transient File fichierDescriptionHIT;
	
	
	/**
	 * nomDescriptionHistoF07 : String :<br/>
	 * Nom du fichier de description du HistoF07 
	 * stocké dans application.properties.<br/>
	 * "2014-07-19_Description_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.descriptions.histof07".<br/>
	 */
	private static transient String nomDescriptionHistoF07;

	
	/**
	 * fichierDescriptionHistoF07 : File :<br/>
	 * Fichier sur disque au format csv 
	 * encodé en UTF-8
	 * contenant la description du HISTO_F07.<br/>
	 */
	private static transient File fichierDescriptionHistoF07;


	/**
	 * nomDescriptionDarwinCsv : String :<br/>
	 * Nom du fichier de description du Darwin.csv
	 * stocké dans application.properties.<br/>
	 * "Description_DarwinCsv.csv".<br/>
	 * Clé = "application.repertoire.ressources.descriptions.darwincsv".<br/>
	 */
	private static transient String nomDescriptionDarwinCsv;

	
	/**
	 * fichierDescriptionDarwinCsv : File :<br/>
	 * Fichier sur disque au format csv
	 * encodé en UTF-8 
	 * contenant la description du DARWIN_CSV.<br/>
	 */
	private static transient File fichierDescriptionDarwinCsv;


	/**
	 * nomDescriptionMapping : String :<br/>
	 * Nom du fichier de description du Mapping Histo-Hit-Darwin-Isidor 
	 * stocké dans application.properties.<br/>
	 * "Description_MAPPING_HistoF07_Hit_Darwin_Isidor.csv".<br/>
	 * Clé = "application.repertoire.ressources.descriptions.mapping".<br/>
	 */
	private static transient String nomDescriptionMapping;

	
	/**
	 * fichierDescriptionMapping : File :<br/>
	 * Fichier sur disque au format csv
	 * encodé en UTF-8 
	 * contenant la description du MAPPING.<br/>
	 */
	private static transient File fichierDescriptionMapping;


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
			.getLog(ConfigurationDescriptionsFichiersManager.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR ConfigurationDescriptionsFichiersManager() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour interdire l'instanciation.<br/>
	 * <br/>
	 */
	private ConfigurationDescriptionsFichiersManager() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________



	/**
	 * method getCheminDescriptions() :<br/>
	 * Getter du Chemin des descriptions de fichier 
	 * stocké dans application.properties.<br/>
	 * ".\\ressources\\descriptions\\".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirCheminDescriptionsEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.descriptions".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirCheminDescriptionsEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirCheminDescriptionsEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return cheminDescriptions : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getCheminDescriptions() throws Exception {
				
		/* Bloc synchronized. */
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;
			
			if (cheminDescriptions == null) {
				
				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {
					
					try {
						
						/* Essaie de récupérer la valeur 
						 * dans le properties. */
						final String valeur 
						= ConfigurationApplicationManager
							.getBundleApplication()
								.getString(
									fournirCleCheminDescriptions());
						
						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {
							
							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
									METHODE_GET_CHEMINSDESCRIPTIONS
									, fournirCleCheminDescriptions()
									, ConfigurationApplicationManager
										.getBundleApplication());
							
							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}
							
							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);
							
							/* utilise la valeur fournie en dur. */
							cheminDescriptions 
								= fournirCheminDescriptionsEnDur();
							
						} // Fin de Si la valeur est blank._________
						
						/* Valeur remplie dans le properties. */
						else {
							
							/* Nettoie la valeur lue dans le 
							 * .properties avec trim(). */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);
							
							cheminDescriptions 
								= valeurNettoyee; 
							
						} // Fin de Valeur remplie dans le properties.____
												
					} catch (MissingResourceException mre) {
						
						/* Création du message. */
						messageIndividuelRapport 
						= creerMessageManqueCle(
								METHODE_GET_CHEMINSDESCRIPTIONS
								, fournirCleCheminDescriptions()
								, ConfigurationApplicationManager
									.getBundleApplication());
						
						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}
						
						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);
						
						/* utilise la valeur fournie en dur. */
						cheminDescriptions 
							= fournirCheminDescriptionsEnDur();
						
					} // Fin de catch (MissingResourceException mre)._____
					
				} // Fin de if (getBundleApplication() != null)._____

				
				/* if (getBundleApplication() == null). */
				else {
					
					/* utilise la valeur fournie en dur. */
					cheminDescriptions 
						= fournirCheminDescriptionsEnDur();
					
				} // Fin de if (getBundleApplication() == null).___
								
			} // Fin de if (cheminDescriptions == null)._________
			
			return cheminDescriptions;
									
		} // Fin de synchronized.________________________________________
		
	} // Fin de getCheminDescriptions().___________________________________


	
	/**
	 * method fournirCleCheminDescriptions() :<br/>
	 * clé du chemin des chemins des descriptions de fichiers dans 
	 * application_fr_FR.properties.<br/>
	 * Clé = "application.repertoire.ressources.descriptions".
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.descriptions".<br/>
	 */
	private static String fournirCleCheminDescriptions() {
		return "application.repertoire.ressources.descriptions";
	} // Fin de fournirCleCheminDescriptions().____________________________
	

	
	/**
	 * method fournirCheminDescriptionsEnDur() :<br/>
	 * Fournit une valeur stockée en dur 
	 * dans la classe pour cheminDescriptions.<br/>
	 * <br/>
	 * ".\\ressources\\Descriptions de fichier\\".<br/>
	 * <br/>
	 *
	 * @return : String : ".\\ressources\\Descriptions de fichier\\".<br/>
	 */
	private static String fournirCheminDescriptionsEnDur() {
		return "ressources/Descriptions de fichier";
	} // Fin de fournirCheminDescriptionsEnDur().__________________________
	
	
	
	/**
	 * method getNomDescriptionHit() :<br/>
	 * Getter du Nom du fichier de description du HIT
	 * stocké dans application.properties.<br/>
	 * "2014-07-19_Description_HIT_Utf8.csv".<br/>
	 * <br/>
	 *  <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomDescriptionHitEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.descriptions.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomDescriptionHitEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomDescriptionHitEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomDescriptionHit : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomDescriptionHit() throws Exception {
						
		/* Bloc synchronized. */
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;
			
			if (nomDescriptionHit == null) {
				
				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {
					
					try {
						
						/* Essaie de récupérer la valeur 
						 * dans le properties. */
						final String valeur 
						= ConfigurationApplicationManager
							.getBundleApplication()
								.getString(
									fournirCleNomDescriptionHit());
						
						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {
							
							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
									METHODE_GET_NOMDESCRIPTIONHIT
									, fournirCleNomDescriptionHit()
									, ConfigurationApplicationManager
										.getBundleApplication());
							
							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}
							
							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);
							
							/* utilise la valeur fournie en dur. */
							nomDescriptionHit 
								= fournirNomDescriptionHitEnDur();
							
						} // Fin de Si la valeur est blank._________
						
						/* Valeur remplie dans le properties. */
						else {
							
							/* Nettoie la valeur lue dans le 
							 * .properties avec trim(). */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);
							
							nomDescriptionHit 
								= valeurNettoyee; 
							
						} // Fin de Valeur remplie dans le properties.____
												
					} catch (MissingResourceException mre) {
						
						/* Création du message. */
						messageIndividuelRapport 
						= creerMessageManqueCle(
								METHODE_GET_NOMDESCRIPTIONHIT
								, fournirCleNomDescriptionHit()
								, ConfigurationApplicationManager
									.getBundleApplication());
						
						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}
						
						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);
						
						/* utilise la valeur fournie en dur. */
						nomDescriptionHit 
							= fournirNomDescriptionHitEnDur();
						
					} // Fin de catch (MissingResourceException mre)._____
					
				} // Fin de if (getBundleApplication() != null)._____
		
				
				/* if (getBundleApplication() == null). */
				else {
					
					/* utilise la valeur fournie en dur. */
					nomDescriptionHit 
						= fournirNomDescriptionHitEnDur();
					
				} // Fin de if (getBundleApplication() == null).___
								
			} // Fin de if (variable == null).______________________
			
			return nomDescriptionHit;
									
		} // Fin de synchronized.________________________________________
		
	} // Fin de getNomDescriptionHit().____________________________________


	
	/**
	 * method fournirCleNomDescriptionHit() :<br/>
	 * clé du nom de la description de fichier HIT dans 
	 * application_fr_FR.properties.<br/>
	 * Clé = "application.repertoire.ressources.descriptions.hit".
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.descriptions.hit".<br/>
	 */
	private static String fournirCleNomDescriptionHit() {
		return "application.repertoire.ressources.descriptions.hit";
	} // Fin de fournirCleNomDescriptionHit()._____________________________
	
	
	
	/**
	 * method fournirNomDescriptionHitEnDur() :<br/>
	 * Fournit une valeur stockée en dur 
	 * dans la classe pour nomDescriptionHit.<br/>
	 * <br/>
	 * "Hit\\Descriptions en UTF-8\\2014-07-19_Description_HIT_Utf8.csv".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "Hit\\Descriptions en UTF-8\\2014-07-19_Description_HIT_Utf8.csv".<br/>
	 */
	private static String fournirNomDescriptionHitEnDur() {
		return "Hit/Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv";
	} // Fin de fournirNomDescriptionHitEnDur().___________________________
	

	
	/**
	 * method getFichierDescriptionHit() :<br/>
	 * Fournit le Fichier sur disque au format csv 
	 * contenant la description du HIT en UTF-8.<br/>
	 * <br/>
	 * .\\ressources\\Descriptions de fichier\\Hit\\Descriptions en UTF-8
	 * \\2014-07-19_Description_HIT_Utf8.csv.<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierDescriptionHIT.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierDescriptionHit() throws Exception {
		
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
			/* Instanciation du Singleton fichierDescriptionHIT. */
			if (fichierDescriptionHIT == null) {
								
				fichierDescriptionHIT 
				= new File(getCheminDescriptions() + getNomDescriptionHit());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierDescriptionHIT
						, METHODE_GET_FICHIERDESCRIPTIONHIT);
			}
			
			return fichierDescriptionHIT;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierDescriptionHit().________________________________
	
	
	
	/**
	 * method getNomDescriptionHistoF07() :<br/>
	 * Getter du Nom du fichier de description du HistoF07 
	 * stocké dans application.properties.<br/>
	 * "2014-07-19_Description_HistoF07_Utf8.csv".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomDescriptionHistoF07EnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.descriptions.histof07".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomDescriptionHistoF07EnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomDescriptionHistoF07EnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomDescriptionHistoF07 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomDescriptionHistoF07() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomDescriptionHistoF07 == null) {

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
										fournirCleNomDescriptionHistoF07());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
								= creerMessageManqueValeur(
									METHODE_GET_NOMDESCRIPTIONHISTOF07,
									fournirCleNomDescriptionHistoF07(),
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
							nomDescriptionHistoF07 
								= fournirNomDescriptionHistoF07EnDur();

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

							nomDescriptionHistoF07 = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMDESCRIPTIONHISTOF07,
								fournirCleNomDescriptionHistoF07(),
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
						nomDescriptionHistoF07 
							= fournirNomDescriptionHistoF07EnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomDescriptionHistoF07 
						= fournirNomDescriptionHistoF07EnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (variable == null).______________________

			return nomDescriptionHistoF07;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomDescriptionHistoF07()._______________________________


	
	/**
	 * method fournirCleNomDescriptionHistoF07() :<br/>
	 * clé du nom de la description de fichier HISTO_F07 dans 
	 * application_fr_FR.properties.<br/>
	 * Clé = "application.repertoire.ressources.descriptions.histof07".
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.descriptions.histof07".<br/>
	 */
	private static String fournirCleNomDescriptionHistoF07() {
		return "application.repertoire.ressources.descriptions.histof07";
	} // Fin de fournirCleNomDescriptionHistoF07().________________________
	
	
	
	/**
	 * method fournirNomDescriptionHistoF07EnDur() :<br/>
	 * Fournit une valeur stockée en dur 
	 * dans la classe pour nomDescriptionHistoF07.<br/>
	 * <br/>
	 * "HistonatF07\\Descriptions en UTF-8\\2014-07-19_Description_HistoF07_Utf8.csv".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "HistonatF07\\Descriptions en UTF-8\\2014-07-19_Description_HistoF07_Utf8.csv".<br/>
	 */
	private static String fournirNomDescriptionHistoF07EnDur() {
		return "HistonatF07/Descriptions en UTF-8/2014-07-19_Description_HistoF07_Utf8.csv";
	} // Fin de fournirNomDescriptionHistoF07EnDur().______________________
	

	/**
	 * method getFichierDescriptionHistoF07() :<br/>
	 * Fournit le Fichier sur disque au format csv 
	 * contenant la description du HISTO_F07 en UTF-8.<br/>
	 * <br/>
	 * .\\ressources\\Descriptions de fichier\\HistonatF07\\
	 * Descriptions en UTF-8\\2014-07-19_Description_HistoF07_Utf8.csv.<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierDescriptionHistoF07.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierDescriptionHistoF07() throws Exception {
		
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
			/* Instanciation du Singleton fichierDescriptionHistoF07. */
			if (fichierDescriptionHistoF07 == null) {
				
				fichierDescriptionHistoF07 
				= new File(getCheminDescriptions() + getNomDescriptionHistoF07());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierDescriptionHistoF07
						, METHODE_GET_FICHIERDESCRIPTIONHISTOF07);

			}
			
			return fichierDescriptionHistoF07;
			
		} // Fin de synchronized._________________________
		
	} // Fin de getFichierDescriptionHistoF07().___________________________

	
	
	/**
	 * method getNomDescriptionDarwinCsv() :<br/>
	 * Getter du Nom du fichier de description du Darwin.csv
	 * stocké dans application.properties.<br/>
	 * "Description_DarwinCsv.csv".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomDescriptionDarwinCsvEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.descriptions.darwincsv".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomDescriptionDarwinCsvEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomDescriptionDarwinCsvEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomDescriptionDarwinCsv : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomDescriptionDarwinCsv() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomDescriptionDarwinCsv == null) {

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
										fournirCleNomDescriptionDarwinCsv());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
								= creerMessageManqueValeur(
									METHODE_GET_NOMDESCRIPTIONDARWINCSV,
									fournirCleNomDescriptionDarwinCsv(),
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
							nomDescriptionDarwinCsv 
								= fournirNomDescriptionDarwinCsvEnDur();

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

							nomDescriptionDarwinCsv = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMDESCRIPTIONDARWINCSV,
								fournirCleNomDescriptionDarwinCsv(),
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
						nomDescriptionDarwinCsv 
							= fournirNomDescriptionDarwinCsvEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomDescriptionDarwinCsv 
						= fournirNomDescriptionDarwinCsvEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (variable == null).______________________

			return nomDescriptionDarwinCsv;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomDescriptionDarwinCsv().______________________________


	
	/**
	 * method fournirCleNomDescriptionDarwinCsv() :<br/>
	 * clé du nom de la description de fichier DARWIN_CSV dans 
	 * application_fr_FR.properties.<br/>
	 * Clé = "application.repertoire.ressources.descriptions.darwincsv".
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.descriptions.darwincsv".<br/>
	 */
	private static String fournirCleNomDescriptionDarwinCsv() {
		return "application.repertoire.ressources.descriptions.darwincsv";
	} // Fin de fournirCleNomDescriptionDarwinCsv()._______________________
	
	
	
	/**
	 * method fournirNomDescriptionDarwinCsvEnDur() :<br/>
	 * Fournit une valeur stockée en dur 
	 * dans la classe pour nomDescriptionDarwinCsv.<br/>
	 * <br/>
	 * "Darwin csv\\Descriptions en UTF-8\\2014-07-19_Description_DarwinCsv_Utf8.csv".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "Darwin csv\\Descriptions en UTF-8\\2014-07-19_Description_DarwinCsv_Utf8.csv".<br/>
	 */
	private static String fournirNomDescriptionDarwinCsvEnDur() {
		return "Darwin csv\\Descriptions en UTF-8\\2014-07-19_Description_DarwinCsv_Utf8.csv";
	} // Fin de fournirNomDescriptionDarwinCsvEnDur()._____________________
	

	
	/**
	 * method getFichierDescriptionDarwinCsv() :<br/>
	 * Fournit le Fichier sur disque au format csv 
	 * contenant la description du DARWIN_CSV en UTF-8.<br/>
	 * <br/>
	 * .\\ressources\\Descriptions de fichier\\Darwin csv
	 * \\Descriptions en UTF-8\\2014-07-19_Description_DarwinCsv_Utf8.csv.<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierDescriptionDarwinCsv.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierDescriptionDarwinCsv() throws Exception {
		
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
			/* Instanciation du Singleton fichierDescriptionDarwinCsv. */
			if (fichierDescriptionDarwinCsv == null) {
				
				fichierDescriptionDarwinCsv 
				= new File(getCheminDescriptions() + getNomDescriptionDarwinCsv());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierDescriptionDarwinCsv
						, METHODE_GET_FICHIERDESCRIPTIONDARWIN);
				
			}
			
			return fichierDescriptionDarwinCsv;
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de getFichierDescriptionDarwinCsv().__________________________


	
	/**
	 * method getNomDescriptionMapping() :<br/>
	 * Getter du Nom du fichier de description 
	 * du Mapping Histo-Hit-Darwin-Isidor 
	 * stocké dans application.properties.<br/>
	 * "Description_MAPPING_HistoF07_Hit_Darwin_Isidor.csv".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomDescriptionMappingEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.descriptions.mapping".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomDescriptionMappingEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomDescriptionMappingEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomDescriptionMapping : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomDescriptionMapping() throws Exception {
				
		/* Bloc synchronized. */
		synchronized (ConfigurationDescriptionsFichiersManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomDescriptionMapping == null) {

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
										fournirCleNomDescriptionMapping());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
								= creerMessageManqueValeur(
										METHODE_GET_NOMDESCRIPTIONMAPPING,
										fournirCleNomDescriptionMapping(),
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
							nomDescriptionMapping 
								= fournirNomDescriptionMappingEnDur();

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

							nomDescriptionMapping = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMDESCRIPTIONMAPPING,
								fournirCleNomDescriptionMapping(),
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
						nomDescriptionMapping 
							= fournirNomDescriptionMappingEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomDescriptionMapping 
						= fournirNomDescriptionMappingEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (variable == null).______________________

			return nomDescriptionMapping;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomDescriptionMapping().________________________________


	
	/**
	 * method fournirCleNomDescriptionMapping() :<br/>
	 * clé du nom de la description de fichier MAPPING dans 
	 * application_fr_FR.properties.<br/>
	 * Clé = "application.repertoire.ressources.descriptions.mapping".
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.descriptions.mapping".<br/>
	 */
	private static String fournirCleNomDescriptionMapping() {
		return "application.repertoire.ressources.descriptions.mapping";
	} // Fin de fournirCleNomDescriptionMapping()._________________________
	
	
	
	/**
	 * method fournirNomDescriptionMappingEnDur() :<br/>
	 * Fournit une valeur stockée en dur 
	 * dans la classe pour nomDescriptionMapping.<br/>
	 * <br/>
	 * "Mapping\\Descriptions en UTF-8\\2014-07-19_Description_Mapping_Utf8.csv".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "Mapping\\Descriptions en UTF-8\\2014-07-19_Description_Mapping_Utf8.csv".<br/>
	 */
	private static String fournirNomDescriptionMappingEnDur() {
		return "Mapping/Descriptions en UTF-8/2014-07-19_Description_Mapping_Utf8.csv";
	} // Fin de fournirNomDescriptionMappingEnDur()._______________________
	

	
	
	/**
	 * method getFichierDescriptionMapping() :<br/>
	 * Fournit le Fichier sur disque au format csv 
	 * encodé en UTF-8
	 * contenant la description du MAPPING.<br/>
	 * <br/>
	 * .\\ressources\\Descriptions de fichier\\Mapping\\
	 * Descriptions en UTF-8\\2014-07-19_Description_Mapping_Utf8.csv.<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierDescriptionMapping.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierDescriptionMapping() throws Exception {
		
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
			/* Instanciation du Singleton fichierDescriptionMapping. */
			if (fichierDescriptionMapping == null) {
				
				fichierDescriptionMapping 
				= new File(getCheminDescriptions() + getNomDescriptionMapping());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierDescriptionMapping
						, METHODE_GET_FICHIERDESCRIPTIONMAPPING);
				
			}
			
			return fichierDescriptionMapping;
			
		} // Fin de synchronized._________________________
		
	} // Fin de getFichierDescriptionMapping().____________________________
	

	
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
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
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
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
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
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONDESCRIPTIONSFICHIERSMANAGER);
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
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONDESCRIPTIONSFICHIERSMANAGER);
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
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
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
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
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
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONDESCRIPTIONSFICHIERSMANAGER);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMethode);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMessage);
			
			return stb.toString();
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de creerMessageMauvaisFichier(
	 // String pMethode
	// , String pMessage)._________________________________________________
	


} // FIN DE LA CLASSE ConfigurationDescriptionsFichiersManager.--------------
