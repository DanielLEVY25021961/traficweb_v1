package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription;

/**
 * INTERFACE IFournisseurJTableImpoDesc :<br/>
 * <p>
 * RESPONSABILITE : AFFICHER UNE DESCRIPTION DE FICHIER DANS UNE JTABLE.
 * </p>
 * 
 * <p>
 * Interface factorisant les méthodes garantissant 
 * qu'un objet qui l'implémente saura s'afficher dans une JTable (Swing).<br/>
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
 * @since 30 juin 2014
 *
 */
public interface IFournisseurJTableImpoDesc {


	
	/**
	* retourne l'en-tête de la p-ième colonne (1-based).<br/>
	* Methode utile pour l'affichage dans une JTable par exemple.<br/>
	* <br/>
	* Par exemple :<br/>
	* desc.getEnteteparColonne(2) == 'colonnes' 
	* pour un DescripteurChampHistoF07.<br/>
	* desc.getEnteteparColonne(2 )== 'intitule' 
	* pour un DescripteurChampDarwinCsv.<br/>
	* <br/>
	* - retourne null si this.descriptionChamp est null.<br/>
	* - retourne null si la p-ième colonne (1-based) n'existe pas.<br/>
	* <br/>
	* 
	* @param pI : int : le numéro (1-based) 
	* de la colonne dans le fichier de 
	* description dont on veut connaitre l'en-tête.<br/>
	* 
	* @return String : en-tête pour ce champ 
	* (ligne du fichier de description) à la
	* p-ième colonne (1-based).<br/>
	*/
	String fournirEnteteparColonne(int pI);

	
	
	/**
	 * retourne le contenu (valeur) de la p-ième colonne (1-based) 
	 * à la l-ième ligne.<br/>
	 * si pL=0, retourne un en-tête.<br/>
	 * Methode utile pour l'affichage dans une JTable par exemple.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * desc.getValeurparColonne(2) == '1-3' pour le champ 
	 * 'Numéro de Département' (1ère ligne) de la description 
	 * d'un HistoNatF07.<br/>
	 * desc.getValeurparColonne(2) == 'route' pour le champ 'route' 
	 * (2ème ligne) dans la description du fichier Darwin csv.<br/>
	 * <br/>
	 * - retourne null si valeursDescriptionMap est null.<br/>
	 * - retourne null si la l-ième ligne (1-based) n'existe pas 
	 * dans la description.<br/>
	 * - retourne null si la p-ième colonne (1-based) n'existe pas 
	 * dans la description.<br/>
	 * <br/>
	 * 
	 * @param pL : int : le numéro (1-based) 
	 * de la ligne dans le fichier de 
	 * description dont on veut connaitre le contenu.<br/>
	 * @param pI : int : le numéro (1-based) 
	 * de la colonne dans le fichier de 
	 * description dont on veut connaitre le contenu.<br/>
	 * 
	 * @return String : contenu pour ce champ 
	 * (ligne du fichier de description) à la
	 * l-ième ligne (1-based) et la p-ième colonne (1-based).<br/>
	 */
	String fournirValeurparLigneColonne(int pL, int pI);
	
	

} // Fin de L' INTERFACE IFournisseurJTableImpoDesc.-------------------------
