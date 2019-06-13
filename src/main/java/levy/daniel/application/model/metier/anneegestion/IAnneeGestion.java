package levy.daniel.application.model.metier.anneegestion;

import java.io.Serializable;
import java.time.LocalDate;

import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;

/**
 * INTERFACE IAnneeGestion :<br/>
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
 * @since 13 juin 2019
 *
 */
public interface IAnneeGestion extends Comparable<IAnneeGestion>
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
	int compareTo(IAnneeGestion pObjet);



	/**
	 * clone.<br/>
	 *
	 * @return IAnneeGestion
	 * 
	 * @throws CloneNotSupportedException
	 */
	IAnneeGestion clone() throws CloneNotSupportedException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un IAnneeGestion</b> :<br/>
	 * "id;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un IAnneeGestion</b> :<br/>
	 * "id;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirStringCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un IAnneeGestion</b> :<br/>
	 * "id;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteColonne(int pI);



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un IAnneeGestion</b> :<br/>
	 * "id;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	Object fournirValeurColonne(int pI);

	
	
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


	
	/**
	 * Getter de l'année de gestion sous forme de LocalDate 
	 * fixée au 1er janvier de l'année.
	 *
	 * @return this.anneeGestionDate : LocalDate.<br/>
	 */
	LocalDate getAnneeGestionDate();
	
	

} // FIN DE LA INTERFACE IAnneeGestion.--------------------------------------
