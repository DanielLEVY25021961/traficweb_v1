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
		
		return this.mapRG;
					
	} // Fin de remplirMapRG().____________________________________________

	

} // FIN DE LA CLASSE SectionHitGestionnaireRG.------------------------------
