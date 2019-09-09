package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.metier.sections;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.sections.SectionHitGestionnairePreferencesControles;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.sections.SectionHitGestionnairePreferencesRG;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.AbstractGestionnaireRG;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.EnumTypesValidation;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.LigneRG;
import levy.daniel.application.model.metier.sections.impl.SectionHit;

/**
 * CLASSE SectionHitGestionnaireRG :<br/>
 * Classe chargée de fournir toutes les REGLES DE GESTION (RG) 
 * implémentées dans la présente application pour un OBJET METIER donné 
 * (ici {@link SectionHit}).<br/>
 * <ul>
 * <li>permet à la Maîtrise d'Ouvrage (MOA) de 
 * <b>connaître instantanémént la liste des RGs implémentées</b> 
 * et leur <b>état d'activation</b> (RG active/non active paramétrable 
 * via une <i>gestion de préférences</i>).</li>
 * <li>retourne via sa méthode <code><b>getMapRG()</b></code> 
 * la Map&lt;String, LigneRG&gt; des RGs implémentées 
 * dans la présente application et contrôlées 
 * dans un OBJETMETIERValideurService.</li>
 * <li>retourne via sa méthode 
 * <code><b>afficherListeRGImplementeesCsv()</b></code> 
 * une String CSV pour l'affichage des RGs implémentées 
 * dans la présente application et contrôlées 
 * dans un OBJETMETIERValideurService.</li>
 * </ul>
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
 * @since 15 août 2019
 *
 */
public class SectionHitGestionnaireRG extends AbstractGestionnaireRG {

	// ************************ATTRIBUTS************************************/

	/**
	 * Path absolu du .properties contenant les 
	 * Booleans de validation des RG sous forme de Path.
	 */
	public static transient Path pathAbsoluPropertiesRG;
	
	/**
	 * Path absolu du .properties contenant les 
	 * Booleans de validation des RG sous forme de String.
	 */
	public static transient String pathAbsoluPropertiesRGString;

	/**
	 * "doit être renseigné".
	 */
	public static final String DOIT_ETRE_RENSEIGNE = "doit être renseigné";
	
	/**
	 * "doit être renseigné (obligatoire)".
	 */
	public static final String DOIT_ETRE_RENSEIGNE_OBLIGATOIRE 
		= "doit être renseigné (obligatoire)";
	
	/**
	 * "doit être renseignée".
	 */
	public static final String DOIT_ETRE_RENSEIGNEE = "doit être renseignée";
	
	/**
	 * "doit être renseignée (obligatoire)".
	 */
	public static final String DOIT_ETRE_RENSEIGNEE_OBLIGATOIRE 
		= "doit être renseignée (obligatoire)";
	
	/**
	 * Nom de l'objet métier concerné par ces Règles de Gestion (RG).<br/>
	 * "SectionHit".<br/>
	 */
	public static final String NOM_OBJETMETIER 
		= "SectionHit";
	
	 /**
	 * "model/services/valideurs/metier/sections/impl/
	 * SectionHitValideurService.java".<br/>
	 */
	public static final String CLASSE_CONTROLE 
		= "model/services/valideurs/metier/sections/impl/SectionHitValideurService.java";
	
	/**
	 * Chemin relatif par rapport au répertoire "ressources_externes" 
	 * du <b>répertoire</b> du fichier properties contenant 
	 * les RG de l'objet métier.<br/>
	 * "preferences/metier/sections".<br/>
	 */
	public static final String CHEMIN_RELATIF_FICHIER_PROPERTIES_RG 
		= "preferences/metier/sections";

	
	/* 1 - numDepartement. ***********************/	
	/**
	 * "numDepartement".<br/>
	 */
	public static final String ATTRIBUT_NUMDEPARTEMENT 
		= "numDepartement";

	/**
	 * "RG_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01 : le numéro de 
	 * département de la section HIT doit être renseigné (obligatoire)".<br/>
	 */
	public static final String RG_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01 
		= "RG_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01 : "
				+ "le numéro de département de la section HIT doit être renseigné "
				+ "(obligatoire)";
	
	/**
	 * "RG_SECTIONHIT_NUMDEPARTEMENT_REGEX_02 : 
	 * le numéro de département doit comporter exactement 3 chiffres 
	 * ('030' pour 03, '300' pour 30, '972' pour 972)".<br/>
	 */
	public static final String RG_SECTIONHIT_NUMDEPARTEMENT_REGEX_02 
		= "RG_SECTIONHIT_NUMDEPARTEMENT_REGEX_02 : "
				+ "le numéro de département doit comporter exactement "
				+ "3 chiffres ('030' pour 03, '300' pour 30, '972' pour 972)";

	/* 2 - numSection. **************/	
	/**
	 * "numSection".<br/>
	 */
	public static final String ATTRIBUT_NUMSECTION 
		= "numSection";

	/**
	 * "RG_SECTIONHIT_NUMSECTION_RENSEIGNE_01 : 
	 * le numéro de section de la section HIT
	 * doit être renseigné (obligatoire)".
	 */
	public static final String RG_SECTIONHIT_NUMSECTION_RENSEIGNE_01 
		= "RG_SECTIONHIT_NUMSECTION_RENSEIGNE_01 : "
				+ "le numéro de section de la section HIT "
				+ DOIT_ETRE_RENSEIGNE_OBLIGATOIRE;

	/**
	 * "RG_SECTIONHIT_NUMSECTION_REGEX_02 : 
	 * le numéro de section doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_NUMSECTION_REGEX_02 
		= "RG_SECTIONHIT_NUMSECTION_REGEX_02 : "
				+ "le numéro de section doit comporter exactement 6 chiffres";

	/* 3 - sens. *****************/	
	/**
	 * "sens".<br/>
	 */
	public static final String ATTRIBUT_SENS 
		= "sens";

	/**
	 * "RG_SECTIONHIT_SENS_RENSEIGNE_01 : 
	 * le sens de la section HIT
	 * doit être renseigné (obligatoire)".
	 */
	public static final String RG_SECTIONHIT_SENS_RENSEIGNE_01 
		= "RG_SECTIONHIT_SENS_RENSEIGNE_01 : "
				+ "le sens de la section HIT "
				+ DOIT_ETRE_RENSEIGNE_OBLIGATOIRE;

	/**
	 * "RG_SECTIONHIT_SENS_REGEX_02 : 
	 * le sens doit comporter exactement 1 chiffre".
	 */
	public static final String RG_SECTIONHIT_SENS_REGEX_02 
		= "RG_SECTIONHIT_SENS_REGEX_02 : "
				+ "le sens doit comporter exactement 1 chiffre";
	
	/**
	 * "RG_SECTIONHIT_SENS_NOMENCLATURE_03 : 
	 * le sens doit respecter une nomenclature [1, 2, 3, 4, 5]".
	 */
	public static final String RG_SECTIONHIT_SENS_NOMENCLATURE_03 
		= "RG_SECTIONHIT_SENS_NOMENCLATURE_03 : "
				+ "le sens doit respecter une nomenclature [1, 2, 3, 4, 5]";

	/* 4 - nature. ***************/	
	/**
	 * "nature".<br/>
	 */
	public static final String ATTRIBUT_NATURE 
		= "nature";

	/**
	 * "RG_SECTIONHIT_NATURE_RENSEIGNE_01 : 
	 * la nature de la section HIT
	 * doit être renseignée (obligatoire)".
	 */
	public static final String RG_SECTIONHIT_NATURE_RENSEIGNE_01 
		= "RG_SECTIONHIT_NATURE_RENSEIGNE_01 : "
				+ "la nature de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE_OBLIGATOIRE;

	/**
	 * "RG_SECTIONHIT_NATURE_REGEX_02 : 
	 * la nature doit comporter exactement 1 chiffre".
	 */
	public static final String RG_SECTIONHIT_NATURE_REGEX_02 
		= "RG_SECTIONHIT_NATURE_REGEX_02 : "
				+ "la nature doit comporter exactement 1 chiffre";
	
	/**
	 * "RG_SECTIONHIT_NATURE_NOMENCLATURE_03 : 
	 * la nature doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]".
	 */
	public static final String RG_SECTIONHIT_NATURE_NOMENCLATURE_03 
		= "RG_SECTIONHIT_NATURE_NOMENCLATURE_03 : "
				+ "la nature doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]";

	/* 5 - classe. ****************/	
	/**
	 * "classe".<br/>
	 */
	public static final String ATTRIBUT_CLASSE 
		= "classe";

	/**
	 * "RG_SECTIONHIT_CLASSE_RENSEIGNE_01 : 
	 * la classe de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_CLASSE_RENSEIGNE_01 
		= "RG_SECTIONHIT_CLASSE_RENSEIGNE_01 : "
				+ "la classe de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_CLASSE_REGEX_02 : 
	 * la classe de la section HIT doit comporter exactement 2 chiffres '00'".
	 */
	public static final String RG_SECTIONHIT_CLASSE_REGEX_02 
		= "RG_SECTIONHIT_CLASSE_REGEX_02 : "
				+ "la classe de la section HIT doit comporter exactement 2 chiffres '00'";

	/* 6 - anneeTraitement. *******/
	/**
	 * "anneeTraitement".<br/>
	 */
	public static final String ATTRIBUT_ANNEETRAITEMENT 
		= "anneeTraitement";

	/**
	 * "RG_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01 : 
	 * l'année de traitement de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01 
		= "RG_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01 : "
				+ "l'année de traitement de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_ANNEETRAITEMENT_REGEX_02 : 
	 * l'année de traitement de la section HIT doit comporter exactement 2 chiffres".
	 */
	public static final String RG_SECTIONHIT_ANNEETRAITEMENT_REGEX_02 
		= "RG_SECTIONHIT_ANNEETRAITEMENT_REGEX_02 : "
				+ "l'année de traitement de la section HIT doit comporter exactement 2 chiffres";

	/* 7 - zoneLibre1. *******/
	/**
	 * "zoneLibre1".<br/>
	 */
	public static final String ATTRIBUT_ZONELIBRE1 
		= "zoneLibre1";

	/**
	 * "RG_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01 : 
	 * la zone libre de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01 
		= "RG_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01 : "
				+ "la zone libre de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_ZONELIBRE1_REGEX_02 : 
	 * la zone libre de la section HIT doit comporter exactement 1 espace".
	 */
	public static final String RG_SECTIONHIT_ZONELIBRE1_REGEX_02 
		= "RG_SECTIONHIT_ZONELIBRE1_REGEX_02 : "
				+ "la zone libre de la section HIT doit comporter exactement 1 espace";

	/* 8 - numRoute. *******/
	/**
	 * "numRoute".<br/>
	 */
	public static final String ATTRIBUT_NUMROUTE 
		= "numRoute";

	/**
	 * "RG_SECTIONHIT_NUMROUTE_RENSEIGNE_01 : 
	 * le numéro de la route de la section HIT doit être renseigné (obligatoire)".
	 */
	public static final String RG_SECTIONHIT_NUMROUTE_RENSEIGNE_01 
		= "RG_SECTIONHIT_NUMROUTE_RENSEIGNE_01 : "
				+ "le numéro de la route de la section HIT "
				+ DOIT_ETRE_RENSEIGNE_OBLIGATOIRE;

	/**
	 * "RG_SECTIONHIT_NUMROUTE_REGEX_02 : 
	 * le numéro de la route de la section HIT doit comporter exactement 4 chiffres".
	 */
	public static final String RG_SECTIONHIT_NUMROUTE_REGEX_02 
		= "RG_SECTIONHIT_NUMROUTE_REGEX_02 : "
				+ "le numéro de la route de la section HIT doit comporter exactement 4 chiffres";

	/* 9 - indiceNumRoute. *******/
	/**
	 * "indiceNumRoute".<br/>
	 */
	public static final String ATTRIBUT_INDICENUMROUTE 
		= "indiceNumRoute";

	/**
	 * "RG_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01 : 
	 * l'indice numérique de la route de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01 
		= "RG_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01 : "
				+ "l'indice numérique de la route de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_INDICENUMROUTE_REGEX_02 : 
	 * l'indice numérique de la route de la section HIT doit comporter exactement 1 chiffre".
	 */
	public static final String RG_SECTIONHIT_INDICENUMROUTE_REGEX_02 
		= "RG_SECTIONHIT_INDICENUMROUTE_REGEX_02 : "
				+ "l'indice numérique de la route de la section HIT doit comporter exactement 1 chiffre";

	/* 10 - indiceLettreRoute. *******/
	/**
	 * "indiceLettreRoute".<br/>
	 */
	public static final String ATTRIBUT_INDICELETTREROUTE 
		= "indiceLettreRoute";

	/**
	 * "RG_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01 : 
	 * l'indice lettre de la route de la section HIT doit être renseigné (ou espace si pas d'indice)".
	 */
	public static final String RG_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01 
		= "RG_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01 : "
				+ "l'indice lettre de la route de la section HIT "
				+ "doit être renseigné (ou espace si pas d'indice)";

	/**
	 * "RG_SECTIONHIT_INDICELETTREROUTE_REGEX_02 : 
	 * l'indice lettre de la route de la section HIT doit comporter exactement 1 lettre (ou espace si pas d'indice)".
	 */
	public static final String RG_SECTIONHIT_INDICELETTREROUTE_REGEX_02 
		= "RG_SECTIONHIT_INDICELETTREROUTE_REGEX_02 : "
				+ "l'indice lettre de la route de la section HIT doit comporter exactement 1 lettre (ou espace si pas d'indice)";

	/* 11 - categorieAdminRoute. *******/
	/**
	 * "categorieAdminRoute".<br/>
	 */
	public static final String ATTRIBUT_CATEGORIEADMINROUTE 
		= "categorieAdminRoute";

	/**
	 * "RG_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01 : 
	 * la catégorie administrative de la route de la section HIT doit être renseignée (obligatoire)".
	 */
	public static final String RG_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01 
		= "RG_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01 : "
				+ "la catégorie administrative de la route de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE_OBLIGATOIRE;

	/**
	 * "RG_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02 : 
	 * la catégorie administrative de la route de la section HIT doit comporter exactement 1 chiffre".
	 */
	public static final String RG_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02 
		= "RG_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02 : "
				+ "la catégorie administrative de la route de la section HIT doit comporter exactement 1 chiffre";
	
	/**
	 * "RG_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03 : 
	 * la catégorie administrative de la route doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]".
	 */
	public static final String RG_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03 
		= "RG_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03 : "
				+ "la catégorie administrative de la route doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]";

	/* 12 - typeComptage. *******/
	/**
	 * "typeComptage".<br/>
	 */
	public static final String ATTRIBUT_TYPECOMPTAGE 
		= "typeComptage";

	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01 : 
	 * le type de comptage de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01 
		= "RG_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01 : "
				+ "le type de comptage de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGE_REGEX_02 : 
	 * le type de comptage de la section HIT doit comporter exactement 1 chiffre".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGE_REGEX_02 
		= "RG_SECTIONHIT_TYPECOMPTAGE_REGEX_02 : "
				+ "le type de comptage de la section HIT doit comporter exactement 1 chiffre";
	
	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03 : 
	 * le type de comptage doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03 
		= "RG_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03 : "
				+ "le type de comptage doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]";

	/* 13 - classementRoute. *******/
	/**
	 * "classementRoute".<br/>
	 */
	public static final String ATTRIBUT_CLASSEMENTROUTE 
		= "classementRoute";

	/**
	 * "RG_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01 : 
	 * le classement de la route de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01 
		= "RG_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01 : "
				+ "le classement de la route de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_CLASSEMENTROUTE_REGEX_02 : 
	 * le classement de la route de la section HIT doit comporter exactement 1 chiffre".
	 */
	public static final String RG_SECTIONHIT_CLASSEMENTROUTE_REGEX_02 
		= "RG_SECTIONHIT_CLASSEMENTROUTE_REGEX_02 : "
				+ "le classement de la route de la section HIT doit comporter exactement 1 chiffre";
	
	/**
	 * "RG_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03 : 
	 * le classement de la route doit respecter une nomenclature [1, 2, 3, 4, 5]".
	 */
	public static final String RG_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03 
		= "RG_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03 : "
				+ "le classement de la route doit respecter une nomenclature [1, 2, 3, 4, 5]";

	/* 14 - classeLargeurChausseeU. *******/
	/**
	 * "classeLargeurChausseeU".<br/>
	 */
	public static final String ATTRIBUT_CLASSELARGEURCHAUSSEEU 
		= "classeLargeurChausseeU";

	/**
	 * "RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01 : 
	 * la classe de largeur de chaussée unique de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01 
		= "RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01 : "
				+ "la classe de largeur de chaussée unique de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02 : 
	 * la classe de largeur de chaussée unique de la section HIT doit comporter exactement 1 chiffre".
	 */
	public static final String RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02 
		= "RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02 : "
				+ "la classe de largeur de chaussée unique de la section HIT doit comporter exactement 1 chiffre";
	
	/**
	 * "RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03 : 
	 * la classe de largeur de chaussée unique doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7, 8]".
	 */
	public static final String RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03 
		= "RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03 : "
				+ "la classe de largeur de chaussée unique doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7, 8]";

	/* 15 - classeLargeurChausseesS. *******/
	/**
	 * "classeLargeurChausseesS".<br/>
	 */
	public static final String ATTRIBUT_CLASSELARGEURCHAUSSEESS 
		= "classeLargeurChausseesS";

	/**
	 * "RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01 : 
	 * la classe de largeur de chaussées séparées de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01 
		= "RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01 : "
				+ "la classe de largeur de chaussées séparées de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02 : 
	 * la classe de largeur de chaussées séparées de la section HIT doit comporter exactement 1 chiffre".
	 */
	public static final String RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02 
		= "RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02 : "
				+ "la classe de largeur de chaussées séparées de la section HIT doit comporter exactement 1 chiffre";
	
	/**
	 * "RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03 : 
	 * la classe de largeur de chaussées séparées doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7]".
	 */
	public static final String RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03 
		= "RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03 : "
				+ "la classe de largeur de chaussées séparées doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7]";				

	/* 16 - typeReseau. *******/
	/**
	 * "typeReseau".<br/>
	 */
	public static final String ATTRIBUT_TYPERESEAU 
		= "typeReseau";

	/**
	 * "RG_SECTIONHIT_TYPERESEAU_RENSEIGNE_01 : 
	 * le type de réseau de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_TYPERESEAU_RENSEIGNE_01 
		= "RG_SECTIONHIT_TYPERESEAU_RENSEIGNE_01 : "
				+ "le type de réseau de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_TYPERESEAU_REGEX_02 : 
	 * le type de réseau de la section HIT doit comporter exactement 1 chiffre".
	 */
	public static final String RG_SECTIONHIT_TYPERESEAU_REGEX_02 
		= "RG_SECTIONHIT_TYPERESEAU_REGEX_02 : "
				+ "le type de réseau de la section HIT doit comporter exactement 1 chiffre";
	
	/**
	 * "RG_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03 : 
	 * le type de réseau doit respecter une nomenclature [0, 1, 2, 3, 4]".
	 */
	public static final String RG_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03 
		= "RG_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03 : "
				+ "le type de réseau doit respecter une nomenclature [0, 1, 2, 3, 4]";

	/* 17 - pRoupK. *******/
	/**
	 * "pRoupK".<br/>
	 */
	public static final String ATTRIBUT_PROUPK 
		= "pRoupK";

	/**
	 * "RG_SECTIONHIT_PROUPK_RENSEIGNE_01 : 
	 * PR ou PK de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PROUPK_RENSEIGNE_01 
		= "RG_SECTIONHIT_PROUPK_RENSEIGNE_01 : "
				+ "PR ou PK de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PROUPK_REGEX_02 : 
	 * PR ou PK de la section HIT doit comporter exactement 1 chiffre".
	 */
	public static final String RG_SECTIONHIT_PROUPK_REGEX_02 
		= "RG_SECTIONHIT_PROUPK_REGEX_02 : "
				+ "PR ou PK de la section HIT doit comporter exactement 1 chiffre";
	
	/**
	 * "RG_SECTIONHIT_PROUPK_NOMENCLATURE_03 : 
	 * PR ou PK doit respecter une nomenclature [1, 2]".
	 */
	public static final String RG_SECTIONHIT_PROUPK_NOMENCLATURE_03 
		= "RG_SECTIONHIT_PROUPK_NOMENCLATURE_03 : "
				+ "PR ou PK doit respecter une nomenclature [1, 2]";

	/* 18 - lieuDitOrigine. *******/
	/**
	 * "lieuDitOrigine".<br/>
	 */
	public static final String ATTRIBUT_LIEUDITORIGINE 
		= "lieuDitOrigine";

	/**
	 * "RG_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01 : 
	 * le lieu-dit origine de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01 
		= "RG_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01 : "
				+ "le lieu-dit origine de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_LIEUDITORIGINE_REGEX_02 : 
	 * le lieu-dit origine de la section HIT doit comporter exactement 20 caractères quelconques".
	 */
	public static final String RG_SECTIONHIT_LIEUDITORIGINE_REGEX_02 
		= "RG_SECTIONHIT_LIEUDITORIGINE_REGEX_02 : "
				+ "le lieu-dit origine de la section HIT doit comporter exactement 20 caractères quelconques";	

	/* 19 - prOrigine. *******/
	/**
	 * "prOrigine".<br/>
	 */
	public static final String ATTRIBUT_PRORIGINE 
		= "prOrigine";

	/**
	 * "RG_SECTIONHIT_PRORIGINE_RENSEIGNE_01 : 
	 * le PR origine de la section HIT doit être renseigné (obligatoire)".
	 */
	public static final String RG_SECTIONHIT_PRORIGINE_RENSEIGNE_01 
		= "RG_SECTIONHIT_PRORIGINE_RENSEIGNE_01 : "
				+ "le PR origine de la section HIT "
				+ DOIT_ETRE_RENSEIGNE_OBLIGATOIRE;

	/**
	 * "RG_SECTIONHIT_PRORIGINE_REGEX_02 : 
	 * le PR origine de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PRORIGINE_REGEX_02 
		= "RG_SECTIONHIT_PRORIGINE_REGEX_02 : "
				+ "le PR origine de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PRORIGINE_NUMERIQUE_03 : 
	 * le PR origine doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PRORIGINE_NUMERIQUE_03 
		= "RG_SECTIONHIT_PRORIGINE_NUMERIQUE_03 : "
				+ "le PR origine doit être homogène à un entier";

	/* 20 - absOrigine. *******/
	/**
	 * "absOrigine".<br/>
	 */
	public static final String ATTRIBUT_ABSORIGINE 
		= "absOrigine";

	/**
	 * "RG_SECTIONHIT_ABSORIGINE_RENSEIGNE_01 : 
	 * l'abscisse origine de la section HIT doit être renseignée (obligatoire)".
	 */
	public static final String RG_SECTIONHIT_ABSORIGINE_RENSEIGNE_01 
		= "RG_SECTIONHIT_ABSORIGINE_RENSEIGNE_01 : "
				+ "l'abscisse origine de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE_OBLIGATOIRE;

	/**
	 * "RG_SECTIONHIT_ABSORIGINE_REGEX_02 : 
	 * l'abscisse origine de la section HIT doit comporter exactement 4 chiffres".
	 */
	public static final String RG_SECTIONHIT_ABSORIGINE_REGEX_02 
		= "RG_SECTIONHIT_ABSORIGINE_REGEX_02 : "
				+ "l'abscisse origine de la section HIT doit comporter exactement 4 chiffres";
	
	/**
	 * "RG_SECTIONHIT_ABSORIGINE_NUMERIQUE_03 : 
	 * l'abscisse origine doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_ABSORIGINE_NUMERIQUE_03 
		= "RG_SECTIONHIT_ABSORIGINE_NUMERIQUE_03 : "
				+ "l'abscisse origine doit être homogène à un entier";

	/* 21 - lieuDitExtremite. *******/
	/**
	 * "lieuDitExtremite".<br/>
	 */
	public static final String ATTRIBUT_LIEUDITEXTREMITE 
		= "lieuDitExtremite";

	/**
	 * "RG_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01 : 
	 * le lieu-dit extremité de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01 
		= "RG_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01 : "
				+ "le lieu-dit extremité de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02 : 
	 * le lieu-dit extremité de la section HIT doit comporter exactement 20 caractères quelconques".
	 */
	public static final String RG_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02 
		= "RG_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02 : "
				+ "le lieu-dit extremité de la section HIT doit comporter exactement 20 caractères quelconques";	

	/* 22 - prExtremite. *******/
	/**
	 * "prExtremite".<br/>
	 */
	public static final String ATTRIBUT_PREXTREMITE 
		= "prExtremite";

	/**
	 * "RG_SECTIONHIT_PREXTREMITE_RENSEIGNE_01 : 
	 * le PR extremité de la section HIT doit être renseigné (obligatoire)".
	 */
	public static final String RG_SECTIONHIT_PREXTREMITE_RENSEIGNE_01 
		= "RG_SECTIONHIT_PREXTREMITE_RENSEIGNE_01 : "
				+ "le PR extremité de la section HIT "
				+ DOIT_ETRE_RENSEIGNE_OBLIGATOIRE;

	/**
	 * "RG_SECTIONHIT_PREXTREMITE_REGEX_02 : 
	 * le PR extremité de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PREXTREMITE_REGEX_02 
		= "RG_SECTIONHIT_PREXTREMITE_REGEX_02 : "
				+ "le PR extremité de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PREXTREMITE_NUMERIQUE_03 : 
	 * le PR extremité doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PREXTREMITE_NUMERIQUE_03 
		= "RG_SECTIONHIT_PREXTREMITE_NUMERIQUE_03 : "
				+ "le PR extremité doit être homogène à un entier";

	/* 23 - absExtremite. *******/
	/**
	 * "absExtremite".<br/>
	 */
	public static final String ATTRIBUT_ABSEXTREMITE 
		= "absExtremite";

	/**
	 * "RG_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01 : 
	 * l'abscisse extremité de la section HIT doit être renseignée (obligatoire)".
	 */
	public static final String RG_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01 
		= "RG_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01 : "
				+ "l'abscisse extremité de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE_OBLIGATOIRE;

	/**
	 * "RG_SECTIONHIT_ABSEXTREMITE_REGEX_02 : 
	 * l'abscisse extremité de la section HIT doit comporter exactement 4 chiffres".
	 */
	public static final String RG_SECTIONHIT_ABSEXTREMITE_REGEX_02 
		= "RG_SECTIONHIT_ABSEXTREMITE_REGEX_02 : "
				+ "l'abscisse extremité de la section HIT doit comporter exactement 4 chiffres";
	
	/**
	 * "RG_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03 : 
	 * l'abscisse extremité doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03 
		= "RG_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03 : "
				+ "l'abscisse extremité doit être homogène à un entier";

	/* 24 - lieuDitComptage. *******/
	/**
	 * "lieuDitComptage".<br/>
	 */
	public static final String ATTRIBUT_LIEUDITCOMPTAGE 
		= "lieuDitComptage";

	/**
	 * "RG_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01 : 
	 * le lieu-dit de comptage de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01 
		= "RG_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01 : "
				+ "le lieu-dit de comptage de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02 : 
	 * le lieu-dit du comptage de la section HIT doit comporter exactement 20 caractères quelconques".
	 */
	public static final String RG_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02 
		= "RG_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02 : "
				+ "le lieu-dit du comptage de la section HIT doit comporter exactement 20 caractères quelconques";	

	/* 25 - prComptage. *******/
	/**
	 * "prComptage".<br/>
	 */
	public static final String ATTRIBUT_PRCOMPTAGE 
		= "prComptage";

	/**
	 * "RG_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01 : 
	 * le PR du comptage de la section HIT doit être renseigné (obligatoire)".
	 */
	public static final String RG_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01 
		= "RG_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01 : "
				+ "le PR du comptage de la section HIT "
				+ DOIT_ETRE_RENSEIGNE_OBLIGATOIRE;

	/**
	 * "RG_SECTIONHIT_PRCOMPTAGE_REGEX_02 : 
	 * le PR du comptage de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PRCOMPTAGE_REGEX_02 
		= "RG_SECTIONHIT_PRCOMPTAGE_REGEX_02 : "
				+ "le PR du comptage de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03 : 
	 * le PR du comptage doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03 
		= "RG_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03 : "
				+ "le PR du comptage doit être homogène à un entier";

	/* 26 - absComptage. *******/
	/**
	 * "absComptage".<br/>
	 */
	public static final String ATTRIBUT_ABSCOMPTAGE 
		= "absComptage";

	/**
	 * "RG_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01 : 
	 * l'abscisse du comptage de la section HIT doit être renseignée (obligatoire)".
	 */
	public static final String RG_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01 
		= "RG_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01 : "
				+ "l'abscisse du comptage de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE_OBLIGATOIRE;

	/**
	 * "RG_SECTIONHIT_ABSCOMPTAGE_REGEX_02 : 
	 * l'abscisse du comptage de la section HIT doit comporter exactement 4 chiffres".
	 */
	public static final String RG_SECTIONHIT_ABSCOMPTAGE_REGEX_02 
		= "RG_SECTIONHIT_ABSCOMPTAGE_REGEX_02 : "
				+ "l'abscisse du comptage de la section HIT doit comporter exactement 4 chiffres";
	
	/**
	 * "RG_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03 : 
	 * l'abscisse du comptage doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03 
		= "RG_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03 : "
				+ "l'abscisse du comptage doit être homogène à un entier";

	/* 27 - longueurSection. *******/
	/**
	 * "longueurSection".<br/>
	 */
	public static final String ATTRIBUT_LONGUEURSECTION 
		= "longueurSection";

	/**
	 * "RG_SECTIONHIT_LONGUEURSECTION_RENSEIGNE_01 : 
	 * la longueur de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_LONGUEURSECTION_RENSEIGNE_01 
		= "RG_SECTIONHIT_LONGUEURSECTION_RENSEIGNE_01 : "
				+ "la longueur de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_LONGUEURSECTION_REGEX_02 : 
	 * la longueur de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_LONGUEURSECTION_REGEX_02 
		= "RG_SECTIONHIT_LONGUEURSECTION_REGEX_02 : "
				+ "la longueur de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_LONGUEURSECTION_NUMERIQUE_03 : 
	 * la longueur doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_LONGUEURSECTION_NUMERIQUE_03 
		= "RG_SECTIONHIT_LONGUEURSECTION_NUMERIQUE_03 : "
				+ "la longueur doit être homogène à un entier";

	/* 28 - longueurRaseCampagne. *******/
	/**
	 * "longueurRaseCampagne".<br/>
	 */
	public static final String ATTRIBUT_LONGUEURRASECAMPAGNE 
		= "longueurRaseCampagne";

	/**
	 * "RG_SECTIONHIT_LONGUEURRASECAMPAGNE_RENSEIGNE_01 : 
	 * la longueur en rase campagne de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_LONGUEURRASECAMPAGNE_RENSEIGNE_01 
		= "RG_SECTIONHIT_LONGUEURRASECAMPAGNE_RENSEIGNE_01 : "
				+ "la longueur en rase campagne de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_LONGUEURRASECAMPAGNE_REGEX_02 : 
	 * la longueur en rase campagne de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_LONGUEURRASECAMPAGNE_REGEX_02 
		= "RG_SECTIONHIT_LONGUEURRASECAMPAGNE_REGEX_02 : "
				+ "la longueur en rase campagne de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_LONGUEURRASECAMPAGNE_NUMERIQUE_03 : 
	 * la longueur en rase campagne doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_LONGUEURRASECAMPAGNE_NUMERIQUE_03 
		= "RG_SECTIONHIT_LONGUEURRASECAMPAGNE_NUMERIQUE_03 : "
				+ "la longueur en rase campagne doit être homogène à un entier";

	/* 29 - numDepartementRattachement. *******/
	/**
	 * "numDepartementRattachement".<br/>
	 */
	public static final String ATTRIBUT_NUMDEPARTEMENTRATTACHEMENT 
		= "numDepartementRattachement";

	/**
	 * "RG_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_RENSEIGNE_01 : 
	 * le numéro de département de rattachement de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_RENSEIGNE_01 
		= "RG_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_RENSEIGNE_01 : "
				+ "le numéro de département de rattachement de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_REGEX_02 : 
	 * le numéro de département de rattachement de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_REGEX_02 
		= "RG_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_REGEX_02 : "
				+ "le numéro de département de rattachement de la section HIT doit comporter exactement 3 chiffres";

	/* 30 - numSectionRattachement. *******/
	/**
	 * "numSectionRattachement".<br/>
	 */
	public static final String ATTRIBUT_NUMSECTIONRATTACHEMENT 
		= "numSectionRattachement";

	/**
	 * "RG_SECTIONHIT_NUMSECTIONRATTACHEMENT_RENSEIGNE_01 : 
	 * le numéro de section de rattachement de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_NUMSECTIONRATTACHEMENT_RENSEIGNE_01 
		= "RG_SECTIONHIT_NUMSECTIONRATTACHEMENT_RENSEIGNE_01 : "
				+ "le numéro de section de rattachement de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_NUMSECTIONRATTACHEMENT_REGEX_02 : 
	 * le numéro de section de rattachement de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_NUMSECTIONRATTACHEMENT_REGEX_02 
		= "RG_SECTIONHIT_NUMSECTIONRATTACHEMENT_REGEX_02 : "
				+ "le numéro de section de rattachement de la section HIT doit comporter exactement 6 chiffres";

	/* 31 - sensRattachement. *******/
	/**
	 * "sensRattachement".<br/>
	 */
	public static final String ATTRIBUT_SENSRATTACHEMENT 
		= "sensRattachement";

	/**
	 * "RG_SECTIONHIT_SENSRATTACHEMENT_RENSEIGNE_01 : 
	 * le sens de la section de rattachement de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_SENSRATTACHEMENT_RENSEIGNE_01 
		= "RG_SECTIONHIT_SENSRATTACHEMENT_RENSEIGNE_01 : "
				+ "le sens de la section de rattachement de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_SENSRATTACHEMENT_REGEX_02 : 
	 * le sens de la section de rattachement de la section HIT doit comporter exactement 1 chiffre".
	 */
	public static final String RG_SECTIONHIT_SENSRATTACHEMENT_REGEX_02 
		= "RG_SECTIONHIT_SENSRATTACHEMENT_REGEX_02 : "
				+ "le sens de la section de rattachement de la section HIT doit comporter exactement 1 chiffre";
	
	/**
	 * "RG_SECTIONHIT_SENSRATTACHEMENT_NOMENCLATURE_03 : 
	 * le sens de la section de rattachement doit respecter une nomenclature [1, 2, 3, 4, 5]".
	 */
	public static final String RG_SECTIONHIT_SENSRATTACHEMENT_NOMENCLATURE_03 
		= "RG_SECTIONHIT_SENSRATTACHEMENT_NOMENCLATURE_03 : "
				+ "le sens de la section de rattachement doit respecter une nomenclature [1, 2, 3, 4, 5]";

	/* 32 - numDepartementLimitrophe. *******/
	/**
	 * "numDepartementLimitrophe".<br/>
	 */
	public static final String ATTRIBUT_NUMDEPARTEMENTLIMITROPHE 
		= "numDepartementLimitrophe";

	/**
	 * "RG_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_RENSEIGNE_01 : 
	 * le numéro de département limitrophe de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_RENSEIGNE_01 
		= "RG_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_RENSEIGNE_01 : "
				+ "le numéro de département limitrophe de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_REGEX_02 : 
	 * le numéro de département limitrophe de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_REGEX_02 
		= "RG_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_REGEX_02 : "
				+ "le numéro de département limitrophe de la section HIT doit comporter exactement 3 chiffres";

	/* 33 - numSectionLimitrophe. *******/
	/**
	 * "numSectionLimitrophe".<br/>
	 */
	public static final String ATTRIBUT_NUMSECTIONLIMITROPHE 
		= "numSectionLimitrophe";

	/**
	 * "RG_SECTIONHIT_NUMSECTIONLIMITROPHE_RENSEIGNE_01 : 
	 * le numéro de section limitrophe de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_NUMSECTIONLIMITROPHE_RENSEIGNE_01 
		= "RG_SECTIONHIT_NUMSECTIONLIMITROPHE_RENSEIGNE_01 : "
				+ "le numéro de section limitrophe de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_NUMSECTIONLIMITROPHE_REGEX_02 : 
	 * le numéro de section limitrophe de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_NUMSECTIONLIMITROPHE_REGEX_02 
		= "RG_SECTIONHIT_NUMSECTIONLIMITROPHE_REGEX_02 : "
				+ "le numéro de section limitrophe de la section HIT doit comporter exactement 6 chiffres";

	/* 34 - sensLimitrophe. *******/
	/**
	 * "sensLimitrophe".<br/>
	 */
	public static final String ATTRIBUT_SENSLIMITROPHE 
		= "sensLimitrophe";

	/**
	 * "RG_SECTIONHIT_SENSLIMITROPHE_RENSEIGNE_01 : 
	 * le sens de la section limitrophe de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_SENSLIMITROPHE_RENSEIGNE_01 
		= "RG_SECTIONHIT_SENSLIMITROPHE_RENSEIGNE_01 : "
				+ "le sens de la section limitrophe de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_SENSLIMITROPHE_REGEX_02 : 
	 * le sens de la section limitrophe de la section HIT doit comporter exactement 1 chiffre".
	 */
	public static final String RG_SECTIONHIT_SENSLIMITROPHE_REGEX_02 
		= "RG_SECTIONHIT_SENSLIMITROPHE_REGEX_02 : "
				+ "le sens de la section limitrophe de la section HIT doit comporter exactement 1 chiffre";
	
	/**
	 * "RG_SECTIONHIT_SENSLIMITROPHE_NOMENCLATURE_03 : 
	 * le sens de la section limitrophe doit respecter une nomenclature [1, 2, 3, 4, 5]".
	 */
	public static final String RG_SECTIONHIT_SENSLIMITROPHE_NOMENCLATURE_03 
		= "RG_SECTIONHIT_SENSLIMITROPHE_NOMENCLATURE_03 : "
				+ "le sens de la section limitrophe doit respecter une nomenclature [1, 2, 3, 4, 5]";

	/* 35 - moisSectionnement. *******/
	/**
	 * "moisSectionnement".<br/>
	 */
	public static final String ATTRIBUT_MOISSECTIONNEMENT 
		= "moisSectionnement";

	/**
	 * "RG_SECTIONHIT_MOISSECTIONNEMENT_RENSEIGNE_01 : 
	 * le mois de sectionnement de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_MOISSECTIONNEMENT_RENSEIGNE_01 
		= "RG_SECTIONHIT_MOISSECTIONNEMENT_RENSEIGNE_01 : "
				+ "le mois de sectionnement de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_MOISSECTIONNEMENT_REGEX_02 : 
	 * le mois de sectionnement de la section HIT doit comporter exactement 2 chiffres".
	 */
	public static final String RG_SECTIONHIT_MOISSECTIONNEMENT_REGEX_02 
		= "RG_SECTIONHIT_MOISSECTIONNEMENT_REGEX_02 : "
				+ "le mois de sectionnement de la section HIT doit comporter exactement 2 chiffres";

	/* 36 - anneeSectionnement. *******/
	/**
	 * "anneeSectionnement".<br/>
	 */
	public static final String ATTRIBUT_ANNEESECTIONNEMENT 
		= "anneeSectionnement";

	/**
	 * "RG_SECTIONHIT_ANNEESECTIONNEMENT_RENSEIGNE_01 : 
	 * l'année de sectionnement de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_ANNEESECTIONNEMENT_RENSEIGNE_01 
		= "RG_SECTIONHIT_ANNEESECTIONNEMENT_RENSEIGNE_01 : "
				+ "l'année de sectionnement de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_ANNEESECTIONNEMENT_REGEX_02 : 
	 * l'année de sectionnement de la section HIT doit comporter exactement 2 chiffres".
	 */
	public static final String RG_SECTIONHIT_ANNEESECTIONNEMENT_REGEX_02 
		= "RG_SECTIONHIT_ANNEESECTIONNEMENT_REGEX_02 : "
				+ "l'année de sectionnement de la section HIT doit comporter exactement 2 chiffres";

	/* 37 - zoneLibre2. *******/
	/**
	 * "zoneLibre2".<br/>
	 */
	public static final String ATTRIBUT_ZONELIBRE2 
		= "zoneLibre2";

	/**
	 * "RG_SECTIONHIT_ZONELIBRE2_RENSEIGNE_01 : 
	 * la zone libre (2) de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_ZONELIBRE2_RENSEIGNE_01 
		= "RG_SECTIONHIT_ZONELIBRE2_RENSEIGNE_01 : "
				+ "la zone libre (2) de la section HIT "
				+ "doit être renseignée";

	/**
	 * "RG_SECTIONHIT_ZONELIBRE2_REGEX_02 : 
	 * la zone libre (2) de la section HIT doit comporter exactement 6 espaces".
	 */
	public static final String RG_SECTIONHIT_ZONELIBRE2_REGEX_02 
		= "RG_SECTIONHIT_ZONELIBRE2_REGEX_02 : "
				+ "la zone libre (2) de la section HIT doit comporter exactement 6 espaces";

	/* 38 - mjaN. *******/
	/**
	 * "mjaN".<br/>
	 */
	public static final String ATTRIBUT_MJAN 
		= "mjaN";

	/**
	 * "RG_SECTIONHIT_MJAN_RENSEIGNE_01 : 
	 * le trafic moyen journalier annuel de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_MJAN_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJAN_RENSEIGNE_01 : "
				+ "le trafic moyen journalier annuel de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_MJAN_REGEX_02 : 
	 * le trafic moyen journalier annuel de l'année n de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJAN_REGEX_02 
		= "RG_SECTIONHIT_MJAN_REGEX_02 : "
				+ "le trafic moyen journalier annuel de l'année n de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJAN_NUMERIQUE_03 : 
	 * le trafic moyen journalier annuel de l'année n doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJAN_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJAN_NUMERIQUE_03 : "
				+ "le trafic moyen journalier annuel de l'année n doit être homogène à un entier";
	
	/* 39 - modeCalculN. *******/
	/**
	 * "modeCalculN".<br/>
	 */
	public static final String ATTRIBUT_MODECALCULN 
		= "modeCalculN";

	/**
	 * "RG_SECTIONHIT_MODECALCULN_RENSEIGNE_01 : 
	 * le mode de calcul des trafics de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_MODECALCULN_RENSEIGNE_01 
		= "RG_SECTIONHIT_MODECALCULN_RENSEIGNE_01 : "
				+ "le mode de calcul des trafics de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_MODECALCULN_REGEX_02 : 
	 * le mode de calcul des trafics de l'année n de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_MODECALCULN_REGEX_02 
		= "RG_SECTIONHIT_MODECALCULN_REGEX_02 : "
				+ "le mode de calcul des trafics de l'année n de la section HIT doit comporter exactement 1 caractère (ou espace)";

	/* 40 - pcPLN. *******/
	/**
	 * "pcPLN".<br/>
	 */
	public static final String ATTRIBUT_PCPLN 
		= "pcPLN";

	/**
	 * "RG_SECTIONHIT_PCPLN_RENSEIGNE_01 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCPLN_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCPLN_RENSEIGNE_01 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCPLN_REGEX_02 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCPLN_REGEX_02 
		= "RG_SECTIONHIT_PCPLN_REGEX_02 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCPLN_NUMERIQUE_03 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCPLN_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCPLN_NUMERIQUE_03 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n doit être homogène à un entier";
	
	/* 41 - evaluationPLN. *******/
	/**
	 * "evaluationPLN".<br/>
	 */
	public static final String ATTRIBUT_EVALUATIONPLN 
		= "evaluationPLN";

	/**
	 * "RG_SECTIONHIT_EVALUATIONPLN_RENSEIGNE_01 : 
	 * l'évaluation des pourcentages de trafic poids lourds de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_EVALUATIONPLN_RENSEIGNE_01 
		= "RG_SECTIONHIT_EVALUATIONPLN_RENSEIGNE_01 : "
				+ "l'évaluation des pourcentages de trafic poids lourds de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_EVALUATIONPLN_REGEX_02 : 
	 * l'évaluation des pourcentages de trafic poids lourds de l'année n de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_EVALUATIONPLN_REGEX_02 
		= "RG_SECTIONHIT_EVALUATIONPLN_REGEX_02 : "
				+ "l'évaluation des pourcentages de trafic poids lourds de l'année n de la section HIT doit comporter exactement 1 caractère (ou espace)";

	/* 42 - pcNuitAnnuelN. *******/
	/**
	 * "pcNuitAnnuelN".<br/>
	 */
	public static final String ATTRIBUT_PCNUITANNUELN 
		= "pcNuitAnnuelN";

	/**
	 * "RG_SECTIONHIT_PCNUITANNUELN_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELN_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITANNUELN_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITANNUELN_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELN_REGEX_02 
		= "RG_SECTIONHIT_PCNUITANNUELN_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITANNUELN_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELN_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITANNUELN_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n doit être homogène à un entier";
	
	/* 43 - indiceFiabiliteMjaN. *******/
	/**
	 * "indiceFiabiliteMjaN".<br/>
	 */
	public static final String ATTRIBUT_INDICEFIABLITEMJAN 
		= "indiceFiabiliteMjaN";

	/**
	 * "RG_SECTIONHIT_INDICEFIABLITEMJAN_RENSEIGNE_01 : 
	 * l'indice de fiabilité de la TMJA de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_INDICEFIABLITEMJAN_RENSEIGNE_01 
		= "RG_SECTIONHIT_INDICEFIABLITEMJAN_RENSEIGNE_01 : "
				+ "l'indice de fiabilité de la TMJA de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_INDICEFIABLITEMJAN_REGEX_02 : 
	 * l'indice de fiabilité de la TMJA de l'année n de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_INDICEFIABLITEMJAN_REGEX_02 
		= "RG_SECTIONHIT_INDICEFIABLITEMJAN_REGEX_02 : "
				+ "l'indice de fiabilité de la TMJA de l'année n de la section HIT doit comporter exactement 1 caractère (ou espace)";

	/* 44 - mjmNmois01. *******/
	/**
	 * "mjmNmois01".<br/>
	 */
	public static final String ATTRIBUT_MJMNMOIS01 
		= "mjmNmois01";

	/**
	 * "RG_SECTIONHIT_MJMNMOIS01_RENSEIGNE_01 : 
	 * la moyenne journalière mensuelle du mois de janvier (01) de l'année n en véhicules/jour de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS01_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJMNMOIS01_RENSEIGNE_01 : "
				+ "la moyenne journalière mensuelle du mois de janvier (01) de l'année n en véhicules/jour de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_MJMNMOIS01_REGEX_02 : 
	 * la moyenne journalière mensuelle du mois de janvier (01) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS01_REGEX_02 
		= "RG_SECTIONHIT_MJMNMOIS01_REGEX_02 : "
				+ "la moyenne journalière mensuelle du mois de janvier (01) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJMNMOIS01_NUMERIQUE_03 : 
	 * la moyenne journalière mensuelle du mois de janvier (01) de l'année n en véhicules/jour doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS01_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJMNMOIS01_NUMERIQUE_03 : "
				+ "la moyenne journalière mensuelle du mois de janvier (01) de l'année n en véhicules/jour doit être homogène à un entier";

	/* 45 - pcNuitNmois01. *******/
	/**
	 * "pcNuitNmois01".<br/>
	 */
	public static final String ATTRIBUT_PCNUITNMOIS01 
		= "pcNuitNmois01";

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS01_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de janvier (01) de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS01_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITNMOIS01_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de janvier (01) de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS01_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de janvier (01) de l'année n de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS01_REGEX_02 
		= "RG_SECTIONHIT_PCNUITNMOIS01_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de janvier (01) de l'année n de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS01_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de janvier (01) de l'année n doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS01_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITNMOIS01_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de janvier (01) de l'année n doit être homogène à un entier";

	/* 46 - mjmNmois02. *******/
	/**
	 * "mjmNmois02".<br/>
	 */
	public static final String ATTRIBUT_MJMNMOIS02 
		= "mjmNmois02";

	/**
	 * "RG_SECTIONHIT_MJMNMOIS02_RENSEIGNE_01 : 
	 * la moyenne journalière mensuelle du mois de février (02) de l'année n en véhicules/jour de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS02_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJMNMOIS02_RENSEIGNE_01 : "
				+ "la moyenne journalière mensuelle du mois de février (02) de l'année n en véhicules/jour de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_MJMNMOIS02_REGEX_02 : 
	 * la moyenne journalière mensuelle du mois de février (02) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS02_REGEX_02 
		= "RG_SECTIONHIT_MJMNMOIS02_REGEX_02 : "
				+ "la moyenne journalière mensuelle du mois de février (02) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJMNMOIS02_NUMERIQUE_03 : 
	 * la moyenne journalière mensuelle du mois de février (02) de l'année n en véhicules/jour doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS02_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJMNMOIS02_NUMERIQUE_03 : "
				+ "la moyenne journalière mensuelle du mois de février (02) de l'année n en véhicules/jour doit être homogène à un entier";

	/* 47 - pcNuitNmois02. *******/
	/**
	 * "pcNuitNmois02".<br/>
	 */
	public static final String ATTRIBUT_PCNUITNMOIS02 
		= "pcNuitNmois02";

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS02_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de février (02) de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS02_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITNMOIS02_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de février (02) de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS02_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de février (02) de l'année n de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS02_REGEX_02 
		= "RG_SECTIONHIT_PCNUITNMOIS02_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de février (02) de l'année n de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS02_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de février (02) de l'année n doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS02_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITNMOIS02_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de février (02) de l'année n doit être homogène à un entier";

	/* 48 - mjmNmois03. *******/
	/**
	 * "mjmNmois03".<br/>
	 */
	public static final String ATTRIBUT_MJMNMOIS03 
		= "mjmNmois03";

	/**
	 * "RG_SECTIONHIT_MJMNMOIS03_RENSEIGNE_01 : 
	 * la moyenne journalière mensuelle du mois de mars (03) de l'année n en véhicules/jour de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS03_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJMNMOIS03_RENSEIGNE_01 : "
				+ "la moyenne journalière mensuelle du mois de mars (03) de l'année n en véhicules/jour de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_MJMNMOIS03_REGEX_02 : 
	 * la moyenne journalière mensuelle du mois de mars (03) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS03_REGEX_02 
		= "RG_SECTIONHIT_MJMNMOIS03_REGEX_02 : "
				+ "la moyenne journalière mensuelle du mois de mars (03) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJMNMOIS03_NUMERIQUE_03 : 
	 * la moyenne journalière mensuelle du mois de mars (03) de l'année n en véhicules/jour doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS03_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJMNMOIS03_NUMERIQUE_03 : "
				+ "la moyenne journalière mensuelle du mois de mars (03) de l'année n en véhicules/jour doit être homogène à un entier";

	/* 49 - pcNuitNmois03. *******/
	/**
	 * "pcNuitNmois03".<br/>
	 */
	public static final String ATTRIBUT_PCNUITNMOIS03 
		= "pcNuitNmois03";

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS03_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de mars (03) de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS03_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITNMOIS03_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de mars (03) de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS03_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de mars (03) de l'année n de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS03_REGEX_02 
		= "RG_SECTIONHIT_PCNUITNMOIS03_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de mars (03) de l'année n de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS03_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de mars (03) de l'année n doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS03_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITNMOIS03_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de mars (03) de l'année n doit être homogène à un entier";	

	/* 50 - mjmNmois04. *******/
	/**
	 * "mjmNmois04".<br/>
	 */
	public static final String ATTRIBUT_MJMNMOIS04 
		= "mjmNmois04";

	/**
	 * "RG_SECTIONHIT_MJMNMOIS04_RENSEIGNE_01 : 
	 * la moyenne journalière mensuelle du mois de avril (04) de l'année n en véhicules/jour de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS04_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJMNMOIS04_RENSEIGNE_01 : "
				+ "la moyenne journalière mensuelle du mois de avril (04) de l'année n en véhicules/jour de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_MJMNMOIS04_REGEX_02 : 
	 * la moyenne journalière mensuelle du mois de avril (04) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS04_REGEX_02 
		= "RG_SECTIONHIT_MJMNMOIS04_REGEX_02 : "
				+ "la moyenne journalière mensuelle du mois de avril (04) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJMNMOIS04_NUMERIQUE_03 : 
	 * la moyenne journalière mensuelle du mois de avril (04) de l'année n en véhicules/jour doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS04_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJMNMOIS04_NUMERIQUE_03 : "
				+ "la moyenne journalière mensuelle du mois de avril (04) de l'année n en véhicules/jour doit être homogène à un entier";

	/* 51 - pcNuitNmois04. *******/
	/**
	 * "pcNuitNmois04".<br/>
	 */
	public static final String ATTRIBUT_PCNUITNMOIS04 
		= "pcNuitNmois04";

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS04_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de avril (04) de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS04_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITNMOIS04_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de avril (04) de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS04_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de avril (04) de l'année n de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS04_REGEX_02 
		= "RG_SECTIONHIT_PCNUITNMOIS04_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de avril (04) de l'année n de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS04_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de avril (04) de l'année n doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS04_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITNMOIS04_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de avril (04) de l'année n doit être homogène à un entier";	


	/* 52 - mjmNmois05. *******/
	/**
	 * "mjmNmois05".<br/>
	 */
	public static final String ATTRIBUT_MJMNMOIS05 
		= "mjmNmois05";

	/**
	 * "RG_SECTIONHIT_MJMNMOIS05_RENSEIGNE_01 : 
	 * la moyenne journalière mensuelle du mois de mai (05) de l'année n en véhicules/jour de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS05_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJMNMOIS05_RENSEIGNE_01 : "
				+ "la moyenne journalière mensuelle du mois de mai (05) de l'année n en véhicules/jour de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_MJMNMOIS05_REGEX_02 : 
	 * la moyenne journalière mensuelle du mois de mai (05) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS05_REGEX_02 
		= "RG_SECTIONHIT_MJMNMOIS05_REGEX_02 : "
				+ "la moyenne journalière mensuelle du mois de mai (05) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJMNMOIS05_NUMERIQUE_03 : 
	 * la moyenne journalière mensuelle du mois de mai (05) de l'année n en véhicules/jour doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS05_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJMNMOIS05_NUMERIQUE_03 : "
				+ "la moyenne journalière mensuelle du mois de mai (05) de l'année n en véhicules/jour doit être homogène à un entier";

	/* 53 - pcNuitNmois05. *******/
	/**
	 * "pcNuitNmois05".<br/>
	 */
	public static final String ATTRIBUT_PCNUITNMOIS05 
		= "pcNuitNmois05";

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS05_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de mai (05) de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS05_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITNMOIS05_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de mai (05) de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS05_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de mai (05) de l'année n de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS05_REGEX_02 
		= "RG_SECTIONHIT_PCNUITNMOIS05_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de mai (05) de l'année n de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS05_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de mai (05) de l'année n doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS05_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITNMOIS05_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de mai (05) de l'année n doit être homogène à un entier";	


	/* 54 - mjmNmois06. *******/
	/**
	 * "mjmNmois06".<br/>
	 */
	public static final String ATTRIBUT_MJMNMOIS06 
		= "mjmNmois06";

	/**
	 * "RG_SECTIONHIT_MJMNMOIS06_RENSEIGNE_01 : 
	 * la moyenne journalière mensuelle du mois de juin (06) de l'année n en véhicules/jour de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS06_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJMNMOIS06_RENSEIGNE_01 : "
				+ "la moyenne journalière mensuelle du mois de juin (06) de l'année n en véhicules/jour de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_MJMNMOIS06_REGEX_02 : 
	 * la moyenne journalière mensuelle du mois de juin (06) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS06_REGEX_02 
		= "RG_SECTIONHIT_MJMNMOIS06_REGEX_02 : "
				+ "la moyenne journalière mensuelle du mois de juin (06) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJMNMOIS06_NUMERIQUE_03 : 
	 * la moyenne journalière mensuelle du mois de juin (06) de l'année n en véhicules/jour doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS06_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJMNMOIS06_NUMERIQUE_03 : "
				+ "la moyenne journalière mensuelle du mois de juin (06) de l'année n en véhicules/jour doit être homogène à un entier";

	/* 55 - pcNuitNmois06. *******/
	/**
	 * "pcNuitNmois06".<br/>
	 */
	public static final String ATTRIBUT_PCNUITNMOIS06 
		= "pcNuitNmois06";

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS06_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de juin (06) de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS06_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITNMOIS06_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de juin (06) de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS06_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de juin (06) de l'année n de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS06_REGEX_02 
		= "RG_SECTIONHIT_PCNUITNMOIS06_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de juin (06) de l'année n de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS06_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de juin (06) de l'année n doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS06_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITNMOIS06_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de juin (06) de l'année n doit être homogène à un entier";	


	/* 56 - mjmNmois07. *******/
	/**
	 * "mjmNmois07".<br/>
	 */
	public static final String ATTRIBUT_MJMNMOIS07 
		= "mjmNmois07";

	/**
	 * "RG_SECTIONHIT_MJMNMOIS07_RENSEIGNE_01 : 
	 * la moyenne journalière mensuelle du mois de juillet (07) de l'année n en véhicules/jour de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS07_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJMNMOIS07_RENSEIGNE_01 : "
				+ "la moyenne journalière mensuelle du mois de juillet (07) de l'année n en véhicules/jour de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_MJMNMOIS07_REGEX_02 : 
	 * la moyenne journalière mensuelle du mois de juillet (07) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS07_REGEX_02 
		= "RG_SECTIONHIT_MJMNMOIS07_REGEX_02 : "
				+ "la moyenne journalière mensuelle du mois de juillet (07) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJMNMOIS07_NUMERIQUE_03 : 
	 * la moyenne journalière mensuelle du mois de juillet (07) de l'année n en véhicules/jour doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS07_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJMNMOIS07_NUMERIQUE_03 : "
				+ "la moyenne journalière mensuelle du mois de juillet (07) de l'année n en véhicules/jour doit être homogène à un entier";

	/* 57 - pcNuitNmois07. *******/
	/**
	 * "pcNuitNmois07".<br/>
	 */
	public static final String ATTRIBUT_PCNUITNMOIS07 
		= "pcNuitNmois07";

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS07_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de juillet (07) de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS07_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITNMOIS07_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de juillet (07) de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS07_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de juillet (07) de l'année n de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS07_REGEX_02 
		= "RG_SECTIONHIT_PCNUITNMOIS07_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de juillet (07) de l'année n de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS07_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de juillet (07) de l'année n doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS07_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITNMOIS07_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de juillet (07) de l'année n doit être homogène à un entier";	


	/* 58 - mjmNmois08. *******/
	/**
	 * "mjmNmois08".<br/>
	 */
	public static final String ATTRIBUT_MJMNMOIS08 
		= "mjmNmois08";

	/**
	 * "RG_SECTIONHIT_MJMNMOIS08_RENSEIGNE_01 : 
	 * la moyenne journalière mensuelle du mois de août (08) de l'année n en véhicules/jour de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS08_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJMNMOIS08_RENSEIGNE_01 : "
				+ "la moyenne journalière mensuelle du mois de août (08) de l'année n en véhicules/jour de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_MJMNMOIS08_REGEX_02 : 
	 * la moyenne journalière mensuelle du mois de août (08) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS08_REGEX_02 
		= "RG_SECTIONHIT_MJMNMOIS08_REGEX_02 : "
				+ "la moyenne journalière mensuelle du mois de août (08) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJMNMOIS08_NUMERIQUE_03 : 
	 * la moyenne journalière mensuelle du mois de août (08) de l'année n en véhicules/jour doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS08_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJMNMOIS08_NUMERIQUE_03 : "
				+ "la moyenne journalière mensuelle du mois de août (08) de l'année n en véhicules/jour doit être homogène à un entier";

	/* 59 - pcNuitNmois08. *******/
	/**
	 * "pcNuitNmois08".<br/>
	 */
	public static final String ATTRIBUT_PCNUITNMOIS08 
		= "pcNuitNmois08";

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS08_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de août (08) de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS08_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITNMOIS08_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de août (08) de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS08_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de août (08) de l'année n de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS08_REGEX_02 
		= "RG_SECTIONHIT_PCNUITNMOIS08_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de août (08) de l'année n de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS08_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de août (08) de l'année n doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS08_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITNMOIS08_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de août (08) de l'année n doit être homogène à un entier";	


	/* 60 - mjmNmois09. *******/
	/**
	 * "mjmNmois09".<br/>
	 */
	public static final String ATTRIBUT_MJMNMOIS09 
		= "mjmNmois09";

	/**
	 * "RG_SECTIONHIT_MJMNMOIS09_RENSEIGNE_01 : 
	 * la moyenne journalière mensuelle du mois de septembre (09) de l'année n en véhicules/jour de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS09_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJMNMOIS09_RENSEIGNE_01 : "
				+ "la moyenne journalière mensuelle du mois de septembre (09) de l'année n en véhicules/jour de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_MJMNMOIS09_REGEX_02 : 
	 * la moyenne journalière mensuelle du mois de septembre (09) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS09_REGEX_02 
		= "RG_SECTIONHIT_MJMNMOIS09_REGEX_02 : "
				+ "la moyenne journalière mensuelle du mois de septembre (09) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJMNMOIS09_NUMERIQUE_03 : 
	 * la moyenne journalière mensuelle du mois de septembre (09) de l'année n en véhicules/jour doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS09_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJMNMOIS09_NUMERIQUE_03 : "
				+ "la moyenne journalière mensuelle du mois de septembre (09) de l'année n en véhicules/jour doit être homogène à un entier";

	/* 61 - pcNuitNmois09. *******/
	/**
	 * "pcNuitNmois09".<br/>
	 */
	public static final String ATTRIBUT_PCNUITNMOIS09 
		= "pcNuitNmois09";

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS09_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de septembre (09) de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS09_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITNMOIS09_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de septembre (09) de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS09_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de septembre (09) de l'année n de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS09_REGEX_02 
		= "RG_SECTIONHIT_PCNUITNMOIS09_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de septembre (09) de l'année n de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS09_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de septembre (09) de l'année n doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS09_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITNMOIS09_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de septembre (09) de l'année n doit être homogène à un entier";	


	/* 62 - mjmNmois10. *******/
	/**
	 * "mjmNmois10".<br/>
	 */
	public static final String ATTRIBUT_MJMNMOIS10 
		= "mjmNmois10";

	/**
	 * "RG_SECTIONHIT_MJMNMOIS10_RENSEIGNE_01 : 
	 * la moyenne journalière mensuelle du mois de octobre (10) de l'année n en véhicules/jour de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS10_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJMNMOIS10_RENSEIGNE_01 : "
				+ "la moyenne journalière mensuelle du mois de octobre (10) de l'année n en véhicules/jour de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_MJMNMOIS10_REGEX_02 : 
	 * la moyenne journalière mensuelle du mois de octobre (10) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS10_REGEX_02 
		= "RG_SECTIONHIT_MJMNMOIS10_REGEX_02 : "
				+ "la moyenne journalière mensuelle du mois de octobre (10) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJMNMOIS10_NUMERIQUE_03 : 
	 * la moyenne journalière mensuelle du mois de octobre (10) de l'année n en véhicules/jour doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS10_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJMNMOIS10_NUMERIQUE_03 : "
				+ "la moyenne journalière mensuelle du mois de octobre (10) de l'année n en véhicules/jour doit être homogène à un entier";

	/* 63 - pcNuitNmois10. *******/
	/**
	 * "pcNuitNmois10".<br/>
	 */
	public static final String ATTRIBUT_PCNUITNMOIS10 
		= "pcNuitNmois10";

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS10_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de octobre (10) de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS10_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITNMOIS10_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de octobre (10) de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS10_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de octobre (10) de l'année n de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS10_REGEX_02 
		= "RG_SECTIONHIT_PCNUITNMOIS10_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de octobre (10) de l'année n de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS10_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de octobre (10) de l'année n doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS10_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITNMOIS10_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de octobre (10) de l'année n doit être homogène à un entier";	


	/* 64 - mjmNmois11. *******/
	/**
	 * "mjmNmois11".<br/>
	 */
	public static final String ATTRIBUT_MJMNMOIS11 
		= "mjmNmois11";

	/**
	 * "RG_SECTIONHIT_MJMNMOIS11_RENSEIGNE_01 : 
	 * la moyenne journalière mensuelle du mois de novembre (11) de l'année n en véhicules/jour de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS11_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJMNMOIS11_RENSEIGNE_01 : "
				+ "la moyenne journalière mensuelle du mois de novembre (11) de l'année n en véhicules/jour de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_MJMNMOIS11_REGEX_02 : 
	 * la moyenne journalière mensuelle du mois de novembre (11) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS11_REGEX_02 
		= "RG_SECTIONHIT_MJMNMOIS11_REGEX_02 : "
				+ "la moyenne journalière mensuelle du mois de novembre (11) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJMNMOIS11_NUMERIQUE_03 : 
	 * la moyenne journalière mensuelle du mois de novembre (11) de l'année n en véhicules/jour doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS11_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJMNMOIS11_NUMERIQUE_03 : "
				+ "la moyenne journalière mensuelle du mois de novembre (11) de l'année n en véhicules/jour doit être homogène à un entier";

	/* 65 - pcNuitNmois11. *******/
	/**
	 * "pcNuitNmois11".<br/>
	 */
	public static final String ATTRIBUT_PCNUITNMOIS11 
		= "pcNuitNmois11";

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS11_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de novembre (11) de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS11_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITNMOIS11_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de novembre (11) de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS11_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de novembre (11) de l'année n de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS11_REGEX_02 
		= "RG_SECTIONHIT_PCNUITNMOIS11_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de novembre (11) de l'année n de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS11_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de novembre (11) de l'année n doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS11_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITNMOIS11_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de novembre (11) de l'année n doit être homogène à un entier";	


	/* 66 - mjmNmois12. *******/
	/**
	 * "mjmNmois12".<br/>
	 */
	public static final String ATTRIBUT_MJMNMOIS12 
		= "mjmNmois12";

	/**
	 * "RG_SECTIONHIT_MJMNMOIS12_RENSEIGNE_01 : 
	 * la moyenne journalière mensuelle du mois de décembre (12) de l'année n en véhicules/jour de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS12_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJMNMOIS12_RENSEIGNE_01 : "
				+ "la moyenne journalière mensuelle du mois de décembre (12) de l'année n en véhicules/jour de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_MJMNMOIS12_REGEX_02 : 
	 * la moyenne journalière mensuelle du mois de décembre (12) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS12_REGEX_02 
		= "RG_SECTIONHIT_MJMNMOIS12_REGEX_02 : "
				+ "la moyenne journalière mensuelle du mois de décembre (12) de l'année n en véhicules/jour de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJMNMOIS12_NUMERIQUE_03 : 
	 * la moyenne journalière mensuelle du mois de décembre (12) de l'année n en véhicules/jour doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJMNMOIS12_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJMNMOIS12_NUMERIQUE_03 : "
				+ "la moyenne journalière mensuelle du mois de décembre (12) de l'année n en véhicules/jour doit être homogène à un entier";

	/* 67 - pcNuitNmois12. *******/
	/**
	 * "pcNuitNmois12".<br/>
	 */
	public static final String ATTRIBUT_PCNUITNMOIS12 
		= "pcNuitNmois12";

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS12_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de décembre (12) de l'année n de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS12_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITNMOIS12_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de décembre (12) de l'année n de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS12_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de décembre (12) de l'année n de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS12_REGEX_02 
		= "RG_SECTIONHIT_PCNUITNMOIS12_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de décembre (12) de l'année n de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITNMOIS12_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules mensuel du mois de décembre (12) de l'année n doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITNMOIS12_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITNMOIS12_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules mensuel du mois de décembre (12) de l'année n doit être homogène à un entier";	

	/* 68 - zoneLibre3. *******/
	/**
	 * "zoneLibre3".<br/>
	 */
	public static final String ATTRIBUT_ZONELIBRE3 
		= "zoneLibre3";

	/**
	 * "RG_SECTIONHIT_ZONELIBRE3_RENSEIGNE_01 : 
	 * la zone libre (3) de la section HIT doit être renseignée".
	 */
	public static final String RG_SECTIONHIT_ZONELIBRE3_RENSEIGNE_01 
		= "RG_SECTIONHIT_ZONELIBRE3_RENSEIGNE_01 : "
				+ "la zone libre (3) de la section HIT "
				+ "doit être renseignée";

	/**
	 * "RG_SECTIONHIT_ZONELIBRE3_REGEX_02 : 
	 * la zone libre (3) de la section HIT doit comporter exactement 12 espaces".
	 */
	public static final String RG_SECTIONHIT_ZONELIBRE3_REGEX_02 
		= "RG_SECTIONHIT_ZONELIBRE3_REGEX_02 : "
				+ "la zone libre (3) de la section HIT doit comporter exactement 12 espaces";

	/* 69 - anneeNmoins1. *******/
	/**
	 * "anneeNmoins1".<br/>
	 */
	public static final String ATTRIBUT_ANNEENMOINS1 
		= "anneeNmoins1";

	/**
	 * "RG_SECTIONHIT_ANNEENMOINS1_RENSEIGNE_01 : 
	 * l'année n-1 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_ANNEENMOINS1_RENSEIGNE_01 
		= "RG_SECTIONHIT_ANNEENMOINS1_RENSEIGNE_01 : "
				+ "l'année n-1 de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_ANNEENMOINS1_REGEX_02 : 
	 * l'année n-1 de la section HIT doit comporter exactement 2 chiffres".
	 */
	public static final String RG_SECTIONHIT_ANNEENMOINS1_REGEX_02 
		= "RG_SECTIONHIT_ANNEENMOINS1_REGEX_02 : "
				+ "l'année n-1 de la section HIT doit comporter exactement 2 chiffres";

	/* 70 - mjaNmoins1. *******/
	/**
	 * "mjaNmoins1".<br/>
	 */
	public static final String ATTRIBUT_MJANMOINS1 
		= "mjaNmoins1";

	/**
	 * "RG_SECTIONHIT_MJANMOINS1_RENSEIGNE_01 : 
	 * le trafic moyen journalier annuel de l'année n-1 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_MJANMOINS1_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJANMOINS1_RENSEIGNE_01 : "
				+ "le trafic moyen journalier annuel de l'année n-1 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_MJANMOINS1_REGEX_02 : 
	 * le trafic moyen journalier annuel de l'année n-1 de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJANMOINS1_REGEX_02 
		= "RG_SECTIONHIT_MJANMOINS1_REGEX_02 : "
				+ "le trafic moyen journalier annuel de l'année n-1 de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJANMOINS1_NUMERIQUE_03 : 
	 * le trafic moyen journalier annuel de l'année n-1 doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJANMOINS1_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJANMOINS1_NUMERIQUE_03 : "
				+ "le trafic moyen journalier annuel de l'année n-1 doit être homogène à un entier";

	/* 71 - typeComptageNmoins1. *******/
	/**
	 * "typeComptageNmoins1".<br/>
	 */
	public static final String ATTRIBUT_TYPECOMPTAGENMOINS1 
		= "typeComptageNmoins1";

	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGENMOINS1_RENSEIGNE_01 : 
	 * le type de comptage de l'année n-1 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGENMOINS1_RENSEIGNE_01 
		= "RG_SECTIONHIT_TYPECOMPTAGENMOINS1_RENSEIGNE_01 : "
				+ "le type de comptage de l'année n-1 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGENMOINS1_REGEX_02 : 
	 * le type de comptage de l'année n-1 de la section HIT doit comporter exactement 1 chiffre".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGENMOINS1_REGEX_02 
		= "RG_SECTIONHIT_TYPECOMPTAGENMOINS1_REGEX_02 : "
				+ "le type de comptage de l'année n-1 de la section HIT doit comporter exactement 1 chiffre";
	
	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGENMOINS1_NOMENCLATURE_03 : 
	 * le type de comptage de l'année n-1 doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGENMOINS1_NOMENCLATURE_03 
		= "RG_SECTIONHIT_TYPECOMPTAGENMOINS1_NOMENCLATURE_03 : "
				+ "le type de comptage de l'année n-1 doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]";
	
	/* 72 - modeCalculNmoins1. *******/
	/**
	 * "modeCalculNmoins1".<br/>
	 */
	public static final String ATTRIBUT_MODECALCULNMOINS1 
		= "modeCalculNmoins1";

	/**
	 * "RG_SECTIONHIT_MODECALCULNMOINS1_RENSEIGNE_01 : 
	 * le mode de calcul des trafics de l'année n-1 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_MODECALCULNMOINS1_RENSEIGNE_01 
		= "RG_SECTIONHIT_MODECALCULNMOINS1_RENSEIGNE_01 : "
				+ "le mode de calcul des trafics de l'année n-1 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_MODECALCULNMOINS1_REGEX_02 : 
	 * le mode de calcul des trafics de l'année n-1 de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_MODECALCULNMOINS1_REGEX_02 
		= "RG_SECTIONHIT_MODECALCULNMOINS1_REGEX_02 : "
				+ "le mode de calcul des trafics de l'année n-1 de la section HIT doit comporter exactement 1 caractère (ou espace)";

	/* 73 - pcPLNmoins1. *******/
	/**
	 * "pcPLNmoins1".<br/>
	 */
	public static final String ATTRIBUT_PCPLNMOINS1 
		= "pcPLNmoins1";

	/**
	 * "RG_SECTIONHIT_PCPLNMOINS1_RENSEIGNE_01 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n-1 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCPLNMOINS1_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCPLNMOINS1_RENSEIGNE_01 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n-1 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCPLNMOINS1_REGEX_02 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n-1 de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCPLNMOINS1_REGEX_02 
		= "RG_SECTIONHIT_PCPLNMOINS1_REGEX_02 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n-1 de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCPLNMOINS1_NUMERIQUE_03 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n-1 doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCPLNMOINS1_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCPLNMOINS1_NUMERIQUE_03 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n-1 doit être homogène à un entier";
	
	/* 74 - evaluationPLNmoins1. *******/
	/**
	 * "evaluationPLNmoins1".<br/>
	 */
	public static final String ATTRIBUT_EVALUATIONPLNMOINS1 
		= "evaluationPLNmoins1";

	/**
	 * "RG_SECTIONHIT_EVALUATIONPLNMOINS1_RENSEIGNE_01 : 
	 * l'évaluation des pourcentages de trafic poids lourds de l'année n-1 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_EVALUATIONPLNMOINS1_RENSEIGNE_01 
		= "RG_SECTIONHIT_EVALUATIONPLNMOINS1_RENSEIGNE_01 : "
				+ "l'évaluation des pourcentages de trafic poids lourds de l'année n-1 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_EVALUATIONPLNMOINS1_REGEX_02 : 
	 * l'évaluation des pourcentages de trafic poids lourds de l'année n-1 de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_EVALUATIONPLNMOINS1_REGEX_02 
		= "RG_SECTIONHIT_EVALUATIONPLNMOINS1_REGEX_02 : "
				+ "l'évaluation des pourcentages de trafic poids lourds de l'année n-1 de la section HIT doit comporter exactement 1 caractère (ou espace)";

	/* 75 - pcNuitAnnuelNmoins1. *******/
	/**
	 * "pcNuitAnnuelNmoins1".<br/>
	 */
	public static final String ATTRIBUT_PCNUITANNUELNMOINS1 
		= "pcNuitAnnuelNmoins1";

	/**
	 * "RG_SECTIONHIT_PCNUITANNUELNMOINS1_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n-1 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELNMOINS1_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITANNUELNMOINS1_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n-1 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITANNUELNMOINS1_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n-1 de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELNMOINS1_REGEX_02 
		= "RG_SECTIONHIT_PCNUITANNUELNMOINS1_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n-1 de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITANNUELNMOINS1_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n-1 doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELNMOINS1_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITANNUELNMOINS1_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n-1 doit être homogène à un entier";
	
	/* 76 - indiceFiabiliteMjaNmoins1. *******/
	/**
	 * "indiceFiabiliteMjaNmoins1".<br/>
	 */
	public static final String ATTRIBUT_INDICEFIABLITEMJANMOINS1 
		= "indiceFiabiliteMjaNmoins1";

	/**
	 * "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS1_RENSEIGNE_01 : 
	 * l'indice de fiabilité de la TMJA de l'année n-1 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_INDICEFIABLITEMJANMOINS1_RENSEIGNE_01 
		= "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS1_RENSEIGNE_01 : "
				+ "l'indice de fiabilité de la TMJA de l'année n-1 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS1_REGEX_02 : 
	 * l'indice de fiabilité de la TMJA de l'année n-1 de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_INDICEFIABLITEMJANMOINS1_REGEX_02 
		= "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS1_REGEX_02 : "
				+ "l'indice de fiabilité de la TMJA de l'année n-1 de la section HIT doit comporter exactement 1 caractère (ou espace)";

	/* 77 - anneeNmoins2. *******/
	/**
	 * "anneeNmoins2".<br/>
	 */
	public static final String ATTRIBUT_ANNEENMOINS2 
		= "anneeNmoins2";

	/**
	 * "RG_SECTIONHIT_ANNEENMOINS2_RENSEIGNE_01 : 
	 * l'année n-2 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_ANNEENMOINS2_RENSEIGNE_01 
		= "RG_SECTIONHIT_ANNEENMOINS2_RENSEIGNE_01 : "
				+ "l'année n-2 de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_ANNEENMOINS2_REGEX_02 : 
	 * l'année n-2 de la section HIT doit comporter exactement 2 chiffres".
	 */
	public static final String RG_SECTIONHIT_ANNEENMOINS2_REGEX_02 
		= "RG_SECTIONHIT_ANNEENMOINS2_REGEX_02 : "
				+ "l'année n-2 de la section HIT doit comporter exactement 2 chiffres";

	/* 78 - mjaNmoins2. *******/
	/**
	 * "mjaNmoins2".<br/>
	 */
	public static final String ATTRIBUT_MJANMOINS2 
		= "mjaNmoins2";

	/**
	 * "RG_SECTIONHIT_MJANMOINS2_RENSEIGNE_01 : 
	 * le trafic moyen journalier annuel de l'année n-2 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_MJANMOINS2_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJANMOINS2_RENSEIGNE_01 : "
				+ "le trafic moyen journalier annuel de l'année n-2 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_MJANMOINS2_REGEX_02 : 
	 * le trafic moyen journalier annuel de l'année n-2 de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJANMOINS2_REGEX_02 
		= "RG_SECTIONHIT_MJANMOINS2_REGEX_02 : "
				+ "le trafic moyen journalier annuel de l'année n-2 de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJANMOINS2_NUMERIQUE_03 : 
	 * le trafic moyen journalier annuel de l'année n-2 doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJANMOINS2_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJANMOINS2_NUMERIQUE_03 : "
				+ "le trafic moyen journalier annuel de l'année n-2 doit être homogène à un entier";

	/* 79 - typeComptageNmoins2. *******/
	/**
	 * "typeComptageNmoins2".<br/>
	 */
	public static final String ATTRIBUT_TYPECOMPTAGENMOINS2 
		= "typeComptageNmoins2";

	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGENMOINS2_RENSEIGNE_01 : 
	 * le type de comptage de l'année n-2 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGENMOINS2_RENSEIGNE_01 
		= "RG_SECTIONHIT_TYPECOMPTAGENMOINS2_RENSEIGNE_01 : "
				+ "le type de comptage de l'année n-2 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGENMOINS2_REGEX_02 : 
	 * le type de comptage de l'année n-2 de la section HIT doit comporter exactement 1 chiffre".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGENMOINS2_REGEX_02 
		= "RG_SECTIONHIT_TYPECOMPTAGENMOINS2_REGEX_02 : "
				+ "le type de comptage de l'année n-2 de la section HIT doit comporter exactement 1 chiffre";
	
	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGENMOINS2_NOMENCLATURE_03 : 
	 * le type de comptage de l'année n-2 doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGENMOINS2_NOMENCLATURE_03 
		= "RG_SECTIONHIT_TYPECOMPTAGENMOINS2_NOMENCLATURE_03 : "
				+ "le type de comptage de l'année n-2 doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]";
	
	/* 80 - modeCalculNmoins2. *******/
	/**
	 * "modeCalculNmoins2".<br/>
	 */
	public static final String ATTRIBUT_MODECALCULNMOINS2 
		= "modeCalculNmoins2";

	/**
	 * "RG_SECTIONHIT_MODECALCULNMOINS2_RENSEIGNE_01 : 
	 * le mode de calcul des trafics de l'année n-2 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_MODECALCULNMOINS2_RENSEIGNE_01 
		= "RG_SECTIONHIT_MODECALCULNMOINS2_RENSEIGNE_01 : "
				+ "le mode de calcul des trafics de l'année n-2 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_MODECALCULNMOINS2_REGEX_02 : 
	 * le mode de calcul des trafics de l'année n-2 de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_MODECALCULNMOINS2_REGEX_02 
		= "RG_SECTIONHIT_MODECALCULNMOINS2_REGEX_02 : "
				+ "le mode de calcul des trafics de l'année n-2 de la section HIT doit comporter exactement 1 caractère (ou espace)";

	/* 81 - pcPLNmoins2. *******/
	/**
	 * "pcPLNmoins2".<br/>
	 */
	public static final String ATTRIBUT_PCPLNMOINS2 
		= "pcPLNmoins2";

	/**
	 * "RG_SECTIONHIT_PCPLNMOINS2_RENSEIGNE_01 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n-2 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCPLNMOINS2_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCPLNMOINS2_RENSEIGNE_01 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n-2 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCPLNMOINS2_REGEX_02 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n-2 de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCPLNMOINS2_REGEX_02 
		= "RG_SECTIONHIT_PCPLNMOINS2_REGEX_02 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n-2 de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCPLNMOINS2_NUMERIQUE_03 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n-2 doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCPLNMOINS2_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCPLNMOINS2_NUMERIQUE_03 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n-2 doit être homogène à un entier";
	
	/* 82 - evaluationPLNmoins2. *******/
	/**
	 * "evaluationPLNmoins2".<br/>
	 */
	public static final String ATTRIBUT_EVALUATIONPLNMOINS2 
		= "evaluationPLNmoins2";

	/**
	 * "RG_SECTIONHIT_EVALUATIONPLNMOINS2_RENSEIGNE_01 : 
	 * l'évaluation des pourcentages de trafic poids lourds de l'année n-2 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_EVALUATIONPLNMOINS2_RENSEIGNE_01 
		= "RG_SECTIONHIT_EVALUATIONPLNMOINS2_RENSEIGNE_01 : "
				+ "l'évaluation des pourcentages de trafic poids lourds de l'année n-2 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_EVALUATIONPLNMOINS2_REGEX_02 : 
	 * l'évaluation des pourcentages de trafic poids lourds de l'année n-2 de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_EVALUATIONPLNMOINS2_REGEX_02 
		= "RG_SECTIONHIT_EVALUATIONPLNMOINS2_REGEX_02 : "
				+ "l'évaluation des pourcentages de trafic poids lourds de l'année n-2 de la section HIT doit comporter exactement 1 caractère (ou espace)";

	/* 83 - pcNuitAnnuelNmoins2. *******/
	/**
	 * "pcNuitAnnuelNmoins2".<br/>
	 */
	public static final String ATTRIBUT_PCNUITANNUELNMOINS2 
		= "pcNuitAnnuelNmoins2";

	/**
	 * "RG_SECTIONHIT_PCNUITANNUELNMOINS2_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n-2 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELNMOINS2_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITANNUELNMOINS2_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n-2 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITANNUELNMOINS2_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n-2 de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELNMOINS2_REGEX_02 
		= "RG_SECTIONHIT_PCNUITANNUELNMOINS2_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n-2 de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITANNUELNMOINS2_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n-2 doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELNMOINS2_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITANNUELNMOINS2_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n-2 doit être homogène à un entier";
	
	/* 84 - indiceFiabiliteMjaNmoins2. *******/
	/**
	 * "indiceFiabiliteMjaNmoins2".<br/>
	 */
	public static final String ATTRIBUT_INDICEFIABLITEMJANMOINS2 
		= "indiceFiabiliteMjaNmoins2";

	/**
	 * "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS2_RENSEIGNE_01 : 
	 * l'indice de fiabilité de la TMJA de l'année n-2 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_INDICEFIABLITEMJANMOINS2_RENSEIGNE_01 
		= "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS2_RENSEIGNE_01 : "
				+ "l'indice de fiabilité de la TMJA de l'année n-2 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS2_REGEX_02 : 
	 * l'indice de fiabilité de la TMJA de l'année n-2 de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_INDICEFIABLITEMJANMOINS2_REGEX_02 
		= "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS2_REGEX_02 : "
				+ "l'indice de fiabilité de la TMJA de l'année n-2 de la section HIT doit comporter exactement 1 caractère (ou espace)";


	/* 85 - anneeNmoins3. *******/
	/**
	 * "anneeNmoins3".<br/>
	 */
	public static final String ATTRIBUT_ANNEENMOINS3 
		= "anneeNmoins3";

	/**
	 * "RG_SECTIONHIT_ANNEENMOINS3_RENSEIGNE_01 : 
	 * l'année n-3 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_ANNEENMOINS3_RENSEIGNE_01 
		= "RG_SECTIONHIT_ANNEENMOINS3_RENSEIGNE_01 : "
				+ "l'année n-3 de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_ANNEENMOINS3_REGEX_02 : 
	 * l'année n-3 de la section HIT doit comporter exactement 2 chiffres".
	 */
	public static final String RG_SECTIONHIT_ANNEENMOINS3_REGEX_02 
		= "RG_SECTIONHIT_ANNEENMOINS3_REGEX_02 : "
				+ "l'année n-3 de la section HIT doit comporter exactement 2 chiffres";

	/* 86 - mjaNmoins3. *******/
	/**
	 * "mjaNmoins3".<br/>
	 */
	public static final String ATTRIBUT_MJANMOINS3 
		= "mjaNmoins3";

	/**
	 * "RG_SECTIONHIT_MJANMOINS3_RENSEIGNE_01 : 
	 * le trafic moyen journalier annuel de l'année n-3 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_MJANMOINS3_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJANMOINS3_RENSEIGNE_01 : "
				+ "le trafic moyen journalier annuel de l'année n-3 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_MJANMOINS3_REGEX_02 : 
	 * le trafic moyen journalier annuel de l'année n-3 de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJANMOINS3_REGEX_02 
		= "RG_SECTIONHIT_MJANMOINS3_REGEX_02 : "
				+ "le trafic moyen journalier annuel de l'année n-3 de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJANMOINS3_NUMERIQUE_03 : 
	 * le trafic moyen journalier annuel de l'année n-3 doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJANMOINS3_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJANMOINS3_NUMERIQUE_03 : "
				+ "le trafic moyen journalier annuel de l'année n-3 doit être homogène à un entier";

	/* 87 - typeComptageNmoins3. *******/
	/**
	 * "typeComptageNmoins3".<br/>
	 */
	public static final String ATTRIBUT_TYPECOMPTAGENMOINS3 
		= "typeComptageNmoins3";

	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGENMOINS3_RENSEIGNE_01 : 
	 * le type de comptage de l'année n-3 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGENMOINS3_RENSEIGNE_01 
		= "RG_SECTIONHIT_TYPECOMPTAGENMOINS3_RENSEIGNE_01 : "
				+ "le type de comptage de l'année n-3 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGENMOINS3_REGEX_02 : 
	 * le type de comptage de l'année n-3 de la section HIT doit comporter exactement 1 chiffre".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGENMOINS3_REGEX_02 
		= "RG_SECTIONHIT_TYPECOMPTAGENMOINS3_REGEX_02 : "
				+ "le type de comptage de l'année n-3 de la section HIT doit comporter exactement 1 chiffre";
	
	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGENMOINS3_NOMENCLATURE_03 : 
	 * le type de comptage de l'année n-3 doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGENMOINS3_NOMENCLATURE_03 
		= "RG_SECTIONHIT_TYPECOMPTAGENMOINS3_NOMENCLATURE_03 : "
				+ "le type de comptage de l'année n-3 doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]";
	
	/* 88 - modeCalculNmoins3. *******/
	/**
	 * "modeCalculNmoins3".<br/>
	 */
	public static final String ATTRIBUT_MODECALCULNMOINS3 
		= "modeCalculNmoins3";

	/**
	 * "RG_SECTIONHIT_MODECALCULNMOINS3_RENSEIGNE_01 : 
	 * le mode de calcul des trafics de l'année n-3 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_MODECALCULNMOINS3_RENSEIGNE_01 
		= "RG_SECTIONHIT_MODECALCULNMOINS3_RENSEIGNE_01 : "
				+ "le mode de calcul des trafics de l'année n-3 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_MODECALCULNMOINS3_REGEX_02 : 
	 * le mode de calcul des trafics de l'année n-3 de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_MODECALCULNMOINS3_REGEX_02 
		= "RG_SECTIONHIT_MODECALCULNMOINS3_REGEX_02 : "
				+ "le mode de calcul des trafics de l'année n-3 de la section HIT doit comporter exactement 1 caractère (ou espace)";

	/* 89 - pcPLNmoins3. *******/
	/**
	 * "pcPLNmoins3".<br/>
	 */
	public static final String ATTRIBUT_PCPLNMOINS3 
		= "pcPLNmoins3";

	/**
	 * "RG_SECTIONHIT_PCPLNMOINS3_RENSEIGNE_01 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n-3 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCPLNMOINS3_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCPLNMOINS3_RENSEIGNE_01 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n-3 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCPLNMOINS3_REGEX_02 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n-3 de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCPLNMOINS3_REGEX_02 
		= "RG_SECTIONHIT_PCPLNMOINS3_REGEX_02 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n-3 de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCPLNMOINS3_NUMERIQUE_03 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n-3 doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCPLNMOINS3_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCPLNMOINS3_NUMERIQUE_03 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n-3 doit être homogène à un entier";
	
	/* 90 - evaluationPLNmoins3. *******/
	/**
	 * "evaluationPLNmoins3".<br/>
	 */
	public static final String ATTRIBUT_EVALUATIONPLNMOINS3 
		= "evaluationPLNmoins3";

	/**
	 * "RG_SECTIONHIT_EVALUATIONPLNMOINS3_RENSEIGNE_01 : 
	 * l'évaluation des pourcentages de trafic poids lourds de l'année n-3 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_EVALUATIONPLNMOINS3_RENSEIGNE_01 
		= "RG_SECTIONHIT_EVALUATIONPLNMOINS3_RENSEIGNE_01 : "
				+ "l'évaluation des pourcentages de trafic poids lourds de l'année n-3 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_EVALUATIONPLNMOINS3_REGEX_02 : 
	 * l'évaluation des pourcentages de trafic poids lourds de l'année n-3 de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_EVALUATIONPLNMOINS3_REGEX_02 
		= "RG_SECTIONHIT_EVALUATIONPLNMOINS3_REGEX_02 : "
				+ "l'évaluation des pourcentages de trafic poids lourds de l'année n-3 de la section HIT doit comporter exactement 1 caractère (ou espace)";

	/* 91 - pcNuitAnnuelNmoins3. *******/
	/**
	 * "pcNuitAnnuelNmoins3".<br/>
	 */
	public static final String ATTRIBUT_PCNUITANNUELNMOINS3 
		= "pcNuitAnnuelNmoins3";

	/**
	 * "RG_SECTIONHIT_PCNUITANNUELNMOINS3_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n-3 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELNMOINS3_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITANNUELNMOINS3_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n-3 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITANNUELNMOINS3_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n-3 de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELNMOINS3_REGEX_02 
		= "RG_SECTIONHIT_PCNUITANNUELNMOINS3_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n-3 de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITANNUELNMOINS3_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n-3 doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELNMOINS3_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITANNUELNMOINS3_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n-3 doit être homogène à un entier";
	
	/* 92 - indiceFiabiliteMjaNmoins3. *******/
	/**
	 * "indiceFiabiliteMjaNmoins3".<br/>
	 */
	public static final String ATTRIBUT_INDICEFIABLITEMJANMOINS3 
		= "indiceFiabiliteMjaNmoins3";

	/**
	 * "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS3_RENSEIGNE_01 : 
	 * l'indice de fiabilité de la TMJA de l'année n-3 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_INDICEFIABLITEMJANMOINS3_RENSEIGNE_01 
		= "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS3_RENSEIGNE_01 : "
				+ "l'indice de fiabilité de la TMJA de l'année n-3 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS3_REGEX_02 : 
	 * l'indice de fiabilité de la TMJA de l'année n-3 de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_INDICEFIABLITEMJANMOINS3_REGEX_02 
		= "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS3_REGEX_02 : "
				+ "l'indice de fiabilité de la TMJA de l'année n-3 de la section HIT doit comporter exactement 1 caractère (ou espace)";


	/* 93 - anneeNmoins4. *******/
	/**
	 * "anneeNmoins4".<br/>
	 */
	public static final String ATTRIBUT_ANNEENMOINS4 
		= "anneeNmoins4";

	/**
	 * "RG_SECTIONHIT_ANNEENMOINS4_RENSEIGNE_01 : 
	 * l'année n-4 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_ANNEENMOINS4_RENSEIGNE_01 
		= "RG_SECTIONHIT_ANNEENMOINS4_RENSEIGNE_01 : "
				+ "l'année n-4 de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_ANNEENMOINS4_REGEX_02 : 
	 * l'année n-4 de la section HIT doit comporter exactement 2 chiffres".
	 */
	public static final String RG_SECTIONHIT_ANNEENMOINS4_REGEX_02 
		= "RG_SECTIONHIT_ANNEENMOINS4_REGEX_02 : "
				+ "l'année n-4 de la section HIT doit comporter exactement 2 chiffres";

	/* 94 - mjaNmoins4. *******/
	/**
	 * "mjaNmoins4".<br/>
	 */
	public static final String ATTRIBUT_MJANMOINS4 
		= "mjaNmoins4";

	/**
	 * "RG_SECTIONHIT_MJANMOINS4_RENSEIGNE_01 : 
	 * le trafic moyen journalier annuel de l'année n-4 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_MJANMOINS4_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJANMOINS4_RENSEIGNE_01 : "
				+ "le trafic moyen journalier annuel de l'année n-4 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_MJANMOINS4_REGEX_02 : 
	 * le trafic moyen journalier annuel de l'année n-4 de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJANMOINS4_REGEX_02 
		= "RG_SECTIONHIT_MJANMOINS4_REGEX_02 : "
				+ "le trafic moyen journalier annuel de l'année n-4 de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJANMOINS4_NUMERIQUE_03 : 
	 * le trafic moyen journalier annuel de l'année n-4 doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJANMOINS4_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJANMOINS4_NUMERIQUE_03 : "
				+ "le trafic moyen journalier annuel de l'année n-4 doit être homogène à un entier";

	/* 95 - typeComptageNmoins4. *******/
	/**
	 * "typeComptageNmoins4".<br/>
	 */
	public static final String ATTRIBUT_TYPECOMPTAGENMOINS4 
		= "typeComptageNmoins4";

	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGENMOINS4_RENSEIGNE_01 : 
	 * le type de comptage de l'année n-4 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGENMOINS4_RENSEIGNE_01 
		= "RG_SECTIONHIT_TYPECOMPTAGENMOINS4_RENSEIGNE_01 : "
				+ "le type de comptage de l'année n-4 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGENMOINS4_REGEX_02 : 
	 * le type de comptage de l'année n-4 de la section HIT doit comporter exactement 1 chiffre".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGENMOINS4_REGEX_02 
		= "RG_SECTIONHIT_TYPECOMPTAGENMOINS4_REGEX_02 : "
				+ "le type de comptage de l'année n-4 de la section HIT doit comporter exactement 1 chiffre";
	
	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGENMOINS4_NOMENCLATURE_03 : 
	 * le type de comptage de l'année n-4 doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGENMOINS4_NOMENCLATURE_03 
		= "RG_SECTIONHIT_TYPECOMPTAGENMOINS4_NOMENCLATURE_03 : "
				+ "le type de comptage de l'année n-4 doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]";
	
	/* 96 - modeCalculNmoins4. *******/
	/**
	 * "modeCalculNmoins4".<br/>
	 */
	public static final String ATTRIBUT_MODECALCULNMOINS4 
		= "modeCalculNmoins4";

	/**
	 * "RG_SECTIONHIT_MODECALCULNMOINS4_RENSEIGNE_01 : 
	 * le mode de calcul des trafics de l'année n-4 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_MODECALCULNMOINS4_RENSEIGNE_01 
		= "RG_SECTIONHIT_MODECALCULNMOINS4_RENSEIGNE_01 : "
				+ "le mode de calcul des trafics de l'année n-4 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_MODECALCULNMOINS4_REGEX_02 : 
	 * le mode de calcul des trafics de l'année n-4 de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_MODECALCULNMOINS4_REGEX_02 
		= "RG_SECTIONHIT_MODECALCULNMOINS4_REGEX_02 : "
				+ "le mode de calcul des trafics de l'année n-4 de la section HIT doit comporter exactement 1 caractère (ou espace)";

	/* 97 - pcPLNmoins4. *******/
	/**
	 * "pcPLNmoins4".<br/>
	 */
	public static final String ATTRIBUT_PCPLNMOINS4 
		= "pcPLNmoins4";

	/**
	 * "RG_SECTIONHIT_PCPLNMOINS4_RENSEIGNE_01 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n-4 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCPLNMOINS4_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCPLNMOINS4_RENSEIGNE_01 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n-4 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCPLNMOINS4_REGEX_02 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n-4 de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCPLNMOINS4_REGEX_02 
		= "RG_SECTIONHIT_PCPLNMOINS4_REGEX_02 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n-4 de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCPLNMOINS4_NUMERIQUE_03 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n-4 doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCPLNMOINS4_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCPLNMOINS4_NUMERIQUE_03 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n-4 doit être homogène à un entier";
	
	/* 98 - evaluationPLNmoins4. *******/
	/**
	 * "evaluationPLNmoins4".<br/>
	 */
	public static final String ATTRIBUT_EVALUATIONPLNMOINS4 
		= "evaluationPLNmoins4";

	/**
	 * "RG_SECTIONHIT_EVALUATIONPLNMOINS4_RENSEIGNE_01 : 
	 * l'évaluation des pourcentages de trafic poids lourds de l'année n-4 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_EVALUATIONPLNMOINS4_RENSEIGNE_01 
		= "RG_SECTIONHIT_EVALUATIONPLNMOINS4_RENSEIGNE_01 : "
				+ "l'évaluation des pourcentages de trafic poids lourds de l'année n-4 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_EVALUATIONPLNMOINS4_REGEX_02 : 
	 * l'évaluation des pourcentages de trafic poids lourds de l'année n-4 de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_EVALUATIONPLNMOINS4_REGEX_02 
		= "RG_SECTIONHIT_EVALUATIONPLNMOINS4_REGEX_02 : "
				+ "l'évaluation des pourcentages de trafic poids lourds de l'année n-4 de la section HIT doit comporter exactement 1 caractère (ou espace)";

	/* 99 - pcNuitAnnuelNmoins4. *******/
	/**
	 * "pcNuitAnnuelNmoins4".<br/>
	 */
	public static final String ATTRIBUT_PCNUITANNUELNMOINS4 
		= "pcNuitAnnuelNmoins4";

	/**
	 * "RG_SECTIONHIT_PCNUITANNUELNMOINS4_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n-4 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELNMOINS4_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITANNUELNMOINS4_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n-4 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITANNUELNMOINS4_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n-4 de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELNMOINS4_REGEX_02 
		= "RG_SECTIONHIT_PCNUITANNUELNMOINS4_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n-4 de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITANNUELNMOINS4_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n-4 doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELNMOINS4_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITANNUELNMOINS4_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n-4 doit être homogène à un entier";
	
	/* 100 - indiceFiabiliteMjaNmoins4. *******/
	/**
	 * "indiceFiabiliteMjaNmoins4".<br/>
	 */
	public static final String ATTRIBUT_INDICEFIABLITEMJANMOINS4 
		= "indiceFiabiliteMjaNmoins4";

	/**
	 * "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS4_RENSEIGNE_01 : 
	 * l'indice de fiabilité de la TMJA de l'année n-4 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_INDICEFIABLITEMJANMOINS4_RENSEIGNE_01 
		= "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS4_RENSEIGNE_01 : "
				+ "l'indice de fiabilité de la TMJA de l'année n-4 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS4_REGEX_02 : 
	 * l'indice de fiabilité de la TMJA de l'année n-4 de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_INDICEFIABLITEMJANMOINS4_REGEX_02 
		= "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS4_REGEX_02 : "
				+ "l'indice de fiabilité de la TMJA de l'année n-4 de la section HIT doit comporter exactement 1 caractère (ou espace)";


	/* 101 - anneeNmoins5. *******/
	/**
	 * "anneeNmoins5".<br/>
	 */
	public static final String ATTRIBUT_ANNEENMOINS5 
		= "anneeNmoins5";

	/**
	 * "RG_SECTIONHIT_ANNEENMOINS5_RENSEIGNE_01 : 
	 * l'année n-5 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_ANNEENMOINS5_RENSEIGNE_01 
		= "RG_SECTIONHIT_ANNEENMOINS5_RENSEIGNE_01 : "
				+ "l'année n-5 de la section HIT "
				+ DOIT_ETRE_RENSEIGNEE;

	/**
	 * "RG_SECTIONHIT_ANNEENMOINS5_REGEX_02 : 
	 * l'année n-5 de la section HIT doit comporter exactement 2 chiffres".
	 */
	public static final String RG_SECTIONHIT_ANNEENMOINS5_REGEX_02 
		= "RG_SECTIONHIT_ANNEENMOINS5_REGEX_02 : "
				+ "l'année n-5 de la section HIT doit comporter exactement 2 chiffres";

	/* 102 - mjaNmoins5. *******/
	/**
	 * "mjaNmoins5".<br/>
	 */
	public static final String ATTRIBUT_MJANMOINS5 
		= "mjaNmoins5";

	/**
	 * "RG_SECTIONHIT_MJANMOINS5_RENSEIGNE_01 : 
	 * le trafic moyen journalier annuel de l'année n-5 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_MJANMOINS5_RENSEIGNE_01 
		= "RG_SECTIONHIT_MJANMOINS5_RENSEIGNE_01 : "
				+ "le trafic moyen journalier annuel de l'année n-5 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_MJANMOINS5_REGEX_02 : 
	 * le trafic moyen journalier annuel de l'année n-5 de la section HIT doit comporter exactement 6 chiffres".
	 */
	public static final String RG_SECTIONHIT_MJANMOINS5_REGEX_02 
		= "RG_SECTIONHIT_MJANMOINS5_REGEX_02 : "
				+ "le trafic moyen journalier annuel de l'année n-5 de la section HIT doit comporter exactement 6 chiffres";
	
	/**
	 * "RG_SECTIONHIT_MJANMOINS5_NUMERIQUE_03 : 
	 * le trafic moyen journalier annuel de l'année n-5 doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_MJANMOINS5_NUMERIQUE_03 
		= "RG_SECTIONHIT_MJANMOINS5_NUMERIQUE_03 : "
				+ "le trafic moyen journalier annuel de l'année n-5 doit être homogène à un entier";

	/* 103 - typeComptageNmoins5. *******/
	/**
	 * "typeComptageNmoins5".<br/>
	 */
	public static final String ATTRIBUT_TYPECOMPTAGENMOINS5 
		= "typeComptageNmoins5";

	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGENMOINS5_RENSEIGNE_01 : 
	 * le type de comptage de l'année n-5 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGENMOINS5_RENSEIGNE_01 
		= "RG_SECTIONHIT_TYPECOMPTAGENMOINS5_RENSEIGNE_01 : "
				+ "le type de comptage de l'année n-5 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGENMOINS5_REGEX_02 : 
	 * le type de comptage de l'année n-5 de la section HIT doit comporter exactement 1 chiffre".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGENMOINS5_REGEX_02 
		= "RG_SECTIONHIT_TYPECOMPTAGENMOINS5_REGEX_02 : "
				+ "le type de comptage de l'année n-5 de la section HIT doit comporter exactement 1 chiffre";
	
	/**
	 * "RG_SECTIONHIT_TYPECOMPTAGENMOINS5_NOMENCLATURE_03 : 
	 * le type de comptage de l'année n-5 doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]".
	 */
	public static final String RG_SECTIONHIT_TYPECOMPTAGENMOINS5_NOMENCLATURE_03 
		= "RG_SECTIONHIT_TYPECOMPTAGENMOINS5_NOMENCLATURE_03 : "
				+ "le type de comptage de l'année n-5 doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]";
	
	/* 104 - modeCalculNmoins5. *******/
	/**
	 * "modeCalculNmoins5".<br/>
	 */
	public static final String ATTRIBUT_MODECALCULNMOINS5 
		= "modeCalculNmoins5";

	/**
	 * "RG_SECTIONHIT_MODECALCULNMOINS5_RENSEIGNE_01 : 
	 * le mode de calcul des trafics de l'année n-5 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_MODECALCULNMOINS5_RENSEIGNE_01 
		= "RG_SECTIONHIT_MODECALCULNMOINS5_RENSEIGNE_01 : "
				+ "le mode de calcul des trafics de l'année n-5 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_MODECALCULNMOINS5_REGEX_02 : 
	 * le mode de calcul des trafics de l'année n-5 de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_MODECALCULNMOINS5_REGEX_02 
		= "RG_SECTIONHIT_MODECALCULNMOINS5_REGEX_02 : "
				+ "le mode de calcul des trafics de l'année n-5 de la section HIT doit comporter exactement 1 caractère (ou espace)";

	/* 105 - pcPLNmoins5. *******/
	/**
	 * "pcPLNmoins5".<br/>
	 */
	public static final String ATTRIBUT_PCPLNMOINS5 
		= "pcPLNmoins5";

	/**
	 * "RG_SECTIONHIT_PCPLNMOINS5_RENSEIGNE_01 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n-5 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCPLNMOINS5_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCPLNMOINS5_RENSEIGNE_01 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n-5 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCPLNMOINS5_REGEX_02 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n-5 de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCPLNMOINS5_REGEX_02 
		= "RG_SECTIONHIT_PCPLNMOINS5_REGEX_02 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n-5 de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCPLNMOINS5_NUMERIQUE_03 : 
	 * le pourcentage de trafic poids lourds annuel de l'année n-5 doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCPLNMOINS5_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCPLNMOINS5_NUMERIQUE_03 : "
				+ "le pourcentage de trafic poids lourds annuel de l'année n-5 doit être homogène à un entier";
	
	/* 106 - evaluationPLNmoins5. *******/
	/**
	 * "evaluationPLNmoins5".<br/>
	 */
	public static final String ATTRIBUT_EVALUATIONPLNMOINS5 
		= "evaluationPLNmoins5";

	/**
	 * "RG_SECTIONHIT_EVALUATIONPLNMOINS5_RENSEIGNE_01 : 
	 * l'évaluation des pourcentages de trafic poids lourds de l'année n-5 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_EVALUATIONPLNMOINS5_RENSEIGNE_01 
		= "RG_SECTIONHIT_EVALUATIONPLNMOINS5_RENSEIGNE_01 : "
				+ "l'évaluation des pourcentages de trafic poids lourds de l'année n-5 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_EVALUATIONPLNMOINS5_REGEX_02 : 
	 * l'évaluation des pourcentages de trafic poids lourds de l'année n-5 de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_EVALUATIONPLNMOINS5_REGEX_02 
		= "RG_SECTIONHIT_EVALUATIONPLNMOINS5_REGEX_02 : "
				+ "l'évaluation des pourcentages de trafic poids lourds de l'année n-5 de la section HIT doit comporter exactement 1 caractère (ou espace)";

	/* 107 - pcNuitAnnuelNmoins5. *******/
	/**
	 * "pcNuitAnnuelNmoins5".<br/>
	 */
	public static final String ATTRIBUT_PCNUITANNUELNMOINS5 
		= "pcNuitAnnuelNmoins5";

	/**
	 * "RG_SECTIONHIT_PCNUITANNUELNMOINS5_RENSEIGNE_01 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n-5 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELNMOINS5_RENSEIGNE_01 
		= "RG_SECTIONHIT_PCNUITANNUELNMOINS5_RENSEIGNE_01 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n-5 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_PCNUITANNUELNMOINS5_REGEX_02 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n-5 de la section HIT doit comporter exactement 3 chiffres".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELNMOINS5_REGEX_02 
		= "RG_SECTIONHIT_PCNUITANNUELNMOINS5_REGEX_02 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n-5 de la section HIT doit comporter exactement 3 chiffres";
	
	/**
	 * "RG_SECTIONHIT_PCNUITANNUELNMOINS5_NUMERIQUE_03 : 
	 * le pourcentage de trafic de nuit tous véhicules annuel de l'année n-5 doit être homogène à un entier".
	 */
	public static final String RG_SECTIONHIT_PCNUITANNUELNMOINS5_NUMERIQUE_03 
		= "RG_SECTIONHIT_PCNUITANNUELNMOINS5_NUMERIQUE_03 : "
				+ "le pourcentage de trafic de nuit tous véhicules annuel de l'année n-5 doit être homogène à un entier";
	
	/* 108 - indiceFiabiliteMjaNmoins5. *******/
	/**
	 * "indiceFiabiliteMjaNmoins5".<br/>
	 */
	public static final String ATTRIBUT_INDICEFIABLITEMJANMOINS5 
		= "indiceFiabiliteMjaNmoins5";

	/**
	 * "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS5_RENSEIGNE_01 : 
	 * l'indice de fiabilité de la TMJA de l'année n-5 de la section HIT doit être renseigné".
	 */
	public static final String RG_SECTIONHIT_INDICEFIABLITEMJANMOINS5_RENSEIGNE_01 
		= "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS5_RENSEIGNE_01 : "
				+ "l'indice de fiabilité de la TMJA de l'année n-5 de la section HIT "
				+ DOIT_ETRE_RENSEIGNE;

	/**
	 * "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS5_REGEX_02 : 
	 * l'indice de fiabilité de la TMJA de l'année n-5 de la section HIT doit comporter exactement 1 caractère (ou espace)".
	 */
	public static final String RG_SECTIONHIT_INDICEFIABLITEMJANMOINS5_REGEX_02 
		= "RG_SECTIONHIT_INDICEFIABLITEMJANMOINS5_REGEX_02 : "
				+ "l'indice de fiabilité de la TMJA de l'année n-5 de la section HIT doit comporter exactement 1 caractère (ou espace)";

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitGestionnaireRG.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>Instancie this.bundleExterneRG (dans la super-classe).</li>
	 * <li>calcule le pathAbsoluPropertiesRG.</li>
	 * <li>remplit la Map des RG.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	public SectionHitGestionnaireRG() throws Exception {
		
		super();
		
		/* calcule le pathAbsoluPropertiesRG. */
		pathAbsoluPropertiesRG = this.getPathAbsoluPropertiesRG();
		
		/* remplit la Map des RG. */
		this.remplirMapRG();

	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	

	/**
	 * retourne un SINGLETON de 
	 * <code><b>pathAbsoluPropertiesRGString</b></code>.<br/>
	 *
	 * @return : String : 
	 * SINGLETON de <code><b>pathAbsoluPropertiesRGString</b></code>.<br/>
	 */
	private static String getPathAbsoluPropertiesRGString() {
		
		synchronized (AbstractGestionnaireRG.class) {
			
			if (pathAbsoluPropertiesRGString == null) {
				
				pathAbsoluPropertiesRGString 
					= pathAbsoluPropertiesRG.toString();
			}
			
			return pathAbsoluPropertiesRGString;
			
		} // Fin de synchronized.________________________
		
	} // Fin de getPathAbsoluPropertiesRGString()._________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirCheminRessourceExterneRG() throws Exception {
		
		final Path ressourcesExternesPath 
			= Paths.get(
					ConfigurationApplicationManager
						.getPathRessourcesExternes());
		
		final Path cheminRelatifRGPropertiesPath 
			= Paths.get(CHEMIN_RELATIF_FICHIER_PROPERTIES_RG);
		
		final Path pathAbsoluPropertiesRGPath 
			= ressourcesExternesPath
				.resolve(
						cheminRelatifRGPropertiesPath)
							.toAbsolutePath().normalize();
		
		final String pathAbsoluPropertiesRGLocal 
			= pathAbsoluPropertiesRGPath.toString();
		
		return pathAbsoluPropertiesRGLocal;
		
	} // Fin de fournirCheminRessourceExterneRG()._________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirNomBasePropertiesRG() {
		return "SectionHit_RG";
	} // Fin de fournirNomBasePropertiesRG().______________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final Map<String, LigneRG> remplirMapRG() 
			throws Exception {
		
		/* 1 - numDepartement. ***************/
		/* RG_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitNumDepartementRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumDepartement()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumDepartementRenseigne01()
				, RG_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNumDepartementRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_NUMDEPARTEMENT
				, CLASSE_CONTROLE
				, "validerRGSectionHitNumDepartementRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNumDepartementRenseigne01());
		
		/* RG_SECTIONHIT_NUMDEPARTEMENT_REGEX_02. */
		final LigneRG ligneRGSectionHitNumDepartementRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumDepartement()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumDepartementRegex02()
				, RG_SECTIONHIT_NUMDEPARTEMENT_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNumDepartementRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_NUMDEPARTEMENT
				, CLASSE_CONTROLE
				, "validerRGSectionHitNumDepartementRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNumDepartementRegex02());
		
		/* 2 - numSection. ***************/
		/* RG_SECTIONHIT_NUMSECTION_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitNumSectionRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumSection()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumSectionRenseigne01()
				, RG_SECTIONHIT_NUMSECTION_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNumSectionRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_NUMSECTION
				, CLASSE_CONTROLE
				, "validerRGSectionHitNumSectionRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNumSectionRenseigne01());
		
		/* RG_SECTIONHIT_NUMSECTION_REGEX_02. */
		final LigneRG ligneRGSectionHitNumSectionRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumSection()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumSectionRegex02()
				, RG_SECTIONHIT_NUMSECTION_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNumSectionRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_NUMSECTION
				, CLASSE_CONTROLE
				, "validerRGSectionHitNumSectionRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNumSectionRegex02());
		
		/* 3 - sens. ***************/
		/* RG_SECTIONHIT_SENS_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitSensRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSens()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSensRenseigne01()
				, RG_SECTIONHIT_SENS_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitSensRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_SENS
				, CLASSE_CONTROLE
				, "validerRGSectionHitSensRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitSensRenseigne01());
		
		/* RG_SECTIONHIT_SENS_REGEX_02. */
		final LigneRG ligneRGSectionHitSensRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSens()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSensRegex02()
				, RG_SECTIONHIT_SENS_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitSensRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_SENS
				, CLASSE_CONTROLE
				, "validerRGSectionHitSensRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitSensRegex02());

		/* RG_SECTIONHIT_SENS_NOMENCLATURE_03. */
		final LigneRG ligneRGSectionHitSensNomenclature03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSens()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSensNomenclature03()
				, RG_SECTIONHIT_SENS_NOMENCLATURE_03
				, EnumTypesValidation.NOMENCLATURE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitSensNomenclature03()
				, NOM_OBJETMETIER
				, ATTRIBUT_SENS
				, CLASSE_CONTROLE
				, "validerRGSectionHitSensNomenclature03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitSensNomenclature03());
		
		/* 4 - nature. ***************/
		/* RG_SECTIONHIT_NATURE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitNatureRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNature()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNatureRenseigne01()
				, RG_SECTIONHIT_NATURE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNatureRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_NATURE
				, CLASSE_CONTROLE
				, "validerRGSectionHitNatureRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNatureRenseigne01());
		
		/* RG_SECTIONHIT_NATURE_REGEX_02. */
		final LigneRG ligneRGSectionHitNatureRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNature()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNatureRegex02()
				, RG_SECTIONHIT_NATURE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNatureRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_NATURE
				, CLASSE_CONTROLE
				, "validerRGSectionHitNatureRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNatureRegex02());

		/* RG_SECTIONHIT_NATURE_NOMENCLATURE_03. */
		final LigneRG ligneRGSectionHitNatureNomenclature03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNature()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNatureNomenclature03()
				, RG_SECTIONHIT_NATURE_NOMENCLATURE_03
				, EnumTypesValidation.NOMENCLATURE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNatureNomenclature03()
				, NOM_OBJETMETIER
				, ATTRIBUT_NATURE
				, CLASSE_CONTROLE
				, "validerRGSectionHitNatureNomenclature03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNatureNomenclature03());
		
		/* 5 - classe. ***************/
		/* RG_SECTIONHIT_CLASSE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitClasseRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClasse()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClasseRenseigne01()
				, RG_SECTIONHIT_CLASSE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitClasseRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_CLASSE
				, CLASSE_CONTROLE
				, "validerRGSectionHitClasseRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitClasseRenseigne01());
		
		/* RG_SECTIONHIT_CLASSE_REGEX_02. */
		final LigneRG ligneRGSectionHitClasseRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClasse()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClasseRegex02()
				, RG_SECTIONHIT_CLASSE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitClasseRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_CLASSE
				, CLASSE_CONTROLE
				, "validerRGSectionHitClasseRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitClasseRegex02());
		
		/* 6 - anneeTraitement. *******/
		/* RG_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitAnneeTraitementRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeTraitement()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeTraitementRenseigne01()
				, RG_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAnneeTraitementRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_ANNEETRAITEMENT
				, CLASSE_CONTROLE
				, "validerRGSectionHitAnneeTraitementRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAnneeTraitementRenseigne01());
		
		/* RG_SECTIONHIT_ANNEETRAITEMENT_REGEX_02. */
		final LigneRG ligneRGSectionHitAnneeTraitementRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeTraitement()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeTraitementRegex02()
				, RG_SECTIONHIT_ANNEETRAITEMENT_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAnneeTraitementRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_ANNEETRAITEMENT
				, CLASSE_CONTROLE
				, "validerRGSectionHitAnneeTraitementRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAnneeTraitementRegex02());
		
		/* 7 - zoneLibre1. *******/
		/* RG_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitZoneLibre1Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitZoneLibre1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitZoneLibre1Renseigne01()
				, RG_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitZoneLibre1Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_ZONELIBRE1
				, CLASSE_CONTROLE
				, "validerRGSectionHitZoneLibre1Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitZoneLibre1Renseigne01());
		
		/* RG_SECTIONHIT_ZONELIBRE1_REGEX_02. */
		final LigneRG ligneRGSectionHitZoneLibre1Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitZoneLibre1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitZoneLibre1Regex02()
				, RG_SECTIONHIT_ZONELIBRE1_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitZoneLibre1Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_ZONELIBRE1
				, CLASSE_CONTROLE
				, "validerRGSectionHitZoneLibre1Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitZoneLibre1Regex02());
		
		/* 8 - numRoute. *******/
		/* RG_SECTIONHIT_NUMROUTE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitNumRouteRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumRoute()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumRouteRenseigne01()
				, RG_SECTIONHIT_NUMROUTE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNumRouteRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_NUMROUTE
				, CLASSE_CONTROLE
				, "validerRGSectionHitNumRouteRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNumRouteRenseigne01());
		
		/* RG_SECTIONHIT_NUMROUTE_REGEX_02. */
		final LigneRG ligneRGSectionHitNumRouteRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumRoute()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumRouteRegex02()
				, RG_SECTIONHIT_NUMROUTE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNumRouteRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_NUMROUTE
				, CLASSE_CONTROLE
				, "validerRGSectionHitNumRouteRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNumRouteRegex02());
		
		/* 9 - indiceNumRoute. *******/
		/* RG_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitIndiceNumRouteRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceNumRoute()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceNumRouteRenseigne01()
				, RG_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitIndiceNumRouteRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_INDICENUMROUTE
				, CLASSE_CONTROLE
				, "validerRGSectionHitIndiceNumRouteRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitIndiceNumRouteRenseigne01());
		
		/* RG_SECTIONHIT_INDICENUMROUTE_REGEX_02. */
		final LigneRG ligneRGSectionHitIndiceNumRouteRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceNumRoute()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceNumRouteRegex02()
				, RG_SECTIONHIT_INDICENUMROUTE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitIndiceNumRouteRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_INDICENUMROUTE
				, CLASSE_CONTROLE
				, "validerRGSectionHitIndiceNumRouteRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitIndiceNumRouteRegex02());
		
		/* 10 - indiceLettreRoute. *******/
		/* RG_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitIndiceLettreRouteRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceLettreRoute()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceLettreRouteRenseigne01()
				, RG_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitIndiceLettreRouteRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_INDICELETTREROUTE
				, CLASSE_CONTROLE
				, "validerRGSectionHitIndiceLettreRouteRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitIndiceLettreRouteRenseigne01());
		
		/* RG_SECTIONHIT_INDICELETTREROUTE_REGEX_02. */
		final LigneRG ligneRGSectionHitIndiceLettreRouteRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceLettreRoute()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceLettreRouteRegex02()
				, RG_SECTIONHIT_INDICELETTREROUTE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitIndiceLettreRouteRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_INDICELETTREROUTE
				, CLASSE_CONTROLE
				, "validerRGSectionHitIndiceLettreRouteRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitIndiceLettreRouteRegex02());
				
		/* 11 - categorieAdminRoute. *******/
		/* RG_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitCategorieAdminRouteRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitCategorieAdminRoute()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitCategorieAdminRouteRenseigne01()
				, RG_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitCategorieAdminRouteRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_CATEGORIEADMINROUTE
				, CLASSE_CONTROLE
				, "validerRGSectionHitCategorieAdminRouteRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitCategorieAdminRouteRenseigne01());
		
		/* RG_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02. */
		final LigneRG ligneRGSectionHitCategorieAdminRouteRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitCategorieAdminRoute()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitCategorieAdminRouteRegex02()
				, RG_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitCategorieAdminRouteRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_CATEGORIEADMINROUTE
				, CLASSE_CONTROLE
				, "validerRGSectionHitCategorieAdminRouteRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitCategorieAdminRouteRegex02());
				
		/* RG_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03. */
		final LigneRG ligneRGSectionHitCategorieAdminRouteNomenclature03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitCategorieAdminRoute()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitCategorieAdminRouteNomenclature03()
				, RG_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03
				, EnumTypesValidation.NOMENCLATURE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitCategorieAdminRouteNomenclature03()
				, NOM_OBJETMETIER
				, ATTRIBUT_CATEGORIEADMINROUTE
				, CLASSE_CONTROLE
				, "validerRGSectionHitCategorieAdminRouteNomenclature03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitCategorieAdminRouteNomenclature03());
		
		/* 12 - typeComptage. *******/
		/* RG_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitTypeComptageRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptage()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageRenseigne01()
				, RG_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGE
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageRenseigne01());
		
		/* RG_SECTIONHIT_TYPECOMPTAGE_REGEX_02. */
		final LigneRG ligneRGSectionHitTypeComptageRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptage()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageRegex02()
				, RG_SECTIONHIT_TYPECOMPTAGE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGE
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageRegex02());
				
		/* RG_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03. */
		final LigneRG ligneRGSectionHitTypeComptageNomenclature03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptage()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNomenclature03()
				, RG_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03
				, EnumTypesValidation.NOMENCLATURE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageNomenclature03()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGE
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageNomenclature03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageNomenclature03());
		
		/* 13 - classementRoute. *******/
		/* RG_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitClassementRouteRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClassementRoute()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClassementRouteRenseigne01()
				, RG_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitClassementRouteRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_CLASSEMENTROUTE
				, CLASSE_CONTROLE
				, "validerRGSectionHitClassementRouteRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitClassementRouteRenseigne01());
		
		/* RG_SECTIONHIT_CLASSEMENTROUTE_REGEX_02. */
		final LigneRG ligneRGSectionHitClassementRouteRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClassementRoute()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClassementRouteRegex02()
				, RG_SECTIONHIT_CLASSEMENTROUTE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitClassementRouteRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_CLASSEMENTROUTE
				, CLASSE_CONTROLE
				, "validerRGSectionHitClassementRouteRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitClassementRouteRegex02());
				
		/* RG_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03. */
		final LigneRG ligneRGSectionHitClassementRouteNomenclature03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClassementRoute()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClassementRouteNomenclature03()
				, RG_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03
				, EnumTypesValidation.NOMENCLATURE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitClassementRouteNomenclature03()
				, NOM_OBJETMETIER
				, ATTRIBUT_CLASSEMENTROUTE
				, CLASSE_CONTROLE
				, "validerRGSectionHitClassementRouteNomenclature03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitClassementRouteNomenclature03());
		
		/* 14 - classeLargeurChausseeU. *******/
		/* RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitClasseLargeurChausseeURenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClasseLargeurChausseeU()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClasseLargeurChausseeURenseigne01()
				, RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitClasseLargeurChausseeURenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_CLASSELARGEURCHAUSSEEU
				, CLASSE_CONTROLE
				, "validerRGSectionHitClasseLargeurChausseeURenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitClasseLargeurChausseeURenseigne01());
		
		/* RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02. */
		final LigneRG ligneRGSectionHitClasseLargeurChausseeURegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClasseLargeurChausseeU()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClasseLargeurChausseeURegex02()
				, RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitClasseLargeurChausseeURegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_CLASSELARGEURCHAUSSEEU
				, CLASSE_CONTROLE
				, "validerRGSectionHitClasseLargeurChausseeURegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitClasseLargeurChausseeURegex02());
				
		/* RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03. */
		final LigneRG ligneRGSectionHitClasseLargeurChausseeUNomenclature03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClasseLargeurChausseeU()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClasseLargeurChausseeUNomenclature03()
				, RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03
				, EnumTypesValidation.NOMENCLATURE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitClasseLargeurChausseeUNomenclature03()
				, NOM_OBJETMETIER
				, ATTRIBUT_CLASSELARGEURCHAUSSEEU
				, CLASSE_CONTROLE
				, "validerRGSectionHitClasseLargeurChausseeUNomenclature03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitClasseLargeurChausseeUNomenclature03());
		
		/* 15 - classeLargeurChausseesS. *******/
		/* RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitClasseLargeurChausseesSRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClasseLargeurChausseesS()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClasseLargeurChausseesSRenseigne01()
				, RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitClasseLargeurChausseesSRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_CLASSELARGEURCHAUSSEESS
				, CLASSE_CONTROLE
				, "validerRGSectionHitClasseLargeurChausseesSRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitClasseLargeurChausseesSRenseigne01());
		
		/* RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02. */
		final LigneRG ligneRGSectionHitClasseLargeurChausseesSRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClasseLargeurChausseesS()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClasseLargeurChausseesSRegex02()
				, RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitClasseLargeurChausseesSRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_CLASSELARGEURCHAUSSEESS
				, CLASSE_CONTROLE
				, "validerRGSectionHitClasseLargeurChausseesSRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitClasseLargeurChausseesSRegex02());
				
		/* RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03. */
		final LigneRG ligneRGSectionHitClasseLargeurChausseesSNomenclature03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClasseLargeurChausseesS()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitClasseLargeurChausseesSNomenclature03()
				, RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03
				, EnumTypesValidation.NOMENCLATURE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitClasseLargeurChausseesSNomenclature03()
				, NOM_OBJETMETIER
				, ATTRIBUT_CLASSELARGEURCHAUSSEESS
				, CLASSE_CONTROLE
				, "validerRGSectionHitClasseLargeurChausseesSNomenclature03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitClasseLargeurChausseesSNomenclature03());
		
		/* 16 - typeReseau. *******/
		/* RG_SECTIONHIT_TYPERESEAU_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitTypeReseauRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeReseau()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeReseauRenseigne01()
				, RG_SECTIONHIT_TYPERESEAU_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeReseauRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPERESEAU
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeReseauRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeReseauRenseigne01());
		
		/* RG_SECTIONHIT_TYPERESEAU_REGEX_02. */
		final LigneRG ligneRGSectionHitTypeReseauRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeReseau()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeReseauRegex02()
				, RG_SECTIONHIT_TYPERESEAU_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeReseauRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPERESEAU
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeReseauRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeReseauRegex02());
				
		/* RG_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03. */
		final LigneRG ligneRGSectionHitTypeReseauNomenclature03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeReseau()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeReseauNomenclature03()
				, RG_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03
				, EnumTypesValidation.NOMENCLATURE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeReseauNomenclature03()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPERESEAU
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeReseauNomenclature03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeReseauNomenclature03());
		
		/* 17 - pRoupK. *******/
		/* RG_SECTIONHIT_PROUPK_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPRoupKRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPRoupK()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPRoupKRenseigne01()
				, RG_SECTIONHIT_PROUPK_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPRoupKRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PROUPK
				, CLASSE_CONTROLE
				, "validerRGSectionHitPRoupKRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPRoupKRenseigne01());
		
		/* RG_SECTIONHIT_PROUPK_REGEX_02. */
		final LigneRG ligneRGSectionHitPRoupKRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPRoupK()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPRoupKRegex02()
				, RG_SECTIONHIT_PROUPK_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPRoupKRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PROUPK
				, CLASSE_CONTROLE
				, "validerRGSectionHitPRoupKRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPRoupKRegex02());
				
		/* RG_SECTIONHIT_PROUPK_NOMENCLATURE_03. */
		final LigneRG ligneRGSectionHitPRoupKNomenclature03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPRoupK()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPRoupKNomenclature03()
				, RG_SECTIONHIT_PROUPK_NOMENCLATURE_03
				, EnumTypesValidation.NOMENCLATURE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPRoupKNomenclature03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PROUPK
				, CLASSE_CONTROLE
				, "validerRGSectionHitPRoupKNomenclature03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPRoupKNomenclature03());
		
		/* 18 - lieuDitOrigine. *******/
		/* RG_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitLieuDitOrigineRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLieuDitOrigine()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLieuDitOrigineRenseigne01()
				, RG_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitLieuDitOrigineRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_LIEUDITORIGINE
				, CLASSE_CONTROLE
				, "validerRGSectionHitLieuDitOrigineRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitLieuDitOrigineRenseigne01());
		
		/* RG_SECTIONHIT_LIEUDITORIGINE_REGEX_02. */
		final LigneRG ligneRGSectionHitLieuDitOrigineRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLieuDitOrigine()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLieuDitOrigineRegex02()
				, RG_SECTIONHIT_LIEUDITORIGINE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitLieuDitOrigineRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_LIEUDITORIGINE
				, CLASSE_CONTROLE
				, "validerRGSectionHitLieuDitOrigineRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitLieuDitOrigineRegex02());
		
		/* 19 - prOrigine. *******/
		/* RG_SECTIONHIT_PRORIGINE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPrOrigineRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrOrigine()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrOrigineRenseigne01()
				, RG_SECTIONHIT_PRORIGINE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPrOrigineRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PRORIGINE
				, CLASSE_CONTROLE
				, "validerRGSectionHitPrOrigineRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPrOrigineRenseigne01());
		
		/* RG_SECTIONHIT_PRORIGINE_REGEX_02. */
		final LigneRG ligneRGSectionHitPrOrigineRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrOrigine()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrOrigineRegex02()
				, RG_SECTIONHIT_PRORIGINE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPrOrigineRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PRORIGINE
				, CLASSE_CONTROLE
				, "validerRGSectionHitPrOrigineRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPrOrigineRegex02());
				
		/* RG_SECTIONHIT_PRORIGINE_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPrOrigineNumerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrOrigine()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrOrigineNumerique03()
				, RG_SECTIONHIT_PRORIGINE_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPrOrigineNumerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PRORIGINE
				, CLASSE_CONTROLE
				, "validerRGSectionHitPrOrigineNumerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPrOrigineNumerique03());
		
		/* 20 - absOrigine. *******/
		/* RG_SECTIONHIT_ABSORIGINE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitAbsOrigineRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsOrigine()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsOrigineRenseigne01()
				, RG_SECTIONHIT_ABSORIGINE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAbsOrigineRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_ABSORIGINE
				, CLASSE_CONTROLE
				, "validerRGSectionHitAbsOrigineRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAbsOrigineRenseigne01());
		
		/* RG_SECTIONHIT_ABSORIGINE_REGEX_02. */
		final LigneRG ligneRGSectionHitAbsOrigineRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsOrigine()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsOrigineRegex02()
				, RG_SECTIONHIT_ABSORIGINE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAbsOrigineRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_ABSORIGINE
				, CLASSE_CONTROLE
				, "validerRGSectionHitAbsOrigineRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAbsOrigineRegex02());
				
		/* RG_SECTIONHIT_ABSORIGINE_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitAbsOrigineNumerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsOrigine()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsOrigineNumerique03()
				, RG_SECTIONHIT_ABSORIGINE_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAbsOrigineNumerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_ABSORIGINE
				, CLASSE_CONTROLE
				, "validerRGSectionHitAbsOrigineNumerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAbsOrigineNumerique03());
		
		/* 21 - lieuDitExtremite. *******/
		/* RG_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitLieuDitExtremiteRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLieuDitExtremite()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLieuDitExtremiteRenseigne01()
				, RG_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitLieuDitExtremiteRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_LIEUDITEXTREMITE
				, CLASSE_CONTROLE
				, "validerRGSectionHitLieuDitExtremiteRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitLieuDitExtremiteRenseigne01());
		
		/* RG_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02. */
		final LigneRG ligneRGSectionHitLieuDitExtremiteRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLieuDitExtremite()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLieuDitExtremiteRegex02()
				, RG_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitLieuDitExtremiteRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_LIEUDITEXTREMITE
				, CLASSE_CONTROLE
				, "validerRGSectionHitLieuDitExtremiteRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitLieuDitExtremiteRegex02());
		
		/* 22 - prExtremite. *******/
		/* RG_SECTIONHIT_PREXTREMITE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPrExtremiteRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrExtremite()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrExtremiteRenseigne01()
				, RG_SECTIONHIT_PREXTREMITE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPrExtremiteRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PREXTREMITE
				, CLASSE_CONTROLE
				, "validerRGSectionHitPrExtremiteRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPrExtremiteRenseigne01());
		
		/* RG_SECTIONHIT_PREXTREMITE_REGEX_02. */
		final LigneRG ligneRGSectionHitPrExtremiteRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrExtremite()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrExtremiteRegex02()
				, RG_SECTIONHIT_PREXTREMITE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPrExtremiteRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PREXTREMITE
				, CLASSE_CONTROLE
				, "validerRGSectionHitPrExtremiteRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPrExtremiteRegex02());
				
		/* RG_SECTIONHIT_PREXTREMITE_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPrExtremiteNumerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrExtremite()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrExtremiteNumerique03()
				, RG_SECTIONHIT_PREXTREMITE_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPrExtremiteNumerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PREXTREMITE
				, CLASSE_CONTROLE
				, "validerRGSectionHitPrExtremiteNumerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPrExtremiteNumerique03());
		
		/* 23 - absExtremite. *******/
		/* RG_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitAbsExtremiteRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsExtremite()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsExtremiteRenseigne01()
				, RG_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAbsExtremiteRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_ABSEXTREMITE
				, CLASSE_CONTROLE
				, "validerRGSectionHitAbsExtremiteRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAbsExtremiteRenseigne01());
		
		/* RG_SECTIONHIT_ABSEXTREMITE_REGEX_02. */
		final LigneRG ligneRGSectionHitAbsExtremiteRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsExtremite()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsExtremiteRegex02()
				, RG_SECTIONHIT_ABSEXTREMITE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAbsExtremiteRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_ABSEXTREMITE
				, CLASSE_CONTROLE
				, "validerRGSectionHitAbsExtremiteRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAbsExtremiteRegex02());
				
		/* RG_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitAbsExtremiteNumerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsExtremite()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsExtremiteNumerique03()
				, RG_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAbsExtremiteNumerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_ABSEXTREMITE
				, CLASSE_CONTROLE
				, "validerRGSectionHitAbsExtremiteNumerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAbsExtremiteNumerique03());
		
		/* 24 - lieuDitComptage. *******/
		/* RG_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitLieuDitComptageRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLieuDitComptage()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLieuDitComptageRenseigne01()
				, RG_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitLieuDitComptageRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_LIEUDITCOMPTAGE
				, CLASSE_CONTROLE
				, "validerRGSectionHitLieuDitComptageRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitLieuDitComptageRenseigne01());
		
		/* RG_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02. */
		final LigneRG ligneRGSectionHitLieuDitComptageRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLieuDitComptage()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLieuDitComptageRegex02()
				, RG_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitLieuDitComptageRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_LIEUDITCOMPTAGE
				, CLASSE_CONTROLE
				, "validerRGSectionHitLieuDitComptageRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitLieuDitComptageRegex02());
		
		/* 25 - prComptage. *******/
		/* RG_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPrComptageRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrComptage()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrComptageRenseigne01()
				, RG_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPrComptageRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PRCOMPTAGE
				, CLASSE_CONTROLE
				, "validerRGSectionHitPrComptageRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPrComptageRenseigne01());
		
		/* RG_SECTIONHIT_PRCOMPTAGE_REGEX_02. */
		final LigneRG ligneRGSectionHitPrComptageRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrComptage()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrComptageRegex02()
				, RG_SECTIONHIT_PRCOMPTAGE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPrComptageRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PRCOMPTAGE
				, CLASSE_CONTROLE
				, "validerRGSectionHitPrComptageRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPrComptageRegex02());
				
		/* RG_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPrComptageNumerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrComptage()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPrComptageNumerique03()
				, RG_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPrComptageNumerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PRCOMPTAGE
				, CLASSE_CONTROLE
				, "validerRGSectionHitPrComptageNumerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPrComptageNumerique03());
		
		/* 26 - absComptage. *******/
		/* RG_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitAbsComptageRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsComptage()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsComptageRenseigne01()
				, RG_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAbsComptageRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_ABSCOMPTAGE
				, CLASSE_CONTROLE
				, "validerRGSectionHitAbsComptageRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAbsComptageRenseigne01());
		
		/* RG_SECTIONHIT_ABSCOMPTAGE_REGEX_02. */
		final LigneRG ligneRGSectionHitAbsComptageRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsComptage()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsComptageRegex02()
				, RG_SECTIONHIT_ABSCOMPTAGE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAbsComptageRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_ABSCOMPTAGE
				, CLASSE_CONTROLE
				, "validerRGSectionHitAbsComptageRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAbsComptageRegex02());
				
		/* RG_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitAbsComptageNumerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsComptage()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAbsComptageNumerique03()
				, RG_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAbsComptageNumerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_ABSCOMPTAGE
				, CLASSE_CONTROLE
				, "validerRGSectionHitAbsComptageNumerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAbsComptageNumerique03());
		
		/* 27 - longueurSection. *******/
		/* RG_SECTIONHIT_LONGUEURSECTION_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitLongueurSectionRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLongueurSection()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLongueurSectionRenseigne01()
				, RG_SECTIONHIT_LONGUEURSECTION_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitLongueurSectionRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_LONGUEURSECTION
				, CLASSE_CONTROLE
				, "validerRGSectionHitLongueurSectionRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitLongueurSectionRenseigne01());
		
		/* RG_SECTIONHIT_LONGUEURSECTION_REGEX_02. */
		final LigneRG ligneRGSectionHitLongueurSectionRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLongueurSection()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLongueurSectionRegex02()
				, RG_SECTIONHIT_LONGUEURSECTION_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitLongueurSectionRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_LONGUEURSECTION
				, CLASSE_CONTROLE
				, "validerRGSectionHitLongueurSectionRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitLongueurSectionRegex02());
				
		/* RG_SECTIONHIT_LONGUEURSECTION_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitLongueurSectionNumerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLongueurSection()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLongueurSectionNumerique03()
				, RG_SECTIONHIT_LONGUEURSECTION_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitLongueurSectionNumerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_LONGUEURSECTION
				, CLASSE_CONTROLE
				, "validerRGSectionHitLongueurSectionNumerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitLongueurSectionNumerique03());
		
		/* 28 - longueurRaseCampagne. *******/
		/* RG_SECTIONHIT_LONGUEURRASECAMPAGNE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitLongueurRaseCampagneRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLongueurRaseCampagne()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLongueurRaseCampagneRenseigne01()
				, RG_SECTIONHIT_LONGUEURRASECAMPAGNE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitLongueurRaseCampagneRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_LONGUEURRASECAMPAGNE
				, CLASSE_CONTROLE
				, "validerRGSectionHitLongueurRaseCampagneRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitLongueurRaseCampagneRenseigne01());
		
		/* RG_SECTIONHIT_LONGUEURRASECAMPAGNE_REGEX_02. */
		final LigneRG ligneRGSectionHitLongueurRaseCampagneRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLongueurRaseCampagne()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLongueurRaseCampagneRegex02()
				, RG_SECTIONHIT_LONGUEURRASECAMPAGNE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitLongueurRaseCampagneRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_LONGUEURRASECAMPAGNE
				, CLASSE_CONTROLE
				, "validerRGSectionHitLongueurRaseCampagneRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitLongueurRaseCampagneRegex02());
				
		/* RG_SECTIONHIT_LONGUEURRASECAMPAGNE_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitLongueurRaseCampagneNumerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLongueurRaseCampagne()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitLongueurRaseCampagneNumerique03()
				, RG_SECTIONHIT_LONGUEURRASECAMPAGNE_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitLongueurRaseCampagneNumerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_LONGUEURRASECAMPAGNE
				, CLASSE_CONTROLE
				, "validerRGSectionHitLongueurRaseCampagneNumerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitLongueurRaseCampagneNumerique03());								
		
		/* 29 - numDepartementRattachement. *******/
		/* RG_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitNumDepartementRattachementRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumDepartementRattachement()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumDepartementRattachementRenseigne01()
				, RG_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNumDepartementRattachementRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_NUMDEPARTEMENTRATTACHEMENT
				, CLASSE_CONTROLE
				, "validerRGSectionHitNumDepartementRattachementRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNumDepartementRattachementRenseigne01());
		
		/* RG_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_REGEX_02. */
		final LigneRG ligneRGSectionHitNumDepartementRattachementRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumDepartementRattachement()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumDepartementRattachementRegex02()
				, RG_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNumDepartementRattachementRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_NUMDEPARTEMENTRATTACHEMENT
				, CLASSE_CONTROLE
				, "validerRGSectionHitNumDepartementRattachementRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNumDepartementRattachementRegex02());
		
		/* 30 - numSectionRattachement. *******/
		/* RG_SECTIONHIT_NUMSECTIONRATTACHEMENT_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitNumSectionRattachementRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumSectionRattachement()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumSectionRattachementRenseigne01()
				, RG_SECTIONHIT_NUMSECTIONRATTACHEMENT_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNumSectionRattachementRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_NUMSECTIONRATTACHEMENT
				, CLASSE_CONTROLE
				, "validerRGSectionHitNumSectionRattachementRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNumSectionRattachementRenseigne01());
		
		/* RG_SECTIONHIT_NUMSECTIONRATTACHEMENT_REGEX_02. */
		final LigneRG ligneRGSectionHitNumSectionRattachementRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumSectionRattachement()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumSectionRattachementRegex02()
				, RG_SECTIONHIT_NUMSECTIONRATTACHEMENT_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNumSectionRattachementRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_NUMSECTIONRATTACHEMENT
				, CLASSE_CONTROLE
				, "validerRGSectionHitNumSectionRattachementRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNumSectionRattachementRegex02());
		
		/* 31 - sensRattachement. *******/
		/* RG_SECTIONHIT_SENSRATTACHEMENT_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitSensRattachementRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSensRattachement()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSensRattachementRenseigne01()
				, RG_SECTIONHIT_SENSRATTACHEMENT_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitSensRattachementRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_SENSRATTACHEMENT
				, CLASSE_CONTROLE
				, "validerRGSectionHitSensRattachementRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitSensRattachementRenseigne01());
		
		/* RG_SECTIONHIT_SENSRATTACHEMENT_REGEX_02. */
		final LigneRG ligneRGSectionHitSensRattachementRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSensRattachement()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSensRattachementRegex02()
				, RG_SECTIONHIT_SENSRATTACHEMENT_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitSensRattachementRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_SENSRATTACHEMENT
				, CLASSE_CONTROLE
				, "validerRGSectionHitSensRattachementRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitSensRattachementRegex02());
				
		/* RG_SECTIONHIT_SENSRATTACHEMENT_NOMENCLATURE_03. */
		final LigneRG ligneRGSectionHitSensRattachementNomenclature03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSensRattachement()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSensRattachementNomenclature03()
				, RG_SECTIONHIT_SENSRATTACHEMENT_NOMENCLATURE_03
				, EnumTypesValidation.NOMENCLATURE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitSensRattachementNomenclature03()
				, NOM_OBJETMETIER
				, ATTRIBUT_SENSRATTACHEMENT
				, CLASSE_CONTROLE
				, "validerRGSectionHitSensRattachementNomenclature03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitSensRattachementNomenclature03());
		
		/* 32 - numDepartementLimitrophe. *******/
		/* RG_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitNumDepartementLimitropheRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumDepartementLimitrophe()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumDepartementLimitropheRenseigne01()
				, RG_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNumDepartementLimitropheRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_NUMDEPARTEMENTLIMITROPHE
				, CLASSE_CONTROLE
				, "validerRGSectionHitNumDepartementLimitropheRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNumDepartementLimitropheRenseigne01());
		
		/* RG_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_REGEX_02. */
		final LigneRG ligneRGSectionHitNumDepartementLimitropheRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumDepartementLimitrophe()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumDepartementLimitropheRegex02()
				, RG_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNumDepartementLimitropheRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_NUMDEPARTEMENTLIMITROPHE
				, CLASSE_CONTROLE
				, "validerRGSectionHitNumDepartementLimitropheRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNumDepartementLimitropheRegex02());
		
		/* 33 - numSectionLimitrophe. *******/
		/* RG_SECTIONHIT_NUMSECTIONLIMITROPHE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitNumSectionLimitropheRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumSectionLimitrophe()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumSectionLimitropheRenseigne01()
				, RG_SECTIONHIT_NUMSECTIONLIMITROPHE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNumSectionLimitropheRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_NUMSECTIONLIMITROPHE
				, CLASSE_CONTROLE
				, "validerRGSectionHitNumSectionLimitropheRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNumSectionLimitropheRenseigne01());
		
		/* RG_SECTIONHIT_NUMSECTIONLIMITROPHE_REGEX_02. */
		final LigneRG ligneRGSectionHitNumSectionLimitropheRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumSectionLimitrophe()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitNumSectionLimitropheRegex02()
				, RG_SECTIONHIT_NUMSECTIONLIMITROPHE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitNumSectionLimitropheRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_NUMSECTIONLIMITROPHE
				, CLASSE_CONTROLE
				, "validerRGSectionHitNumSectionLimitropheRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNumSectionLimitropheRegex02());
		
		/* 34 - sensLimitrophe. *******/
		/* RG_SECTIONHIT_SENSLIMITROPHE_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitSensLimitropheRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSensLimitrophe()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSensLimitropheRenseigne01()
				, RG_SECTIONHIT_SENSLIMITROPHE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitSensLimitropheRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_SENSLIMITROPHE
				, CLASSE_CONTROLE
				, "validerRGSectionHitSensLimitropheRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitSensLimitropheRenseigne01());
		
		/* RG_SECTIONHIT_SENSLIMITROPHE_REGEX_02. */
		final LigneRG ligneRGSectionHitSensLimitropheRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSensLimitrophe()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSensLimitropheRegex02()
				, RG_SECTIONHIT_SENSLIMITROPHE_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitSensLimitropheRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_SENSLIMITROPHE
				, CLASSE_CONTROLE
				, "validerRGSectionHitSensLimitropheRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitSensLimitropheRegex02());
				
		/* RG_SECTIONHIT_SENSLIMITROPHE_NOMENCLATURE_03. */
		final LigneRG ligneRGSectionHitSensLimitropheNomenclature03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSensLimitrophe()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitSensLimitropheNomenclature03()
				, RG_SECTIONHIT_SENSLIMITROPHE_NOMENCLATURE_03
				, EnumTypesValidation.NOMENCLATURE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitSensLimitropheNomenclature03()
				, NOM_OBJETMETIER
				, ATTRIBUT_SENSLIMITROPHE
				, CLASSE_CONTROLE
				, "validerRGSectionHitSensLimitropheNomenclature03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitSensLimitropheNomenclature03());
		
		/* 35 - moisSectionnement. *******/
		/* RG_SECTIONHIT_MOISSECTIONNEMENT_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMoisSectionnementRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMoisSectionnement()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMoisSectionnementRenseigne01()
				, RG_SECTIONHIT_MOISSECTIONNEMENT_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMoisSectionnementRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MOISSECTIONNEMENT
				, CLASSE_CONTROLE
				, "validerRGSectionHitMoisSectionnementRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMoisSectionnementRenseigne01());
		
		/* RG_SECTIONHIT_MOISSECTIONNEMENT_REGEX_02. */
		final LigneRG ligneRGSectionHitMoisSectionnementRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMoisSectionnement()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMoisSectionnementRegex02()
				, RG_SECTIONHIT_MOISSECTIONNEMENT_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMoisSectionnementRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MOISSECTIONNEMENT
				, CLASSE_CONTROLE
				, "validerRGSectionHitMoisSectionnementRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMoisSectionnementRegex02());
		
		/* 36 - anneeSectionnement. *******/
		/* RG_SECTIONHIT_ANNEESECTIONNEMENT_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitAnneeSectionnementRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeSectionnement()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeSectionnementRenseigne01()
				, RG_SECTIONHIT_ANNEESECTIONNEMENT_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAnneeSectionnementRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_ANNEESECTIONNEMENT
				, CLASSE_CONTROLE
				, "validerRGSectionHitAnneeSectionnementRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAnneeSectionnementRenseigne01());
		
		/* RG_SECTIONHIT_ANNEESECTIONNEMENT_REGEX_02. */
		final LigneRG ligneRGSectionHitAnneeSectionnementRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeSectionnement()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeSectionnementRegex02()
				, RG_SECTIONHIT_ANNEESECTIONNEMENT_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAnneeSectionnementRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_ANNEESECTIONNEMENT
				, CLASSE_CONTROLE
				, "validerRGSectionHitAnneeSectionnementRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAnneeSectionnementRegex02());
		
		/* 37 - zoneLibre2. *******/
		/* RG_SECTIONHIT_ZONELIBRE2_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitZoneLibre2Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitZoneLibre2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitZoneLibre2Renseigne01()
				, RG_SECTIONHIT_ZONELIBRE2_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitZoneLibre2Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_ZONELIBRE2
				, CLASSE_CONTROLE
				, "validerRGSectionHitZoneLibre2Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitZoneLibre2Renseigne01());
		
		/* RG_SECTIONHIT_ZONELIBRE2_REGEX_02. */
		final LigneRG ligneRGSectionHitZoneLibre2Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitZoneLibre2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitZoneLibre2Regex02()
				, RG_SECTIONHIT_ZONELIBRE2_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitZoneLibre2Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_ZONELIBRE2
				, CLASSE_CONTROLE
				, "validerRGSectionHitZoneLibre2Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitZoneLibre2Regex02());
		
		/* 38 - mjaN. *******/
		/* RG_SECTIONHIT_MJAN_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjaNRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaN()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNRenseigne01()
				, RG_SECTIONHIT_MJAN_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJAN
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNRenseigne01());
		
		/* RG_SECTIONHIT_MJAN_REGEX_02. */
		final LigneRG ligneRGSectionHitMjaNRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaN()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNRegex02()
				, RG_SECTIONHIT_MJAN_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJAN
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNRegex02());
				
		/* RG_SECTIONHIT_MJAN_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjaNNumerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaN()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNNumerique03()
				, RG_SECTIONHIT_MJAN_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNNumerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJAN
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNNumerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNNumerique03());
		
		/* 39 - modeCalculN. *******/
		/* RG_SECTIONHIT_MODECALCULN_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitModeCalculNRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculN()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNRenseigne01()
				, RG_SECTIONHIT_MODECALCULN_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitModeCalculNRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MODECALCULN
				, CLASSE_CONTROLE
				, "validerRGSectionHitModeCalculNRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitModeCalculNRenseigne01());
		
		/* RG_SECTIONHIT_MODECALCULN_REGEX_02. */
		final LigneRG ligneRGSectionHitModeCalculNRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculN()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNRegex02()
				, RG_SECTIONHIT_MODECALCULN_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitModeCalculNRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MODECALCULN
				, CLASSE_CONTROLE
				, "validerRGSectionHitModeCalculNRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitModeCalculNRegex02());
		
		/* 40 - pcPLN. *******/
		/* RG_SECTIONHIT_PCPLN_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcPLNRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLN()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNRenseigne01()
				, RG_SECTIONHIT_PCPLN_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLN
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNRenseigne01());
		
		/* RG_SECTIONHIT_PCPLN_REGEX_02. */
		final LigneRG ligneRGSectionHitPcPLNRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLN()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNRegex02()
				, RG_SECTIONHIT_PCPLN_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLN
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNRegex02());
				
		/* RG_SECTIONHIT_PCPLN_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcPLNNumerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLN()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNNumerique03()
				, RG_SECTIONHIT_PCPLN_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNNumerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLN
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNNumerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNNumerique03());
		
		/* 41 - evaluationPLN. *******/
		/* RG_SECTIONHIT_EVALUATIONPLN_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitEvaluationPLNRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLN()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNRenseigne01()
				, RG_SECTIONHIT_EVALUATIONPLN_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitEvaluationPLNRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_EVALUATIONPLN
				, CLASSE_CONTROLE
				, "validerRGSectionHitEvaluationPLNRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitEvaluationPLNRenseigne01());
		
		/* RG_SECTIONHIT_EVALUATIONPLN_REGEX_02. */
		final LigneRG ligneRGSectionHitEvaluationPLNRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLN()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNRegex02()
				, RG_SECTIONHIT_EVALUATIONPLN_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitEvaluationPLNRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_EVALUATIONPLN
				, CLASSE_CONTROLE
				, "validerRGSectionHitEvaluationPLNRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitEvaluationPLNRegex02());
		
		/* 42 - pcNuitAnnuelN. *******/
		/* RG_SECTIONHIT_PCNUITANNUELN_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelN()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNRenseigne01()
				, RG_SECTIONHIT_PCNUITANNUELN_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELN
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNRenseigne01());
		
		/* RG_SECTIONHIT_PCNUITANNUELN_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelN()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNRegex02()
				, RG_SECTIONHIT_PCNUITANNUELN_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELN
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNRegex02());
				
		/* RG_SECTIONHIT_PCNUITANNUELN_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNNumerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelN()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNNumerique03()
				, RG_SECTIONHIT_PCNUITANNUELN_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNNumerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELN
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNNumerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNNumerique03());
		
		/* 43 - indiceFiabiliteMjaN. *******/
		/* RG_SECTIONHIT_INDICEFIABLITEMJAN_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitIndiceFiabiliteMjaNRenseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaN()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01()
				, RG_SECTIONHIT_INDICEFIABLITEMJAN_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitIndiceFiabiliteMjaNRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_INDICEFIABLITEMJAN
				, CLASSE_CONTROLE
				, "validerRGSectionHitIndiceFiabiliteMjaNRenseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01());
		
		/* RG_SECTIONHIT_INDICEFIABLITEMJAN_REGEX_02. */
		final LigneRG ligneRGSectionHitIndiceFiabiliteMjaNRegex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaN()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNRegex02()
				, RG_SECTIONHIT_INDICEFIABLITEMJAN_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitIndiceFiabiliteMjaNRegex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_INDICEFIABLITEMJAN
				, CLASSE_CONTROLE
				, "validerRGSectionHitIndiceFiabiliteMjaNRegex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNRegex02());
		
		/* 44 - mjmNmois01. *******/
		/* RG_SECTIONHIT_MJMNMOIS01_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjmNmois01Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois01()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois01Renseigne01()
				, RG_SECTIONHIT_MJMNMOIS01_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois01Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS01
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois01Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois01Renseigne01());
		
		/* RG_SECTIONHIT_MJMNMOIS01_REGEX_02. */
		final LigneRG ligneRGSectionHitMjmNmois01Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois01()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois01Regex02()
				, RG_SECTIONHIT_MJMNMOIS01_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois01Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS01
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois01Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois01Regex02());
				
		/* RG_SECTIONHIT_MJMNMOIS01_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjmNmois01Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois01()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois01Numerique03()
				, RG_SECTIONHIT_MJMNMOIS01_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois01Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS01
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois01Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois01Numerique03());
		
		/* 45 - pcNuitNmois01. *******/
		/* RG_SECTIONHIT_PCNUITNMOIS01_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitNmois01Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois01()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois01Renseigne01()
				, RG_SECTIONHIT_PCNUITNMOIS01_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois01Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS01
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois01Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois01Renseigne01());
		
		/* RG_SECTIONHIT_PCNUITNMOIS01_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitNmois01Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois01()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois01Regex02()
				, RG_SECTIONHIT_PCNUITNMOIS01_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois01Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS01
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois01Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois01Regex02());
				
		/* RG_SECTIONHIT_PCNUITNMOIS01_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitNmois01Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois01()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois01Numerique03()
				, RG_SECTIONHIT_PCNUITNMOIS01_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois01Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS01
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois01Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois01Numerique03());
		
		/* 46 - mjmNmois02. *******/
		/* RG_SECTIONHIT_MJMNMOIS02_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjmNmois02Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois02()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois02Renseigne01()
				, RG_SECTIONHIT_MJMNMOIS02_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois02Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS02
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois02Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois02Renseigne01());
		
		/* RG_SECTIONHIT_MJMNMOIS02_REGEX_02. */
		final LigneRG ligneRGSectionHitMjmNmois02Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois02()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois02Regex02()
				, RG_SECTIONHIT_MJMNMOIS02_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois02Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS02
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois02Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois02Regex02());
				
		/* RG_SECTIONHIT_MJMNMOIS02_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjmNmois02Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois02()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois02Numerique03()
				, RG_SECTIONHIT_MJMNMOIS02_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois02Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS02
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois02Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois02Numerique03());
		
		/* 47 - pcNuitNmois02. *******/
		/* RG_SECTIONHIT_PCNUITNMOIS02_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitNmois02Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois02()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois02Renseigne01()
				, RG_SECTIONHIT_PCNUITNMOIS02_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois02Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS02
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois02Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois02Renseigne01());
		
		/* RG_SECTIONHIT_PCNUITNMOIS02_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitNmois02Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois02()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois02Regex02()
				, RG_SECTIONHIT_PCNUITNMOIS02_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois02Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS02
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois02Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois02Regex02());
				
		/* RG_SECTIONHIT_PCNUITNMOIS02_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitNmois02Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois02()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois02Numerique03()
				, RG_SECTIONHIT_PCNUITNMOIS02_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois02Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS02
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois02Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois02Numerique03());
		
		/* 48 - mjmNmois03. *******/
		/* RG_SECTIONHIT_MJMNMOIS03_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjmNmois03Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois03()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois03Renseigne01()
				, RG_SECTIONHIT_MJMNMOIS03_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois03Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS03
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois03Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois03Renseigne01());
		
		/* RG_SECTIONHIT_MJMNMOIS03_REGEX_02. */
		final LigneRG ligneRGSectionHitMjmNmois03Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois03()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois03Regex02()
				, RG_SECTIONHIT_MJMNMOIS03_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois03Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS03
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois03Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois03Regex02());
				
		/* RG_SECTIONHIT_MJMNMOIS03_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjmNmois03Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois03()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois03Numerique03()
				, RG_SECTIONHIT_MJMNMOIS03_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois03Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS03
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois03Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois03Numerique03());
		
		/* 49 - pcNuitNmois03. *******/
		/* RG_SECTIONHIT_PCNUITNMOIS03_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitNmois03Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois03()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois03Renseigne01()
				, RG_SECTIONHIT_PCNUITNMOIS03_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois03Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS03
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois03Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois03Renseigne01());
		
		/* RG_SECTIONHIT_PCNUITNMOIS03_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitNmois03Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois03()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois03Regex02()
				, RG_SECTIONHIT_PCNUITNMOIS03_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois03Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS03
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois03Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois03Regex02());
				
		/* RG_SECTIONHIT_PCNUITNMOIS03_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitNmois03Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois03()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois03Numerique03()
				, RG_SECTIONHIT_PCNUITNMOIS03_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois03Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS03
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois03Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois03Numerique03());
		
		/* 50 - mjmNmois04. *******/
		/* RG_SECTIONHIT_MJMNMOIS04_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjmNmois04Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois04()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois04Renseigne01()
				, RG_SECTIONHIT_MJMNMOIS04_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois04Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS04
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois04Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois04Renseigne01());
		
		/* RG_SECTIONHIT_MJMNMOIS04_REGEX_02. */
		final LigneRG ligneRGSectionHitMjmNmois04Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois04()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois04Regex02()
				, RG_SECTIONHIT_MJMNMOIS04_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois04Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS04
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois04Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois04Regex02());
				
		/* RG_SECTIONHIT_MJMNMOIS04_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjmNmois04Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois04()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois04Numerique03()
				, RG_SECTIONHIT_MJMNMOIS04_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois04Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS04
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois04Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois04Numerique03());
		
		/* 51 - pcNuitNmois04. *******/
		/* RG_SECTIONHIT_PCNUITNMOIS04_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitNmois04Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois04()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois04Renseigne01()
				, RG_SECTIONHIT_PCNUITNMOIS04_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois04Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS04
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois04Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois04Renseigne01());
		
		/* RG_SECTIONHIT_PCNUITNMOIS04_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitNmois04Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois04()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois04Regex02()
				, RG_SECTIONHIT_PCNUITNMOIS04_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois04Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS04
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois04Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois04Regex02());
				
		/* RG_SECTIONHIT_PCNUITNMOIS04_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitNmois04Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois04()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois04Numerique03()
				, RG_SECTIONHIT_PCNUITNMOIS04_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois04Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS04
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois04Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois04Numerique03());

		
		/* 52 - mjmNmois05. *******/
		/* RG_SECTIONHIT_MJMNMOIS05_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjmNmois05Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois05()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois05Renseigne01()
				, RG_SECTIONHIT_MJMNMOIS05_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois05Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS05
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois05Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois05Renseigne01());
		
		/* RG_SECTIONHIT_MJMNMOIS05_REGEX_02. */
		final LigneRG ligneRGSectionHitMjmNmois05Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois05()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois05Regex02()
				, RG_SECTIONHIT_MJMNMOIS05_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois05Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS05
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois05Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois05Regex02());
				
		/* RG_SECTIONHIT_MJMNMOIS05_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjmNmois05Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois05()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois05Numerique03()
				, RG_SECTIONHIT_MJMNMOIS05_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois05Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS05
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois05Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois05Numerique03());
		
		/* 53 - pcNuitNmois05. *******/
		/* RG_SECTIONHIT_PCNUITNMOIS05_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitNmois05Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois05()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois05Renseigne01()
				, RG_SECTIONHIT_PCNUITNMOIS05_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois05Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS05
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois05Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois05Renseigne01());
		
		/* RG_SECTIONHIT_PCNUITNMOIS05_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitNmois05Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois05()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois05Regex02()
				, RG_SECTIONHIT_PCNUITNMOIS05_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois05Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS05
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois05Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois05Regex02());
				
		/* RG_SECTIONHIT_PCNUITNMOIS05_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitNmois05Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois05()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois05Numerique03()
				, RG_SECTIONHIT_PCNUITNMOIS05_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois05Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS05
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois05Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois05Numerique03());

		
		/* 54 - mjmNmois06. *******/
		/* RG_SECTIONHIT_MJMNMOIS06_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjmNmois06Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois06()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois06Renseigne01()
				, RG_SECTIONHIT_MJMNMOIS06_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois06Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS06
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois06Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois06Renseigne01());
		
		/* RG_SECTIONHIT_MJMNMOIS06_REGEX_02. */
		final LigneRG ligneRGSectionHitMjmNmois06Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois06()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois06Regex02()
				, RG_SECTIONHIT_MJMNMOIS06_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois06Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS06
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois06Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois06Regex02());
				
		/* RG_SECTIONHIT_MJMNMOIS06_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjmNmois06Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois06()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois06Numerique03()
				, RG_SECTIONHIT_MJMNMOIS06_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois06Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS06
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois06Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois06Numerique03());
		
		/* 55 - pcNuitNmois06. *******/
		/* RG_SECTIONHIT_PCNUITNMOIS06_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitNmois06Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois06()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois06Renseigne01()
				, RG_SECTIONHIT_PCNUITNMOIS06_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois06Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS06
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois06Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois06Renseigne01());
		
		/* RG_SECTIONHIT_PCNUITNMOIS06_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitNmois06Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois06()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois06Regex02()
				, RG_SECTIONHIT_PCNUITNMOIS06_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois06Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS06
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois06Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois06Regex02());
				
		/* RG_SECTIONHIT_PCNUITNMOIS06_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitNmois06Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois06()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois06Numerique03()
				, RG_SECTIONHIT_PCNUITNMOIS06_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois06Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS06
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois06Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois06Numerique03());

		
		/* 56 - mjmNmois07. *******/
		/* RG_SECTIONHIT_MJMNMOIS07_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjmNmois07Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois07()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois07Renseigne01()
				, RG_SECTIONHIT_MJMNMOIS07_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois07Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS07
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois07Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois07Renseigne01());
		
		/* RG_SECTIONHIT_MJMNMOIS07_REGEX_02. */
		final LigneRG ligneRGSectionHitMjmNmois07Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois07()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois07Regex02()
				, RG_SECTIONHIT_MJMNMOIS07_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois07Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS07
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois07Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois07Regex02());
				
		/* RG_SECTIONHIT_MJMNMOIS07_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjmNmois07Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois07()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois07Numerique03()
				, RG_SECTIONHIT_MJMNMOIS07_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois07Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS07
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois07Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois07Numerique03());
		
		/* 57 - pcNuitNmois07. *******/
		/* RG_SECTIONHIT_PCNUITNMOIS07_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitNmois07Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois07()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois07Renseigne01()
				, RG_SECTIONHIT_PCNUITNMOIS07_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois07Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS07
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois07Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois07Renseigne01());
		
		/* RG_SECTIONHIT_PCNUITNMOIS07_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitNmois07Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois07()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois07Regex02()
				, RG_SECTIONHIT_PCNUITNMOIS07_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois07Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS07
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois07Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois07Regex02());
				
		/* RG_SECTIONHIT_PCNUITNMOIS07_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitNmois07Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois07()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois07Numerique03()
				, RG_SECTIONHIT_PCNUITNMOIS07_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois07Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS07
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois07Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois07Numerique03());

		
		/* 58 - mjmNmois08. *******/
		/* RG_SECTIONHIT_MJMNMOIS08_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjmNmois08Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois08()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois08Renseigne01()
				, RG_SECTIONHIT_MJMNMOIS08_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois08Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS08
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois08Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois08Renseigne01());
		
		/* RG_SECTIONHIT_MJMNMOIS08_REGEX_02. */
		final LigneRG ligneRGSectionHitMjmNmois08Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois08()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois08Regex02()
				, RG_SECTIONHIT_MJMNMOIS08_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois08Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS08
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois08Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois08Regex02());
				
		/* RG_SECTIONHIT_MJMNMOIS08_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjmNmois08Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois08()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois08Numerique03()
				, RG_SECTIONHIT_MJMNMOIS08_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois08Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS08
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois08Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois08Numerique03());
		
		/* 59 - pcNuitNmois08. *******/
		/* RG_SECTIONHIT_PCNUITNMOIS08_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitNmois08Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois08()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois08Renseigne01()
				, RG_SECTIONHIT_PCNUITNMOIS08_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois08Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS08
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois08Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois08Renseigne01());
		
		/* RG_SECTIONHIT_PCNUITNMOIS08_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitNmois08Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois08()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois08Regex02()
				, RG_SECTIONHIT_PCNUITNMOIS08_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois08Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS08
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois08Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois08Regex02());
				
		/* RG_SECTIONHIT_PCNUITNMOIS08_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitNmois08Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois08()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois08Numerique03()
				, RG_SECTIONHIT_PCNUITNMOIS08_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois08Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS08
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois08Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois08Numerique03());

		
		/* 60 - mjmNmois09. *******/
		/* RG_SECTIONHIT_MJMNMOIS09_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjmNmois09Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois09()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois09Renseigne01()
				, RG_SECTIONHIT_MJMNMOIS09_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois09Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS09
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois09Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois09Renseigne01());
		
		/* RG_SECTIONHIT_MJMNMOIS09_REGEX_02. */
		final LigneRG ligneRGSectionHitMjmNmois09Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois09()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois09Regex02()
				, RG_SECTIONHIT_MJMNMOIS09_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois09Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS09
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois09Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois09Regex02());
				
		/* RG_SECTIONHIT_MJMNMOIS09_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjmNmois09Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois09()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois09Numerique03()
				, RG_SECTIONHIT_MJMNMOIS09_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois09Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS09
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois09Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois09Numerique03());
		
		/* 61 - pcNuitNmois09. *******/
		/* RG_SECTIONHIT_PCNUITNMOIS09_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitNmois09Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois09()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois09Renseigne01()
				, RG_SECTIONHIT_PCNUITNMOIS09_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois09Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS09
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois09Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois09Renseigne01());
		
		/* RG_SECTIONHIT_PCNUITNMOIS09_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitNmois09Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois09()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois09Regex02()
				, RG_SECTIONHIT_PCNUITNMOIS09_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois09Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS09
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois09Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois09Regex02());
				
		/* RG_SECTIONHIT_PCNUITNMOIS09_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitNmois09Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois09()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois09Numerique03()
				, RG_SECTIONHIT_PCNUITNMOIS09_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois09Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS09
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois09Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois09Numerique03());

		
		/* 62 - mjmNmois10. *******/
		/* RG_SECTIONHIT_MJMNMOIS10_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjmNmois10Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois10()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois10Renseigne01()
				, RG_SECTIONHIT_MJMNMOIS10_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois10Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS10
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois10Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois10Renseigne01());
		
		/* RG_SECTIONHIT_MJMNMOIS10_REGEX_02. */
		final LigneRG ligneRGSectionHitMjmNmois10Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois10()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois10Regex02()
				, RG_SECTIONHIT_MJMNMOIS10_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois10Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS10
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois10Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois10Regex02());
				
		/* RG_SECTIONHIT_MJMNMOIS10_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjmNmois10Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois10()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois10Numerique03()
				, RG_SECTIONHIT_MJMNMOIS10_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois10Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS10
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois10Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois10Numerique03());
		
		/* 63 - pcNuitNmois10. *******/
		/* RG_SECTIONHIT_PCNUITNMOIS10_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitNmois10Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois10()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois10Renseigne01()
				, RG_SECTIONHIT_PCNUITNMOIS10_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois10Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS10
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois10Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois10Renseigne01());
		
		/* RG_SECTIONHIT_PCNUITNMOIS10_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitNmois10Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois10()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois10Regex02()
				, RG_SECTIONHIT_PCNUITNMOIS10_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois10Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS10
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois10Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois10Regex02());
				
		/* RG_SECTIONHIT_PCNUITNMOIS10_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitNmois10Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois10()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois10Numerique03()
				, RG_SECTIONHIT_PCNUITNMOIS10_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois10Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS10
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois10Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois10Numerique03());

		
		/* 64 - mjmNmois11. *******/
		/* RG_SECTIONHIT_MJMNMOIS11_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjmNmois11Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois11()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois11Renseigne01()
				, RG_SECTIONHIT_MJMNMOIS11_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois11Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS11
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois11Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois11Renseigne01());
		
		/* RG_SECTIONHIT_MJMNMOIS11_REGEX_02. */
		final LigneRG ligneRGSectionHitMjmNmois11Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois11()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois11Regex02()
				, RG_SECTIONHIT_MJMNMOIS11_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois11Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS11
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois11Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois11Regex02());
				
		/* RG_SECTIONHIT_MJMNMOIS11_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjmNmois11Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois11()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois11Numerique03()
				, RG_SECTIONHIT_MJMNMOIS11_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois11Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS11
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois11Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois11Numerique03());
		
		/* 65 - pcNuitNmois11. *******/
		/* RG_SECTIONHIT_PCNUITNMOIS11_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitNmois11Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois11()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois11Renseigne01()
				, RG_SECTIONHIT_PCNUITNMOIS11_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois11Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS11
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois11Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois11Renseigne01());
		
		/* RG_SECTIONHIT_PCNUITNMOIS11_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitNmois11Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois11()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois11Regex02()
				, RG_SECTIONHIT_PCNUITNMOIS11_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois11Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS11
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois11Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois11Regex02());
				
		/* RG_SECTIONHIT_PCNUITNMOIS11_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitNmois11Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois11()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois11Numerique03()
				, RG_SECTIONHIT_PCNUITNMOIS11_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois11Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS11
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois11Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois11Numerique03());

		
		/* 66 - mjmNmois12. *******/
		/* RG_SECTIONHIT_MJMNMOIS12_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjmNmois12Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois12()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois12Renseigne01()
				, RG_SECTIONHIT_MJMNMOIS12_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois12Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS12
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois12Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois12Renseigne01());
		
		/* RG_SECTIONHIT_MJMNMOIS12_REGEX_02. */
		final LigneRG ligneRGSectionHitMjmNmois12Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois12()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois12Regex02()
				, RG_SECTIONHIT_MJMNMOIS12_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois12Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS12
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois12Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois12Regex02());
				
		/* RG_SECTIONHIT_MJMNMOIS12_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjmNmois12Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois12()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjmNmois12Numerique03()
				, RG_SECTIONHIT_MJMNMOIS12_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjmNmois12Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJMNMOIS12
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjmNmois12Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjmNmois12Numerique03());
		
		/* 67 - pcNuitNmois12. *******/
		/* RG_SECTIONHIT_PCNUITNMOIS12_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitNmois12Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois12()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois12Renseigne01()
				, RG_SECTIONHIT_PCNUITNMOIS12_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois12Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS12
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois12Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois12Renseigne01());
		
		/* RG_SECTIONHIT_PCNUITNMOIS12_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitNmois12Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois12()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois12Regex02()
				, RG_SECTIONHIT_PCNUITNMOIS12_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois12Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS12
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois12Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois12Regex02());
				
		/* RG_SECTIONHIT_PCNUITNMOIS12_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitNmois12Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois12()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitNmois12Numerique03()
				, RG_SECTIONHIT_PCNUITNMOIS12_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitNmois12Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITNMOIS12
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitNmois12Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitNmois12Numerique03());
		
		/* 68 - zoneLibre3. *******/
		/* RG_SECTIONHIT_ZONELIBRE3_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitZoneLibre3Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitZoneLibre3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitZoneLibre3Renseigne01()
				, RG_SECTIONHIT_ZONELIBRE3_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitZoneLibre3Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_ZONELIBRE3
				, CLASSE_CONTROLE
				, "validerRGSectionHitZoneLibre3Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitZoneLibre3Renseigne01());
		
		/* RG_SECTIONHIT_ZONELIBRE3_REGEX_02. */
		final LigneRG ligneRGSectionHitZoneLibre3Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitZoneLibre3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitZoneLibre3Regex02()
				, RG_SECTIONHIT_ZONELIBRE3_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitZoneLibre3Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_ZONELIBRE3
				, CLASSE_CONTROLE
				, "validerRGSectionHitZoneLibre3Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitZoneLibre3Regex02());
		
		/* 69 - anneeNmoins1. *******/
		/* RG_SECTIONHIT_ANNEENMOINS1_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitAnneeNmoins1Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins1Renseigne01()
				, RG_SECTIONHIT_ANNEENMOINS1_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAnneeNmoins1Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_ANNEENMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitAnneeNmoins1Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAnneeNmoins1Renseigne01());
		
		/* RG_SECTIONHIT_ANNEENMOINS1_REGEX_02. */
		final LigneRG ligneRGSectionHitAnneeNmoins1Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins1Regex02()
				, RG_SECTIONHIT_ANNEENMOINS1_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAnneeNmoins1Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_ANNEENMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitAnneeNmoins1Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAnneeNmoins1Regex02());
		
		/* 70 - mjaNmoins1. *******/
		/* RG_SECTIONHIT_MJANMOINS1_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjaNmoins1Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins1Renseigne01()
				, RG_SECTIONHIT_MJANMOINS1_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNmoins1Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJANMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNmoins1Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNmoins1Renseigne01());
		
		/* RG_SECTIONHIT_MJANMOINS1_REGEX_02. */
		final LigneRG ligneRGSectionHitMjaNmoins1Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins1Regex02()
				, RG_SECTIONHIT_MJANMOINS1_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNmoins1Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJANMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNmoins1Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNmoins1Regex02());
				
		/* RG_SECTIONHIT_MJANMOINS1_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjaNmoins1Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins1Numerique03()
				, RG_SECTIONHIT_MJANMOINS1_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNmoins1Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJANMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNmoins1Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNmoins1Numerique03());							
		
		/* 71 - typeComptageNmoins1. *******/
		/* RG_SECTIONHIT_TYPECOMPTAGENMOINS1_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitTypeComptageNmoins1Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins1Renseigne01()
				, RG_SECTIONHIT_TYPECOMPTAGENMOINS1_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageNmoins1Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGENMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageNmoins1Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageNmoins1Renseigne01());
		
		/* RG_SECTIONHIT_TYPECOMPTAGENMOINS1_REGEX_02. */
		final LigneRG ligneRGSectionHitTypeComptageNmoins1Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins1Regex02()
				, RG_SECTIONHIT_TYPECOMPTAGENMOINS1_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageNmoins1Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGENMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageNmoins1Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageNmoins1Regex02());
				
		/* RG_SECTIONHIT_TYPECOMPTAGENMOINS1_NOMENCLATURE_03. */
		final LigneRG ligneRGSectionHitTypeComptageNmoins1Nomenclature03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins1Nomenclature03()
				, RG_SECTIONHIT_TYPECOMPTAGENMOINS1_NOMENCLATURE_03
				, EnumTypesValidation.NOMENCLATURE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageNmoins1Nomenclature03()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGENMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageNmoins1Nomenclature03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageNmoins1Nomenclature03());
		
		/* 72 - modeCalculNmoins1. *******/
		/* RG_SECTIONHIT_MODECALCULNMOINS1_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitModeCalculNmoins1Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins1Renseigne01()
				, RG_SECTIONHIT_MODECALCULNMOINS1_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitModeCalculNmoins1Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MODECALCULNMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitModeCalculNmoins1Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitModeCalculNmoins1Renseigne01());
		
		/* RG_SECTIONHIT_MODECALCULNMOINS1_REGEX_02. */
		final LigneRG ligneRGSectionHitModeCalculNmoins1Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins1Regex02()
				, RG_SECTIONHIT_MODECALCULNMOINS1_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitModeCalculNmoins1Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MODECALCULNMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitModeCalculNmoins1Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitModeCalculNmoins1Regex02());
		
		/* 73 - pcPLNmoins1. *******/
		/* RG_SECTIONHIT_PCPLNMOINS1_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcPLNmoins1Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins1Renseigne01()
				, RG_SECTIONHIT_PCPLNMOINS1_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNmoins1Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLNMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNmoins1Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNmoins1Renseigne01());
		
		/* RG_SECTIONHIT_PCPLNMOINS1_REGEX_02. */
		final LigneRG ligneRGSectionHitPcPLNmoins1Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins1Regex02()
				, RG_SECTIONHIT_PCPLNMOINS1_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNmoins1Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLNMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNmoins1Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNmoins1Regex02());
				
		/* RG_SECTIONHIT_PCPLNMOINS1_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcPLNmoins1Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins1Numerique03()
				, RG_SECTIONHIT_PCPLNMOINS1_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNmoins1Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLNMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNmoins1Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNmoins1Numerique03());
		
		/* 74 - evaluationPLNmoins1. *******/
		/* RG_SECTIONHIT_EVALUATIONPLNMOINS1_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitEvaluationPLNmoins1Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins1Renseigne01()
				, RG_SECTIONHIT_EVALUATIONPLNMOINS1_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitEvaluationPLNmoins1Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_EVALUATIONPLNMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitEvaluationPLNmoins1Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitEvaluationPLNmoins1Renseigne01());
		
		/* RG_SECTIONHIT_EVALUATIONPLNMOINS1_REGEX_02. */
		final LigneRG ligneRGSectionHitEvaluationPLNmoins1Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins1Regex02()
				, RG_SECTIONHIT_EVALUATIONPLNMOINS1_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitEvaluationPLNmoins1Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_EVALUATIONPLNMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitEvaluationPLNmoins1Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitEvaluationPLNmoins1Regex02());
		
		/* 75 - pcNuitAnnuelNmoins1. *******/
		/* RG_SECTIONHIT_PCNUITANNUELNMOINS1_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNmoins1Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins1Renseigne01()
				, RG_SECTIONHIT_PCNUITANNUELNMOINS1_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNmoins1Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELNMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNmoins1Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNmoins1Renseigne01());
		
		/* RG_SECTIONHIT_PCNUITANNUELNMOINS1_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNmoins1Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins1Regex02()
				, RG_SECTIONHIT_PCNUITANNUELNMOINS1_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNmoins1Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELNMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNmoins1Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNmoins1Regex02());
				
		/* RG_SECTIONHIT_PCNUITANNUELNMOINS1_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNmoins1Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins1Numerique03()
				, RG_SECTIONHIT_PCNUITANNUELNMOINS1_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNmoins1Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELNMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNmoins1Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNmoins1Numerique03());
		
		/* 76 - indiceFiabiliteMjaNmoins1. *******/
		/* RG_SECTIONHIT_INDICEFIABLITEMJANMOINS1_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitIndiceFiabiliteMjaNmoins1Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins1Renseigne01()
				, RG_SECTIONHIT_INDICEFIABLITEMJANMOINS1_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitIndiceFiabiliteMjaNmoins1Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_INDICEFIABLITEMJANMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitIndiceFiabiliteMjaNmoins1Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNmoins1Renseigne01());
		
		/* RG_SECTIONHIT_INDICEFIABLITEMJANMOINS1_REGEX_02. */
		final LigneRG ligneRGSectionHitIndiceFiabiliteMjaNmoins1Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins1()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins1Regex02()
				, RG_SECTIONHIT_INDICEFIABLITEMJANMOINS1_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitIndiceFiabiliteMjaNmoins1Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_INDICEFIABLITEMJANMOINS1
				, CLASSE_CONTROLE
				, "validerRGSectionHitIndiceFiabiliteMjaNmoins1Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNmoins1Regex02());
		
		/* 77 - anneeNmoins2. *******/
		/* RG_SECTIONHIT_ANNEENMOINS2_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitAnneeNmoins2Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins2Renseigne01()
				, RG_SECTIONHIT_ANNEENMOINS2_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAnneeNmoins2Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_ANNEENMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitAnneeNmoins2Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAnneeNmoins2Renseigne01());
		
		/* RG_SECTIONHIT_ANNEENMOINS2_REGEX_02. */
		final LigneRG ligneRGSectionHitAnneeNmoins2Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins2Regex02()
				, RG_SECTIONHIT_ANNEENMOINS2_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAnneeNmoins2Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_ANNEENMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitAnneeNmoins2Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAnneeNmoins2Regex02());
		
		/* 78 - mjaNmoins2. *******/
		/* RG_SECTIONHIT_MJANMOINS2_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjaNmoins2Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins2Renseigne01()
				, RG_SECTIONHIT_MJANMOINS2_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNmoins2Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJANMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNmoins2Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNmoins2Renseigne01());
		
		/* RG_SECTIONHIT_MJANMOINS2_REGEX_02. */
		final LigneRG ligneRGSectionHitMjaNmoins2Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins2Regex02()
				, RG_SECTIONHIT_MJANMOINS2_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNmoins2Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJANMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNmoins2Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNmoins2Regex02());
				
		/* RG_SECTIONHIT_MJANMOINS2_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjaNmoins2Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins2Numerique03()
				, RG_SECTIONHIT_MJANMOINS2_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNmoins2Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJANMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNmoins2Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNmoins2Numerique03());							
		
		/* 79 - typeComptageNmoins2. *******/
		/* RG_SECTIONHIT_TYPECOMPTAGENMOINS2_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitTypeComptageNmoins2Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins2Renseigne01()
				, RG_SECTIONHIT_TYPECOMPTAGENMOINS2_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageNmoins2Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGENMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageNmoins2Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageNmoins2Renseigne01());
		
		/* RG_SECTIONHIT_TYPECOMPTAGENMOINS2_REGEX_02. */
		final LigneRG ligneRGSectionHitTypeComptageNmoins2Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins2Regex02()
				, RG_SECTIONHIT_TYPECOMPTAGENMOINS2_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageNmoins2Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGENMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageNmoins2Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageNmoins2Regex02());
				
		/* RG_SECTIONHIT_TYPECOMPTAGENMOINS2_NOMENCLATURE_03. */
		final LigneRG ligneRGSectionHitTypeComptageNmoins2Nomenclature03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins2Nomenclature03()
				, RG_SECTIONHIT_TYPECOMPTAGENMOINS2_NOMENCLATURE_03
				, EnumTypesValidation.NOMENCLATURE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageNmoins2Nomenclature03()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGENMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageNmoins2Nomenclature03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageNmoins2Nomenclature03());
		
		/* 80 - modeCalculNmoins2. *******/
		/* RG_SECTIONHIT_MODECALCULNMOINS2_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitModeCalculNmoins2Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins2Renseigne01()
				, RG_SECTIONHIT_MODECALCULNMOINS2_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitModeCalculNmoins2Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MODECALCULNMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitModeCalculNmoins2Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitModeCalculNmoins2Renseigne01());
		
		/* RG_SECTIONHIT_MODECALCULNMOINS2_REGEX_02. */
		final LigneRG ligneRGSectionHitModeCalculNmoins2Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins2Regex02()
				, RG_SECTIONHIT_MODECALCULNMOINS2_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitModeCalculNmoins2Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MODECALCULNMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitModeCalculNmoins2Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitModeCalculNmoins2Regex02());
		
		/* 81 - pcPLNmoins2. *******/
		/* RG_SECTIONHIT_PCPLNMOINS2_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcPLNmoins2Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins2Renseigne01()
				, RG_SECTIONHIT_PCPLNMOINS2_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNmoins2Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLNMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNmoins2Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNmoins2Renseigne01());
		
		/* RG_SECTIONHIT_PCPLNMOINS2_REGEX_02. */
		final LigneRG ligneRGSectionHitPcPLNmoins2Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins2Regex02()
				, RG_SECTIONHIT_PCPLNMOINS2_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNmoins2Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLNMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNmoins2Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNmoins2Regex02());
				
		/* RG_SECTIONHIT_PCPLNMOINS2_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcPLNmoins2Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins2Numerique03()
				, RG_SECTIONHIT_PCPLNMOINS2_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNmoins2Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLNMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNmoins2Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNmoins2Numerique03());
		
		/* 82 - evaluationPLNmoins2. *******/
		/* RG_SECTIONHIT_EVALUATIONPLNMOINS2_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitEvaluationPLNmoins2Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins2Renseigne01()
				, RG_SECTIONHIT_EVALUATIONPLNMOINS2_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitEvaluationPLNmoins2Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_EVALUATIONPLNMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitEvaluationPLNmoins2Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitEvaluationPLNmoins2Renseigne01());
		
		/* RG_SECTIONHIT_EVALUATIONPLNMOINS2_REGEX_02. */
		final LigneRG ligneRGSectionHitEvaluationPLNmoins2Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins2Regex02()
				, RG_SECTIONHIT_EVALUATIONPLNMOINS2_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitEvaluationPLNmoins2Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_EVALUATIONPLNMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitEvaluationPLNmoins2Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitEvaluationPLNmoins2Regex02());
		
		/* 83 - pcNuitAnnuelNmoins2. *******/
		/* RG_SECTIONHIT_PCNUITANNUELNMOINS2_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNmoins2Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins2Renseigne01()
				, RG_SECTIONHIT_PCNUITANNUELNMOINS2_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNmoins2Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELNMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNmoins2Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNmoins2Renseigne01());
		
		/* RG_SECTIONHIT_PCNUITANNUELNMOINS2_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNmoins2Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins2Regex02()
				, RG_SECTIONHIT_PCNUITANNUELNMOINS2_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNmoins2Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELNMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNmoins2Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNmoins2Regex02());
				
		/* RG_SECTIONHIT_PCNUITANNUELNMOINS2_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNmoins2Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins2Numerique03()
				, RG_SECTIONHIT_PCNUITANNUELNMOINS2_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNmoins2Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELNMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNmoins2Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNmoins2Numerique03());
		
		/* 84 - indiceFiabiliteMjaNmoins2. *******/
		/* RG_SECTIONHIT_INDICEFIABLITEMJANMOINS2_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitIndiceFiabiliteMjaNmoins2Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins2Renseigne01()
				, RG_SECTIONHIT_INDICEFIABLITEMJANMOINS2_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitIndiceFiabiliteMjaNmoins2Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_INDICEFIABLITEMJANMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitIndiceFiabiliteMjaNmoins2Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNmoins2Renseigne01());
		
		/* RG_SECTIONHIT_INDICEFIABLITEMJANMOINS2_REGEX_02. */
		final LigneRG ligneRGSectionHitIndiceFiabiliteMjaNmoins2Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins2()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins2Regex02()
				, RG_SECTIONHIT_INDICEFIABLITEMJANMOINS2_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitIndiceFiabiliteMjaNmoins2Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_INDICEFIABLITEMJANMOINS2
				, CLASSE_CONTROLE
				, "validerRGSectionHitIndiceFiabiliteMjaNmoins2Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNmoins2Regex02());

		
		/* 85 - anneeNmoins3. *******/
		/* RG_SECTIONHIT_ANNEENMOINS3_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitAnneeNmoins3Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins3Renseigne01()
				, RG_SECTIONHIT_ANNEENMOINS3_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAnneeNmoins3Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_ANNEENMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitAnneeNmoins3Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAnneeNmoins3Renseigne01());
		
		/* RG_SECTIONHIT_ANNEENMOINS3_REGEX_02. */
		final LigneRG ligneRGSectionHitAnneeNmoins3Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins3Regex02()
				, RG_SECTIONHIT_ANNEENMOINS3_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAnneeNmoins3Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_ANNEENMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitAnneeNmoins3Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAnneeNmoins3Regex02());
		
		/* 86 - mjaNmoins3. *******/
		/* RG_SECTIONHIT_MJANMOINS3_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjaNmoins3Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins3Renseigne01()
				, RG_SECTIONHIT_MJANMOINS3_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNmoins3Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJANMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNmoins3Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNmoins3Renseigne01());
		
		/* RG_SECTIONHIT_MJANMOINS3_REGEX_02. */
		final LigneRG ligneRGSectionHitMjaNmoins3Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins3Regex02()
				, RG_SECTIONHIT_MJANMOINS3_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNmoins3Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJANMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNmoins3Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNmoins3Regex02());
				
		/* RG_SECTIONHIT_MJANMOINS3_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjaNmoins3Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins3Numerique03()
				, RG_SECTIONHIT_MJANMOINS3_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNmoins3Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJANMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNmoins3Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNmoins3Numerique03());							
		
		/* 87 - typeComptageNmoins3. *******/
		/* RG_SECTIONHIT_TYPECOMPTAGENMOINS3_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitTypeComptageNmoins3Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins3Renseigne01()
				, RG_SECTIONHIT_TYPECOMPTAGENMOINS3_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageNmoins3Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGENMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageNmoins3Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageNmoins3Renseigne01());
		
		/* RG_SECTIONHIT_TYPECOMPTAGENMOINS3_REGEX_02. */
		final LigneRG ligneRGSectionHitTypeComptageNmoins3Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins3Regex02()
				, RG_SECTIONHIT_TYPECOMPTAGENMOINS3_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageNmoins3Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGENMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageNmoins3Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageNmoins3Regex02());
				
		/* RG_SECTIONHIT_TYPECOMPTAGENMOINS3_NOMENCLATURE_03. */
		final LigneRG ligneRGSectionHitTypeComptageNmoins3Nomenclature03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins3Nomenclature03()
				, RG_SECTIONHIT_TYPECOMPTAGENMOINS3_NOMENCLATURE_03
				, EnumTypesValidation.NOMENCLATURE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageNmoins3Nomenclature03()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGENMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageNmoins3Nomenclature03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageNmoins3Nomenclature03());
		
		/* 88 - modeCalculNmoins3. *******/
		/* RG_SECTIONHIT_MODECALCULNMOINS3_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitModeCalculNmoins3Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins3Renseigne01()
				, RG_SECTIONHIT_MODECALCULNMOINS3_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitModeCalculNmoins3Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MODECALCULNMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitModeCalculNmoins3Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitModeCalculNmoins3Renseigne01());
		
		/* RG_SECTIONHIT_MODECALCULNMOINS3_REGEX_02. */
		final LigneRG ligneRGSectionHitModeCalculNmoins3Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins3Regex02()
				, RG_SECTIONHIT_MODECALCULNMOINS3_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitModeCalculNmoins3Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MODECALCULNMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitModeCalculNmoins3Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitModeCalculNmoins3Regex02());
		
		/* 89 - pcPLNmoins3. *******/
		/* RG_SECTIONHIT_PCPLNMOINS3_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcPLNmoins3Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins3Renseigne01()
				, RG_SECTIONHIT_PCPLNMOINS3_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNmoins3Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLNMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNmoins3Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNmoins3Renseigne01());
		
		/* RG_SECTIONHIT_PCPLNMOINS3_REGEX_02. */
		final LigneRG ligneRGSectionHitPcPLNmoins3Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins3Regex02()
				, RG_SECTIONHIT_PCPLNMOINS3_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNmoins3Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLNMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNmoins3Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNmoins3Regex02());
				
		/* RG_SECTIONHIT_PCPLNMOINS3_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcPLNmoins3Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins3Numerique03()
				, RG_SECTIONHIT_PCPLNMOINS3_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNmoins3Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLNMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNmoins3Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNmoins3Numerique03());
		
		/* 90 - evaluationPLNmoins3. *******/
		/* RG_SECTIONHIT_EVALUATIONPLNMOINS3_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitEvaluationPLNmoins3Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins3Renseigne01()
				, RG_SECTIONHIT_EVALUATIONPLNMOINS3_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitEvaluationPLNmoins3Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_EVALUATIONPLNMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitEvaluationPLNmoins3Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitEvaluationPLNmoins3Renseigne01());
		
		/* RG_SECTIONHIT_EVALUATIONPLNMOINS3_REGEX_02. */
		final LigneRG ligneRGSectionHitEvaluationPLNmoins3Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins3Regex02()
				, RG_SECTIONHIT_EVALUATIONPLNMOINS3_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitEvaluationPLNmoins3Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_EVALUATIONPLNMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitEvaluationPLNmoins3Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitEvaluationPLNmoins3Regex02());
		
		/* 91 - pcNuitAnnuelNmoins3. *******/
		/* RG_SECTIONHIT_PCNUITANNUELNMOINS3_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNmoins3Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins3Renseigne01()
				, RG_SECTIONHIT_PCNUITANNUELNMOINS3_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNmoins3Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELNMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNmoins3Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNmoins3Renseigne01());
		
		/* RG_SECTIONHIT_PCNUITANNUELNMOINS3_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNmoins3Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins3Regex02()
				, RG_SECTIONHIT_PCNUITANNUELNMOINS3_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNmoins3Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELNMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNmoins3Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNmoins3Regex02());
				
		/* RG_SECTIONHIT_PCNUITANNUELNMOINS3_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNmoins3Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins3Numerique03()
				, RG_SECTIONHIT_PCNUITANNUELNMOINS3_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNmoins3Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELNMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNmoins3Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNmoins3Numerique03());
		
		/* 92 - indiceFiabiliteMjaNmoins3. *******/
		/* RG_SECTIONHIT_INDICEFIABLITEMJANMOINS3_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitIndiceFiabiliteMjaNmoins3Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins3Renseigne01()
				, RG_SECTIONHIT_INDICEFIABLITEMJANMOINS3_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitIndiceFiabiliteMjaNmoins3Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_INDICEFIABLITEMJANMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitIndiceFiabiliteMjaNmoins3Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNmoins3Renseigne01());
		
		/* RG_SECTIONHIT_INDICEFIABLITEMJANMOINS3_REGEX_02. */
		final LigneRG ligneRGSectionHitIndiceFiabiliteMjaNmoins3Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins3()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins3Regex02()
				, RG_SECTIONHIT_INDICEFIABLITEMJANMOINS3_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitIndiceFiabiliteMjaNmoins3Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_INDICEFIABLITEMJANMOINS3
				, CLASSE_CONTROLE
				, "validerRGSectionHitIndiceFiabiliteMjaNmoins3Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNmoins3Regex02());

		
		/* 93 - anneeNmoins4. *******/
		/* RG_SECTIONHIT_ANNEENMOINS4_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitAnneeNmoins4Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins4Renseigne01()
				, RG_SECTIONHIT_ANNEENMOINS4_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAnneeNmoins4Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_ANNEENMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitAnneeNmoins4Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAnneeNmoins4Renseigne01());
		
		/* RG_SECTIONHIT_ANNEENMOINS4_REGEX_02. */
		final LigneRG ligneRGSectionHitAnneeNmoins4Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins4Regex02()
				, RG_SECTIONHIT_ANNEENMOINS4_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAnneeNmoins4Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_ANNEENMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitAnneeNmoins4Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAnneeNmoins4Regex02());
		
		/* 94 - mjaNmoins4. *******/
		/* RG_SECTIONHIT_MJANMOINS4_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjaNmoins4Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins4Renseigne01()
				, RG_SECTIONHIT_MJANMOINS4_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNmoins4Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJANMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNmoins4Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNmoins4Renseigne01());
		
		/* RG_SECTIONHIT_MJANMOINS4_REGEX_02. */
		final LigneRG ligneRGSectionHitMjaNmoins4Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins4Regex02()
				, RG_SECTIONHIT_MJANMOINS4_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNmoins4Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJANMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNmoins4Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNmoins4Regex02());
				
		/* RG_SECTIONHIT_MJANMOINS4_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjaNmoins4Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins4Numerique03()
				, RG_SECTIONHIT_MJANMOINS4_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNmoins4Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJANMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNmoins4Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNmoins4Numerique03());							
		
		/* 95 - typeComptageNmoins4. *******/
		/* RG_SECTIONHIT_TYPECOMPTAGENMOINS4_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitTypeComptageNmoins4Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins4Renseigne01()
				, RG_SECTIONHIT_TYPECOMPTAGENMOINS4_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageNmoins4Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGENMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageNmoins4Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageNmoins4Renseigne01());
		
		/* RG_SECTIONHIT_TYPECOMPTAGENMOINS4_REGEX_02. */
		final LigneRG ligneRGSectionHitTypeComptageNmoins4Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins4Regex02()
				, RG_SECTIONHIT_TYPECOMPTAGENMOINS4_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageNmoins4Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGENMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageNmoins4Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageNmoins4Regex02());
				
		/* RG_SECTIONHIT_TYPECOMPTAGENMOINS4_NOMENCLATURE_03. */
		final LigneRG ligneRGSectionHitTypeComptageNmoins4Nomenclature03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins4Nomenclature03()
				, RG_SECTIONHIT_TYPECOMPTAGENMOINS4_NOMENCLATURE_03
				, EnumTypesValidation.NOMENCLATURE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageNmoins4Nomenclature03()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGENMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageNmoins4Nomenclature03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageNmoins4Nomenclature03());
		
		/* 96 - modeCalculNmoins4. *******/
		/* RG_SECTIONHIT_MODECALCULNMOINS4_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitModeCalculNmoins4Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins4Renseigne01()
				, RG_SECTIONHIT_MODECALCULNMOINS4_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitModeCalculNmoins4Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MODECALCULNMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitModeCalculNmoins4Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitModeCalculNmoins4Renseigne01());
		
		/* RG_SECTIONHIT_MODECALCULNMOINS4_REGEX_02. */
		final LigneRG ligneRGSectionHitModeCalculNmoins4Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins4Regex02()
				, RG_SECTIONHIT_MODECALCULNMOINS4_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitModeCalculNmoins4Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MODECALCULNMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitModeCalculNmoins4Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitModeCalculNmoins4Regex02());
		
		/* 97 - pcPLNmoins4. *******/
		/* RG_SECTIONHIT_PCPLNMOINS4_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcPLNmoins4Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins4Renseigne01()
				, RG_SECTIONHIT_PCPLNMOINS4_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNmoins4Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLNMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNmoins4Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNmoins4Renseigne01());
		
		/* RG_SECTIONHIT_PCPLNMOINS4_REGEX_02. */
		final LigneRG ligneRGSectionHitPcPLNmoins4Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins4Regex02()
				, RG_SECTIONHIT_PCPLNMOINS4_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNmoins4Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLNMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNmoins4Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNmoins4Regex02());
				
		/* RG_SECTIONHIT_PCPLNMOINS4_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcPLNmoins4Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins4Numerique03()
				, RG_SECTIONHIT_PCPLNMOINS4_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNmoins4Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLNMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNmoins4Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNmoins4Numerique03());
		
		/* 98 - evaluationPLNmoins4. *******/
		/* RG_SECTIONHIT_EVALUATIONPLNMOINS4_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitEvaluationPLNmoins4Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins4Renseigne01()
				, RG_SECTIONHIT_EVALUATIONPLNMOINS4_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitEvaluationPLNmoins4Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_EVALUATIONPLNMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitEvaluationPLNmoins4Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitEvaluationPLNmoins4Renseigne01());
		
		/* RG_SECTIONHIT_EVALUATIONPLNMOINS4_REGEX_02. */
		final LigneRG ligneRGSectionHitEvaluationPLNmoins4Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins4Regex02()
				, RG_SECTIONHIT_EVALUATIONPLNMOINS4_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitEvaluationPLNmoins4Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_EVALUATIONPLNMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitEvaluationPLNmoins4Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitEvaluationPLNmoins4Regex02());
		
		/* 99 - pcNuitAnnuelNmoins4. *******/
		/* RG_SECTIONHIT_PCNUITANNUELNMOINS4_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNmoins4Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins4Renseigne01()
				, RG_SECTIONHIT_PCNUITANNUELNMOINS4_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNmoins4Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELNMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNmoins4Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNmoins4Renseigne01());
		
		/* RG_SECTIONHIT_PCNUITANNUELNMOINS4_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNmoins4Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins4Regex02()
				, RG_SECTIONHIT_PCNUITANNUELNMOINS4_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNmoins4Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELNMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNmoins4Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNmoins4Regex02());
				
		/* RG_SECTIONHIT_PCNUITANNUELNMOINS4_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNmoins4Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins4Numerique03()
				, RG_SECTIONHIT_PCNUITANNUELNMOINS4_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNmoins4Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELNMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNmoins4Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNmoins4Numerique03());
		
		/* 100 - indiceFiabiliteMjaNmoins4. *******/
		/* RG_SECTIONHIT_INDICEFIABLITEMJANMOINS4_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitIndiceFiabiliteMjaNmoins4Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins4Renseigne01()
				, RG_SECTIONHIT_INDICEFIABLITEMJANMOINS4_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitIndiceFiabiliteMjaNmoins4Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_INDICEFIABLITEMJANMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitIndiceFiabiliteMjaNmoins4Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNmoins4Renseigne01());
		
		/* RG_SECTIONHIT_INDICEFIABLITEMJANMOINS4_REGEX_02. */
		final LigneRG ligneRGSectionHitIndiceFiabiliteMjaNmoins4Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins4()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins4Regex02()
				, RG_SECTIONHIT_INDICEFIABLITEMJANMOINS4_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitIndiceFiabiliteMjaNmoins4Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_INDICEFIABLITEMJANMOINS4
				, CLASSE_CONTROLE
				, "validerRGSectionHitIndiceFiabiliteMjaNmoins4Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNmoins4Regex02());

		
		/* 101 - anneeNmoins5. *******/
		/* RG_SECTIONHIT_ANNEENMOINS5_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitAnneeNmoins5Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins5Renseigne01()
				, RG_SECTIONHIT_ANNEENMOINS5_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAnneeNmoins5Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_ANNEENMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitAnneeNmoins5Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAnneeNmoins5Renseigne01());
		
		/* RG_SECTIONHIT_ANNEENMOINS5_REGEX_02. */
		final LigneRG ligneRGSectionHitAnneeNmoins5Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitAnneeNmoins5Regex02()
				, RG_SECTIONHIT_ANNEENMOINS5_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitAnneeNmoins5Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_ANNEENMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitAnneeNmoins5Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitAnneeNmoins5Regex02());
		
		/* 102 - mjaNmoins5. *******/
		/* RG_SECTIONHIT_MJANMOINS5_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitMjaNmoins5Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins5Renseigne01()
				, RG_SECTIONHIT_MJANMOINS5_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNmoins5Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJANMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNmoins5Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNmoins5Renseigne01());
		
		/* RG_SECTIONHIT_MJANMOINS5_REGEX_02. */
		final LigneRG ligneRGSectionHitMjaNmoins5Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins5Regex02()
				, RG_SECTIONHIT_MJANMOINS5_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNmoins5Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJANMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNmoins5Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNmoins5Regex02());
				
		/* RG_SECTIONHIT_MJANMOINS5_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitMjaNmoins5Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitMjaNmoins5Numerique03()
				, RG_SECTIONHIT_MJANMOINS5_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitMjaNmoins5Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_MJANMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitMjaNmoins5Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitMjaNmoins5Numerique03());							
		
		/* 103 - typeComptageNmoins5. *******/
		/* RG_SECTIONHIT_TYPECOMPTAGENMOINS5_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitTypeComptageNmoins5Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins5Renseigne01()
				, RG_SECTIONHIT_TYPECOMPTAGENMOINS5_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageNmoins5Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGENMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageNmoins5Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageNmoins5Renseigne01());
		
		/* RG_SECTIONHIT_TYPECOMPTAGENMOINS5_REGEX_02. */
		final LigneRG ligneRGSectionHitTypeComptageNmoins5Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins5Regex02()
				, RG_SECTIONHIT_TYPECOMPTAGENMOINS5_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageNmoins5Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGENMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageNmoins5Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageNmoins5Regex02());
				
		/* RG_SECTIONHIT_TYPECOMPTAGENMOINS5_NOMENCLATURE_03. */
		final LigneRG ligneRGSectionHitTypeComptageNmoins5Nomenclature03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitTypeComptageNmoins5Nomenclature03()
				, RG_SECTIONHIT_TYPECOMPTAGENMOINS5_NOMENCLATURE_03
				, EnumTypesValidation.NOMENCLATURE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitTypeComptageNmoins5Nomenclature03()
				, NOM_OBJETMETIER
				, ATTRIBUT_TYPECOMPTAGENMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitTypeComptageNmoins5Nomenclature03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitTypeComptageNmoins5Nomenclature03());
		
		/* 104 - modeCalculNmoins5. *******/
		/* RG_SECTIONHIT_MODECALCULNMOINS5_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitModeCalculNmoins5Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins5Renseigne01()
				, RG_SECTIONHIT_MODECALCULNMOINS5_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitModeCalculNmoins5Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_MODECALCULNMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitModeCalculNmoins5Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitModeCalculNmoins5Renseigne01());
		
		/* RG_SECTIONHIT_MODECALCULNMOINS5_REGEX_02. */
		final LigneRG ligneRGSectionHitModeCalculNmoins5Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitModeCalculNmoins5Regex02()
				, RG_SECTIONHIT_MODECALCULNMOINS5_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitModeCalculNmoins5Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_MODECALCULNMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitModeCalculNmoins5Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitModeCalculNmoins5Regex02());
		
		/* 105 - pcPLNmoins5. *******/
		/* RG_SECTIONHIT_PCPLNMOINS5_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcPLNmoins5Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins5Renseigne01()
				, RG_SECTIONHIT_PCPLNMOINS5_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNmoins5Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLNMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNmoins5Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNmoins5Renseigne01());
		
		/* RG_SECTIONHIT_PCPLNMOINS5_REGEX_02. */
		final LigneRG ligneRGSectionHitPcPLNmoins5Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins5Regex02()
				, RG_SECTIONHIT_PCPLNMOINS5_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNmoins5Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLNMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNmoins5Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNmoins5Regex02());
				
		/* RG_SECTIONHIT_PCPLNMOINS5_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcPLNmoins5Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcPLNmoins5Numerique03()
				, RG_SECTIONHIT_PCPLNMOINS5_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcPLNmoins5Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCPLNMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcPLNmoins5Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcPLNmoins5Numerique03());
		
		/* 106 - evaluationPLNmoins5. *******/
		/* RG_SECTIONHIT_EVALUATIONPLNMOINS5_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitEvaluationPLNmoins5Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins5Renseigne01()
				, RG_SECTIONHIT_EVALUATIONPLNMOINS5_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitEvaluationPLNmoins5Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_EVALUATIONPLNMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitEvaluationPLNmoins5Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitEvaluationPLNmoins5Renseigne01());
		
		/* RG_SECTIONHIT_EVALUATIONPLNMOINS5_REGEX_02. */
		final LigneRG ligneRGSectionHitEvaluationPLNmoins5Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitEvaluationPLNmoins5Regex02()
				, RG_SECTIONHIT_EVALUATIONPLNMOINS5_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitEvaluationPLNmoins5Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_EVALUATIONPLNMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitEvaluationPLNmoins5Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitEvaluationPLNmoins5Regex02());
		
		/* 107 - pcNuitAnnuelNmoins5. *******/
		/* RG_SECTIONHIT_PCNUITANNUELNMOINS5_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNmoins5Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins5Renseigne01()
				, RG_SECTIONHIT_PCNUITANNUELNMOINS5_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNmoins5Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELNMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNmoins5Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNmoins5Renseigne01());
		
		/* RG_SECTIONHIT_PCNUITANNUELNMOINS5_REGEX_02. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNmoins5Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins5Regex02()
				, RG_SECTIONHIT_PCNUITANNUELNMOINS5_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNmoins5Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELNMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNmoins5Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNmoins5Regex02());
				
		/* RG_SECTIONHIT_PCNUITANNUELNMOINS5_NUMERIQUE_03. */
		final LigneRG ligneRGSectionHitPcNuitAnnuelNmoins5Numerique03 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitPcNuitAnnuelNmoins5Numerique03()
				, RG_SECTIONHIT_PCNUITANNUELNMOINS5_NUMERIQUE_03
				, EnumTypesValidation.NUMERIQUE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitPcNuitAnnuelNmoins5Numerique03()
				, NOM_OBJETMETIER
				, ATTRIBUT_PCNUITANNUELNMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitPcNuitAnnuelNmoins5Numerique03()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitPcNuitAnnuelNmoins5Numerique03());
		
		/* 108 - indiceFiabiliteMjaNmoins5. *******/
		/* RG_SECTIONHIT_INDICEFIABLITEMJANMOINS5_RENSEIGNE_01. */
		final LigneRG ligneRGSectionHitIndiceFiabiliteMjaNmoins5Renseigne01 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins5Renseigne01()
				, RG_SECTIONHIT_INDICEFIABLITEMJANMOINS5_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitIndiceFiabiliteMjaNmoins5Renseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_INDICEFIABLITEMJANMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitIndiceFiabiliteMjaNmoins5Renseigne01()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNmoins5Renseigne01());
		
		/* RG_SECTIONHIT_INDICEFIABLITEMJANMOINS5_REGEX_02. */
		final LigneRG ligneRGSectionHitIndiceFiabiliteMjaNmoins5Regex02 
		= new LigneRG(SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins5()
				, SectionHitGestionnairePreferencesRG.getValiderRGSectionHitIndiceFiabiliteMjaNmoins5Regex02()
				, RG_SECTIONHIT_INDICEFIABLITEMJANMOINS5_REGEX_02
				, EnumTypesValidation.MOTIF.getNumero()
				, SectionHitGestionnairePreferencesControles.getMessageSectionHitIndiceFiabiliteMjaNmoins5Regex02()
				, NOM_OBJETMETIER
				, ATTRIBUT_INDICEFIABLITEMJANMOINS5
				, CLASSE_CONTROLE
				, "validerRGSectionHitIndiceFiabiliteMjaNmoins5Regex02()"
				, getPathAbsoluPropertiesRGString()
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNmoins5Regex02());

		
		
		// *********************************************
		// REMPLISSAGE DE LA MAP. **********************
		// *********************************************
		
		/* 1 - numDepartement. ****************/
		this.mapRG.put(
				RG_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01
					, ligneRGSectionHitNumDepartementRenseigne01);
		this.mapRG.put(
				RG_SECTIONHIT_NUMDEPARTEMENT_REGEX_02
					, ligneRGSectionHitNumDepartementRegex02);
		
		/* 2 - numSection. ********************/
		this.mapRG.put(
				RG_SECTIONHIT_NUMSECTION_RENSEIGNE_01
					, ligneRGSectionHitNumSectionRenseigne01);
		this.mapRG.put(
				RG_SECTIONHIT_NUMSECTION_REGEX_02
					, ligneRGSectionHitNumSectionRegex02);
		
		/* 3 - sens. ***************************/
		this.mapRG.put(
				RG_SECTIONHIT_SENS_RENSEIGNE_01
					, ligneRGSectionHitSensRenseigne01);
		this.mapRG.put(
				RG_SECTIONHIT_SENS_REGEX_02
					, ligneRGSectionHitSensRegex02);
		this.mapRG.put(
				RG_SECTIONHIT_SENS_NOMENCLATURE_03
					, ligneRGSectionHitSensNomenclature03);
		
		/* 4 - nature. ***************************/
		this.mapRG.put(
				RG_SECTIONHIT_NATURE_RENSEIGNE_01
					, ligneRGSectionHitNatureRenseigne01);
		this.mapRG.put(
				RG_SECTIONHIT_NATURE_REGEX_02
					, ligneRGSectionHitNatureRegex02);
		this.mapRG.put(
				RG_SECTIONHIT_NATURE_NOMENCLATURE_03
					, ligneRGSectionHitNatureNomenclature03);
		
		/* 5 - classe. ********************/
		this.mapRG.put(
				RG_SECTIONHIT_CLASSE_RENSEIGNE_01
					, ligneRGSectionHitClasseRenseigne01);
		this.mapRG.put(
				RG_SECTIONHIT_CLASSE_REGEX_02
					, ligneRGSectionHitClasseRegex02);
				
		/* 6 - anneeTraitement. *******/
		this.mapRG.put(
				RG_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01
					, ligneRGSectionHitAnneeTraitementRenseigne01);
		this.mapRG.put(
				RG_SECTIONHIT_ANNEETRAITEMENT_REGEX_02
					, ligneRGSectionHitAnneeTraitementRegex02);
				
		/* 7 - zoneLibre1. *******/
		this.mapRG.put(
				RG_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01
					, ligneRGSectionHitZoneLibre1Renseigne01);
		this.mapRG.put(
				RG_SECTIONHIT_ZONELIBRE1_REGEX_02
					, ligneRGSectionHitZoneLibre1Regex02);
					
		/* 8 - numRoute. *******/
		this.mapRG.put(
				RG_SECTIONHIT_NUMROUTE_RENSEIGNE_01
					, ligneRGSectionHitNumRouteRenseigne01);
		this.mapRG.put(
				RG_SECTIONHIT_NUMROUTE_REGEX_02
					, ligneRGSectionHitNumRouteRegex02);
		
		/* 9 - indiceNumRoute. *******/
		this.mapRG.put(
				RG_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01
					, ligneRGSectionHitIndiceNumRouteRenseigne01);
		this.mapRG.put(
				RG_SECTIONHIT_INDICENUMROUTE_REGEX_02
					, ligneRGSectionHitIndiceNumRouteRegex02);
				
		/* 10 - indiceLettreRoute. *******/
		this.mapRG.put(
				RG_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01
					, ligneRGSectionHitIndiceLettreRouteRenseigne01);
		this.mapRG.put(
				RG_SECTIONHIT_INDICELETTREROUTE_REGEX_02
					, ligneRGSectionHitIndiceLettreRouteRegex02);
				
		/* 11 - categorieAdminRoute. *******/
		this.mapRG.put(
		RG_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01
			, ligneRGSectionHitCategorieAdminRouteRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02
			, ligneRGSectionHitCategorieAdminRouteRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03
			, ligneRGSectionHitCategorieAdminRouteNomenclature03);				
				
		/* 12 - typeComptage. *******/
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01
			, ligneRGSectionHitTypeComptageRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGE_REGEX_02
			, ligneRGSectionHitTypeComptageRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03
			, ligneRGSectionHitTypeComptageNomenclature03);				
				
		/* 13 - classementRoute. *******/
		this.mapRG.put(
		RG_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01
			, ligneRGSectionHitClassementRouteRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_CLASSEMENTROUTE_REGEX_02
			, ligneRGSectionHitClassementRouteRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03
			, ligneRGSectionHitClassementRouteNomenclature03);
				
		/* 14 - classeLargeurChausseeU. *******/
		this.mapRG.put(
		RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01
			, ligneRGSectionHitClasseLargeurChausseeURenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02
			, ligneRGSectionHitClasseLargeurChausseeURegex02);
		this.mapRG.put(
		RG_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03
			, ligneRGSectionHitClasseLargeurChausseeUNomenclature03);				
				
		/* 15 - classeLargeurChausseesS. *******/
		this.mapRG.put(
		RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01
			, ligneRGSectionHitClasseLargeurChausseesSRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02
			, ligneRGSectionHitClasseLargeurChausseesSRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03
			, ligneRGSectionHitClasseLargeurChausseesSNomenclature03);				
				
		/* 16 - typeReseau. *******/
		this.mapRG.put(
		RG_SECTIONHIT_TYPERESEAU_RENSEIGNE_01
			, ligneRGSectionHitTypeReseauRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_TYPERESEAU_REGEX_02
			, ligneRGSectionHitTypeReseauRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03
			, ligneRGSectionHitTypeReseauNomenclature03);				
				
		/* 17 - pRoupK. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PROUPK_RENSEIGNE_01
			, ligneRGSectionHitPRoupKRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PROUPK_REGEX_02
			, ligneRGSectionHitPRoupKRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_PROUPK_NOMENCLATURE_03
			, ligneRGSectionHitPRoupKNomenclature03);				
				
		/* 18 - lieuDitOrigine. *******/
		this.mapRG.put(
		RG_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01
			, ligneRGSectionHitLieuDitOrigineRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_LIEUDITORIGINE_REGEX_02
			, ligneRGSectionHitLieuDitOrigineRegex02);
				
		/* 19 - prOrigine. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PRORIGINE_RENSEIGNE_01
			, ligneRGSectionHitPrOrigineRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PRORIGINE_REGEX_02
			, ligneRGSectionHitPrOrigineRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_PRORIGINE_NUMERIQUE_03
			, ligneRGSectionHitPrOrigineNumerique03);				
				
		/* 20 - absOrigine. *******/
		this.mapRG.put(
		RG_SECTIONHIT_ABSORIGINE_RENSEIGNE_01
			, ligneRGSectionHitAbsOrigineRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_ABSORIGINE_REGEX_02
			, ligneRGSectionHitAbsOrigineRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_ABSORIGINE_NUMERIQUE_03
			, ligneRGSectionHitAbsOrigineNumerique03);				
				
		/* 21 - lieuDitExtremite. *******/
		this.mapRG.put(
		RG_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01
			, ligneRGSectionHitLieuDitExtremiteRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02
			, ligneRGSectionHitLieuDitExtremiteRegex02);
				
		/* 22 - prExtremite. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PREXTREMITE_RENSEIGNE_01
			, ligneRGSectionHitPrExtremiteRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PREXTREMITE_REGEX_02
			, ligneRGSectionHitPrExtremiteRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_PREXTREMITE_NUMERIQUE_03
			, ligneRGSectionHitPrExtremiteNumerique03);				
				
		/* 23 - absExtremite. *******/
		this.mapRG.put(
		RG_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01
			, ligneRGSectionHitAbsExtremiteRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_ABSEXTREMITE_REGEX_02
			, ligneRGSectionHitAbsExtremiteRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03
			, ligneRGSectionHitAbsExtremiteNumerique03);				
				
		/* 24 - lieuDitComptage. *******/
		this.mapRG.put(
		RG_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01
			, ligneRGSectionHitLieuDitComptageRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02
			, ligneRGSectionHitLieuDitComptageRegex02);
				
		/* 25 - prComptage. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01
			, ligneRGSectionHitPrComptageRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PRCOMPTAGE_REGEX_02
			, ligneRGSectionHitPrComptageRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03
			, ligneRGSectionHitPrComptageNumerique03);				
				
		/* 26 - absComptage. *******/
		this.mapRG.put(
		RG_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01
			, ligneRGSectionHitAbsComptageRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_ABSCOMPTAGE_REGEX_02
			, ligneRGSectionHitAbsComptageRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03
			, ligneRGSectionHitAbsComptageNumerique03);				
				
		/* 27 - longueurSection. *******/
		this.mapRG.put(
		RG_SECTIONHIT_LONGUEURSECTION_RENSEIGNE_01
			, ligneRGSectionHitLongueurSectionRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_LONGUEURSECTION_REGEX_02
			, ligneRGSectionHitLongueurSectionRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_LONGUEURSECTION_NUMERIQUE_03
			, ligneRGSectionHitLongueurSectionNumerique03);				
				
		/* 28 - longueurRaseCampagne. *******/
		this.mapRG.put(
		RG_SECTIONHIT_LONGUEURRASECAMPAGNE_RENSEIGNE_01
			, ligneRGSectionHitLongueurRaseCampagneRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_LONGUEURRASECAMPAGNE_REGEX_02
			, ligneRGSectionHitLongueurRaseCampagneRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_LONGUEURRASECAMPAGNE_NUMERIQUE_03
			, ligneRGSectionHitLongueurRaseCampagneNumerique03);				
				
		/* 29 - numDepartementRattachement. *******/
		this.mapRG.put(
		RG_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_RENSEIGNE_01
			, ligneRGSectionHitNumDepartementRattachementRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_REGEX_02
			, ligneRGSectionHitNumDepartementRattachementRegex02);
				
		/* 30 - numSectionRattachement. *******/
		this.mapRG.put(
		RG_SECTIONHIT_NUMSECTIONRATTACHEMENT_RENSEIGNE_01
			, ligneRGSectionHitNumSectionRattachementRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_NUMSECTIONRATTACHEMENT_REGEX_02
			, ligneRGSectionHitNumSectionRattachementRegex02);
				
		/* 31 - sensRattachement. *******/
		this.mapRG.put(
		RG_SECTIONHIT_SENSRATTACHEMENT_RENSEIGNE_01
			, ligneRGSectionHitSensRattachementRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_SENSRATTACHEMENT_REGEX_02
			, ligneRGSectionHitSensRattachementRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_SENSRATTACHEMENT_NOMENCLATURE_03
			, ligneRGSectionHitSensRattachementNomenclature03);				
				
		/* 32 - numDepartementLimitrophe. *******/
		this.mapRG.put(
		RG_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_RENSEIGNE_01
			, ligneRGSectionHitNumDepartementLimitropheRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_REGEX_02
			, ligneRGSectionHitNumDepartementLimitropheRegex02);
				
		/* 33 - numSectionLimitrophe. *******/
		this.mapRG.put(
		RG_SECTIONHIT_NUMSECTIONLIMITROPHE_RENSEIGNE_01
			, ligneRGSectionHitNumSectionLimitropheRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_NUMSECTIONLIMITROPHE_REGEX_02
			, ligneRGSectionHitNumSectionLimitropheRegex02);
				
		/* 34 - sensLimitrophe. *******/
		this.mapRG.put(
		RG_SECTIONHIT_SENSLIMITROPHE_RENSEIGNE_01
			, ligneRGSectionHitSensLimitropheRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_SENSLIMITROPHE_REGEX_02
			, ligneRGSectionHitSensLimitropheRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_SENSLIMITROPHE_NOMENCLATURE_03
			, ligneRGSectionHitSensLimitropheNomenclature03);				
				
		/* 35 - moisSectionnement. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MOISSECTIONNEMENT_RENSEIGNE_01
			, ligneRGSectionHitMoisSectionnementRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MOISSECTIONNEMENT_REGEX_02
			, ligneRGSectionHitMoisSectionnementRegex02);
				
		/* 36 - anneeSectionnement. *******/
		this.mapRG.put(
		RG_SECTIONHIT_ANNEESECTIONNEMENT_RENSEIGNE_01
			, ligneRGSectionHitAnneeSectionnementRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_ANNEESECTIONNEMENT_REGEX_02
			, ligneRGSectionHitAnneeSectionnementRegex02);
				
		/* 37 - zoneLibre2. *******/
		this.mapRG.put(
		RG_SECTIONHIT_ZONELIBRE2_RENSEIGNE_01
			, ligneRGSectionHitZoneLibre2Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_ZONELIBRE2_REGEX_02
			, ligneRGSectionHitZoneLibre2Regex02);
				
		/* 38 - mjaN. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJAN_RENSEIGNE_01
			, ligneRGSectionHitMjaNRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJAN_REGEX_02
			, ligneRGSectionHitMjaNRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJAN_NUMERIQUE_03
			, ligneRGSectionHitMjaNNumerique03);				
				
		/* 39 - modeCalculN. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MODECALCULN_RENSEIGNE_01
			, ligneRGSectionHitModeCalculNRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MODECALCULN_REGEX_02
			, ligneRGSectionHitModeCalculNRegex02);
				
		/* 40 - pcPLN. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCPLN_RENSEIGNE_01
			, ligneRGSectionHitPcPLNRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCPLN_REGEX_02
			, ligneRGSectionHitPcPLNRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCPLN_NUMERIQUE_03
			, ligneRGSectionHitPcPLNNumerique03);				
				
		/* 41 - evaluationPLN. *******/
		this.mapRG.put(
		RG_SECTIONHIT_EVALUATIONPLN_RENSEIGNE_01
			, ligneRGSectionHitEvaluationPLNRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_EVALUATIONPLN_REGEX_02
			, ligneRGSectionHitEvaluationPLNRegex02);
				
		/* 42 - pcNuitAnnuelN. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELN_RENSEIGNE_01
			, ligneRGSectionHitPcNuitAnnuelNRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELN_REGEX_02
			, ligneRGSectionHitPcNuitAnnuelNRegex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELN_NUMERIQUE_03
			, ligneRGSectionHitPcNuitAnnuelNNumerique03);				
				
		/* 43 - indiceFiabiliteMjaN. *******/
		this.mapRG.put(
		RG_SECTIONHIT_INDICEFIABLITEMJAN_RENSEIGNE_01
			, ligneRGSectionHitIndiceFiabiliteMjaNRenseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_INDICEFIABLITEMJAN_REGEX_02
			, ligneRGSectionHitIndiceFiabiliteMjaNRegex02);
				
		/* 44 - mjmNmois01. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS01_RENSEIGNE_01
			, ligneRGSectionHitMjmNmois01Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS01_REGEX_02
			, ligneRGSectionHitMjmNmois01Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS01_NUMERIQUE_03
			, ligneRGSectionHitMjmNmois01Numerique03);
				
		/* 45 - pcNuitNmois01. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS01_RENSEIGNE_01
			, ligneRGSectionHitPcNuitNmois01Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS01_REGEX_02
			, ligneRGSectionHitPcNuitNmois01Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS01_NUMERIQUE_03
			, ligneRGSectionHitPcNuitNmois01Numerique03);				
				
		/* 46 - mjmNmois02. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS02_RENSEIGNE_01
			, ligneRGSectionHitMjmNmois02Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS02_REGEX_02
			, ligneRGSectionHitMjmNmois02Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS02_NUMERIQUE_03
			, ligneRGSectionHitMjmNmois02Numerique03);
				
		/* 47 - pcNuitNmois02. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS02_RENSEIGNE_01
			, ligneRGSectionHitPcNuitNmois02Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS02_REGEX_02
			, ligneRGSectionHitPcNuitNmois02Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS02_NUMERIQUE_03
			, ligneRGSectionHitPcNuitNmois02Numerique03);				
				
		/* 48 - mjmNmois03. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS03_RENSEIGNE_01
			, ligneRGSectionHitMjmNmois03Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS03_REGEX_02
			, ligneRGSectionHitMjmNmois03Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS03_NUMERIQUE_03
			, ligneRGSectionHitMjmNmois03Numerique03);
				
		/* 49 - pcNuitNmois03. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS03_RENSEIGNE_01
			, ligneRGSectionHitPcNuitNmois03Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS03_REGEX_02
			, ligneRGSectionHitPcNuitNmois03Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS03_NUMERIQUE_03
			, ligneRGSectionHitPcNuitNmois03Numerique03);				
				
		/* 50 - mjmNmois04. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS04_RENSEIGNE_01
			, ligneRGSectionHitMjmNmois04Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS04_REGEX_02
			, ligneRGSectionHitMjmNmois04Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS04_NUMERIQUE_03
			, ligneRGSectionHitMjmNmois04Numerique03);
				
		/* 51 - pcNuitNmois04. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS04_RENSEIGNE_01
			, ligneRGSectionHitPcNuitNmois04Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS04_REGEX_02
			, ligneRGSectionHitPcNuitNmois04Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS04_NUMERIQUE_03
			, ligneRGSectionHitPcNuitNmois04Numerique03);				
		
				
		/* 52 - mjmNmois05. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS05_RENSEIGNE_01
			, ligneRGSectionHitMjmNmois05Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS05_REGEX_02
			, ligneRGSectionHitMjmNmois05Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS05_NUMERIQUE_03
			, ligneRGSectionHitMjmNmois05Numerique03);
				
		/* 53 - pcNuitNmois05. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS05_RENSEIGNE_01
			, ligneRGSectionHitPcNuitNmois05Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS05_REGEX_02
			, ligneRGSectionHitPcNuitNmois05Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS05_NUMERIQUE_03
			, ligneRGSectionHitPcNuitNmois05Numerique03);				
		
				
		/* 54 - mjmNmois06. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS06_RENSEIGNE_01
			, ligneRGSectionHitMjmNmois06Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS06_REGEX_02
			, ligneRGSectionHitMjmNmois06Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS06_NUMERIQUE_03
			, ligneRGSectionHitMjmNmois06Numerique03);
				
		/* 55 - pcNuitNmois06. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS06_RENSEIGNE_01
			, ligneRGSectionHitPcNuitNmois06Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS06_REGEX_02
			, ligneRGSectionHitPcNuitNmois06Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS06_NUMERIQUE_03
			, ligneRGSectionHitPcNuitNmois06Numerique03);				
		
				
		/* 56 - mjmNmois07. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS07_RENSEIGNE_01
			, ligneRGSectionHitMjmNmois07Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS07_REGEX_02
			, ligneRGSectionHitMjmNmois07Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS07_NUMERIQUE_03
			, ligneRGSectionHitMjmNmois07Numerique03);
				
		/* 57 - pcNuitNmois07. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS07_RENSEIGNE_01
			, ligneRGSectionHitPcNuitNmois07Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS07_REGEX_02
			, ligneRGSectionHitPcNuitNmois07Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS07_NUMERIQUE_03
			, ligneRGSectionHitPcNuitNmois07Numerique03);				
		
				
		/* 58 - mjmNmois08. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS08_RENSEIGNE_01
			, ligneRGSectionHitMjmNmois08Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS08_REGEX_02
			, ligneRGSectionHitMjmNmois08Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS08_NUMERIQUE_03
			, ligneRGSectionHitMjmNmois08Numerique03);
				
		/* 59 - pcNuitNmois08. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS08_RENSEIGNE_01
			, ligneRGSectionHitPcNuitNmois08Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS08_REGEX_02
			, ligneRGSectionHitPcNuitNmois08Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS08_NUMERIQUE_03
			, ligneRGSectionHitPcNuitNmois08Numerique03);				
		
				
		/* 60 - mjmNmois09. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS09_RENSEIGNE_01
			, ligneRGSectionHitMjmNmois09Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS09_REGEX_02
			, ligneRGSectionHitMjmNmois09Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS09_NUMERIQUE_03
			, ligneRGSectionHitMjmNmois09Numerique03);
				
		/* 61 - pcNuitNmois09. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS09_RENSEIGNE_01
			, ligneRGSectionHitPcNuitNmois09Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS09_REGEX_02
			, ligneRGSectionHitPcNuitNmois09Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS09_NUMERIQUE_03
			, ligneRGSectionHitPcNuitNmois09Numerique03);				
		
				
		/* 62 - mjmNmois10. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS10_RENSEIGNE_01
			, ligneRGSectionHitMjmNmois10Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS10_REGEX_02
			, ligneRGSectionHitMjmNmois10Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS10_NUMERIQUE_03
			, ligneRGSectionHitMjmNmois10Numerique03);
				
		/* 63 - pcNuitNmois10. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS10_RENSEIGNE_01
			, ligneRGSectionHitPcNuitNmois10Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS10_REGEX_02
			, ligneRGSectionHitPcNuitNmois10Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS10_NUMERIQUE_03
			, ligneRGSectionHitPcNuitNmois10Numerique03);				
		
				
		/* 64 - mjmNmois11. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS11_RENSEIGNE_01
			, ligneRGSectionHitMjmNmois11Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS11_REGEX_02
			, ligneRGSectionHitMjmNmois11Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS11_NUMERIQUE_03
			, ligneRGSectionHitMjmNmois11Numerique03);
				
		/* 65 - pcNuitNmois11. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS11_RENSEIGNE_01
			, ligneRGSectionHitPcNuitNmois11Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS11_REGEX_02
			, ligneRGSectionHitPcNuitNmois11Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS11_NUMERIQUE_03
			, ligneRGSectionHitPcNuitNmois11Numerique03);				
		
				
		/* 66 - mjmNmois12. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS12_RENSEIGNE_01
			, ligneRGSectionHitMjmNmois12Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS12_REGEX_02
			, ligneRGSectionHitMjmNmois12Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJMNMOIS12_NUMERIQUE_03
			, ligneRGSectionHitMjmNmois12Numerique03);
				
		/* 67 - pcNuitNmois12. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS12_RENSEIGNE_01
			, ligneRGSectionHitPcNuitNmois12Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS12_REGEX_02
			, ligneRGSectionHitPcNuitNmois12Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITNMOIS12_NUMERIQUE_03
			, ligneRGSectionHitPcNuitNmois12Numerique03);				
				
		/* 68 - zoneLibre3. *******/
		this.mapRG.put(
		RG_SECTIONHIT_ZONELIBRE3_RENSEIGNE_01
			, ligneRGSectionHitZoneLibre3Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_ZONELIBRE3_REGEX_02
			, ligneRGSectionHitZoneLibre3Regex02);
				
		/* 69 - anneeNmoins1. *******/
		this.mapRG.put(
		RG_SECTIONHIT_ANNEENMOINS1_RENSEIGNE_01
			, ligneRGSectionHitAnneeNmoins1Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_ANNEENMOINS1_REGEX_02
			, ligneRGSectionHitAnneeNmoins1Regex02);
				
		/* 70 - mjaNmoins1. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJANMOINS1_RENSEIGNE_01
			, ligneRGSectionHitMjaNmoins1Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJANMOINS1_REGEX_02
			, ligneRGSectionHitMjaNmoins1Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJANMOINS1_NUMERIQUE_03
			, ligneRGSectionHitMjaNmoins1Numerique03);
				
		/* 71 - typeComptageNmoins1. *******/
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGENMOINS1_RENSEIGNE_01
			, ligneRGSectionHitTypeComptageNmoins1Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGENMOINS1_REGEX_02
			, ligneRGSectionHitTypeComptageNmoins1Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGENMOINS1_NOMENCLATURE_03
			, ligneRGSectionHitTypeComptageNmoins1Nomenclature03);
				
		/* 72 - modeCalculNmoins1. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MODECALCULNMOINS1_RENSEIGNE_01
			, ligneRGSectionHitModeCalculNmoins1Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MODECALCULNMOINS1_REGEX_02
			, ligneRGSectionHitModeCalculNmoins1Regex02);
				
		/* 73 - pcPLNmoins1. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCPLNMOINS1_RENSEIGNE_01
			, ligneRGSectionHitPcPLNmoins1Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCPLNMOINS1_REGEX_02
			, ligneRGSectionHitPcPLNmoins1Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCPLNMOINS1_NUMERIQUE_03
			, ligneRGSectionHitPcPLNmoins1Numerique03);				
				
		/* 74 - evaluationPLNmoins1. *******/
		this.mapRG.put(
		RG_SECTIONHIT_EVALUATIONPLNMOINS1_RENSEIGNE_01
			, ligneRGSectionHitEvaluationPLNmoins1Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_EVALUATIONPLNMOINS1_REGEX_02
			, ligneRGSectionHitEvaluationPLNmoins1Regex02);
				
		/* 75 - pcNuitAnnuelNmoins1. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELNMOINS1_RENSEIGNE_01
			, ligneRGSectionHitPcNuitAnnuelNmoins1Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELNMOINS1_REGEX_02
			, ligneRGSectionHitPcNuitAnnuelNmoins1Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELNMOINS1_NUMERIQUE_03
			, ligneRGSectionHitPcNuitAnnuelNmoins1Numerique03);				
				
		/* 76 - indiceFiabiliteMjaNmoins1. *******/
		this.mapRG.put(
		RG_SECTIONHIT_INDICEFIABLITEMJANMOINS1_RENSEIGNE_01
			, ligneRGSectionHitIndiceFiabiliteMjaNmoins1Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_INDICEFIABLITEMJANMOINS1_REGEX_02
			, ligneRGSectionHitIndiceFiabiliteMjaNmoins1Regex02);
				
		/* 77 - anneeNmoins2. *******/
		this.mapRG.put(
		RG_SECTIONHIT_ANNEENMOINS2_RENSEIGNE_01
			, ligneRGSectionHitAnneeNmoins2Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_ANNEENMOINS2_REGEX_02
			, ligneRGSectionHitAnneeNmoins2Regex02);
				
		/* 78 - mjaNmoins2. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJANMOINS2_RENSEIGNE_01
			, ligneRGSectionHitMjaNmoins2Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJANMOINS2_REGEX_02
			, ligneRGSectionHitMjaNmoins2Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJANMOINS2_NUMERIQUE_03
			, ligneRGSectionHitMjaNmoins2Numerique03);
				
		/* 79 - typeComptageNmoins2. *******/
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGENMOINS2_RENSEIGNE_01
			, ligneRGSectionHitTypeComptageNmoins2Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGENMOINS2_REGEX_02
			, ligneRGSectionHitTypeComptageNmoins2Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGENMOINS2_NOMENCLATURE_03
			, ligneRGSectionHitTypeComptageNmoins2Nomenclature03);
				
		/* 80 - modeCalculNmoins2. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MODECALCULNMOINS2_RENSEIGNE_01
			, ligneRGSectionHitModeCalculNmoins2Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MODECALCULNMOINS2_REGEX_02
			, ligneRGSectionHitModeCalculNmoins2Regex02);
				
		/* 81 - pcPLNmoins2. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCPLNMOINS2_RENSEIGNE_01
			, ligneRGSectionHitPcPLNmoins2Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCPLNMOINS2_REGEX_02
			, ligneRGSectionHitPcPLNmoins2Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCPLNMOINS2_NUMERIQUE_03
			, ligneRGSectionHitPcPLNmoins2Numerique03);				
				
		/* 82 - evaluationPLNmoins2. *******/
		this.mapRG.put(
		RG_SECTIONHIT_EVALUATIONPLNMOINS2_RENSEIGNE_01
			, ligneRGSectionHitEvaluationPLNmoins2Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_EVALUATIONPLNMOINS2_REGEX_02
			, ligneRGSectionHitEvaluationPLNmoins2Regex02);
				
		/* 83 - pcNuitAnnuelNmoins2. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELNMOINS2_RENSEIGNE_01
			, ligneRGSectionHitPcNuitAnnuelNmoins2Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELNMOINS2_REGEX_02
			, ligneRGSectionHitPcNuitAnnuelNmoins2Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELNMOINS2_NUMERIQUE_03
			, ligneRGSectionHitPcNuitAnnuelNmoins2Numerique03);				
				
		/* 84 - indiceFiabiliteMjaNmoins2. *******/
		this.mapRG.put(
		RG_SECTIONHIT_INDICEFIABLITEMJANMOINS2_RENSEIGNE_01
			, ligneRGSectionHitIndiceFiabiliteMjaNmoins2Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_INDICEFIABLITEMJANMOINS2_REGEX_02
			, ligneRGSectionHitIndiceFiabiliteMjaNmoins2Regex02);
		
				
		/* 85 - anneeNmoins3. *******/
		this.mapRG.put(
		RG_SECTIONHIT_ANNEENMOINS3_RENSEIGNE_01
			, ligneRGSectionHitAnneeNmoins3Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_ANNEENMOINS3_REGEX_02
			, ligneRGSectionHitAnneeNmoins3Regex02);
				
		/* 86 - mjaNmoins3. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJANMOINS3_RENSEIGNE_01
			, ligneRGSectionHitMjaNmoins3Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJANMOINS3_REGEX_02
			, ligneRGSectionHitMjaNmoins3Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJANMOINS3_NUMERIQUE_03
			, ligneRGSectionHitMjaNmoins3Numerique03);
				
		/* 87 - typeComptageNmoins3. *******/
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGENMOINS3_RENSEIGNE_01
			, ligneRGSectionHitTypeComptageNmoins3Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGENMOINS3_REGEX_02
			, ligneRGSectionHitTypeComptageNmoins3Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGENMOINS3_NOMENCLATURE_03
			, ligneRGSectionHitTypeComptageNmoins3Nomenclature03);
				
		/* 88 - modeCalculNmoins3. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MODECALCULNMOINS3_RENSEIGNE_01
			, ligneRGSectionHitModeCalculNmoins3Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MODECALCULNMOINS3_REGEX_02
			, ligneRGSectionHitModeCalculNmoins3Regex02);
				
		/* 89 - pcPLNmoins3. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCPLNMOINS3_RENSEIGNE_01
			, ligneRGSectionHitPcPLNmoins3Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCPLNMOINS3_REGEX_02
			, ligneRGSectionHitPcPLNmoins3Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCPLNMOINS3_NUMERIQUE_03
			, ligneRGSectionHitPcPLNmoins3Numerique03);				
				
		/* 90 - evaluationPLNmoins3. *******/
		this.mapRG.put(
		RG_SECTIONHIT_EVALUATIONPLNMOINS3_RENSEIGNE_01
			, ligneRGSectionHitEvaluationPLNmoins3Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_EVALUATIONPLNMOINS3_REGEX_02
			, ligneRGSectionHitEvaluationPLNmoins3Regex02);
				
		/* 91 - pcNuitAnnuelNmoins3. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELNMOINS3_RENSEIGNE_01
			, ligneRGSectionHitPcNuitAnnuelNmoins3Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELNMOINS3_REGEX_02
			, ligneRGSectionHitPcNuitAnnuelNmoins3Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELNMOINS3_NUMERIQUE_03
			, ligneRGSectionHitPcNuitAnnuelNmoins3Numerique03);				
				
		/* 92 - indiceFiabiliteMjaNmoins3. *******/
		this.mapRG.put(
		RG_SECTIONHIT_INDICEFIABLITEMJANMOINS3_RENSEIGNE_01
			, ligneRGSectionHitIndiceFiabiliteMjaNmoins3Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_INDICEFIABLITEMJANMOINS3_REGEX_02
			, ligneRGSectionHitIndiceFiabiliteMjaNmoins3Regex02);
		
				
		/* 93 - anneeNmoins4. *******/
		this.mapRG.put(
		RG_SECTIONHIT_ANNEENMOINS4_RENSEIGNE_01
			, ligneRGSectionHitAnneeNmoins4Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_ANNEENMOINS4_REGEX_02
			, ligneRGSectionHitAnneeNmoins4Regex02);
				
		/* 94 - mjaNmoins4. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJANMOINS4_RENSEIGNE_01
			, ligneRGSectionHitMjaNmoins4Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJANMOINS4_REGEX_02
			, ligneRGSectionHitMjaNmoins4Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJANMOINS4_NUMERIQUE_03
			, ligneRGSectionHitMjaNmoins4Numerique03);
				
		/* 95 - typeComptageNmoins4. *******/
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGENMOINS4_RENSEIGNE_01
			, ligneRGSectionHitTypeComptageNmoins4Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGENMOINS4_REGEX_02
			, ligneRGSectionHitTypeComptageNmoins4Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGENMOINS4_NOMENCLATURE_03
			, ligneRGSectionHitTypeComptageNmoins4Nomenclature03);
				
		/* 96 - modeCalculNmoins4. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MODECALCULNMOINS4_RENSEIGNE_01
			, ligneRGSectionHitModeCalculNmoins4Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MODECALCULNMOINS4_REGEX_02
			, ligneRGSectionHitModeCalculNmoins4Regex02);
				
		/* 97 - pcPLNmoins4. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCPLNMOINS4_RENSEIGNE_01
			, ligneRGSectionHitPcPLNmoins4Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCPLNMOINS4_REGEX_02
			, ligneRGSectionHitPcPLNmoins4Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCPLNMOINS4_NUMERIQUE_03
			, ligneRGSectionHitPcPLNmoins4Numerique03);				
				
		/* 98 - evaluationPLNmoins4. *******/
		this.mapRG.put(
		RG_SECTIONHIT_EVALUATIONPLNMOINS4_RENSEIGNE_01
			, ligneRGSectionHitEvaluationPLNmoins4Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_EVALUATIONPLNMOINS4_REGEX_02
			, ligneRGSectionHitEvaluationPLNmoins4Regex02);
				
		/* 99 - pcNuitAnnuelNmoins4. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELNMOINS4_RENSEIGNE_01
			, ligneRGSectionHitPcNuitAnnuelNmoins4Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELNMOINS4_REGEX_02
			, ligneRGSectionHitPcNuitAnnuelNmoins4Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELNMOINS4_NUMERIQUE_03
			, ligneRGSectionHitPcNuitAnnuelNmoins4Numerique03);				
				
		/* 100 - indiceFiabiliteMjaNmoins4. *******/
		this.mapRG.put(
		RG_SECTIONHIT_INDICEFIABLITEMJANMOINS4_RENSEIGNE_01
			, ligneRGSectionHitIndiceFiabiliteMjaNmoins4Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_INDICEFIABLITEMJANMOINS4_REGEX_02
			, ligneRGSectionHitIndiceFiabiliteMjaNmoins4Regex02);
		
				
		/* 101 - anneeNmoins5. *******/
		this.mapRG.put(
		RG_SECTIONHIT_ANNEENMOINS5_RENSEIGNE_01
			, ligneRGSectionHitAnneeNmoins5Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_ANNEENMOINS5_REGEX_02
			, ligneRGSectionHitAnneeNmoins5Regex02);
				
		/* 102 - mjaNmoins5. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MJANMOINS5_RENSEIGNE_01
			, ligneRGSectionHitMjaNmoins5Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MJANMOINS5_REGEX_02
			, ligneRGSectionHitMjaNmoins5Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_MJANMOINS5_NUMERIQUE_03
			, ligneRGSectionHitMjaNmoins5Numerique03);
				
		/* 103 - typeComptageNmoins5. *******/
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGENMOINS5_RENSEIGNE_01
			, ligneRGSectionHitTypeComptageNmoins5Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGENMOINS5_REGEX_02
			, ligneRGSectionHitTypeComptageNmoins5Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_TYPECOMPTAGENMOINS5_NOMENCLATURE_03
			, ligneRGSectionHitTypeComptageNmoins5Nomenclature03);
				
		/* 104 - modeCalculNmoins5. *******/
		this.mapRG.put(
		RG_SECTIONHIT_MODECALCULNMOINS5_RENSEIGNE_01
			, ligneRGSectionHitModeCalculNmoins5Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_MODECALCULNMOINS5_REGEX_02
			, ligneRGSectionHitModeCalculNmoins5Regex02);
				
		/* 105 - pcPLNmoins5. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCPLNMOINS5_RENSEIGNE_01
			, ligneRGSectionHitPcPLNmoins5Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCPLNMOINS5_REGEX_02
			, ligneRGSectionHitPcPLNmoins5Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCPLNMOINS5_NUMERIQUE_03
			, ligneRGSectionHitPcPLNmoins5Numerique03);				
				
		/* 106 - evaluationPLNmoins5. *******/
		this.mapRG.put(
		RG_SECTIONHIT_EVALUATIONPLNMOINS5_RENSEIGNE_01
			, ligneRGSectionHitEvaluationPLNmoins5Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_EVALUATIONPLNMOINS5_REGEX_02
			, ligneRGSectionHitEvaluationPLNmoins5Regex02);
				
		/* 107 - pcNuitAnnuelNmoins5. *******/
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELNMOINS5_RENSEIGNE_01
			, ligneRGSectionHitPcNuitAnnuelNmoins5Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELNMOINS5_REGEX_02
			, ligneRGSectionHitPcNuitAnnuelNmoins5Regex02);
		this.mapRG.put(
		RG_SECTIONHIT_PCNUITANNUELNMOINS5_NUMERIQUE_03
			, ligneRGSectionHitPcNuitAnnuelNmoins5Numerique03);				
				
		/* 108 - indiceFiabiliteMjaNmoins5. *******/
		this.mapRG.put(
		RG_SECTIONHIT_INDICEFIABLITEMJANMOINS5_RENSEIGNE_01
			, ligneRGSectionHitIndiceFiabiliteMjaNmoins5Renseigne01);
		this.mapRG.put(
		RG_SECTIONHIT_INDICEFIABLITEMJANMOINS5_REGEX_02
			, ligneRGSectionHitIndiceFiabiliteMjaNmoins5Regex02);

		return this.mapRG;
					
	} // Fin de remplirMapRG().____________________________________________

	

} // FIN DE LA CLASSE SectionHitGestionnaireRG.------------------------------
