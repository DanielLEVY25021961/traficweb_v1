package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.impl;

import static org.junit.Assert.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.apptechnic.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.apptechnic.exceptions.technical.impl.TableauNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.TableauVideException;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;

/**
 * CLASSE DescriptionChampHitTest :<br/>
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
 * @author dan Lévy
 * @version 1.0
 * @since 16 mai 2019
 *
 */
public class DescriptionChampHitTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;

	/**
	 * "unused".
	 */
	public static final String UNUSED 
		= "unused";

	/**
	 * "doit retourner null : ".
	 */
	public static final String DOIT_RETOURNER_NULL 
		= "doit retourner null : ";
	
	/**
	 * "ne doit pas retourner null : ".
	 */
	public static final String NE_DOIT_PAS_RETOURNER_NULL 
		= "ne doit pas retourner null : ";

	/**
	 * new DescriptionChampHit().
	 */
	public static IDescriptionChamp descriptionChamp; 
		
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(DescriptionChampHitTest.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public DescriptionChampHitTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * teste la méthode lireChamp(tokens[]).<br/>
	 * <ul>
	 * <li>garantit que lireChamp(null) jette une TableauNullException.</li> 
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testLireChampNull() throws  Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE DescriptionChampHitTest - méthode testLireChampNull() ********** ");
		}
		
		try {
			
			descriptionChamp.lireChamp(null);
			
		} catch (Exception e) {
			
			/* garantit que lireChamp(null) jette une TableauNullException. */
			assertTrue("doit être instance de TableauNullException : "
					, e instanceof TableauNullException);
		}
		
	} // Fin de testLireChampNull()._______________________________________

	
	
	/**
	 * teste la méthode lireChamp(tokens[]).<br/>
	 * <ul>
	 * <li>garantit que lireChamp(vide) jette une TableauVideException.</li> 
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testLireChampVide() throws  Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE DescriptionChampHitTest - méthode testLireChampVide() ********** ");
		}
		
		try {
			
			final String[] tableauVide = new String[9];
			tableauVide[0] = "3";
			tableauVide[1] = "47";
			tableauVide[3] = "bidon";
			tableauVide[5] = "champJavaBidon";
			tableauVide[6] = "StringBidon";
			tableauVide[7] = "falseBidon";
			
			descriptionChamp.lireChamp(tableauVide);
			
		} catch (Exception e) {
			
			/* garantit que lireChamp(vide) jette une TableauVideException. */
			assertTrue("doit être instance de TableauVideException : "
					, e instanceof TableauVideException);
		}
		
	} // Fin de testLireChampVide()._______________________________________

	
	
	/**
	 * teste la méthode lireChamp(tokens[]).<br/>
	 * <ul>
	 * <li>garantit que lireChamp(trop court) jette une ExceptionImport.</li> 
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testLireChampTropCourt() throws  Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE DescriptionChampHitTest - méthode testLireChampTropCourt() ********** ");
		}
		
		try {
			
			final String[] tableauTropCourt = new String[8];
			tableauTropCourt[0] = "3";
			tableauTropCourt[1] = "47";
			tableauTropCourt[3] = "bidon";
			tableauTropCourt[5] = "champJavaBidon";
			tableauTropCourt[6] = "StringBidon";
			tableauTropCourt[7] = "falseBidon";
			
			descriptionChamp.lireChamp(tableauTropCourt);
			
		} catch (Exception e) {
			
			/* garantit que lireChamp(trop court) jette une ExceptionImport. */
			assertTrue("doit être instance de ExceptionImport : "
					, e instanceof ExceptionImport);
		}
		
	} // Fin de testLireChampTropCourt().__________________________________

	
	
	/**
	 * teste la méthode lireChamp(tokens[]).<br/>
	 * <ul>
	 * <li>garantit que lireChamp(mal renseigné) jette une ExceptionImport.</li> 
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testLireChampMalRenseigne() throws  Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE DescriptionChampHitTest - méthode testLireChampMalRenseigne() ********** ");
		}
		
		try {
			
			final String[] tableauMalRenseigne = new String[9];

			tableauMalRenseigne[1] = "47";
			tableauMalRenseigne[3] = "bidon";
			tableauMalRenseigne[5] = "champJavaBidon";
			tableauMalRenseigne[6] = "StringBidon";
			tableauMalRenseigne[7] = "falseBidon";
			
			descriptionChamp.lireChamp(tableauMalRenseigne);
			
		} catch (Exception e) {
			
			/* garantit que lireChamp(mal renseigné) jette une ExceptionImport. */
			assertTrue("doit être instance de ExceptionImport : "
					, e instanceof ExceptionImport);
		}
		
	} // Fin de testLireChampMalRenseigne().__________________________________

	
	
	/**
	 * teste la méthode lireChamp(tokens[]).<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testLireChamp() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE DescriptionChampHitTest - méthode testLireChamp() ********** ");
		}
		
		final String[] tokens = {"1", "1-3", "3", "Numéro de Département", "cadré à gauche", "numDepartment", "Integer", "false", "false"};
		
		descriptionChamp.lireChamp(tokens);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(descriptionChamp.descriptionChampToString());
		}
		
		final Integer ordreChamps = descriptionChamp.getOrdreChamps();

	} // Fin de testLireChamp().___________________________________________


	
	/**
	 * réalisé avant tous les tests de la classe.<br/>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		
		descriptionChamp = new DescriptionChampHit();
		
	} // Fin de beforeClass()._____________________________________________

	
	
} // FIN DE LA CLASSE DescriptionChampHitTest.-------------------------------
