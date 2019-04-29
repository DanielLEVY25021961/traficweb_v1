package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesdescriptions;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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
 * class ConfigurationDescriptionsFichiersManager :<br/>
 * Classe UTILITAIRE 
 * Chargée de gérer la configuration des 
 * CHEMINS DES DESCRIPTIONS DES FICHIERS 
 * de l'application.<br/>
 * Met à disposition de l'ensemble de l'application 
 * des Singletons.<br/>
 * <ul>
 * <li>La méthode getCheminDescriptions fournit un Singleton 
 * du chemin vers les descriptions des fichiers 
 * (HIT, HISTO_F07, Darwin.csv, ...).</li>
 * <li>Les méthodes getNomDescriptionXXX fournissent un singleton  
 * du nom du fichier de description du fichierXXX 
 * (HIT, HISTO_F07, Darwin.csv, ...).</li>
 * <li>Les méthodes getFichierDescriptionXXX fournissent un singleton  
 * du fichier de description du fichierXXX 
 * (HIT, HISTO_F07, Darwin.csv, ...).</li>
 * </ul>
 * <p>
 * <b><span style="text-decoration:underline;">
 * Diagramme de classe du ConfigurationDescriptionsFichiersManager : 
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../javadoc/images/apptechnic/configurationmanagers/gestionnairesdescriptions/classe_ConfigurationDescriptionsFichiersManager.png" 
 * alt="Diagramme de classe du ConfigurationDescriptionsFichiersManager" />
 * </p>
 *
 * - Exemple d'utilisation :<br/>
 * <code>
 * // Récupère la description des fichiers HIT.<br/>
 * File fichierDescriptionHit = ConfigurationDescriptionsFichiersManager.getFichierDescriptionHit();<br/>
 *  // récupère un message d'erreur provoqué par la méthode précédente si problème de config (null si OK).<br/>
 * String messageIndividuelRapport = ConfigurationDescriptionsFichiersManager.getMessageIndividuelRapport();<br/>
 *  // ...<br/>
 *  // récupère le rapport des erreurs de configuration de l'ensemble des méthodes de la classe si problème de config (null si OK).<br/>
 * String rapportConfigurationCsv = ConfigurationDescriptionsFichiersManager.getRapportConfigurationCsv();<br/>
 * </code>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * pattern délégation, DELEGATION, <br/>
 * pattern Singleton, singleton, <br/>
 * Rapport du chargement de la configuration au format csv,<br/>
 * Resource, resource, ressources, URL, URI,<br/>
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
	 * <b>Chemin relatif par rapport au classpath (contexte) 
	 * du répertoire [descriptions de fichier] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code> .<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Descriptions de fichier" sous le contexte target/classes.<br/>
	 * Clé = "application.repertoire.ressources.descriptions".<br/>
	 */
	private static transient String cheminDescriptions;

	/**
	 * <b>Path relatif par rapport au classpath (contexte) 
	 * du répertoire [descriptions de fichier] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code> .<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Descriptions de fichier" sous le contexte target/classes.<br/>
	 * Clé = "application.repertoire.ressources.descriptions".<br/>
	 */
	private static transient Path pathDescriptions;
	
	/**
	 * <b>Chemin relatif par rapport au répertoire [descriptions de fichier] 
	 * de la description du HIT</b>.<br/>
	 * Ce chemin est stocké dans <code>application.properties</code> 
	 * dans le classpath sous le contexte.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "Hit/Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.descriptions.hit".<br/>
	 */
	private static transient String nomDescriptionHit;
		
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8
	 * contenant la description du HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient File fichierDescriptionHIT;
		
	/**
	 * <b>Chemin relatif par rapport au répertoire [descriptions de fichier] 
	 * de la description du HistoF07</b>.<br/>
	 * Ce chemin est stocké dans <code>application.properties</code> 
	 * dans le classpath sous le contexte.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "HistonatF07/Descriptions en UTF-8/2014-07-19_Description_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.descriptions.histof07".<br/>
	 */
	private static transient String nomDescriptionHistoF07;
	
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8
	 * contenant la description du HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient File fichierDescriptionHistoF07;

	/**
	 * <b>Chemin relatif par rapport au répertoire [descriptions de fichier] 
	 * de la description du Darwin.csv</b>.<br/>
	 * Ce chemin est stocké dans <code>application.properties</code> 
	 * dans le classpath sous le contexte.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "Darwin csv/Descriptions en UTF-8/2014-07-19_Description_DarwinCsv_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.descriptions.darwincsv".<br/>
	 */
	private static transient String nomDescriptionDarwinCsv;
	
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 
	 * contenant la description du DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient File fichierDescriptionDarwinCsv;

	/**
	 * <b>Chemin relatif par rapport au répertoire [descriptions de fichier] 
	 * de la description du Mapping Histo-Hit-Darwin-Isidor</b>.<br/>
	 * Ce chemin est stocké dans <code>application.properties</code> 
	 * dans le classpath sous le contexte.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "Mapping/Descriptions en UTF-8/2014-07-19_Description_Mapping_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.descriptions.mapping".<br/>
	 */
	private static transient String nomDescriptionMapping;
	
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 
	 * contenant la description du MAPPING entre 
	 * les différents types de fichiers (Histo-Hit-Darwin-Isidor).<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient File fichierDescriptionMapping;


	/**
	 * Rapport du chargement de la configuration au format csv.<br/>
	 * Le rapport est null si il n'y a eu aucun 
	 * problème d'initialisation de l'application.<br/>
	 * Par exemple : <br/>
	 * "Classe ConfigurationDescriptionsFichiersManager - 
	 * Méthode getNomDescriptionHit() - 
	 * La clé 'application.repertoire.ressources.descriptions.hit' 
	 * n'existe pas dans application_fr_FR.properties;".<br/>
	 * "Classe ConfigurationDescriptionsFichiersManager - 
	 * Méthode getNomDescriptionDarwinCsv() - 
	 * La valeur associée à la clé 
	 * 'application.repertoire.ressources.descriptions.darwincsv' 
	 * n'existe pas (null ou vide) dans application_fr_FR.properties;".<br/>
	 */
	private static transient String rapportConfigurationCsv;

	
	/**
	 * Message pour le Rapport du chargement de la configuration au format csv 
	 * généré par chaque méthode individuellement.<br/>
	 * Par exemple : <br/>
	 * "Classe ConfigurationDescriptionsFichiersManager - 
	 * Méthode getNomDescriptionHit() - 
	 * La clé 'application.repertoire.ressources.descriptions.hit' 
	 * n'existe pas dans applicationfr_FR.properties;".<br/>
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
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour interdire l'instanciation.<br/>
	 */
	private ConfigurationDescriptionsFichiersManager() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________



	/**
	 * Getter du <b>Chemin relatif par rapport au classpath (contexte) 
	 * du répertoire [descriptions de fichier] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code> .<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Descriptions de fichier" sous le contexte target/classes.<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * <code>application_fr_FR.properties</code>.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par <code>fournirCheminDescriptionsEnDur()</code>.</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * <li>fabrique un <code>messageIndividuelRapport</code> 
	 * si la clé ou la valeur sont manquantes dans 
	 * <code>application.properties</code>. <br/>
	 * <code>messageIndividuelRapport</code> est null sinon.</li>
	 * <li>ajoute le messageIndividuelRapport à 
	 * <code>rapportConfigurationCsv</code> le cas échéant.<br/> 
	 * <code>rapportConfigurationCsv</code> contient les éventuels 
	 * messages d'erreur de configuration de toutes 
	 * les méthodes de la présente classe.
	 * <br/><code>rapportConfigurationCsv</code> est null 
	 * si il n'y a aucune erreur de configuration.</li>
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
			
			if (cheminDescriptions == null) {
				
				/* Reset du messageIndividuelRapport. */
				messageIndividuelRapport = null;
								
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
	 * retourne la 
	 * clé du Chemin relatif par rapport au classpath (contexte) 
	 * du répertoire [descriptions de fichier].<br/>
	 * la clé est stockée dans 
	 * <code>application_fr_FR.properties</code>.<br/>
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
	 * Fournit une valeur stockée en dur 
	 * dans la classe pour <code>cheminDescriptions</code>.<br/>
	 * <br/>
	 * "ressources/Descriptions de fichier".<br/>
	 * <br/>
	 *
	 * @return : String : "ressources/Descriptions de fichier".<br/>
	 */
	private static String fournirCheminDescriptionsEnDur() {
		return "ressources/Descriptions de fichier";
	} // Fin de fournirCheminDescriptionsEnDur().__________________________
	

	
	/**
	 * Getter du <b>Path relatif par rapport au classpath (contexte) 
	 * du répertoire [descriptions de fichier] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code> .<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Descriptions de fichier" sous le contexte target/classes.<br/>
	 * Clé = "application.repertoire.ressources.descriptions".<br/>
	 *
	 * @return : Path.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Path getPathDescriptions() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
			return Paths.get(getCheminDescriptions());
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de getPathDescriptions()._____________________________________
	

	
	/**
	 * Getter du <b>Chemin relatif par rapport 
	 * au répertoire [descriptions de fichier] 
	 * de la description du HIT</b>.<br/>
	 * Ce chemin est stocké dans <code>application.properties</code> 
	 * dans le classpath sous le contexte.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "Hit/Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv".<br/>
	 *  <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomDescriptionHitEnDur().</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * <li>fabrique un <code>messageIndividuelRapport</code> 
	 * si la clé ou la valeur sont manquantes dans 
	 * <code>application.properties</code>. <br/>
	 * <code>messageIndividuelRapport</code> est null sinon.</li>
	 * <li>ajoute le messageIndividuelRapport à 
	 * <code>rapportConfigurationCsv</code> le cas échéant.<br/> 
	 * <code>rapportConfigurationCsv</code> contient les éventuels 
	 * messages d'erreur de configuration de toutes 
	 * les méthodes de la présente classe.
	 * <br/><code>rapportConfigurationCsv</code> est null 
	 * si il n'y a aucune erreur de configuration.</li>
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
			
			if (nomDescriptionHit == null) {
				
				/* Reset du messageIndividuelRapport. */
				messageIndividuelRapport = null;

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
	 * retourne la clé du 
	 * Chemin relatif par rapport au répertoire [descriptions de fichier] 
	 * de la description du HIT.<br/>
	 * La clé est stockée dans <code>application_fr_FR.properties</code>.<br/>
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
	 * Fournit une valeur stockée en dur 
	 * dans la classe pour <code>nomDescriptionHit</code>.<br/>
	 * <br/>
	 * "Hit/Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "Hit/Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv".<br/>
	 */
	private static String fournirNomDescriptionHitEnDur() {
		return "Hit/Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv";
	} // Fin de fournirNomDescriptionHitEnDur().___________________________
	

	
	/**
	 * Getter du <b>Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8
	 * contenant la description du HIT</b>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "context/ressources/Descriptions de fichier/Hit
	 * /Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv".<br/>
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
				
				final Path pathRelatifDescriptionHit 
					= Paths.get(getNomDescriptionHit());
				
				final Path pathRelatifContextDescriptionHit 
					= getPathDescriptions()
						.resolve(pathRelatifDescriptionHit);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(pathRelatifContextDescriptionHit.toString());
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierDescriptionHIT 
					= new File(uriRessources.getPath());
				
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
	 * Getter du <b>Chemin relatif par rapport au répertoire 
	 * [descriptions de fichier] 
	 * de la description du HistoF07</b>.<br/>
	 * Ce chemin est stocké dans <code>application.properties</code> 
	 * dans le classpath sous le contexte.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "HistonatF07/Descriptions en UTF-8/2014-07-19_Description_HistoF07_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomDescriptionHistoF07EnDur().</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * <li>fabrique un <code>messageIndividuelRapport</code> 
	 * si la clé ou la valeur sont manquantes dans 
	 * <code>application.properties</code>. <br/>
	 * <code>messageIndividuelRapport</code> est null sinon.</li>
	 * <li>ajoute le messageIndividuelRapport à 
	 * <code>rapportConfigurationCsv</code> le cas échéant.<br/> 
	 * <code>rapportConfigurationCsv</code> contient les éventuels 
	 * messages d'erreur de configuration de toutes 
	 * les méthodes de la présente classe.
	 * <br/><code>rapportConfigurationCsv</code> est null 
	 * si il n'y a aucune erreur de configuration.</li>
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
	 * retourne la clé du 
	 * Chemin relatif par rapport au répertoire [descriptions de fichier] 
	 * de la description du HISTOF07.<br/>
	 * La clé est stockée dans <code>application_fr_FR.properties</code>.<br/>
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
	 * Fournit une valeur stockée en dur 
	 * dans la classe pour <code>nomDescriptionHistoF07</code>.<br/>
	 * <br/>
	 * "HistonatF07/Descriptions en UTF-8/2014-07-19_Description_HistoF07_Utf8.csv".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "HistonatF07/Descriptions en UTF-8/2014-07-19_Description_HistoF07_Utf8.csv".<br/>
	 */
	private static String fournirNomDescriptionHistoF07EnDur() {
		return "HistonatF07/Descriptions en UTF-8/2014-07-19_Description_HistoF07_Utf8.csv";
	} // Fin de fournirNomDescriptionHistoF07EnDur().______________________
	

	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8
	 * contenant la description du HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "context/ressources/Descriptions de fichier/HistonatF07/
	 * Descriptions en UTF-8/2014-07-19_Description_HistoF07_Utf8.csv".<br/>
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
				
				final Path pathRelatifDescriptionHistoF07 
					= Paths.get(getNomDescriptionHistoF07());
				
				final Path pathRelatifContextDescriptionHistoF07 
					= getPathDescriptions()
						.resolve(pathRelatifDescriptionHistoF07);
				
				final ClassLoader classloader 
				= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(pathRelatifContextDescriptionHistoF07.toString());
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierDescriptionHistoF07 
					= new File(uriRessources.getPath());
				
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
	 * Getter du <b>Chemin relatif par rapport au répertoire 
	 * [descriptions de fichier] 
	 * de la description du Darwin.csv</b>.<br/>
	 * Ce chemin est stocké dans <code>application.properties</code> 
	 * dans le classpath sous le contexte.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "Darwin csv/Descriptions en UTF-8/2014-07-19_Description_DarwinCsv_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomDescriptionDarwinCsvEnDur().</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * <li>fabrique un <code>messageIndividuelRapport</code> 
	 * si la clé ou la valeur sont manquantes dans 
	 * <code>application.properties</code>. <br/>
	 * <code>messageIndividuelRapport</code> est null sinon.</li>
	 * <li>ajoute le messageIndividuelRapport à 
	 * <code>rapportConfigurationCsv</code> le cas échéant.<br/> 
	 * <code>rapportConfigurationCsv</code> contient les éventuels 
	 * messages d'erreur de configuration de toutes 
	 * les méthodes de la présente classe.
	 * <br/><code>rapportConfigurationCsv</code> est null 
	 * si il n'y a aucune erreur de configuration.</li>
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

			if (nomDescriptionDarwinCsv == null) {
				
				/* Reset du messageIndividuelRapport. */
				messageIndividuelRapport = null;

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
	 * retourne la clé du 
	 * Chemin relatif par rapport au répertoire 
	 * [descriptions de fichier] 
	 * de la description du DARWIN.CSV<br/>
	 * La clé est stockée dans <code>application_fr_FR.properties</code>.<br/>
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
	 * Fournit une valeur stockée en dur 
	 * dans la classe pour <code>nomDescriptionDarwinCsv</code>.<br/>
	 * <br/>
	 * "Darwin csv/Descriptions en UTF-8/2014-07-19_Description_DarwinCsv_Utf8.csv".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "Darwin csv/Descriptions en UTF-8/2014-07-19_Description_DarwinCsv_Utf8.csv".<br/>
	 */
	private static String fournirNomDescriptionDarwinCsvEnDur() {
		return "Darwin csv/Descriptions en UTF-8/2014-07-19_Description_DarwinCsv_Utf8.csv";
	} // Fin de fournirNomDescriptionDarwinCsvEnDur()._____________________
	

	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 
	 * contenant la description du DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <br/>
	 * context/ressources/Descriptions de fichier/Darwin csv/
	 * Descriptions en UTF-8/2014-07-19_Description_DarwinCsv_Utf8.csv.<br/>
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
			
				final Path pathRelatifDescriptionDarwinCsv 
					= Paths.get(getNomDescriptionDarwinCsv());
				
				final Path pathRelatifContextDescriptionDarwinCsv 
					= getPathDescriptions()
						.resolve(pathRelatifDescriptionDarwinCsv);
				
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(pathRelatifContextDescriptionDarwinCsv.toString());
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierDescriptionDarwinCsv 
					= new File(uriRessources.getPath());

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
	 * Getter du <b>Chemin relatif par rapport au répertoire [descriptions de fichier] 
	 * de la description du Mapping Histo-Hit-Darwin-Isidor</b>.<br/>
	 * Ce chemin est stocké dans <code>application.properties</code> 
	 * dans le classpath sous le contexte.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "Mapping/Descriptions en UTF-8/2014-07-19_Description_Mapping_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomDescriptionMappingEnDur().</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * <li>fabrique un <code>messageIndividuelRapport</code> 
	 * si la clé ou la valeur sont manquantes dans 
	 * <code>application.properties</code>. <br/>
	 * <code>messageIndividuelRapport</code> est null sinon.</li>
	 * <li>ajoute le messageIndividuelRapport à 
	 * <code>rapportConfigurationCsv</code> le cas échéant.<br/> 
	 * <code>rapportConfigurationCsv</code> contient les éventuels 
	 * messages d'erreur de configuration de toutes 
	 * les méthodes de la présente classe.
	 * <br/><code>rapportConfigurationCsv</code> est null 
	 * si il n'y a aucune erreur de configuration.</li>
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

			if (nomDescriptionMapping == null) {

				/* Reset du messageIndividuelRapport. */
				messageIndividuelRapport = null;

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
	 * retourne la clé du 
	 * Chemin relatif par rapport au répertoire [descriptions de fichier] 
	 * de la description du Mapping Histo-Hit-Darwin-Isidor.<br/>
	 * La clé est stockée dans <code>application_fr_FR.properties</code>.<br/>
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
	 * Fournit une valeur stockée en dur 
	 * dans la classe pour <code>nomDescriptionMapping</code>.<br/>
	 * <br/>
	 * "Mapping/Descriptions en UTF-8/2014-07-19_Description_Mapping_Utf8.csv".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "Mapping/Descriptions en UTF-8/2014-07-19_Description_Mapping_Utf8.csv".<br/>
	 */
	private static String fournirNomDescriptionMappingEnDur() {
		return "Mapping/Descriptions en UTF-8/2014-07-19_Description_Mapping_Utf8.csv";
	} // Fin de fournirNomDescriptionMappingEnDur()._______________________
	

	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 
	 * contenant la description du MAPPING entre 
	 * les différents types de fichiers (Histo-Hit-Darwin-Isidor).<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <br/>
	 * "context/ressources/Descriptions de fichier/Mapping/
	 * Descriptions en UTF-8/2014-07-19_Description_Mapping_Utf8.csv".<br/>
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
				
				final Path pathRelatifDescriptionMapping 
				= Paths.get(getNomDescriptionMapping());
			
				final Path pathRelatifContextDescriptionMapping 
					= getPathDescriptions()
						.resolve(pathRelatifDescriptionMapping);
				
				final ClassLoader classloader 
				= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(pathRelatifContextDescriptionMapping.toString());
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierDescriptionMapping 
					= new File(uriRessources.getPath());

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
	 * Getter du Rapport du chargement de la configuration au format csv 
	 * pour toute la classe <code>rapportConfigurationCsv</code>.<br/>
	 * Par exemple : <br/>
	 * "Classe ConfigurationDescriptionsFichiersManager - 
	 * Méthode getNomDescriptionHit() - 
	 * La clé 'application.repertoire.ressources.descriptions.hit' 
	 * n'existe pas dans application_fr_FR.properties;".<br/>
	 * "Classe ConfigurationDescriptionsFichiersManager - 
	 * Méthode getNomDescriptionDarwinCsv() - 
	 * La valeur associée à la clé 
	 * 'application.repertoire.ressources.descriptions.darwincsv' 
	 * n'existe pas (null ou vide) dans application_fr_FR.properties;".<br/>
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
	 * Getter du Message <code>messageIndividuelRapport</code> pour le 
	 * Rapport du chargement de la configuration au format csv 
	 * généré par chaque méthode individuellement.<br/>
	 * Par exemple : <br/>
	 * "Classe ConfigurationDescriptionsFichiersManager - 
	 * Méthode getNomDescriptionHit() - 
	 * La clé 'application.repertoire.ressources.descriptions.hit' 
	 * n'existe pas dans applicationfr_FR.properties;".<br/>
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
			stb.append("_fr_FR.properties");
			
			return stb.toString();
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de creerMessageManqueCle(...).________________________________
	

	
	/**
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
			stb.append("_fr_FR.properties");
			
			return stb.toString();
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de creerMessageManqueValeur(...)._____________________________


	
	/**
	 * Rajoute (à la ligne) le message pMessage au rapport 
	 * de chargement de la configuration au format csv 
	 * <code>rapportConfigurationCsv</code>.<br/>
	 * <br/>
	 * - Ne fait rien si pMessage est blank.<br/>
	 * - Ne Rajoute PAS l'en-tête (avec BOM_UTF-8) 
	 * au rapport de chargement de la configuration.<br/>
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
	 * LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <ul>
	 * <li>LOG.FATAL, rapporte et jette 
	 * une FichierNullRunTimeException si pFile est null.</li>
	 * <li>LOG.FATAL, rapporte et jette 
	 * une FichierInexistantRunTimeException si pFile est inexistant.</li>
	 * <li>LOG.FATAL, rapporte et jette 
	 * une FichierRepertoireRunTimeException si pFile est un répertoire.</li>
	 * <li>LOG.FATAL, rapporte et jette 
	 * une FichierVideRunTimeException si pFile est vide.</li>
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
		
	} // Fin de traiterFichier(...)._______________________________________
	

	
	/**
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
		
	} // Fin de creerMessageMauvaisFichier(...).___________________________
	

	
	/**
	 * retourne le <b>chemin sous forme de String 
	 * du répertoire ressources dans le classpath</b> sous target/classes.<br/>
	 * "D:\Donnees\eclipse\eclipseworkspace\traficweb_v1\
	 * target\classes\ressources"<br/>
	 *
	 * @return : String : 
	 * chemin sous forme de String du répertoire 
	 * ressources dans le classpath.<br/>
	 * 
	 * @throws URISyntaxException 
	 */
	public static String retournerRessourcesSousTargetClasses() 
			throws URISyntaxException {
		
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
			final ClassLoader classloader 
			= Thread.currentThread().getContextClassLoader();
		
			final URL urlRessources 
				= classloader
					.getResource("ressources");
			
			final URI uriRessources = urlRessources.toURI();
					
			final String uriRessourcesString = uriRessources.getPath();
			
			final File ressourcesFile = new File(uriRessourcesString);
			
			final String pathRessourcesString 
				= ressourcesFile.getAbsolutePath();
			
			return pathRessourcesString;

		} // Fin de synchronized.________________________________________

	} // Fin de retournerRessourcesSousTargetClasses().____________________
	

	
	/**
	 * retourne le <b>chemin sous forme de String 
	 * du répertoire classes dans le classpath</b> sous target.<br/>
	 * "D:\Donnees\eclipse\eclipseworkspace\traficweb_v1\
	 * target\classes"<br/>
	 *
	 * @return : String : 
	 * chemin sous forme de String du répertoire 
	 * classes dans le classpath.<br/>
	 * 
	 * @throws URISyntaxException
	 */
	public static String retournerClassesSousTarget() 
			throws URISyntaxException {
		
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
			final String pathRessourcesString 
				= retournerRessourcesSousTargetClasses();
			
			final Path pathRessources = Paths.get(pathRessourcesString);
			
			final Path pathClasses = pathRessources.getParent();
			
			final String pathClassesString = pathClasses.toString();
			
			return pathClassesString;
			
		} // Fin de synchronized.________________________________________

	} // Fin de retournerClassesSousTarget().______________________________
	
	

	/**
	 * retourne le 
	 * <b>path relatif de pFile par rapport à target/classes</b> (contexte).<br/>
	 * <ul>
	 * <li>Par exemple :<br/>
	 * <code>fournirPathRelatifSousTargetClasses(fichierDescriptionHit)</code> 
	 * retourne 
	 * 'ressources/Descriptions de fichier/Hit/Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv'
	 * </li>
	 * </ul>
	 *
	 * @param pFile : File : ressource dans le classpath.<br/>
	 * 
	 * @return Path : path relatif de pFile par rapport à target/classes.<br/>
	 * 
	 * @throws URISyntaxException
	 */
	public static Path fournirPathRelatifSousTargetClasses(final File pFile) 
						throws URISyntaxException {
		
		synchronized (ConfigurationDescriptionsFichiersManager.class) {
			
			final String pathClassesString 
			= ConfigurationDescriptionsFichiersManager
				.retournerClassesSousTarget();
		
			final Path pathClasses = Paths.get(pathClassesString);
			
			final Path pathPFile = pFile.toPath();
			
			final Path pathRelatifPFile = pathClasses.relativize(pathPFile);
			
			return pathRelatifPFile;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de fournirPathRelatifSousTargetClasses(...).__________________	
	


} // FIN DE LA CLASSE ConfigurationDescriptionsFichiersManager.--------------
