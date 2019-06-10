package levy.daniel.application.generationcode;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairespaths.ArboresceurPresentProjet;

/**
 * CLASSE Generateur :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * afficherListString, afficherList<String>, afficher Liste String, <br/>
 * afficherListeString, afficher liste String<br/>
 * REGEX, Regex, Expression reguliere, Expression Régulière,
 * détecter champ private, <br/>
 * conserver l'ordre d'entrée d'une Map, LinkedHashMap, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 8 juin 2019
 *
 */
public final class Generateur {

	// ************************ATTRIBUTS************************************/
	
	//*****************************************************************/
	//**************************** SEPARATEURS ************************/
	//*****************************************************************/
	/**
	 * Séparateur point virgule pour les CSV.<br/>
	 * ";"
	 */
	public static final String SEP_PV = ";";
    
	/**
	 * " - ".<br/>
	 */
	public static final String SEPARATEUR_MOINS_AERE = " - ";
		
	/**
	 * "_".<br/>
	 */
	public static final String UNDERSCORE = "_";
	
	/**
	 * "."
	 */
	public static final String POINT = ".";
	
	/**
	 * "/".
	 */
	public static final String SLASH = "/";
	
	/**
	 * Tabulation "\t".<br/>
	 */
	public static final String TAB = "\t";
	
	
	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/

	/**
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");
	
	//*****************************************************************/
	//**************************** BOM_UTF-8 **************************/
	//*****************************************************************/
	/**
	 * '\uFEFF'<br/>
	 * BOM UTF-8 pour forcer Excel 2010 à lire en UTF-8.<br/>
	 */
	public static final char BOM_UTF_8 = '\uFEFF';

	/**
	 * <b>motif REGEX permettant de détecter une 
	 * ligne de déclaration de champ private 
	 * dans une classe source Java</b>.<br/>
	 * Par exemple :<br/>
	 * <code>private String id;</code><br/>
	 * <code>private String numDepartement;</code><br/>
	 * <ol>
	 * <li>^(\\s*) signifie commence par 0 ou plusieurs espaces.</li>
	 * <li>"private " signifie enchaîne par "private" suivi d'un espace.</li>
	 * <li>(\\S+) signifie au moins un caractère non-espace. 
	 * Groupe capturant pour trouver le TYPE du champ.</li>
	 * <li>" " signifie un espace.</li>
	 * <li>(\\S+) signifie au moins un caractère non-espace. 
	 * Groupe capturant pour trouver le NOM du champ.</li>
	 * <li>;$ signifie se termine par un point-virgule.</li>
	 * </ol>
	 * 
	 * <b>"^(\\s*)private (\\S+) (\\S+);$"</b>.
	 */
	public static final String MOTIF_CHAMP_PRIVATE = "^(\\s*)private (\\S+) (\\S+);$";
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(Generateur.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * privat pour bloquer l'instanciation.
	 */
	private Generateur() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * <b>fournit une String comportant le code à insérer 
	 * dans une méthode clone() d'une classe pClass</b>.<br/>
	 * Par exemple :<br/>
	 * <code>clone.setSensLimitrophe(this.getSensLimitrophe());</code><br/>
	 * <code>clone.setClasse(this.getClasse());</code><br/>
	 * <code>clone.setNature(this.getNature());</code><br/>
	 *
	 * @param pClass : java.lang.Class<?>
	 * 
	 * @return : String : code de la méthode clone() de pClass.<br/>
	 * 
	 * @throws IOException 
	 */
	public static String genererMethodeClone(final Class<?> pClass) 
			throws IOException {
		
		final StringBuffer stb = new StringBuffer();
		
		final Map<String, EncapsulationChampGetterSetter> map 
			= fournirMapChampGetterSetter(pClass);
		
		final Collection<EncapsulationChampGetterSetter> listeChamps 
			=  map.values();
		
		for (final EncapsulationChampGetterSetter encaps : listeChamps) {
			
			stb.append("clone.");
			stb.append(encaps.getNomSetter());
			stb.append("(");
			stb.append("this.");
			stb.append(encaps.getNomGetter());
			stb.append("());");
			stb.append(NEWLINE);
		}
		
		return stb.toString();
		
	} // Fin de genererMethodeClone(...).__________________________________

	
	
	/**
	 * <b>fournit une String comportant le code à insérer 
	 * dans une méthode toString() d'une classe pClass</b>.<br/>
	 * <br/>
	 *
	 * @param pClass : java.lang.Class<?>
	 * 
	 * @return : String : code de la méthode toString() de pClass.<br/>
	 * 
	 * @throws IOException
	 */
	public static String genererMethodeToString(final Class<?> pClass) 
			throws IOException {
		
		final StringBuffer stb = new StringBuffer();
		
		final Map<String, EncapsulationChampGetterSetter> map 
			= fournirMapChampGetterSetter(pClass);
		
		if (map == null) {
			return null;
		}
		
		stb.append("");
		stb.append(NEWLINE);
		
		stb.append(TAB + TAB);
		stb.append("final StringBuilder stb = new StringBuilder();");
		stb.append(NEWLINE);
		stb.append(NEWLINE);
		
		stb.append(TAB + TAB);
		stb.append("stb.append(\"");
		stb.append(pClass.getSimpleName());
		stb.append(" [\");");
		stb.append(NEWLINE);
		stb.append(NEWLINE);
		
		final int nombreChamps = map.size();
		int compteur = 0;
		
		for (final EncapsulationChampGetterSetter champ : map.values()) {
			
			compteur++;
			
			stb.append(TAB + TAB);
			stb.append("stb.append(\"");
			stb.append(champ.getNomChamp());
			stb.append("=\");");
			stb.append(NEWLINE);
			
			stb.append(TAB + TAB);
			stb.append("if (this.");
			stb.append(champ.getNomGetter());
			stb.append("() != null) {");
			stb.append(NEWLINE);
			
			stb.append(TAB + TAB + TAB);
			stb.append("stb.append(this.");
			stb.append(champ.getNomGetter());
			stb.append("());");
			stb.append(NEWLINE);
			
			stb.append(TAB + TAB);
			stb.append("} else {");
			stb.append(NEWLINE);
			
			stb.append(TAB + TAB + TAB);
			stb.append("stb.append(NULL);");
			stb.append(NEWLINE);
			
			stb.append(TAB + TAB);
			stb.append('}');
			stb.append(NEWLINE);
			
			if (compteur < nombreChamps) {
				stb.append(TAB + TAB);
				stb.append("stb.append(VIRGULE_ESPACE);");
			}
			
			stb.append(NEWLINE);
			stb.append(NEWLINE);
			
		}
		
		stb.append(TAB + TAB);
		stb.append("stb.append(']');");
		stb.append(NEWLINE);
		stb.append(NEWLINE);
		
		stb.append(TAB + TAB);
		stb.append("return stb.toString();");
		stb.append(NEWLINE);
		stb.append(NEWLINE);

		return stb.toString();
		
	} // Fin de genererMethodeToString(...)._______________________________
	
	
	
	/**
	 * <b>fournit une String comportant le code à insérer 
	 * dans une méthode fournirEnTeteCsv() d'une classe pClass</b>.<br/>
	 * Par exemple :<br/>
	 * <code>return <b>"id;numDepartement;numSection;sens;nature;classe;anneeTraitement;";</b></code>
	 * 
	 * @param pClass : java.lang.Class<?>
	 *
	 * @return : String : 
	 * code de la méthode fournirEnTeteCsv() de pClass.<br/>
	 * 
	 * @throws IOException 
	 */
	public static String genererMethodeFournirEnTeteCsv(
			final Class<?> pClass) throws IOException {
		
		final StringBuffer stb = new StringBuffer();
		
		final List<String> listeChampsPrivateOrdonnee 
			= trouverListeDeclarativeChampsPrivate(pClass);
		
		if (listeChampsPrivateOrdonnee == null) {
			return null;
		}
		
		stb.append("return \"");
		
		for (final String champ : listeChampsPrivateOrdonnee) {
			stb.append(champ);
			stb.append(SEP_PV);
		}
		
		stb.append("\";");
		
		return stb.toString();
		
	} // Fin de genererMethodeFournirEnTeteCsv(...)._______________________

	
	
	/**
	 * <b>fournit une String comportant le code à insérer 
	 * dans une méthode fournirStringCsv() d'une classe pClass</b>.<br/>
	 * Par exemple :<br/>
	 * <pre>
	 * final StringBuilder stb = new StringBuilder();
	 * 
	 * stb.append(this.getId());
	 * stb.append(POINT_VIRGULE);
	 * stb.append(this.getNumDepartement());
	 * stb.append(POINT_VIRGULE);
	 * </pre>
	 * 
	 * @param pClass : java.lang.Class<?>
	 *
	 * @return : String : 
	 * code de la méthode fournirStringCsv() de pClass.<br/>
	 * 
	 * @throws IOException 
	 */
	public static String genererMethodeFournirStringCsv(
			final Class<?> pClass) throws IOException {
		
		final StringBuffer stb = new StringBuffer();
		
		final Map<String, EncapsulationChampGetterSetter> map 
			= fournirMapChampGetterSetter(pClass);
		
		if (map == null) {
			return null;
		}
		
		stb.append("");
		stb.append(NEWLINE);
		
		stb.append(TAB + TAB);
		stb.append("final StringBuilder stb = new StringBuilder();");
		stb.append(NEWLINE);
		stb.append(NEWLINE);
		
		for (final EncapsulationChampGetterSetter champ : map.values()) {
			stb.append(TAB + TAB);
			stb.append("stb.append(");
			stb.append("this.");
			stb.append(champ.getNomGetter());
			stb.append("());");
			stb.append(NEWLINE);
			stb.append(TAB + TAB);
			stb.append("stb.append(POINT_VIRGULE);");
			stb.append(NEWLINE);
		}
		
		stb.append(NEWLINE);
		stb.append(TAB + TAB);
		stb.append("return stb.toString();");
		stb.append(NEWLINE);
		
		return stb.toString();
		
	} // Fin de genererMethodeFournirStringCsv(...)._______________________

	
	
	/**
	 * <b>fournit une String comportant le code à insérer 
	 * dans une méthode fournirEnTeteColonne(int pI) 
	 * d'une classe pClass</b>.<br/>
	 * Par exemple :<br/>
	 * <pre>
	 * String entete = null;
	 * 
	 * switch (pI) {
	 * 		case 0:
	 * 			entete = "id";
	 * 			break;
	 * 
	 * 		case 1:
	 * 			entete = "numDepartement";
	 * 			break;
	 * </pre>
	 * 
	 * @param pClass : java.lang.Class<?>
	 *
	 * @return : String : 
	 * code de la méthode fournirEnTeteColonne() de pClass.<br/>
	 * 
	 * @throws IOException 
	 */
	public static String genererMethodeFournirEnTeteColonne(
			final Class<?> pClass) throws IOException {
		
		final StringBuffer stb = new StringBuffer();
		
		final Map<String, EncapsulationChampGetterSetter> map 
			= fournirMapChampGetterSetter(pClass);
		
		if (map == null) {
			return null;
		}
		
		stb.append("");
		stb.append(NEWLINE);
		
		stb.append(TAB + TAB);
		stb.append("String entete = null;");
		stb.append(NEWLINE);
		stb.append(NEWLINE);
		
		stb.append(TAB + TAB);
		stb.append("switch (pI) {");
		stb.append(NEWLINE);
		stb.append(NEWLINE);
		
		final int nombreChamps = map.size();
		int compteur = 0;
		
		for (final EncapsulationChampGetterSetter champ : map.values()) {
			
			stb.append(TAB + TAB);
			stb.append("case ");
			stb.append(compteur);
			stb.append(":");
			stb.append(NEWLINE);
			
			stb.append(TAB + TAB + TAB);
			stb.append("entete = \"");
			stb.append(champ.getNomChamp());
			stb.append("\";");
			stb.append(NEWLINE);
			
			stb.append(TAB + TAB + TAB);
			stb.append("break;");
			stb.append(NEWLINE);
			stb.append(NEWLINE);
			
			compteur++;
		}
		
		stb.append(TAB + TAB);
		stb.append("default:");
		stb.append(NEWLINE);
		stb.append(TAB + TAB + TAB);
		stb.append("entete = \"invalide\";");
		stb.append(NEWLINE);
		stb.append(TAB + TAB + TAB);
		stb.append("break;");
		stb.append(NEWLINE);
		stb.append(NEWLINE);
		
		stb.append(TAB + TAB);
		stb.append("} // Fin du Switch._________________________________");
		stb.append(NEWLINE);
		stb.append(NEWLINE);
		
		stb.append(TAB + TAB);
		stb.append("return entete;");
		stb.append(NEWLINE);
		stb.append(NEWLINE);
		
		return stb.toString();
		
	} // Fin de genererMethodeFournirEnTeteColonne(...).___________________

	
	
	/**
	 * <b>fournit une String comportant le code à insérer 
	 * dans une méthode fournirValeurColonne(int pI) 
	 * d'une classe pClass</b>.<br/>
	 * Par exemple :<br/>
	 * <pre>
	 * Object valeur = null;
	 * 
	 * 		switch (pI) {
	 * 
	 * 		case 0:
	 * 			valeur = this.getId();
	 * 			break;
	 * 		case 1:
	 * 			valeur = this.getNumDepartement();
	 * 			break;
	 * </pre>
	 * 
	 * @param pClass : java.lang.Class<?>
	 *
	 * @return : String : 
	 * code de la méthode fournirValeurColonne() de pClass.<br/>
	 * 
	 * @throws IOException 
	 */
	public static String genererMethodeFournirValeurColonne(
			final Class<?> pClass) throws IOException {
		
		final StringBuffer stb = new StringBuffer();
		
		final Map<String, EncapsulationChampGetterSetter> map 
			= fournirMapChampGetterSetter(pClass);
		
		if (map == null) {
			return null;
		}
		
		stb.append("");
		stb.append(NEWLINE);
		
		stb.append(TAB + TAB);
		stb.append("Object valeur = null;");
		stb.append(NEWLINE);
		stb.append(NEWLINE);
		
		stb.append(TAB + TAB);
		stb.append("switch (pI) {");
		stb.append(NEWLINE);
		stb.append(NEWLINE);
		
		final int nombreChamps = map.size();
		int compteur = 0;
		
		for (final EncapsulationChampGetterSetter champ : map.values()) {
			
			stb.append(TAB + TAB);
			stb.append("case ");
			stb.append(compteur);
			stb.append(":");
			stb.append(NEWLINE);
			
			stb.append(TAB + TAB + TAB);
			stb.append("valeur = this.");
			stb.append(champ.getNomGetter());
			stb.append("();");
			stb.append(NEWLINE);
			
			stb.append(TAB + TAB + TAB);
			stb.append("break;");
			stb.append(NEWLINE);
			stb.append(NEWLINE);
			
			compteur++;
		}
		
		stb.append(TAB + TAB);
		stb.append("default:");
		stb.append(NEWLINE);
		stb.append(TAB + TAB + TAB);
		stb.append("valeur = \"invalide\";");
		stb.append(NEWLINE);
		stb.append(TAB + TAB + TAB);
		stb.append("break;");
		stb.append(NEWLINE);
		stb.append(NEWLINE);
		
		stb.append(TAB + TAB);
		stb.append("} // Fin du Switch._________________________________");
		stb.append(NEWLINE);
		stb.append(NEWLINE);
		
		stb.append(TAB + TAB);
		stb.append("return valeur;");
		stb.append(NEWLINE);
		stb.append(NEWLINE);
		
		return stb.toString();
		
	} // Fin de genererMethodeFournirValeurColonne(...).___________________


	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param pClass
	 * @return  : String : 
	 * code de la méthode fournirDTO() de pClass.<br/>
	 * 
	 * @throws IOException
	 */
	public static String genererMethodeFournirDTO(
			final Class<?> pClass) throws IOException {
		
		final StringBuffer stb = new StringBuffer();
		
		final Map<String, EncapsulationChampGetterSetter> map 
			= fournirMapChampGetterSetter(pClass);
		
		if (map == null) {
			return null;
		}
		
		stb.append("");
		stb.append(NEWLINE);
		
		final int nombreChamps = map.size();
		int compteur = 0;
		
		for (final EncapsulationChampGetterSetter champ : map.values()) {
			
			stb.append(TAB + TAB);
			stb.append("sectionDTO.");
			stb.append(champ.getNomSetter());
			stb.append("(pDescriptionChamp.get(");
			stb.append(compteur);
			stb.append("));");
			stb.append(NEWLINE);
						
			compteur++;
		}

		return stb.toString();
	}
	
	
	
	/**
	 * <b>retourne la liste des champs private de la classe pClasse 
	 * <i>ordonnée selon l'ordre de déclaration dans la classe</i></b>.<br/>
	 *
	 * @param pClass : java.lang.Class<?>
	 * 
	 * @return : List&lt;String&gt; :  
	 * liste des champs private de la classe <i>ordonnée 
	 * selon l'ordre déclaratif</i>.<br/>
	 * 
	 * @throws IOException 
	 */
	public static List<String> trouverListeDeclarativeChampsPrivate(
			final Class<?> pClass) throws IOException {
		
		final File fichierSource = trouverFichierSource(pClass);
		
		if (fichierSource == null) {
			return null;
		}
		
		final List<String> resultat = new ArrayList<String>();
		
		final List<String> lignesLues 
			= Files.readAllLines(fichierSource.toPath());
		
		final Pattern patternChampPrivate 
			= Pattern.compile(MOTIF_CHAMP_PRIVATE);
				
		final List<String> champsPrivate = new ArrayList<String>();
		
		for (final String ligne : lignesLues) {
			
			final Matcher matcherChampPrivate 
				= patternChampPrivate.matcher(ligne);
			
			if (matcherChampPrivate.matches()) {
				
				final String nomChamp = matcherChampPrivate.group(3);
				resultat.add(nomChamp);
				
			}
		}
		
		return resultat;
		
	} // Fin de trouverListeDeclarativeChampsPrivate(...)._________________

	
		
	/**
	 * <b>fournit une Map&lt;String,EncapsulationChampGetterSetter&gt;
	 * d'encapsulations associant un champ d'une classe, 
	 * son Getter et son Setter</b> avec :.<br/>
	 * - String : le nom du champ.<br/>
	 * - EncapsulationChampGetterSetter : 
	 * encapsulation contenant le champ, son getter et son setter.<br/>
	 * <ul>
	 * <li><b>conserve l'ordre de déclaration des champs dans la classe</b>.</li>
	 * </ul>
	 *
	 * @param pClass : java.lang.Class<?>
	 * 
	 * @return : Map&lt;String,EncapsulationChampGetterSetter&gt; :  .<br/>
	 * 
	 * @throws IOException 
	 */
	public static Map<String, EncapsulationChampGetterSetter> 
				fournirMapChampGetterSetter(final Class<?> pClass) 
						throws IOException {
		
		final Map<String, EncapsulationChampGetterSetter> resultat 
			= new ConcurrentHashMap<String, EncapsulationChampGetterSetter>();
		
		final List<Field> listeChamps = findChampsPrivate(pClass);
		
		for (final Field field : listeChamps) {
			
			final EncapsulationChampGetterSetter encaps 
				= new EncapsulationChampGetterSetter();
			
			final String nomChamp = field.getName();
			
			encaps.setChamp(field);
			encaps.setNomChamp(nomChamp);
			
			final Method getter = trouverGetterChamp(pClass, field);
			if (getter != null) {
				encaps.setGetter(getter);
				encaps.setNomGetter(getter.getName());
			}
			
			
			final Method setter = trouverSetterChamp(pClass, field);
			if (setter != null) {
				encaps.setSetter(setter);
				encaps.setNomSetter(setter.getName());
			}
			
			resultat.put(nomChamp, encaps);
		}
		
		/* ORDRE DECLARATIF. */
		final List<String> listeChampsPrivateOrdonnee 
			= trouverListeDeclarativeChampsPrivate(pClass);
		
		final Map<String, EncapsulationChampGetterSetter> resultatOrdonne 
			= new LinkedHashMap<String, EncapsulationChampGetterSetter>();
		
		for (final String champ : listeChampsPrivateOrdonnee) {
			resultatOrdonne.put(champ, resultat.get(champ));
		}
		
		return resultatOrdonne;
		
	} // Fin de fournirMapChampGetterSetter(...).__________________________

	
	
	/**
	 * retourne le Getter (Method) associé au champ pField 
	 * dans la classe pClass.<br/>
	 *
	 * @param pClass : java.lang.Class<?>
	 * @param pField : java.lang.reflect.Field
	 * 
	 * @return : java.lang.reflect.Method : 
	 * Getter (Method) associé au champ pField.<br/>
	 */
	public static Method trouverGetterChamp(
			final Class<?> pClass, final Field pField) {
		
		final String getterATrouver 
			= "get" + StringUtils.capitalize(pField.getName());
		
		final String getterBooleanATrouver 
			= "is" + StringUtils.capitalize(pField.getName());
		
		final List<Method> listeGetters = findGetters(pClass);
		
		for (final Method method : listeGetters) {
			
			final String nomMethode = method.getName();
			
			if (nomMethode.equals(getterATrouver) 
					|| nomMethode.equals(getterBooleanATrouver)) {
				
				return method;
			}
		}
		
		return null;
		
	} // Fin de trouverGetterChamp(...).___________________________________

	
	
	/**
	 * retourne le Setter (Method) associé au champ pField 
	 * dans la classe pClass.<br/>
	 *
	 * @param pClass : java.lang.Class<?>
	 * @param pField : java.lang.reflect.Field
	 * 
	 * @return : java.lang.reflect.Method : 
	 * Setter (Method) associé au champ pField.<br/>
	 */
	public static Method trouverSetterChamp(
			final Class<?> pClass, final Field pField) {
		
		final String setterATrouver 
			= "set" + StringUtils.capitalize(pField.getName());
		
		final List<Method> listeSetters = findSetters(pClass);
		
		for (final Method method : listeSetters) {
			
			if (method.getName().equals(setterATrouver)) {
				return method;
			}
		}
		
		return null;
		
	} // Fin de trouverSetterChamp(...).___________________________________
	
	
	
	/**
	 * retourne la liste de tous les champs (attributs) 
	 * de la classe pClass.<br/>
	 *
	 * @param pClass : java.lang.Class<?>
	 * 
	 * @return : List&lt;Field&gt; : 
	 * liste des champs (attributs) de la classe.<br/>
	 */
	public static List<Field> findChamps(final Class<?> pClass) {
		
		final List<Field> list = new ArrayList<Field>();
		
		final Field[] fields = pClass.getDeclaredFields();
		
		for (final Field field : fields) {
			list.add(field);
		}
		
		return list;
		
	} // Fin de findChamps(...).___________________________________________

	
	
	/**
	 * retourne la liste des champs (attributs) PRIVATE NON STATIC
	 * de la classe pClass.<br/>
	 *
	 * @param pClass : java.lang.Class<?>
	 * 
	 * @return : List&lt;Field&gt; : 
	 * liste des champs (attributs) de la classe.<br/>
	 */
	public static List<Field> findChampsPrivate(final Class<?> pClass) {
		
		final List<Field> list = new ArrayList<Field>();
		
		final Field[] fields = pClass.getDeclaredFields();
		
		for (final Field field : fields) {
			
			if (Modifier.isPrivate(field.getModifiers()) 
					&& !Modifier.isStatic(field.getModifiers())) {
				
				list.add(field);
			}
			
		}
		
		return list;
		
	} // Fin de findChampsPrivate(...).______________________________________

	
	
	/**
	 * retourne la liste des getters et des setters de la classe pClass.<br/>
	 *
	 * @param pClass : java.lang.Class<?>
	 * 
	 * @return : List&lt;Method&gt; : 
	 * liste des getters et des setters de la classe pClass.<br/>
	 */
	public static List<Method> findGettersAndSetters(final Class<?> pClass) {
		
		final List<Method> list = new ArrayList<Method>();
		
		final Method[] methods = pClass.getDeclaredMethods();
		
		for (final Method method : methods) {
			
			if (isGetter(method) || isSetter(method)) {
				list.add(method);
			}
				
		}
			
		return list;
		
	} // Fin de findGettersAndSetters(...).________________________________


	
	/**
	 * retourne la liste des getters de la classe pClass.<br/>
	 *
	 * @param pClass : java.lang.Class<?>
	 * 
	 * @return : List&lt;Method&gt; : 
	 * liste des getters de la classe pClass.<br/>
	 */
	public static List<Method> findGetters(final Class<?> pClass) {
		
		final List<Method> list = new ArrayList<Method>();
		
		final Method[] methods = pClass.getDeclaredMethods();
		
		for (final Method method : methods) {
			
			if (isGetter(method)) {
				list.add(method);
			}
				
		}
			
		return list;
		
	} // Fin de findGetters(...).__________________________________________


	
	/**
	 * retourne la liste des setters de la classe pClass.<br/>
	 *
	 * @param pClass : java.lang.Class<?>
	 * 
	 * @return : List&lt;Method&gt; : 
	 * liste des setters de la classe pClass.<br/>
	 */
	public static List<Method> findSetters(final Class<?> pClass) {
		
		final List<Method> list = new ArrayList<Method>();
		
		final Method[] methods = pClass.getDeclaredMethods();
		
		for (final Method method : methods) {
			
			if (isSetter(method)) {
				list.add(method);
			}
				
		}
			
		return list;
		
	} // Fin de findSetters(...).__________________________________________


	
	/**
	 * Détermine si pMethod est un Getter.<br/>
	 *
	 * @param pMethod : java.lang.reflect.Method
	 * 
	 * @return : boolean : true si pMethod est un Getter.<br/>
	 */
	public static boolean isGetter(final Method pMethod) {

		if (Modifier.isPublic(pMethod.getModifiers()) 
				&& pMethod.getParameterTypes().length == 0) {

			/* si c'est un getter de champ classique. */
			if (pMethod.getName().matches("^get[A-Z].*") 
					&& !pMethod.getReturnType().equals(void.class)) {

				return true;
			}

			/* si c'est un getter de champ boolean. */
			if (pMethod.getName().matches("^is[A-Z].*") 
					&& pMethod.getReturnType().equals(boolean.class)) {

				return true;
			}

		}

		return false;

	} // Fin de isGetter(...)._____________________________________________


	
	/**
	 * Détermine si pMethod est un Setter.<br/>
	 *
	 * @param pMethod : java.lang.reflect.Method
	 * 
	 * @return : boolean : true si pMethod est un Setter.<br/>
	 */
	public static boolean isSetter(Method pMethod) {

		return Modifier.isPublic(pMethod.getModifiers()) 
				&& pMethod.getReturnType().equals(void.class)
				&& pMethod.getParameterTypes().length == 1 
				&& pMethod.getName().matches("^set[A-Z].*");

	} // Fin de isSetter(...)._____________________________________________


	
	/**
	 * <b>retourne le fichier source correspondant à pClass 
	 * dans le présent projet ECLIPSE</b>.<br/>
	 *
	 * @param pClass : java.lang.Class<?>
	 * 
	 * @return : File : File encapsulant pClass.java.<br/>
	 */
	public static File trouverFichierSource(final Class<?> pClass) {
		
		
		final Path pathSrcMainJava 
			= ArboresceurPresentProjet.getSrcMainJavaPath();
		
		final Path pathRelatif = fournirPathAPartirCanonicalName(pClass);
		
		final Path pathAbsoluFichierSource 
			= pathSrcMainJava.resolve(pathRelatif);
		
		return pathAbsoluFichierSource.toFile();
		
	} // Fin de trouverFichierSource(...)._________________________________


	
	/**
	 * <b>retourne le PATH RELATIF</b> <i>par rapport au répertoire des sources</i> 
	 * du présent projet <code>${ceProjet}/src/main/java</code> 
	 * d'une Classe pClass.
	 * <ul>
	 * <li>transforme les points du chemin d'une classe Java en slashes.</i>
	 * <li>rajoute l'extension.java au nom de la classe</li>
	 * </ul>
	 * Par exemple :<br/>
	 * retourne 
	 * <code><b>levy/daniel/application/model/dto/metier/sections/impl/SectionHitDTO.java</b></code> 
	 * pour la classe 
	 * <code><b>levy.daniel.application.model.dto.metier.sections.impl.SectionHitDTO</b></code><br/>
	 * <br/>
	 *
	 * @param pClass : java.lang.Class<?>
	 * 
	 * @return : Path : Path relatif de la classe source Java.<br/>
	 */
	public static Path fournirPathAPartirCanonicalName(final Class<?> pClass) {
		
		final String canonicalName = pClass.getCanonicalName();
		
		final String pathRelatifString 
			= StringUtils.replace(canonicalName, POINT, SLASH) + ".java";
		
		final Path pathRelatif = Paths.get(pathRelatifString);
		
		return pathRelatif;
		
	} // Fin de fournirPathAPartirCanonicalName(...).______________________
	
	
	
	/**
	 * retourne une String pour affichage d'une List&lt;Field&gt; pList.<br/>
	 *
	 * @param pList : List&lt;Field&gt;.
	 * 
	 * @return : String : pour affichage.<br/>
	 */
	public static String afficherListeField(final List<Field> pList) {
		
		final StringBuffer stb = new StringBuffer();
		
		for (final Field field : pList) {
			stb.append(field.getName());
			stb.append(NEWLINE);
		}
		
		return stb.toString();
		
	} // Fin de afficherListeField(...).___________________________________
	

	
	/**
	 * retourne une String pour affichage d'une List&lt;Method&gt; pList.<br/>
	 *
	 * @param pList : List&lt;Method&gt;.
	 * 
	 * @return : String : pour affichage.<br/>
	 */
	public static String afficherListeMethod(final List<Method> pList) {
		
		final StringBuffer stb = new StringBuffer();
		
		for (final Method method : pList) {
			stb.append(method.getName());
			stb.append(NEWLINE);
		}
		
		return stb.toString();
		
	} // Fin de afficherListeMethod(...).__________________________________
	

	
	/**
	 * retourne une String pour l'affichage d'une 
	 * Map&lt;String, EncapsulationChampGetterSetter&gt;.
	 *
	 * @param pMap : Map&lt;String, EncapsulationChampGetterSetter&gt;
	 * 
	 * @return : String : pour affichage.<br/>
	 */
	public static String afficherMapStringEncapsulation(
			final Map<String, EncapsulationChampGetterSetter> pMap) {
		
		final StringBuffer stb = new StringBuffer();
		
		final Set<Entry<String, EncapsulationChampGetterSetter>> entrySet 
			= pMap.entrySet();
		
		final Iterator<Entry<String, EncapsulationChampGetterSetter>> ite 
			= entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<String, EncapsulationChampGetterSetter> entry 
				= ite.next();
			
			final EncapsulationChampGetterSetter encaps = entry.getValue();
			
			stb.append(encaps.toString());
			stb.append(NEWLINE);
		}
		
		return stb.toString();
		
	} // Fin de afficherMapStringEncapsulation(...)._______________________

	
	
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
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		for (final String ligne : pList) {
			stb.append(ligne);
			stb.append(NEWLINE);
		}
		
		return stb.toString();
			
	} // Fin de afficherListeString(...).__________________________________
	
	
	
	/**
	 * Point d'entrée de l'application.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 *
	 * @param pArgs : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	public static void main(
			final String... pArgs) throws Exception {
		
		final Class<?> classe = Class.forName("levy.daniel.application.model.dto.metier.sections.impl.SectionHitDTO");
		
//		List<Method> listeGetters = findGetters(classe);
//		System.out.println(afficherListeMethod(listeGetters));
//		
//		System.out.println();
//		
//		List<Method> listeSetters = findSetters(classe);
//		System.out.println(afficherListeMethod(listeSetters));
		
		
//		List<Field> listeChamps = findChampsPrivate(classe);
//		System.out.println(afficherListeField(listeChamps));
		
		
//		final Map<String, EncapsulationChampGetterSetter> map 
//			= fournirMapChampGetterSetter(classe);
//		
//		System.out.println(afficherMapStringEncapsulation(map));
//		
//		System.out.println("nombre de champs : " + map.size());
		

//		final File fichierSource = trouverFichierSource(classe);
//		System.out.println(fichierSource.getAbsolutePath());
//		System.out.println("existes ? : " + fichierSource.exists());
		
//		final List<String> listeChampsPrivateOrdonnee 
//			= trouverListeDeclarativeChampsPrivate(classe);
//		
//		System.out.println(afficherListeString(listeChampsPrivateOrdonnee));


//		System.out.println(genererMethodeClone(classe));
		
//		System.out.println(genererMethodeToString(classe));
		
//		System.out.println(genererMethodeFournirEnTeteCsv(classe));
		
//		System.out.println(genererMethodeFournirStringCsv(classe));
		
//		System.out.println(genererMethodeFournirEnTeteColonne(classe));
		
//		System.out.println(genererMethodeFournirValeurColonne(classe));
		
		System.out.println(genererMethodeFournirDTO(classe));
		
    } // Fin de main(...)._________________________________________________
	
	
	
	
	
} // FIN DE LA CLASSE Generateur.--------------------------------------------
