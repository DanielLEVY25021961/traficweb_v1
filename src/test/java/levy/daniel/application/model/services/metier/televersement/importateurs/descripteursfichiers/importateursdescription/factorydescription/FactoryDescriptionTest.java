package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.factorydescription;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;

/**
 * CLASSE FactoryDescriptionTest :<br/>
 * Test JUnit de la classe {@link FactoryDescription}.<br/>
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
 * @since 2 juin 2019
 *
 */
public class FactoryDescriptionTest {

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
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(FactoryDescriptionTest.class);

	// *************************METHODES************************************/
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public FactoryDescriptionTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * teste la méthode getDecriptionHitMap().<br/>
	 * <ul>
	 * <li>garantit que getDecriptionHitMap() ne retourne pas null.</li>
	 * <li>garantit que getDecriptionHitMap() retourne un singleton.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testgetDecriptionHitMap() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE FactoryDescriptionTest - méthode testgetDecriptionHitMap() ********** ");
		}
		
		// METHODE A TESTER.
		final SortedMap<Integer, IDescriptionChamp> descriptionHitMap = 
				FactoryDescription.getDecriptionHitMap();
		
		final SortedMap<Integer, IDescriptionChamp> descriptionHitMap1 = 
				FactoryDescription.getDecriptionHitMap();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(FactoryDescription.afficherDescription(descriptionHitMap));
		}

		/* garantit que getDecriptionHitMap() ne retourne pas null. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, descriptionHitMap);
		
		/* garantit que getDecriptionHitMap() retourne un singleton. */
		assertSame(DOIT_RETOURNER_MEME_INSTANCE
				, descriptionHitMap, descriptionHitMap1);
		
	} // Fin de testgetDecriptionHitMap()._________________________________

	
	
	/**
	 * teste la méthode getDecriptionHistoF07Map().<br/>
	 * <ul>
	 * <li>garantit que getDecriptionHistoF07Map() ne retourne pas null.</li>
	 * <li>garantit que getDecriptionHistoF07Map() retourne un singleton.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testgetDecriptionHistoF07Map() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE FactoryDescriptionTest - méthode testgetDecriptionHistoF07Map() ********** ");
		}
		
		// METHODE A TESTER.
		final SortedMap<Integer, IDescriptionChamp> descriptionHistoF07Map = 
				FactoryDescription.getDecriptionHistoF07Map();
		
		final SortedMap<Integer, IDescriptionChamp> descriptionHistoF07Map1 = 
				FactoryDescription.getDecriptionHistoF07Map();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(FactoryDescription.afficherDescription(descriptionHistoF07Map));
		}

		/* garantit que getDecriptionHistoF07Map() ne retourne pas null. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, descriptionHistoF07Map);
		
		/* garantit que getDecriptionHistoF07Map() retourne un singleton. */
		assertSame(DOIT_RETOURNER_MEME_INSTANCE
				, descriptionHistoF07Map, descriptionHistoF07Map1);
		
	} // Fin de testgetDecriptionHistoF07Map().____________________________

	
	
	/**
	 * teste la méthode getDecriptionHistoF08Map().<br/>
	 * <ul>
	 * <li>garantit que getDecriptionHistoF08Map() ne retourne pas null.</li>
	 * <li>garantit que getDecriptionHistoF08Map() retourne un singleton.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testgetDecriptionHistoF08Map() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE FactoryDescriptionTest - méthode testgetDecriptionHistoF08Map() ********** ");
		}
		
		// METHODE A TESTER.
		final SortedMap<Integer, IDescriptionChamp> descriptionHistoF08Map = 
				FactoryDescription.getDecriptionHistoF08Map();
		
		final SortedMap<Integer, IDescriptionChamp> descriptionHistoF08Map1 = 
				FactoryDescription.getDecriptionHistoF08Map();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(FactoryDescription.afficherDescription(descriptionHistoF08Map));
		}

		/* garantit que getDecriptionHistoF08Map() ne retourne pas null. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, descriptionHistoF08Map);
		
		/* garantit que getDecriptionHistoF08Map() retourne un singleton. */
		assertSame(DOIT_RETOURNER_MEME_INSTANCE
				, descriptionHistoF08Map, descriptionHistoF08Map1);
		
	} // Fin de testgetDecriptionHistoF08Map().____________________________

	
	
	/**
	 * teste la méthode getDecriptionDarwinCsvMap().<br/>
	 * <ul>
	 * <li>garantit que getDecriptionDarwinCsvMap() ne retourne pas null.</li>
	 * <li>garantit que getDecriptionDarwinCsvMap() retourne un singleton.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testgetDecriptionDarwinCsvMap() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE FactoryDescriptionTest - méthode testgetDecriptionDarwinCsvMap() ********** ");
		}
		
		// METHODE A TESTER.
		final SortedMap<Integer, IDescriptionChamp> descriptionDarwinCsvMap = 
				FactoryDescription.getDecriptionDarwinCsvMap();
		
		final SortedMap<Integer, IDescriptionChamp> descriptionDarwinCsvMap1 = 
				FactoryDescription.getDecriptionDarwinCsvMap();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(FactoryDescription.afficherDescription(descriptionDarwinCsvMap));
		}

		/* garantit que getDecriptionDarwinCsvMap() ne retourne pas null. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, descriptionDarwinCsvMap);
		
		/* garantit que getDecriptionDarwinCsvMap() retourne un singleton. */
		assertSame(DOIT_RETOURNER_MEME_INSTANCE
				, descriptionDarwinCsvMap, descriptionDarwinCsvMap1);
		
	} // Fin de testgetDecriptionDarwinCsvMap().___________________________

	
	
	/**
	 * teste la méthode getDecriptionMappingMap().<br/>
	 * <ul>
	 * <li>garantit que getDecriptionMappingMap() ne retourne pas null.</li>
	 * <li>garantit que getDecriptionMappingMap() retourne un singleton.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testgetDecriptionMappingMap() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE FactoryDescriptionTest - méthode testgetDecriptionMappingMap() ********** ");
		}
		
		// METHODE A TESTER.
		final SortedMap<Integer, IDescriptionChamp> descriptionMappingMap = 
				FactoryDescription.getDecriptionMappingMap();
		
		final SortedMap<Integer, IDescriptionChamp> descriptionMappingMap1 = 
				FactoryDescription.getDecriptionMappingMap();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(FactoryDescription.afficherDescription(descriptionMappingMap));
		}

		/* garantit que getDecriptionMappingMap() ne retourne pas null. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, descriptionMappingMap);
		
		/* garantit que getDecriptionMappingMap() retourne un singleton. */
		assertSame(DOIT_RETOURNER_MEME_INSTANCE
				, descriptionMappingMap, descriptionMappingMap1);
		
	} // Fin de testgetDecriptionMappingMap()._____________________________

	
	
} // FIN DE LA CLASSE FactoryDescriptionTest.--------------------------------
