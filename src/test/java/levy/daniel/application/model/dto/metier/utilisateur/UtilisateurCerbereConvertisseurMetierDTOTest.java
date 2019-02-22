package levy.daniel.application.model.dto.metier.utilisateur;

import static org.junit.Assert.assertNotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.dto.metier.utilisateur.impl.UtilisateurCerbereDTO;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.metier.utilisateur.impl.UtilisateurCerbere;

/**
 * CLASSE UtilisateurCerbereConvertisseurMetierDTOTest :<br/>
 * test JUnit de la classe {@link UtilisateurCerbereConvertisseurMetierDTO}.<br/>
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
 * @since 21 févr. 2019
 *
 */
public class UtilisateurCerbereConvertisseurMetierDTOTest {

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
	= LogFactory.getLog(UtilisateurCerbereConvertisseurMetierDTOTest.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereConvertisseurMetierDTOTest() {
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
		System.out.println("********** CLASSE UtilisateurCerbereConvertisseurMetierDTOTest - méthode testConvertirDTOEnObjetMetier() ********** ");
		}
		
		final IUtilisateurCerbereDTO dto 
			= new UtilisateurCerbereDTO("7"
					, "M."
					, "Papy", "Gonzales"
					, "01 02 03 04 05", "papy.gonzales@yahoo.com"
					, "SG", "SG/SPSSI/CPII/DOSO/ET"
					, "ADMINISTRATEUR", "SG", "France métropolitaine");
		
		final IUtilisateurCerbere objet = UtilisateurCerbereConvertisseurMetierDTO.convertirDTOEnObjetMetier(dto);
		
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
		System.out.println("********** CLASSE UtilisateurCerbereConvertisseurMetierDTOTest - méthode testConvertirObjetMetierEnDTO() ********** ");
		}
		
		final IUtilisateurCerbere objet 
			= new UtilisateurCerbere(7L
					, "M."
					, "Papy", "Gonzales"
					, "01 02 03 04 05", "papy.gonzales@yahoo.com"
					, "SG", "SG/SPSSI/CPII/DOSO/ET"
					, "ADMINISTRATEUR", "SG", "France métropolitaine");
		
		final IUtilisateurCerbereDTO dto = UtilisateurCerbereConvertisseurMetierDTO.convertirObjetMetierEnDTO(objet);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(objet.toString());
			System.out.println(dto.toString());
		}
		
		assertNotNull("id ne doit pas être null : ", dto.getId());
	
	} // Fin de testConvertirObjetMetierEnDTO().___________________________


	
} // Fin de CLASSE UtilisateurCerbereConvertisseurMetierDTOTest.-------------
