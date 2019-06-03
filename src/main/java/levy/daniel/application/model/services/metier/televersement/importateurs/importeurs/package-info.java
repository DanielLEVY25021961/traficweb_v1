/**
 * class package-info :<br/>
 * Ce package contient toutes les classes qui lisent les fichiers de données
 * (HIT, HISTO_F07, HISTO_F08, DARWIN_CSV, FEOR_XML, Histonat amélioré, ...) 
 * et les restituent en mémoire sous forme de 
 * <code><b>SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt;</b></code> 
 * avec :
 * <ul>
 * <li>Integer : le numéro de la ligne dans le fichier importé.</li>
 * <li>Integer : le numéro d'ordre du champ dans la description 
 * du fichier importé.</li>
 * <li>String : la valeur lue pour ce champ.</li>
 * </ul>
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
 * @since 26 juin 2014
 *
 */
package levy.daniel.application.model.services.metier.televersement.importateurs.importeurs;