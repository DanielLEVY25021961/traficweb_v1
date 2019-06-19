package levy.daniel.application.model.dto.metier.televersement.impl;

import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.dto.metier.televersement.ITeleversementDTO;

/**
 * CLASSE TeleversementDTO :<br/>
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
public class TeleversementDTO implements ITeleversementDTO {

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
	private String id;
	
	/**
	 * date-heure du téléversement.
	 */
	private String dateTeleversement;
	
	/**
	 * utilisateur réalisant le téléversement.
	 */
	private String utilisateur;
	
	/**
	 * gestionnaire (DIRA, DARWIN, ...) 
	 * pour le compte duquel l'utilisateur agit.
	 */
	private String gestionnaire;
	
	/**
	 * type du fichier (HIT, DARWIN_CSV) téléversé par l'utilisateur.
	 */
	private String typeFichier;
	
	/**
	 * nom du fichier soumis au téléversement ("HITDIRA2018" par exemple).
	 */
	private String nomFichierTeleverse;

	/**
	 * fichier résultant du téléversement stocké sur le serveur.
	 */
	private String fichierStockeServeur;
	
	/**
	 * année de gestion concernée par le téléversement.
	 */
	private String anneeGestion;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(TeleversementDTO.class);

	// *************************METHODES************************************/
	

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public TeleversementDTO() {
		this(null, null, null, null, null, null, null, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.
	 *
	 * @param pDateTeleversement : String : 
	 * date-heure du téléversement.
	 * @param pUtilisateur : String : 
	 * utilisateur réalisant le téléversement.
	 * @param pGestionnaire : String : 
	 * gestionnaire (DIRA, DARWIN, ...) 
	 * pour le compte duquel l'utilisateur agit.
	 * @param pTypeFichier : String : 
	 * type du fichier (HIT, DARWIN_CSV) téléversé par l'utilisateur.
	 * @param pNomFichierTeleverse : String : 
	 * nom du fichier soumis au téléversement ("HITDIRA2018" par exemple).
	 * @param pFichierStockeServeur : String : 
	 * fichier résultant du téléversement stocké sur le serveur.
	 * @param pAnneeGestion : String : 
	 * année de gestion concernée par le téléversement.
	 */
	public TeleversementDTO(
				final String pDateTeleversement
				, final String pUtilisateur
				, final String pGestionnaire
				, final String pTypeFichier
				, final String pNomFichierTeleverse
				, final String pFichierStockeServeur
				, final String pAnneeGestion) {
		
		this(null
				, pDateTeleversement
				, pUtilisateur
				, pGestionnaire
				, pTypeFichier
				, pNomFichierTeleverse
				, pFichierStockeServeur
				, pAnneeGestion);
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	

	
	 /**
	 * CONSTRUCTEUR COMPLET BASE.
	 *
	 * @param pId : String : 
	 * id en base.
	 * @param pDateTeleversement : String : 
	 * date-heure du téléversement.
	 * @param pUtilisateur : String : 
	 * utilisateur réalisant le téléversement.
	 * @param pGestionnaire : String : 
	 * gestionnaire (DIRA, DARWIN, ...) 
	 * pour le compte duquel l'utilisateur agit.
	 * @param pTypeFichier : String : 
	 * type du fichier (HIT, DARWIN_CSV) téléversé par l'utilisateur.
	 * @param pNomFichierTeleverse : String : 
	 * nom du fichier soumis au téléversement ("HITDIRA2018" par exemple).
	 * @param pFichierStockeServeur : String : 
	 * fichier résultant du téléversement stocké sur le serveur.
	 * @param pAnneeGestion : String : 
	 * année de gestion concernée par le téléversement.
	 */
	public TeleversementDTO(
			final String pId
				, final String pDateTeleversement
				, final String pUtilisateur
				, final String pGestionnaire
				, final String pTypeFichier
				, final String pNomFichierTeleverse
				, final String pFichierStockeServeur
				, final String pAnneeGestion) {
		
		super();
		
		this.id = pId;
		this.dateTeleversement = pDateTeleversement;
		this.utilisateur = pUtilisateur;
		this.gestionnaire = pGestionnaire;
		this.typeFichier = pTypeFichier;
		this.nomFichierTeleverse = pNomFichierTeleverse;
		this.fichierStockeServeur = pFichierStockeServeur;
		this.anneeGestion = pAnneeGestion;
		
	} // Fin de CONSTRUCTEUR COMPLET BASE._________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		
		return Objects.hash(
				this.getDateTeleversement()
					, this.getUtilisateur()
						, this.getNomFichierTeleverse());
		
	} // Fin de hashCode().________________________________________________
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(
			final Object pObjet) {
		
		if (this == pObjet) {
			return true;
		}
		
		if (pObjet == null) {
			return false;
		}
		
		if (!(pObjet instanceof ITeleversementDTO)) {
			return false;
		}
		
		final ITeleversementDTO other = (ITeleversementDTO) pObjet;
				
		return Objects
				.equals(this.getDateTeleversement(), other.getDateTeleversement())
				&& Objects.equals(this.getUtilisateur(), other.getUtilisateur())
				&& Objects.equals(this.getNomFichierTeleverse(), other.getNomFichierTeleverse());
		
	} // Fin de equals(...)._______________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(
			final ITeleversementDTO pObjet) {
		
		if (this == pObjet) {
			return 0;
		}

		if (pObjet == null) {
			return -1;
		}

		int compareAnneeGestion = 0;
		int compareGestionnaire = 0;
		int compareDateTeleversement = 0;
		
		/* anneeGestion. */
		if (this.getAnneeGestion() == null) {
			if (pObjet.getAnneeGestion() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getAnneeGestion() == null) {
				return -1;
			}
			
			compareAnneeGestion 
				= this.getAnneeGestion()
					.compareToIgnoreCase(pObjet.getAnneeGestion());
		
			if (compareAnneeGestion != 0) {
				return compareAnneeGestion;
			}
		}

		/* gestionnaire. */
		if (this.getGestionnaire() == null) {
			if (pObjet.getGestionnaire() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getGestionnaire() == null) {
				return -1;
			}
			
			compareGestionnaire 
				= this.getGestionnaire()
					.compareToIgnoreCase(pObjet.getGestionnaire());
		
			if (compareGestionnaire != 0) {
				return compareGestionnaire;
			}
		}
		
		/* dateTeleversement. */
		if (this.getDateTeleversement() == null) {
			if (pObjet.getDateTeleversement() != null) {
				return +1;
			}
			
			return 0;
		}
		
		if (pObjet.getDateTeleversement() == null) {
			return -1;
		}
		
		compareDateTeleversement 
			= this.getDateTeleversement()
				.compareToIgnoreCase(pObjet.getDateTeleversement());
		
		return compareDateTeleversement;

	} // Fin de compareTo(...).____________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ITeleversementDTO clone() throws CloneNotSupportedException {
		
		final ITeleversementDTO clone 
			= (ITeleversementDTO) super.clone();
				
		clone.setId(this.getId());
		clone.setDateTeleversement(this.getDateTeleversement());
		clone.setUtilisateur(this.getUtilisateur());
		clone.setGestionnaire(this.getGestionnaire());
		clone.setTypeFichier(this.getTypeFichier());
		clone.setNomFichierTeleverse(this.getNomFichierTeleverse());
		clone.setFichierStockeServeur(this.getFichierStockeServeur());
		clone.setAnneeGestion(this.getAnneeGestion());
		
		return (TeleversementDTO) clone;
		
	} // Fin de clone().___________________________________________________

	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {

		final StringBuilder stb = new StringBuilder();

		stb.append("TeleversementDTO [");

		stb.append("id=");
		if (this.getId() != null) {
			stb.append(this.getId());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("dateTeleversement=");
		if (this.getDateTeleversement() != null) {
			stb.append(this.getDateTeleversement());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("utilisateur=");
		if (this.getUtilisateur() != null) {
			stb.append(this.getUtilisateur());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("gestionnaire=");
		if (this.getGestionnaire() != null) {
			stb.append(this.getGestionnaire());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("typeFichier=");
		if (this.getTypeFichier() != null) {
			stb.append(this.getTypeFichier());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("nomFichierTeleverse=");
		if (this.getNomFichierTeleverse() != null) {
			stb.append(this.getNomFichierTeleverse());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("fichierStockeServeur=");
		if (this.getFichierStockeServeur() != null) {
			stb.append(this.getFichierStockeServeur());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("anneeGestion=");
		if (this.getAnneeGestion() != null) {
			stb.append(this.getAnneeGestion());
		} else {
			stb.append(NULL);
		}

		stb.append(']');

		return stb.toString();
		
	} // Fin de toString().________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String fournirEnTeteCsv() {
		return "id;dateTeleversement;utilisateur;gestionnaire;"
				+ "typeFichier;nomFichierTeleverse;fichierStockeServeur;"
				+ "anneeGestion;";
	} // Fin de fournirEnTeteCsv().________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String fournirStringCsv() {

		final StringBuilder stb = new StringBuilder();

		stb.append(this.getId());
		stb.append(POINT_VIRGULE);
		stb.append(this.getDateTeleversement());
		stb.append(POINT_VIRGULE);
		stb.append(this.getUtilisateur());
		stb.append(POINT_VIRGULE);
		stb.append(this.getGestionnaire());
		stb.append(POINT_VIRGULE);
		stb.append(this.getTypeFichier());
		stb.append(POINT_VIRGULE);
		stb.append(this.getNomFichierTeleverse());
		stb.append(POINT_VIRGULE);
		stb.append(this.getFichierStockeServeur());
		stb.append(POINT_VIRGULE);
		stb.append(this.getAnneeGestion());
		stb.append(POINT_VIRGULE);

		return stb.toString();

	} // Fin de fournirStringCsv().________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String fournirEnTeteColonne(
			final int pI) {

		String entete = null;

		switch (pI) {

		case 0:
			entete = "id";
			break;

		case 1:
			entete = "dateTeleversement";
			break;

		case 2:
			entete = "utilisateur";
			break;

		case 3:
			entete = "gestionnaire";
			break;

		case 4:
			entete = "typeFichier";
			break;

		case 5:
			entete = "nomFichierTeleverse";
			break;

		case 6:
			entete = "fichierStockeServeur";
			break;

		case 7:
			entete = "anneeGestion";
			break;

		default:
			entete = "invalide";
			break;

		} // Fin du Switch._________________________________

		return entete;

	} // Fin de fournirEnTeteColonne(...)._________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object fournirValeurColonne(
			final int pI) {

		Object valeur = null;

		switch (pI) {

		case 0:
			valeur = this.getId();
			break;

		case 1:
			valeur = this.getDateTeleversement();
			break;

		case 2:
			valeur = this.getUtilisateur();
			break;

		case 3:
			valeur = this.getGestionnaire();
			break;

		case 4:
			valeur = this.getTypeFichier();
			break;

		case 5:
			valeur = this.getNomFichierTeleverse();
			break;

		case 6:
			valeur = this.getFichierStockeServeur();
			break;

		case 7:
			valeur = this.getAnneeGestion();
			break;

		default:
			valeur = "invalide";
			break;

		} // Fin du Switch._________________________________

		return valeur;

	} // Fin de fournirValeurColonne(...)._________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getId() {	
		return this.id;
	} // Fin de getId().___________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setId(
			final String pId) {	
		this.id = pId;
	} // Fin de setId(...).________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getDateTeleversement() {
		return this.dateTeleversement;
	} // Fin de getDateTeleversement().____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setDateTeleversement(
			final String pDateTeleversement) {
		this.dateTeleversement = pDateTeleversement;
	} // Fin de setDateTeleversement(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getUtilisateur() {
		return this.utilisateur;
	} // Fin de getUtilisateur().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setUtilisateur(
			final String pUtilisateur) {
		this.utilisateur = pUtilisateur;
	} // Fin de setUtilisateur(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getGestionnaire() {
		return this.gestionnaire;
	} // Fin de getGestionnaire()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setGestionnaire(
			final String pGestionnaire) {
		this.gestionnaire = pGestionnaire;
	} // Fin de setGestionnaire(...).______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getTypeFichier() {
		return this.typeFichier;
	} // Fin de getTypeFichier().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setTypeFichier(
			final String pTypeFichier) {
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
	public final String getFichierStockeServeur() {
		return this.fichierStockeServeur;
	} // Fin de getFichierStockeServeur()._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setFichierStockeServeur(
			final String pFichierStockeServeur) {
		this.fichierStockeServeur = pFichierStockeServeur;
	} // Fin de setFichierStockeServeur(...).______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getAnneeGestion() {
		return this.anneeGestion;
	} // Fin de getAnneeGestion()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setAnneeGestion(
			final String pAnneeGestion) {
		this.anneeGestion = pAnneeGestion;
	} // Fin de setAnneeGestion(...).______________________________________


	
} // FIN DE LA CLASSE TeleversementDTO.--------------------------------------
