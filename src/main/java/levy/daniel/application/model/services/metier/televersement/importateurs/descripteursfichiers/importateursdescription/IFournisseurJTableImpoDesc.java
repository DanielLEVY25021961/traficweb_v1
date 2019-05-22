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
	 * <b>retourne le contenu (valeur) de la p-ième colonne (1-based) 
	 * à la pL-ième ligne</b>.
	 * <ul>
	 * <li>utilise <code><b>this.specificationChampsMap</b></code></li>
	 * <li>retourne une valeur de l'en-tête si pL == 0.</li>
	 * <li>Methode utile pour l'affichage dans une JTable par exemple.</li>
	 * </ul>
	 * ATTENTION : faire importerDescription(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * desc.fournirValeurparLigneColonne(1, 2) retourne '1-3' 
	 * pour la 2ème colonne ('colonnes') du 1er champ 
	 * 'Numéro de Département' (1ère ligne) de la description 
	 * d'un HISTO_F07.<br/>
	 * desc.fournirValeurparLigneColonne(2, 2) retourne 'route' 
	 * pour la 2ème colonne ('intitule') du 2ème champ 'route' 
	 * (2ème ligne) dans la description du fichier DARWIN_CSV.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.specificationChampsMap</b></code> 
	 * est null.<br/>
	 * - retourne null si la l-ième ligne (1-based) n'existe pas 
	 * dans la description.<br/>
	 * - retourne null si la p-ième colonne (1-based) n'existe pas 
	 * dans la description.<br/>
	 * <br/>
	 * 
	 * @param pL : int : le numéro (1-based) 
	 * de la <b>ligne</b> dans le fichier de 
	 * description dont on veut connaitre le contenu.<br/>
	 * @param pI : int : le numéro (1-based) 
	 * de la <b>colonne</b> dans le fichier de 
	 * description dont on veut connaitre le contenu.<br/>
	 * 
	 * @return String : contenu pour ce champ 
	 * (ligne du fichier de description) à la
	 * l-ième ligne (1-based) et la p-ième colonne (1-based).<br/>
	 */
	String fournirValeurparLigneColonne(int pL, int pI);
	
	

} // Fin de L' INTERFACE IFournisseurJTableImpoDesc.-------------------------
