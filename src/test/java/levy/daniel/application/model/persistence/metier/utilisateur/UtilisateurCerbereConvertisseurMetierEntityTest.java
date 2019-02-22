package levy.daniel.application.model.persistence.metier.utilisateur;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.metier.utilisateur.impl.UtilisateurCerbere;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.UtilisateurCerbereEntityJPA;

/**
 * CLASSE UtilisateurCerbereConvertisseurMetierEntityTest :<br/>
 * test JUnit de la classe {@link UtilisateurCerbereConvertisseurMetierEntity}.<br/>
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
 * @since 21 févr. 2019
 *
 */
public class UtilisateurCerbereConvertisseurMetierEntityTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";

	/**
	 * FORMAT pour affichage formaté à la console 
	 * des IUtilisateurCerbere.<br/>
	 * "id=%1$-5d civilité = %2$-5s  
	 * prénom = %3$-15s nom = %4$-20s 
	 * tel = %5$-18s eMail = %6$-25s  Service = %7$-15s  
	 * Unité = %8$-35s  Profil = %9$-25s  Portée = %10$-15s  
	 * Restriction = %11$-35s".
	 */
	public static final String FORMAT_UTILISATEURCERBERE 
		= "id=%1$-5d civilité = %2$-5s  prénom = %3$-15s "
				+ "nom = %4$-20s tel = %5$-18s eMail = %6$-25s  "
				+ "Service = %7$-15s  Unité = %8$-35s  Profil = %9$-25s  "
				+ "Portée = %10$-15s  Restriction = %11$-35s";
	
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
	 * "01 02 03 04 05".
	 */
	public static final String TELTEST = "01 02 03 04 05";
	
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
	public static final String PROFILTEST = "ADMINISTRATEUR";
	
	/**
	 * "CEREMA".
	 */
	public static final String PORTEETEST = "CEREMA";
	
	/**
	 * "france métropolitaine".
	 */
	public static final String RESTRICTIONTEST = "france métropolitaine";

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereConvertisseurMetierEntityTest.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereConvertisseurMetierEntityTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * teste la méthode creerObjetMetierAPartirEntityJPA(Entity).<br/>
	 * <ul>
	 * <li>garantit le bon fonctionnement de la méthode.</li>
	 * <li>garantit que creerObjetMetierAPartirEntityJPA(entityNull) 
	 * retourne un objet avec toutes les valeurs à null.</li>
	 * <li>garantit que creerObjetMetierAPartirEntityJPA(entity) 
	 * retourne un objet métier correspondant.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCreerObjetMetierAPartirEntityJPA() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE UtilisateurCerbereConvertisseurMetierEntityTest - méthode testCreerObjetMetierAPartirEntityJPA() ********** ");
		}

		/* valeur null. */
		final UtilisateurCerbereEntityJPA entityNull = null;
		
		final IUtilisateurCerbere objetNull = UtilisateurCerbereConvertisseurMetierEntity.creerObjetMetierAPartirEntityJPA(entityNull);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("Entity JPA null");
			System.out.println(objetNull.toString());
		}

		/* garantit que creerObjetMetierAPartirEntityJPA(entityNull) 
		 * retourne un objet avec toutes les valeurs à null. */
		assertEquals("creerObjetMetierAPartirEntityJPA(entityNull) "
				+ "doit retourner un objet instancié par "
				+ "le constructeur d'arité nulle : "
				, new UtilisateurCerbere(), objetNull);
		
		
		/* valeur existante. */
		final UtilisateurCerbereEntityJPA entity 
			= new UtilisateurCerbereEntityJPA(
					7L
					, M
					, PRENOMTEST, NOMTEST
					, TELTEST, MAILTEST
					, SERVICETEST, UNITETEST
					, PROFILTEST, PORTEETEST, RESTRICTIONTEST);
		
		final IUtilisateurCerbere objet = UtilisateurCerbereConvertisseurMetierEntity.creerObjetMetierAPartirEntityJPA(entity);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(entity.toString());
			System.out.println(objet.toString());
		}
		
		/* garantit que creerObjetMetierAPartirEntityJPA(entity) 
		 * retourne un objet métier correspondant. */
		assertEquals(
				"l'objet doit correspondre à l'entité : "
					, entity
						, objet);
		
	} // Fin de testCreerObjetMetierAPartirEntityJPA().____________________


	
	/**
	 * teste la méthode convertirEntityJPAEnObjetMetier(Entity).<br/>
	 * <ul>
	 * <li>garantit le bon fonctionnement de la méthode.</li>
	 * <li>garantit que convertirEntityJPAEnObjetMetier(entityNull) 
	 * retourne null.</li>
	 * <li>garantit que convertirEntityJPAEnObjetMetier(entity) 
	 * retourne un objet métier correspondant.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConvertirEntityJPAEnObjetMetier() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE UtilisateurCerbereConvertisseurMetierEntityTest - méthode testConvertirEntityJPAEnObjetMetier() ********** ");
		}

		/* valeur null. */
		final UtilisateurCerbereEntityJPA entityNull = null;
		
		final IUtilisateurCerbere objetNull = UtilisateurCerbereConvertisseurMetierEntity.convertirEntityJPAEnObjetMetier(entityNull);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("Entity JPA null");
			System.out.println("null");
		}

		/* garantit que convertirEntityJPAEnObjetMetier(entityNull) 
		 * retourne null. */
		assertNull("convertirEntityJPAEnObjetMetier(entityNull) "
				+ "doit retourner null : "
				, objetNull);
		
		
		/* valeur existante. */
		final UtilisateurCerbereEntityJPA entity 
			= new UtilisateurCerbereEntityJPA(
					7L
					, M
					, PRENOMTEST, NOMTEST
					, TELTEST, MAILTEST
					, SERVICETEST, UNITETEST
					, PROFILTEST, PORTEETEST, RESTRICTIONTEST);
		
		final IUtilisateurCerbere objet = UtilisateurCerbereConvertisseurMetierEntity.convertirEntityJPAEnObjetMetier(entity);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(entity.toString());
			System.out.println(objet.toString());
		}
		
		/* garantit que creerObjetMetierAPartirEntityJPA(entity) 
		 * retourne un objet métier correspondant. */
		assertEquals(
				"l'objet doit correspondre à l'entité : "
					, entity
						, objet);

	} // Fin de testConvertirEntityJPAEnObjetMetier()._____________________


	
	/**
	 * teste la méthode convertirListEntitiesJPAEnModel(List Entities).<br/>
	 * <ul>
	 * <li>garantit que convertirListEntitiesJPAEnModel(listEntitiesNull) 
	 * retourne null.</li>
	 * <li>garantit que convertirListEntitiesJPAEnModel(listEntities) 
	 * ne prend pas en compte les items null dans la liste.</li>
	 * <li>garantit que convertirListEntitiesJPAEnModel(listEntities) 
	 * retourne la liste d'objets métier correspondants.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConvertirListEntitiesJPAEnModel() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE UtilisateurCerbereConvertisseurMetierEntityTest - méthode testConvertirListEntitiesJPAEnModel() ********** ");
		}

		/* valeur null. */
		final List<UtilisateurCerbereEntityJPA> listEntitiesNull = null;
		
		final List<IUtilisateurCerbere> listObjetsNull = UtilisateurCerbereConvertisseurMetierEntity.convertirListEntitiesJPAEnModel(listEntitiesNull);
		
		/* garantit que convertirListEntitiesJPAEnModel(listEntitiesNull) 
		 * retourne null. */
		assertNull("convertirListEntitiesJPAEnModel("
				+ "listEntitiesNull) doit retourner null : "
				, listObjetsNull);

		
		/* valeur existante. */
		final List<UtilisateurCerbereEntityJPA> listEntities 
			= new ArrayList<UtilisateurCerbereEntityJPA>();
		
		final UtilisateurCerbereEntityJPA entity1 
			= new UtilisateurCerbereEntityJPA(
				7L
				, M
				, PRENOMTEST, NOMTEST
				, TELTEST, MAILTEST
				, SERVICETEST, UNITETEST
				, PROFILTEST, PORTEETEST, RESTRICTIONTEST);
		
		final UtilisateurCerbereEntityJPA entity2 = null;
		
		final UtilisateurCerbereEntityJPA entity3 
			= new UtilisateurCerbereEntityJPA(
				8L
				, "Mlle"
				, "Papy", "Gonzales"
				, "04 05 06 07 08", "test2.test2@yahoo.fr"
				, SERVICETEST, UNITETEST
				, PROFILTEST, PORTEETEST, RESTRICTIONTEST);
		
		
		listEntities.add(entity1);
		listEntities.add(entity2);
		listEntities.add(entity3);
		
		final List<IUtilisateurCerbere> listObjets = UtilisateurCerbereConvertisseurMetierEntity.convertirListEntitiesJPAEnModel(listEntities);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("TAILLE DE LA lISTE D'ENTITIES : " + listEntities.size());
			System.out.println();
			System.out.println("TAILLE DE LA lISTE D'OBJETS : " + listObjets.size());
			System.out.println();
			System.out.println("LISTE D'ENTITES : ");
			System.out.println(this.afficherFormateListEntities(listEntities));
			System.out.println();
			System.out.println("LISTE D'OBJETS METIER : ");
			System.out.println(UtilisateurCerbereConvertisseurMetierEntity.afficherFormateListObjets(listObjets));
		}
		
		/* garantit que convertirListEntitiesJPAEnModel(listEntities) 
		 * ne prend pas en compte les items null dans la liste. */
		assertTrue(
				"listObjets ne doit pas prendre en compte les items null : "
				, listObjets.size() == listEntities.size() - 1);
		
		/* garantit que convertirListEntitiesJPAEnModel(listEntities) 
		 * retourne la liste d'objets métier correspondants. */
		assertEquals("l'objet doit correspondre à l'entité : "
				, listEntities.get(0), listObjets.get(0));
		assertEquals("l'objet doit correspondre à l'entité : "
				, listEntities.get(2), listObjets.get(1));
		
	} // Fin de testConvertirListEntitiesJPAEnModel()._____________________


	
	/**
	 * teste la méthode creerEntityJPA(Objet).<br/>
	 * <ul>
	 * <li>garantit le bon fonctionnement de la méthode.</li>
	 * <li>garantit que creerEntityJPA(objetNull) 
	 * retourne une entity avec toutes les valeurs à null.</li>
	 * <li>garantit que creerEntityJPA(objet) 
	 * retourne une entity correspondante.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCreerEntityJPA() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE UtilisateurCerbereConvertisseurMetierEntityTest - méthode testCreerEntityJPA() ********** ");
		}

		/* valeur null. */
		final IUtilisateurCerbere objetNull = null;
		
		final UtilisateurCerbereEntityJPA entityNull = UtilisateurCerbereConvertisseurMetierEntity.creerEntityJPA(objetNull);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("Objet métier null");
			System.out.println(entityNull.toString());
		}

		/* garantit que creerEntityJPA(objetNull) 
		 * retourne un entity avec toutes les valeurs à null. */
		assertEquals("creerEntityJPA(entityNull) "
				+ "doit retourner un entity instancié par "
				+ "le constructeur d'arité nulle : "
				, new UtilisateurCerbereEntityJPA(), entityNull);
		
		
		/* valeur existante. */
		final IUtilisateurCerbere objet 
			= new UtilisateurCerbere(
					7L
					, M
					, PRENOMTEST, NOMTEST
					, TELTEST, MAILTEST
					, SERVICETEST, UNITETEST
					, PROFILTEST, PORTEETEST, RESTRICTIONTEST);
		
		final UtilisateurCerbereEntityJPA entity = UtilisateurCerbereConvertisseurMetierEntity.creerEntityJPA(objet);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(objet.toString());
			System.out.println(entity.toString());
		}
		
		/* garantit que creerEntityJPA(objet) 
		 * retourne un entity correspondant. */
		assertEquals(
				"l'entité doit correspondre à l'objet : "
					, objet
						, entity);
		
	} // Fin de testCreerEntityJPA().______________________________________


	
	/**
	 * teste la méthode convertirObjetMetierEnEntityJPA(objet).<br/>
	 * <ul>
	 * <li>garantit le bon fonctionnement de la méthode.</li>
	 * <li>garantit que convertirObjetMetierEnEntityJPA(objetNull) 
	 * retourne null.</li>
	 * <li>garantit que convertirObjetMetierEnEntityJPA(objet) 
	 * retourne un entity correspondant.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConvertirObjetMetierEnEntityJPA() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE UtilisateurCerbereConvertisseurMetierEntityTest - méthode testConvertirObjetMetierEnEntityJPA() ********** ");
		}

		/* valeur null. */
		final IUtilisateurCerbere objetNull = null;
		
		final UtilisateurCerbereEntityJPA entityNull = UtilisateurCerbereConvertisseurMetierEntity.convertirObjetMetierEnEntityJPA(objetNull);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("objet métier null");
			System.out.println("null");
		}

		/* garantit que convertirObjetMetierEnEntityJPA(objetNull) 
		 * retourne null. */
		assertNull("convertirObjetMetierEnEntityJPA(objetNull) "
				+ "doit retourner null : "
				, entityNull);
		
		
		/* valeur existante. */
		final IUtilisateurCerbere objet 
			= new UtilisateurCerbere(
					7L
					, M
					, PRENOMTEST, NOMTEST
					, TELTEST, MAILTEST
					, SERVICETEST, UNITETEST
					, PROFILTEST, PORTEETEST, RESTRICTIONTEST);
		
		final UtilisateurCerbereEntityJPA entity = UtilisateurCerbereConvertisseurMetierEntity.convertirObjetMetierEnEntityJPA(objet);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(objet.toString());
			System.out.println(entity.toString());			
		}
		
		/* garantit que convertirObjetMetierEnEntityJPA(entity) 
		 * retourne un objet métier correspondant. */
		assertEquals(
				"l'objet doit correspondre à l'entité : "
					, objet
						, entity);

	} // Fin de testConvertirObjetMetierEnEntityJPA()._____________________


	
	/**
	 * teste la méthode convertirListModelEnEntitiesJPA(List Objets).<br/>
	 * <ul>
	 * <li>garantit que convertirListModelEnEntitiesJPA(listObjetsNull) 
	 * retourne null.</li>
	 * <li>garantit que convertirListModelEnEntitiesJPA(listObjets) 
	 * ne prend pas en compte les items null dans la liste.</li>
	 * <li>garantit que convertirListModelEnEntitiesJPA(listObjets) 
	 * retourne la liste d'entities correspondants.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConvertirListModelEnEntitiesJPA() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE UtilisateurCerbereConvertisseurMetierEntityTest - méthode testConvertirListModelEnEntitiesJPA() ********** ");
		}

		/* valeur null. */
		final List<IUtilisateurCerbere> listObjetsNull = null;
		
		final List<UtilisateurCerbereEntityJPA> listEntitiesNull = UtilisateurCerbereConvertisseurMetierEntity.convertirListModelEnEntitiesJPA(listObjetsNull);
		
		/* garantit que convertirListModelEnEntitiesJPA(listObjetsNull) 
		 * retourne null. */
		assertNull("convertirListModelEnEntitiesJPA("
				+ "listObjetsNull) doit retourner null : "
				, listEntitiesNull);

		
		/* valeur existante. */
		final List<IUtilisateurCerbere> listObjets 
			= new ArrayList<IUtilisateurCerbere>();
		
		final IUtilisateurCerbere objet1 
			= new UtilisateurCerbere(
				7L
				, M
				, PRENOMTEST, NOMTEST
				, TELTEST, MAILTEST
				, SERVICETEST, UNITETEST
				, PROFILTEST, PORTEETEST, RESTRICTIONTEST);
		
		final IUtilisateurCerbere objet2 = null;
		
		final IUtilisateurCerbere objet3 
			= new UtilisateurCerbere(
				8L
				, "Mlle"
				, "Papy", "Gonzales"
				, "04 05 06 07 08", "test2.test2@yahoo.fr"
				, SERVICETEST, UNITETEST
				, PROFILTEST, PORTEETEST, RESTRICTIONTEST);
		
		
		listObjets.add(objet1);
		listObjets.add(objet2);
		listObjets.add(objet3);
		
		final List<UtilisateurCerbereEntityJPA> listEntities = UtilisateurCerbereConvertisseurMetierEntity.convertirListModelEnEntitiesJPA(listObjets);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("TAILLE DE LA lISTE D'OBJETS : " + listObjets.size());
			System.out.println();
			System.out.println("TAILLE DE LA lISTE D'ENTITIES : " + listEntities.size());
			System.out.println();
			System.out.println("LISTE D'OBJETS METIER : ");
			System.out.println(UtilisateurCerbereConvertisseurMetierEntity.afficherFormateListObjets(listObjets));
			System.out.println();
			System.out.println("LISTE D'ENTITES : ");
			System.out.println(this.afficherFormateListEntities(listEntities));			
		}
		
		/* garantit que convertirListModelEnEntitiesJPA(listObjets) 
		 * ne prend pas en compte les items null dans la liste. */
		assertTrue(
				"listEntities ne doit pas prendre en compte les items null : "
				, listEntities.size() == listObjets.size() - 1);
		
		/* garantit que convertirListModelEnEntitiesJPA(listObjets) 
		 * retourne la liste d'entites correspondants. */
		assertEquals("l'entité doit correspondre à l'objet : "
				, listObjets.get(0), listEntities.get(0));
		assertEquals("l'entité doit correspondre à l'objet : "
				, listObjets.get(2), listEntities.get(1));
		
	} // Fin de testConvertirListModelEnEntitiesJPA()._____________________
	

	
	/**
	 * <b>retourne une String pour affichage formaté 
	 * (FORMAT_UTILISATEURCERBERE) 
	 * d'une liste d'entities</b>.<br/>
	 * <ul>
	 * <li>chaque item de la liste est retournée 
	 * sur une ligne formatée.</li>
	 * <li>utilise le saut de la plateforme comme saut à la ligne 
	 * (<code>System.getProperty("line.separator")</code>).</li>
	 * </ul>
	 * - retourne null si pList == null.<br/>
	 * - n'affiche pas un item null dans la liste 
	 * passée en paramètre.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;UtilisateurCerbereEntityJPA&gt; : 
	 * liste d'Entities.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	private String afficherFormateListEntities(
			final List<UtilisateurCerbereEntityJPA> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final IUtilisateurCerbere entity : pList) {
			
			/* n'affiche pas une Entity null 
			 * dans la liste passée en paramètre. */
			if (entity != null) {
				
				final String stringformatee 
					= String.format(
							Locale.getDefault()
								, FORMAT_UTILISATEURCERBERE
								, entity.getId()
								, entity.getCivilite()
								, entity.getPrenom(), entity.getNom()
								, entity.getTel(), entity.getEmail()
								, entity.getService(), entity.getUnite()
								, entity.getProfil()
								, entity.getPortee()
								, entity.getRestriction());
				
				stb.append(stringformatee);
				
				/* utilise le saut de la plateforme. */
				stb.append(System.getProperty("line.separator"));
			}
		}
		
		return stb.toString();
		
	} //Fin de afficherFormateListEntities(...).___________________________
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereConvertisseurMetierEntityTest.-------
