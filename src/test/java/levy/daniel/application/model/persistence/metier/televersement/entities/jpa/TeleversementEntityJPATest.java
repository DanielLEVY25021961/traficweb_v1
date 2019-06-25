package levy.daniel.application.model.persistence.metier.televersement.entities.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.time.LocalDateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.model.metier.anneegestion.IAnneeGestion;
import levy.daniel.application.model.metier.televersement.EnumTypeFichierDonnees;
import levy.daniel.application.model.metier.televersement.ITeleversement;
import levy.daniel.application.model.metier.utilisateur.EnumGestionnaire;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.persistence.metier.anneegestion.entities.jpa.AnneeGestionEntityJPA;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.UtilisateurCerbereEntityJPA;
import levy.daniel.application.model.services.valideurs.metier.utilisateurs.EnumCivilites;

/**
 * CLASSE TeleversementEntityJPATest :<br/>
 * Test JUnit de la classe {@link TeleversementEntityJPA}.<br/>
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
 * @since 21 juin 2019
 *
 */
public class TeleversementEntityJPATest {

	// ************************ATTRIBUTS************************************/

	/**
	 * "titi".
	 */
	public static final String TITI = "titi";

	/**
	 * "HITDIRA2018".
	 */
	public static final String NOM_FICHIER_HITDIRA2018 = "HITDIRA2018";
	
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
	 * "DIRA".<br/>
	 */
	public static final String SERVICE_DIRA = "DIRA";	
	
	/**
	 * "DIRCE".<br/>
	 */
	public static final String SERVICE_DIRCE = "DIRCE";	
	
	/**
	 * "CEREMA/DTecITM/CITS/DACSI".<br/>
	 */
	public static final String UNITETEST = "CEREMA/DTecITM/CITS/DACSI";
	
	/**
	 * "DIRA/SIEER/CIGT".<br/>
	 */
	public static final String UNITE_DIRA_SIEER_CIGT = "DIRA/SIEER/CIGT";
	
	/**
	 * "ADMINISTRATEUR".<br/>
	 */
	public static final String PROFIL_ADMINISTRATEUR = "ADMINISTRATEUR";
	
	/**
	 * "GESTIONNAIRE".<br/>
	 */
	public static final String PROFIL_GESTIONNAIRE = "GESTIONNAIRE";

	/**
	 * ADMINISTRATEUR.<br/>
	 */
	public static final IUtilisateurCerbere UTILISATEUR_ADMIN_1 
		= new UtilisateurCerbereEntityJPA(
				EnumCivilites.MONSIEUR.getAbreviation()
				, "papy", "Gonzales"
				, "01 68 97 36 12", "papy.gonzales@free.fr"
				, SERVICETEST, UNITETEST
				, PROFIL_ADMINISTRATEUR, "France entière", null);
	
	/**
	 * DIRA.<br/>
	 */
	public static final transient IUtilisateurCerbere UTILISATEUR_DIRA_1 
	= new UtilisateurCerbereEntityJPA(3L
			, EnumCivilites.MADEMOISELLE.getAbreviation()
			, PRENOMTEST, NOMTEST
			, "05 25 89 87 45"
			, MAILTEST
			, SERVICE_DIRA, UNITE_DIRA_SIEER_CIGT
			, PROFIL_GESTIONNAIRE
			, "DIRA"
			, "44");
	
	/**
	 * LocalDateTime.of(2019, 6, 13, 8, 37, 43).<br/>
	 */
	public static final LocalDateTime DATE_TELEVERSEMENT_1 
		= LocalDateTime.of(2019, 6, 13, 8, 37, 43);

	/**
	 * LocalDateTime.of(2018, 6, 13, 8, 37, 43).<br/>
	 */
	public static final LocalDateTime DATE_TELEVERSEMENT_2 
		= LocalDateTime.of(2018, 6, 13, 8, 37, 43);
	
	/**
	 * LocalDateTime.of(2018, 1, 13, 8, 37, 43).<br/>
	 */
	public static final LocalDateTime DATE_TELEVERSEMENT_3 
		= LocalDateTime.of(2018, 1, 13, 8, 37, 43);
	
	/**
	 * 2016.<br/>
	 */
	public static final IAnneeGestion ANNEE_GESTION_2016 
		= new AnneeGestionEntityJPA("2016");
	
	/**
	 * 2017.<br/>
	 */
	public static final IAnneeGestion ANNEE_GESTION_2017 
		= new AnneeGestionEntityJPA("2017");
	
	/**
	 * 2018.<br/>
	 */
	public static final IAnneeGestion ANNEE_GESTION_2018 
		= new AnneeGestionEntityJPA("2018");
	
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
	public static transient ITeleversement objet1 
		= new TeleversementEntityJPA(
					1L
					, DATE_TELEVERSEMENT_1
						, UTILISATEUR_DIRA_1
							, EnumGestionnaire.DIRA
								, EnumTypeFichierDonnees.HIT
									, NOM_FICHIER_HITDIRA2018
										, new File("toto")
											, ANNEE_GESTION_2018);
	
	/**
	 * objet1MemeInstance doit être la même instance que objet1.<br/>
	 */
	public static transient ITeleversement objet1MemeInstance = objet1;
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().
	 */
	public static transient ITeleversement objet2EqualsObj1 
		=  new TeleversementEntityJPA(
				2L
				, DATE_TELEVERSEMENT_1
					, UTILISATEUR_DIRA_1
						, EnumGestionnaire.DIRA
							, EnumTypeFichierDonnees.HIT
								, NOM_FICHIER_HITDIRA2018
									, new File("tata")
										, ANNEE_GESTION_2018);
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().
	 */
	public static transient ITeleversement objet3EqualsObj1 
		= new TeleversementEntityJPA(
				3L
				, DATE_TELEVERSEMENT_1
					, UTILISATEUR_DIRA_1
						, EnumGestionnaire.DIRA
							, EnumTypeFichierDonnees.HIT
								, NOM_FICHIER_HITDIRA2018
									, new File(TITI)
										, ANNEE_GESTION_2018);
	
	/**
	 * objetNull1 et objetNull2 doivent être instanciés 
	 * avec le constructeur d'arité nulle ou avoir 
	 * tous les attributs aux valeurs par défaut.
	 */
	public static transient ITeleversement objetNull1 
		= new TeleversementEntityJPA(null, null, null, null, null, null, null, null);
	
	/**
	 * objetNull1 et objetNull2 doivent être instanciés 
	 * avec le constructeur d'arité nulle ou avoir 
	 * tous les attributs aux valeurs par défaut.
	 */
	public static transient ITeleversement objetNull2 
		=  new TeleversementEntityJPA(null, null, null, null, null, null, null, null);
	
	/**
	 * objet1AvecNull et objet2EqualsObjet1AvecNull 
	 * doivent être equals() et avoir certains attributs à null.
	 */
	public static transient ITeleversement objet1AvecNull 
		= new TeleversementEntityJPA(
				1L
				, DATE_TELEVERSEMENT_1
					, UTILISATEUR_DIRA_1
						, EnumGestionnaire.DIRA
							, EnumTypeFichierDonnees.HIT
								, null
									, new File(TITI)
										, ANNEE_GESTION_2018);
	
	/**
	 * objet1AvecNull et objet2EqualsObjet1AvecNull 
	 * doivent être equals() et avoir certains attributs à null.
	 */
	public static transient ITeleversement objet2EqualsObjet1AvecNull 
		= new TeleversementEntityJPA(
				3L
				, DATE_TELEVERSEMENT_1
					, UTILISATEUR_DIRA_1
						, EnumGestionnaire.DIRA
							, EnumTypeFichierDonnees.HIT
								, null
									, new File(TITI)
										, ANNEE_GESTION_2018);
	
	/**
	 * objetDiff1 doit être différent de objetDiff2
	 */
	public static transient ITeleversement objetDiff1 
		= new TeleversementEntityJPA(
				3L
				, DATE_TELEVERSEMENT_1
					, UTILISATEUR_DIRA_1
						, EnumGestionnaire.DIRA
							, EnumTypeFichierDonnees.HIT
								, NOM_FICHIER_HITDIRA2018
									, new File(TITI)
										, ANNEE_GESTION_2018);
	
	/**
	 * objetDiff2 doit être différent de objetDiff1
	 */
	public static transient ITeleversement objetDiff2 
		= new TeleversementEntityJPA(
				3L
				, DATE_TELEVERSEMENT_2
					, UTILISATEUR_DIRA_1
						, EnumGestionnaire.DIRA
							, EnumTypeFichierDonnees.HIT
								, NOM_FICHIER_HITDIRA2018
									, new File(TITI)
										, ANNEE_GESTION_2018);
	
	/**
	 * objetCompAvant doit être AVANT objetCompApres.
	 */
	public static transient ITeleversement objetCompAvant 
		= new TeleversementEntityJPA(
				1L
				, DATE_TELEVERSEMENT_1
					, UTILISATEUR_DIRA_1
						, EnumGestionnaire.DIRA
							, EnumTypeFichierDonnees.HIT
								, NOM_FICHIER_HITDIRA2018
									, new File("toto")
										, ANNEE_GESTION_2018);
	
	/**
	 * objetCompApres doit être APRES objetCompAvant.
	 */
	public static transient ITeleversement objetCompApres 
		= new TeleversementEntityJPA(
				1L
				, DATE_TELEVERSEMENT_1
					, UTILISATEUR_DIRA_1
						, EnumGestionnaire.DIRCE
							, EnumTypeFichierDonnees.HIT
								, "HITDIRCE2018"
									, new File("toto")
										, ANNEE_GESTION_2018);
	
	/**
	 * clone de objetNull1.<br/>
	 */
	public static transient ITeleversement objetNullClone1;
	
	/**
	 * clone de objet1.<br/>
	 */
	public static transient ITeleversement objetClone1;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(TeleversementEntityJPATest.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public TeleversementEntityJPATest() {
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
		System.out.println("********** CLASSE TeleversementEntityJPATest - méthode testEquals() ********** ");
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
		System.out.println("********** CLASSE TeleversementEntityJPATest - méthode testCompareTo() ********** ");
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
			System.out.println("********** CLASSE TeleversementEntityJPATest - méthode testClone() ********** ");
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
			System.out.println("objet1 == objetClone1 : " + (objet1 == objetClone1));    
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
			System.out.println("********** CLASSE TeleversementEntityJPATest - méthode testToString() ********** ");
		}
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("objetNull1.toString() : " + objetNull1.toString());
		}
		
		/* garantit que les null sont bien gérés dans toString(). */
		assertEquals("objetNull1.toString() retourne une chaine : "
				, "TeleversementEntityJPA [id=null, dateTeleversement=null, utilisateur=null, gestionnaire=null, typeFichier=null, nomFichierTeleverse=null, fichierStockeServeur=null, anneeGestion=null]"
						, objetNull1.toString());
		
		final TeleversementEntityJPA objetCaste = (TeleversementEntityJPA) objet1;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("objet1.toString() : " + objet1.toString());
			System.out.println("FichierStockeServeurString : " + objetCaste.getFichierStockeServeurString());
		}
		
		/* garantit le bon affichage de toString(). */
		assertEquals("affichage : "
				, "TeleversementEntityJPA [id=1, dateTeleversement=2019-06-13_08_37_43, utilisateur=nomTest, gestionnaire=DIRA, typeFichier=HIT, nomFichierTeleverse=HITDIRA2018, fichierStockeServeur=toto, anneeGestion=2018]"
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
			System.out.println("********** CLASSE TeleversementEntityJPATest - méthode testFournirEnTeteCsv() ********** ");
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
				, "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;nomFichierTeleverse;fichierStockeServeur;anneeGestion;"
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
			System.out.println("********** CLASSE TeleversementEntityJPATest - méthode testFournirStringCsv() ********** ");
		}

		/* garantit que les null sont gérés dans fournirStringCsv(). */
		final String ligneCsvNull = objetNull1.fournirStringCsv();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("STRING CSV D' UN OBJET INSTANCIE AVEC LE CONSTRUCTEUR D'ARITE NULLE -----------------------");
			System.out.println("objetNull1.toString() : " + objetNull1.toString());
			System.out.println();
			System.out.println("objetNull1.fournirStringCsv() = " 
					+ ligneCsvNull);
		}

		assertEquals("ligne csv null = "
				, "null;null;null;null;null;null;null;null;"
					, ligneCsvNull);
		
						
		/* garantit que fournirStringCsv() retourne la bonne ligne csv. */
		final String ligneCsv = objet1.fournirStringCsv();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("STRING CSV D' UN OBJET INSTANCIE AVEC LE CONSTRUCTEUR ORDINAIRE -----------------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println();
			System.out.println("objet1.fournirStringCsv() = " + ligneCsv);
		}

		assertEquals("ligne csv : "
				, "1;2019-06-13_08_37_43;nomTest;DIRA;HIT;HITDIRA2018;toto;2018;"
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
			System.out.println("********** CLASSE TeleversementEntityJPATest - méthode testFournirEnTeteColonne() ********** ");
		}
		
		/* garantit que les null sont gérés 
		 * dans fournirEnTeteColonne(int pI). */		
		final String enteteNull0 = objetNull1.fournirEnTeteColonne(0);
		final String enteteNull1 = objetNull1.fournirEnTeteColonne(1);
		
		/* garantit que fournirEnTeteColonne(int pI) retourne 
		 * la bonne en-tête de colonne. */
		final String entete0 = objet1.fournirEnTeteColonne(0);
		final String entete1 = objet1.fournirEnTeteColonne(1);
		
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
			
		}

		assertEquals("entete0 : ", "id", entete0);
		assertEquals("enteteNull0 : ", "id", enteteNull0);
		
		assertEquals("entete1 : ", "dateTeleversement", entete1);
		assertEquals("enteteNull1 : ", "dateTeleversement", enteteNull1);
				
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
			System.out.println("********** CLASSE TeleversementEntityJPATest - méthode testFournirValeurColonne() ********** ");
		}
		
		/* garantit que les null sont gérés 
		 * dans fournirValeurColonne(int pI). */
		final String valeurNull0 = (String) objetNull1.fournirValeurColonne(0);
		final String valeurNull1 = (String) objetNull1.fournirValeurColonne(1);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'UN OBJET INSTANCIE AVEC LE CONSTRUCTEUR D'ARITE NULLE --------------------");
			System.out.println(OBJETNULL1 + objetNull1.toString());
			System.out.println();
			System.out.println("valeurNull0 ((String) objetNull1.fournirValeurColonne(0)) : " + valeurNull0);
			System.out.println("valeurNull1 ((String) objetNull1.fournirValeurColonne(1)) : " + valeurNull1);
		}

		assertEquals("valeurNull0 ((String) objetNull1.fournirValeurColonne(0)) : ", null, valeurNull0);
		assertEquals("valeurNull1 ((String) objetNull1.fournirValeurColonne(1)) : ", null, valeurNull1);

		
		/* garantit que fournirValeurColonne(int pI) retourne 
		 * la bonne en-tête de colonne. */
		final String valeur0 = (String) objet1.fournirValeurColonne(0);
		final String valeur1 = (String) objet1.fournirValeurColonne(1);

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {	
			System.out.println();
			System.out.println("CAS D'UN OBJET INSTANCIE AVEC LE CONSTRUCTEUR ORDINAIRE -------------------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println();
			System.out.println("valeur0 ((String) objet1.fournirValeurColonne(0)) : " + valeur0);
			System.out.println("valeur1 ((String) objet1.fournirValeurColonne(1)) : " + valeur1);
		}
		
		assertEquals("valeur0 ((String) objet1.fournirValeurColonne(0)) : ", "1", valeur0);		
		assertEquals("valeur1 ((String) objet1.fournirValeurColonne(1)) : ", "2019-06-13_08_37_43", valeur1);
		
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

		
		
} // FIN DE LA CLASSE TeleversementEntityJPATest.----------------------------
