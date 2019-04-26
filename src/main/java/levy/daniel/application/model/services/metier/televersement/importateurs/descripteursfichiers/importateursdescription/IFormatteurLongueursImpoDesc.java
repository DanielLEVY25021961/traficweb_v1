package levy.daniel.application.metier.importateurs.descripteursfichiers.importateursdescription;

import levy.daniel.application.exceptions.technical.impl.ExceptionImport;

/**
 * class IFormatteurLongueursImpoDesc :<br/>
 * RESPONSABILITE : FORMATTER LES DESCRIPTIONS A LA CONSOLE.<br/>
 * Interface factorisant les méthodes garantissant 
 * qu'un objet qui l'implémente saura formatter les tableaux à la console.<br/>
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
public interface IFormatteurLongueursImpoDesc {
	
	/**
	 * method mettreAJourLongueursMax(
	 * int[] pLongueurs) :<br/>
	 * Tient à jour le tableau contenant les longueurs maxi
	 * des contenus dans la description de fichier
	 * (utile pour l'affichage formatté à la console des descriptions).<br/>
	 * <br/>
	 * 
	 * @param pLongueurs : int[] : tableau des longueurs
	 * des contenus pour un champ (ligne) du fichier de
	 * description.<br/>
	 */
	void mettreAJourLongueursMax(
			final int[] pLongueurs);
	

	
	/**
	 * method calculerLongueurMaxInt(
	 * int pNouvelleLongueur, int pAncienneLongueur) :<br/>
	 * Retourne le Max entre :<br/>
	 * - la nouvelle longueur 'pNouvelleLongueur',<br/>
	 * - une longueur int 'pAncienneLongueur' déjà existante.<br/>
	 * <br/>
	 *
	 * @param pNouvelleLongueur : int : la nouvelle longueur.<br/>
	 * @param pAncienneLongueur : int : La longueur par rapport
	 * à laquelle on veut comparer la nouvelle longueur.<br/>
	 * 
	 * @return nouvelleLongueur : int : 
	 * le Max(int pNouvelleLongueur, int pAncienneLongueur).<br/>
	 */
	int calculerLongueurMaxInt(
			final int pNouvelleLongueur
				, final int pAncienneLongueur);
	

	
	/**
	 * method getLongueursMax() :<br/>
	 * Getter du tableau des longueurs
	 * maximales des contenus des champs dans une description.<br/>
	 * Utile pour l'affichage avec des colonnes de taille fixe
	 * à la console.<br/>
	 *
	 * @return int[] : le tableau des longueurs
	 * maximales des contenus des champs dans une description.<br/>
	 */
	int[] getLongueursMax();
	
	
	
	/**
	 * method toStringFormatte() :<br/>
	 * Affiche la description formattée à la console.<br/>
	 * <br/>
	 *
	 * @return : String : Chaîne de caractères 
	 * formattée pour affichage à la console.<br/>
	 * 
	 * @throws ExceptionImport<br/> 
	 */
	String toStringFormatte() throws ExceptionImport;
	
	
	
	/**
	 * method tableauIntToString(
	 * int[] pTableauLongueurs) :<br/>
	 * Affichage d'un tableau d'entiers.<br/>
	 * - Retourne une chaîne vide si le tableau
	 * passé en paramètre est null.<br/>
	 * <br/>
	 * 
	 * @param pTableauLongueurs : int[].<br/>
	 * 
	 * @return String.<br/>
	 */
	String tableauIntToString(
			final int[] pTableauLongueurs);

} // FIN DE L'INTERFACE IFormatteurLongueursImpoDesc.------------------------
