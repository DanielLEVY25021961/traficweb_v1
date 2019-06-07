package levy.daniel.application.model.dto.metier.sections.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.dto.metier.sections.ISectionHitDTO;

/**
 * CLASSE SectionHitDTO :<br/>
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
 * @since 7 juin 2019
 *
 */
public class SectionHitDTO implements ISectionHitDTO {

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
	 * id en base sous forme de String.<br/>
	 */
	private String id;
	
	/**
	 * numéro de département.<br/>
	 */
	private String numDepartement;
	
	/**
	 * numéro de section.<br/>
	 */
	private String numSection;

	/**
	 * sens de trafic.<br/>
	 */
	private String sens;
	
	/**
	 * nature du comptage.<br/>
	 */
	private String nature;
	
	/**
	 * classe du trafic.<br/>
	 */
	private String classe;
	
	/**
	 * année de traitement.<br/>
	 */
	private String anneeTraitement;
	
	/**
	 * zone libre 1.<br/>
	 */
	private String zoneLibre1;
	
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
	 * type de comptage.
	 */
	private String typeComptage;
	
	/**
	 * classement de la route.
	 */
	private String classementRoute;
	
	/**
	 * classe de largeur de chaussée unique.
	 */
	private String classeLargeurChausseeU;
	
	/**
	 * classe de largeur de chaussées séparées.
	 */
	private String classeLargeurChausseesS;
	
	/**
	 * type de réseau.
	 */
	private String typeReseau;
	
	/**
	 * type de localisation (PR ou PK).
	 */
	private String pRoupK;
	
	/**
	 * libellé du lieu-dit origine.
	 */
	private String lieuDitOrigine;
	
	/**
	 * PR Origine.
	 */
	private String prOrigine;
	
	/**
	 * abscisse du point origine.
	 */
	private String absOrigine;
	
	/**
	 * libellé du lieu-dit extremité.
	 */
	private String lieuDitExtremite;
	
	/**
	 * PR Extremité.
	 */
	private String prExtremite;
	
	/**
	 * abscisse du point extremité.
	 */
	private String absExtremite;
	
	/**
	 * libellé du lieu-dit du point de comptage.
	 */
	private String lieuDitComptage;
	
	/**
	 * PR du point de comptage.
	 */
	private String prComptage;
	
	/**
	 * abscisse du point de comptage.
	 */
	private String absComptage;
	
	/**
	 * longueur de la section en mètres.
	 */
	private String longueurSection;
	
	/**
	 * longueur en rase campagne en mètres.
	 */
	private String longueurRaseCampagne;
	
	/**
	 * numéro de département de la section de Rattachement.
	 */
	private String numDepartementRattachement;
	
	/**
	 * numéro de la section de Rattachement.
	 */
	private String numSectionRattachement;
	
	/**
	 * sens de la section de Rattachement.
	 */
	private String sensRattachement;
	
	/**
	 * numéro de département de la section Limitrophe.
	 */
	private String numDepartementLimitrophe;
	
	/**
	 * numéro de la section Limitrophe.
	 */
	private String numSectionLimitrophe;
	
	/**
	 * sens de la section Limitrophe.
	 */
	private String sensLimitrophe;
	
	/**
	 * mois de sectionnement.
	 */
	private String moisSectionnement;
	
	/**
	 * annee de sectionnement.
	 */
	private String anneeSectionnement;
	
	/**
	 * zone libre 2.
	 */
	private String zoneLibre2;
	
	/**
	 * trafic moyen journalier annuel de l'année de traitement N.
	 */
	private String mjaN;
	
	/**
	 * mode de calcul des trafics.
	 */
	private String modeCalcul;
	
	/**
	 * pourcentage de trafic poids lourds annuel de l'année de traitement N.
	 */
	private String pcPLN;
	
	/**
	 * moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année de traitement N.
	 */
	private String evaluationPLN;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année de traitement N.
	 */
	private String pcNuitNannuel;
	
	/**
	 * indice de fiabilité de la TMJAN.
	 */
	private String indiceFiabiliteMjaN;
	
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
	 * zone libre 3.
	 */
	private String zoneLibre3;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(SectionHitDTO.class);

	
	// *************************METHODES************************************/
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(ISectionHitDTO pObjet) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ISectionHitDTO clone() throws CloneNotSupportedException {
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
	@Override
	public final String getNumSection() {
		return this.numSection;
	} // Fin de getNumSection().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNumSection(
			final String pNumSection) {
		this.numSection = pNumSection;
	} // Fin de setNumSection(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getSens() {
		return this.sens;
	} // Fin de getSens()._________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setSens(
			final String pSens) {
		this.sens = pSens;
	} // Fin de setSens(...).______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNature() {
		return this.nature;
	} // Fin de getNature()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNature(
			final String pNature) {
		this.nature = pNature;
	} // Fin de setNature(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getClasse() {
		return this.classe;
	} // Fin de getClasse()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setClasse(
			final String pClasse) {
		this.classe = pClasse;
	} // Fin de setClasse(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getAnneeTraitement() {
		return this.anneeTraitement;
	} // Fin de getAnneeTraitement().______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setAnneeTraitement(
			final String pAnneeTraitement) {
		this.anneeTraitement = pAnneeTraitement;
	} // Fin de setAnneeTraitement(...).___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getZoneLibre1() {
		return this.zoneLibre1;
	} // Fin de getZoneLibre1().___________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setZoneLibre1(
			final String pZoneLibre1) {
		this.zoneLibre1 = pZoneLibre1;
	} // Fin de setZoneLibre1(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
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
	@Override
	public final String getTypeComptage() {
		return this.typeComptage;
	} // Fin de getTypeComptage()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setTypeComptage(
			final String pTypeComptage) {
		this.typeComptage = pTypeComptage;
	} // Fin de setTypeComptage(...).______________________________________


	
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
	public final String getClasseLargeurChausseeU() {
		return this.classeLargeurChausseeU;
	} // Fin de getClasseLargeurChausseeU()._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setClasseLargeurChausseeU(
			final String pClasseLargeurChausseeU) {
		this.classeLargeurChausseeU = pClasseLargeurChausseeU;
	} // Fin de setClasseLargeurChausseeU(...).____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getClasseLargeurChausseesS() {
		return this.classeLargeurChausseesS;
	} // Fin de getClasseLargeurChausseesS().______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setClasseLargeurChausseesS(
			final String pClasseLargeurChausseesS) {
		this.classeLargeurChausseesS = pClasseLargeurChausseesS;
	} // Fin de setClasseLargeurChausseesS(...).___________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getTypeReseau() {
		return this.typeReseau;
	} // Fin de getTypeReseau().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setTypeReseau(
			final String pTypeReseau) {
		this.typeReseau = pTypeReseau;
	} // Fin de setTypeReseau(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getpRoupK() {
		return this.pRoupK;
	} // Fin de getpRoupK()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setpRoupK(
			final String pPRoupK) {
		this.pRoupK = pPRoupK;
	} // Fin de setpRoupK(...).____________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
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
	@Override
	public final String getPrOrigine() {
		return this.prOrigine;
	} // Fin de getPrOrigine().____________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPrOrigine(
			final String pPrOrigine) {
		this.prOrigine = pPrOrigine;
	} // Fin de setPrOrigine(...)._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getAbsOrigine() {
		return this.absOrigine;
	} // Fin de getAbsOrigine().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setAbsOrigine(
			final String pAbsOrigine) {
		this.absOrigine = pAbsOrigine;
	} // Fin de setAbsOrigine(...).________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
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
	@Override
	public final String getPrExtremite() {
		return this.prExtremite;
	} // Fin de getPrExtremite().__________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPrExtremite(
			final String pPrExtremite) {
		this.prExtremite = pPrExtremite;
	} // Fin de setPrExtremite(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getAbsExtremite() {
		return this.absExtremite;
	} // Fin de getAbsExtremite()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setAbsExtremite(
			final String pAbsExtremite) {
		this.absExtremite = pAbsExtremite;
	} // Fin de setAbsExtremite(...).______________________________________

	
	
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
	public final String getLongueurSection() {
		return this.longueurSection;
	} // Fin de getLongueurSection().______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setLongueurSection(
			final String pLongueurSection) {
		this.longueurSection = pLongueurSection;
	} // Fin de setLongueurSection(...).___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getLongueurRaseCampagne() {
		return this.longueurRaseCampagne;
	} // Fin de getLongueurRaseCampagne()._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setLongueurRaseCampagne(
			final String pLongueurRaseCampagne) {
		this.longueurRaseCampagne = pLongueurRaseCampagne;
	} // Fin de setLongueurRaseCampagne(...).______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNumDepartementRattachement() {
		return this.numDepartementRattachement;
	} // Fin de getNumDepartementRattachement().___________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNumDepartementRattachement(
			final String pNumDepartementRattachement) {
		this.numDepartementRattachement = pNumDepartementRattachement;
	} // Fin de setNumDepartementRattachement(...).________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNumSectionRattachement() {
		return this.numSectionRattachement;
	} // Fin de getNumSectionRattachement()._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNumSectionRattachement(
			final String pNumSectionRattachement) {
		this.numSectionRattachement = pNumSectionRattachement;
	} // Fin de setNumSectionRattachement(...).____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getSensRattachement() {
		return this.sensRattachement;
	} // Fin de getSensRattachement()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setSensRattachement(
			final String pSensRattachement) {
		this.sensRattachement = pSensRattachement;
	} // Fin de setSensRattachement(...).__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNumDepartementLimitrophe() {
		return this.numDepartementLimitrophe;
	} // Fin de getNumDepartementLimitrophe()._____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNumDepartementLimitrophe(
			final String pNumDepartementLimitrophe) {
		this.numDepartementLimitrophe = pNumDepartementLimitrophe;
	} // Fin de setNumDepartementLimitrophe(...).__________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNumSectionLimitrophe() {
		return this.numSectionLimitrophe;
	} // Fin de getNumSectionLimitrophe()._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNumSectionLimitrophe(
			final String pNumSectionLimitrophe) {
		this.numSectionLimitrophe = pNumSectionLimitrophe;
	} // Fin de setNumSectionLimitrophe(...).______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getSensLimitrophe() {
		return this.sensLimitrophe;
	} // Fin de getSensLimitrophe()._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setSensLimitrophe(
			final String pSensLimitrophe) {
		this.sensLimitrophe = pSensLimitrophe;
	} // Fin de setSensLimitrophe(...).____________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMoisSectionnement() {
		return this.moisSectionnement;
	} // Fin de getMoisSectionnement().____________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMoisSectionnement(
			final String pMoisSectionnement) {
		this.moisSectionnement = pMoisSectionnement;
	} // Fin de setMoisSectionnement(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getAnneeSectionnement() {
		return this.anneeSectionnement;
	} // Fin de getAnneeSectionnement().___________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setAnneeSectionnement(
			final String pAnneeSectionnement) {
		this.anneeSectionnement = pAnneeSectionnement;
	} // Fin de setAnneeSectionnement(...).________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getZoneLibre2() {
		return this.zoneLibre2;
	} // Fin de getZoneLibre2().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setZoneLibre2(
			final String pZoneLibre2) {
		this.zoneLibre2 = pZoneLibre2;
	} // Fin de setZoneLibre2(...).________________________________________


	
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
	public final String getModeCalcul() {
		return this.modeCalcul;
	} // Fin de getModeCalcul().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setModeCalcul(
			final String pModeCalcul) {
		this.modeCalcul = pModeCalcul;
	} // Fin de setModeCalcul(...).________________________________________


	
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
	public final String getEvaluationPLN() {
		return this.evaluationPLN;
	} // Fin de getEvaluationPLN().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setEvaluationPLN(
			final String pEvaluationPLN) {
		this.evaluationPLN = pEvaluationPLN;
	} // Fin de setEvaluationPLN(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPcNuitNannuel() {
		return this.pcNuitNannuel;
	} // Fin de getPcNuitNannuel().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPcNuitNannuel(
			final String pPcNuitNannuel) {
		this.pcNuitNannuel = pPcNuitNannuel;
	} // Fin de setPcNuitNannuel(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getIndiceFiabiliteMjaN() {
		return this.indiceFiabiliteMjaN;
	} // Fin de getIndiceFiabiliteMjaN().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setIndiceFiabiliteMjaN(
			final String pIndiceFiabiliteMjaN) {
		this.indiceFiabiliteMjaN = pIndiceFiabiliteMjaN;
	} // Fin de setIndiceFiabiliteMjaN(...)._______________________________


	
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
	public final String getZoneLibre3() {
		return this.zoneLibre3;
	} // Fin de getZoneLibre3().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setZoneLibre3(
			final String pZoneLibre3) {
		this.zoneLibre3 = pZoneLibre3;
	} // Fin de setZoneLibre3(...).________________________________________

	
	
} // FIN DE LA CLASSE SectionHitDTO.-----------------------------------------
