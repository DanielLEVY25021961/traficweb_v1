package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.IImportateurDescription;

/**
 * CLASSE ImportateurDescriptionHitTest :<br/>
 * Test JUnit de la classe {@link ImportateurDescriptionHit}.<br/>
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
 * @since 14 mai 2019
 *
 */
public class ImportateurDescriptionHitTest {

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
	 * IImportateurDescription à tester.<br/>
	 * instancié dans instancierImportateur (beforeClass).
	 */
	public static transient IImportateurDescription importateur;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings(UNUSED)
	private static final Log LOG 
		= LogFactory.getLog(ImportateurDescriptionHitTest.class);
	
	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ImportateurDescriptionHitTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * Teste la méthode importerDescriptionUtf8().<br/>
	 * <ul>
	 * <li>garantit que importerDescriptionUtf8() importe 
	 * la description de fichier.</li>
	 * <i>après l'import</i> : <br/>
	 * <li>garantit que getSpecificationChampsMap() retourne 
	 * <code><b>this.specificationChampsMap</b></code>.</li>
	 * <li>garantit le bon fonctionnement de fournirLigneEnTetesCsv().</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testImporterDescriptionUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionHitTest - méthode testImporterDescriptionUtf8() ********** ");
		}
		
		// TEST DE LA METHODE
		final Map<Integer, IDescriptionChamp> resultat = 
				importateur.getSpecificationChampsMap();
			
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(importateur.toStringFormate());
		}
		
		/* garantit que getSpecificationChampsMap() retourne 
		 * this.specificationChampsMap. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultat);
		
		final String ligneEnTetesToString = importateur.fournirLigneEnTetestoString();
		final String ligneEnTetesCsv = importateur.fournirLigneEnTetesCsv();
		final String ligneEnTetesCsvDesc = importateur.getDescriptionChamp().fournirLigneEnTetesCsv();
		
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("ligneEnTetesToString : " + ligneEnTetesToString);
			System.out.println("ligneEnTetesCsv : " + ligneEnTetesCsv);
			System.out.println("ligneEnTetesCsvDesc : " + ligneEnTetesCsvDesc);
		}

		/* garantit le bon fonctionnement de fournirLigneEnTetesCsv(). */
		assertEquals("lignesEnTetesCsv doit valoir importateur.getDescriptionChamp().fournirLigneEnTetesCsv() : "
				, "ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;aNomenclature;aLexique;colonneDebut;colonneFin;longueurCalculee;"
				, ligneEnTetesCsv);
		
		assertEquals("lignesEnTetesCsv doit valoir importateur.getDescriptionChamp().fournirLigneEnTetesCsv() : "
				, ligneEnTetesCsvDesc
				, ligneEnTetesCsv);
		
	} // Fin de testImporterDescriptionUtf8()._______________________________
	
	
	
	/**
	 * Teste la méthode fournirLigneValeursCsv(int pI).<br/>
	 * <i>après import.</i>
	 * <ul>
	 * <li>garantit que fournirLigneValeursCsv(0) retourne l'en-tête 
	 * de la description de fichier.</li>
	 * <li>garantit que fournirLigneValeursCsv(i) retourne la bonne valeur.</li>
	 * <li>garantit que fournirLigneValeursCsv(hors index) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirLigneValeursCsv() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionHitTest - méthode testFournirLigneValeursCsv() ********** ");
		}
		
		final String ligneValeursCsv0 = importateur.fournirLigneValeursCsv(0);
		final String ligneValeursCsv1 = importateur.fournirLigneValeursCsv(1);
		final String ligneValeursCsv2 = importateur.fournirLigneValeursCsv(2);
		final String ligneValeursCsv133 = importateur.fournirLigneValeursCsv(133);
		final String ligneValeursCsv134 = importateur.fournirLigneValeursCsv(134);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("ligneValeursCsv0 : " + ligneValeursCsv0);
			System.out.println("ligneValeursCsv1 : " + ligneValeursCsv1);
			System.out.println("ligneValeursCsv2 : " + ligneValeursCsv2);
			System.out.println("ligneValeursCsv133 : " + ligneValeursCsv133);
			System.out.println("ligneValeursCsv134 : " + ligneValeursCsv134);
		}
		
		final String ligneEnTetesCsv = importateur.fournirLigneEnTetesCsv();
		
		/* garantit que fournirLigneValeursCsv(0) retourne l'en-tête de la description de fichier. */
		assertEquals("fournirLigneValeursCsv(0) doit valoir importateur.getDescriptionChamp().fournirLigneEnTetesCsv() : "
				, ligneValeursCsv0
				, ligneEnTetesCsv);
		
		/* garantit que fournirLigneValeursCsv(i) retourne la bonne valeur. */
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "1;1-3;3;Numéro de Département;Exactement 3 Chiffres. Complété par un 0 à droite si dep < 3 chiffres et complété par un 0 à gauche si dep < 2 chiffres. Exemples : (Ain) 1 = 010, (Allier) 3 = 030, (Bouches-du-Rhône) 13 = 130, (Dordogne) 24 = 240, (Guadeloupe) 971 = 971;numDepartement;Integer;false;false;1;3;3;"
				, ligneValeursCsv1);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "2;4-9;6;Numéro de Section;Numéro de section sur 4 chiffres significatifs maxi complété éventuellement avec des 0 à gauche et indice sur 2 chiffres significatifs maxi complété éventuellement avec des 0 à gauche. Exemples : section 26 indice 4 = 002604, section 1 indice 0 = 000100, section 162 indice 65 = 016265;numSection;String;false;false;4;9;6;"
				, ligneValeursCsv2);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "133;486-520;35;Zone libre;35 espaces;sans objet;sans objet;false;false;486;520;35;"
				, ligneValeursCsv133);
		
		/* garantit que fournirLigneValeursCsv(hors index) retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, ligneValeursCsv134);
		
	} // Fin de testFournirLigneValeursCsv().______________________________
	
	
	
	/**
	 * Teste la méthode getDescriptionChamp(int pI).<br/>
	 * <i>après import.</i>
	 * <ul>
	 * <li>garantit que getDescriptionChamp(0) null.</li>
	 * <li>garantit que getDescriptionChamp(i) retourne la bonne valeur.</li>
	 * <li>garantit que getDescriptionChamp(hors index) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetDescriptionChamp() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionHitTest - méthode testGetDescriptionChamp() ********** ");
		}
		
		final IDescriptionChamp descriptionChamp0 = importateur.getDescriptionChamp(0);
		final IDescriptionChamp descriptionChamp1 = importateur.getDescriptionChamp(1);
		final IDescriptionChamp descriptionChamp2 = importateur.getDescriptionChamp(2);
		final IDescriptionChamp descriptionChamp133 = importateur.getDescriptionChamp(133);
		final IDescriptionChamp descriptionChamp134 = importateur.getDescriptionChamp(134);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("descriptionChamp0 : " + descriptionChamp0);
			System.out.println("descriptionChamp1 : " + descriptionChamp1.fournirLigneValeursCsv());
			System.out.println("descriptionChamp2 : " + descriptionChamp2.fournirLigneValeursCsv());
			System.out.println("descriptionChamp133 : " + descriptionChamp133.fournirLigneValeursCsv());
			System.out.println("descriptionChamp134 : " + descriptionChamp134);
		}
		
		/* garantit que getDescriptionChamp(0) retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, descriptionChamp0);
		
		/* garantit que getDescriptionChamp(i) retourne la bonne valeur. */
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "1;1-3;3;Numéro de Département;Exactement 3 Chiffres. Complété par un 0 à droite si dep < 3 chiffres et complété par un 0 à gauche si dep < 2 chiffres. Exemples : (Ain) 1 = 010, (Allier) 3 = 030, (Bouches-du-Rhône) 13 = 130, (Dordogne) 24 = 240, (Guadeloupe) 971 = 971;numDepartement;Integer;false;false;1;3;3;"
				, descriptionChamp1.fournirLigneValeursCsv());
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "2;4-9;6;Numéro de Section;Numéro de section sur 4 chiffres significatifs maxi complété éventuellement avec des 0 à gauche et indice sur 2 chiffres significatifs maxi complété éventuellement avec des 0 à gauche. Exemples : section 26 indice 4 = 002604, section 1 indice 0 = 000100, section 162 indice 65 = 016265;numSection;String;false;false;4;9;6;"
				, descriptionChamp2.fournirLigneValeursCsv());
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "133;486-520;35;Zone libre;35 espaces;sans objet;sans objet;false;false;486;520;35;"
				, descriptionChamp133.fournirLigneValeursCsv());
		
		/* garantit que getDescriptionChamp(hors index) retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, descriptionChamp134);
		
	} // Fin de testGetDescriptionChamp()._________________________________


	
	/**
	 * Instancie un IImportateurDescription 
	 * avant tout test de la classe.<br/>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void instancierImportateur() throws Exception {
		
		importateur 
			= new ImportateurDescriptionHit();
		
		// IMPORT
		importateur.importerDescriptionUtf8();
		
	} // Fin de instancierImportateur().___________________________________
	
	
	
} // FIN DE LA CLASSE ImportateurDescriptionHitTest.-------------------------
