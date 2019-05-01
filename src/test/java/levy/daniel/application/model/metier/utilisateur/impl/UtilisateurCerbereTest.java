package levy.daniel.application.model.metier.utilisateur.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;


/**
 * CLASSE UtilisateurCerbereTest :<br/>
 * Test JUnit de la classe OBJET METIER {@link UtilisateurCerbere}.<br/>
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
 * @since 18 févr. 2019
 *
 */
public class UtilisateurCerbereTest {

	// ************************ATTRIBUTS************************************/

	/**
	 * "M.".<br/>
	 */
	public static final String M = "M.";
	
	/**
	 * "prenomTest".<br/>
	 */
	public static final String PRENOMTEST = "prenomTest";
	
	/**
	 * "nomTest".<br/>
	 */
	public static final String NOMTEST = "nomTest";
	
	/**
	 * "test.test@yahoo.fr".<br/>
	 */
	public static final String MAILTEST = "test.test@yahoo.fr";
	
	/**
	 * "CEREMA".<br/>
	 */
	public static final String SERVICETEST = "CEREMA";
	
	/**
	 * "CEREMA/DTecITM/CITS/DACSI".<br/>
	 */
	public static final String UNITETEST = "CEREMA/DTecITM/CITS/DACSI";
	
	/**
	 * "ADMINISTRATEUR".<br/>
	 */
	public static final String ADMINISTRATEUR = "ADMINISTRATEUR";
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	/**
	 * "objet1 : ".<br/>
	 */
	public static final String OBJET1 = "objet1 : ";
	
	/**
	 * "objetNull1 : ".<br/>
	 */
	public static final String OBJETNULL1 = "objetNull1 : ";
	
	/**
	 * "objet1.equals(objet2EqualsObj1) : ".<br/>
	 */
	public static final String OBJET1_EQUALS_OBJET2 
		= "objet1.equals(objet2EqualsObj1) : ";
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().
	 */
	public static transient IUtilisateurCerbere objet1 
		= new UtilisateurCerbere(1L
				, M
				, PRENOMTEST, NOMTEST
				, "01 60 52 34 51"
				, MAILTEST
				, SERVICETEST, UNITETEST
				, ADMINISTRATEUR
				, null
				, null);
	
	/**
	 * objet1MemeInstance doit être la même instance que objet1.<br/>
	 */
	public static transient IUtilisateurCerbere objet1MemeInstance = objet1;
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().
	 */
	public static transient IUtilisateurCerbere objet2EqualsObj1 
		=  new UtilisateurCerbere(2L
				, M
				, PRENOMTEST, NOMTEST
				, "07 69 52 31 65"
				, MAILTEST
				, SERVICETEST, UNITETEST
				, ADMINISTRATEUR
				, null
				, null);
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().
	 */
	public static transient IUtilisateurCerbere objet3EqualsObj1 
		= new UtilisateurCerbere(3L
				, "Mlle"
				, PRENOMTEST, NOMTEST
				, "05 25 89 87 45"
				, MAILTEST
				, SERVICETEST, UNITETEST
				, ADMINISTRATEUR
				, "DIRA"
				, "44");
	
	/**
	 * objetNull1 et objetNull2 doivent être instanciés 
	 * avec le constructeur d'arité nulle ou avoir 
	 * tous les attributs aux valeurs par défaut.
	 */
	public static transient IUtilisateurCerbere objetNull1 
		= new UtilisateurCerbere(0L
				, null
				, null, null
				, null, null
				, null, null
				, null
				, null
				, null);
	
	/**
	 * objetNull1 et objetNull2 doivent être instanciés 
	 * avec le constructeur d'arité nulle ou avoir 
	 * tous les attributs aux valeurs par défaut.
	 */
	public static transient IUtilisateurCerbere objetNull2 
		=  new UtilisateurCerbere(7L
				, null
				, null, null
				, null, null
				, null, null
				, null
				, null
				, null);
	
	/**
	 * objet1AvecNull et objet2EqualsObjet1AvecNull 
	 * doivent être equals() et avoir certains attributs à null.
	 */
	public static transient IUtilisateurCerbere objet1AvecNull 
		= new UtilisateurCerbere(3L
				, "Mlle"
				, null, "nomTestavecNull"
				, "01 60 81 44 44", null
				, "SG", "SG"
				, "CONSULTANT"
				, "DIRE"
				, "sans restriction");
	
	/**
	 * objet1AvecNull et objet2EqualsObjet1AvecNull 
	 * doivent être equals() et avoir certains attributs à null.
	 */
	public static transient IUtilisateurCerbere objet2EqualsObjet1AvecNull 
		= new UtilisateurCerbere(4L
				, "Mlle"
				, null, "nomTestavecNull"
				, "01 60 81 44 44", null
				, "SG", "SG"
				, "CONSULTANT"
				, "DIRE"
				, "sans restriction");
	
	/**
	 * objetDiff1 doit être différent de objetDiff2
	 */
	public static transient IUtilisateurCerbere objetDiff1 
		= new UtilisateurCerbere(1L
				, M
				, "Laurent", "Sergent"
				, "03 98 87 54 65", "laurent.sergent@free.fr"
				, "CEREMA1", "CEREMA2"
				, ADMINISTRATEUR
				, "CEREMA"
				, null);
	
	/**
	 * objetDiff2 doit être différent de objetDiff1
	 */
	public static transient IUtilisateurCerbere objetDiff2 
		= new UtilisateurCerbere(1L
				, "Mme"
				, "Melanie", "Daisy"
				, "01 21 32 65 54", "melanie.daisy@free.fr"
				, "SG", "SG"
				, ADMINISTRATEUR
				, "FRANCE ENTIERE"
				, "sans objet");
	
	/**
	 * objetDiff1AvecNull doit être différent de objetDiff2AvecNull.<br/>
	 * objetDiff1AvecNull et objetDiff2AvecNull doivent avoir des attributs null.
	 */
	public static transient IUtilisateurCerbere objetDiff1AvecNull 
		= new UtilisateurCerbere(1L
				, M
				, null, "SergentDiff"
				, "08 88 88 88 88", null
				, "CEREMA11", "CEREMA12"
				, ADMINISTRATEUR
				, "CEREMA13"
				, null);
	
	/**
	 * objetDiff1AvecNull doit être différent de objetDiff2AvecNull.<br/>
	 * objetDiff1AvecNull et objetDiff2AvecNull doivent avoir des attributs null.
	 */
	public static transient IUtilisateurCerbere objetDiff2AvecNull 
		= new UtilisateurCerbere(
				1L
				, "Maitre"
				, null, "Daisy"
				, "09 99 99 99 99", null
				, "CEREMA21", "CEREMA22"
				, ADMINISTRATEUR
				, "CEREMA23"
				, "pas de restriction");
	
	/**
	 * objetCompAvant doit être AVANT objetCompApres.
	 */
	public static transient IUtilisateurCerbere objetCompAvant 
		= new UtilisateurCerbere(1L 
				, M
				, "Stacy", "King"
				, "00 01 02 03 04", null
				, SERVICETEST, "Rennes7"
				, ADMINISTRATEUR
				, null
				, null);
	
	/**
	 * objetCompApres doit être APRES objetCompAvant.
	 */
	public static transient IUtilisateurCerbere objetCompApres 
		= new UtilisateurCerbere(1L
				, M
				, "Stacy", "King"
				, "01 02 03 04 05", null
				, SERVICETEST, "Rennes8"
				, ADMINISTRATEUR
				, null
				, null);
	
	/**
	 * clone de objetNull1.<br/>
	 */
	public static transient IUtilisateurCerbere objetNullClone1;
	
	/**
	 * clone de objet1.<br/>
	 */
	public static transient IUtilisateurCerbere objetClone1;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereTest.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * Teste la méthode <b>equals(Object pObject)</b> :
	 * <ul>
	 * <li>garantit le contrat Java reflexif x.equals(x).</li>
	 * <li>garantit le contrat Java symétrique 
	 * x.equals(y) ----> y.equals(x).</li>
	 * <li>garantit le contrat Java transitif 
	 * x.equals(y) et y.equals(z) ----> x.equals(z).</li>
	 * <li>garantit le contrat Java sur les hashcode 
	 * x.equals(y) ----> x.hashcode() == y.hashcode().</li>
	 * <li>garantit que les null sont bien gérés 
	 * dans equals(Object pObj).</li>
	 * <li>garantit que x.equals(null) retourne false 
	 * (avec x non null).</li>
	 * <li>garantit le bon fonctionnement de equals() 
	 * en cas d'égalité métier.</li>
	 * <li>garantit le bon fonctionnement de equals() 
	 * en cas d'inégalité métier.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testEquals() {
					
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE UtilisateurCerbereTest - méthode testEquals() ********** ");
		}

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DE 3 Objets equals ----------------");
			System.out.println("objet1.toString() : " + objet1.toString());
			System.out.println("objet2EqualsObj1.toString() : " + objet2EqualsObj1.toString());
			System.out.println("objet3EqualsObj1.toString() : " + objet3EqualsObj1.toString());
			System.out.println();
			System.out.println("objet1.equals(objet1) : " + objet1.equals(objet1));
			System.out.println(OBJET1_EQUALS_OBJET2 + objet1.equals(objet2EqualsObj1));
			System.out.println("objet2EqualsObj1.equals(objet1) : " + objet2EqualsObj1.equals(objet1));
			System.out.println("objet2EqualsObj1.equals(objet3EqualsObj1) : " + objet2EqualsObj1.equals(objet3EqualsObj1));
			System.out.println("objet1.equals(objet3EqualsObj1) : " + objet1.equals(objet3EqualsObj1));
			System.out.println("objet1.hashCode() == objet2EqualsObj1.hashCode() : " + (objet1.hashCode() == objet2EqualsObj1.hashCode()));
		}
		
		
		/* garantit le contrat Java reflexif x.equals(x). */
		assertEquals("x.equals(x) : "
				, objet1
					, objet1);
				
		/* garantit le contrat Java symétrique 
		 * x.equals(y) ----> y.equals(x). */
		assertEquals(OBJET1_EQUALS_OBJET2
				, objet1
					, objet2EqualsObj1);
		
		assertEquals("objet2EqualsObj1.equals(objet1) : "
				, objet2EqualsObj1
					, objet1);
		
		/* garantit le contrat Java transitif 
		 * x.equals(y) et y.equals(z) ----> x.equals(z). */
		assertEquals(OBJET1_EQUALS_OBJET2, objet1, objet2EqualsObj1);
		assertEquals("objet2EqualsObj1.equals(objet3EqualsObj1) : ", objet2EqualsObj1, objet3EqualsObj1);
		assertEquals("objet1.equals(objet3EqualsObj1) : ", objet1, objet3EqualsObj1);
		
		/* garantit le contrat Java sur les hashcode 
		 * x.equals(y) ----> x.hashcode() == y.hashcode(). */
		assertEquals("objet1.hashCode().equals(objet2EqualsObj1.hashCode()) : "
				, objet1.hashCode()
					, objet2EqualsObj1.hashCode());

				
		/* garantit que les null sont bien gérés dans equals(...). */
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'OBJETS INSTANCIES AVEC LE CONSTRUCTEUR D'ARITE NULLE (ou attributs par défaut) -------");
			System.out.println("OBJETNULL1 : " 
					+ objetNull1.toString());
			System.out.println("OBJETNULL2 : " 
					+ objetNull2.toString());
			System.out.println();
			System.out.println("objetNull1.equals(objetNull2) : " + objetNull1.equals(objetNull2));
			System.out.println("objetNull1.hashCode().equals(objetNull2.hashCode()) : " + (objetNull1.hashCode() == objetNull2.hashCode()));
		}

		assertEquals("objetNull1.equals(objetNull2) : "
				, objetNull1
					, objetNull2);
		assertEquals("objetNull1.hashCode().equals(objetNull2.hashCode()) : "
				, objetNull1.hashCode()
					, objetNull2.hashCode());
		

		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'OBJETS AVEC CERTAINS ATTRIBUTS NULL -----------");
			System.out.println("objet1AvecNull : " 
					+ objet1AvecNull.toString());
			System.out.println("objet2EqualsObjet1AvecNull : " 
					+ objet2EqualsObjet1AvecNull.toString());
			System.out.println();
			System.out.println("objet1AvecNull.equals(objet2EqualsObjet1AvecNull) : " + objet1AvecNull.equals(objet2EqualsObjet1AvecNull));
			System.out.println("objet1AvecNull.hashCode() == objet2EqualsObjet1AvecNull.hashCode()" + (objet1AvecNull.hashCode() == objet2EqualsObjet1AvecNull.hashCode()));
		}

		assertEquals("objet1AvecNull.equals(objet2EqualsObjet1AvecNull) : "
				, objet1AvecNull
					, objet2EqualsObjet1AvecNull);
		assertEquals("objet1AvecNull.hashCode()"
				+ ".equals(objet2EqualsObjet1AvecNull.hashCode()) : "
				, objet1AvecNull.hashCode()
					, objet2EqualsObjet1AvecNull.hashCode());


		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("OBJET EXISTANT EQUALS null --------------------------");
			System.out.println("objet1.toString() : " + objet1.toString());
			System.out.println();
			System.out.println("objet1.equals(null) : " + (objet1 == null));
		}
		
		/* garantit que x.equals(null) retourne false (avec x non null). */
		assertNotNull("objet1 pas equals(null) : "
				, objet1);

		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'EGALITE METIER --------------------");
			System.out.println(OBJET1 
					+ objet1.toString());
			System.out.println("objet2EqualsObj1 : " 
					+ objet2EqualsObj1.toString());
			System.out.println();
			System.out.println("objet1.equals(objet2EqualsObj1) : " + objet1.equals(objet2EqualsObj1));
		}

		
		/* garantit le bon fonctionnement de equals() 
		 * en cas d'égalité métier. */
		assertEquals(OBJET1_EQUALS_OBJET2
				, objet1
					, objet2EqualsObj1);
		

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DE 2 Objets DIFFERENTS (pas equals) ---------------------");
			System.out.println("objetDiff1 : " 
					+ objetDiff1.toString());
			System.out.println("objetDiff2 : " 
					+ objetDiff2.toString());
			System.out.println();
			System.out.println("objetDiff1.equals(objetDiff2) : " + objetDiff1.equals(objetDiff2));
			System.out.println("objetDiff1.hashcode() == objetDiff2.hashcode() : " + (objetDiff1.hashCode() == objetDiff2.hashCode()));
		}
		
		/* garantit le bon fonctionnement de equals() 
		 * en cas d'inégalité métier. */
		assertFalse("objetDiff1 PAS equals(objetDiff2) : "
				, objetDiff1.equals(objetDiff2));
		assertFalse("objetDiff1.hashCode() "
				+ "PAS equals(objetDiff2.hashCode()) : "
				, objetDiff1.hashCode() == objetDiff2.hashCode());
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DE 2 Objets DIFFERENTS (pas equals) avec des attributs null---------------------");
			System.out.println("objetDiff1AvecNull : " 
					+ objetDiff1AvecNull.toString());
			System.out.println("objetDiff2AvecNull : " 
					+ objetDiff2AvecNull.toString());
			System.out.println();
			System.out.println("objetDiff1AvecNull.equals(objetDiff2AvecNull) : " + objetDiff1AvecNull.equals(objetDiff2AvecNull));
			System.out.println("objetDiff1AvecNull.hashcode() == objetDiff2AvecNull.hashcode() : " + (objetDiff1AvecNull.hashCode() == objetDiff2AvecNull.hashCode()));
		}
		
		/* garantit le bon fonctionnement de equals() 
		 * en cas d'inégalité métier. */
		assertFalse("objetDiff1AvecNull PAS equals(objetDiff2AvecNull) : "
				, objetDiff1AvecNull.equals(objetDiff2AvecNull));
		assertFalse("objetDiff1AvecNull.hashCode() "
				+ "PAS equals(objetDiff2AvecNull.hashCode()) : "
				, objetDiff1AvecNull.hashCode() == objetDiff2AvecNull.hashCode());
				
	} // Fin de testEquals().______________________________________________



	/**
	 * Teste la méthode <b>compareTo(...)</b> :
	 * <ul>
	 * <li>garantit que compareTo(memeInstance) retourne 0.</li>
	 * <li>garantit que compareTo(null) retourne un nombre négatif.</li>
	 * <li>garantit le contrat Java Contrat Java : 
	 * x.equals(y) ---> x.compareTo(y) == 0.</li>
	 * <li>garantit que les null sont bien gérés 
	 * dans compareTo(...).</li>
	 * <li>garantit le bon fonctionnement (bon ordre) de compareTo().</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCompareTo() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE UtilisateurCerbereTest - méthode testCompareTo() ********** ");
		}

		
		/* garantit que compareTo(memeInstance) retourne 0. */		
		final int compareMemeInstance 
			= objet1.compareTo(objet1MemeInstance);

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE 2 MEMES INSTANCES ------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println("objet1MemeInstance : " + objet1MemeInstance.toString());
			System.out.println();
			System.out.println("COMPARETO() DE LA MEME INSTANCE (objet1.compareTo(objet1MemeInstance)) : " 
					+ compareMemeInstance);
		}
		
		assertTrue("compareTo(memeInstance) doit retourner 0 : "
				, compareMemeInstance == 0);

		
		/* garantit que compareTo(null) retourne -1. */
		final int compareToNull = objet1.compareTo(null);

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE objet1 avec null ---------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println();
			System.out.println("COMPARETO(null) (objet1.compareTo(null)) : " 
					+ compareToNull);
		}
		
		assertTrue("compareTo(null) doit retourner négatif : "
				, compareToNull < 0);

		
		/* garantit le contrat Java Contrat Java : 
		 * x.equals(y) ---> x.compareTo(y) == 0. */		
		final int compareToEquals 
			= objet2EqualsObj1.compareTo(objet3EqualsObj1);

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE 2 objets equals ---------------------");
			System.out.println("objet2EqualsObj1 : " + objet2EqualsObj1.toString());
			System.out.println("objet3EqualsObj1 : " + objet3EqualsObj1.toString());
			System.out.println();
			System.out.println("COMPARETO(2 objets equals)  (objet2EqualsObj1.compareTo(objet3EqualsObj1)) : " 
					+ compareToEquals);
		}

		assertTrue("Instance.compareTo(equalsInstance) doit retourner 0 : "
				, compareToEquals == 0);
		assertTrue("loc10.hashCode() == loc11.hashCode() : "
				, objet2EqualsObj1.hashCode() == objet3EqualsObj1.hashCode());
		
		
		
		/* garantit que les null sont bien gérés dans 
		 * compareTo(...). */		
		final int compareToEqualsNull 
			= objetNull1.compareTo(objetNull2);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE 2 Objets INSTANCIES AVEC LE CONSTRUCTEUR D'ARITE NULLE (ou valeurs par défaut) ------------");
			System.out.println(OBJETNULL1 + objetNull1.toString());
			System.out.println("objetNull2 : " + objetNull2.toString());
			System.out.println();
			System.out.println("COMPARETO(2 objets instanciés avec le constructeur d'arite nulle)  (objetNull1.compareTo(objetNull2)) : " 
					+ compareToEqualsNull);
		}
		
		assertTrue("InstanceNull.compareTo(equalsInstanceNull) doit retourner 0 : "
				, compareToEqualsNull == 0);
		assertTrue("locNull10.hashCode() == locNull11.hashCode() : "
				, objetNull1.hashCode() == objetNull2.hashCode());
		

		
		/* garantit le bon fonctionnement (bon ordre) de compareTo(). */		
		final int compare 
			= objetCompAvant.compareTo(objetCompApres);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE 2 Objets différents -------------");
			System.out.println("objetCompAvant : " + objetCompAvant.toString());
			System.out.println("objetCompApres : " + objetCompApres.toString());
			System.out.println();
			System.out.println("COMPARETO(2 objets différents) (objetCompAvant.compareTo(this.objetCompApres) : " 
					+ compare);
		}
		
		assertTrue("objetCompAvant doit être avant objetCompApres : "
				, compare < 0);
		
	} // Fin de testCompareTo().___________________________________________
	

	
	/**
	 * Teste la méthode <b>clone()</b> :
	 * <ul>
	 * <li>garantit que les null sont bien gérés dans clone().</li>
	 * <li>garantit que clonex.equals(x).</li>
	 * <li>garantit que x et son clone ne sont pas la même instance.</li>
	 * </ul>
	 * @throws CloneNotSupportedException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testClone() throws CloneNotSupportedException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE UtilisateurCerbereTest - méthode testClone() ********** ");
		}

		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DU CLONAGE D'OBJET INSTANCIES AVEC LE CONSTRUCTEUR D'ARITE NULLE ---------------------");
			System.out.println(OBJETNULL1 
					+ objetNull1.toString());
			System.out.println("objetNullClone1 : " 
					+ objetNullClone1.toString());
			System.out.println();
			System.out.println("objetNull1.equals(objetNullClone1) : " + objetNull1.equals(objetNullClone1));
		}
		
		/* garantit que les null sont bien gérés dans clone(). */
		assertEquals("objetNull1.equals(objetNullClone1) : "
				, objetNull1
					, objetNullClone1);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DU CLONAGE D'OBJETS ORDINAIREMENT INSTANCIES --------------------");
			System.out.println(OBJET1 
					+ objet1.toString());
			System.out.println("objetClone1 : " 
					+ objetClone1.toString());
			System.out.println();
			System.out.println("objet1.equals(objetClone1) : " + objet1.equals(objetClone1));
		}
		
		/* garantit que clone(x).equals(x). */
		assertEquals("clone(x).equals(x) : "
				, objet1
					, objetClone1);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("INSTANCE ET CLONE DIFFERENTS --------------------");
			System.out.println(OBJET1 
					+ objet1.toString());
			System.out.println("objetClone1 : " 
					+ objetClone1.toString());
			System.out.println();
			System.out.println("objet1 == objetClone1 : " + (objet1 == objetClone1));    // NOPMD by dan on 01/05/19 09:10
		}
		
		/* garantit que x et son clone ne sont pas la même instance. */
		assertNotSame("x != clonex : "
				, objet1
					, objetClone1);
				
	} // Fin de testClone()._______________________________________________
	
	
		
	/**
	 * Teste la méthode <b>toString()</b> :
	 * <ul>
	 * <li>garantit que les null sont bien gérés dans toString().</li>
	 * <li>garantit le bon affichage de toString().</li>
	 * <li><b>Pour les Développeurs : 
	 * Adapter la chaîne affichée dans les assertEquals</b></li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testToString() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE UtilisateurCerbereTest - méthode testToString() ********** ");
		}
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(objetNull1.toString());
		}
		
		/* garantit que les null sont bien gérés dans toString(). */
		assertEquals("objetNull1.toString() retourne une chaine : "
				, "UtilisateurCerbere [id=0, civilite=null, prenom=null, nom=null, tel=null, email=null, service=null, unite=null, profil=null, portee=null, restriction=null]"
						, objetNull1.toString());
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(objet1.toString());
		}
		
		/* garantit le bon affichage de toString(). */
		assertEquals("affichage : "
				, "UtilisateurCerbere [id=1, civilite=M., prenom=prenomTest, nom=nomTest, tel=01 60 52 34 51, email=test.test@yahoo.fr, service=CEREMA, unite=CEREMA/DTecITM/CITS/DACSI, profil=ADMINISTRATEUR, portee=null, restriction=null]"
						, objet1.toString());
				
	} // Fin de testToString().____________________________________________
	

	
	/**
	 * Teste la méthode <b>fournirEnTeteCsv()</b> :
	 * <ul>
	 * <li>garantit que fournirEnTeteCsv() retourne le bon en-tête csv.</li>
	 * <li><b>Pour les Développeurs : 
	 * Adapter la chaîne affichée dans les assertEquals</b></li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirEnTeteCsv() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE UtilisateurCerbereTest - méthode testFournirEnTeteCsv() ********** ");
		}
	

		/* garantit que fournirEnTeteCsv() retourne le bon en-tête csv. */
		final String entete = objet1.fournirEnTeteCsv();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("EN-TETE CSV ------------------------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println();
			System.out.println("En-tête csv : " + entete);
		}
		
		assertEquals("en-tête csv : "
				, "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;"
					, entete);
				
	} // Fin de testFournirEnTeteCsv().____________________________________
	

	
	/**
	 * Teste la méthode <b>fournirStringCsv()</b> :
	 * <ul>
	 * <li>garantit que les null sont gérés dans fournirStringCsv().</li> 
	 * <li>garantit que fournirStringCsv() retourne la bonne ligne csv.</li>
	 * <li><b>Adapter la chaîne affichée dans les assertEquals</b></li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirStringCsv() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE UtilisateurCerbereTest - méthode testFournirStringCsv() ********** ");
		}

		/* garantit que les null sont gérés dans fournirStringCsv(). */
		final String ligneCsvNull = objetNull1.fournirStringCsv();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("STRING CSV D' UN OBJET INSTANCIE AVEC LE CONSTRUCTEUR D'ARITE NULLE -----------------------");
			System.out.println("objetNull1.toString() : " + objetNull1.toString());
			System.out.println();
			System.out.println("objetNull1.fournirStringCsv() : " 
					+ ligneCsvNull);
		}

		assertEquals("ligne csv null = "
				, "0;null;null;null;null;null;null;null;null;null;null;"
					, ligneCsvNull);
		
						
		/* garantit que fournirStringCsv() retourne la bonne ligne csv. */
		final String ligneCsv = objet1.fournirStringCsv();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("STRING CSV D' UN OBJET INSTANIE AVEC LE CONSTRUCTEUR ORDINAIRE -----------------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println();
			System.out.println("objet1.fournirStringCsv() = " + ligneCsv);
		}

		assertEquals("ligne csv : "
				, "1;M.;prenomTest;nomTest;01 60 52 34 51;test.test@yahoo.fr;CEREMA;CEREMA/DTecITM/CITS/DACSI;ADMINISTRATEUR;null;null;"
					, ligneCsv);
				
	} // Fin de testFournirStringCsv().____________________________________
	

	
	/**
	 * Teste la méthode <b>fournirEnTeteColonne(int pI)</b> :
	 * <ul>
	 * <li>garantit que les null sont gérés dans 
	 * fournirEnTeteColonne(int pI).</li> 
	 * <li>garantit que fournirEnTeteColonne(int pI) retourne 
	 * la bonne en-tête de colonne.</li>
	 * <li><b>Compléter les colonnes.</b></li>
	 * <li><b>Adapter la chaîne affichée dans les assertEquals</b></li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirEnTeteColonne() {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE UtilisateurCerbereTest - méthode testFournirEnTeteColonne() ********** ");
		}
		
		/* garantit que les null sont gérés 
		 * dans fournirEnTeteColonne(int pI). */		
		final String enteteNull0 = objetNull1.fournirEnTeteColonne(0);
		final String enteteNull1 = objetNull1.fournirEnTeteColonne(1);
		final String enteteNull2 = objetNull1.fournirEnTeteColonne(2);
		final String enteteNull3 = objetNull1.fournirEnTeteColonne(3);
		final String enteteNull4 = objetNull1.fournirEnTeteColonne(4);
		final String enteteNull5 = objetNull1.fournirEnTeteColonne(5);
		final String enteteNull6 = objetNull1.fournirEnTeteColonne(6);
		final String enteteNull7 = objetNull1.fournirEnTeteColonne(7);
		final String enteteNull8 = objetNull1.fournirEnTeteColonne(8);
		final String enteteNull9 = objetNull1.fournirEnTeteColonne(9);
		final String enteteNull10 = objetNull1.fournirEnTeteColonne(10);
		final String enteteNull11 = objetNull1.fournirEnTeteColonne(11);
		
		/* garantit que fournirEnTeteColonne(int pI) retourne 
		 * la bonne en-tête de colonne. */
		final String entete0 = objet1.fournirEnTeteColonne(0);
		final String entete1 = objet1.fournirEnTeteColonne(1);
		final String entete2 = objet1.fournirEnTeteColonne(2);
		final String entete3 = objet1.fournirEnTeteColonne(3);
		final String entete4 = objet1.fournirEnTeteColonne(4);
		final String entete5 = objet1.fournirEnTeteColonne(5);
		final String entete6 = objet1.fournirEnTeteColonne(6);
		final String entete7 = objet1.fournirEnTeteColonne(7);
		final String entete8 = objet1.fournirEnTeteColonne(8);
		final String entete9 = objet1.fournirEnTeteColonne(9);
		final String entete10 = objet1.fournirEnTeteColonne(10);
		final String entete11 = objet1.fournirEnTeteColonne(11);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET1 + objet1.toString());
			System.out.println(OBJETNULL1 + objetNull1.toString());
			System.out.println();
			System.out.println("EN-TETES JTABLE -----------------------------");
			System.out.println("entete0 (objet1.fournirEnTeteColonne(0)) : " + entete0);
			System.out.println("enteteNull0 (objetNull1.fournirEnTeteColonne(0)) : " + enteteNull0);
			System.out.println();
			System.out.println("entete1 (objet1.fournirEnTeteColonne(1)) : " + entete1);
			System.out.println("enteteNull1 (objetNull1.fournirEnTeteColonne(1)) : " + enteteNull1);
			System.out.println();
			System.out.println("entete2 (objet1.fournirEnTeteColonne(2)) : " + entete2);
			System.out.println("enteteNull2 (objetNull1.fournirEnTeteColonne(2)) : " + enteteNull2);			
			System.out.println();
			System.out.println("entete3 (objet1.fournirEnTeteColonne(3)) : " + entete3);
			System.out.println("enteteNull3 (objetNull1.fournirEnTeteColonne(3)) : " + enteteNull3);			
			System.out.println();
			System.out.println("entete4 (objet1.fournirEnTeteColonne(4)) : " + entete4);
			System.out.println("enteteNull4 (objetNull1.fournirEnTeteColonne(4)) : " + enteteNull4);			
			System.out.println();
			System.out.println("entete5 (objet1.fournirEnTeteColonne(5)) : " + entete5);
			System.out.println("enteteNull5 (objetNull1.fournirEnTeteColonne(5)) : " + enteteNull5);			
			System.out.println();
			System.out.println("entete6 (objet1.fournirEnTeteColonne(6)) : " + entete6);
			System.out.println("enteteNull6 (objetNull1.fournirEnTeteColonne(6)) : " + enteteNull6);			
			System.out.println();
			System.out.println("entete7 (objet1.fournirEnTeteColonne(7)) : " + entete7);
			System.out.println("enteteNull7 (objetNull1.fournirEnTeteColonne(7)) : " + enteteNull7);			
			System.out.println();
			System.out.println("entete8 (objet1.fournirEnTeteColonne(8)) : " + entete8);
			System.out.println("enteteNull8 (objetNull1.fournirEnTeteColonne(8)) : " + enteteNull8);			
			System.out.println();
			System.out.println("entete9 (objet1.fournirEnTeteColonne(9)) : " + entete9);
			System.out.println("enteteNull9 (objetNull1.fournirEnTeteColonne(9)) : " + enteteNull9);			
			System.out.println();
			System.out.println("entete10 (objet1.fournirEnTeteColonne(10)) : " + entete10);
			System.out.println("enteteNull10 (objetNull1.fournirEnTeteColonne(10)) : " + enteteNull10);			
			System.out.println();
			System.out.println("entete11 (objet1.fournirEnTeteColonne(11)) : " + entete11);
			System.out.println("enteteNull11 (objetNull1.fournirEnTeteColonne(11)) : " + enteteNull11);			
			
		}

		assertEquals("entete0 : ", "id", entete0);
		assertEquals("enteteNull0 : ", "id", enteteNull0);
		
		assertEquals("entete1 : ", "civilité", entete1);
		assertEquals("enteteNull1 : ", "civilité", enteteNull1);
				

	} // Fin de testFournirEnTeteColonne().________________________________
	
	
	
	/**
	 * Teste la méthode <b>fournirValeurColonne(int pI)</b> :
	 * <ul>
	 * <li>garantit que les null sont gérés dans 
	 * fournirValeurColonne(int pI).</li> 
	 * <li>garantit que fournirValeurColonne(int pI) retourne 
	 * la bonne valeur de colonne.</li>
	 * <li><b>Compléter les colonnes.</b></li>
	 * <li><b>Adapter la chaîne affichée dans les assertEquals</b></li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirValeurColonne() {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE UtilisateurCerbereTest - méthode testFournirValeurColonne() ********** ");
		}
		
		/* garantit que les null sont gérés 
		 * dans fournirValeurColonne(int pI). */
		final String valeurNull0 = (String) objetNull1.fournirValeurColonne(0);
		final String valeurNull1 = (String) objetNull1.fournirValeurColonne(1);
		final String valeurNull2 = (String) objetNull1.fournirValeurColonne(2);
		final String valeurNull3 = (String) objetNull1.fournirValeurColonne(3);
		final String valeurNull4 = (String) objetNull1.fournirValeurColonne(4);
		final String valeurNull5 = (String) objetNull1.fournirValeurColonne(5);
		final String valeurNull6 = (String) objetNull1.fournirValeurColonne(6);
		final String valeurNull7 = (String) objetNull1.fournirValeurColonne(7);
		final String valeurNull8 = (String) objetNull1.fournirValeurColonne(8);
		final String valeurNull9 = (String) objetNull1.fournirValeurColonne(9);
		final String valeurNull10 = (String) objetNull1.fournirValeurColonne(10);
		final String valeurNull11 = (String) objetNull1.fournirValeurColonne(11);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'UN OBJET INSTANCIE AVEC LE CONSTRUCTEUR D'ARITE NULLE --------------------");
			System.out.println(OBJETNULL1 + objetNull1.toString());
			System.out.println();
			System.out.println("valeurNull0 ((String) objetNull1.fournirValeurColonne(0)) : " + valeurNull0);
			System.out.println("valeurNull1 ((String) objetNull1.fournirValeurColonne(1)) : " + valeurNull1);
			System.out.println("valeurNull2 ((String) objetNull1.fournirValeurColonne(2)) : " + valeurNull2);
			System.out.println("valeurNull3 ((String) objetNull1.fournirValeurColonne(3)) : " + valeurNull3);
			System.out.println("valeurNull4 ((String) objetNull1.fournirValeurColonne(4)) : " + valeurNull4);
			System.out.println("valeurNull5 ((String) objetNull1.fournirValeurColonne(5)) : " + valeurNull5);
			System.out.println("valeurNull6 ((String) objetNull1.fournirValeurColonne(6)) : " + valeurNull6);
			System.out.println("valeurNull7 ((String) objetNull1.fournirValeurColonne(7)) : " + valeurNull7);
			System.out.println("valeurNull8 ((String) objetNull1.fournirValeurColonne(8)) : " + valeurNull8);
			System.out.println("valeurNull9 ((String) objetNull1.fournirValeurColonne(9)) : " + valeurNull9);
			System.out.println("valeurNull10 ((String) objetNull1.fournirValeurColonne(10)) : " + valeurNull10);
			System.out.println("valeurNull11 ((String) objetNull1.fournirValeurColonne(11)) : " + valeurNull11);
		}

		assertEquals("valeurNull0 ((String) objetNull1.fournirValeurColonne(0)) : ", "0", valeurNull0);
		assertEquals("valeurNull1 ((String) objetNull1.fournirValeurColonne(1)) : ", null, valeurNull1);

		
		/* garantit que fournirValeurColonne(int pI) retourne 
		 * la bonne en-tête de colonne. */
		final String valeur0 = (String) objet1.fournirValeurColonne(0);
		final String valeur1 = (String) objet1.fournirValeurColonne(1);
		final String valeur2 = (String) objet1.fournirValeurColonne(2);
		final String valeur3 = (String) objet1.fournirValeurColonne(3);
		final String valeur4 = (String) objet1.fournirValeurColonne(4);
		final String valeur5 = (String) objet1.fournirValeurColonne(5);
		final String valeur6 = (String) objet1.fournirValeurColonne(6);
		final String valeur7 = (String) objet1.fournirValeurColonne(7);
		final String valeur8 = (String) objet1.fournirValeurColonne(8);
		final String valeur9 = (String) objet1.fournirValeurColonne(9);
		final String valeur10 = (String) objet1.fournirValeurColonne(10);
		final String valeur11 = (String) objet1.fournirValeurColonne(11);

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {	
			System.out.println();
			System.out.println("CAS D'UN OBJET INSTANCIE AVEC LE CONSTRUCTEUR ORDINAIRE -------------------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println();
			System.out.println("valeur0 ((String) objet1.fournirValeurColonne(0)) : " + valeur0);
			System.out.println("valeur1 ((String) objet1.fournirValeurColonne(1)) : " + valeur1);
			System.out.println("valeur2 ((String) objet1.fournirValeurColonne(2)) : " + valeur2);
			System.out.println("valeur3 ((String) objet1.fournirValeurColonne(3)) : " + valeur3);
			System.out.println("valeur4 ((String) objet1.fournirValeurColonne(4)) : " + valeur4);
			System.out.println("valeur5 ((String) objet1.fournirValeurColonne(5)) : " + valeur5);
			System.out.println("valeur6 ((String) objet1.fournirValeurColonne(6)) : " + valeur6);
			System.out.println("valeur7 ((String) objet1.fournirValeurColonne(7)) : " + valeur7);
			System.out.println("valeur8 ((String) objet1.fournirValeurColonne(8)) : " + valeur8);
			System.out.println("valeur9 ((String) objet1.fournirValeurColonne(9)) : " + valeur9);
			System.out.println("valeur10 ((String) objet1.fournirValeurColonne(10)) : " + valeur10);
			System.out.println("valeur11 ((String) objet1.fournirValeurColonne(11)) : " + valeur11);
		}
		
		assertEquals("valeur0 ((String) objet1.fournirValeurColonne(0)) : ", "1", valeur0);		
		assertEquals("valeur1 ((String) objet1.fournirValeurColonne(1)) : ", "M.", valeur1);
		
	} // Fin de testFournirValeurColonne().________________________________
	

	
	/**
	 * method avantTests() :<br/>
	 * <ul>
	 * <li>instructions exécutées <b>avant l'ensemble des tests</b> 
	 * de la classe JUnit.</li>
	 * <li><b>A REMPLIR A LA MAIN</b></li>
	 * </ul>
	 * @throws CloneNotSupportedException 
	 */
	@BeforeClass
   public static void avantTests() throws CloneNotSupportedException {
		
		objetNullClone1 = objetNull1.clone();
		objetClone1 = objet1.clone();
		
	} // Fin de avantTests().______________________________________________

	
	
} // FIN DE LA CLASSE UtilisateurCerbereTest.--------------------------------
