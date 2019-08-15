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
	
	/**
	 * "numDepartement".<br/>
	 */
	public static final String ATTRIBUT_NUMDEPARTEMENT 
		= "numDepartement";

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
	 * 
	 * @throws Exception 
	 */
	public SectionHitGestionnaireRG() throws Exception {		
		super();				
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirCheminRessourceExterneRG() throws Exception {
		
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
		
		final String pathAbsoluPropertiesRG 
			= pathAbsoluPropertiesRGPath.toString();
		
		return pathAbsoluPropertiesRG;
		
	} // Fin de fournirCheminRessourceExterneRG()._________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirNomBasePropertiesRG() {
		return "SectionHit_RG";
	} // Fin de fournirNomBasePropertiesRG().______________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final Map<String, LigneRG> remplirMapRG() 
			throws Exception {
				
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
				, "validerRGSectionHitNumDepartementRenseigne01"
				, CHEMIN_RELATIF_FICHIER_PROPERTIES_RG
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
				, "validerRGSectionHitNumDepartementRegex02"
				, CHEMIN_RELATIF_FICHIER_PROPERTIES_RG
				, SectionHitGestionnairePreferencesRG.fournirKeyValiderRGSectionHitNumDepartementRegex02());
		
		// REMPLISSAGE DE LA MAP.
		this.mapRG.put(
				RG_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01
					, ligneRGSectionHitNumDepartementRenseigne01);
		this.mapRG.put(
				RG_SECTIONHIT_NUMDEPARTEMENT_REGEX_02
					, ligneRGSectionHitNumDepartementRegex02);

		return this.mapRG;
					
	} // Fin de remplirMapRG().____________________________________________

	

} // FIN DE LA CLASSE SectionHitGestionnaireRG.------------------------------
