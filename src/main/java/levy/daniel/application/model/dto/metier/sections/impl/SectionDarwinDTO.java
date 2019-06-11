package levy.daniel.application.model.dto.metier.sections.impl;

import java.util.Objects;

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
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {

		return Objects.hash(
			this.getId(), this.getObjetID(), this.getRoute()
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
			final Object pObject) {
		
		if (this == pObject) {
			return true;
		}
		
		if (pObject == null) {
			return false;
		}
		if (!(pObject instanceof SectionDarwinDTO)) {
			return false;
		}
		
		final ISectionDarwinDTO other = (ISectionDarwinDTO) pObject;
		
		return Objects
				.equals(absComptage, other.absComptage) 
				&& Objects.equals(absD, other.absD)
				&& Objects.equals(absF, other.absF) 
				&& Objects.equals(anneeMesureTrafic, other.anneeMesureTrafic)
				&& Objects.equals(classementRoute, other.classementRoute)
				&& Objects.equals(concessionPrD, other.concessionPrD)
				&& Objects.equals(concessionPrF, other.concessionPrF) && Objects.equals(depPrD, other.depPrD)
				&& Objects.equals(depPrF, other.depPrF) && Objects.equals(extremiteSection, other.extremiteSection)
				&& Objects.equals(itiEuropeen1, other.itiEuropeen1)
				&& Objects.equals(lieuDitComptage, other.lieuDitComptage) && Objects.equals(mjaN, other.mjaN)
				&& Objects.equals(mjaNmoins1, other.mjaNmoins1) && Objects.equals(mjaNmoins2, other.mjaNmoins2)
				&& Objects.equals(mjaNmoins3, other.mjaNmoins3) && Objects.equals(mjaNmoins4, other.mjaNmoins4)
				&& Objects.equals(mjaNmoins5, other.mjaNmoins5) && Objects.equals(mjmNmois01, other.mjmNmois01)
				&& Objects.equals(mjmNmois02, other.mjmNmois02) && Objects.equals(mjmNmois03, other.mjmNmois03)
				&& Objects.equals(mjmNmois04, other.mjmNmois04) && Objects.equals(mjmNmois05, other.mjmNmois05)
				&& Objects.equals(mjmNmois06, other.mjmNmois06) && Objects.equals(mjmNmois07, other.mjmNmois07)
				&& Objects.equals(mjmNmois08, other.mjmNmois08) && Objects.equals(mjmNmois09, other.mjmNmois09)
				&& Objects.equals(mjmNmois10, other.mjmNmois10) && Objects.equals(mjmNmois11, other.mjmNmois11)
				&& Objects.equals(mjmNmois12, other.mjmNmois12)
				&& Objects.equals(numSectionTrafic, other.numSectionTrafic) && Objects.equals(objetID, other.objetID)
				&& Objects.equals(origineSection, other.origineSection)
				&& Objects.equals(pcNuitAnnuelN, other.pcNuitAnnuelN)
				&& Objects.equals(pcNuitAnnuelNmoins1, other.pcNuitAnnuelNmoins1)
				&& Objects.equals(pcNuitAnnuelNmoins2, other.pcNuitAnnuelNmoins2)
				&& Objects.equals(pcNuitAnnuelNmoins3, other.pcNuitAnnuelNmoins3)
				&& Objects.equals(pcNuitAnnuelNmoins4, other.pcNuitAnnuelNmoins4)
				&& Objects.equals(pcNuitAnnuelNmoins5, other.pcNuitAnnuelNmoins5)
				&& Objects.equals(pcNuitNmois01, other.pcNuitNmois01)
				&& Objects.equals(pcNuitNmois02, other.pcNuitNmois02)
				&& Objects.equals(pcNuitNmois03, other.pcNuitNmois03)
				&& Objects.equals(pcNuitNmois04, other.pcNuitNmois04)
				&& Objects.equals(pcNuitNmois05, other.pcNuitNmois05)
				&& Objects.equals(pcNuitNmois06, other.pcNuitNmois06)
				&& Objects.equals(pcNuitNmois07, other.pcNuitNmois07)
				&& Objects.equals(pcNuitNmois08, other.pcNuitNmois08)
				&& Objects.equals(pcNuitNmois09, other.pcNuitNmois09)
				&& Objects.equals(pcNuitNmois10, other.pcNuitNmois10)
				&& Objects.equals(pcNuitNmois11, other.pcNuitNmois11)
				&& Objects.equals(pcNuitNmois12, other.pcNuitNmois12) && Objects.equals(pcPLN, other.pcPLN)
				&& Objects.equals(pcPLNmoins1, other.pcPLNmoins1) && Objects.equals(pcPLNmoins2, other.pcPLNmoins2)
				&& Objects.equals(pcPLNmoins3, other.pcPLNmoins3) && Objects.equals(pcPLNmoins4, other.pcPLNmoins4)
				&& Objects.equals(pcPLNmoins5, other.pcPLNmoins5) && Objects.equals(prComptage, other.prComptage)
				&& Objects.equals(prD, other.prD) && Objects.equals(prF, other.prF)
				&& Objects.equals(profilTraversSicre, other.profilTraversSicre) && Objects.equals(route, other.route)
				&& Objects.equals(sensSectionTrafic, other.sensSectionTrafic)
				&& Objects.equals(sousReseauIndice, other.sousReseauIndice)
				&& Objects.equals(typeComptageTrafic, other.typeComptageTrafic);
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(ISectionDarwinDTO pObjet) {
		// TODO Auto-generated method stub
		return 0;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public ISectionDarwinDTO clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toStringASCII() {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String fournirEnTeteCsv() {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String fournirStringCsv() {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String fournirEnTeteColonne(int pI) {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object fournirValeurColonne(int pI) {
		// TODO Auto-generated method stub
		return null;
	}



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
