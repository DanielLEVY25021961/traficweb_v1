package levy.daniel.application.model.metier.televersementdelotsections;

import java.io.Serializable;
import java.util.Map;

import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;
import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.televersement.ITeleversement;

/**
 * INTERFACE ITeleversementDeLotSectionsHit :<br/>
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
 * @since 1 août 2019
 *
 */
public interface ITeleversementDeLotSectionsHit 
			extends Comparable<ITeleversementDeLotSectionsHit>
										, Serializable, Cloneable
										, IExportateurCsv, IExportateurJTable{

	
	
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
	int compareTo(ITeleversementDeLotSectionsHit pObjet);



	/**
	 * clone.<br/>
	 *
	 * @return ITeleversementDeLotSectionsHit
	 * 
	 * @throws CloneNotSupportedException
	 */
	ITeleversementDeLotSectionsHit clone() throws CloneNotSupportedException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ITeleversementDeLotSectionsHit</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ITeleversementDeLotSectionsHit</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirStringCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ITeleversementDeLotSectionsHit</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteColonne(int pI);



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ITeleversementDeLotSectionsHit</b> :<br/>
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
	 * Getter de l'ID en base.<br/>
	 *
	 * @return this.id : Long.<br/>
	 */
	Long getId();

	
	
	/**
	* Setter de l'ID en base.<br/>
	*
	* @param pId : Long : 
	* valeur à passer à this.id.<br/>
	*/
	void setId(Long pId);
	

	
	/**
	 * Getter du Televersement.
	 *
	 * @return : this.televersement : ITeleversement.
	 */
	ITeleversement getTeleversement();
	

	
	/**
	 * Setter du Televersement.
	 * 
	 * @param pTeleversement : ITeleversement : 
	 * valeur à passer à this.televersement. 
	 */
	void setTeleversement(ITeleversement pTeleversement);


	
	/**
	 * Getter du lot de Sections HIT.
	 *
	 * @return : this.lotSections : Map&lt;Integer, ISectionHit&gt;.
	 */
	Map<Integer, ISectionHit> getLotSections();
	

	
	/**
	 * Setter du lot de Sections HIT.
	 *
	 * @param pLotSections : Map&lt;Integer, ISectionHit&gt; : 
	 * valeur à passer à this.lotSections.
	 */
	void setLotSections(Map<Integer, ISectionHit> pLotSections);
	
	
	
} // FIN DE L'INTERFACE ITeleversementDeLotSectionsHit.----------------------
