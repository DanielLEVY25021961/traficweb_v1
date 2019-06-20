package levy.daniel.application.model.persistence.metier.anneegestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.anneegestion.IAnneeGestion;
import levy.daniel.application.model.metier.anneegestion.impl.AnneeGestion;
import levy.daniel.application.model.persistence.metier.anneegestion.entities.jpa.AnneeGestionEntityJPA;

/**
 * CLASSE AnneeGestionConvertisseurMetierEntity :<br/>
 * classe <b>utilitaire</b> chargée de <b>convertir 
 * une ENTITY en OBJET METIER</b> et de <b>convertir un
 * OBJET METIER en ENTITY</b>.<br/>
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
 * @since 20 juin 2019
 *
 */
public final class AnneeGestionConvertisseurMetierEntity {

	// ************************ATTRIBUTS************************************/

	/**
	 * FORMAT pour affichage formaté à la console 
	 * des IAnneeGesion.<br/>
	 * "id=%1$-5d anneeGestion = %2$-5s".
	 */
	public static final String FORMAT_ANNEEGESTION 
		= "id=%1$-5d anneeGestion = %2$-5s";

	/**
	 * "line.separator".<br/>
	 */
	public static final String LINE_SEPARATOR = "line.separator";

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(AnneeGestionConvertisseurMetierEntity.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private AnneeGestionConvertisseurMetierEntity() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * <b>Crée un OBJET METIER à partir d'une EntityJPA</b>.<br/>
	 * <ul>
	 * <li>retourne un OBJET METIER avec toutes les valeurs 
	 * à null si pEntityJPA == null.</li>
	 * </ul>
	 *
	 * @param pEntityJPA : AnneeGestionEntityJPA.<br/>
	 * 
	 * @return : IAnneeGestion.<br/>
	 */
	public static IAnneeGestion creerObjetMetierAPartirEntityJPA(
			final AnneeGestionEntityJPA pEntityJPA) {

		synchronized (AnneeGestionConvertisseurMetierEntity.class) {
			
			final IAnneeGestion objet 
				= new AnneeGestion();
			
			if (pEntityJPA != null) {
				
				objet.setId(pEntityJPA.getId());
				objet.setAnneeGestion(pEntityJPA.getAnneeGestion());
				
			}
							
			return objet;
		
		} // Fin de synchronized._______________________
		
	} // Fin de creerObjetMetierAPartirEntityJPA(...)._____________________
	
	
	
	
	/**
	 * <b>convertit une ENTITY JPA en OBJET METIER</b>.<br/>
	 * <ul>
	 * <li>retourne null si pEntity == null.</li>
	 * <li>récupère les valeurs dans le pEntity.</li>
	 * <li>injecte les valeurs de l'ENTITY dans un OBJET METIER.</li>
	 * </ul>
	 *
	 * @param pEntity : AnneeGestionEntityJPA.<br/>
	 * 
	 * @return : IAnneeGestion : Objet métier.<br/>
	 */
	public static IAnneeGestion convertirEntityJPAEnObjetMetier(
			final AnneeGestionEntityJPA pEntity) {
		
		synchronized (AnneeGestionConvertisseurMetierEntity.class) {
			
			IAnneeGestion objet = null;
			
			if (pEntity != null) {
				
				/* récupère les valeurs dans l'Entity. */
				/* injecte les valeurs typées dans un OBJET METIER. */
				objet 
					= new AnneeGestion(
							pEntity.getId()
							, pEntity.getAnneeGestion());
			}
			
			return objet;
			
		} // Fin de synchronized._______________________
		
	} // Fin de convertirEntityJPAEnObjetMetier(...).______________________
	

	
	/**
	 * convertit une Liste d'ENTITIES JPA 
	 * en liste d'OBJETS METIER et la retourne.<br/>
	 * <br/>
	 * - retourne null si pList == null.<br/>
	 * - n'insère dans la liste résultat que les Entities non null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;AnneeGestionEntityJPA&gt;.<br/>
	 * 
	 * @return : List&lt;IAnneeGestion&gt;.<br/>
	 */
	public static List<IAnneeGestion> convertirListEntitiesJPAEnModel(
			final List<AnneeGestionEntityJPA> pList) {
		
		synchronized (AnneeGestionConvertisseurMetierEntity.class) {
			
			/* retourne null si pList == null. */
			if (pList == null) {
				return null;
			}
			
			final List<IAnneeGestion> resultat 
				= new ArrayList<IAnneeGestion>();
			
			for (final AnneeGestionEntityJPA entity : pList) {
				
				/* n'insère dans la liste résultat 
				 * que les Entities non null. */
				if (entity != null) {
					
					final IAnneeGestion objet 													
						= convertirEntityJPAEnObjetMetier(entity);
					
					resultat.add(objet);
					
				}
			}
			
			return resultat;
			
		} // Fin de synchronized.______________________
		
	} // Fin de convertirListEntitiesJPAEnModel(...).______________________
	
	
		
	/**
	 * <b>crée une ENTITY JPA à partir d'un OBJET METIER</b>.<br/>
	 * <ul>
	 * <li>retourne une Entity JPA avec toutes les valeurs 
	 * à null si pObject == null.</li>
	 * </ul>
	 *
	 * @param pObject : IAnneeGestion.<br/>
	 *  
	 * @return : AnneeGestionEntityJPA.<br/>
	 */
	public static AnneeGestionEntityJPA creerEntityJPA(
			final IAnneeGestion pObject) {
		
		synchronized (AnneeGestionConvertisseurMetierEntity.class) {
			
			final AnneeGestionEntityJPA entity 
				= new AnneeGestionEntityJPA();
			
			if (pObject != null) {
				
				entity.setId(pObject.getId());
				entity.setAnneeGestion(pObject.getAnneeGestion());
				
			}
			
			return entity;
					
		} // Fin de synchronized.______________________
		
	} // Fin de creerEntityJPA(...)._______________________________________

	
	
	/**
	 * <b>convertit un OBJET METIER en ENTITY JPA</b>.<br/>
	 * <ul>
	 * <li>retourne null si pObject == null.</li>
	 * <li>récupère les valeurs typées dans l'objet métier.</li>
	 * <li>injecte les valeurs de l'objet métier dans une ENTITY.</li>
	 * </ul>
	 *
	 * @param pObject : IAnneeGestion : Objet métier.<br/>
	 * 
	 * @return : AnneeGestionEntityJPA : ENTITY JPA.<br/>
	 */
	public static AnneeGestionEntityJPA convertirObjetMetierEnEntityJPA(
			final IAnneeGestion pObject) {
		
		synchronized (AnneeGestionConvertisseurMetierEntity.class) {
			
			AnneeGestionEntityJPA resultat = null;
			
			if (pObject != null) {
								
				/* injecte les valeurs String dans un DTO. */
				resultat 
					= new AnneeGestionEntityJPA(
							pObject.getId()
							, pObject.getAnneeGestion());
				
			}
						
			return resultat;
			
		} // Fin de synchronized._______________________
		
	} // Fin de convertirObjetMetierEnEntityJPA(...).______________________
	

	
	/**
	 * convertit une Liste d'OBJETS METIER en liste 
	 * d'ENTITIES JPA.<br/>
	 * <br/>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;IAnneeGestion&gt;
	 * 
	 * @return : List&lt;AnneeGestionEntityJPA&gt;.<br/>
	 */
	public static List<AnneeGestionEntityJPA> convertirListModelEnEntitiesJPA(
			final Iterable<IAnneeGestion> pList) {
		
		synchronized (AnneeGestionConvertisseurMetierEntity.class) {
			
			/* retourne null si pList == null. */
			if (pList == null) {
				return null;
			}
			
			final List<AnneeGestionEntityJPA> resultat 
				= new ArrayList<AnneeGestionEntityJPA>();
			
			for (final IAnneeGestion objet : pList) {
				
				if (objet != null) {
					
					final AnneeGestionEntityJPA entity 
						= convertirObjetMetierEnEntityJPA(objet);
					
					resultat.add(entity);
					
				}
			}
			
			return resultat;

		} // Fin de synchronized._______________________
		
	} // Fin de convertirListModelEnEntitiesJPA(...).______________________
	

	
	/**
	 * <b>retourne une String pour affichage formaté 
	 * (FORMAT_ANNEEGESTION) 
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
	 * @param pList : List&lt;AnneeGestionEntityJPA&gt; : 
	 * liste d'Entities.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	public static String afficherFormateListEntities(
			final List<AnneeGestionEntityJPA> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final IAnneeGestion entity : pList) {
			
			/* n'affiche pas une Entity null 
			 * dans la liste passée en paramètre. */
			if (entity != null) {
				
				final String stringformatee 
					= String.format(
							Locale.getDefault()
								, FORMAT_ANNEEGESTION
								, entity.getId()
								, entity.getAnneeGestion());
				
				stb.append(stringformatee);
				
				/* utilise le saut de la plateforme. */
				stb.append(System.getProperty(LINE_SEPARATOR));
			}
		}
		
		return stb.toString();
		
	} //Fin de afficherFormateListEntities(...).___________________________
	

	
	/**
	 * <b>retourne une String pour affichage formaté 
	 * (FORMAT_ANNEEGESTION) 
	 * d'une liste d'objets métier</b>.<br/>
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
	 * @param pList : List&lt;IAnneeGestion&gt; : 
	 * liste d'Entities.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	public static String afficherFormateListObjets(
			final List<IAnneeGestion> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final IAnneeGestion entity : pList) {
			
			/* n'affiche pas une Entity null 
			 * dans la liste passée en paramètre. */
			if (entity != null) {
				
				final String stringformatee 
					= String.format(
							Locale.getDefault()
								, FORMAT_ANNEEGESTION
								, entity.getId()
								, entity.getAnneeGestion());
				
				stb.append(stringformatee);
				
				/* utilise le saut de la plateforme. */
				stb.append(System.getProperty(LINE_SEPARATOR));
			}
		}
		
		return stb.toString();
		
	} //Fin de afficherFormateListObjets(...)._____________________________
	
	
	
} // FIN DE LA CLASSE AnneeGestionConvertisseurMetierEntity.-----------------
