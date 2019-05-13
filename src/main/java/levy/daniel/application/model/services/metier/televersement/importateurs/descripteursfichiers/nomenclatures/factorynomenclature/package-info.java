/**
 * class package-info :<br/>
 * <p>
 * Ce package contient toutes les <b>Factory</b> 
 * chargées de fournir les <b>nomenclatures</b> et <b>lexiques</b> 
 * des champs à valeurs contraintes des divers fichiers 
 * (HIT, HISTO_F07, HISTO_F08, DARWIN_CSV, ...) 
 * sous forme de SINGLETONS 
 * à l'ensemble de l'application.<br/>
 * Les Factory permettent de <b>récupérer la bonne nomenclature 
 * à l'aide du numéro d'ordre du champ</b> à valeurs contraintes dans 
 * la description du fichier.<br/>
 * </p>
 *
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * <p>
 * <code> // instanciation d'une FACTORY.</code><br/>
 * <code><b>IFactoryNomenclature FACTORY_NOMENCLATURE_HIT = new FactoryNomenclatureHit();</b></code><br/>
 * <code> // Récupération de la nomenclature de la CATEGORIE ADMINISTRATIVE DE LA ROUTE 
 * dans un fichier HIT (11ème champ dans la description du HIT)</code><br/>
 * <code><b>Map&lt;Integer, String&gt; resultatCatAdminRoute = FACTORY_NOMENCLATURE_HIT.getNomenclatureMap(11);</b></code><br/>
 * </p>
 * <br/>
 *
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
 * @since 2 août 2014
 *
 */
package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.factorynomenclature;
