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
				+ "doit être renseigné (obligatoire)";

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
				+ "doit être renseigné (obligatoire)";

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
				+ "doit être renseignée (obligatoire)";

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
				+ "doit être renseignée";

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
				+ "doit être renseigné";

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
				+ "doit être renseignée";

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
				+ "doit être renseigné (obligatoire)";

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
				+ "doit être renseigné";

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
		
		return this.mapRG;
					
	} // Fin de remplirMapRG().____________________________________________

	

} // FIN DE LA CLASSE SectionHitGestionnaireRG.------------------------------
