package levy.daniel.application.model.dto.metier.anneegestion;

import static org.junit.Assert.assertNotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.dto.metier.anneegestion.impl.AnneeGestionDTO;
import levy.daniel.application.model.metier.anneegestion.IAnneeGestion;
import levy.daniel.application.model.metier.anneegestion.impl.AnneeGestion;

/**
 * CLASSE AnneeGestionConvertisseurMetierDTOTest :<br/>
 * Test JUnit de la classe {@link AnneeGestionConvertisseurMetierDTO}.<br/>
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
 * @since 20 juin 2019
 *
 */
public class AnneeGestionConvertisseurMetierDTOTest {

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
		= LogFactory.getLog(AnneeGestionConvertisseurMetierDTOTest.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public AnneeGestionConvertisseurMetierDTOTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * teste la méthode convertirDTOEnObjetMetier(DTO).<br/>
	 * <ul>
	 * <li>garantit le bon fonctionnement de la méthode.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConvertirDTOEnObjetMetier() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE AnneeGestionConvertisseurMetierDTOTest - méthode testConvertirDTOEnObjetMetier() ********** ");
		}
		
		final IAnneeGestionDTO dto = new AnneeGestionDTO("1", "2019");
		
		// CONVERSION
		final IAnneeGestion objet 
			= AnneeGestionConvertisseurMetierDTO.convertirDTOEnObjetMetier(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(dto.toString());
			System.out.println(objet.toString());
		}
		
		assertNotNull("id ne doit pas être null : ", objet.getId());
	
	} // Fin de testConvertirDTOEnObjetMetier().___________________________
	

	
	/**
	 * teste la méthode testConvertirObjetMetierEnDTO(ObjetMetier).<br/>
	 * <ul>
	 * <li>garantit le bon fonctionnement de la méthode.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConvertirObjetMetierEnDTO() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE AnneeGestionConvertisseurMetierDTOTest - méthode testConvertirObjetMetierEnDTO() ********** ");
		}
		
		final IAnneeGestion objet 
			= new AnneeGestion(7L
					, "2021");
		
		// CONVERSION
		final IAnneeGestionDTO dto 
			= AnneeGestionConvertisseurMetierDTO
				.convertirObjetMetierEnDTO(objet);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(objet.toString());
			System.out.println(dto.toString());
		}
		
		assertNotNull("id ne doit pas être null : ", dto.getId());
	
	} // Fin de testConvertirObjetMetierEnDTO().___________________________


	
} // FIN DE LA CLASSE AnneeGestionConvertisseurMetierDTOTest.----------------
