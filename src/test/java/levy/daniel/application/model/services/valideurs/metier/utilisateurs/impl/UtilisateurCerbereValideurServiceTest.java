package levy.daniel.application.model.services.valideurs.metier.utilisateurs.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	 * <li>garantit que l'interrupteur général attribut fonctionne.</li>
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
		final UtilisateurCerbereValideurService service 
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
		
		/* garantit que l'interrupteur général attribut fonctionne. */
		UtilisateurCerbereGestionnairePreferencesRG.setValiderRGUtilisateurCivilite(false);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* civilite renseigne avec zozo et interrupteur ATTRIBUT desactivé *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		
		UtilisateurCerbereGestionnairePreferencesRG.setValiderRGUtilisateurCivilite(true);
		
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
	 * <li>garantit que l'interrupteur general à false 
	 * désactive les contrôles sur l'attribut.</li>
	 * <li>garantit que l'interrupteur general à true 
	 * active les contrôles sur l'attribut.</li>
	 * <li>garantit que les interrupteurs de RG fonctionnent.</li>
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
		
		/* AFFICHAGE DES PREFERENCES. */
		this.afficherPreferences(affichage);
		
		/* instanciation d'un SERVICE. */
		final UtilisateurCerbereValideurService service 
			= new UtilisateurCerbereValideurService();
		
		ErreursMaps erreurMaps = null;
		
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPrenom("");
		
		/* desactive l'interrupteur général attribut. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("DESACTIVE L'INTERRUPTEUR GENERAL D'ATTRIBUT");
		}
		UtilisateurCerbereGestionnairePreferencesRG.setValiderRGUtilisateurPrenom(false);
		
		/* AFFICHAGE DES PREFERENCES. */
		this.afficherPreferences(affichage);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);

		/* AFFICHAGE A LA CONSOLE DES ERREURS. */
		this.afficherErreurs(
				affichage
					, erreurMaps
					, "prenom non renseigne et interrupteur general désactivé");
		
		/* garantit que l'interrupteur general à false 
		 * désactive les contrôles sur l'attribut. */
		this.assertPasErreurs(erreurMaps);
		
		/* active l'interrupteur général attribut. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("ACTIVE L'INTERRUPTEUR GENERAL D'ATTRIBUT");
		}
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurPrenom(true);
		
		/* AFFICHAGE DES PREFERENCES. */
		this.afficherPreferences(affichage);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE DES ERREURS. */
		this.afficherErreurs(
				affichage
					, erreurMaps
					, "prenom non renseigne et interrupteur general activé");
		
		/* garantit que l'interrupteur general à true 
		 * active les contrôles sur l'attribut. */
		this.assertErreurs(erreurMaps);
		
		dto.setPrenom("Adalbert");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		this.afficherErreurs(
				affichage
					, erreurMaps
					, "prenom renseigne avec Adalbert");
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		this.assertPasErreurs(erreurMaps);
		
		dto.setPrenom("MauvaisPrenom8");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		this.afficherErreurs(
				affichage
					, erreurMaps
					, "prenom renseigne avec MauvaisPrenom8");
		
		/* garantit que la RG LITTERAL fonctionne. */
		this.assertErreurs(erreurMaps);
		
		/* désactive l'interrupteur RG LITTERAL. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("DESACTIVE L'INTERRUPTEUR RG LITTERAL");
		}
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurPrenomLitteral02(false);
		
		/* AFFICHAGE DES PREFERENCES. */
		this.afficherPreferences(affichage);

		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		this.afficherErreurs(
				affichage
					, erreurMaps
					, "prenom renseigne avec MauvaisPrenom8 et RG LITTERAL désactivée");
		
		/* garantit que les interrupteurs de RG fonctionnent. */
		this.assertPasErreurs(erreurMaps);
		
		/* réactive l'interrupteur RG*/
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurPrenomLitteral02(true);
		
		dto.setPrenom("MauvaisPrenomTropLongmlmlmlmlmmlmlmlmlmlmlmlmmlmlmmlmlmlmlmlmlmlmlmlmlmlmlm");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		this.afficherErreurs(
				affichage
					, erreurMaps
					, "prenom renseigne avec MauvaisPrenomTropLongmlmlmlmlmmlmlmlmlmlmlmlmmlmlmmlmlmlmlmlmlmlmlmlmlmlmlm");
		
		/* garantit que la RG LONGUEUR fonctionne. */
		this.assertErreurs(erreurMaps);
		
		/* désactive l'interrupteur RG LONGUEUR. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("DESACTIVE L'INTERRUPTEUR RG LONGUEUR");
		}
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurPrenomLongueur03(false);
		
		/* AFFICHAGE DES PREFERENCES. */
		this.afficherPreferences(affichage);

		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		this.afficherErreurs(
				affichage
					, erreurMaps
					, "prenom renseigne avec MauvaisPrenomTropLongmlmlmlmlmmlmlmlmlmlmlmlmmlmlmmlmlmlmlmlmlmlmlmlmlmlmlm et RG LONGUEUR désactivée");
		
		/* garantit que les interrupteurs de RG fonctionnent. */
		this.assertPasErreurs(erreurMaps);
		
		/* ré-active l'interrupteur RG LONGUEUR. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("RE-ACTIVE L'INTERRUPTEUR RG LONGUEUR");
		}
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurPrenomLongueur03(true);

		dto.setPrenom("Bon Prénom");
		
	} // Fin de testValiderPrenom()._______________________________________

	
	
	/**
	 * teste la validation du nom 
	 * validerNom(IUtilisateurCerbereDTO, String, ErreursMaps).<br/>
	 * <ul>
	 * <li>garantit que l'interrupteur general à false 
	 * désactive les contrôles sur l'attribut.</li>
	 * <li>garantit que l'interrupteur general à true 
	 * active les contrôles sur l'attribut.</li>
	 * <li>garantit que les interrupteurs de RG fonctionnent.</li>
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
	public void testValiderNom() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE UtilisateurCerbereValideurServiceTest - méthode testValiderNom() ********** ");
		}

		/* active toutes les RG. */
		UtilisateurCerbereGestionnairePreferencesRG.setValiderRGUtilisateurNom(true);
		UtilisateurCerbereGestionnairePreferencesRG.setValiderRGUtilisateurNomRenseigne01(true);
		UtilisateurCerbereGestionnairePreferencesRG.setValiderRGUtilisateurNomLitteral02(true);
		UtilisateurCerbereGestionnairePreferencesRG.setValiderRGUtilisateurNomLongueur03(true);
		
		/* AFFICHAGE DES PREFERENCES. */
		this.afficherPreferences(affichage);
		
		/* instanciation d'un SERVICE. */
		final UtilisateurCerbereValideurService service 
			= new UtilisateurCerbereValideurService();
		
		ErreursMaps erreurMaps = null;
		
		/* TEST DU NON RENSEIGNE ***** */
		dto.setNom("");
		
		/* desactive l'interrupteur général attribut. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("DESACTIVE L'INTERRUPTEUR GENERAL D'ATTRIBUT");
		}
		UtilisateurCerbereGestionnairePreferencesRG.setValiderRGUtilisateurNom(false);
		
		/* AFFICHAGE DES PREFERENCES. */
		this.afficherPreferences(affichage);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);

		/* AFFICHAGE A LA CONSOLE DES ERREURS. */
		this.afficherErreurs(
				affichage
					, erreurMaps
					, "nom non renseigne et interrupteur general désactivé");
		
		/* garantit que l'interrupteur general à false 
		 * désactive les contrôles sur l'attribut. */
		this.assertPasErreurs(erreurMaps);
		
		/* active l'interrupteur général attribut. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("ACTIVE L'INTERRUPTEUR GENERAL D'ATTRIBUT");
		}
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurNom(true);
		
		/* AFFICHAGE DES PREFERENCES. */
		this.afficherPreferences(affichage);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE DES ERREURS. */
		this.afficherErreurs(
				affichage
					, erreurMaps
					, "nom non renseigne et interrupteur general activé");
		
		/* garantit que l'interrupteur general à true 
		 * active les contrôles sur l'attribut. */
		this.assertErreurs(erreurMaps);
		
		dto.setNom("Bon Nom");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		this.afficherErreurs(
				affichage
					, erreurMaps
					, "nom renseigne avec Adalbert");
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		this.assertPasErreurs(erreurMaps);
		
		dto.setNom("MauvaisNom8");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		this.afficherErreurs(
				affichage
					, erreurMaps
					, "nom renseigne avec MauvaisNom8");
		
		/* garantit que la RG LITTERAL fonctionne. */
		this.assertErreurs(erreurMaps);
		
		/* désactive l'interrupteur RG LITTERAL. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("DESACTIVE L'INTERRUPTEUR RG LITTERAL");
		}
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurNomLitteral02(false);
		
		/* AFFICHAGE DES PREFERENCES. */
		this.afficherPreferences(affichage);

		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		this.afficherErreurs(
				affichage
					, erreurMaps
					, "nom renseigne avec MauvaisNom8 et RG LITTERAL désactivée");
		
		/* garantit que les interrupteurs de RG fonctionnent. */
		this.assertPasErreurs(erreurMaps);
		
		/* réactive l'interrupteur RG*/
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurNomLitteral02(true);
		
		dto.setNom("MauvaisNomTropLongmlmlmlmlmmlmlmlmlmlmlmlmmlmlmmlmlmlmlmlmlmlmlmlmlmlmlm");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		this.afficherErreurs(
				affichage
					, erreurMaps
					, "nom renseigne avec MauvaisNomTropLongmlmlmlmlmmlmlmlmlmlmlmlmmlmlmmlmlmlmlmlmlmlmlmlmlmlmlm");
		
		/* garantit que la RG LONGUEUR fonctionne. */
		this.assertErreurs(erreurMaps);
		
		/* désactive l'interrupteur RG LONGUEUR. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("DESACTIVE L'INTERRUPTEUR RG LONGUEUR");
		}
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurNomLongueur03(false);
		
		/* AFFICHAGE DES PREFERENCES. */
		this.afficherPreferences(affichage);

		// VALIDATION PAR LE SERVICE.
		erreurMaps = service.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		this.afficherErreurs(
				affichage
					, erreurMaps
					, "nom renseigne avec MauvaisNomTropLongmlmlmlmlmmlmlmlmlmlmlmlmmlmlmmlmlmlmlmlmlmlmlmlmlmlmlm et RG LONGUEUR désactivée");
		
		/* garantit que les interrupteurs de RG fonctionnent. */
		this.assertPasErreurs(erreurMaps);

		/* ré-active l'interrupteur RG LONGUEUR. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("RE-ACTIVE L'INTERRUPTEUR RG LONGUEUR");
		}
		UtilisateurCerbereGestionnairePreferencesRG
			.setValiderRGUtilisateurNomLongueur03(true);

		dto.setNom("Bon Nom");
		
	} // Fin de testValiderNom().__________________________________________


	
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
	

	
	/**
	 * .<br/>
	 *
	 * @throws Exception : void :  .<br/>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testValiderTel() throws Exception {
		
		final String numTelBon1 = "03 84 41 80 47"; // NOPMD by daniel.levy on 23/04/19 11:17
		final String numTelBon2 = "03-84-41-80-47"; // NOPMD by daniel.levy on 23/04/19 11:17
		final String numTelBon3 = "03.84.41.80.47"; // NOPMD by daniel.levy on 23/04/19 11:17
		final String numTelBon4 = "0384418047";
		
		final String motif2Chiffres = "(\\d\\d)";
		final String motifTiretOuPointOuEspaceOuRien = "([\\-\\.\\x20])?";
		
		final String motifTelFrancais2  // NOPMD by daniel.levy on 23/04/19 11:17
			= "^(\\d\\d)([\\-\\.\\x20])?(\\d\\d)([\\-\\.\\x20])?(\\d\\d)([\\-\\.\\x20])?(\\d\\d)([\\-\\.\\x20])?(\\d\\d)([\\-\\.\\x20])?$";
		
		/* 03-84-41-80-47 ou 03.84.41.80.47 ou 03 84 41 80 47 ou 0384418047 */
		final String motifTelFrancais 
			= "^" 
					+ motif2Chiffres + motifTiretOuPointOuEspaceOuRien 
					+ motif2Chiffres + motifTiretOuPointOuEspaceOuRien
					+ motif2Chiffres + motifTiretOuPointOuEspaceOuRien
					+ motif2Chiffres + motifTiretOuPointOuEspaceOuRien
					+ motif2Chiffres + motifTiretOuPointOuEspaceOuRien 
					+ "$";
		
		System.out.println("MOTIF = " + motifTelFrancais);
		
		final Pattern pattern = Pattern.compile(motifTelFrancais);
		
		final Matcher matcher = pattern.matcher(numTelBon4);
		
		if (matcher.matches()) {
			
			System.out.println("C'est bon : " + matcher.group());
			final int nombreGroups = matcher.groupCount();
			System.out.println("nombre de groupes capturés : " + nombreGroups);
			
			for (int i = 0; i < nombreGroups; i++) {
				System.out.println("Group(" + i + ") = " + matcher.group(i));
			}
			
		} else {
			System.out.println("Ne matche pas");
		}
		
	} // Fin de testValiderTel().__________________________________________
	

	
	/**
	 * .<br/>
	 *
	 * @throws Exception : void :  .<br/>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testRemplacer() throws Exception {
		
		final String string = "la civilite de l'Utilisateur ne doit pas excéder 15 caractères";
		final String valeurRemplacement = "127";
		
		final String motif = "(\\d+)";
		
		final Pattern pattern = Pattern.compile(motif);
		
		final Matcher matcher = pattern.matcher(string);
		
		if (matcher.find()) {
			
			final String groupTrouve = matcher.group();
			
			System.out.println("Groupe trouvé : " + groupTrouve);
			
			final String chaineSubstituee = matcher.replaceFirst(valeurRemplacement);
			
			System.out.println("Chaine finale : " + chaineSubstituee);
		}
		
	} // Fin de testRemplacer().___________________________________________
	
	
	/**
	 * .<br/>
	 *
	 * @throws Exception : void :  .<br/>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testValiderFormat1Nombre() throws Exception {
		
		final String stringBon = "la civilite de l'Utilisateur ne doit pas excéder 15 caractères";
		
		final String motif = "^(\\D*)(\\d+)(\\D*)$";
		
		final Pattern pattern = Pattern.compile(motif);
		
		final Matcher matcher = pattern.matcher(stringBon);
		
		if (matcher.matches()) {
			System.out.println("MATCHE");
		} else {
			System.out.println("NE MATCHE PAS");
		}
		
	} // Fin de testValiderFormat1Nombre().________________________________

	
	/**
	 * .<br/>
	 *
	 * @throws Exception : void :  .<br/>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testExtraireNombreUnique() throws Exception {
		
		final String stringBon = "la civilite de l'Utilisateur ne doit pas excéder 150 caractères";
		
		final String motif = "^(\\D*)(\\d+)(\\D*)$";
		
		final Pattern pattern = Pattern.compile(motif);
		
		final Matcher matcher = pattern.matcher(stringBon);
		
		if (matcher.matches()) {
			
			System.out.println("MATCHE");
			
			final int nombreGroupes = matcher.groupCount();
			
			for (int i = 0; i <= nombreGroupes; i++) {
				System.out.println("Groupe(" + i + ") = " + matcher.group(i));
			}
			
		} else {
			System.out.println("NE MATCHE PAS");
		}
		
	} // Fin de testExtraireNombreUnique().________________________________

	
} // FIN DE LA CLASSE UtilisateurCerbereValideurServiceTest.-----------------
