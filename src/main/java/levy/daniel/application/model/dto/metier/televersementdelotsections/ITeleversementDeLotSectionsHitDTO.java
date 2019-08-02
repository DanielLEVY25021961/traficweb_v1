package levy.daniel.application.model.dto.metier.televersementdelotsections;

import java.io.Serializable;
import java.util.Map;

import levy.daniel.application.model.dto.metier.sections.ISectionHitDTO;
import levy.daniel.application.model.dto.metier.televersement.ITeleversementDTO;
import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;

/**
 * INTERFACE ITeleversementDeLotSectionsHitDTO :<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 2 août 2019
 *
 */
public interface ITeleversementDeLotSectionsHitDTO 
							extends Comparable<ITeleversementDeLotSectionsHitDTO>
								, Serializable, Cloneable
									, IExportateurCsv, IExportateurJTable {

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	int hashCode();

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	boolean equals(Object pObjet);

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	int compareTo(ITeleversementDeLotSectionsHitDTO pObjet);



	/**
	 * clone.<br/>
	 *
	 * @return ITeleversementDeLotSectionsHitDTO
	 * 
	 * @throws CloneNotSupportedException
	 */
	ITeleversementDeLotSectionsHitDTO clone() throws CloneNotSupportedException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ITeleversementDeLotSectionsHitDTO</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ITeleversementDeLotSectionsHitDTO</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirStringCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ITeleversementDeLotSectionsHitDTO</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteColonne(int pI);



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ITeleversementDeLotSectionsHitDTO</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	Object fournirValeurColonne(int pI);
	
	
	
	/**
	 * fournit une String pour l'affichage de la 
	 * Map&lt;Integer,ISectionHit&gt; <code><b>this.lotSections</b></code><br/>
	 * <br/>
	 * - retourne null si this.lotSections == null.<br/>
	 * <br/>
	 * 
	 * @return : String : pour affichage.<br/>
	 */
	String afficherLotSections();
	
	
	
	/**
	 * Getter de l'ID en base (sous forme de String).<br/>
	 *
	 * @return this.idString : String.<br/>
	 */
	String getIdString();

	
	
	/**
	* Setter de l'ID en base (sous forme de String).<br/>
	*
	* @param pIdString : String : 
	* valeur à passer à this.idString.<br/>
	*/
	void setIdString(String pIdString);
	

	
	/**
	 * Getter du TeleversementDTO.
	 *
	 * @return : this.televersementDTO : ITeleversementDTO.
	 */
	ITeleversementDTO getTeleversementDTO();
	

	
	/**
	 * Setter du TeleversementDTO.
	 * 
	 * @param pTeleversementDTO : ITeleversementDTO : 
	 * valeur à passer à this.televersementDTO. 
	 */
	void setTeleversementDTO(ITeleversementDTO pTeleversementDTO);


	
	/**
	 * Getter du lot de DTO des Sections HIT.
	 *
	 * @return : this.lotSectionsDTO : Map&lt;Integer, ISectionHitDTO&gt;.
	 */
	Map<Integer, ISectionHitDTO> getLotSectionsDTO();
	

	
	/**
	 * Setter du lot de DTO des Sections HIT.
	 *
	 * @param pLotSectionsDTO : Map&lt;Integer, ISectionHitDTO&gt; : 
	 * valeur à passer à this.lotSectionsDTO.
	 */
	void setLotSectionsDTO(Map<Integer, ISectionHitDTO> pLotSectionsDTO);
	
	
	
} // FIN DE L'INTERFACE ITeleversementDeLotSectionsHitDTO.----------------------
