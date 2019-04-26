/**
 * class package-info :<br/>
 * Ce package comporte toutes les classes chargées 
 * de mettre à disposition de l'application 
 * les descriptions des fichiers à importer 
 * (HIT, Darwin csv, Darwin XML, Feor XML, ...).<br/>
 * <br/>
 * Une description de fichier est le document qui permet de 
 * savoir comment lire le fichier.<br/> 
 * <br/>
 * Par exemple, pour un HIT, la description du HIT commence par :<br/>
 * ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;aNomenclature;colonneDebut;colonneFin;longueurCalculee;<br/>
 * 1;1-3;3;Numéro de Département;cadré à gauche. Ex: dept 13 = 130;numDepartement;Integer;false;1;3;3;<br/>
 * 2;4-9;6;Numéro de Section;;numSection;String;false;4;9;6;<br/>
 * 3;10;1;Sens;3 - Cumul des deux sens. [sep] 4 - Sens unique P.R. croissants. [sep] 5 - Sens unique P.R. Décroissants.;sens;Integer;true;10;10;1;<br/>
 * ...................................................<br/>
 * <br/>
 * La description d'un Darwin.csv commence par :<br/>
 * ordreChamps;intitule;nomenclature;champJava;typeJava;aNomenclature;<br/>
 * 1;Identifiant de la section;Identifiant de la section;objetId;Integer;false;<br/>
 * 2;route;Route au format Isidor (ex : A0034b1 ou A0006);route;String;false;<br/>
 * 3;Département du PR Origine;Département du PR Origine  ('début');depPrd;String;false;<br/>
 * 4;Code Concession du PR Origine;Code Concession du PR Origine ('début');concessionPrd;String;false;<br/>
 * 5;PR Origine;PR Origine  ('début');prd;Integer;false;<br/>
 * ...................................................<br/>
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
package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers;