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
 * CLASSE FactoryNomenclatureHistoF08Test :<br/>
 * Test JUnit de la classe {@link FactoryNomenclatureHistoF08}.<br/>
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
public class FactoryNomenclatureHistoF08Test {

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
	 * FactoryNomenclatureHistoF08 à tester.
	 */
	public static final FactoryNomenclatureHistoF08 FACTORY_NOMENCLATURE_HISTO_F08 
		= new FactoryNomenclatureHistoF08();

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
		= LogFactory.getLog(FactoryNomenclatureHistoF08Test.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public FactoryNomenclatureHistoF08Test() {
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
			System.out.println("********** CLASSE FactoryNomenclatureHistoF08Test - méthode testGetClesPossiblesSetInexistant() ********** ");
		}

		final Set<Integer> resultat 
			= FACTORY_NOMENCLATURE_HISTO_F08.getClesPossiblesSet(2);
		
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
			System.out.println("********** CLASSE FactoryNomenclatureHistoF08Test - méthode testGetClesPossiblesSetLexiqueInexistant() ********** ");
		}

		final Set<String> resultat 
			= FACTORY_NOMENCLATURE_HISTO_F08.getClesPossiblesSetLexique(2);
		
		/* garantit que getClesPossiblesSet(inexistant) retourne null. */
		assertNull(DOIT_RETOURNER_NULL, resultat);
				
	} // Fin de testGetClesPossiblesSetLexiqueInexistant().________________
	

	
	/**
	 * Teste la méthode getClesPossiblesSet(int pNumOrdre).<br/>
	 * <ul>
	 * <li>garantit que getClesPossiblesSet(int pNumOrdre) 
	 * retourne toujours des SINGLETONS.</li>
	 * <li>garantit que getClesPossiblesSet(3) retourne 
	 * les clés possibles du SENS.</li>
	 * <li>garantit que getClesPossiblesSet(4) retourne 
	 * les clés possibles du NATURE.</li>
	 * <li>garantit que getClesPossiblesSet(9) retourne 
	 * les clés possibles du TYPE DE COMPTAGE.</li>
	 * <li>garantit que getClesPossiblesSet(10) retourne 
	 * les clés possibles du CLASSEMENT DE LA ROUTE.</li>
	 * <li>garantit que getClesPossiblesSet(11) retourne 
	 * les clés possibles du CLASSE DE LARGEUR DE CHAUSSEE UNIQUE.</li>
	 * <li>garantit que getClesPossiblesSet(12) retourne 
	 * les clés possibles du CLASSE DE LARGEUR DE CHAUSSEES SEPAREES.</li>
	 * <li>garantit que getClesPossiblesSet(13) retourne 
	 * les clés possibles du TYPE DE RESEAU.</li>
	 * <li>garantit que getClesPossiblesSet(97) retourne 
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
			System.out.println("********** CLASSE FactoryNomenclatureHistoF08Test - méthode testGetClesPossiblesSet() ********** ");
		}
		
		/* SENS. */
		final Set<Integer> resultatSens 
			= FACTORY_NOMENCLATURE_HISTO_F08.getClesPossiblesSet(3);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "SENS : ");
			System.out.print(resultatSens);
		}
		
		/* garantit que getClesPossiblesSet(3) retourne 
		 * les clés possibles du SENS. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatSens);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getSetClesPossiblesSens()
					, resultatSens);

		/* NATURE. */
		final Set<Integer> resultatNature 
			= FACTORY_NOMENCLATURE_HISTO_F08.getClesPossiblesSet(4);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "NATURE : ");
			System.out.print(resultatNature);
		}
		
		/* garantit que getClesPossiblesSet(4) retourne 
		 * les clés possibles du NATURE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatNature);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getSetClesPossiblesNature()
					, resultatNature);

		/* TYPE DE COMPTAGE. */
		final Set<Integer> resultatTypeComptage 
			= FACTORY_NOMENCLATURE_HISTO_F08.getClesPossiblesSet(9);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "TYPE DE COMPTAGE : ");
			System.out.print(resultatTypeComptage);
		}
		
		/* garantit que getClesPossiblesSet(9) retourne 
		 * les clés possibles du TYPE DE COMPTAGE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeComptage);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getSetClesPossiblesTypeComptage()
					, resultatTypeComptage);

		/* CLASSEMENT DE LA ROUTE. */
		final Set<Integer> resultatClassementRoute 
			= FACTORY_NOMENCLATURE_HISTO_F08.getClesPossiblesSet(10);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "CLASSEMENT DE LA ROUTE : ");
			System.out.print(resultatClassementRoute);
		}
		
		/* garantit que getClesPossiblesSet(10) retourne 
		 * les clés possibles du CLASSEMENT DE LA ROUTE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatClassementRoute);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getSetClesPossiblesClassementRoute()
					, resultatClassementRoute);

		/* CLASSE DE LARGEUR DE CHAUSSEE UNIQUE. */
		final Set<Integer> resultatClasseLargeurChausseeU 
			= FACTORY_NOMENCLATURE_HISTO_F08.getClesPossiblesSet(11);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "CLASSE DE LARGEUR DE CHAUSSEE UNIQUE : ");
			System.out.print(resultatClasseLargeurChausseeU);
		}
		
		/* garantit que getClesPossiblesSet(11) retourne 
		 * les clés possibles du CLASSE DE LARGEUR DE CHAUSSEE UNIQUE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatClasseLargeurChausseeU);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getSetClesPossiblesClasseLargeurChausseeU()
					, resultatClasseLargeurChausseeU);

		/* CLASSE DE LARGEUR DE CHAUSSEES SEPAREES. */
		final Set<Integer> resultatClasseLargeurChausseesS 
			= FACTORY_NOMENCLATURE_HISTO_F08.getClesPossiblesSet(12);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "CLASSE DE LARGEUR DE CHAUSSEES SEPAREES : ");
			System.out.print(resultatClasseLargeurChausseesS);
		}
		
		/* garantit que getClesPossiblesSet(12) retourne 
		 * les clés possibles du CLASSE DE LARGEUR DE CHAUSSEES SEPAREES. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatClasseLargeurChausseesS);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getSetClesPossiblesClasseLargeurChausseesS()
					, resultatClasseLargeurChausseesS);

		/* TYPE DE RESEAU. */
		final Set<Integer> resultatTypeReseau 
			= FACTORY_NOMENCLATURE_HISTO_F08.getClesPossiblesSet(13);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "TYPE DE RESEAU : ");
			System.out.print(resultatTypeReseau);
		}
		
		/* garantit que getClesPossiblesSet(13) retourne 
		 * les clés possibles du TYPE DE RESEAU. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeReseau);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getSetClesPossiblesTypeReseau()
					, resultatTypeReseau);

		/* SOUS-RESEAU INDICE. */
		final Set<Integer> resultatSousReseauIndice 
			= FACTORY_NOMENCLATURE_HISTO_F08.getClesPossiblesSet(97);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "SOUS-RESEAU INDICE : ");
			System.out.print(resultatSousReseauIndice);
		}
		
		/* garantit que getClesPossiblesSet(97) retourne 
		 * les clés possibles du SOUS-RESEAU INDICE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatSousReseauIndice);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getSetClesPossiblesSousReseauIndice()
					, resultatSousReseauIndice);

	} // Fin de testGetClesPossiblesSet()._________________________________
	
	
	
	/**
	 * Teste la méthode getClesPossiblesSetLexique(int pNumOrdre).<br/>
	 * <ul>
	 * <li>garantit que getClesPossiblesSetLexique(int pNumOrdre) 
	 * retourne toujours des SINGLETONS.</li>
	 * <li>garantit que getClesPossiblesSetLexique(93) retourne 
	 * les clés possibles du CODE CONCESSION SICRE.</li>
	 * <li>garantit que getClesPossiblesSet(96) retourne 
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
			System.out.println("********** CLASSE FactoryNomenclatureHistoF08Test - méthode testGetClesPossiblesSetLexique() ********** ");
		}
		
		/* CODE CONCESSION SICRE. */
		final Set<String> resultatCodeConcessionSicre 
			= FACTORY_NOMENCLATURE_HISTO_F08.getClesPossiblesSetLexique(93);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "CODE CONCESSION SICRE : ");
			System.out.print(resultatCodeConcessionSicre);
		}
		
		/* garantit que getClesPossiblesSet(93) retourne 
		 * les clés possibles du CODE CONCESSION SICRE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatCodeConcessionSicre);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getSetClesPossiblesCodeConcessionSicre()
					, resultatCodeConcessionSicre);
		
		/* PROFIL EN TRAVERS SICRE. */
		final Set<String> resultatProfilTraversSicre 
			= FACTORY_NOMENCLATURE_HISTO_F08.getClesPossiblesSetLexique(96);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "PROFIL EN TRAVERS SICRE : ");
			System.out.print(resultatProfilTraversSicre);
		}
		
		/* garantit que getClesPossiblesSet(96) retourne 
		 * les clés possibles du PROFIL EN TRAVERS SICRE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatProfilTraversSicre);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getSetClesPossiblesProfilTraversSicre()
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
			System.out.println("********** CLASSE FactoryNomenclatureHistoF08Test - méthode testGetNomenclatureMapInexistant() ********** ");
		}

		final Map<Integer, String> resultat 
			= FACTORY_NOMENCLATURE_HISTO_F08.getNomenclatureMap(2);
		
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
			System.out.println("********** CLASSE FactoryNomenclatureHistoF08Test - méthode testGetLexiqueMapInexistant() ********** ");
		}

		final Map<String, String> resultat 
			= FACTORY_NOMENCLATURE_HISTO_F08.getLexiqueMap(2);
		
		/* garantit que getLexiqueMap(inexistant) retourne null. */
		assertNull(DOIT_RETOURNER_NULL, resultat);
				
	} // Fin de testGetLexiqueMapInexistant()._____________________________
	

	
	/**
	 * Teste la méthode getNomenclatureMap(int pNumOrdre).<br/>
	 * <ul>
	 * <li>garantit que getNomenclatureMap(int pNumOrdre) 
	 * retourne toujours des SINGLETONS.</li>
	 * <li>garantit que getNomenclatureMap(3) retourne 
	 * la nomenclature du SENS.</li>
	 * <li>garantit que getNomenclatureMap(4) retourne 
	 * la nomenclature du NATURE.</li>
	 * <li>garantit que getNomenclatureMap(9) retourne 
	 * la nomenclature du TYPE DE COMPTAGE.</li>
	 * <li>garantit que getNomenclatureMap(10) retourne 
	 * la nomenclature du CLASSEMENT DE LA ROUTE.</li>
	 * <li>garantit que getNomenclatureMap(11) retourne 
	 * la nomenclature du CLASSE DE LARGEUR DE CHAUSSEE UNIQUE.</li>
	 * <li>garantit que getNomenclatureMap(12) retourne 
	 * la nomenclature du CLASSE DE LARGEUR DE CHAUSSEES SEPAREES.</li>
	 * <li>garantit que getNomenclatureMap(13) retourne 
	 * la nomenclature du TYPE DE RESEAU.</li>
	 * <li>garantit que getNomenclatureMap(97) retourne 
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
			System.out.println("********** CLASSE FactoryNomenclatureHistoF08Test - méthode testGetNomenclatureMap() ********** ");
		}
		
		/* SENS. */
		final Map<Integer, String> resultatSens 
			= FACTORY_NOMENCLATURE_HISTO_F08.getNomenclatureMap(3);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "SENS : ");
			System.out.print(FACTORY_NOMENCLATURE_HISTO_F08.afficherMapIntegerString(resultatSens));
		}
		
		/* garantit que getNomenclatureMap(3) retourne 
		 * la nomenclature du SENS. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatSens);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getNomenclatureMapSens()
					, resultatSens);

		/* NATURE. */
		final Map<Integer, String> resultatNature 
			= FACTORY_NOMENCLATURE_HISTO_F08.getNomenclatureMap(4);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "NATURE : ");
			System.out.print(FACTORY_NOMENCLATURE_HISTO_F08.afficherMapIntegerString(resultatNature));
		}
		
		/* garantit que getNomenclatureMap(4) retourne 
		 * la nomenclature du NATURE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatNature);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getNomenclatureMapNature()
					, resultatNature);

		/* TYPE DE COMPTAGE. */
		final Map<Integer, String> resultatTypeComptage 
			= FACTORY_NOMENCLATURE_HISTO_F08.getNomenclatureMap(9);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "TYPE DE COMPTAGE : ");
			System.out.print(FACTORY_NOMENCLATURE_HISTO_F08.afficherMapIntegerString(resultatTypeComptage));
		}
		
		/* garantit que getNomenclatureMap(9) retourne 
		 * la nomenclature du TYPE DE COMPTAGE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeComptage);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getNomenclatureMapTypeComptage()
					, resultatTypeComptage);

		/* CLASSEMENT DE LA ROUTE. */
		final Map<Integer, String> resultatClassementRoute 
			= FACTORY_NOMENCLATURE_HISTO_F08.getNomenclatureMap(10);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "CLASSEMENT DE LA ROUTE : ");
			System.out.print(FACTORY_NOMENCLATURE_HISTO_F08.afficherMapIntegerString(resultatClassementRoute));
		}
		
		/* garantit que getNomenclatureMap(10) retourne 
		 * la nomenclature du CLASSEMENT DE LA ROUTE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatClassementRoute);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getNomenclatureMapClassementRoute()
					, resultatClassementRoute);

		/* CLASSE DE LARGEUR DE CHAUSSEE UNIQUE. */
		final Map<Integer, String> resultatClasseLargeurChausseeU 
			= FACTORY_NOMENCLATURE_HISTO_F08.getNomenclatureMap(11);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "CLASSE DE LARGEUR DE CHAUSSEE UNIQUE : ");
			System.out.print(FACTORY_NOMENCLATURE_HISTO_F08.afficherMapIntegerString(resultatClasseLargeurChausseeU));
		}
		
		/* garantit que getNomenclatureMap(11) retourne 
		 * la nomenclature du CLASSE DE LARGEUR DE CHAUSSEE UNIQUE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatClasseLargeurChausseeU);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getNomenclatureMapClasseLargeurChausseeU()
					, resultatClasseLargeurChausseeU);

		/* CLASSE DE LARGEUR DE CHAUSSEES SEPAREES. */
		final Map<Integer, String> resultatClasseLargeurChausseesS 
			= FACTORY_NOMENCLATURE_HISTO_F08.getNomenclatureMap(12);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "CLASSE DE LARGEUR DE CHAUSSEES SEPAREES : ");
			System.out.print(FACTORY_NOMENCLATURE_HISTO_F08.afficherMapIntegerString(resultatClasseLargeurChausseesS));
		}
		
		/* garantit que getNomenclatureMap(12) retourne 
		 * la nomenclature du CLASSE DE LARGEUR DE CHAUSSEES SEPAREES. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatClasseLargeurChausseesS);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getNomenclatureMapClasseLargeurChausseesS()
					, resultatClasseLargeurChausseesS);

		/* TYPE DE RESEAU. */
		final Map<Integer, String> resultatTypeReseau 
			= FACTORY_NOMENCLATURE_HISTO_F08.getNomenclatureMap(13);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "TYPE DE RESEAU : ");
			System.out.print(FACTORY_NOMENCLATURE_HISTO_F08.afficherMapIntegerString(resultatTypeReseau));
		}
		
		/* garantit que getNomenclatureMap(13) retourne 
		 * la nomenclature du TYPE DE RESEAU. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeReseau);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getNomenclatureMapTypeReseau()
					, resultatTypeReseau);

		/* SOUS-RESEAU INDICE. */
		final Map<Integer, String> resultatSousReseauIndice 
			= FACTORY_NOMENCLATURE_HISTO_F08.getNomenclatureMap(97);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "SOUS-RESEAU INDICE : ");
			System.out.print(FACTORY_NOMENCLATURE_HISTO_F08.afficherMapIntegerString(resultatSousReseauIndice));
		}
		
		/* garantit que getNomenclatureMap(97) retourne 
		 * la nomenclature du SOUS-RESEAU INDICE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatSousReseauIndice);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getNomenclatureMapSousReseauIndice()
					, resultatSousReseauIndice);

	} // Fin de testGetNomenclatureMap()._________________________________
	

	
	/**
	 * Teste la méthode getLexiqueMap(int pNumOrdre).<br/>
	 * <ul>
	 * <li>garantit que getLexiqueMap(int pNumOrdre) 
	 * retourne toujours des SINGLETONS.</li>
	 * <li>garantit que getLexiqueMap(93) retourne 
	 * le lexique du CODE CONCESSION SICRE.</li>
	 * <li>garantit que getLexiqueMap(96) retourne 
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
			System.out.println("********** CLASSE FactoryNomenclatureHistoF08Test - méthode testGetLexiqueMap() ********** ");
		}
		
		/* CODE CONCESSION SICRE. */
		final Map<String, String> resultatCodeConcessionSicre 
			= FACTORY_NOMENCLATURE_HISTO_F08.getLexiqueMap(93);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "CODE CONCESSION SICRE : ");
			System.out.print(FACTORY_NOMENCLATURE_HISTO_F08.afficherMapStringString(resultatCodeConcessionSicre));
		}
		
		/* garantit que getNomenclatureMap(93) retourne 
		 * le lexique du CODE CONCESSION SICRE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatCodeConcessionSicre);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getLexiqueMapCodeConcessionSicre()
					, resultatCodeConcessionSicre);
		
		/* PROFIL EN TRAVERS SICRE. */
		final Map<String, String> resultatProfilTraversSicre 
			= FACTORY_NOMENCLATURE_HISTO_F08.getLexiqueMap(96);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "PROFIL EN TRAVERS SICRE : ");
			System.out.print(FACTORY_NOMENCLATURE_HISTO_F08.afficherMapStringString(resultatProfilTraversSicre));
		}
		
		/* garantit que getNomenclatureMap(96) retourne 
		 * le lexique du PROFIL EN TRAVERS SICRE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatProfilTraversSicre);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHistoF08.getLexiqueMapProfilTraversSicre()
					, resultatProfilTraversSicre);


	} // Fin de testGetLexiqueMap()._______________________________________
	
	
	
} // FIN DE LA CLASSE FactoryNomenclatureHistoF08Test.-----------------------
