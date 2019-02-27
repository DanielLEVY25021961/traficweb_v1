package levy.daniel.application.vues.desktop.metier.utilisateur.modelobs;

import java.io.Serializable;

import javafx.beans.property.StringProperty;
import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;

/**
 * INTERFACE IUtilisateurCerbereModelObs :<br/>
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
 * @since 18 févr. 2019
 *
 */
public interface IUtilisateurCerbereModelObs 
	extends Comparable<IUtilisateurCerbereModelObs>
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
	int compareTo(IUtilisateurCerbereModelObs pObjet);



	/**
	 * clone.<br/>
	 *
	 * @return IUtilisateurCerbereModelObs
	 * 
	 * @throws CloneNotSupportedException
	 */
	IUtilisateurCerbereModelObs clone() throws CloneNotSupportedException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un UtilisateurCerbere</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un UtilisateurCerbere</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	String fournirStringCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un UtilisateurCerbere</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteColonne(int pI);



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un UtilisateurCerbere</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	Object fournirValeurColonne(int pI);


	
	/**
	 * Getter de la valeur dans la Property id.<br/>
	 *
	 * @return this.id.get() : String : 
	 * valeur dans la property this.id.<br/>
	 */
	String getId();



	/**
	 * Setter de la valeur dans la Property id.<br/>
	 *
	 * @param pId : String : 
	 * valeur à insérer dans la property this.id.<br/>
	 */
	void setId(String pId);



	/**
	 * Getter de la property ID en base.<br/>
	 *
	 * @return this.id : StringProperty.<br/>
	 */
	StringProperty getIdProperty();

	
	
	/**
	 * Getter de la valeur dans la Property civilite.<br/>
	 *
	 * @return this.civilite.get() : String : 
	 * valeur dans la property this.civilite.<br/>
	 */
	String getCivilite();

	
	
	/**
	* Setter de la valeur dans la Property civilite.<br/>
	 *
	 * @param pCivilite : String : 
	 * valeur à insérer dans la property this.civilite.<br/>
	*/
	void setCivilite(String pCivilite);



	/**
	 * Getter de la property civilite.<br/>
	 *
	 * @return this.civilite : StringProperty.<br/>
	 */
	StringProperty getCiviliteProperty();



	/**
	 * Getter de la valeur dans la Property prenom.<br/>
	 *
	 * @return this.prenom.get() : String : 
	 * valeur dans la Property this.prenom.<br/>
	 */
	String getPrenom();



	/**
	 * Setter de la valeur dans la Property prenom.<br/>
	 *
	 * @param pPrenom : String : 
	 * valeur à insérer dans la Property this.prenom.<br/>
	 */
	void setPrenom(String pPrenom);



	/**
	 * Getter de la property prenom.<br/>
	 *
	 * @return this.prenom : StringProperty.<br/>
	 */
	StringProperty getPrenomProperty();



	/**
	 * Getter de la valeur de la property nom.<br/>
	 *
	 * @return this.nom.get() : String : 
	 * valeur de la property this.nom.<br/>
	 */
	String getNom();



	/**
	 * Setter de la valeur de la property nom.<br/>
	 *
	 * @param pNom : String : 
	 * valeur à insérer dans la property this.nom.<br/>
	 */
	void setNom(String pNom);



	/**
	 * Getter de la property nom.<br/>
	 *
	 * @return this.nom : StringProperty.<br/>
	 */
	StringProperty getNomProperty();



	/**
	 * Getter de la valeur de la property tel.<br/>
	 *
	 * @return this.tel.get() : String : 
	 * valeur de la property this.tel.<br/>
	 */
	String getTel();



	/**
	 * Setter de la valeur de la property tel.<br/>
	 *
	 * @param pTel : String : 
	 * valeur à insérer dans la property this.tel.<br/>
	 */
	void setTel(String pTel);



	/**
	 * Getter de la property tel.<br/>
	 *
	 * @return this.tel : StringProperty.<br/>
	 */
	StringProperty getTelProperty();



	/**
	 * Getter de la valeur de la property email.<br/>
	 *
	 * @return this.email.get() : String : 
	 * valeur de la property this.email.<br/>
	 */
	String getEmail();



	/**
	 * Setter de la valeur de la property email.<br/>
	 *
	 * @param pEmail : String : 
	 * valeur à insérer dans la property this.email.<br/>
	 */
	void setEmail(String pEmail);



	/**
	 * Getter de la property email.<br/>
	 *
	 * @return this.email : StringProperty.<br/>
	 */
	StringProperty getEmailProperty();



	/**
	 * Getter de la valeur de la property service.<br/>
	 *
	 * @return this.service.get() : String : 
	 * valeur de la property this.service.<br/>
	 */
	String getService();



	/**
	 * Setter de la valeur de la property service.<br/>
	 *
	 * @param pService : String : 
	 * valeur à insérer dans la property this.service.<br/>
	 */
	void setService(String pService);



	/**
	 * Getter de la property service.<br/>
	 *
	 * @return this.service : StringProperty.<br/>
	 */
	StringProperty getServiceProperty();



	/**
	 * Getter de la valeur de la property unite.<br/>
	 *
	 * @return this.unite.get() : String : 
	 * valeur de la property this.unite.<br/>
	 */
	String getUnite();



	/**
	 * Setter de la valeur de la property unite.<br/>
	 *
	 * @param pUnite : String : 
	 * valeur à insérer dans la property this.unite.<br/>
	 */
	void setUnite(String pUnite);



	/**
	 * Getter de la property unite.<br/>
	 *
	 * @return this.unite : StringProperty.<br/>
	 */
	StringProperty getUniteProperty();



	/**
	 * Getter de la valeur de la property profil.<br/>
	 *
	 * @return this.profil.get() : String : 
	 * valeur de la property this.profil.<br/>
	 */
	String getProfil();



	/**
	 * Setter de la valeur de la property profil.<br/>
	 *
	 * @param pProfil : String : 
	 * valeur à insérer dans la property this.profil.<br/>
	 */
	void setProfil(String pProfil);



	/**
	 * Getter de la property profil.<br/>
	 *
	 * @return this.profil : StringProperty.<br/>
	 */
	StringProperty getProfilProperty();



	/**
	 * Getter de la valeur de la property portee.<br/>
	 *
	 * @return this.portee.get() : String : 
	 * valeur de la property this.portee.<br/>
	 */
	String getPortee();



	/**
	 * Setter de la valeur de la property portee.<br/>
	 *
	 * @param pPortee : String : 
	 * valeur à insérer dans la property this.portee.<br/>
	 */
	void setPortee(String pPortee);



	/**
	 * Getter de la property portee.<br/>
	 *
	 * @return this.portee : StringProperty.<br/>
	 */
	StringProperty getPorteeProperty();



	/**
	 * Getter de la valeur de la property restriction.<br/>
	 *
	 * @return this.restriction.get() : String : 
	 * valeur de la property this.restriction.<br/>
	 */
	String getRestriction();



	/**
	 * Setter de la valeur de la property restriction.<br/>
	 *
	 * @param pRestriction : String : 
	 * valeur à insérer dans la property this.restriction.<br/>
	 */
	void setRestriction(String pRestriction);



	/**
	 * Getter de la property restriction.<br/>
	 *
	 * @return this.restriction : StringProperty.<br/>
	 */
	StringProperty getRestrictionProperty();
	
	

} // FIN DE L'INTERFACE IUtilisateurCerbereModelObs.----------------------------