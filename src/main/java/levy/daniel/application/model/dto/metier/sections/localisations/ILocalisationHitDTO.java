package levy.daniel.application.model.dto.metier.sections.localisations;

import java.io.Serializable;

import levy.daniel.application.model.dto.metier.sections.localisations.impl.LocalisationHitDTO;
import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;

/**
 * INTERFACE ILocalisationHitDTO :<br/>
 * Interface factorisant les comportements des {@link LocalisationHitDTO}.<br/>
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
public interface ILocalisationHitDTO 
					extends Comparable<ILocalisationHitDTO>
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
	 * pour un ILocalisationHitDTO</b><br/>
	 * <br/>
	 */
	@Override
	int compareTo(ILocalisationHitDTO pObjet);



	/**
	 * clone.<br/>
	 *
	 * @return ILocalisationHitDTO
	 * 
	 * @throws CloneNotSupportedException
	 */
	ILocalisationHitDTO clone() throws CloneNotSupportedException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ILocalisationHitDTO</b> :<br/>
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
	 * <b>enTete CSV pour un ILocalisationHitDTO</b> :<br/>
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
	 * <b>enTete CSV pour un ILocalisationHitDTO</b> :<br/>
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
	 * <b>enTete CSV pour un ILocalisationHitDTO</b> :<br/>
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
	 * Getter du PR Origine (sous forme de String).
	 *
	 * @return this.prOrigine : String.<br/>
	 */
	String getPrOrigine();

	
	
	/**
	* Setter du PR Origine (sousforme de String).
	*
	* @param pPrOrigine : String : 
	* valeur à passer à this.prOrigine.<br/>
	*/
	void setPrOrigine(String pPrOrigine);


	
	/**
	 * Getter de l'abscisse du point origine (sous forme de String).
	 *
	 * @return this.absOrigine : String.<br/>
	 */
	String getAbsOrigine();

	
	
	/**
	* Setter de l'abscisse du point origine (sous forme de String).
	*
	* @param pAbsOrigine : String : 
	* valeur à passer à this.absOrigine.<br/>
	*/
	void setAbsOrigine(String pAbsOrigine);

	
	
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
	 * Getter du PR Extremité (sous forme de String).
	 *
	 * @return this.prExtremite : String.<br/>
	 */
	String getPrExtremite();

	
	
	/**
	* Setter du PR Extremité (sous forme de String).
	*
	* @param pPrExtremite : String : 
	* valeur à passer à this.prExtremite.<br/>
	*/
	void setPrExtremite(String pPrExtremite);


	
	/**
	 * Getter de l'abscisse du point extremité (sous forme de String).
	 *
	 * @return this.absExtremite : String.<br/>
	 */
	String getAbsExtremite();

	
	
	/**
	* Setter de l'abscisse du point extremité (sous forme de String).
	*
	* @param pAbsExtremite : String : 
	* valeur à passer à this.absExtremite.<br/>
	*/
	void setAbsExtremite(String pAbsExtremite);

	
	
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
	 * Getter du PR du point de comptage (sous forme de String).
	 *
	 * @return this.prComptage : String.<br/>
	 */
	String getPrComptage();

	
	
	/**
	* Setter du PR du point de comptage (sous forme de String).
	*
	* @param pPrComptage : String : 
	* valeur à passer à this.prComptage.<br/>
	*/
	void setPrComptage(String pPrComptage);


	
	/**
	 * Getter de l'abscisse du point de comptage (sous forme de String).
	 *
	 * @return this.absComptage : String.<br/>
	 */
	String getAbsComptage();

	
	
	/**
	* Setter de l'abscisse du point de comptage (sous forme de String).
	*
	* @param pAbsComptage : String : 
	* valeur à passer à this.absComptage.<br/>
	*/
	void setAbsComptage(String pAbsComptage);
	
	

} // FIN DE L'INTERFACE ILocalisationHitDTO.---------------------------------
