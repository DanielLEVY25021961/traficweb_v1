package levy.daniel.application.model.services.metier.utilisateurs.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.metier.utilisateur.impl.UtilisateurCerbere;
import levy.daniel.application.model.services.metier.utilisateurs.IUtilisateurCerbereService;
import levy.daniel.application.model.utilitaires.spring.configurateurspring.ConfigurateurSpringFrmkAnnotationJPAH2Memory;

/**
 * CLASSE UtilisateurCerbereServiceTest :<br/>
 * Test JUnit de la classe {@link UtilisateurCerbereService}.<br/>
 * TEST DE SERVICE SPRING.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Test Spring, Test JUnit Spring, SPRING, TEST SERVICE SPRING,<br/>
 * fabriquer liste à partir d'un Iterable,<br/>
 * Test Dao, test DAO, Test persistence avec SPRING frmawork,<br/> 
 * UTILISER LES ANNOTATIONS RunWith(SpringRunner.class), DataJpaTest 
 * et ComponentScan("levy.daniel.application").<br/>
 * ComponentScan("levy.daniel.application") est ESSENTIEL sinon SPRING BOOT 
 * ne résoud pas les dépendances et ne trouve pas 
 * les Beans lors des tests JUnit.<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 28 févr. 2019
 *
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes= {ConfigurateurSpringFrmkAnnotationJPAH2Memory.class})
public class UtilisateurCerbereServiceTest {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe UtilisateurCerbereServiceTest".
	 */
	public static final String CLASSE_UTILISATEURCERBERE_SERVICE_TEST 
		= "Classe UtilisateurCerbereServiceTest";
	
	/**
	 * Contexte SPRING injecté par SPRING dès que 
	 * la configuration est terminée.<br/>
	 * <ul>
	 * <li><b>injecté via son SETTER</b>.</li>
	 * <li>injecté <i>après</i> avantTests() (BeforeClass) 
	 * et après le Constructeur du Test.</li>
	 * <li>injecté <i>avant</i> avantChaqueTest() (Before).</li>
	 * </ul>
	 */
    private GenericApplicationContext contextInjectable;	
	
	/**
	 * Contexte SPRING pour les tests.<br/>
	 */
	private static transient GenericApplicationContext contexteSpring;
	
	/**
	 * DAO à tester.<br/>
	 * Instancié dans avantTests() ou injecté par Spring.
	 */
	@Autowired(required=true)
    @Qualifier("UtilisateurCerbereService")
	private transient IUtilisateurCerbereService service;
	
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;
	
	/**
	 * boolean qui stipule si les transactions 
	 * doivent être RollBackées lors des tests.<br/>
	 * Par défaut, Spring met cette valeur à true 
	 * et on ne peut donc pas voir les données dans une table 
	 * en mode FILE après un test.<br/>
	 * Passer cette valeur à false si on souhaite 
	 * consulter la table après un test.
	 */
	public static final boolean VALEUR_ROLLBACK = false;
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	/**
	 * "testCreateNull()".<br/>
	 */
	public static final String TEST_CREATE_NULL 
		= "testCreateNull()";
	
	/**
	 * "testCreate()".<br/>
	 */
	public static final String TEST_CREATE 
		= "testCreate()";
	
	/**
	 * "testCreateDoublon()".<br/>
	 */
	public static final String TEST_CREATE_DOUBLON 
		= "testCreateDoublon()";
	
	/**
	 * "testPersistNull()".<br/>
	 */
	public static final String TEST_PERSIST_NULL 
		= "testPersistNull()";
	
	/**
	 * "testPersist()".<br/>
	 */
	public static final String TEST_PERSIST 
		= "testPersist()";
	
	/**
	 * "testPersistDoublon()".<br/>
	 */
	public static final String TEST_PERSIST_DOUBLON 
	= "testPersistDoublon()";
	
	/**
	 * "testCreateReturnIdNull()".<br/>
	 */
	public static final String TEST_CREATERETURNID_NULL 
		= "testCreateReturnIdNull()";
	
	/**
	 * "testCreateReturnId()".<br/>
	 */
	public static final String TEST_CREATERETURNID 
		= "testCreateReturnId()";
	
	/**
	 * "testCreateReturnIdDoublon()".<br/>
	 */
	public static final String TEST_CREATERETURNID_DOUBLON 
		= "testCreateReturnIdDoublon()";
	
	/**
	 * "testSaveIterableNull()".<br/>
	 */
	public static final String TEST_SAVEITERABLE_NULL 
		= "testSaveIterableNull()";

	/**
	 * testSaveIterableObjetsNull().
	 */
	public static final String TEST_SAVEITERABLE_OBJETS_NULL 
		= "testSaveIterableObjetsNull()";

	/**
	 * "testSaveIterable()".<br/>
	 */
	public static final String TEST_SAVEITERABLE 
		= "testSaveIterable()";
		
	/**
	 * "testSaveIterableDoublon()".<br/>
	 */
	public static final String TEST_SAVEITERABLE_DOUBLON 
		= "testSaveIterableDoublon()";
	
	/**
	 * "testRetrieveNull()".<br/>
	 */
	public static final String TEST_RETRIEVE_NULL 
		= "testRetrieveNull()";

	
	/**
	 * "testRetrieveObjetNull()".<br/>
	 */
	public static final String TEST_RETRIEVE_OBJET_NULL 
		= "testRetrieveObjetNull()";

	/**
	 * "testRetrieveInexistant()".<br/>
	 */
	public static final String TEST_RETRIEVE_INEXISTANT 
		= "testRetrieveInexistant()";
	
	/**
	 * "testRetrieve()".<br/>
	 */
	public static final String TEST_RETRIEVE 
		= "testRetrieve()";

	/**
	 * "testFindByIdNull()".<br/>
	 */
	public static final String TEST_FINDBYID_NULL 
		= "testFindByIdNull()";

	/**
	 * "testFindByIdInexistant()".<br/>
	 */
	public static final String TEST_FINDBYID_INEXISTANT 
		= "testFindByIdInexistant()";

	/**
	 * "testFindById()".<br/>
	 */
	public static final String TEST_FINDBYID 
		= "testFindById()";
	
	/**
	 * "testRetrieveIdNull()".<br/>
	 */
	public static final String TEST_RETRIEVEID_NULL 
		= "testRetrieveIdNull()";

	
	/**
	 * "testRetrieveIdObjetNull()".<br/>
	 */
	public static final String TEST_RETRIEVEID_OBJET_NULL 
		= "testRetrieveIdObjetNull()";

	/**
	 * "testRetrieveIdInexistant()".<br/>
	 */
	public static final String TEST_RETRIEVEID_INEXISTANT 
		= "testRetrieveIdInexistant()";
	
	/**
	 * "testRetrieveId()".<br/>
	 */
	public static final String TEST_RETRIEVEID 
		= "testRetrieveId()";
	
	/**
	 * "testFindAll()".<br/>
	 */
	public static final String TEST_FINDALL 
		= "testFindAll()";
	
	/**
	 * "testFindAllMaxOut()".<br/>
	 */
	public static final String TEST_FINDALLMAX_OUT 
		= "testFindAllMaxOut()";
	
	/**
	 * "testFindAllMax()".<br/>
	 */
	public static final String TEST_FINDALLMAX 
		= "testFindAllMax()";
		
	/**
	 * "testFindAllIterableNull()".<br/>
	 */
	public static final String TEST_FINDALLITERABLE_NULL 
		= "testFindAllIterableNull()";
		
	/**
	 * "testFindAllIterableOut()".<br/>
	 */
	public static final String TEST_FINDALLITERABLE_OUT 
		= "testFindAllIterableOut()";
	
	/**
	* "testFindAllIterable()".<br/>
	*/
	public static final String TEST_FINDALLITERABLE 
	= "testFindAllIterable()";

	/**
	 * "testUpdateNull()".<br/>
	 */
	public static final String TEST_UPDATE_NULL 
		= "testUpdateNull()";

	/**
	 * "testUpdateInexistant()".<br/>
	 */
	public static final String TEST_UPDATE_INEXISTANT 
		= "testUpdateInexistant()";

	/**
	 * "testUpdateDoublon()".<br/>
	 */
	public static final String TEST_UPDATE_DOUBLON 
		= "testUpdateDoublon()";

	/**
	 * "testUpdate()".<br/>
	 */
	public static final String TEST_UPDATE 
		= "testUpdate()";

	/**
	 * "testUpdateIdNull()".<br/>
	 */
	public static final String TEST_UPDATEID_NULL 
		= "testUpdateIdNull()";

	/**
	 * "testUpdateIdInexistant()".<br/>
	 */
	public static final String TEST_UPDATEID_INEXISTANT 
		= "testUpdateIdInexistant()";

	/**
	 * "testUpdateIdDoublon()".<br/>
	 */
	public static final String TEST_UPDATEID_DOUBLON 
		= "testUpdateIdDoublon()";
	
	/**
	 * "testUpdateId()".<br/>
	 */
	public static final String TEST_UPDATEID 
		= "testUpdateId()";

	/**
	 * "testDeleteNull()".<br/>
	 */
	public static final String TEST_DELETE_NULL 
		= "testDeleteNull()";
	
	/**
	 * "testDeleteInexistant()".<br/>
	 */
	public static final String TEST_DELETE_INEXISTANT 
		= "testDeleteInexistant()";
	
	/**
	 * "testDelete()".<br/>
	 */
	public static final String TEST_DELETE 
		= "testDelete()";

	/**
	 * "testDeleteByIdNull()".<br/>
	 */
	public static final String TEST_DELETEBYID_NULL 
		= "testDeleteByIdNull()";

	/**
	 * "testDeleteByIdInexistant()".<br/>
	 */
	public static final String TEST_DELETEBYID_INEXISTANT 
		= "testDeleteByIdInexistant()";
		
	/**
	 * "testDeleteById()".<br/>
	 */
	public static final String TEST_DELETEBYID 
		= "testDeleteById()";

	/**
	 * "testDeleteByIdBooleanNull()".<br/>
	 */
	public static final String TEST_DELETEBYIDBOOLEAN_NULL 
		= "testDeleteByIdBooleanNull()";

	/**
	 * "testDeleteByIdBooleanInexistant()".<br/>
	 */
	public static final String TEST_DELETEBYIDBOOLEAN_INEXISTANT 
		= "testDeleteByIdBooleanInexistant()";
		
	/**
	 * "testDeleteByIdBoolean()".<br/>
	 */
	public static final String TEST_DELETEBYIDBOOLEAN 
		= "testDeleteByIdBoolean()";
	
	/**
	 * "testDeleteAll()".<br/>
	 */
	public static final String TEST_DELETEALL 
		= "testDeleteAll()";
	
	/**
	 * "testDeleteAllBoolean()".<br/>
	 */
	public static final String TEST_DELETEALLBOOLEAN 
		= "testDeleteAllBoolean()";
	
	/**
	 * "testDeleteIterable()".<br/>
	 */
	public static final String TEST_DELETEITERABLE 
		= "testDeleteIterable()";
	
	/**
	 * "testDeleteIterableBoolean()".<br/>
	 */
	public static final String TEST_DELETEITERABLEBOOLEAN 
		= "testDeleteIterableBoolean()";

	/**
	 * "testExists".
	 */
	public static final String TEST_EXISTS 
		= "testExists";
	
	/**
	 * "testExistsId".
	 */
	public static final String TEST_EXISTS_ID 
		= "testExistsId";
	
	/**
	 * "testCount".
	 */
	public static final String TEST_COUNT 
		= "testCount";
	
	/**
	 * TIRETS : String :<br/>
	 * "--------------------------------------------------------".<br/>
	 */
	public static final String TIRETS 
	= "--------------------------------------------------------";

	
	/**
	 * NBRE_OBJET_INITIAL : String :<br/>
	 * "NOMBRE D'OBJETS INITIALEMENT dans le stockage : ".<br/>
	 */
	public static final String NBRE_OBJET_INITIAL 
		= "NOMBRE D'OBJETS INITIALEMENT dans le stockage : ";
	
		
	/**
	 * "nombreObjetsFinal doit valoir ".<br/>
	 */
	public static final String NBRE_OBJETS_FINAL_DOIT 
		= "nombreObjetsFinal doit valoir ";
	
	
	/**
	 * "nombreObjetsinitial + 0 : ".<br/>
	 */
	public static final String NBRE_INITIAL_PLUS_ZERO 
		= "nombreObjetsinitial + 0 : ";
	
	
	/**
	 * "nombreObjetsinitial + 1 : ".<br/>
	 */
	public static final String NBRE_INITIAL_PLUS_UN 
		= "nombreObjetsinitial + 1 : ";

	/**
	 * "nombreObjetsFinal vaut nombreObjetsInitial + 1 : ".<br/>
	 */
	public static final String FINAL_VAUT_INITIAL_PLUS_1 
		= "nombreObjetsFinal vaut nombreObjetsInitial + 1 : ";
	
	/**
	 * "nombreObjetsinitial + 2 : ".<br/>
	 */
	public static final String NBRE_INITIAL_PLUS_DEUX 
		= "nombreObjetsinitial + 2 : ";

	
	/**
	 * "nombreObjetsinitial + 3 : ".<br/>
	 */
	public static final String NBRE_INITIAL_PLUS_TROIS 
	= "nombreObjetsinitial + 3 : ";
	
	/**
	 * "nombreObjetsinitial + 4 : ".<br/>
	 */
	public static final String NBRE_INITIAL_PLUS_QUATRE 
	= "nombreObjetsinitial + 4 : ";


	/**
	 * "OBJET1 NON PERSISTANT : ".<br/>
	 */
	public static final String OBJET1_NON_PERSISTANT 
		= "OBJET1 NON PERSISTANT : ";
	
	/**
	 * "OBJET2 NON PERSISTANT (DOUBLON DE OBJET1) : ".<br/>
	 */
	public static final String OBJET2_NON_PERSISTANT_DOUBLON1 
		= "OBJET2 NON PERSISTANT (DOUBLON DE OBJET1) : ";
		
	/**
	 * "LOT D'OBJETS INITIALEMENT dans le stockage : ".<br/>
	 */
	public static final String LOT_OBJET_INITIAL 
	= "LOT D'OBJETS INITIALEMENT dans le stockage : ";
	
	/**
	 * "LOT D'OBJETS dans le stockage APRES DELETE : ".<br/>
	 */
	public static final String LOT_OBJETS_APRES_DELETE 
		= "LOT D'OBJETS dans le stockage APRES DELETE : ";
	
	/**
	 * saut de ligne de la plateforme.<br/>
	 */
	public static final String SAUT_LIGNE_PLATEFORME 
		= System.getProperty("line.separator");

	/**
	 * "objet1 : ".<br/>
	 */
	public static final String OBJET1 
		= "objet1 : ";

	/**
	 * "objet1MemeInstance : ".<br/>
	 */
	public static final String OBJET1_MEME_INSTANCE 
		= "objet1MemeInstance : ";
	
	/**
	 * "objet2EqualsObj1 : ".<br/>
	 */
	public static final String OBJET2_EQUALS_OBJET1 
		= "objet2EqualsObj1 : ";

	/**
	 * "objet3EqualsObj1 : ".<br/>
	 */
	public static final String OBJET3_EQUALS_OBJET1 
		= "objet3EqualsObj1 : ";
	
	/**
	 * "OBJET PERSISTANT TROUVE : ".<br/>
	 */
	public static final String OBJET_PERSISTANT_TROUVE 
		= "OBJET PERSISTANT TROUVE : ";
	
	/**
	 * "OBJET METIER A RECHERCHER : ".<br/>
	 */
	public static final String OBJET_METIER_A_RECHERCHER 
		= "OBJET METIER A RECHERCHER : ";

	/**
	 * "objetPersisteTrouve1Id : ".<br/>
	 */
	public static final String OBJET_PERSISTE_TROUVE1_ID 
		= "objetPersisteTrouve1Id : ";
	
	/**
	 * "objetPersisteTrouve1Id : NULL".<br/>
	 */
	public static final String OBJET_PERSISTE_TROUVE1_ID_NULL 
		= "objetPersisteTrouve1Id : NULL";

	/**
	 * "objetARechercher1 : ".<br/>
	 */
	public static final String OBJET_A_RECHERCHER1 
		= "objetARechercher1 : ";
	
	/**
	 * "objetARechercher1 : NULL".<br/>
	 */
	public static final String OBJET_A_RECHERCHER1_NULL 
		= "objetARechercher1 : NULL";
	
	/**
	 * "objetPersisteTrouve1 : ".<br/>
	 */
	public static final String OBJET_PERSISTE_TROUVE1 
		= "objetPersisteTrouve1 : ";	
	
	/**
	 * "objetPersisteTrouve1 : NULL".<br/>
	 */
	public static final String OBJET_PERSISTE_TROUVE_NULL 
		= "objetPersisteTrouve1 : NULL";

	/**
	 * "LISTE D'OBJETS METIER A CREER : ".<br/>
	 */
	public static final String LISTE_OBJETS_METIER_A_CREER 
		= "LISTE D'OBJETS METIER A CREER : ";

	/**
	 * "LISTE D'OBJETS PERSISTES : ".<br/>
	 */
	public static final String LISTE_OBJETS_PERSISTES 
		= "LISTE D'OBJETS PERSISTES : ";

	/**
	 * "LISTE D'OBJETS PERSISTES : NULL".<br/>
	 */
	public static final String LISTE_OBJETS_PERSISTES_NULL 
		= "LISTE D'OBJETS PERSISTES : NULL";

	/**
	 * "LISTE D'OBJETS APRES CREATE : ".<br/>
	 */
	public static final String LISTE_OBJETS_APRES_CREATE 
		= "LISTE D'OBJETS APRES CREATE : ";
	
	/**
	 * "OBJETS TROUVES : ".<br/>
	 */
	public static final String OBJETS_TROUVES 
		= "OBJETS TROUVES : ";	

	/**
	 * "ID DE L'OBJET PERSISTANT TROUVE : ".<br/>
	 */
	public static final String ID_OBJET_PERSISTANT_TROUVE 
		= "ID DE L'OBJET PERSISTANT TROUVE : ";
	
	/**
	 * "findAll() doit retourner 0 enregistrements : ".<br/>
	 */
	public static final String FINDALL_DOIT_RETOURNER_0_ENREGISTREMENTS 
		= "findAll() doit retourner 0 enregistrements : ";
	
	/**
	 * "findAll() doit retourner 2 enregistrements : ".<br/>
	 */
	public static final String FINDALL_DOIT_RETOURNER_2_ENREGISTREMENTS 
		= "findAll() doit retourner 2 enregistrements : ";
	
	/**
	 * "findAll() doit retourner 3 enregistrements : ".<br/>
	 */
	public static final String FINDALL_DOIT_RETOURNER_3_ENREGISTREMENTS 
		= "findAll() doit retourner 3 enregistrements : ";
	
	/**
	 * "findAll() doit retourner 4 enregistrements : ".<br/>
	 */
	public static final String FINDALL_DOIT_RETOURNER_4_ENREGISTREMENTS 
		= "findAll() doit retourner 4 enregistrements : ";
		
	/**
	 * "OBJET METIER A MODIFIER : ".<br/>
	 */
	public static final String OBJET_METIER_A_MODIFIER 
		= "OBJET METIER A MODIFIER : ";
	
	/**
	 * "OBJET METIER A MODIFIER : NULL".<br/>
	 */
	public static final String OBJET_METIER_A_MODIFIER_NULL 
		= "OBJET METIER A MODIFIER : NULL";

	/**
	 * "ID DE L'OBJET A MODIFIER : ".<br/>
	 */
	public static final String ID_OBJET_A_MODIFIER 
		= "ID DE L'OBJET A MODIFIER : ";
	
	/**
	 * "ID DE L'OBJET A MODIFIER : NULL".<br/>
	 */
	public static final String ID_OBJET_A_MODIFIER_NULL 
		= "ID DE L'OBJET A MODIFIER : NULL";

	/**
	 * "OBJET MODIFIE : ".<br/>
	 */
	public static final String OBJET_MODIFIE 
		= "OBJET MODIFIE : ";

	/**
	 * "OBJET MODIFIE : NULL".<br/>
	 */
	public static final String OBJET_MODIFIE_NULL 
		= "OBJET MODIFIE : NULL";

	/**
	 * "ID DE L'OBJET A DELETER : ".
	 */
	public static final String ID_OBJET_A_DELETER 
		= "ID DE L'OBJET A DELETER : ";
	
	/**
	 * "ID DE L'OBJET A DELETER : NULL".
	 */
	public static final String ID_OBJET_A_DELETER_NULL 
		= "ID DE L'OBJET A DELETER : NULL";
	
	/**
	 * "OBJET METIER A DELETER : ".<br/>
	 */
	public static final String OBJET_A_DELETER 
		= "OBJET METIER A DELETER : ";
	
	/**
	 * "OBJET METIER A DELETER : NULL".<br/>
	 */
	public static final String OBJET_A_DELETER_NULL 
		= "OBJET METIER A DELETER : NULL";

	/**
	 * "L'OBJET METIER A-T-IL ETE DETRUIT ? : ".<br/>
	 */
	public static final String OBJET_DETRUIT 
		= "L'OBJET METIER A-T-IL ETE DETRUIT ? : ";

	/**
	 * "MODIFICATIONS : ".
	 */
	public static final String MODIFICATIONS 
		= "MODIFICATIONS : ";

	/**
	 * FORMAT pour affichage formaté à la console 
	 * des IUtilisateurCerbere.<br/>
	 * "id=%1$-5d civilité = %2$-5s  
	 * prénom = %3$-15s nom = %4$-20s 
	 * tel = %5$-18s eMail = %6$-25s  Service = %7$-15s  
	 * Unité = %8$-35s  Profil = %9$-25s  Portée = %10$-15s  
	 * Restriction = %11$-35s".
	 */
	public static final String FORMAT_UTILISATEURCERBERE 
		= "id=%1$-5d civilité = %2$-5s  prénom = %3$-15s "
				+ "nom = %4$-20s tel = %5$-18s eMail = %6$-25s  "
				+ "Service = %7$-15s  Unité = %8$-35s  Profil = %9$-25s  "
				+ "Portée = %10$-15s  Restriction = %11$-35s";
	
	/**
	 * "M.".<br/>
	 */
	public static final String M = "M.";
	
	/**
	 * "prenomTest".<br/>
	 */
	public static final String PRENOMTEST = "prenomTest";
	
	/**
	 * "nomTest".<br/>
	 */
	public static final String NOMTEST = "nomTest";
	
	/**
	 * "01 02 03 04 05".
	 */
	public static final String TELTEST = "01 02 03 04 05";
	
	/**
	 * "test.test@yahoo.fr".<br/>
	 */
	public static final String MAILTEST = "test.test@yahoo.fr";
	
	/**
	 * "CEREMA".<br/>
	 */
	public static final String SERVICETEST = "CEREMA";
	
	/**
	 * "CEREMA/DTecITM/CITS/DACSI".<br/>
	 */
	public static final String UNITETEST = "CEREMA/DTecITM/CITS/DACSI";
	
	/**
	 * "ADMINISTRATEUR".<br/>
	 */
	public static final String PROFILTEST = "ADMINISTRATEUR";
	
	/**
	 * "CEREMA".
	 */
	public static final String PORTEETEST = "CEREMA";
	
	/**
	 * "france métropolitaine".
	 */
	public static final String RESTRICTIONTEST = "france métropolitaine";

	 /**
	 * objet CORRECT à créer dans le stockage 
	 * pour la méthode remplirStockage() .<br/>
	 * <b>Ne doit pas causer de doublon</b>.<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient IUtilisateurCerbere objetRemplirStockage1 
		= new UtilisateurCerbere(
			M
			, "Horace", "Silver"
			, "04 79 85 54 63", "horace.silver@free.fr"
			, "SG", "SG/PSII/PSII2"
			, "GESTIONNAIRE"
			, "SG"
			, "sans objet");
	
	 /**
	 * objet CORRECT à créer dans le stockage 
	 * pour la méthode remplirStockage() .<br/>
	 * <b>Ne doit pas causer de doublon</b>.<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient IUtilisateurCerbere objetRemplirStockage2 
		= new UtilisateurCerbere(
			M
			, "Johnny", "Halliday"
			, "01 44 85 54 63", "johnny.halliday@free.fr"
			, "CEREMA", "CEREMA/DTecITM/CITS/DACSI"
			, "CONSULTANT"
			, "CEREMA"
			, "France entière");

	 /**
	 * objet CORRECT à créer dans le stockage 
	 * pour la méthode remplirStockage() .<br/>
	 * <b>Ne doit pas causer de doublon</b>.<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient IUtilisateurCerbere objetRemplirStockage3 
	= new UtilisateurCerbere(
			"Mme"
			, "Papy", "Gonzales"
			, "00 33 (1) 585 54 63", "papy.gonzales@aol.com"
			, "DIRA", "DIRA/SIEER/SGT"
			, "ADMINISTRATEUR LOCAL"
			, "DIRA"
			, "DIRA + DIRE");

	 /**
	 * objet CORRECT à créer dans le stockage 
	 * pour la méthode remplirStockage() .<br/>
	 * <b>Ne doit pas causer de doublon</b>.<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient IUtilisateurCerbere objetRemplirStockage4 
		= new UtilisateurCerbere(
				"Mlle"
				, "Zorro", "Démoniaque"
				, "00 33 (3) 472 54 63", "zorro.demoniac@british.com"
				, "DIRE", "DIRE/SIEER/GST"
				, "ADMINISTRATEUR LOCAL"
				, "DIRE"
				, "DIRE");
	
	/**
	 * objet CORRECT à créer dans le stockage 
	 * pour les tests creation.<br/>
	 * Ne doit pas causer de doublon avec les objetRemplirStockage.<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient IUtilisateurCerbere objetACreer1 
		= new UtilisateurCerbere(
			M
			, "Michael", "Caine"
			, "00 33 322 56 98", "michael.caine@aol.com"
			, "NY", "New York"
			, "ADMINISTRATEUR NATIONAL"
			, "USA"
			, "USA + Canada");
	
	/**
	 * objet CORRECT à créer dans le stockage 
	 * pour les tests creation.<br/>
	 * Ne doit pas causer de doublon avec les objetRemplirStockage.<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient IUtilisateurCerbere objetACreer2 
		= new UtilisateurCerbere(
			M
			, "Steve", "McQueen"
			, "00 31 344 56 98", "steve.mcqueen@aol.com"
			, "Greenwich", "San Fransisco"
			, "ADMINISTRATEUR NATIONAL"
			, "USA"
			, "USA + Canada");
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient IUtilisateurCerbere objet1 
		= new UtilisateurCerbere(
				M
				, PRENOMTEST, NOMTEST
				, TELTEST, MAILTEST
				, SERVICETEST, UNITETEST
				, PROFILTEST
				, PORTEETEST
				, RESTRICTIONTEST);
	
	/**
	 * objet1MemeInstance doit être la même instance que objet1.<br/>
	 */
	public static transient IUtilisateurCerbere objet1MemeInstance = objet1;
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient IUtilisateurCerbere objet2EqualsObj1 
		=  new UtilisateurCerbere(
				M
				, PRENOMTEST, NOMTEST
				, "01 01 01 01 01", MAILTEST
				, SERVICETEST, UNITETEST
				, PROFILTEST
				, PORTEETEST
				, RESTRICTIONTEST);
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient IUtilisateurCerbere objet3EqualsObj1 
		= new UtilisateurCerbere(
				M
				, PRENOMTEST, NOMTEST
				, "02 02 02 02 02", MAILTEST
				, SERVICETEST, UNITETEST
				, PROFILTEST
				, PORTEETEST
				, RESTRICTIONTEST);

	/**
	 * objetNull1 et objetNull2 doivent être instanciés 
	 * avec le constructeur d'arité nulle ou avoir 
	 * tous les attributs aux valeurs par défaut.
	 */
	public static transient IUtilisateurCerbere objetNull1 
		= new UtilisateurCerbere(null, null
				, null
				, null
				, null, null
				, null
				, null
				, null
				, null);
	
	/**
	 * objetNull1 et objetNull2 doivent être instanciés 
	 * avec le constructeur d'arité nulle ou avoir 
	 * tous les attributs aux valeurs par défaut.
	 */
	public static transient IUtilisateurCerbere objetNull2 
		= new UtilisateurCerbere();

	/**
	 * Objet Inexistant dans le stockage.
	 */
	public static transient IUtilisateurCerbere objetInexistant 
		= new UtilisateurCerbere(
			"Maître"
			, "prenomInexistant", "nomInexistant"
			, "telInexistant", "mailInexistant"
			, "serviceInexistant", "uniteInexistant"
			, "Profil inexistant"
			, "portée inexistant"
			, "rrestriction inexistant");

	
	/**
	 * Objet modifiant objetRemplirStockage1 
	 * sans toucher à son id et sans créer 
	 * de doublon dans le stockage.<br/>
	 */
	public static transient IUtilisateurCerbere objetModifieCorrect
		= new UtilisateurCerbere(
				M
				, "Horace modifié", "Silver modifié"
				, "04 79 85 54 63", "horace.silver@free.fr"
				, "SG", "SG/PSII/PSII2"
				, "GESTIONNAIRE"
				, "SG"
				, "sans objet");
	
	/**
	 * Objet modifiant objetRemplirStockage2 
	 * sans toucher à son id et créant 
	 * un doublon avec objetRemplirStockage1 dans le stockage.<br/>
	 */
	public static transient IUtilisateurCerbere objetModifieDoublon
		= new UtilisateurCerbere(
				M
				, "Horace", "Silver"
				, "01 44 85 54 63", "horace.silver@free.fr"
				, "CEREMA", "SG/PSII/PSII2"
				, "CONSULTANT"
				, "CEREMA"
				, "France entière");


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereServiceTest.class);

	// *************************METHODES************************************/
	

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereServiceTest() {
		
		super();
		
		System.out.println();
		System.out.println("DANS LE CONSTRUCTEUR DU TEST");
		System.out.println("CONTEXTE SPRING : " + contexteSpring);
		
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	/**
	 * Teste la méthode create(Objet Metier).<br/>
	 * <ul>
	 * <li>garantit que create(objet correct) 
	 * rajoute l'objet métier dans le stockage.</li>
	 * <li>garantit que create(objet correct) retourne l'objet persisté.</li>
	 * </ul>
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	@Rollback(value = VALEUR_ROLLBACK)
	public void testCreate() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE UtilisateurCerbereServiceTest - méthode testCreate() ********** ");
		}
		
	} // Fin de testCreate().______________________________________________
	
	
	
	/**
	 * Getter du Contexte SPRING injecté par SPRING dès que 
	 * la configuration est terminée.<br/>
	 * <ul>
	 * <li><b>injecté via son SETTER</b>.</li>
	 * <li>injecté <i>après</i> avantTests() (BeforeClass) 
	 * et après le Constructeur du Test.</li>
	 * <li>injecté <i>avant</i> avantChaqueTest() (Before).</li>
	 * </ul>
	 *
	 * @return this.contextInjectable : GenericApplicationContext.<br/>
	 */
	public GenericApplicationContext getContextInjectable() {
		return this.contextInjectable;
	} // Fin de getContextInjectable().____________________________________



	/**
	* Setter du Contexte SPRING injecté par SPRING dès que 
	* la configuration est terminée.<br/>
	* <ul>
	* <li><b>injecté via son SETTER</b>.</li>
	* <li>injecté <i>après</i> avantTests() (BeforeClass) 
	* et après le Constructeur du Test.</li>
	* <li>injecté <i>avant</i> avantChaqueTest() (Before).</li>
	* </ul>
	*
	* @param pContextInjectable : GenericApplicationContext : 
	* valeur à passer à this.contextInjectable.<br/>
	*/
	@Autowired
	public void setContextInjectable(
			final GenericApplicationContext pContextInjectable) {
		
		System.out.println();
		System.out.println("****************INJECTION DU CONTEXTE DANS LE SETTER setContextInjectable(...)**************");
		System.out.println(pContextInjectable);
		System.out.println();
		
		this.contextInjectable = pContextInjectable;
		
		/* instancie le contexteSpring STATIC la première fois. */
		if (contexteSpring == null || !contexteSpring.isActive()) {
			contexteSpring = this.contextInjectable;
		}
		
	} // Fin de setContextInjectable(...)._________________________________

		
	
}
