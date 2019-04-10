package levy.daniel.application.model.services.valideurs.metier.utilisateurs.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	 * teste la validation de la civilite 
	 * validerCivilite(IUtilisateurCerbereDTO, String, ErreursMaps).<br/>
	 * <ul>
	 * <li>garantit que le SERVICE rafraichit les messages à chaque appel.</li>
	 * <li>garantit que la RG NON RENSEIGNE fonctionne.</li>
	 * <li>garantit que la RG LITTERAL fonctionne.</li>
	 * <li>garantit que la RG LONGUEUR fonctionne.</li>
	 * <li>garantit que la RG NOMENCLATURE fonctionne.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testValiderCivilite() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
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
			System.out.println();
			System.out.println("PREFERENCES");
			System.out.println(
					UtilisateurCerbereGestionnairePreferencesRG
						.afficherPreferences());
			System.out.println();
		}
				
		/* instanciation d'un SERVICE. */
		UtilisateurCerbereValideurService service 
			= new UtilisateurCerbereValideurService();
		
		ErreursMaps erreurMaps = null;
		
		/* TEST DU NON RENSEIGNE ***** */
		dto.setCivilite("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* civilite non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
		/* TEST DU RENSEIGNE. ********* */
		dto.setCivilite("M.");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* civilite renseigne avec M. *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
		/* TEST DU LITTERAL. ********* */
		dto.setCivilite("Maître_8");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* civilite renseigne avec Maître_8 *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}

		/* garantit que la RG LITTERAL fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
		dto.setCivilite("Maître");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* civilite renseigne avec Maître *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		/* TEST DE LONGUEUR. ********* */
		dto.setCivilite("Maître_8_fghjfjhgfhjgfjfjfhjgjjgfh");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* civilite renseigne avec Maître_8_fghjfjhgfhjgfjfjfhjgjjgfh *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}

		/* garantit que la RG LONGUEUR fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
		dto.setCivilite("Maître");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* civilite renseigne avec Maître *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		/* TEST DE NOMENCLATURE. ********* */
		dto.setCivilite("zozo");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* civilite renseigne avec zozo *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}

		/* garantit que la RG NOMENCLATURE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
		dto.setCivilite("Monsieur");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* civilite renseigne avec Monsieur *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

	} // Fin de testValiderCivilite()._______________________________________

	
	
	/**
	 * teste la validation du prenom 
	 * validerPrenom(IUtilisateurCerbereDTO, String, ErreursMaps).<br/>
	 * <ul>
	 * <li>garantit que le SERVICE rafraichit les messages à chaque appel.</li>
	 * <li>garantit que la RG NON RENSEIGNE fonctionne.</li>
	 * <li>garantit que la RG LITTERAL fonctionne.</li>
	 * <li>garantit que la RG LONGUEUR fonctionne.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testValiderPrenom() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE UtilisateurCerbereValideurServiceTest - méthode testValiderPrenom() ********** ");
		}

		/* active toutes les RG. */
		UtilisateurCerbereGestionnairePreferencesRG.setValiderRGUtilisateurPrenom(true);
		UtilisateurCerbereGestionnairePreferencesRG.setValiderRGUtilisateurPrenomRenseigne01(true);
		UtilisateurCerbereGestionnairePreferencesRG.setValiderRGUtilisateurPrenomLitteral02(true);
		UtilisateurCerbereGestionnairePreferencesRG.setValiderRGUtilisateurPrenomLongueur03(true);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("PREFERENCES");
			System.out.println(
					UtilisateurCerbereGestionnairePreferencesRG
						.afficherPreferences());
			System.out.println();
		}
				
		/* instanciation d'un SERVICE. */
		UtilisateurCerbereValideurService service 
			= new UtilisateurCerbereValideurService();
		
	}


} // FIN DE LA CLASSE UtilisateurCerbereValideurServiceTest.-----------------
