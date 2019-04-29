package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesnomenclatures;

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
 * class ConfigurationNomenclaturesHitManager :<br/>
 * Classe UTILITAIRE 
 * chargée de gérer la configuration des 
 * NOMENCLATURES DES CHAMPS A NOMENCLATURE DU FICHIER HIT.<br/>
 * Met à disposition de l'ensemble de l'application 
 * des <b>Singletons</b>.<br/>
 * <br/>
 * <ul>
 * <li>La méthode getCheminNomenclaturesHitUtf8 fournit un Singleton 
 * du chemin vers les nomenclatures encodées en UTF-8 
 * des champs à nomenclature 
 * du fichier HIT.</li>
 * <li>Les méthodes getNomNomenclatureXXX fournissent un singleton  
 * du nom du fichier de nomenclature du champXXX 
 * encodé en UTF-8 dans le HIT.</li>
 * <li>Les méthodes getFichierNomenclatureXXX fournissent un singleton  
 * du fichier de nomenclature du champXXX 
 * encodé en UTF-8 dans le HIT.</li>
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
public final class ConfigurationNomenclaturesHitManager {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * CLASSE_CONFIGURATIONNOMENCLATURESHIT : String :<br/>
	 * "Classe ConfigurationNomenclaturesHitManager".<br/>
	 */
	public static final String CLASSE_CONFIGURATIONNOMENCLATURESHIT 
		= "Classe ConfigurationNomenclaturesHitManager";
	
	
	/**
	 * METHODE_GET_CHEMINNOMENCLATURES_HIT : String :<br/>
	 * "Méthode getCheminNomenclaturesHitUtf8".<br/>
	 */
	public static final String METHODE_GET_CHEMINNOMENCLATURES_HIT 
		= "Méthode getCheminNomenclaturesHitUtf8";

	
	/**
	 * METHODE_GET_NOMNOMENCLATURE_HIT_SENS : String :<br/>
	 * "Méthode getNomNomenclatureHitSens()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_SENS 
		= "Méthode getNomNomenclatureHitSens()";

	
	/**
	 * METHODE_GET_NOMNOMENCLATURE_HIT_NATURE : String :<br/>
	 * "Méthode getNomNomenclatureHitNature()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_NATURE 
		= "Méthode getNomNomenclatureHitNature()";

	
	/**
	 * METHODE_GET_NOMNOMENCLATURE_HIT_CATADMINROUTE : String :<br/>
	 * "Méthode getNomNomenclatureHitCatAdminRoute()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_CATADMINROUTE 
		= "Méthode getNomNomenclatureHitCatAdminRoute()";

	
	/**
	 * METHODE_GET_NOMNOMENCLATURE_HIT_TYPECOMPTAGE : String :<br/>
	 * "Méthode getNomNomenclatureHitTypeComptage()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_TYPECOMPTAGE 
		= "Méthode getNomNomenclatureHitTypeComptage()";

	
	/**
	 * METHODE_GET_NOMNOMENCLATURE_HIT_CLASSEMENTROUTE : String :<br/>
	 * "Méthode getNomNomenclatureHitClassementRoute()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_CLASSEMENTROUTE 
		= "Méthode getNomNomenclatureHitClassementRoute()";

	
	/**
	 * METHODE_GET_NOMNOMENCLATURE_HIT_CLASSELARGEURCHAUSSEEU : String :<br/>
	 * "Méthode getNomNomenclatureHitClasseLargeurChausseeU()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_CLASSELARGEURCHAUSSEEU 
		= "Méthode getNomNomenclatureHitClasseLargeurChausseeU()";

	
	/**
	 * METHODE_GET_NOMNOMENCLATURE_HIT_CLASSELARGEURCHAUSSEESS : String :<br/>
	 * "Méthode getNomNomenclatureHitClasseLargeurChausseesS()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_CLASSELARGEURCHAUSSEESS 
		= "Méthode getNomNomenclatureHitClasseLargeurChausseesS()";

	
	/**
	 * METHODE_GET_NOMNOMENCLATURE_HIT_TYPERESEAU : String :<br/>
	 * "Méthode getNomNomenclatureHitTypeReseau()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_TYPERESEAU 
		= "Méthode getNomNomenclatureHitTypeReseau()";

	
	/**
	 * METHODE_GET_NOMNOMENCLATURE_HIT_PRPK : String :<br/>
	 * "Méthode getNomNomenclatureHitPrPk()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_PRPK 
		= "Méthode getNomNomenclatureHitPrPk()";
	

	
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
		
	// HIT.*********************
	
	/**
	 * cheminNomenclaturesHitUtf8 : String :<br/>
	 * Chemin des nomenclatures en UTF-8 des champs pour les HIT
	 * stocké dans application.properties.<br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8\\".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.hit.utf8".<br/>
	 */
	private static transient String cheminNomenclaturesHitUtf8;
	
	
	/**
	 * nomNomenclatureHitSens : String :<br/>
	 * Nom du fichier de nomenclature du sens pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Sens_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sens.hit".<br/>
	 */
	private static transient String nomNomenclatureHitSens;

	
	/**
	 * fichierNomenclatureHitSensUtf8 : File :<br/>
	 * Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le SENS
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8\\
	 * 2014-07-15_Nomenclature_Sens_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitSensUtf8;

	
	/**
	 * nomNomenclatureHitNature : String :<br/>
	 * Nom du fichier de nomenclature de la nature pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Nature_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.nature.hit".<br/>
	 */
	private static transient String nomNomenclatureHitNature;

	
	/**
	 * fichierNomenclatureHitNatureUtf8 : File :<br/>
	 * Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la NATURE du comptage 
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Nature_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitNatureUtf8;
	

	/**
	 * nomNomenclatureHitCatAdminRoute : String :<br/>
	 * Nom du fichier de nomenclature de la catégorie administrative 
	 * de la route pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Categorie_Adm_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.catadminroute.hit"<br/>	 
	 * */
	private static transient String nomNomenclatureHitCatAdminRoute;

		
	/**
	 * fichierNomenclatureHitCatAdminRouteUtf8 : File :<br/>
	 * Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CATEGORIE ADMINISTRATIVE de la route 
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Categorie_Adm_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitCatAdminRouteUtf8;
	

	/**
	 * nomNomenclatureHitTypeComptage : String :<br/>
	 * Nom du fichier de nomenclature du type de comptage 
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Type_Comptage_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typecomptage.hit"<br/>
	 */
	private static transient String nomNomenclatureHitTypeComptage;

		
	/**
	 * fichierNomenclatureHitTypeComptageUtf8 : File :<br/>
	 * Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le TYPE DE COMPTAGE 
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Type_Comptage_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitTypeComptageUtf8;
	

	/**
	 * nomNomenclatureHitClassementRoute : String :<br/>
	 * Nom du fichier de nomenclature du classement de la route 
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Classement_Route_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classementroute.hit"<br/>
	 */
	private static transient String nomNomenclatureHitClassementRoute;

		
	/**
	 * fichierNomenclatureHitClassementRouteUtf8 : File :<br/>
	 * Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le CLASSEMENT DE LA ROUTE 
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Classement_Route_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitClassementRouteUtf8;
	

	/**
	 * nomNomenclatureHitClasseLargeurChausseeU : String :<br/>
	 * Nom du fichier de nomenclature 
	 * de la classe de largeur de chaussée unique
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Classe_Largeur_Chausse_Unique_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.hit"<br/>
	 */
	private static transient String nomNomenclatureHitClasseLargeurChausseeU;
	
		
	/**
	 * fichierNomenclatureHitClasseLargeurChausseeUUtf8 : File :<br/>
	 * Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE 
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Classe_Largeur_Chausse_Unique_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitClasseLargeurChausseeUUtf8;
	

	/**
	 * nomNomenclatureHitClasseLargeurChausseesS : String :<br/>
	 * Nom du fichier de nomenclature 
	 * de la classe de largeur de chaussées séparées
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Classe_Largeur_Chaussees_Separees_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classelargeurchausseess.hit"<br/>
	 */
	private static transient String nomNomenclatureHitClasseLargeurChausseesS;
	
		
	/**
	 * fichierNomenclatureHitClasseLargeurChausseesSUtf8 : File :<br/>
	 * Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES 
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Classe_Largeur_Chausses_Séparées_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitClasseLargeurChausseesSUtf8;
	

	/**
	 * nomNomenclatureHitTypeReseau : String :<br/>
	 * Nom du fichier de nomenclature du type de réseau
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Type_Reseau_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typereseau.hit"<br/>
	 */
	private static transient String nomNomenclatureHitTypeReseau;
	
		
	/**
	 * fichierNomenclatureHitTypeReseauUtf8 : File :<br/>
	 * Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le TYPE DE RESEAU 
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Type_Reseau_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitTypeReseauUtf8;
	

	/**
	 * nomNomenclatureHitPrPk : String :<br/>
	 * Nom du fichier de nomenclature du type PR/PK
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_PR_PK_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.prpk.hit"<br/>
	 */
	private static transient String nomNomenclatureHitPrPk;
	
		
	/**
	 * fichierNomenclatureHitPrPkUtf8 : File :<br/>
	 * Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le TYPE PR/PK
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_PR_PK_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitPrPkUtf8;
	

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
			.getLog(ConfigurationNomenclaturesHitManager.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR ConfigurationNomenclaturesHitManager() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour interdire l'instanciation.<br/>
	 * <br/>
	 */
	private ConfigurationNomenclaturesHitManager() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________

	

	/**
	 * method getCheminNomenclaturesHitUtf8() :<br/>
	 * Getter du Chemin des nomenclatures en UTF-8 des champs pour les HIT
	 * stocké dans application.properties.<br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8\\".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirCheminNomenclaturesHitUtf8EnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.chemin.hit.utf8".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirCheminNomenclaturesHitUtf8EnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirCheminNomenclaturesHitUtf8EnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return cheminNomenclaturesHitUtf8 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getCheminNomenclaturesHitUtf8() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (cheminNomenclaturesHitUtf8 == null) {

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
										fournirCleCheminNomenclaturesHitUtf8());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_CHEMINNOMENCLATURES_HIT,
								fournirCleCheminNomenclaturesHitUtf8(),
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
							cheminNomenclaturesHitUtf8 
								= fournirCheminNomenclaturesHitUtf8EnDur();

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

							cheminNomenclaturesHitUtf8 = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_CHEMINNOMENCLATURES_HIT,
								fournirCleCheminNomenclaturesHitUtf8(),
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
						cheminNomenclaturesHitUtf8 
							= fournirCheminNomenclaturesHitUtf8EnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					cheminNomenclaturesHitUtf8 
						= fournirCheminNomenclaturesHitUtf8EnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (cheminNomenclaturesHitUtf8 == null)._________

			return cheminNomenclaturesHitUtf8;

		} // Fin de synchronized.________________________________________

	} // Fin de getCheminNomenclaturesHitUtf8().___________________________

	
	
	/**
	 * method fournirCleCheminNomenclaturesHitUtf8() :<br/>
	 * clé du chemin des chemins des nomenclatures en UTF-8 
	 * des HIT dans 
	 * application_fr_FR.properties.<br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8\\".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.hit.utf8".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.chemin.hit.utf8".<br/>
	 */
	private static String fournirCleCheminNomenclaturesHitUtf8() {
		return "application.repertoire.ressources.nomenclatures.chemin.hit.utf8";
	} // Fin de fournirCleCheminNomenclaturesHitUtf8().____________________
	

	
	/**
	 * method fournirCheminNomenclaturesHitUtf8EnDur() :<br/>
	 * Fournit une valeur stockée en dur 
	 * dans la classe pour chemins des nomenclatures en UTF-8 
	 * des HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8\\".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8\\".<br/>
	 */
	private static String fournirCheminNomenclaturesHitUtf8EnDur() {
		return "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8";
	} // Fin de fournirCheminNomenclaturesHitUtf8EnDur().__________________
	


	/**
	 * method getNomNomenclatureHitSens() :<br/>
	 * Getter du Nom du fichier de nomenclature 
	 * du SENS 
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Sens_Hit_Utf8.csv".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitSensEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.sens.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitSensEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitSensEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitSens : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitSens() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitSens == null) {

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
										fournirCleNomNomenclatureHitSens());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HIT_SENS,
								fournirCleNomNomenclatureHitSens(),
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
							nomNomenclatureHitSens 
								= fournirNomNomenclatureHitSensEnDur();

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

							nomNomenclatureHitSens = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_SENS,
								fournirCleNomNomenclatureHitSens(),
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
						nomNomenclatureHitSens 
							= fournirNomNomenclatureHitSensEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitSens 
						= fournirNomNomenclatureHitSensEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitSens == null)._________

			return nomNomenclatureHitSens;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitSens()._______________________________


	
	/**
	 * method fournirCleNomNomenclatureHitSens() :<br/>
	 * clé du nom de la nomenclature en UTF-8 
	 * du SENS 
	 * dans le HIT 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_Sens_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sens.hit".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.sens.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitSens() {
		return "application.repertoire.ressources.nomenclatures.sens.hit";
	} // Fin de fournirCleNomNomenclatureHitSens().________________________
	

	
	/**
	 * method fournirNomNomenclatureHitSensEnDur() :<br/>
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le Nom du fichier de nomenclature en UTF-8 
	 * concernant le SENS 
	 * dans un HIT.<br/>
	 * <br/>
	 *
	 * @return : String : "2014-07-15_Nomenclature_Sens_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitSensEnDur() {
		return "2014-07-15_Nomenclature_Sens_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitSensEnDur().______________________

	
	
	/**
	 * method getFichierNomenclatureHitSensUtf8() :<br/>
	 * Fournit le Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le SENS
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8\\
	 * 2014-07-15_Nomenclature_Sens_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitSensUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitSensUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitSensUtf8 == null) {
								
				fichierNomenclatureHitSensUtf8 
				= new File(getCheminNomenclaturesHitUtf8() 
						+ getNomNomenclatureHitSens());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitSensUtf8
						, "Méthode getFichierNomenclatureHitSensUtf8()");
			}
			
			return fichierNomenclatureHitSensUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitSensUtf8()._______________________
	

	
	/**
	 * method getNomNomenclatureHitNature() :<br/>
	 * Getter du Nom du fichier de nomenclature 
	 * de la NATURE du comptage 
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Nature_Hit_Utf8.csv".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitNatureEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.nature.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitNatureEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitNatureEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitNature : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitNature() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitNature == null) {

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
										fournirCleNomNomenclatureHitNature());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HIT_NATURE,
								fournirCleNomNomenclatureHitNature(),
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
							nomNomenclatureHitNature 
								= fournirNomNomenclatureHitNatureEnDur();

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

							nomNomenclatureHitNature = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_NATURE,
								fournirCleNomNomenclatureHitNature(),
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
						nomNomenclatureHitNature 
							= fournirNomNomenclatureHitNatureEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitNature 
						= fournirNomNomenclatureHitNatureEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitNature == null)._________

			return nomNomenclatureHitNature;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitNature()._____________________________


	
	/**
	 * method fournirCleNomNomenclatureHitNature() :<br/>
	 * clé du nom de la nomenclature en UTF-8 
	 * de NATURE du comptage 
	 * dans le HIT 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_Nature_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.nature.hit".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.nature.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitNature() {
		return "application.repertoire.ressources.nomenclatures.nature.hit";
	} // Fin de fournirCleNomNomenclatureHitNature().______________________
	

	
	/**
	 * method fournirNomNomenclatureHitNatureEnDur() :<br/>
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le Nom du fichier de nomenclature en UTF-8 
	 * concernant la NATURE du comptage
	 * dans un HIT.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_Nature_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitNatureEnDur() {
		return "2014-07-15_Nomenclature_Nature_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitNatureEnDur().____________________
	
	
	
	/**
	 * method getFichierNomenclatureHitNatureUtf8() :<br/>
	 * Fournit le Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la NATURE du comptage 
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Nature_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitNatureUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitNatureUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitNatureUtf8 == null) {
								
				fichierNomenclatureHitNatureUtf8 
				= new File(getCheminNomenclaturesHitUtf8() 
						+ getNomNomenclatureHitNature());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitNatureUtf8
						, "Méthode getFichierNomenclatureHitNatureUtf8()");
			}
			
			return fichierNomenclatureHitNatureUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitNatureUtf8()._______________________
	


	/**
	 * method getNomNomenclatureHitCatAdminRoute() :<br/>
	 * Getter Nom du fichier de nomenclature 
	 * de la CATEGORIE ADMINISTRATIVE de la route 
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Categorie_Adm_Hit_Utf8.csv".<br/> 
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitCatAdminRouteEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.catadminroute.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitCatAdminRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitCatAdminRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitCatAdminRoute : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitCatAdminRoute() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitCatAdminRoute == null) {

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
										fournirCleNomNomenclatureHitCatAdminRoute());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HIT_CATADMINROUTE,
								fournirCleNomNomenclatureHitCatAdminRoute(),
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
							nomNomenclatureHitCatAdminRoute 
								= fournirNomNomenclatureHitCatAdminRouteEnDur();

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

							nomNomenclatureHitCatAdminRoute = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_CATADMINROUTE,
								fournirCleNomNomenclatureHitCatAdminRoute(),
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
						nomNomenclatureHitCatAdminRoute 
							= fournirNomNomenclatureHitCatAdminRouteEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitCatAdminRoute 
						= fournirNomNomenclatureHitCatAdminRouteEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitCatAdminRoute == null)._________

			return nomNomenclatureHitCatAdminRoute;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitCatAdminRoute().______________________


	
	/**
	 * method fournirCleNomNomenclatureHitCatAdminRoute() :<br/>
	 * clé du nom de la nomenclature en UTF-8 
	 * de CATEGORIE ADMINISTRATIVE de la route 
	 * dans le HIT 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_Categorie_Adm_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.catadminroute.hit".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.catadminroute.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitCatAdminRoute() {
		return "application.repertoire.ressources.nomenclatures.catadminroute.hit";
	} // Fin de fournirCleNomNomenclatureHitCatAdminRoute()._______________
	

	
	/**
	 * method fournirNomNomenclatureHitCatAdminRouteEnDur() :<br/>
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le Nom du fichier de nomenclature en UTF-8 
	 * concernant la CATEGORIE ADMINISTRATIVE de la route
	 * dans un HIT.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_Categorie_Adm_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitCatAdminRouteEnDur() {
		return "2014-07-15_Nomenclature_Categorie_Adm_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitCatAdminRouteEnDur()._____________
	

	
	/**
	 * method getFichierNomenclatureHitCatAdminRouteUtf8() :<br/>
	 * Fournit le Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CATEGORIE ADMINISTRATIVE de la route 
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Categorie_Adm_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitCatAdminRouteUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitCatAdminRouteUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitCatAdminRouteUtf8 == null) {
								
				fichierNomenclatureHitCatAdminRouteUtf8 
				= new File(getCheminNomenclaturesHitUtf8() 
						+ getNomNomenclatureHitCatAdminRoute());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitCatAdminRouteUtf8
						, "Méthode getFichierNomenclatureHitCatAdminRouteUtf8()");
			}
			
			return fichierNomenclatureHitCatAdminRouteUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitCatAdminRouteUtf8().______________
	

	
	/**
	 * method getNomNomenclatureHitTypeComptage() :<br/>
	 * Getter du Nom du fichier de nomenclature 
	 * du TYPE DE COMPTAGE 
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Type_Comptage_Hit_Utf8.csv".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitTypeComptageEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.typecomptage.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitTypeComptageEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitTypeComptageEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitTypeComptage : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitTypeComptage() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitTypeComptage == null) {

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
										fournirCleNomNomenclatureHitTypeComptage());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HIT_TYPECOMPTAGE,
								fournirCleNomNomenclatureHitTypeComptage(),
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
							nomNomenclatureHitTypeComptage 
								= fournirNomNomenclatureHitTypeComptageEnDur();

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

							nomNomenclatureHitTypeComptage = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_TYPECOMPTAGE,
								fournirCleNomNomenclatureHitTypeComptage(),
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
						nomNomenclatureHitTypeComptage 
							= fournirNomNomenclatureHitTypeComptageEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitTypeComptage 
						= fournirNomNomenclatureHitTypeComptageEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitTypeComptage == null)._________

			return nomNomenclatureHitTypeComptage;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitTypeComptage()._______________________


	
	/**
	 * method fournirCleNomNomenclatureHitTypeComptage() :<br/>
	 * clé du nom de la nomenclature en UTF-8 
	 * de TYPE DE COMPTAGE 
	 * dans le HIT 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_Type_Comptage_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typecomptage.hit".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.typecomptage.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitTypeComptage() {
		return "application.repertoire.ressources.nomenclatures.typecomptage.hit";
	} // Fin de fournirCleNomNomenclatureHitTypeComptage().________________
	

	
	/**
	 * method fournirNomNomenclatureHitTypeComptageEnDur() :<br/>
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le Nom du fichier de nomenclature en UTF-8 
	 * concernant le TYPE DE COMPTAGE
	 * dans un HIT.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_Type_Comptage_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitTypeComptageEnDur() {
		return "2014-07-15_Nomenclature_Type_Comptage_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitTypeComptageEnDur().______________


	
	/**
	 * method getFichierNomenclatureHitTypeComptageUtf8() :<br/>
	 * Fournit le Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le TYPE DE COMPTAGE 
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Type_Comptage_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitTypeComptageUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitTypeComptageUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitTypeComptageUtf8 == null) {
								
				fichierNomenclatureHitTypeComptageUtf8 
				= new File(getCheminNomenclaturesHitUtf8() 
						+ getNomNomenclatureHitTypeComptage());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitTypeComptageUtf8
						, "Méthode getFichierNomenclatureHitTypeComptageUtf8()");
			}
			
			return fichierNomenclatureHitTypeComptageUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitTypeComptageUtf8()._______________
	

	
	/**
	 * method getNomNomenclatureHitClassementRoute() :<br/>
	 * Getter du Nom du fichier de nomenclature 
	 * du CLASSEMENT DE LA ROUTE 
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Classement_Route_Hit_Utf8.csv".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitClassementRouteEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.classementroute.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitClassementRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitClassementRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitClassementRoute : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitClassementRoute() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitClassementRoute == null) {

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
										fournirCleNomNomenclatureHitClassementRoute());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HIT_CLASSEMENTROUTE,
								fournirCleNomNomenclatureHitClassementRoute(),
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
							nomNomenclatureHitClassementRoute 
								= fournirNomNomenclatureHitClassementRouteEnDur();

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

							nomNomenclatureHitClassementRoute = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_CLASSEMENTROUTE,
								fournirCleNomNomenclatureHitClassementRoute(),
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
						nomNomenclatureHitClassementRoute 
							= fournirNomNomenclatureHitClassementRouteEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitClassementRoute 
						= fournirNomNomenclatureHitClassementRouteEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitClassementRoute == null)._________

			return nomNomenclatureHitClassementRoute;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitClassementRoute().____________________



	/**
	 * method fournirCleNomNomenclatureHitClassementRoute() :<br/>
	 * clé du nom de la nomenclature en UTF-8 
	 * du CLASSEMENT DE LA ROUTE
	 * dans le HIT 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_Classement_Route_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classementroute.hit".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.typecomptage.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitClassementRoute() {
		return "application.repertoire.ressources.nomenclatures.classementroute.hit";
	} // Fin de fournirCleNomNomenclatureHitClassementRoute()._____________
	

	
	/**
	 * method fournirNomNomenclatureHitClassementRouteEnDur() :<br/>
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le Nom du fichier de nomenclature en UTF-8 
	 * concernant le CLASSEMENT DE LA ROUTE
	 * dans un HIT.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_Classement_Route_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitClassementRouteEnDur() {
		return "2014-07-15_Nomenclature_Classement_Route_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitClassementRouteEnDur().___________


	
	/**
	 * method getFichierNomenclatureHitClassementRouteUtf8() :<br/>
	 * Fournit le Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le CLASSEMENT DE LA ROUTE 
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Classement_Route_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitClassementRouteUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitClassementRouteUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitClassementRouteUtf8 == null) {
								
				fichierNomenclatureHitClassementRouteUtf8 
				= new File(getCheminNomenclaturesHitUtf8() 
						+ getNomNomenclatureHitClassementRoute());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitClassementRouteUtf8
						, "Méthode getFichierNomenclatureHitClassementRouteUtf8()");
			}
			
			return fichierNomenclatureHitClassementRouteUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitClassementRouteUtf8()._______________
	

	
	/**
	 * method getNomNomenclatureHitClasseLargeurChausseeU() :<br/>
	 * Getter du Nom du fichier de nomenclature 
	 * de la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Classe_Largeur_Chausse_Unique_Hit_Utf8.csv".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitClasseLargeurChausseeUEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitClasseLargeurChausseeUEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitClasseLargeurChausseeUEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitClasseLargeurChausseeU : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitClasseLargeurChausseeU() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitClasseLargeurChausseeU == null) {

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
										fournirCleNomNomenclatureHitClasseLargeurChausseeU());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HIT_CLASSELARGEURCHAUSSEEU,
								fournirCleNomNomenclatureHitClasseLargeurChausseeU(),
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
							nomNomenclatureHitClasseLargeurChausseeU 
								= fournirNomNomenclatureHitClasseLargeurChausseeUEnDur();

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

							nomNomenclatureHitClasseLargeurChausseeU 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_CLASSELARGEURCHAUSSEEU,
								fournirCleNomNomenclatureHitClasseLargeurChausseeU(),
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
						nomNomenclatureHitClasseLargeurChausseeU 
							= fournirNomNomenclatureHitClasseLargeurChausseeUEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitClasseLargeurChausseeU 
						= fournirNomNomenclatureHitClasseLargeurChausseeUEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitClasseLargeurChausseeU == null)._________

			return nomNomenclatureHitClasseLargeurChausseeU;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitClasseLargeurChausseeU()._____________



	/**
	 * method fournirCleNomNomenclatureHitClasseLargeurChausseeU() :<br/>
	 * clé du nom de la nomenclature en UTF-8 
	 * de la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE
	 * dans le HIT 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_Classe_Largeur_Chausse_Unique_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.hit".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitClasseLargeurChausseeU() {
		return "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.hit";
	} // Fin de fournirCleNomNomenclatureHitClasseLargeurChausseeU().______
	
	
	
	/**
	 * method fournirNomNomenclatureHitClasseLargeurChausseeUEnDur() :<br/>
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le Nom du fichier de nomenclature en UTF-8 
	 * concernant la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE
	 * dans un HIT.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_Classe_Largeur_Chausse_Unique_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitClasseLargeurChausseeUEnDur() {
		return "2014-07-15_Nomenclature_Classe_Largeur_Chausse_Unique_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitClasseLargeurChausseeUEnDur().____
	
	
	
	/**
	 * method getFichierNomenclatureHitClasseLargeurChausseeUUtf8() :<br/>
	 * Fournit le Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE 
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Classe_Largeur_Chausse_Unique_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitClasseLargeurChausseeUUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitClasseLargeurChausseeUUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitClasseLargeurChausseeUUtf8 == null) {
								
				fichierNomenclatureHitClasseLargeurChausseeUUtf8 
				= new File(getCheminNomenclaturesHitUtf8() 
						+ getNomNomenclatureHitClasseLargeurChausseeU());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitClasseLargeurChausseeUUtf8
						, "Méthode getFichierNomenclatureHitClasseLargeurChausseeUUtf8()");
			}
			
			return fichierNomenclatureHitClasseLargeurChausseeUUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitClasseLargeurChausseeUUtf8()._______________
	


	/**
	 * method getNomNomenclatureHitClasseLargeurChausseesS() :<br/>
	 * Getter du Nom du fichier de nomenclature 
	 * de la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Classe_Largeur_Chaussees_Separees_Hit_Utf8.csv".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitClasseLargeurChausseesSEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.classelargeurchausseess.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitClasseLargeurChausseesSEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitClasseLargeurChausseesSEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitClasseLargeurChausseesS : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitClasseLargeurChausseesS() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitClasseLargeurChausseesS == null) {

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
										fournirCleNomNomenclatureHitClasseLargeurChausseesS());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HIT_CLASSELARGEURCHAUSSEESS,
								fournirCleNomNomenclatureHitClasseLargeurChausseesS(),
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
							nomNomenclatureHitClasseLargeurChausseesS 
								= fournirNomNomenclatureHitClasseLargeurChausseesSEnDur();

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

							nomNomenclatureHitClasseLargeurChausseesS 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_CLASSELARGEURCHAUSSEESS,
								fournirCleNomNomenclatureHitClasseLargeurChausseesS(),
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
						nomNomenclatureHitClasseLargeurChausseesS 
							= fournirNomNomenclatureHitClasseLargeurChausseesSEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitClasseLargeurChausseesS 
						= fournirNomNomenclatureHitClasseLargeurChausseesSEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitClasseLargeurChausseesS == null)._________

			return nomNomenclatureHitClasseLargeurChausseesS;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitClasseLargeurChausseesS().____________


	
	/**
	 * method fournirCleNomNomenclatureHitClasseLargeurChausseesS() :<br/>
	 * clé du nom de la nomenclature en UTF-8 
	 * de la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES
	 * dans le HIT 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_Classe_Largeur_Chaussees_Separees_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classelargeurchausseess.hit".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.classelargeurchausseess.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitClasseLargeurChausseesS() {
		return "application.repertoire.ressources.nomenclatures.classelargeurchausseess.hit";
	} // Fin de fournirCleNomNomenclatureHitClasseLargeurChausseesS()._____
	

	
	/**
	 * method fournirNomNomenclatureHitClasseLargeurChausseesSEnDur() :<br/>
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le Nom du fichier de nomenclature en UTF-8 
	 * concernant la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES
	 * dans un HIT.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_Classe_Largeur_Chaussees_Separees_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitClasseLargeurChausseesSEnDur() {
		return "2014-07-15_Nomenclature_Classe_Largeur_Chaussees_Separees_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitClasseLargeurChausseesSEnDur().___


	
	/**
	 * method getFichierNomenclatureHitClasseLargeurChausseesSUtf8() :<br/>
	 * Fournit le Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES 
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Classe_Largeur_Chausses_Séparées_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitClasseLargeurChausseesSUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitClasseLargeurChausseesSUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitClasseLargeurChausseesSUtf8 == null) {
								
				fichierNomenclatureHitClasseLargeurChausseesSUtf8 
				= new File(getCheminNomenclaturesHitUtf8() 
						+ getNomNomenclatureHitClasseLargeurChausseesS());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitClasseLargeurChausseesSUtf8
						, "Méthode getFichierNomenclatureHitClasseLargeurChausseesSUtf8()");
			}
			
			return fichierNomenclatureHitClasseLargeurChausseesSUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitClasseLargeurChausseesSUtf8().____
	


	/**
	 * method getNomNomenclatureHitTypeReseau() :<br/>
	 * Getter du Nom du fichier de nomenclature 
	 * du TYPE DE RESEAU
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Type_Reseau_Hit_Utf8.csv".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitTypeReseauEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.typereseau.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitTypeReseauEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitTypeReseauEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitTypeReseau : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitTypeReseau() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitTypeReseau == null) {

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
										fournirCleNomNomenclatureHitTypeReseau());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HIT_TYPERESEAU,
								fournirCleNomNomenclatureHitTypeReseau(),
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
							nomNomenclatureHitTypeReseau 
								= fournirNomNomenclatureHitTypeReseauEnDur();

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

							nomNomenclatureHitTypeReseau 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_TYPERESEAU,
								fournirCleNomNomenclatureHitTypeReseau(),
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
						nomNomenclatureHitTypeReseau 
							= fournirNomNomenclatureHitTypeReseauEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitTypeReseau 
						= fournirNomNomenclatureHitTypeReseauEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitTypeReseau == null)._________

			return nomNomenclatureHitTypeReseau;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitTypeReseau().__________________________


	
	/**
	 * method fournirCleNomNomenclatureHitTypeReseau() :<br/>
	 * clé du nom de la nomenclature en UTF-8 
	 * du TYPE DE RESEAU
	 * dans le HIT 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_Type_Reseau_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typereseau.hit".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.typereseau.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitTypeReseau() {
		return "application.repertoire.ressources.nomenclatures.typereseau.hit";
	} // Fin de fournirCleNomNomenclatureHitTypeReseau().__________________
	

	
	/**
	 * method fournirNomNomenclatureHitTypeReseauEnDur() :<br/>
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le Nom du fichier de nomenclature en UTF-8 
	 * concernant le TYPE DE RESEAU
	 * dans un HIT.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_Type_Reseau_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitTypeReseauEnDur() {
		return "2014-07-15_Nomenclature_Type_Reseau_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitTypeReseauEnDur().________________


	
	/**
	 * method getFichierNomenclatureHitTypeReseauUtf8() :<br/>
	 * Fournit le Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le TYPE DE RESEAU 
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_Type_Reseau_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitTypeReseauUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitTypeReseauUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitTypeReseauUtf8 == null) {
								
				fichierNomenclatureHitTypeReseauUtf8 
				= new File(getCheminNomenclaturesHitUtf8() 
						+ getNomNomenclatureHitTypeReseau());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitTypeReseauUtf8
						, "Méthode getFichierNomenclatureHitTypeReseauUtf8()");
			}
			
			return fichierNomenclatureHitTypeReseauUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitTypeReseauUtf8()._______________
	


	/**
	 * method getNomNomenclatureHitPrPk() :<br/>
	 * Getter du Nom du fichier de nomenclature 
	 * du TYPE PR/PK
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_PR_PK_Hit_Utf8.csv".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li><br/>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitPrPkEnDur().</li><br/>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li><br/>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.prpk.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitPrPkEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitPrPkEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitPrPk : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitPrPk() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitPrPk == null) {

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
										fournirCleNomNomenclatureHitPrPk());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
									METHODE_GET_NOMNOMENCLATURE_HIT_PRPK,
								fournirCleNomNomenclatureHitPrPk(),
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
							nomNomenclatureHitPrPk 
								= fournirNomNomenclatureHitPrPkEnDur();

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

							nomNomenclatureHitPrPk 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_PRPK,
								fournirCleNomNomenclatureHitPrPk(),
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
						nomNomenclatureHitPrPk 
							= fournirNomNomenclatureHitPrPkEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitPrPk 
						= fournirNomNomenclatureHitPrPkEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitPrPk == null)._________

			return nomNomenclatureHitPrPk;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitPrPk()._______________________________


	
	/**
	 * method fournirCleNomNomenclatureHitPrPk() :<br/>
	 * clé du nom de la nomenclature en UTF-8 
	 * du TYPE PR/PK
	 * dans le HIT 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_PR_PK_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.prpk.hit".<br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.prpk.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitPrPk() {
		return "application.repertoire.ressources.nomenclatures.prpk.hit";
	} // Fin de fournirCleNomNomenclatureHitPrPk().________________________
	

	
	/**
	 * method fournirNomNomenclatureHitPrPkEnDur() :<br/>
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le Nom du fichier de nomenclature en UTF-8 
	 * concernant le TYPE PR/PK
	 * dans un HIT.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_PR_PK_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitPrPkEnDur() {
		return "2014-07-15_Nomenclature_PR_PK_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitPrPkEnDur().______________________


	
	/**
	 * method getFichierNomenclatureHitPrPkUtf8() :<br/>
	 * Fournit le Fichier sur disque encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * le TYPE PR/PK
	 * dans un HIT.<br/>
	 * <br/>
	 * ".\\ressources\\Nomenclatures\\Hit\\Nomenclatures en UTF-8
	 * \\2014-07-15_Nomenclature_PR_PK_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitPrPkUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitPrPkUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitPrPkUtf8 == null) {
								
				fichierNomenclatureHitPrPkUtf8 
				= new File(getCheminNomenclaturesHitUtf8() 
						+ getNomNomenclatureHitPrPk());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitPrPkUtf8
						, "Méthode getFichierNomenclatureHitPrPkUtf8()");
			}
			
			return fichierNomenclatureHitPrPkUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitPrPkUtf8()._______________________
	


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
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
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
		synchronized (ConfigurationNomenclaturesHitManager.class) {
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
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESHIT);
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
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESHIT);
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
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
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
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
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
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESHIT);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMethode);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMessage);
			
			return stb.toString();
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de creerMessageMauvaisFichier(
	 // String pMethode
	// , String pMessage)._________________________________________________
	

	
} // FIN DE LA CLASSE ConfigurationNomenclaturesHitManager.------------------
