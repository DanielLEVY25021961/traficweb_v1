package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg;



/**
 * Enumeration EnumTypesValidation :<br/>
 * .<br/>
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
 * @since 7 déc. 2017
 *
 */
public enum EnumTypesValidation {

	/**
	 * RENSEIGNE : EnumTypesValidation :<br/>
	 * Attribut devant être renseigné.<br/>
	 */
	RENSEIGNE(1, "non renseigné")
	
	, /**
	 * LITTERAL : EnumTypesValidation :<br/>
	 * Attribut devant être entièrement littéral avec des lettres 
	 * de l'alphabet et des caractères spéciaux (-, _, ...)
	 * (pas de chiffres).<br/>
	 */
	LITTERAL(2, "champ littéral (lettres et caractères spéciaux)")
	
	, /**
	 * LITTERAL_ALPHABETIQUE : EnumTypesValidation :<br/>
	 * Attribut devant être entièrement littéral avec uniquement 
	 * des lettres de l'alphabet ([a-z[A-Z]).<br/>
	 */
	LITTERAL_ALPHABETIQUE(3, "champ littéral (lettres exclusivement)")
	
	, /**
	 * NUMERIQUE : EnumTypesValidation :<br/>
	 * Attribut devant être entièrement numérique (pas de lettres).<br/>
	 */
	NUMERIQUE(4, "numérique")
	
	, /**
	 * LONGUEUR : EnumTypesValidation :<br/>
	 * Attribut devant avoir une longueur bornée.<br/>
	 */
	LONGUEUR(5, "longueur bornée")
	
	, /**
	 * MOTIF : EnumTypesValidation :<br/>
	 * Attributs devant suivre un motif (Regex).<br/>
	 */
	MOTIF(6, "motif regex")
	
	, /**
	 * NOMENCLATURE : EnumTypesValidation :<br/>
	 * Attributs devant se conformer à une nomenclature.<br/>
	 */
	NOMENCLATURE(7, "nomenclature")
	
	, /**
	 * FOURCHETTE : EnumTypesValidation :<br/>
	 * Attribut devant avoir une valeur numérique bornée.<br/>
	 */
	FOURCHETTE(8, "valeur numérique bornée");
    

	
	/**
	 * type de validation sous forme de numéro.<br/>
	 */
	private final int numero;
	
	
    /**
     * label du type de validation.<br/>
     */
    private final String typeString;

    
    
     /**
     * CONSTRUCTEUR COMPLET.<br/>
     *
     * @param pNumero : int :
     * @param pTypeString : String : 
     */
    EnumTypesValidation(
    		final int pNumero, final String pTypeString) {
        this.numero = pNumero;
        this.typeString = pTypeString;
    } // Fin de CONSTRUCTEUR COMPLET.______________________________________


    
	/**
	 * Getter du numéro.<br/>
	 *
	 * @return this.numero : int.<br/>
	 */
	public final int getNumero() {
		return this.numero;
	} // Fin de getNumero()._______________________________________________



	/**
	 * Getter du label.<br/>
	 *
	 * @return this.typeString : String.<br/>
	 */
	public final String getTypeString() {
		return this.typeString;
	} // Fin de getTypeString().___________________________________________
    
  
	
} // Fin de Enumeration EnumTypesValidation.-------------------------------------
