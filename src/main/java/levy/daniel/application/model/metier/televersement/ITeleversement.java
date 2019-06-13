package levy.daniel.application.model.metier.televersement;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;

import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;
import levy.daniel.application.model.metier.anneegestion.IAnneeGestion;
import levy.daniel.application.model.metier.utilisateur.EnumGestionnaire;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;

/**
 * INTERFACE ITeleversement :<br/>
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
 * @since 13 juin 2019
 *
 */
public interface ITeleversement extends Comparable<ITeleversement>
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
	int compareTo(ITeleversement pObjet);



	/**
	 * clone.<br/>
	 *
	 * @return ITeleversement
	 * 
	 * @throws CloneNotSupportedException
	 */
	ITeleversement clone() throws CloneNotSupportedException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ITeleversement</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ITeleversement</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirStringCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ITeleversement</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteColonne(int pI);



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ITeleversement</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
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
	 * Getter de la date-heure du téléversement.
	 *
	 * @return this.dateTeleversement : LocalDateTime.<br/>
	 */
	LocalDateTime getDateTeleversement();


	
	/**
	* Setter de la date-heure du téléversement.
	*
	* @param pDateTeleversement : LocalDateTime : 
	* valeur à passer à this.dateTeleversement.<br/>
	*/
	void setDateTeleversement(LocalDateTime pDateTeleversement);


	
	/**
	 * Getter de l'utilisateur réalisant le téléversement.
	 *
	 * @return this.utilisateur : IUtilisateurCerbere.<br/>
	 */
	IUtilisateurCerbere getUtilisateur();


	
	/**
	* Setter de l'utilisateur réalisant le téléversement.
	*
	* @param pUtilisateur : IUtilisateurCerbere : 
	* valeur à passer à this.utilisateur.<br/>
	*/
	void setUtilisateur(IUtilisateurCerbere pUtilisateur);


	
	/**
	 * Getter du gestionnaire (DIRA, DARWIN, ...) 
	 * pour le compte duquel l'utilisateur agit.
	 *
	 * @return this.gestionnaire : EnumGestionnaire.<br/>
	 */
	EnumGestionnaire getGestionnaire();


	
	/**
	* Setter du gestionnaire (DIRA, DARWIN, ...) 
	* pour le compte duquel l'utilisateur agit.
	*
	* @param pGestionnaire : EnumGestionnaire : 
	* valeur à passer à this.gestionnaire.<br/>
	*/
	void setGestionnaire(EnumGestionnaire pGestionnaire);


	
	/**
	 * Getter du type du fichier (HIT, DARWIN_CSV) téléversé par l'utilisateur.
	 *
	 * @return this.typeFichier : EnumTypeFichierDonnees.<br/>
	 */
	EnumTypeFichierDonnees getTypeFichier();


	
	/**
	* Setter du type du fichier (HIT, DARWIN_CSV) téléversé par l'utilisateur.
	*
	* @param pTypeFichier : EnumTypeFichierDonnees : 
	* valeur à passer à this.typeFichier.<br/>
	*/
	void setTypeFichier(EnumTypeFichierDonnees pTypeFichier);


	
	/**
	 * Getter du nom du fichier soumis au téléversement 
	 * ("HITDIRA2018" par exemple).
	 *
	 * @return this.nomFichierTeleverse : String.<br/>
	 */
	String getNomFichierTeleverse();


	
	/**
	* Setter du nom du fichier soumis au téléversement 
	* ("HITDIRA2018" par exemple).
	*
	* @param pNomFichierTeleverse : String : 
	* valeur à passer à this.nomFichierTeleverse.<br/>
	*/
	void setNomFichierTeleverse(String pNomFichierTeleverse);



	/**
	 * Getter du fichier résultant du téléversement stocké sur le serveur.
	 *
	 * @return this.fichierStockeServeur : File.<br/>
	 */
	File getFichierStockeServeur();


	
	/**
	* Setter du fichier résultant du téléversement stocké sur le serveur.
	*
	* @param pFichierStockeServeur : File : 
	* valeur à passer à this.fichierStockeServeur.<br/>
	*/
	void setFichierStockeServeur(File pFichierStockeServeur);


	
	/**
	 * Getter de l'année de gestion concernée par le téléversement.
	 *
	 * @return this.anneeGestion : IAnneeGestion.<br/>
	 */
	IAnneeGestion getAnneeGestion();


	
	/**
	* Setter de l'année de gestion concernée par le téléversement.
	*
	* @param pAnneeGestion : IAnneeGestion : 
	* valeur à passer à this.anneeGestion.<br/>
	*/
	void setAnneeGestion(IAnneeGestion pAnneeGestion);

	

} // FIN DE L'INTERFACE ITeleversement.--------------------------------------
