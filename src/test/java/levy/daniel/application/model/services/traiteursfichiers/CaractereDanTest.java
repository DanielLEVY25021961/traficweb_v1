package levy.daniel.application.model.services.traiteursfichiers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;





/**
 * CLASSE CaractereDanTest :<br/>
 * Test JUnit de {@link CaractereDan}.<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 23 sept. 2019
 *
 */
public class CaractereDanTest {

	// ************************ATTRIBUTS************************************/

	/**
	 * CARAC_NULL : CaractereDan :<br/>
	 * .<br/>
	 */
	public static final CaractereDan CARAC_NULL 
	= new CaractereDan(null, null, null, null, 0, 0, 0, 0, null, null);
	
	/**
	 * CARAC_D1 : CaractereDan :<br/>
	 * 'd' LATIN SMALL LETTER D.<br/>
	 */
	public static final CaractereDan CARAC_D1 
	= new CaractereDan(2L, 258, 'd', "\\u0064", 13, 2, 100, 100, "64", "LATIN SMALL LETTER D");
	
	/**
	 * CARAC_D2 : CaractereDan :<br/>
	 * 'd' LATIN SMALL LETTER D.<br/>
	 */
	public static final CaractereDan CARAC_D2 
	= new CaractereDan(3L, 264, 'd', "\\u0064", 13, 2, 100, 100, "64", "LATIN SMALL LETTER D");
	
	/**
	 * CARAC_E_AIGU : CaractereDan :<br/>
	 * 'é' LATIN SMALL LETTER E WITH ACUTE.<br/>
	 */
	public static final CaractereDan CARAC_E_AIGU 
	= new CaractereDan(454L, 454, 'é', "\\u00e9", -1, 2, 233, 233, "e9", "LATIN SMALL LETTER E WITH ACUTE");
	
	/**
	 * CARAC_E_GRAVE : CaractereDan :<br/>
	 * 'è' LATIN SMALL LETTER E WITH GRAVE.<br/>
	 */
	public static final CaractereDan CARAC_E_GRAVE 
	= new CaractereDan(454L, 454, 'è', "\\u00e8", -1, 2, 232, 232, "e8", "LATIN SMALL LETTER E WITH GRAVE");
	
	/**
	 * CARAC_POINT : CaractereDan :<br/>
	 * '.' FULL STOP.<br/>
	 */
	public static final CaractereDan CARAC_POINT 
	= new CaractereDan(501L, 501, '.', "\\u002e", -1, 24, 46, 46, "2e", "FULL STOP");
	
	/**
	 * CARAC_REPLACEMENT : CaractereDan :<br/>
	 * REPLACEMENT CHARACTER, "\\ufffd".<br/>
	 * Caractère de remplacement introduit lors de la lecture en UTF-8 
	 * d'un fichier texte encodé avec un autre Charset.<br/>
	 */
	public static final CaractereDan CARAC_REPLACEMENT 
	= new CaractereDan(1L, 259, '�', "\\ufffd", -1, 28, 65533, 65533, "fffd", "REPLACEMENT CHARACTER");
	
	/**
	 * TOSTRING_CARAC_NULL : String :<br/>
	 * toString() de CARAC_NULL.<br/>
	 */
	public static final String TOSTRING_CARAC_NULL = "Caractère : null  Unicode : null    NumericValue : 0     TypeCaractere : 0   valeurEntiere : 0     Point de Code décimal : 0     Point de Code Hexa : null    Nom : null                                    ";
	
	/**
	 * TOCSV_CARAC_NULL : String :<br/>
	 * fournirStringCsv() de CARAC_NULL.<br/>
	 * "null;null;null;null;0;0;0;0;null;null;".<br/>
	 */
	public static final String TOCSV_CARAC_NULL = "null;null;null;null;0;0;0;0;null;null;";
		
	/**
	 * LISTE_COLONNES_CARAC_NULL : List<Object> :<br/>
	 * Liste des valeurs dans les colonnes de CARAC_NULL.<br/>
	 */
	public static final List<Object> LISTE_COLONNES_CARAC_NULL = new ArrayList<Object>();

	
	/**
	 * TOSTRING_CARAC_E_AIGU : String :<br/>
	 * toString() de CARAC_E_AIGU.<br/>
	 */
	public static final String TOSTRING_CARAC_E_AIGU = "id : 454    Position : 454    Caractère : é     Unicode : \u00e9  NumericValue : -1    TypeCaractere : 2   valeurEntiere : 233   Point de Code décimal : 233   Point de Code Hexa : e9      Nom : LATIN SMALL LETTER E WITH ACUTE         ";
	
	/**
	 * TOCSV_CARAC_E_AIGU : String :<br/>
	 * fournirStringCsv() de CARAC_E_AIGU.<br/>
	 * "454;454;é;\u00e9;-1;2;233;233;e9;LATIN SMALL LETTER E WITH ACUTE;".<br/>
	 */
	public static final String TOCSV_CARAC_E_AIGU = "454;454;é;\u00e9;-1;2;233;233;e9;LATIN SMALL LETTER E WITH ACUTE;";
	
	/**
	 * LISTE_COLONNES_CARAC_E_AIGU : List<Object> :<br/>
	 * Liste des valeurs dans les colonnes de CARAC_E_AIGU.<br/>
	 */
	public static final List<Object> LISTE_COLONNES_CARAC_E_AIGU = new ArrayList<Object>();
	
	
	/**
	 * ENTETE_CARACTEREDAN : String :<br/>
	 * "id;Position;Caractère;Unicode;numericValue;Type de Caractère;
	 * Valeur Entière;Point de Code Décimal;Point de Code HexaDécimal;
	 * Nom Unicode;".<br/>
	 */
	public static final String ENTETE_CARACTEREDAN 
		= "id;Position;Caractère;Unicode;numericValue;Type de Caractère;"
				+ "Valeur Entière;Point de Code Décimal;"
				+ "Point de Code HexaDécimal;Nom Unicode;";
	
	static {
		
		LISTE_COLONNES_CARAC_NULL.add(null);
		LISTE_COLONNES_CARAC_NULL.add(null);
		LISTE_COLONNES_CARAC_NULL.add(null);
		LISTE_COLONNES_CARAC_NULL.add(null);
		LISTE_COLONNES_CARAC_NULL.add(0);
		LISTE_COLONNES_CARAC_NULL.add(0);
		LISTE_COLONNES_CARAC_NULL.add(0);
		LISTE_COLONNES_CARAC_NULL.add(0);
		LISTE_COLONNES_CARAC_NULL.add(null);
		LISTE_COLONNES_CARAC_NULL.add(null);
		
		LISTE_COLONNES_CARAC_E_AIGU.add(454L);
		LISTE_COLONNES_CARAC_E_AIGU.add(454);
		LISTE_COLONNES_CARAC_E_AIGU.add('é');
		LISTE_COLONNES_CARAC_E_AIGU.add("\\u00e9");
		LISTE_COLONNES_CARAC_E_AIGU.add(-1);
		LISTE_COLONNES_CARAC_E_AIGU.add(2);
		LISTE_COLONNES_CARAC_E_AIGU.add(233);
		LISTE_COLONNES_CARAC_E_AIGU.add(233);
		LISTE_COLONNES_CARAC_E_AIGU.add("e9");
		LISTE_COLONNES_CARAC_E_AIGU.add("LATIN SMALL LETTER E WITH ACUTE");
		
	}

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(CaractereDanTest.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>

	 */
	public CaractereDanTest() {		
		/* Instanciation de la Super-Classe. */
		super();		
	} // Fin de CaractereDanTest().________________________________________
	

	/**
	 * Teste le constructeur d'arite nulle 
	 * pour Vérifier les NullPointerException.<br/>
	 * <br/>
	 * Contrats Java :<br/>
	 * - Si deux objets sont equals, alors même HashCode.<br/>
	 * - Si deux objets ne sont pas equals, alors HashCode différents.<br/>
	 * <br/>
	 * - x.equals(y) ---> x.compareTo(y) == 0.<br/>
	 * <br/>
	 * - vérifie les contrats Java.<br/>
	 * - vérifie que CONSTRUCTEUR_ARITE_NULLE == CONSTRUCTEUR_AUTO_NULL 
	 * == CONSTRUCTEUR_COMPLET_NULL.<br/>
	 * - Vérifie le toString().<br/>
	 * - Vérifie le fournirStringCsv().<br/>
	 * - Vérifie le fournirValeurColonne().<br/>
	 * - vérifie equals.<br/>
	 * - vérifie hashcode.<br/>
	 * - vérifie compare.<br/>
	 * - vérifie clone.<br/>
	 * <br/>
	 * 
	 * @throws CloneNotSupportedException 
	 */
	@Test
	public void testConstructeurAriteNulle() throws CloneNotSupportedException {
		
		/* vérifie que CONSTRUCTEUR_ARITE_NULLE == CONSTRUCTEUR_AUTO_NULL 
		 * == CONSTRUCTEUR_COMPLET_NULL.*/
		final CaractereDan caracAriteNulle1 = new CaractereDan();
		
		final CaractereDan caracAutoNull = new CaractereDan(null);
		
		final CaractereDan caracNull1 
		= new CaractereDan(1L, null, null, null, 0, 0, 0, 0, null, null);

		
		/* Vérifie le toString(). */
		assertEquals("caracAriteNulle1.toString() equals TOSTRING_CARAC_NULL : "
					, TOSTRING_CARAC_NULL, caracAriteNulle1.toString());
		
		assertEquals("caracAutoNull.toString() equals TOSTRING_CARAC_NULL : "
				, TOSTRING_CARAC_NULL, caracAutoNull.toString());
		
		assertEquals("CARAC_NULL.toString() equals TOSTRING_CARAC_NULL : "
				, TOSTRING_CARAC_NULL, CARAC_NULL.toString());
		
		assertNotEquals("caracNull1.toString() equals TOSTRING_CARAC_NULL : "
				, TOSTRING_CARAC_NULL, caracNull1.toString());
		
		/* Vérifie le fournirStringCsv(). */
		assertEquals("caracAriteNulle1.fournirStringCsv() equals TOCSV_CARAC_NULL : "
				, TOCSV_CARAC_NULL, caracAriteNulle1.fournirStringCsv());
		
		assertEquals("caracAutoNull.fournirStringCsv() equals TOCSV_CARAC_NULL : "
				, TOCSV_CARAC_NULL, caracAutoNull.fournirStringCsv());
		
		assertEquals("CARAC_NULL.fournirStringCsv() equals TOCSV_CARAC_NULL : "
				, TOCSV_CARAC_NULL, CARAC_NULL.fournirStringCsv());
		
		assertNotEquals("caracNull1.fournirStringCsv() equals TOCSV_CARAC_NULL : "
				, TOCSV_CARAC_NULL, caracNull1.fournirStringCsv());
		
		/* Vérifie le fournirValeurColonne(). */
		this.assertionsListeColonne(LISTE_COLONNES_CARAC_NULL, caracAriteNulle1);
		
		this.assertionsListeColonne(LISTE_COLONNES_CARAC_NULL, caracAutoNull);
		
		this.assertionsListeColonne(LISTE_COLONNES_CARAC_NULL, CARAC_NULL);
		
		
		/* vérifie equals. */
		assertEquals("caracAriteNulle1 equals caracNull1 : "
				, caracAriteNulle1, caracNull1);
		
		assertEquals("caracAutoNull equals caracNull1 : "
				, caracAutoNull, caracNull1);
		
		assertEquals("caracAriteNulle1 equals caracAutoNull : "
				, caracAriteNulle1, caracAutoNull);
		
		assertEquals("caracAriteNulle1 equals CARAC_NULL : "
				, caracAriteNulle1, CARAC_NULL);
		
		/* vérifie hashcode. */
		assertEquals("caracAriteNulle1 equals caracNull1, donc caracAriteNulle1.hascode == caracNull1.hascode : "
				, caracAriteNulle1.hashCode(), caracNull1.hashCode());
		
		assertEquals("caracAutoNull equals caracNull1, donc caracAutoNull.hascode == caracNull1.hascode : "
				, caracAutoNull.hashCode(), caracNull1.hashCode());
		
		assertEquals("caracAriteNulle1 equals caracAutoNull, donc caracAriteNulle1.hascode == caracAutoNull.hascode : "
				, caracAriteNulle1.hashCode(), caracAutoNull.hashCode());
		
		assertEquals("caracAriteNulle1 equals CARAC_NULL, donc caracAriteNulle1.hascode == CARAC_NULL.hascode : "
				, caracAriteNulle1.hashCode(), CARAC_NULL.hashCode());
		
		/* vérifie compare. */
		assertEquals("caracAriteNulle1.compareTo(caracNull1) == 0 : "
				, 0, caracAriteNulle1.compareTo(caracNull1));
		
		assertEquals("caracAutoNull.compareTo(caracNull1) == 0 : "
				, 0, caracAutoNull.compareTo(caracNull1));
		
		assertEquals("caracAriteNulle1.compareTo(caracAutoNull) == 0 : "
				, 0, caracAriteNulle1.compareTo(caracAutoNull));
		
		assertEquals("caracAriteNulle1.compareTo(CARAC_NULL) == 0 : "
				, 0, caracAriteNulle1.compareTo(CARAC_NULL));
		
		/* vérifie clone. */
		final CaractereDan caracAriteNulle1Clone 
			= (CaractereDan) caracAriteNulle1.clone();
		
		final CaractereDan caracAutoNullClone 
			= (CaractereDan) caracAutoNull.clone();
		
		final CaractereDan caracNull1Clone 
			= (CaractereDan) caracNull1.clone();
		
		assertFalse("caracAriteNulle1 pas == caracAriteNulle1Clone : ", caracAriteNulle1 == caracAriteNulle1Clone);
		assertEquals("caracAriteNulle1 equals caracAriteNulle1Clone : ", caracAriteNulle1, caracAriteNulle1Clone);
		
		assertFalse("caracAutoNull pas == caracAutoNullClone : ", caracAutoNull == caracAutoNullClone);
		assertEquals("caracAutoNull equals caracAutoNullClone : ", caracAutoNull, caracAutoNullClone);
		
		assertFalse("caracNull1 pas == caracNull1Clone : ", caracNull1 == caracNull1Clone);
		assertEquals("caracNull1 equals caracNull1Clone : ", caracNull1, caracNull1Clone);
		
	} // Fin de testConstructeurAriteNulle().______________________________
	

	
	/**
	 * method testConstructeurAutoNul() :<br/>
	 * Teste le constructeur à remplissage automatique avec un Character null.<br/>
	 * <br/>
	 * Contrats Java :<br/>
	 * - Si deux objets sont equals, alors même HashCode.<br/>
	 * - Si deux objets ne sont pas equals, alors HashCode différents.<br/>
	 * <br/>
	 * - x.equals(y) ---> x.compareTo(y) == 0.<br/>
	 * <br/>
	 * - vérifie les contrats Java.<br/>
	 * - vérifie que CONSTRUCTEUR_ARITE_NULLE == CONSTRUCTEUR_AUTO_NULL 
	 * == CONSTRUCTEUR_COMPLET_NULL.<br/>
	 * - Vérifie le toString().<br/>
	 * - Vérifie le fournirStringCsv().<br/>
	 * - Vérifie le fournirValeurColonne().<br/>
	 * - vérifie equals.<br/>
	 * - vérifie hashcode.<br/>
	 * - vérifie compare.<br/>
	 * - vérifie clone.<br/>
	 * <br/>
	 *
	 * @throws CloneNotSupportedException
	 */
	@Test
	public void testConstructeurAutoNul() throws CloneNotSupportedException {
		this.testConstructeurAriteNulle();
	} // Fin de testConstructeurAutoNul()._________________________________
	
	
	
	/**
	 * method testConstructeurCompletNull() :<br/>
	 * Teste le constructeur complet avec toutes les valeurs à null 
	 * pour Vérifier les NullPointerException.<br/>
	 * <br/>
	 * Contrats Java :<br/>
	 * - Si deux objets sont equals, alors même HashCode.<br/>
	 * - Si deux objets ne sont pas equals, alors HashCode différents.<br/>
	 * <br/>
	 * - x.equals(y) ---> x.compareTo(y) == 0.<br/>
	 */
	@Test
	public void testConstructeurCompletNull() {
		
		final CaractereDan caracNull1 
		= new CaractereDan(1L, null, null, null, 0, 0, 0, 0, null, null);
		
		final CaractereDan caracNull2 
		= new CaractereDan(2L, null, null, null, 0, 0, 0, 0, null, null);
		
		assertEquals("caracNull1 equals caracNull2 : "
				, caracNull1, caracNull2);
		
		assertEquals("caracNull1 equals caracNull2, donc caracNull1.hascode == caracNull2.hascode : "
				, caracNull1.hashCode(), caracNull2.hashCode());
		
		assertEquals("caracNull1.compareTo(caracNull2) == 0 : "
				, 0, caracNull1.compareTo(caracNull2));
		
		
	} // Fin de testConstructeurCompletNull()._____________________________

	
		
	/**
	 * method testConstructeurAriteNulleEtSetters() :<br/>
	 * Teste le constructeur d'arité nulle et les setters.<br/>
	 * <br/>
	 * "id;Position;Caractère;Unicode;numericValue;Type de Caractère;
	 * Valeur Entière;Point de Code Décimal;
	 * Point de Code HexaDécimal;Nom Unicode;".<br/>
	 * <br/>
	 * Contrats Java :<br/>
	 * - Si deux objets sont equals, alors même HashCode.<br/>
	 * - Si deux objets ne sont pas equals, alors HashCode différents.<br/>
	 * <br/>
	 * - x.equals(y) ---> x.compareTo(y) == 0.<br/>
	 */
	@Test
	public void testConstructeurAriteNulleEtSetters() {
		
		final CaractereDan caracEAiguNull = new CaractereDan();
		
		caracEAiguNull.setId(454L);
		caracEAiguNull.setPosition(454);
		caracEAiguNull.setCaractere('é');
		
		/* Vérifie le toString(). */
		assertEquals("caracEAiguNull.toString() equals CARAC_E_AIGU.toString() : "
				, CARAC_E_AIGU.toString()
					, caracEAiguNull.toString());
		
		/* Vérifie le fournirStringCsv(). */
		assertEquals("caracEAiguNull.fournirStringCsv() equals CARAC_E_AIGU.fournirStringCsv() : "
				, CARAC_E_AIGU.fournirStringCsv()
					, caracEAiguNull.fournirStringCsv());
		
		/* Vérifie le fournirValeurColonne(). */
		this.assertionsListeColonne(LISTE_COLONNES_CARAC_E_AIGU, caracEAiguNull);
		
		/* vérifie equals. */
		assertEquals("caracEAiguNull equals CARAC_E_AIGU : "
				, CARAC_E_AIGU, caracEAiguNull);
		
		assertNotEquals("caracEAiguNull pas equals CARAC_E_GRAVE : "
				, CARAC_E_GRAVE
					, caracEAiguNull);
		
		/* vérifie hashcode. */
		assertEquals("caracEAiguNull equals CARAC_E_AIGU, donc caracEAiguNull.hascode == CARAC_E_AIGU.hascode : "
				, caracEAiguNull.hashCode()
					, CARAC_E_AIGU.hashCode());
		
		assertNotEquals("caracEAiguNull pas equals CARAC_E_GRAVE donc caracEAiguNull.hascode != CARAC_E_GRAVE.hascode : "
				, caracEAiguNull.hashCode()
					, CARAC_E_GRAVE.hashCode());
		
		/* vérifie compare. */
		assertEquals("caracEAiguNull.compareTo(CARAC_E_AIGU) == 0 : "
				, 0, caracEAiguNull.compareTo(CARAC_E_AIGU));
		
		assertTrue("CARAC_E_AIGU après CARAC_E_GRAVE : "
				, CARAC_E_AIGU.compareTo(CARAC_E_GRAVE) > 0);
		
	} // Fin de testConstructeurAriteNulleEtSetters()._____________________
	

	
	/**
	 * method testConstructeurAuto() :<br/>
	 * Teste le constructeur à remplissage automatique.<br/>
	 * <br/>
	 * "id;Position;Caractère;Unicode;numericValue;Type de Caractère;
	 * Valeur Entière;Point de Code Décimal;
	 * Point de Code HexaDécimal;Nom Unicode;".<br/>
	 * <br/>
	 * Contrats Java :<br/>
	 * - Si deux objets sont equals, alors même HashCode.<br/>
	 * - Si deux objets ne sont pas equals, alors HashCode différents.<br/>
	 * <br/>
	 * - x.equals(y) ---> x.compareTo(y) == 0.<br/>
	 */
	@Test
	public void testConstructeurAuto() {
		
		final CaractereDan caracEAiguAuto1 = new CaractereDan(454L, 454, 'é');
		final CaractereDan caracEAiguAuto2 = new CaractereDan(455L, 455, 'é');
		
		/* Vérifie le toString(). */
		assertEquals("caracEAiguAuto1.toString() equals CARAC_E_AIGU.toString() : "
				, CARAC_E_AIGU.toString()
					, caracEAiguAuto1.toString());
		
		/* Vérifie le fournirStringCsv(). */
		assertEquals("caracEAiguAuto1.fournirStringCsv() equals CARAC_E_AIGU.fournirStringCsv() : "
				, CARAC_E_AIGU.fournirStringCsv()
					, caracEAiguAuto1.fournirStringCsv());
		
		/* Vérifie le fournirValeurColonne(). */
		this.assertionsListeColonne(LISTE_COLONNES_CARAC_E_AIGU, caracEAiguAuto1);
		
		/* vérifie equals. */
		assertEquals("caracEAiguAuto1 equals CARAC_E_AIGU : "
				, CARAC_E_AIGU, caracEAiguAuto1);
		
		assertNotEquals("caracEAiguAuto1 pas equals CARAC_E_GRAVE : "
				, CARAC_E_GRAVE
					, caracEAiguAuto1);
		
		/* vérifie hashcode. */
		assertEquals("caracEAiguAuto1 equals CARAC_E_AIGU, donc caracEAiguAuto1.hascode == CARAC_E_AIGU.hascode : "
				, caracEAiguAuto1.hashCode()
					, CARAC_E_AIGU.hashCode());
		
		assertNotEquals("caracEAiguAuto1 pas equals CARAC_E_GRAVE donc caracEAiguAuto1.hascode != CARAC_E_GRAVE.hascode : "
				, caracEAiguAuto1.hashCode()
					, CARAC_E_GRAVE.hashCode());
		
		/* vérifie compare. */
		assertEquals("caracEAiguAuto1.compareTo(CARAC_E_AIGU) == 0 : "
				, 0, caracEAiguAuto1.compareTo(CARAC_E_AIGU));
		
		assertTrue("caracEAiguAuto1 après CARAC_E_GRAVE : "
				, caracEAiguAuto1.compareTo(CARAC_E_GRAVE) > 0);
		
		assertTrue("caracEAiguAuto2 après caracEAiguAuto1 : "
				, caracEAiguAuto2.compareTo(caracEAiguAuto1) > 0);
		
	} // Fin de testConstructeurAuto().____________________________________
	
	
	
	/**
	 * method testGetEnTeteCsv() :<br/>
	 * Garantit que fournirEnTeteCsv() de CaractereDan 
	 * retourne l'en-tête csv : <br/> 
	 * "id;Position;Caractère;Unicode;numericValue;Type de Caractère;
	 * Valeur Entière;Point de Code Décimal;
	 * Point de Code HexaDécimal;Nom Unicode;".<br/>
	 * <br/>
	 */
	@Test
	public void testGetEnTeteCsv() {
		
		final CaractereDan carac = new CaractereDan();
		
		assertEquals(
				"Doit retourner le bon en-tête csv : "
					, ENTETE_CARACTEREDAN, carac.fournirEnTeteCsv());
			
	} // Fin de testGetEnTeteCsv().________________________________________


	
	/**
	 * method testGetEnTeteColonne() :<br/>
	 * Teste les en-tête de colonnes pour les JTable.<br/>
	 * "id;position;caractère;unicode;numericValue;typeCaractere;
	 * valeurEntiere;codePointDecimal;
	 * codePointHexa;nom;".<br/>
	 * <br/>
	 */
	@Test
	public void testGetEnTeteColonne() {
		
		assertEquals("enTete(0) equals id : "
				, "id"
					, CARAC_D1.fournirEnTeteColonne(0));
		
		assertEquals("enTete(1) equals position : "
				, "position"
					, CARAC_D1.fournirEnTeteColonne(1));
		
		assertEquals("enTete(2) equals caractère : "
				, "caractère"
					, CARAC_D1.fournirEnTeteColonne(2));
		
		assertEquals("enTete(3) equals unicode : "
				, "unicode"
					, CARAC_D1.fournirEnTeteColonne(3));
		
		assertEquals("enTete(4) equals numericValue : "
				, "numericValue"
					, CARAC_D1.fournirEnTeteColonne(4));
		
		assertEquals("enTete(5) equals typeCaractere : "
				, "typeCaractere"
					, CARAC_D1.fournirEnTeteColonne(5));
		
		assertEquals("enTete(6) equals valeurEntiere : "
				, "valeurEntiere"
					, CARAC_D1.fournirEnTeteColonne(6));
		
		assertEquals("enTete(7) equals codePointDecimal : "
				, "codePointDecimal"
					, CARAC_D1.fournirEnTeteColonne(7));
		
		assertEquals("enTete(8) equals codePointHexa : "
				, "codePointHexa"
					, CARAC_D1.fournirEnTeteColonne(8));
		
		assertEquals("enTete(9) equals nom : "
				, "nom"
					, CARAC_D1.fournirEnTeteColonne(9));
		
		assertEquals("enTete(12) equals invalide : "
				, "invalide"
					, CARAC_D1.fournirEnTeteColonne(12));
		
	} // Fin de testGetEnTeteColonne().____________________________________
	

	
	/**
	 * method assertionsListeColonne() :<br/>
	 * Teste les valeurs dans les colonnes d'une JTable.<br/>
	 * <br/>
	 * "id;position;caractère;unicode;numericValue;typeCaractere;
	 * valeurEntiere;codePointDecimal;
	 * codePointHexa;nom;".<br/>
	 * <br/>
	 *
	 * @param pList : List<Object>.<br/>
	 * @param pCar : CaractereDan.<br/>
	 */
	public void assertionsListeColonne(
			final List<Object> pList
				, final CaractereDan pCar) {
		
		assertEquals("colonne(0) equals id : "
				, pList.get(0)
					, pCar.fournirValeurColonne(0));
		
		assertEquals("colonne(1) equals position : "
				, pList.get(1)
					, pCar.fournirValeurColonne(1));
		
		assertEquals("colonne(2) equals caractère : "
				, pList.get(2)
					, pCar.fournirValeurColonne(2));
		
		assertEquals("colonne(3) equals unicode : "
				, pList.get(3)
					, pCar.fournirValeurColonne(3));
		
		assertEquals("colonne(4) equals numericValue : "
				, pList.get(4)
					, pCar.fournirValeurColonne(4));
		
		assertEquals("colonne(5) equals typeCaractere : "
				, pList.get(5)
					, pCar.fournirValeurColonne(5));
		
		assertEquals("colonne(6) equals valeurEntiere : "
				, pList.get(6)
					, pCar.fournirValeurColonne(6));
		
		assertEquals("colonne(7) equals codePointDecimal : "
				, pList.get(7)
					, pCar.fournirValeurColonne(7));
		
		assertEquals("colonne(8) equals codePointHexa : "
				, pList.get(8)
					, pCar.fournirValeurColonne(8));
		
		assertEquals("colonne(9) equals nom : "
				, pList.get(9)
					, pCar.fournirValeurColonne(9));
		
		assertEquals("colonne(> 9) equals invalide : "
				, "invalide"
					, pCar.fournirValeurColonne(12));
		
	} // Fin de assertionsListeColonne().__________________________________
	
	
		
}
