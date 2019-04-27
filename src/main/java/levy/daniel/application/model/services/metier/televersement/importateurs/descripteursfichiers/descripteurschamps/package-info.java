/**
 * class package-info :<br/>
 * Package regroupant 
 * toutes les classes nécessaires à la description d'un champ 
 * dans un fichier 
 * (HIT, HistonatF07, Darwin csv, Darwin XML, Feor XML, ...).<br/>
 * <br/>
 * La description d'un champ est une encapsulation 
 * permettant de mettre à disposition de l'application 
 * toutes les valeurs décrivant un champ d'un fichier.<br/>
 * <br/>
 * Par exemple, le champ décrit à la 2ème ligne de la description
 * d'un fichier HistoNatF07 a pour intitulé 'Numéro de Section', est
 * situé dans les  colonnes 4 à 9 des lignes du HistoNatF07, ...<br/>
 * <br/>
 * La description d'un HistonatF07 commence par :<br/>
 * ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;aNomenclature;colonneDebut;colonneFin;longueurCalculee;<br/>
 * 1;1-3;3;Numéro de Département;cadré à gauche. Ex: dept 13 = 130;numDepartement;Integer;false;1;3;3;<br/>
 * 2;4-9;6;Numéro de Section;;numSection;String;false;4;9;6;<br/>
 * .......................................................<br/>
 * <br/>
 * La description d'un Darwin.csv commence par :<br/>
 * ordreChamps;intitule;nomenclature;champJava;typeJava;aNomenclature;<br/>
 * 1;Identifiant de la section;Identifiant de la section;objetId;Integer;false;<br/>
 * 2;route;Route au format Isidor (ex : A0034b1 ou A0006);route;String;false;<br/>
 * 3;Département du PR Origine;Département du PR Origine  ('début');depPrd;String;false;<br/>
 * 4;Code Concession du PR Origine;Code Concession du PR Origine ('début');concessionPrd;String;false;<br/>
 * 5;PR Origine;PR Origine  ('début');prd;Integer;false;<br/>
 * .......................................................<br/>
 * <br/>
 * L'encapsulation DescriptionChamp comportera donc 
 * (pour le 2ème champ 'Numéro de Section' 
 * d'une description de Histonat F07) :<br/>
 * - dans la SortedMap&lt;Integer, String&gt; <i>entetesDescriptionMap</i> :<br/> 
 * [1-ordreChamps;2-colonnes;3-longueur;4-intitule;5-nomenclature;6-champJava;7-typeJava;8-aNomenclature;9-colonneDebut;10-colonneFin;11-longueurCalculee;]<br/>
 * - dans la SortedMap&lt;Integer, String&gt; <i>valeursDescriptionMap</i> :<br/>
 * [1-2;2-4-9;3-6;4-Numéro de Section;5-;6-numSection;7-String;8-false;9-4;10-9;11-6;]<br/>
 * <br/>
 * La description d'un champ de fichier ASCII diffère 
 * de la description d'un champ de fichier csv car on besoin :<br/>
 * - des colonnes de début et de fin localisant le champ dans un fichier ASCII 
 * (par exemple, le département est localisé entre 
 * les colonnes 1 et 3 dans un HIT ASCII),<br/>
 * - de l'ordre du champ dans un fichier csv.<br/>
 * <br/>
 * 
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
package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps;