package levy.daniel.application.model.metier.televersement.impl;

import java.time.LocalDateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.televersement.ITeleversement;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.metier.utilisateur.impl.EnumGestionnaire;

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
 * sous [2019-02-25_16-05_HITDIRA2019_UTF-8.txt] 
 * pour l'année de gestion [2018].</code>
 * </p>
 * 
 * <p>
 * Soit :<br/>
 * <code>le [this.dateTeleversement], l'utilisateur [this.utilisateur] 
 * agissant pour le compte de la [this.gestionnaire] 
 * téléverse un [fichier HIT] 
 * nommé [2019_DIRAHIT] à stocker sur le serveur 
 * sous [2019-02-25_16-05_HITDIRA2019_UTF-8.txt] 
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
	
	
	
} // FIN DE LA CLASSE Televersement.-----------------------------------------
