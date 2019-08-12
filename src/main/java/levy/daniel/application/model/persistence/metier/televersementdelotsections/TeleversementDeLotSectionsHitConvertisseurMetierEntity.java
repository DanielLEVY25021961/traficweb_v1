package levy.daniel.application.model.persistence.metier.televersementdelotsections;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.sections.impl.SectionHit;
import levy.daniel.application.model.metier.televersement.ITeleversement;
import levy.daniel.application.model.metier.televersementdelotsections.ITeleversementDeLotSectionsHit;
import levy.daniel.application.model.metier.televersementdelotsections.impl.TeleversementDeLotSectionsHit;
import levy.daniel.application.model.persistence.metier.sections.SectionHitConvertisseurMetierEntity;
import levy.daniel.application.model.persistence.metier.sections.entities.jpa.SectionHitEntityJPA;
import levy.daniel.application.model.persistence.metier.televersement.TeleversementConvertisseurMetierEntity;
import levy.daniel.application.model.persistence.metier.televersement.entities.jpa.TeleversementEntityJPA;
import levy.daniel.application.model.persistence.metier.televersementdelotsections.entities.jpa.TeleversementDeLotSectionsHitEntityJPA;

/**
 * CLASSE TeleversementDeLotSectionsHitDeLotSectionsHitConvertisseurMetierEntity :<br/>
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
 * @since 5 août 2019
 *
 */
public final class TeleversementDeLotSectionsHitConvertisseurMetierEntity {

	// ************************ATTRIBUTS************************************/

	/**
	 * FORMAT pour affichage formaté à la console 
	 * des ITeleversementDeLotSectionsHit.<br/>
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
		= LogFactory.getLog(TeleversementDeLotSectionsHitConvertisseurMetierEntity.class);
	
	// *************************METHODES************************************/
	
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private TeleversementDeLotSectionsHitConvertisseurMetierEntity() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * <b>Crée un OBJET METIER à partir d'une EntityJPA</b>.<br/>
	 * <ul>
	 * <li>retourne un OBJET METIER avec toutes les valeurs 
	 * à null si pEntityJPA == null.</li>
	 * </ul>
	 *
	 * @param pEntityJPA : TeleversementDeLotSectionsHitEntityJPA.<br/>
	 * 
	 * @return : ITeleversementDeLotSectionsHit.<br/>
	 * 
	 * @throws Exception 
	 */
	public static ITeleversementDeLotSectionsHit creerObjetMetierAPartirEntityJPA(
			final TeleversementDeLotSectionsHitEntityJPA pEntityJPA) 
					throws Exception {

		synchronized (TeleversementDeLotSectionsHitConvertisseurMetierEntity.class) {
			
			final ITeleversementDeLotSectionsHit objet 
				= new TeleversementDeLotSectionsHit();
			
			if (pEntityJPA != null) {
				
				objet.setId(pEntityJPA.getId());
				objet.setTeleversement(
						TeleversementConvertisseurMetierEntity
							.convertirEntityJPAEnObjetMetier(
									(TeleversementEntityJPA) pEntityJPA.getTeleversement()));
				objet.setLotSections(
						convertirLotEntityEnLotObjet(
								pEntityJPA.getLotSections()));
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
	 * @param pEntity : TeleversementDeLotSectionsHitEntityJPA.<br/>
	 * 
	 * @return : ITeleversementDeLotSectionsHit : Objet métier.<br/>
	 * 
	 * @throws Exception 
	 */
	public static ITeleversementDeLotSectionsHit convertirEntityJPAEnObjetMetier(
			final TeleversementDeLotSectionsHitEntityJPA pEntity) 
						throws Exception {
		
		synchronized (TeleversementDeLotSectionsHitConvertisseurMetierEntity.class) {
			
			ITeleversementDeLotSectionsHit objet = null;
			
			if (pEntity != null) {
				
				/* récupère les valeurs dans l'Entity. */
				/* injecte les valeurs typées dans un OBJET METIER. */
				objet 
					= new TeleversementDeLotSectionsHit(
							pEntity.getId()
							, TeleversementConvertisseurMetierEntity
							.convertirEntityJPAEnObjetMetier(
									(TeleversementEntityJPA) pEntity.getTeleversement())
							, convertirLotEntityEnLotObjet(
									pEntity.getLotSections()));
				
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
	 * @param pList : List&lt;TeleversementDeLotSectionsHitEntityJPA&gt;.<br/>
	 * 
	 * @return : List&lt;ITeleversementDeLotSectionsHit&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static List<ITeleversementDeLotSectionsHit> convertirListEntitiesJPAEnModel(
			final List<TeleversementDeLotSectionsHitEntityJPA> pList) 
					throws Exception {
		
		synchronized (TeleversementDeLotSectionsHitConvertisseurMetierEntity.class) {
			
			/* retourne null si pList == null. */
			if (pList == null) {
				return null;
			}
			
			final List<ITeleversementDeLotSectionsHit> resultat 
				= new ArrayList<ITeleversementDeLotSectionsHit>();
			
			for (final TeleversementDeLotSectionsHitEntityJPA entity : pList) {
				
				/* n'insère dans la liste résultat 
				 * que les Entities non null. */
				if (entity != null) {
					
					final ITeleversementDeLotSectionsHit objet 													
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
	 * @param pObject : ITeleversementDeLotSectionsHit.<br/>
	 *  
	 * @return : TeleversementDeLotSectionsHitEntityJPA.<br/>
	 * 
	 * @throws Exception 
	 */
	public static TeleversementDeLotSectionsHitEntityJPA creerEntityJPA(
			final ITeleversementDeLotSectionsHit pObject) throws Exception {
		
		synchronized (TeleversementDeLotSectionsHitConvertisseurMetierEntity.class) {
			
			final TeleversementDeLotSectionsHitEntityJPA entity 
				= new TeleversementDeLotSectionsHitEntityJPA();
			
			if (pObject != null) {
				
				entity.setId(pObject.getId());
				entity.setTeleversement(
						TeleversementConvertisseurMetierEntity
							.convertirObjetMetierEnEntityJPA(
									pObject.getTeleversement()));
				entity.setLotSections(
						convertirLotObjetEnLotEntity(pObject.getLotSections()));
				
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
	 * @param pObject : ITeleversementDeLotSectionsHit : Objet métier.<br/>
	 * 
	 * @return : TeleversementDeLotSectionsHitEntityJPA : ENTITY JPA.<br/>
	 * 
	 * @throws Exception 
	 */
	public static TeleversementDeLotSectionsHitEntityJPA convertirObjetMetierEnEntityJPA(
			final ITeleversementDeLotSectionsHit pObject) throws Exception {
		
		synchronized (TeleversementDeLotSectionsHitConvertisseurMetierEntity.class) {
			
			TeleversementDeLotSectionsHitEntityJPA resultat = null;
			
			if (pObject != null) {
				
				final ITeleversement televersement = pObject.getTeleversement();
				
				// CONVERSION DES INTERFACES EN ENTITYJPA.
				final TeleversementEntityJPA televersementEntityJPA 
				= TeleversementConvertisseurMetierEntity
					.convertirObjetMetierEnEntityJPA(televersement);
				
				/* injecte les valeurs converties dans une Entity. */
				resultat 
					= new TeleversementDeLotSectionsHitEntityJPA(
							pObject.getId()
							, televersementEntityJPA
							, convertirLotObjetEnLotEntity(
									pObject.getLotSections()));
				
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
	 * @param pList : List&lt;ITeleversementDeLotSectionsHit&gt;
	 * 
	 * @return : List&lt;TeleversementDeLotSectionsHitEntityJPA&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static List<TeleversementDeLotSectionsHitEntityJPA> convertirListModelEnEntitiesJPA(
			final Iterable<ITeleversementDeLotSectionsHit> pList) throws Exception {
		
		synchronized (TeleversementDeLotSectionsHitConvertisseurMetierEntity.class) {
			
			/* retourne null si pList == null. */
			if (pList == null) {
				return null;
			}
			
			final List<TeleversementDeLotSectionsHitEntityJPA> resultat 
				= new ArrayList<TeleversementDeLotSectionsHitEntityJPA>();
			
			for (final ITeleversementDeLotSectionsHit objet : pList) {
				
				if (objet != null) {
					
					final TeleversementDeLotSectionsHitEntityJPA entity 
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
	 * @param pList : List&lt;TeleversementDeLotSectionsHitEntityJPA&gt; : 
	 * liste d'Entities.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	public static String afficherFormateListEntities(
			final List<TeleversementDeLotSectionsHitEntityJPA> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final ITeleversementDeLotSectionsHit entity : pList) {
			
			/* n'affiche pas une Entity null 
			 * dans la liste passée en paramètre. */
			if (entity != null) {
				
				final String stringformatee 
					= String.format(
							Locale.getDefault()
								, FORMAT_TELEVERSEMENT
								, entity.getId()
								, formaterLocalDateTimeEnString(entity.getTeleversement().getDateTeleversement())
								, entity.getTeleversement().getUtilisateur().getNom()
								, entity.getTeleversement().getGestionnaire().getNomCourt()
								, entity.getTeleversement().getTypeFichier().getNomCourt()
								, entity.getTeleversement().getNomFichierTeleverse()
								, entity.getTeleversement().getFichierStockeServeur().getAbsolutePath()
								, entity.getTeleversement().getAnneeGestion().getAnneeGestion());
				
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
	 * @param pList : List&lt;ITeleversementDeLotSectionsHit&gt; : 
	 * liste d'Objets métier.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	public static String afficherFormateListObjets(
			final List<ITeleversementDeLotSectionsHit> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final ITeleversementDeLotSectionsHit objet : pList) {
			
			/* n'affiche pas une Entity null 
			 * dans la liste passée en paramètre. */
			if (objet != null) {
				
				final String stringformatee 
					= String.format(
							Locale.getDefault()
								, FORMAT_TELEVERSEMENT
								, objet.getId()
								, formaterLocalDateTimeEnString(objet.getTeleversement().getDateTeleversement())
								, objet.getTeleversement().getUtilisateur().getNom()
								, objet.getTeleversement().getGestionnaire().getNomCourt()
								, objet.getTeleversement().getTypeFichier().getNomCourt()
								, objet.getTeleversement().getNomFichierTeleverse()
								, objet.getTeleversement().getFichierStockeServeur().getAbsolutePath()
								, objet.getTeleversement().getAnneeGestion().getAnneeGestion());
				
				stb.append(stringformatee);
				
				/* utilise le saut de la plateforme. */
				stb.append(System.getProperty(LINE_SEPARATOR));
			}
		}
		
		return stb.toString();
		
	} //Fin de afficherFormateListObjets(...)._____________________________
	

	
	/**
	 * convertit une Map&lt;Integer, INTERFACE OBJET METIER MAIS ENTITY&gt; 
	 * dans laquelle <b>toutes les valeurs sont des ENTITY</b> 
	 * <i>vues comme des Interfaces</i>
	 * en Map&lt;Integer, OBJET METIER&gt;.<br/>
	 * <ul>
	 * <li>vérifie que la value dans pMap est une ENTITY (instance réelle) 
	 * <i>vue comme une Interface commune avec l'OBJET METIER</i>.</li>
	 * <li>caste l'interface en ENTITY.</li>
	 * <li>convertit l'ENTITY en OBJET METIER.</li>
	 * <li>insère la valeur convertie en OBJET METIER 
	 * dans le résultat retourné.</li>
	 * <li>retourne une nouvelle Map ne contenant que des OBJETS METIER.</li>
	 * </ul>
	 * - retourne null si pMap == null.<br/>
	 * - retourne pMap inchangée si pMap ne contient déjà que des OBJETS METIER 
	 * (et pas des ENTITY).<br/>
	 * <br/>
	 *
	 * @param pMap : Map&lt;Integer, ISectionHit&gt; : 
	 * Map ne contenant que des ENTITY vues comme des Interfaces.
	 * 
	 * @return : Map&lt;Integer,ISectionHit&gt; : 
	 * Map d'OBJETS METIER.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Map<Integer, ISectionHit> convertirLotEntityEnLotObjet(
			final Map<Integer, ISectionHit> pMap) throws Exception {
		
		synchronized (TeleversementDeLotSectionsHitConvertisseurMetierEntity.class) {
			
			/* retourne null si pMap == null. */
			if (pMap == null) {
				return null;
			}
			
			final Map<Integer, ISectionHit> resultat 
				= new ConcurrentHashMap<Integer, ISectionHit>();
			
			for (final Entry<Integer, ISectionHit> entry : pMap.entrySet()) {
				
				final Integer key = entry.getKey();
				final ISectionHit value = entry.getValue();
				
				SectionHitEntityJPA valueCastee = null;
				ISectionHit valueConvertie = null;
				
				/* vérifie que la value dans pMap est une ENTITY 
				 * (instance réelle). */
				if (value instanceof SectionHitEntityJPA) {
					
					/* caste l'interface en ENTITY. */
					valueCastee 
						= (SectionHitEntityJPA) entry.getValue();
				
					/* convertit l'ENTITY en OBJET METIER. */
					valueConvertie 
						= SectionHitConvertisseurMetierEntity
							.convertirEntityJPAEnObjetMetier(valueCastee);
				
				} else {
					
					/* retourne pMap inchangée si pMap contient 
					 * déjà des OBJETS METIER (et pas des ENTITY). */
					valueConvertie = value;
				}
				
				/* insère la valeur convertie en OBJET METIER 
				 * dans le résultat retourné. */
				resultat.put(key, valueConvertie);
			}
			
			return resultat;

		} // Fin de synchronized.___________________________________
		
	} // Fin de convertirLotEntityEnLotObjet(...).___________________________

	
		
	/**
	 *  convertit une Map&lt;Integer, INTERFACE OBJET METIER MAIS OBJET&gt; 
	 * dans laquelle <b>toutes les valeurs sont des OBJETS METIER</b> 
	 * <i>vues comme des Interfaces</i>
	 * en Map&lt;Integer, ENTITY&gt;.<br/>
	 * <ul>
	 * <li>vérifie que la value dans pMap est une OBJET METIER (instance réelle) 
	 * <i>vue comme une Interface commune avec l'ENTITY</i>.</li>
	 * <li>caste l'interface en OBJET METIER.</li>
	 * <li>convertit l'OBJET METIER en ENTITY.</li>
	 * <li>insère la valeur convertie en ENTITY 
	 * dans le résultat retourné.</li>
	 * <li>retourne une nouvelle Map ne contenant que des ENTITY.</li>
	 * </ul>
	 * - retourne null si pMap == null.<br/>
	 * - retourne pMap inchangée si pMap ne contient déjà que des ENTITY 
	 * (et pas des OBJETS METIER).<br/>
	 * <br/>
	 *
	 * @param pMap : Map&lt;Integer, ISectionHit&gt; : 
	 * Map ne contenant que des OBJETS METIER vues comme des Interfaces.
	 * 
	 * @return : Map&lt;Integer,ISectionHit&gt; : 
	 * Map ne contenant que des ENTITY.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Map<Integer, ISectionHit> convertirLotObjetEnLotEntity(
			final Map<Integer, ISectionHit> pMap) throws Exception {
		
		synchronized (TeleversementDeLotSectionsHitConvertisseurMetierEntity.class) {
			
			/* retourne null si pMap == null. */
			if (pMap == null) {
				return null;
			}
			
			final Map<Integer, ISectionHit> resultat 
				= new ConcurrentHashMap<Integer, ISectionHit>();
			
			for (final Entry<Integer, ISectionHit> entry : pMap.entrySet()) {

				final Integer key = entry.getKey();
				final ISectionHit value = entry.getValue();
								
				SectionHit valueCastee = null;
				ISectionHit valueConvertie = null;
				
				/* vérifie que la value dans pMap est un OBJET METIER
				 * (instance réelle). */
				if (value instanceof SectionHit) {
					
					/* caste l'interface en OBJET. */
					valueCastee 
						= (SectionHit) entry.getValue();
					
					/* convertit l'OBJET METIER en ENTITY. */
					valueConvertie 
						= SectionHitConvertisseurMetierEntity
							.convertirObjetMetierEnEntityJPA(valueCastee);
					
				} else {

					/*
					 * retourne pMap inchangée si pMap contient déjà 
					 * des ENTIY (et pas des OBJETS METIER).
					 */
					valueConvertie = value;

				}
								
				/* insère la valeur convertie en ENTITY 
				 * dans le résultat retourné. */
				resultat.put(key, valueConvertie);

			}
			
			return resultat;
			
		} // Fin de synchronized.___________________________________
		
	} // Fin de convertirLotObjetEnLotEntity(...)._________________________

	
	
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
	
		
		
} // FIN DE LA CLASSE TeleversementDeLotSectionsHitConvertisseurMetierEntity.
