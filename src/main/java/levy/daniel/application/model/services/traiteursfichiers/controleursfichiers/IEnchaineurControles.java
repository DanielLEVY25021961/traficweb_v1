package levy.daniel.application.model.services.traiteursfichiers.controleursfichiers;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Locale;
import java.util.SortedMap;

import levy.daniel.application.model.services.traiteursfichiers.IEnregistreurRapport;
import levy.daniel.application.model.services.traiteursfichiers.IRapporteurControle;


/**
 * INTERFACE IEnchaineurControles :<br/>
 * Abstraction qui garantit que :<br/>
 * - Tout enchaînement de contrôles est effectué à une 'dateContrôle'. 
 * La classe calcule automatiquement 'dateControleStringFormatee' 
 * connaissant dateControle.<br/>
 * - Tout enchaînement de contrôles est effectué par un utilisateur (user) 
 * dont on connait le nom 'userName'. La classe remplit automatiquement 
 * userName avec 'Administrateur' si on ne lui fournit pas de userName.<br/>
 * - Tout enchaînement de contrôles s'applique sur un File 'fichier'. 
 * La classe calcule automatiquement 'nomFichier' connaissant fichier.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * levy.daniel.application.IExportateurCsv.<br/>
 * levy.daniel.application.IExportateurJTable.<br/>
 * levy.daniel.application.IResetable.<br/>
 * levy.daniel.application.metier.controles.rapportscontroles.LigneRapport.<br/>
 * levy.daniel.application.metier.controles.IRapporteurControle.<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.rapportsenregistrements.LigneRapportEnregistrement.<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 6 mars 2016
 *
 */
public interface IEnchaineurControles 
			extends IRapporteurControle, IEnregistreurRapport {
	
	/**
	 * new Locale("fr", "FR").<br/>
	 */
	Locale LOCALE_FR_FR = new Locale("fr", "FR");
		
	/**
	 * Charset.forName("UTF-8").<br/>
	 * Eight-bit Unicode (or UCS) Transformation Format.<br/> 
	 */
	Charset CHARSET_UTF8 
		= Charset.forName("UTF-8");
		
	/**
	 * Charset.forName("US-ASCII").<br/>
	 * Seven-bit ASCII, a.k.a. ISO646-US, 
	 * a.k.a. the Basic Latin block of the Unicode character set.<br/>
	 * standard américain.<br/>
	 * American Standard Code for Information Interchange.<br/> 
	 * 128 caractères imprimables.<br/> 
	 * Sert à écrire l’anglo-américain.<br/> 
	 * Ne permet pas d’écrire les langues européennes 
	 * qui utilisent des lettres avec diacritiques (accents, cédille, ...).<br/> 
	 * On ne peut pas écrire en français avec de l’ASCII.<br/>
	 */
	Charset CHARSET_US_ASCII 
		= Charset.forName("US-ASCII");
		
	/**
	 * Charset.forName("ISO-8859-1").<br/>
	 * Latin1.<br/>
	 * 191 caractères imprimables.<br/> 
	 * Permet d’écrire la plupart des langues d’Europe de l’Ouest.<br/> 
	 * Presque tous les caractères du français y sont (manquent le œ et €).<br/>
	 */
	Charset CHARSET_ISO_8859_1 
		= Charset.forName("ISO-8859-1");
		
	/**
	 * Charset.forName("ISO-8859-1").<br/>
	 * Latin1.<br/>
	 * 191 caractères imprimables.<br/> 
	 * Permet d’écrire la plupart des langues d’Europe de l’Ouest.<br/> 
	 * Presque tous les caractères du français y sont (manquent le œ et €).<br/>
	 */
	Charset CHARSET_LATIN1 
		= Charset.forName("ISO-8859-1");
		
	/**
	 * Charset.forName("ISO-8859-2").<br/>
	 * <br/>
	 */
	Charset CHARSET_ISO_8859_2 
		= Charset.forName("ISO-8859-2");
		
	/**
	 * Charset.forName("ISO-8859-9").<br/>
	 * Latin Alphabet No. 5<br/>
	 */
	Charset CHARSET_ISO_8859_9 
		= Charset.forName("ISO-8859-9");
		
	/**
	 * Charset.forName("ISO-8859-15").<br/>
	 * Latin9, Latin Alphabet No. 9.<br/>
	 * modifie légèrement ISO-8859-1.<br/> 
	 * Ajout du caractère œ et du symbole monétaire € (Euro) entre autres.<br/>
	 */
	Charset CHARSET_ISO_8859_15 
		= Charset.forName("ISO-8859-15");
		
	/**
	 * Charset.forName("ISO-8859-15").<br/>
	 * Latin9, Latin Alphabet No. 9.<br/>
	 * modifie légèrement ISO-8859-1.<br/> 
	 * Ajout du caractère œ et du symbole monétaire € (Euro) entre autres.<br/>
	 */
	Charset CHARSET_LATIN9 
		= Charset.forName("ISO-8859-15");
		
	/**
	 * Charset.forName("windows-1252").<br/>
	 * ANSI, CP1252.<br/>
	 * 218 caractères imprimables.<br/>
	 * extension d’ISO-8859-1, qui rajoute quelques caractères: œ, € (euro), 
	 * guillemets anglais (« »), points de suspension (...)
	 * , signe «pour mille» (‰), 
	 * tirets cadratin (— = \u2014 en unicode ) et demi-cadratin (–), ...<br/>
	 */
	Charset CHARSET_WINDOWS_1252 
		= Charset.forName("windows-1252");
		
	/**
	 * Charset.forName("windows-1252").<br/>
	 * ANSI, CP1252.<br/>
	 * 218 caractères imprimables.<br/>
	 * extension d’ISO-8859-1, qui rajoute quelques caractères: œ, € (euro), 
	 * guillemets anglais (« »), points de suspension (...)
	 * , signe «pour mille» (‰), 
	 * tirets cadratin (— = \u2014 en unicode ) et demi-cadratin (–), ...<br/>
	 */
	Charset CHARSET_ANSI
		= Charset.forName("windows-1252");
		
	/**
	 * Charset.forName("windows-1252").<br/>
	 * ANSI, CP1252.<br/>
	 * 218 caractères imprimables.<br/>
	 * extension d’ISO-8859-1, qui rajoute quelques caractères: œ, € (euro), 
	 * guillemets anglais (« »), points de suspension (...)
	 * , signe «pour mille» (‰), 
	 * tirets cadratin (— = \u2014 en unicode ) et demi-cadratin (–), ...<br/>
	 */
	Charset CHARSET_CP1252
		= Charset.forName("windows-1252");
		
	/**
	 * Charset IBM-850.<br/>
	 * Cp850, MS-DOS Latin-1.<br/>
	 */
	Charset CHARSET_IBM850
		= Charset.forName("IBM-850");
		
	/**
	 * SAUTDELIGNE_UNIX : String :<br/>
	 * Saut de ligne généré par les éditeurs Unix.<br/>
	 * "\n" (Retour Ligne = LINE FEED (LF)).
	 */
	String SAUTDELIGNE_UNIX = "\n";
		
	/**
	 * SAUTDELIGNE_MAC : String :<br/>
	 * Saut de ligne généré par les éditeurs Mac.<br/>
	 * "\r" (Retour Chariot RC = CARRIAGE RETURN (CR))
	 */
	String SAUTDELIGNE_MAC = "\r";
		
	/**
	 * SAUTDELIGNE_DOS_WINDOWS : String :<br/>
	 * Saut de ligne généré par les éditeurs DOS/Windows.<br/>
	 * "\r\n" (Retour Chariot RC + Retour Ligne LF).
	 */
	String SAUTDELIGNE_DOS_WINDOWS = "\r\n";
	
	/**
	 * NEWLINE : String :<br/>
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	String NEWLINE = System.getProperty("line.separator");
			
	/**
	 * Caractère de remplacement introduit lors de la lecture en UTF-8 
	 * d'un fichier texte encodé avec un autre Charset.<br/>
	 * REPLACEMENT CHARACTER."\\ufffd" '�'.<br/> 
	 */
	char CARACTERE_REMPLACEMENT = '\ufffd';

	/**
	 * BOM UTF-8 pour forcer Excel 2010 à lire en UTF-8.<br/>
	 * '\uFEFF'
	 */
	char BOM_UTF_8 = '\uFEFF';
	
	/**
	 * " - ".<br/>
	 */
	String SEP_MOINS = " - ";
		
	/**
	 * '_'.<br/>
	 */
	char UNDERSCORE = '_';
		
	/**
	 * '.'.<br/>
	 */
	char POINT = '.';
		
	/**
	 * "\\".<br/>
	 */
	String SEPARATEUR_FILE = "\\";
		
	/**
	 * Séparateur Java pour les répertoires "\\".<br/>
	 */
	String SEP_REP = "\\";
	
	/**
	 * ";".<br/>
	 */
	String SEP_POINTVIRGULE = ";";


	
	/**
	 * SERVICE PRINCIPAL.<br/>
	 * Contrôle d'un fichier.<br/>
	 * Vérifie qu'un fichier passe un enchaînement de contrôles.<br/>
	 * Doit retourner true si le contrôle s'effectue favorablement. 
	 * Par exemple, un contrôle vérifiant qu'un fichier est un texte 
	 * doit retourner true si c'est le cas.<br/>
	 * <br/>
	 * - Met automatiquement le boolean pEnregistrerRapport à true.<br/>
	 * - Enregistre un rapport de contrôle sur le disque.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier dont on veut savoir 
	 * si il passe le contrôle.<br/>
	 * 
	 * @return : boolean : true si pFile passe l'enchaînement de contrôles.<br/>
	 */
	boolean controler(File pFile);
	
	
	
	/**
	 * SERVICE PRINCIPAL.<br/>
	 * Contrôle d'un fichier.<br/>
	 * Vérifie qu'un fichier passe un enchaînement de contrôles.<br/>
	 * Doit retourner true si le contrôle s'effectue favorablement. 
	 * Par exemple, un contrôle vérifiant qu'un fichier est un texte 
	 * doit retourner true si c'est le cas.<br/>
	 * <ul>
	 * <li>N'exécute un contrôle que si son aEffectuer vaut true 
	 * (contrôles paramétrables).</li>
	 * <li>passe pFile à this.fichier et 
	 * rafraîchit automatiquement this.nomFichier.</li>
	 * <li>rafraîchit le rapport (en instancie un nouveau à chaque appel 
	 * de la méthode 
	 * <code>controler(File pFile, boolean pEnregistrerRapport)</code>).</li>
	 * <li>Ajoute chaque rapport d'un contrôle à <code>this.rapport</code>.</li>
	 * <li>passe le résultat de la méthode à false si un seul contrôle 
	 * n'est pas favorable.</li>
	 * <li>retourne false, met <code>this.estBloquant</code> 
	 * à true et sort de la méthode 
	 * si un contrôle défavorable est bloquant.</li>
	 * <li>Enregistre le rapport de contrôle sur disque 
	 * si pEnregistrerRapport vaut true.</li>
	 * </ul>
	 * <br/>
	 * - retourne false si this.mapControles == null.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier dont on veut savoir 
	 * si il passe le contrôle.<br/>
	 * @param pEnregistrerRapport : boolean : 
	 * true si on veut enregistrer le rapport dans un fichier sur disque.<br/>
	 * 
	 * @return : boolean : true si pFile passe l'enchaînement de contrôles.<br/>
	 */
	boolean controler(File pFile, boolean pEnregistrerRapport);
	
	
	
	/**
	 * SERVICE PRINCIPAL.<br/>
	 * Contrôle d'une String.<br/>
	 * Vérifie qu'une String passe un enchaînement de contrôles.<br/>
	 * Doit retourner true si le contrôle s'effectue favorablement. 
	 * Par exemple, un contrôle vérifiant qu'un fichier est un texte 
	 * doit retourner true si c'est le cas.<br/>
	 *
	 * @param pString : String : chaîne de caractères dont on veut savoir 
	 * si elle passe le contrôle.<br/>
	 * @param pEnregistrerRapport : boolean : 
	 * true si on veut enregistrer le rapport dans un fichier sur disque.<br/>
	 * 
	 * @return : boolean : true si pString passe l'enchaînement de contrôles.<br/>
	 */
	boolean controler(String pString, boolean pEnregistrerRapport);
	


	/**
	 * Getter de la java.util.Date de l'enchaînement de contrôles.<br/>
	 *
	 * @return dateControle : Date.<br/>
	 */
	Date getDateControle();
	
	

	/**
	 * Setter de la java.util.Date de l'enchaînement de contrôles.<br/>
	 * <br/>
	 * - calcule automatiquement dateControleStringFormattee.<br/>
	 * <br/>
	 *
	 * @param pDateControle : Date : valeur à passer à dateControle.<br/>
	 */
	void setDateControle(Date pDateControle);
	
	

	/**
	 * Getter de la date de l'enchaînement de contrôles formattée 
	 * au format dfDatetimemilliFrancaiseLexico.<br/>
	 * Format des dates-heures françaises lexicographique 
	 * avec millisecondes comme
	 * '1961-01-25_12-27-07-251'.<br/>
	 * "yyyy-MM-dd_HH-mm-ss-SSS".<br/>
	 *
	 * @return dateControleStringFormatee : String.<br/>
	 */
	String getDateControleStringFormatee();
	
	

	/**
	 * Getter du nom de l'utilisateur qui a déclenché 
	 * l'enchaînement de contrôles.<br/>
	 *
	 * @return userName : String.<br/>
	 */
	String getUserName();
	
	

	/**
	 * Setter du nom de l'utilisateur qui a déclenché 
	 * l'enchaînement de contrôles.<br/>
	 * <br/>
	 * remplit userName avec pUserName si pUserName != null 
	 * ou 'Administrateur' sinon.<br/>
	 *
	 * @param pUserName : String : 
	 * valeur à passer à userName.<br/>
	 */
	void setUserName(String pUserName);
	
	

	/**
	 * Getter du fichier sur lequel s'applique 
	 * l'enchaînement de contrôles.<br/>
	 *
	 * @return fichier : File.<br/>
	 */
	File getFichier();
	
	

	/**
	 * Setter du fichier sur lequel s'applique 
	 * l'enchaînement de contrôles.<br/>
	 * <br/>
	 * - calcule automatiquement nomFichier.<br/>
	 *
	 * @param pFichier : File : valeur à passer à fichier.<br/>
	 */
	void setFichier(File pFichier);
	
	

	/**
	 * Getter du nom du fichier objet 
	 * de l'enchaînement de contrôles.<br/>
	 *
	 * @return nomFichier : String.<br/>
	 */
	String getNomFichier();


	
	/**
	 * indique si l'enchainement de contrôles va bloquer le programme.<br/>
	 *
	 * @return : boolean : true si l'enchainement de contrôles 
	 * va bloquer le programme.<br/>
	 */
	boolean isEstBloquant();
	
	
	
	/**
	 * Getter de la Collection contenant l'ensemble des contrôles 
	 * à enchaîner sur un fichier.<br/>
	 *
	 * @return mapControles : SortedMap&lt;Integer,IControle&gt;.<br/>
	 */
	SortedMap<Integer, IControle> getMapControles();
	
	
	
} // FIN DE L'INTERFACE IEnchaineurControles.--------------------------------
