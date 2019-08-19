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

/**
 * CLASSE SectionHitGestionnaireRG :<br/>
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

	/* 2 - numSection. */	
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

	/* 3 - sens. */	
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

	/* 4 - nature. */	
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

	/* 5 - classe. */	
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

		return this.mapRG;
					
	} // Fin de remplirMapRG().____________________________________________

	

} // FIN DE LA CLASSE SectionHitGestionnaireRG.------------------------------
