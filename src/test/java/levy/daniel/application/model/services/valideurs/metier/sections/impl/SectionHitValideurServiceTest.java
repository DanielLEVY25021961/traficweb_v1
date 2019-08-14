package levy.daniel.application.model.services.valideurs.metier.sections.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.utilisateurs.UtilisateurCerbereGestionnairePreferencesRG;
import levy.daniel.application.model.services.valideurs.ErreursMaps;

/**
 * CLASSE SectionHitValideurServiceTest :<br/>
 * Test JUnit de la classe {@link SectionHitValideurService}.<br/>
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
 * @since 13 août 2019
 *
 */
public class SectionHitValideurServiceTest {

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
		= LogFactory.getLog(SectionHitValideurServiceTest.class);

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public SectionHitValideurServiceTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * affiche les preferences à la console.<br/>
	 *
	 * @param pAffichage : boolean.
	 * 
	 * @throws Exception
	 */
	private void afficherPreferences(
			final boolean pAffichage) throws Exception {
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && pAffichage) {
			System.out.println();
			System.out.println("PREFERENCES");
			System.out.println(
					UtilisateurCerbereGestionnairePreferencesRG
						.afficherPreferences());
			System.out.println();
		}
		
	} // Fin de afficherPreferences(...).__________________________________
	

	
	/**
	 * affiche à la console les erreurs.<br/>
	 *
	 * @param pAffichage : boolean
	 * @param pErreursMaps : ErreursMaps
	 * @param pString : String.<br/>
	 */
	private void afficherErreurs(
			final boolean pAffichage
				, final ErreursMaps pErreursMaps
					, final String pString) {
		
		/* AFFICHAGE A LA CONSOLE DES ERREURS. */
		if (AFFICHAGE_GENERAL && pAffichage) {
			System.out.println("******* " + pString  + " *******");
			System.out.println("ErrorsMap : \n" + pErreursMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + pErreursMaps.afficherErrorsMapDetaille());
		}
		
	} // Fin de afficherErreurs(...).______________________________________
	

	
	/**
	 * garantit que le service ne lève pas d'erreurs.<br/>
	 *
	 * @param pErreursMaps : ErreursMaps.<br/>
	 */
	private void assertPasErreurs(
			final ErreursMaps pErreursMaps) {
		
		assertTrue("ErrorsMap doit être vide : "
				, pErreursMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, pErreursMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de assertPasErreurs(...)._____________________________________
	

	
	/**
	 * garantit que le service lève des erreurs.<br/>
	 *
	 * @param pErreursMaps : ErreursMaps.<br/>
	 */
	private void assertErreurs(
			final ErreursMaps pErreursMaps) {
		
		assertFalse("ErrorsMap ne doit pas être vide : "
				, pErreursMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, pErreursMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de assertErreurs(...).________________________________________
	
	
		
} // FIN DE LA CLASSE SectionHitValideurServiceTest.-------------------------
