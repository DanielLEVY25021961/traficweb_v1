package levy.daniel.application.model.services.valideurs.metier.utilisateurs.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.utilisateurs.UtilisateurCerbereGestionnairePreferencesRG;
import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.dto.metier.utilisateur.impl.UtilisateurCerbereDTO;
import levy.daniel.application.model.services.valideurs.ErreursMaps;

/**
 * CLASSE UtilisateurCerbereValideurServiceTest :<br/>
 * Test JUnit de la classe UtilisateurCerbereValideurService.<br/>
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
 * @since 25 mars 2019
 *
 */
public class UtilisateurCerbereValideurServiceTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";

	/**
	 * DTO.<br/>
	 */
	public static transient IUtilisateurCerbereDTO dto 
		= new UtilisateurCerbereDTO("3"
			, "Mlle"
			, "Sylviane", "TESTUD"
			, "01 60 81 44 44", "sylviane.testud@free.fr"
			, "SG", "SG/AMEN"
			, "CONSULTANT"
			, "DIRE"
			, "sans restriction");
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereValideurServiceTest.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereValideurServiceTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * .<br/>
	 * : void :  .<br/>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testValiderCivilite() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE UtilisateurCerbereValideurServiceTest - méthode testValiderCivilite() ********** ");
		}

		/* active toutes les RG. */
		UtilisateurCerbereGestionnairePreferencesRG.setValiderRGUtilisateurCivilite(true);
		UtilisateurCerbereGestionnairePreferencesRG.setValiderRGUtilisateurCiviliteRenseigne01(true);
		UtilisateurCerbereGestionnairePreferencesRG.setValiderRGUtilisateurCiviliteLitteral02(true);
		UtilisateurCerbereGestionnairePreferencesRG.setValiderRGUtilisateurCiviliteLongueur03(true);
		UtilisateurCerbereGestionnairePreferencesRG.setValiderRGUtilisateurCiviliteNomenclature04(true);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			UtilisateurCerbereGestionnairePreferencesRG.afficherPreferences();
		}
				
		/* instanciation d'un SERVICE. */
		UtilisateurCerbereValideurService service 
			= new UtilisateurCerbereValideurService();
		
		ErreursMaps erreurMaps = null;
		
		/* test du non-renseigné*/
		dto.setCivilite("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
		
		System.out.println(erreurMaps.getErrorsMap());
		
	} // Fin de testValiderCivilite()._______________________________________


} // FIN DE LA CLASSE UtilisateurCerbereValideurServiceTest.-----------------
