package levy.daniel.application.model.services.valideurs.metier.sections.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.sections.SectionHitGestionnairePreferencesRG;
import levy.daniel.application.model.FactorySectionHit;
import levy.daniel.application.model.dto.metier.sections.ISectionHitDTO;
import levy.daniel.application.model.services.valideurs.ErreursMaps;
import levy.daniel.application.model.services.valideurs.metier.sections.ISectionHitValideurService;

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
	 * ISectionHitValideurService.
	 */
	public static final ISectionHitValideurService SERVICE 
		= new SectionHitValideurService();

	/**
	 * ISectionHitDTO.
	 */
	public static ISectionHitDTO dto;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitValideurServiceTest.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public SectionHitValideurServiceTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/* 1 - numDepartement. *********************/
	/**
	 * teste la validation de numDepartement 
	 * validerNumDepartement(ISectionHitDTO, String, ErreursMaps).<br/>
	 * <ul>
	 * <li>garantit que l'interrupteur général attribut fonctionne.</li>
	 * <li>garantit que le SERVICE rafraichit les messages à chaque appel.</li>
	 * <li>garantit que la RG NON RENSEIGNE fonctionne.</li>
	 * <li>garantit que la RG REGEX fonctionne.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testValiderNumDepartement() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderNumDepartement() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;
		
		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setNumDepartement("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numDepartement non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
		//*********************************
		/* TEST DU MAL RENSEIGNE ***** */
		final String valeurMalRenseigne = "0A1B";
		dto.setNumDepartement(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numDepartement mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU RENSEIGNE. ********* */
		final String valeur = "030";
		dto.setNumDepartement(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numDepartement renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		
	} // Fin de testValiderNumDepartement()._______________________________

	

	/* 2 - numSection. *******************************/
	/**
	 * teste la validation de numSection 
	 * validerNumSection(ISectionHitDTO, String, ErreursMaps).<br/>
	 * <ul>
	 * <li>garantit que l'interrupteur général attribut fonctionne.</li>
	 * <li>garantit que le SERVICE rafraichit les messages à chaque appel.</li>
	 * <li>garantit que la RG NON RENSEIGNE fonctionne.</li>
	 * <li>garantit que la RG REGEX fonctionne.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testValiderNumSection() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderNumSection() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setNumSection("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numSection non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU MAL RENSEIGNE ***** */
		final String valeurMalRenseigne = "041A078";
		dto.setNumSection(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numSection mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "027007";
		dto.setNumSection(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numSection renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		
	} // Fin de testValiderNumSection().___________________________________

	
	
	/* 3 - sens. ************************************/
	/**
	 * teste la validation de sens 
	 * validerSens(ISectionHitDTO, String, ErreursMaps).<br/>
	 * <ul>
	 * <li>garantit que l'interrupteur général attribut fonctionne.</li>
	 * <li>garantit que le SERVICE rafraichit les messages à chaque appel.</li>
	 * <li>garantit que la RG NON RENSEIGNE fonctionne.</li>
	 * <li>garantit que la RG REGEX fonctionne.</li>
	 * <li>garantit que la RG NOMENCLATURE fonctionne.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testValiderSens() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderSens() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setSens("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* sens non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU MAL RENSEIGNE ***** */
		final String valeurMalRenseigne = "A";
		dto.setSens(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* sens mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU HORS NOMENCLATURE ***** */
		final String valeurHorsNomenclature = "6";
		dto.setSens(valeurHorsNomenclature);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* sens hors nomenclature avec '" + valeurHorsNomenclature + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "5";
		dto.setSens(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* sens renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		
	} // Fin de testValiderSens()._________________________________________

	
	
	/* 4 - nature. ************************************/
	/**
	 * teste la validation de nature 
	 * validerNature(ISectionHitDTO, String, ErreursMaps).<br/>
	 * <ul>
	 * <li>garantit que l'interrupteur général attribut fonctionne.</li>
	 * <li>garantit que le SERVICE rafraichit les messages à chaque appel.</li>
	 * <li>garantit que la RG NON RENSEIGNE fonctionne.</li>
	 * <li>garantit que la RG REGEX fonctionne.</li>
	 * <li>garantit que la RG NOMENCLATURE fonctionne.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testValiderNature() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderNature() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setNature("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* nature non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU MAL RENSEIGNE ***** */
		final String valeurMalRenseigne = "A";
		dto.setNature(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* nature mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU HORS NOMENCLATURE ***** */
		final String valeurHorsNomenclature = "10";
		dto.setNature(valeurHorsNomenclature);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* nature hors nomenclature avec '" + valeurHorsNomenclature + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "1";
		dto.setNature(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* nature renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderNature()._______________________________________

	

	/* 5 - classe. *******************************/
	/**
	 * teste la validation de classe 
	 * validerClasse(ISectionHitDTO, String, ErreursMaps).<br/>
	 * <ul>
	 * <li>garantit que l'interrupteur général attribut fonctionne.</li>
	 * <li>garantit que le SERVICE rafraichit les messages à chaque appel.</li>
	 * <li>garantit que la RG NON RENSEIGNE fonctionne.</li>
	 * <li>garantit que la RG REGEX fonctionne.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testValiderClasse() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderClasse() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setClasse("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* classe non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU MAL RENSEIGNE ***** */
		final String valeurMalRenseigne = "01";
		dto.setClasse(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* classe mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "00";
		dto.setClasse(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* classe renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderClasse()._______________________________________

	

	/* 6 - anneeTraitement. **************/
	/**
	 * teste la validation de l'attribut <code><b>anneeTraitement</b></code> 
	 * dans validerAnneeTraitement(ISectionHitDTO, String, ErreursMaps).<br/>
	 * <ul>
	 * <li>garantit que l'interrupteur général attribut fonctionne.</li>
	 * <li>garantit que le SERVICE rafraichit les messages à chaque appel.</li>
	 * <li>garantit que la RG NON RENSEIGNE fonctionne.</li>
	 * <li>garantit que la RG REGEX fonctionne.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testValiderAnneeTraitement() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderAnneeTraitement() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setAnneeTraitement("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* anneeTraitement non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU MAL RENSEIGNE ***** */
		final String valeurMalRenseigne = "1a3";
		dto.setAnneeTraitement(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* anneeTraitement mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "19";
		dto.setAnneeTraitement(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* anneeTraitement renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderAnneeTraitement()._______________________________________

	

	/**
	 * active toutes les RG.
	 * 
	 * @throws Exception 
	 */
	private void activerToutesRG() throws Exception {
		
		/* 1 - numDepartement. ***************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartement(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRegex02(true);
		
		/* 2 - numSection. *******************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSection(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRegex02(true);
		
		/* 3 - sens. *******************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSens(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensNomenclature03(true);
		
		/* 4 - nature. *******************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNature(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNatureRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNatureRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNatureNomenclature03(true);
		
		/* 5 - classe. *******************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasse(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseRegex02(true);
		
		/* 6 - anneeTraitement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeTraitement(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeTraitementRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeTraitementRegex02(true);
		
	} // Fin de activerToutesRG()._________________________________________
	

	
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
					SectionHitGestionnairePreferencesRG
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
	 * Exécuté avant tout test de la méthode.<br/>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		
		System.setProperty("%log4j.skipJansi", "false");
		
		dto = FactorySectionHit.getBonneSectionHitDTO();
		
	} // Fin de beforeClass()._____________________________________________
	
	
		
} // FIN DE LA CLASSE SectionHitValideurServiceTest.-------------------------
