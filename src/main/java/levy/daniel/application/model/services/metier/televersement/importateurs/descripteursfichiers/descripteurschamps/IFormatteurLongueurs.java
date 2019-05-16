package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps;

import java.util.SortedMap;

/**
 * class IFormatteurLongueurs :<br/>
 * <p>
 * RESPONSABILITE : FORMATTER LES DESCRIPTIONS A LA CONSOLE.<br/>
 * Interface factorisant les méthodes garantissant 
 * qu'un objet qui l'implémente saura formater les tableaux à la console.<br/>
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
 * @since 29 juin 2014
 *
 */
public interface IFormatteurLongueurs {
	

	
	/**
	 * Retourne le tableau des longueurs des en-têtes
	 * de chaque colonne de la description de fichier.<br/>
	 * <br/>
	 * Méthode utile pour formater les sorties à la console 
	 * (alignement des tableaux).<br/>
	 * <br/>
	 * -Retourne null si entetesDescriptionMap est null.<br/>
	 * <br/>
	 * 
	 * @return longueursEnTableau : int[].<br/>
	 */
	int[] getLongueursEnTableauEnTetes();
	
	
	
	/**
	 * Retourne le tableau des longueurs des contenus
	 * de chaque colonne de la description de fichier.<br/>
	 * <br/>
	 * Méthode utile pour formater les sorties à la console 
	 * (alignement des tableaux).<br/>
	 * <br/>
	 * - retourne null si entetesDescriptionMap est null.<br/>
	 * - retourne null si longueursDescriptionMap est null.<br/>
	 * <br/>
	 * 
	 * @return longueursEnTableau : int[].<br/>
	 */
	int[] getLongueursEnTableauValeurs();
	
	
	/**
	 * Affichage à la console du tableau des longueurs des en-têtes.<br/>
	 * <br/>
	 * Méthode utile pour formater les sorties à la console 
	 * (alignement des tableaux).<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * [Longueur de 'ordreChamps' = 11, Longueur de 'colonnes' = 8, 
	 * Longueur de 'longueur' = 8, Longueur de 'intitule' = 8
	 * , Longueur de 'nomenclature' = 12, Longueur de 'champJava' = 9
	 * , Longueur de 'typeJava' = 8, Longueur de 'aNomenclature' = 13
	 * , Longueur de 'aLexique' = 13
	 * , Longueur de 'colonneDebut' = 12, Longueur de 'colonneFin' = 10
	 * , Longueur de 'longueurCalculee' = 16] 
	 * pour un DescriptionChampHistoF07.<br/>
	 * [Longueur de 'ordreChamps' = 11, Longueur de 'intitule' = 8
	 * , Longueur de 'nomenclature' = 12, Longueur de 'champJava' = 9
	 * , Longueur de 'typeJava' = 8, Longueur de 'aNomenclature' = 13
	 * , Longueur de 'aLexique' = 13] 
	 * pour un DescriptionChampDarwinCsv.<br/>
	 * <br/>
	 * - Retourne null si entetesDescriptionMap est null.<br/>
	 * - retourne null si this.getLongueursEnTableauEnTetes() 
	 * retourne null.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String afficherTableauLongueurEnTetes();
	
	
	
	/**
	 * Affiche à la console du tableau des longueurs des valeurs.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * [Longueur du contenu dans 'ordreChamps' = 1
	 * , Longueur du contenu dans 'colonnes' = 3
	 * , Longueur du contenu dans 'longueur' = 1
	 * , Longueur du contenu dans 'intitule' = 21
	 * , Longueur du contenu dans 'nomenclature' = 13
	 * , Longueur du contenu dans 'champJava' = 13
	 * , Longueur du contenu dans 'typeJava' = 7
	 * , Longueur du contenu dans 'aNomenclature' = 5
	 * , Longueur du contenu dans 'aLexique' = 5
	 * , Longueur du contenu dans 'colonneDebut' = 1
	 * , Longueur du contenu dans 'colonneFin' = 1
	 * , Longueur du contenu dans 'longueurCalculee' = 1] 
	 * pour le champ 'Numéro de Département' 
	 * (1ère ligne) dans la description du fichier HISTO_F07.<br/>
	 * [Longueur du contenu dans 'ordreChamps' = 1
	 * , Longueur du contenu dans 'intitule' = 5
	 * , Longueur du contenu dans 'nomenclature' = 46
	 * , Longueur du contenu dans 'champJava' = 5
	 * , Longueur du contenu dans 'typeJava' = 6
	 * , Longueur du contenu dans 'aNomenclature' = 5
	 * , Longueur du contenu dans 'aLexique' = 5] 
	 * pour le champ 'route' (2ème ligne) 
	 * dans la description du fichier DARWIN_CSV.<br/>
	 *  <br/>
	 * Méthode utile pour formater les sorties à la console 
	 * (alignement des tableaux).<br/>
	 * <br/>
	 * - Retourne null si entetesDescriptionMap est null.<br/>
	 * - retourne null 
	 * si this.getLongueursEnTableauValeurs() retourne null.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String afficherTableauLongueurValeurs();
	
	
	
	/**
	 * Getter de la Map triée fournissant pour un champ donné 
	 * de la description de fichier : <br/>
	 * - Integer : le numéro de la colonne dans la description,<br/>
	 * - Integer : la longueur du contenu.<br/>
	 * <br/>
	 * Map utile pour formater les sorties à la console 
	 * (alignement des tableaux).<br/>
	 * <br/>
	 * 
	 * @return longueursDescriptionMap : SortedMap&lt;Integer, Integer&gt;.<br/>
	 */
	SortedMap<Integer, Integer> getLongueursDescriptionMap();
	
	
	
	/**
	 * Setter de la Map triée fournissant pour un champ donné 
	 * de la description de fichier : <br/>
	 * - Integer : le numéro de la colonne dans la description,<br/>
	 * - Integer : la longueur du contenu.<br/>
	 * <br/>
	 * Map utile pour formater les sorties à la console 
	 * (alignement des tableaux).<br/>
	 * <br/>
	 * 
	 * @param pLongueursDescriptionMap : SortedMap&lt;Integer, Integer&gt;.<br/>
	 */
	void setLongueursDescriptionMap(
			SortedMap<Integer, Integer> pLongueursDescriptionMap);
	

	
	/**
	 * Fabrique une chaine de caractères comportant les
	 * longueurs des contenus des colonnes dans la description 
	 *  pour un champ (ligne) donné.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * [1, 3, 1, 21, 13, 13, 7, 5, 5, 1, 1, 1] 
	 * pour le champ 'Numéro de Département' 
	 * (1ère ligne) dans la description du fichier HISTO_F07.<br/>
	 * [1, 5, 46, 5, 6, 5, 5] pour le champ 'route' 
	 * (2ème ligne) dans la description du fichier DARWIN_CSV.<br/>
	 * <br/>
	 * Méthode utile pour formater les sorties à la console 
	 * (alignement des tableaux).<br/>
	 * <br/>
	 * NECESSITE D'AVOIR FAIT desc.lireChamp(pTokens) 
	 * APRES L'INSTANCIATION DU Descripteur 
	 * pour que longueursDescriptionMap SOIT INSTANCIEE.<br/>
	 * <br/>
	 * - retourne "" si longueursDescriptionMap est null.<br/>
	 * - retourne "" si longueursDescriptionMap est vide.<br/>
	 * <br/>
	 * 
	 * @return String : les longueurs des contenus.<br/>
	 */
	String longueursMapToString();
	


} // FIN DE L'INTERFACE IFormatteurLongueurs.--------------------------------
