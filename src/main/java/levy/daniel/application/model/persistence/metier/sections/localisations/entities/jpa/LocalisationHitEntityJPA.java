package levy.daniel.application.model.persistence.metier.sections.localisations.entities.jpa;

import java.util.Objects;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.sections.localisations.ILocalisationHit;

/**
 * CLASSE LocalisationHitEntityJPA :<br/>
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
@Entity
@Table(name="LOCALISATIONHITS", schema="PUBLIC"
, uniqueConstraints=@UniqueConstraint(
		name="UNICITE_NUMROUTE_INDICENUMROUTE_INDICELETTREROUTE_CATEGORIEADMINROUTE_NUMDEPARTEMENT_PRORIGINE_ABSORIGINE_PREXTREMITE_ABSEXTREMITE"
, columnNames={"NUMROUTE", "INDICENUMROUTE", "INDICELETTREROUTE", "CATEGORIEADMINROUTE", "NUMDEPARTEMENT", "PRORIGINE", "ABSORIGINE", "PREXTREMITE", "ABSEXTREMITE"})
, indexes={@Index(name="INDEX_NUMROUTE_CATEGORIEADMINROUTE_NUMDEPARTEMENT_PRORIGINE"
, columnList="NUMROUTE, CATEGORIEADMINROUTE, NUMDEPARTEMENT, PRORIGINE")})
public class LocalisationHitEntityJPA implements ILocalisationHit {

	// ************************ATTRIBUTS************************************/

	/**
	 * serialVersionUID.<br/>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * "Classe LocalisationHitEntityJPA".
	 */
	public static final String CLASSE_LOCALISATION_HIT 
		= "Classe LocalisationHitEntityJPA";
	
	/**
	 * ';'.<br/>
	 */
	public static final char POINT_VIRGULE = ';';
	
	/**
	 * ", ".<br/>
	 */
	public static final String VIRGULE_ESPACE = ", ";
	
	/**
	 * " - ".
	 */
	public static final String MOINS_ESPACE = " - ";
	
	/**
	 * "null".<br/>
	 */
	public static final String NULL = "null";
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
		
	/**
	 * id en base sous forme de Long.<br/>
	 */
	private Long id;
	
	/**
	 * numero de la route.
	 */
	private String numRoute;
	
	/**
	 * indice numérique de la route.
	 */
	private String indiceNumRoute;
	
	/**
	 * indice lettre de la route.
	 */
	private String indiceLettreRoute;
	
	/**
	 * catégorie administrative de la route.
	 */
	private String categorieAdminRoute;
	
	/**
	 * numéro de département.
	 */
	private String numDepartement;
	
	/**
	 * libellé du lieu-dit origine.
	 */
	private String lieuDitOrigine;
	
	/**
	 * PR Origine.
	 */
	private Integer prOrigine;
	
	/**
	 * abscisse du point origine.
	 */
	private Integer absOrigine;
	
	/**
	 * libellé du lieu-dit extremité.
	 */
	private String lieuDitExtremite;
	
	/**
	 * PR Extremité.
	 */
	private Integer prExtremite;
	
	/**
	 * abscisse du point extremité.
	 */
	private Integer absExtremite;
	
	/**
	 * libellé du lieu-dit du point de comptage.
	 */
	private String lieuDitComptage;
	
	/**
	 * PR du point de comptage.
	 */
	private Integer prComptage;
	
	/**
	 * abscisse du point de comptage.
	 */
	private Integer absComptage;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(LocalisationHitEntityJPA.class);

	// *************************METHODES************************************/
	

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public LocalisationHitEntityJPA() {
		
		this(null
				, null, null, null, null, null
				, null, null, null
				, null, null, null
				, null, null, null);
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * 
	 * @param pNumRoute : String : numero de la route.
	 * @param pIndiceNumRoute : String : indice numérique de la route.
	 * @param pIndiceLettreRoute : String : indice lettre de la route.
	 * @param pCategorieAdminRoute : String : 
	 * catégorie administrative de la route.
	 * @param pNumDepartement : String : numéro de département.
	 * @param pLieuDitOrigine : String : libellé du lieu-dit origine.
	 * @param pPrOrigine : Integer : PR Origine.
	 * @param pAbsOrigine : Integer : abscisse du point origine.
	 * @param pLieuDitExtremite : String : libellé du lieu-dit extremité.
	 * @param pPrExtremite : Integer : PR Extremité.
	 * @param pAbsExtremite : Integer : abscisse du point extremité.
	 * @param pLieuDitComptage : String : 
	 * libellé du lieu-dit du point de comptage.
	 * @param pPrComptage : Integer : PR du point de comptage.
	 * @param pAbsComptage : Integer : abscisse du point de comptage.
	 */
	public LocalisationHitEntityJPA(
			final String pNumRoute
			, final String pIndiceNumRoute
			, final String pIndiceLettreRoute
			, final String pCategorieAdminRoute
			, final String pNumDepartement
			, final String pLieuDitOrigine, final Integer pPrOrigine
			, final Integer pAbsOrigine
			, final String pLieuDitExtremite, final Integer pPrExtremite
			, final Integer pAbsExtremite
			, final String pLieuDitComptage, final Integer pPrComptage
			, final Integer pAbsComptage) {
		
		this(null
				, pNumRoute
				, pIndiceNumRoute, pIndiceLettreRoute, pCategorieAdminRoute
				, pNumDepartement
				, pLieuDitOrigine, pPrOrigine, pAbsOrigine
				, pLieuDitExtremite, pPrExtremite, pAbsExtremite
				, pLieuDitComptage, pPrComptage, pAbsComptage);
		
	} // Fin du CONSTRUCTEUR COMPLET.______________________________________
	

	
	 /**
	 * CONSTRUCTEUR COMPLET BASE.<br/>
	 * 
	 * @param pId : Long :ID en base
	 * @param pNumRoute : String : numero de la route.
	 * @param pIndiceNumRoute : String : indice numérique de la route.
	 * @param pIndiceLettreRoute : String : indice lettre de la route.
	 * @param pCategorieAdminRoute : String : 
	 * catégorie administrative de la route.
	 * @param pNumDepartement : String : numéro de département.
	 * @param pLieuDitOrigine : String : libellé du lieu-dit origine.
	 * @param pPrOrigine : Integer : PR Origine.
	 * @param pAbsOrigine : Integer : abscisse du point origine.
	 * @param pLieuDitExtremite : String : libellé du lieu-dit extremité.
	 * @param pPrExtremite : Integer : PR Extremité.
	 * @param pAbsExtremite : Integer : abscisse du point extremité.
	 * @param pLieuDitComptage : String : 
	 * libellé du lieu-dit du point de comptage.
	 * @param pPrComptage : Integer : PR du point de comptage.
	 * @param pAbsComptage : Integer : abscisse du point de comptage.
	 */
	public LocalisationHitEntityJPA(
			final Long pId
			, final String pNumRoute
			, final String pIndiceNumRoute
			, final String pIndiceLettreRoute
			, final String pCategorieAdminRoute
			, final String pNumDepartement
			, final String pLieuDitOrigine, final Integer pPrOrigine
			, final Integer pAbsOrigine
			, final String pLieuDitExtremite, final Integer pPrExtremite
			, final Integer pAbsExtremite
			, final String pLieuDitComptage, final Integer pPrComptage
			, final Integer pAbsComptage) {
		
		super();
		
		this.id = pId;
		this.numRoute = pNumRoute;
		this.indiceNumRoute = pIndiceNumRoute;
		this.indiceLettreRoute = pIndiceLettreRoute;
		this.categorieAdminRoute = pCategorieAdminRoute;
		this.numDepartement = pNumDepartement;
		this.lieuDitOrigine = pLieuDitOrigine;
		this.prOrigine = pPrOrigine;
		this.absOrigine = pAbsOrigine;
		this.lieuDitExtremite = pLieuDitExtremite;
		this.prExtremite = pPrExtremite;
		this.absExtremite = pAbsExtremite;
		this.lieuDitComptage = pLieuDitComptage;
		this.prComptage = pPrComptage;
		this.absComptage = pAbsComptage;
		
	} // Fin du CONSTRUCTEUR COMPLET BASE._________________________________

	
	
	 /**
	 * CONSTRUCTEUR CONVERTISSEUR.<br/>
	 * Instancie un OBJET METIER à partir d'une SortedMap&lt;Integer, String&gt; 
	 * description de ligne d'un fichier HIT.<br/>
	 * <br/>
	 * - LOG.fatal et jette une RunTimeException 
	 * si pDescriptionLigne == null.<br/>
	 * </br/>
	 * 
	 * 
	 * @param pDescriptionLigne : SortedMap&lt;Integer, String&gt;
	 * 
	 * @throws Exception 
	 */
	public LocalisationHitEntityJPA(final SortedMap<Integer, String> pDescriptionLigne) 
			throws Exception {
		
		super();
		
		/* LOG.fatal et jette une RunTimeException 
		 * si pDescriptionLigne == null. */
		if (pDescriptionLigne == null) {
			
			final String message 
				= "Impossible d'instancier un LocalisationHitEntityJPA à partir "
						+ "d'une SortedMap<Integer, String> "
						+ "pDescriptionLigne null";
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			throw new RuntimeException(message);
		}
		
		this.setId(null);
		this.setNumRoute(pDescriptionLigne.get(8));
		this.setIndiceNumRoute(pDescriptionLigne.get(9));
		this.setIndiceLettreRoute(pDescriptionLigne.get(10));
		this.setCategorieAdminRoute(pDescriptionLigne.get(11));
		this.setNumDepartement(pDescriptionLigne.get(1));
		this.setLieuDitOrigine(pDescriptionLigne.get(18));
		this.setPrOrigine(this.fournirInteger(pDescriptionLigne.get(19)));
		this.setAbsOrigine(this.fournirInteger(pDescriptionLigne.get(20)));
		this.setLieuDitExtremite(pDescriptionLigne.get(21));
		this.setPrExtremite(this.fournirInteger(pDescriptionLigne.get(22)));
		this.setAbsExtremite(this.fournirInteger(pDescriptionLigne.get(23)));
		this.setLieuDitComptage(pDescriptionLigne.get(24));
		this.setPrComptage(this.fournirInteger(pDescriptionLigne.get(25)));
		this.setAbsComptage(this.fournirInteger(pDescriptionLigne.get(26)));

	} // Fin de CONSTRUCTEUR CONVERTISSEUR.________________________________

	
	
	/**
	 * retourne un Integer à partir de la chaine fournie dans pString.<br/>
	 * <br/>
	 * - retire d'éventuels espaces avant et/ou après la valeur entière.<br/>
	 * - retourne null si pString est blank.<br/>
	 * - LOG.fatal et retourne null si pString n'est pas homogène 
	 * à un entier.<br/>
	 * <br/>
	 *
	 * @param pString : String : String homogène à un Integer.
	 * 
	 * @return : Integer.<br/>
	 */
	private Integer fournirInteger(final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		Integer resultat = null;
		
		final String motifInteger = "\\s*(\\d+)\\s*";
		final Pattern patternInteger 
			= Pattern.compile(motifInteger); 
		final Matcher matcher = patternInteger.matcher(pString);
		
		/* retourne null si pString n'est pas homogène 
		 * à un entier. */
		if (!matcher.matches()) {
			
			final String message 
				= CLASSE_LOCALISATION_HIT
				+ MOINS_ESPACE
				+ "méthode fournirInteger(String)"
				+ MOINS_ESPACE 
				+ "pString passé en paramètre n'est pas homogène à un entier : " 
				+ pString;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			return null;
		}

		/* retire d'éventuels espaces avant et/ou après la valeur entière. */
		final String valeur = matcher.group(1);
		
		resultat = Integer.valueOf(valeur);
		
		return resultat;
		
	} // Fin de fournirInteger(...)._______________________________________

	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		
		return Objects.hash(
				this.getNumRoute(), this.getIndiceNumRoute()
				, this.getIndiceLettreRoute(), this.getCategorieAdminRoute()
				, this.getNumDepartement()
				, this.getPrOrigine(), this.getAbsOrigine()
				, this.getPrExtremite(), this.getAbsExtremite());

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
		
		if (!(pObjet instanceof ILocalisationHit)) {
			return false;
		}
		
		final ILocalisationHit other = (ILocalisationHit) pObjet;

		return Objects
			.equals(this.getNumRoute(), other.getNumRoute())
			&& Objects.equals(this.getIndiceNumRoute(), other.getIndiceNumRoute())
			&& Objects.equals(this.getIndiceLettreRoute(), other.getIndiceLettreRoute())
			&& Objects.equals(this.getCategorieAdminRoute(), other.getCategorieAdminRoute())
			&& Objects.equals(this.getNumDepartement(), other.getNumDepartement())
			&& Objects.equals(this.getPrOrigine(), other.getPrOrigine())
			&& Objects.equals(this.getAbsOrigine(), other.getAbsOrigine())
			&& Objects.equals(this.getPrExtremite(), other.getPrExtremite())
			&& Objects.equals(this.getAbsExtremite(), other.getAbsExtremite());

	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(final ILocalisationHit pObjet) {
		
		if (this == pObjet) {
			return 0;
		}

		if (pObjet == null) {
			return -1;
		}

		int compareNumDepartement = 0;
		int compareNumRoute = 0;
		int compareIndiceNumRoute = 0;
		int compareIndiceLettreRoute = 0;
		int compareCategorieAdminRoute = 0;
		int comparePrOrigine = 0;
		int compareAbsOrigine = 0;
		
		/* numDepartement. */
		if (this.getNumDepartement() == null) {
			if (pObjet.getNumDepartement() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getNumDepartement() == null) {
				return -1;
			}
			
			compareNumDepartement 
			= this.getNumDepartement()
				.compareToIgnoreCase(pObjet.getNumDepartement());
		
			if (compareNumDepartement != 0) {
				return compareNumDepartement;
			}
		}
		
		/* numeroRoute. */
		if (this.getNumRoute() == null) {
			if (pObjet.getNumRoute() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getNumRoute() == null) {
				return -1;
			}
			
			compareNumRoute 
			= this.getNumRoute()
				.compareToIgnoreCase(pObjet.getNumRoute());
		
			if (compareNumRoute != 0) {
				return compareNumRoute;
			}
		}
		
		/* indiceNumRoute. */
		if (this.getIndiceNumRoute() == null) {
			if (pObjet.getIndiceNumRoute() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getIndiceNumRoute() == null) {
				return -1;
			}
			
			compareIndiceNumRoute 
			= this.getIndiceNumRoute()
				.compareToIgnoreCase(pObjet.getIndiceNumRoute());
		
			if (compareIndiceNumRoute != 0) {
				return compareIndiceNumRoute;
			}
		}
		
		/* indiceLettreRoute. */
		if (this.getIndiceLettreRoute() == null) {
			if (pObjet.getIndiceLettreRoute() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getIndiceLettreRoute() == null) {
				return -1;
			}
			
			compareIndiceLettreRoute 
			= this.getIndiceLettreRoute()
				.compareToIgnoreCase(pObjet.getIndiceLettreRoute());
		
			if (compareIndiceLettreRoute != 0) {
				return compareIndiceLettreRoute;
			}
		}
		
		/* categorieAdminRoute. */
		if (this.getCategorieAdminRoute() == null) {
			if (pObjet.getCategorieAdminRoute() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getCategorieAdminRoute() == null) {
				return -1;
			}
			
			compareCategorieAdminRoute 
			= this.getCategorieAdminRoute()
				.compareToIgnoreCase(pObjet.getCategorieAdminRoute());
		
			if (compareCategorieAdminRoute != 0) {
				return compareCategorieAdminRoute;
			}
		}
		
		/* prOrigine. */
		if (this.getPrOrigine() == null) {
			if (pObjet.getPrOrigine() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getPrOrigine() == null) {
				return -1;
			}
			
			comparePrOrigine 
			= this.getPrOrigine()
				.compareTo(pObjet.getPrOrigine());
		
			if (comparePrOrigine != 0) {
				return comparePrOrigine;
			}
		}
		
		/* absOrigine. */
		if (this.getAbsOrigine() == null) {
			if (pObjet.getAbsOrigine() != null) {
				return +1;
			}
			
			return 0;
		}
		
		if (pObjet.getAbsOrigine() == null) {
			return -1;
		}
		
		compareAbsOrigine 
			= this.getAbsOrigine()
				.compareTo(pObjet.getAbsOrigine());
		
		return compareAbsOrigine;

	} // Fin de compareTo(...).____________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ILocalisationHit clone() throws CloneNotSupportedException {
		
		final ILocalisationHit clone 
			= (ILocalisationHit) super.clone();
		
		clone.setId(this.getId());
		clone.setNumRoute(this.getNumRoute());
		clone.setIndiceNumRoute(this.getIndiceNumRoute());
		clone.setIndiceLettreRoute(this.getIndiceLettreRoute());
		clone.setCategorieAdminRoute(this.getCategorieAdminRoute());
		clone.setNumDepartement(this.getNumDepartement());
		clone.setLieuDitOrigine(this.getLieuDitOrigine());
		clone.setPrOrigine(this.getPrOrigine());
		clone.setAbsOrigine(this.getAbsOrigine());
		clone.setLieuDitExtremite(this.getLieuDitExtremite());
		clone.setPrExtremite(this.getPrExtremite());
		clone.setAbsExtremite(this.getAbsExtremite());
		clone.setLieuDitComptage(this.getLieuDitComptage());
		clone.setPrComptage(this.getPrComptage());
		clone.setAbsComptage(this.getAbsComptage());
		
		return (LocalisationHitEntityJPA) clone;
		
	} // Fin de clone().___________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {

		final StringBuilder stb = new StringBuilder();

		stb.append("LocalisationHitEntityJPA [");

		stb.append("id=");
		if (this.getId() != null) {
			stb.append(this.getId());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("numRoute=");
		if (this.getNumRoute() != null) {
			stb.append(this.getNumRoute());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("indiceNumRoute=");
		if (this.getIndiceNumRoute() != null) {
			stb.append(this.getIndiceNumRoute());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("indiceLettreRoute=");
		if (this.getIndiceLettreRoute() != null) {
			stb.append(this.getIndiceLettreRoute());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("categorieAdminRoute=");
		if (this.getCategorieAdminRoute() != null) {
			stb.append(this.getCategorieAdminRoute());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("numDepartement=");
		if (this.getNumDepartement() != null) {
			stb.append(this.getNumDepartement());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("lieuDitOrigine=");
		if (this.getLieuDitOrigine() != null) {
			stb.append(this.getLieuDitOrigine());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("prOrigine=");
		if (this.getPrOrigine() != null) {
			stb.append(this.getPrOrigine());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("absOrigine=");
		if (this.getAbsOrigine() != null) {
			stb.append(this.getAbsOrigine());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("lieuDitExtremite=");
		if (this.getLieuDitExtremite() != null) {
			stb.append(this.getLieuDitExtremite());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("prExtremite=");
		if (this.getPrExtremite() != null) {
			stb.append(this.getPrExtremite());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("absExtremite=");
		if (this.getAbsExtremite() != null) {
			stb.append(this.getAbsExtremite());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("lieuDitComptage=");
		if (this.getLieuDitComptage() != null) {
			stb.append(this.getLieuDitComptage());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("prComptage=");
		if (this.getPrComptage() != null) {
			stb.append(this.getPrComptage());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("absComptage=");
		if (this.getAbsComptage() != null) {
			stb.append(this.getAbsComptage());
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
		
		return "id;"
				+ "numRoute;indiceNumRoute;indiceLettreRoute;categorieAdminRoute;"
				+ "numDepartement;"
				+ "lieuDitOrigine;prOrigine;absOrigine;"
				+ "lieuDitExtremite;prExtremite;absExtremite;"
				+ "lieuDitComptage;prComptage;absComptage;";
		
	} // Fin de fournirEnTeteCsv().________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirStringCsv() {

		final StringBuilder stb = new StringBuilder();

		stb.append(this.getId());
		stb.append(POINT_VIRGULE);
		stb.append(this.getNumRoute());
		stb.append(POINT_VIRGULE);
		stb.append(this.getIndiceNumRoute());
		stb.append(POINT_VIRGULE);
		stb.append(this.getIndiceLettreRoute());
		stb.append(POINT_VIRGULE);
		stb.append(this.getCategorieAdminRoute());
		stb.append(POINT_VIRGULE);
		stb.append(this.getNumDepartement());
		stb.append(POINT_VIRGULE);
		stb.append(this.getLieuDitOrigine());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPrOrigine());
		stb.append(POINT_VIRGULE);
		stb.append(this.getAbsOrigine());
		stb.append(POINT_VIRGULE);
		stb.append(this.getLieuDitExtremite());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPrExtremite());
		stb.append(POINT_VIRGULE);
		stb.append(this.getAbsExtremite());
		stb.append(POINT_VIRGULE);
		stb.append(this.getLieuDitComptage());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPrComptage());
		stb.append(POINT_VIRGULE);
		stb.append(this.getAbsComptage());
		stb.append(POINT_VIRGULE);

		return stb.toString();

	} // Fin de fournirStringCsv().________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteColonne(final int pI) {

		String entete = null;

		switch (pI) {

		case 0:
			entete = "id";
			break;

		case 1:
			entete = "numRoute";
			break;

		case 2:
			entete = "indiceNumRoute";
			break;

		case 3:
			entete = "indiceLettreRoute";
			break;

		case 4:
			entete = "categorieAdminRoute";
			break;

		case 5:
			entete = "numDepartement";
			break;

		case 6:
			entete = "lieuDitOrigine";
			break;

		case 7:
			entete = "prOrigine";
			break;

		case 8:
			entete = "absOrigine";
			break;

		case 9:
			entete = "lieuDitExtremite";
			break;

		case 10:
			entete = "prExtremite";
			break;

		case 11:
			entete = "absExtremite";
			break;

		case 12:
			entete = "lieuDitComptage";
			break;

		case 13:
			entete = "prComptage";
			break;

		case 14:
			entete = "absComptage";
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
	public final Object fournirValeurColonne(final int pI) {

		Object valeur = null;

		switch (pI) {

		case 0:
			if (this.getId() != null) {
				valeur = String.valueOf(this.getId());
			}			
			break;

		case 1:
			valeur = this.getNumRoute();
			break;

		case 2:
			valeur = this.getIndiceNumRoute();
			break;

		case 3:
			valeur = this.getIndiceLettreRoute();
			break;

		case 4:
			valeur = this.getCategorieAdminRoute();
			break;

		case 5:
			valeur = this.getNumDepartement();
			break;

		case 6:
			valeur = this.getLieuDitOrigine();
			break;

		case 7:
			if (this.getPrOrigine() != null) {
				valeur = String.valueOf(this.getPrOrigine());
			}			
			break;

		case 8:
			if (this.getAbsOrigine() != null) {
				valeur = String.valueOf(this.getAbsOrigine());
			}			
			break;

		case 9:
			valeur = this.getLieuDitExtremite();
			break;

		case 10:
			if (this.getPrExtremite() != null) {
				valeur = String.valueOf(this.getPrExtremite());
			}			
			break;

		case 11:
			if (this.getAbsExtremite() != null) {
				valeur = String.valueOf(this.getAbsExtremite());
			}			
			break;

		case 12:
			valeur = this.getLieuDitComptage();
			break;

		case 13:
			if (this.getPrComptage() != null) {
				valeur = String.valueOf(this.getPrComptage());
			}			
			break;

		case 14:
			if (this.getAbsComptage() != null) {
				valeur = String.valueOf(this.getAbsComptage());
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
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
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
	@Column(name="NUMROUTE"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	@Override
	public final String getNumRoute() {
		return this.numRoute;
	} // Fin de getNumRoute()._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNumRoute(
			final String pNumRoute) {
		this.numRoute = pNumRoute;
	} // Fin de setNumRoute(...).__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="INDICENUMROUTE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public final String getIndiceNumRoute() {
		return this.indiceNumRoute;
	} // Fin de getIndiceNumRoute()._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setIndiceNumRoute(
			final String pIndiceNumRoute) {
		this.indiceNumRoute = pIndiceNumRoute;
	} // Fin de setIndiceNumRoute(...).____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="INDICELETTREROUTE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public final String getIndiceLettreRoute() {
		return this.indiceLettreRoute;
	} // Fin de getIndiceLettreRoute().____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setIndiceLettreRoute(
			final String pIndiceLettreRoute) {
		this.indiceLettreRoute = pIndiceLettreRoute;
	} // Fin de setIndiceLettreRoute(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="CATEGORIEADMINROUTE"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	@Override
	public final String getCategorieAdminRoute() {
		return this.categorieAdminRoute;
	} // Fin de getCategorieAdminRoute().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setCategorieAdminRoute(
			final String pCategorieAdminRoute) {
		this.categorieAdminRoute = pCategorieAdminRoute;
	} // Fin de setCategorieAdminRoute(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="NUMDEPARTEMENT"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	@Override
	public final String getNumDepartement() {
		return this.numDepartement;
	} // Fin de getNumDepartement()._______________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNumDepartement(
			final String pNumDepartement) {
		this.numDepartement = pNumDepartement;
	} // Fin de setNumDepartement(...).____________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="LIEUDITORIGINE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public final String getLieuDitOrigine() {
		return this.lieuDitOrigine;
	} // Fin de getLieuDitOrigine()._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setLieuDitOrigine(
			final String pLieuDitOrigine) {
		this.lieuDitOrigine = pLieuDitOrigine;
	} // Fin de setLieuDitOrigine(...).____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PRORIGINE"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	@Override
	public final Integer getPrOrigine() {
		return this.prOrigine;
	} // Fin de getPrOrigine().____________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPrOrigine(
			final Integer pPrOrigine) {
		this.prOrigine = pPrOrigine;
	} // Fin de setPrOrigine(...)._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="ABSORIGINE"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	@Override
	public final Integer getAbsOrigine() {
		return this.absOrigine;
	} // Fin de getAbsOrigine().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setAbsOrigine(
			final Integer pAbsOrigine) {
		this.absOrigine = pAbsOrigine;
	} // Fin de setAbsOrigine(...).________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="LIEUDITEXTREMITE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public final String getLieuDitExtremite() {
		return this.lieuDitExtremite;
	} // Fin de getLieuDitExtremite()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setLieuDitExtremite(
			final String pLieuDitExtremite) {
		this.lieuDitExtremite = pLieuDitExtremite;
	} // Fin de setLieuDitExtremite(...).__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PREXTREMITE"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	@Override
	public final Integer getPrExtremite() {
		return this.prExtremite;
	} // Fin de getPrExtremite().__________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPrExtremite(
			final Integer pPrExtremite) {
		this.prExtremite = pPrExtremite;
	} // Fin de setPrExtremite(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="ABSEXTREMITE"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	@Override
	public final Integer getAbsExtremite() {
		return this.absExtremite;
	} // Fin de getAbsExtremite()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setAbsExtremite(
			final Integer pAbsExtremite) {
		this.absExtremite = pAbsExtremite;
	} // Fin de setAbsExtremite(...).______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="LIEUDITCOMPTAGE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public final String getLieuDitComptage() {
		return this.lieuDitComptage;
	} // Fin de getLieuDitComptage().______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setLieuDitComptage(
			final String pLieuDitComptage) {
		this.lieuDitComptage = pLieuDitComptage;
	} // Fin de setLieuDitComptage(...).___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PRCOMPTAGE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public final Integer getPrComptage() {
		return this.prComptage;
	} // Fin de getPrComptage().___________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPrComptage(
			final Integer pPrComptage) {
		this.prComptage = pPrComptage;
	} // Fin de setPrComptage(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="ABSCOMPTAGE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public final Integer getAbsComptage() {
		return this.absComptage;
	} // Fin de getAbsComptage().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setAbsComptage(
			final Integer pAbsComptage) {
		this.absComptage = pAbsComptage;
	} // Fin de setAbsComptage(...)._______________________________________



} // FIN DE LA CLASSE LocalisationHitEntityJPA.------------------------------
