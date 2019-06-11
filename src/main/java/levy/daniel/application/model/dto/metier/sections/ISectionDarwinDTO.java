package levy.daniel.application.model.dto.metier.sections;

import java.io.Serializable;

import levy.daniel.application.model.dto.metier.sections.impl.SectionDarwinDTO;
import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;

/**
 * INTERFACE ISectionDarwinDTO :<br/>
 * Interface factorisant les comportements des {@link SectionDarwinDTO}.<br/>
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
public interface ISectionDarwinDTO extends Comparable<ISectionDarwinDTO>
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
	int compareTo(ISectionDarwinDTO pObjet);



	/**
	 * clone.<br/>
	 *
	 * @return ISectionDarwinDTO
	 * 
	 * @throws CloneNotSupportedException
	 */
	ISectionDarwinDTO clone() throws CloneNotSupportedException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();

	
	
	/**
	 * retourne le DTO sous forme ASCII, 
	 * c'est à dire avec tous les champs concaténés sans séparateur.<br/>
	 * <br/>
	 * par exemple :<br/>
	 * "860000900310017 00100 411 251CHAUNAY EX RN 10    1000110DEPARTEMENT 79
	 *       1070118CHAUNAY             1060"<br/>
	 * <br/>
	 * - ne met pas l'ID en base.<br/>
	 * - ne met pas de saut de ligne à la fin.<br/>
	 * <br/>
	 *
	 * @return : String :  .<br/>
	 */
	String toStringASCII();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ISectionDarwinDTO</b> :<br/>
	 * "id;numDepartement;numSection;sens;nature;classe;anneeTraitement;
	 * zoneLibre1;
	 * numRoute;indiceNumRoute;indiceLettreRoute;categorieAdminRoute;
	 * typeComptage;classementRoute;classeLargeurChausseeU;
	 * classeLargeurChausseesS;typeReseau;pRoupK;
	 * lieuDitOrigine;prOrigine;absOrigine;
	 * lieuDitExtremite;prExtremite;absExtremite;
	 * lieuDitComptage;prComptage;absComptage;
	 * longueurSection;longueurRaseCampagne;
	 * numDepartementRattachement;numSectionRattachement;sensRattachement;
	 * numDepartementLimitrophe;numSectionLimitrophe;sensLimitrophe;
	 * moisSectionnement;anneeSectionnement;
	 * zoneLibre2;
	 * mjaN;modeCalculN;pcPLN;evaluationPLN;pcNuitAnnuelN;indiceFiabiliteMjaN;
	 * mjmNmois01;pcNuitNmois01;mjmNmois02;pcNuitNmois02;
	 * mjmNmois03;pcNuitNmois03;mjmNmois04;pcNuitNmois04;
	 * mjmNmois05;pcNuitNmois05;mjmNmois06;pcNuitNmois06;
	 * mjmNmois07;pcNuitNmois07;mjmNmois08;pcNuitNmois08
	 * ;mjmNmois09;pcNuitNmois09;mjmNmois10;pcNuitNmois10;
	 * mjmNmois11;pcNuitNmois11;mjmNmois12;pcNuitNmois12;
	 * zoneLibre3;
	 * anneeNmoins1;mjaNmoins1;typeComptageNmoins1;modeCalculNmoins1;
	 * pcPLNmoins1;evaluationPLNmoins1;pcNuitAnnuelNmoins1;
	 * indiceFiabiliteMjaNmoins1;
	 * anneeNmoins2;mjaNmoins2;typeComptageNmoins2;modeCalculNmoins2;
	 * pcPLNmoins2;evaluationPLNmoins2;pcNuitAnnuelNmoins2;
	 * indiceFiabiliteMjaNmoins2;
	 * anneeNmoins3;mjaNmoins3;typeComptageNmoins3;modeCalculNmoins3;
	 * pcPLNmoins3;evaluationPLNmoins3;pcNuitAnnuelNmoins3;
	 * indiceFiabiliteMjaNmoins3;
	 * anneeNmoins4;mjaNmoins4;typeComptageNmoins4;modeCalculNmoins4;
	 * pcPLNmoins4;evaluationPLNmoins4;pcNuitAnnuelNmoins4;
	 * indiceFiabiliteMjaNmoins4;
	 * anneeNmoins5;mjaNmoins5;typeComptageNmoins5;modeCalculNmoins5;
	 * pcPLNmoins5;evaluationPLNmoins5;pcNuitAnnuelNmoins5;
	 * indiceFiabiliteMjaNmoins5;
	 * mjmNmoins1mois01;pcNuitNmoins1mois01;
	 * mjmNmoins1mois02;pcNuitNmoins1mois02;
	 * mjmNmoins1mois03;pcNuitNmoins1mois03;
	 * mjmNmoins1mois04;pcNuitNmoins1mois04;
	 * mjmNmoins1mois05;pcNuitNmoins1mois05;
	 * mjmNmoins1mois06;pcNuitNmoins1mois06;
	 * mjmNmoins1mois07;pcNuitNmoins1mois07;
	 * mjmNmoins1mois08;pcNuitNmoins1mois08;
	 * mjmNmoins1mois09;pcNuitNmoins1mois09
	 * ;mjmNmoins1mois10;pcNuitNmoins1mois10;
	 * mjmNmoins1mois11;pcNuitNmoins1mois11;
	 * mjmNmoins1mois12;pcNuitNmoins1mois12;
	 * zoneLibre4;".<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ISectionDarwinDTO</b> :<br/>
	 * "id;numDepartement;numSection;sens;nature;classe;anneeTraitement;
	 * zoneLibre1;
	 * numRoute;indiceNumRoute;indiceLettreRoute;categorieAdminRoute;
	 * typeComptage;classementRoute;classeLargeurChausseeU;
	 * classeLargeurChausseesS;typeReseau;pRoupK;
	 * lieuDitOrigine;prOrigine;absOrigine;
	 * lieuDitExtremite;prExtremite;absExtremite;
	 * lieuDitComptage;prComptage;absComptage;
	 * longueurSection;longueurRaseCampagne;
	 * numDepartementRattachement;numSectionRattachement;sensRattachement;
	 * numDepartementLimitrophe;numSectionLimitrophe;sensLimitrophe;
	 * moisSectionnement;anneeSectionnement;
	 * zoneLibre2;
	 * mjaN;modeCalculN;pcPLN;evaluationPLN;pcNuitAnnuelN;indiceFiabiliteMjaN;
	 * mjmNmois01;pcNuitNmois01;mjmNmois02;pcNuitNmois02;
	 * mjmNmois03;pcNuitNmois03;mjmNmois04;pcNuitNmois04;
	 * mjmNmois05;pcNuitNmois05;mjmNmois06;pcNuitNmois06;
	 * mjmNmois07;pcNuitNmois07;mjmNmois08;pcNuitNmois08
	 * ;mjmNmois09;pcNuitNmois09;mjmNmois10;pcNuitNmois10;
	 * mjmNmois11;pcNuitNmois11;mjmNmois12;pcNuitNmois12;
	 * zoneLibre3;
	 * anneeNmoins1;mjaNmoins1;typeComptageNmoins1;modeCalculNmoins1;
	 * pcPLNmoins1;evaluationPLNmoins1;pcNuitAnnuelNmoins1;
	 * indiceFiabiliteMjaNmoins1;
	 * anneeNmoins2;mjaNmoins2;typeComptageNmoins2;modeCalculNmoins2;
	 * pcPLNmoins2;evaluationPLNmoins2;pcNuitAnnuelNmoins2;
	 * indiceFiabiliteMjaNmoins2;
	 * anneeNmoins3;mjaNmoins3;typeComptageNmoins3;modeCalculNmoins3;
	 * pcPLNmoins3;evaluationPLNmoins3;pcNuitAnnuelNmoins3;
	 * indiceFiabiliteMjaNmoins3;
	 * anneeNmoins4;mjaNmoins4;typeComptageNmoins4;modeCalculNmoins4;
	 * pcPLNmoins4;evaluationPLNmoins4;pcNuitAnnuelNmoins4;
	 * indiceFiabiliteMjaNmoins4;
	 * anneeNmoins5;mjaNmoins5;typeComptageNmoins5;modeCalculNmoins5;
	 * pcPLNmoins5;evaluationPLNmoins5;pcNuitAnnuelNmoins5;
	 * indiceFiabiliteMjaNmoins5;
	 * mjmNmoins1mois01;pcNuitNmoins1mois01;
	 * mjmNmoins1mois02;pcNuitNmoins1mois02;
	 * mjmNmoins1mois03;pcNuitNmoins1mois03;
	 * mjmNmoins1mois04;pcNuitNmoins1mois04;
	 * mjmNmoins1mois05;pcNuitNmoins1mois05;
	 * mjmNmoins1mois06;pcNuitNmoins1mois06;
	 * mjmNmoins1mois07;pcNuitNmoins1mois07;
	 * mjmNmoins1mois08;pcNuitNmoins1mois08;
	 * mjmNmoins1mois09;pcNuitNmoins1mois09
	 * ;mjmNmoins1mois10;pcNuitNmoins1mois10;
	 * mjmNmoins1mois11;pcNuitNmoins1mois11;
	 * mjmNmoins1mois12;pcNuitNmoins1mois12;
	 * zoneLibre4;".<br/>
	 * <br/>
	 */
	@Override
	String fournirStringCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ISectionDarwinDTO</b> :<br/>
	 * "id;numDepartement;numSection;sens;nature;classe;anneeTraitement;
	 * zoneLibre1;
	 * numRoute;indiceNumRoute;indiceLettreRoute;categorieAdminRoute;
	 * typeComptage;classementRoute;classeLargeurChausseeU;
	 * classeLargeurChausseesS;typeReseau;pRoupK;
	 * lieuDitOrigine;prOrigine;absOrigine;
	 * lieuDitExtremite;prExtremite;absExtremite;
	 * lieuDitComptage;prComptage;absComptage;
	 * longueurSection;longueurRaseCampagne;
	 * numDepartementRattachement;numSectionRattachement;sensRattachement;
	 * numDepartementLimitrophe;numSectionLimitrophe;sensLimitrophe;
	 * moisSectionnement;anneeSectionnement;
	 * zoneLibre2;
	 * mjaN;modeCalculN;pcPLN;evaluationPLN;pcNuitAnnuelN;indiceFiabiliteMjaN;
	 * mjmNmois01;pcNuitNmois01;mjmNmois02;pcNuitNmois02;
	 * mjmNmois03;pcNuitNmois03;mjmNmois04;pcNuitNmois04;
	 * mjmNmois05;pcNuitNmois05;mjmNmois06;pcNuitNmois06;
	 * mjmNmois07;pcNuitNmois07;mjmNmois08;pcNuitNmois08
	 * ;mjmNmois09;pcNuitNmois09;mjmNmois10;pcNuitNmois10;
	 * mjmNmois11;pcNuitNmois11;mjmNmois12;pcNuitNmois12;
	 * zoneLibre3;
	 * anneeNmoins1;mjaNmoins1;typeComptageNmoins1;modeCalculNmoins1;
	 * pcPLNmoins1;evaluationPLNmoins1;pcNuitAnnuelNmoins1;
	 * indiceFiabiliteMjaNmoins1;
	 * anneeNmoins2;mjaNmoins2;typeComptageNmoins2;modeCalculNmoins2;
	 * pcPLNmoins2;evaluationPLNmoins2;pcNuitAnnuelNmoins2;
	 * indiceFiabiliteMjaNmoins2;
	 * anneeNmoins3;mjaNmoins3;typeComptageNmoins3;modeCalculNmoins3;
	 * pcPLNmoins3;evaluationPLNmoins3;pcNuitAnnuelNmoins3;
	 * indiceFiabiliteMjaNmoins3;
	 * anneeNmoins4;mjaNmoins4;typeComptageNmoins4;modeCalculNmoins4;
	 * pcPLNmoins4;evaluationPLNmoins4;pcNuitAnnuelNmoins4;
	 * indiceFiabiliteMjaNmoins4;
	 * anneeNmoins5;mjaNmoins5;typeComptageNmoins5;modeCalculNmoins5;
	 * pcPLNmoins5;evaluationPLNmoins5;pcNuitAnnuelNmoins5;
	 * indiceFiabiliteMjaNmoins5;
	 * mjmNmoins1mois01;pcNuitNmoins1mois01;
	 * mjmNmoins1mois02;pcNuitNmoins1mois02;
	 * mjmNmoins1mois03;pcNuitNmoins1mois03;
	 * mjmNmoins1mois04;pcNuitNmoins1mois04;
	 * mjmNmoins1mois05;pcNuitNmoins1mois05;
	 * mjmNmoins1mois06;pcNuitNmoins1mois06;
	 * mjmNmoins1mois07;pcNuitNmoins1mois07;
	 * mjmNmoins1mois08;pcNuitNmoins1mois08;
	 * mjmNmoins1mois09;pcNuitNmoins1mois09
	 * ;mjmNmoins1mois10;pcNuitNmoins1mois10;
	 * mjmNmoins1mois11;pcNuitNmoins1mois11;
	 * mjmNmoins1mois12;pcNuitNmoins1mois12;
	 * zoneLibre4;".<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteColonne(int pI);



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un ISectionDarwinDTO</b> :<br/>
	 * "id;numDepartement;numSection;sens;nature;classe;anneeTraitement;
	 * zoneLibre1;
	 * numRoute;indiceNumRoute;indiceLettreRoute;categorieAdminRoute;
	 * typeComptage;classementRoute;classeLargeurChausseeU;
	 * classeLargeurChausseesS;typeReseau;pRoupK;
	 * lieuDitOrigine;prOrigine;absOrigine;
	 * lieuDitExtremite;prExtremite;absExtremite;
	 * lieuDitComptage;prComptage;absComptage;
	 * longueurSection;longueurRaseCampagne;
	 * numDepartementRattachement;numSectionRattachement;sensRattachement;
	 * numDepartementLimitrophe;numSectionLimitrophe;sensLimitrophe;
	 * moisSectionnement;anneeSectionnement;
	 * zoneLibre2;
	 * mjaN;modeCalculN;pcPLN;evaluationPLN;pcNuitAnnuelN;indiceFiabiliteMjaN;
	 * mjmNmois01;pcNuitNmois01;mjmNmois02;pcNuitNmois02;
	 * mjmNmois03;pcNuitNmois03;mjmNmois04;pcNuitNmois04;
	 * mjmNmois05;pcNuitNmois05;mjmNmois06;pcNuitNmois06;
	 * mjmNmois07;pcNuitNmois07;mjmNmois08;pcNuitNmois08
	 * ;mjmNmois09;pcNuitNmois09;mjmNmois10;pcNuitNmois10;
	 * mjmNmois11;pcNuitNmois11;mjmNmois12;pcNuitNmois12;
	 * zoneLibre3;
	 * anneeNmoins1;mjaNmoins1;typeComptageNmoins1;modeCalculNmoins1;
	 * pcPLNmoins1;evaluationPLNmoins1;pcNuitAnnuelNmoins1;
	 * indiceFiabiliteMjaNmoins1;
	 * anneeNmoins2;mjaNmoins2;typeComptageNmoins2;modeCalculNmoins2;
	 * pcPLNmoins2;evaluationPLNmoins2;pcNuitAnnuelNmoins2;
	 * indiceFiabiliteMjaNmoins2;
	 * anneeNmoins3;mjaNmoins3;typeComptageNmoins3;modeCalculNmoins3;
	 * pcPLNmoins3;evaluationPLNmoins3;pcNuitAnnuelNmoins3;
	 * indiceFiabiliteMjaNmoins3;
	 * anneeNmoins4;mjaNmoins4;typeComptageNmoins4;modeCalculNmoins4;
	 * pcPLNmoins4;evaluationPLNmoins4;pcNuitAnnuelNmoins4;
	 * indiceFiabiliteMjaNmoins4;
	 * anneeNmoins5;mjaNmoins5;typeComptageNmoins5;modeCalculNmoins5;
	 * pcPLNmoins5;evaluationPLNmoins5;pcNuitAnnuelNmoins5;
	 * indiceFiabiliteMjaNmoins5;
	 * mjmNmoins1mois01;pcNuitNmoins1mois01;
	 * mjmNmoins1mois02;pcNuitNmoins1mois02;
	 * mjmNmoins1mois03;pcNuitNmoins1mois03;
	 * mjmNmoins1mois04;pcNuitNmoins1mois04;
	 * mjmNmoins1mois05;pcNuitNmoins1mois05;
	 * mjmNmoins1mois06;pcNuitNmoins1mois06;
	 * mjmNmoins1mois07;pcNuitNmoins1mois07;
	 * mjmNmoins1mois08;pcNuitNmoins1mois08;
	 * mjmNmoins1mois09;pcNuitNmoins1mois09
	 * ;mjmNmoins1mois10;pcNuitNmoins1mois10;
	 * mjmNmoins1mois11;pcNuitNmoins1mois11;
	 * mjmNmoins1mois12;pcNuitNmoins1mois12;
	 * zoneLibre4;".<br/>
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
	 * Getter de l'id en base sous forme de String.
	 *
	 * @return this.objetID : String.<br/>
	 */
	String getObjetID();



	/**
	* Setter de l'id en base sous forme de String.
	*
	* @param pObjetID : String : 
	* valeur à passer à this.objetID.<br/>
	*/
	void setObjetID(String pObjetID);


	
	/**
	 * Getter de la route au format FEOR (A0034b1 ou A0006).
	 *
	 * @return this.route : String.<br/>
	 */
	String getRoute();


	
	/**
	* Setter de la route au format FEOR (A0034b1 ou A0006).
	*
	* @param pRoute : String : 
	* valeur à passer à this.route.<br/>
	*/
	void setRoute(String pRoute);


	
	/**
	 * Getter du département du PR DEBUT au format FEOR (01, 30, 75, 971, ...).
	 *
	 * @return this.depPrD : String.<br/>
	 */
	String getDepPrD();


	
	/**
	* Setter du département du PR DEBUT au format FEOR (01, 30, 75, 971, ...).
	*
	* @param pDepPrD : String : 
	* valeur à passer à this.depPrD.<br/>
	*/
	void setDepPrD(String pDepPrD);



	/**
	 * Getter du code concession au PR DEBUT.
	 *
	 * @return this.concessionPrD : String.<br/>
	 */
	String getConcessionPrD();


	
	/**
	* Setter du code concession au PR DEBUT.
	*
	* @param pConcessionPrD : String : 
	* valeur à passer à this.concessionPrD.<br/>
	*/
	void setConcessionPrD(String pConcessionPrD);


	
	/**
	 * Getter du PR DEBUT.
	 *
	 * @return this.prD : String.<br/>
	 */
	String getPrD();


	
	/**
	* Setter du PR DEBUT.
	*
	* @param pPrD : String : 
	* valeur à passer à this.prD.<br/>
	*/
	void setPrD(String pPrD);


	
	/**
	 * Getter de l'abscisse du PR DEBUT.
	 *
	 * @return this.absD : String.<br/>
	 */
	String getAbsD();


	
	/**
	* Setter de l'abscisse du PR DEBUT.
	*
	* @param pAbsD : String : 
	* valeur à passer à this.absD.<br/>
	*/
	void setAbsD(String pAbsD);


	
	/**
	 * Getter du département du PR FIN au format FEOR (01, 30, 75, 971, ...).
	 *
	 * @return this.depPrF : String.<br/>
	 */
	String getDepPrF();


	
	/**
	* Setter du département du PR FIN au format FEOR (01, 30, 75, 971, ...).
	*
	* @param pDepPrF : String : 
	* valeur à passer à this.depPrF.<br/>
	*/
	void setDepPrF(String pDepPrF);



	/**
	 * Getter du code concession au PR FIN.
	 *
	 * @return this.concessionPrF : String.<br/>
	 */
	String getConcessionPrF();


	
	/**
	* Setter du code concession au PR FIN.
	*
	* @param pConcessionPrF : String : 
	* valeur à passer à this.concessionPrF.<br/>
	*/
	void setConcessionPrF(String pConcessionPrF);


	
	/**
	 * Getter du PR FIN.
	 *
	 * @return this.prF : String.<br/>
	 */
	String getPrF();


	
	/**
	* Setter du PR FIN.
	*
	* @param pPrF : String : 
	* valeur à passer à this.prF.<br/>
	*/
	void setPrF(String pPrF);


	
	/**
	 * Getter de l'abscisse du PR FIN.
	 *
	 * @return this.absF : String.<br/>
	 */
	String getAbsF();


	
	/**
	* Setter de l'abscisse du PR FIN.
	*
	* @param pAbsF : String : 
	* valeur à passer à this.absF.<br/>
	*/
	void setAbsF(String pAbsF);


	
	/**
	 * Getter de l'année de mesure du trafic (par exemple '2019').
	 *
	 * @return this.anneeMesureTrafic : String.<br/>
	 */
	String getAnneeMesureTrafic();


	
	/**
	* Setter de l'année de mesure du trafic (par exemple '2019').
	*
	* @param pAnneeMesureTrafic : String : 
	* valeur à passer à this.anneeMesureTrafic.<br/>
	*/
	void setAnneeMesureTrafic(String pAnneeMesureTrafic);


	
	/**
	 * Getter du numéro DARWIN de la section de trafic.
	 *
	 * @return this.numSectionTrafic : String.<br/>
	 */
	String getNumSectionTrafic();


	
	/**
	* Setter du numéro DARWIN de la section de trafic.
	*
	* @param pNumSectionTrafic : String : 
	* valeur à passer à this.numSectionTrafic.<br/>
	*/
	void setNumSectionTrafic(String pNumSectionTrafic);


	
	/**
	 * Getter du sens de la section de trafic.
	 *
	 * @return this.sensSectionTrafic : String.<br/>
	 */
	String getSensSectionTrafic();



	/**
	* Setter du sens de la section de trafic.
	*
	* @param pSensSectionTrafic : String : 
	* valeur à passer à this.sensSectionTrafic.<br/>
	*/
	void setSensSectionTrafic(String pSensSectionTrafic);


	
	/**
	 * Getter du type de comptage de la section de trafic.
	 *
	 * @return this.typeComptageTrafic : String.<br/>
	 */
	String getTypeComptageTrafic();


	
	/**
	* Setter du type de comptage de la section de trafic.
	*
	* @param pTypeComptageTrafic : String : 
	* valeur à passer à this.typeComptageTrafic.<br/>
	*/
	void setTypeComptageTrafic(String pTypeComptageTrafic);


	
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
	 * Getter du pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année de traitement N.
	 *
	 * @return this.pcNuitAnnuelN : String.<br/>
	 */
	String getPcNuitAnnuelN();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules annuel 
	* de l'année de traitement N.
	*
	* @param pPcNuitAnnuelN : String : 
	* valeur à passer à this.pcNuitAnnuelN.<br/>
	*/
	void setPcNuitAnnuelN(String pPcNuitAnnuelN);


	
	/**
	 * Getter du trafic moyen journalier annuel de l'année n-1.
	 *
	 * @return this.mjaNmoins1 : String.<br/>
	 */
	String getMjaNmoins1();


	
	/**
	* Setter du trafic moyen journalier annuel de l'année n-1.
	*
	* @param pMjaNmoins1 : String : 
	* valeur à passer à this.mjaNmoins1.<br/>
	*/
	void setMjaNmoins1(String pMjaNmoins1);


	
	/**
	 * Getter du pourcentage de trafic poids lourds annuel de l'année n-1.
	 *
	 * @return this.pcPLNmoins1 : String.<br/>
	 */
	String getPcPLNmoins1();


	
	/**
	* Setter du pourcentage de trafic poids lourds annuel de l'année n-1.
	*
	* @param pPcPLNmoins1 : String : 
	* valeur à passer à this.pcPLNmoins1.<br/>
	*/
	void setPcPLNmoins1(String pPcPLNmoins1);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-1.
	 *
	 * @return this.pcNuitAnnuelNmoins1 : String.<br/>
	 */
	String getPcNuitAnnuelNmoins1();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules annuel 
	* de l'année n-1.
	*
	* @param pPcNuitAnnuelNmoins1 : String : 
	* valeur à passer à this.pcNuitAnnuelNmoins1.<br/>
	*/
	void setPcNuitAnnuelNmoins1(String pPcNuitAnnuelNmoins1);


	
	/**
	 * Getter du trafic moyen journalier annuel de l'année n-2.
	 *
	 * @return this.mjaNmoins2 : String.<br/>
	 */
	String getMjaNmoins2();


	
	/**
	* Setter du trafic moyen journalier annuel de l'année n-2.
	*
	* @param pMjaNmoins2 : String : 
	* valeur à passer à this.mjaNmoins2.<br/>
	*/
	void setMjaNmoins2(String pMjaNmoins2);


	
	/**
	 * Getter du pourcentage de trafic poids lourds annuel de l'année n-2.
	 *
	 * @return this.pcPLNmoins2 : String.<br/>
	 */
	String getPcPLNmoins2();


	
	/**
	* Setter du pourcentage de trafic poids lourds annuel de l'année n-2.
	*
	* @param pPcPLNmoins2 : String : 
	* valeur à passer à this.pcPLNmoins2.<br/>
	*/
	void setPcPLNmoins2(String pPcPLNmoins2);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-2.
	 *
	 * @return this.pcNuitAnnuelNmoins2 : String.<br/>
	 */
	String getPcNuitAnnuelNmoins2();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules annuel 
	* de l'année n-2.
	*
	* @param pPcNuitAnnuelNmoins2 : String : 
	* valeur à passer à this.pcNuitAnnuelNmoins2.<br/>
	*/
	void setPcNuitAnnuelNmoins2(String pPcNuitAnnuelNmoins2);


	
	/**
	 * Getter du trafic moyen journalier annuel de l'année n-3.
	 *
	 * @return this.mjaNmoins3 : String.<br/>
	 */
	String getMjaNmoins3();


	
	/**
	* Setter du trafic moyen journalier annuel de l'année n-3.
	*
	* @param pMjaNmoins3 : String : 
	* valeur à passer à this.mjaNmoins3.<br/>
	*/
	void setMjaNmoins3(String pMjaNmoins3);


	
	/**
	 * Getter du pourcentage de trafic poids lourds annuel de l'année n-3.
	 *
	 * @return this.pcPLNmoins3 : String.<br/>
	 */
	String getPcPLNmoins3();


	
	/**
	* Setter du pourcentage de trafic poids lourds annuel de l'année n-3.
	*
	* @param pPcPLNmoins3 : String : 
	* valeur à passer à this.pcPLNmoins3.<br/>
	*/
	void setPcPLNmoins3(String pPcPLNmoins3);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-3.
	 *
	 * @return this.pcNuitAnnuelNmoins3 : String.<br/>
	 */
	String getPcNuitAnnuelNmoins3();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules annuel 
	* de l'année n-3.
	*
	* @param pPcNuitAnnuelNmoins3 : String : 
	* valeur à passer à this.pcNuitAnnuelNmoins3.<br/>
	*/
	void setPcNuitAnnuelNmoins3(String pPcNuitAnnuelNmoins3);


	
	/**
	 * Getter du trafic moyen journalier annuel de l'année n-4.
	 *
	 * @return this.mjaNmoins4 : String.<br/>
	 */
	String getMjaNmoins4();


	
	/**
	* Setter du trafic moyen journalier annuel de l'année n-4.
	*
	* @param pMjaNmoins4 : String : 
	* valeur à passer à this.mjaNmoins4.<br/>
	*/
	void setMjaNmoins4(String pMjaNmoins4);


	
	/**
	 * Getter du pourcentage de trafic poids lourds annuel de l'année n-4.
	 *
	 * @return this.pcPLNmoins4 : String.<br/>
	 */
	String getPcPLNmoins4();


	
	/**
	* Setter du pourcentage de trafic poids lourds annuel de l'année n-4.
	*
	* @param pPcPLNmoins4 : String : 
	* valeur à passer à this.pcPLNmoins4.<br/>
	*/
	void setPcPLNmoins4(String pPcPLNmoins4);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-4.
	 *
	 * @return this.pcNuitAnnuelNmoins4 : String.<br/>
	 */
	String getPcNuitAnnuelNmoins4();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules annuel 
	* de l'année n-4.
	*
	* @param pPcNuitAnnuelNmoins4 : String : 
	* valeur à passer à this.pcNuitAnnuelNmoins4.<br/>
	*/
	void setPcNuitAnnuelNmoins4(String pPcNuitAnnuelNmoins4);


	
	/**
	 * Getter du trafic moyen journalier annuel de l'année n-5.
	 *
	 * @return this.mjaNmoins5 : String.<br/>
	 */
	String getMjaNmoins5();


	
	/**
	* Setter du trafic moyen journalier annuel de l'année n-5.
	*
	* @param pMjaNmoins5 : String : 
	* valeur à passer à this.mjaNmoins5.<br/>
	*/
	void setMjaNmoins5(String pMjaNmoins5);


	
	/**
	 * Getter du pourcentage de trafic poids lourds annuel de l'année n-5.
	 *
	 * @return this.pcPLNmoins5 : String.<br/>
	 */
	String getPcPLNmoins5();


	
	/**
	* Setter du pourcentage de trafic poids lourds annuel de l'année n-5.
	*
	* @param pPcPLNmoins5 : String : 
	* valeur à passer à this.pcPLNmoins5.<br/>
	*/
	void setPcPLNmoins5(String pPcPLNmoins5);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-5.
	 *
	 * @return this.pcNuitAnnuelNmoins5 : String.<br/>
	 */
	String getPcNuitAnnuelNmoins5();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules annuel 
	* de l'année n-5.
	*
	* @param pPcNuitAnnuelNmoins5 : String : 
	* valeur à passer à this.pcNuitAnnuelNmoins5.<br/>
	*/
	void setPcNuitAnnuelNmoins5(String pPcNuitAnnuelNmoins5);


	
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
	 * Getter du classement de la route (agglomération).
	 *
	 * @return this.classementRoute : String.<br/>
	 */
	String getClassementRoute();


	
	/**
	* Setter du classement de la route (agglomération).
	*
	* @param pClassementRoute : String : 
	* valeur à passer à this.classementRoute.<br/>
	*/
	void setClassementRoute(String pClassementRoute);


	
	/**
	 * Getter du profil en travers à la codification SICRE 
	 * ('1V' pour chaussée unique 1 voie, ...).
	 *
	 * @return this.profilTraversSicre : String.<br/>
	 */
	String getProfilTraversSicre();


	
	/**
	* Setter du profil en travers à la codification SICRE 
	* ('1V' pour chaussée unique 1 voie, ...).
	*
	* @param pProfilTraversSicre : String : 
	* valeur à passer à this.profilTraversSicre.<br/>
	*/
	void setProfilTraversSicre(String pProfilTraversSicre);


	
	/**
	 * Getter du libellé du lieu-dit ORIGINE (DEBUT) de la section.
	 *
	 * @return this.origineSection : String.<br/>
	 */
	String getOrigineSection();


	
	/**
	* Setter du libellé du lieu-dit ORIGINE (DEBUT) de la section.
	*
	* @param pOrigineSection : String : 
	* valeur à passer à this.origineSection.<br/>
	*/
	void setOrigineSection(String pOrigineSection);


	
	/**
	 * Getter du libellé du lieu-dit EXTEMITE (FIN) de la section.
	 *
	 * @return this.extremiteSection : String.<br/>
	 */
	String getExtremiteSection();


	
	/**
	* Setter du libellé du lieu-dit EXTEMITE (FIN) de la section.
	*
	* @param pExtremiteSection : String : 
	* valeur à passer à this.extremiteSection.<br/>
	*/
	void setExtremiteSection(String pExtremiteSection);


	
	/**
	 * Getter du libellé du lieu-dit du point de comptage de la section.
	 *
	 * @return this.lieuDitComptage : String.<br/>
	 */
	String getLieuDitComptage();


	
	/**
	* Setter du libellé du lieu-dit du point de comptage de la section.
	*
	* @param pLieuDitComptage : String : 
	* valeur à passer à this.lieuDitComptage.<br/>
	*/
	void setLieuDitComptage(String pLieuDitComptage);


	
	/**
	 * Getter du PR du point de comptage de la section.
	 *
	 * @return this.prComptage : String.<br/>
	 */
	String getPrComptage();


	
	/**
	* Setter du PR du point de comptage de la section.
	*
	* @param pPrComptage : String : 
	* valeur à passer à this.prComptage.<br/>
	*/
	void setPrComptage(String pPrComptage);


	
	/**
	 * Getter de l'abscisse du point de comptage de la section.
	 *
	 * @return this.absComptage : String.<br/>
	 */
	String getAbsComptage();


	
	/**
	* Setter de l'abscisse du point de comptage de la section.
	*
	* @param pAbsComptage : String : 
	* valeur à passer à this.absComptage.<br/>
	*/
	void setAbsComptage(String pAbsComptage);


	
	/**
	 * Getter de l'itinéraire européen passant par la section de trafic.
	 *
	 * @return this.itiEuropeen1 : String.<br/>
	 */
	String getItiEuropeen1();


	
	/**
	* Setter de l'itinéraire européen passant par la section de trafic.
	*
	* @param pItiEuropeen1 : String : 
	* valeur à passer à this.itiEuropeen1.<br/>
	*/
	void setItiEuropeen1(String pItiEuropeen1);


	
	/**
	 * Getter du sous-réseau défini pour l'Indice National de Circulation.
	 *
	 * @return this.sousReseauIndice : String.<br/>
	 */
	String getSousReseauIndice();


	
	/**
	* Setter du sous-réseau défini pour l'Indice National de Circulation.
	*
	* @param pSousReseauIndice : String : 
	* valeur à passer à this.sousReseauIndice.<br/>
	*/
	void setSousReseauIndice(String pSousReseauIndice);

	
	
} // FIN DE L'INTERFACE ISectionDarwinDTO.-----------------------------------
