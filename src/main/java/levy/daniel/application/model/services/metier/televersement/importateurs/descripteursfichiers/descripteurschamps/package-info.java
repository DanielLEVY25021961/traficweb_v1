/**
 * class package-info :
 * 
 * <p>
 * Package regroupant 
 * toutes les classes nécessaires à la <b>description d'un champ</b> 
 * dans un fichier 
 * (HIT, Histo_F07, HISTO_F08, DARWIN_CSV, Darwin XML, Feor XML, ...).
 * </p>
 * 
 * <p>
 * La description d'un champ est une encapsulation 
 * permettant de mettre à disposition de l'application 
 * toutes les valeurs décrivant un champ d'un fichier.
 * </p>
 * 
 * <p>
 * Par exemple, le champ décrit à la 2ème ligne de la description
 * d'un fichier HISTO_F07 a pour intitulé 'Numéro de Section', est
 * situé dans les  colonnes 4 à 9 des lignes du HISTO_F07, ...
 * </p>
 * 
 * <p>
 * La description d'un HISTO_F07 commence par :<br/>
 * ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;aNomenclature;aLexique;colonneDebut;colonneFin;longueurCalculee;<br/>
 * 1;1-3;3;Numéro de Département;cadré à gauche. Ex: dept 13 = 130;numDepartement;Integer;false;false;1;3;3;<br/>
 * 2;4-9;6;Numéro de Section;;numSection;String;false;false;4;9;6;<br/>
 * .......................................................<br/>
 * </p>
 * 
 * <p>
 * <table border="1">
 * <tr>
 * <th>ordreChamps</th> <th>colonnes</th> <th>longueur</th> <th>intitule</th> 
 * <th>nomenclature</th> <th>champJava</th> <th>typeJava</th> <th>aNomenclature</th>
 * <th>aLexique</th> <th>colonneDebut</th> <th>colonneFin</th> <th>longueurCalculee</th>
 * </tr>
 * <tr>
 * <td>1</td> <td>1-3</td> <td>3</td> <td>Numéro de Département</td> 
 * <td>cadré à gauche. Ex: dept 13 = 130</td> <td>numDepartement</td> <td>Integer</td> <td>false</td> 
 * <td>false</td> <td>1</td> <td>3</td> <td>3</td> 
 * </tr>
 * <tr>
 * <td>2</td> <td>4-9</td> <td>6</td> <td>Numéro de Section</td> 
 * <td> </td> <td>numSection</td> <td>String</td> <td>false</td> 
 * <td>false</td> <td>4</td> <td>9</td> <td>6</td> 
 * </tr>
 * </table>
 * </p>
 * 
 * <p>
 * La description d'un DARWIN_CSV commence par :<br/>
 * ordreChamps;intitule;nomenclature;champJava;typeJava;aNomenclature;aLexique;<br/>
 * 1;Identifiant de la section;Identifiant de la section;objetId;Integer;false;false;<br/>
 * 2;route;Route au format Isidor (ex : A0034b1 ou A0006);route;String;false;false;<br/>
 * 3;Département du PR Origine;Département du PR Origine ('début');depPrd;String;false;false;<br/>
 * 4;Code Concession du PR Origine;Code Concession du PR Origine ('début');concessionPrd;String;false;false;<br/>
 * 5;PR Origine;PR Origine ('début');prd;Integer;false;false;<br/>
 * .......................................................<br/>
 * </p>
 * 
 * <p>
 * <table border="1">
 * <tr>
 * <th>ordreChamps</th> <th>intitule</th> <th>nomenclature</th> <th>champJava</th> 
 * <th>typeJava</th> <th>aNomenclature</th> <th>aLexique</th> 
 * </tr>
 * <tr>
 * <td>1</td> <td>Identifiant de la section</td> <td>Identifiant de la section</td> <td>objetId</td> 
 * <td>Integer</td> <td>false</td> <td>false</td> 
 * </tr>
 * <tr>
 * <td>2</td> <td>route</td> <td>Route au format Isidor (ex : A0034b1 ou A0006)</td> <td>route</td> 
 * <td>String</td> <td>false</td> <td>false</td> 
 * </tr>
 * <tr>
 * <td>3</td> <td>Département du PR Origine</td> <td>Département du PR Origine ('début')</td> <td>depPrd</td> 
 * <td>String</td> <td>false</td> <td>false</td> 
 * </tr>
 * <tr>
 * <td>4</td> <td>Code Concession du PR Origine</td> <td>Code Concession du PR Origine ('début')</td> <td>concessionPrd</td> 
 * <td>String</td> <td>false</td> <td>false</td> 
 * </tr>
 * <tr>
 * <td>5</td> <td>PR Origine</td> <td>PR Origine ('début')</td> <td>prd</td> 
 * <td>Integer</td> <td>false</td> <td>false</td> 
 * </tr>
 * </table>
 * </p>
 * 
 * <p>
 * L'encapsulation <code><b>DescriptionChamp</b></code> comportera donc 
 * (pour le 2ème champ 'Numéro de Section' 
 * d'une description de HISTO_F07) :
 * <ul>
 * <li>dans la SortedMap&lt;Integer, String&gt; <b><i>entetesDescriptionMap</i></b> :<br/> 
 * [1-ordreChamps;2-colonnes;3-longueur;4-intitule;5-nomenclature;6-champJava;7-typeJava;8-aNomenclature;9-aLexique;10-colonneDebut;11-colonneFin;12-longueurCalculee;]<br/>
 * <li>dans la SortedMap&lt;Integer, String&gt; <i>valeursDescriptionMap</i> :</li>
 * [1-2;2-4-9;3-6;4-Numéro de Section;5-;6-numSection;7-String;8-false;9-false;10-4;11-9;12-6;]</li>
 * </ul>
 * </p>
 * 
 * <p>
 * La description d'un champ de fichier ASCII (comme HIT ou HISTO_F07) diffère 
 * de la description d'un champ de fichier csv (comme DARWIN_CSV) 
 * car on besoin :<br/>
 * - des colonnes de début et de fin localisant le champ dans un fichier ASCII 
 * (par exemple, le département est localisé entre 
 * les colonnes 1 et 3 dans un HIT ASCII),<br/>
 * - de l'ordre du champ dans un fichier csv (comme un DARWIN_CSV).<br/>
 * </p>
 * 
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