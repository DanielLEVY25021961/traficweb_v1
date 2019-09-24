package levy.daniel.application.model.services.traiteursfichiers.controleursfichiers;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Locale;

import levy.daniel.application.model.services.traiteursfichiers.IEnregistreurRapport;
import levy.daniel.application.model.services.traiteursfichiers.ILecteurDecodeurFile;
import levy.daniel.application.model.services.traiteursfichiers.IListeurDeCaracteresUnicode;
import levy.daniel.application.model.services.traiteursfichiers.IRapporteurControle;

/**
 * Interface IControle :<br/>
 * INTERFACE (Abstraction) qui factorise le concept de Contrôle de Fichier, 
 * publie ses méthodes et garantit que :<br/>
 * <ul>
 * <li>Tout contrôle peut connaitre son ordre 
 * d'exécution dans un enchaînement de contrôles.</li><br/>
 * <li>Tout contrôle est effectué à une 'dateControle'.<br/> 
 * En principe, dateControle vaut la date système 
 * au moment de l'exécution du contrôle.<br/> 
 * La classe calcule automatiquement 'dateControleStringFormatee' 
 * (c'est à dire l'objet Java Date exprimé sous forme de String formatée)
 * connaissant dateControle.</li><br/>
 * <li>Tout contrôle est effectué par un utilisateur (user) 
 * dont on connait le nom 'userName'. La classe remplit automatiquement 
 * userName avec 'Administrateur' si on ne lui fournit pas de userName.</li><br/>
 * <li>Tout contrôle s'applique sur un File 'fichier'. 
 * La classe calcule automatiquement 'nomFichier' connaissant fichier.</li><br/>
 * <li>Tout contrôle appartient à un type de contrôle 'typeControle' 
 * comme "contrôle de surface". 
 * 'typeControle' est fourni par chaque classe concrète.</li><br/>
 * <li>Tout contrôle a un nom 'nomControle' comme 'contrôle fichier texte'. 
 * 'nomControle' est fourni par chaque classe concrète.</li><br/>
 * <li>Tout contrôle vérifie un critère 'nomCritere' comme 
 * 'le fichier ne doit pas comporter de caractères indésirables'. 
 * 'nomCritere' est fourni par chaque classe concrète.</li><br/>
 * <li>Tout contrôle a une gravité 'gravite' comme '1 - bloquant'. 
 * Cette gravité est directement liée au niveau d'anomalie du contrôle 
 * 'niveauAnomalie' comme "1" pour bloquant. 
 * Chaque classe concrète fournit le 'niveauAnomalie' du contrôle 
 * via sa méthode fournirCleNiveauAnomalie() qui permet d'aller 
 * chercher la valeur dans messagescontroles_fr_FR.properties 
 * ou via fournirNiveauAnomalieEnDur().</li><br/>
 * <li>Tout contrôle sait si il est bloquant via 'estBloquant'. 
 * La classe remplit automatiquement 'estBloquant' 
 * connaissant niveauAnomalie.</li><br/>
 * <li>Tout contrôle fournit un rapport de contrôle 
 * sous forme de List&lt;LigneRapport&gt; 'rapport'.</li><br/>
 *</ul>
 * <br/>
 * <br/>
 * Attributs : <br/>
 * [nomClasseConcrete;dateControle;dateControleStringFormatee;userName;
 * fichier;nomFichier;typeControle;nomControle;nomCritere;gravite;
 * niveauAnomalie;estBloquant;rapport].<br/>
 * <br/>
 * <ul>
 * comporte essentiellement : <br/>
 * <li>une méthode controler(File pFile, boolean pEnregistrerRapport) 
 * qui permet de contrôler un fichier pFile 
 * et de retourner true si le fichier passe favorablement le contrôle.<br/>
 * Cette méthode peut écrire le rapport de contrôle 
 * sous forme de List&lt;LigneRapport&gt; sur disque 
 * et génère 
 * également un compte rendu d'enregistrement 
 * sous forme de List&lt;LigneRapportEnregistrement&gt;.</li><br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<code>
 *  // Instanciation d'un ControleurTypeTexte.<br/>
 *  final ControleurTypeTexte control = new ControleurTypeTexte();<br/>
 *  // Invocation de la méthode controler(...) en demandant 
 *  l'écriture des rapports textuels et csv sur disque.<br/>
 *  final boolean resultat = control.controler(FILE_CHARETTE_ANSI, true);<br/>
 *  // resultat = true FILE_CHARETTE_ANSI est un fichier textuel.<br/>
 *  control.afficherRapportTextuel() // Pour voir le 
 *  rapport de contrôle sous forme textuelles.<br/>
 *  control.afficherRapportCsvAvecEntete() // Pour voir le 
 *  rapport de contrôle sous forme csv.<br/>
 *  // id;date du contrôle;utilisateur;Fichier;type de contrôle;Contrôle;
 *  Critère du Contrôle;Gravité du Contrôle;Numéro de Ligne;
 *  Message du Contrôle;Ordre du Champ;Position du Champ;
 *  Valeur du Champ;Action;<br/>
 *  // null;2016-03-06_19-08-55-259;Administrateur;
 *  chaàâreéèêëtte_ANSI.txt;Contrôle de surface;Contrôle fichier texte;
 *  Le fichier ne doit pas comporter de caractères indésirables 
 *  (impossibles à écrire au clavier);1 - anomalie bloquante;
 *  null;Le fichier 'chaàâreéèêëtte_ANSI.txt' est bien un fichier texte;
 *  null;sans objet;sans objet;OK - Fichier accepté;<br/> 
 *  control.afficherRapportEnregistrementTextuel() // Pour voir le compte-rendu 
 *  de l'enregistrement du rapport de contrôle sous forme textuelle.<br/>
 *  control.afficherRapportEnregistrementCsv() // Pour voir le compte-rendu 
 *  de l'enregistrement du rapport de contrôle sous forme csv.<br/>
 * </code>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Charset de décodage pCharset, lireFichier(File pFile)<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * levy.daniel.application.ILecteurDecodeurFile.<br/>
 * levy.daniel.application.IListeurDeCaracteresUnicode.<br/>
 * levy.daniel.application.IExportateurCsv.<br/>
 * levy.daniel.application.IExportateurJTable.<br/>
 * levy.daniel.application.IResetable.<br/>
 * levy.daniel.application.metier.controles.rapportscontroles.LigneRapport.<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.rapportsenregistrements.LigneRapportEnregistrement.<br/>
 * levy.daniel.application.metier.controles.IEnregistreurRapport.<br/>
 * levy.daniel.application.metier.controles.IRapporteurControle.<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 29 févr. 2016
 *
 */
public interface IControle extends IRapporteurControle
			, ILecteurDecodeurFile, IListeurDeCaracteresUnicode
				, IEnregistreurRapport {

	/**
	 * new Locale("fr", "FR").<br/>
	 */
	Locale LOCALE_FR_FR = new Locale("fr", "FR");
		
	/**
	 * CHARSET_UTF8 : Charset :<br/>
	 * Charset.forName("UTF-8").<br/>
	 * Eight-bit Unicode (or UCS) Transformation Format.<br/> 
	 */
	Charset CHARSET_UTF8 
		= Charset.forName("UTF-8");
	
	
	/**
	 * CHARSET_US_ASCII : Charset :<br/>
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
	 * CHARSET_ISO_8859_1 : Charset :<br/>
	 * Charset.forName("ISO-8859-1").<br/>
	 * Latin1.<br/>
	 * 191 caractères imprimables.<br/> 
	 * Permet d’écrire la plupart des langues d’Europe de l’Ouest.<br/> 
	 * Presque tous les caractères du français y sont (manquent le œ et €).<br/>
	 */
	Charset CHARSET_ISO_8859_1 
		= Charset.forName("ISO-8859-1");
	
	
	/**
	 * CHARSET_LATIN1 : Charset :<br/>
	 * Charset.forName("ISO-8859-1").<br/>
	 * Latin1.<br/>
	 * 191 caractères imprimables.<br/> 
	 * Permet d’écrire la plupart des langues d’Europe de l’Ouest.<br/> 
	 * Presque tous les caractères du français y sont (manquent le œ et €).<br/>
	 */
	Charset CHARSET_LATIN1 
		= Charset.forName("ISO-8859-1");
	
	
	/**
	 * CHARSET_ISO_8859_2 : Charset :<br/>
	 * Charset.forName("ISO-8859-2").<br/>
	 * <br/>
	 */
	Charset CHARSET_ISO_8859_2 
		= Charset.forName("ISO-8859-2");
	
	
	/**
	 * CHARSET_ISO_8859_9 : Charset :<br/>
	 * Charset.forName("ISO-8859-9").<br/>
	 * Latin Alphabet No. 5<br/>
	 */
	Charset CHARSET_ISO_8859_9 
		= Charset.forName("ISO-8859-9");
	
	
	/**
	 * CHARSET_ISO_8859_15 : Charset :<br/>
	 * Charset.forName("ISO-8859-15").<br/>
	 * Latin9, Latin Alphabet No. 9.<br/>
	 * modifie légèrement ISO-8859-1.<br/> 
	 * Ajout du caractère œ et du symbole monétaire € (Euro) entre autres.<br/>
	 */
	Charset CHARSET_ISO_8859_15 
		= Charset.forName("ISO-8859-15");
	
	
	/**
	 * CHARSET_LATIN9 : Charset :<br/>
	 * Charset.forName("ISO-8859-15").<br/>
	 * Latin9, Latin Alphabet No. 9.<br/>
	 * modifie légèrement ISO-8859-1.<br/> 
	 * Ajout du caractère œ et du symbole monétaire € (Euro) entre autres.<br/>
	 */
	Charset CHARSET_LATIN9 
		= Charset.forName("ISO-8859-15");
	
	
	/**
	 * CHARSET_WINDOWS_1252 : Charset :<br/>
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
	 * CHARSET_ANSI : Charset :<br/>
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
	 * CHARSET_CP1252 : Charset :<br/>
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
	 * CHARSET_IBM850 : Charset :<br/>
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
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	String NEWLINE = System.getProperty("line.separator");
	
		
	/**
	 * CARACTERE_REMPLACEMENT : char :<br/>
	 * Caractère de remplacement introduit lors de la lecture en UTF-8 
	 * d'un fichier texte encodé avec un autre Charset.<br/>
	 * REPLACEMENT CHARACTER."\\ufffd" '�'.<br/> 
	 */
	char CARACTERE_REMPLACEMENT = '\ufffd';

	
	/**
	 * BOM_UTF : char :<br/>
	 * BOM UTF-8 pour forcer Excel 2010 à lire en UTF-8.<br/>
	 */
	char BOM_UTF_8 = '\uFEFF';

	
	/**
	 * SEP_MOINS : String :<br/>
	 * " - ".<br/>
	 */
	String SEP_MOINS = " - ";
	
	
	/**
	 * UNDERSCORE : char :<br/>
	 * '_'.<br/>
	 */
	char UNDERSCORE = '_';
	
	
	/**
	 * POINT : char :<br/>
	 * '.'.<br/>
	 */
	char POINT = '.';
	
	
	/**
	 * SEPARATEUR_FILE : String :<br/>
	 * "\\".<br/>
	 */
	String SEPARATEUR_FILE = "\\";
	
	
	/**
	 * SEP_REP : String :<br/>
	 * Séparateur Java pour les répertoires "\\".<br/>
	 */
	String SEP_REP = "\\";
	

	/**
	 * SEP_POINTVIRGULE : String :<br/>
	 * ";".<br/>
	 */
	String SEP_POINTVIRGULE = ";";


	/**
	 * NULL : String :<br/>
	 * "null".<br/>
	 */
	String NULL = "null";
	
	
	/**
	 * SANS_OBJET : String :<br/>
	 * "sans objet".<br/>
	 */
	String SANS_OBJET = "sans objet";
	
	
	/**
	 * TOUS : String :<br/>
	 * "tous".<br/>
	 */
	String TOUS = "tous";
	
	
	/**
	 * MESSAGE_EXCEPTION : String :<br/>
	 * "Exception GRAVE : ".<br/>
	 */
	String MESSAGE_EXCEPTION = "Exception GRAVE : ";
	

	/**
	 * ACTION_FICHIER_REFUSE : String :<br/>
	 * "Fichier refusé".<br/>
	 */
	String ACTION_FICHIER_REFUSE = "KO - Fichier refusé";

	
	/**
	 * ACTION_FICHIER_ACCEPTE : String :<br/>
	 * "OK - Fichier accepté".<br/>
	 */
	String ACTION_FICHIER_ACCEPTE = "OK - Fichier accepté";
	

	/**
	 * ACTION_LIGNE_NON_HIT : String :<br/>
	 * "Ligne ne comportant pas 520 caractères".<br/>
	 */
	String ACTION_LIGNE_NON_HIT 
		= "Ligne ne comportant pas 520 caractères";

	
	/**
	 * ACTION_LIGNE_NON_DARWIN : String :<br/>
	 * "Ligne ne comportant pas 57 champs".<br/>
	 */
	String ACTION_LIGNE_NON_DARWIN 
	= "Ligne ne comportant pas 57 champs";

	
	/**
	 * SERVICE PRINCIPAL - N'ENREGISTRE PAS DE RAPPORT SUR DISQUE.<br/>
	 * Contrôle d'un fichier.<br/>
	 * <ul>
	 * <li>Vérifie qu'un fichier passe un contrôle.</li>
	 * <li>Doit retourner true si le contrôle s'effectue favorablement. 
	 * Par exemple, un contrôle vérifiant qu'un fichier est un texte 
	 * doit retourner true si c'est le cas.</li>
	 * </ul>
	 * <br/>
	 * - retourne false, LOG de niveau INFO et rapport 
	 * si pFile == null.<br/>
	 * - retourne false, LOG de niveau INFO et rapport 
	 * si pFile est inexistant.<br/>
	 * - retourne false, LOG de niveau INFO et rapport 
	 * si pFile est un répertoire.<br/>
	 * - retourne false, LOG de niveau INFO et rapport 
	 * si pFile est vide.<br/>
	 * <br/>
	 * - Met automatiquement le boolean pEnregistrerRapport à false.<br/>
	 * - N'enregistre pas de rapport sur le disque.<br/>
	 * <br/>
	 * RG-01 : Contrôle de validité<br/>
	 * RG-01-Controler : true si favorable.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier dont on veut savoir 
	 * si il passe le contrôle.<br/>
	 * 
	 * @return : boolean : true si pFile passe le contrôle.<br/>
	 */
	boolean controler(File pFile);
	
	
	
	/**
	 * SERVICE PRINCIPAL.<br/>
	 * Contrôle d'un fichier.<br/>
	 * - Enregistre le rapport de contrôle sur disque 
	 * si pEnregistrerRapport == true.<br/>
	 * <ul>
	 * <li>Vérifie qu'un fichier passe un contrôle.</li>
	 * <li>Doit retourner true si le contrôle s'effectue favorablement. 
	 * Par exemple, un contrôle vérifiant qu'un fichier est un texte 
	 * doit retourner true si c'est le cas.</li>
	 * </ul>
	 * <br/>
	 * - retourne false, LOG de niveau INFO et rapport 
	 * si pFile == null.<br/>
	 * - retourne false, LOG de niveau INFO et rapport 
	 * si pFile est inexistant.<br/>
	 * - retourne false, LOG de niveau INFO et rapport 
	 * si pFile est un répertoire.<br/>
	 * - retourne false, LOG de niveau INFO et rapport 
	 * si pFile est vide.<br/>
	 * <br/>
	 * RG-01 : Contrôle de validité<br/>
	 * RG-01-Controler : true si favorable.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier dont on veut savoir 
	 * si il passe le contrôle.<br/>
	 * @param pEnregistrerRapport : boolean : 
	 * true si on veut enregistrer le rapport dans un fichier sur disque.<br/>
	 * 
	 * @return : boolean : true si pFile passe le contrôle.<br/>
	 */
	boolean controler(File pFile, boolean pEnregistrerRapport);
	
	
	
	/**
	 * Getter de l' ordre d'exécution du contrôle 
	 * dans un enchaînement de contrôles.<br/>
	 *
	 * @return ordreControle : Integer.<br/>
	 */
	Integer getOrdreControle();
	
	

	/**
	 * Setter de l' ordre d'exécution du contrôle 
	 * dans un enchaînement de contrôles.<br/>
	 *
	 * @param pOrdreControle : Integer : 
	 * valeur à passer à ordreControle.<br/>
	 */
	void setOrdreControle(Integer pOrdreControle);
	
	
	
	/**
	 * Getter de la java.util.Date du contrôle.<br/>
	 *
	 * @return dateControle : Date.<br/>
	 */
	Date getDateControle();
	
	

	/**
	 * Setter de la java.util.Date du contrôle.<br/>
	 * <br/>
	 * - calcule automatiquement dateControleStringFormattee.<br/>
	 *
	 * @param pDateControle : Date : valeur à passer à dateControle.<br/>
	 */
	void setDateControle(Date pDateControle);
	
	

	/**
	 * Getter de la date du contrôle formattée 
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
	 * Getter du nom de l'utilisateur qui a déclenché le contrôle.<br/>
	 *
	 * @return userName : String.<br/>
	 */
	String getUserName();
	
	

	/**
	 * Setter du nom de l'utilisateur qui a déclenché le contrôle.<br/>
	 * <br/>
	 * remplit userName avec pUserName si pUserName != null 
	 * ou 'Administrateur' sinon.<br/>
	 *
	 * @param pUserName : String : 
	 * valeur à passer à userName.<br/>
	 */
	void setUserName(String pUserName);
	
	

	/**
	 * Getter du fichier sur lequel s'applique le contrôle.<br/>
	 *
	 * @return fichier : File.<br/>
	 */
	File getFichier();
	
	

	/**
	 * Setter du fichier sur lequel s'applique le contrôle.<br/>
	 * <br/>
	 * - calcule automatiquement nomFichier.<br/>
	 *
	 * @param pFichier : File : valeur à passer à fichier.<br/>
	 */
	void setFichier(File pFichier);
	
	

	/**
	 * Getter du nom du fichier objet du contrôle.<br/>
	 *
	 * @return nomFichier : String.<br/>
	 */
	String getNomFichier();

	
	
	/**
	 * Getter du type du contrôle ('contrôle de surface' par exemple).<br/>
	 *
	 * @return typeControle : String.<br/>
	 */
	String getTypeControle();

	
	
	/**
	 * Getter du nom du contrôle ('contrôle fichier texte' par exemple).<br/>
	 *
	 * @return nomControle : String.<br/>
	 */
	String getNomControle();

	
	
	/**
	 * Getter du nom du critère appliqué dans le contrôle 
	 * ('le fichier ne doit pas comporter de 
	 * caractères indésirables' par exemple).<br/>
	 *
	 * @return nomCritere : String.<br/>
	 */
	String getNomCritere();
	
	

	/**
	 * Getter de la désignation de la gravité de ce contrôle 
	 * (par exemple '1 - bloquant').<br/>
	 *
	 * @return gravite : String.<br/>
	 */
	String getGravite();
	
	

	/**
	 * Getter du Niveau de l'anomalie correspondant au Contrôle
	 * dans le messagescontroles_fr_FR.properties.<br/>
	 * Par exemple : "1" pour le ControleurTypeTexte.<br/>
	 *
	 * @return niveauAnomalie : String.<br/>
	 */
	String getNiveauAnomalie();

	
	
	/**
	 * Getter du boolean qui stipule si le contrôle doit pouvoir 
	 * bloquer le programme.<br/>
	 * true si le contrôle doit pouvoir bloquer le programme.<br/>
	 *
	 * @return estBloquant : boolean.<br/>
	 */
	boolean isEstBloquant();

	
	
	/**
	 * Getter du boolean qui stipule si le contrôle doit être effectué 
	 * dans un enchaînement de contrôles.<br/>
	 * Cette valeur doit figurer dans le messagescontroles_fr_FR.properties 
	 * ou être fournie en dur par les classes concrètes.<br/>
	 * true si le contrôle doit être effectué.<br/>
	 * Contrôle paramétrable.<br/>
	 *
	 * @return aEffectuer : boolean.<br/>
	 */
	boolean isaEffectuer();

	
	
	/**
	 * Retourne le fichier résultant du contrôle ou du traitement.<br/>
	 * Utile pour l'enchaînement des contrôles.<br/>
	 *
	 * @return : File : fichier résultant du contrôle ou du traitement.<br/>
	 */
	File getFichierTraite();
	
	

} // FIN DE L'INTERFACE IControle.-------------------------------------------