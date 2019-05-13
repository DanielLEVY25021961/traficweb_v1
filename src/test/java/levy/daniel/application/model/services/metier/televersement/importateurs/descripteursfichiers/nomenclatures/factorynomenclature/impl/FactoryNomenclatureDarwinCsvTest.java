package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.factorynomenclature.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * CLASSE FactoryNomenclatureDarwinCsvTest :<br/>
 * Test JUnit de la classe {@link FactoryNomenclatureDarwinCsv}.<br/>
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
 * @since 13 mai 2019
 *
 */
public class FactoryNomenclatureDarwinCsvTest {

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
	 * FactoryNomenclatureDarwinCsv à tester.
	 */
	public static final FactoryNomenclatureDarwinCsv FACTORY_NOMENCLATURE_DARWIN_CSV 
		= new FactoryNomenclatureDarwinCsv();

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
	 * "Clés possibles pour ".
	 */
	public static final String CLES_POSSIBLES_POUR 
		= "Clés possibles pour ";
	
	/**
	 * "doit retourner la bonne nomenclature : ".
	 */
	public static final String DOIT_RETOURNER_BONNE_NOMENCLATURE 
		= "doit retourner la bonne nomenclature : ";
	
	/**
	 * "Nomenclature pour : ".
	 */
	public static final String NOMENCLATURE_POUR 
		= "Nomenclature pour : ";

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(FactoryNomenclatureDarwinCsvTest.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public FactoryNomenclatureDarwinCsvTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * Teste la méthode getClesPossiblesSet(inexistant).<br/>
	 * <ul>
	 * <li>garantit que getClesPossiblesSet(inexistant) retourne null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetClesPossiblesSetInexistant() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE FactoryNomenclatureDarwinCsvTest - méthode testGetClesPossiblesSetInexistant() ********** ");
		}

		final Set<Integer> resultat 
			= FACTORY_NOMENCLATURE_DARWIN_CSV.getClesPossiblesSet(2);
		
		/* garantit que getClesPossiblesSet(inexistant) retourne null. */
		assertNull(DOIT_RETOURNER_NULL, resultat);
				
	} // Fin de testGetClesPossiblesSetInexistant()._______________________
	
	
	
	/**
	 * Teste la méthode getClesPossiblesSetLexique(inexistant).<br/>
	 * <ul>
	 * <li>garantit que getClesPossiblesSetLexique(inexistant) retourne null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetClesPossiblesSetLexiqueInexistant() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE FactoryNomenclatureDarwinCsvTest - méthode testGetClesPossiblesSetLexiqueInexistant() ********** ");
		}

		final Set<String> resultat 
			= FACTORY_NOMENCLATURE_DARWIN_CSV.getClesPossiblesSetLexique(2);
		
		/* garantit que getClesPossiblesSet(inexistant) retourne null. */
		assertNull(DOIT_RETOURNER_NULL, resultat);
				
	} // Fin de testGetClesPossiblesSetLexiqueInexistant().________________
	

	
	/**
	 * Teste la méthode getClesPossiblesSet(int pNumOrdre).<br/>
	 * <ul>
	 * <li>garantit que getClesPossiblesSet(int pNumOrdre) 
	 * retourne toujours des SINGLETONS.</li>
	 * <li>garantit que getClesPossiblesSet(13) retourne 
	 * les clés possibles du SENS.</li>
	 * <li>garantit que getClesPossiblesSet(14) retourne 
	 * les clés possibles du TYPE DE COMPTAGE.</li>
	 * <li>garantit que getClesPossiblesSet(57) retourne 
	 * les clés possibles du CLASSEMENT DE LA ROUTE.</li>
	 * <li>garantit que getClesPossiblesSet(65) retourne 
	 * les clés possibles du SOUS-RESEAU INDICE.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetClesPossiblesSet() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE FactoryNomenclatureDarwinCsvTest - méthode testGetClesPossiblesSet() ********** ");
		}
		
		/* SENS. */
		final Set<Integer> resultatSens 
			= FACTORY_NOMENCLATURE_DARWIN_CSV.getClesPossiblesSet(13);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "SENS : ");
			System.out.print(resultatSens);
		}
		
		/* garantit que getClesPossiblesSet(13) retourne 
		 * les clés possibles du SENS. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatSens);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureDarwinCsv.getSetClesPossiblesSens()
					, resultatSens);

		/* TYPE DE COMPTAGE. */
		final Set<Integer> resultatTypeComptage 
			= FACTORY_NOMENCLATURE_DARWIN_CSV.getClesPossiblesSet(14);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "TYPE DE COMPTAGE : ");
			System.out.print(resultatTypeComptage);
		}
		
		/* garantit que getClesPossiblesSet(14) retourne 
		 * les clés possibles du TYPE DE COMPTAGE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeComptage);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureDarwinCsv.getSetClesPossiblesTypeComptage()
					, resultatTypeComptage);

		/* CLASSEMENT DE LA ROUTE. */
		final Set<Integer> resultatClassementRoute 
			= FACTORY_NOMENCLATURE_DARWIN_CSV.getClesPossiblesSet(57);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "CLASSEMENT DE LA ROUTE : ");
			System.out.print(resultatClassementRoute);
		}
		
		/* garantit que getClesPossiblesSet(57) retourne 
		 * les clés possibles du CLASSEMENT DE LA ROUTE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatClassementRoute);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureDarwinCsv.getSetClesPossiblesClassementRoute()
					, resultatClassementRoute);

		/* SOUS-RESEAU INDICE. */
		final Set<Integer> resultatSousReseauIndice 
			= FACTORY_NOMENCLATURE_DARWIN_CSV.getClesPossiblesSet(65);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "SOUS-RESEAU INDICE : ");
			System.out.print(resultatSousReseauIndice);
		}
		
		/* garantit que getClesPossiblesSet(65) retourne 
		 * les clés possibles du SOUS-RESEAU INDICE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatSousReseauIndice);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureDarwinCsv.getSetClesPossiblesSousReseauIndice()
					, resultatSousReseauIndice);

	} // Fin de testGetClesPossiblesSet()._________________________________
	
	
	
	/**
	 * Teste la méthode getClesPossiblesSetLexique(int pNumOrdre).<br/>
	 * <ul>
	 * <li>garantit que getClesPossiblesSetLexique(int pNumOrdre) 
	 * retourne toujours des SINGLETONS.</li>
	 * <li>garantit que getClesPossiblesSet(58) retourne 
	 * les clés possibles du PROFIL EN TRAVERS SICRE.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetClesPossiblesSetLexique() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE FactoryNomenclatureDarwinCsvTest - méthode testGetClesPossiblesSetLexique() ********** ");
		}
				
		/* PROFIL EN TRAVERS SICRE. */
		final Set<String> resultatProfilTraversSicre 
			= FACTORY_NOMENCLATURE_DARWIN_CSV.getClesPossiblesSetLexique(58);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "PROFIL EN TRAVERS SICRE : ");
			System.out.print(resultatProfilTraversSicre);
		}
		
		/* garantit que getClesPossiblesSet(58) retourne 
		 * les clés possibles du PROFIL EN TRAVERS SICRE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatProfilTraversSicre);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureDarwinCsv.getSetClesPossiblesProfilTraversSicre()
					, resultatProfilTraversSicre);
		
	} // Fin de testGetClesPossiblesSetLexique().__________________________
	

	
	/**
	 * Teste la méthode getNomenclatureMap(inexistant).<br/>
	 * <ul>
	 * <li>garantit que getNomenclatureMap(inexistant) retourne null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomenclatureMapInexistant() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE FactoryNomenclatureDarwinCsvTest - méthode testGetNomenclatureMapInexistant() ********** ");
		}

		final Map<Integer, String> resultat 
			= FACTORY_NOMENCLATURE_DARWIN_CSV.getNomenclatureMap(2);
		
		/* garantit que getNomenclatureMap(inexistant) retourne null. */
		assertNull(DOIT_RETOURNER_NULL, resultat);
				
	} // Fin de testGetNomenclatureMapInexistant().________________________
	

	
	/**
	 * Teste la méthode getLexiqueMap(inexistant).<br/>
	 * <ul>
	 * <li>garantit que getLexiqueMap(inexistant) retourne null.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetLexiqueMapInexistant() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE FactoryNomenclatureDarwinCsvTest - méthode testGetLexiqueMapInexistant() ********** ");
		}

		final Map<String, String> resultat 
			= FACTORY_NOMENCLATURE_DARWIN_CSV.getLexiqueMap(2);
		
		/* garantit que getLexiqueMap(inexistant) retourne null. */
		assertNull(DOIT_RETOURNER_NULL, resultat);
				
	} // Fin de testGetLexiqueMapInexistant()._____________________________
	

	
	/**
	 * Teste la méthode getNomenclatureMap(int pNumOrdre).<br/>
	 * <ul>
	 * <li>garantit que getNomenclatureMap(int pNumOrdre) 
	 * retourne toujours des SINGLETONS.</li>
	 * <li>garantit que getNomenclatureMap(13) retourne 
	 * la nomenclature du SENS.</li>
	 * <li>garantit que getNomenclatureMap(14) retourne 
	 * la nomenclature du TYPE DE COMPTAGE.</li>
	 * <li>garantit que getNomenclatureMap(57) retourne 
	 * la nomenclature du CLASSEMENT DE LA ROUTE.</li>
	 * <li>garantit que getNomenclatureMap(65) retourne 
	 * la nomenclature du SOUS-RESEAU INDICE.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomenclatureMap() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE FactoryNomenclatureDarwinCsvTest - méthode testGetNomenclatureMap() ********** ");
		}
		
		/* SENS. */
		final Map<Integer, String> resultatSens 
			= FACTORY_NOMENCLATURE_DARWIN_CSV.getNomenclatureMap(13);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "SENS : ");
			System.out.print(FACTORY_NOMENCLATURE_DARWIN_CSV.afficherMapIntegerString(resultatSens));
		}
		
		/* garantit que getNomenclatureMap(13) retourne 
		 * la nomenclature du SENS. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatSens);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureDarwinCsv.getNomenclatureMapSens()
					, resultatSens);

		/* TYPE DE COMPTAGE. */
		final Map<Integer, String> resultatTypeComptage 
			= FACTORY_NOMENCLATURE_DARWIN_CSV.getNomenclatureMap(14);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "TYPE DE COMPTAGE : ");
			System.out.print(FACTORY_NOMENCLATURE_DARWIN_CSV.afficherMapIntegerString(resultatTypeComptage));
		}
		
		/* garantit que getNomenclatureMap(14) retourne 
		 * la nomenclature du TYPE DE COMPTAGE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeComptage);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureDarwinCsv.getNomenclatureMapTypeComptage()
					, resultatTypeComptage);

		/* CLASSEMENT DE LA ROUTE. */
		final Map<Integer, String> resultatClassementRoute 
			= FACTORY_NOMENCLATURE_DARWIN_CSV.getNomenclatureMap(57);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "CLASSEMENT DE LA ROUTE : ");
			System.out.print(FACTORY_NOMENCLATURE_DARWIN_CSV.afficherMapIntegerString(resultatClassementRoute));
		}
		
		/* garantit que getNomenclatureMap(57) retourne 
		 * la nomenclature du CLASSEMENT DE LA ROUTE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatClassementRoute);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureDarwinCsv.getNomenclatureMapClassementRoute()
					, resultatClassementRoute);

		/* SOUS-RESEAU INDICE. */
		final Map<Integer, String> resultatSousReseauIndice 
			= FACTORY_NOMENCLATURE_DARWIN_CSV.getNomenclatureMap(65);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "SOUS-RESEAU INDICE : ");
			System.out.print(FACTORY_NOMENCLATURE_DARWIN_CSV.afficherMapIntegerString(resultatSousReseauIndice));
		}
		
		/* garantit que getNomenclatureMap(65) retourne 
		 * la nomenclature du SOUS-RESEAU INDICE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatSousReseauIndice);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureDarwinCsv.getNomenclatureMapSousReseauIndice()
					, resultatSousReseauIndice);

	} // Fin de testGetNomenclatureMap()._________________________________
	

	
	/**
	 * Teste la méthode getLexiqueMap(int pNumOrdre).<br/>
	 * <ul>
	 * <li>garantit que getLexiqueMap(int pNumOrdre) 
	 * retourne toujours des SINGLETONS.</li>
	 * <li>garantit que getLexiqueMap(58) retourne 
	 * le lexique du PROFIL EN TRAVERS SICRE.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetLexiqueMap() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE FactoryNomenclatureDarwinCsvTest - méthode testGetLexiqueMap() ********** ");
		}
		
		/* PROFIL EN TRAVERS SICRE. */
		final Map<String, String> resultatProfilTraversSicre 
			= FACTORY_NOMENCLATURE_DARWIN_CSV.getLexiqueMap(58);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "PROFIL EN TRAVERS SICRE : ");
			System.out.print(FACTORY_NOMENCLATURE_DARWIN_CSV.afficherMapStringString(resultatProfilTraversSicre));
		}
		
		/* garantit que getNomenclatureMap(58) retourne 
		 * le lexique du PROFIL EN TRAVERS SICRE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatProfilTraversSicre);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureDarwinCsv.getLexiqueMapProfilTraversSicre()
					, resultatProfilTraversSicre);


	} // Fin de testGetLexiqueMap()._______________________________________
	
	
	
} // FIN DE LA CLASSE FactoryNomenclatureDarwinCsvTest.----------------------
