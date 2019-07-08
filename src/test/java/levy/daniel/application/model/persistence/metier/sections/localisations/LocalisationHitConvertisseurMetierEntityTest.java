package levy.daniel.application.model.persistence.metier.sections.localisations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.metier.sections.localisations.ILocalisationHit;
import levy.daniel.application.model.metier.sections.localisations.impl.LocalisationHit;
import levy.daniel.application.model.persistence.metier.sections.localisations.entities.jpa.LocalisationHitEntityJPA;

/**
 * CLASSE LocalisationHitConvertisseurMetierEntityTest :<br/>
 * Test JUnit de la classe {@link LocalisationHitConvertisseurMetierEntity}.<br/>
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
 * @since 8 juil. 2019
 *
 */
public class LocalisationHitConvertisseurMetierEntityTest {

	// ************************ATTRIBUTS************************************/

	/**
	 * "0086".
	 */
	public static final String NUM_ROUTE_86 = "0086";
	
	/**
	 * "2".
	 */
	public static final String INDICE_NUM_ROUTE_2 = "2";
	
	/**
	 * "b".
	 */
	public static final String INDICE_LETTRE_ROUTE_B = "b";
	
	/**
	 * "4".
	 */
	public static final String CATEGORIE_ADMIN_ROUTE_4 = "4";
	
	/**
	 * "730".
	 */
	public static final String DEPT_73 = "730";
	
	/**
	 * "lieu_dit_origine".
	 */
	public static final String LIEU_DIT_ORIGINE = "lieu_dit_origine";
	
	/**
	 * "lieu_dit_extremite".
	 */
	public static final String LIEU_DIT_EXTREMITE = "lieu_dit_extremite";
	
	/**
	 * "lieu_dit_comptage".
	 */
	public static final String LIEU_DIT_COMPTAGE = "lieu_dit_comptage";
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(LocalisationHitConvertisseurMetierEntityTest.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public LocalisationHitConvertisseurMetierEntityTest() {
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
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE LocalisationHitConvertisseurMetierEntityTest - méthode testCreerObjetMetierAPartirEntityJPA() ********** ");
		}

		/* valeur null. */
		final LocalisationHitEntityJPA entityNull = null;
		
		final ILocalisationHit objetNull 
			= LocalisationHitConvertisseurMetierEntity
				.creerObjetMetierAPartirEntityJPA(entityNull);
		
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
				, new LocalisationHit(), objetNull);
		
		
		/* valeur existante. */
		final LocalisationHitEntityJPA entity 
			= new LocalisationHitEntityJPA(7L
					, NUM_ROUTE_86, INDICE_NUM_ROUTE_2, INDICE_LETTRE_ROUTE_B
					, CATEGORIE_ADMIN_ROUTE_4
					, DEPT_73
					, LIEU_DIT_ORIGINE, 3, 200
					, LIEU_DIT_EXTREMITE, 7, 400
					, LIEU_DIT_COMPTAGE, 5, 600);
		
		final ILocalisationHit objet 
			= LocalisationHitConvertisseurMetierEntity
				.creerObjetMetierAPartirEntityJPA(entity);
				
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
		System.out.println("********** CLASSE LocalisationHitConvertisseurMetierEntityTest - méthode testConvertirEntityJPAEnObjetMetier() ********** ");
		}

		/* valeur null. */
		final LocalisationHitEntityJPA entityNull = null;
		
		final ILocalisationHit objetNull 
			= LocalisationHitConvertisseurMetierEntity
				.convertirEntityJPAEnObjetMetier(entityNull);
		
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
		final LocalisationHitEntityJPA entity 
			= new LocalisationHitEntityJPA(7L
							, NUM_ROUTE_86, INDICE_NUM_ROUTE_2
							, INDICE_LETTRE_ROUTE_B, CATEGORIE_ADMIN_ROUTE_4
							, DEPT_73
							, LIEU_DIT_ORIGINE, 3, 200
							, LIEU_DIT_EXTREMITE, 7, 400
							, LIEU_DIT_COMPTAGE, 5, 600);
		
		final ILocalisationHit objet 
			= LocalisationHitConvertisseurMetierEntity
			.convertirEntityJPAEnObjetMetier(entity);
				
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
		System.out.println("********** CLASSE LocalisationHitConvertisseurMetierEntityTest - méthode testConvertirListEntitiesJPAEnModel() ********** ");
		}

		/* valeur null. */
		final List<LocalisationHitEntityJPA> listEntitiesNull = null;
		
		final List<ILocalisationHit> listObjetsNull = LocalisationHitConvertisseurMetierEntity.convertirListEntitiesJPAEnModel(listEntitiesNull);
		
		/* garantit que convertirListEntitiesJPAEnModel(listEntitiesNull) 
		 * retourne null. */
		assertNull("convertirListEntitiesJPAEnModel("
				+ "listEntitiesNull) doit retourner null : "
				, listObjetsNull);

		
		/* valeur existante. */
		final List<LocalisationHitEntityJPA> listEntities 
			= new ArrayList<LocalisationHitEntityJPA>();
		
		final LocalisationHitEntityJPA entity1 
			= new LocalisationHitEntityJPA(3L
							, "0006", "", "", "1"
							, "740"
							, "lieu_dit_origine_1", 3, 200
							, "lieu_dit_extremite_1", 7, 400
							, "lieu_dit_comptage_1", 5, 600);
		
		final LocalisationHitEntityJPA entity2 = null;
		
		final LocalisationHitEntityJPA entity3 
			= new LocalisationHitEntityJPA(6L
					, "0286", null, null, "1"
					, "440"
					, "lieu_dit_origine_2", 3, 200
					, "lieu_dit_extremite_2", 7, 400
					, "lieu_dit_comptage_2", 5, 600);
		
		
		listEntities.add(entity1);
		listEntities.add(entity2);
		listEntities.add(entity3);
		
		final List<ILocalisationHit> listObjets 
			= LocalisationHitConvertisseurMetierEntity
				.convertirListEntitiesJPAEnModel(listEntities);
		
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
			System.out.println(LocalisationHitConvertisseurMetierEntity.afficherFormateListObjets(listObjets));
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
		System.out.println("********** CLASSE LocalisationHitConvertisseurMetierEntityTest - méthode testCreerEntityJPA() ********** ");
		}

		/* valeur null. */
		final ILocalisationHit objetNull = null;
		
		final LocalisationHitEntityJPA entityNull 
			= LocalisationHitConvertisseurMetierEntity
			.creerEntityJPA(objetNull);
		
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
				, new LocalisationHitEntityJPA(), entityNull);
		
		
		/* valeur existante. */
		final ILocalisationHit objet 
			= new LocalisationHit(7L
					, NUM_ROUTE_86, INDICE_NUM_ROUTE_2
					, INDICE_LETTRE_ROUTE_B, CATEGORIE_ADMIN_ROUTE_4
					, DEPT_73
					, LIEU_DIT_ORIGINE, 3, 200
					, LIEU_DIT_EXTREMITE, 7, 400
					, LIEU_DIT_COMPTAGE, 5, 600);
		
		final LocalisationHitEntityJPA entity 
			= LocalisationHitConvertisseurMetierEntity
			.creerEntityJPA(objet);
				
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
		System.out.println("********** CLASSE LocalisationHitConvertisseurMetierEntityTest - méthode testConvertirObjetMetierEnEntityJPA() ********** ");
		}

		/* valeur null. */
		final ILocalisationHit objetNull = null;
		
		final LocalisationHitEntityJPA entityNull 
			= LocalisationHitConvertisseurMetierEntity
			.convertirObjetMetierEnEntityJPA(objetNull);
		
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
		final ILocalisationHit objet 
			= new LocalisationHit(7L
					, NUM_ROUTE_86, INDICE_NUM_ROUTE_2
					, INDICE_LETTRE_ROUTE_B, CATEGORIE_ADMIN_ROUTE_4
					, DEPT_73
					, LIEU_DIT_ORIGINE, 3, 200
					, LIEU_DIT_EXTREMITE, 7, 400
					, LIEU_DIT_COMPTAGE, 5, 600);
		
		final LocalisationHitEntityJPA entity 
			= LocalisationHitConvertisseurMetierEntity
			.convertirObjetMetierEnEntityJPA(objet);
				
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
		System.out.println("********** CLASSE LocalisationHitConvertisseurMetierEntityTest - méthode testConvertirListModelEnEntitiesJPA() ********** ");
		}

		/* valeur null. */
		final List<ILocalisationHit> listObjetsNull = null;
		
		final List<LocalisationHitEntityJPA> listEntitiesNull 
			= LocalisationHitConvertisseurMetierEntity
				.convertirListModelEnEntitiesJPA(listObjetsNull);
		
		/* garantit que convertirListModelEnEntitiesJPA(listObjetsNull) 
		 * retourne null. */
		assertNull("convertirListModelEnEntitiesJPA("
				+ "listObjetsNull) doit retourner null : "
				, listEntitiesNull);

		
		/* valeur existante. */
		final List<ILocalisationHit> listObjets 
			= new ArrayList<ILocalisationHit>();
		
		final ILocalisationHit objet1 
			= new LocalisationHit(3L
					, "0006", "", "", "1"
					, "740"
					, "lieu_dit_origine_1", 3, 200
					, "lieu_dit_extremite_1", 7, 400
					, "lieu_dit_comptage_1", 5, 600);
		
		final ILocalisationHit objet2 = null;
		
		final ILocalisationHit objet3 
			= new LocalisationHit(6L
					, "0286", null, null, "1"
					, "440"
					, "lieu_dit_origine_2", 3, 200
					, "lieu_dit_extremite_2", 7, 400
					, "lieu_dit_comptage_2", 5, 600);
		
		
		listObjets.add(objet1);
		listObjets.add(objet2);
		listObjets.add(objet3);
		
		final List<LocalisationHitEntityJPA> listEntities 
			= LocalisationHitConvertisseurMetierEntity
				.convertirListModelEnEntitiesJPA(listObjets);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("TAILLE DE LA lISTE D'OBJETS : " + listObjets.size());
			System.out.println();
			System.out.println("TAILLE DE LA lISTE D'ENTITIES : " + listEntities.size());
			System.out.println();
			System.out.println("LISTE D'OBJETS METIER : ");
			System.out.println(LocalisationHitConvertisseurMetierEntity.afficherFormateListObjets(listObjets));
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
	 * @param pList : List&lt;LocalisationHitEntityJPA&gt; : 
	 * liste d'Entities.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	private String afficherFormateListEntities(
			final List<LocalisationHitEntityJPA> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final ILocalisationHit entity : pList) {
			
			/* n'affiche pas une Entity null 
			 * dans la liste passée en paramètre. */
			if (entity != null) {
				
				final String stringformatee 
					= entity.toString();
				
				stb.append(stringformatee);
				
				/* utilise le saut de la plateforme. */
				stb.append(System.getProperty("line.separator"));
			}
		}
		
		return stb.toString();
		
	} //Fin de afficherFormateListEntities(...).___________________________

	
	
} // FIN DE LA CLASSE LocalisationHitConvertisseurMetierEntityTest.----------
