package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.metier.utilisateurs;

import java.net.MalformedURLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.utilisateurs.UtilisateurCerbereGestionnairePreferencesRG;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.AbstractGestionnaireRG;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.EnumTypesValidation;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.GestionnaireRG;
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
	 * Règle de Gestion.<br/>
	 * "RG_UTILISATEUR_CIVILITE_RENSEIGNE_01 : la civilité de l'Utilisateur doit être renseignée".<br/>
	 */
	public static final String RG_UTILISATEUR_CIVILITE_RENSEIGNE_01 
		= "RG_UTILISATEUR_CIVILITE_RENSEIGNE_01 : la civilité de l'Utilisateur doit être renseignée";
	
	/**
	 * Message à l'attention de l'utilisateur.<br/>
	 * "la civilité de l'Utilisateur doit être renseignée"<br/>
	 */
	public static final String RG_UTILISATEUR_CIVILITE_01_MESSAGE 
		= "la civilité de l'Utilisateur doit être renseignée (obligatoire)";

	
	
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
	 */
	private UtilisateurCerbereGestionnaireRG() {		
		super();				
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirCheminRessourceExterneRG() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirNomBasePropertiesRG() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	/**
	 * method remplirMapRG() :<br/>
	 * <ul>
	 * remplit et retourne la Map&lt;String, LigneRG&gt; mapRG 
	 * contenant toutes 
	 * les Règles de Gestion (RG) de l'UTILISATEUR implémentées 
	 * dans les services de l'application avec :
	 * <li>String : le nom de la RG.</li>
	 * <li>LigneRG : pure fabrication encapsulant 
	 * tous les éléments relatifs à la RG.</li>
	 * </ul>
	 * Une LigneRG encapsule :<br/>
	 * [id;Actif;activité des contrôles sur l'attribut;activité de la RG
	 * ;RG implémentée;clé du type de contrôle;type de contrôle
	 * ;Message d'erreur;Objet Métier concerné;Attribut concerné
	 * ;Classe implémentant la RG;Méthode implémentant la RG;].<br/>
	 * <br/>
	 *
	 * @return : Map&lt;String, LigneRG&gt; : mapRG.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	private static Map<String, LigneRG> remplirMapRG() 
			throws MalformedURLException {
		
		synchronized (GestionnaireRG.class) {
			
			/* RG_UTILISATEUR_CIVILITE_01. */
			final LigneRG ligneRGUtilisateurCiviliteRenseigne01 
			= new LigneRG(UtilisateurCerbereGestionnairePreferencesRG.getValiderRGUtilisateurCivilite()
					, UtilisateurCerbereGestionnairePreferencesRG.getValiderRGUtilisateurCiviliteRenseigne01()
					, RG_UTILISATEUR_CIVILITE_RENSEIGNE_01
					, EnumTypesValidation.RENSEIGNE.getNumero()
					, pMessageRG
					, pNomObjetMetier
					, pNomAttributObjetMetier
					, pClasseControle
					, pMethodeControle
					, pFichierProperties
					, pCleDansProperties);
						
			mapRG.put(
					RG_UTILISATEUR_CIVILITE_RENSEIGNE_01
						, ligneRGUtilisateurCiviliteRenseigne01);
			

			return mapRG;
			
		} // Fin de bloc synchronized.__________________________
		
	} // Fin de remplirMapRG().____________________________________________

	

} // FIN DE LA CLASSE UtilisateurCerbereGestionnaireRG.----------------------
