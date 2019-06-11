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
	 * <b>enTete CSV pour un ISectionHitDTO</b> :<br/>
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
	 * <b>enTete CSV pour un ISectionHitDTO</b> :<br/>
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
	 * <b>enTete CSV pour un ISectionHitDTO</b> :<br/>
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
	 * <b>enTete CSV pour un ISectionHitDTO</b> :<br/>
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
	String getPRoupK();


	
	/**
	* Setter du type de localisation (PR ou PK).
	*
	* @param pPRoupK : String : 
	* valeur à passer à this.pRoupK.<br/>
	*/
	void setPRoupK(String pPRoupK);

	
	
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
	 * Getter du mode de calcul des trafics de l'année de traitement N.
	 *
	 * @return this.modeCalculN : String.<br/>
	 */
	String getModeCalculN();


	
	/**
	* Setter du mode de calcul des trafics de l'année de traitement N.
	*
	* @param pModeCalculN : String : 
	* valeur à passer à this.modeCalculN.<br/>
	*/
	void setModeCalculN(String pModeCalculN);


	
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
	 * Getter de l'indice de fiabilité de la TMJA de l'année de traitement N.
	 *
	 * @return this.indiceFiabiliteMjaN : String.<br/>
	 */
	String getIndiceFiabiliteMjaN();


	
	/**
	* Setter de l'indice de fiabilité de la TMJA de l'année de traitement N.
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


	
	/**
	 * Getter de l'année n-1.
	 *
	 * @return this.anneeNmoins1 : String.<br/>
	 */
	String getAnneeNmoins1();
	

	
	/**
	* Setter de l'année n-1.
	*
	* @param pAnneeNmoins1 : String : 
	* valeur à passer à this.anneeNmoins1.<br/>
	*/
	void setAnneeNmoins1(String pAnneeNmoins1);


	
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
	 * Getter du type de comptage de l'année n-1.
	 *
	 * @return this.typeComptageNmoins1 : String.<br/>
	 */
	String getTypeComptageNmoins1();


	
	/**
	* Setter du type de comptage de l'année n-1.
	*
	* @param pTypeComptageNmoins1 : String : 
	* valeur à passer à this.typeComptageNmoins1.<br/>
	*/
	void setTypeComptageNmoins1(String pTypeComptageNmoins1);


	
	/**
	 * Getter du mode de calcul des trafics de l'année n-1.
	 *
	 * @return this.modeCalculNmoins1 : String.<br/>
	 */
	String getModeCalculNmoins1();


	
	/**
	* Setter du mode de calcul des trafics de l'année n-1.
	*
	* @param pModeCalculNmoins1 : String : 
	* valeur à passer à this.modeCalculNmoins1.<br/>
	*/
	void setModeCalculNmoins1(String pModeCalculNmoins1);


	
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
	 * Getter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année n-1.
	 *
	 * @return this.evaluationPLNmoins1 : String.<br/>
	 */
	String getEvaluationPLNmoins1();


	
	/**
	* Setter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	* de l'année n-1.
	*
	* @param pEvaluationPLNmoins1 : String : 
	* valeur à passer à this.evaluationPLNmoins1.<br/>
	*/
	void setEvaluationPLNmoins1(String pEvaluationPLNmoins1);

	
	
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
	 * Getter de l'indice de fiabilité de la TMJA 
	 * de l'année n-1.
	 *
	 * @return this.indiceFiabiliteMjaNmoins1 : String.<br/>
	 */
	String getIndiceFiabiliteMjaNmoins1();


	
	/**
	* Setter de l'indice de fiabilité de la TMJA 
	* de l'année n-1.
	*
	* @param pIndiceFiabiliteMjaNmoins1 : String : 
	* valeur à passer à this.indiceFiabiliteMjaNmoins1.<br/>
	*/
	void setIndiceFiabiliteMjaNmoins1(String pIndiceFiabiliteMjaNmoins1);


	
	/**
	 * Getter de l'année n-2.
	 *
	 * @return this.anneeNmoins2 : String.<br/>
	 */
	String getAnneeNmoins2();
	

	
	/**
	* Setter de l'année n-2.
	*
	* @param pAnneeNmoins2 : String : 
	* valeur à passer à this.anneeNmoins2.<br/>
	*/
	void setAnneeNmoins2(String pAnneeNmoins2);


	
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
	 * Getter du type de comptage de l'année n-2.
	 *
	 * @return this.typeComptageNmoins2 : String.<br/>
	 */
	String getTypeComptageNmoins2();


	
	/**
	* Setter du type de comptage de l'année n-2.
	*
	* @param pTypeComptageNmoins2 : String : 
	* valeur à passer à this.typeComptageNmoins2.<br/>
	*/
	void setTypeComptageNmoins2(String pTypeComptageNmoins2);


	
	/**
	 * Getter du mode de calcul des trafics de l'année n-2.
	 *
	 * @return this.modeCalculNmoins2 : String.<br/>
	 */
	String getModeCalculNmoins2();


	
	/**
	* Setter du mode de calcul des trafics de l'année n-2.
	*
	* @param pModeCalculNmoins2 : String : 
	* valeur à passer à this.modeCalculNmoins2.<br/>
	*/
	void setModeCalculNmoins2(String pModeCalculNmoins2);


	
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
	 * Getter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année n-2.
	 *
	 * @return this.evaluationPLNmoins2 : String.<br/>
	 */
	String getEvaluationPLNmoins2();


	
	/**
	* Setter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	* de l'année n-2.
	*
	* @param pEvaluationPLNmoins2 : String : 
	* valeur à passer à this.evaluationPLNmoins2.<br/>
	*/
	void setEvaluationPLNmoins2(String pEvaluationPLNmoins2);

	
	
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
	 * Getter de l'indice de fiabilité de la TMJA 
	 * de l'année n-2.
	 *
	 * @return this.indiceFiabiliteMjaNmoins2 : String.<br/>
	 */
	String getIndiceFiabiliteMjaNmoins2();


	
	/**
	* Setter de l'indice de fiabilité de la TMJA 
	* de l'année n-2.
	*
	* @param pIndiceFiabiliteMjaNmoins2 : String : 
	* valeur à passer à this.indiceFiabiliteMjaNmoins2.<br/>
	*/
	void setIndiceFiabiliteMjaNmoins2(String pIndiceFiabiliteMjaNmoins2);


	
	/**
	 * Getter de l'année n-3.
	 *
	 * @return this.anneeNmoins3 : String.<br/>
	 */
	String getAnneeNmoins3();
	

	
	/**
	* Setter de l'année n-3.
	*
	* @param pAnneeNmoins3 : String : 
	* valeur à passer à this.anneeNmoins3.<br/>
	*/
	void setAnneeNmoins3(String pAnneeNmoins3);


	
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
	 * Getter du type de comptage de l'année n-3.
	 *
	 * @return this.typeComptageNmoins3 : String.<br/>
	 */
	String getTypeComptageNmoins3();


	
	/**
	* Setter du type de comptage de l'année n-3.
	*
	* @param pTypeComptageNmoins3 : String : 
	* valeur à passer à this.typeComptageNmoins3.<br/>
	*/
	void setTypeComptageNmoins3(String pTypeComptageNmoins3);


	
	/**
	 * Getter du mode de calcul des trafics de l'année n-3.
	 *
	 * @return this.modeCalculNmoins3 : String.<br/>
	 */
	String getModeCalculNmoins3();


	
	/**
	* Setter du mode de calcul des trafics de l'année n-3.
	*
	* @param pModeCalculNmoins3 : String : 
	* valeur à passer à this.modeCalculNmoins3.<br/>
	*/
	void setModeCalculNmoins3(String pModeCalculNmoins3);


	
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
	 * Getter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année n-3.
	 *
	 * @return this.evaluationPLNmoins3 : String.<br/>
	 */
	String getEvaluationPLNmoins3();


	
	/**
	* Setter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	* de l'année n-3.
	*
	* @param pEvaluationPLNmoins3 : String : 
	* valeur à passer à this.evaluationPLNmoins3.<br/>
	*/
	void setEvaluationPLNmoins3(String pEvaluationPLNmoins3);

	
	
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
	 * Getter de l'indice de fiabilité de la TMJA 
	 * de l'année n-3.
	 *
	 * @return this.indiceFiabiliteMjaNmoins3 : String.<br/>
	 */
	String getIndiceFiabiliteMjaNmoins3();


	
	/**
	* Setter de l'indice de fiabilité de la TMJA 
	* de l'année n-3.
	*
	* @param pIndiceFiabiliteMjaNmoins3 : String : 
	* valeur à passer à this.indiceFiabiliteMjaNmoins3.<br/>
	*/
	void setIndiceFiabiliteMjaNmoins3(String pIndiceFiabiliteMjaNmoins3);


	
	/**
	 * Getter de l'année n-4.
	 *
	 * @return this.anneeNmoins4 : String.<br/>
	 */
	String getAnneeNmoins4();
	

	
	/**
	* Setter de l'année n-4.
	*
	* @param pAnneeNmoins4 : String : 
	* valeur à passer à this.anneeNmoins4.<br/>
	*/
	void setAnneeNmoins4(String pAnneeNmoins4);


	
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
	 * Getter du type de comptage de l'année n-4.
	 *
	 * @return this.typeComptageNmoins4 : String.<br/>
	 */
	String getTypeComptageNmoins4();


	
	/**
	* Setter du type de comptage de l'année n-4.
	*
	* @param pTypeComptageNmoins4 : String : 
	* valeur à passer à this.typeComptageNmoins4.<br/>
	*/
	void setTypeComptageNmoins4(String pTypeComptageNmoins4);


	
	/**
	 * Getter du mode de calcul des trafics de l'année n-4.
	 *
	 * @return this.modeCalculNmoins4 : String.<br/>
	 */
	String getModeCalculNmoins4();


	
	/**
	* Setter du mode de calcul des trafics de l'année n-4.
	*
	* @param pModeCalculNmoins4 : String : 
	* valeur à passer à this.modeCalculNmoins4.<br/>
	*/
	void setModeCalculNmoins4(String pModeCalculNmoins4);


	
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
	 * Getter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année n-4.
	 *
	 * @return this.evaluationPLNmoins4 : String.<br/>
	 */
	String getEvaluationPLNmoins4();


	
	/**
	* Setter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	* de l'année n-4.
	*
	* @param pEvaluationPLNmoins4 : String : 
	* valeur à passer à this.evaluationPLNmoins4.<br/>
	*/
	void setEvaluationPLNmoins4(String pEvaluationPLNmoins4);

	
	
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
	 * Getter de l'indice de fiabilité de la TMJA 
	 * de l'année n-4.
	 *
	 * @return this.indiceFiabiliteMjaNmoins4 : String.<br/>
	 */
	String getIndiceFiabiliteMjaNmoins4();


	
	/**
	* Setter de l'indice de fiabilité de la TMJA 
	* de l'année n-4.
	*
	* @param pIndiceFiabiliteMjaNmoins4 : String : 
	* valeur à passer à this.indiceFiabiliteMjaNmoins4.<br/>
	*/
	void setIndiceFiabiliteMjaNmoins4(String pIndiceFiabiliteMjaNmoins4);


	
	/**
	 * Getter de l'année n-5.
	 *
	 * @return this.anneeNmoins5 : String.<br/>
	 */
	String getAnneeNmoins5();
	

	
	/**
	* Setter de l'année n-5.
	*
	* @param pAnneeNmoins5 : String : 
	* valeur à passer à this.anneeNmoins5.<br/>
	*/
	void setAnneeNmoins5(String pAnneeNmoins5);


	
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
	 * Getter du type de comptage de l'année n-5.
	 *
	 * @return this.typeComptageNmoins5 : String.<br/>
	 */
	String getTypeComptageNmoins5();


	
	/**
	* Setter du type de comptage de l'année n-5.
	*
	* @param pTypeComptageNmoins5 : String : 
	* valeur à passer à this.typeComptageNmoins5.<br/>
	*/
	void setTypeComptageNmoins5(String pTypeComptageNmoins5);


	
	/**
	 * Getter du mode de calcul des trafics de l'année n-5.
	 *
	 * @return this.modeCalculNmoins5 : String.<br/>
	 */
	String getModeCalculNmoins5();


	
	/**
	* Setter du mode de calcul des trafics de l'année n-5.
	*
	* @param pModeCalculNmoins5 : String : 
	* valeur à passer à this.modeCalculNmoins5.<br/>
	*/
	void setModeCalculNmoins5(String pModeCalculNmoins5);


	
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
	 * Getter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année n-5.
	 *
	 * @return this.evaluationPLNmoins5 : String.<br/>
	 */
	String getEvaluationPLNmoins5();


	
	/**
	* Setter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	* de l'année n-5.
	*
	* @param pEvaluationPLNmoins5 : String : 
	* valeur à passer à this.evaluationPLNmoins5.<br/>
	*/
	void setEvaluationPLNmoins5(String pEvaluationPLNmoins5);

	
	
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
	 * Getter de l'indice de fiabilité de la TMJA 
	 * de l'année n-5.
	 *
	 * @return this.indiceFiabiliteMjaNmoins5 : String.<br/>
	 */
	String getIndiceFiabiliteMjaNmoins5();


	
	/**
	* Setter de l'indice de fiabilité de la TMJA 
	* de l'année n-5.
	*
	* @param pIndiceFiabiliteMjaNmoins5 : String : 
	* valeur à passer à this.indiceFiabiliteMjaNmoins5.<br/>
	*/
	void setIndiceFiabiliteMjaNmoins5(String pIndiceFiabiliteMjaNmoins5);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de janvier (01) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois01 : String.<br/>
	 */
	String getMjmNmoins1mois01();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de janvier (01) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois01 : String : 
	* valeur à passer à this.mjmNmoins1mois01.<br/>
	*/
	void setMjmNmoins1mois01(String pMjmNmoins1mois01);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de janvier (01) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois01 : String.<br/>
	 */
	String getPcNuitNmoins1mois01();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de janvier (01) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois01 : String : 
	* valeur à passer à this.pcNuitNmoins1mois01.<br/>
	*/
	void setPcNuitNmoins1mois01(String pPcNuitNmoins1mois01);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de février (02) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois02 : String.<br/>
	 */
	String getMjmNmoins1mois02();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de février (02) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois02 : String : 
	* valeur à passer à this.mjmNmoins1mois02.<br/>
	*/
	void setMjmNmoins1mois02(String pMjmNmoins1mois02);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de février (02) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois02 : String.<br/>
	 */
	String getPcNuitNmoins1mois02();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de février (02) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois02 : String : 
	* valeur à passer à this.pcNuitNmoins1mois02.<br/>
	*/
	void setPcNuitNmoins1mois02(String pPcNuitNmoins1mois02);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de mars (03) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois03 : String.<br/>
	 */
	String getMjmNmoins1mois03();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de mars (03) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois03 : String : 
	* valeur à passer à this.mjmNmoins1mois03.<br/>
	*/
	void setMjmNmoins1mois03(String pMjmNmoins1mois03);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de mars (03) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois03 : String.<br/>
	 */
	String getPcNuitNmoins1mois03();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de mars (03) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois03 : String : 
	* valeur à passer à this.pcNuitNmoins1mois03.<br/>
	*/
	void setPcNuitNmoins1mois03(String pPcNuitNmoins1mois03);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de avril (04) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois04 : String.<br/>
	 */
	String getMjmNmoins1mois04();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de avril (04) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois04 : String : 
	* valeur à passer à this.mjmNmoins1mois04.<br/>
	*/
	void setMjmNmoins1mois04(String pMjmNmoins1mois04);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de avril (04) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois04 : String.<br/>
	 */
	String getPcNuitNmoins1mois04();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de avril (04) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois04 : String : 
	* valeur à passer à this.pcNuitNmoins1mois04.<br/>
	*/
	void setPcNuitNmoins1mois04(String pPcNuitNmoins1mois04);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de mai (05) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois05 : String.<br/>
	 */
	String getMjmNmoins1mois05();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de mai (05) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois05 : String : 
	* valeur à passer à this.mjmNmoins1mois05.<br/>
	*/
	void setMjmNmoins1mois05(String pMjmNmoins1mois05);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de mai (05) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois05 : String.<br/>
	 */
	String getPcNuitNmoins1mois05();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de mai (05) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois05 : String : 
	* valeur à passer à this.pcNuitNmoins1mois05.<br/>
	*/
	void setPcNuitNmoins1mois05(String pPcNuitNmoins1mois05);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de juin (06) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois06 : String.<br/>
	 */
	String getMjmNmoins1mois06();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de juin (06) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois06 : String : 
	* valeur à passer à this.mjmNmoins1mois06.<br/>
	*/
	void setMjmNmoins1mois06(String pMjmNmoins1mois06);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de juin (06) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois06 : String.<br/>
	 */
	String getPcNuitNmoins1mois06();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de juin (06) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois06 : String : 
	* valeur à passer à this.pcNuitNmoins1mois06.<br/>
	*/
	void setPcNuitNmoins1mois06(String pPcNuitNmoins1mois06);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de juillet (07) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois07 : String.<br/>
	 */
	String getMjmNmoins1mois07();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de juillet (07) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois07 : String : 
	* valeur à passer à this.mjmNmoins1mois07.<br/>
	*/
	void setMjmNmoins1mois07(String pMjmNmoins1mois07);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de juillet (07) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois07 : String.<br/>
	 */
	String getPcNuitNmoins1mois07();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de juillet (07) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois07 : String : 
	* valeur à passer à this.pcNuitNmoins1mois07.<br/>
	*/
	void setPcNuitNmoins1mois07(String pPcNuitNmoins1mois07);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de août (08) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois08 : String.<br/>
	 */
	String getMjmNmoins1mois08();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de août (08) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois08 : String : 
	* valeur à passer à this.mjmNmoins1mois08.<br/>
	*/
	void setMjmNmoins1mois08(String pMjmNmoins1mois08);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de août (08) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois08 : String.<br/>
	 */
	String getPcNuitNmoins1mois08();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de août (08) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois08 : String : 
	* valeur à passer à this.pcNuitNmoins1mois08.<br/>
	*/
	void setPcNuitNmoins1mois08(String pPcNuitNmoins1mois08);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de septembre (09) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois09 : String.<br/>
	 */
	String getMjmNmoins1mois09();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de septembre (09) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois09 : String : 
	* valeur à passer à this.mjmNmoins1mois09.<br/>
	*/
	void setMjmNmoins1mois09(String pMjmNmoins1mois09);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de septembre (09) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois09 : String.<br/>
	 */
	String getPcNuitNmoins1mois09();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de septembre (09) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois09 : String : 
	* valeur à passer à this.pcNuitNmoins1mois09.<br/>
	*/
	void setPcNuitNmoins1mois09(String pPcNuitNmoins1mois09);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de octobre (10) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois10 : String.<br/>
	 */
	String getMjmNmoins1mois10();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de octobre (10) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois10 : String : 
	* valeur à passer à this.mjmNmoins1mois10.<br/>
	*/
	void setMjmNmoins1mois10(String pMjmNmoins1mois10);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de octobre (10) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois10 : String.<br/>
	 */
	String getPcNuitNmoins1mois10();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de octobre (10) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois10 : String : 
	* valeur à passer à this.pcNuitNmoins1mois10.<br/>
	*/
	void setPcNuitNmoins1mois10(String pPcNuitNmoins1mois10);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de novembre (11) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois11 : String.<br/>
	 */
	String getMjmNmoins1mois11();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de novembre (11) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois11 : String : 
	* valeur à passer à this.mjmNmoins1mois11.<br/>
	*/
	void setMjmNmoins1mois11(String pMjmNmoins1mois11);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de novembre (11) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois11 : String.<br/>
	 */
	String getPcNuitNmoins1mois11();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de novembre (11) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois11 : String : 
	* valeur à passer à this.pcNuitNmoins1mois11.<br/>
	*/
	void setPcNuitNmoins1mois11(String pPcNuitNmoins1mois11);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de décembre (12) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois12 : String.<br/>
	 */
	String getMjmNmoins1mois12();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de décembre (12) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois12 : String : 
	* valeur à passer à this.mjmNmoins1mois12.<br/>
	*/
	void setMjmNmoins1mois12(String pMjmNmoins1mois12);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de décembre (12) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois12 : String.<br/>
	 */
	String getPcNuitNmoins1mois12();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de décembre (12) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois12 : String : 
	* valeur à passer à this.pcNuitNmoins1mois12.<br/>
	*/
	void setPcNuitNmoins1mois12(String pPcNuitNmoins1mois12);


	
	/**
	 * Getter de la zone libre 4.
	 *
	 * @return this.zoneLibre4 : String.<br/>
	 */
	String getZoneLibre4();


	
	/**
	* Setter de la zone libre 4.
	*
	* @param pZoneLibre4 : String : 
	* valeur à passer à this.zoneLibre4.<br/>
	*/
	void setZoneLibre4(String pZoneLibre4);
	
	

} // FIN DE L'INTERFACE ISectionHitDTO.--------------------------------------
