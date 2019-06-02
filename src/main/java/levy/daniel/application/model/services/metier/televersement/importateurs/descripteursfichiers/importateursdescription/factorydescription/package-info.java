/**
 * CLASSE package-info :<br/>
 * <p>
 * Ce package contient les classes chargées de 
 * <b>fournir à l'application des SINGLETONS des descriptions de fichier 
 * (description du HIT, description du HISTO_F07, ...)</b>.<br/>
 * </p>
 * 
 * <p>
 * délègue au bon {@link IImportateurDescription} 
 * la lecture de la description de fichier encodée en UTF-8
 * au format csv avec séparateur ';'
 * et de la rendre disponible pour toute l'application 
 * via une <code><b>SortedMap&lt;Integer,IDescriptionChamp&gt;</b></code>.<br/>
 * </p>
 * 
 * 
 * <p>
 * Une description de champ {@link IDescriptionChamp} 
 * est une encapsulation (pure fabrication) 
 * chargée de stocker tous les éléments caractérisant un champ défini 
 * dans une description de fichier.
 * </p>
 * 
 * <p>
 * Par exemple, le fichier de description du HISTO_F08 
 * stipule que le 3ème champ dans un fichier ASCII HISTO_F08 
 * est le <code>sens</code>.<br/>
 * le fichier de description du HISTO_F08 stipule également 
 * que le sens figure à la colonne 10 d'un HISTO_F08
 * , qu'il doit respecter une nomenclature, ...<br/>
 * IDescriptionChamp pour le sens encapsule toutes les données 
 * précisées pour ce champ dans la description de fichier du HISTO_F08.<br/>
 * </p>
 * 
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
 * @since 31 mai 2019
 *
 */
package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.factorydescription;