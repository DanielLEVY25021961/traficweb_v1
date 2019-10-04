package levy.daniel.application.model.dto.metier.sections;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.model.dto.metier.sections.impl.SectionHitDTO;
import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.sections.impl.SectionHit;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl.ImporteurHit;

/**
 * CLASSE SectionHitConvertisseurMetierDTOTest :<br/>
 * Test JUnit de la classe {@link SectionHitConvertisseurMetierDTO}.<br/>
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
 * @since 4 juil. 2019
 *
 */
public class SectionHitConvertisseurMetierDTOTest {

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
	 * "doit retourner la bonne valeur : ".<br/>
	 */
	public static final String DOIT_RETOURNER_BONNE_VALEUR 
		= "doit retourner la bonne valeur : ";

	/**
	 * "Doit retourner la même instance : ".
	 */
	public static final String DOIT_RETOURNER_MEME_INSTANCE 
		= "Doit retourner la même instance : ";

	/**
	 * "doit retourner invalide : ".
	 */
	public static final String DOIT_RETOURNER_INVALIDE 
		= "doit retourner invalide : ";
	
	/**
	 * Path absolu du présent projet Eclipse.<br/>
	 * <code>Paths.get(".").toAbsolutePath().normalize()</code>.<br/>
	 */
	public static final Path PATH_ABSOLU_PRESENT_PROJET 
		= Paths.get(".").toAbsolutePath().normalize();
	
	/**
	 * path <b>relatif</b> (par rapport au présent projet ECLIPSE) 
	 * des ressources des tests JUnit dans le présent projet ECLIPSE.<br/>
	 * Paths.get("src/test/resources")
	 */
	public static final Path SRC_TEST_RESOURCES_PATH_RELATIF 
		= Paths.get("src/test/resources");

	/**
	 * Path absolu vers les ressources de test.<br/>
	 */
	public static final Path PATH_ABSOLU_TEST_RESOURCES 
		= PATH_ABSOLU_PRESENT_PROJET
			.resolve(SRC_TEST_RESOURCES_PATH_RELATIF)
				.toAbsolutePath().normalize();
	
	 /**
	 * Path absolu vers les nomenclatures de test.<br/>
	 */
	public static final Path PATH_ABSOLU_TEST_NOMENCLATURES 
		= PATH_ABSOLU_TEST_RESOURCES
			.resolve("ressources/Nomenclatures")
				.toAbsolutePath().normalize();
	
	 /**
	 * Path absolu vers les jeux d'essai de test.<br/>
	 * src/test/resources/jeux_essai
	 */
	public static final Path PATH_ABSOLU_TEST_JEUX_ESSAI 
		= PATH_ABSOLU_TEST_RESOURCES
			.resolve("jeux_essai")
				.toAbsolutePath().normalize();
	
	/**
	 * Path absolu vers le répertoire 'temp' sous le présent projet.
	 */
	public static final Path PATH_ABSOLU_REPERTOIRE_TEMP 
		= PATH_ABSOLU_PRESENT_PROJET.resolve("temp");

	/**
	 * DTO a tester.
	 */
	public static  ISectionHitDTO dto;

	/**
	 * OBJET METIER a tester.
	 */
	public static ISectionHit objetMetier;
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitConvertisseurMetierDTOTest.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public SectionHitConvertisseurMetierDTOTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * teste la méthode convertirDTOEnObjetMetier(DTO).<br/>
	 * <ul>
	 * <li>garantit le bon fonctionnement de la méthode.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConvertirDTOEnObjetMetier() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitConvertisseurMetierDTOTest - méthode testConvertirDTOEnObjetMetier() ********** ");
		}
		
		
		// CONVERSION
		final ISectionHit objet 
			= SectionHitConvertisseurMetierDTO
				.convertirDTOEnObjetMetier(dto);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(dto.toString());
			System.out.println(objet.toString());
		}
		
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, dto.getNumDepartement()
					, objet.getNumDepartement());
		
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, objetMetier
					, objet);
	
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
		System.out.println("********** CLASSE SectionHitConvertisseurMetierDTOTest - méthode testConvertirObjetMetierEnDTO() ********** ");
		}
				
		
		// CONVERSION
		final ISectionHitDTO dtoLocal 
			= SectionHitConvertisseurMetierDTO
				.convertirObjetMetierEnDTO(objetMetier);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(objetMetier.toString());
			System.out.println(dtoLocal.toString());
		}
		
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, objetMetier.getNumDepartement()
					, dtoLocal.getNumDepartement());
		
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, dto
					, dtoLocal);
	
	} // Fin de testConvertirObjetMetierEnDTO().___________________________
	
	
	
	/**
	 * Exécuté avant tout test de la méthode.<br/>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		
		final Path fichierDonneesPath 
			= PATH_ABSOLU_TEST_JEUX_ESSAI.resolve("HITDIRA2017.txt");
		final File fichierDonnees = fichierDonneesPath.toFile();
		final Charset charsetAnsi = Charset.forName("Windows-1252");
		
		// OBJET A TESTER.
		final ImporteurHit importeurHIT = new ImporteurHit();
		
		final Map<Integer, ISectionHit> fichierMapObjet 
			= importeurHIT.importerObjet(fichierDonnees, charsetAnsi);
		
		final SortedMap<Integer, SortedMap<Integer, String>> fichierImporteMap 
			= importeurHIT.getFichierImporteMap();
		
		final SortedMap<Integer, String> ligneMap = fichierImporteMap.get(1);
		final ISectionHit objetMap = new SectionHit(ligneMap);
		
		objetMetier = fichierMapObjet.get(1);
		
		
		final Map<Integer, ISectionHitDTO> fichierMapDTO 
			= importeurHIT.importerDTO(fichierDonnees, charsetAnsi);
		
		final ISectionHitDTO dtoMap = new SectionHitDTO(ligneMap);
		
		dto = fichierMapDTO.get(1);
		
		assertEquals("doivent être égaux : ", dto, dtoMap);

		
		assertEquals("doivent être égaux : ", objetMetier, objetMap);
		
	} // Fin de beforeClass()._____________________________________________

	

} // FIN DE LA CLASSE SectionHitConvertisseurMetierDTOTest.------------------
