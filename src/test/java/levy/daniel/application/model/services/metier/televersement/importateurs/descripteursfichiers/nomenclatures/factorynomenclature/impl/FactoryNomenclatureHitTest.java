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
 * CLASSE FactoryNomenclatureHitTest :<br/>
 * Test JUnit de la classe {@link FactoryNomenclatureHit}.<br/>
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
 * @since 12 mai 2019
 *
 */
public class FactoryNomenclatureHitTest {

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
	 * FactoryNomenclatureHit à tester.
	 */
	public static final FactoryNomenclatureHit FACTORY_NOMENCLATURE_HIT 
		= new FactoryNomenclatureHit();

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
	@SuppressWarnings(UNUSED)
	private static final Log LOG 
		= LogFactory.getLog(FactoryNomenclatureHitTest.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public FactoryNomenclatureHitTest() {
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
			System.out.println("********** CLASSE FactoryNomenclatureHitTest - méthode testGetClesPossiblesSetInexistant() ********** ");
		}

		final Set<Integer> resultat 
			= FACTORY_NOMENCLATURE_HIT.getClesPossiblesSet(2);
		
		/* garantit que getClesPossiblesSet(inexistant) retourne null. */
		assertNull(DOIT_RETOURNER_NULL, resultat);
				
	} // Fin de testGetClesPossiblesSetInexistant()._______________________
	

	
	/**
	 * Teste la méthode getClesPossiblesSet(int pNumOrdre).<br/>
	 * <ul>
	 * <li>garantit que getClesPossiblesSet(int pNumOrdre) 
	 * retourne toujours des SINGLETONS.</li>
	 * <li>garantit que getClesPossiblesSet(3) retourne 
	 * les clés possibles du SENS.</li>
	 * <li>garantit que getClesPossiblesSet(4) retourne 
	 * les clés possibles du NATURE.</li>
	 * <li>garantit que getClesPossiblesSet(11) retourne 
	 * les clés possibles du CATEGORIE ADMINISTRATIVE DE LA ROUTE.</li>
	 * <li>garantit que getClesPossiblesSet(12) retourne 
	 * les clés possibles du TYPE DE COMPTAGE.</li>
	 * <li>garantit que getClesPossiblesSet(13) retourne 
	 * les clés possibles du CLASSEMENT DE LA ROUTE.</li>
	 * <li>garantit que getClesPossiblesSet(14) retourne 
	 * les clés possibles du CLASSE DE LARGEUR DE CHAUSSEE UNIQUE.</li>
	 * <li>garantit que getClesPossiblesSet(15) retourne 
	 * les clés possibles du CLASSE DE LARGEUR DE CHAUSSEES SEPAREES.</li>
	 * <li>garantit que getClesPossiblesSet(16) retourne 
	 * les clés possibles du TYPE DE RESEAU.</li>
	 * <li>garantit que getClesPossiblesSet(17) retourne 
	 * les clés possibles du PR/PK.</li>
	 * <li>garantit que getClesPossiblesSet(31) retourne 
	 * les clés possibles du SENS DE LA SECTION DE RATTACHEMENT.</li>
	 * <li>garantit que getClesPossiblesSet(34) retourne 
	 * les clés possibles du SENS DE LA SECTION LIMITROPHE.</li>
	 * <li>garantit que getClesPossiblesSet(71) retourne 
	 * les clés possibles du TYPE DE COMPTAGE DE L'ANNEE N-1.</li>
	 * <li>garantit que getClesPossiblesSet(79) retourne 
	 * les clés possibles du TYPE DE COMPTAGE DE L'ANNEE N-2.</li>
	 * <li>garantit que getClesPossiblesSet(87) retourne 
	 * les clés possibles du TYPE DE COMPTAGE DE L'ANNEE N-3.</li>
	 * <li>garantit que getClesPossiblesSet(95) retourne 
	 * les clés possibles du TYPE DE COMPTAGE DE L'ANNEE N-4.</li>
	 * <li>garantit que getClesPossiblesSet(103) retourne 
	 * les clés possibles du TYPE DE COMPTAGE DE L'ANNEE N-5.</li>
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
			System.out.println("********** CLASSE FactoryNomenclatureHitTest - méthode testGetClesPossiblesSet() ********** ");
		}
		
		/* SENS. */
		final Set<Integer> resultatSens 
			= FACTORY_NOMENCLATURE_HIT.getClesPossiblesSet(3);
		
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
				, FactoryNomenclatureHit.getSetClesPossiblesSens()
					, resultatSens);

		/* NATURE. */
		final Set<Integer> resultatNature 
			= FACTORY_NOMENCLATURE_HIT.getClesPossiblesSet(4);
		
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
				, FactoryNomenclatureHit.getSetClesPossiblesNature()
					, resultatNature);

		/* CATEGORIE ADMINISTRATIVE DE LA ROUTE. */
		final Set<Integer> resultatCatAdminRoute 
			= FACTORY_NOMENCLATURE_HIT.getClesPossiblesSet(11);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "CATEGORIE ADMINISTRATIVE DE LA ROUTE : ");
			System.out.print(resultatCatAdminRoute);
		}
		
		/* garantit que getClesPossiblesSet(11) retourne 
		 * les clés possibles du CATEGORIE ADMINISTRATIVE DE LA ROUTE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatCatAdminRoute);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesCategorieAdminRoute()
					, resultatCatAdminRoute);

		/* TYPE DE COMPTAGE. */
		final Set<Integer> resultatTypeComptage 
			= FACTORY_NOMENCLATURE_HIT.getClesPossiblesSet(12);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "TYPE DE COMPTAGE : ");
			System.out.print(resultatTypeComptage);
		}
		
		/* garantit que getClesPossiblesSet(12) retourne 
		 * les clés possibles du TYPE DE COMPTAGE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeComptage);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesTypeComptage()
					, resultatTypeComptage);

		/* CLASSEMENT DE LA ROUTE. */
		final Set<Integer> resultatClassementRoute 
			= FACTORY_NOMENCLATURE_HIT.getClesPossiblesSet(13);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "CLASSEMENT DE LA ROUTE : ");
			System.out.print(resultatClassementRoute);
		}
		
		/* garantit que getClesPossiblesSet(13) retourne 
		 * les clés possibles du CLASSEMENT DE LA ROUTE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatClassementRoute);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesClassementRoute()
					, resultatClassementRoute);

		/* CLASSE DE LARGEUR DE CHAUSSEE UNIQUE. */
		final Set<Integer> resultatClasseLargeurChausseeU 
			= FACTORY_NOMENCLATURE_HIT.getClesPossiblesSet(14);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "CLASSE DE LARGEUR DE CHAUSSEE UNIQUE : ");
			System.out.print(resultatClasseLargeurChausseeU);
		}
		
		/* garantit que getClesPossiblesSet(14) retourne 
		 * les clés possibles du CLASSE DE LARGEUR DE CHAUSSEE UNIQUE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatClasseLargeurChausseeU);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesClasseLargeurChausseeU()
					, resultatClasseLargeurChausseeU);

		/* CLASSE DE LARGEUR DE CHAUSSEES SEPAREES. */
		final Set<Integer> resultatClasseLargeurChausseesS 
			= FACTORY_NOMENCLATURE_HIT.getClesPossiblesSet(15);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "CLASSE DE LARGEUR DE CHAUSSEES SEPAREES : ");
			System.out.print(resultatClasseLargeurChausseesS);
		}
		
		/* garantit que getClesPossiblesSet(15) retourne 
		 * les clés possibles du CLASSE DE LARGEUR DE CHAUSSEES SEPAREES. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatClasseLargeurChausseesS);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesClasseLargeurChausseesS()
					, resultatClasseLargeurChausseesS);

		/* TYPE DE RESEAU. */
		final Set<Integer> resultatTypeReseau 
			= FACTORY_NOMENCLATURE_HIT.getClesPossiblesSet(16);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "TYPE DE RESEAU : ");
			System.out.print(resultatTypeReseau);
		}
		
		/* garantit que getClesPossiblesSet(16) retourne 
		 * les clés possibles du TYPE DE RESEAU. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeReseau);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesTypeReseau()
					, resultatTypeReseau);

		/* PR/PK. */
		final Set<Integer> resultatPrPk 
			= FACTORY_NOMENCLATURE_HIT.getClesPossiblesSet(17);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "PR/PK : ");
			System.out.print(resultatPrPk);
		}
		
		/* garantit que getClesPossiblesSet(17) retourne 
		 * les clés possibles du PR/PK. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatPrPk);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesPrPk()
					, resultatPrPk);

		/* SENS DE LA SECTION DE RATTACHEMENT. */
		final Set<Integer> resultatSensRattachement 
			= FACTORY_NOMENCLATURE_HIT.getClesPossiblesSet(31);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "SENS DE LA SECTION DE RATTACHEMENT : ");
			System.out.print(resultatSensRattachement);
		}
		
		/* garantit que getClesPossiblesSet(31) retourne 
		 * les clés possibles du SENS DE LA SECTION DE RATTACHEMENT. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatSensRattachement);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesSensRattachement()
					, resultatSensRattachement);

		/* SENS DE LA SECTION LIMITROPHE. */
		final Set<Integer> resultatSensLimitrophe 
			= FACTORY_NOMENCLATURE_HIT.getClesPossiblesSet(34);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "SENS DE LA SECTION LIMITROPHE : ");
			System.out.print(resultatSensLimitrophe);
		}
		
		/* garantit que getClesPossiblesSet(34) retourne 
		 * les clés possibles du SENS DE LA SECTION LIMITROPHE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatSensLimitrophe);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesSensLimitrophe()
					, resultatSensLimitrophe);

		/* TYPE DE COMPTAGE DE L'ANNEE N-1. */
		final Set<Integer> resultatTypeComptageNmoins1 
			= FACTORY_NOMENCLATURE_HIT.getClesPossiblesSet(71);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "TYPE DE COMPTAGE DE L'ANNEE N-1 : ");
			System.out.print(resultatTypeComptageNmoins1);
		}
		
		/* garantit que getClesPossiblesSet(71) retourne 
		 * les clés possibles du TYPE DE COMPTAGE DE L'ANNEE N-1. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeComptageNmoins1);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesTypeComptageNmoins1()
					, resultatTypeComptageNmoins1);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesTypeComptage()
					, resultatTypeComptageNmoins1);

		/* TYPE DE COMPTAGE DE L'ANNEE N-2. */
		final Set<Integer> resultatTypeComptageNmoins2 
			= FACTORY_NOMENCLATURE_HIT.getClesPossiblesSet(79);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "TYPE DE COMPTAGE DE L'ANNEE N-2 : ");
			System.out.print(resultatTypeComptageNmoins2);
		}
		
		/* garantit que getClesPossiblesSet(79) retourne 
		 * les clés possibles du TYPE DE COMPTAGE DE L'ANNEE N-2. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeComptageNmoins2);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesTypeComptageNmoins2()
					, resultatTypeComptageNmoins2);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesTypeComptage()
					, resultatTypeComptageNmoins2);

		/* TYPE DE COMPTAGE DE L'ANNEE N-3. */
		final Set<Integer> resultatTypeComptageNmoins3 
			= FACTORY_NOMENCLATURE_HIT.getClesPossiblesSet(87);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "TYPE DE COMPTAGE DE L'ANNEE N-3 : ");
			System.out.print(resultatTypeComptageNmoins3);
		}
		
		/* garantit que getClesPossiblesSet(87) retourne 
		 * les clés possibles du TYPE DE COMPTAGE DE L'ANNEE N-3. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeComptageNmoins3);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesTypeComptageNmoins3()
					, resultatTypeComptageNmoins3);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesTypeComptage()
					, resultatTypeComptageNmoins3);

		/* TYPE DE COMPTAGE DE L'ANNEE N-4. */
		final Set<Integer> resultatTypeComptageNmoins4 
			= FACTORY_NOMENCLATURE_HIT.getClesPossiblesSet(95);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "TYPE DE COMPTAGE DE L'ANNEE N-4 : ");
			System.out.print(resultatTypeComptageNmoins4);
		}
		
		/* garantit que getClesPossiblesSet(95) retourne 
		 * les clés possibles du TYPE DE COMPTAGE DE L'ANNEE N-4. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeComptageNmoins4);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesTypeComptageNmoins4()
					, resultatTypeComptageNmoins4);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesTypeComptage()
					, resultatTypeComptageNmoins4);

		/* TYPE DE COMPTAGE DE L'ANNEE N-5. */
		final Set<Integer> resultatTypeComptageNmoins5 
			= FACTORY_NOMENCLATURE_HIT.getClesPossiblesSet(103);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(CLES_POSSIBLES_POUR + "TYPE DE COMPTAGE DE L'ANNEE N-5 : ");
			System.out.print(resultatTypeComptageNmoins5);
		}
		
		/* garantit que getClesPossiblesSet(103) retourne 
		 * les clés possibles du TYPE DE COMPTAGE DE L'ANNEE N-5. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeComptageNmoins5);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesTypeComptageNmoins5()
					, resultatTypeComptageNmoins5);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getSetClesPossiblesTypeComptage()
					, resultatTypeComptageNmoins5);

	} // Fin de testGetClesPossiblesSet()._________________________________
	
	
	
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
			System.out.println("********** CLASSE FactoryNomenclatureHitTest - méthode testGetNomenclatureMapInexistant() ********** ");
		}

		final Map<Integer, String> resultat 
			= FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(2);
		
		/* garantit que getNomenclatureMap(inexistant) retourne null. */
		assertNull(DOIT_RETOURNER_NULL, resultat);
				
	} // Fin de testGetNomenclatureMapInexistant().________________________
	

	
	/**
	 * Teste la méthode getNomenclatureMap(int pNumOrdre).<br/>
	 * <ul>
	 * <li>garantit que getNomenclatureMap(int pNumOrdre) 
	 * retourne toujours des SINGLETONS.</li>
	 * <li>garantit que getNomenclatureMap(3) retourne 
	 * la nomenclature du SENS.</li>
	 * <li>garantit que getNomenclatureMap(4) retourne 
	 * la nomenclature du NATURE.</li>
	 * <li>garantit que getNomenclatureMap(11) retourne 
	 * la nomenclature du CATEGORIE ADMINISTRATIVE DE LA ROUTE.</li>
	 * <li>garantit que getNomenclatureMap(12) retourne 
	 * la nomenclature du TYPE DE COMPTAGE.</li>
	 * <li>garantit que getNomenclatureMap(13) retourne 
	 * la nomenclature du CLASSEMENT DE LA ROUTE.</li>
	 * <li>garantit que getNomenclatureMap(14) retourne 
	 * la nomenclature du CLASSE DE LARGEUR DE CHAUSSEE UNIQUE.</li>
	 * <li>garantit que getNomenclatureMap(15) retourne 
	 * la nomenclature du CLASSE DE LARGEUR DE CHAUSSEES SEPAREES.</li>
	 * <li>garantit que getNomenclatureMap(16) retourne 
	 * la nomenclature du TYPE DE RESEAU.</li>
	 * <li>garantit que getNomenclatureMap(17) retourne 
	 * la nomenclature du PR/PK.</li>
	 * <li>garantit que getNomenclatureMap(31) retourne 
	 * la nomenclature du SENS DE LA SECTION DE RATTACHEMENT.</li>
	 * <li>garantit que getNomenclatureMap(34) retourne 
	 * la nomenclature du SENS DE LA SECTION LIMITROPHE.</li>
	 * <li>garantit que getNomenclatureMap(71) retourne 
	 * la nomenclature du TYPE DE COMPTAGE DE L'ANNEE N-1.</li>
	 * <li>garantit que getNomenclatureMap(79) retourne 
	 * la nomenclature du TYPE DE COMPTAGE DE L'ANNEE N-2.</li>
	 * <li>garantit que getNomenclatureMap(87) retourne 
	 * la nomenclature du TYPE DE COMPTAGE DE L'ANNEE N-3.</li>
	 * <li>garantit que getNomenclatureMap(95) retourne 
	 * la nomenclature du TYPE DE COMPTAGE DE L'ANNEE N-4.</li>
	 * <li>garantit que getNomenclatureMap(103) retourne 
	 * la nomenclature du TYPE DE COMPTAGE DE L'ANNEE N-5.</li>
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
			System.out.println("********** CLASSE FactoryNomenclatureHitTest - méthode testGetNomenclatureMap() ********** ");
		}
		
		/* SENS. */
		final Map<Integer, String> resultatSens 
			= FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(3);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "SENS : ");
			System.out.print(FACTORY_NOMENCLATURE_HIT.afficherMapIntegerString(resultatSens));
		}
		
		/* garantit que getNomenclatureMap(3) retourne 
		 * la nomenclature du SENS. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatSens);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapSens()
					, resultatSens);

		/* NATURE. */
		final Map<Integer, String> resultatNature 
			= FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(4);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "NATURE : ");
			System.out.print(FACTORY_NOMENCLATURE_HIT.afficherMapIntegerString(resultatNature));
		}
		
		/* garantit que getNomenclatureMap(4) retourne 
		 * la nomenclature du NATURE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatNature);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapNature()
					, resultatNature);

		/* CATEGORIE ADMINISTRATIVE DE LA ROUTE. */
		final Map<Integer, String> resultatCatAdminRoute 
			= FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(11);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "CATEGORIE ADMINISTRATIVE DE LA ROUTE : ");
			System.out.print(FACTORY_NOMENCLATURE_HIT.afficherMapIntegerString(resultatCatAdminRoute));
		}
		
		/* garantit que getNomenclatureMap(11) retourne 
		 * la nomenclature du CATEGORIE ADMINISTRATIVE DE LA ROUTE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatCatAdminRoute);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapCategorieAdminRoute()
					, resultatCatAdminRoute);

		/* TYPE DE COMPTAGE. */
		final Map<Integer, String> resultatTypeComptage 
			= FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(12);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "TYPE DE COMPTAGE : ");
			System.out.print(FACTORY_NOMENCLATURE_HIT.afficherMapIntegerString(resultatTypeComptage));
		}
		
		/* garantit que getNomenclatureMap(12) retourne 
		 * la nomenclature du TYPE DE COMPTAGE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeComptage);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapTypeComptage()
					, resultatTypeComptage);

		/* CLASSEMENT DE LA ROUTE. */
		final Map<Integer, String> resultatClassementRoute 
			= FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(13);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "CLASSEMENT DE LA ROUTE : ");
			System.out.print(FACTORY_NOMENCLATURE_HIT.afficherMapIntegerString(resultatClassementRoute));
		}
		
		/* garantit que getNomenclatureMap(13) retourne 
		 * la nomenclature du CLASSEMENT DE LA ROUTE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatClassementRoute);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapClassementRoute()
					, resultatClassementRoute);

		/* CLASSE DE LARGEUR DE CHAUSSEE UNIQUE. */
		final Map<Integer, String> resultatClasseLargeurChausseeU 
			= FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(14);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "CLASSE DE LARGEUR DE CHAUSSEE UNIQUE : ");
			System.out.print(FACTORY_NOMENCLATURE_HIT.afficherMapIntegerString(resultatClasseLargeurChausseeU));
		}
		
		/* garantit que getNomenclatureMap(14) retourne 
		 * la nomenclature du CLASSE DE LARGEUR DE CHAUSSEE UNIQUE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatClasseLargeurChausseeU);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapClasseLargeurChausseeU()
					, resultatClasseLargeurChausseeU);

		/* CLASSE DE LARGEUR DE CHAUSSEES SEPAREES. */
		final Map<Integer, String> resultatClasseLargeurChausseesS 
			= FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(15);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "CLASSE DE LARGEUR DE CHAUSSEES SEPAREES : ");
			System.out.print(FACTORY_NOMENCLATURE_HIT.afficherMapIntegerString(resultatClasseLargeurChausseesS));
		}
		
		/* garantit que getNomenclatureMap(15) retourne 
		 * la nomenclature du CLASSE DE LARGEUR DE CHAUSSEES SEPAREES. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatClasseLargeurChausseesS);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapClasseLargeurChausseesS()
					, resultatClasseLargeurChausseesS);

		/* TYPE DE RESEAU. */
		final Map<Integer, String> resultatTypeReseau 
			= FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(16);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "TYPE DE RESEAU : ");
			System.out.print(FACTORY_NOMENCLATURE_HIT.afficherMapIntegerString(resultatTypeReseau));
		}
		
		/* garantit que getNomenclatureMap(16) retourne 
		 * la nomenclature du TYPE DE RESEAU. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeReseau);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapTypeReseau()
					, resultatTypeReseau);

		/* PR/PK. */
		final Map<Integer, String> resultatPrPk 
			= FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(17);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "PR/PK : ");
			System.out.print(FACTORY_NOMENCLATURE_HIT.afficherMapIntegerString(resultatPrPk));
		}
		
		/* garantit que getNomenclatureMap(17) retourne 
		 * la nomenclature du PR/PK. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatPrPk);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapPrPk()
					, resultatPrPk);

		/* SENS DE LA SECTION DE RATTACHEMENT. */
		final Map<Integer, String> resultatSensRattachement 
			= FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(31);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "SENS DE LA SECTION DE RATTACHEMENT : ");
			System.out.print(FACTORY_NOMENCLATURE_HIT.afficherMapIntegerString(resultatSensRattachement));
		}
		
		/* garantit que getNomenclatureMap(31) retourne 
		 * la nomenclature du SENS DE LA SECTION DE RATTACHEMENT. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatSensRattachement);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapSensRattachement()
					, resultatSensRattachement);

		/* SENS DE LA SECTION LIMITROPHE. */
		final Map<Integer, String> resultatSensLimitrophe 
			= FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(34);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "SENS DE LA SECTION LIMITROPHE : ");
			System.out.print(FACTORY_NOMENCLATURE_HIT.afficherMapIntegerString(resultatSensLimitrophe));
		}
		
		/* garantit que getNomenclatureMap(34) retourne 
		 * la nomenclature du SENS DE LA SECTION LIMITROPHE. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatSensLimitrophe);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapSensLimitrophe()
					, resultatSensLimitrophe);

		/* TYPE DE COMPTAGE DE L'ANNEE N-1. */
		final Map<Integer, String> resultatTypeComptageNmoins1 
			= FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(71);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(NOMENCLATURE_POUR + "TYPE DE COMPTAGE DE L'ANNEE N-1 : ");
			System.out.print(FACTORY_NOMENCLATURE_HIT.afficherMapIntegerString(resultatTypeComptageNmoins1));
		}
		
		/* garantit que getNomenclatureMap(71) retourne 
		 * la nomenclature du TYPE DE COMPTAGE DE L'ANNEE N-1. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeComptageNmoins1);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapTypeComptageNmoins1()
					, resultatTypeComptageNmoins1);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapTypeComptage()
					, resultatTypeComptageNmoins1);

		/* TYPE DE COMPTAGE DE L'ANNEE N-2. */
		final Map<Integer, String> resultatTypeComptageNmoins2 
			= FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(79);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(NOMENCLATURE_POUR + "TYPE DE COMPTAGE DE L'ANNEE N-2 : ");
			System.out.print(resultatTypeComptageNmoins2);
		}
		
		/* garantit que getNomenclatureMap(79) retourne 
		 * la nomenclature du TYPE DE COMPTAGE DE L'ANNEE N-2. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeComptageNmoins2);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapTypeComptageNmoins2()
					, resultatTypeComptageNmoins2);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapTypeComptage()
					, resultatTypeComptageNmoins2);

		/* TYPE DE COMPTAGE DE L'ANNEE N-3. */
		final Map<Integer, String> resultatTypeComptageNmoins3 
			= FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(87);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(NOMENCLATURE_POUR + "TYPE DE COMPTAGE DE L'ANNEE N-3 : ");
			System.out.print(resultatTypeComptageNmoins3);
		}
		
		/* garantit que getNomenclatureMap(87) retourne 
		 * la nomenclature du TYPE DE COMPTAGE DE L'ANNEE N-3. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeComptageNmoins3);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapTypeComptageNmoins3()
					, resultatTypeComptageNmoins3);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapTypeComptage()
					, resultatTypeComptageNmoins3);

		/* TYPE DE COMPTAGE DE L'ANNEE N-4. */
		final Map<Integer, String> resultatTypeComptageNmoins4 
			= FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(95);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(NOMENCLATURE_POUR + "TYPE DE COMPTAGE DE L'ANNEE N-4 : ");
			System.out.print(resultatTypeComptageNmoins4);
		}
		
		/* garantit que getNomenclatureMap(95) retourne 
		 * la nomenclature du TYPE DE COMPTAGE DE L'ANNEE N-4. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeComptageNmoins4);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapTypeComptageNmoins4()
					, resultatTypeComptageNmoins4);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapTypeComptage()
					, resultatTypeComptageNmoins4);

		/* TYPE DE COMPTAGE DE L'ANNEE N-5. */
		final Map<Integer, String> resultatTypeComptageNmoins5 
			= FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(103);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.print(NOMENCLATURE_POUR + "TYPE DE COMPTAGE DE L'ANNEE N-5 : ");
			System.out.print(resultatTypeComptageNmoins5);
		}
		
		/* garantit que getNomenclatureMap(103) retourne 
		 * la nomenclature du TYPE DE COMPTAGE DE L'ANNEE N-5. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultatTypeComptageNmoins5);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapTypeComptageNmoins5()
					, resultatTypeComptageNmoins5);
		assertSame(DOIT_RETOURNER_BONNE_NOMENCLATURE
				, FactoryNomenclatureHit.getNomenclatureMapTypeComptage()
					, resultatTypeComptageNmoins5);

	} // Fin de testGetNomenclatureMap()._________________________________


	
} // FIN DE LA CLASSE FactoryNomenclatureHitTest.----------------------------
