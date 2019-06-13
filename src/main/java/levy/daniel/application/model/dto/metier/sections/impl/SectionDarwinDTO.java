package levy.daniel.application.model.dto.metier.sections.impl;

import java.util.Objects;
import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.dto.metier.sections.ISectionDarwinDTO;


/**
 * CLASSE SectionDarwinDTO :<br/>
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
 * @since 11 juin 2019
 *
 */
public class SectionDarwinDTO implements ISectionDarwinDTO {

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
	 * id en base sous forme de String.
	 */
	private String id;
	
	/**
	 * identifiant DARWIN de la section.
	 */
	private String objetID;
	
	/**
	 * route au format FEOR (A0034b1 ou A0006).
	 */
	private String route;
	
	/**
	 * département du PR DEBUT au format FEOR (01, 30, 75, 971, ...).
	 */
	private String depPrD;
	
	/**
	 * code concession au PR DEBUT.
	 */
	private String concessionPrD;
	
	/**
	 * PR DEBUT.
	 */
	private String prD;
	
	/**
	 * abscisse du PR DEBUT.
	 */
	private String absD;
	
	/**
	 * département du PR FIN au format FEOR (01, 30, 75, 971, ...).
	 */
	private String depPrF;
	
	/**
	 * code concession au PR FIN.
	 */
	private String concessionPrF;
	
	/**
	 * PR FIN.
	 */
	private String prF;
	
	/**
	 * abscisse du PR FIN.
	 */
	private String absF;
	
	/**
	 * année de mesure du trafic (par exemple '2019').
	 */
	private String anneeMesureTrafic;
	
	/**
	 * numéro DARWIN de la section de trafic.
	 */
	private String numSectionTrafic;

	/**
	 * sens de la section de trafic.
	 */
	private String sensSectionTrafic;

	/**
	 * type de comptage de la section de trafic.
	 */
	private String typeComptageTrafic;
	
	/**
	 * trafic moyen journalier annuel de l'année de traitement N.
	 */
	private String mjaN;
	
	/**
	 * pourcentage de trafic poids lourds annuel de l'année de traitement N.
	 */
	private String pcPLN;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année de traitement N.
	 */
	private String pcNuitAnnuelN;
	
	/**
	 * trafic moyen journalier annuel de l'année n-1.
	 */
	private String mjaNmoins1;
	
	/**
	 * pourcentage de trafic poids lourds annuel de l'année n-1.
	 */
	private String pcPLNmoins1;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-1.
	 */
	private String pcNuitAnnuelNmoins1;
	
	/**
	 * trafic moyen journalier annuel de l'année n-2.
	 */
	private String mjaNmoins2;
	
	/**
	 * pourcentage de trafic poids lourds annuel de l'année n-2.
	 */
	private String pcPLNmoins2;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-2.
	 */
	private String pcNuitAnnuelNmoins2;
	
	/**
	 * trafic moyen journalier annuel de l'année n-3.
	 */
	private String mjaNmoins3;
	
	/**
	 * pourcentage de trafic poids lourds annuel de l'année n-3.
	 */
	private String pcPLNmoins3;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-3.
	 */
	private String pcNuitAnnuelNmoins3;
	
	/**
	 * trafic moyen journalier annuel de l'année n-4.
	 */
	private String mjaNmoins4;
	
	/**
	 * pourcentage de trafic poids lourds annuel de l'année n-4.
	 */
	private String pcPLNmoins4;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-4.
	 */
	private String pcNuitAnnuelNmoins4;
	
	/**
	 * trafic moyen journalier annuel de l'année n-5.
	 */
	private String mjaNmoins5;
	
	/**
	 * pourcentage de trafic poids lourds annuel de l'année n-5.
	 */
	private String pcPLNmoins5;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-5.
	 */
	private String pcNuitAnnuelNmoins5;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de janvier (01) de l'année de traitement N.
	 */
	private String mjmNmois01;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de janvier (01) de l'année de traitement N.
	 */
	private String pcNuitNmois01;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de février (02) de l'année de traitement N.
	 */
	private String mjmNmois02;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de février (02) de l'année de traitement N.
	 */
	private String pcNuitNmois02;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de mars (03) de l'année de traitement N.
	 */
	private String mjmNmois03;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de mars (03) de l'année de traitement N.
	 */
	private String pcNuitNmois03;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de avril (04) de l'année de traitement N.
	 */
	private String mjmNmois04;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de avril (04) de l'année de traitement N.
	 */
	private String pcNuitNmois04;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de mai (05) de l'année de traitement N.
	 */
	private String mjmNmois05;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de mai (05) de l'année de traitement N.
	 */
	private String pcNuitNmois05;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de juin (06) de l'année de traitement N.
	 */
	private String mjmNmois06;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de juin (06) de l'année de traitement N.
	 */
	private String pcNuitNmois06;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de juillet (07) de l'année de traitement N.
	 */
	private String mjmNmois07;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de juillet (07) de l'année de traitement N.
	 */
	private String pcNuitNmois07;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de août (08) de l'année de traitement N.
	 */
	private String mjmNmois08;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de août (08) de l'année de traitement N.
	 */
	private String pcNuitNmois08;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de septembre (09) de l'année de traitement N.
	 */
	private String mjmNmois09;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de septembre (09) de l'année de traitement N.
	 */
	private String pcNuitNmois09;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de octobre (10) de l'année de traitement N.
	 */
	private String mjmNmois10;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de octobre (10) de l'année de traitement N.
	 */
	private String pcNuitNmois10;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de novembre (11) de l'année de traitement N.
	 */
	private String mjmNmois11;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de novembre (11) de l'année de traitement N.
	 */
	private String pcNuitNmois11;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de décembre (12) de l'année de traitement N.
	 */
	private String mjmNmois12;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de décembre (12) de l'année de traitement N.
	 */
	private String pcNuitNmois12;
	
	/**
	 * classement de la route (agglomération).
	 */
	private String classementRoute;
	
	/**
	 * profil en travers à la codification SICRE 
	 * ('1V' pour chaussée unique 1 voie, ...).
	 */
	private String profilTraversSicre;
	
	/**
	 * libellé du lieu-dit ORIGINE (DEBUT) de la section.
	 */
	private String origineSection;
	
	/**
	 * libellé du lieu-dit EXTEMITE (FIN) de la section.
	 */
	private String extremiteSection;
	
	/**
	 * libellé du lieu-dit du point de comptage de la section.
	 */
	private String lieuDitComptage;
	
	/**
	 * PR du point de comptage de la section.
	 */
	private String prComptage;
	
	/**
	 * abscisse du point de comptage de la section.
	 */
	private String absComptage;
	
	/**
	 * itinéraire européen passant par la section de trafic.
	 */
	private String itiEuropeen1;
	
	/**
	 * sous-réseau défini pour l'Indice National de Circulation.
	 */
	private String sousReseauIndice;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(SectionDarwinDTO.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public SectionDarwinDTO() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	 /**
	 * CONSTRUCTEUR CONVERTISSEUR.<br/>
	 * Instancie un DTO à partir d'une SortedMap&lt;Integer, String&gt; 
	 * description de ligne d'un fichier DARWIN_CSV.<br/>
	 * <br/>
	 * - LOG.fatal et jette une RunTimeException 
	 * si pDescriptionLigne == null.<br/>
	 * </br/>
	 * 
	 * 
	 * @param pDescriptionLigne : SortedMap&lt;Integer, String&gt;
	 */
	public SectionDarwinDTO(final SortedMap<Integer, String> pDescriptionLigne) {
		
		super();
		
		/* LOG.fatal et jette une RunTimeException si pDescriptionLigne == null. */
		if (pDescriptionLigne == null) {
			
			final String message 
				= "Impossible d'instancier un SectionDarwinDTO à partir "
						+ "d'une SortedMap<Integer, String> "
						+ "pDescriptionLigne null";
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			throw new RuntimeException(message);
		}
		
		this.setId(null);
		this.setObjetID(pDescriptionLigne.get(1));
		this.setRoute(pDescriptionLigne.get(2));
		this.setDepPrD(pDescriptionLigne.get(3));
		this.setConcessionPrD(pDescriptionLigne.get(4));
		this.setPrD(pDescriptionLigne.get(5));
		this.setAbsD(pDescriptionLigne.get(6));
		this.setDepPrF(pDescriptionLigne.get(7));
		this.setConcessionPrF(pDescriptionLigne.get(8));
		this.setPrF(pDescriptionLigne.get(9));
		this.setAbsF(pDescriptionLigne.get(10));
		this.setAnneeMesureTrafic(pDescriptionLigne.get(11));
		this.setNumSectionTrafic(pDescriptionLigne.get(12));
		this.setSensSectionTrafic(pDescriptionLigne.get(13));
		this.setTypeComptageTrafic(pDescriptionLigne.get(14));
		this.setMjaN(pDescriptionLigne.get(15));
		this.setPcPLN(pDescriptionLigne.get(16));
		this.setPcNuitAnnuelN(pDescriptionLigne.get(17));
		this.setMjaNmoins1(pDescriptionLigne.get(18));
		this.setPcPLNmoins1(pDescriptionLigne.get(19));
		this.setPcNuitAnnuelNmoins1(pDescriptionLigne.get(20));
		this.setMjaNmoins2(pDescriptionLigne.get(21));
		this.setPcPLNmoins2(pDescriptionLigne.get(22));
		this.setPcNuitAnnuelNmoins2(pDescriptionLigne.get(23));
		this.setMjaNmoins3(pDescriptionLigne.get(24));
		this.setPcPLNmoins3(pDescriptionLigne.get(25));
		this.setPcNuitAnnuelNmoins3(pDescriptionLigne.get(26));
		this.setMjaNmoins4(pDescriptionLigne.get(27));
		this.setPcPLNmoins4(pDescriptionLigne.get(28));
		this.setPcNuitAnnuelNmoins4(pDescriptionLigne.get(29));
		this.setMjaNmoins5(pDescriptionLigne.get(30));
		this.setPcPLNmoins5(pDescriptionLigne.get(31));
		this.setPcNuitAnnuelNmoins5(pDescriptionLigne.get(32));
		this.setMjmNmois01(pDescriptionLigne.get(33));
		this.setPcNuitNmois01(pDescriptionLigne.get(34));
		this.setMjmNmois02(pDescriptionLigne.get(35));
		this.setPcNuitNmois02(pDescriptionLigne.get(36));
		this.setMjmNmois03(pDescriptionLigne.get(37));
		this.setPcNuitNmois03(pDescriptionLigne.get(38));
		this.setMjmNmois04(pDescriptionLigne.get(39));
		this.setPcNuitNmois04(pDescriptionLigne.get(40));
		this.setMjmNmois05(pDescriptionLigne.get(41));
		this.setPcNuitNmois05(pDescriptionLigne.get(42));
		this.setMjmNmois06(pDescriptionLigne.get(43));
		this.setPcNuitNmois06(pDescriptionLigne.get(44));
		this.setMjmNmois07(pDescriptionLigne.get(45));
		this.setPcNuitNmois07(pDescriptionLigne.get(46));
		this.setMjmNmois08(pDescriptionLigne.get(47));
		this.setPcNuitNmois08(pDescriptionLigne.get(48));
		this.setMjmNmois09(pDescriptionLigne.get(49));
		this.setPcNuitNmois09(pDescriptionLigne.get(50));
		this.setMjmNmois10(pDescriptionLigne.get(51));
		this.setPcNuitNmois10(pDescriptionLigne.get(52));
		this.setMjmNmois11(pDescriptionLigne.get(53));
		this.setPcNuitNmois11(pDescriptionLigne.get(54));
		this.setMjmNmois12(pDescriptionLigne.get(55));
		this.setPcNuitNmois12(pDescriptionLigne.get(56));
		this.setClassementRoute(pDescriptionLigne.get(57));
		this.setProfilTraversSicre(pDescriptionLigne.get(58));
		this.setOrigineSection(pDescriptionLigne.get(59));
		this.setExtremiteSection(pDescriptionLigne.get(60));
		this.setLieuDitComptage(pDescriptionLigne.get(61));
		this.setPrComptage(pDescriptionLigne.get(62));
		this.setAbsComptage(pDescriptionLigne.get(63));
		this.setItiEuropeen1(pDescriptionLigne.get(64));
		this.setSousReseauIndice(pDescriptionLigne.get(65));
		
	} // Fin de CONSTRUCTEUR CONVERTISSEUR.________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {

		return Objects.hash(
			this.getObjetID(), this.getRoute()
			, this.getDepPrD(), this.getConcessionPrD(), this.getPrD()
			, this.getAbsD(), this.getDepPrF(), this.getConcessionPrF()
			, this.getPrF(), this.getAbsF(), this.getAnneeMesureTrafic()
			, this.getNumSectionTrafic(), this.getSensSectionTrafic(), this.getTypeComptageTrafic()
			, this.getMjaN(), this.getPcPLN(), this.getPcNuitAnnuelN()
			, this.getMjaNmoins1(), this.getPcPLNmoins1(), this.getPcNuitAnnuelNmoins1()
			, this.getMjaNmoins2(), this.getPcPLNmoins2(), this.getPcNuitAnnuelNmoins2()
			, this.getMjaNmoins3(), this.getPcPLNmoins3(), this.getPcNuitAnnuelNmoins3()
			, this.getMjaNmoins4(), this.getPcPLNmoins4(), this.getPcNuitAnnuelNmoins4()
			, this.getMjaNmoins5(), this.getPcPLNmoins5(), this.getPcNuitAnnuelNmoins5()
			, this.getMjmNmois01(), this.getPcNuitNmois01(), this.getMjmNmois02()
			, this.getPcNuitNmois02(), this.getMjmNmois03(), this.getPcNuitNmois03()
			, this.getMjmNmois04(), this.getPcNuitNmois04(), this.getMjmNmois05()
			, this.getPcNuitNmois05(), this.getMjmNmois06(), this.getPcNuitNmois06()
			, this.getMjmNmois07(), this.getPcNuitNmois07(), this.getMjmNmois08()
			, this.getPcNuitNmois08(), this.getMjmNmois09(), this.getPcNuitNmois09()
			, this.getMjmNmois10(), this.getPcNuitNmois10(), this.getMjmNmois11()
			, this.getPcNuitNmois11(), this.getMjmNmois12(), this.getPcNuitNmois12()
			, this.getClassementRoute(), this.getProfilTraversSicre(), this.getOrigineSection()
			, this.getExtremiteSection(), this.getLieuDitComptage(), this.getPrComptage()
			, this.getAbsComptage(), this.getItiEuropeen1(), this.getSousReseauIndice());

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
		if (!(pObjet instanceof ISectionDarwinDTO)) {
			return false;
		}
		
		final ISectionDarwinDTO other = (ISectionDarwinDTO) pObjet;

		return Objects
			.equals(this.getObjetID(), other.getObjetID())
			&& Objects.equals(this.getRoute(), other.getRoute())
			&& Objects.equals(this.getDepPrD(), other.getDepPrD())
			&& Objects.equals(this.getConcessionPrD(), other.getConcessionPrD())
			&& Objects.equals(this.getPrD(), other.getPrD())
			&& Objects.equals(this.getAbsD(), other.getAbsD())
			&& Objects.equals(this.getDepPrF(), other.getDepPrF())
			&& Objects.equals(this.getConcessionPrF(), other.getConcessionPrF())
			&& Objects.equals(this.getPrF(), other.getPrF())
			&& Objects.equals(this.getAbsF(), other.getAbsF())
			&& Objects.equals(this.getAnneeMesureTrafic(), other.getAnneeMesureTrafic())
			&& Objects.equals(this.getNumSectionTrafic(), other.getNumSectionTrafic())
			&& Objects.equals(this.getSensSectionTrafic(), other.getSensSectionTrafic())
			&& Objects.equals(this.getTypeComptageTrafic(), other.getTypeComptageTrafic())
			&& Objects.equals(this.getMjaN(), other.getMjaN())
			&& Objects.equals(this.getPcPLN(), other.getPcPLN())
			&& Objects.equals(this.getPcNuitAnnuelN(), other.getPcNuitAnnuelN())
			&& Objects.equals(this.getMjaNmoins1(), other.getMjaNmoins1())
			&& Objects.equals(this.getPcPLNmoins1(), other.getPcPLNmoins1())
			&& Objects.equals(this.getPcNuitAnnuelNmoins1(), other.getPcNuitAnnuelNmoins1())
			&& Objects.equals(this.getMjaNmoins2(), other.getMjaNmoins2())
			&& Objects.equals(this.getPcPLNmoins2(), other.getPcPLNmoins2())
			&& Objects.equals(this.getPcNuitAnnuelNmoins2(), other.getPcNuitAnnuelNmoins2())
			&& Objects.equals(this.getMjaNmoins3(), other.getMjaNmoins3())
			&& Objects.equals(this.getPcPLNmoins3(), other.getPcPLNmoins3())
			&& Objects.equals(this.getPcNuitAnnuelNmoins3(), other.getPcNuitAnnuelNmoins3())
			&& Objects.equals(this.getMjaNmoins4(), other.getMjaNmoins4())
			&& Objects.equals(this.getPcPLNmoins4(), other.getPcPLNmoins4())
			&& Objects.equals(this.getPcNuitAnnuelNmoins4(), other.getPcNuitAnnuelNmoins4())
			&& Objects.equals(this.getMjaNmoins5(), other.getMjaNmoins5())
			&& Objects.equals(this.getPcPLNmoins5(), other.getPcPLNmoins5())
			&& Objects.equals(this.getPcNuitAnnuelNmoins5(), other.getPcNuitAnnuelNmoins5())
			&& Objects.equals(this.getMjmNmois01(), other.getMjmNmois01())
			&& Objects.equals(this.getPcNuitNmois01(), other.getPcNuitNmois01())
			&& Objects.equals(this.getMjmNmois02(), other.getMjmNmois02())
			&& Objects.equals(this.getPcNuitNmois02(), other.getPcNuitNmois02())
			&& Objects.equals(this.getMjmNmois03(), other.getMjmNmois03())
			&& Objects.equals(this.getPcNuitNmois03(), other.getPcNuitNmois03())
			&& Objects.equals(this.getMjmNmois04(), other.getMjmNmois04())
			&& Objects.equals(this.getPcNuitNmois04(), other.getPcNuitNmois04())
			&& Objects.equals(this.getMjmNmois05(), other.getMjmNmois05())
			&& Objects.equals(this.getPcNuitNmois05(), other.getPcNuitNmois05())
			&& Objects.equals(this.getMjmNmois06(), other.getMjmNmois06())
			&& Objects.equals(this.getPcNuitNmois06(), other.getPcNuitNmois06())
			&& Objects.equals(this.getMjmNmois07(), other.getMjmNmois07())
			&& Objects.equals(this.getPcNuitNmois07(), other.getPcNuitNmois07())
			&& Objects.equals(this.getMjmNmois08(), other.getMjmNmois08())
			&& Objects.equals(this.getPcNuitNmois08(), other.getPcNuitNmois08())
			&& Objects.equals(this.getMjmNmois09(), other.getMjmNmois09())
			&& Objects.equals(this.getPcNuitNmois09(), other.getPcNuitNmois09())
			&& Objects.equals(this.getMjmNmois10(), other.getMjmNmois10())
			&& Objects.equals(this.getPcNuitNmois10(), other.getPcNuitNmois10())
			&& Objects.equals(this.getMjmNmois11(), other.getMjmNmois11())
			&& Objects.equals(this.getPcNuitNmois11(), other.getPcNuitNmois11())
			&& Objects.equals(this.getMjmNmois12(), other.getMjmNmois12())
			&& Objects.equals(this.getPcNuitNmois12(), other.getPcNuitNmois12())
			&& Objects.equals(this.getClassementRoute(), other.getClassementRoute())
			&& Objects.equals(this.getProfilTraversSicre(), other.getProfilTraversSicre())
			&& Objects.equals(this.getOrigineSection(), other.getOrigineSection())
			&& Objects.equals(this.getExtremiteSection(), other.getExtremiteSection())
			&& Objects.equals(this.getLieuDitComptage(), other.getLieuDitComptage())
			&& Objects.equals(this.getPrComptage(), other.getPrComptage())
			&& Objects.equals(this.getAbsComptage(), other.getAbsComptage())
			&& Objects.equals(this.getItiEuropeen1(), other.getItiEuropeen1())
			&& Objects.equals(this.getSousReseauIndice(), other.getSousReseauIndice());

	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(
			final ISectionDarwinDTO pObjet) {
		
		if (this == pObjet) {
			return 0;
		}

		if (pObjet == null) {
			return -1;
		}
		
		int compareAnneeMesureTrafic = 0;
		int compareDepPrD = 0;
		int compareRoute = 0;
		int comparePrD = 0;
		
		/* anneeMesureTrafic. */
		if (this.getAnneeMesureTrafic() == null) {
			if (pObjet.getAnneeMesureTrafic() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getAnneeMesureTrafic() == null) {
				return -1;
			}
			
			compareAnneeMesureTrafic 
			= this.getAnneeMesureTrafic()
				.compareToIgnoreCase(pObjet.getAnneeMesureTrafic());
		
			if (compareAnneeMesureTrafic != 0) {
				return compareAnneeMesureTrafic;
			}
		}
		
		/* depPrD. */
		if (this.getDepPrD() == null) {
			if (pObjet.getDepPrD() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getDepPrD() == null) {
				return -1;
			}
			
			compareDepPrD 
			= this.getDepPrD()
				.compareToIgnoreCase(pObjet.getDepPrD());
		
			if (compareDepPrD != 0) {
				return compareDepPrD;
			}
		}

		/* route. */
		if (this.getRoute() == null) {
			if (pObjet.getRoute() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getRoute() == null) {
				return -1;
			}
			
			compareRoute 
			= this.getRoute()
				.compareToIgnoreCase(pObjet.getRoute());
		
			if (compareRoute != 0) {
				return compareRoute;
			}
		}
		
		/* prD. */
		if (this.getPrD() == null) {
			if (pObjet.getPrD() != null) {
				return +1;
			}
			
			return 0;
		}
		
		if (pObjet.getPrD() == null) {
			return -1;
		}
		
		comparePrD 
			= this.getPrD()
				.compareToIgnoreCase(pObjet.getPrD());
		
		return comparePrD;

	} // Fin de compareTo(...).____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ISectionDarwinDTO clone() throws CloneNotSupportedException {
		
		final ISectionDarwinDTO clone 
			= (ISectionDarwinDTO) super.clone();
				
		clone.setId(this.getId());
		clone.setObjetID(this.getObjetID());
		clone.setRoute(this.getRoute());
		clone.setDepPrD(this.getDepPrD());
		clone.setConcessionPrD(this.getConcessionPrD());
		clone.setPrD(this.getPrD());
		clone.setAbsD(this.getAbsD());
		clone.setDepPrF(this.getDepPrF());
		clone.setConcessionPrF(this.getConcessionPrF());
		clone.setPrF(this.getPrF());
		clone.setAbsF(this.getAbsF());
		clone.setAnneeMesureTrafic(this.getAnneeMesureTrafic());
		clone.setNumSectionTrafic(this.getNumSectionTrafic());
		clone.setSensSectionTrafic(this.getSensSectionTrafic());
		clone.setTypeComptageTrafic(this.getTypeComptageTrafic());
		clone.setMjaN(this.getMjaN());
		clone.setPcPLN(this.getPcPLN());
		clone.setPcNuitAnnuelN(this.getPcNuitAnnuelN());
		clone.setMjaNmoins1(this.getMjaNmoins1());
		clone.setPcPLNmoins1(this.getPcPLNmoins1());
		clone.setPcNuitAnnuelNmoins1(this.getPcNuitAnnuelNmoins1());
		clone.setMjaNmoins2(this.getMjaNmoins2());
		clone.setPcPLNmoins2(this.getPcPLNmoins2());
		clone.setPcNuitAnnuelNmoins2(this.getPcNuitAnnuelNmoins2());
		clone.setMjaNmoins3(this.getMjaNmoins3());
		clone.setPcPLNmoins3(this.getPcPLNmoins3());
		clone.setPcNuitAnnuelNmoins3(this.getPcNuitAnnuelNmoins3());
		clone.setMjaNmoins4(this.getMjaNmoins4());
		clone.setPcPLNmoins4(this.getPcPLNmoins4());
		clone.setPcNuitAnnuelNmoins4(this.getPcNuitAnnuelNmoins4());
		clone.setMjaNmoins5(this.getMjaNmoins5());
		clone.setPcPLNmoins5(this.getPcPLNmoins5());
		clone.setPcNuitAnnuelNmoins5(this.getPcNuitAnnuelNmoins5());
		clone.setMjmNmois01(this.getMjmNmois01());
		clone.setPcNuitNmois01(this.getPcNuitNmois01());
		clone.setMjmNmois02(this.getMjmNmois02());
		clone.setPcNuitNmois02(this.getPcNuitNmois02());
		clone.setMjmNmois03(this.getMjmNmois03());
		clone.setPcNuitNmois03(this.getPcNuitNmois03());
		clone.setMjmNmois04(this.getMjmNmois04());
		clone.setPcNuitNmois04(this.getPcNuitNmois04());
		clone.setMjmNmois05(this.getMjmNmois05());
		clone.setPcNuitNmois05(this.getPcNuitNmois05());
		clone.setMjmNmois06(this.getMjmNmois06());
		clone.setPcNuitNmois06(this.getPcNuitNmois06());
		clone.setMjmNmois07(this.getMjmNmois07());
		clone.setPcNuitNmois07(this.getPcNuitNmois07());
		clone.setMjmNmois08(this.getMjmNmois08());
		clone.setPcNuitNmois08(this.getPcNuitNmois08());
		clone.setMjmNmois09(this.getMjmNmois09());
		clone.setPcNuitNmois09(this.getPcNuitNmois09());
		clone.setMjmNmois10(this.getMjmNmois10());
		clone.setPcNuitNmois10(this.getPcNuitNmois10());
		clone.setMjmNmois11(this.getMjmNmois11());
		clone.setPcNuitNmois11(this.getPcNuitNmois11());
		clone.setMjmNmois12(this.getMjmNmois12());
		clone.setPcNuitNmois12(this.getPcNuitNmois12());
		clone.setClassementRoute(this.getClassementRoute());
		clone.setProfilTraversSicre(this.getProfilTraversSicre());
		clone.setOrigineSection(this.getOrigineSection());
		clone.setExtremiteSection(this.getExtremiteSection());
		clone.setLieuDitComptage(this.getLieuDitComptage());
		clone.setPrComptage(this.getPrComptage());
		clone.setAbsComptage(this.getAbsComptage());
		clone.setItiEuropeen1(this.getItiEuropeen1());
		clone.setSousReseauIndice(this.getSousReseauIndice());

		return (SectionDarwinDTO) clone;
		
	} // Fin de clone().___________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {

		final StringBuilder stb = new StringBuilder();

		stb.append("SectionDarwinDTO [");

		stb.append("id=");
		if (this.getId() != null) {
			stb.append(this.getId());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("objetID=");
		if (this.getObjetID() != null) {
			stb.append(this.getObjetID());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("route=");
		if (this.getRoute() != null) {
			stb.append(this.getRoute());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("depPrD=");
		if (this.getDepPrD() != null) {
			stb.append(this.getDepPrD());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("concessionPrD=");
		if (this.getConcessionPrD() != null) {
			stb.append(this.getConcessionPrD());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("prD=");
		if (this.getPrD() != null) {
			stb.append(this.getPrD());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("absD=");
		if (this.getAbsD() != null) {
			stb.append(this.getAbsD());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("depPrF=");
		if (this.getDepPrF() != null) {
			stb.append(this.getDepPrF());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("concessionPrF=");
		if (this.getConcessionPrF() != null) {
			stb.append(this.getConcessionPrF());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("prF=");
		if (this.getPrF() != null) {
			stb.append(this.getPrF());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("absF=");
		if (this.getAbsF() != null) {
			stb.append(this.getAbsF());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("anneeMesureTrafic=");
		if (this.getAnneeMesureTrafic() != null) {
			stb.append(this.getAnneeMesureTrafic());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("numSectionTrafic=");
		if (this.getNumSectionTrafic() != null) {
			stb.append(this.getNumSectionTrafic());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("sensSectionTrafic=");
		if (this.getSensSectionTrafic() != null) {
			stb.append(this.getSensSectionTrafic());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("typeComptageTrafic=");
		if (this.getTypeComptageTrafic() != null) {
			stb.append(this.getTypeComptageTrafic());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjaN=");
		if (this.getMjaN() != null) {
			stb.append(this.getMjaN());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcPLN=");
		if (this.getPcPLN() != null) {
			stb.append(this.getPcPLN());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitAnnuelN=");
		if (this.getPcNuitAnnuelN() != null) {
			stb.append(this.getPcNuitAnnuelN());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjaNmoins1=");
		if (this.getMjaNmoins1() != null) {
			stb.append(this.getMjaNmoins1());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcPLNmoins1=");
		if (this.getPcPLNmoins1() != null) {
			stb.append(this.getPcPLNmoins1());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitAnnuelNmoins1=");
		if (this.getPcNuitAnnuelNmoins1() != null) {
			stb.append(this.getPcNuitAnnuelNmoins1());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjaNmoins2=");
		if (this.getMjaNmoins2() != null) {
			stb.append(this.getMjaNmoins2());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcPLNmoins2=");
		if (this.getPcPLNmoins2() != null) {
			stb.append(this.getPcPLNmoins2());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitAnnuelNmoins2=");
		if (this.getPcNuitAnnuelNmoins2() != null) {
			stb.append(this.getPcNuitAnnuelNmoins2());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjaNmoins3=");
		if (this.getMjaNmoins3() != null) {
			stb.append(this.getMjaNmoins3());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcPLNmoins3=");
		if (this.getPcPLNmoins3() != null) {
			stb.append(this.getPcPLNmoins3());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitAnnuelNmoins3=");
		if (this.getPcNuitAnnuelNmoins3() != null) {
			stb.append(this.getPcNuitAnnuelNmoins3());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjaNmoins4=");
		if (this.getMjaNmoins4() != null) {
			stb.append(this.getMjaNmoins4());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcPLNmoins4=");
		if (this.getPcPLNmoins4() != null) {
			stb.append(this.getPcPLNmoins4());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitAnnuelNmoins4=");
		if (this.getPcNuitAnnuelNmoins4() != null) {
			stb.append(this.getPcNuitAnnuelNmoins4());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjaNmoins5=");
		if (this.getMjaNmoins5() != null) {
			stb.append(this.getMjaNmoins5());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcPLNmoins5=");
		if (this.getPcPLNmoins5() != null) {
			stb.append(this.getPcPLNmoins5());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitAnnuelNmoins5=");
		if (this.getPcNuitAnnuelNmoins5() != null) {
			stb.append(this.getPcNuitAnnuelNmoins5());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois01=");
		if (this.getMjmNmois01() != null) {
			stb.append(this.getMjmNmois01());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois01=");
		if (this.getPcNuitNmois01() != null) {
			stb.append(this.getPcNuitNmois01());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois02=");
		if (this.getMjmNmois02() != null) {
			stb.append(this.getMjmNmois02());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois02=");
		if (this.getPcNuitNmois02() != null) {
			stb.append(this.getPcNuitNmois02());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois03=");
		if (this.getMjmNmois03() != null) {
			stb.append(this.getMjmNmois03());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois03=");
		if (this.getPcNuitNmois03() != null) {
			stb.append(this.getPcNuitNmois03());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois04=");
		if (this.getMjmNmois04() != null) {
			stb.append(this.getMjmNmois04());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois04=");
		if (this.getPcNuitNmois04() != null) {
			stb.append(this.getPcNuitNmois04());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois05=");
		if (this.getMjmNmois05() != null) {
			stb.append(this.getMjmNmois05());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois05=");
		if (this.getPcNuitNmois05() != null) {
			stb.append(this.getPcNuitNmois05());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois06=");
		if (this.getMjmNmois06() != null) {
			stb.append(this.getMjmNmois06());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois06=");
		if (this.getPcNuitNmois06() != null) {
			stb.append(this.getPcNuitNmois06());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois07=");
		if (this.getMjmNmois07() != null) {
			stb.append(this.getMjmNmois07());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois07=");
		if (this.getPcNuitNmois07() != null) {
			stb.append(this.getPcNuitNmois07());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois08=");
		if (this.getMjmNmois08() != null) {
			stb.append(this.getMjmNmois08());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois08=");
		if (this.getPcNuitNmois08() != null) {
			stb.append(this.getPcNuitNmois08());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois09=");
		if (this.getMjmNmois09() != null) {
			stb.append(this.getMjmNmois09());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois09=");
		if (this.getPcNuitNmois09() != null) {
			stb.append(this.getPcNuitNmois09());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois10=");
		if (this.getMjmNmois10() != null) {
			stb.append(this.getMjmNmois10());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois10=");
		if (this.getPcNuitNmois10() != null) {
			stb.append(this.getPcNuitNmois10());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois11=");
		if (this.getMjmNmois11() != null) {
			stb.append(this.getMjmNmois11());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois11=");
		if (this.getPcNuitNmois11() != null) {
			stb.append(this.getPcNuitNmois11());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois12=");
		if (this.getMjmNmois12() != null) {
			stb.append(this.getMjmNmois12());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois12=");
		if (this.getPcNuitNmois12() != null) {
			stb.append(this.getPcNuitNmois12());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("classementRoute=");
		if (this.getClassementRoute() != null) {
			stb.append(this.getClassementRoute());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("profilTraversSicre=");
		if (this.getProfilTraversSicre() != null) {
			stb.append(this.getProfilTraversSicre());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("origineSection=");
		if (this.getOrigineSection() != null) {
			stb.append(this.getOrigineSection());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("extremiteSection=");
		if (this.getExtremiteSection() != null) {
			stb.append(this.getExtremiteSection());
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
		stb.append(VIRGULE_ESPACE);

		stb.append("itiEuropeen1=");
		if (this.getItiEuropeen1() != null) {
			stb.append(this.getItiEuropeen1());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("sousReseauIndice=");
		if (this.getSousReseauIndice() != null) {
			stb.append(this.getSousReseauIndice());
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
	public final String toStringASCII() {

		final StringBuilder stb = new StringBuilder();

		stb.append(this.getObjetID());
		stb.append(this.getRoute());
		stb.append(this.getDepPrD());
		stb.append(this.getConcessionPrD());
		stb.append(this.getPrD());
		stb.append(this.getAbsD());
		stb.append(this.getDepPrF());
		stb.append(this.getConcessionPrF());
		stb.append(this.getPrF());
		stb.append(this.getAbsF());
		stb.append(this.getAnneeMesureTrafic());
		stb.append(this.getNumSectionTrafic());
		stb.append(this.getSensSectionTrafic());
		stb.append(this.getTypeComptageTrafic());
		stb.append(this.getMjaN());
		stb.append(this.getPcPLN());
		stb.append(this.getPcNuitAnnuelN());
		stb.append(this.getMjaNmoins1());
		stb.append(this.getPcPLNmoins1());
		stb.append(this.getPcNuitAnnuelNmoins1());
		stb.append(this.getMjaNmoins2());
		stb.append(this.getPcPLNmoins2());
		stb.append(this.getPcNuitAnnuelNmoins2());
		stb.append(this.getMjaNmoins3());
		stb.append(this.getPcPLNmoins3());
		stb.append(this.getPcNuitAnnuelNmoins3());
		stb.append(this.getMjaNmoins4());
		stb.append(this.getPcPLNmoins4());
		stb.append(this.getPcNuitAnnuelNmoins4());
		stb.append(this.getMjaNmoins5());
		stb.append(this.getPcPLNmoins5());
		stb.append(this.getPcNuitAnnuelNmoins5());
		stb.append(this.getMjmNmois01());
		stb.append(this.getPcNuitNmois01());
		stb.append(this.getMjmNmois02());
		stb.append(this.getPcNuitNmois02());
		stb.append(this.getMjmNmois03());
		stb.append(this.getPcNuitNmois03());
		stb.append(this.getMjmNmois04());
		stb.append(this.getPcNuitNmois04());
		stb.append(this.getMjmNmois05());
		stb.append(this.getPcNuitNmois05());
		stb.append(this.getMjmNmois06());
		stb.append(this.getPcNuitNmois06());
		stb.append(this.getMjmNmois07());
		stb.append(this.getPcNuitNmois07());
		stb.append(this.getMjmNmois08());
		stb.append(this.getPcNuitNmois08());
		stb.append(this.getMjmNmois09());
		stb.append(this.getPcNuitNmois09());
		stb.append(this.getMjmNmois10());
		stb.append(this.getPcNuitNmois10());
		stb.append(this.getMjmNmois11());
		stb.append(this.getPcNuitNmois11());
		stb.append(this.getMjmNmois12());
		stb.append(this.getPcNuitNmois12());
		stb.append(this.getClassementRoute());
		stb.append(this.getProfilTraversSicre());
		stb.append(this.getOrigineSection());
		stb.append(this.getExtremiteSection());
		stb.append(this.getLieuDitComptage());
		stb.append(this.getPrComptage());
		stb.append(this.getAbsComptage());
		stb.append(this.getItiEuropeen1());
		stb.append(this.getSousReseauIndice());

		return stb.toString();

	} // Fin de toStringASCII().___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteCsv() {
		
		return "id;objetID;route;"
				+ "depPrD;concessionPrD;prD;absD;"
				+ "depPrF;concessionPrF;prF;absF;"
				+ "anneeMesureTrafic;"
				+ "numSectionTrafic;"
				+ "sensSectionTrafic;"
				+ "typeComptageTrafic;"
				+ "mjaN;pcPLN;pcNuitAnnuelN;"
				+ "mjaNmoins1;pcPLNmoins1;pcNuitAnnuelNmoins1;"
				+ "mjaNmoins2;pcPLNmoins2;pcNuitAnnuelNmoins2;"
				+ "mjaNmoins3;pcPLNmoins3;pcNuitAnnuelNmoins3;"
				+ "mjaNmoins4;pcPLNmoins4;pcNuitAnnuelNmoins4;"
				+ "mjaNmoins5;pcPLNmoins5;pcNuitAnnuelNmoins5;"
				+ "mjmNmois01;pcNuitNmois01;"
				+ "mjmNmois02;pcNuitNmois02;"
				+ "mjmNmois03;pcNuitNmois03;"
				+ "mjmNmois04;pcNuitNmois04;"
				+ "mjmNmois05;pcNuitNmois05;"
				+ "mjmNmois06;pcNuitNmois06;"
				+ "mjmNmois07;pcNuitNmois07;"
				+ "mjmNmois08;pcNuitNmois08;"
				+ "mjmNmois09;pcNuitNmois09;"
				+ "mjmNmois10;pcNuitNmois10;"
				+ "mjmNmois11;pcNuitNmois11;"
				+ "mjmNmois12;pcNuitNmois12;"
				+ "classementRoute;"
				+ "profilTraversSicre;"
				+ "origineSection;extremiteSection;"
				+ "lieuDitComptage;prComptage;absComptage;"
				+ "itiEuropeen1;"
				+ "sousReseauIndice;";
		
	} // Fin de fournirEnTeteCsv().________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirStringCsv() {

		final StringBuilder stb = new StringBuilder();

		stb.append(this.getId());
		stb.append(POINT_VIRGULE);
		stb.append(this.getObjetID());
		stb.append(POINT_VIRGULE);
		stb.append(this.getRoute());
		stb.append(POINT_VIRGULE);
		stb.append(this.getDepPrD());
		stb.append(POINT_VIRGULE);
		stb.append(this.getConcessionPrD());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPrD());
		stb.append(POINT_VIRGULE);
		stb.append(this.getAbsD());
		stb.append(POINT_VIRGULE);
		stb.append(this.getDepPrF());
		stb.append(POINT_VIRGULE);
		stb.append(this.getConcessionPrF());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPrF());
		stb.append(POINT_VIRGULE);
		stb.append(this.getAbsF());
		stb.append(POINT_VIRGULE);
		stb.append(this.getAnneeMesureTrafic());
		stb.append(POINT_VIRGULE);
		stb.append(this.getNumSectionTrafic());
		stb.append(POINT_VIRGULE);
		stb.append(this.getSensSectionTrafic());
		stb.append(POINT_VIRGULE);
		stb.append(this.getTypeComptageTrafic());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjaN());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcPLN());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitAnnuelN());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjaNmoins1());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcPLNmoins1());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitAnnuelNmoins1());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjaNmoins2());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcPLNmoins2());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitAnnuelNmoins2());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjaNmoins3());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcPLNmoins3());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitAnnuelNmoins3());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjaNmoins4());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcPLNmoins4());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitAnnuelNmoins4());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjaNmoins5());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcPLNmoins5());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitAnnuelNmoins5());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois01());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois01());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois02());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois02());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois03());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois03());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois04());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois04());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois05());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois05());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois06());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois06());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois07());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois07());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois08());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois08());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois09());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois09());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois10());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois10());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois11());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois11());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois12());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois12());
		stb.append(POINT_VIRGULE);
		stb.append(this.getClassementRoute());
		stb.append(POINT_VIRGULE);
		stb.append(this.getProfilTraversSicre());
		stb.append(POINT_VIRGULE);
		stb.append(this.getOrigineSection());
		stb.append(POINT_VIRGULE);
		stb.append(this.getExtremiteSection());
		stb.append(POINT_VIRGULE);
		stb.append(this.getLieuDitComptage());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPrComptage());
		stb.append(POINT_VIRGULE);
		stb.append(this.getAbsComptage());
		stb.append(POINT_VIRGULE);
		stb.append(this.getItiEuropeen1());
		stb.append(POINT_VIRGULE);
		stb.append(this.getSousReseauIndice());
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
			entete = "objetID";
			break;

		case 2:
			entete = "route";
			break;

		case 3:
			entete = "depPrD";
			break;

		case 4:
			entete = "concessionPrD";
			break;

		case 5:
			entete = "prD";
			break;

		case 6:
			entete = "absD";
			break;

		case 7:
			entete = "depPrF";
			break;

		case 8:
			entete = "concessionPrF";
			break;

		case 9:
			entete = "prF";
			break;

		case 10:
			entete = "absF";
			break;

		case 11:
			entete = "anneeMesureTrafic";
			break;

		case 12:
			entete = "numSectionTrafic";
			break;

		case 13:
			entete = "sensSectionTrafic";
			break;

		case 14:
			entete = "typeComptageTrafic";
			break;

		case 15:
			entete = "mjaN";
			break;

		case 16:
			entete = "pcPLN";
			break;

		case 17:
			entete = "pcNuitAnnuelN";
			break;

		case 18:
			entete = "mjaNmoins1";
			break;

		case 19:
			entete = "pcPLNmoins1";
			break;

		case 20:
			entete = "pcNuitAnnuelNmoins1";
			break;

		case 21:
			entete = "mjaNmoins2";
			break;

		case 22:
			entete = "pcPLNmoins2";
			break;

		case 23:
			entete = "pcNuitAnnuelNmoins2";
			break;

		case 24:
			entete = "mjaNmoins3";
			break;

		case 25:
			entete = "pcPLNmoins3";
			break;

		case 26:
			entete = "pcNuitAnnuelNmoins3";
			break;

		case 27:
			entete = "mjaNmoins4";
			break;

		case 28:
			entete = "pcPLNmoins4";
			break;

		case 29:
			entete = "pcNuitAnnuelNmoins4";
			break;

		case 30:
			entete = "mjaNmoins5";
			break;

		case 31:
			entete = "pcPLNmoins5";
			break;

		case 32:
			entete = "pcNuitAnnuelNmoins5";
			break;

		case 33:
			entete = "mjmNmois01";
			break;

		case 34:
			entete = "pcNuitNmois01";
			break;

		case 35:
			entete = "mjmNmois02";
			break;

		case 36:
			entete = "pcNuitNmois02";
			break;

		case 37:
			entete = "mjmNmois03";
			break;

		case 38:
			entete = "pcNuitNmois03";
			break;

		case 39:
			entete = "mjmNmois04";
			break;

		case 40:
			entete = "pcNuitNmois04";
			break;

		case 41:
			entete = "mjmNmois05";
			break;

		case 42:
			entete = "pcNuitNmois05";
			break;

		case 43:
			entete = "mjmNmois06";
			break;

		case 44:
			entete = "pcNuitNmois06";
			break;

		case 45:
			entete = "mjmNmois07";
			break;

		case 46:
			entete = "pcNuitNmois07";
			break;

		case 47:
			entete = "mjmNmois08";
			break;

		case 48:
			entete = "pcNuitNmois08";
			break;

		case 49:
			entete = "mjmNmois09";
			break;

		case 50:
			entete = "pcNuitNmois09";
			break;

		case 51:
			entete = "mjmNmois10";
			break;

		case 52:
			entete = "pcNuitNmois10";
			break;

		case 53:
			entete = "mjmNmois11";
			break;

		case 54:
			entete = "pcNuitNmois11";
			break;

		case 55:
			entete = "mjmNmois12";
			break;

		case 56:
			entete = "pcNuitNmois12";
			break;

		case 57:
			entete = "classementRoute";
			break;

		case 58:
			entete = "profilTraversSicre";
			break;

		case 59:
			entete = "origineSection";
			break;

		case 60:
			entete = "extremiteSection";
			break;

		case 61:
			entete = "lieuDitComptage";
			break;

		case 62:
			entete = "prComptage";
			break;

		case 63:
			entete = "absComptage";
			break;

		case 64:
			entete = "itiEuropeen1";
			break;

		case 65:
			entete = "sousReseauIndice";
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
			valeur = this.getObjetID();
			break;

		case 2:
			valeur = this.getRoute();
			break;

		case 3:
			valeur = this.getDepPrD();
			break;

		case 4:
			valeur = this.getConcessionPrD();
			break;

		case 5:
			valeur = this.getPrD();
			break;

		case 6:
			valeur = this.getAbsD();
			break;

		case 7:
			valeur = this.getDepPrF();
			break;

		case 8:
			valeur = this.getConcessionPrF();
			break;

		case 9:
			valeur = this.getPrF();
			break;

		case 10:
			valeur = this.getAbsF();
			break;

		case 11:
			valeur = this.getAnneeMesureTrafic();
			break;

		case 12:
			valeur = this.getNumSectionTrafic();
			break;

		case 13:
			valeur = this.getSensSectionTrafic();
			break;

		case 14:
			valeur = this.getTypeComptageTrafic();
			break;

		case 15:
			valeur = this.getMjaN();
			break;

		case 16:
			valeur = this.getPcPLN();
			break;

		case 17:
			valeur = this.getPcNuitAnnuelN();
			break;

		case 18:
			valeur = this.getMjaNmoins1();
			break;

		case 19:
			valeur = this.getPcPLNmoins1();
			break;

		case 20:
			valeur = this.getPcNuitAnnuelNmoins1();
			break;

		case 21:
			valeur = this.getMjaNmoins2();
			break;

		case 22:
			valeur = this.getPcPLNmoins2();
			break;

		case 23:
			valeur = this.getPcNuitAnnuelNmoins2();
			break;

		case 24:
			valeur = this.getMjaNmoins3();
			break;

		case 25:
			valeur = this.getPcPLNmoins3();
			break;

		case 26:
			valeur = this.getPcNuitAnnuelNmoins3();
			break;

		case 27:
			valeur = this.getMjaNmoins4();
			break;

		case 28:
			valeur = this.getPcPLNmoins4();
			break;

		case 29:
			valeur = this.getPcNuitAnnuelNmoins4();
			break;

		case 30:
			valeur = this.getMjaNmoins5();
			break;

		case 31:
			valeur = this.getPcPLNmoins5();
			break;

		case 32:
			valeur = this.getPcNuitAnnuelNmoins5();
			break;

		case 33:
			valeur = this.getMjmNmois01();
			break;

		case 34:
			valeur = this.getPcNuitNmois01();
			break;

		case 35:
			valeur = this.getMjmNmois02();
			break;

		case 36:
			valeur = this.getPcNuitNmois02();
			break;

		case 37:
			valeur = this.getMjmNmois03();
			break;

		case 38:
			valeur = this.getPcNuitNmois03();
			break;

		case 39:
			valeur = this.getMjmNmois04();
			break;

		case 40:
			valeur = this.getPcNuitNmois04();
			break;

		case 41:
			valeur = this.getMjmNmois05();
			break;

		case 42:
			valeur = this.getPcNuitNmois05();
			break;

		case 43:
			valeur = this.getMjmNmois06();
			break;

		case 44:
			valeur = this.getPcNuitNmois06();
			break;

		case 45:
			valeur = this.getMjmNmois07();
			break;

		case 46:
			valeur = this.getPcNuitNmois07();
			break;

		case 47:
			valeur = this.getMjmNmois08();
			break;

		case 48:
			valeur = this.getPcNuitNmois08();
			break;

		case 49:
			valeur = this.getMjmNmois09();
			break;

		case 50:
			valeur = this.getPcNuitNmois09();
			break;

		case 51:
			valeur = this.getMjmNmois10();
			break;

		case 52:
			valeur = this.getPcNuitNmois10();
			break;

		case 53:
			valeur = this.getMjmNmois11();
			break;

		case 54:
			valeur = this.getPcNuitNmois11();
			break;

		case 55:
			valeur = this.getMjmNmois12();
			break;

		case 56:
			valeur = this.getPcNuitNmois12();
			break;

		case 57:
			valeur = this.getClassementRoute();
			break;

		case 58:
			valeur = this.getProfilTraversSicre();
			break;

		case 59:
			valeur = this.getOrigineSection();
			break;

		case 60:
			valeur = this.getExtremiteSection();
			break;

		case 61:
			valeur = this.getLieuDitComptage();
			break;

		case 62:
			valeur = this.getPrComptage();
			break;

		case 63:
			valeur = this.getAbsComptage();
			break;

		case 64:
			valeur = this.getItiEuropeen1();
			break;

		case 65:
			valeur = this.getSousReseauIndice();
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
	public final String getObjetID() {
		return this.objetID;
	} // Fin de getObjetID().______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setObjetID(
			final String pObjetID) {
		this.objetID = pObjetID;
	} // Fin de setObjetId(...).___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getRoute() {
		return this.route;
	} // Fin de getRoute().________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setRoute(
			final String pRoute) {
		this.route = pRoute;
	} // Fin de setRoute(...)._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getDepPrD() {
		return this.depPrD;
	} // Fin de getDepPrD()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setDepPrD(
			final String pDepPrD) {
		this.depPrD = pDepPrD;
	} // Fin de setDepPrD(...).____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getConcessionPrD() {
		return this.concessionPrD;
	} // Fin de getConcessionPrD().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setConcessionPrD(
			final String pConcessionPrD) {
		this.concessionPrD = pConcessionPrD;
	} // Fin de setConcessionPrD(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPrD() {
		return this.prD;
	} // Fin de getPrD().__________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPrD(
			final String pPrD) {
		this.prD = pPrD;
	} // Fin de setPrD(...)._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getAbsD() {
		return this.absD;
	} // Fin de getAbsD()._________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setAbsD(
			final String pAbsD) {
		this.absD = pAbsD;
	} // Fin de setAbsD(...).______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getDepPrF() {
		return this.depPrF;
	} // Fin de getDepPrF()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setDepPrF(
			final String pDepPrF) {
		this.depPrF = pDepPrF;
	} // Fin de setDepPrF(...).____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getConcessionPrF() {
		return this.concessionPrF;
	} // Fin de getConcessionPrF().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setConcessionPrF(
			final String pConcessionPrF) {
		this.concessionPrF = pConcessionPrF;
	} // Fin de setConcessionPrF(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPrF() {
		return this.prF;
	} // Fin de getPrF().__________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPrF(
			final String pPrF) {
		this.prF = pPrF;
	} // Fin de setPrF(...)._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getAbsF() {
		return this.absF;
	} // Fin de getAbsF()._________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setAbsF(
			final String pAbsF) {
		this.absF = pAbsF;
	} // Fin de setAbsF(...).______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getAnneeMesureTrafic() {
		return this.anneeMesureTrafic;
	} // Fin de getAnneeMesureTrafic().____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setAnneeMesureTrafic(
			final String pAnneeMesureTrafic) {
		this.anneeMesureTrafic = pAnneeMesureTrafic;
	} // Fin de setAnneeMesureTrafic(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNumSectionTrafic() {
		return this.numSectionTrafic;
	} // Fin de getNumSectionTrafic()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNumSectionTrafic(
			final String pNumSectionTrafic) {
		this.numSectionTrafic = pNumSectionTrafic;
	} // Fin de setNumSectionTrafic(...).__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getSensSectionTrafic() {
		return this.sensSectionTrafic;
	} // Fin de getSensSectionTrafic().____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setSensSectionTrafic(
			final String pSensSectionTrafic) {
		this.sensSectionTrafic = pSensSectionTrafic;
	} // Fin de setSensSectionTrafic(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getTypeComptageTrafic() {
		return this.typeComptageTrafic;
	} // Fin de getTypeComptageTrafic().___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setTypeComptageTrafic(
			final String pTypeComptageTrafic) {
		this.typeComptageTrafic = pTypeComptageTrafic;
	} // Fin de setTypeComptageTrafic(...).________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjaN() {
		return this.mjaN;
	} // Fin de getMjaN()._________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjaN(
			final String pMjaN) {
		this.mjaN = pMjaN;
	} // Fin de setMjaN(...).______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcPLN() {
		return this.pcPLN;
	} // Fin de getPcPLN().________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcPLN(
			final String pPcPLN) {
		this.pcPLN = pPcPLN;
	} // Fin de setPcPLN(...)._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitAnnuelN() {
		return this.pcNuitAnnuelN;
	} // Fin de getPcNuitAnnuelN().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitAnnuelN(
			final String pPcNuitAnnuelN) {
		this.pcNuitAnnuelN = pPcNuitAnnuelN;
	} // Fin de setPcNuitAnnuelN(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjaNmoins1() {
		return this.mjaNmoins1;
	} // Fin de getMjaNmoins1().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjaNmoins1(
			final String pMjaNmoins1) {
		this.mjaNmoins1 = pMjaNmoins1;
	} // Fin de setMjaNmoins1(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcPLNmoins1() {
		return this.pcPLNmoins1;
	} // Fin de getPcPLNmoins1().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcPLNmoins1(
			final String pPcPLNmoins1) {
		this.pcPLNmoins1 = pPcPLNmoins1;
	} // Fin de setPcPLNmoins1(...)._______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitAnnuelNmoins1() {
		return this.pcNuitAnnuelNmoins1;
	} // Fin de getPcNuitAnnuelNmoins1().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitAnnuelNmoins1(
			final String pPcNuitAnnuelNmoins1) {
		this.pcNuitAnnuelNmoins1 = pPcNuitAnnuelNmoins1;
	} // Fin de setPcNuitAnnuelNmoins1(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjaNmoins2() {
		return this.mjaNmoins2;
	} // Fin de getMjaNmoins2().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjaNmoins2(
			final String pMjaNmoins2) {
		this.mjaNmoins2 = pMjaNmoins2;
	} // Fin de setMjaNmoins2(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcPLNmoins2() {
		return this.pcPLNmoins2;
	} // Fin de getPcPLNmoins2().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcPLNmoins2(
			final String pPcPLNmoins2) {
		this.pcPLNmoins2 = pPcPLNmoins2;
	} // Fin de setPcPLNmoins2(...)._______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitAnnuelNmoins2() {
		return this.pcNuitAnnuelNmoins2;
	} // Fin de getPcNuitAnnuelNmoins2().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitAnnuelNmoins2(
			final String pPcNuitAnnuelNmoins2) {
		this.pcNuitAnnuelNmoins2 = pPcNuitAnnuelNmoins2;
	} // Fin de setPcNuitAnnuelNmoins2(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjaNmoins3() {
		return this.mjaNmoins3;
	} // Fin de getMjaNmoins3().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjaNmoins3(
			final String pMjaNmoins3) {
		this.mjaNmoins3 = pMjaNmoins3;
	} // Fin de setMjaNmoins3(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcPLNmoins3() {
		return this.pcPLNmoins3;
	} // Fin de getPcPLNmoins3().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcPLNmoins3(
			final String pPcPLNmoins3) {
		this.pcPLNmoins3 = pPcPLNmoins3;
	} // Fin de setPcPLNmoins3(...)._______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitAnnuelNmoins3() {
		return this.pcNuitAnnuelNmoins3;
	} // Fin de getPcNuitAnnuelNmoins3().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitAnnuelNmoins3(
			final String pPcNuitAnnuelNmoins3) {
		this.pcNuitAnnuelNmoins3 = pPcNuitAnnuelNmoins3;
	} // Fin de setPcNuitAnnuelNmoins3(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjaNmoins4() {
		return this.mjaNmoins4;
	} // Fin de getMjaNmoins4().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjaNmoins4(
			final String pMjaNmoins4) {
		this.mjaNmoins4 = pMjaNmoins4;
	} // Fin de setMjaNmoins4(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcPLNmoins4() {
		return this.pcPLNmoins4;
	} // Fin de getPcPLNmoins4().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcPLNmoins4(
			final String pPcPLNmoins4) {
		this.pcPLNmoins4 = pPcPLNmoins4;
	} // Fin de setPcPLNmoins4(...)._______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitAnnuelNmoins4() {
		return this.pcNuitAnnuelNmoins4;
	} // Fin de getPcNuitAnnuelNmoins4().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitAnnuelNmoins4(
			final String pPcNuitAnnuelNmoins4) {
		this.pcNuitAnnuelNmoins4 = pPcNuitAnnuelNmoins4;
	} // Fin de setPcNuitAnnuelNmoins4(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjaNmoins5() {
		return this.mjaNmoins5;
	} // Fin de getMjaNmoins5().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjaNmoins5(
			final String pMjaNmoins5) {
		this.mjaNmoins5 = pMjaNmoins5;
	} // Fin de setMjaNmoins5(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcPLNmoins5() {
		return this.pcPLNmoins5;
	} // Fin de getPcPLNmoins5().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcPLNmoins5(
			final String pPcPLNmoins5) {
		this.pcPLNmoins5 = pPcPLNmoins5;
	} // Fin de setPcPLNmoins5(...)._______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitAnnuelNmoins5() {
		return this.pcNuitAnnuelNmoins5;
	} // Fin de getPcNuitAnnuelNmoins5().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitAnnuelNmoins5(
			final String pPcNuitAnnuelNmoins5) {
		this.pcNuitAnnuelNmoins5 = pPcNuitAnnuelNmoins5;
	} // Fin de setPcNuitAnnuelNmoins5(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjmNmois01() {
		return this.mjmNmois01;
	} // Fin de getMjmNmois01().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjmNmois01(
			final String pMjmNmois01) {
		this.mjmNmois01 = pMjmNmois01;
	} // Fin de setMjmNmois01(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitNmois01() {
		return this.pcNuitNmois01;
	} // Fin de getPcNuitNmois01().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitNmois01(
			final String pPcNuitNmois01) {
		this.pcNuitNmois01 = pPcNuitNmois01;
	} // Fin de setPcNuitNmois01(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjmNmois02() {
		return this.mjmNmois02;
	} // Fin de getMjmNmois02().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjmNmois02(
			final String pMjmNmois02) {
		this.mjmNmois02 = pMjmNmois02;
	} // Fin de setMjmNmois02(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitNmois02() {
		return this.pcNuitNmois02;
	} // Fin de getPcNuitNmois02().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitNmois02(
			final String pPcNuitNmois02) {
		this.pcNuitNmois02 = pPcNuitNmois02;
	} // Fin de setPcNuitNmois02(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjmNmois03() {
		return this.mjmNmois03;
	} // Fin de getMjmNmois03().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjmNmois03(
			final String pMjmNmois03) {
		this.mjmNmois03 = pMjmNmois03;
	} // Fin de setMjmNmois03(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitNmois03() {
		return this.pcNuitNmois03;
	} // Fin de getPcNuitNmois03().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitNmois03(
			final String pPcNuitNmois03) {
		this.pcNuitNmois03 = pPcNuitNmois03;
	} // Fin de setPcNuitNmois03(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjmNmois04() {
		return this.mjmNmois04;
	} // Fin de getMjmNmois04().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjmNmois04(
			final String pMjmNmois04) {
		this.mjmNmois04 = pMjmNmois04;
	} // Fin de setMjmNmois04(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitNmois04() {
		return this.pcNuitNmois04;
	} // Fin de getPcNuitNmois04().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitNmois04(
			final String pPcNuitNmois04) {
		this.pcNuitNmois04 = pPcNuitNmois04;
	} // Fin de setPcNuitNmois04(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjmNmois05() {
		return this.mjmNmois05;
	} // Fin de getMjmNmois05().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjmNmois05(
			final String pMjmNmois05) {
		this.mjmNmois05 = pMjmNmois05;
	} // Fin de setMjmNmois05(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitNmois05() {
		return this.pcNuitNmois05;
	} // Fin de getPcNuitNmois05().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitNmois05(
			final String pPcNuitNmois05) {
		this.pcNuitNmois05 = pPcNuitNmois05;
	} // Fin de setPcNuitNmois05(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjmNmois06() {
		return this.mjmNmois06;
	} // Fin de getMjmNmois06().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjmNmois06(
			final String pMjmNmois06) {
		this.mjmNmois06 = pMjmNmois06;
	} // Fin de setMjmNmois06(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitNmois06() {
		return this.pcNuitNmois06;
	} // Fin de getPcNuitNmois06().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitNmois06(
			final String pPcNuitNmois06) {
		this.pcNuitNmois06 = pPcNuitNmois06;
	} // Fin de setPcNuitNmois06(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjmNmois07() {
		return this.mjmNmois07;
	} // Fin de getMjmNmois07().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjmNmois07(
			final String pMjmNmois07) {
		this.mjmNmois07 = pMjmNmois07;
	} // Fin de setMjmNmois07(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitNmois07() {
		return this.pcNuitNmois07;
	} // Fin de getPcNuitNmois07().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitNmois07(
			final String pPcNuitNmois07) {
		this.pcNuitNmois07 = pPcNuitNmois07;
	} // Fin de setPcNuitNmois07(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjmNmois08() {
		return this.mjmNmois08;
	} // Fin de getMjmNmois08().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjmNmois08(
			final String pMjmNmois08) {
		this.mjmNmois08 = pMjmNmois08;
	} // Fin de setMjmNmois08(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitNmois08() {
		return this.pcNuitNmois08;
	} // Fin de getPcNuitNmois08().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitNmois08(
			final String pPcNuitNmois08) {
		this.pcNuitNmois08 = pPcNuitNmois08;
	} // Fin de setPcNuitNmois08(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjmNmois09() {
		return this.mjmNmois09;
	} // Fin de getMjmNmois09().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjmNmois09(
			final String pMjmNmois09) {
		this.mjmNmois09 = pMjmNmois09;
	} // Fin de setMjmNmois09(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitNmois09() {
		return this.pcNuitNmois09;
	} // Fin de getPcNuitNmois09().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitNmois09(
			final String pPcNuitNmois09) {
		this.pcNuitNmois09 = pPcNuitNmois09;
	} // Fin de setPcNuitNmois09(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjmNmois10() {
		return this.mjmNmois10;
	} // Fin de getMjmNmois10().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjmNmois10(
			final String pMjmNmois10) {
		this.mjmNmois10 = pMjmNmois10;
	} // Fin de setMjmNmois10(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitNmois10() {
		return this.pcNuitNmois10;
	} // Fin de getPcNuitNmois10().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitNmois10(
			final String pPcNuitNmois10) {
		this.pcNuitNmois10 = pPcNuitNmois10;
	} // Fin de setPcNuitNmois10(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjmNmois11() {
		return this.mjmNmois11;
	} // Fin de getMjmNmois11().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjmNmois11(
			final String pMjmNmois11) {
		this.mjmNmois11 = pMjmNmois11;
	} // Fin de setMjmNmois11(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitNmois11() {
		return this.pcNuitNmois11;
	} // Fin de getPcNuitNmois11().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitNmois11(
			final String pPcNuitNmois11) {
		this.pcNuitNmois11 = pPcNuitNmois11;
	} // Fin de setPcNuitNmois11(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMjmNmois12() {
		return this.mjmNmois12;
	} // Fin de getMjmNmois12().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMjmNmois12(
			final String pMjmNmois12) {
		this.mjmNmois12 = pMjmNmois12;
	} // Fin de setMjmNmois12(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitNmois12() {
		return this.pcNuitNmois12;
	} // Fin de getPcNuitNmois12().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitNmois12(
			final String pPcNuitNmois12) {
		this.pcNuitNmois12 = pPcNuitNmois12;
	} // Fin de setPcNuitNmois12(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getClassementRoute() {
		return this.classementRoute;
	} // Fin de getClassementRoute().______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setClassementRoute(
			final String pClassementRoute) {
		this.classementRoute = pClassementRoute;
	} // Fin de setClassementRoute(...).___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getProfilTraversSicre() {
		return this.profilTraversSicre;
	} // Fin de getProfilTraversSicre().___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setProfilTraversSicre(
			final String pProfilTraversSicre) {
		this.profilTraversSicre = pProfilTraversSicre;
	} // Fin de setProfilTraversSicre(...).________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getOrigineSection() {
		return this.origineSection;
	} // Fin de getOrigineSection()._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setOrigineSection(
			final String pOrigineSection) {
		this.origineSection = pOrigineSection;
	} // Fin de setOrigineSection(...).____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getExtremiteSection() {
		return this.extremiteSection;
	} // Fin de getExtremiteSection()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setExtremiteSection(
			final String pExtremiteSection) {
		this.extremiteSection = pExtremiteSection;
	} // Fin de setExtremiteSection(...).__________________________________


	
	/**
	 * {@inheritDoc}
	 */
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
	@Override
	public final String getPrComptage() {
		return this.prComptage;
	} // Fin de getPrComptage().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPrComptage(
			final String pPrComptage) {
		this.prComptage = pPrComptage;
	} // Fin de setPrComptage(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getAbsComptage() {
		return this.absComptage;
	} // Fin de getAbsComptage().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setAbsComptage(
			final String pAbsComptage) {
		this.absComptage = pAbsComptage;
	} // Fin de setAbsComptage(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getItiEuropeen1() {
		return this.itiEuropeen1;
	} // Fin de getItiEuropeen1()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setItiEuropeen1(
			final String pItiEuropeen1) {
		this.itiEuropeen1 = pItiEuropeen1;
	} // Fin de setItiEuropeen1(...).______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getSousReseauIndice() {
		return this.sousReseauIndice;
	} // Fin de getSousReseauIndice()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setSousReseauIndice(
			final String pSousReseauIndice) {
		this.sousReseauIndice = pSousReseauIndice;
	} // Fin de setSousReseauIndice(...).__________________________________

	
	
} // FIN DE LA CLASSE SectionDarwinDTO.--------------------------------------
