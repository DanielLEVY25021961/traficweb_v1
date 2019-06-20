package levy.daniel.application.model.dto.metier.televersement;

import java.io.Serializable;

import levy.daniel.application.model.dto.metier.anneegestion.IAnneeGestionDTO;
import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;

/**
 * INTERFACE ITeleversementDTO :<br/>
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
 * @since 19 juin 2019
 *
 */
public interface ITeleversementDTO 
					extends Comparable<ITeleversementDTO>
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
	int compareTo(ITeleversementDTO pObjet);



	/**
	 * clone.<br/>
	 *
	 * @return ITeleversementDTO
	 * 
	 * @throws CloneNotSupportedException
	 */
	ITeleversementDTO clone() throws CloneNotSupportedException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un Televersement</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un Televersement</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirStringCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un Televersement</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteColonne(int pI);



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un Televersement</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
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
	 * Getter de la date-heure du téléversement
	 * au format LocalDateTime ISO-8601 calendar system 
	 * comme '2019-06-20T10:26:17' 
	 * pour 20 juin 2019 à 10h 26 minutes et 17secondes.
	 *
	 * @return this.dateTeleversement : String.<br/>
	 */
	String getDateTeleversement();


	
	/**
	* Setter de la date-heure du téléversement
	* au format LocalDateTime ISO-8601 calendar system 
	* comme '2019-06-20T10:26:17' 
	* pour 20 juin 2019 à 10h 26 minutes et 17secondes.
	*
	* @param pDateTeleversement : String : 
	* valeur à passer à this.dateTeleversement.<br/>
	*/
	void setDateTeleversement(String pDateTeleversement);


	
	/**
	 * Getter de l'utilisateur réalisant le téléversement.
	 *
	 * @return this.utilisateur : String.<br/>
	 */
	IUtilisateurCerbereDTO getUtilisateur();


	
	/**
	* Setter de l'utilisateur réalisant le téléversement.
	*
	* @param pUtilisateur : String : 
	* valeur à passer à this.utilisateur.<br/>
	*/
	void setUtilisateur(IUtilisateurCerbereDTO pUtilisateur);


	
	/**
	 * Getter du gestionnaire (DIRA, DARWIN, ...) 
	 * pour le compte duquel l'utilisateur agit.
	 *
	 * @return this.gestionnaire : String.<br/>
	 */
	String getGestionnaire();


	
	/**
	* Setter du gestionnaire (DIRA, DARWIN, ...) 
	* pour le compte duquel l'utilisateur agit.
	*
	* @param pGestionnaire : String : 
	* valeur à passer à this.gestionnaire.<br/>
	*/
	void setGestionnaire(String pGestionnaire);


	
	/**
	 * Getter du type du fichier (HIT, DARWIN_CSV) téléversé par l'utilisateur.
	 *
	 * @return this.typeFichier : String.<br/>
	 */
	String getTypeFichier();


	
	/**
	* Setter du type du fichier (HIT, DARWIN_CSV) téléversé par l'utilisateur.
	*
	* @param pTypeFichier : String : 
	* valeur à passer à this.typeFichier.<br/>
	*/
	void setTypeFichier(String pTypeFichier);


	
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
	 * @return this.fichierStockeServeur : String.<br/>
	 */
	String getFichierStockeServeur();


	
	/**
	* Setter du fichier résultant du téléversement stocké sur le serveur.
	*
	* @param pFichierStockeServeur : String : 
	* valeur à passer à this.fichierStockeServeur.<br/>
	*/
	void setFichierStockeServeur(String pFichierStockeServeur);


	
	/**
	 * Getter de l'année de gestion concernée par le téléversement.
	 *
	 * @return this.anneeGestion : IAnneeGestionDTO.<br/>
	 */
	IAnneeGestionDTO getAnneeGestion();


	
	/**
	* Setter de l'année de gestion concernée par le téléversement.
	*
	* @param pAnneeGestionDTO : IAnneeGestionDTO : 
	* valeur à passer à this.anneeGestion.<br/>
	*/
	void setAnneeGestion(IAnneeGestionDTO pAnneeGestionDTO);

	

} // FIN DE L'INTERFACE ITeleversementDTO.-----------------------------------
