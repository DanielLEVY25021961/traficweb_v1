/**
 * class package-info :<br/>
 * Ce package contient tous les ImporteursNomenclature concrets.<br/>
 * <br/>
 *
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * <p>
 * <code> // Instanciation d'un ImporteurLexique</code><br/>
 * <code><b>IImporteurLexique IMPORTEUR_LEXIQUE = new ImporteurLexique();</b></code><br/>
 * <code> // Import du fichier de lexique lexiqueFile</code><br/>
 * <code><b>Map&lt;String, String&gt; resultat = IMPORTEUR_LEXIQUE.importerLexiqueEnUtf8(lexiqueFile);</b></code><br/>
 * <code> // Récupération du Set de clés possibles</code><br/>
 * <code><b>Set&lt;String&gt; clesPossiblesSet = IMPORTEUR_LEXIQUE.getClesPossiblesSet();</b></code><br/>
 * <code> // Génère le lexique importé dans le fichier nomenclatureGenereeFile avec en-tête et encodée en UTF-8.</code><br/>
 * <code><b>IMPORTEUR_LEXIQUE.genererNomenclatureCsvFile(true, nomenclatureGenereeFile, charsetUtf8);</b></code><br/>
 * </p>
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
 * @since 16 juil. 2014
 *
 */
package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.impl;