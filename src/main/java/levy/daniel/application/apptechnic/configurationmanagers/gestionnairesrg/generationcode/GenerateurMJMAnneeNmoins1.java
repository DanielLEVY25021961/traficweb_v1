package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.generationcode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

/**
 * CLASSE GenerateurMJMAnneeNmoins1 :<br/>
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
 * @since 8 sept. 2019
 *
 */
public final class GenerateurMJMAnneeNmoins1 {

	// ************************ATTRIBUTS************************************/

	/**
	 * '\n'.<br/>
	 */
	public static final char SAUT_LIGNE_JAVA_CHAR = '\n';

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(GenerateurMJMAnneeNmoins1.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation
	 */
	private GenerateurMJMAnneeNmoins1() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param pList
	 * @return : List<String> :  .<br/>
	 */
	public static List<String> substituer(final List<String> pList) {
		
		final List<String> resultat = new ArrayList<String>();
		
		for (String ligne : pList) {
			
			ligne = StringUtils.replace(ligne, "44", String.valueOf(44 + 65));
			ligne = StringUtils.replace(ligne, "45", String.valueOf(45 + 65));
			ligne = StringUtils.replace(ligne, "46", String.valueOf(46 + 65));
			ligne = StringUtils.replace(ligne, "47", String.valueOf(47 + 65));
			ligne = StringUtils.replace(ligne, "48", String.valueOf(48 + 65));
			ligne = StringUtils.replace(ligne, "49", String.valueOf(49 + 65));
			ligne = StringUtils.replace(ligne, "50", String.valueOf(50 + 65));
			ligne = StringUtils.replace(ligne, "51", String.valueOf(51 + 65));
			ligne = StringUtils.replace(ligne, "52", String.valueOf(52 + 65));
			ligne = StringUtils.replace(ligne, "53", String.valueOf(53 + 65));
			ligne = StringUtils.replace(ligne, "54", String.valueOf(54 + 65));
			ligne = StringUtils.replace(ligne, "55", String.valueOf(55 + 65));
			ligne = StringUtils.replace(ligne, "56", String.valueOf(56 + 65));
			ligne = StringUtils.replace(ligne, "57", String.valueOf(57 + 65));
			ligne = StringUtils.replace(ligne, "58", String.valueOf(58 + 65));
			ligne = StringUtils.replace(ligne, "59", String.valueOf(59 + 65));
			ligne = StringUtils.replace(ligne, "60", String.valueOf(60 + 65));
			ligne = StringUtils.replace(ligne, "61", String.valueOf(61 + 65));
			ligne = StringUtils.replace(ligne, "62", String.valueOf(62 + 65));
			ligne = StringUtils.replace(ligne, "63", String.valueOf(63 + 65));
			ligne = StringUtils.replace(ligne, "64", String.valueOf(64 + 65));
			ligne = StringUtils.replace(ligne, "65", String.valueOf(65 + 65));
			ligne = StringUtils.replace(ligne, "66", String.valueOf(66 + 65));
			ligne = StringUtils.replace(ligne, "67", String.valueOf(67 + 65));

			ligne = StringUtils.replace(ligne, "168", String.valueOf(168 + 210));
			ligne = StringUtils.replace(ligne, "173", String.valueOf(173 + 210));
			ligne = StringUtils.replace(ligne, "174", String.valueOf(174 + 210));
			ligne = StringUtils.replace(ligne, "176", String.valueOf(176 + 210));
			ligne = StringUtils.replace(ligne, "177", String.valueOf(177 + 210));
			ligne = StringUtils.replace(ligne, "182", String.valueOf(182 + 210));
			ligne = StringUtils.replace(ligne, "183", String.valueOf(183 + 210));
			ligne = StringUtils.replace(ligne, "185", String.valueOf(185 + 210));
			ligne = StringUtils.replace(ligne, "186", String.valueOf(186 + 210));
			ligne = StringUtils.replace(ligne, "191", String.valueOf(191 + 210));
			ligne = StringUtils.replace(ligne, "192", String.valueOf(192 + 210));
			ligne = StringUtils.replace(ligne, "194", String.valueOf(194 + 210));
			ligne = StringUtils.replace(ligne, "195", String.valueOf(195 + 210));
			ligne = StringUtils.replace(ligne, "200", String.valueOf(200 + 210));
			ligne = StringUtils.replace(ligne, "201", String.valueOf(201 + 210));
			ligne = StringUtils.replace(ligne, "203", String.valueOf(203 + 210));
			ligne = StringUtils.replace(ligne, "204", String.valueOf(204 + 210));
			ligne = StringUtils.replace(ligne, "209", String.valueOf(209 + 210));
			ligne = StringUtils.replace(ligne, "210", String.valueOf(210 + 210));
			ligne = StringUtils.replace(ligne, "212", String.valueOf(212 + 210));
			ligne = StringUtils.replace(ligne, "213", String.valueOf(213 + 210));
			ligne = StringUtils.replace(ligne, "218", String.valueOf(218 + 210));
			ligne = StringUtils.replace(ligne, "219", String.valueOf(219 + 210));
			ligne = StringUtils.replace(ligne, "221", String.valueOf(221 + 210));
			ligne = StringUtils.replace(ligne, "222", String.valueOf(222 + 210));
			ligne = StringUtils.replace(ligne, "227", String.valueOf(227 + 210));
			ligne = StringUtils.replace(ligne, "228", String.valueOf(228 + 210));
			ligne = StringUtils.replace(ligne, "230", String.valueOf(230 + 210));
			ligne = StringUtils.replace(ligne, "231", String.valueOf(231 + 210));
			ligne = StringUtils.replace(ligne, "236", String.valueOf(236 + 210));
			ligne = StringUtils.replace(ligne, "237", String.valueOf(237 + 210));
			ligne = StringUtils.replace(ligne, "239", String.valueOf(239 + 210));
			ligne = StringUtils.replace(ligne, "240", String.valueOf(240 + 210));
			ligne = StringUtils.replace(ligne, "245", String.valueOf(245 + 210));
			ligne = StringUtils.replace(ligne, "246", String.valueOf(246 + 210));
			ligne = StringUtils.replace(ligne, "248", String.valueOf(248 + 210));
			ligne = StringUtils.replace(ligne, "249", String.valueOf(249 + 210));
			ligne = StringUtils.replace(ligne, "254", String.valueOf(254 + 210));
			ligne = StringUtils.replace(ligne, "255", String.valueOf(255 + 210));
			ligne = StringUtils.replace(ligne, "257", String.valueOf(257 + 210));
			ligne = StringUtils.replace(ligne, "258", String.valueOf(258 + 210));
			ligne = StringUtils.replace(ligne, "263", String.valueOf(263 + 210));
			ligne = StringUtils.replace(ligne, "264", String.valueOf(264 + 210));
			ligne = StringUtils.replace(ligne, "266", String.valueOf(266 + 210));
			ligne = StringUtils.replace(ligne, "267", String.valueOf(267 + 210));
			ligne = StringUtils.replace(ligne, "272", String.valueOf(272 + 210));
			ligne = StringUtils.replace(ligne, "273", String.valueOf(273 + 210));
			ligne = StringUtils.replace(ligne, "275", String.valueOf(275 + 210));

			ligne = StringUtils.replace(ligne, "Nmois", "Nmoins1mois");
			ligne = StringUtils.replace(ligne, "NMOIS", "NMOINS1MOIS");
			ligne = StringUtils.replace(ligne, "année n", "année n-1");
			
			/* ajout dans le résultat. */
			resultat.add(ligne);
		}
		
		return resultat;
		
	} // Fin de substituer(...).___________________________________________
	

	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @return : List<String> :  .<br/>
	 * 
	 * @throws IOException 
	 */
	public static List<String> lireContenu() throws IOException {
		
		final File contenuFile = new File("src/main/resources/templates/contenu.txt");
		final List<String> resultat = Files.readAllLines(contenuFile.toPath());
		return resultat;
		
	} // Fin de lireContenu()._____________________________________________
	
	
	
	/**
	 * point d'entrée de l'application.<br/>
	 *
	 * @param pArgs : String[] :  .<br/>
	 * 
	 * @throws IOException 
	 */
	public static void main(String... pArgs) throws IOException {
		
		final List<String> contenuList = lireContenu();
		final List<String> resultat = substituer(contenuList);
		System.out.println(afficherListeString(resultat));

	} // Fin de main(...)._________________________________________________
	
	
	
	/**
	 * Fournit une String pour l'affichage 
	 * d'une List&lt;String&gt;.<br/>
	 * <br/>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;String&gt;
	 * 
	 * @return : String.<br/>
	 */
	public static String afficherListeString(
			final List<String> pList) {
		
		/* Bloc synchronized. */
		synchronized (GenerateurMJMAnneeNmoins1.class) {
			
			/* retourne null si pList == null. */
			if (pList == null) {
				return null;
			}
			
			final StringBuffer stb = new StringBuffer();
			
			for (final String ligne : pList) {
				stb.append(ligne);
				stb.append(SAUT_LIGNE_JAVA_CHAR);
			}
			
			return stb.toString();
			
		} // Fin de synchronized._____________________________
				
	} // Fin de afficherListeString(...).__________________________________


}
