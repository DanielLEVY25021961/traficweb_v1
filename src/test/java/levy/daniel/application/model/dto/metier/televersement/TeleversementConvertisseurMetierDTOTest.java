package levy.daniel.application.model.dto.metier.televersement;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.time.LocalDateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.dto.metier.anneegestion.IAnneeGestionDTO;
import levy.daniel.application.model.dto.metier.anneegestion.impl.AnneeGestionDTO;
import levy.daniel.application.model.dto.metier.televersement.impl.TeleversementDTO;
import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.dto.metier.utilisateur.impl.UtilisateurCerbereDTO;
import levy.daniel.application.model.metier.anneegestion.IAnneeGestion;
import levy.daniel.application.model.metier.anneegestion.impl.AnneeGestion;
import levy.daniel.application.model.metier.televersement.EnumTypeFichierDonnees;
import levy.daniel.application.model.metier.televersement.ITeleversement;
import levy.daniel.application.model.metier.televersement.impl.Televersement;
import levy.daniel.application.model.metier.utilisateur.EnumGestionnaire;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.metier.utilisateur.impl.UtilisateurCerbere;

/**
 * CLASSE TeleversementConvertisseurMetierDTOTest :<br/>
 * Test JUnit de la classe {@link TeleversementConvertisseurMetierDTO}.<br/>
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
public class TeleversementConvertisseurMetierDTOTest {

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
		= LogFactory.getLog(TeleversementConvertisseurMetierDTOTest.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public TeleversementConvertisseurMetierDTOTest() {
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
		System.out.println("********** CLASSE TeleversementConvertisseurMetierDTOTest - méthode testConvertirDTOEnObjetMetier() ********** ");
		}
		
		final IUtilisateurCerbereDTO utlisateurDTO 
			= new UtilisateurCerbereDTO("103"
					, "M."
					, "Papy", "Gonzales"
					, "01 63 43 43 89", "papy.gonzales@free.fr"
					, "DIRA", "DIRA/SIEER/TRAFIC_1"
					, "GESTIONNAIRE", "DIRA", null);
		
		final IAnneeGestionDTO anneeGestionDTO 
			= new AnneeGestionDTO("7", "2019");
		
		final ITeleversementDTO dto 
			= new TeleversementDTO("1"
					, "2019-06-20T10:14:27"
					, utlisateurDTO
					, "DIRA"
					, "HIT"
					, "HITDIRA2019"
					, "serveur/donnees/2019_HIT_DIRA.txt"
					, anneeGestionDTO);
		
		// CONVERSION
		final ITeleversement objet 
			= TeleversementConvertisseurMetierDTO
				.convertirDTOEnObjetMetier(dto);
		
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
		System.out.println("********** CLASSE TeleversementConvertisseurMetierDTOTest - méthode testConvertirObjetMetierEnDTO() ********** ");
		}
		
		final IUtilisateurCerbere utilisateur 
			= new UtilisateurCerbere(7L
					, "M."
					, "papy", "Gonzales"
					, "01 63 43 43 89", "papy.gonzales@free.fr"
					, "DIRA", "DIRA/SIEER/TRAFIC_1"
					, "GESTIONNAIRE", "DIRA", null);
		
		final IAnneeGestion anneeGestion = new AnneeGestion(100L, "2019");
		
		final ITeleversement objet 
			= new Televersement(7L
					, LocalDateTime.of(2019, 06, 20, 10, 34, 27)
					, utilisateur
					, EnumGestionnaire.DIRA
					, EnumTypeFichierDonnees.HIT
					, "HITDIRA2019"
					, new File("serveur/donnees/2019_HIT_DIRA.txt")
					, anneeGestion);
		
		
		// CONVERSION
		final ITeleversementDTO dto 
			= TeleversementConvertisseurMetierDTO
				.convertirObjetMetierEnDTO(objet);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(objet.toString());
			System.out.println(dto.toString());
		}
		
		assertNotNull("id ne doit pas être null : ", dto.getId());
	
	} // Fin de testConvertirObjetMetierEnDTO().___________________________


	
} // FIN DE LA CLASSE TeleversementConvertisseurMetierDTOTest.---------------
