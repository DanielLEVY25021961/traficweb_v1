package levy.daniel.application.model.persistence.metier.sections.localisations;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.sections.localisations.ILocalisationHit;
import levy.daniel.application.model.metier.sections.localisations.impl.LocalisationHit;
import levy.daniel.application.model.persistence.metier.anneegestion.AnneeGestionConvertisseurMetierEntity;
import levy.daniel.application.model.persistence.metier.sections.localisations.entities.jpa.LocalisationHitEntityJPA;
import levy.daniel.application.model.persistence.metier.utilisateur.UtilisateurCerbereConvertisseurMetierEntity;

/**
 * CLASSE LocalisationHitConvertisseurMetierEntity :<br/>
 * .<br/>
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
 * @since 6 juil. 2019
 *
 */
public class LocalisationHitConvertisseurMetierEntity {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(LocalisationHitConvertisseurMetierEntity.class);

	// *************************METHODES************************************/
	
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private LocalisationHitConvertisseurMetierEntity() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * <b>Crée un OBJET METIER à partir d'une EntityJPA</b>.<br/>
	 * <ul>
	 * <li>retourne un OBJET METIER avec toutes les valeurs 
	 * à null si pEntityJPA == null.</li>
	 * </ul>
	 *
	 * @param pEntityJPA : LocalisationHitEntityJPA.<br/>
	 * 
	 * @return : ILocalisationHit.<br/>
	 */
	public static ILocalisationHit creerObjetMetierAPartirEntityJPA(
			final LocalisationHitEntityJPA pEntityJPA) {

		synchronized (LocalisationHitConvertisseurMetierEntity.class) {
			
			final ILocalisationHit objet 
				= new LocalisationHit();
			
			if (pEntityJPA != null) {

				objet.setId(pEntityJPA.getId());
				objet.setNumRoute(pEntityJPA.getNumRoute());
				objet.setIndiceNumRoute(pEntityJPA.getIndiceNumRoute());
				objet.setIndiceLettreRoute(pEntityJPA.getIndiceLettreRoute());
				objet.setCategorieAdminRoute(pEntityJPA.getCategorieAdminRoute());
				objet.setNumDepartement(pEntityJPA.getNumDepartement());
				objet.setLieuDitOrigine(pEntityJPA.getLieuDitOrigine());
				objet.setPrOrigine(pEntityJPA.getPrOrigine());
				objet.setAbsOrigine(pEntityJPA.getAbsOrigine());
				objet.setLieuDitExtremite(pEntityJPA.getLieuDitExtremite());
				objet.setPrExtremite(pEntityJPA.getPrExtremite());
				objet.setAbsExtremite(pEntityJPA.getAbsExtremite());
				objet.setLieuDitComptage(pEntityJPA.getLieuDitComptage());
				objet.setPrComptage(pEntityJPA.getPrComptage());
				objet.setAbsComptage(pEntityJPA.getAbsComptage());

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
	 * @param pEntityJPA : LocalisationHitEntityJPA.<br/>
	 * 
	 * @return : ILocalisationHit : Objet métier.<br/>
	 */
	public static ILocalisationHit convertirEntityJPAEnObjetMetier(
			final LocalisationHitEntityJPA pEntityJPA) {
		
		synchronized (LocalisationHitConvertisseurMetierEntity.class) {
			
			ILocalisationHit objet = null;

			if (pEntityJPA != null) {

				/* récupère les valeurs dans l'Entity. */
				/* injecte les valeurs typées dans un OBJET METIER. */
					objet
						 = new LocalisationHit(
							pEntityJPA.getId()
							, pEntityJPA.getNumRoute()
							, pEntityJPA.getIndiceNumRoute()
							, pEntityJPA.getIndiceLettreRoute()
							, pEntityJPA.getCategorieAdminRoute()
							, pEntityJPA.getNumDepartement()
							, pEntityJPA.getLieuDitOrigine()
							, pEntityJPA.getPrOrigine()
							, pEntityJPA.getAbsOrigine()
							, pEntityJPA.getLieuDitExtremite()
							, pEntityJPA.getPrExtremite()
							, pEntityJPA.getAbsExtremite()
							, pEntityJPA.getLieuDitComptage()
							, pEntityJPA.getPrComptage()
							, pEntityJPA.getAbsComptage());
					
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
	 * @param pList : List&lt;LocalisationHitEntityJPA&gt;.<br/>
	 * 
	 * @return : List&lt;ILocalisationHit&gt;.<br/>
	 */
	public static List<ILocalisationHit> convertirListEntitiesJPAEnModel(
			final List<LocalisationHitEntityJPA> pList) {
		
		synchronized (LocalisationHitConvertisseurMetierEntity.class) {
			
			/* retourne null si pList == null. */
			if (pList == null) {
				return null;
			}
			
			final List<ILocalisationHit> resultat 
				= new ArrayList<ILocalisationHit>();
			
			for (final LocalisationHitEntityJPA entity : pList) {
				
				/* n'insère dans la liste résultat 
				 * que les Entities non null. */
				if (entity != null) {
					
					final ILocalisationHit objet 													
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
	 * @param pObject : ILocalisationHit.<br/>
	 *  
	 * @return : LocalisationHitEntityJPA.<br/>
	 */
	public static LocalisationHitEntityJPA creerEntityJPA(
			final ILocalisationHit pObject) {
		
		synchronized (LocalisationHitConvertisseurMetierEntity.class) {
			
			final LocalisationHitEntityJPA entity 
				= new LocalisationHitEntityJPA();
			
			if (pObject != null) {
				
				entity.setId(pObject.getId());
				entity.setDateLocalisationHit(pObject.getDateLocalisationHit());
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
	 * @param pObject : ILocalisationHit : Objet métier.<br/>
	 * 
	 * @return : LocalisationHitEntityJPA : ENTITY JPA.<br/>
	 */
	public static LocalisationHitEntityJPA convertirObjetMetierEnEntityJPA(
			final ILocalisationHit pObject) {
		
		synchronized (LocalisationHitConvertisseurMetierEntity.class) {
			
			LocalisationHitEntityJPA resultat = null;
			
			if (pObject != null) {
								
				/* injecte les valeurs String dans un DTO. */
				resultat 
					= new LocalisationHitEntityJPA(
							pObject.getId()
							, pObject.getDateLocalisationHit()
							, UtilisateurCerbereConvertisseurMetierEntity.convertirObjetMetierEnEntityJPA(pObject.getUtilisateur()) 
							, pObject.getGestionnaire()
							, pObject.getTypeFichier()
							, pObject.getNomFichierTeleverse()
							, pObject.getFichierStockeServeur()
							, AnneeGestionConvertisseurMetierEntity.convertirObjetMetierEnEntityJPA(pObject.getAnneeGestion()));
				
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
	 * @param pList : List&lt;ILocalisationHit&gt;
	 * 
	 * @return : List&lt;LocalisationHitEntityJPA&gt;.<br/>
	 */
	public static List<LocalisationHitEntityJPA> convertirListModelEnEntitiesJPA(
			final Iterable<ILocalisationHit> pList) {
		
		synchronized (LocalisationHitConvertisseurMetierEntity.class) {
			
			/* retourne null si pList == null. */
			if (pList == null) {
				return null;
			}
			
			final List<LocalisationHitEntityJPA> resultat 
				= new ArrayList<LocalisationHitEntityJPA>();
			
			for (final ILocalisationHit objet : pList) {
				
				if (objet != null) {
					
					final LocalisationHitEntityJPA entity 
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
	 * @param pList : List&lt;LocalisationHitEntityJPA&gt; : 
	 * liste d'Entities.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	public static String afficherFormateListEntities(
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
					= String.format(
							Locale.getDefault()
								, FORMAT_TELEVERSEMENT
								, entity.getId()
								, formaterLocalDateTimeEnString(entity.getDateLocalisationHit())
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
	 * @param pList : List&lt;ILocalisationHit&gt; : 
	 * liste d'Objets métier.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	public static String afficherFormateListObjets(
			final List<ILocalisationHit> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final ILocalisationHit objet : pList) {
			
			/* n'affiche pas une Entity null 
			 * dans la liste passée en paramètre. */
			if (objet != null) {
				
				final String stringformatee 
					= String.format(
							Locale.getDefault()
								, FORMAT_TELEVERSEMENT
								, objet.getId()
								, formaterLocalDateTimeEnString(objet.getDateLocalisationHit())
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
	
	
	
} // FIN DE LA CLASSE LocalisationHitConvertisseurMetierEntity.--------------
