package levy.daniel.application.model.persistence.metier.televersementdelotsections.dao.jpaspring.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.televersement.ITeleversement;
import levy.daniel.application.model.metier.televersementdelotsections.ITeleversementDeLotSectionsHit;
import levy.daniel.application.model.persistence.daoexceptions.GestionnaireDaoException;
import levy.daniel.application.model.persistence.metier.sections.ISectionHitDAO;
import levy.daniel.application.model.persistence.metier.sections.SectionHitConvertisseurMetierEntity;
import levy.daniel.application.model.persistence.metier.sections.entities.jpa.SectionHitEntityJPA;
import levy.daniel.application.model.persistence.metier.televersement.ITeleversementDAO;
import levy.daniel.application.model.persistence.metier.televersementdelotsections.ITeleversementDeLotSectionsHitDAO;
import levy.daniel.application.model.persistence.metier.televersementdelotsections.TeleversementDeLotSectionsHitConvertisseurMetierEntity;
import levy.daniel.application.model.persistence.metier.televersementdelotsections.entities.jpa.TeleversementDeLotSectionsHitEntityJPA;

/**
 * CLASSE TeleversementDeLotSectionsHitDAOJPASpring :<br/>
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
 * @since 5 août 2019
 *
 */
@Repository(value="TeleversementDeLotSectionsHitDAOJPASpring")
public class TeleversementDeLotSectionsHitDAOJPASpring implements ITeleversementDeLotSectionsHitDAO {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe TeleversementDeLotSectionsHitDAOJPASpring".<br/>
	 */
	public static final String CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING 
		= "Classe TeleversementDeLotSectionsHitDAOJPASpring";
	
	/**
	 * " - ".
	 */
	public static final String SEPARATEUR_MOINS_AERE 
		= " - ";
	
	/**
	 * saut de ligne de la plateforme.<br/>
	 */
	public static final String SAUT_LIGNE_PLATEFORME 
		= System.getProperty("line.separator");
	
	/**
	 * "this.entityManager est NULL dans le présent DAO".<br/>
	 */
	public static final String MESSAGE_ENTITYMANAGER_NULL 
	= "this.entityManager est NULL dans le présent DAO";

	
	/**
	 * "select televersementDeLotSectionsHit from 
	 * TeleversementDeLotSectionsHitEntityJPA as televersementDeLotSectionsHit ".<br/>
	 */
	public static final String SELECT_OBJET 
		= "select televersementDeLotSectionsHit from "
				+ "TeleversementDeLotSectionsHitEntityJPA as televersementDeLotSectionsHit ";
	
	/**
	 * "STOCKAGE IMPOSSIBLE : l'Objet métier à créer est null".
	 */
	public static final String OBJET_NULL 
		= "STOCKAGE IMPOSSIBLE : l'Objet métier à créer est null";
	
	/**
	 * "STOCKAGE IMPOSSIBLE - DOUBLON : 
	 * l'objet existe déjà dans le stockage : ".
	 */
	public static final String DOUBLON 
		= "STOCKAGE IMPOSSIBLE - DOUBLON : l'objet existe déjà dans le stockage : ";
	
	/**
	 * "STOCKAGE IMPOSSIBLE - les champs OBLIGATOIRES (televersement, lotSections) ne sont pas remplis : ".
	 */
	public static final String CHAMPS_OBLIGATOIRES 
		= "STOCKAGE IMPOSSIBLE - les champs OBLIGATOIRES (televersement, lotSections) ne sont pas remplis : ";
	
	/**
	 * JPA EntityManager <b>injecté par SPRING</b>.<br/>
	 */
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private transient EntityManager entityManager;
	
	/**
	 * Gestionnaire pour les Exceptions de DAO.<br/>
	 */
	private final transient GestionnaireDaoException gestionnaireException 
		= new GestionnaireDaoException();

	/**
	 * Liste des messages d'erreur à l'intention de l'utilisateur.<br/>
	 * Ne peut jamis être null. <b>tester avec isEmpty()</b>.<br/>
	 */
	private transient List<String> messagesErrorUtilisateurList 
		= new ArrayList<String>(); 

	/**
	 * DAO ITeleversementDAO.<br/>
	 * injecté par Spring via son Setter.
	 */
	private transient ITeleversementDAO televersementDAO;
	
	/**
	 * DAO ISectionHitDAO.<br/>
	 * injecté par Spring via son Setter.
	 */
	private transient ISectionHitDAO sectionHitDAO;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(TeleversementDeLotSectionsHitDAOJPASpring.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public TeleversementDeLotSectionsHitDAOJPASpring() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * retourne un boolean qui stipule si les champs obligatoires 
	 * de pObject sont bien remplis.<br/>
	 * <br/>
	 * - retourne true si les champs obligatoires sont tous remplis.<br/>
	 * - retourne false si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : ITeleversementDeLotSectionsHit : Objet Métier.
	 * 
	 * @return : boolean : 
	 * true si les champs obligatoires sont tous remplis.<br/>
	 */
	private boolean champsObligatoiresRemplis(
				final ITeleversementDeLotSectionsHit pObject) {
		
		/* retourne false si pObject == null. */
		if (pObject == null) {
			return false;
		}
		
		if (pObject.getTeleversement() == null) {
			return false;
		}
		
		if (pObject.getLotSections() == null) {
			return false;
		}
		
		if (pObject.getLotSections().isEmpty()) {
			return false;
		}
				
		return true;
		
	} // Fin de champsObligatoiresRemplis(...).____________________________
	
	
	
	/**
	 * retourne la requete JPQL paramétrée pour la méthode 
	 * <code><b>retrieve(OBJET METIER pObject)</b></code> 
	 * en tenant compte de la getion des paramètres null.<br/>
	 * <br/>
	 *
	 * @param pObject : ITeleversementDeLotSectionsHit : OBJET METIER
	 * 
	 * @return javax.persistence.Query
	 * 
	 * @throws Exception
	 */
	private Query fournirRequeteEgaliteMetier(
			final ITeleversementDeLotSectionsHit pObject) throws Exception {
		
		/* REQUETE HQL PARMETREE. */
		String requeteString = null;
		
		Query requete = null;
		
		requeteString
		= SELECT_OBJET
		+ "where ((televersementDeLotSectionsHit.televersement.id IS NULL and :pTeleversementId IS NULL) OR (televersementDeLotSectionsHit.televersement.id = :pTeleversementId))";
				
		/* Construction de la requête HQL. */
		requete
			= this.entityManager.createQuery(requeteString);
		
		/* Passage des paramètres de la requête JPQL. */
		requete.setParameter("pTeleversementId", pObject.getTeleversement().getId());

		return requete;
		
	} // Fin de fournirRequeteEgaliteMetier(...).__________________________

	
	
	/**
	 * persiste tous les composants transients de l'Entity JPA pEntity.
	 *
	 * @param pEntity : ITeleversementDeLotSectionsHit : ENTITYJPA.<br/>
	 * 
	 * @throws Exception 
	 */
	private void stockerComposants(
			final ITeleversementDeLotSectionsHit pEntity) throws Exception {
		
		/* RECUPERATION DES ENTITY COMPOSANTES TRANSIENTES 
		 * DANS LE COMPOSITE. */
		final ITeleversement televersementEntity 
			= pEntity.getTeleversement();
		
		final Map<Integer, ISectionHit> lotSectionsObjet 
			= pEntity.getLotSections();
		
		/* CREATION DANS LE STOCKAGE OU RECUPERATION DES ENTITY 
		 * COMPOSANTES PERSISTES. */
		final ITeleversement televersementEntityPersiste 
			= this.televersementDAO
							.createOrRetrieve(televersementEntity);
		
		final Map<Integer, ISectionHit> lotSectionsEntityPersiste 
			= new ConcurrentHashMap<Integer, ISectionHit>();
		
		for (final Entry<Integer, ISectionHit> entry : lotSectionsObjet.entrySet()) {
			
			final Integer numero = entry.getKey();
			final ISectionHit objet = entry.getValue();
			
			final SectionHitEntityJPA sectionHitEntityJPA 
				= SectionHitConvertisseurMetierEntity
					.convertirObjetMetierEnEntityJPA(objet);
			
			final ISectionHit sectionHitEntityJPAPersistent 
				= this.sectionHitDAO.createOrRetrieve(sectionHitEntityJPA);
			
			lotSectionsEntityPersiste.put(numero, sectionHitEntityJPAPersistent);
		}
		
		/* INJECTION DES ENTITY COMPOSANTES PERSISTEES 
		 * DANS LE COMPOSITE. */
		pEntity.setTeleversement(televersementEntityPersiste);
		pEntity.setLotSections(lotSectionsEntityPersiste);
		
	} // Fin de stockerComposants(...).____________________________________


	
	/**
	 * récupère dans le stockage les COMPOSANTS PERSISTANTS 
	 * d'un objet métier pObject.
	 * <ul>
	 * <li>récupère dans le stockage les composants persistants.</li>
	 * <li><b>injecte les composants persistants dans pObject</b>.</li>
	 * </ul>
	 * - ne fait rien lorsqu'un composant de pObject n'est pas persisté.<br/>
	 * <br/>
	 *
	 * @param pObject : ITeleversementDeLotSectionsHit : Objet métier.
	 * 
	 * @throws Exception
	 */
	private void rechercherComposantsPersistants(
			final ITeleversementDeLotSectionsHit pObject) throws Exception {

		/* récupère dans le stockage les composants persistants. */
		final ITeleversement televersementPersistent 
			= this.televersementDAO.retrieve(pObject.getTeleversement());

		/* ne fait rien lorsqu'un composant de pObject n'est pas persisté. */
		if (televersementPersistent == null) {
			return;
		}

		final Map<Integer, ISectionHit> lotSectionsObjet 
		= pObject.getLotSections();

		final Map<Integer, ISectionHit> lotSectionsEntityPersiste 
			= new ConcurrentHashMap<Integer, ISectionHit>();

		for (final Entry<Integer, ISectionHit> entry : lotSectionsObjet.entrySet()) {

			final Integer numero = entry.getKey();
			final ISectionHit objet = entry.getValue();

			final SectionHitEntityJPA sectionHitEntityJPA 
				= SectionHitConvertisseurMetierEntity
					.convertirObjetMetierEnEntityJPA(objet);

			/* récupère dans le stockage les composants persistants. */
			final ISectionHit sectionHitEntityJPAPersistent 
				= this.sectionHitDAO.retrieve(sectionHitEntityJPA);

			lotSectionsEntityPersiste.put(numero, sectionHitEntityJPAPersistent);
		}

		/* INJECTION DU COMPOSANT PERSISTANT DANS L'ENTITE A RECHERCHER. */
		pObject.setTeleversement(televersementPersistent);
		pObject.setLotSections(lotSectionsEntityPersiste);

	} // Fin de rechercherComposantsPersistants(...).______________________

	
	
	/* CREATE ************/

	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public ITeleversementDeLotSectionsHit create(
			final ITeleversementDeLotSectionsHit pObject) throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* instancie une nouvelle liste à chaque appel de la méthode. */
		this.messagesErrorUtilisateurList = new ArrayList<String>();

		
		/* NULL : retourne null si pObject == null. */
		if (pObject == null) {
			
			/* ajout d'une explication dans le rapport utilisateur. */
			this.messagesErrorUtilisateurList.add(OBJET_NULL);
			
			return null;
			
		} // Fin de NULL._____________________________________
		
		
		/* CHAMPS OBLIGATOIRES : retourne null si les attributs obligatoires 
		 * de pObject ne sont pas remplis.*/
		if (!this.champsObligatoiresRemplis(pObject)) {
			
			final String message = CHAMPS_OBLIGATOIRES + pObject.toString();
			
			/* ajout d'une explication dans le rapport utilisateur. */
			this.messagesErrorUtilisateurList.add(message);
			
			return null;
			
		} // Fin de CHAMPS OBLIGATOIRES.______________________________
		
		
		/* DOUBLON : retourne null si pObject est un doublon. */
		if (this.exists(pObject)) {
			
			final String message = DOUBLON + pObject.toString();
			
			/* ajout d'une explication dans le rapport utilisateur. */
			this.messagesErrorUtilisateurList.add(message);
			
			return null;
			
		} // Fin de DOUBLON.___________________________________
		

		ITeleversementDeLotSectionsHit persistentObject = null;

		try {
			
			/* conversion de l'OBJET METIER en ENTITY. */
			final TeleversementDeLotSectionsHitEntityJPA entity 
				= TeleversementDeLotSectionsHitConvertisseurMetierEntity
						.convertirObjetMetierEnEntityJPA(pObject);
						
			// SAUVEGARDE DES COMPOSANTS *******************************
			this.stockerComposants(entity);

			/* ***************** */
			/* PERSISTE en base. */
			this.entityManager.persist(entity);

			/* conversion de l'ENTITY en OBJET METIER. */
			persistentObject 
				= TeleversementDeLotSectionsHitConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
							, "méthode create(object)", e);

		}

		/* retourne l'Objet persistant. */
		return persistentObject;

	} // Fin de create(...)._______________________________________________


	

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public ITeleversementDeLotSectionsHit createOrRetrieve(
			final ITeleversementDeLotSectionsHit pEntity) throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* instancie une nouvelle liste à chaque appel de la méthode. */
		this.messagesErrorUtilisateurList = new ArrayList<String>();

		
		/* NULL : retourne null si pObject == null. */
		if (pEntity == null) {
			
			/* ajout d'une explication dans le rapport utilisateur. */
			this.messagesErrorUtilisateurList.add(OBJET_NULL);
			
			return null;
			
		} // Fin de NULL._____________________________________
		
		
		/* CHAMPS OBLIGATOIRES : retourne null si les attributs obligatoires 
		 * de pObject ne sont pas remplis.*/
		if (!this.champsObligatoiresRemplis(pEntity)) {
			
			final String message = CHAMPS_OBLIGATOIRES + pEntity.toString();
			
			/* ajout d'une explication dans le rapport utilisateur. */
			this.messagesErrorUtilisateurList.add(message);
			
			return null;
			
		} // Fin de CHAMPS OBLIGATOIRES.______________________________
		
		
		/* DOUBLON : retourne l'objet déjà persisté si pObject est un doublon. */
		if (this.exists(pEntity)) {
			
			return TeleversementDeLotSectionsHitConvertisseurMetierEntity
					.convertirObjetMetierEnEntityJPA(
							this.retrieve(pEntity));
			
		} // Fin de DOUBLON.___________________________________
		

		ITeleversementDeLotSectionsHit persistentObject = null;

		try {
			
			TeleversementDeLotSectionsHitEntityJPA entity = null;
			
			/* conversion de l'OBJET METIER en ENTITY. */
			if (!(pEntity instanceof TeleversementDeLotSectionsHitEntityJPA)) {
				entity 
				= TeleversementDeLotSectionsHitConvertisseurMetierEntity
						.convertirObjetMetierEnEntityJPA(pEntity);
			} else {
				entity = (TeleversementDeLotSectionsHitEntityJPA) pEntity;
			}
									
			// SAUVEGARDE DES COMPOSANTS *******************************
			this.stockerComposants(entity);

			/* ***************** */
			/* PERSISTE en base. */
			this.entityManager.persist(entity);

			/* RETOURNE L'ENTITY. */
			persistentObject = entity;

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
							, "méthode createOrRetrieve(object)", e);

		}

		/* retourne l'Objet persistant. */
		return persistentObject;

	} // Fin de createOrRetrieve(...)._____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void persist(
			final ITeleversementDeLotSectionsHit pObject) throws Exception {
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}
		
		/* ne fait rien si pObject == null. */
		if (pObject == null) {
			return;
		}
		
		/* ne fait rien si les attributs obligatoires 
		 * de pObject ne sont pas remplis.*/
		if (!this.champsObligatoiresRemplis(pObject)) {
			return;
		}
		
		/* ne fait rien si pObject est un doublon. */
		if (this.exists(pObject)) {
			return;
		}
		
		if (this.existsId(pObject.getId())) {
			return;
		}


		/* conversion de l'OBJET METIER en ENTITY. */
		final TeleversementDeLotSectionsHitEntityJPA entity = 
				TeleversementDeLotSectionsHitConvertisseurMetierEntity
					.convertirObjetMetierEnEntityJPA(pObject);
								
		// SAUVEGARDE DES COMPOSANTS *******************************
		this.stockerComposants(entity);

		try {
			
			/* ***************** */
			/* PERSISTE en base. */
			this.entityManager.persist(entity);

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
							, "méthode persist(object)", e);

		}

	} // Fin de persist(...).______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long createReturnId(
			final ITeleversementDeLotSectionsHit pObject) throws Exception {
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
		
		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}
		
		/* retourne null si les attributs obligatoires 
		 * de pObject ne sont pas remplis.*/
		if (!this.champsObligatoiresRemplis(pObject)) {
			return null;
		}
		
		/* retourne null si pObject est un doublon. */
		if (this.exists(pObject)) {
			return null;
		}
		
		
		/* conversion de l'OBJET METIER en ENTITY. */
		final TeleversementDeLotSectionsHitEntityJPA entity = 
				TeleversementDeLotSectionsHitConvertisseurMetierEntity
					.convertirObjetMetierEnEntityJPA(pObject);
				
		// SAUVEGARDE DES COMPOSANTS *******************************
		this.stockerComposants(entity);
		
		ITeleversementDeLotSectionsHit persistentObject = null;
		
		try {
			
			/* ***************** */
			/* PERSISTE en base. */
			this.entityManager.persist(entity);
			
			/* conversion de l'ENTITY en OBJET METIER. */
			persistentObject 
				= TeleversementDeLotSectionsHitConvertisseurMetierEntity
				.convertirEntityJPAEnObjetMetier(entity);

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
							, "méthode createReturnId(object)", e);

		}
		
		/* retourne l'ID de l'Objet persistant. */
		if (persistentObject != null) {
			return persistentObject.getId();
		}
		
		return null;		

	} // Fin de createReturnId(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<ITeleversementDeLotSectionsHit> saveIterable(
			final Iterable<ITeleversementDeLotSectionsHit> pList) 
					throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		/* conversion de le liste de MODEL en liste d'ENTITIES. */
		final List<TeleversementDeLotSectionsHitEntityJPA> listeEntities 
			= TeleversementDeLotSectionsHitConvertisseurMetierEntity
				.convertirListModelEnEntitiesJPA(pList);
		
		final List<ITeleversementDeLotSectionsHit> resultat 
			= new ArrayList<ITeleversementDeLotSectionsHit>();

		final Iterator<TeleversementDeLotSectionsHitEntityJPA> iteS 
			= listeEntities.iterator();

		try {

			while (iteS.hasNext()) {

				final TeleversementDeLotSectionsHitEntityJPA entity = iteS.next();

				/* passe un null dans le lot. */
				if (entity == null) {
					continue;
				}
					
					
				/* Passe les doublons existants en base. */
				if (!this.exists(entity)) {
					
					/* passe si les attributs obligatoires 
					 * de l'objet ne sont pas remplis.*/
					if (this.champsObligatoiresRemplis(entity)) {
						
						ITeleversementDeLotSectionsHit objectPersistant = null;

						try {
														
							// SAUVEGARDE DES COMPOSANTS *******************************
							this.stockerComposants(entity);

							/* ***************** */
							/* PERSISTE en base. */
							this.entityManager.persist(entity);

							/* conversion de l'ENTITY en OBJET METIER. */
							objectPersistant 
								= TeleversementDeLotSectionsHitConvertisseurMetierEntity
								.convertirEntityJPAEnObjetMetier(entity);

						} catch (Exception e) {

							/* LOG. */
							if (LOG.isFatalEnabled()) {
								LOG.fatal(e.getMessage(), e);
							}

							/* Gestion de la DAO Exception. */
							this.gestionnaireException
								.gererException(
										CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
											, "Méthode saveIterable(lot)", e);
						}
						
						/* ne sauvegarde pas un doublon 
						 * présent dans le lot. */
						if (objectPersistant != null) {

							/* Ajoute à l'iterable resultat. */
							resultat.add(objectPersistant);								
						}
						
					} // Entity avec attributs obligatoires remplis.
					
				} // Entity persistante._________________
				
			} // Next._____________________________________

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
							, "Méthode saveIterable(lot)", e);

		}

		/* retourne l'iterable resultat. */
		return resultat;

	} // Fin de saveIterable(...)._________________________________________
	
	

	/* READ *************/


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ITeleversementDeLotSectionsHit retrieve(
			final ITeleversementDeLotSectionsHit pObject) throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* return null si pObject == null. */
		if (pObject == null) {
			return null;
		}

		ITeleversementDeLotSectionsHit objetResultat = null;		
		TeleversementDeLotSectionsHitEntityJPA entity = null;
		
		/* RECHERCHE DES COMPOSANTS EXISTANTS ET INJECTION DANS pOBJECT. */
		this.rechercherComposantsPersistants(pObject);
		
		/* récupération de la requête paramétrée. */
		final Query requete = this.fournirRequeteEgaliteMetier(pObject);
		
		if (requete == null) {
			return null;
		}
		
		try {
			
			/* Execution de la requete HQL. */
			entity 
				= (TeleversementDeLotSectionsHitEntityJPA) 
							requete.getSingleResult();
			
			/* conversion de l'ENTITY en OBJET METIER. */
			objetResultat 
				= TeleversementDeLotSectionsHitConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);
			
		}
		catch (NoResultException noResultExc) {
			
			/* retourne null si l'Objet métier n'existe pas 
			 * dans le stockage. */
			return null;
			
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
						, "Méthode retrieve(objet)", e);
		}
		
		/* retourne l'objet metier trouvé. */
		return objetResultat;

	} // Fin de retrieve(...)._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ITeleversementDeLotSectionsHit findById(
			final Long pId) throws Exception {
		
		/* retourne null si pId == null. */
		if (pId == null) {
			return null;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		ITeleversementDeLotSectionsHit objetTrouve = null;
		TeleversementDeLotSectionsHitEntityJPA entity = null;
		
		try {
			
			/* ************************* */
			/* récupération de l'ENTITY. */
			entity 
				= this.entityManager.find(
						TeleversementDeLotSectionsHitEntityJPA.class, pId);
			
			/* conversion de l'ENTITY en OBJET METIER. */
			objetTrouve 
				= TeleversementDeLotSectionsHitConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);
			
		}
		catch (Exception e) {
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
						, "Méthode findById(ID)", e);
			
		}
		
		/* retourne l'objet metier trouvé. */
		return objetTrouve;
				
	} // Fin de findById(...)._____________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long retrieveId(
			final ITeleversementDeLotSectionsHit pObject) throws Exception {
		
		/* return null si pObject == null. */
		if (pObject == null) {
			return null;
		}
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
		
		ITeleversementDeLotSectionsHit objetResultat = null;		
		TeleversementDeLotSectionsHitEntityJPA entity = null;

		/* RECHERCHE DES COMPOSANTS EXISTANTS ET INJECTION DANS pOBJECT. */
		this.rechercherComposantsPersistants(pObject);
		
		/* récupération de la requête paramétrée. */
		final Query requete = this.fournirRequeteEgaliteMetier(pObject);
		
		if (requete == null) {
			return null;
		}
		
		try {
			
			/* Execution de la requete HQL. */
			entity 
				= (TeleversementDeLotSectionsHitEntityJPA) requete.getSingleResult();
			
			/* conversion de l'ENTITY en OBJET METIER. */
			objetResultat 
				= TeleversementDeLotSectionsHitConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);
			
		}
		catch (NoResultException noResultExc) {
			
			/* retourne null si l'Objet métier n'existe pas 
			 * dans le stockage. */
			return null;
			
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
						, "Méthode retrieveId(objet)", e);
		}
		
		/* retourne l'ID de l'objet metier trouvé. */
		if (objetResultat != null) {
			return objetResultat.getId();
		}
		
		return null;
		
	} // Fin de retrieveId(...).___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ITeleversementDeLotSectionsHit> rechercherRapide(
			final String pString) throws Exception {
				
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
		
		/* Création de la requête JPQL sous forme de String. */
		final String requeteString 
			= SELECT_OBJET 
			+ "where televersementDeLotSectionsHit.televersement.dateTeleversement LIKE :pattern "
					+ "OR televersementDeLotSectionsHit.televersement.utilisateur.nom LIKE :pattern "
					+ "OR televersementDeLotSectionsHit.televersement.gestionnaire.nomCourt LIKE :pattern "
					+ "OR televersementDeLotSectionsHit.televersement.typeFichier.nomCourt LIKE :pattern" 
					+ "OR televersementDeLotSectionsHit.televersement.anneeGestion.anneeGestion LIKE :pattern";
		
		/* Construction de la requête HQL. */
		final Query requete 
			= this.entityManager.createQuery(requeteString);
		
		/* Passage des paramètres de la requête HQL. */
		final String patternRecherche = "%" + pString + "%";
		
		requete.setParameter("pattern", patternRecherche);
		
		List<TeleversementDeLotSectionsHitEntityJPA> resultatEntity = null;
				
		List<ITeleversementDeLotSectionsHit> resultat = null;
		
		try {
			
			/* Execution de la requete HQL. */
			resultatEntity 
				= requete.getResultList();
			
			/* conversion de la liste ENTITY en liste OBJET METIER. */
			resultat 
				= TeleversementDeLotSectionsHitConvertisseurMetierEntity
					.convertirListEntitiesJPAEnModel(resultatEntity);
			
		}
		catch (NoResultException noResultExc) {
			
			/* retourne null si l'Objet métier n'existe pas 
			 * dans le stockage. */
			return null;
			
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
						, "Méthode rechercherRapide(String %recherche%)", e);
		}
		
		/* retourne la liste d'objets métier. */
		if (resultat != null) {
			return resultat;
		}
		
		return null;
		
	} // Fin de rechercherRapide(...)._____________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ITeleversementDeLotSectionsHit> findAll() throws Exception {
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
		
		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "from TeleversementDeLotSectionsHitEntityJPA";
		
		List<TeleversementDeLotSectionsHitEntityJPA> resultatEntity = null;
		
		List<ITeleversementDeLotSectionsHit> resultat = null;
		
		try {
			
			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString);
			
			/* Exécute la javax.persistence.Query. */
			resultatEntity = query.getResultList();
			
			/* convertit la liste d'Entities en OBJETS METIER. */
			resultat = TeleversementDeLotSectionsHitConvertisseurMetierEntity
						.convertirListEntitiesJPAEnModel(
								resultatEntity);

		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
						, "Méthode findAll()", e);
			
		}
		
		/* Retourne la liste résultat. */
		return resultat;
		
	} // Fin de findAll()._________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ITeleversementDeLotSectionsHit> findAllMax(
			final int pStartPosition
				, final int pMaxResult) throws Exception {

		/* retourne null si pId est en dehors des index de stockage. */
		if (pStartPosition > this.count() - 1) {
			return null;
		}
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
		
		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "from TeleversementDeLotSectionsHitEntityJPA";
		
		List<TeleversementDeLotSectionsHitEntityJPA> resultatEntity = null;
		
		List<ITeleversementDeLotSectionsHit> resultat = null;
		
		try {
			
			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString)
					.setFirstResult(pStartPosition)
						.setMaxResults(pMaxResult);
			
			/* Exécute la javax.persistence.Query. */
			resultatEntity = query.getResultList();
						
			/* convertit la liste d'Entities en OBJETS METIER. */
			resultat 
			= TeleversementDeLotSectionsHitConvertisseurMetierEntity
				.convertirListEntitiesJPAEnModel(resultatEntity);

		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
						, "Méthode findAllMax(int pStartPosition"
								+ ", int pMaxResult)", e);
			
		}
		
		/* Retourne la liste résultat. */
		return resultat;
		
	} // Fin de findAllMax(...).___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<ITeleversementDeLotSectionsHit> findAllIterable(
			final Iterable<Long> pIds) throws Exception {
		
		/* retourne null si pIds == null. */
		if (pIds == null) {
			return null;
		}

		final List<ITeleversementDeLotSectionsHit> resultat 
			= new ArrayList<ITeleversementDeLotSectionsHit>();

		for (final Long id : pIds) {
			
			final ITeleversementDeLotSectionsHit objet = this.findById(id);
			
			if (objet != null) {
				resultat.add(objet);
			}
		}
		
		/* Retourne la liste résultat. */
		return resultat;
		
	} // Fin de findAllIterable(...).______________________________________



	/* UPDATE *************/

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ITeleversementDeLotSectionsHit update(
			final ITeleversementDeLotSectionsHit pObject) throws Exception {
		
		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}
		
		/* retourne pObject si l'objet n'est pas 
		 * déjà persistant en base. */
		if (!this.existsId(pObject.getId())) {
			return pObject;
		}
		
		/* retourne null si pObject créerait un doublon. */
		if (this.exists(pObject)) {
			return null;
		}
		
		/* retourne null si les attributs obligatoires 
		 * de pObject ne sont pas remplis.*/
		if (!this.champsObligatoiresRemplis(pObject)) {
			return null;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
				
		/* conversion de l'OBJET METIER en ENTITY. */
		final TeleversementDeLotSectionsHitEntityJPA entity = 
				TeleversementDeLotSectionsHitConvertisseurMetierEntity
					.convertirObjetMetierEnEntityJPA(pObject);
								
		// SAUVEGARDE DES COMPOSANTS *******************************
		this.stockerComposants(entity);

		ITeleversementDeLotSectionsHit persistentObject = null;
		
		try {
			
			/* MODIFIE en base. */
			this.entityManager.merge(entity);

			/* conversion de l'ENTITY en OBJET METIER. */
			persistentObject 
				= TeleversementDeLotSectionsHitConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);
			
		}
		catch (Exception e) {
		
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
						, "Méthode update(objet)", e);
						
		}
				
		/* retourne l'Objet persistant modifié. */
		return persistentObject;
		
	} // Fin de update(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public ITeleversementDeLotSectionsHit updateById(
			final Long pId, final ITeleversementDeLotSectionsHit pObjectModifie) 
					throws Exception {

		/* retourne null si pId == null. */
		if (pId == null) {
			return null;
		}

		/* retourne null si pId est hors indexes. */
		if (this.findById(pId) == null) {
			return null;
		}

		/* retourne null si pObjectModifie == null. */
		if (pObjectModifie == null) {
			return null;
		}

		/*
		 * retourne null si les attributs obligatoires 
		 * de pObjectModifie ne sont pas remplis.
		 */
		if (!this.champsObligatoiresRemplis(pObjectModifie)) {
			return null;
		}

		/*
		 * retourne null si l'objet modifie pObjectModifie 
		 * créerait un doublon dans le stockage.
		 */
		if (this.exists(pObjectModifie)) {
			return null;
		}

		/* récupère en BASE l'objet à modifier existant par son index. */
		final ITeleversementDeLotSectionsHit objetAModifier 
			= this.findById(pId);

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		ITeleversementDeLotSectionsHit persistentObject = null;

		try {
			
			/* RECHERCHE DES COMPOSANTS EXISTANTS 
			 * ET INJECTION DANS objetAModifier. */
			this.rechercherComposantsPersistants(objetAModifier);

			// APPLIQUE LES MODIFICATIONS.
			objetAModifier.setTeleversement(pObjectModifie.getTeleversement());
			objetAModifier.setLotSections(pObjectModifie.getLotSections());

			/* conversion de l'OBJET METIER en ENTITY. */
			final TeleversementDeLotSectionsHitEntityJPA entity 
				= TeleversementDeLotSectionsHitConvertisseurMetierEntity
					.convertirObjetMetierEnEntityJPA(objetAModifier);

			// SAUVEGARDE DES COMPOSANTS *******************************
			this.stockerComposants(entity);

			/* MODIFIE en base. */
			this.entityManager.merge(entity);

			/* applique les modifications dans le stockage (si nécessaire). */
			this.entityManager.flush();

			/* conversion de l'ENTITY en OBJET METIER. */
			persistentObject 
				= TeleversementDeLotSectionsHitConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);

		} catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException.gererException(
					CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
					, "méthode updateById(Id, Object)", e);

		}

		/* retourne l'Objet persistant modifié. */
		return persistentObject;

	} // Fin de updateById(...).___________________________________________



	/* DELETE *************/


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(
			final ITeleversementDeLotSectionsHit pObject) throws Exception {
				
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}
		
		/* retourne false si pObject == null. */
		if (pObject == null) {
			return false;
		}
		
		/* récupère l'instance persistante. */
		final ITeleversementDeLotSectionsHit persistanceInstance 
			= this.retrieve(pObject);
		
		/* retourne false si pObject n'est pas persisté. */
		if (persistanceInstance == null) {
			return false;
		}
				
		boolean resultat = false;
			
		try {

			/* récupération de l'ENTITY a détruire. */
			final TeleversementDeLotSectionsHitEntityJPA entity 
				= this.entityManager.find(
						TeleversementDeLotSectionsHitEntityJPA.class
							, persistanceInstance.getId());
						
			/* ************ */
			/* DESTRUCTION. */
			this.entityManager.remove(entity);
			
			resultat = true;
							
		} catch (Exception e) {
		
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
						, "Méthode delete(objet)", e);
									
		}
		
		/* retourne le boolean resultat. */
		return resultat;
										
	} // Fin de delete(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(
			final Long pId) throws Exception {
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}
		
		/* ne fait rien si pId == null. */
		if (pId == null) {
			return;
		}
		
		/* ne fait rien si pId est hors indexes. */
		if (this.findById(pId) == null) {
			return;
		}
						
		try {
		
			/* récupération de l'ENTITY a détruire. */
			final TeleversementDeLotSectionsHitEntityJPA entity 
				= this.entityManager.find(
						TeleversementDeLotSectionsHitEntityJPA.class
							, pId);
						
			/* ************ */
			/* DESTRUCTION. */
			this.entityManager.remove(entity);
			
		}
		catch (Exception e) {
		
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
						, "Méthode deleteById(ID)", e);
		}
		
	} // Fin de deleteById(...).___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteByIdBoolean(
			final Long pId) throws Exception {
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}
		
		/* retourne false si pId == null. */
		if (pId == null) {
			return false;
		}
		
		/* retourne false si pId est hors indexes. */
		if (this.findById(pId) == null) {
			return false;
		}
				
		try {
						
			/* récupération de l'ENTITY a détruire. */
			final TeleversementDeLotSectionsHitEntityJPA entity 
				= this.entityManager.find(
						TeleversementDeLotSectionsHitEntityJPA.class
							, pId);
			
			/* ************ */
			/* DESTRUCTION. */
			this.entityManager.remove(entity);
						
			return true;
				
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
						, "Méthode deleteByIdBoolean(ID)", e);
		}
		
		return false;
		
	} // Fin de deleteByIdBoolean(...).____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll() throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}

		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "delete from TeleversementDeLotSectionsHitEntityJPA";

		try {
			
			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString);
			
			/* EXECUTION DE LA REQUETE. */
			query.executeUpdate();
			
		}
		catch (Exception e) {
		
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
						, "Méthode deleteAll()", e);
			
		}
		
	} // Fin de deleteAll()._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteAllBoolean() throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}

		boolean resultat = false;
		
		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "delete from TeleversementDeLotSectionsHitEntityJPA";
		
		try {
			
			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString);
			
			/* EXECUTION DE LA REQUETE. */
			query.executeUpdate();
			
			resultat = true;
			
		}
		catch (Exception e) {
					
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
						, "Méthode deleteAllBoolean()", e);
			
		}
		
		/* retourne le boolean resultat. */
		return resultat;
		
	} // Fin de deleteAll()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteIterable(
			final Iterable<ITeleversementDeLotSectionsHit> pList) throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}
		
		/* ne fait rien si pList == null. */
		if (pList == null) {
			return;
		}
		
		final Iterator<ITeleversementDeLotSectionsHit> ite = pList.iterator();
		
		try {
			
			while (ite.hasNext()) {
				
				final ITeleversementDeLotSectionsHit objet = ite.next();
				final ITeleversementDeLotSectionsHit objetPersistant = this.retrieve(objet);
				
				if (objetPersistant == null) {
					continue;
				}
								
				/* récupération de l'ENTITY a détruire. */
				final TeleversementDeLotSectionsHitEntityJPA entity 
					= this.entityManager.find(
							TeleversementDeLotSectionsHitEntityJPA.class
								, objetPersistant.getId());
				
				/* ************ */
				/* DESTRUCTION. */
				this.entityManager.remove(entity);
				
			}
			
		}
		catch (Exception e) {
					
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException.gererException(
					CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
					, "Méthode deleteIterable(Iterable)", e);
			
		}
				
	} // Fin de deleteIterable(...)._______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteIterableBoolean(
			final Iterable<ITeleversementDeLotSectionsHit> pList) throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}
		
		/* retourne false si pList == null. */
		if (pList == null) {
			return false;
		}

		boolean resultat = false;
		
		final Iterator<ITeleversementDeLotSectionsHit> ite = pList.iterator();
		
		try {
			
			while (ite.hasNext()) {
				
				final ITeleversementDeLotSectionsHit objet = ite.next();
				final ITeleversementDeLotSectionsHit objetPersistant = this.retrieve(objet);
				
				if (objetPersistant == null) {
					continue;
				}
				
				/* récupération de l'ENTITY a détruire. */
				final TeleversementDeLotSectionsHitEntityJPA entity 
					= this.entityManager.find(
							TeleversementDeLotSectionsHitEntityJPA.class
								, objetPersistant.getId());
				
				/* ************ */
				/* DESTRUCTION. */
				this.entityManager.remove(entity);
				
			}
			
			resultat = true;
			
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException.gererException(
					CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
					, "Méthode deleteIterableBoolean(Iterable)", e);
			
		}
		
		/* retourne le boolean resultat. */
		return resultat;
				
	} // Fin de deleteIterableBoolean(...).________________________________



	/* TOOLS *************/


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(
			final ITeleversementDeLotSectionsHit pObject) throws Exception {
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}
		
		/* retourne false si pObject == null. */
		if (pObject == null) {
			return false;
		}

		boolean resultat = false;		
		ITeleversementDeLotSectionsHit objetResultat = null;
		
		/* RECHERCHE DES COMPOSANTS EXISTANTS ET INJECTION DANS pOBJECT. */
		this.rechercherComposantsPersistants(pObject);
		
		/* récupération de la requête paramétrée. */
		final Query requete = this.fournirRequeteEgaliteMetier(pObject);
		
		if (requete == null) {
			return false;
		}
		
		try {
						
			/* Execution de la requete HQL. */
			objetResultat 
			= (ITeleversementDeLotSectionsHit) requete.getSingleResult();
			
			/* retourne true si l'objet existe en base. */
			if (objetResultat != null) {
				resultat = true;
			}
			
		}
		catch (NoResultException noResultExc) {
			
			/* retourne false si l'Objet métier n'existe pas en base. */
			return false;
			
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING
						, "Méthode exists(objet)", e);
		}
		
		/* retourne le boolean resultat. */
		return resultat;
		
	} // Fin de exists(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsId(
			final Long pId) throws Exception {
		
		/* retourne false si pId == null . */
		if (pId == null) {
			return false;
		}
		
		/* retourne true si l'objet métier existe en base. */
		if (this.findById(pId) != null) {
			return true;
		}
		
		return false;
		
	} // Fin de existsId(...)._____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long count() throws Exception {
		
		/* Récupère la liste d'Objets métier de Type paramétré T. */
		final List<ITeleversementDeLotSectionsHit> listObjects = this.findAll();
		
		if (listObjects != null) {
			
			/* Retourne la taille de la liste. */
			return Long.valueOf(listObjects.size()) ;
		}
		
		return null;
		
	} // Fin de count().___________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ecrireStockageDansConsole() throws Exception {
		
		final List<ITeleversementDeLotSectionsHit> stockageList = this.findAll();
		
		/* ne fait rien si this.findAll() retourne null. */
		if (stockageList == null) {
			return;
		}
		
		System.out.println(this.afficherListeObjetsMetier(stockageList));
		
	} // Fin de ecrireStockageDansConsole()._______________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String afficherListeObjetsMetier(
			final List<ITeleversementDeLotSectionsHit> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		for (final ITeleversementDeLotSectionsHit objet : pList) {
			
			stb.append(objet.toString());
			stb.append(SAUT_LIGNE_PLATEFORME);
			
		}
		
		return stb.toString();
		
	} // Fin de afficherListeObjetsMetier(...).____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getMessagesErrorUtilisateurList() {
		return this.messagesErrorUtilisateurList;
	} // Fin de getMessagesErrorUtilisateurList()._________________________


	
	/**
	 * Getter de this.televersementDAO.
	 *
	 * @return this.televersementDAO : ITeleversementDAO.<br/>
	 */
	public ITeleversementDAO getTeleversementDAO() {
		return this.televersementDAO;
	} // Fin de getTeleversementDAO()._____________________________________
	

	
	/**
	* Setter de this.televersementDAO.
	*
	* @param pTeleversementDAO : ITeleversementDAO : 
	* valeur à passer à this.televersementDAO.<br/>
	*/
	@Autowired(required=true)
    @Qualifier("TeleversementDAOJPASpring")
	public void setTeleversementDAO(
			final ITeleversementDAO pTeleversementDAO) {
		this.televersementDAO = pTeleversementDAO;
	} // Fin de setTeleversementDAO(...).__________________________________


	
	/**
	 * Getter de this.sectionHitDAO.
	 *
	 * @return this.sectionHitDAO : ISectionHitDAO.<br/>
	 */
	public ISectionHitDAO getSectionHitDAO() {
		return this.sectionHitDAO;
	} // Fin de getSectionHitDAO().________________________________________


	
	/**
	* Setter de this.sectionHitDAO.
	*
	* @param pSectionHitDAO : ISectionHitDAO : 
	* valeur à passer à this.sectionHitDAO.<br/>
	*/
	@Autowired(required=true)
    @Qualifier("SectionHitDAOJPASpring")
	public void setSectionHitDAO(
			final ISectionHitDAO pSectionHitDAO) {
		this.sectionHitDAO = pSectionHitDAO;
	} // Fin de setSectionHitDAO(...)._____________________________________

	
	
} // FIN DE LA CLASSE TeleversementDeLotSectionsHitDAOJPASpring.----------------------
