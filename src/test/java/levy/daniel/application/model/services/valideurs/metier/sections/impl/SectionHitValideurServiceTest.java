package levy.daniel.application.model.services.valideurs.metier.sections.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
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

	

	/* 18 - lieuDitOrigine. **************/
	/**
	 * teste la validation de l'attribut <code><b>lieuDitOrigine</b></code> 
	 * dans validerLieuDitOrigine(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderLieuDitOrigine() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderLieuDitOrigine() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setLieuDitOrigine("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* lieuDitOrigine non renseigne *******");
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
		final String valeurMalRenseigne = "aabbcc";
		dto.setLieuDitOrigine(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* lieuDitOrigine mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		final String valeur = "lieu-dit origine    ";
		dto.setLieuDitOrigine(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* lieuDitOrigine renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderLieuDitOrigine()._______________________________

	

	/* 19 - prOrigine. **************/
	/**
	 * teste la validation de l'attribut <code><b>prOrigine</b></code> 
	 * dans validerPrOrigine(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPrOrigine() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPrOrigine() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPrOrigine("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* prOrigine non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "0a3";
		dto.setPrOrigine(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* prOrigine mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "1 8";
		dto.setPrOrigine(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* prOrigine non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "018";
		dto.setPrOrigine(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* prOrigine renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPrOrigine().____________________________________

	

	/* 20 - absOrigine. **************/
	/**
	 * teste la validation de l'attribut <code><b>absOrigine</b></code> 
	 * dans validerAbsOrigine(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderAbsOrigine() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderAbsOrigine() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setAbsOrigine("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* absOrigine non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "0A25";
		dto.setAbsOrigine(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* absOrigine mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "1 25";
		dto.setAbsOrigine(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* absOrigine non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "0001";
		dto.setAbsOrigine(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* absOrigine renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderAbsOrigine().___________________________________

	

	/* 21 - lieuDitExtremite. **************/
	/**
	 * teste la validation de l'attribut <code><b>lieuDitExtremite</b></code> 
	 * dans validerLieuDitExtremite(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderLieuDitExtremite() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderLieuDitExtremite() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setLieuDitExtremite("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* lieuDitExtremite non renseigne *******");
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
		final String valeurMalRenseigne = "aabbcc";
		dto.setLieuDitExtremite(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* lieuDitExtremite mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		final String valeur = "lieu-dit extremité  ";
		dto.setLieuDitExtremite(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* lieuDitExtremite renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderLieuDitExtremite()._____________________________

	

	/* 22 - prExtremite. **************/
	/**
	 * teste la validation de l'attribut <code><b>prExtremite</b></code> 
	 * dans validerPrExtremite(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPrExtremite() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPrExtremite() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPrExtremite("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* prExtremite non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "0a3";
		dto.setPrExtremite(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* prExtremite mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "1 8";
		dto.setPrExtremite(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* prExtremite non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "018";
		dto.setPrExtremite(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* prExtremite renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPrExtremite().____________________________________

	

	/* 22 - absExtremite. **************/
	/**
	 * teste la validation de l'attribut <code><b>absExtremite</b></code> 
	 * dans validerAbsExtremite(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderAbsExtremite() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderAbsExtremite() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setAbsExtremite("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* absExtremite non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "0A25";
		dto.setAbsExtremite(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* absExtremite mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "1 25";
		dto.setAbsExtremite(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* absExtremite non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "0001";
		dto.setAbsExtremite(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* absExtremite renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderAbsExtremite().___________________________________

	

	/* 24 - lieuDitComptage. **************/
	/**
	 * teste la validation de l'attribut <code><b>lieuDitComptage</b></code> 
	 * dans validerLieuDitComptage(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderLieuDitComptage() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderLieuDitComptage() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setLieuDitComptage("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* lieuDitComptage non renseigne *******");
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
		final String valeurMalRenseigne = "aabbcc";
		dto.setLieuDitComptage(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* lieuDitComptage mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		final String valeur = "lieu-dit comptage   ";
		dto.setLieuDitComptage(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* lieuDitComptage renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderLieuDitComptage().______________________________

	

	/* 25 - prComptage. **************/
	/**
	 * teste la validation de l'attribut <code><b>prComptage</b></code> 
	 * dans validerPrComptage(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPrComptage() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPrComptage() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPrComptage("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* prComptage non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "0a3";
		dto.setPrComptage(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* prComptage mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "1 8";
		dto.setPrComptage(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* prComptage non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "018";
		dto.setPrComptage(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* prComptage renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPrComptage().___________________________________

	

	/* 26 - absComptage. **************/
	/**
	 * teste la validation de l'attribut <code><b>absComptage</b></code> 
	 * dans validerAbsComptage(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderAbsComptage() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderAbsComptage() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setAbsComptage("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* absComptage non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "0A25";
		dto.setAbsComptage(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* absComptage mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "1 25";
		dto.setAbsComptage(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* absComptage non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "0001";
		dto.setAbsComptage(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* absComptage renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderAbsComptage().__________________________________

	

	/* 27 - longueurSection. **************/
	/**
	 * teste la validation de l'attribut <code><b>longueurSection</b></code> 
	 * dans validerLongueurSection(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderLongueurSection() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderLongueurSection() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setLongueurSection("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* longueurSection non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "63k566";
		dto.setLongueurSection(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* longueurSection mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "63 654";
		dto.setLongueurSection(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* longueurSection non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "000623";
		dto.setLongueurSection(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* longueurSection renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderLongueurSection()._______________________________________	

	

	/* 28 - longueurRaseCampagne. **************/
	/**
	 * teste la validation de l'attribut <code><b>longueurRaseCampagne</b></code> 
	 * dans validerLongueurRaseCampagne(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderLongueurRaseCampagne() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderLongueurRaseCampagne() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setLongueurRaseCampagne("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* longueurRaseCampagne non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "abcdef";
		dto.setLongueurRaseCampagne(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* longueurRaseCampagne mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "01 563";
		dto.setLongueurRaseCampagne(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* longueurRaseCampagne non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "000632";
		dto.setLongueurRaseCampagne(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* longueurRaseCampagne renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderLongueurRaseCampagne()._______________________________________

	

	/* 29 - numDepartementRattachement. **************/
	/**
	 * teste la validation de l'attribut <code><b>numDepartementRattachement</b></code> 
	 * dans validerNumDepartementRattachement(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderNumDepartementRattachement() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderNumDepartementRattachement() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setNumDepartementRattachement("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numDepartementRattachement non renseigne *******");
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
		final String valeurMalRenseigne = "21A";
		dto.setNumDepartementRattachement(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numDepartementRattachement mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		final String valeur = "730";
		dto.setNumDepartementRattachement(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numDepartementRattachement renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderNumDepartementRattachement()._______________________________________

	

	/* 30 - numSectionRattachement. **************/
	/**
	 * teste la validation de l'attribut <code><b>numSectionRattachement</b></code> 
	 * dans validerNumSectionRattachement(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderNumSectionRattachement() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderNumSectionRattachement() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setNumSectionRattachement("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numSectionRattachement non renseigne *******");
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
		final String valeurMalRenseigne = "00A564C";
		dto.setNumSectionRattachement(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numSectionRattachement mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		final String valeur = "000402";
		dto.setNumSectionRattachement(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numSectionRattachement renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderNumSectionRattachement()._______________________

	

	/* 31 - sensRattachement. **************/
	/**
	 * teste la validation de l'attribut <code><b>sensRattachement</b></code> 
	 * dans validerSensRattachement(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderSensRattachement() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderSensRattachement() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setSensRattachement("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* sensRattachement non renseigne *******");
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
		dto.setSensRattachement(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* sensRattachement mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		dto.setSensRattachement(valeurHorsNomenclature);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* sensRattachement hors nomenclature avec '" + valeurHorsNomenclature + "'  **********");
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
		final String valeur = "3";
		dto.setSensRattachement(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* sensRattachement renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderSensRattachement()._____________________________

	

	/* 32 - numDepartementLimitrophe. **************/
	/**
	 * teste la validation de l'attribut <code><b>numDepartementLimitrophe</b></code> 
	 * dans validerNumDepartementLimitrophe(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderNumDepartementLimitrophe() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderNumDepartementLimitrophe() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setNumDepartementLimitrophe("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numDepartementLimitrophe non renseigne *******");
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
		final String valeurMalRenseigne = "21A";
		dto.setNumDepartementLimitrophe(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numDepartementLimitrophe mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		final String valeur = "730";
		dto.setNumDepartementLimitrophe(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numDepartementLimitrophe renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderNumDepartementLimitrophe()._____________________

	

	/* 33 - numSectionLimitrophe. **************/
	/**
	 * teste la validation de l'attribut <code><b>numSectionLimitrophe</b></code> 
	 * dans validerNumSectionLimitrophe(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderNumSectionLimitrophe() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderNumSectionLimitrophe() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setNumSectionLimitrophe("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numSectionLimitrophe non renseigne *******");
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
		final String valeurMalRenseigne = "00A564C";
		dto.setNumSectionLimitrophe(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numSectionLimitrophe mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		final String valeur = "000402";
		dto.setNumSectionLimitrophe(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* numSectionLimitrophe renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderNumSectionLimitrophe()._________________________

	

	/* 34 - sensLimitrophe. **************/
	/**
	 * teste la validation de l'attribut <code><b>sensLimitrophe</b></code> 
	 * dans validerSensLimitrophe(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderSensLimitrophe() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderSensLimitrophe() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setSensLimitrophe("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* sensLimitrophe non renseigne *******");
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
		dto.setSensLimitrophe(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* sensLimitrophe mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		dto.setSensLimitrophe(valeurHorsNomenclature);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* sensLimitrophe hors nomenclature avec '" + valeurHorsNomenclature + "'  **********");
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
		final String valeur = "3";
		dto.setSensLimitrophe(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* sensLimitrophe renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderSensLimitrophe()._______________________________

	

	/* 35 - moisSectionnement. **************/
	/**
	 * teste la validation de l'attribut <code><b>moisSectionnement</b></code> 
	 * dans validerMoisSectionnement(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderMoisSectionnement() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderMoisSectionnement() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setMoisSectionnement("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* moisSectionnement non renseigne *******");
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
		final String valeurMalRenseigne = "13";
		dto.setMoisSectionnement(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* moisSectionnement mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		final String valeur = "12";
		dto.setMoisSectionnement(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* moisSectionnement renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderMoisSectionnement().____________________________

	

	/* 36 - anneeSectionnement. **************/
	/**
	 * teste la validation de l'attribut <code><b>anneeSectionnement</b></code> 
	 * dans validerAnneeSectionnement(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderAnneeSectionnement() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderAnneeSectionnement() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setAnneeSectionnement("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* anneeSectionnement non renseigne *******");
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
		final String valeurMalRenseigne = "1A3";
		dto.setAnneeSectionnement(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* anneeSectionnement mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		dto.setAnneeSectionnement(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* anneeSectionnement renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderAnneeSectionnement().___________________________	

	

	/* 37 - zoneLibre2. **************/
	/**
	 * teste la validation de l'attribut <code><b>zoneLibre2</b></code> 
	 * dans validerZoneLibre2(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderZoneLibre2() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderZoneLibre2() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setZoneLibre2("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* zoneLibre2 non renseigne *******");
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
		final String valeurMalRenseigne = "  6   ";
		dto.setZoneLibre2(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* zoneLibre2 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		final String valeur = "      ";
		dto.setZoneLibre2(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* zoneLibre2 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderZoneLibre2().___________________________________

	

	/* 38 - mjaN. **************/
	/**
	 * teste la validation de l'attribut <code><b>mjaN</b></code> 
	 * dans validerMjaN(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderMjaN() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderMjaN() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setMjaN("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjaN non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "012A23";
		dto.setMjaN(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjaN mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "012 23";
		dto.setMjaN(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjaN non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "012036";
		dto.setMjaN(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjaN renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderMjaN()._______________________________________

	

	/* 39 - modeCalculN. **************/
	/**
	 * teste la validation de l'attribut <code><b>modeCalculN</b></code> 
	 * dans validerModeCalculN(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderModeCalculN() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderModeCalculN() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setModeCalculN("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* modeCalculN non renseigne *******");
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
		final String valeurMalRenseigne = "-";
		dto.setModeCalculN(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* modeCalculN mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		dto.setModeCalculN(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* modeCalculN renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderModeCalculN().__________________________________

	

	/* 40 - pcPLN. **************/
	/**
	 * teste la validation de l'attribut <code><b>pcPLN</b></code> 
	 * dans validerPcPLN(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPcPLN() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPcPLN() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPcPLN("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcPLN non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = " 23";
		dto.setPcPLN(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcPLN mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "7 9";
		dto.setPcPLN(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcPLN non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "079";
		dto.setPcPLN(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcPLN renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPcPLN()._______________________________________	

	

	/* 41 - evaluationPLN. **************/
	/**
	 * teste la validation de l'attribut <code><b>evaluationPLN</b></code> 
	 * dans validerEvaluationPLN(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderEvaluationPLN() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderEvaluationPLN() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setEvaluationPLN("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* evaluationPLN non renseigne *******");
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
		final String valeurMalRenseigne = "-";
		dto.setEvaluationPLN(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* evaluationPLN mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		dto.setEvaluationPLN(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* evaluationPLN renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderEvaluationPLN().__________________________________

	

	/* 42 - pcNuitAnnuelN. **************/
	/**
	 * teste la validation de l'attribut <code><b>pcNuitAnnuelN</b></code> 
	 * dans validerPcNuitAnnuelN(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPcNuitAnnuelN() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPcNuitAnnuelN() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPcNuitAnnuelN("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitAnnuelN non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = " 23";
		dto.setPcNuitAnnuelN(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitAnnuelN mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "7 9";
		dto.setPcNuitAnnuelN(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitAnnuelN non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "079";
		dto.setPcNuitAnnuelN(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitAnnuelN renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPcNuitAnnuelN().________________________________

	

	/* 43 - indiceFiabiliteMjaN. **************/
	/**
	 * teste la validation de l'attribut <code><b>indiceFiabiliteMjaN</b></code> 
	 * dans validerIndiceFiabiliteMjaN(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderIndiceFiabiliteMjaN() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderIndiceFiabiliteMjaN() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setIndiceFiabiliteMjaN("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* indiceFiabiliteMjaN non renseigne *******");
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
		final String valeurMalRenseigne = "-";
		dto.setIndiceFiabiliteMjaN(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* indiceFiabiliteMjaN mal renseigne avec '" + valeurMalRenseigne + "'  **********");
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
		dto.setIndiceFiabiliteMjaN(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* indiceFiabiliteMjaN renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderIndiceFiabiliteMjaN().__________________________

	

	/* 44 - mjmNmois01. **************/
	/**
	 * teste la validation de l'attribut <code><b>mjmNmois01</b></code> 
	 * dans validerMjmNmois01(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderMjmNmois01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderMjmNmois01() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setMjmNmois01("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois01 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "00A63 8";
		dto.setMjmNmois01(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois01 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = " 2 023";
		dto.setMjmNmois01(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois01 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "010631";
		dto.setMjmNmois01(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois01 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderMjmNmois01().___________________________________

	

	/* 45 - pcNuitNmois01. **************/
	/**
	 * teste la validation de l'attribut <code><b>pcNuitNmois01</b></code> 
	 * dans validerPcNuitNmois01(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPcNuitNmois01() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPcNuitNmois01() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPcNuitNmois01("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois01 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "05A";
		dto.setPcNuitNmois01(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois01 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "5 4";
		dto.setPcNuitNmois01(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois01 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "054";
		dto.setPcNuitNmois01(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois01 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPcNuitNmois01()._______________________________________

	

	/* 46 - mjmNmois02. **************/
	/**
	 * teste la validation de l'attribut <code><b>mjmNmois02</b></code> 
	 * dans validerMjmNmois02(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderMjmNmois02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderMjmNmois02() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setMjmNmois02("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois02 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "00A63 8";
		dto.setMjmNmois02(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois02 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = " 2 023";
		dto.setMjmNmois02(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois02 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "010631";
		dto.setMjmNmois02(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois02 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderMjmNmois02().___________________________________

	

	/* 47 - pcNuitNmois02. **************/
	/**
	 * teste la validation de l'attribut <code><b>pcNuitNmois02</b></code> 
	 * dans validerPcNuitNmois02(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPcNuitNmois02() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPcNuitNmois02() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPcNuitNmois02("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois02 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "05A";
		dto.setPcNuitNmois02(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois02 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "5 4";
		dto.setPcNuitNmois02(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois02 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "054";
		dto.setPcNuitNmois02(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois02 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPcNuitNmois02()._______________________________________

	

	/* 48 - mjmNmois03. **************/
	/**
	 * teste la validation de l'attribut <code><b>mjmNmois03</b></code> 
	 * dans validerMjmNmois03(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderMjmNmois03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderMjmNmois03() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setMjmNmois03("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois03 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "00A63 8";
		dto.setMjmNmois03(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois03 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = " 2 023";
		dto.setMjmNmois03(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois03 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "010631";
		dto.setMjmNmois03(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois03 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderMjmNmois03().___________________________________

	

	/* 49 - pcNuitNmois03. **************/
	/**
	 * teste la validation de l'attribut <code><b>pcNuitNmois03</b></code> 
	 * dans validerPcNuitNmois03(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPcNuitNmois03() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPcNuitNmois03() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPcNuitNmois03("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois03 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "05A";
		dto.setPcNuitNmois03(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois03 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "5 4";
		dto.setPcNuitNmois03(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois03 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "054";
		dto.setPcNuitNmois03(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois03 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPcNuitNmois03()._______________________________________

	

	/* 50 - mjmNmois04. **************/
	/**
	 * teste la validation de l'attribut <code><b>mjmNmois04</b></code> 
	 * dans validerMjmNmois04(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderMjmNmois04() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderMjmNmois04() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setMjmNmois04("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois04 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "00A63 8";
		dto.setMjmNmois04(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois04 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = " 2 023";
		dto.setMjmNmois04(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois04 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "010631";
		dto.setMjmNmois04(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois04 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderMjmNmois04().___________________________________

	

	/* 51 - pcNuitNmois04. **************/
	/**
	 * teste la validation de l'attribut <code><b>pcNuitNmois04</b></code> 
	 * dans validerPcNuitNmois04(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPcNuitNmois04() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPcNuitNmois04() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPcNuitNmois04("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois04 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "05A";
		dto.setPcNuitNmois04(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois04 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "5 4";
		dto.setPcNuitNmois04(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois04 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "054";
		dto.setPcNuitNmois04(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois04 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPcNuitNmois04()._______________________________________


	

	/* 52 - mjmNmois05. **************/
	/**
	 * teste la validation de l'attribut <code><b>mjmNmois05</b></code> 
	 * dans validerMjmNmois05(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderMjmNmois05() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderMjmNmois05() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setMjmNmois05("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois05 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "00A63 8";
		dto.setMjmNmois05(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois05 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = " 2 023";
		dto.setMjmNmois05(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois05 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "010631";
		dto.setMjmNmois05(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois05 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderMjmNmois05().___________________________________

	

	/* 53 - pcNuitNmois05. **************/
	/**
	 * teste la validation de l'attribut <code><b>pcNuitNmois05</b></code> 
	 * dans validerPcNuitNmois05(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPcNuitNmois05() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPcNuitNmois05() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPcNuitNmois05("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois05 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "05A";
		dto.setPcNuitNmois05(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois05 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "5 4";
		dto.setPcNuitNmois05(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois05 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "054";
		dto.setPcNuitNmois05(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois05 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPcNuitNmois05()._______________________________________


	

	/* 54 - mjmNmois06. **************/
	/**
	 * teste la validation de l'attribut <code><b>mjmNmois06</b></code> 
	 * dans validerMjmNmois06(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderMjmNmois06() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderMjmNmois06() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setMjmNmois06("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois06 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "00A63 8";
		dto.setMjmNmois06(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois06 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = " 2 023";
		dto.setMjmNmois06(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois06 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "010631";
		dto.setMjmNmois06(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois06 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderMjmNmois06().___________________________________

	

	/* 55 - pcNuitNmois06. **************/
	/**
	 * teste la validation de l'attribut <code><b>pcNuitNmois06</b></code> 
	 * dans validerPcNuitNmois06(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPcNuitNmois06() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPcNuitNmois06() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPcNuitNmois06("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois06 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "05A";
		dto.setPcNuitNmois06(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois06 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "5 4";
		dto.setPcNuitNmois06(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois06 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "054";
		dto.setPcNuitNmois06(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois06 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPcNuitNmois06()._______________________________________


	

	/* 56 - mjmNmois07. **************/
	/**
	 * teste la validation de l'attribut <code><b>mjmNmois07</b></code> 
	 * dans validerMjmNmois07(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderMjmNmois07() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderMjmNmois07() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setMjmNmois07("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois07 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "00A63 8";
		dto.setMjmNmois07(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois07 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = " 2 023";
		dto.setMjmNmois07(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois07 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "010631";
		dto.setMjmNmois07(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois07 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderMjmNmois07().___________________________________

	

	/* 57 - pcNuitNmois07. **************/
	/**
	 * teste la validation de l'attribut <code><b>pcNuitNmois07</b></code> 
	 * dans validerPcNuitNmois07(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPcNuitNmois07() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPcNuitNmois07() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPcNuitNmois07("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois07 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "05A";
		dto.setPcNuitNmois07(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois07 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "5 4";
		dto.setPcNuitNmois07(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois07 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "054";
		dto.setPcNuitNmois07(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois07 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPcNuitNmois07()._______________________________________


	

	/* 58 - mjmNmois08. **************/
	/**
	 * teste la validation de l'attribut <code><b>mjmNmois08</b></code> 
	 * dans validerMjmNmois08(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderMjmNmois08() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderMjmNmois08() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setMjmNmois08("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois08 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "00A63 8";
		dto.setMjmNmois08(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois08 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = " 2 023";
		dto.setMjmNmois08(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois08 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "010631";
		dto.setMjmNmois08(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois08 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderMjmNmois08().___________________________________

	

	/* 59 - pcNuitNmois08. **************/
	/**
	 * teste la validation de l'attribut <code><b>pcNuitNmois08</b></code> 
	 * dans validerPcNuitNmois08(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPcNuitNmois08() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPcNuitNmois08() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPcNuitNmois08("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois08 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "05A";
		dto.setPcNuitNmois08(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois08 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "5 4";
		dto.setPcNuitNmois08(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois08 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "054";
		dto.setPcNuitNmois08(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois08 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPcNuitNmois08()._______________________________________


	

	/* 60 - mjmNmois09. **************/
	/**
	 * teste la validation de l'attribut <code><b>mjmNmois09</b></code> 
	 * dans validerMjmNmois09(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderMjmNmois09() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderMjmNmois09() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setMjmNmois09("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois09 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "00A63 8";
		dto.setMjmNmois09(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois09 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = " 2 023";
		dto.setMjmNmois09(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois09 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "010631";
		dto.setMjmNmois09(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois09 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderMjmNmois09().___________________________________

	

	/* 61 - pcNuitNmois09. **************/
	/**
	 * teste la validation de l'attribut <code><b>pcNuitNmois09</b></code> 
	 * dans validerPcNuitNmois09(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPcNuitNmois09() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPcNuitNmois09() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPcNuitNmois09("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois09 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "05A";
		dto.setPcNuitNmois09(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois09 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "5 4";
		dto.setPcNuitNmois09(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois09 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "054";
		dto.setPcNuitNmois09(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois09 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPcNuitNmois09()._______________________________________


	

	/* 62 - mjmNmois10. **************/
	/**
	 * teste la validation de l'attribut <code><b>mjmNmois10</b></code> 
	 * dans validerMjmNmois10(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderMjmNmois10() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderMjmNmois10() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setMjmNmois10("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois10 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "00A63 8";
		dto.setMjmNmois10(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois10 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = " 2 023";
		dto.setMjmNmois10(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois10 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "010631";
		dto.setMjmNmois10(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois10 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderMjmNmois10().___________________________________

	

	/* 63 - pcNuitNmois10. **************/
	/**
	 * teste la validation de l'attribut <code><b>pcNuitNmois10</b></code> 
	 * dans validerPcNuitNmois10(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPcNuitNmois10() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPcNuitNmois10() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPcNuitNmois10("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois10 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "05A";
		dto.setPcNuitNmois10(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois10 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "5 4";
		dto.setPcNuitNmois10(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois10 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "054";
		dto.setPcNuitNmois10(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois10 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPcNuitNmois10()._______________________________________


	

	/* 64 - mjmNmois11. **************/
	/**
	 * teste la validation de l'attribut <code><b>mjmNmois11</b></code> 
	 * dans validerMjmNmois11(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderMjmNmois11() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderMjmNmois11() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setMjmNmois11("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois11 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "00A63 8";
		dto.setMjmNmois11(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois11 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = " 2 023";
		dto.setMjmNmois11(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois11 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "010631";
		dto.setMjmNmois11(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois11 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderMjmNmois11().___________________________________

	

	/* 65 - pcNuitNmois11. **************/
	/**
	 * teste la validation de l'attribut <code><b>pcNuitNmois11</b></code> 
	 * dans validerPcNuitNmois11(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPcNuitNmois11() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPcNuitNmois11() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPcNuitNmois11("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois11 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "05A";
		dto.setPcNuitNmois11(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois11 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "5 4";
		dto.setPcNuitNmois11(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois11 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "054";
		dto.setPcNuitNmois11(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois11 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPcNuitNmois11()._______________________________________


	

	/* 66 - mjmNmois12. **************/
	/**
	 * teste la validation de l'attribut <code><b>mjmNmois12</b></code> 
	 * dans validerMjmNmois12(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderMjmNmois12() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderMjmNmois12() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setMjmNmois12("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois12 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "00A63 8";
		dto.setMjmNmois12(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois12 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = " 2 023";
		dto.setMjmNmois12(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois12 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "010631";
		dto.setMjmNmois12(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* mjmNmois12 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderMjmNmois12().___________________________________

	

	/* 67 - pcNuitNmois12. **************/
	/**
	 * teste la validation de l'attribut <code><b>pcNuitNmois12</b></code> 
	 * dans validerPcNuitNmois12(ISectionHitDTO, String, ErreursMaps).<br/>
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
	public void testValiderPcNuitNmois12() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testValiderPcNuitNmois12() ********** ");
		}
		
		/* active toutes les RG. */
		this.activerToutesRG();
		
		/* AFFICHAGE A LA CONSOLE. */
		/* affiche les préférences. */
		this.afficherPreferences(affichage);
		
		ErreursMaps erreurMaps = null;

		//*********************************
		/* TEST DU NON RENSEIGNE ***** */
		dto.setPcNuitNmois12("");
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois12 non renseigne *******");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG NON RENSEIGNE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//**************************************
		/* TEST DU MAL RENSEIGNE (REGEX) ***** */
		final String valeurMalRenseigne = "05A";
		dto.setPcNuitNmois12(valeurMalRenseigne);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois12 mal renseigne avec '" + valeurMalRenseigne + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE (REGEX) fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*****************************************
		/* TEST DU MAL RENSEIGNE (NUMERIQUE)***** */
		final String valeurNonNumerique = "5 4";
		dto.setPcNuitNmois12(valeurNonNumerique);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois12 non numérique avec '" + valeurNonNumerique + "'  **********");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que la RG MAL RENSEIGNE NUMERIQUE fonctionne. */
		assertFalse("ErrorsMap ne doit pas être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertFalse("ErrorsMapDetaille ne doit pas être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());

		//*********************************
		/* TEST DU BIEN RENSEIGNE. ********* */
		final String valeur = "054";
		dto.setPcNuitNmois12(valeur);
		
		// VALIDATION PAR LE SERVICE.
		erreurMaps = SERVICE.valider(dto);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("******* pcNuitNmois12 renseigne avec '" + valeur + "' ***************");
			System.out.println("ErrorsMap : \n" + erreurMaps.afficherErrorsMap());
			System.out.println("ErrorsMapDetaille : \n" + erreurMaps.afficherErrorsMapDetaille());
		}
		
		/* garantit que le SERVICE rafraichit les messages à chaque appel. */
		assertTrue("ErrorsMap doit être vide : "
				, erreurMaps.getErrorsMap().isEmpty());
		assertTrue("ErrorsMapDetaille doit être vide : "
				, erreurMaps.getErrorsMapDetaille().isEmpty());
		
	} // Fin de testValiderPcNuitNmois12()._______________________________________

	
		
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
		
		/* 18 - lieuDitOrigine. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitOrigine(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitOrigineRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitOrigineRegex02(true);
		
		/* 19 - prOrigine. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrOrigine(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrOrigineRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrOrigineRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrOrigineNumerique03(true);
		
		/* 20 - absOrigine. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsOrigine(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsOrigineRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsOrigineRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsOrigineNumerique03(true);
		
		/* 21 - lieuDitExtremite. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitExtremite(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitExtremiteRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitExtremiteRegex02(true);
		
		/* 22 - prExtremite. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrExtremite(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrExtremiteRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrExtremiteRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrExtremiteNumerique03(true);
		
		/* 23 - absExtremite. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsExtremite(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsExtremiteRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsExtremiteRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsExtremiteNumerique03(true);
		
		/* 24 - lieuDitComptage. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitComptage(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitComptageRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitComptageRegex02(true);
		
		/* 25 - prComptage. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrComptage(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrComptageRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrComptageRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrComptageNumerique03(true);
		
		/* 26 - absComptage. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsComptage(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsComptageRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsComptageRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsComptageNumerique03(true);
		
		/* 28 - longueurRaseCampagne. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLongueurRaseCampagne(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLongueurRaseCampagneRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLongueurRaseCampagneRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLongueurRaseCampagneNumerique03(true);
		
		/* 29 - numDepartementRattachement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRattachement(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRattachementRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRattachementRegex02(true);
		
		/* 30 - numSectionRattachement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRattachement(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRattachementRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRattachementRegex02(true);
		
		/* 31 - sensRattachement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRattachement(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRattachementRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRattachementRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRattachementNomenclature03(true);
		
		/* 32 - numDepartementLimitrophe. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementLimitrophe(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementLimitropheRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementLimitropheRegex02(true);
		
		/* 33 - numSectionLimitrophe. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionLimitrophe(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionLimitropheRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionLimitropheRegex02(true);
		
		/* 34 - sensLimitrophe. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensLimitrophe(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensLimitropheRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensLimitropheRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensLimitropheNomenclature03(true);
		
		/* 35 - moisSectionnement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMoisSectionnement(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMoisSectionnementRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMoisSectionnementRegex02(true);
		
		/* 36 - anneeSectionnement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeSectionnement(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeSectionnementRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeSectionnementRegex02(true);
		
		/* 37 - zoneLibre2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre2(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre2Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre2Regex02(true);
		
		/* 38 - mjaN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaN(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNNumerique03(true);
		
		/* 39 - modeCalculN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculN(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNRegex02(true);
		
		/* 40 - pcPLN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLN(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNNumerique03(true);
		
		/* 41 - evaluationPLN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLN(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNRegex02(true);
		
		/* 42 - pcNuitAnnuelN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelN(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNNumerique03(true);
		
		/* 43 - indiceFiabiliteMjaN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaN(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNRegex02(true);
		
		/* 44 - mjmNmois01. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois01Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois01Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois01Numerique03(true);
		
		/* 45 - pcNuitNmois01. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois01Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois01Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois01Numerique03(true);
		
		/* 46 - mjmNmois02. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois02Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois02Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois02Numerique03(true);
		
		/* 47 - pcNuitNmois02. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois02Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois02Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois02Numerique03(true);
		
		/* 48 - mjmNmois03. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois03(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois03Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois03Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois03Numerique03(true);
		
		/* 49 - pcNuitNmois03. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois03(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois03Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois03Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois03Numerique03(true);
		
		/* 50 - mjmNmois04. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois04(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois04Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois04Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois04Numerique03(true);
		
		/* 51 - pcNuitNmois04. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois04(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois04Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois04Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois04Numerique03(true);
		
		/* 52 - mjmNmois05. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois05(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois05Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois05Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois05Numerique03(true);
		
		/* 53 - pcNuitNmois05. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois05(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois05Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois05Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois05Numerique03(true);
		
		/* 54 - mjmNmois06. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois06(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois06Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois06Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois06Numerique03(true);
		
		/* 55 - pcNuitNmois06. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois06(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois06Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois06Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois06Numerique03(true);
		
		/* 56 - mjmNmois07. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois07(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois07Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois07Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois07Numerique03(true);
		
		/* 57 - pcNuitNmois07. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois07(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois07Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois07Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois07Numerique03(true);
		
		/* 58 - mjmNmois08. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois08(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois08Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois08Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois08Numerique03(true);
		
		/* 59 - pcNuitNmois08. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois08(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois08Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois08Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois08Numerique03(true);
		
		/* 60 - mjmNmois09. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois09(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois09Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois09Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois09Numerique03(true);
		
		/* 61 - pcNuitNmois09. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois09(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois09Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois09Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois09Numerique03(true);
		
		/* 62 - mjmNmois10. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois10(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois10Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois10Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois10Numerique03(true);
		
		/* 63 - pcNuitNmois10. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois10(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois10Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois10Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois10Numerique03(true);
		
		/* 64 - mjmNmois11. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois11(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois11Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois11Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois11Numerique03(true);
		
		/* 65 - pcNuitNmois11. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois11(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois11Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois11Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois11Numerique03(true);
		
		/* 66 - mjmNmois12. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois12(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois12Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois12Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois12Numerique03(true);
		
		/* 67 - pcNuitNmois12. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois12(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois12Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois12Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois12Numerique03(true);

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
	 * retire les éventuels zéros à gauche de pString.<br/>
	 * <br/>
	 * - retourne null si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String : String avec éventuellement des zéros à gauche.
	 * 
	 * @return : String : String sans les zéros à gauche.<br/>
	 */
	private static String retirerZerosAGauche(final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		String resultat = pString;
		
		while (StringUtils.startsWith(resultat, "0")) {
			resultat = StringUtils.removeStart(resultat, "0");
		} 
		
		return resultat;
		
	} // Fin de retirerZerosAGauche(...).__________________________________
	

	
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
