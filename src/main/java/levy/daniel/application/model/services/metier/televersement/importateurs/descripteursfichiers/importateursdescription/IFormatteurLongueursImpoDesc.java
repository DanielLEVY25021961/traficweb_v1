package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription;

import levy.daniel.application.apptechnic.exceptions.technical.impl.ExceptionImport;

/**
 * INTERFACE IFormatteurLongueursImpoDesc :<br/>
 * <p>
 * RESPONSABILITE : FORMATER LES DESCRIPTIONS DE FICHIER A LA CONSOLE.
 * </p>
 * 
 * <p>
 * Interface factorisant les méthodes garantissant 
 * qu'un objet qui l'implémente saura formater les tableaux à la console.
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
public interface IFormatteurLongueursImpoDesc {

	
	
	/**
	 * Tient à jour le tableau contenant les longueurs maxi
	 * des contenus dans la description de fichier
	 * (utile pour l'affichage formaté à la console 
	 * des descriptions de fichier).<br/>
	 * 
	 * @param pLongueurs : int[] : tableau des longueurs
	 * des contenus pour un champ (ligne) du fichier de
	 * description.<br/>
	 */
	void mettreAJourLongueursMax(int[] pLongueurs);
	

	
	/**
	 * Retourne le Max entre :<br/>
	 * - la nouvelle longueur 'pNouvelleLongueur',<br/>
	 * - une longueur int 'pAncienneLongueur' déjà existante.<br/>
	 *
	 * @param pNouvelleLongueur : int : la nouvelle longueur.<br/>
	 * @param pAncienneLongueur : int : La longueur par rapport
	 * à laquelle on veut comparer la nouvelle longueur.<br/>
	 * 
	 * @return nouvelleLongueur : int : 
	 * le Max(int pNouvelleLongueur, int pAncienneLongueur).<br/>
	 */
	int calculerLongueurMaxInt(
			int pNouvelleLongueur
				, int pAncienneLongueur);
	

	
	/**
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
	 * <b>Affiche la description de fichier formatée à la console</b>.
	 * <ul>
	 * <li>utilise <code><b>this.specificationChampsMap</b></code></li>
	 * <li>ATTENTION : faire importerDescription(...) 
	 * AVANT d'utiliser cette méthode.<br/></li>
	 * </ul>
	 *
	 * @return : String : Chaîne de caractères 
	 * formatée pour affichage à la console.<br/>
	 * 
	 * @throws ExceptionImport<br/> 
	 * @throws Exception 
	 */
	String toStringFormate() throws ExceptionImport, Exception;
	
	
	
	/**
	 * Affiche un tableau d'entiers.<br/>
	 * - Retourne une chaîne vide si le tableau
	 * passé en paramètre est null.<br/>
	 * 
	 * @param pTableauLongueurs : int[].<br/>
	 * 
	 * @return String.<br/>
	 */
	String tableauIntToString(int[] pTableauLongueurs);

	
	
} // FIN DE L'INTERFACE IFormatteurLongueursImpoDesc.------------------------
