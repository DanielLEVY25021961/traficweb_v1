package levy.daniel.application.model.metier.televersement;


/**
 * ENUMERATION EnumTypeFichierDonnees :<br/>
 * Enumération chargée de gérer tous les 
 * types de fichiers de données possibles.<br/>
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
 * @since 13 juin 2019
 *
 */
public enum EnumTypeFichierDonnees {
	
	/**
	 * HIT.
	 */
	HIT("HIT", "FICHIER HIT FOURNI PAR LES DIR (RRN NON CONCEDE)")
	
	, /**
	 * DARWIN_CSV.
	 */
	DARWIN_CSV("DARWIN_CSV", "FICHIER DARWIN au format CSV FOURNI PAR LES AUTOROUTIERS (RRN CONCEDE)");
	
	
	
	/**
	 * nom court du type de fichier de données.
	 */
	private final String nomCourt;
	
	/**
	 * nom complet du type de fichier de données.
	 */
	private final String nomComplet;
	

	
    /**
    * CONSTRUCTEUR COMPLET.<br/>
    *
    * @param pNomCourt : String : nom court du type de fichier de données.
    * @param pNomComplet : String : nom complet du type de fichier de données.
    */
	EnumTypeFichierDonnees(
   		final String pNomCourt, final String pNomComplet) {
       this.nomCourt = pNomCourt;
       this.nomComplet = pNomComplet;
   } // Fin de CONSTRUCTEUR COMPLET.______________________________________


	
	/**
	 * Getter du nom court du type de fichier de données.
	 *
	 * @return this.nomCourt : String.<br/>
	 */
	public final String getNomCourt() {
		return this.nomCourt;
	} // Fin de getNomCourt()._____________________________________________


	
	/**
	 * Getter du nom complet du type de fichier de données.
	 *
	 * @return this.nomComplet : String.<br/>
	 */
	public final String getNomComplet() {
		return this.nomComplet;
	} // Fin de getNomComplet().___________________________________________

	
	
} // FIN DE L'ENUMERATION EnumTypeFichierDonnees.----------------------------
