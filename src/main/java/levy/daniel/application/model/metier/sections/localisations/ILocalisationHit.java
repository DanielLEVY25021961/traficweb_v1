package levy.daniel.application.model.metier.sections.localisations;

import java.io.Serializable;

import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;

/**
 * INTERFACE ILocalisationHit :<br/>
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
 * @since 4 juil. 2019
 *
 */
public interface ILocalisationHit 
					extends Comparable<ILocalisationHit>
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
	 * {@inheritDoc}<br/>
	 * <br/>
	 * <b>comparaison en fonction de :
	 * <ol>
	 * <li>numDepartement</li>
	 * <li>numRoute</li>
	 * <li>indiceNumRoute</li>
	 * <li>indiceLettreRoute</li>
	 * <li>categorieAdminRoute</li>
	 * <li>prOrigine</li>
	 * <li>absOrigine</li>
	 * </ol>
	 * pour un ILocalisationHit</b><br/>
	 * <br/>
	 */
	@Override
	int compareTo(ILocalisationHit pObjet);



	/**
	 * clone.<br/>
	 *
	 * @return ILocalisationHit
	 * 
	 * @throws CloneNotSupportedException
	 */
	ILocalisationHit clone() throws CloneNotSupportedException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ILocalisationHit</b> :<br/>
	 * "id;
	 * numRoute;indiceNumRoute;indiceLettreRoute;categorieAdminRoute;
	 * numDepartement;
	 * lieuDitOrigine;prOrigine;absOrigine;
	 * lieuDitExtremite;prExtremite;absExtremite;
	 * lieuDitComptage;prComptage;absComptage;".<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ILocalisationHit</b> :<br/>
	 * "id;
	 * numRoute;indiceNumRoute;indiceLettreRoute;categorieAdminRoute;
	 * numDepartement;
	 * lieuDitOrigine;prOrigine;absOrigine;
	 * lieuDitExtremite;prExtremite;absExtremite;
	 * lieuDitComptage;prComptage;absComptage;".<br/>
	 * <br/>
	 */
	@Override
	String fournirStringCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ILocalisationHit</b> :<br/>
	 * "id;
	 * numRoute;indiceNumRoute;indiceLettreRoute;categorieAdminRoute;
	 * numDepartement;
	 * lieuDitOrigine;prOrigine;absOrigine;
	 * lieuDitExtremite;prExtremite;absExtremite;
	 * lieuDitComptage;prComptage;absComptage;".<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteColonne(int pI);



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ILocalisationHit</b> :<br/>
	 * "id;
	 * numRoute;indiceNumRoute;indiceLettreRoute;categorieAdminRoute;
	 * numDepartement;
	 * lieuDitOrigine;prOrigine;absOrigine;
	 * lieuDitExtremite;prExtremite;absExtremite;
	 * lieuDitComptage;prComptage;absComptage;".<br/>
	 * <br/>
	 */
	@Override
	Object fournirValeurColonne(int pI);

	
	
	/**
	 * Getter de l'ID en base (sous forme de Long).<br/>
	 *
	 * @return this.id : Long.<br/>
	 */
	Long getId();

	
	
	/**
	* Setter de l'ID en base (sous forme de Long).<br/>
	*
	* @param pId : Long : 
	* valeur à passer à this.id.<br/>
	*/
	void setId(Long pId);


	
	/**
	 * Getter du numero de la route.
	 *
	 * @return this.numRoute : String.<br/>
	 */
	String getNumRoute();


	
	/**
	* Setter du numero de la route.
	*
	* @param pNumRoute : String : 
	* valeur à passer à this.numRoute.<br/>
	*/
	void setNumRoute(String pNumRoute);


	
	/**
	 * Getter de l'indice numérique de la route.
	 *
	 * @return this.indiceNumRoute : String.<br/>
	 */
	String getIndiceNumRoute();


	
	/**
	* Setter de l'indice numérique de la route.
	*
	* @param pIndiceNumRoute : String : 
	* valeur à passer à this.indiceNumRoute.<br/>
	*/
	void setIndiceNumRoute(String pIndiceNumRoute);


	
	/**
	 * Getter de l'indice lettre de la route.
	 *
	 * @return this.indiceLettreRoute : String.<br/>
	 */
	String getIndiceLettreRoute();


	
	/**
	* Setter de l'indice lettre de la route.
	*
	* @param pIndiceLettreRoute : String : 
	* valeur à passer à this.indiceLettreRoute.<br/>
	*/
	void setIndiceLettreRoute(String pIndiceLettreRoute);


	
	/**
	 * Getter de la catégorie administrative de la route.
	 *
	 * @return this.categorieAdminRoute : String.<br/>
	 */
	String getCategorieAdminRoute();


	
	/**
	* Setter de la catégorie administrative de la route.
	*
	* @param pCategorieAdminRoute : String : 
	* valeur à passer à this.categorieAdminRoute.<br/>
	*/
	void setCategorieAdminRoute(String pCategorieAdminRoute);


	
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
	 * Getter du libellé du lieu-dit origine.
	 *
	 * @return this.lieuDitOrigine : String.<br/>
	 */
	String getLieuDitOrigine();


	
	/**
	* Setter du libellé du lieu-dit origine.
	*
	* @param pLieuDitOrigine : String : 
	* valeur à passer à this.lieuDitOrigine.<br/>
	*/
	void setLieuDitOrigine(String pLieuDitOrigine);


	
	/**
	 * Getter du PR Origine.
	 *
	 * @return this.prOrigine : Integer.<br/>
	 */
	Integer getPrOrigine();

	
	
	/**
	* Setter du PR Origine.
	*
	* @param pPrOrigine : Integer : 
	* valeur à passer à this.prOrigine.<br/>
	*/
	void setPrOrigine(Integer pPrOrigine);


	
	/**
	 * Getter de l'abscisse du point origine.
	 *
	 * @return this.absOrigine : Integer.<br/>
	 */
	Integer getAbsOrigine();

	
	
	/**
	* Setter de l'abscisse du point origine.
	*
	* @param pAbsOrigine : Integer : 
	* valeur à passer à this.absOrigine.<br/>
	*/
	void setAbsOrigine(Integer pAbsOrigine);

	
	
	/**
	 * Getter du libellé du lieu-dit extremité.
	 *
	 * @return this.lieuDitExtremite : String.<br/>
	 */
	String getLieuDitExtremite();


	
	/**
	* Setter du libellé du lieu-dit extremité.
	*
	* @param pLieuDitExtremite : String : 
	* valeur à passer à this.lieuDitExtremite.<br/>
	*/
	void setLieuDitExtremite(String pLieuDitExtremite);


	
	/**
	 * Getter du PR Extremité.
	 *
	 * @return this.prExtremite : Integer.<br/>
	 */
	Integer getPrExtremite();

	
	
	/**
	* Setter du PR Extremité.
	*
	* @param pPrExtremite : Integer : 
	* valeur à passer à this.prExtremite.<br/>
	*/
	void setPrExtremite(Integer pPrExtremite);


	
	/**
	 * Getter de l'abscisse du point extremité.
	 *
	 * @return this.absExtremite : Integer.<br/>
	 */
	Integer getAbsExtremite();

	
	
	/**
	* Setter de l'abscisse du point extremité.
	*
	* @param pAbsExtremite : Integer : 
	* valeur à passer à this.absExtremite.<br/>
	*/
	void setAbsExtremite(Integer pAbsExtremite);

	
	
	/**
	 * Getter du libellé du lieu-dit du point de comptage.
	 *
	 * @return this.lieuDitComptage : String.<br/>
	 */
	String getLieuDitComptage();


	
	/**
	* Setter du libellé du lieu-dit du point de comptage.
	*
	* @param pLieuDitComptage : String : 
	* valeur à passer à this.lieuDitComptage.<br/>
	*/
	void setLieuDitComptage(String pLieuDitComptage);


	
	/**
	 * Getter du PR du point de comptage.
	 *
	 * @return this.prComptage : Integer.<br/>
	 */
	Integer getPrComptage();

	
	
	/**
	* Setter du PR du point de comptage.
	*
	* @param pPrComptage : Integer : 
	* valeur à passer à this.prComptage.<br/>
	*/
	void setPrComptage(Integer pPrComptage);


	
	/**
	 * Getter de l'abscisse du point de comptage.
	 *
	 * @return this.absComptage : Integer.<br/>
	 */
	Integer getAbsComptage();

	
	
	/**
	* Setter de l'abscisse du point de comptage.
	*
	* @param pAbsComptage : Integer : 
	* valeur à passer à this.absComptage.<br/>
	*/
	void setAbsComptage(Integer pAbsComptage);
	
	

} // FIN DE L'INTERFACE ILocalisationHit.------------------------------------
