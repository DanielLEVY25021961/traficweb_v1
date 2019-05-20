package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testImporterDescriptionUtf8() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ImportateurDescriptionHitTest - méthode testImporterDescriptionUtf8() ********** ");
		}
		
		// TEST DE LA METHODE
		final Map<Integer, IDescriptionChamp> resultat = 
				importateur.importerDescriptionUtf8();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(importateur.toStringFormate());
		}
		
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultat);
		
		final String ligneEnTetesToString = importateur.fournirLigneEnTetestoString();
		final String ligneEnTetesCsv = importateur.fournirLigneEnTetesCsv();
		final String ligneEnTetesCsvDesc = importateur.getDescriptionChamp().fournirLigneEnTetesCsv();
		final String ligneValeursCsv0 = importateur.fournirLigneValeursCsv(0);
		final String ligneValeursCsv1 = importateur.fournirLigneValeursCsv(1);
		final String ligneValeursCsv2 = importateur.fournirLigneValeursCsv(2);
		final String ligneValeursCsv133 = importateur.fournirLigneValeursCsv(133);
		final String ligneValeursCsv134 = importateur.fournirLigneValeursCsv(134);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("ligneEnTetesToString : " + ligneEnTetesToString);
			System.out.println("ligneEnTetesCsv : " + ligneEnTetesCsv);
			System.out.println("ligneEnTetesCsvDesc : " + ligneEnTetesCsvDesc);
			System.out.println("ligneValeursCsv0 : " + ligneValeursCsv0);
			System.out.println("ligneValeursCsv1 : " + ligneValeursCsv1);
			System.out.println("ligneValeursCsv2 : " + ligneValeursCsv2);
			System.out.println("ligneValeursCsv133 : " + ligneValeursCsv133);
			System.out.println("ligneValeursCsv134 : " + ligneValeursCsv134);
		}

		assertEquals("lignesEnTetesCsv doit valoir importateur.getDescriptionChamp().fournirLigneEnTetesCsv() : "
				, "ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;aNomenclature;aLexique;colonneDebut;colonneFin;longueurCalculee;"
				, ligneEnTetesCsv);
		
		assertEquals("lignesEnTetesCsv doit valoir importateur.getDescriptionChamp().fournirLigneEnTetesCsv() : "
				, ligneEnTetesCsvDesc
				, ligneEnTetesCsv);
		
	} // Fin de testImporterDescriptionUtf8()._______________________________


	
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
		
	} // Fin de instancierImportateur().___________________________________
	
	
	
} // FIN DE LA CLASSE ImportateurDescriptionHitTest.-------------------------
