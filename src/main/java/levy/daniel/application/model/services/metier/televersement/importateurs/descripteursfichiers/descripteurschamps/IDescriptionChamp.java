package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps;

import java.util.SortedMap;

import levy.daniel.application.apptechnic.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.apptechnic.exceptions.technical.impl.TableauNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.TableauVideException;

/**
 * class IDescriptionChamp :<br/>
 * RESPONSABILITE : ENCAPSULATION D'UNE LIGNE D'UNE DESCRIPTION (CHAMP).<br/>
 * Interface factorisant les méthodes des DescriptionChamp.<br/>
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
 * @since 27 juin 2014
 *
 */
public interface IDescriptionChamp 
				extends IFournisseurCsv
					, IFournisseurJTable
						, IRapporteur
							, IFormatteurLongueurs {

	
	
	/**
	 * <b>Lit le tableau String[] pTokens correspondant à la 
	 * définition d'un champ donné dans une description de fichier 
	 * (ligne d'une description de fichier) 
	 * et alimente les attributs d'une encapsulation IDescriptionChamp</b>.<br/>
	 * Calcule automatiquement les champs <i>non obligatoirement fournis</i> 
	 * (colonne début, colonne fin, longueur calculée).<br/>
	 * <i>Methode à implémenter dans chaque DescriptionChamp concret</i>.<br/>
	 * <ul>
	 * Cette méthode est :
	 * <li>Chargée d'alimenter les attributs de la classe.</li>
	 * <li>Chargée d'alimenter la SortedMap&lt;Integer, String&gt;
	 *  <code><b>this.valeursDescriptionMap</b></code>.</li>
	 * <li>Chargée d'alimenter la SortedMap&lt;Integer, Integer&gt;
	 *  <code><b>this.longueursDescriptionMap</b></code>.</li>
	 * <li>Garantit que tous les DescriptionChamp concrets sauront lire un
	 * tableau de tokens et alimenter la SortedMap&lt;Integer, String&gt; 
	 * <code><b>this.valeursDescriptionMap</b></code>.</li>
	 * <li>Le tableau de tokens correspond aux valeurs lues dans la
	 * description du fichier pour un champ (ligne) donné.</li>
	 * </ul>
	 * <br/>
	 * - LOG FATAL, et jette une Exception si pTokens est null, vide
	 * , trop court, ou si des champs indispensables 
	 * (ordreChamps, colonnes, intitulé, ...) ne sont pas renseignés.<br/>
	 * <br/>
	 * 
	 * @param pTokens : String[].<br/>
	 * La ligne de la description de fichier décomposée en tokens.<br/>
	 * Par exemple {"1"
	 * , "1-3"
	 * , "3"
	 * , "Numéro de Département"
	 * , "cadré à gauche"
	 * , "numDepartment"
	 * , "Integer"
	 * , "false"
	 * , "false"}
	 * pour DescriptionChampHistoF07 appliquée au champ numéro
	 * de département (ligne 1) dans la description du fichier
	 * HISTO_F07.<br/>
	 * 
	 * @throws TableauNullException lorsque : pTokens
	 * passé en paramètre est null.<br/>
	 * @throws TableauVideException lorsque : pTokens
	 * passé en paramètre est vide.<br/>
	 * @throws ExceptionImport lorsque : <br/>
	 * - Le tableau de tokens passé en paramètre est trop court.
	 * (Il doit au moins posséder autant de tokens qu'il n'y a
	 * de colonnes décrites dans 'entetesDescriptionMap').<br/>
	 * - Une valeur dans le tableau de tokens décrivant le champ
	 * dans la description de fichier est manquante
	 * ou inadmissible. <br/>
	 * 
	 * @throws Exception 
	 */
	void lireChamp(String[] pTokens)
		throws 
			TableauNullException
				, TableauVideException
					, ExceptionImport, Exception;
	

	
	/**
	 * Fabrique une chaine de caractères comportant les
	 * colonnes du champ (en-têtes des
	 * colonnes dans la description) séparées par des virgules 
	 * et encadrées par des crochets.<br/>
	 * <br/>
	 * Par exemple : <br/>
	 * [ordreChamps, colonnes, longueur, intitule, nomenclature
	 * , champJava, typeJava, aNomenclature, aLexique
	 * , colonneDebut, colonneFin, longueurCalculee]
	 * pour un DescriptionChampHistoF07.<br/>
	 * [ordreChamps, intitule, nomenclature, champJava, typeJava
	 * , aNomenclature, aLexique] 
	 * pour un DescriptionChampDarwinCsv.<br/>
	 * <br/>
	 * - retourne "" si <code>this.entetesDescriptionMap</code> est null.<br/>
	 * - retourne "" si <code>this.entetesDescriptionMap</code> est vide.<br/>
	 * <br/>
	 *
	 * @return String : une chaine de caractères décrivant 
	 * les en-têtes des colonnes dans la description du fichier 
	 * séparées par des virgules et encadrées par des crochets.<br/>
	 */
	String entetesDescriptionToString();
	

	
	/**
	 * Fabrique une chaine de caractères comportant les
	 * en-têtes des colonnes dans la description concaténés
	 * avec la valeur dans la description pour un champ
	 * (ligne) donné, le tout séparé par des virgules.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * [ordreChamps = 1, colonnes = 1-3, longueur = 3, 
	 * intitule = Numéro de Département, nomenclature = calé à gauche
	 * , champJava = numDepartment, typeJava = Integer
	 * , aNomenclature = false, aLexique = false, 
	 * colonneDebut = 1, colonneFin = 3
	 * , longueurCalculee = 3] pour le 'Numéro de Département' 
	 * (1ère ligne) de la description d'un HISTO_F07.<br/>
	 * [ordreChamps = 2, intitule = route, 
	 * nomenclature = Route au format Isidor (ex : A0034b1 ou A0006)
	 * , champJava = route, typeJava = String, aNomenclature = false
	 * , aLexique = false] 
	 * pour le champ 'route' 
	 * (2ème ligne) dans la description du fichier DARWIN_CSV.<br/>
	 * <br/>
	 * - retourne "" si <code>this.valeursDescriptionMap</code> est null.<br/>
	 * - retourne "" si <code>this.valeursDescriptionMap</code> est vide.<br/>
	 * <br/>
	 * NECESSITE D'AVOIR FAIT desc.lireChamp(pTokens) 
	 * APRES L'INSTANCIATION DU Descripteur 
	 * pour que <code>this.valeursDescriptionMap</code> SOIT INSTANCIEE.<br/>
	 * <br/>
	 *
	 * @return une chaine de caractères décrivant 
	 * la description d'un champ : String.<br/>
	 */
	String valeursDescriptionToString();
	
	
	
	/**
	 * <b>Transforme String[] en csv</b>.<br/>
	 * Affiche une ligne de la description de fichier
	 * - décomposée sous forme de tableau String[] de tokens - 
	 * sous forme de ligne CSV avec séparateur ';'.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * 1;1-3;3;Numéro de Département;calé à gauche;numDepartment;Integer;false;
	 *  false;1;3;3; pour le champ 'Numéro de Département' 
	 * (1ère ligne) dans la description du fichier HISTO_F07.<br/>
	 * 2;route;Route au format Isidor (ex : A0034b1 ou A0006);route;String;
	 * false; pour le champ 'route' 
	 * (2ème ligne) dans la description du fichier DARWIN_CSV.<br/>
	 * <br/>
	 * - retourne "" si pTokens est null.<br/>
	 * <br/>
	 * 
	 * @param pTokens : String[] : une ligne de la description
	 * de fichier décomposée en tokens.<br/>
	 * 
	 * @return String : ligne csv avec séparateur ';'.<br/>
	 */
	String tokensToString(String[] pTokens);
	
	
	
	/**
	* Fabrique une chaine de caractères comportant tous
	* les éléments de description du champ séparés par des tabulations 
	* et avec un saut de ligne \n à la fin.<br/>
	* <br/>
	* Par exemple : <br/>
	* [1	1-3	3	Numéro de Département	calé à gauche	
	* numDepartment	Integer	false	false	1	3	3	\n] 
	* pour le champ 'Numéro de Département' 
	* (1ère ligne) dans la description du fichier HISTO_F07.<br/>
	* [2	route	Route au format Isidor (ex : A0034b1 ou A0006
	* )	route	String	false	false	\n] pour le champ 'route' 
	* (2ème ligne) dans la description du fichier DARWIN_CSV.<br/>
	* <br/>
	* - retourne null si valeursDescriptionMap est null.<br/>
	* <br/>
	* NECESSITE D'AVOIR FAIT desc.lireChamp(pTokens) 
	* APRES L'INSTANCIATION DU Descripteur 
	* pour que valeursDescriptionMap SOIT INSTANCIEE.<br/>
	* <br/>
	* 
	* @return String : une chaine de caractères décrivant le champ 
	* en exposant toutes ses valeurs séparées par des tabulations 
	* et avec un saut de ligne \n à la fin.<br/>
	*/
	String descriptionChampToString();
	
	
	
	/**
	 * retire des caractères incongrus dans la description
	 * de fichier.<br/>
	 * Par exemple, "Sens" devient 'sens' (guillemets nettoyés).<br/>
	 * <br/>
	 * - retourne null si pString est null.<br/>
	 * <br/>
	 * 
	 * @param pString : la chaîne de caractères à nettoyer.<br/>
	 * 
	 * @return String : la chaîne nettoyée.<br/>
	 */
	String nettoyerString(String pString);
	
		
	
	/**
	 * Getter de la Map triée <code>this.entetesDescriptionMap</code> 
	 * stockant :<br/>
	 * - Integer : l'ordre des colonnes d'une description de fichier.<br/>
	 * - String : le libellé (java) des colonnes de la description
	 * de fichier.<br/>
	 * Par exemple :<br/>
	 * {1=ordreChamps, 2=colonnes, 3=longueur, 4=intitule, 5=nomenclature
	 * , 6=champJava, 7=typeJava, 8=aNomenclature, 9=aLexique
	 * , 10=colonneDebut, 11=colonneFin, 12=longueurCalculee}
	 * pour un DescriptionChampHit.<br/>
	 * <br/>
	 * 
	 * @return this.entetesDescriptionMap : SortedMap&lt;Integer, String&gt;.<br/>
	 * Map triée stockant :<br/>
	 * - Integer : l'ordre des colonnes d'une description de fichier.<br/>
	 * - String : le libellé (java) des colonnes de la description
	 * de fichier.<br/>
	 */
	SortedMap<Integer, String> getEntetesDescriptionMap();
	
	
	
	/**
	 * Setter de la Map triée stockant :<br/>
	 * - Integer : l'ordre des colonnes d'une description de fichier.<br/>
	 * - String : le libellé (java) des colonnes de la description
	 * de fichier.<br/>
	 * Par exemple :<br/>
	 * [1-ordreChamps;2-colonnes;3-longueur;4-intitule;5-nomenclature;
	 * 6-champJava;7-typeJava;8-aNomenclature;
	 * 9-colonneDebut;10-colonneFin;11-longueurCalculee;]
	 * pour un DescriptionChampHistoF07.<br/>
	 * [1-ordreChamps;2-intitule;3-nomenclature;
	 * 4-champJava;5-typeJava;7-aNomenclature;] 
	 * pour un DescriptionChampDarwinCsv.<br/>
	 * <br/>
	 * 
	 * @param pColonnesDescriptionMap : SortedMap&lt;Integer, String&gt;.<br/>
	 * Map triée stockant :<br/>
	 * - Integer : l'ordre des colonnes d'une description de fichier.<br/>
	 * - String : le libellé (java) des colonnes de la description
	 * de fichier.<br/>
	 */
	void setEntetesDescriptionMap(
			SortedMap<Integer, String> pColonnesDescriptionMap);
	
	
	
	/**
	 * Getter de la Map triée <code>this.valeursDescriptionMap</code> 
	 * stockant :<br/>
	 * - Integer : l'ordre des colonnes d'une description de fichier.<br/>
	 * - String : les valeurs dans les colonnes de la description
	 * de fichier pour un champ donné 
	 * (une ligne de la description de fichier).<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * [(1 pour ordreChamps, '3'), (2 pour colonnes, '10')
	 * , (3 pour longueur, '1')
	 * , (4 pour intitule, 'Sens'), etc...] pour le champ 'sens'
	 *  (ligne 3) dans 
	 * DescriptionChampHistoF07.<br/>
	 * {1=1, 2=1-3, 3=3, 4=Numéro de Département, 5=cadré à gauche
	 * , 6=numDepartment, 7=Integer, 8=false, 9=false
	 * , 10=1, 11=3, 12=3}
	 * pour le champ 'Numéro de Département' 
	 * (1ère ligne) dans la description du fichier HISTO_F07.<br/>
	 * {1=2, 2=route, 3=Route au format Isidor 
	 * (ex : A0034b1 ou A0006), 4=route, 5=String, 6=false} 
	 * pour le champ 'route' (2ème ligne) 
	 * dans la description du fichier DARWIN_CSV.<br/>
	 * <br/>
	 * NECESSITE D'AVOIR FAIT desc.lireChamp(pTokens) 
	 * APRES L'INSTANCIATION DU Descripteur 
	 * pour que <code>this.valeursDescriptionMap</code> SOIT INSTANCIEE.<br/>
	 * <br/>
	 * 
	 * @return this.valeursDescriptionMap :  SortedMap&lt;Integer, String&gt;.<br/>
	 * Map triée stockant :<br/>
	 * - Integer : l'ordre des colonnes d'une description de fichier.<br/>
	 * - String : les valeurs dans les colonnes de la description
	 * de fichier pour un champ (ligne de la description) donné.<br/>
	 */
	SortedMap<Integer, String> getValeursDescriptionMap();
	
	
	
	/**
	 * Setter de la Map triée stockant :<br/>
	 * - Integer : l'ordre des colonnes d'une description de fichier.<br/>
	 * - String : les valeurs dans les colonnes de la description
	 * de fichier pour un champ donné 
	 * (une ligne de la description de fichier).<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * [(1 pour ordreChamps, '3'), (2 pour colonnes, '10')
	 * , (3 pour longueur, '1')
	 * , (4 pour intitule, 'Sens'), etc...] pour le champ 'sens'
	 *  (ligne 3) dans 
	 * DescriptionChampHistoF07.<br/>
	 * {1=1, 2=1-3, 3=3, 4=Numéro de Département, 5=calé à gauche
	 * , 6=numDepartment, 7=Integer, 8=false, 9=1, 10=3, 11=3} 
	 * pour le champ 'Numéro de Département' 
	 * (1ère ligne) dans la description du fichier HISTO_F07.<br/>
	 * {1=2, 2=route, 3=Route au format Isidor 
	 * (ex : A0034b1 ou A0006), 4=route, 5=String, 6=false} 
	 * pour le champ 'route' (2ème ligne) 
	 * dans la description du fichier DARWIN_CSV.<br/>
	 * <br/>
	 * 
	 * @param pValeursDescriptionMap : SortedMap&lt;Integer, String&gt;.<br/>
	 * Map triée stockant :<br/>
	 * - Integer : l'ordre des colonnes d'une description de fichier.<br/>
	 * - String : les valeurs dans les colonnes de la description
	 * de fichier pour un champ (ligne de la description) donné.<br/>
	 */
	void setValeursDescriptionMap(
			SortedMap<Integer, String> pValeursDescriptionMap);
	
	
	
	/**
	 * Getter du nombre de colonnes que la description
	 * de fichier fournie en entrée doit absolument comporter.<br/>
	 * Les autres colonnes sont calculées 
	 * (colonne de début, longueur calculée, ...).<br/>
	 * <br/>
	 * 
	 * @return nombreColonnesObligatoires : int :<br/>
	 * le nombre de colonnes que la description
	 * de fichier fournie en entrée doit absolument comporter.<br/>
	 */
	int getNombreColonnesObligatoires();
	

	
	/**
	 * Setter du nombre de colonnes que la description
	 * de fichier fournie en entrée doit absolument comporter.<br/>
	 * Les autres colonnes sont calculées 
	 * (colonne de début, longueur calculée, ...).<br/>
	 * <br/>
	 * 
	 * @param pNombreColonnesObligatoires : int :<br/>
	 * le nombre de colonnes que la description
	 * de fichier fournie en entrée doit absolument comporter.<br/>
	 */
	void setNombreColonnesObligatoires(int pNombreColonnesObligatoires);
	
	
		
	/**
	 * Getter du nom Java du champ concerné par cette description.<br/>
	 * <br/>
	 *
	 * @return : String : nom Java du champ concerné par cette description.<br/>
	 */
	String getNomChampJava();
	

	
	
	/**
	 * Getter de l'Ordre du champ 1- based (ligne) dans la description.<br/>
	 * Par exemple, 'Numéro de Section' est le 
	 * deuxième champ dans la description du HISTO_F07.<br/>
	 * <br/>
	 *
	 * @return this.ordreChamps : Integer.<br/>
	 */
	Integer getOrdreChamps();



	/**
	 * Setter de l'Ordre du champ 1 - based (ligne) dans la description.<br/>
	 * Par exemple, 'Numéro de Section' est le 
	 * deuxième champ dans la description du HISTO_F07.<br/>
	 * <br/>
	 *
	 * @param pOrdreChamps : Integer : 
	 * valeur à passer à this.ordreChamps.<br/>
	 */
	void setOrdreChamps(Integer pOrdreChamps);


	

	/**
	 * Getter de l'Intitulé du champ dans la description 
	 * comme 'Numéro de Section'.<br/>
	 * <br/>
	 *
	 * @return this.intitule : String.<br/>
	 */
	String getIntitule();



	/**
	 * Setter de l'Intitulé du champ dans la description 
	 * comme 'Numéro de Section'.<br/>
	 * <br/>
	 *
	 * @param pIntitule : String : 
	 * valeur à passer à this.intitule.<br/>
	 */
	void setIntitule(String pIntitule);



	/**
	 * Getter de l'Explication/Nomenclature éventuelle du champ comme :<br/>
	 * "3 - Cumul des deux sens. [sep]<br/>
	 *  4 - Sens unique P.R. croissants.[sep]  <br/>
	 *  5 - Sens unique P.R. Décroissants.".<br/>
	 * <br/>
	 *
	 * @return this.nomenclature : String.<br/>
	 */
	String getNomenclature();



	/**
	 * Setter de la Nomenclature éventuelle du champ comme :<br/>
	 * "3 - Cumul des deux sens. [sep]<br/>
	 *  4 - Sens unique P.R. croissants.[sep]  <br/>
	 *  5 - Sens unique P.R. Décroissants.".<br/>
	 * <br/>
	 * <br/>
	 *
	 * @param pNomenclature : String : 
	 * valeur à passer à this.nomenclature.<br/>
	 */
	void setNomenclature(String pNomenclature);



	/**
	 * Getter du Nom du champ dans l'application comme numDepartement 
	 * pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @return this.champJava : String.<br/>
	 */
	String getChampJava();



	/**
	 * Setter du Nom du champ dans l'application comme numDepartement 
	 * pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @param pChampJava : String : 
	 * valeur à passer à this.champJava.<br/>
	 */
	void setChampJava(String pChampJava);



	/**
	 * Getter du type Java du champ dans l'application 
	 * comme Integer pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @return this.typeJava : String.<br/>
	 */
	String getTypeJava();



	/**
	 * Setter du type Java du champ dans l'application 
	 * comme Integer pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @param pTypeJava : String : 
	 * valeur à passer à this.typeJava.<br/>
	 */
	void setTypeJava(String pTypeJava);



	/**
	 * Getter du boolean qui stipule si le champ 
	 * fait l'objet d'une nomenclature.<br/>
	 * <br/>
	 *
	 * @return this.aNomenclature : boolean.<br/>
	 */
	boolean isANomenclature();



	/**
	 * Setter du boolean qui stipule si le champ 
	 * fait l'objet d'une nomenclature.<br/>
	 * <br/>
	 *
	 * @param pANomenclature : boolean : 
	 * valeur à passer à this.aNomenclature.<br/>
	 */
	void setANomenclature(boolean pANomenclature);



	/**
	 * Getter du boolean qui stipule si le champ 
	 * fait l'objet d'un lexique.<br/>
	 * <br/>
	 *
	 * @return this.aLexique : boolean.<br/>
	 */
	boolean isALexique();



	/**
	 * Setter du boolean qui stipule si le champ 
	 * fait l'objet d'une nomenclature.<br/>
	 * <br/>
	 *
	 * @param pALexique : boolean : 
	 * valeur à passer à this.aLexique.<br/>
	 */
	void setALexique(boolean pALexique);


	
	/**
	 * Fournit le nom de la description des champs.<br/>
	 * <br/>
	 * Par exemple : <br/>
	 * "Description des champs d'un fichier HIT".<br/>
	 *
	 * @return : String.<br/>
	 */
	String getNomDescriptionChamp();

	
	
} // FIN DE L'INTERFACE IDescriptionChamp.-----------------------------------
