package levy.daniel.application.model.dto.metier.sections;

import java.io.Serializable;

import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;

/**
 * INTERFACE ISectionHitDTO :<br/>
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
 * @since 7 juin 2019
 *
 */
public interface ISectionHitDTO extends Comparable<ISectionHitDTO>
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
	int compareTo(ISectionHitDTO pObjet);



	/**
	 * clone.<br/>
	 *
	 * @return ISectionHitDTO
	 * 
	 * @throws CloneNotSupportedException
	 */
	ISectionHitDTO clone() throws CloneNotSupportedException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ISectionHitDTO</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ISectionHitDTO</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	String fournirStringCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ISectionHitDTO</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteColonne(int pI);



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ISectionHitDTO</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
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
	 * Getter du numéro de département.<br/>
	 *
	 * @return this.numDepartement : String.<br/>
	 */
	String getNumDepartement();
	

	
	/**
	* Setter du numéro de département.<br/>
	*
	* @param pNumDepartement : String : 
	* valeur à passer à this.numDepartement.<br/>
	*/
	void setNumDepartement(String pNumDepartement);


	
	/**
	 * Getter du numéro de section.<br/>
	 *
	 * @return this.numSection : String.<br/>
	 */
	String getNumSection();


	
	/**
	* Setter du numéro de section.<br/>
	*
	* @param pNumSection : String : 
	* valeur à passer à this.numSection.<br/>
	*/
	void setNumSection(String pNumSection);


	
	/**
	 * Getter du sens de trafic.<br/>
	 *
	 * @return this.sens : String.<br/>
	 */
	String getSens();


	
	/**
	* Setter du sens de trafic.<br/>
	*
	* @param pSens : String : 
	* valeur à passer à this.sens.<br/>
	*/
	void setSens(String pSens);


	
	/**
	 * Getter de la nature du comptage.<br/>
	 *
	 * @return this.nature : String.<br/>
	 */
	String getNature();


	
	/**
	* Setter de la nature du comptage.<br/>
	*
	* @param pNature : String : 
	* valeur à passer à this.nature.<br/>
	*/
	void setNature(String pNature);


	
	/**
	 * Getter de la classe du trafic.<br/>
	 *
	 * @return this.classe : String.<br/>
	 */
	String getClasse();


	
	/**
	* Setter de la classe du trafic.<br/>
	*
	* @param pClasse : String : 
	* valeur à passer à this.classe.<br/>
	*/
	void setClasse(String pClasse);


	
	/**
	 * Getter de l'année de traitement.<br/>
	 *
	 * @return this.anneeTraitement : String.<br/>
	 */
	String getAnneeTraitement();


	
	/**
	* Setter de l'année de traitement.<br/>
	*
	* @param pAnneeTraitement : String : 
	* valeur à passer à this.anneeTraitement.<br/>
	*/
	void setAnneeTraitement(String pAnneeTraitement);


	
	/**
	 * Getter de la zone libre 1.<br/>
	 *
	 * @return this.zoneLibre1 : String.<br/>
	 */
	String getZoneLibre1();

	
	
	/**
	* Setter de la zone libre 1.<br/>
	*
	* @param pZoneLibre1 : String : 
	* valeur à passer à this.zoneLibre1.<br/>
	*/
	void setZoneLibre1(String pZoneLibre1);

	

} // FIN DE L'INTERFACE ISectionHitDTO.--------------------------------------
