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
		
		/* garantit que la RG HORS NOMENCLATURE fonctionne. */
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
		final boolean affichage = false;
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

	

	/* 7 - zoneLibre1. **************/
	/**
	 * teste la validation de l'attribut <code><b>zoneLibre1</b></code> 
	 * dans validerZoneLibre1(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderZoneLibre1() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderZoneLibre1() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setZoneLibre1("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* zoneLibre1 non renseigne *******");
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
		dto.setZoneLibre1(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* zoneLibre1 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		final String valeur = " ";
		dto.setZoneLibre1(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* zoneLibre1 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderZoneLibre1()._______________________________________

	

	/* 8 - numRoute. **************/
	/**
	 * teste la validation de l'attribut <code><b>numRoute</b></code> 
	 * dans validerNumRoute(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderNumRoute() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderNumRoute() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setNumRoute("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numRoute non renseigne *******");
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
		final String valeurMalRenseigne = "05B67";
		dto.setNumRoute(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numRoute mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		final String valeur = "0207";
		dto.setNumRoute(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numRoute renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderNumRoute()._______________________________________

	

	/* 9 - indiceNumRoute. **************/
	/**
	 * teste la validation de l'attribut <code><b>indiceNumRoute</b></code> 
	 * dans validerIndiceNumRoute(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderIndiceNumRoute() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderIndiceNumRoute() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setIndiceNumRoute("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* indiceNumRoute non renseigne *******");
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
		final String valeurMalRenseigne = "2A0";
		dto.setIndiceNumRoute(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* indiceNumRoute mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		final String valeur = "0";
		dto.setIndiceNumRoute(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* indiceNumRoute renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderIndiceNumRoute()._______________________________

	

	/* 10 - indiceLettreRoute. **************/
	/**
	 * teste la validation de l'attribut <code><b>indiceLettreRoute</b></code> 
	 * dans validerIndiceLettreRoute(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderIndiceLettreRoute() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderIndiceLettreRoute() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setIndiceLettreRoute("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* indiceLettreRoute non renseigne *******");
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
		final String valeurMalRenseigne = "6";
		dto.setIndiceLettreRoute(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* indiceLettreRoute mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		final String valeur = "B";
		dto.setIndiceLettreRoute(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* indiceLettreRoute renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderIndiceLettreRoute()._______________________________________

	

	/* 11 - categorieAdminRoute. **************/
	/**
	 * teste la validation de l'attribut <code><b>categorieAdminRoute</b></code> 
	 * dans validerCategorieAdminRoute(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderCategorieAdminRoute() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderCategorieAdminRoute() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setCategorieAdminRoute("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* categorieAdminRoute non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//***************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "A";
		dto.setCategorieAdminRoute(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* categorieAdminRoute mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU HORS NOMENCLATURE ***** */
		final String valeurHorsNomenclature = "18";
		dto.setCategorieAdminRoute(valeurHorsNomenclature);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* categorieAdminRoute hors nomenclature avec '" + valeurHorsNomenclature + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG HORS NOMENCLATURE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*************************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "1";
		dto.setCategorieAdminRoute(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* categorieAdminRoute renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderCategorieAdminRoute()._______________________________________

	

	/* 12 - typeComptage. **************/
	/**
	 * teste la validation de l'attribut <code><b>typeComptage</b></code> 
	 * dans validerTypeComptage(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderTypeComptage() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderTypeComptage() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setTypeComptage("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* typeComptage non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//***************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "A2";
		dto.setTypeComptage(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* typeComptage mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU HORS NOMENCLATURE ***** */
		final String valeurHorsNomenclature = "12";
		dto.setTypeComptage(valeurHorsNomenclature);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* typeComptage hors nomenclature avec '" + valeurHorsNomenclature + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG HORS NOMENCLATURE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*************************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "7";
		dto.setTypeComptage(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* typeComptage renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderTypeComptage()._________________________________

	

	/* 13 - classementRoute. **************/
	/**
	 * teste la validation de l'attribut <code><b>classementRoute</b></code> 
	 * dans validerClassementRoute(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderClassementRoute() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderClassementRoute() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setClassementRoute("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* classementRoute non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//***************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "C";
		dto.setClassementRoute(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* classementRoute mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU HORS NOMENCLATURE ***** */
		final String valeurHorsNomenclature = "7";
		dto.setClassementRoute(valeurHorsNomenclature);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* classementRoute hors nomenclature avec '" + valeurHorsNomenclature + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG HORS NOMENCLATURE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*************************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "4";
		dto.setClassementRoute(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* classementRoute renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderClassementRoute()._______________________________________

	

	/* 14 - classeLargeurChausseeU. **************/
	/**
	 * teste la validation de l'attribut <code><b>classeLargeurChausseeU</b></code> 
	 * dans validerClasseLargeurChausseeU(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderClasseLargeurChausseeU() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderClasseLargeurChausseeU() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setClasseLargeurChausseeU("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* classeLargeurChausseeU non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//***************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = " ";
		dto.setClasseLargeurChausseeU(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* classeLargeurChausseeU mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU HORS NOMENCLATURE ***** */
		final String valeurHorsNomenclature = "9";
		dto.setClasseLargeurChausseeU(valeurHorsNomenclature);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* classeLargeurChausseeU hors nomenclature avec '" + valeurHorsNomenclature + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG HORS NOMENCLATURE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*************************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "0";
		dto.setClasseLargeurChausseeU(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* classeLargeurChausseeU renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderClasseLargeurChausseeU()._______________________

	

	/* 15 - classeLargeurChausseesS. **************/
	/**
	 * teste la validation de l'attribut <code><b>classeLargeurChausseesS</b></code> 
	 * dans validerClasseLargeurChausseesS(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderClasseLargeurChausseesS() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderClasseLargeurChausseesS() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setClasseLargeurChausseesS("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* classeLargeurChausseesS non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//***************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "A4";
		dto.setClasseLargeurChausseesS(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* classeLargeurChausseesS mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU HORS NOMENCLATURE ***** */
		final String valeurHorsNomenclature = "9";
		dto.setClasseLargeurChausseesS(valeurHorsNomenclature);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* classeLargeurChausseesS hors nomenclature avec '" + valeurHorsNomenclature + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG HORS NOMENCLATURE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*************************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "2";
		dto.setClasseLargeurChausseesS(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* classeLargeurChausseesS renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderClasseLargeurChausseesS().______________________

	

	/* 16 - typeReseau. **************/
	/**
	 * teste la validation de l'attribut <code><b>typeReseau</b></code> 
	 * dans validerTypeReseau(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderTypeReseau() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderTypeReseau() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setTypeReseau("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* typeReseau non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//***************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "5A";
		dto.setTypeReseau(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* typeReseau mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU HORS NOMENCLATURE ***** */
		final String valeurHorsNomenclature = "8";
		dto.setTypeReseau(valeurHorsNomenclature);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* typeReseau hors nomenclature avec '" + valeurHorsNomenclature + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG HORS NOMENCLATURE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*************************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "0";
		dto.setTypeReseau(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* typeReseau renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderTypeReseau().___________________________________

	

	/* 17 - pRoupK. **************/
	/**
	 * teste la validation de l'attribut <code><b>pRoupK</b></code> 
	 * dans validerPRoupK(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPRoupK() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPRoupK() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPRoupK("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pRoupK non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//***************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "M7";
		dto.setPRoupK(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pRoupK mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU HORS NOMENCLATURE ***** */
		final String valeurHorsNomenclature = "0";
		dto.setPRoupK(valeurHorsNomenclature);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pRoupK hors nomenclature avec '" + valeurHorsNomenclature + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG HORS NOMENCLATURE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*************************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "1";
		dto.setPRoupK(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pRoupK renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPRoupK()._______________________________________
	

		
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
		
		/* 7 - zoneLibre1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre1(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre1Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre1Regex02(true);
		
		/* 8 - numRoute. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumRoute(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumRouteRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumRouteRegex02(true);
		
		/* 9 - indiceNumRoute. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceNumRoute(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceNumRouteRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceNumRouteRegex02(true);
		
		/* 10 - indiceLettreRoute. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceLettreRoute(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceLettreRouteRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceLettreRouteRegex02(true);
		
		/* 11 - categorieAdminRoute. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitCategorieAdminRoute(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitCategorieAdminRouteRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitCategorieAdminRouteRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitCategorieAdminRouteNomenclature03(true);
		
		/* 12 - typeComptage. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptage(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNomenclature03(true);
		
		/* 13 - classementRoute. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClassementRoute(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClassementRouteRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClassementRouteRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClassementRouteNomenclature03(true);
		
		/* 14 - classeLargeurChausseeU. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseeU(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseeURenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseeURegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseeUNomenclature03(true);
		
		/* 15 - classeLargeurChausseesS. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseesS(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseesSRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseesSRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseesSNomenclature03(true);
		
		/* 16 - typeReseau. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeReseau(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeReseauRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeReseauRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeReseauNomenclature03(true);
		
		/* 17 - pRoupK. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPRoupK(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPRoupKRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPRoupKRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPRoupKNomenclature03(true);

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
