package levy.daniel.application.model.persistence.metier.televersement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.televersement.ITeleversement;
import levy.daniel.application.model.metier.televersement.impl.Televersement;
import levy.daniel.application.model.persistence.metier.televersement.entities.jpa.TeleversementEntityJPA;

/**
 * CLASSE TeleversementConvertisseurMetierEntity :<br/>
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
 * @author dan Lévy
 * @version 1.0
 * @since 21 juin 2019
 *
 */
public final class TeleversementConvertisseurMetierEntity {

	// ************************ATTRIBUTS************************************/

	/**
	 * FORMAT pour affichage formaté à la console 
	 * des ITeleversement.<br/>
	 * "id = %1$-5d dateTeleversement = %2$-20s utilisateur = %3$-30s 
	 * gestionnaire = %4$-10s typeFichier = %5$-10s 
	 * nomfichierTeleverse = %6$-20s 
	 * fichierStockeServeur = %7$-100s anneeGestion = %8$-4s".
	 */
	public static final String FORMAT_TELEVERSEMENT 
		= "id = %1$-5d dateTeleversement = %2$-20s utilisateur = %3$-30s "
				+ "gestionnaire = %4$-10s typeFichier = %5$-10s "
				+ "nomfichierTeleverse = %6$-20s "
				+ "fichierStockeServeur = %7$-100s anneeGestion = %8$-4s";

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
		= LogFactory.getLog(TeleversementConvertisseurMetierEntity.class);

	// *************************METHODES************************************/
	
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private TeleversementConvertisseurMetierEntity() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * <b>Crée un OBJET METIER à partir d'une EntityJPA</b>.<br/>
	 * <ul>
	 * <li>retourne un OBJET METIER avec toutes les valeurs 
	 * à null si pEntityJPA == null.</li>
	 * </ul>
	 *
	 * @param pEntityJPA : TeleversementEntityJPA.<br/>
	 * 
	 * @return : ITeleversement.<br/>
	 */
	public static ITeleversement creerObjetMetierAPartirEntityJPA(
			final TeleversementEntityJPA pEntityJPA) {

		synchronized (TeleversementConvertisseurMetierEntity.class) {
			
			final ITeleversement objet 
				= new Televersement();
			
			if (pEntityJPA != null) {
				
				objet.setId(pEntityJPA.getId());
				objet.setDateTeleversement(pEntityJPA.getDateTeleversement());
				objet.setUtilisateur(pEntityJPA.getUtilisateur());
				objet.setGestionnaire(pEntityJPA.getGestionnaire());
				objet.setTypeFichier(pEntityJPA.getTypeFichier());
				objet.setNomFichierTeleverse(pEntityJPA.getNomFichierTeleverse());
				objet.setFichierStockeServeur(pEntityJPA.getFichierStockeServeur());
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
	 * @param pEntity : TeleversementEntityJPA.<br/>
	 * 
	 * @return : ITeleversement : Objet métier.<br/>
	 */
	public static ITeleversement convertirEntityJPAEnObjetMetier(
			final TeleversementEntityJPA pEntity) {
		
		synchronized (TeleversementConvertisseurMetierEntity.class) {
			
			ITeleversement objet = null;
			
			if (pEntity != null) {
				
				/* récupère les valeurs dans l'Entity. */
				/* injecte les valeurs typées dans un OBJET METIER. */
				objet 
					= new Televersement(
							pEntity.getId()
							, pEntity.getDateTeleversement()
							, pEntity.getUtilisateur()
							, pEntity.getGestionnaire()
							, pEntity.getTypeFichier()
							, pEntity.getNomFichierTeleverse()
							, pEntity.getFichierStockeServeur()
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
	 * @param pList : List&lt;TeleversementEntityJPA&gt;.<br/>
	 * 
	 * @return : List&lt;ITeleversement&gt;.<br/>
	 */
	public static List<ITeleversement> convertirListEntitiesJPAEnModel(
			final List<TeleversementEntityJPA> pList) {
		
		synchronized (TeleversementConvertisseurMetierEntity.class) {
			
			/* retourne null si pList == null. */
			if (pList == null) {
				return null;
			}
			
			final List<ITeleversement> resultat 
				= new ArrayList<ITeleversement>();
			
			for (final TeleversementEntityJPA entity : pList) {
				
				/* n'insère dans la liste résultat 
				 * que les Entities non null. */
				if (entity != null) {
					
					final ITeleversement objet 													
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
	 * @param pObject : ITeleversement.<br/>
	 *  
	 * @return : TeleversementEntityJPA.<br/>
	 */
	public static TeleversementEntityJPA creerEntityJPA(
			final ITeleversement pObject) {
		
		synchronized (TeleversementConvertisseurMetierEntity.class) {
			
			final TeleversementEntityJPA entity 
				= new TeleversementEntityJPA();
			
			if (pObject != null) {
				
				entity.setId(pObject.getId());
				entity.setDateTeleversement(pObject.getDateTeleversement());
				entity.setUtilisateur(pObject.getUtilisateur());
				entity.setGestionnaire(pObject.getGestionnaire());
				entity.setTypeFichier(pObject.getTypeFichier());
				entity.setNomFichierTeleverse(pObject.getNomFichierTeleverse());
				entity.setFichierStockeServeur(pObject.getFichierStockeServeur());
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
	 * @param pObject : ITeleversement : Objet métier.<br/>
	 * 
	 * @return : TeleversementEntityJPA : ENTITY JPA.<br/>
	 */
	public static TeleversementEntityJPA convertirObjetMetierEnEntityJPA(
			final ITeleversement pObject) {
		
		synchronized (TeleversementConvertisseurMetierEntity.class) {
			
			TeleversementEntityJPA resultat = null;
			
			if (pObject != null) {
								
				/* injecte les valeurs String dans un DTO. */
				resultat 
					= new TeleversementEntityJPA(
							pObject.getId()
							, pObject.getDateTeleversement()
							, pObject.getUtilisateur()
							, pObject.getGestionnaire()
							, pObject.getTypeFichier()
							, pObject.getNomFichierTeleverse()
							, pObject.getFichierStockeServeur()
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
	 * @param pList : List&lt;ITeleversement&gt;
	 * 
	 * @return : List&lt;TeleversementEntityJPA&gt;.<br/>
	 */
	public static List<TeleversementEntityJPA> convertirListModelEnEntitiesJPA(
			final Iterable<ITeleversement> pList) {
		
		synchronized (TeleversementConvertisseurMetierEntity.class) {
			
			/* retourne null si pList == null. */
			if (pList == null) {
				return null;
			}
			
			final List<TeleversementEntityJPA> resultat 
				= new ArrayList<TeleversementEntityJPA>();
			
			for (final ITeleversement objet : pList) {
				
				if (objet != null) {
					
					final TeleversementEntityJPA entity 
						= convertirObjetMetierEnEntityJPA(objet);
					
					resultat.add(entity);
					
				}
			}
			
			return resultat;

		} // Fin de synchronized._______________________
		
	} // Fin de convertirListModelEnEntitiesJPA(...).______________________
	

	
	/**
	 * <b>retourne une String pour affichage formaté 
	 * (FORMAT_TELEVERSEMENT) 
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
	 * @param pList : List&lt;TeleversementEntityJPA&gt; : 
	 * liste d'Entities.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	public static String afficherFormateListEntities(
			final List<TeleversementEntityJPA> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final ITeleversement entity : pList) {
			
			/* n'affiche pas une Entity null 
			 * dans la liste passée en paramètre. */
			if (entity != null) {
				
				final String stringformatee 
					= String.format(
							Locale.getDefault()
								, FORMAT_TELEVERSEMENT
								, entity.getId()
								, formaterLocalDateTimeEnString(entity.getDateTeleversement())
								, entity.getUtilisateur().getNom()
								, entity.getGestionnaire().getNomCourt()
								, entity.getTypeFichier().getNomCourt()
								, entity.getNomFichierTeleverse()
								, entity.getFichierStockeServeur().getAbsolutePath()
								, entity.getAnneeGestion().getAnneeGestion());
				
				stb.append(stringformatee);
				
				/* utilise le saut de la plateforme. */
				stb.append(System.getProperty(LINE_SEPARATOR));
			}
		}
		
		return stb.toString();
		
	} //Fin de afficherFormateListEntities(...).___________________________
	

	
	/**
	 * <b>retourne une String pour affichage formaté 
	 * (FORMAT_TELEVERSEMENT) 
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
	 * @param pList : List&lt;ITeleversement&gt; : 
	 * liste d'Objets métier.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	public static String afficherFormateListObjets(
			final List<ITeleversement> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final ITeleversement objet : pList) {
			
			/* n'affiche pas une Entity null 
			 * dans la liste passée en paramètre. */
			if (objet != null) {
				
				final String stringformatee 
					= String.format(
							Locale.getDefault()
								, FORMAT_TELEVERSEMENT
								, objet.getId()
								, formaterLocalDateTimeEnString(objet.getDateTeleversement())
								, objet.getUtilisateur().getNom()
								, objet.getGestionnaire().getNomCourt()
								, objet.getTypeFichier().getNomCourt()
								, objet.getNomFichierTeleverse()
								, objet.getFichierStockeServeur().getAbsolutePath()
								, objet.getAnneeGestion().getAnneeGestion());
				
				stb.append(stringformatee);
				
				/* utilise le saut de la plateforme. */
				stb.append(System.getProperty(LINE_SEPARATOR));
			}
		}
		
		return stb.toString();
		
	} //Fin de afficherFormateListObjets(...)._____________________________

	
	
	/**
	 * retourne une String de la forme 
	 * <code>[annee-mois-jour_heure_minute_seconde]</code> 
	 * à partir d'une LocalDateTime pDate.<br/>
	 * Par exemple, retourne <b>2019-06-13_21_03_57</b> 
	 * pour le 13 juin 2019 à 21h 03 minutes et 57 secondes.<br/>
	 * <br/>
	 * - retourne null si pDate == null.<br/>
	 * <br/>
	 *
	 * @param pDate : LocalDateTime : date à formater.
	 * 
	 * @return : String : affichage de la date formatée.<br/>
	 */
	private static String formaterLocalDateTimeEnString(
			final LocalDateTime pDate) {
		
		/* retourne null si pDate == null. */
		if (pDate == null) {
			return null;
		}
		
		/* formateur de type 2019-06-13_21_03_57 
		 * (13 juin 2019 à 21h 03 minutes et 57 secondes)*/
		final DateTimeFormatter formatter 
			= DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");
	
		final String resultat = pDate.format(formatter);
				
		return resultat;
		
	} // Fin de formaterLocalDateTimeEnString(...).________________________
	
		
	
} // FIN DE LA CLASSE TeleversementConvertisseurMetierEntity.----------------
