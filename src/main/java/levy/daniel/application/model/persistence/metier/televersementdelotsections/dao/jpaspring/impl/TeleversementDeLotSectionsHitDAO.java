package levy.daniel.application.model.persistence.metier.televersementdelotsections.dao.jpaspring.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import levy.daniel.application.model.metier.televersementdelotsections.ITeleversementDeLotSectionsHit;
import levy.daniel.application.model.persistence.daoexceptions.GestionnaireDaoException;
import levy.daniel.application.model.persistence.metier.televersement.ITeleversementDAO;
import levy.daniel.application.model.persistence.metier.televersementdelotsections.ITeleversementDeLotSectionsHitDAO;

/**
 * CLASSE TeleversementDeLotSectionsHitDAO :<br/>
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
@Repository(value="TeleversementDeLotSectionsHitDAO")
public class TeleversementDeLotSectionsHitDAO implements ITeleversementDeLotSectionsHitDAO {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe TeleversementDeLotSectionsHitDAO".<br/>
	 */
	public static final String CLASSE_TELEVERSEMENTDELOTSECTIONSHITDAO_JPA_SPRING 
		= "Classe TeleversementDeLotSectionsHitDAO";
	
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
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(TeleversementDeLotSectionsHitDAO.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public TeleversementDeLotSectionsHitDAO() {
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
	 * {@inheritDoc}
	 */
	@Override
	public ITeleversementDeLotSectionsHit create(ITeleversementDeLotSectionsHit pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ITeleversementDeLotSectionsHit createOrRetrieve(ITeleversementDeLotSectionsHit pEntity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void persist(ITeleversementDeLotSectionsHit pObject) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long createReturnId(ITeleversementDeLotSectionsHit pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<ITeleversementDeLotSectionsHit> saveIterable(Iterable<ITeleversementDeLotSectionsHit> pList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ITeleversementDeLotSectionsHit retrieve(ITeleversementDeLotSectionsHit pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ITeleversementDeLotSectionsHit findById(Long pId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long retrieveId(ITeleversementDeLotSectionsHit pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ITeleversementDeLotSectionsHit> rechercherRapide(String pString) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ITeleversementDeLotSectionsHit> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ITeleversementDeLotSectionsHit> findAllMax(int pStartPosition, int pMaxResult) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<ITeleversementDeLotSectionsHit> findAllIterable(Iterable<Long> pIds) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ITeleversementDeLotSectionsHit update(ITeleversementDeLotSectionsHit pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ITeleversementDeLotSectionsHit updateById(Long pId, ITeleversementDeLotSectionsHit pObjectModifie)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(ITeleversementDeLotSectionsHit pObject) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(Long pId) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteByIdBoolean(Long pId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteAllBoolean() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteIterable(Iterable<ITeleversementDeLotSectionsHit> pList) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteIterableBoolean(Iterable<ITeleversementDeLotSectionsHit> pList) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(ITeleversementDeLotSectionsHit pObject) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsId(Long pId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long count() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ecrireStockageDansConsole() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String afficherListeObjetsMetier(List<ITeleversementDeLotSectionsHit> pList) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getMessagesErrorUtilisateurList() {
		// TODO Auto-generated method stub
		return null;
	}


	
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
	public void setTeleversementDAO(
			final ITeleversementDAO pTeleversementDAO) {
		this.televersementDAO = pTeleversementDAO;
	} // Fin de setTeleversementDAO(...).__________________________________

	
	
} // FIN DE LA CLASSE TeleversementDeLotSectionsHitDAO.----------------------
