package levy.daniel.application.model.dto.metier.anneegestion;

import java.io.Serializable;

import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;

/**
 * INTERFACE IAnneeGestionDTO :<br/>
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
 * @since 19 juin 2019
 *
 */
public interface IAnneeGestionDTO 
				extends Comparable<IAnneeGestionDTO>
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
	int compareTo(IAnneeGestionDTO pObjet);



	/**
	 * clone.<br/>
	 *
	 * @return IAnneeGestionDTO
	 * 
	 * @throws CloneNotSupportedException
	 */
	IAnneeGestionDTO clone() throws CloneNotSupportedException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un Televersement</b> :<br/>
	 * "id;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un Televersement</b> :<br/>
	 * "id;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirStringCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un Televersement</b> :<br/>
	 * "id;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteColonne(int pI);



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un Televersement</b> :<br/>
	 * "id;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	Object fournirValeurColonne(int pI);

	
	
	/**
	 * Getter de l'ID en base (sous forme de String).<br/>
	 *
	 * @return this.id : String.<br/>
	 */
	String getId();

	
	
	/**
	* Setter de l'ID en base (sous forme de String).<br/>
	*
	* @param pId : String : 
	* valeur à passer à this.id.<br/>
	*/
	void setId(String pId);


	
	/**
	 * Getter de l'année de gestion sur 4 chiffres ("2018" par exemple).
	 *
	 * @return this.anneeGestion : String.<br/>
	 */
	String getAnneeGestion();


	
	/**
	* Setter de l'année de gestion sur 4 chiffres ("2018" par exemple).
	*
	* @param pAnneeGestion : String : 
	* valeur à passer à this.anneeGestion.<br/>
	*/
	void setAnneeGestion(String pAnneeGestion);
	
	
	
} // FIN DE L'INTERFACE IAnneeGestionDTO.------------------------------------
