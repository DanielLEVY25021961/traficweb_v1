package levy.daniel.application.model.dto.metier.sections;

import java.io.Serializable;

import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;

/**
 * INTERFACE ISectionHitDTO :<br/>
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
public interface ISectionHitDTO extends Comparable<ISectionHitDTO>
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
	int compareTo(ISectionHitDTO pObjet);



	/**
	 * clone.<br/>
	 *
	 * @return ISectionHitDTO
	 * 
	 * @throws CloneNotSupportedException
	 */
	ISectionHitDTO clone() throws CloneNotSupportedException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ISectionHitDTO</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ISectionHitDTO</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	String fournirStringCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ISectionHitDTO</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteColonne(int pI);



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ISectionHitDTO</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
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
	 * Getter du numéro de département.<br/>
	 *
	 * @return this.numDepartement : String.<br/>
	 */
	String getNumDepartement();
	

	
	/**
	* Setter du numéro de département.<br/>
	*
	* @param pNumDepartement : String : 
	* valeur à passer à this.numDepartement.<br/>
	*/
	void setNumDepartement(String pNumDepartement);


	
	/**
	 * Getter du numéro de section.<br/>
	 *
	 * @return this.numSection : String.<br/>
	 */
	String getNumSection();


	
	/**
	* Setter du numéro de section.<br/>
	*
	* @param pNumSection : String : 
	* valeur à passer à this.numSection.<br/>
	*/
	void setNumSection(String pNumSection);


	
	/**
	 * Getter du sens de trafic.<br/>
	 *
	 * @return this.sens : String.<br/>
	 */
	String getSens();


	
	/**
	* Setter du sens de trafic.<br/>
	*
	* @param pSens : String : 
	* valeur à passer à this.sens.<br/>
	*/
	void setSens(String pSens);


	
	/**
	 * Getter de la nature du comptage.<br/>
	 *
	 * @return this.nature : String.<br/>
	 */
	String getNature();


	
	/**
	* Setter de la nature du comptage.<br/>
	*
	* @param pNature : String : 
	* valeur à passer à this.nature.<br/>
	*/
	void setNature(String pNature);


	
	/**
	 * Getter de la classe du trafic.<br/>
	 *
	 * @return this.classe : String.<br/>
	 */
	String getClasse();


	
	/**
	* Setter de la classe du trafic.<br/>
	*
	* @param pClasse : String : 
	* valeur à passer à this.classe.<br/>
	*/
	void setClasse(String pClasse);


	
	/**
	 * Getter de l'année de traitement.<br/>
	 *
	 * @return this.anneeTraitement : String.<br/>
	 */
	String getAnneeTraitement();


	
	/**
	* Setter de l'année de traitement.<br/>
	*
	* @param pAnneeTraitement : String : 
	* valeur à passer à this.anneeTraitement.<br/>
	*/
	void setAnneeTraitement(String pAnneeTraitement);


	
	/**
	 * Getter de la zone libre 1.<br/>
	 *
	 * @return this.zoneLibre1 : String.<br/>
	 */
	String getZoneLibre1();

	
	
	/**
	* Setter de la zone libre 1.<br/>
	*
	* @param pZoneLibre1 : String : 
	* valeur à passer à this.zoneLibre1.<br/>
	*/
	void setZoneLibre1(String pZoneLibre1);


	
	/**
	 * Getter du numero de la route.
	 *
	 * @return this.numRoute : String.<br/>
	 */
	String getNumRoute();


	
	/**
	* Setter du numero de la route.
	*
	* @param pNumRoute : String : 
	* valeur à passer à this.numRoute.<br/>
	*/
	void setNumRoute(String pNumRoute);


	
	/**
	 * Getter de l'indice numérique de la route.
	 *
	 * @return this.indiceNumRoute : String.<br/>
	 */
	String getIndiceNumRoute();


	
	/**
	* Setter de l'indice numérique de la route.
	*
	* @param pIndiceNumRoute : String : 
	* valeur à passer à this.indiceNumRoute.<br/>
	*/
	void setIndiceNumRoute(String pIndiceNumRoute);


	
	/**
	 * Getter de l'indice lettre de la route.
	 *
	 * @return this.indiceLettreRoute : String.<br/>
	 */
	String getIndiceLettreRoute();


	
	/**
	* Setter de l'indice lettre de la route.
	*
	* @param pIndiceLettreRoute : String : 
	* valeur à passer à this.indiceLettreRoute.<br/>
	*/
	void setIndiceLettreRoute(String pIndiceLettreRoute);


	
	/**
	 * Getter de la catégorie administrative de la route.
	 *
	 * @return this.categorieAdminRoute : String.<br/>
	 */
	String getCategorieAdminRoute();


	
	/**
	* Setter de la catégorie administrative de la route.
	*
	* @param pCategorieAdminRoute : String : 
	* valeur à passer à this.categorieAdminRoute.<br/>
	*/
	void setCategorieAdminRoute(String pCategorieAdminRoute);


	
	/**
	 * Getter du type de comptage.
	 *
	 * @return this.typeComptage : String.<br/>
	 */
	String getTypeComptage();


	
	/**
	* Setter du type de comptage.
	*
	* @param pTypeComptage : String : 
	* valeur à passer à this.typeComptage.<br/>
	*/
	void setTypeComptage(String pTypeComptage);


	
	/**
	 * Getter du classement de la route.
	 *
	 * @return this.classementRoute : String.<br/>
	 */
	String getClassementRoute();


	
	/**
	* Setter du classement de la route.
	*
	* @param pClassementRoute : String : 
	* valeur à passer à this.classementRoute.<br/>
	*/
	void setClassementRoute(String pClassementRoute);


	
	/**
	 * Getter de la classe de largeur de chaussée unique.
	 *
	 * @return this.classeLargeurChausseeU : String.<br/>
	 */
	String getClasseLargeurChausseeU();


	
	/**
	* Setter de la classe de largeur de chaussée unique.
	*
	* @param pClasseLargeurChausseeU : String : 
	* valeur à passer à this.classeLargeurChausseeU.<br/>
	*/
	void setClasseLargeurChausseeU(String pClasseLargeurChausseeU);


	
	/**
	 * Getter de la classe de largeur de chaussées séparées.
	 *
	 * @return this.classeLargeurChausseesS : String.<br/>
	 */
	String getClasseLargeurChausseesS();


	
	/**
	* Setter de la classe de largeur de chaussées séparées.
	*
	* @param pClasseLargeurChausseesS : String : 
	* valeur à passer à this.classeLargeurChausseesS.<br/>
	*/
	void setClasseLargeurChausseesS(String pClasseLargeurChausseesS);


	
	/**
	 * Getter du type de réseau.
	 *
	 * @return this.typeReseau : String.<br/>
	 */
	String getTypeReseau();


	
	/**
	* Setter du type de réseau.
	*
	* @param pTypeReseau : String : 
	* valeur à passer à this.typeReseau.<br/>
	*/
	void setTypeReseau(String pTypeReseau);


	
	/**
	 * Getter du type de localisation (PR ou PK).
	 *
	 * @return this.pRoupK : String.<br/>
	 */
	String getpRoupK();


	
	/**
	* Setter du type de localisation (PR ou PK).
	*
	* @param pPRoupK : String : 
	* valeur à passer à this.pRoupK.<br/>
	*/
	void setpRoupK(String pPRoupK);

	
	
	/**
	 * Getter du libellé du lieu-dit origine.
	 *
	 * @return this.lieuDitOrigine : String.<br/>
	 */
	String getLieuDitOrigine();


	
	/**
	* Setter du libellé du lieu-dit origine.
	*
	* @param pLieuDitOrigine : String : 
	* valeur à passer à this.lieuDitOrigine.<br/>
	*/
	void setLieuDitOrigine(String pLieuDitOrigine);


	
	/**
	 * Getter du PR Origine.
	 *
	 * @return this.prOrigine : String.<br/>
	 */
	String getPrOrigine();

	
	
	/**
	* Setter du PR Origine.
	*
	* @param pPrOrigine : String : 
	* valeur à passer à this.prOrigine.<br/>
	*/
	void setPrOrigine(String pPrOrigine);


	
	/**
	 * Getter de l'abscisse du point origine.
	 *
	 * @return this.absOrigine : String.<br/>
	 */
	String getAbsOrigine();

	
	
	/**
	* Setter de l'abscisse du point origine.
	*
	* @param pAbsOrigine : String : 
	* valeur à passer à this.absOrigine.<br/>
	*/
	void setAbsOrigine(String pAbsOrigine);

	
	
	/**
	 * Getter du libellé du lieu-dit extremité.
	 *
	 * @return this.lieuDitExtremite : String.<br/>
	 */
	String getLieuDitExtremite();


	
	/**
	* Setter du libellé du lieu-dit extremité.
	*
	* @param pLieuDitExtremite : String : 
	* valeur à passer à this.lieuDitExtremite.<br/>
	*/
	void setLieuDitExtremite(String pLieuDitExtremite);


	
	/**
	 * Getter du PR Extremité.
	 *
	 * @return this.prExtremite : String.<br/>
	 */
	String getPrExtremite();

	
	
	/**
	* Setter du PR Extremité.
	*
	* @param pPrExtremite : String : 
	* valeur à passer à this.prExtremite.<br/>
	*/
	void setPrExtremite(String pPrExtremite);


	
	/**
	 * Getter de l'abscisse du point extremité.
	 *
	 * @return this.absExtremite : String.<br/>
	 */
	String getAbsExtremite();

	
	
	/**
	* Setter de l'abscisse du point extremité.
	*
	* @param pAbsExtremite : String : 
	* valeur à passer à this.absExtremite.<br/>
	*/
	void setAbsExtremite(String pAbsExtremite);

	
	
	/**
	 * Getter du libellé du lieu-dit du point de comptage.
	 *
	 * @return this.lieuDitComptage : String.<br/>
	 */
	String getLieuDitComptage();


	
	/**
	* Setter du libellé du lieu-dit du point de comptage.
	*
	* @param pLieuDitComptage : String : 
	* valeur à passer à this.lieuDitComptage.<br/>
	*/
	void setLieuDitComptage(String pLieuDitComptage);


	
	/**
	 * Getter du PR du point de comptage.
	 *
	 * @return this.prComptage : String.<br/>
	 */
	String getPrComptage();

	
	
	/**
	* Setter du PR du point de comptage.
	*
	* @param pPrComptage : String : 
	* valeur à passer à this.prComptage.<br/>
	*/
	void setPrComptage(String pPrComptage);


	
	/**
	 * Getter de l'abscisse du point de comptage.
	 *
	 * @return this.absComptage : String.<br/>
	 */
	String getAbsComptage();

	
	
	/**
	* Setter de l'abscisse du point de comptage.
	*
	* @param pAbsComptage : String : 
	* valeur à passer à this.absComptage.<br/>
	*/
	void setAbsComptage(String pAbsComptage);


	
	/**
	 * Getter de la longueur de la section en mètres.
	 *
	 * @return this.longueurSection : String.<br/>
	 */
	String getLongueurSection();


	
	/**
	* Setter de la longueur de la section en mètres.
	*
	* @param pLongueurSection : String : 
	* valeur à passer à this.longueurSection.<br/>
	*/
	void setLongueurSection(String pLongueurSection);


	
	/**
	 * Getter de la longueur en rase campagne en mètres.
	 *
	 * @return this.longueurRaseCampagne : String.<br/>
	 */
	String getLongueurRaseCampagne();


	
	/**
	* Setter de la longueur en rase campagne en mètres.
	*
	* @param pLongueurRaseCampagne : String : 
	* valeur à passer à this.longueurRaseCampagne.<br/>
	*/
	void setLongueurRaseCampagne(String pLongueurRaseCampagne);


	
	/**
	 * Getter du numéro de département de la section de Rattachement.
	 *
	 * @return this.numDepartementRattachement : String.<br/>
	 */
	String getNumDepartementRattachement();


	
	/**
	* Setter du numéro de département de la section de Rattachement.
	*
	* @param pNumDepartementRattachement : String : 
	* valeur à passer à this.numDepartementRattachement.<br/>
	*/
	void setNumDepartementRattachement(String pNumDepartementRattachement);


	
	/**
	 * Getter du numéro de la section de Rattachement.
	 *
	 * @return this.numSectionRattachement : String.<br/>
	 */
	String getNumSectionRattachement();


	
	/**
	* Setter du numéro de la section de Rattachement.
	*
	* @param pNumSectionRattachement : String : 
	* valeur à passer à this.numSectionRattachement.<br/>
	*/
	void setNumSectionRattachement(String pNumSectionRattachement);


	
	/**
	 * Getter du sens de la section de Rattachement.
	 *
	 * @return this.sensRattachement : String.<br/>
	 */
	String getSensRattachement();


	
	/**
	* Setter du sens de la section de Rattachement.
	*
	* @param pSensRattachement : String : 
	* valeur à passer à this.sensRattachement.<br/>
	*/
	void setSensRattachement(String pSensRattachement);


	
	/**
	 * Getter du numéro de département de la section Limitrophe.
	 *
	 * @return this.numDepartementLimitrophe : String.<br/>
	 */
	String getNumDepartementLimitrophe();


	
	/**
	* Setter du numéro de département de la section Limitrophe.
	*
	* @param pNumDepartementLimitrophe : String : 
	* valeur à passer à this.numDepartementLimitrophe.<br/>
	*/
	void setNumDepartementLimitrophe(String pNumDepartementLimitrophe);


	
	/**
	 * Getter du numéro de la section Limitrophe.
	 *
	 * @return this.numSectionLimitrophe : String.<br/>
	 */
	String getNumSectionLimitrophe();


	
	/**
	* Setter du numéro de la section Limitrophe.
	*
	* @param pNumSectionLimitrophe : String : 
	* valeur à passer à this.numSectionLimitrophe.<br/>
	*/
	void setNumSectionLimitrophe(String pNumSectionLimitrophe);


	
	/**
	 * Getter du sens de la section Limitrophe.
	 *
	 * @return this.sensLimitrophe : String.<br/>
	 */
	String getSensLimitrophe();


	
	/**
	* Setter du sens de la section Limitrophe.
	*
	* @param pSensLimitrophe : String : 
	* valeur à passer à this.sensLimitrophe.<br/>
	*/
	void setSensLimitrophe(String pSensLimitrophe);

	
	
	/**
	 * Getter du mois de sectionnement.
	 *
	 * @return this.moisSectionnement : String.<br/>
	 */
	String getMoisSectionnement();

	
	
	/**
	* Setter du mois de sectionnement.
	*
	* @param pMoisSectionnement : String : 
	* valeur à passer à this.moisSectionnement.<br/>
	*/
	void setMoisSectionnement(String pMoisSectionnement);


	
	/**
	 * Getter de l'annee de sectionnement.
	 *
	 * @return this.anneeSectionnement : String.<br/>
	 */
	String getAnneeSectionnement();

	
	
	/**
	* Setter de l'annee de sectionnement.
	*
	* @param pAnneeSectionnement : String : 
	* valeur à passer à this.anneeSectionnement.<br/>
	*/
	void setAnneeSectionnement(String pAnneeSectionnement);


	
	/**
	 * Getter de la zone libre 2.
	 *
	 * @return this.zoneLibre2 : String.<br/>
	 */
	String getZoneLibre2();


	
	/**
	* Setter de la zone libre 2.
	*
	* @param pZoneLibre2 : String : 
	* valeur à passer à this.zoneLibre2.<br/>
	*/
	void setZoneLibre2(String pZoneLibre2);


	
	/**
	 * Getter du trafic moyen journalier annuel de l'année de traitement N.
	 *
	 * @return this.mjaN : String.<br/>
	 */
	String getMjaN();


	
	/**
	* Setter du trafic moyen journalier annuel de l'année de traitement N.
	*
	* @param pMjaN : String : 
	* valeur à passer à this.mjaN.<br/>
	*/
	void setMjaN(String pMjaN);


	
	/**
	 * Getter du mode de calcul des trafics.
	 *
	 * @return this.modeCalcul : String.<br/>
	 */
	String getModeCalcul();


	
	/**
	* Setter du mode de calcul des trafics.
	*
	* @param pModeCalcul : String : 
	* valeur à passer à this.modeCalcul.<br/>
	*/
	void setModeCalcul(String pModeCalcul);


	
	/**
	 * Getter du pourcentage de trafic poids lourds annuel 
	 * de l'année de traitement N.
	 *
	 * @return this.pcPLN : String.<br/>
	 */
	String getPcPLN();
	

	
	/**
	* Setter du pourcentage de trafic poids lourds annuel 
	* de l'année de traitement N.
	*
	* @param pPcPLN : String : 
	* valeur à passer à this.pcPLN.<br/>
	*/
	void setPcPLN(String pPcPLN);


	
	/**
	 * Getter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année de traitement N.
	 *
	 * @return this.evaluationPLN : String.<br/>
	 */
	String getEvaluationPLN();


	
	/**
	* Setter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	* de l'année de traitement N.
	*
	* @param pEvaluationPLN : String : 
	* valeur à passer à this.evaluationPLN.<br/>
	*/
	void setEvaluationPLN(String pEvaluationPLN);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année de traitement N.
	 *
	 * @return this.pcNuitNannuel : String.<br/>
	 */
	String getPcNuitNannuel();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules annuel 
	* de l'année de traitement N.
	*
	* @param pPcNuitNannuel : String : 
	* valeur à passer à this.pcNuitNannuel.<br/>
	*/
	void setPcNuitNannuel(String pPcNuitNannuel);


	
	/**
	 * Getter de l'indice de fiabilité de la TMJAN.
	 *
	 * @return this.indiceFiabiliteMjaN : String.<br/>
	 */
	String getIndiceFiabiliteMjaN();


	
	/**
	* Setter de l'indice de fiabilité de la TMJAN.
	*
	* @param pIndiceFiabiliteMjaN : String : 
	* valeur à passer à this.indiceFiabiliteMjaN.<br/>
	*/
	void setIndiceFiabiliteMjaN(String pIndiceFiabiliteMjaN);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de janvier (01) de l'année de traitement N.
	 *
	 * @return this.mjmNmois01 : String.<br/>
	 */
	String getMjmNmois01();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de janvier (01) de l'année de traitement N.
	*
	* @param pMjmNmois01 : String : 
	* valeur à passer à this.mjmNmois01.<br/>
	*/
	void setMjmNmois01(String pMjmNmois01);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de janvier (01) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois01 : String.<br/>
	 */
	String getPcNuitNmois01();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de janvier (01) de l'année de traitement N.
	*
	* @param pPcNuitNmois01 : String : 
	* valeur à passer à this.pcNuitNmois01.<br/>
	*/
	void setPcNuitNmois01(String pPcNuitNmois01);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de février (02) de l'année de traitement N.
	 *
	 * @return this.mjmNmois02 : String.<br/>
	 */
	String getMjmNmois02();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de février (02) de l'année de traitement N.
	*
	* @param pMjmNmois02 : String : 
	* valeur à passer à this.mjmNmois02.<br/>
	*/
	void setMjmNmois02(String pMjmNmois02);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de février (02) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois02 : String.<br/>
	 */
	String getPcNuitNmois02();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de février (02) de l'année de traitement N.
	*
	* @param pPcNuitNmois02 : String : 
	* valeur à passer à this.pcNuitNmois02.<br/>
	*/
	void setPcNuitNmois02(String pPcNuitNmois02);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de mars (03) de l'année de traitement N.
	 *
	 * @return this.mjmNmois03 : String.<br/>
	 */
	String getMjmNmois03();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de mars (03) de l'année de traitement N.
	*
	* @param pMjmNmois03 : String : 
	* valeur à passer à this.mjmNmois03.<br/>
	*/
	void setMjmNmois03(String pMjmNmois03);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de mars (03) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois03 : String.<br/>
	 */
	String getPcNuitNmois03();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de mars (03) de l'année de traitement N.
	*
	* @param pPcNuitNmois03 : String : 
	* valeur à passer à this.pcNuitNmois03.<br/>
	*/
	void setPcNuitNmois03(String pPcNuitNmois03);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de avril (04) de l'année de traitement N.
	 *
	 * @return this.mjmNmois04 : String.<br/>
	 */
	String getMjmNmois04();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de avril (04) de l'année de traitement N.
	*
	* @param pMjmNmois04 : String : 
	* valeur à passer à this.mjmNmois04.<br/>
	*/
	void setMjmNmois04(String pMjmNmois04);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de avril (04) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois04 : String.<br/>
	 */
	String getPcNuitNmois04();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de avril (04) de l'année de traitement N.
	*
	* @param pPcNuitNmois04 : String : 
	* valeur à passer à this.pcNuitNmois04.<br/>
	*/
	void setPcNuitNmois04(String pPcNuitNmois04);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de mai (05) de l'année de traitement N.
	 *
	 * @return this.mjmNmois05 : String.<br/>
	 */
	String getMjmNmois05();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de mai (05) de l'année de traitement N.
	*
	* @param pMjmNmois05 : String : 
	* valeur à passer à this.mjmNmois05.<br/>
	*/
	void setMjmNmois05(String pMjmNmois05);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de mai (05) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois05 : String.<br/>
	 */
	String getPcNuitNmois05();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de mai (05) de l'année de traitement N.
	*
	* @param pPcNuitNmois05 : String : 
	* valeur à passer à this.pcNuitNmois05.<br/>
	*/
	void setPcNuitNmois05(String pPcNuitNmois05);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de juin (06) de l'année de traitement N.
	 *
	 * @return this.mjmNmois06 : String.<br/>
	 */
	String getMjmNmois06();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de juin (06) de l'année de traitement N.
	*
	* @param pMjmNmois06 : String : 
	* valeur à passer à this.mjmNmois06.<br/>
	*/
	void setMjmNmois06(String pMjmNmois06);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de juin (06) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois06 : String.<br/>
	 */
	String getPcNuitNmois06();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de juin (06) de l'année de traitement N.
	*
	* @param pPcNuitNmois06 : String : 
	* valeur à passer à this.pcNuitNmois06.<br/>
	*/
	void setPcNuitNmois06(String pPcNuitNmois06);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de juillet (07) de l'année de traitement N.
	 *
	 * @return this.mjmNmois07 : String.<br/>
	 */
	String getMjmNmois07();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de juillet (07) de l'année de traitement N.
	*
	* @param pMjmNmois07 : String : 
	* valeur à passer à this.mjmNmois07.<br/>
	*/
	void setMjmNmois07(String pMjmNmois07);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de juillet (07) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois07 : String.<br/>
	 */
	String getPcNuitNmois07();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de juillet (07) de l'année de traitement N.
	*
	* @param pPcNuitNmois07 : String : 
	* valeur à passer à this.pcNuitNmois07.<br/>
	*/
	void setPcNuitNmois07(String pPcNuitNmois07);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de août (08) de l'année de traitement N.
	 *
	 * @return this.mjmNmois08 : String.<br/>
	 */
	String getMjmNmois08();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de août (08) de l'année de traitement N.
	*
	* @param pMjmNmois08 : String : 
	* valeur à passer à this.mjmNmois08.<br/>
	*/
	void setMjmNmois08(String pMjmNmois08);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de août (08) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois08 : String.<br/>
	 */
	String getPcNuitNmois08();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de août (08) de l'année de traitement N.
	*
	* @param pPcNuitNmois08 : String : 
	* valeur à passer à this.pcNuitNmois08.<br/>
	*/
	void setPcNuitNmois08(String pPcNuitNmois08);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de septembre (09) de l'année de traitement N.
	 *
	 * @return this.mjmNmois09 : String.<br/>
	 */
	String getMjmNmois09();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de septembre (09) de l'année de traitement N.
	*
	* @param pMjmNmois09 : String : 
	* valeur à passer à this.mjmNmois09.<br/>
	*/
	void setMjmNmois09(String pMjmNmois09);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de septembre (09) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois09 : String.<br/>
	 */
	String getPcNuitNmois09();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de septembre (09) de l'année de traitement N.
	*
	* @param pPcNuitNmois09 : String : 
	* valeur à passer à this.pcNuitNmois09.<br/>
	*/
	void setPcNuitNmois09(String pPcNuitNmois09);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de octobre (10) de l'année de traitement N.
	 *
	 * @return this.mjmNmois10 : String.<br/>
	 */
	String getMjmNmois10();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de octobre (10) de l'année de traitement N.
	*
	* @param pMjmNmois10 : String : 
	* valeur à passer à this.mjmNmois10.<br/>
	*/
	void setMjmNmois10(String pMjmNmois10);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de octobre (10) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois10 : String.<br/>
	 */
	String getPcNuitNmois10();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de octobre (10) de l'année de traitement N.
	*
	* @param pPcNuitNmois10 : String : 
	* valeur à passer à this.pcNuitNmois10.<br/>
	*/
	void setPcNuitNmois10(String pPcNuitNmois10);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de novembre (11) de l'année de traitement N.
	 *
	 * @return this.mjmNmois11 : String.<br/>
	 */
	String getMjmNmois11();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de novembre (11) de l'année de traitement N.
	*
	* @param pMjmNmois11 : String : 
	* valeur à passer à this.mjmNmois11.<br/>
	*/
	void setMjmNmois11(String pMjmNmois11);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de novembre (11) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois11 : String.<br/>
	 */
	String getPcNuitNmois11();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de novembre (11) de l'année de traitement N.
	*
	* @param pPcNuitNmois11 : String : 
	* valeur à passer à this.pcNuitNmois11.<br/>
	*/
	void setPcNuitNmois11(String pPcNuitNmois11);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de décembre (12) de l'année de traitement N.
	 *
	 * @return this.mjmNmois12 : String.<br/>
	 */
	String getMjmNmois12();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de décembre (12) de l'année de traitement N.
	*
	* @param pMjmNmois12 : String : 
	* valeur à passer à this.mjmNmois12.<br/>
	*/
	void setMjmNmois12(String pMjmNmois12);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de décembre (12) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois12 : String.<br/>
	 */
	String getPcNuitNmois12();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de décembre (12) de l'année de traitement N.
	*
	* @param pPcNuitNmois12 : String : 
	* valeur à passer à this.pcNuitNmois12.<br/>
	*/
	void setPcNuitNmois12(String pPcNuitNmois12);


	
	/**
	 * Getter de la zone libre 3.
	 *
	 * @return this.zoneLibre3 : String.<br/>
	 */
	String getZoneLibre3();


	
	/**
	* Setter de la zone libre 3.
	*
	* @param pZoneLibre3 : String : 
	* valeur à passer à this.zoneLibre3.<br/>
	*/
	void setZoneLibre3(String pZoneLibre3);
	
	

} // FIN DE L'INTERFACE ISectionHitDTO.--------------------------------------
