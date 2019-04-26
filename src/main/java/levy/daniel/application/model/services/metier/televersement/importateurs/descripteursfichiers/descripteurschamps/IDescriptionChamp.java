package levy.daniel.application.metier.importateurs.descripteursfichiers.descripteurschamps;

import java.util.SortedMap;

import levy.daniel.application.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.exceptions.technical.impl.TableauNullException;
import levy.daniel.application.exceptions.technical.impl.TableauVideException;

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
	 * method lireChamp() :<br/>
	 * Methode à implémenter dans chaque DescriptionChamp.<br/>
	 * - Chargée d'alimenter les attributs de la classe.<br/>
	 * - Chargée d'alimenter la SortedMap&lt;Integer, String&gt;
	 *  'valeursDescriptionMap'.<br/>
	 *  - Chargée d'alimenter la SortedMap&lt;Integer, Integer&gt;
	 *  'longueursDescriptionMap'.<br/>
	 * - Garantit que tous les DescriptionChamp concrets sauront lire un
	 * tableau de tokens et alimenter la Map 'valeursDescriptionMap'.<br/>
	 * - Le tableau de tokens correspond aux valeurs lues dans la
	 * description du fichier pour un champ (ligne) donné.<br/>
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
	 * , "false"}
	 * pour DescriptionChampHistoF07 appliquée au champ numéro
	 * de département (ligne 1) dans la description du fichier
	 * HistoNatF07.<br/>
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
	 */
	void lireChamp(String[] pTokens)
		throws 
			TableauNullException
				, TableauVideException
					, ExceptionImport;
	

	
	/**
	 * method entetesDescriptionToString() :<br/>
	 * Fabrique une chaine de caractères comportant les
	 * colonnes du champ (en-têtes des
	 * colonnes dans la description) séparées par des virgules 
	 * et encadrées par des crochets.<br/>
	 * <br/>
	 * Par exemple : <br/>
	 * [ordreChamps, colonnes, longueur, intitule, nomenclature
	 * , champJava, typeJava, aNomenclature
	 * , colonneDebut, colonneFin, longueurCalculee]
	 * pour un DescriptionChampHistoF07.<br/>
	 * [ordreChamps, intitule, nomenclature, champJava, typeJava, aNomenclature] 
	 * pour un DescriptionChampDarwinCsv.<br/>
	 * <br/>
	 * - retourne "" si this.entetesDescriptionMap est null.<br/>
	 * - retourne "" si this.entetesDescriptionMap est vide.<br/>
	 * <br/>
	 *
	 * @return String : une chaine de caractères décrivant 
	 * les en-têtes des colonnes dans la description du fichier 
	 * séparées par des virgules et encadrées par des crochets.<br/>
	 */
	String entetesDescriptionToString();
	

	
	/**
	 * method valeursDescriptionToString() :<br/>
	 * Fabrique une chaine de caractères comportant les
	 * en-têtes des colonnes dans la description concaténés
	 * avec la valeur dans la description pour un champ
	 * (ligne) donné, le tout séparé par des virgules.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * [ordreChamps = 1, colonnes = 1-3, longueur = 3, 
	 * intitule = Numéro de Département, nomenclature = calé à gauche
	 * , champJava = numDepartment, typeJava = Integer
	 * , aNomenclature = false, colonneDebut = 1, colonneFin = 3
	 * , longueurCalculee = 3] pour le 'Numéro de Département' 
	 * (1ère ligne) de la description d'un HistoNatF07.<br/>
	 * [ordreChamps = 2, intitule = route, 
	 * nomenclature = Route au format Isidor (ex : A0034b1 ou A0006)
	 * , champJava = route, typeJava = String, aNomenclature = false] 
	 * pour le champ 'route' 
	 * (2ème ligne) dans la description du fichier Darwin csv.<br/>
	 * <br/>
	 * - retourne "" si valeursDescriptionMap est null.<br/>
	 * - retourne "" si valeursDescriptionMap est vide.<br/>
	 * <br/>
	 * NECESSITE D'AVOIR FAIT desc.lireChamp(pTokens) 
	 * APRES L'INSTANCIATION DU Descripteur 
	 * pour que valeursDescriptionMap SOIT INSTANCIEE.<br/>
	 * <br/>
	 *
	 * @return une chaine de caractères décrivant 
	 * la description d'un champ : String.<br/>
	 */
	String valeursDescriptionToString();
	
	
	
	/**
	 * method tokensToString(
	 * String[] pTokens) :<br/>
	 * Permet d'afficher une ligne de la description de fichier
	 * - décomposée sous forme de tokens - sous forme de ligne 
	 * csv avec séparateur ';'.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * 1;1-3;3;Numéro de Département;calé à gauche;numDepartment;Integer;false;
	 *  pour le champ 'Numéro de Département' 
	 * (1ère ligne) dans la description du fichier HistonatF07.<br/>
	 * 2;route;Route au format Isidor (ex : A0034b1 ou A0006);route;String;
	 * false; pour le champ 'route' 
	 * (2ème ligne) dans la description du fichier Darwin csv.<br/>
	 * <br/>
	 * - retourne "" si pTokens est null.<br/>
	 * <br/>
	 * 
	 * @param pTokens : String[] : une ligne de la description
	 * de fichier décomposée en tokens.<br/>
	 * 
	 * @return String : ligne csv avec séparateur ';'.<br/>
	 */
	String tokensToString(
			final String[] pTokens);
	
	
	
	/**
	* method descriptionChampToString() :<br/>
	* Fabrique une chaine de caractères comportant tous
	* les éléments de description du champ séparés par des tabulations 
	* et avec un saut de ligne \n à la fin.<br/>
	* <br/>
	* Par exemple : <br/>
	* 1	1-3	3	Numéro de Département	calé à gauche	
	* numDepartment	Integer	false	1	3	3	\n 
	* pour le champ 'Numéro de Département' 
	* (1ère ligne) dans la description du fichier HistonatF07.<br/>
	* 2	route	Route au format Isidor (ex : A0034b1 ou A0006
	* )	route	String	false	\n pour le champ 'route' 
	* (2ème ligne) dans la description du fichier Darwin csv.<br/>
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
	 * method nettoyerString(
	 * String pString) :<br/>
	 * Sert à retirer des caractères incongrus dans la description
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
	String nettoyerString(
			final String pString);
	
		
	
	/**
	 * method getEntetesDescriptionMap() :<br/>
	 * Getter de la Map triée stockant :<br/>
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
	 * @return entetesDescriptionMap : SortedMap&lt;Integer, String&gt;.<br/>
	 * Map triée stockant :<br/>
	 * - Integer : l'ordre des colonnes d'une description de fichier.<br/>
	 * - String : le libellé (java) des colonnes de la description
	 * de fichier.<br/>
	 */
	SortedMap<Integer, String> getEntetesDescriptionMap();
	
	
	
	/**
	 * method setEntetesDescriptionMap(
	 * SortedMap&lt;Integer, String&gt; pColonnesDescriptionMap) :<br/>
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
			final SortedMap<Integer, String> pColonnesDescriptionMap);
	
	
	
	/**
	 * method getValeursDescriptionMap() :<br/>
	 * Getter de la Map triée stockant :<br/>
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
	 * (1ère ligne) dans la description du fichier HistonatF07.<br/>
	 * {1=2, 2=route, 3=Route au format Isidor 
	 * (ex : A0034b1 ou A0006), 4=route, 5=String, 6=false} 
	 * pour le champ 'route' (2ème ligne) 
	 * dans la description du fichier Darwin csv.<br/>
	 * <br/>
	 * NECESSITE D'AVOIR FAIT desc.lireChamp(pTokens) 
	 * APRES L'INSTANCIATION DU Descripteur 
	 * pour que valeursDescriptionMap SOIT INSTANCIEE.<br/>
	 * <br/>
	 * 
	 * @return valeursDescriptionMap :  SortedMap&lt;Integer, String&gt;.<br/>
	 * Map triée stockant :<br/>
	 * - Integer : l'ordre des colonnes d'une description de fichier.<br/>
	 * - String : les valeurs dans les colonnes de la description
	 * de fichier pour un champ (ligne de la description) donné.<br/>
	 */
	SortedMap<Integer, String> getValeursDescriptionMap();
	
	
	
	/**
	 * method setValeursDescriptionMap(
	 * SortedMap&lt;Integer, String&gt; pValeurDescriptionMap) :<br/>
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
	 * (1ère ligne) dans la description du fichier HistonatF07.<br/>
	 * {1=2, 2=route, 3=Route au format Isidor 
	 * (ex : A0034b1 ou A0006), 4=route, 5=String, 6=false} 
	 * pour le champ 'route' (2ème ligne) 
	 * dans la description du fichier Darwin csv.<br/>
	 * <br/>
	 * 
	 * @param pValeursDescriptionMap : SortedMap&lt;Integer, String&gt;.<br/>
	 * Map triée stockant :<br/>
	 * - Integer : l'ordre des colonnes d'une description de fichier.<br/>
	 * - String : les valeurs dans les colonnes de la description
	 * de fichier pour un champ (ligne de la description) donné.<br/>
	 */
	void setValeursDescriptionMap(
			final SortedMap<Integer, String> pValeursDescriptionMap);
	
	
	
	/**
	 * method getNombreColonnesObligatoires() :<br/>
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
	 * method setNombreColonnesObligatoires(
	 * int pNombreColonnesObligatoires) :<br/>
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
	void setNombreColonnesObligatoires(
			final int pNombreColonnesObligatoires);
	
	
		
	/**
	 * method getNomChampJava() :<br/>
	 * Getter du nom Java du champ concerné par cette description.<br/>
	 * <br/>
	 *
	 * @return : String : nom Java du champ concerné par cette description.<br/>
	 */
	String getNomChampJava();
	

	
	
	/**
	 * method getOrdreChamps() :<br/>
	 * Getter de l'Ordre du champ 1- based (ligne) dans la description.<br/>
	 * Par exemple, 'Numéro de Section' est le 
	 * deuxième champ dans la description du HistonatF07.<br/>
	 * <br/>
	 *
	 * @return ordreChamps : Integer.<br/>
	 */
	Integer getOrdreChamps();



	/**
	 * method setOrdreChamps(
	 * Integer pOrdreChamps) :<br/>
	 * Setter de l'Ordre du champ 1 - based (ligne) dans la description.<br/>
	 * Par exemple, 'Numéro de Section' est le 
	 * deuxième champ dans la description du HistonatF07.<br/>
	 * <br/>
	 *
	 * @param pOrdreChamps : Integer : valeur à passer à ordreChamps.<br/>
	 */
	void setOrdreChamps(
			final Integer pOrdreChamps);


	

	/**
	 * method getIntitule() :<br/>
	 * Getter de l'Intitulé du champ dans la description 
	 * comme 'Numéro de Section'.<br/>
	 * <br/>
	 *
	 * @return intitule : String.<br/>
	 */
	String getIntitule();



	/**
	 * method setIntitule(
	 * String pIntitule) :<br/>
	 * Setter de l'Intitulé du champ dans la description 
	 * comme 'Numéro de Section'.<br/>
	 * <br/>
	 *
	 * @param pIntitule : String : 
	 * valeur à passer à intitule.<br/>
	 */
	void setIntitule(
			final String pIntitule);



	/**
	 * method getNomenclature() :<br/>
	 * Getter de la Nomenclature éventuelle du champ comme :<br/>
	 * "3 - Cumul des deux sens. [sep]<br/>
	 *  4 - Sens unique P.R. croissants.[sep]  <br/>
	 *  5 - Sens unique P.R. Décroissants.".<br/>
	 * <br/>
	 *
	 * @return nomenclature : String.<br/>
	 */
	String getNomenclature();



	/**
	 * method setNomenclature(
	 * String pNomenclature) :<br/>
	 * Setter de la Nomenclature éventuelle du champ comme :<br/>
	 * "3 - Cumul des deux sens. [sep]<br/>
	 *  4 - Sens unique P.R. croissants.[sep]  <br/>
	 *  5 - Sens unique P.R. Décroissants.".<br/>
	 * <br/>
	 * <br/>
	 *
	 * @param pNomenclature : String : 
	 * valeur à passer à nomenclature.<br/>
	 */
	void setNomenclature(
			final String pNomenclature);



	/**
	 * method getChampJava() :<br/>
	 * Getter du Nom du champ dans l'application comme numDepartement 
	 * pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @return champJava : String.<br/>
	 */
	String getChampJava();



	/**
	 * method setChampJava(
	 * String pChampJava) :<br/>
	 * Setter du Nom du champ dans l'application comme numDepartement 
	 * pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @param pChampJava : String : valeur à passer à champJava.<br/>
	 */
	void setChampJava(
			final String pChampJava);



	/**
	 * method getTypeJava() :<br/>
	 * Getter du type Java du champ dans l'application 
	 * comme Integer pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @return typeJava : String.<br/>
	 */
	String getTypeJava();



	/**
	 * method setTypeJava(
	 * String pTypeJava) :<br/>
	 * Setter du type Java du champ dans l'application 
	 * comme Integer pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @param pTypeJava : String : valeur à passer à typeJava.<br/>
	 */
	void setTypeJava(
			final String pTypeJava);



	/**
	 * method isANomenclature() :<br/>
	 * Getter du boolean qui stipule si le champ 
	 * fait l'objet d'une nomenclature.<br/>
	 * <br/>
	 *
	 * @return aNomenclature : boolean.<br/>
	 */
	boolean isANomenclature();



	/**
	 * method setANomenclature(
	 * boolean pANomenclature) :<br/>
	 * Setter du boolean qui stipule si le champ 
	 * fait l'objet d'une nomenclature.<br/>
	 * <br/>
	 *
	 * @param pANomenclature : boolean : 
	 * valeur à passer à aNomenclature.<br/>
	 */
	void setANomenclature(
			final boolean pANomenclature);


	
	/**
	 * method getNomDescriptionChamp() :<br/>
	 * Fournit le nom de la description des champs.<br/>
	 * <br/>
	 * Par exemple : <br/>
	 * "Description des champs d'un fichier HIT".<br/>
	 *
	 * @return : String.<br/>
	 */
	String getNomDescriptionChamp();

	
	
} // FIN DE L'INTERFACE IDescriptionChamp.-----------------------------------
