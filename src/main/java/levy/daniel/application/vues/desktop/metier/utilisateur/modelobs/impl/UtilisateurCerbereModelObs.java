package levy.daniel.application.vues.desktop.metier.utilisateur.modelobs.impl;

import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import levy.daniel.application.vues.desktop.metier.utilisateur.modelobs.IUtilisateurCerbereModelObs;

/**
 * CLASSE UtilisateurCerbereModelObs :<br/>
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
 * @since 27 févr. 2019
 *
 */
public class UtilisateurCerbereModelObs 
			implements IUtilisateurCerbereModelObs {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * serialVersionUID : long :<br/>
	 * .<br/>
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
	 * ID en base (StringProperty).<br/>
	 */
	private StringProperty id;
	
	/**
	 * civilite (StringProperty).<br/>
	 */
	private StringProperty civilite;
	
    /**
     * prénom (StringProperty).<br/>
     */
    private StringProperty prenom;
    
    /**
     * nom (StringProperty).<br/>
     */
    private StringProperty nom;
    
    /**
     * tel (StringProperty).<br/>
     */
    private StringProperty tel;
    
    /**
     * email (StringProperty).<br/>
     */
    private StringProperty email;
    
    /**
     * service (StringProperty).<br/>
     */
    private StringProperty service;
    
    /**
     * unite (StringProperty).<br/>
     */
    private StringProperty unite;
    
    /**
     * profil (StringProperty).<br/>
     */
    private StringProperty profil;
    
    /**
     * portee (StringProperty).<br/>
     */
    private StringProperty portee;
    
    /**
     * restriction (StringProperty).<br/>
     */
    private StringProperty restriction;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereModelObs.class);
	
	// *************************METHODES************************************/

	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereModelObs() {
		
		this((StringProperty) null
				, null, null, null
				, null, null
				, null, null
				, null, null, null);
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 *
	 * @param pCivilite : StringProperty : civilité.
	 * @param pPrenom : StringProperty : prénom.
	 * @param pNom : StringProperty : nom.
	 * @param pTel : StringProperty : téléphone.
	 * @param pEmail : StringProperty : email.
	 * @param pService : StringProperty : service.
	 * @param pUnite : StringProperty : unité.
	 * @param pProfil : StringProperty : profil.
	 * @param pPortee : StringProperty : portée.
	 * @param pRestriction : StringProperty : restriction. 
	 */
	public UtilisateurCerbereModelObs(
			final StringProperty pCivilite
				, final StringProperty pPrenom
					, final StringProperty pNom
			, final StringProperty pTel, final StringProperty pEmail
			, final StringProperty pService, final StringProperty pUnite
			, final StringProperty pProfil
			, final StringProperty pPortee
			, final StringProperty pRestriction) {
		
		this(null
				, pCivilite, pPrenom, pNom
				, pTel, pEmail
				, pService, pUnite
				, pProfil, pPortee, pRestriction);
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________

	
	
	 /**
	 * CONSTRUCTEUR COMPLET BASE.<br/>
	 *
	 * @param pId : StringProperty : ID en base.
	 * @param pCivilite : StringProperty : civilité.
	 * @param pPrenom : StringProperty : prénom.
	 * @param pNom : StringProperty : nom.
	 * @param pTel : StringProperty : téléphone.
	 * @param pEmail : StringProperty : email.
	 * @param pService : StringProperty : service.
	 * @param pUnite : StringProperty : unité.
	 * @param pProfil : StringProperty : profil.
	 * @param pPortee : StringProperty : portée.
	 * @param pRestriction : StringProperty : restriction. 
	 */
	public UtilisateurCerbereModelObs(
			final StringProperty pId
				, final StringProperty pCivilite
					, final StringProperty pPrenom
						, final StringProperty pNom
			, final StringProperty pTel, final StringProperty pEmail
			, final StringProperty pService, final StringProperty pUnite
			, final StringProperty pProfil
			, final StringProperty pPortee
			, final StringProperty pRestriction) {
		
		super();
		
		this.id = pId;
		this.civilite = pCivilite;
		this.prenom = pPrenom;
		this.nom = pNom;
		this.tel = pTel;
		this.email = pEmail;
		this.service = pService;
		this.unite = pUnite;
		this.profil = pProfil;
		this.portee = pPortee;
		this.restriction = pRestriction;
		
	} // Fin de CONSTRUCTEUR COMPLET BASE._________________________________

	
	
	 /**
	 * CONSTRUCTEUR MALIN.<br/>
	 * <ul>
	 * <li>instancie un OBSERVABLE 
	 * en insérant directement des valeurs 
	 * dans ses propriétés.</li>
	 * </ul>
	 *
	 * @param pId : String : valeur à insérer dans id.
	 * @param pCivilite : String : valeur à insérer dans civilite. 
	 * @param pPrenom : String : valeur à insérer dans prenom.
	 * @param pNom : String : valeur à insérer dans nom. 
	 * @param pTel : String : valeur à insérer dans tel. 
	 * @param pEmail : String : valeur à insérer dans email.
	 * @param pService : String : valeur à insérer dans service. 
	 * @param pUnite : String : valeur à insérer dans unite.
	 * @param pProfil : String : valeur à insérer dans profil. 
	 * @param pPortee : String : valeur à insérer dans portee.
	 * @param pRestriction : String : valeur à insérer dans restriction. 
	 */
	public UtilisateurCerbereModelObs(
			final String pId
				, final String pCivilite
					, final String pPrenom
						, final String pNom
			, final String pTel, final String pEmail
			, final String pService, final String pUnite
			, final String pProfil
			, final String pPortee
			, final String pRestriction) {
		
		super();
		
		this.id = new SimpleStringProperty(pId);
		this.civilite = new SimpleStringProperty(pCivilite);
		this.prenom = new SimpleStringProperty(pPrenom);
		this.nom = new SimpleStringProperty(pNom);
		this.tel = new SimpleStringProperty(pTel);
		this.email = new SimpleStringProperty(pEmail);
		this.service = new SimpleStringProperty(pService);
		this.unite = new SimpleStringProperty(pUnite);
		this.profil = new SimpleStringProperty(pProfil);
		this.portee = new SimpleStringProperty(pPortee);
		this.restriction = new SimpleStringProperty(pRestriction);
		
	} // Fin de CONSTRUCTEUR MALIN.________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		
		return Objects.hash(this.nom.get(), this.prenom.get()
				, this.email.get(), this.unite.get());
		
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
		
		if (!(pObjet instanceof IUtilisateurCerbereModelObs)) {
			return false;
		}
		
		final IUtilisateurCerbereModelObs other 
			= (IUtilisateurCerbereModelObs) pObjet;
		
		return Objects.equals(this.getNom(), other.getNom()) 					 
				&& Objects.equals(this.getPrenom(), other.getPrenom()) 
				&& Objects.equals(this.getEmail(), other.getEmail())
				&& Objects.equals(this.getUnite(), other.getUnite());
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(
			final IUtilisateurCerbereModelObs pObject) {
		
		if (this == pObject) {
			return 0;
		}

		if (pObject == null) {
			return -1;
		}

		int compareNom = 0;
		int comparePrenom = 0;
		int compareEmail = 0;
		int compareUnite = 0;
		
		/* Nom. */
		if (this.getNomProperty() == null) {
			if (pObject.getNomProperty() != null) {
				return +1;
			}
		} else if (pObject.getNomProperty() == null) {
			return -1;
		}
		
		if (this.getNom() == null) {
			if (pObject.getNom() != null) {
				return +1;
			}
		} else if (pObject.getNom() == null) {
			return -1;
		}
		
		if (this.getNom() != null && pObject.getNom() != null) {
			
			compareNom 
				= this.getNom()
					.compareToIgnoreCase(pObject.getNom());
			
			if (compareNom != 0) {
				return compareNom;
			}
			
		}
		
		
		/* prenom. */
		if (this.getPrenomProperty() == null) {
			if (pObject.getPrenomProperty() != null) {
				return +1;
			}
		} else if (pObject.getPrenomProperty() == null) {
			return -1;
		}
		
		if (this.getPrenom() == null) {
			if (pObject.getPrenom() != null) {
				return +1;
			}
		} else if (pObject.getPrenom() == null) {
			return -1;
		}
		
		if (this.getPrenom() != null && pObject.getPrenom() != null) {
			
			comparePrenom 
				= this.getPrenom()
				.compareToIgnoreCase(pObject.getPrenom());
			
			if (comparePrenom != 0) {
				return comparePrenom;
			}
		}
		
		/* Email. */
		if (this.getEmailProperty() == null) {
			if (pObject.getEmailProperty() != null) {
				return +1;
			}
		} else if (pObject.getEmailProperty() == null) {
			return -1;
		}
		
		if (this.getEmail() == null) {
			if (pObject.getEmail() != null) {
				return +1;
			}
		} else if (pObject.getEmail() == null) {
			return -1;
		}
		
		if (this.getEmail() != null && pObject.getEmail() != null) {
			
			compareEmail 
				= this.getEmail()
					.compareToIgnoreCase(pObject.getEmail());
			
			if (compareEmail != 0) {
				return compareEmail;
			}
			
		}
		
		
		/* unite. */
		if (this.getUniteProperty() == null) {
			if (pObject.getUniteProperty() != null) {
				return +1;
			}
			
			return 0;
			
		} else if (pObject.getUniteProperty() == null) {
			return -1;
		}
		
		if (this.getUnite() == null) {
			if (pObject.getUnite() != null) {
				return +1;
			}
			
			return 0;
			
		} else if (pObject.getUnite() == null) {
			return -1;
		}
		
		compareUnite 
			= this.getUnite().compareTo(pObject.getUnite());

		return compareUnite;

	} // Fin de compareTo(...).____________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final UtilisateurCerbereModelObs clone() 
				throws CloneNotSupportedException {
		
		final UtilisateurCerbereModelObs clone 
			= (UtilisateurCerbereModelObs) super.clone();
		
		clone.setId(this.getId());
		clone.setCivilite(this.getCivilite());
		clone.setPrenom(this.getPrenom());
		clone.setNom(this.getNom());
		clone.setTel(this.getTel());
		clone.setEmail(this.getEmail());
		clone.setService(this.getService());
		clone.setUnite(this.getUnite());
		clone.setProfil(this.getProfil());
		clone.setPortee(this.getPortee());
		clone.setRestriction(this.getRestriction());
		
		return clone;
		
	} // Fin de clone().___________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("UtilisateurCerbereModelObs [");
		
		builder.append("id=");
		if (this.getId() != null) {			
			builder.append(this.getId());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("civilite=");
		if (this.getCivilite() != null) {			
			builder.append(this.getCivilite());			
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("prenom=");
		if (this.getPrenom() != null) {			
			builder.append(this.getPrenom());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("nom=");
		if (this.getNom() != null) {			
			builder.append(this.getNom());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("tel=");
		if (this.getTel() != null) {			
			builder.append(this.getTel());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("email=");
		if (this.getEmail() != null) {			
			builder.append(this.getEmail());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("service=");
		if (this.getService() != null) {			
			builder.append(this.getService());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("unite=");
		if (this.getUnite() != null) {			
			builder.append(this.getUnite());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("profil=");
		if (this.getProfil() != null) {			
			builder.append(this.getProfil());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("portee=");
		if (this.getPortee() != null) {			
			builder.append(this.getPortee());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("restriction=");
		if (this.getRestriction() != null) {			
			builder.append(this.getRestriction());
		} else {
			builder.append(NULL);
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteCsv() {
		return "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;";
	} // Fin de fournirEnTeteCsv().________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirStringCsv() {
		
		final StringBuilder stb = new StringBuilder();

		stb.append(this.getId());
		stb.append(POINT_VIRGULE);
		stb.append(this.getCivilite());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPrenom());
		stb.append(POINT_VIRGULE);
		stb.append(this.getNom());
		stb.append(POINT_VIRGULE);
		stb.append(this.getTel());
		stb.append(POINT_VIRGULE);
		stb.append(this.getEmail());
		stb.append(POINT_VIRGULE);
		stb.append(this.getService());
		stb.append(POINT_VIRGULE);
		stb.append(this.getUnite());
		stb.append(POINT_VIRGULE);
		stb.append(this.getProfil());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPortee());
		stb.append(POINT_VIRGULE);
		stb.append(this.getRestriction());
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
			entete = "civilité";
			break;

		case 2:
			entete = "prénom";
			break;

		case 3:
			entete = "nom";
			break;
			
		case 4:
			entete = "tel";
			break;
			
		case 5:
			entete = "email";
			break;
			
		case 6:
			entete = "service";
			break;
			
		case 7:
			entete = "unite";
			break;
			
		case 8:
			entete = "profil";
			break;
			
		case 9:
			entete = "portée";
			break;
			
		case 10:
			entete = "restriction";
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
			valeur = this.getId();
			break;

		case 1:
			valeur = this.getCivilite();
			break;

		case 2:
			valeur = this.getPrenom();
			break;

		case 3:
			valeur = this.getNom();
			break;

		case 4:
			valeur = this.getTel();
			break;
			
		case 5:
			valeur = this.getEmail();
			break;
			
		case 6:
			valeur = this.getService();
			break;
			
		case 7:
			valeur = this.getUnite();
			break;
			
		case 8:
			valeur = this.getProfil();
			break;
			
		case 9:
			valeur = this.getPortee();
			break;
			
		case 10:
			valeur = this.getRestriction();		
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
		if (this.id != null) {
			return this.id.get();
		}
		return null;
	} // Fin de getId().___________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setId(
			final String pId) {
		if (this.id != null) {
			this.id.set(pId);
		}		
	} // Fin de setId(...).________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final StringProperty getIdProperty() {
		return this.id;
	} // Fini de getIdProperty().__________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getCivilite() {
		if (this.civilite != null) {
			return this.civilite.get();
		}
		return null;
	} // Fin de getCivilite()._____________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setCivilite(
			final String pCivilite) {
		if (this.civilite != null) {
			this.civilite.set(pCivilite);
		}	
	} // Fin de setCivilite(...).__________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final StringProperty getCiviliteProperty() {
		return this.civilite;
	} // Fin de getCiviliteProperty()._____________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPrenom() {
		if (this.prenom != null) {
			return this.prenom.get();
		}
		return null;
	} // Fin de getPrenom()._______________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPrenom(
			final String pPrenom) {
		if (this.prenom != null) {
			this.prenom.set(pPrenom);
		}		
	} // Fin de setPrenom(...).____________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final StringProperty getPrenomProperty() {
		return this.prenom;
	} // Fin de getPrenomProperty()._______________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNom() {
		if (this.nom != null) {
			return this.nom.get();
		}
		return null;
	} // Fin de getNom().__________________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNom(
			final String pNom) {
		if (this.nom != null) {
			this.nom.set(pNom);
		}		
	} // Fin de setNom(...)._______________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final StringProperty getNomProperty() {
		return this.nom;
	} // Fin de getNomProperty().__________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getTel() {
		if (this.tel != null) {
			return this.tel.get();
		}
		return null;
	} // Fin de getTel().__________________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setTel(
			final String pTel) {
		if (this.tel != null) {
			this.tel.set(pTel);
		}		
	} // Fin de setTel(...)._______________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final StringProperty getTelProperty() {
		return this.tel;
	} // Fin de getTelProperty().__________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getEmail() {
		if (this.email != null) {
			return this.email.get();
		}
		return null;
	} // Fin de getEmail().________________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setEmail(
			final String pEmail) {
		if (this.email != null) {
			this.email.set(pEmail);
		}		
	} // Fin de setEmail(...)._____________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final StringProperty getEmailProperty() {
		return this.email;
	} // Fin de getEmailProperty().________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getService() {
		if (this.service != null) {
			return this.service.get();
		}
		return null;
	} // Fin de getService().______________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setService(
			final String pService) {
		if (this.service != null) {
			this.service.set(pService);
		}		
	} // Fin de setService(...).___________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final StringProperty getServiceProperty() {
		return this.service;
	} // Fin de getServiceProperty().______________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getUnite() {
		if (this.unite != null) {
			return this.unite.get();
		}
		return null;
	} // Fin de getUnite().________________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setUnite(
			final String pUnite) {
		if (this.unite != null) {
			this.unite.set(pUnite);
		}		
	} // Fin de setUnite(...)._____________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final StringProperty getUniteProperty() {
		return this.unite;
	} // Fin de getUniteProperty().________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getProfil() {
		if (this.profil != null) {
			return this.profil.get();
		}
		return null;
	} // Fin de getProfil()._______________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setProfil(
			final String pProfil) {
		if (this.profil != null) {
			this.profil.set(pProfil);
		}		
	} // Fin de setProfil(...).____________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final StringProperty getProfilProperty() {
		return this.profil;
	} // Fin de getProfilProperty()._______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPortee() {
		if (this.portee != null) {
			return this.portee.get();
		}
		return null;
	} // Fin de getPortee()._______________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPortee(
			final String pPortee) {
		if (this.portee != null) {
			this.portee.set(pPortee);
		}		
	} // Fin de setPortee(...).____________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final StringProperty getPorteeProperty() {
		return this.portee;
	} // Fin de getPorteeProperty()._______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getRestriction() {
		if (this.restriction != null) {
			return this.restriction.get();
		}
		return null;
	} // Fin de getRestriction().__________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setRestriction(
			final String pRestriction) {
		if (this.restriction != null) {
			this.restriction.set(pRestriction);
		}		
	} // Fin de setRestriction(...)._______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final StringProperty getRestrictionProperty() {
		return this.restriction;
	} // Fin de getRestrictionProperty().__________________________________

	

} // FIN DE LA CLASSE UtilisateurCerbereModelObs.----------------------------
