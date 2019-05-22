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
		
		// METHODE A TESTER.
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
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionHitTest - méthode testGetDescriptionChamp() ********** ");
		}
		
		// METHODE A TESTER.
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
	 * Teste la méthode fournirEnteteparColonne(int pI).<br/>
	 * <i>après import.</i>
	 * <ul>
	 * <li>garantit que fournirEnteteparColonne(0) null.</li>
	 * <li>garantit que fournirEnteteparColonne(i) retourne la bonne valeur.</li>
	 * <li>garantit que fournirEnteteparColonne(hors index) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirEnteteparColonne() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionHitTest - méthode testFournirEnteteparColonne() ********** ");
		}
		
		// METHODE A TESTER.
		final String fournirEnteteparColonne0 
			= importateur.fournirEnteteparColonne(0);
		
		final String fournirEnteteparColonne1 
			= importateur.fournirEnteteparColonne(1);
		final String fournirEnteteparColonne2 
			= importateur.fournirEnteteparColonne(2);
		final String fournirEnteteparColonne3 
			= importateur.fournirEnteteparColonne(3);
		final String fournirEnteteparColonne4 
			= importateur.fournirEnteteparColonne(4);
		final String fournirEnteteparColonne5 
			= importateur.fournirEnteteparColonne(5);
		final String fournirEnteteparColonne6 
			= importateur.fournirEnteteparColonne(6);
		final String fournirEnteteparColonne7 
			= importateur.fournirEnteteparColonne(7);
		final String fournirEnteteparColonne8 
			= importateur.fournirEnteteparColonne(8);
		final String fournirEnteteparColonne9 
			= importateur.fournirEnteteparColonne(9);
		final String fournirEnteteparColonne10 
			= importateur.fournirEnteteparColonne(10);
		final String fournirEnteteparColonne11 
			= importateur.fournirEnteteparColonne(11);
		final String fournirEnteteparColonne12 
			= importateur.fournirEnteteparColonne(12);

		final String fournirEnteteparColonne13 
			= importateur.fournirEnteteparColonne(13);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("fournirEnteteparColonne0 : " + fournirEnteteparColonne0);
			
			System.out.println("fournirEnteteparColonne1 : " + fournirEnteteparColonne1);
			System.out.println("fournirEnteteparColonne2 : " + fournirEnteteparColonne2);
			System.out.println("fournirEnteteparColonne3 : " + fournirEnteteparColonne3);
			System.out.println("fournirEnteteparColonne4 : " + fournirEnteteparColonne4);
			System.out.println("fournirEnteteparColonne5 : " + fournirEnteteparColonne5);
			System.out.println("fournirEnteteparColonne6 : " + fournirEnteteparColonne6);
			System.out.println("fournirEnteteparColonne7 : " + fournirEnteteparColonne7);
			System.out.println("fournirEnteteparColonne8 : " + fournirEnteteparColonne8);
			System.out.println("fournirEnteteparColonne9 : " + fournirEnteteparColonne9);
			System.out.println("fournirEnteteparColonne10 : " + fournirEnteteparColonne10);
			System.out.println("fournirEnteteparColonne11 : " + fournirEnteteparColonne11);
			System.out.println("fournirEnteteparColonne12 : " + fournirEnteteparColonne12);
			
			System.out.println("fournirEnteteparColonne13 : " + fournirEnteteparColonne13);
		}
		
		/* garantit que fournirEnteteparColonne(0) null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, fournirEnteteparColonne0);
		
		/* garantit que fournirEnteteparColonne(i) retourne la bonne valeur. */
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "ordreChamps"
						, fournirEnteteparColonne1);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "colonnes"
						, fournirEnteteparColonne2);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "longueur"
						, fournirEnteteparColonne3);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "intitule"
						, fournirEnteteparColonne4);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "nomenclature"
						, fournirEnteteparColonne5);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "champJava"
						, fournirEnteteparColonne6);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "typeJava"
						, fournirEnteteparColonne7);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "aNomenclature"
						, fournirEnteteparColonne8);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "aLexique"
						, fournirEnteteparColonne9);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "colonneDebut"
						, fournirEnteteparColonne10);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "colonneFin"
						, fournirEnteteparColonne11);
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "longueurCalculee"
						, fournirEnteteparColonne12);
		
		/* garantit que fournirEnteteparColonne(hors index) retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
				, fournirEnteteparColonne13);
		
	} // Fin de testFournirEnteteparColonne()._____________________________


	
	/**
	 * Test la méthode fournirLigneEnTetesCsv().<br/>
	 * <ul>
	 * <li>garantit que fournirLigneEnTetesCsv() retourne la bonne valeur.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirLigneEnTetesCsv() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionHitTest - méthode testFournirLigneEnTetesCsv() ********** ");
		}
		
		// METHODE A TESTER.
		final String ligneEnTetesCsv = importateur.fournirLigneEnTetesCsv();
		final String[] tokens =importateur.stringCsvToTokens(ligneEnTetesCsv);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("ligneEnTetesCsv : " + ligneEnTetesCsv);
		}
		
		/* garantit que fournirLigneEnTetesCsv() retourne la bonne valeur. */
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;aNomenclature;aLexique;colonneDebut;colonneFin;longueurCalculee;"
						, ligneEnTetesCsv);
		assertEquals("l'en-tête doit comporter 12 éléments : "
				, 12
					, tokens.length);
		
	} // Fin de testFournirLigneEnTetesCsv().______________________________
	
	
	
	/**
	 * Teste la méthode fournirLigneValeursCsv(int pL).<br/>
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
		
		// METHODE A TESTER.
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
	 * Teste la méthode fournirValeurparLigneColonne(int pLigne, int pColonne).<br/>
	 * <i>après import.</i>
	 * <ul>
	 * <li>garantit que fournirValeurparLigneColonne(0, pCol) retourne 
	 * une valeur de l'en-tête de la description de fichier.</li>
	 * li>garantit que fournirValeurparLigneColonne(0, inexistant) retourne null.</li>
	 * <li>garantit que fournirValeurparLigneColonne(ligne, pCol) 
	 * retourne la bonne valeur de la description de fichier.</li>
	 * <li>garantit que garantit que fournirValeurparLigneColonne(ligne, inexistant) 
	 * retourne null.</li>
	 * <li>garantit que fournirValeurparLigneColonne(inexistant, colonne)
	 *  retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirValeurparLigneColonne() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionHitTest - méthode testFournirValeurparLigneColonne() ********** ");
		}
		
		// METHODE A TESTER.
		final String valeurl0c0 = importateur.fournirValeurparLigneColonne(0, 0);
		final String valeurl0c1 = importateur.fournirValeurparLigneColonne(0, 1);
		final String valeurl0c6 = importateur.fournirValeurparLigneColonne(0, 6);
		final String valeurl0c12 = importateur.fournirValeurparLigneColonne(0, 12);
		final String valeurl0c13 = importateur.fournirValeurparLigneColonne(0, 13);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("valeurl0c0 : " + valeurl0c0);
			System.out.println("valeurl0c1 : " + valeurl0c1);
			System.out.println("valeurl0c6 : " + valeurl0c6);
			System.out.println("valeurl0c12 : " + valeurl0c12);
			System.out.println("valeurl0c13 : " + valeurl0c13);
		}
		
		/* garantit que fournirValeurparLigneColonne(0, pCol) 
		 * retourne une valeur de l'en-tête de la description de fichier. */
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "ordreChamps"
					, valeurl0c1);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "champJava"
					, valeurl0c6);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "longueurCalculee"
					, valeurl0c12);
		
		/* garantit que fournirValeurparLigneColonne(0, inexistant) 
		 * retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl0c0);
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl0c13);
		
		
		// METHODE A TESTER.
		final String valeurl28c0 = importateur.fournirValeurparLigneColonne(28, 0);
		final String valeurl28c1 = importateur.fournirValeurparLigneColonne(28, 1);
		final String valeurl28c6 = importateur.fournirValeurparLigneColonne(28, 6);
		final String valeurl28c12 = importateur.fournirValeurparLigneColonne(28, 12);
		final String valeurl28c13 = importateur.fournirValeurparLigneColonne(28, 13);
		final String valeurl134c7 = importateur.fournirValeurparLigneColonne(134, 7);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("valeurl28c0 : " + valeurl28c0);
			System.out.println("valeurl28c1 : " + valeurl28c1);
			System.out.println("valeurl28c6 : " + valeurl28c6);
			System.out.println("valeurl28c12 : " + valeurl28c12);
			System.out.println("valeurl28c13 : " + valeurl28c13);
			System.out.println("valeurl134c7 : " + valeurl134c7);
		}
		
		/* garantit que fournirValeurparLigneColonne(ligne, pCol) 
		 * retourne la bonne valeur de la description de fichier. */
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "28"
					, valeurl28c1);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "longueurRaseCampagne"
					, valeurl28c6);
		assertEquals(DOIT_RETOURNER_BONNE_VALEUR
				, "6"
					, valeurl28c12);
		
		/* garantit que fournirValeurparLigneColonne(ligne, inexistant) 
		 * retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl28c0);
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl28c13);
		
		/* garantit que fournirValeurparLigneColonne(inexistant, colonne) 
		 * retourne null. */
		assertNull(
				DOIT_RETOURNER_NULL
					, valeurl134c7);
		
	} // Fin de testFournirValeurparLigneColonne().________________________
	
	
	
	
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
