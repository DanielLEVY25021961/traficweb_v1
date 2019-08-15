package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.metier.utilisateurs;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.utilisateurs.UtilisateurCerbereGestionnairePreferencesControles;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.utilisateurs.UtilisateurCerbereGestionnairePreferencesRG;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.AbstractGestionnaireRG;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.EnumTypesValidation;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.LigneRG;

/**
 * CLASSE UtilisateurCerbereGestionnaireRG :<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 12 mars 2019
 *
 */
public class UtilisateurCerbereGestionnaireRG 
					extends AbstractGestionnaireRG {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "RG_UTILISATEUR_CIVILITE_RENSEIGNE_01 : la civilité de l'Utilisateur doit être renseignée (obligatoire)".<br/>
	 */
	public static final String RG_UTILISATEUR_CIVILITE_RENSEIGNE_01 
		= "RG_UTILISATEUR_CIVILITE_RENSEIGNE_01 : la civilité de l'Utilisateur doit être renseignée (obligatoire)";
	
	/**
	 * Message à l'attention de l'utilisateur.<br/>
	 * "la civilité de l'Utilisateur doit être renseignée (obligatoire)"<br/>
	 */
	public static final String RG_UTILISATEUR_CIVILITE_RENSEIGNE_01_MESSAGE 
		= "la civilité de l'Utilisateur doit être renseignée (obligatoire)";

	/**
	 * Nom de l'objet métier concerné par ces Règles de Gestion (RG).<br/>
	 * "UtilisateurCerbere".<br/>
	 */
	public static final String NOM_OBJETMETIER 
		= "UtilisateurCerbere";
	
	 /**
	 * "model/services/valideurs/metier/utilisateurs/impl/UtilisateurCerbereValideurService.java".<br/>
	 */
	public static final String CLASSE_CONTROLE 
		= "model/services/valideurs/metier/utilisateurs/impl/UtilisateurCerbereValideurService.java";
	
	/**
	 * Chemin relatif par rapport au répertoire "ressources_externes" 
	 * du <b>répertoire</b> du fichier properties contenant 
	 * les RG de l'objet métier.<br/>
	 * "preferences/metier/utilisateurs".<br/>
	 */
	public static final String CHEMIN_RELATIF_FICHIER_PROPERTIES_RG 
		= "preferences/metier/utilisateurs";
	
	/**
	 * "civilite".<br/>
	 */
	public static final String ATTRIBUT_CIVILITE 
		= "civilite";
	
	/**
	 * mapRG : Map&lt;String,LigneRG&gt; :<br/>
	 * <ul>
	 * Map contenant toutes les RG concernant l'UTILISATEUR 
	 * implémentées dans l'application avec :
	 * <li>String : nom de la RG</li>
	 * <li>LigneRG : Encapsulation des éléments relatifs à la RG</li>
	 * </ul>
	 * Une ligne RG encapsule :<br/>
	 * [id;Actif;activité des contrôles sur l'attribut;activité de la RG
	 * ;RG implémentée;clé du type de contrôle;type de contrôle
	 * ;Message d'erreur;Objet Métier concerné;Attribut concerné
	 * ;Classe implémentant la RG;Méthode implémentant la RG;
	 * properties;clé;].<br/>
	 */
	private static Map<String, LigneRG> mapRG 
		= new ConcurrentHashMap<String, LigneRG>();
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereGestionnaireRG.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * 
	 * @throws Exception 
	 */
	public UtilisateurCerbereGestionnaireRG() throws Exception {		
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
		return "UtilisateurCerbere_RG";
	} // Fin de fournirNomBasePropertiesRG().______________________________
	
	
	
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final Map<String, LigneRG> remplirMapRG() 
			throws Exception {

		/* RG_UTILISATEUR_CIVILITE_RENSEIGNE_01. */
		final LigneRG ligneRGUtilisateurCiviliteRenseigne01 
		= new LigneRG(UtilisateurCerbereGestionnairePreferencesRG.getValiderRGUtilisateurCivilite()
				, UtilisateurCerbereGestionnairePreferencesRG.getValiderRGUtilisateurCiviliteRenseigne01()
				, RG_UTILISATEUR_CIVILITE_RENSEIGNE_01
				, EnumTypesValidation.RENSEIGNE.getNumero()
				, UtilisateurCerbereGestionnairePreferencesControles.getMessageUtilisateurCiviliteRenseigne01()
				, NOM_OBJETMETIER
				, ATTRIBUT_CIVILITE
				, CLASSE_CONTROLE
				, "validerRGUtilisateurCiviliteRenseigne01"
				, CHEMIN_RELATIF_FICHIER_PROPERTIES_RG
				, UtilisateurCerbereGestionnairePreferencesRG.fournirKeyValiderRGUtilisateurCiviliteRenseigne01());
		
		// REMPLISSAGE DE LA MAP.
		mapRG.put(
				RG_UTILISATEUR_CIVILITE_RENSEIGNE_01
					, ligneRGUtilisateurCiviliteRenseigne01);
		

		return mapRG;
			
	} // Fin de remplirMapRG().____________________________________________

	

} // FIN DE LA CLASSE UtilisateurCerbereGestionnaireRG.----------------------
