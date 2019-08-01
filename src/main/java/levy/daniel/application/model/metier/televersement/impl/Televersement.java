package levy.daniel.application.model.metier.televersement.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.anneegestion.IAnneeGestion;
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
	 * année de gestion concernée par le téléversement.
	 */
	private IAnneeGestion anneeGestion;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(Televersement.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public Televersement() {
		
		this(null, null, null, null, null, null, null, null);
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * 
	 * @param pDateTeleversement : LocalDateTime : 
	 * date-heure du téléversement.
	 * @param pUtilisateur : IUtilisateurCerbere : 
	 * utilisateur réalisant le téléversement.
	 * @param pGestionnaire : EnumGestionnaire : 
	 * gestionnaire (DIRA, DARWIN, ...) 
	 * pour le compte duquel l'utilisateur agit.
	 * @param pTypeFichier : EnumTypeFichierDonnees : 
	 * type du fichier (HIT, DARWIN_CSV) téléversé par l'utilisateur.
	 * @param pNomFichierTeleverse : String : 
	 * nom du fichier soumis au téléversement ("HITDIRA2018" par exemple).
	 * @param pFichierStockeServeur : File : 
	 * fichier résultant du téléversement stocké sur le serveur. 
	 * @param pAnneeGestion : IAnneeGestion : 
	 * année de gestion concernée par le téléversement.
	 */
	public Televersement(
			final LocalDateTime pDateTeleversement
			, final IUtilisateurCerbere pUtilisateur
			, final EnumGestionnaire pGestionnaire
			, final EnumTypeFichierDonnees pTypeFichier
			, final String pNomFichierTeleverse
			, final File pFichierStockeServeur
			, final IAnneeGestion pAnneeGestion) {
		
		this(null, pDateTeleversement, pUtilisateur, pGestionnaire
				, pTypeFichier, pNomFichierTeleverse, pFichierStockeServeur
				, pAnneeGestion);
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	

	
	 /**
	 * CONSTRUCTEUR COMPLET BASE.<br/>
	 * 
	 * @param pId : Long : id en base.
	 * @param pDateTeleversement : LocalDateTime : 
	 * date-heure du téléversement.
	 * @param pUtilisateur : IUtilisateurCerbere : 
	 * utilisateur réalisant le téléversement.
	 * @param pGestionnaire : EnumGestionnaire : 
	 * gestionnaire (DIRA, DARWIN, ...) 
	 * pour le compte duquel l'utilisateur agit.
	 * @param pTypeFichier : EnumTypeFichierDonnees : 
	 * type du fichier (HIT, DARWIN_CSV) téléversé par l'utilisateur.
	 * @param pNomFichierTeleverse : String : 
	 * nom du fichier soumis au téléversement ("HITDIRA2018" par exemple).
	 * @param pFichierStockeServeur : File : 
	 * fichier résultant du téléversement stocké sur le serveur. 
	 * @param pAnneeGestion : IAnneeGestion : 
	 * année de gestion concernée par le téléversement.
	 */
	public Televersement(
			final Long pId
			, final LocalDateTime pDateTeleversement
			, final IUtilisateurCerbere pUtilisateur
			, final EnumGestionnaire pGestionnaire
			, final EnumTypeFichierDonnees pTypeFichier
			, final String pNomFichierTeleverse
			, final File pFichierStockeServeur
			, final IAnneeGestion pAnneeGestion) {
		
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
	 * retourne une String de la forme 
	 * <code>[annee-mois-jour_heure_minute_seconde]</code> 
	 * à partir d'une LocalDateTime pDate.<br/>
	 * Par exemple, retourne <b>"2019-06-13_21_03_57"</b> 
	 * pour le 13 juin 2019 à 21h 03 minutes et 57 secondes.<br/>
	 * <br/>
	 * - retourne null si pDate == null.<br/>
	 * <br/>
	 *
	 * @param pDate : LocalDateTime : date à formater.
	 * 
	 * @return : String : affichage de la date formatée.<br/>
	 */
	private String formaterLocalDateTimeEnString(
			final LocalDateTime pDate) {
		
		/* retourne null si pDate == null. */
		if (pDate == null) {
			return null;
		}
		
		/* formateur de type 2019-06-13_21_03_57 
		 * (13 juin 2019 à 21h 03 minutes et 57 secondes)*/
		final DateTimeFormatter formatter 
			= DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");
	
		final String resultat = pDate.format(formatter);
				
		return resultat;
		
	} // Fin de formaterLocalDateTimeEnString(...).________________________
	
	
	
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
		
		if (!(pObjet instanceof ITeleversement)) {
			return false;
		}
		
		final ITeleversement other = (ITeleversement) pObjet;
		
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
			final ITeleversement pObjet) {
		
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
				= this.getAnneeGestion().compareTo(pObjet.getAnneeGestion());
		
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
				= this.getGestionnaire().compareTo(pObjet.getGestionnaire());
		
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
				.compareTo(pObjet.getDateTeleversement());
		
		return compareDateTeleversement;

	} // Fin de compareTo(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ITeleversement clone() 
				throws CloneNotSupportedException {
		
		final ITeleversement clone 
			= (ITeleversement) super.clone();
		
		/* CLONAGE PROFOND. */
		IUtilisateurCerbere utilisateurCerbereClone = null;
		if (this.getUtilisateur() != null) {
			utilisateurCerbereClone 
			= this.getUtilisateur().clone();
		}
		
		IAnneeGestion anneeGestionClone = null;
		if (this.getAnneeGestion() != null) {
			anneeGestionClone 
			= this.getAnneeGestion().clone();
		}
						
		clone.setId(this.getId());
		clone.setDateTeleversement(this.getDateTeleversement());
		clone.setUtilisateur(utilisateurCerbereClone);
		clone.setGestionnaire(this.getGestionnaire());
		clone.setTypeFichier(this.getTypeFichier());
		clone.setNomFichierTeleverse(this.getNomFichierTeleverse());
		clone.setFichierStockeServeur(this.getFichierStockeServeur());
		clone.setAnneeGestion(anneeGestionClone);
		
		return (Televersement) clone;
		
	} // Fin de clone().___________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {

		final StringBuilder stb = new StringBuilder();

		stb.append("Televersement [");

		stb.append("id=");
		if (this.getId() != null) {
			stb.append(this.getId());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("dateTeleversement=");
		
		if (this.getDateTeleversement() != null) {
			stb.append(this.formaterLocalDateTimeEnString(
					this.getDateTeleversement()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("utilisateur=");
		if (this.getUtilisateur() != null) {
			stb.append(this.getUtilisateur().getNom());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("gestionnaire=");
		if (this.getGestionnaire() != null) {
			stb.append(this.getGestionnaire().getNomCourt());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("typeFichier=");
		if (this.getTypeFichier() != null) {
			stb.append(this.getTypeFichier().getNomCourt());
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
			stb.append(this.getFichierStockeServeur().getName());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("anneeGestion=");
		if (this.getAnneeGestion() != null) {
			stb.append(this.getAnneeGestion().getAnneeGestion());
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
	public final String fournirEnTeteCsv() {
		return "id;dateTeleversement;utilisateur;gestionnaire;"
				+ "typeFichier;nomFichierTeleverse;fichierStockeServeur;"
				+ "anneeGestion;";
	} // Fin de fournirEnTeteCsv().________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirStringCsv() {

		final StringBuilder stb = new StringBuilder();

		stb.append(this.getId());
		stb.append(POINT_VIRGULE);
		
		final LocalDateTime dateTeleversementLocal = this.getDateTeleversement();
		if (dateTeleversementLocal != null) {
			stb.append(this.formaterLocalDateTimeEnString(dateTeleversementLocal));
		} else {
			stb.append(NULL);
		}		
		stb.append(POINT_VIRGULE);
		
		final IUtilisateurCerbere utilisateurLocal = this.getUtilisateur();
		if (utilisateurLocal != null) {
			stb.append(utilisateurLocal.getNom());
		} else {
			stb.append(NULL);
		}		
		stb.append(POINT_VIRGULE);
		
		final EnumGestionnaire gestionnaireLocal = this.getGestionnaire();
		if (gestionnaireLocal != null) {
			stb.append(gestionnaireLocal.getNomCourt());
		} else {
			stb.append(NULL);
		}		
		stb.append(POINT_VIRGULE);
		
		final EnumTypeFichierDonnees typeFichierLocal = this.getTypeFichier();
		if (typeFichierLocal != null) {
			stb.append(typeFichierLocal.getNomCourt());
		} else {
			stb.append(NULL);
		}		
		stb.append(POINT_VIRGULE);
		
		stb.append(this.getNomFichierTeleverse());
		stb.append(POINT_VIRGULE);
		
		final File fichierStockeServeurLocal = this.getFichierStockeServeur();
		if (fichierStockeServeurLocal != null) {
			stb.append(fichierStockeServeurLocal.getName());
		} else {
			stb.append(NULL);
		}		
		stb.append(POINT_VIRGULE);
		
		final IAnneeGestion anneeGestionLocal = this.getAnneeGestion();
		if (anneeGestionLocal != null) {
			stb.append(anneeGestionLocal.getAnneeGestion());
		} else {
			stb.append(NULL);
		}		
		stb.append(POINT_VIRGULE);

		return stb.toString();

	} // Fin de fournirStringCsv().________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteColonne(
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
	public final Object fournirValeurColonne(
			final int pI) {

		Object valeur = null;

		switch (pI) {

		case 0:
			final Long idLocal = this.getId();
			if (idLocal != null) {
				valeur = String.valueOf(this.getId());
			}			
			break;

		case 1:
			final LocalDateTime dateTeleversementLocal 
				= this.getDateTeleversement();
			valeur 
				= this.formaterLocalDateTimeEnString(dateTeleversementLocal);
			break;

		case 2:
			final IUtilisateurCerbere utilisateurLocal = this.getUtilisateur();
			if (utilisateurLocal != null) {
				valeur = utilisateurLocal.getNom();
			}			
			break;

		case 3:
			final EnumGestionnaire gestionnaireLocal = this.getGestionnaire();
			if (gestionnaireLocal != null) {
				valeur = gestionnaireLocal.getNomCourt();
			}			
			break;

		case 4:
			final EnumTypeFichierDonnees typeFichierLocal = this.getTypeFichier();
			if (typeFichierLocal != null) {
				valeur = typeFichierLocal.getNomCourt();
			}			
			break;

		case 5:
			valeur = this.getNomFichierTeleverse();
			break;

		case 6:
			final File fichierStockeServeurLocal = this.getFichierStockeServeur();
			if (fichierStockeServeurLocal != null) {
				valeur = fichierStockeServeurLocal.getName();
			}			
			break;

		case 7:
			final IAnneeGestion anneeGestionLocal = this.getAnneeGestion();
			if (anneeGestionLocal != null) {
				valeur = anneeGestionLocal.getAnneeGestion();
			}			
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


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IAnneeGestion getAnneeGestion() {
		return this.anneeGestion;
	} // Fin de getAnneeGestion()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setAnneeGestion(
			final IAnneeGestion pAnneeGestion) {
		this.anneeGestion = pAnneeGestion;
	} // Fin de setAnneeGestion(...).______________________________________
	
	
	
} // FIN DE LA CLASSE Televersement.-----------------------------------------
