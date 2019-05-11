/**
 * class package-info :<br/>
 * <p>
 * Ce package contient toutes les classes 
 * nécessaires à la <b>lecture des nomenclatures et des lexiques</b> 
 * des <i>champs à valeurs contraintes</i> des fichiers 
 * (HIT, HISTO_F07, ...).
 * </p>
 * 
 * <p>
 *  Une <b>nomenclature</b> doit être un fichier csv avec séparateur ';' 
 *  encodé en UTF-8
 * sous forme [Integer, String] signifiant [Clé, Libellé] comme par exemple 
 * pour le SENS dans un HIT :<br/>
 * 1; sens des PR croissants pour route à 2 sens;<br/>
 * 2; sens des PR décroissants pour route à 2 sens;<br/>
 * 3; sens confondus pour une route à 2 sens;<br/>
 * ...........................................<br/>
 *</p>
 *
 * <p>
 * La <b>clé est donc toujours entière dans une nomenclature</b>.
 * </p>
 * 
 * <p>
 * <b><span style="text-decoration:underline;">
 * Diagramme de classe du ImportateurNomenclature : 
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../../../../../javadoc/images/model/services/metier/televersement/importateurs/descripteursfichiers/nomenclatures/diagramme_de_classes_ImporteurNomenclature.png" 
 * alt="Diagramme de classe du ImportateurNomenclature" />
 * </p>
 * 
 * <br/>
 * 
 * <p>
 * Un <b>lexique</b> doit être un fichier csv avec séparateur ';' 
 * sous forme [String, String] signifiant [Clé, Libellé] comme par exemple 
 * pour le PROFIL EN TRAVERS dans un HISTO_F07 :<br/>
 * 1V;chaussée unique 1 voie;<br/>
 * 2V<7;chaussée unique 2 voies < 7m;<br/>
 * 2V>=7;chaussée unique 2 voies >= 7m;<br/>
 * ...........................................<br/>
 * </p>
 * 
 * <p>
 * La <b>clé est donc toujours String dans un lexique</b>.
 * </p>
 *  
 * <p>
 * <b><span style="text-decoration:underline;">
 * Diagramme de classe du ImportateurLexique : 
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../../../../../javadoc/images/model/services/metier/televersement/importateurs/descripteursfichiers/nomenclatures/diagramme_de_classes_ImporteurLexique.png" 
 * alt="Diagramme de classe du ImportateurLexique" />
 * </p>
 * 
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
package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures;