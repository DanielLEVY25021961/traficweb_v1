package levy.daniel.application.model.services.metier.televersement.importateurs.importeurs;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;


/**
 * INTERFACE IFournisseurCsvImporteur :<br/>
 * RESPONSABILITE : GENERER LE FICHIER IMPORTE 
 * (HIT, HISTO_F07, DARWIN_CSV, ...) 
 * AU FORMAT CSV avec séparateur ';'.<br/>
 * Interface factorisant les méthodes garantissant 
 * qu'un objet qui l'implémente saura s'écrire sous forme de fichier csv 
 * avec séparateur ';'.<br/>
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
public interface IFournisseurCsvImporteur {

	
	
	/**
	 * Fournit une ligne csv avec un séparateur ';' pour les en-têtes
	 * de fichier (encodée dans le charset par défaut de la plateforme).<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.fichierImporteMap</b></code> 
	 * est null.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * Numéro de Département;Numéro de Section;Sens;Nature;
	 * Classe;Année de traitement;Zone libre;Route;
	 * Type de Comptage;Classement de la route;
	 * Classe de largeur chaussée unique;Classe de largeur chaussée séparées;
	 * Type de réseau;Zone libre;Lieu-dit d'origine;
	 * P.R. Origine;Abscisse du PRO;Lieu-dit d'extrémité;
	 * P.R. Extrémité;Abscisse du PRE;Lieu-dit de comptage;
	 * P.R. Comptage;Abscisse du PRC;Longueur section;Longueur rase campagne;
	 * Zone libre;MJA;Zone libre;Pourcentage de trafic poids lourds (annuel);
	 * Zone libre;Pourcentage de trafic de nuit tous véhicules (annuel);
	 * Zone libre;Moyenne journalière mensuelle 01;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 01;
	 * Moyenne journalière mensuelle 02;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 02;
	 * Moyenne journalière mensuelle 03;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 03;
	 * Moyenne journalière mensuelle 04;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 04;
	 * Moyenne journalière mensuelle 05;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 05;
	 * Moyenne journalière mensuelle 06;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 06;
	 * Moyenne journalière mensuelle 07;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 07;
	 * Moyenne journalière mensuelle 08;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 08;
	 * Moyenne journalière mensuelle 09;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 09;
	 * Moyenne journalière mensuelle 10;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 10;
	 * Moyenne journalière mensuelle 11;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 11;
	 * Moyenne journalière mensuelle 12;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 12
	 * ;Zone libre;Année N-1;MJA N-1;Zone libre;
	 * Pourcentage de trafic poids lourds (annuel) N-1;
	 * Zone libre;Pourcentage de trafic de nuit tous véhicules (annuel) N-1;
	 * Zone libre;Année N-2;MJA N-2;Zone libre;
	 * Pourcentage de trafic poids lourds (annuel) N-2;
	 * Zone libre;Pourcentage de trafic de nuit tous véhicules (annuel) N-2;
	 * Zone libre;Année N-3;MJA N-3;Zone libre;
	 * Pourcentage de trafic poids lourds (annuel) N-3;Zone libre;
	 * Pourcentage de trafic de nuit tous véhicules (annuel) N-3;
	 * Zone libre;Année N-4;MJA N-4;Zone libre;
	 * Pourcentage de trafic poids lourds (annuel) N-4;
	 * Zone libre;Pourcentage de trafic de nuit tous véhicules (annuel) N-4;
	 * Zone libre;Année N-5;MJA N-5;Zone libre;
	 * Pourcentage de trafic poids lourds (annuel) N-5;
	 * Zone libre;Pourcentage de trafic de nuit tous véhicules (annuel) N-5;
	 * Gestionnaire;Code Concession;Département Début;Route;
	 * Profil en travers;Sous-réseau Indice;Zone libre;
	 * Itinéraire Européen 1;Itinéraire Européen 2;Itinéraire Européen 3;
	 * Zone libre; pour les HIT.<br/>
	 * <br/>
	 *
	 * @return String : ligne d'en-têtes csv avec séparateur ';'.<br/>
	 */
	String fournirLigneEnTetesCsv();

	
	
	/**
	 * Fournit une ligne csv avec un séparateur ';'
	 * contenant les valeurs pour la ligne pL (1-based) du fichier 
	 * (encodée dans le charset par défaut de la plateforme).<br/>
	 * - retourne null si this.fichierImporteMap est null.<br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.fichierImporteMap</b></code> 
	 * est null.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * Numéro de Département;Numéro de Section;Sens;Nature;
	 * Classe;Année de traitement;Zone libre;Route;
	 * Type de Comptage;Classement de la route;
	 * Classe de largeur chaussée unique;Classe de largeur chaussée séparées;
	 * Type de réseau;Zone libre;Lieu-dit d'origine;
	 * P.R. Origine;Abscisse du PRO;Lieu-dit d'extrémité;
	 * P.R. Extrémité;Abscisse du PRE;Lieu-dit de comptage;
	 * P.R. Comptage;Abscisse du PRC;Longueur section;Longueur rase campagne;
	 * Zone libre;MJA;Zone libre;Pourcentage de trafic poids lourds (annuel);
	 * Zone libre;Pourcentage de trafic de nuit tous véhicules (annuel);
	 * Zone libre;Moyenne journalière mensuelle 01;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 01;
	 * Moyenne journalière mensuelle 02;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 02;
	 * Moyenne journalière mensuelle 03;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 03;
	 * Moyenne journalière mensuelle 04;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 04;
	 * Moyenne journalière mensuelle 05;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 05;
	 * Moyenne journalière mensuelle 06;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 06;
	 * Moyenne journalière mensuelle 07;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 07;
	 * Moyenne journalière mensuelle 08;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 08;
	 * Moyenne journalière mensuelle 09;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 09;
	 * Moyenne journalière mensuelle 10;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 10;
	 * Moyenne journalière mensuelle 11;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 11;
	 * Moyenne journalière mensuelle 12;
	 * Pourcentage de trafic de nuit tous véhicules (mensuel) 12
	 * ;Zone libre;Année N-1;MJA N-1;Zone libre;
	 * Pourcentage de trafic poids lourds (annuel) N-1;
	 * Zone libre;Pourcentage de trafic de nuit tous véhicules (annuel) N-1;
	 * Zone libre;Année N-2;MJA N-2;Zone libre;
	 * Pourcentage de trafic poids lourds (annuel) N-2;
	 * Zone libre;Pourcentage de trafic de nuit tous véhicules (annuel) N-2;
	 * Zone libre;Année N-3;MJA N-3;Zone libre;
	 * Pourcentage de trafic poids lourds (annuel) N-3;Zone libre;
	 * Pourcentage de trafic de nuit tous véhicules (annuel) N-3;
	 * Zone libre;Année N-4;MJA N-4;Zone libre;
	 * Pourcentage de trafic poids lourds (annuel) N-4;
	 * Zone libre;Pourcentage de trafic de nuit tous véhicules (annuel) N-4;
	 * Zone libre;Année N-5;MJA N-5;Zone libre;
	 * Pourcentage de trafic poids lourds (annuel) N-5;
	 * Zone libre;Pourcentage de trafic de nuit tous véhicules (annuel) N-5;
	 * Gestionnaire;Code Concession;Département Début;Route;
	 * Profil en travers;Sous-réseau Indice;Zone libre;
	 * Itinéraire Européen 1;Itinéraire Européen 2;Itinéraire Européen 3;
	 * Zone libre; pour les HIT.<br/>
	 * <br/>
	 * 
	 * @param pL : int : numéro (1-based) de la ligne du fichier.<br/>
	 *
	 * @return String : Les valeurs de la ligne pL en csv.<br/>
	 */
	String fournirLigneValeursCsv(int pL);
	
	
		
	/**
	 * Génère le fichier au format csv 
	 * avec séparateur ';' sous forme de String.<br/>
	 * - Rajoute automatiquement la ligne d'en-têtes.<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.fichierImporteMap</b></code> 
	 * est null.<br/>
	 * <br/>
	 * 
	 * @return : String : le fichier au format 
	 * csv avec séparateur ';' sous forme de String.<br/>
	 */
	String genererCsvString();
	
	
	
	/**
	 * Génère le fichier au format csv 
	 * avec séparateur ';' <i>sous forme de String</i>.<br/>
	 * Rajoute la ligne d'en-têtes si pAvecLigneEntetes vaut true.<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.fichierImporteMap</b></code> 
	 * est null.<br/>
	 * <br/>
	 *
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit.<br/>
	 * 
	 * @return : String : le fichier au format 
	 * csv avec séparateur ';' sous forme de String.<br/>
	 */
	String genererCsvString(
			boolean pAvecLigneEntetes);

	
	
	/**
	 * Génère un <b>fichier csv encodé en UTF-8 avec séparateur ';'</b> 
	 * contenant 
	 * le fichier importé <code><b>this.fichierAImporter</b></code>.<br/>
	 * Ce fichier csv généré est accessible dans le même répertoire 
	 * que this.fichierAImporter et porte le même nom 
	 * avec l'extension _UTF-8.csv.<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.fichierImporteMap</b></code> 
	 * est null.<br/>
	 * <br/>
	 *
	 * @return : File : le fichier csv généré.<br/>
	 * 
	 * @throws IOException<br/> 
	 */
	File genererCsvFile() throws IOException;
	
	
		
	/**
	 * Génère un <b>fichier csv encodé en ISO-8859-15 (Latin9)
	 *  avec séparateur ';'</b> contenant 
	 * le fichier importé <code><b>this.fichierAImporter</b></code>.<br/>
	 * Ce fichier csv généré est accessible dans le même répertoire 
	 * que this.fichierAImporter et porte le même nom 
	 * avec l'extension _ISO-8859-15.csv.<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.fichierImporteMap</b></code> 
	 * est null.<br/>
	 * <br/>
	 *
	 * @return : File : le fichier csv généré.<br/>
	 * 
	 * @throws IOException<br/> 
	 */
	File genererCsvFileLatin9() throws IOException;
	
	
	
	/**
	 * Génère un <b>fichier csv encodé en Windows-1252 (ANSI)
	 *  avec séparateur ';'</b> contenant 
	 * le fichier importé <code><b>this.fichierAImporter</b></code>.<br/>
	 * Ce fichier csv généré est accessible dans le même répertoire 
	 * que this.fichierAImporter et porte le même nom 
	 * avec l'extension _WINDOWS-1252.csv.<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.fichierImporteMap</b></code> 
	 * est null.<br/>
	 * <br/>
	 *
	 * @return : File : le fichier csv généré.<br/>
	 * 
	 * @throws IOException<br/> 
	 */
	File genererCsvFileAnsi() throws IOException;

	
	
	/**
	 * Génère un <b>fichier csv encodé en UTF-8 avec séparateur ';'</b> 
	 * contenant 
	 * le fichier importé <code><b>this.fichierAImporter</b></code>.<br/>
	 * Ce fichier csv généré est accessible dans le même répertoire 
	 * que this.fichierAImporter et porte le même nom 
	 * avec l'extension _UTF-8.csv.<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.fichierImporteMap</b></code> 
	 * est null.<br/>
	 * <br/>
	 *
	 * @return : File : le fichier csv généré.<br/>
	 * 
	 * @throws IOException<br/> 
	 */
	File genererCsvFileUtf8() throws IOException;


	
	/**
	 * Génère un <b>fichier csv encodé en UTF-8 avec séparateur ';'</b> 
	 * contenant 
	 * le fichier importé <code><b>this.fichierAImporter</b></code>.<br/>
	 * - Ce fichier csv généré est accessible à pFile.<br/>
	 * - La ligne d'en-têtes est toujours générée.<br/>
	 * - Génère automatiquement le fichier de sortie dans le 
	 * même répertoire que this.fichierAImporter 
	 * avec l'extension _UTF-8.csv 
	 * si pFile est null.<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.fichierImporteMap</b></code> 
	 * est null.<br/>
	 * - retourne null si pFile est null et 
	 * <code><b>this.fichierAImporter</b></code> est introuvable.<br/>
	 * <br/>
	 *
	 * @param pFile : File : le fichier csv à générer.<br/>
	 * 
	 * @return : File : le fichier csv généré.<br/>
	 * 
	 * @throws IOException<br/>  
	 */
	File genererCsvFile(File pFile) throws IOException;


	
	/**
	 * Génère un <b>fichier csv encodé en UTF-8 avec séparateur ';'</b> 
	 * contenant 
	 * le fichier importé <code><b>this.fichierAImporter</b></code>.<br/>
	 * - Ce fichier csv généré est accessible à pFile.<br/>
	 * - pAvecLigneEntetes à true permet de générer la ligne d'en-tête.<br/>
	 * - Génère automatiquement le fichier de sortie dans le 
	 * même répertoire que this.fichierAImporter 
	 * avec l'extension _UTF-8.csv 
	 * si pFile est null.<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.fichierImporteMap</b></code> 
	 * est null.<br/>
	 * - retourne null si pFile est null et 
	 * <code><b>this.fichierAImporter</b></code> est introuvable.<br/>
	 * <br/>
	 *
	 * @param pAvecLigneEntetes : boolean :boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv généré.<br/>
	 * @param pFile : File : le fichier csv à générer.<br/>
	 * 
	 * @return : File : le fichier csv généré.<br/>
	 * 
	 * @throws IOException  
	 */
	File genererCsvFile(
			boolean pAvecLigneEntetes
				, File pFile) throws IOException;
	
	
	
	/**
	 * Génère un <b>fichier csv encodé en pCharset avec séparateur ';'</b> 
	 * contenant 
	 * le fichier importé <code><b>this.fichierAImporter</b></code>.<br/>
	 * - Ce fichier csv généré est accessible à pFile.<br/>
	 * - pAvecLigneEntetes à true permet de générer la ligne d'en-tête.<br/>
	 * - Génère automatiquement le fichier de sortie dans le 
	 * même répertoire que this.fichierAImporter 
	 * avec l'extension _CHARSET.csv 
	 * si pFile est null.<br/>
	 * - Choisit automatiquement UTF-8 si pCharset == null.<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.fichierImporteMap</b></code> 
	 * est null.<br/>
	 * - retourne null si pFile est null et 
	 * <code><b>this.fichierAImporter</b></code> est introuvable.<br/>
	 * <br/>
	 *
	 * @param pAvecLigneEntetes : boolean :boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv généré.<br/>
	 * @param pFile : File : le fichier csv à générer.<br/>
	 * @param pCharset : Charset : le Charset dans lequel 
	 * on veut encoder le fichier généré.<br/>
	 * 
	 * @return : File : le fichier csv généré.<br/>
	 * 
	 * @throws IOException  
	 */
	File genererCsvFile(
			boolean pAvecLigneEntetes
				, File pFile
					, Charset pCharset) throws IOException;
	

	
} // FIN DE L'INTERFACE IFournisseurCsvImporteur.-----------------------------
