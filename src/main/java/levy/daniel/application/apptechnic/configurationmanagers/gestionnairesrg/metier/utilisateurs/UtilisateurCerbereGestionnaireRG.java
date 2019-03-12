package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.metier.utilisateurs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.AbstractGestionnaireRG;

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
	 * Boolean activant globalement les contrôles 
	 * sur la civilite de l'utilisateur.<br/>
	 */
	private static Boolean validerCiviliteUtilisateur;

	
	/**
	 * Boolean activant la RG-Utilisateur-Civilite-01 : 
	 * "la civilite de l'Utilisateur 
	 * doit être renseignée".<br/>
	 */
	private static Boolean validerRGUtilisateurCivilite01;
	

	/**
	 * Boolean activant la RG-Utilisateur-Civilite-02 : 
	 * "la civilite de l'Utilisateur 
	 * doit se conformer à une nomenclature".<br/>
	 */
	private static Boolean validerRGUtilisateurCivilite02;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereGestionnaireRG.class);

	// *************************METHODES************************************/
	
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


}
