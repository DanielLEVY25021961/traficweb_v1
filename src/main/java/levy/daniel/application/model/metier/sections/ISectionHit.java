package levy.daniel.application.model.metier.sections;

import java.io.Serializable;
import java.time.LocalDate;

import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;
import levy.daniel.application.model.metier.sections.localisations.ILocalisationHit;

/**
 * INTERFACE ISectionHit :<br/>
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
 * @since 29 juin 2019
 *
 */
public interface ISectionHit extends Comparable<ISectionHit>
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
	 * {@inheritDoc}<br/>
	 * <br/>
	 * <b>comparaison en fonction de :
	 * <ol>
	 * <li>anneeTraitement</li>
	 * <li>numDepartement</li>
	 * <li>numRoute</li>
	 * <li>indiceNumRoute</li>
	 * <li>indiceLettreRoute</li>
	 * <li>categorieAdminRoute</li>
	 * <li>prOrigine</li>
	 * <li>absOrigine</li>
	 * </ol>
	 * pour un ISectionHit</b><br/>
	 * <br/>
	 */
	@Override
	int compareTo(ISectionHit pObjet);



	/**
	 * clone.<br/>
	 *
	 * @return ISectionHit
	 * 
	 * @throws CloneNotSupportedException
	 */
	ISectionHit clone() throws CloneNotSupportedException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();

	
	
	/**
	 * retourne l'OBJET METIER sous forme ASCII, 
	 * c'est à dire avec tous les champs concaténés sans séparateur.<br/>
	 * <ul>
	 * <li>complète avec des zéros à gauche les attributs Integer 
	 * (mjaX, mjmX, ...) pour obtenir la longueur indiquée 
	 * dans la description de fichier.</li>
	 * <li><b>reconstitue une ligne HIT</b>.</li>
	 * </ul>
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
	 * <b>enTete CSV pour un ISectionHit</b> :<br/>
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
	 * <b>enTete CSV pour un ISectionHit</b> :<br/>
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
	 * <b>enTete CSV pour un ISectionHit</b> :<br/>
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
	 * <b>enTete CSV pour un ISectionHit</b> :<br/>
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
	 * Getter de l'ID en base (sous forme de Long).
	 *
	 * @return this.id : Long.<br/>
	 */
	Long getId();

	
	
	/**
	* Setter de l'ID en base (sous forme de Long).
	*
	* @param pId : Long : 
	* valeur à passer à this.id.
	*/
	void setId(Long pId);


	
	/**
	 * Getter du numéro de département.
	 *
	 * @return this.numDepartement : String.
	 */
	String getNumDepartement();
	

	
	/**
	* Setter du numéro de département.
	* <br/>
	 * passe pNumDepartement au COMPOSANT <code><b>this.localisation</b></code>
	*
	* @param pNumDepartement : String : 
	* valeur à passer à this.numDepartement.<br/>
	*/
	void setNumDepartement(String pNumDepartement);


	
	/**
	 * Getter du numéro de section.
	 *
	 * @return this.numSection : String.
	 */
	String getNumSection();


	
	/**
	* Setter du numéro de section.
	*
	* @param pNumSection : String : 
	* valeur à passer à this.numSection.
	*/
	void setNumSection(String pNumSection);


	
	/**
	 * Getter du sens de trafic.
	 *
	 * @return this.sens : String.
	 */
	String getSens();


	
	/**
	* Setter du sens de trafic.
	*
	* @param pSens : String : 
	* valeur à passer à this.sens.
	*/
	void setSens(String pSens);


	
	/**
	 * Getter de la nature du comptage.
	 *
	 * @return this.nature : String.
	 */
	String getNature();


	
	/**
	* Setter de la nature du comptage.
	*
	* @param pNature : String : 
	* valeur à passer à this.nature.
	*/
	void setNature(String pNature);


	
	/**
	 * Getter de la classe du trafic.
	 *
	 * @return this.classe : String.
	 */
	String getClasse();


	
	/**
	* Setter de la classe du trafic.
	*
	* @param pClasse : String : 
	* valeur à passer à this.classe.
	*/
	void setClasse(String pClasse);


	
	/**
	 * Getter de l'année de traitement.
	 *
	 * @return this.anneeTraitement : java.time.LocalDate.
	 */
	LocalDate getAnneeTraitement();


	
	/**
	* Setter de l'année de traitement.
	*
	* @param pAnneeTraitement : java.time.LocalDate : 
	* valeur à passer à this.anneeTraitement.
	*/
	void setAnneeTraitement(LocalDate pAnneeTraitement);


	
	/**
	 * Getter de la zone libre 1.
	 *
	 * @return this.zoneLibre1 : String.
	 */
	String getZoneLibre1();

	
	
	/**
	* Setter de la zone libre 1.
	*
	* @param pZoneLibre1 : String : 
	* valeur à passer à this.zoneLibre1.
	*/
	void setZoneLibre1(String pZoneLibre1);


	
	/**
	 * Getter du numero de la route.
	 *
	 * @return this.numRoute : String.
	 */
	String getNumRoute();


	
	/**
	* Setter du numero de la route.
	* <br/>
	* passe pNumRoute au COMPOSANT <code><b>this.localisation</b></code>
	*
	* @param pNumRoute : String : 
	* valeur à passer à this.numRoute.
	*/
	void setNumRoute(String pNumRoute);


	
	/**
	 * Getter de l'indice numérique de la route.
	 *
	 * @return this.indiceNumRoute : String.
	 */
	String getIndiceNumRoute();


	
	/**
	* Setter de l'indice numérique de la route.
	* <br/>
	* passe pIndiceNumRoute au COMPOSANT <code><b>this.localisation</b></code>
	*
	* @param pIndiceNumRoute : String : 
	* valeur à passer à this.indiceNumRoute.
	*/
	void setIndiceNumRoute(String pIndiceNumRoute);


	
	/**
	 * Getter de l'indice lettre de la route.
	 *
	 * @return this.indiceLettreRoute : String.
	 */
	String getIndiceLettreRoute();


	
	/**
	* Setter de l'indice lettre de la route.
	* <br/>
	* passe pIndiceLettreRoute au COMPOSANT <code><b>this.localisation</b></code>
	*
	* @param pIndiceLettreRoute : String : 
	* valeur à passer à this.indiceLettreRoute.
	*/
	void setIndiceLettreRoute(String pIndiceLettreRoute);


	
	/**
	 * Getter de la catégorie administrative de la route.
	 *
	 * @return this.categorieAdminRoute : String.
	 */
	String getCategorieAdminRoute();


	
	/**
	* Setter de la catégorie administrative de la route.
	* <br/>
	* passe pCategorieAdminRoute au COMPOSANT <code><b>this.localisation</b></code>
	*
	* @param pCategorieAdminRoute : String : 
	* valeur à passer à this.categorieAdminRoute.
	*/
	void setCategorieAdminRoute(String pCategorieAdminRoute);


	
	/**
	 * Getter du type de comptage.
	 *
	 * @return this.typeComptage : String.
	 */
	String getTypeComptage();


	
	/**
	* Setter du type de comptage.
	*
	* @param pTypeComptage : String : 
	* valeur à passer à this.typeComptage.
	*/
	void setTypeComptage(String pTypeComptage);


	
	/**
	 * Getter du classement de la route (agglomération).
	 *
	 * @return this.classementRoute : String.
	 */
	String getClassementRoute();


	
	/**
	* Setter du classement de la route (agglomération).
	*
	* @param pClassementRoute : String : 
	* valeur à passer à this.classementRoute.
	*/
	void setClassementRoute(String pClassementRoute);


	
	/**
	 * Getter de la classe de largeur de chaussée unique.
	 *
	 * @return this.classeLargeurChausseeU : String.
	 */
	String getClasseLargeurChausseeU();


	
	/**
	* Setter de la classe de largeur de chaussée unique.
	*
	* @param pClasseLargeurChausseeU : String : 
	* valeur à passer à this.classeLargeurChausseeU.
	*/
	void setClasseLargeurChausseeU(String pClasseLargeurChausseeU);


	
	/**
	 * Getter de la classe de largeur de chaussées séparées.
	 *
	 * @return this.classeLargeurChausseesS : String.
	 */
	String getClasseLargeurChausseesS();


	
	/**
	* Setter de la classe de largeur de chaussées séparées.
	*
	* @param pClasseLargeurChausseesS : String : 
	* valeur à passer à this.classeLargeurChausseesS.
	*/
	void setClasseLargeurChausseesS(String pClasseLargeurChausseesS);


	
	/**
	 * Getter du type de réseau.
	 *
	 * @return this.typeReseau : String.
	 */
	String getTypeReseau();


	
	/**
	* Setter du type de réseau.
	*
	* @param pTypeReseau : String : 
	* valeur à passer à this.typeReseau.
	*/
	void setTypeReseau(String pTypeReseau);


	
	/**
	 * Getter du type de localisation (PR ou PK).
	 *
	 * @return this.pRoupK : String.
	 */
	String getpRoupK();


	
	/**
	* Setter du type de localisation (PR ou PK).
	*
	* @param pPRoupK : String : 
	* valeur à passer à this.pRoupK.
	*/
	void setpRoupK(String pPRoupK);

	
	
	/**
	 * Getter du libellé du lieu-dit origine.
	 *
	 * @return this.lieuDitOrigine : String.
	 */
	String getLieuDitOrigine();


	
	/**
	* Setter du libellé du lieu-dit origine.
	* <br/>
	* passe pLieuDitOrigine au COMPOSANT <code><b>this.localisation</b></code>
	*
	* @param pLieuDitOrigine : String : 
	* valeur à passer à this.lieuDitOrigine.
	*/
	void setLieuDitOrigine(String pLieuDitOrigine);


	
	/**
	 * Getter du PR Origine.
	 *
	 * @return this.prOrigine : Integer.
	 */
	Integer getPrOrigine();

	
	
	/**
	* Setter du PR Origine.
	* <br/>
	* passe pPrOrigine au COMPOSANT <code><b>this.localisation</b></code>
	*
	* @param pPrOrigine : Integer : 
	* valeur à passer à this.prOrigine.
	*/
	void setPrOrigine(Integer pPrOrigine);


	
	/**
	 * Getter de l'abscisse du point origine.
	 *
	 * @return this.absOrigine : Integer.
	 */
	Integer getAbsOrigine();

	
	
	/**
	* Setter de l'abscisse du point origine.
	* <br/>
	* passe pAbsOrigine au COMPOSANT <code><b>this.localisation</b></code>
	*
	* @param pAbsOrigine : Integer : 
	* valeur à passer à this.absOrigine.
	*/
	void setAbsOrigine(Integer pAbsOrigine);

	
	
	/**
	 * Getter du libellé du lieu-dit extremité.
	 *
	 * @return this.lieuDitExtremite : String.
	 */
	String getLieuDitExtremite();


	
	/**
	* Setter du libellé du lieu-dit extremité.
	* <br/>
	* passe pLieuDitExtremite au COMPOSANT <code><b>this.localisation</b></code>
	*
	* @param pLieuDitExtremite : String : 
	* valeur à passer à this.lieuDitExtremite.
	*/
	void setLieuDitExtremite(String pLieuDitExtremite);


	
	/**
	 * Getter du PR Extremité.
	 *
	 * @return this.prExtremite : Integer.
	 */
	Integer getPrExtremite();

	
	
	/**
	* Setter du PR Extremité.
	* <br/>
	* passe pPrExtremite au COMPOSANT <code><b>this.localisation</b></code>
	*
	* @param pPrExtremite : Integer : 
	* valeur à passer à this.prExtremite.
	*/
	void setPrExtremite(Integer pPrExtremite);


	
	/**
	 * Getter de l'abscisse du point extremité.
	 *
	 * @return this.absExtremite : Integer.
	 */
	Integer getAbsExtremite();

	
	
	/**
	* Setter de l'abscisse du point extremité.
	*
	* @param pAbsExtremite : Integer : 
	* valeur à passer à this.absExtremite.
	*/
	void setAbsExtremite(Integer pAbsExtremite);

	
	
	/**
	 * Getter du libellé du lieu-dit du point de comptage.
	 *
	 * @return this.lieuDitComptage : String.
	 */
	String getLieuDitComptage();


	
	/**
	* Setter du libellé du lieu-dit du point de comptage.
	*
	* @param pLieuDitComptage : String : 
	* valeur à passer à this.lieuDitComptage.
	*/
	void setLieuDitComptage(String pLieuDitComptage);


	
	/**
	 * Getter du PR du point de comptage.
	 *
	 * @return this.prComptage : Integer.
	 */
	Integer getPrComptage();

	
	
	/**
	* Setter du PR du point de comptage.
	*
	* @param pPrComptage : Integer : 
	* valeur à passer à this.prComptage.
	*/
	void setPrComptage(Integer pPrComptage);


	
	/**
	 * Getter de l'abscisse du point de comptage.
	 *
	 * @return this.absComptage : Integer.
	 */
	Integer getAbsComptage();

	
	
	/**
	* Setter de l'abscisse du point de comptage.
	*
	* @param pAbsComptage : Integer : 
	* valeur à passer à this.absComptage.
	*/
	void setAbsComptage(Integer pAbsComptage);


	
	/**
	 * Getter de la longueur de la section en mètres.
	 *
	 * @return this.longueurSection : Integer.
	 */
	Integer getLongueurSection();


	
	/**
	* Setter de la longueur de la section en mètres.
	*
	* @param pLongueurSection : Integer : 
	* valeur à passer à this.longueurSection.
	*/
	void setLongueurSection(Integer pLongueurSection);


	
	/**
	 * Getter de la longueur en rase campagne en mètres.
	 *
	 * @return this.longueurRaseCampagne : Integer.
	 */
	Integer getLongueurRaseCampagne();


	
	/**
	* Setter de la longueur en rase campagne en mètres.
	*
	* @param pLongueurRaseCampagne : Integer : 
	* valeur à passer à this.longueurRaseCampagne.
	*/
	void setLongueurRaseCampagne(Integer pLongueurRaseCampagne);


	
	/**
	 * Getter du numéro de département de la section de Rattachement.
	 *
	 * @return this.numDepartementRattachement : String.
	 */
	String getNumDepartementRattachement();


	
	/**
	* Setter du numéro de département de la section de Rattachement.
	*
	* @param pNumDepartementRattachement : String : 
	* valeur à passer à this.numDepartementRattachement.
	*/
	void setNumDepartementRattachement(String pNumDepartementRattachement);


	
	/**
	 * Getter du numéro de la section de Rattachement.
	 *
	 * @return this.numSectionRattachement : String.
	 */
	String getNumSectionRattachement();


	
	/**
	* Setter du numéro de la section de Rattachement.
	*
	* @param pNumSectionRattachement : String : 
	* valeur à passer à this.numSectionRattachement.
	*/
	void setNumSectionRattachement(String pNumSectionRattachement);


	
	/**
	 * Getter du sens de la section de Rattachement.
	 *
	 * @return this.sensRattachement : String.
	 */
	String getSensRattachement();


	
	/**
	* Setter du sens de la section de Rattachement.
	*
	* @param pSensRattachement : String : 
	* valeur à passer à this.sensRattachement.
	*/
	void setSensRattachement(String pSensRattachement);


	
	/**
	 * Getter du numéro de département de la section Limitrophe.
	 *
	 * @return this.numDepartementLimitrophe : String.
	 */
	String getNumDepartementLimitrophe();


	
	/**
	* Setter du numéro de département de la section Limitrophe.
	*
	* @param pNumDepartementLimitrophe : String : 
	* valeur à passer à this.numDepartementLimitrophe.
	*/
	void setNumDepartementLimitrophe(String pNumDepartementLimitrophe);


	
	/**
	 * Getter du numéro de la section Limitrophe.
	 *
	 * @return this.numSectionLimitrophe : String.
	 */
	String getNumSectionLimitrophe();


	
	/**
	* Setter du numéro de la section Limitrophe.
	*
	* @param pNumSectionLimitrophe : String : 
	* valeur à passer à this.numSectionLimitrophe.
	*/
	void setNumSectionLimitrophe(String pNumSectionLimitrophe);


	
	/**
	 * Getter du sens de la section Limitrophe.
	 *
	 * @return this.sensLimitrophe : String.
	 */
	String getSensLimitrophe();


	
	/**
	* Setter du sens de la section Limitrophe.
	*
	* @param pSensLimitrophe : String : 
	* valeur à passer à this.sensLimitrophe.
	*/
	void setSensLimitrophe(String pSensLimitrophe);

	
	
	/**
	 * Getter du mois de sectionnement.
	 *
	 * @return this.moisSectionnement : String.
	 */
	String getMoisSectionnement();

	
	
	/**
	* Setter du mois de sectionnement.
	*
	* @param pMoisSectionnement : String : 
	* valeur à passer à this.moisSectionnement.
	*/
	void setMoisSectionnement(String pMoisSectionnement);


	
	/**
	 * Getter de l'annee de sectionnement.
	 *
	 * @return this.anneeSectionnement : String.
	 */
	String getAnneeSectionnement();

	
	
	/**
	* Setter de l'annee de sectionnement.
	*
	* @param pAnneeSectionnement : String : 
	* valeur à passer à this.anneeSectionnement.
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
	* valeur à passer à this.zoneLibre2.
	*/
	void setZoneLibre2(String pZoneLibre2);


	
	/**
	 * Getter du trafic moyen journalier annuel de l'année de traitement N.
	 *
	 * @return this.mjaN : Integer.
	 */
	Integer getMjaN();


	
	/**
	* Setter du trafic moyen journalier annuel de l'année de traitement N.
	*
	* @param pMjaN : Integer : 
	* valeur à passer à this.mjaN.
	*/
	void setMjaN(Integer pMjaN);


	
	/**
	 * Getter du mode de calcul des trafics de l'année de traitement N.
	 *
	 * @return this.modeCalculN : String.
	 */
	String getModeCalculN();


	
	/**
	* Setter du mode de calcul des trafics de l'année de traitement N.
	*
	* @param pModeCalculN : String : 
	* valeur à passer à this.modeCalculN.
	*/
	void setModeCalculN(String pModeCalculN);


	
	/**
	 * Getter du pourcentage de trafic poids lourds annuel 
	 * de l'année de traitement N.
	 *
	 * @return this.pcPLN : String.
	 */
	String getPcPLN();
	

	
	/**
	* Setter du pourcentage de trafic poids lourds annuel 
	* de l'année de traitement N.
	*
	* @param pPcPLN : String : 
	* valeur à passer à this.pcPLN.
	*/
	void setPcPLN(String pPcPLN);


	
	/**
	 * Getter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année de traitement N.
	 *
	 * @return this.evaluationPLN : String.
	 */
	String getEvaluationPLN();


	
	/**
	* Setter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	* de l'année de traitement N.
	*
	* @param pEvaluationPLN : String : 
	* valeur à passer à this.evaluationPLN.
	*/
	void setEvaluationPLN(String pEvaluationPLN);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année de traitement N.
	 *
	 * @return this.pcNuitAnnuelN : String.
	 */
	String getPcNuitAnnuelN();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules annuel 
	* de l'année de traitement N.
	*
	* @param pPcNuitAnnuelN : String : 
	* valeur à passer à this.pcNuitAnnuelN.
	*/
	void setPcNuitAnnuelN(String pPcNuitAnnuelN);


	
	/**
	 * Getter de l'indice de fiabilité de la TMJA de l'année de traitement N.
	 *
	 * @return this.indiceFiabiliteMjaN : String.
	 */
	String getIndiceFiabiliteMjaN();


	
	/**
	* Setter de l'indice de fiabilité de la TMJA de l'année de traitement N.
	*
	* @param pIndiceFiabiliteMjaN : String : 
	* valeur à passer à this.indiceFiabiliteMjaN.
	*/
	void setIndiceFiabiliteMjaN(String pIndiceFiabiliteMjaN);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de janvier (01) de l'année de traitement N.
	 *
	 * @return this.mjmNmois01 : Integer.
	 */
	Integer getMjmNmois01();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de janvier (01) de l'année de traitement N.
	*
	* @param pMjmNmois01 : Integer : 
	* valeur à passer à this.mjmNmois01.
	*/
	void setMjmNmois01(Integer pMjmNmois01);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de janvier (01) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois01 : String.
	 */
	String getPcNuitNmois01();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de janvier (01) de l'année de traitement N.
	*
	* @param pPcNuitNmois01 : String : 
	* valeur à passer à this.pcNuitNmois01.
	*/
	void setPcNuitNmois01(String pPcNuitNmois01);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de février (02) de l'année de traitement N.
	 *
	 * @return this.mjmNmois02 : Integer.
	 */
	Integer getMjmNmois02();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de février (02) de l'année de traitement N.
	*
	* @param pMjmNmois02 : Integer : 
	* valeur à passer à this.mjmNmois02.
	*/
	void setMjmNmois02(Integer pMjmNmois02);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de février (02) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois02 : String.
	 */
	String getPcNuitNmois02();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de février (02) de l'année de traitement N.
	*
	* @param pPcNuitNmois02 : String : 
	* valeur à passer à this.pcNuitNmois02.
	*/
	void setPcNuitNmois02(String pPcNuitNmois02);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de mars (03) de l'année de traitement N.
	 *
	 * @return this.mjmNmois03 : Integer.
	 */
	Integer getMjmNmois03();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de mars (03) de l'année de traitement N.
	*
	* @param pMjmNmois03 : Integer : 
	* valeur à passer à this.mjmNmois03.
	*/
	void setMjmNmois03(Integer pMjmNmois03);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de mars (03) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois03 : String.
	 */
	String getPcNuitNmois03();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de mars (03) de l'année de traitement N.
	*
	* @param pPcNuitNmois03 : String : 
	* valeur à passer à this.pcNuitNmois03.
	*/
	void setPcNuitNmois03(String pPcNuitNmois03);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de avril (04) de l'année de traitement N.
	 *
	 * @return this.mjmNmois04 : Integer.
	 */
	Integer getMjmNmois04();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de avril (04) de l'année de traitement N.
	*
	* @param pMjmNmois04 : Integer : 
	* valeur à passer à this.mjmNmois04.
	*/
	void setMjmNmois04(Integer pMjmNmois04);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de avril (04) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois04 : String.
	 */
	String getPcNuitNmois04();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de avril (04) de l'année de traitement N.
	*
	* @param pPcNuitNmois04 : String : 
	* valeur à passer à this.pcNuitNmois04.
	*/
	void setPcNuitNmois04(String pPcNuitNmois04);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de mai (05) de l'année de traitement N.
	 *
	 * @return this.mjmNmois05 : Integer.
	 */
	Integer getMjmNmois05();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de mai (05) de l'année de traitement N.
	*
	* @param pMjmNmois05 : Integer : 
	* valeur à passer à this.mjmNmois05.
	*/
	void setMjmNmois05(Integer pMjmNmois05);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de mai (05) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois05 : String.
	 */
	String getPcNuitNmois05();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de mai (05) de l'année de traitement N.
	*
	* @param pPcNuitNmois05 : String : 
	* valeur à passer à this.pcNuitNmois05.
	*/
	void setPcNuitNmois05(String pPcNuitNmois05);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de juin (06) de l'année de traitement N.
	 *
	 * @return this.mjmNmois06 : Integer.
	 */
	Integer getMjmNmois06();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de juin (06) de l'année de traitement N.
	*
	* @param pMjmNmois06 : Integer : 
	* valeur à passer à this.mjmNmois06.
	*/
	void setMjmNmois06(Integer pMjmNmois06);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de juin (06) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois06 : String.
	 */
	String getPcNuitNmois06();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de juin (06) de l'année de traitement N.
	*
	* @param pPcNuitNmois06 : String : 
	* valeur à passer à this.pcNuitNmois06.
	*/
	void setPcNuitNmois06(String pPcNuitNmois06);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de juillet (07) de l'année de traitement N.
	 *
	 * @return this.mjmNmois07 : Integer.
	 */
	Integer getMjmNmois07();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de juillet (07) de l'année de traitement N.
	*
	* @param pMjmNmois07 : Integer : 
	* valeur à passer à this.mjmNmois07.
	*/
	void setMjmNmois07(Integer pMjmNmois07);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de juillet (07) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois07 : String.
	 */
	String getPcNuitNmois07();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de juillet (07) de l'année de traitement N.
	*
	* @param pPcNuitNmois07 : String : 
	* valeur à passer à this.pcNuitNmois07.
	*/
	void setPcNuitNmois07(String pPcNuitNmois07);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de août (08) de l'année de traitement N.
	 *
	 * @return this.mjmNmois08 : Integer.
	 */
	Integer getMjmNmois08();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de août (08) de l'année de traitement N.
	*
	* @param pMjmNmois08 : Integer : 
	* valeur à passer à this.mjmNmois08.
	*/
	void setMjmNmois08(Integer pMjmNmois08);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de août (08) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois08 : String.
	 */
	String getPcNuitNmois08();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de août (08) de l'année de traitement N.
	*
	* @param pPcNuitNmois08 : String : 
	* valeur à passer à this.pcNuitNmois08.
	*/
	void setPcNuitNmois08(String pPcNuitNmois08);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de septembre (09) de l'année de traitement N.
	 *
	 * @return this.mjmNmois09 : Integer.
	 */
	Integer getMjmNmois09();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de septembre (09) de l'année de traitement N.
	*
	* @param pMjmNmois09 : Integer : 
	* valeur à passer à this.mjmNmois09.
	*/
	void setMjmNmois09(Integer pMjmNmois09);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de septembre (09) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois09 : String.
	 */
	String getPcNuitNmois09();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de septembre (09) de l'année de traitement N.
	*
	* @param pPcNuitNmois09 : String : 
	* valeur à passer à this.pcNuitNmois09.
	*/
	void setPcNuitNmois09(String pPcNuitNmois09);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de octobre (10) de l'année de traitement N.
	 *
	 * @return this.mjmNmois10 : Integer.
	 */
	Integer getMjmNmois10();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de octobre (10) de l'année de traitement N.
	*
	* @param pMjmNmois10 : Integer : 
	* valeur à passer à this.mjmNmois10.
	*/
	void setMjmNmois10(Integer pMjmNmois10);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de octobre (10) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois10 : String.
	 */
	String getPcNuitNmois10();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de octobre (10) de l'année de traitement N.
	*
	* @param pPcNuitNmois10 : String : 
	* valeur à passer à this.pcNuitNmois10.
	*/
	void setPcNuitNmois10(String pPcNuitNmois10);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de novembre (11) de l'année de traitement N.
	 *
	 * @return this.mjmNmois11 : Integer.
	 */
	Integer getMjmNmois11();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de novembre (11) de l'année de traitement N.
	*
	* @param pMjmNmois11 : Integer : 
	* valeur à passer à this.mjmNmois11.
	*/
	void setMjmNmois11(Integer pMjmNmois11);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de novembre (11) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois11 : String.
	 */
	String getPcNuitNmois11();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de novembre (11) de l'année de traitement N.
	*
	* @param pPcNuitNmois11 : String : 
	* valeur à passer à this.pcNuitNmois11.
	*/
	void setPcNuitNmois11(String pPcNuitNmois11);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de décembre (12) de l'année de traitement N.
	 *
	 * @return this.mjmNmois12 : Integer.
	 */
	Integer getMjmNmois12();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de décembre (12) de l'année de traitement N.
	*
	* @param pMjmNmois12 : Integer : 
	* valeur à passer à this.mjmNmois12.
	*/
	void setMjmNmois12(Integer pMjmNmois12);


	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de décembre (12) de l'année de traitement N.
	 *
	 * @return this.pcNuitNmois12 : String.
	 */
	String getPcNuitNmois12();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel
	* du mois de décembre (12) de l'année de traitement N.
	*
	* @param pPcNuitNmois12 : String : 
	* valeur à passer à this.pcNuitNmois12.
	*/
	void setPcNuitNmois12(String pPcNuitNmois12);


	
	/**
	 * Getter de la zone libre 3.
	 *
	 * @return this.zoneLibre3 : String.
	 */
	String getZoneLibre3();


	
	/**
	* Setter de la zone libre 3.
	*
	* @param pZoneLibre3 : String : 
	* valeur à passer à this.zoneLibre3.
	*/
	void setZoneLibre3(String pZoneLibre3);


	
	/**
	 * Getter de l'année n-1.
	 *
	 * @return this.anneeNmoins1 : java.time.LocalDate.
	 */
	LocalDate getAnneeNmoins1();
	

	
	/**
	* Setter de l'année n-1.
	*
	* @param pAnneeNmoins1 : java.time.LocalDate : 
	* valeur à passer à this.anneeNmoins1.
	*/
	void setAnneeNmoins1(LocalDate pAnneeNmoins1);


	
	/**
	 * Getter du trafic moyen journalier annuel de l'année n-1.
	 *
	 * @return this.mjaNmoins1 : Integer.
	 */
	Integer getMjaNmoins1();


	
	/**
	* Setter du trafic moyen journalier annuel de l'année n-1.
	*
	* @param pMjaNmoins1 : Integer : 
	* valeur à passer à this.mjaNmoins1.
	*/
	void setMjaNmoins1(Integer pMjaNmoins1);


	
	/**
	 * Getter du type de comptage de l'année n-1.
	 *
	 * @return this.typeComptageNmoins1 : String.
	 */
	String getTypeComptageNmoins1();


	
	/**
	* Setter du type de comptage de l'année n-1.
	*
	* @param pTypeComptageNmoins1 : String : 
	* valeur à passer à this.typeComptageNmoins1.
	*/
	void setTypeComptageNmoins1(String pTypeComptageNmoins1);


	
	/**
	 * Getter du mode de calcul des trafics de l'année n-1.
	 *
	 * @return this.modeCalculNmoins1 : String.
	 */
	String getModeCalculNmoins1();


	
	/**
	* Setter du mode de calcul des trafics de l'année n-1.
	*
	* @param pModeCalculNmoins1 : String : 
	* valeur à passer à this.modeCalculNmoins1.
	*/
	void setModeCalculNmoins1(String pModeCalculNmoins1);


	
	/**
	 * Getter du pourcentage de trafic poids lourds annuel de l'année n-1.
	 *
	 * @return this.pcPLNmoins1 : String.
	 */
	String getPcPLNmoins1();


	
	/**
	* Setter du pourcentage de trafic poids lourds annuel de l'année n-1.
	*
	* @param pPcPLNmoins1 : String : 
	* valeur à passer à this.pcPLNmoins1.
	*/
	void setPcPLNmoins1(String pPcPLNmoins1);


	
	/**
	 * Getter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année n-1.
	 *
	 * @return this.evaluationPLNmoins1 : String.
	 */
	String getEvaluationPLNmoins1();


	
	/**
	* Setter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	* de l'année n-1.
	*
	* @param pEvaluationPLNmoins1 : String : 
	* valeur à passer à this.evaluationPLNmoins1.
	*/
	void setEvaluationPLNmoins1(String pEvaluationPLNmoins1);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-1.
	 *
	 * @return this.pcNuitAnnuelNmoins1 : String.
	 */
	String getPcNuitAnnuelNmoins1();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules annuel 
	* de l'année n-1.
	*
	* @param pPcNuitAnnuelNmoins1 : String : 
	* valeur à passer à this.pcNuitAnnuelNmoins1.
	*/
	void setPcNuitAnnuelNmoins1(String pPcNuitAnnuelNmoins1);


	
	/**
	 * Getter de l'indice de fiabilité de la TMJA 
	 * de l'année n-1.
	 *
	 * @return this.indiceFiabiliteMjaNmoins1 : String.
	 */
	String getIndiceFiabiliteMjaNmoins1();


	
	/**
	* Setter de l'indice de fiabilité de la TMJA 
	* de l'année n-1.
	*
	* @param pIndiceFiabiliteMjaNmoins1 : String : 
	* valeur à passer à this.indiceFiabiliteMjaNmoins1.
	*/
	void setIndiceFiabiliteMjaNmoins1(String pIndiceFiabiliteMjaNmoins1);


	
	/**
	 * Getter de l'année n-2.
	 *
	 * @return this.anneeNmoins2 : java.time.LocalDate.
	 */
	LocalDate getAnneeNmoins2();
	

	
	/**
	* Setter de l'année n-2.
	*
	* @param pAnneeNmoins2 : java.time.LocalDate : 
	* valeur à passer à this.anneeNmoins2.
	*/
	void setAnneeNmoins2(LocalDate pAnneeNmoins2);


	
	/**
	 * Getter du trafic moyen journalier annuel de l'année n-2.
	 *
	 * @return this.mjaNmoins2 : Integer.
	 */
	Integer getMjaNmoins2();


	
	/**
	* Setter du trafic moyen journalier annuel de l'année n-2.
	*
	* @param pMjaNmoins2 : Integer : 
	* valeur à passer à this.mjaNmoins2.
	*/
	void setMjaNmoins2(Integer pMjaNmoins2);


	
	/**
	 * Getter du type de comptage de l'année n-2.
	 *
	 * @return this.typeComptageNmoins2 : String.
	 */
	String getTypeComptageNmoins2();


	
	/**
	* Setter du type de comptage de l'année n-2.
	*
	* @param pTypeComptageNmoins2 : String : 
	* valeur à passer à this.typeComptageNmoins2.
	*/
	void setTypeComptageNmoins2(String pTypeComptageNmoins2);


	
	/**
	 * Getter du mode de calcul des trafics de l'année n-2.
	 *
	 * @return this.modeCalculNmoins2 : String.
	 */
	String getModeCalculNmoins2();


	
	/**
	* Setter du mode de calcul des trafics de l'année n-2.
	*
	* @param pModeCalculNmoins2 : String : 
	* valeur à passer à this.modeCalculNmoins2.
	*/
	void setModeCalculNmoins2(String pModeCalculNmoins2);


	
	/**
	 * Getter du pourcentage de trafic poids lourds annuel de l'année n-2.
	 *
	 * @return this.pcPLNmoins2 : String.
	 */
	String getPcPLNmoins2();


	
	/**
	* Setter du pourcentage de trafic poids lourds annuel de l'année n-2.
	*
	* @param pPcPLNmoins2 : String : 
	* valeur à passer à this.pcPLNmoins2.
	*/
	void setPcPLNmoins2(String pPcPLNmoins2);


	
	/**
	 * Getter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année n-2.
	 *
	 * @return this.evaluationPLNmoins2 : String.
	 */
	String getEvaluationPLNmoins2();


	
	/**
	* Setter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	* de l'année n-2.
	*
	* @param pEvaluationPLNmoins2 : String : 
	* valeur à passer à this.evaluationPLNmoins2.
	*/
	void setEvaluationPLNmoins2(String pEvaluationPLNmoins2);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-2.
	 *
	 * @return this.pcNuitAnnuelNmoins2 : String.
	 */
	String getPcNuitAnnuelNmoins2();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules annuel 
	* de l'année n-2.
	*
	* @param pPcNuitAnnuelNmoins2 : String : 
	* valeur à passer à this.pcNuitAnnuelNmoins2.
	*/
	void setPcNuitAnnuelNmoins2(String pPcNuitAnnuelNmoins2);


	
	/**
	 * Getter de l'indice de fiabilité de la TMJA 
	 * de l'année n-2.
	 *
	 * @return this.indiceFiabiliteMjaNmoins2 : String.
	 */
	String getIndiceFiabiliteMjaNmoins2();


	
	/**
	* Setter de l'indice de fiabilité de la TMJA 
	* de l'année n-2.
	*
	* @param pIndiceFiabiliteMjaNmoins2 : String : 
	* valeur à passer à this.indiceFiabiliteMjaNmoins2.
	*/
	void setIndiceFiabiliteMjaNmoins2(String pIndiceFiabiliteMjaNmoins2);


	
	/**
	 * Getter de l'année n-3.
	 *
	 * @return this.anneeNmoins3 : java.time.LocalDate.
	 */
	LocalDate getAnneeNmoins3();
	

	
	/**
	* Setter de l'année n-3.
	*
	* @param pAnneeNmoins3 : java.time.LocalDate : 
	* valeur à passer à this.anneeNmoins3.
	*/
	void setAnneeNmoins3(LocalDate pAnneeNmoins3);


	
	/**
	 * Getter du trafic moyen journalier annuel de l'année n-3.
	 *
	 * @return this.mjaNmoins3 : Integer.
	 */
	Integer getMjaNmoins3();


	
	/**
	* Setter du trafic moyen journalier annuel de l'année n-3.
	*
	* @param pMjaNmoins3 : Integer : 
	* valeur à passer à this.mjaNmoins3.
	*/
	void setMjaNmoins3(Integer pMjaNmoins3);


	
	/**
	 * Getter du type de comptage de l'année n-3.
	 *
	 * @return this.typeComptageNmoins3 : String.
	 */
	String getTypeComptageNmoins3();


	
	/**
	* Setter du type de comptage de l'année n-3.
	*
	* @param pTypeComptageNmoins3 : String : 
	* valeur à passer à this.typeComptageNmoins3.
	*/
	void setTypeComptageNmoins3(String pTypeComptageNmoins3);


	
	/**
	 * Getter du mode de calcul des trafics de l'année n-3.
	 *
	 * @return this.modeCalculNmoins3 : String.
	 */
	String getModeCalculNmoins3();


	
	/**
	* Setter du mode de calcul des trafics de l'année n-3.
	*
	* @param pModeCalculNmoins3 : String : 
	* valeur à passer à this.modeCalculNmoins3.
	*/
	void setModeCalculNmoins3(String pModeCalculNmoins3);


	
	/**
	 * Getter du pourcentage de trafic poids lourds annuel de l'année n-3.
	 *
	 * @return this.pcPLNmoins3 : String.
	 */
	String getPcPLNmoins3();


	
	/**
	* Setter du pourcentage de trafic poids lourds annuel de l'année n-3.
	*
	* @param pPcPLNmoins3 : String : 
	* valeur à passer à this.pcPLNmoins3.
	*/
	void setPcPLNmoins3(String pPcPLNmoins3);


	
	/**
	 * Getter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année n-3.
	 *
	 * @return this.evaluationPLNmoins3 : String.
	 */
	String getEvaluationPLNmoins3();


	
	/**
	* Setter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	* de l'année n-3.
	*
	* @param pEvaluationPLNmoins3 : String : 
	* valeur à passer à this.evaluationPLNmoins3.
	*/
	void setEvaluationPLNmoins3(String pEvaluationPLNmoins3);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-3.
	 *
	 * @return this.pcNuitAnnuelNmoins3 : String.
	 */
	String getPcNuitAnnuelNmoins3();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules annuel 
	* de l'année n-3.
	*
	* @param pPcNuitAnnuelNmoins3 : String : 
	* valeur à passer à this.pcNuitAnnuelNmoins3.
	*/
	void setPcNuitAnnuelNmoins3(String pPcNuitAnnuelNmoins3);


	
	/**
	 * Getter de l'indice de fiabilité de la TMJA 
	 * de l'année n-3.
	 *
	 * @return this.indiceFiabiliteMjaNmoins3 : String.
	 */
	String getIndiceFiabiliteMjaNmoins3();


	
	/**
	* Setter de l'indice de fiabilité de la TMJA 
	* de l'année n-3.
	*
	* @param pIndiceFiabiliteMjaNmoins3 : String : 
	* valeur à passer à this.indiceFiabiliteMjaNmoins3.
	*/
	void setIndiceFiabiliteMjaNmoins3(String pIndiceFiabiliteMjaNmoins3);


	
	/**
	 * Getter de l'année n-4.
	 *
	 * @return this.anneeNmoins4 : java.time.LocalDate.
	 */
	LocalDate getAnneeNmoins4();
	

	
	/**
	* Setter de l'année n-4.
	*
	* @param pAnneeNmoins4 : java.time.LocalDate : 
	* valeur à passer à this.anneeNmoins4.
	*/
	void setAnneeNmoins4(LocalDate pAnneeNmoins4);


	
	/**
	 * Getter du trafic moyen journalier annuel de l'année n-4.
	 *
	 * @return this.mjaNmoins4 : Integer.
	 */
	Integer getMjaNmoins4();


	
	/**
	* Setter du trafic moyen journalier annuel de l'année n-4.
	*
	* @param pMjaNmoins4 : Integer : 
	* valeur à passer à this.mjaNmoins4.
	*/
	void setMjaNmoins4(Integer pMjaNmoins4);


	
	/**
	 * Getter du type de comptage de l'année n-4.
	 *
	 * @return this.typeComptageNmoins4 : String.
	 */
	String getTypeComptageNmoins4();


	
	/**
	* Setter du type de comptage de l'année n-4.
	*
	* @param pTypeComptageNmoins4 : String : 
	* valeur à passer à this.typeComptageNmoins4.
	*/
	void setTypeComptageNmoins4(String pTypeComptageNmoins4);


	
	/**
	 * Getter du mode de calcul des trafics de l'année n-4.
	 *
	 * @return this.modeCalculNmoins4 : String.
	 */
	String getModeCalculNmoins4();


	
	/**
	* Setter du mode de calcul des trafics de l'année n-4.
	*
	* @param pModeCalculNmoins4 : String : 
	* valeur à passer à this.modeCalculNmoins4.
	*/
	void setModeCalculNmoins4(String pModeCalculNmoins4);


	
	/**
	 * Getter du pourcentage de trafic poids lourds annuel de l'année n-4.
	 *
	 * @return this.pcPLNmoins4 : String.
	 */
	String getPcPLNmoins4();


	
	/**
	* Setter du pourcentage de trafic poids lourds annuel de l'année n-4.
	*
	* @param pPcPLNmoins4 : String : 
	* valeur à passer à this.pcPLNmoins4.
	*/
	void setPcPLNmoins4(String pPcPLNmoins4);


	
	/**
	 * Getter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année n-4.
	 *
	 * @return this.evaluationPLNmoins4 : String.
	 */
	String getEvaluationPLNmoins4();


	
	/**
	* Setter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	* de l'année n-4.
	*
	* @param pEvaluationPLNmoins4 : String : 
	* valeur à passer à this.evaluationPLNmoins4.
	*/
	void setEvaluationPLNmoins4(String pEvaluationPLNmoins4);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-4.
	 *
	 * @return this.pcNuitAnnuelNmoins4 : String.
	 */
	String getPcNuitAnnuelNmoins4();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules annuel 
	* de l'année n-4.
	*
	* @param pPcNuitAnnuelNmoins4 : String : 
	* valeur à passer à this.pcNuitAnnuelNmoins4.
	*/
	void setPcNuitAnnuelNmoins4(String pPcNuitAnnuelNmoins4);


	
	/**
	 * Getter de l'indice de fiabilité de la TMJA 
	 * de l'année n-4.
	 *
	 * @return this.indiceFiabiliteMjaNmoins4 : String.
	 */
	String getIndiceFiabiliteMjaNmoins4();


	
	/**
	* Setter de l'indice de fiabilité de la TMJA 
	* de l'année n-4.
	*
	* @param pIndiceFiabiliteMjaNmoins4 : String : 
	* valeur à passer à this.indiceFiabiliteMjaNmoins4.
	*/
	void setIndiceFiabiliteMjaNmoins4(String pIndiceFiabiliteMjaNmoins4);


	
	/**
	 * Getter de l'année n-5.
	 *
	 * @return this.anneeNmoins5 : java.time.LocalDate.
	 */
	LocalDate getAnneeNmoins5();
	

	
	/**
	* Setter de l'année n-5.
	*
	* @param pAnneeNmoins5 : java.time.LocalDate : 
	* valeur à passer à this.anneeNmoins5.
	*/
	void setAnneeNmoins5(LocalDate pAnneeNmoins5);


	
	/**
	 * Getter du trafic moyen journalier annuel de l'année n-5.
	 *
	 * @return this.mjaNmoins5 : Integer.
	 */
	Integer getMjaNmoins5();


	
	/**
	* Setter du trafic moyen journalier annuel de l'année n-5.
	*
	* @param pMjaNmoins5 : Integer : 
	* valeur à passer à this.mjaNmoins5.
	*/
	void setMjaNmoins5(Integer pMjaNmoins5);


	
	/**
	 * Getter du type de comptage de l'année n-5.
	 *
	 * @return this.typeComptageNmoins5 : String.
	 */
	String getTypeComptageNmoins5();


	
	/**
	* Setter du type de comptage de l'année n-5.
	*
	* @param pTypeComptageNmoins5 : String : 
	* valeur à passer à this.typeComptageNmoins5.
	*/
	void setTypeComptageNmoins5(String pTypeComptageNmoins5);


	
	/**
	 * Getter du mode de calcul des trafics de l'année n-5.
	 *
	 * @return this.modeCalculNmoins5 : String.
	 */
	String getModeCalculNmoins5();


	
	/**
	* Setter du mode de calcul des trafics de l'année n-5.
	*
	* @param pModeCalculNmoins5 : String : 
	* valeur à passer à this.modeCalculNmoins5.
	*/
	void setModeCalculNmoins5(String pModeCalculNmoins5);


	
	/**
	 * Getter du pourcentage de trafic poids lourds annuel de l'année n-5.
	 *
	 * @return this.pcPLNmoins5 : String.
	 */
	String getPcPLNmoins5();


	
	/**
	* Setter du pourcentage de trafic poids lourds annuel de l'année n-5.
	*
	* @param pPcPLNmoins5 : String : 
	* valeur à passer à this.pcPLNmoins5.
	*/
	void setPcPLNmoins5(String pPcPLNmoins5);


	
	/**
	 * Getter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année n-5.
	 *
	 * @return this.evaluationPLNmoins5 : String.
	 */
	String getEvaluationPLNmoins5();


	
	/**
	* Setter du moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	* de l'année n-5.
	*
	* @param pEvaluationPLNmoins5 : String : 
	* valeur à passer à this.evaluationPLNmoins5.
	*/
	void setEvaluationPLNmoins5(String pEvaluationPLNmoins5);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-5.
	 *
	 * @return this.pcNuitAnnuelNmoins5 : String.
	 */
	String getPcNuitAnnuelNmoins5();


	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules annuel 
	* de l'année n-5.
	*
	* @param pPcNuitAnnuelNmoins5 : String : 
	* valeur à passer à this.pcNuitAnnuelNmoins5.
	*/
	void setPcNuitAnnuelNmoins5(String pPcNuitAnnuelNmoins5);


	
	/**
	 * Getter de l'indice de fiabilité de la TMJA 
	 * de l'année n-5.
	 *
	 * @return this.indiceFiabiliteMjaNmoins5 : String.
	 */
	String getIndiceFiabiliteMjaNmoins5();


	
	/**
	* Setter de l'indice de fiabilité de la TMJA 
	* de l'année n-5.
	*
	* @param pIndiceFiabiliteMjaNmoins5 : String : 
	* valeur à passer à this.indiceFiabiliteMjaNmoins5.
	*/
	void setIndiceFiabiliteMjaNmoins5(String pIndiceFiabiliteMjaNmoins5);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de janvier (01) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois01 : Integer.
	 */
	Integer getMjmNmoins1mois01();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de janvier (01) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois01 : Integer : 
	* valeur à passer à this.mjmNmoins1mois01.
	*/
	void setMjmNmoins1mois01(Integer pMjmNmoins1mois01);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de janvier (01) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois01 : String.
	 */
	String getPcNuitNmoins1mois01();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de janvier (01) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois01 : String : 
	* valeur à passer à this.pcNuitNmoins1mois01.
	*/
	void setPcNuitNmoins1mois01(String pPcNuitNmoins1mois01);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de février (02) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois02 : Integer.
	 */
	Integer getMjmNmoins1mois02();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de février (02) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois02 : Integer : 
	* valeur à passer à this.mjmNmoins1mois02.
	*/
	void setMjmNmoins1mois02(Integer pMjmNmoins1mois02);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de février (02) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois02 : String.
	 */
	String getPcNuitNmoins1mois02();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de février (02) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois02 : String : 
	* valeur à passer à this.pcNuitNmoins1mois02.
	*/
	void setPcNuitNmoins1mois02(String pPcNuitNmoins1mois02);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de mars (03) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois03 : Integer.
	 */
	Integer getMjmNmoins1mois03();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de mars (03) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois03 : Integer : 
	* valeur à passer à this.mjmNmoins1mois03.
	*/
	void setMjmNmoins1mois03(Integer pMjmNmoins1mois03);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de mars (03) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois03 : String.
	 */
	String getPcNuitNmoins1mois03();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de mars (03) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois03 : String : 
	* valeur à passer à this.pcNuitNmoins1mois03.
	*/
	void setPcNuitNmoins1mois03(String pPcNuitNmoins1mois03);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de avril (04) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois04 : Integer.
	 */
	Integer getMjmNmoins1mois04();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de avril (04) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois04 : Integer : 
	* valeur à passer à this.mjmNmoins1mois04.
	*/
	void setMjmNmoins1mois04(Integer pMjmNmoins1mois04);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de avril (04) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois04 : String.
	 */
	String getPcNuitNmoins1mois04();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de avril (04) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois04 : String : 
	* valeur à passer à this.pcNuitNmoins1mois04.
	*/
	void setPcNuitNmoins1mois04(String pPcNuitNmoins1mois04);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de mai (05) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois05 : Integer.
	 */
	Integer getMjmNmoins1mois05();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de mai (05) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois05 : Integer : 
	* valeur à passer à this.mjmNmoins1mois05.
	*/
	void setMjmNmoins1mois05(Integer pMjmNmoins1mois05);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de mai (05) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois05 : String.
	 */
	String getPcNuitNmoins1mois05();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de mai (05) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois05 : String : 
	* valeur à passer à this.pcNuitNmoins1mois05.
	*/
	void setPcNuitNmoins1mois05(String pPcNuitNmoins1mois05);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de juin (06) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois06 : Integer.
	 */
	Integer getMjmNmoins1mois06();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de juin (06) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois06 : Integer : 
	* valeur à passer à this.mjmNmoins1mois06.
	*/
	void setMjmNmoins1mois06(Integer pMjmNmoins1mois06);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de juin (06) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois06 : String.
	 */
	String getPcNuitNmoins1mois06();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de juin (06) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois06 : String : 
	* valeur à passer à this.pcNuitNmoins1mois06.
	*/
	void setPcNuitNmoins1mois06(String pPcNuitNmoins1mois06);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de juillet (07) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois07 : Integer.
	 */
	Integer getMjmNmoins1mois07();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de juillet (07) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois07 : Integer : 
	* valeur à passer à this.mjmNmoins1mois07.
	*/
	void setMjmNmoins1mois07(Integer pMjmNmoins1mois07);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de juillet (07) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois07 : String.
	 */
	String getPcNuitNmoins1mois07();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de juillet (07) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois07 : String : 
	* valeur à passer à this.pcNuitNmoins1mois07.
	*/
	void setPcNuitNmoins1mois07(String pPcNuitNmoins1mois07);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de août (08) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois08 : Integer.
	 */
	Integer getMjmNmoins1mois08();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de août (08) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois08 : Integer : 
	* valeur à passer à this.mjmNmoins1mois08.
	*/
	void setMjmNmoins1mois08(Integer pMjmNmoins1mois08);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de août (08) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois08 : String.
	 */
	String getPcNuitNmoins1mois08();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de août (08) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois08 : String : 
	* valeur à passer à this.pcNuitNmoins1mois08.
	*/
	void setPcNuitNmoins1mois08(String pPcNuitNmoins1mois08);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de septembre (09) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois09 : Integer.
	 */
	Integer getMjmNmoins1mois09();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de septembre (09) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois09 : Integer : 
	* valeur à passer à this.mjmNmoins1mois09.
	*/
	void setMjmNmoins1mois09(Integer pMjmNmoins1mois09);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de septembre (09) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois09 : String.
	 */
	String getPcNuitNmoins1mois09();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de septembre (09) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois09 : String : 
	* valeur à passer à this.pcNuitNmoins1mois09.
	*/
	void setPcNuitNmoins1mois09(String pPcNuitNmoins1mois09);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de octobre (10) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois10 : Integer.
	 */
	Integer getMjmNmoins1mois10();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de octobre (10) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois10 : Integer : 
	* valeur à passer à this.mjmNmoins1mois10.
	*/
	void setMjmNmoins1mois10(Integer pMjmNmoins1mois10);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de octobre (10) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois10 : String.
	 */
	String getPcNuitNmoins1mois10();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de octobre (10) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois10 : String : 
	* valeur à passer à this.pcNuitNmoins1mois10.
	*/
	void setPcNuitNmoins1mois10(String pPcNuitNmoins1mois10);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de novembre (11) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois11 : Integer.
	 */
	Integer getMjmNmoins1mois11();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de novembre (11) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois11 : Integer : 
	* valeur à passer à this.mjmNmoins1mois11.
	*/
	void setMjmNmoins1mois11(Integer pMjmNmoins1mois11);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de novembre (11) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois11 : String.
	 */
	String getPcNuitNmoins1mois11();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de novembre (11) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois11 : String : 
	* valeur à passer à this.pcNuitNmoins1mois11.
	*/
	void setPcNuitNmoins1mois11(String pPcNuitNmoins1mois11);


	
	/**
	 * Getter de la moyenne journalière mensuelle en véhicules/jour 
	 * du mois de décembre (12) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.mjmNmoins1mois12 : Integer.
	 */
	Integer getMjmNmoins1mois12();


	
	/**
	* Setter de la moyenne journalière mensuelle en véhicules/jour 
	* du mois de décembre (12) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pMjmNmoins1mois12 : Integer : 
	* valeur à passer à this.mjmNmoins1mois12.
	*/
	void setMjmNmoins1mois12(Integer pMjmNmoins1mois12);

	
	
	/**
	 * Getter du pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de décembre (12) 
	 * de l'année précédent l'année de traitement N.
	 *
	 * @return this.pcNuitNmoins1mois12 : String.
	 */
	String getPcNuitNmoins1mois12();

	
	
	/**
	* Setter du pourcentage de trafic de nuit tous véhicules mensuel 
	* du mois de décembre (12) 
	* de l'année précédent l'année de traitement N.
	*
	* @param pPcNuitNmoins1mois12 : String : 
	* valeur à passer à this.pcNuitNmoins1mois12.
	*/
	void setPcNuitNmoins1mois12(String pPcNuitNmoins1mois12);


	
	/**
	 * Getter de la zone libre 4.
	 *
	 * @return this.zoneLibre4 : String.
	 */
	String getZoneLibre4();


	
	/**
	* Setter de la zone libre 4.
	*
	* @param pZoneLibre4 : String : 
	* valeur à passer à this.zoneLibre4.
	*/
	void setZoneLibre4(String pZoneLibre4);


	
	/**
	 * Getter de la Localisation de la section.<br/>
	 * COMPOSANT.<br/>
	 *
	 * @return this.localisation : ILocalisationHit.
	 */
	ILocalisationHit getLocalisation();


	
	/**
	* Setter de la Localisation de la section.<br/>
	* COMPOSANT.
	* <ul>
	* <li>alimente automatiquement les attributs de la présente classe 
	* avec les attributs correspondants du COMPOSANT.</li>
	* </ul>
	*
	* @param pLocalisation : ILocalisationHit : 
	* valeur à passer à this.localisation.<br/>
	*/
	void setLocalisation(ILocalisationHit pLocalisation);
	
	

} // FIN DE L'INTERFACE ISectionHit.-----------------------------------------
