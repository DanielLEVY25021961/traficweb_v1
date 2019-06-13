package levy.daniel.application.model.metier.televersement.impl;

import java.io.File;
import java.time.LocalDateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.televersement.EnumTypeFichierDonnees;
import levy.daniel.application.model.metier.televersement.ITeleversement;
import levy.daniel.application.model.metier.utilisateur.EnumGestionnaire;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;

/**
 * CLASSE Televersement :<br/>
 * <p>
 * modélise un <b>téléversement</b> d'un lot de données dans l'application.
 * </p>
 * 
 * <p>
 * Un téléversement conceptualise l'évènement suivant :<br/>
 * <code>le [25/02/2019 à 16h05], l'utilisateur [Zorro.Yoka] 
 * agissant pour le compte de la [DIRA] téléverse un [fichier HIT] 
 * nommé [DIRAHIT2018] à stocker sur le serveur 
 * sous [2019-02-25_16-05_HITDIRA2018_UTF-8.txt] 
 * pour l'année de gestion [2018].</code>
 * </p>
 * 
 * <p>
 * Soit :<br/>
 * <code>le [this.dateTeleversement], l'utilisateur [this.utilisateur] 
 * agissant pour le compte de [this.gestionnaire] 
 * téléverse un [this.typeFichier] 
 * nommé [this.nomFichierTeleverse] à stocker sur le serveur 
 * sous [this.fichierStockeServeur] 
 * pour l'année de gestion [2018].</code>
 * </p>
 * 
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
public class Televersement implements ITeleversement {

	// ************************ATTRIBUTS************************************/

	/**
	 * serialVersionUID.<br/>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ';'.<br/>
	 */
	public static final char POINT_VIRGULE = ';';
	
	/**
	 * ", ".<br/>
	 */
	public static final String VIRGULE_ESPACE = ", ";
	
	/**
	 * "null".<br/>
	 */
	public static final String NULL = "null";
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	/**
	 * id en base.
	 */
	private Long id;
	
	/**
	 * date-heure du téléversement.
	 */
	private LocalDateTime dateTeleversement;
	
	/**
	 * utilisateur réalisant le téléversement.
	 */
	private IUtilisateurCerbere utilisateur;
	
	/**
	 * gestionnaire (DIRA, DARWIN, ...) 
	 * pour le compte duquel l'utilisateur agit.
	 */
	private EnumGestionnaire gestionnaire;
	
	/**
	 * type du fichier (HIT, DARWIN_CSV) téléversé par l'utilisateur.
	 */
	private EnumTypeFichierDonnees typeFichier;
	
	/**
	 * nom du fichier soumis au téléversement ("HITDIRA2018" par exemple).
	 */
	private String nomFichierTeleverse;

	/**
	 * fichier résultant du téléversement stocké sur le serveur.
	 */
	private File fichierStockeServeur;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(Televersement.class);

	// *************************METHODES************************************/
	



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Long getId() {	
		return this.id;
	} // Fin de getId().___________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setId(
			final Long pId) {	
		this.id = pId;
	} // Fin de setId(...).________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final LocalDateTime getDateTeleversement() {
		return this.dateTeleversement;
	} // Fin de getDateTeleversement().____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setDateTeleversement(
			final LocalDateTime pDateTeleversement) {
		this.dateTeleversement = pDateTeleversement;
	} // Fin de setDateTeleversement(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IUtilisateurCerbere getUtilisateur() {
		return this.utilisateur;
	} // Fin de getUtilisateur().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setUtilisateur(
			final IUtilisateurCerbere pUtilisateur) {
		this.utilisateur = pUtilisateur;
	} // Fin de setUtilisateur(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final EnumGestionnaire getGestionnaire() {
		return this.gestionnaire;
	} // Fin de getGestionnaire()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setGestionnaire(
			final EnumGestionnaire pGestionnaire) {
		this.gestionnaire = pGestionnaire;
	} // Fin de setGestionnaire(...).______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final EnumTypeFichierDonnees getTypeFichier() {
		return this.typeFichier;
	} // Fin de getTypeFichier().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setTypeFichier(
			final EnumTypeFichierDonnees pTypeFichier) {
		this.typeFichier = pTypeFichier;
	} // Fin de setTypeFichier(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomFichierTeleverse() {
		return this.nomFichierTeleverse;
	} // Fin de getNomFichierTeleverse().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNomFichierTeleverse(
			final String pNomFichierTeleverse) {
		this.nomFichierTeleverse = pNomFichierTeleverse;
	} // Fin de setNomFichierTeleverse(...)._______________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getFichierStockeServeur() {
		return this.fichierStockeServeur;
	} // Fin de getFichierStockeServeur()._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setFichierStockeServeur(
			final File pFichierStockeServeur) {
		this.fichierStockeServeur = pFichierStockeServeur;
	} // Fin de setFichierStockeServeur(...).______________________________
	
	
	
} // FIN DE LA CLASSE Televersement.-----------------------------------------
