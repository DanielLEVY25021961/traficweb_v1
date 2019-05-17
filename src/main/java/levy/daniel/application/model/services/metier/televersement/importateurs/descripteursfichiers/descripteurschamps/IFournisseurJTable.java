package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps;

/**
 * class IFournisseurJTable :<br/>
 * <p>
 * RESPONSABILITE : AFFICHER UNE LIGNE DE DESCRIPTION DANS UNE JTABLE.<br/>
 * Interface factorisant les méthodes garantissant 
 * qu'un objet qui l'implémente saura s'afficher dans une JTable.<br/>
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
public interface IFournisseurJTable {
	
		
	/**
	* retourne l'en-tête de la p-ième colonne (1-based) 
	* d'un fichier de description.<br/>
	* Methode utile pour l'affichage dans une JTable (Swing) par exemple.<br/>
	* <br/>
	* Par exemple :<br/>
	* desc.fournirEnteteparColonne(2) == 'colonnes' 
	* pour un DescripteurChampHistoF07.<br/>
	* desc.fournirEnteteparColonne(2 )== 'intitule' 
	* pour un DescripteurChampDarwinCsv.<br/>
	* <br/>
	* - retourne null si <code>this.entetesDescriptionMap</code> est null.<br/>
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
	 * d'une ligne dans un fichier de description.<br/>
	 * Methode utile pour l'affichage dans une JTable par exemple.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * desc.fournirValeurparColonne(2) == '1-3' pour le champ 
	 * 'Numéro de Département' (1ère ligne) de la description 
	 * d'un HISTO_F07.<br/>
	 * desc.fournirValeurparColonne(2) == 'route' pour le champ 'route' 
	 * (2ème ligne) dans la description du fichier DARWIN_CSV.<br/>
	 * <br/>
	 * - retourne null si valeursDescriptionMap est null.<br/>
	 * - retourne null si la p-ième colonne (1-based) n'existe pas.<br/>
	 * <br/>
	 * 
	 * @param pI : int : le numéro (1-based) 
	 * de la colonne dans le fichier de 
	 * description dont on veut connaitre le contenu.<br/>
	 * 
	 * @return String : contenu pour ce champ 
	 * (ligne du fichier de description) à la
	 * p-ième colonne (1-based).<br/>
	 */
	String fournirValeurparColonne(int pI);
	


} // Fin de IFournisseurJTable.----------------------------------------------
