package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.utilisateurs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * CLASSE UtilisateurCerbereGestionnairePreferencesRGTest :<br/>
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
 * @since 22 mars 2019
 *
 */
public class UtilisateurCerbereGestionnairePreferencesRGTest {

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
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(
				UtilisateurCerbereGestionnairePreferencesRGTest.class);
	

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereGestionnairePreferencesRGTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * teste la méthode getValiderRGUtilisateurCivilite().<br/>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetValiderRGUtilisateurCivilite() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE UtilisateurCerbereGestionnairePreferencesRGTest - méthode testGetValiderRGUtilisateurCivilite() ********** ");
		}
		
		final Boolean validerRGUtilisateurCivilite 
			= UtilisateurCerbereGestionnairePreferencesRG
				.getValiderRGUtilisateurCivilite();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("validerRGUtilisateurCivilite : " + validerRGUtilisateurCivilite);
		}

	} // Fin de testGetValiderRGUtilisateurCivilite()._____________________

		
	
} // FIN DE LA CLASSE CLASSE UtilisateurCerbereGestionnairePreferencesRGTest.
