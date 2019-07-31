package levy.daniel.application.model.persistence.metier.sections.dao.jpaspring.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.sections.impl.SectionHit;
import levy.daniel.application.model.persistence.daoexceptions.AbstractDaoException;
import levy.daniel.application.model.persistence.metier.sections.ISectionHitDAO;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl.ImporteurHit;
import levy.daniel.application.model.utilitaires.spring.afficheurcontexte.AfficheurContexteSpring;
import levy.daniel.application.model.utilitaires.spring.configurateurspring.ConfigurateurSpringFrmkAnnotationJPAPostgresServer;

/**
 * CLASSE SectionHitDAOJPASpringTest :<br/>
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
 * @since 9 juil. 2019
 *
 */
//@ActiveProfiles("PROFIL_TEST_H2_MEMORY")
//@ActiveProfiles("PROFIL_TEST_H2_FILE")
@ActiveProfiles("PROFIL_PROD_POSTGRES_SERVER")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes= {ConfigurateurSpringFrmkAnnotationJPAPostgresServer.class})
public class SectionHitDAOJPASpringTest {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe SectionHitDAOJPASpringTest".
	 */
	public static final String CLASSE_SECTIONHIT_DAOJPASPRING_TEST 
		= "Classe SectionHitDAOJPASpringTest";
	
	/**
	 * "PROFIL_TEST_H2_FILE".
	 */
	public static final String PROFIL_TEST_H2_FILE = "PROFIL_TEST_H2_FILE";
	
	/**
	 * "PROFIL_TEST_H2_MEMORY".
	 */
	public static final String PROFIL_TEST_H2_MEMORY = "PROFIL_TEST_H2_MEMORY";

	/**
	 * "PROFIL_PROD_POSTGRES_SERVER".
	 */
	public static final String PROFIL_PROD_POSTGRES_SERVER 
		= "PROFIL_PROD_POSTGRES_SERVER";
	
	/**
	 * Contexte SPRING injecté par SPRING dès que 
	 * la configuration est terminée.<br/>
	 * <ul>
	 * <li>instancie le <code><b>contexteSpring</b></code> 
	 * STATIC la première fois.</li>
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
    @Qualifier("SectionHitDAOJPASpring")
	private transient ISectionHitDAO dao;
	
	/**
	 * boolean qui spécifie si le DAO testé est de type JPA 
	 * (utilise Hibernate et merge()) ou pas (JAXB, ...).<br/>
	 */
	private static final transient boolean DAO_JPA = true;
	
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
	public static final boolean VALEUR_ROLLBACK = true;
	
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
	 * "doit retourner null : ".
	 */
	public static final String DOIT_RETOURNER_NULL 
		= "doit retourner null : ";
	
	/**
	 * "ne doit pas retourner null : ".
	 */
	public static final String NE_DOIT_PAS_RETOURNER_NULL 
		= "ne doit pas retourner null : ";

	/**
	 * "doit retourner la bonne valeur : ".<br/>
	 */
	public static final String DOIT_RETOURNER_BONNE_VALEUR 
		= "doit retourner la bonne valeur : ";

	/**
	 * "Doit retourner la même instance : ".
	 */
	public static final String DOIT_RETOURNER_MEME_INSTANCE 
		= "Doit retourner la même instance : ";

	/**
	 * "doit retourner invalide : ".
	 */
	public static final String DOIT_RETOURNER_INVALIDE 
		= "doit retourner invalide : ";
	
	/**
	 * Paths.get(".").toAbsolutePath().normalize().<br/>
	 */
	public static final Path PATH_ABSOLU_PRESENT_PROJET 
		= Paths.get(".").toAbsolutePath().normalize();
	
	/**
	 * path <b>relatif</b> (par rapport au présent projet ECLIPSE) 
	 * des ressources des tests JUnit dans le présent projet ECLIPSE.<br/>
	 * Paths.get("src/test/resources")
	 */
	public static final Path SRC_TEST_RESOURCES_PATH_RELATIF 
		= Paths.get("src/test/resources");

	/**
	 * Path absolu vers les ressources de test.<br/>
	 */
	public static final Path PATH_ABSOLU_TEST_RESOURCES 
		= PATH_ABSOLU_PRESENT_PROJET
			.resolve(SRC_TEST_RESOURCES_PATH_RELATIF)
				.toAbsolutePath().normalize();
	
	 /**
	 * Path absolu vers les nomenclatures de test.<br/>
	 */
	public static final Path PATH_ABSOLU_TEST_NOMENCLATURES 
		= PATH_ABSOLU_TEST_RESOURCES
			.resolve("ressources/Nomenclatures")
				.toAbsolutePath().normalize();
	
	 /**
	 * Path absolu vers les jeux d'essai de test.<br/>
	 * src/test/resources/jeux_essai
	 */
	public static final Path PATH_ABSOLU_TEST_JEUX_ESSAI 
		= PATH_ABSOLU_TEST_RESOURCES
			.resolve("jeux_essai")
				.toAbsolutePath().normalize();
	
	/**
	 * Path absolu vers le répertoire 'temp' sous le présent projet.
	 */
	public static final Path PATH_ABSOLU_REPERTOIRE_TEMP 
		= PATH_ABSOLU_PRESENT_PROJET.resolve("temp");

	 /**
	 * objet CORRECT à créer dans le stockage 
	 * pour la méthode remplirStockage() .<br/>
	 * <b>Ne doit pas causer de doublon</b>.<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient ISectionHit objetRemplirStockage1;
	
	 /**
	 * objet CORRECT à créer dans le stockage 
	 * pour la méthode remplirStockage() .<br/>
	 * <b>Ne doit pas causer de doublon</b>.<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient ISectionHit objetRemplirStockage2;

	 /**
	 * objet CORRECT à créer dans le stockage 
	 * pour la méthode remplirStockage() .<br/>
	 * <b>Ne doit pas causer de doublon</b>.<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient ISectionHit objetRemplirStockage3;

	 /**
	 * objet CORRECT à créer dans le stockage 
	 * pour la méthode remplirStockage() .<br/>
	 * <b>Ne doit pas causer de doublon</b>.<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient ISectionHit objetRemplirStockage4;
	
	/**
	 * objet CORRECT à créer dans le stockage 
	 * pour les tests creation.<br/>
	 * Ne doit pas causer de doublon avec les objetRemplirStockage.<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient ISectionHit objetACreer1;
	
	/**
	 * objet CORRECT à créer dans le stockage 
	 * pour les tests creation.<br/>
	 * Ne doit pas causer de doublon avec les objetRemplirStockage.<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient ISectionHit objetACreer2;
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient ISectionHit objet1;
	
	/**
	 * objet1MemeInstance doit être la même instance que objet1.<br/>
	 */
	public static transient ISectionHit objet1MemeInstance = objet1;
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient ISectionHit objet2EqualsObj1;
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().<br/>
	 * <b>PAS d'ID pour ne pas gêner de séquence</b>.
	 */
	public static transient ISectionHit objet3EqualsObj1;

	/**
	 * objetNull1 et objetNull2 doivent être instanciés 
	 * avec le constructeur d'arité nulle ou avoir 
	 * tous les attributs aux valeurs par défaut.
	 */
	public static transient ISectionHit objetNull1;
	
	/**
	 * objetNull1 et objetNull2 doivent être instanciés 
	 * avec le constructeur d'arité nulle ou avoir 
	 * tous les attributs aux valeurs par défaut.
	 */
	public static transient ISectionHit objetNull2;

	/**
	 * Objet Inexistant dans le stockage.
	 */
	public static transient ISectionHit objetInexistant;

	
	/**
	 * Objet modifiant objetRemplirStockage1 
	 * sans toucher à son id et sans créer 
	 * de doublon dans le stockage.<br/>
	 */
	public static transient ISectionHit objetModifieCorrect;
	
	/**
	 * Objet modifiant objetRemplirStockage2 
	 * sans toucher à son id et créant 
	 * un doublon avec objetRemplirStockage1 dans le stockage.<br/>
	 */
	public static transient ISectionHit objetModifieDoublon;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitDAOJPASpringTest.class);

	// *************************METHODES************************************/
	

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public SectionHitDAOJPASpringTest() {
		
		super();
		
//		System.out.println();
//		System.out.println("DANS LE CONSTRUCTEUR DU TEST");
//		System.out.println("CONTEXTE SPRING : " + contexteSpring);
		
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	


	/**
	 * Teste la méthode <b>create(null)</b>.<br/>
	 * <ul>
	 * <li>garantit que create(null) ne stocke rien.</li>
	 * <li>garantit que create(null) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCreateNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testCreateNull() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_CREATE_NULL);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________

		
		/* Vide le stockage. */
		this.viderStockage();

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT CREATE(NULL) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final ISectionHit objetVraimentNull1 = null;
				
		ISectionHit objetVraimentNull1Persistant = null;
						
		
		try {
			
			/* *********************************************** */
			/* ********************* CREATION **************** */
			objetVraimentNull1Persistant = this.dao.create(objetVraimentNull1);
			/* *********************************************** */
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				this.afficherObjetPersistant(
						objetVraimentNull1Persistant, nombreObjetsFinal);						
			}
			
			/* garantit que create(null) retourne null. */
			assertNull(
					"create(null) doit retourner null : "
						, objetVraimentNull1Persistant);
			
			/* garantit que create(null) ne stocke rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		}
		catch (AbstractDaoException e) {
			System.out.println(TEST_CREATE_NULL);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
		}
		
	} // Fin de testCreateNull().__________________________________________
	

	
	/**
	 * Teste la méthode <b>create(objetNull)</b>.<br/>
	 * <ul>
	 * <li>garantit que create(objetNull) ne stocke rien .</li>
	 * <li>garantit que create(objetNull) retourne null.</li>
	 * <li>objetNull est un objet avec les valeurs obligatoires à null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCreateObjetNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testCreateObjetNull() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_CREATE_NULL);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________

		
		/* Vide le stockage. */
		this.viderStockage();

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT CREATE( OBJET NULL) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		
		ISectionHit objetNull1Persistant = null;
		ISectionHit objetNull2Persistant = null;
						
		
		try {
			
			/* *********************************************** */
			/* ********************* CREATION **************** */
			objetNull1Persistant = this.dao.create(objetNull1);
			objetNull2Persistant = this.dao.create(objetNull2);
			/* *********************************************** */
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println("OBJET METIER AVEC ATTRIBUTS NULL objetNull1 : " + objetNull1.toString());
				
				if (objetNull1Persistant != null) {
					System.out.println("OBJET PERSISTANT objetNull1Persistant : " + objetNull1Persistant.toString());
				} else {
					System.out.println("OBJET PERSISTANT objetNull1Persistant : null");
				}
				
				System.out.println("OBJET METIER AVEC ATTRIBUTS NULL objetNull2 : " + objetNull2.toString());
				
				if (objetNull1Persistant != null) {
					System.out.println("OBJET PERSISTANT objetNull2Persistant : " + objetNull2Persistant.toString());
				} else {
					System.out.println("OBJET PERSISTANT objetNull2Persistant : null");
				}
				
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
				
			}
			
			/* garantit que create(objetNull) retourne null. */
			assertNull(
					"create(objetNull) doit retourner null : "
						, objetNull1Persistant);

			/* garantit que create(objetNull) ne stocke rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		}
		catch (AbstractDaoException e) {
			System.out.println(TEST_CREATE_NULL);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
		}
		
	} // Fin de testCreateNull().__________________________________________
	

		
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
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testCreate() ********** ");
		}
		
		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_CREATE);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________

		
//		afficherContexte();
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);
		
		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT CREATE : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		try {
			
			/* ********************************************************* */
			/* ***********************CREATION************************** */		
			final ISectionHit objetPersiste1 = this.dao.create(objetACreer1);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("OBJET CREE : " + objetPersiste1.toString());
			}
			
			/* garantit que create(...) retourne l'objet persisté. */
			assertNotNull(
					"l'objet persisté retourne par create(...) ne doit pas être null : "
						, objetPersiste1);
			
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(LISTE_OBJETS_APRES_CREATE);
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}
						
			/* garantit que create rajoute l'objet métier dans le stockage. */
			assertEquals(FINAL_VAUT_INITIAL_PLUS_1
					, nombreObjetsFinal
						, Long.valueOf(nombreObjetsInitial + 1));

			
		}
		catch (AbstractDaoException e) {
			System.out.println(TEST_CREATE);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
		}
		
	} // Fin de testCreate().______________________________________________
	

	
	/**
	 * Teste la méthode create(Doublon).<br/>
	 * <ul>
	 * <li>garantit que create(Doublon) ne rajoute rien dans le stockage.</li>
	 * <li>garantit que create(Doublon) retourne null.</li>
	 * </ul>
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCreateDoublon() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testCreateDoublon() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_CREATE_DOUBLON);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________

		/* Vide le stockage. */
		this.viderStockage();

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		ISectionHit objet1Persistant = null;
		ISectionHit objet1MemeInstancePersistant = null;
		ISectionHit objet2EqualsObjet1Persistant = null;
		ISectionHit objet3EqualsObjet1Persistant = null;
		
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT CREATE DOUBLON : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
			
			System.out.println();
			System.out.println(LISTE_OBJETS_METIER_A_CREER);
			System.out.println(OBJET1 + objet1.toString());
			System.out.println(OBJET1_MEME_INSTANCE + objet1MemeInstance.toString());
			System.out.println(OBJET2_EQUALS_OBJET1 + objet2EqualsObj1.toString());
			System.out.println(OBJET3_EQUALS_OBJET1 + objet2EqualsObj1.toString());
		}
		
		
		assertEquals(
				"objet1 doit etre equals à objet1MemeInstance : "
					, objet1
						, objet1MemeInstance);
		
		assertEquals(
				"objet1 doit etre equals à objet2EqualsObj1 : "
					, objet1
						, objet2EqualsObj1);
		
		assertEquals(
				"objet1 doit etre equals à objet2EqualsObj1 : "
					, objet1
						, objet2EqualsObj1);

		
		try {
			
			/* ********************************************************* */
			/* ***********************CREATION************************** */		
			objet1Persistant = this.dao.create(objet1);
			objet1MemeInstancePersistant = this.dao.create(objet1MemeInstance);
			objet2EqualsObjet1Persistant = this.dao.create(objet2EqualsObj1);
			objet3EqualsObjet1Persistant = this.dao.create(objet3EqualsObj1);
			/* ********************************************************* */
			
			if (objet1Persistant != null) {
				assertNotNull(
						NE_DOIT_PAS_RETOURNER_NULL
							, objet1Persistant.getLocalisation());
			}
			
			if (objet1MemeInstancePersistant != null) {
				assertNotNull(
						NE_DOIT_PAS_RETOURNER_NULL
							, objet1MemeInstancePersistant.getLocalisation());
			}
			
			if (objet2EqualsObjet1Persistant != null) {
				assertNotNull(
						NE_DOIT_PAS_RETOURNER_NULL
							, objet2EqualsObjet1Persistant.getLocalisation());
			}
			
			if (objet3EqualsObjet1Persistant != null) {
				assertNotNull(
						NE_DOIT_PAS_RETOURNER_NULL
							, objet3EqualsObjet1Persistant.getLocalisation());
			}
			
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				
				if (objet1Persistant != null) {
					System.out.println("OBJET CREE objet1Persistant : " + objet1Persistant.toString());
				} else {
					System.out.println("OBJET CREE objet1Persistant : NULL");
				}
				
				if (objet1MemeInstancePersistant != null) {
					System.out.println("OBJET CREE objet1MemeInstancePersistant : " + objet1MemeInstancePersistant.toString());
				} else {
					System.out.println("OBJET CREE objet1MemeInstancePersistant : NULL");
				}
				
				if (objet2EqualsObjet1Persistant != null) {
					System.out.println("OBJET CREE objet2EqualsObjet1Persistant : " + objet2EqualsObjet1Persistant.toString());
				} else {
					System.out.println("OBJET CREE objet2EqualsObjet1Persistant : NULL");
				}
				
				if (objet3EqualsObjet1Persistant != null) {
					System.out.println("OBJET CREE objet3EqualsObjet1Persistant : " + objet3EqualsObjet1Persistant.toString());
				} else {
					System.out.println("OBJET CREE objet3EqualsObjet1Persistant : NULL");
				}
				
			}
			
			/* garantit que create(...) retourne l'objet persisté. */
			assertNotNull(
					"l'objet persisté retourne par create(...) ne doit pas être null : "
						, objet1Persistant);
			
			/* garantit que create(Doublon) retourne null. */
			assertNull(
					"create(doublon) doit retourner null : "
						, objet1MemeInstancePersistant);
			
			/* garantit que create(Doublon) retourne null. */
			assertNull(
					"create(doublon) doit retourner null : "
						, objet2EqualsObjet1Persistant);
			
			/* garantit que create(Doublon) retourne null. */
			assertNull(
					"create(doublon) doit retourner null : "
						, objet3EqualsObjet1Persistant);
			
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(LISTE_OBJETS_APRES_CREATE);
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}
						
			/* garantit que create rajoute l'objet métier dans le stockage. */
			/* garantit que create(Doublon) ne rajoute rien dans le stockage. */
			assertEquals(FINAL_VAUT_INITIAL_PLUS_1
					, nombreObjetsFinal
						, Long.valueOf(nombreObjetsInitial + 1));

			
		}
		catch (AbstractDaoException e) {
			System.out.println(TEST_CREATE);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
		}

	} // Fin de testCreateDoublon()._______________________________________
	


	/**
	 * Teste la méthode <b>persist(null)</b>.<br/>
	 * <ul>
	 * <li>garantit que persist(null) ne stocke rien.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testPersistNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testPersistNull() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_PERSIST_NULL);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________

		
		/* Vide le stockage. */
		this.viderStockage();

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		final ISectionHit objetVraimentNull1 = null;
						
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			this.afficherNbreObjetsInitial(nombreObjetsInitial);			
		}
		
		try {
			
			/* *********************************************** */
			/* ********************* CREATION **************** */
			this.dao.persist(objetVraimentNull1);
			/* *********************************************** */
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				this.afficherNbreObjetsFinal(nombreObjetsFinal);			
			}
			
			/* garantit que persist(null) ne stocke rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		}
		catch (AbstractDaoException e) {
			System.out.println(TEST_PERSIST_NULL);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
		}
		
	} // Fin de testPersistNull().__________________________________________
	

	
	/**
	 * Teste la méthode <b>persist(objetNull)</b>.<br/>
	 * <ul>
	 * <li>garantit que persist(objetNull) ne stocke rien .</li>
	 * <li>objetNull est un objet avec les valeurs obligatoires à null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testPersistObjetNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testPersistObjetNull() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_PERSIST_NULL);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________

		
		/* Vide le stockage. */
		this.viderStockage();

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			this.afficherNbreObjetsInitial(nombreObjetsInitial);			
		}
		
		try {
			
			/* *********************************************** */
			/* ********************* CREATION **************** */
			this.dao.persist(objetNull1);
			this.dao.persist(objetNull2);
			/* *********************************************** */
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println("OBJET METIER AVEC ATTRIBUTS NULL objetNull1 : " + objetNull1.toString());
								
				System.out.println("OBJET METIER AVEC ATTRIBUTS NULL objetNull2 : " + objetNull2.toString());
								
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
				
			}
			
			/* garantit que persist(objetNull) ne stocke rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		}
		catch (AbstractDaoException e) {
			System.out.println(TEST_PERSIST_NULL);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
		}
		
	} // Fin de testPersistNull().__________________________________________
	

		
	/**
	 * Teste la méthode persist(Objet Metier).<br/>
	 * <ul>
	 * <li>garantit que persist(objet correct) 
	 * rajoute l'objet métier dans le stockage.</li>
	 * <li>garantit que persist(objet correct) retourne l'objet persisté.</li>
	 * </ul>
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testPersist() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testPersist() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_PERSIST);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________

		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);
		
		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT PERSIST : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final ISectionHit objetAPersister = objetACreer1;
		
		if (AFFICHAGE_GENERAL && affichage) {			
			System.out.println();
			System.out.println("OBJET A PERSISTER : " + objetAPersister.toString());
		}
		
		
		try {
			
			/* ********************************************************* */
			/* ***********************CREATION************************** */		
			this.dao.persist(objetAPersister);
			/* ********************************************************* */
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES PERSIST : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}
						
			/* garantit que persist rajoute l'objet métier dans le stockage. */
			assertEquals(FINAL_VAUT_INITIAL_PLUS_1
					, nombreObjetsFinal
						, Long.valueOf(nombreObjetsInitial + 1));

			
		}
		catch (AbstractDaoException e) {
			System.out.println(TEST_PERSIST);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
		}
				
	} // Fin de testPersist().______________________________________________
	

	
	/**
	 * Teste la méthode persist(Doublon).<br/>
	 * <ul>
	 * <li>garantit que persist(Doublon) ne rajoute rien dans le stockage.</li>
	 * </ul>
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testPersistDoublon() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testPersistDoublon() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_PERSIST_DOUBLON);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________

		/* Vide le stockage. */
		this.viderStockage();

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT PERSIST DOUBLON : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
				
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(LISTE_OBJETS_METIER_A_CREER);
			System.out.println(OBJET1 + objet1.toString());
			System.out.println(OBJET1_MEME_INSTANCE + objet1MemeInstance.toString());
			System.out.println(OBJET2_EQUALS_OBJET1 + objet2EqualsObj1.toString());
			System.out.println(OBJET3_EQUALS_OBJET1 + objet3EqualsObj1.toString());
		}

		
		try {
			
			/* ********************************************************* */
			/* ***********************CREATION************************** */		
			this.dao.persist(objet1);
			this.dao.persist(objet1MemeInstance);
			this.dao.persist(objet2EqualsObj1);
			this.dao.persist(objet3EqualsObj1);
			/* ********************************************************* */
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES PERSIST : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}
						
			/* garantit que persist rajoute l'objet métier dans le stockage. */
			/* garantit que persist(Doublon) ne rajoute rien dans le stockage. */
			assertEquals(FINAL_VAUT_INITIAL_PLUS_1
					, nombreObjetsFinal
						, Long.valueOf(nombreObjetsInitial + 1));

			
		}
		catch (AbstractDaoException e) {
			System.out.println(TEST_PERSIST);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
		}

	} // Fin de testPersistDoublon()._______________________________________
	


	/**
	 * Teste la méthode <b>createReturnId(null)</b>.<br/>
	 * <ul>
	 * <li>garantit que createReturnId(null) ne stocke rien.</li>
	 * <li>garantit que createReturnId(null) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCreateReturnIdNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testCreateReturnIdNull() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_CREATERETURNID_NULL);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________

		
		/* Vide le stockage. */
		this.viderStockage();

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		
		//ETAT INITIAL
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			this.afficherNbreObjetsInitial(nombreObjetsInitial);		
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final ISectionHit objetVraimentNull1 = null;
		Long objetVraimentNull1PersistantId = null;

		
		try {
			
			/* *********************************************** */
			/* ********************* CREATION **************** */
			objetVraimentNull1PersistantId = this.dao.createReturnId(objetVraimentNull1);
			/* *********************************************** */

			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que createReturnId(null) retourne null. */
			assertNull(
					"createReturnId(null) doit retourner null : "
						, objetVraimentNull1PersistantId);
			
			// ETAT FINAL
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				this.afficherNbreObjetsFinal(nombreObjetsFinal);					
			}
						
			/* garantit que createReturnId(null) ne stocke rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		}
		catch (AbstractDaoException e) {
			System.out.println(TEST_CREATERETURNID_NULL);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
		}
		
	} // Fin de testCreateReturnIdNull().__________________________________________
	

	
	/**
	 * Teste la méthode <b>createReturnId(objetNull)</b>.<br/>
	 * <ul>
	 * <li>garantit que createReturnId(objetNull) ne stocke rien .</li>
	 * <li>garantit que createReturnId(objetNull) retourne null.</li>
	 * <li>objetNull est un objet avec les valeurs obligatoires à null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCreateReturnIdObjetNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testCreateReturnIdObjetNull() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_CREATERETURNID_NULL);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________

		
		/* Vide le stockage. */
		this.viderStockage();

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		// ETAT INITIAL				
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			this.afficherNbreObjetsInitial(nombreObjetsInitial);			
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */		
		Long objetNull1PersistantId = null;
		Long objetNull2PersistantId = null;

		
		try {
			
			/* *********************************************** */
			/* ********************* CREATION **************** */
			objetNull1PersistantId = this.dao.createReturnId(objetNull1);
			objetNull2PersistantId = this.dao.createReturnId(objetNull2);
			/* *********************************************** */
			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que createReturnId(objetNull) retourne null. */
			assertNull(
					"createReturnId(objetNull) doit retourner null : "
						, objetNull1PersistantId);

			// ETAT FINAL
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println("OBJET METIER AVEC ATTRIBUTS NULL objetNull1 : " + objetNull1.toString());
				
				if (objetNull1PersistantId != null) {
					System.out.println("ID OBJET PERSISTANT objetNull1Persistant : " + objetNull1PersistantId.toString());
				} else {
					System.out.println("ID OBJET PERSISTANT objetNull1Persistant : null");
				}
				
				System.out.println("OBJET METIER AVEC ATTRIBUTS NULL objetNull2 : " + objetNull2.toString());
				
				if (objetNull1PersistantId != null) {
					System.out.println("ID OBJET PERSISTANT objetNull2Persistant : " + objetNull2PersistantId.toString());
				} else {
					System.out.println("ID OBJET PERSISTANT objetNull2Persistant : null");
				}
				
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
				
			}
			
			/* garantit que createReturnId(objetNull) ne stocke rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		}
		catch (AbstractDaoException e) {
			System.out.println(TEST_CREATERETURNID_NULL);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
		}
		
	} // Fin de testCreateReturnIdNull().__________________________________________
	

		
	/**
	 * Teste la méthode createReturnId(Objet Metier).<br/>
	 * <ul>
	 * <li>garantit que createReturnId(objet correct) 
	 * rajoute l'objet métier dans le stockage.</li>
	 * <li>garantit que createReturnId(objet correct) retourne 
	 * l'ID de l'objet persisté.</li>
	 * </ul>
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCreateReturnId() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testCreateReturnId() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_CREATERETURNID);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________

		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);
		
		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT CREATERETURNID(objet correct) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */		
		final ISectionHit objetAStocker = objetACreer1;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("OBJET A STOCKER AVEC createReturnId(Object) : " + objetAStocker.toString());
		}
		
		
		try {
			
			/* ********************************************************* */
			/* ***********************CREATION************************** */		
			final Long objetPersiste1Id = this.dao.createReturnId(objetAStocker);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("ID de l'OBJET CREE : " + objetPersiste1Id.toString());
			}
			
			/* *********** */
			// ASSERTIONS
			/* *********** */			
			/* garantit que createReturnId(...) retourne 
			 * l'ID de l'objet persisté. */
			assertNotNull(
					"l'ID de l'objet persisté retourne par createReturnId(...) ne doit pas être null : "
						, objetPersiste1Id);

			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES CREATERETURNID(objet correct) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}
						
			/* garantit que createReturnId rajoute l'objet métier dans le stockage. */
			assertEquals(FINAL_VAUT_INITIAL_PLUS_1
					, nombreObjetsFinal
						, Long.valueOf(nombreObjetsInitial + 1));

			
		}
		catch (AbstractDaoException e) {
			System.out.println(TEST_CREATERETURNID);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
		}
				
	} // Fin de testCreateReturnId().______________________________________________
	

	
	/**
	 * Teste la méthode createReturnId(Doublon).<br/>
	 * <ul>
	 * <li>garantit que createReturnId(Doublon) ne rajoute rien dans le stockage.</li>
	 * <li>garantit que createReturnId(Doublon) retourne null.</li>
	 * </ul>
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCreateReturnIdDoublon() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testCreateReturnIdDoublon() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_CREATERETURNID_DOUBLON);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________

		/* Vide le stockage. */
		this.viderStockage();

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT CREATERETURNID DOUBLON : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);			
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */				
		Long objet1PersistantId = null;
		Long objet1MemeInstancePersistantId = null;
		Long objet2EqualsObjet1PersistantId = null;
		Long objet3EqualsObjet1PersistantId = null;

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(LISTE_OBJETS_METIER_A_CREER);
			System.out.println(OBJET1 + objet1.toString());
			System.out.println(OBJET1_MEME_INSTANCE + objet1MemeInstance.toString());
			System.out.println(OBJET2_EQUALS_OBJET1 + objet2EqualsObj1.toString());
			System.out.println(OBJET3_EQUALS_OBJET1 + objet3EqualsObj1.toString());
		}
		
		
		try {
			
			/* ********************************************************* */
			/* ***********************CREATION************************** */		
			objet1PersistantId = this.dao.createReturnId(objet1);
			objet1MemeInstancePersistantId = this.dao.createReturnId(objet1MemeInstance);
			objet2EqualsObjet1PersistantId = this.dao.createReturnId(objet2EqualsObj1);
			objet3EqualsObjet1PersistantId = this.dao.createReturnId(objet3EqualsObj1);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				
				if (objet1PersistantId != null) {
					System.out.println("ID OBJET CREE objet1Persistant : " + objet1PersistantId.toString());
				} else {
					System.out.println("ID OBJET CREE objet1Persistant : NULL");
				}
				
				if (objet1MemeInstancePersistantId != null) {
					System.out.println("ID OBJET CREE objet1MemeInstancePersistant : " + objet1MemeInstancePersistantId.toString());
				} else {
					System.out.println("ID OBJET CREE objet1MemeInstancePersistant : NULL");
				}
				
				if (objet2EqualsObjet1PersistantId != null) {
					System.out.println("ID OBJET CREE objet2EqualsObjet1Persistant : " + objet2EqualsObjet1PersistantId.toString());
				} else {
					System.out.println("ID OBJET CREE objet2EqualsObjet1Persistant : NULL");
				}
				
				if (objet3EqualsObjet1PersistantId != null) {
					System.out.println("ID OBJET CREE objet3EqualsObjet1Persistant : " + objet3EqualsObjet1PersistantId.toString());
				} else {
					System.out.println("ID OBJET CREE objet3EqualsObjet1Persistant : NULL");
				}
				
			}

			/* *********** */
			// ASSERTIONS
			/* *********** */			
			/* garantit que createReturnId(...) retourne l'objet persisté. */
			assertNotNull(
					"ID de l'objet persisté retourne par createReturnId(...) ne doit pas être null : "
						, objet1PersistantId);
			
			/* garantit que createReturnId(Doublon) retourne null. */
			assertNull(
					"createReturnId(doublon) doit retourner null : "
						, objet1MemeInstancePersistantId);
			
			/* garantit que createReturnId(Doublon) retourne null. */
			assertNull(
					"createReturnId(doublon) doit retourner null : "
						, objet2EqualsObjet1PersistantId);
			
			/* garantit que createReturnId(Doublon) retourne null. */
			assertNull(
					"createReturnId(doublon) doit retourner null : "
						, objet3EqualsObjet1PersistantId);

			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES CREATERETURNID DOUBLON : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}
						
			/* garantit que createReturnId rajoute l'objet métier dans le stockage. */
			/* garantit que createReturnId(Doublon) ne rajoute rien dans le stockage. */
			assertEquals(FINAL_VAUT_INITIAL_PLUS_1
					, nombreObjetsFinal
						, Long.valueOf(nombreObjetsInitial + 1));

			
		}
		catch (AbstractDaoException e) {
			System.out.println(TEST_CREATERETURNID_DOUBLON);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
		}

	} // Fin de testCreateReturnIdDoublon()._______________________________________
	


	/**
	 * Teste la méthode <b>saveIterable(null)</b>.<br/>
	 * <ul>
	 * <li>garantit que saveIterable(null) ne stocke rien.</li>
	 * <li>garantit que saveIterable(null) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testSaveIterableNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testSaveIterableNull() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_SAVEITERABLE_NULL);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________

		
		/* Vide le stockage. */
		this.viderStockage();

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
					
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT SAVEITERABLE(NULL) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);			
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */				
		final List<ISectionHit> listVraimentNull1 = null;
		List<ISectionHit> listVraimentNull1Persistant = null;
		

		try {
			
			/* *********************************************** */
			/* ********************* CREATION **************** */
			listVraimentNull1Persistant = (List<ISectionHit>) this.dao.saveIterable(listVraimentNull1);
			/* *********************************************** */
			
			/* *********** */
			// ASSERTIONS
			/* *********** */			
			/* garantit que saveIterable(null) retourne null. */
			assertNull(
					"saveIterable(null) doit retourner null : "
						, listVraimentNull1Persistant);
			

			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println("LISTE D'OBJETS APRES SAVEITERABLE(NULL) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				
				System.out.println();
				if (listVraimentNull1Persistant != null) {
					System.out.println(LISTE_OBJETS_PERSISTES);
					System.out.println(this.afficherListeObjetsMetier(
									listVraimentNull1Persistant));
				} else {
					System.out.println(LISTE_OBJETS_PERSISTES_NULL);
				}
					
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}
			
			/* garantit que saveIterable(null) ne stocke rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		}
		catch (AbstractDaoException e) {
			System.out.println(TEST_SAVEITERABLE_NULL);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
		}
		
	} // Fin de testSaveIterableNull().____________________________________
	
	
	
	/**
	 * Teste la méthode <b>saveIterable(objets Null)</b>.<br/>
	 * <ul>
	 * <li>garantit que saveIterable(objets null) ne stocke rien.</li>
	 * <li>garantit que saveIterable(objets null) retourne une liste vide.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testSaveIterableObjetsNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testSaveIterableObjetsNull() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_SAVEITERABLE_OBJETS_NULL);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________

		
		/* Vide le stockage. */
		this.viderStockage();

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
				
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
					
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT SAVEITERABLE(OBJETS NULL) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);			
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */				
		final List<ISectionHit> listAvecNull1 = new ArrayList<ISectionHit>();
		listAvecNull1.add(objetNull1);
		listAvecNull1.add(objetNull2);
		List<ISectionHit> listAvecNull1Persistant = null;

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(LISTE_OBJETS_METIER_A_CREER);
			System.out.println("objetNull1 : " + objetNull1.toString());
			System.out.println("objetNull2 : " + objetNull2.toString());
		}

		
		try {
			
			/* *********************************************** */
			/* ********************* CREATION **************** */
			listAvecNull1Persistant = (List<ISectionHit>) this.dao.saveIterable(listAvecNull1);
			/* *********************************************** */
			
			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que saveIterable(objets null) retourne une liste vide. */
			if (listAvecNull1Persistant != null) {
				
				assertTrue(
						"saveIterable(objets null) doit retourner une liste vide : "
							, listAvecNull1Persistant.isEmpty());
			}

			
			// ETAT FINAL			
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES SAVEITERABLE(OBJETS NULL) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				
				System.out.println();
				if (listAvecNull1Persistant != null) {
					System.out.println(LISTE_OBJETS_PERSISTES);
					System.out.println(this.afficherListeObjetsMetier(
									listAvecNull1Persistant));
				} else {
					System.out.println(LISTE_OBJETS_PERSISTES_NULL);
				}
					
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}
						
			/* garantit que saveIterable(objets null) ne stocke rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		}
		catch (AbstractDaoException e) {
			System.out.println(TEST_SAVEITERABLE_OBJETS_NULL);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
		}
		
	} // Fin de testSaveIterableObjetsNull().______________________________
	
	
	
	/**
	 * Teste la méthode <b>saveIterable(objets corrects)</b>.<br/>
	 * <ul>
	 * <li>garantit que saveIterable(objets corrects) stocke.</li>
	 * <li>garantit que saveIterable(objets corrects) 
	 * retourne une liste des objets stockés.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testSaveIterable() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testSaveIterable() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_SAVEITERABLE);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________

		
		/* Vide le stockage. */
		this.viderStockage();

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
					
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT SAVEITERABLE(OBJETS CORRECTS) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */				
		final List<ISectionHit> list1 = new ArrayList<ISectionHit>();
		list1.add(objetACreer1);
		list1.add(objetACreer2);
		List<ISectionHit> list1Persistante = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(LISTE_OBJETS_METIER_A_CREER);
			System.out.println("objetACreer1 : " + objetACreer1.toString());
			System.out.println("objetACreer2 : " + objetACreer2.toString());
		}
		
		
		try {
			
			/* *********************************************** */
			/* ********************* CREATION **************** */
			list1Persistante = (List<ISectionHit>) this.dao.saveIterable(list1);
			/* *********************************************** */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				if (list1Persistante != null) {
					System.out.println(LISTE_OBJETS_PERSISTES);
					System.out.println(this.afficherListeObjetsMetier(
									list1Persistante));
				} else {
					System.out.println(LISTE_OBJETS_PERSISTES_NULL);
				}
			}
			
			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que saveIterable(objets corrects) retourne une liste non vide. */
			if (list1Persistante != null) {
				
				assertFalse(
						"saveIterable(objets null) doit retourner une liste NON vide : "
							, list1Persistante.isEmpty());
			}
			

			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES SAVEITERABLE(OBJETS CORRECTS) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				
				
					
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}
						
			/* garantit que saveIterable(objets corrects) stocke. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_DEUX
					, nombreObjetsFinal == nombreObjetsInitial + 2);
			
		}
		catch (AbstractDaoException e) {
			System.out.println(TEST_SAVEITERABLE);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
		}
		
	} // Fin de testSaveIterable().________________________________________
	
	
	
	/**
	 * Teste la méthode <b>saveIterable(doublons)</b>.<br/>
	 * <ul>
	 * <li>garantit que saveIterable(doublons) ne stocke pas de doublon.</li>
	 * <li>garantit que saveIterable(doublons) 
	 * retourne une liste des objets stockés.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testSaveIterableDoublon() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testSaveIterableDoublon() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_SAVEITERABLE_DOUBLON);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________

		
		/* Vide le stockage. */
		this.viderStockage();

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
					
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT SAVEITERABLE(DOUBLONS) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);			
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */				
		final List<ISectionHit> list1 = new ArrayList<ISectionHit>();
		list1.add(objet1);
		list1.add(objet1MemeInstance);
		list1.add(objet2EqualsObj1);
		list1.add(objet3EqualsObj1);
		List<ISectionHit> list1Persistante = null;

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(LISTE_OBJETS_METIER_A_CREER);
			System.out.println(OBJET1 + objet1.toString());
			System.out.println(OBJET1_MEME_INSTANCE + objet1MemeInstance.toString());
			System.out.println(OBJET2_EQUALS_OBJET1 + objet2EqualsObj1.toString());
			System.out.println(OBJET3_EQUALS_OBJET1 + objet3EqualsObj1.toString());
		}
		
		
		try {
			
			/* *********************************************** */
			/* ********************* CREATION **************** */
			list1Persistante = (List<ISectionHit>) this.dao.saveIterable(list1);
			/* *********************************************** */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				if (list1Persistante != null) {
					System.out.println(LISTE_OBJETS_PERSISTES);
					System.out.println(this.afficherListeObjetsMetier(
									list1Persistante));
				} else {
					System.out.println(LISTE_OBJETS_PERSISTES_NULL);
				}
			}
			
			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que saveIterable(doublon) retourne une liste non vide. */
			if (list1Persistante != null) {
				
				assertFalse(
						"saveIterable(doublon) doit retourner une liste NON vide : "
							, list1Persistante.isEmpty());
			}

			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES SAVEITERABLE(DOUBLONS) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));					
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}
						
			/* garantit que saveIterable(doublon) stocke mais aucun doublon. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_UN
					, nombreObjetsFinal == nombreObjetsInitial + 1);
			
		}
		catch (AbstractDaoException e) {
			System.out.println(TEST_SAVEITERABLE_DOUBLON);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
		}
		
	} // Fin de testSaveIterableDoublon()._________________________________
	

	
	/**
	 * Teste la méthode <b>retrieve(null)</b>.<br/>
	 * <ul>
	 * <li>garantit que retrieve(null) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testRetrieveNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testRetrieveNull() ********** ");
		}
		
		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_RETRIEVE_NULL);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________
		
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);
		
		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT RETRIEVE(NULL) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */						
		final ISectionHit objetARechercher1 = null;
		ISectionHit objetPersisteTrouve1 = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET_METIER_A_RECHERCHER);
			System.out.println(OBJET_A_RECHERCHER1_NULL);

		}

		
		try {
			
			/* ********************************************************* */
			/* ***********************RETRIEVE************************** */		
			objetPersisteTrouve1 = this.dao.retrieve(objetARechercher1);
			/* ********************************************************* */

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(OBJET_PERSISTANT_TROUVE);
				if (objetPersisteTrouve1 != null) {
					System.out.println(OBJET_PERSISTE_TROUVE1 + objetPersisteTrouve1.toString());
				} else {
					System.out.println(OBJET_PERSISTE_TROUVE_NULL);
				}
			}

			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que retrieve(null) retourne null. */
			assertNull(
					"retrieve(null) doit retourner null : "
						, objetPersisteTrouve1);

			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES RETRIEVE(NULL) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que retrieve(null) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		} catch (AbstractDaoException e) {
			
			System.out.println(TEST_RETRIEVE_NULL);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
			
		}

	} // Fin de testRetrieveNull().________________________________________
	

	
	/**
	 * Teste la méthode <b>retrieve(objet null)</b>.<br/>
	 * <ul>
	 * <li>garantit que retrieve(objet null) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testRetrieveObjetNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testRetrieveObjetNull() ********** ");
		}
		
		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_RETRIEVE_OBJET_NULL);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________
		
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);
		
		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT RETRIEVE(OBJET NULL) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
				
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */								
		final ISectionHit objetARechercher1 = objetNull1;
		ISectionHit objetPersisteTrouve1 = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET_METIER_A_RECHERCHER);
			if (objetARechercher1 != null) {
				System.out.println(OBJET_A_RECHERCHER1 + objetARechercher1.toString());
			} else {
				System.out.println(OBJET_A_RECHERCHER1_NULL);
			}
		}

		
		try {
			
			/* ********************************************************* */
			/* ***********************RETRIEVE************************** */		
			objetPersisteTrouve1 = this.dao.retrieve(objetARechercher1);
			/* ********************************************************* */

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(OBJET_PERSISTANT_TROUVE);
				if (objetPersisteTrouve1 != null) {
					System.out.println(OBJET_PERSISTE_TROUVE1 + objetPersisteTrouve1.toString());
				} else {
					System.out.println(OBJET_PERSISTE_TROUVE_NULL);
				}
			}
			
			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que retrieve(objet null) retourne null. */
			assertNull(
					"retrieve(objet null) doit retourner null : "
						, objetPersisteTrouve1);
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES RETRIEVE(OBJET NULL) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que retrieve(objet null) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);

		} catch (AbstractDaoException e) {
			
			System.out.println(TEST_RETRIEVE_OBJET_NULL);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
			
		}

	} // Fin de testRetrieveObjetNull().___________________________________
	

	
	/**
	 * Teste la méthode <b>retrieve(inexistant)</b>.<br/>
	 * <ul>
	 * <li>garantit que retrieve(inexistant) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testRetrieveInexistant() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testRetrieveInexistant() ********** ");
		}
		
		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_RETRIEVE_INEXISTANT);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________
		
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);
		
		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT RETRIEVE(INEXISTANT) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
						
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */										
		final ISectionHit objetARechercher1 = objetInexistant;
		ISectionHit objetPersisteTrouve1 = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET_METIER_A_RECHERCHER);
			if (objetARechercher1 != null) {
				System.out.println(OBJET_A_RECHERCHER1 + objetARechercher1.toString());
			} else {
				System.out.println(OBJET_A_RECHERCHER1_NULL);
			}
		}

		
		try {
			
			/* ********************************************************* */
			/* ***********************RETRIEVE************************** */		
			objetPersisteTrouve1 = this.dao.retrieve(objetARechercher1);
			/* ********************************************************* */

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(OBJET_PERSISTANT_TROUVE);
				if (objetPersisteTrouve1 != null) {
					System.out.println(OBJET_PERSISTE_TROUVE1 + objetPersisteTrouve1.toString());
				} else {
					System.out.println(OBJET_PERSISTE_TROUVE_NULL);
				}
			}
			
			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que retrieve(inexistant) retourne null. */
			assertNull(
					"retrieve(inexistant) doit retourner null : "
						, objetPersisteTrouve1);

			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES RETRIEVE(INEXISTANT) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que retrieve(inexistant) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);

			
		} catch (AbstractDaoException e) {
			
			System.out.println(TEST_RETRIEVE_INEXISTANT);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
			
		}

	} // Fin de testRetrieveInexistant().__________________________________
	

	
	/**
	 * Teste la méthode <b>retrieve(existant)</b>.<br/>
	 * <ul>
	 * <li>garantit que retrieve(existant) retourne l'objet 
	 * persistant récupéré dans le stockage.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testRetrieve() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testRetrieve() ********** ");
		}
		
		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_RETRIEVE);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________
		
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);
		
		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT RETRIEVE(EXISTANT) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
				
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */												
		final ISectionHit objetARechercher1 = objetRemplirStockage1;
		ISectionHit objetPersisteTrouve1 = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET_METIER_A_RECHERCHER);
			if (objetARechercher1 != null) {
				System.out.println(OBJET_A_RECHERCHER1 + objetARechercher1.toString());
			} else {
				System.out.println(OBJET_A_RECHERCHER1_NULL);
			}
		}

		
		try {
			
			/* ********************************************************* */
			/* ***********************RETRIEVE************************** */		
			objetPersisteTrouve1 = this.dao.retrieve(objetARechercher1);
			/* ********************************************************* */

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(OBJET_PERSISTANT_TROUVE);
				if (objetPersisteTrouve1 != null) {
					System.out.println(OBJET_PERSISTE_TROUVE1 + objetPersisteTrouve1.toString());
				} else {
					System.out.println(OBJET_PERSISTE_TROUVE_NULL);
				}
			}
			
			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que retrieve(existant) retourne l'objet 
			 * persistant récupéré dans le stockage. */
			assertNotNull(
					"retrieve(existant) doit retourner l'objet persisté : "
						, objetPersisteTrouve1);
			
			assertEquals(
					"l'objet persistant doit être equals à l'objet recherché : "
						, objetARechercher1
							, objetPersisteTrouve1);
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES RETRIEVE(EXISTANT) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que retrieve(existant) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		} catch (AbstractDaoException e) {
			
			System.out.println(TEST_RETRIEVE);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
			
		}

	} // Fin de testRetrieve().____________________________________________
	

	
	/**
	 * Teste la méthode <b>findById(null)</b>.<br/>
	 * <ul>
	 * <li>garantit que findById(null) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFindByIdNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testFindByIdNull() ********** ");
		}
		
		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_FINDBYID_NULL);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________
		
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);
		
		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT FINDBYID(NULL) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */														
		final Long objetARechercher1Id = null;
		ISectionHit objetPersisteTrouve1 = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("ID DE L'OBJET METIER A RECHERCHER : ");
			System.out.println("objetARechercher1Id : NULL");

		}

		
		try {
			
			/* ********************************************************* */
			/* ***********************FINDBYID************************** */		
			objetPersisteTrouve1 = this.dao.findById(objetARechercher1Id);
			/* ********************************************************* */

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(OBJET_PERSISTANT_TROUVE);
				if (objetPersisteTrouve1 != null) {
					System.out.println(OBJET_PERSISTE_TROUVE1 + objetPersisteTrouve1.toString());
				} else {
					System.out.println(OBJET_PERSISTE_TROUVE_NULL);
				}
			}

			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que findById(null) retourne null. */
			assertNull(
					"findById(null) doit retourner null : "
						, objetPersisteTrouve1);

			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES FINDBYID(NULL) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}
			
			/* garantit que findById(null) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		} catch (AbstractDaoException e) {
			
			System.out.println(TEST_FINDBYID_NULL);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
			
		}

	} // Fin de testFindByIdNull().________________________________________
	

	
	/**
	 * Teste la méthode <b>findById(inexistant)</b>.<br/>
	 * <ul>
	 * <li>garantit que findById(inexistant) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFindByIdInexistant() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testFindByIdInexistant() ********** ");
		}
		
		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_FINDBYID_INEXISTANT);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________
		
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);
		
		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT FINDBYID(INEXISTANT) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */																
		final Long objetARechercher1Id = 17L;
		ISectionHit objetPersisteTrouve1 = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("ID DE L'OBJET METIER A RECHERCHER : ");			
			System.out.println("objetARechercher1Id : " + objetARechercher1Id.toString());			
		}

		
		try {
			
			/* ********************************************************* */
			/* ***********************FINDBYID************************** */		
			objetPersisteTrouve1 = this.dao.findById(objetARechercher1Id);
			/* ********************************************************* */

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(OBJET_PERSISTANT_TROUVE);
				if (objetPersisteTrouve1 != null) {
					System.out.println(OBJET_PERSISTE_TROUVE1 + objetPersisteTrouve1.toString());
				} else {
					System.out.println(OBJET_PERSISTE_TROUVE_NULL);
				}
			}
			
			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que findById(inexistant) retourne null. */
			assertNull(
					"findById(inexistant) doit retourner null : "
						, objetPersisteTrouve1);
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES FINDBYID(INEXISTANT) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que findById(inexistant) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		} catch (AbstractDaoException e) {
			
			System.out.println(TEST_FINDBYID_INEXISTANT);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
			
		}

	} // Fin de testFindByIdInexistant().__________________________________
	

	
	/**
	 * Teste la méthode <b>findById(existant)</b>.<br/>
	 * <ul>
	 * <li>garantit que findById(existant) retourne l'objet 
	 * persistant récupéré dans le stockage.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFindById() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testFindById() ********** ");
		}
		
		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_FINDBYID);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________
		
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);
		
		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT FINDBYID(EXISTANT) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */																		
		final Long objetARechercher1Id = 3L;
		ISectionHit objetPersisteTrouve1 = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("ID DE L'OBJET METIER A RECHERCHER : ");			
			System.out.println("objetARechercher1Id : " + objetARechercher1Id.toString());
		}

		
		try {
			
			/* ********************************************************* */
			/* ***********************FINDBYID************************** */		
			objetPersisteTrouve1 = this.dao.findById(objetARechercher1Id);
			/* ********************************************************* */

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(OBJET_PERSISTANT_TROUVE);
				if (objetPersisteTrouve1 != null) {
					System.out.println(OBJET_PERSISTE_TROUVE1 + objetPersisteTrouve1.toString());
				} else {
					System.out.println(OBJET_PERSISTE_TROUVE_NULL);
				}
			}
			
			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que findById(existant) retourne l'objet 
			 * persistant récupéré dans le stockage. */
			assertNotNull(
					"findById(existant) doit retourner l'objet persisté : "
						, objetPersisteTrouve1);
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES FINDBYID(EXISTANT) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que findById(existant) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		} catch (AbstractDaoException e) {
			
			System.out.println(TEST_FINDBYID);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
			
		}

	} // Fin de testFindById().____________________________________________
	

	
	/**
	 * Teste la méthode <b>retrieveId(null)</b>.<br/>
	 * <ul>
	 * <li>garantit que retrieveId(null) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testRetrieveIdNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testRetrieveIdNull() ********** ");
		}
		
		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_RETRIEVEID_NULL);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________
		
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);
		
		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT RETRIEVEID(NULL) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */																				
		final ISectionHit objetARechercher1 = null;
		Long objetPersisteTrouve1Id = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET_METIER_A_RECHERCHER);
			System.out.println(OBJET_A_RECHERCHER1_NULL);

		}

		
		try {
			
			/* ********************************************************* */
			/* ***********************RETRIVEID************************** */		
			objetPersisteTrouve1Id = this.dao.retrieveId(objetARechercher1);
			/* ********************************************************* */

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(ID_OBJET_PERSISTANT_TROUVE);
				if (objetPersisteTrouve1Id != null) {
					System.out.println(OBJET_PERSISTE_TROUVE1_ID + objetPersisteTrouve1Id.toString());
				} else {
					System.out.println(OBJET_PERSISTE_TROUVE1_ID_NULL);
				}
			}

			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que retrieveId(null) retourne null. */
			assertNull(
					"retrieveId(null) doit retourner null : "
						, objetPersisteTrouve1Id);

			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES RETRIEVEID(NULL) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que retrieveId(null) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);

		} catch (AbstractDaoException e) {
			
			System.out.println(TEST_RETRIEVEID_NULL);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
			
		}

	} // Fin de testRetrieveIdNull().______________________________________
	

	
	/**
	 * Teste la méthode <b>retrieveId(objet null)</b>.<br/>
	 * <ul>
	 * <li>garantit que retrieveId(objet null) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testRetrieveIdObjetNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testRetrieveIdObjetNull() ********** ");
		}
		
		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_RETRIEVEID_OBJET_NULL);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________
		
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);
		
		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT RETRIEVEID(OBJET NULL) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */																						
		final ISectionHit objetARechercher1 = objetNull1;
		Long objetPersisteTrouve1Id = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET_METIER_A_RECHERCHER);
			if (objetARechercher1 != null) {
				System.out.println(OBJET_A_RECHERCHER1 + objetARechercher1.toString());
			} else {
				System.out.println(OBJET_A_RECHERCHER1_NULL);
			}
		}

		
		try {
			
			/* ********************************************************* */
			/* ***********************RETRIVEID************************** */		
			objetPersisteTrouve1Id = this.dao.retrieveId(objetARechercher1);
			/* ********************************************************* */

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(ID_OBJET_PERSISTANT_TROUVE);
				if (objetPersisteTrouve1Id != null) {
					System.out.println(OBJET_PERSISTE_TROUVE1_ID + objetPersisteTrouve1Id.toString());
				} else {
					System.out.println(OBJET_PERSISTE_TROUVE1_ID_NULL);
				}
			}

			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que retrieveId(objet null) retourne null. */
			assertNull(
					"retrieveId(objet null) doit retourner null : "
						, objetPersisteTrouve1Id);
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES RETRIEVEID(OBJET NULL) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que retrieveId(objet null) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		} catch (AbstractDaoException e) {
			
			System.out.println(TEST_RETRIEVEID_OBJET_NULL);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
			
		}

	} // Fin de testRetrieveIdObjetNull()._________________________________
	

	
	/**
	 * Teste la méthode <b>retrieveId(inexistant)</b>.<br/>
	 * <ul>
	 * <li>garantit que retrieveId(inexistant) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testRetrieveIdInexistant() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testRetrieveIdInexistant() ********** ");
		}
		
		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_RETRIEVEID_INEXISTANT);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________
		
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);
		
		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT RETRIEVEID(INEXISTANT) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */																								
		final ISectionHit objetARechercher1 = objetInexistant;
		Long objetPersisteTrouve1Id = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET_METIER_A_RECHERCHER);
			if (objetARechercher1 != null) {
				System.out.println(OBJET_A_RECHERCHER1 + objetARechercher1.toString());
			} else {
				System.out.println(OBJET_A_RECHERCHER1_NULL);
			}
		}

		
		try {
			
			/* ********************************************************* */
			/* ***********************RETRIVEID************************** */		
			objetPersisteTrouve1Id = this.dao.retrieveId(objetARechercher1);
			/* ********************************************************* */

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(ID_OBJET_PERSISTANT_TROUVE);
				if (objetPersisteTrouve1Id != null) {
					System.out.println(OBJET_PERSISTE_TROUVE1_ID + objetPersisteTrouve1Id.toString());
				} else {
					System.out.println(OBJET_PERSISTE_TROUVE1_ID_NULL);
				}
			}

			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que retrieveId(inexistant) retourne null. */
			assertNull(
					"retrieveId(inexistant) doit retourner null : "
						, objetPersisteTrouve1Id);

			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES RETRIEVEID(INEXISTANT) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que retrieveId(inexistant) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		} catch (AbstractDaoException e) {
			
			System.out.println(TEST_RETRIEVEID_INEXISTANT);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
			
		}

	} // Fin de testRetrieveIdInexistant().________________________________
	

	
	/**
	 * Teste la méthode <b>retrieveId(existant)</b>.<br/>
	 * <ul>
	 * <li>garantit que retrieveId(existant) retourne l'ID de 
	 * l'objet persistant récupéré dans le stockage.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testRetrieveId() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testRetrieveId() ********** ");
		}
		
		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_RETRIEVEID);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________
		
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);
		
		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();
		
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT RETRIEVEID(EXISTANT) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */																										
		final ISectionHit objetARechercher1 = objetRemplirStockage1;
		Long objetPersisteTrouve1Id = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET_METIER_A_RECHERCHER);
			if (objetARechercher1 != null) {
				System.out.println(OBJET_A_RECHERCHER1 + objetARechercher1.toString());
			} else {
				System.out.println(OBJET_A_RECHERCHER1_NULL);
			}
		}

		
		try {
			
			/* ********************************************************* */
			/* ***********************RETRIVEID************************** */		
			objetPersisteTrouve1Id = this.dao.retrieveId(objetARechercher1);
			/* ********************************************************* */

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(ID_OBJET_PERSISTANT_TROUVE);
				if (objetPersisteTrouve1Id != null) {
					System.out.println(OBJET_PERSISTE_TROUVE1_ID + objetPersisteTrouve1Id.toString());
				} else {
					System.out.println(OBJET_PERSISTE_TROUVE1_ID_NULL);
				}
			}

			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que retrieveId(existant) retourne l'objet 
			 * persistant récupéré dans le stockage. */
			assertNotNull(
					"retrieveId(existant) doit retourner l'ID de l'objet persisté : "
						, objetPersisteTrouve1Id);

			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();
			
			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES RETRIEVEID(EXISTANT) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que retrieveId(existant) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);

			
		} catch (AbstractDaoException e) {
			
			System.out.println(TEST_RETRIEVEID);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
			
		}

	} // Fin de testRetrieveId().__________________________________________
	

	
	/**
	 * Teste la méthode <b>findAll()</b>.<br/>
	 * <ul>
	 * <li>garantit que findAll() retourne tout le stockage.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFindAll() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testFindAll() ********** ");
		}
		
		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_FINDALL);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________
		
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);
		
		List<ISectionHit> stockageList = null;
		
		try {
			
			/* ********************************************************* */
			/* ***********************RETRIVEID************************** */		
			stockageList = this.dao.findAll();
			/* ********************************************************* */

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			final Long nombreObjetsFinal = this.dao.count();
			
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES FINDALL() : ");
				System.out.println(this.dao.afficherListeObjetsMetier(stockageList));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			assertEquals(FINDALL_DOIT_RETOURNER_4_ENREGISTREMENTS
					, Long.valueOf(4L)
						, nombreObjetsFinal);
			
		} catch (AbstractDaoException e) {
			
			System.out.println(TEST_FINDALL);
			this.afficherAbstractDaoException(e);			
			e.printStackTrace();
			
		}

	} // Fin de testFindAll()._____________________________________________
	

	
	/**
	 * Teste la méthode <b>findAllMax(out)</b>.<br/>
	 * <ul>
	 * <li>garantit que findAllMax(out) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFindAllMaxOut() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testFindAllMaxOut() ********** ");
		}
		
		/* this.dao NON INJECTE. */
		if (this.dao == null) {
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_FINDALLMAX_OUT);
				this.afficherDAONonInstancie();
			}
			
			return;
			
		} // Fin de this.dao NON INJECTE._____________________
		
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT FINDALLMAX(OUT) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */																										
		/* ************************* */
		// STARTPOSITION HORS DES CLOUS.
		final int startPosition = 4;
		final int maxResult = 3;
		List<ISectionHit> resultat = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("INDEX (0-based) DE DEPART : " + startPosition);
			System.out.println("NOMBRE DE RESULTATS MAXIMUM : " + maxResult);
		}
		
		
		try {

			/* ********************************************************* */
			/* *********************FINDALLMAX************************** */
			resultat = this.dao.findAllMax(startPosition, maxResult);
			/* ********************************************************* */

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(OBJETS_TROUVES);
				System.out.println(this.dao.afficherListeObjetsMetier(resultat));
			}

			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que findAllMax(out) retourne null. */
			assertNull(
					"findAllMax(out) doit retourner null : "
						, resultat);

			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES FINDALLMAX(OUT) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que findAllMax(out) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);

		} catch (AbstractDaoException e) {

			System.out.println(TEST_FINDALLMAX_OUT);
			this.afficherAbstractDaoException(e);
			e.printStackTrace();

		}

	} // Fin de testFindAllMaxOut()._______________________________________

	
	
	/**
	 * Teste la méthode <b>findAllMax()</b>.<br/>
	 * <ul>
	 * <li>garantit que findAllMax() retourne 
	 * la liste d'objets trouvés.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFindAllMax() throws Exception {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testFindAllMax() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_FINDALLMAX);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________

		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT FINDALLMAX() : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */																										
		/* ************************* */
		// STARTPOSITION DANS LES CLOUS.
		final int startPosition = 2;
		final int maxResult = 7;
		List<ISectionHit> resultat = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("INDEX (0-based) DE DEPART : " + startPosition);
			System.out.println("NOMBRE DE RESULTATS MAXIMUM : " + maxResult);
		}

		try {

			/* ********************************************************* */
			/* *********************FINDALLMAX************************** */
			resultat = this.dao.findAllMax(startPosition, maxResult);
			/* ********************************************************* */

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(OBJETS_TROUVES);
				System.out.println(this.dao.afficherListeObjetsMetier(resultat));
			}

			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que findAllMax() retourne 
			 * la liste d'objets trouvés. */
			assertNotNull(
					"findAllMax() doit retourner la liste d'objets trouvés : "
						, resultat);
			
			assertEquals(
					"findAllMax() doit retourner nombreObjetsInitial - startPosition objets : "
					, nombreObjetsInitial - startPosition 
						, resultat.size());
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES FINDALLMAX() : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que findAllMax() ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);

		} catch (AbstractDaoException e) {

			System.out.println(TEST_FINDALLMAX);
			this.afficherAbstractDaoException(e);
	
		}

	} // Fin de testFindAllMax().__________________________________________
	
	

	/**
	 * Teste la méthode <b>findAllIterable(null)</b>.<br/>
	 * <ul>
	 * <li>garantit que findAllIterable(null) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFindAllIterableNull() throws Exception {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testFindAllIterableNull() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_FINDALLITERABLE_NULL);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________

		/* vide et remplit le stockage. */
		this.remplirStockage(false);
		
		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT FINDALLITERABLE(NULL) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */																										
		/* ************************* */
		// ITERABLE NULL.
		final Iterable<Long> ids = null;
		List<ISectionHit> resultat = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("LISTE DES IDs A RECHERCHER : null");
		}
				
		try {

			/* ********************************************************* */
			/* ********************FINDALLITERABLE********************** */
			resultat = (List<ISectionHit>) this.dao.findAllIterable(ids);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				if (resultat != null) {
					System.out.println(OBJETS_TROUVES + this.dao.afficherListeObjetsMetier(resultat));
				} else {
					System.out.println("OBJETS TROUVES : NULL");
				}				
			}

			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que findAllIterable(null) retourne null. */
			assertNull(
					"findAllIterable(null) doit retourner null : "
						, resultat);
			
			// ETAT FINAL		
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES FINDALLITERABLE(NULL) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que findAllIterable(null) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);

		} catch (AbstractDaoException e) {

			System.out.println(TEST_FINDALLITERABLE_NULL);
			this.afficherAbstractDaoException(e);

		}

	} // Fin de testFindAllIterableNull()._________________________________
	
	
	
	/**
	 * Teste la méthode <b>findAllIterable(out)</b>.<br/>
	 * <ul>
	 * <li>garantit que findAllIterable(out) retourne une liste vide.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFindAllIterableOut() throws Exception {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testFindAllIterableOut() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_FINDALLITERABLE_OUT);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________

		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT FINDALLITERABLE(OUT) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */																										
		/* ************************* */
		// ITERABLE OUT.
		final List<Long> ids = new ArrayList<Long>();
		ids.add(17L);
		ids.add(20L);
		List<ISectionHit> resultat = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("LISTE DES IDS A RECHERCHER : ");
			for (final Long id : ids) {
				System.out.println(id.toString());
			}
		}

		
		try {

			/* ********************************************************* */
			/* ********************FINDALLITERABLE********************** */
			resultat = (List<ISectionHit>) this.dao.findAllIterable(ids);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				if (resultat != null) {
					System.out.println(OBJETS_TROUVES);
					System.out.println(this.dao.afficherListeObjetsMetier(resultat));
				} else {
					System.out.println("OBJETS TROUVES : NULL");
				}				
			}

			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que findAllIterable(out) retourne une liste vide. */
			if (resultat != null) {
				assertTrue(
						"findAllIterable(out) doit retourner une liste vide : "
							, resultat.isEmpty());
			}
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES FINDALLITERABLE(OUT) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que findAllIterable(out) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);

		} catch (AbstractDaoException e) {

			System.out.println(TEST_FINDALLITERABLE_OUT);
			this.afficherAbstractDaoException(e);

		}

	} // Fin de testFindAllIterableOut().__________________________________
	
	
	
	/**
	 * Teste la méthode <b>findAllIterable()</b>.<br/>
	 * <ul>
	 * <li>garantit que findAllIterable() retourne la liste des objets.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFindAllIterable() throws Exception {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testFindAllIterable() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_FINDALLITERABLE);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________

		/* vide et remplit le stockage. */
		this.remplirStockage(affichage);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT FINDALLITERABLE() : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */																										
		/* ************************* */
		// ITERABLE CORRECT.
		final List<Long> ids = new ArrayList<Long>();
		ids.add(2L);
		ids.add(3L);
		ids.add(17L);
		ids.add(20L);
		List<ISectionHit> resultat = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("LISTE DES IDS A RECHERCHER : ");
			for (final Long id : ids) {
				System.out.println(id.toString());
			}
		}

		
		try {

			/* ********************************************************* */
			/* ********************FINDALLITERABLE********************** */
			resultat = (List<ISectionHit>) this.dao.findAllIterable(ids);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				if (resultat != null) {
					System.out.println(OBJETS_TROUVES);
					System.out.println(this.dao.afficherListeObjetsMetier(resultat));
				} else {
					System.out.println("OBJETS TROUVES : NULL");
				}				
			}

			/* *********** */
			// ASSERTIONS
			/* *********** */						
			/* garantit que findAllIterable() retourne une liste non vide. */
			if (resultat != null) {
				assertFalse(
						"findAllIterable() doit retourner une liste NON vide : "
							, resultat.isEmpty());
			}
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES FINDALLITERABLE() : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que findAllIterable() ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);

		} catch (AbstractDaoException e) {

			System.out.println(TEST_FINDALLITERABLE);
			this.afficherAbstractDaoException(e);

		}

	} // Fin de testFindAllIterable()._____________________________________
	

	
	/**
	 * teste la méthode <b>update(null)</b>.<br/>
	 * <ul>
	 * <li>garantit que update(null) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testUpdateNull() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testUpdateNull() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_UPDATE_NULL);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________

		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTUPDATE(NULL) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final ISectionHit objetAModifier = null;
		ISectionHit resultat = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET_METIER_A_MODIFIER_NULL);
		}
		
		
		try {

			/* ********************************************************* */
			/* ***********************UPDATE**************************** */
			resultat = this.dao.update(objetAModifier);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				if (resultat != null) {
					System.out.println(OBJET_MODIFIE + resultat.toString());
				} else {
					System.out.println(OBJET_MODIFIE_NULL);
				}
			}

			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que update(null) retourne null. */
			assertNull(
					"update(null) doit retourner null : "
						, resultat);
			
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTUPDATE(NULL) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que update(null) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		} catch (AbstractDaoException e) {

			System.out.println(TEST_UPDATE_NULL);
			this.afficherAbstractDaoException(e);

		}
				
	} // Fin de testUpdateNull().__________________________________________
	

	
	/**
	 * teste la méthode <b>update(inexistant)</b>.<br/>
	 * <ul>
	 * <li>garantit que update(inexistant) ne fait rien 
	 * et retourne l'objetAModifier pour un DAO JPA.</li>
	 * <li>garantit que update(inexistant) retourne null 
	 * pour un DAO non JPA.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testUpdateInexistant() throws Exception {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testUpdateInexistant() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_UPDATE_INEXISTANT);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL	
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTUPDATE(INEXISTANT) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final ISectionHit objetAModifier = objetInexistant;
		ISectionHit resultat = null;

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET_METIER_A_MODIFIER + objetAModifier.toString());
		}
		
		
		try {

			/* ********************************************************* */
			/* ***********************UPDATE**************************** */
			resultat = this.dao.update(objetAModifier);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				if (resultat != null) {
					System.out.println(OBJET_MODIFIE + resultat.toString());
				} else {
					System.out.println(OBJET_MODIFIE_NULL);
				}
			}

			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			if (DAO_JPA) {
				/* garantit que update(inexistant) ne fait rien 
				 * et retourne l'objetAModifier pour un DAO JPA. */
				assertEquals(
						"update(inexistant) ne fait rien et doit "
						+ "retourner l'objetAModifier pour un DAO JPA : "
						, objetAModifier
							, resultat);
			} else {
				/* garantit que update(inexistant) retourne null 
				 * pour un DAO non JPA. */
				assertNull(
						"update(inexistant) doit retourner "
						+ "null pour un DAO non JPA : "
							, resultat);
			}
			
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTUPDATE(INEXISTANT) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que update(inexistant) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		} catch (AbstractDaoException e) {

			System.out.println(TEST_UPDATE_INEXISTANT);
			this.afficherAbstractDaoException(e);

		}
				
	} // Fin de testUpdateInexistant().____________________________________
	
	
	
	/**
	 * teste la méthode <b>update(doublon)</b>.<br/>
	 * <ul>
	 * <li>garantit que update(doublon) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testUpdateDoublon() throws Exception {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testUpdateDoublon() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_UPDATE_DOUBLON);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL		
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTUPDATE(DOUBLON) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final ISectionHit objetAModifier = objetRemplirStockage1;
		ISectionHit objetAModifierPersistant = null;
		ISectionHit resultat = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET_METIER_A_MODIFIER + objetAModifier.toString());
		}
		
		/* modifier et créer un doublon. */			
		objetAModifierPersistant = this.dao.retrieve(objetAModifier);
		
		if (objetAModifierPersistant != null) {
			
			objetAModifierPersistant.setNumRoute(objetRemplirStockage2.getNumRoute());
			objetAModifierPersistant.setIndiceNumRoute(objetRemplirStockage2.getIndiceNumRoute());
			objetAModifierPersistant.setIndiceLettreRoute(objetRemplirStockage2.getIndiceLettreRoute());
			objetAModifierPersistant.setCategorieAdminRoute(objetRemplirStockage2.getCategorieAdminRoute());
			objetAModifierPersistant.setNumDepartement(objetRemplirStockage2.getNumDepartement());
			objetAModifierPersistant.setLieuDitOrigine(objetRemplirStockage2.getLieuDitOrigine());
			objetAModifierPersistant.setPrOrigine(objetRemplirStockage2.getPrOrigine());
			objetAModifierPersistant.setAbsOrigine(objetRemplirStockage2.getAbsOrigine());
			objetAModifierPersistant.setLieuDitExtremite(objetRemplirStockage2.getLieuDitExtremite());
			objetAModifierPersistant.setPrExtremite(objetRemplirStockage2.getPrExtremite());
			objetAModifierPersistant.setAbsExtremite(objetRemplirStockage2.getAbsExtremite());
			objetAModifierPersistant.setLieuDitComptage(objetRemplirStockage2.getLieuDitComptage());
			objetAModifierPersistant.setPrComptage(objetRemplirStockage2.getPrComptage());
			objetAModifierPersistant.setAbsComptage(objetRemplirStockage2.getAbsComptage());
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(MODIFICATIONS + objetAModifierPersistant.toString());
			}
		}
			
						
		try {

			/* ********************************************************* */
			/* ***********************UPDATE**************************** */
			resultat = this.dao.update(objetAModifierPersistant);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				if (resultat != null) {
					System.out.println(OBJET_MODIFIE + resultat.toString());
				} else {
					System.out.println(OBJET_MODIFIE_NULL);
				}
			}

			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que update(doublon) retourne null. */
			assertNull(
					"update(doublon) doit retourner null : "
						, resultat);
			
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTUPDATE(DOUBLON) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que update(doublon) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
						
		} catch (AbstractDaoException e) {

			System.out.println(TEST_UPDATE_DOUBLON);
			this.afficherAbstractDaoException(e);

		}
						
	} // Fin de testUpdateDoublon()._______________________________________
	

	
	/**
	 * teste la méthode <b>update()</b>.<br/>
	 * <ul>
	 * <li>garantit que update() modifie 
	 * et retourne l'objet modifié pour un DAO JPA.</li>
	 * <li>garantit que update() retourne null 
	 * pour un DAO non JPA.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testUpdate() throws Exception {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testUpdate() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_UPDATE);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTUPDATE() : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final ISectionHit objetAModifier = objetRemplirStockage1;
		ISectionHit objetAModifierPersistant = null;
		ISectionHit resultat = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET_METIER_A_MODIFIER + objetAModifier.toString());
		}
		
		/* modifier SANS créer un doublon. */			
		objetAModifierPersistant = this.dao.retrieve(objetAModifier);
		
		if (objetAModifierPersistant != null) {
			
			objetAModifierPersistant.setNumRoute(objetModifieCorrect.getNumRoute());
			objetAModifierPersistant.setIndiceNumRoute(objetModifieCorrect.getIndiceNumRoute());
			objetAModifierPersistant.setIndiceLettreRoute(objetModifieCorrect.getIndiceLettreRoute());
			objetAModifierPersistant.setCategorieAdminRoute(objetModifieCorrect.getCategorieAdminRoute());
			objetAModifierPersistant.setNumDepartement(objetModifieCorrect.getNumDepartement());
			objetAModifierPersistant.setLieuDitOrigine(objetModifieCorrect.getLieuDitOrigine());
			objetAModifierPersistant.setPrOrigine(objetModifieCorrect.getPrOrigine());
			objetAModifierPersistant.setAbsOrigine(objetModifieCorrect.getAbsOrigine());
			objetAModifierPersistant.setLieuDitExtremite(objetModifieCorrect.getLieuDitExtremite());
			objetAModifierPersistant.setPrExtremite(objetModifieCorrect.getPrExtremite());
			objetAModifierPersistant.setAbsExtremite(objetModifieCorrect.getAbsExtremite());
			objetAModifierPersistant.setLieuDitComptage(objetModifieCorrect.getLieuDitComptage());
			objetAModifierPersistant.setPrComptage(objetModifieCorrect.getPrComptage());
			objetAModifierPersistant.setAbsComptage(objetModifieCorrect.getAbsComptage());
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(MODIFICATIONS + objetAModifierPersistant.toString());
			}
		}
		
		
		try {

			/* ********************************************************* */
			/* ***********************UPDATE**************************** */
			resultat = this.dao.update(objetAModifierPersistant);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				if (resultat != null) {
					System.out.println(OBJET_MODIFIE + resultat.toString());
				} else {
					System.out.println(OBJET_MODIFIE_NULL);
				}
			}

			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			if (DAO_JPA) {
				/* garantit que update() modifie 
				 * et retourne l'objet modifié pour un DAO JPA. */
				assertEquals(
						"update() modifie et doit "
						+ "retourner l'objet modifié pour un DAO JPA : "
						, objetAModifierPersistant
							, resultat);
			} else {
				/* garantit que update() retourne null 
				 * pour un DAO non JPA. */
				assertNull(
						"update() doit retourner null "
						+ "pour un DAO non JPA : "
							, resultat);
			}
			
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTUPDATE() : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que update() ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
						
		} catch (AbstractDaoException e) {

			System.out.println(TEST_UPDATE);
			this.afficherAbstractDaoException(e);

		}
				
	} // Fin de testUpdate().______________________________________________
	

	
	/**
	 * teste la méthode <b>updateById(id null, objet correct)</b>.<br/>
	 * <ul>
	 * <li>garantit que updateById(id null, objet correct) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */	
	@SuppressWarnings(UNUSED)
	@Test
	public void testUpdateIdNull() throws Exception {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testUpdateIdNull() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_UPDATEID_NULL);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTUPDATEID(NULL) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final Long idObjetAModifier = null;
		final ISectionHit objetAModifier = this.dao.findById(idObjetAModifier);
		final ISectionHit objetModifie = objetModifieCorrect;
		ISectionHit resultat = null;

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(ID_OBJET_A_MODIFIER_NULL);
			System.out.println(MODIFICATIONS + objetModifie);
			if (objetAModifier != null) {
				System.out.println(OBJET_METIER_A_MODIFIER + objetAModifier.toString());
			} else {
				System.out.println(OBJET_METIER_A_MODIFIER_NULL);
			}			
		}
		
		
		try {

			/* ********************************************************* */
			/* **********************UPDATEID*************************** */
			resultat = this.dao.updateById(idObjetAModifier, objetModifie);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				if (resultat != null) {
					System.out.println(OBJET_MODIFIE + resultat.toString());
				} else {
					System.out.println(OBJET_MODIFIE_NULL);
				}
			}

			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que updateById(id null, objet correct) retourne null. */
			assertNull(
					"updateById(id null, objet correct) doit retourner null : "
						, resultat);
			
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTUPDATEID(NULL) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que updateById(id null, objet correct) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
			
		} catch (AbstractDaoException e) {

			System.out.println(TEST_UPDATEID_NULL);
			this.afficherAbstractDaoException(e);

		}
				
	} // Fin de testUpdateIdNull().________________________________________
	

	
	/**
	 * teste la méthode <b>updateById(id inexistant, objet correct)</b>.<br/>
	 * <ul>
	 * <li>garantit que updateById(id inexistant, objet correct) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */	
	@SuppressWarnings(UNUSED)
	@Test
	public void testUpdateIdInexistant() throws Exception {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testUpdateIdInexistant() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_UPDATEID_INEXISTANT);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
	
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTUPDATEID(INEXISTANT) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		/* ID inexistant. */
		final Long idObjetAModifier = 17L;
		final ISectionHit objetAModifier = this.dao.findById(idObjetAModifier);
		final ISectionHit objetModifie = objetModifieCorrect;
		ISectionHit resultat = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(ID_OBJET_A_MODIFIER + idObjetAModifier.toString());
			System.out.println(MODIFICATIONS + objetModifie.toString());
			if (objetAModifier != null) {
				System.out.println(OBJET_METIER_A_MODIFIER + objetAModifier.toString());
			} else {
				System.out.println(OBJET_METIER_A_MODIFIER_NULL);
			}			
		}
		
		
		try {

			/* ********************************************************* */
			/* **********************UPDATEID*************************** */
			resultat = this.dao.updateById(idObjetAModifier, objetModifie);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				if (resultat != null) {
					System.out.println(OBJET_MODIFIE + resultat.toString());
				} else {
					System.out.println(OBJET_MODIFIE_NULL);
				}
			}

			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que updateById(id inexistant, objet correct) retourne null. */
			assertNull(
					"updateById(id inexistant, objet correct) doit retourner null : "
						, resultat);
			
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTUPDATEID(INEXISTANT) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que updateById(id inexistant, objet correct) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
									
		} catch (AbstractDaoException e) {

			System.out.println(TEST_UPDATEID_INEXISTANT);
			this.afficherAbstractDaoException(e);

		}
				
	} // Fin de testUpdateIdInexistant().__________________________________
	

	
	/**
	 * teste la méthode <b>updateById(id correct, objet doublon)</b>.<br/>
	 * <ul>
	 * <li>garantit que updateById(id correct, objet doublon) retourne null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */	
	@SuppressWarnings(UNUSED)
	@Test
	public void testUpdateIdDoublon() throws Exception {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testUpdateIdDoublon() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_UPDATEID_DOUBLON);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTUPDATEID(DOUBLON) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final Long idObjetAModifier = 2L;
		final ISectionHit objetAModifier = this.dao.findById(idObjetAModifier);
		final ISectionHit objetModifie = objetModifieDoublon;
		ISectionHit resultat = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(ID_OBJET_A_MODIFIER + idObjetAModifier.toString());
			System.out.println(MODIFICATIONS + objetModifie.toString());
			if (objetAModifier != null) {
				System.out.println(OBJET_METIER_A_MODIFIER + objetAModifier.toString());
			} else {
				System.out.println(OBJET_METIER_A_MODIFIER_NULL);
			}			
		}
		
		
		try {

			/* ********************************************************* */
			/* **********************UPDATEID*************************** */
			resultat = this.dao.updateById(idObjetAModifier, objetModifie);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				if (resultat != null) {
					System.out.println(OBJET_MODIFIE + resultat.toString());
				} else {
					System.out.println(OBJET_MODIFIE_NULL);
				}
			}

			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que updateById(id correct, objet doublon) retourne null. */
			assertNull(
					"updateById(id correct, objet doublon) doit retourner null : "
						, resultat);
			
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTUPDATEID(DOUBLON) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que updateById(id correct, objet doublon) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
									
		} catch (AbstractDaoException e) {

			System.out.println(TEST_UPDATEID_DOUBLON);
			this.afficherAbstractDaoException(e);

		}
				
	} // Fin de testUpdateIdDoublon()._____________________________________
	

	
	/**
	 * teste la méthode <b>updateById(id correct, objet correct)</b>.<br/>
	 * <ul>
	 * <li>garantit que updateById(id correct, objet correct) 
	 * retourne l'instance modifiée.</li>
	 * <li>garantit que l'ID de l'objet modifié vaut pId.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */	
	@SuppressWarnings(UNUSED)
	@Test
	public void testUpdateId() throws Exception {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testUpdateId() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_UPDATEID);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTUPDATEID() : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		Long idObjetAModifier = null;
		if (DAO_JPA) {
			idObjetAModifier = 1L;
		} else {
			idObjetAModifier = 0L;
		}		
		final ISectionHit objetAModifier = this.dao.findById(idObjetAModifier);
		final ISectionHit objetModifie = objetModifieCorrect;
		ISectionHit resultat = null;

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(ID_OBJET_A_MODIFIER + idObjetAModifier.toString());
			System.out.println(MODIFICATIONS + objetModifie.toString());
			if (objetAModifier != null) {
				System.out.println(OBJET_METIER_A_MODIFIER + objetAModifier.toString());
			} else {
				System.out.println(OBJET_METIER_A_MODIFIER_NULL);
			}			
		}
		
		
		try {

			/* ********************************************************* */
			/* **********************UPDATEID*************************** */
			resultat = this.dao.updateById(idObjetAModifier, objetModifie);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				if (resultat != null) {
					System.out.println(OBJET_MODIFIE + resultat.toString());
				} else {
					System.out.println(OBJET_MODIFIE_NULL);
				}
			}

			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que updateById(id correct, objet correct) 
			 * retourne l'instance modifiée. */
			assertNotNull(
					"updateById(id correct, objet correct) doit "
					+ "retourner l'instance modifiée : "
						, resultat);
			
			assertEquals(
					"updateById(id correct, objet correct) doit "
					+ "retourner l'instance modifiée : "
						, objetModifie
							, resultat);
			
			/* garantit que l'ID de l'objet modifié vaut pId. */
			assertEquals(
					"ID de l'objet modifié doit valoir pId : "
						, idObjetAModifier
							, this.dao.retrieveId(resultat));
			
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTUPDATEID(DOUBLON) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que updateById(id correct, objet correct) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
									
		} catch (AbstractDaoException e) {

			System.out.println(TEST_UPDATEID);
			this.afficherAbstractDaoException(e);

		}
				
	} // Fin de testUpdateId().____________________________________________


	
	/**
	 * teste la méthode <b>delete(null)</b>.<br/>
	 * <ul>
	 * <li>garantit que delete(null) retourne false.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testDeleteNull() throws Exception {	
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testDeleteNull() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_DELETE_NULL);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________

		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTDELETE(NULL) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final ISectionHit objetADeleter = null;
		boolean resultat = false;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET_A_DELETER_NULL);
		}

		
		try {

			/* ********************************************************* */
			/* ***********************DELETE**************************** */
			resultat = this.dao.delete(objetADeleter);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(OBJET_DETRUIT + resultat);
			}

			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que delete(null) retourne false. */
			assertFalse(
					"delete(null) doit retourner false : "
						, resultat);
			
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTDELETE(NULL) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que delete(null) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
												
		} catch (AbstractDaoException e) {

			System.out.println(TEST_DELETE_NULL);
			this.afficherAbstractDaoException(e);

		}
		
	} // Fin de testDeleteNull().__________________________________________


	
	/**
	 * teste la méthode <b>delete(inexistant)</b>.<br/>
	 * <ul>
	 * <li>garantit que delete(inexistant) retourne false.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testDeleteInexistant() throws Exception {	
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testDeleteInexistant() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_DELETE_INEXISTANT);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________

		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
				
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTDELETE(INEXISTANT) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final ISectionHit objetADeleter = objetInexistant;
		boolean resultat = false;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET_A_DELETER + objetADeleter.toString());
		}

		
		try {

			/* ********************************************************* */
			/* ***********************DELETE**************************** */
			resultat = this.dao.delete(objetADeleter);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(OBJET_DETRUIT + resultat);
			}

			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que delete(inexistant) retourne false. */
			assertFalse(
					"delete(inexistant) doit retourner false : "
						, resultat);
			
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTDELETE(INEXISTANT) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que delete(inexistant) ne change rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
												
		} catch (AbstractDaoException e) {

			System.out.println(TEST_DELETE_NULL);
			this.afficherAbstractDaoException(e);

		}
						
	} // Fin de testDeleteInexistant().____________________________________


	
	/**
	 * teste la méthode <b>delete()</b>.<br/>
	 * <ul>
	 * <li>garantit que delete() retourne true.</li>
	 * <li>garantit que delete(existant) retire l'objet du stockage.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testDelete() throws Exception {	
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testDelete() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_DELETE);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________

		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
				
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTDELETE() : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final ISectionHit objetADeleter = objetRemplirStockage1;
		boolean resultat = false;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET_A_DELETER + objetADeleter.toString());
		}

		
		try {

			/* ********************************************************* */
			/* ***********************DELETE**************************** */
			resultat = this.dao.delete(objetADeleter);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(OBJET_DETRUIT + resultat);
			}

			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que delete(existant) retourne true. */
			assertTrue(
					"delete(existant) doit retourner true : "
						, resultat);
			
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTDELETE() : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que delete(existant) retire l'objet du stockage.*/
			assertEquals(
					FINDALL_DOIT_RETOURNER_3_ENREGISTREMENTS
						, Long.valueOf(3L)
							, nombreObjetsFinal);
												
			
		} catch (AbstractDaoException e) {

			System.out.println(TEST_DELETE_NULL);
			this.afficherAbstractDaoException(e);

		}
		
	} // Fin de testDelete().______________________________________________
	

	
	/**
	 * teste la méthode <b>deleteById(null)</b>.<br/>
	 * <ul>
	 * <li>garantit que deleteById(null) ne fait rien.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testDeleteByIdNull() throws Exception {	

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testDeleteByIdNull() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_DELETEBYID_NULL);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTDELETEBYID(NULL) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final Long idObjetADeleter = null;
		
		final ISectionHit objetADeleter = this.dao.findById(idObjetADeleter);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println();
			
			System.out.println(ID_OBJET_A_DELETER_NULL);
						
			if (objetADeleter != null) {
				System.out.println(OBJET_A_DELETER + objetADeleter.toString());
			} else {
				System.out.println(OBJET_A_DELETER_NULL);
			}			
		}

		
		try {

			/* ********************************************************* */
			/* *********************DELETEBYID************************** */
			this.dao.deleteById(idObjetADeleter);
			/* ********************************************************* */
			
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTDELETEBYID(NULL) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que deleteById(null) ne fait rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
															
		} catch (AbstractDaoException e) {

			System.out.println(TEST_DELETEBYID_NULL);
			this.afficherAbstractDaoException(e);

		}
		
	} // Fin de testDeleteByIdNull().______________________________________
	

	
	/**
	 * teste la méthode <b>deleteById(inexistant)</b>.<br/>
	 * <ul>
	 * <li>garantit que deleteById(inexistant) ne fait rien.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testDeleteByIdInexistant() throws Exception {	

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testDeleteByIdInexistant() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_DELETEBYID_INEXISTANT);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTDELETEBYID(INEXISTANT) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final Long idObjetADeleter = 17L;
		
		final ISectionHit objetADeleter = this.dao.findById(idObjetADeleter);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println();
						
			System.out.println(ID_OBJET_A_DELETER + idObjetADeleter.toString());
						
			if (objetADeleter != null) {
				System.out.println(OBJET_A_DELETER + objetADeleter.toString());
			} else {
				System.out.println(OBJET_A_DELETER_NULL);
			}			
		}

		
		try {

			/* ********************************************************* */
			/* *********************DELETEBYID************************** */
			this.dao.deleteById(idObjetADeleter);
			/* ********************************************************* */
			
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTDELETEBYID(INEXISTANT) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que deleteById(inexistant) ne fait rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
														
		} catch (AbstractDaoException e) {

			System.out.println(TEST_DELETEBYID_INEXISTANT);
			this.afficherAbstractDaoException(e);

		}
				
	} // Fin de testDeleteByIdInexistant().________________________________
	

	
	/**
	 * teste la méthode <b>deleteById()</b>.<br/>
	 * <ul>
	 * <li>garantit que deleteById() supprime un enregistrement.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testDeleteById() throws Exception {	

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testDeleteById() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_DELETEBYID);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTDELETEBYID() : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		Long idObjetADeleter = null;
		if (DAO_JPA) {
			idObjetADeleter = 1L;
		} else {
			idObjetADeleter = 0L;
		}
		
		final ISectionHit objetADeleter = this.dao.findById(idObjetADeleter);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println();
						
			System.out.println(ID_OBJET_A_DELETER + idObjetADeleter.toString());
						
			if (objetADeleter != null) {
				System.out.println(OBJET_A_DELETER + objetADeleter.toString());
			} else {
				System.out.println(OBJET_A_DELETER_NULL);
			}			
		}

		
		try {

			/* ********************************************************* */
			/* *********************DELETEBYID************************** */
			this.dao.deleteById(idObjetADeleter);
			/* ********************************************************* */
			
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTDELETEBYID() : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que deleteById() supprime un enregistrement. */
			assertEquals(
					FINDALL_DOIT_RETOURNER_3_ENREGISTREMENTS
						, Long.valueOf(3L)
							, nombreObjetsFinal);
															
		} catch (AbstractDaoException e) {

			System.out.println(TEST_DELETEBYID);
			this.afficherAbstractDaoException(e);

		}
						
	} // Fin de testDeleteById().__________________________________________
	

	
	/**
	 * teste la méthode <b>deleteByIdBoolean(null)</b>.<br/>
	 * <ul>
	 * <li>garantit que deleteByIdBoolean(null) 
	 * ne fait rien et retourne false.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testDeleteByIdBooleanNull() throws Exception {	

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testDeleteByIdBooleanNull() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_DELETEBYIDBOOLEAN_NULL);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTDELETEBYIDBOOLEAN(NULL) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final Long idObjetADeleter = null;
		boolean resultat = false;
		final ISectionHit objetADeleter = this.dao.findById(idObjetADeleter);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println();
			
			System.out.println(ID_OBJET_A_DELETER_NULL);
						
			if (objetADeleter != null) {
				System.out.println(OBJET_A_DELETER + objetADeleter.toString());
			} else {
				System.out.println(OBJET_A_DELETER_NULL);
			}			
		}

		
		try {

			/* ********************************************************* */
			/* ***************DELETEBYIDBOOLEAN************************* */
			resultat = this.dao.deleteByIdBoolean(idObjetADeleter);
			/* ********************************************************* */
						
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(OBJET_DETRUIT + resultat);
			}

			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que deleteByIdBoolean(null) retourne false. */
			assertFalse(
					"deleteByIdBoolean(null) doit retourner false : "
						, resultat);
			
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTDELETEBYIDBOOLEAN(NULL) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que deleteByIdBoolean(null) ne fait rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
															
		} catch (AbstractDaoException e) {

			System.out.println(TEST_DELETEBYIDBOOLEAN_NULL);
			this.afficherAbstractDaoException(e);

		}
		
	} // Fin de testDeleteByIdBooleanNull()._______________________________
	

	
	/**
	 * teste la méthode <b>deleteByIdBoolean(inexistant)</b>.<br/>
	 * <ul>
	 * <li>garantit que deleteByIdBoolean(inexistant) ne fait rien 
	 * et retourne false.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testDeleteByIdBooleanInexistant() throws Exception {	

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testDeleteByIdBooleanInexistant() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_DELETEBYIDBOOLEAN_INEXISTANT);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTDELETEBYIDBOOLEAN(INEXISTANT) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final Long idObjetADeleter = 17L;	
		final ISectionHit objetADeleter = this.dao.findById(idObjetADeleter);
		boolean resultat = false;
		

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println();
						
			System.out.println(ID_OBJET_A_DELETER + idObjetADeleter.toString());
						
			if (objetADeleter != null) {
				System.out.println(OBJET_A_DELETER + objetADeleter.toString());
			} else {
				System.out.println(OBJET_A_DELETER_NULL);
			}			
		}

		
		try {

			/* ********************************************************* */
			/* ***************DELETEBYIDBOOLEAN************************* */
			resultat = this.dao.deleteByIdBoolean(idObjetADeleter);
			/* ********************************************************* */
						
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(OBJET_DETRUIT + resultat);
			}
			
			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que deleteByIdBoolean(inexistant) retourne false. */
			assertFalse(
					"deleteByIdBoolean(inexistant) doit retourner false : "
						, resultat);

			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTDELETEBYIDBOOLEAN(INEXISTANT) : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que deleteByIdBoolean(inexistant) ne fait rien. */
			assertTrue(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_ZERO
					, nombreObjetsFinal == nombreObjetsInitial + 0);
														
		} catch (AbstractDaoException e) {

			System.out.println(TEST_DELETEBYIDBOOLEAN_INEXISTANT);
			this.afficherAbstractDaoException(e);

		}
				
	} // Fin de testDeleteByIdBooleanInexistant()._________________________
	

	
	/**
	 * teste la méthode <b>deleteByIdBoolean()</b>.<br/>
	 * <ul>
	 * <li>garantit que deleteByIdBoolean() 
	 * supprime un enregistrement et retourne true.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testDeleteByIdBoolean() throws Exception {	

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testDeleteByIdBoolean() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_DELETEBYIDBOOLEAN);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________
		
		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTDELETEBYIDBOOLEAN() : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		Long idObjetADeleter = null;
		if (DAO_JPA) {
			idObjetADeleter = 1L;
		} else {
			idObjetADeleter = 0L;
		}	
		final ISectionHit objetADeleter = this.dao.findById(idObjetADeleter);
		boolean resultat = false;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println();
						
			System.out.println(ID_OBJET_A_DELETER + idObjetADeleter.toString());
						
			if (objetADeleter != null) {
				System.out.println(OBJET_A_DELETER + objetADeleter.toString());
			} else {
				System.out.println(OBJET_A_DELETER_NULL);
			}			
		}

		
		try {

			/* ********************************************************* */
			/* ***************DELETEBYIDBOOLEAN************************* */
			resultat = this.dao.deleteByIdBoolean(idObjetADeleter);
			/* ********************************************************* */
									
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println(OBJET_DETRUIT + resultat);
			}
			
			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que deleteByIdBoolean() retourne true. */
			assertTrue(
					"deleteByIdBoolean() doit retourner true : "
						, resultat);
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTDELETEBYIDBOOLEAN() : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que deleteByIdBoolean() supprime un enregistrement. */
			assertEquals(
					FINDALL_DOIT_RETOURNER_3_ENREGISTREMENTS
						, Long.valueOf(3L)
							, nombreObjetsFinal);
															
		} catch (AbstractDaoException e) {

			System.out.println(TEST_DELETEBYIDBOOLEAN);
			this.afficherAbstractDaoException(e);

		}
						
	} // Fin de testDeleteByIdBoolean().___________________________________
	
	
	
	/**
	 * teste la méthode <b>deleteAll()</b>.<br/>
	 * <ul>
	 * <li>garantit que deleteAll() détruit 
	 * tous les enregistrements dans le stockage.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testDeleteAll() throws Exception {	
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testDeleteAll() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_DELETEALL);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________

		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTDELETEALL() : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		
		try {

			/* ********************************************************* */
			/* *********************DELETEBYID************************** */
			this.dao.deleteAll();
			/* ********************************************************* */
			
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTDELETEALL() : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que deleteAll() supprime tous les enregistrements. */
			assertEquals(
					FINDALL_DOIT_RETOURNER_0_ENREGISTREMENTS
						, Long.valueOf(0L)
							, nombreObjetsFinal);
															
		} catch (AbstractDaoException e) {

			System.out.println(TEST_DELETEALL);
			this.afficherAbstractDaoException(e);

		}
						
		
	} // Fin de testDeleteAll().___________________________________________
	
	
	
	/**
	 * teste la méthode <b>deleteAllBoolean()</b>.<br/>
	 * <ul>
	 * <li>garantit que deleteAllBoolean() détruit 
	 * tous les enregistrements dans le stockage et retourne true.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testDeleteAllBoolean() throws Exception {	
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testDeleteAllBoolean() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_DELETEALLBOOLEAN);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________

		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT TESTDELETEALLBOOLEAN() : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		boolean resultat = false;
		
		
		try {

			/* ********************************************************* */
			/* *********************DELETEBYID************************** */
			resultat = this.dao.deleteAllBoolean();
			/* ********************************************************* */
						
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LE STOCKAGE A-T-IL ETE VIDE ? : " + resultat);
			}
						
			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que deleteAllBoolean() retourne true. */
			assertTrue(
					"deleteAllBoolean() doit retourner true : "
						, resultat);
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES TESTDELETEALLBOOLEAN() : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que deleteAllBoolean() supprime tous les enregistrements. */
			assertEquals(
					FINDALL_DOIT_RETOURNER_0_ENREGISTREMENTS
						, Long.valueOf(0L)
							, nombreObjetsFinal);
															
		} catch (AbstractDaoException e) {

			System.out.println(TEST_DELETEALLBOOLEAN);
			this.afficherAbstractDaoException(e);

		}
								
	} // Fin de testDeleteAllBoolean().____________________________________
	

	
	/**
	 * teste la méthode <b>deleteIterable()</b>.<br/>
	 * <ul>
	 * <li>garantit que deleteIterable() supprime tous les 
	 * enregistrements persistés de l'iterable.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testDeleteIterable() throws Exception {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testDeleteIterable() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_DELETEITERABLE);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________

		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;
	
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT DELETEITERABLE() : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final List<ISectionHit> list1 = new ArrayList<ISectionHit>();
		list1.add(objetRemplirStockage1);
		list1.add(objetRemplirStockage2);
		list1.add(objetNull2);
		list1.add(objetInexistant);

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("LISTE D'OBJETS A DETRUIRE");
			for (final ISectionHit objet : list1) {
				System.out.println(objet.toString());
			}
		}
		
		
		try {

			/* ********************************************************* */
			/* *********************DELETEITERABLE********************* */
			this.dao.deleteIterable(list1);
			/* ********************************************************* */
			
			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES DELETEITERABLE() : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que deleteIterable() supprime tous les enregistrements 
			 * persistés de l'iterable. */
			assertEquals(
					FINDALL_DOIT_RETOURNER_2_ENREGISTREMENTS
						, Long.valueOf(2L)
							, nombreObjetsFinal);
															
		} catch (AbstractDaoException e) {

			System.out.println(TEST_DELETEITERABLE);
			this.afficherAbstractDaoException(e);

		}
								
	} // Fin de testDeleteIterable().______________________________________
	

	
	/**
	 * teste la méthode <b>deleteIterableBoolean()</b>.<br/>
	 * <ul>
	 * <li>garantit que deleteIterableBoolean() supprime tous les 
	 * enregistrements persistés de l'iterable et retourne true.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testDeleteIterableBoolean() throws Exception {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testDeleteIterableBoolean() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_DELETEITERABLEBOOLEAN);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________

		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT DELETEITERABLEBOOLEAN() : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final List<ISectionHit> list1 = new ArrayList<ISectionHit>();
		list1.add(objetRemplirStockage1);
		list1.add(objetRemplirStockage2);
		list1.add(objetNull2);
		list1.add(objetInexistant);
		boolean resultat = false;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("LISTE D'OBJETS A DETRUIRE");
			for (final ISectionHit objet : list1) {
				System.out.println(objet.toString());
			}
		}
		
		
		try {

			/* ********************************************************* */
			/* *****************DELETEITERABLEBOOLEAN******************* */
			resultat = this.dao.deleteIterableBoolean(list1);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LES OBJETS DE L'ITERABLE ONT BIEN ETE DETRUITS ? : " + resultat);
			}
			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que deleteIterableBoolean() retourne true. */
			assertTrue(
					"deleteIterableBoolean() doit retourner true : "
						, resultat);

			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES DELETEITERABLEBOOLEAN() : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que deleteIterableBoolean() supprime tous les enregistrements 
			 * persistés de l'iterable. */
			assertEquals(
					FINDALL_DOIT_RETOURNER_2_ENREGISTREMENTS
						, Long.valueOf(2L)
							, nombreObjetsFinal);
															
		} catch (AbstractDaoException e) {

			System.out.println(TEST_DELETEITERABLEBOOLEAN);
			this.afficherAbstractDaoException(e);

		}
								
	} // Fin de testDeleteIterableBoolean()._______________________________
	

	
	/**
	 * teste la méthode <b>exists(objet)</b>.<br/>
	 * <ul>
	 * <li>garantit que exists(existant) retourne true.</li>
	 * <li>garantit que exists(inexistant) retourne false.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testExists() throws Exception {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testExists() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_EXISTS);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________

		/* vide et remplit le stockage. */
		this.remplirStockage(false);


		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		boolean resultatExistant = false;
		boolean resultatInexistant = false;
		
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT EXISTS() : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		final ISectionHit objetExistantTest = objetRemplirStockage1;
		final ISectionHit objetInexistantTest = objetInexistant;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("OBJETS DE TEST : ");
			System.out.println("objetExistantTest : " 
					+ objetExistantTest.toString());
			System.out.println("objetInexistantTest : " 
					+ objetInexistantTest.toString());
		}
		
		try {

			/* ********************************************************* */
			/* ************************EXISTS*************************** */
			resultatExistant = this.dao.exists(objetExistantTest);
			resultatInexistant = this.dao.exists(objetInexistantTest);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("objetExistantTest EXISTE ? : " + resultatExistant);
				System.out.println("objetInexistantTest EXISTE ? : " + resultatInexistant);
			}
	
			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que exists(existant) retourne true. */
			assertTrue(
					"exists(existant) doit retourner true : "
						, resultatExistant);
			/* garantit que exists(inexistant) retourne false. */
			assertFalse(
					"exists(inexistant) doit retourner false : "
						, resultatInexistant);

			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES EXISTS() : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que exists() ne touche à rien. */
			assertEquals(
					FINDALL_DOIT_RETOURNER_4_ENREGISTREMENTS
						, Long.valueOf(4L)
							, nombreObjetsFinal);
															
		} catch (AbstractDaoException e) {

			System.out.println(TEST_EXISTS);
			this.afficherAbstractDaoException(e);

		}

	} // Fin de testExists().______________________________________________
	

	
	/**
	 * teste la méthode <b>existsId(id)</b>.<br/>
	 * <ul>
	 * <li>garantit que existsId(existant) retourne true.</li>
	 * <li>garantit que existsId(inexistant) retourne false.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testExistsId() throws Exception {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testExistsId() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_EXISTS_ID);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________

		/* vide et remplit le stockage. */
		this.remplirStockage(false);

		Long nombreObjetsInitial = 0L;
		Long nombreObjetsFinal = 0L;

		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS AVANT EXISTS_ID() : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		

		/* ************************* */
		// CONDITIONS DE TEST
		/* ************************* */
		Long objetExistantTestId = null;
		if (DAO_JPA) {
			objetExistantTestId = 1L;
		} else {
			objetExistantTestId = 0L;
		}
		final Long objetInexistantTestId = 17L;
		boolean resultatExistant = false;
		boolean resultatInexistant = false;
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("ID DES OBJETS DE TEST : ");
			System.out.println("objetExistantTestId : " 
					+ objetExistantTestId.toString());
			System.out.println("objetInexistantTestId : " 
					+ objetInexistantTestId.toString());
		}
		
		try {

			/* ********************************************************* */
			/* ************************EXISTS_ID*************************** */
			resultatExistant = this.dao.existsId(objetExistantTestId);
			resultatInexistant = this.dao.existsId(objetInexistantTestId);
			/* ********************************************************* */
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("objetExistantTest EXISTE ? : " + resultatExistant);
				System.out.println("objetInexistantTest EXISTE ? : " + resultatInexistant);
			}
	
			
			/* *********** */
			// ASSERTIONS
			/* *********** */
			/* garantit que existsId(existant) retourne true. */
			assertTrue(
					"existsId(existant) doit retourner true : "
						, resultatExistant);
			/* garantit que existsId(inexistant) retourne false. */
			assertFalse(
					"existsId(inexistant) doit retourner false : "
						, resultatInexistant);

			
			// ETAT FINAL
			/* récupération. */
			final List<ISectionHit> objetsFinaux = this.dao.findAll();

			/* Calcul du nombre d'objets dans le stockage après le traitement. */
			nombreObjetsFinal = this.dao.count();

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("LISTE D'OBJETS APRES EXISTS_ID() : ");
				System.out.println(this.dao.afficherListeObjetsMetier(objetsFinaux));
				this.afficherNbreObjetsFinal(nombreObjetsFinal);
			}

			/* garantit que exists() ne touche à rien. */
			assertEquals(
					FINDALL_DOIT_RETOURNER_4_ENREGISTREMENTS
						, Long.valueOf(4L)
							, nombreObjetsFinal);
															
		} catch (AbstractDaoException e) {

			System.out.println(TEST_EXISTS_ID);
			this.afficherAbstractDaoException(e);

		}

	} // Fin de testExistsId().______________________________________________
	

	
	/**
	 * teste la méthode <b>count()</b>.<br/>
	 * <ul>
	 * <li>garantit que count() retourne 
	 * le bon nombre d'enregistrements.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCount() throws Exception {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode testCount() ********** ");
		}

		/* this.dao NON INJECTE. */
		if (this.dao == null) {

			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(TEST_COUNT);
				this.afficherDAONonInstancie();
			}

			return;

		} // Fin de this.dao NON INJECTE._____________________

		/* vide et remplit le stockage. */
		this.remplirStockage(false);
		
		Long nombreObjetsInitial = 0L;
		
		// ETAT INITIAL
		/* récupération. */
		final List<ISectionHit> objetInitiaux = this.dao.findAll();

		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsInitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LISTE D'OBJETS DANS LE STOCKAGE : ");
			System.out.println(this.dao.afficherListeObjetsMetier(objetInitiaux));
			this.afficherNbreObjetsInitial(nombreObjetsInitial);
		}
		
		/* garantit que count() retourne le bon nombre d'enregistrements. */
		assertEquals(
				FINDALL_DOIT_RETOURNER_4_ENREGISTREMENTS
					, Long.valueOf(4L)
						, nombreObjetsInitial);
		
	} // Fin de testCount()._______________________________________________
	
	
	
	/**
	 * <b>Vide d'abord puis Remplit le STOCKAGE 
	 * avec 4 enregistrements</b>.<br/>
	 * <ul>
	 * <li>annule l'entityManagerFactory pour forcer 
	 * la re-création des tables à chaque appel.</li>
	 * </ul>
	 * 
	 * @param pAffichage : boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	private void remplirStockage(
			final boolean pAffichage) throws Exception {
			
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = pAffichage;
		// **********************************
	
		/* annule l'entityManagerFactory pour forcer 
		 * la re-création des tables à chaque appel. */
//		JPAUtils.annulerEntityManagerFactory();
		
		
		Long nombreObjetsinitial = 0L;
		Long nombreObjetsFinal = 0L;
		
		/* vide le stockage*/
		this.viderStockage();
		
		/* Constitution d'un lot d'objets. */
		final List<ISectionHit> lot 
			= new ArrayList<ISectionHit>();
		
		lot.add(objetRemplirStockage1);
		lot.add(objetRemplirStockage2);
		lot.add(objetRemplirStockage3);
		lot.add(objetRemplirStockage4);
		
		List<ISectionHit> lotPersistant = null;
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitDAOJPASpringTest - méthode remplirStockage(boolean) ********** ");
		}
		
		// ETAT INITIAL
		/* Compte du nombre d'Objets initialement dans le stockage. */
		nombreObjetsinitial = this.dao.count();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && pAffichage) {
			System.out.println(NBRE_OBJET_INITIAL + nombreObjetsinitial);
			System.out.println();
		}

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && pAffichage) {
			System.out.println();
			System.out.println("OBJETS METIER A STOCKER : ");
			for (final ISectionHit objet : lot) {
				System.out.println(objet.toString());
			}
		}
		
		
		try {
			
			/* *********************************************** */
			/* ********************* CREATION **************** */
			lotPersistant = (List<ISectionHit>) this.dao.saveIterable(lot);
			/* *********************************************** */
			
			nombreObjetsFinal = this.dao.count();
			
			/* garantit que save(Lot pObjects) 
			 * insère des objets dans le stockage.*/
			assertEquals(NBRE_OBJETS_FINAL_DOIT
					+ NBRE_INITIAL_PLUS_QUATRE
					, Long.valueOf(nombreObjetsinitial + 4)
						, nombreObjetsFinal);
			
		}
		catch (AbstractDaoException e) {
			this.afficherAbstractDaoException(e);
		}

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && pAffichage) {
			System.out.println();
			System.out.println(TIRETS);
			System.out.println("remplirTable(boolean)");
			System.out.println("NOMBRE D'OBJETS PERSISTES APRES save(Lot) : " + nombreObjetsFinal);
			System.out.println("LOT D'ENREGISTREMENTS dans le stockage APRES remplirTable(boolean) : ");
			System.out.println(this.dao.afficherListeObjetsMetier(lotPersistant));
			System.out.println(TIRETS);
			System.out.println();

		}

	} // Fin de remplirStockage(...).______________________________________
	

	
	/**
	 * <b>Vide le stockage</b>.<br/>
	 *
	 * @throws Exception
	 */
	private void viderStockage() throws Exception {
		
		this.dao.deleteAll();
		
		final Long nbreObjetsFinal = this.dao.count();
		
		assertEquals("Le stockage doit être vide : "
				, Long.valueOf(0L), nbreObjetsFinal);
		
	} // Fin de viderStockage().___________________________________________
	

	
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
	* <li>instancie le <code><b>contexteSpring</b></code> 
	* STATIC la première fois.</li>
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
		
//		System.out.println();
//		System.out.println("****************INJECTION DU CONTEXTE DANS LE SETTER setContextInjectable(...)**************");
//		System.out.println(pContextInjectable);
//		System.out.println();
		
		this.contextInjectable = pContextInjectable;
		
		/* instancie le contexteSpring STATIC la première fois. */
		if (contexteSpring == null || !contexteSpring.isActive()) {
			contexteSpring = this.contextInjectable;
		}
		
	} // Fin de setContextInjectable(...)._________________________________



	/**
	 * <ul>
	 * Affiche à la console :
	 * <li>Le nombre d'objets pNbreObjetsInitial initialement 
	 * dans le stockage avant le test.</li>
	 * </ul>
	 * <br/>
	 *
	 * @param pNbreObjetsInitial : Long : 
	 * Nombre d'objets initialement dans le stockage.<br/>
	 */
	private void afficherNbreObjetsInitial(
						final Long pNbreObjetsInitial) {
		
		System.out.println();
		System.out.println(TIRETS);
		System.out.println("nombre d'objets initialement dans le stockage = " 
				+ pNbreObjetsInitial);
		System.out.println(TIRETS);
		System.out.println();

	} // Fin de afficherNbreObjetsInitial(...).____________________________
	

	
	/**
	 * <ul>
	 * Affiche à la console :
	 * <li>Le nombre d'objets pNbreObjetsFinal finalement 
	 * dans le stockage après le test.</li>
	 * </ul>
	 * <br/>
	 *
	 * @param pNbreObjetsFinal : Long : 
	 * Nombre d'objets finalement dans le stockage.<br/>
	 */
	private void afficherNbreObjetsFinal(
						final Long pNbreObjetsFinal) {
		
		System.out.println();
		System.out.println(TIRETS);
		System.out.println("nombre d'objets finalement dans le stockage = " 
				+ pNbreObjetsFinal);
		System.out.println(TIRETS);
		System.out.println();

	} // Fin de afficherNbreObjetsFinal(...).______________________________
	
	
	
	/**
	 * <ul>
	 * Affiche à la console :
	 * <li>l'objet pObjetPersistant persisté dans le stockage.</li>
	 * <li>Le nombre d'objets pNbreObjetsFinal finalement 
	 * dans le stockage après le test.</li>
	 * </ul>
	 *
	 * @param pObjetPersistant : ISectionHit : 
	 * Objet persistant dans le stockage.<br/>
	 * @param pNbreObjetsFinal : Long : 
	 * Nombre d'objets finalement dans le stockage.<br/>
	 */
	private void afficherObjetPersistant(
			final ISectionHit pObjetPersistant
				, final Long pNbreObjetsFinal) {
		
		if (pObjetPersistant != null) {
			
			System.out.println();
			System.out.println(TIRETS);
			System.out.println("objet persistant : " 
					+ pObjetPersistant.toString());
			System.out.println("nombre d'objets finalement dans le stockage = " 
					+ pNbreObjetsFinal);
			System.out.println(TIRETS);
			System.out.println();
			
		} else {
			
			System.out.println();
			System.out.println(TIRETS);
			System.out.println("objetPersistant est NULL");
			System.out.println("nombre d'objets finalement dans le stockage = " 
					+ pNbreObjetsFinal);
			System.out.println(TIRETS);
			System.out.println();
			
		}
		
	} // Fin de afficherObjetPersistant(...).______________________________
	
	

	/**
	 * method afficherAbstractDaoException(
	 * AbstractDaoException pE) :<br/>
	 * <ul>
	 * Affiche à la console :
	 * <li>le message Utilisateur d'une AbstractDaoException pE.</li>
	 * <li>le message Technique d'une AbstractDaoException pE.</li>
	 * </ul>
	 * <br/>
	 *
	 * @param pE : AbstractDaoException.<br/>
	 */
	private void afficherAbstractDaoException(
			final AbstractDaoException pE) {
		
		System.out.println();
		System.out.println(TIRETS);
		System.out.println("MESSAGE UTILISATEUR : " 
				+ pE.getMessageUtilisateur());
		System.out.println();
		System.out.println("MESSAGE TECHNIQUE : " 
				+ pE.getMessageTechnique());
		System.out.println(TIRETS);
		System.out.println();

	} // Fin de afficherAbstractDaoException(...)._________________________
	

			
	/**
	 * Affiche à la console de
	 * <b>"DAO NON INSTANCIE - this.dao est NULL"</b>.<br/>
	 * <br/>
	 */
	private void afficherDAONonInstancie() {
		
		System.out.println();
		System.out.println(TIRETS);
		System.out.println("DAO NON INSTANCIE "
				+ "- this.dao est NULL");
		System.out.println(TIRETS);
		System.out.println();
		
	} // Fin de afficherDAONonInstancie()._________________________________


	
	/**
	 * <b>fournit une String pour l'affichage à la console 
	 * d'une Liste d'OBJETS METIER</b>.<br/>
	 * <br/>
	 * retourne null si pList == null.<br/>
	 *
	 * @param pList : List&lt;ISectionHit&gt;.<br/>
	 * 
	 * @return : String.<br/>
	 */
	private String afficherListeObjetsMetier(
			final List<ISectionHit> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		for (final ISectionHit objet : pList) {
			
			stb.append(objet.toString());
			stb.append(SAUT_LIGNE_PLATEFORME);
			
		}
		
		return stb.toString();
		
	} // Fin de afficherListeContactSimple(...).________________________________

	
	
	
	/**
	 * <b>Instancie et retourne une LocalDate à partir d'une String 
	 * sous la forme "dd/MM/yyyy"</b>.<br/>
	 * <ul>
	 * <li>Par exemple, <code>fournirLocalDate("05/01/1976")</code> 
	 * retourne une LocalDate située le 05 janvier 1976.</li>
	 * <li>utilise <code>dateFormatterSaisie.<b>parse</b>(pString
	 * , LocalDate::from);</code></li>
	 * <li>vérifie que pString est au format "23/07/1972" 
	 * (format REGEX "^\\d{2}/\\d{2}/\\d{4}").<br/>
	 * retourne null si ce n'est pas le cas.</li>
	 * </ul>
	 * - retourne null si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String : 
	 * String au format "05/01/1976" ou "19/02/1961".<br/>
	 * 
	 * @return : LocalDate.<br/>
	 */
	private static LocalDate fournirLocalDate(
			final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		final DateTimeFormatter dateFormatterSaisie 
			= DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
		/* motif REGEX d'une date sous la forme 05/01/1976. */
		final String motifRegex = "^\\d{2}/\\d{2}/\\d{4}";
		final Pattern pattern = Pattern.compile(motifRegex);
		final Matcher matcher = pattern.matcher(pString);
		
		LocalDate resultat = null;
		
		/* vérifie que pString est au format "23/07/1972" 
		 * (format REGEX "^\\d{2}/\\d{2}/\\d{4}"). */
		if (matcher.matches()) {
			
			/* instancie une LocalDate à partir d'une String 
			 * sous la forme 05/01/1976. */
			resultat = dateFormatterSaisie.parse(pString, LocalDate::from);
						
			return resultat;
		}

		return null;
		
	} // Fin de fournirLocalDate(...)._____________________________________
	

	
	/**
	 * Exécuté avant tout test de la méthode.<br/>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		
		System.setProperty("%log4j.skipJansi", "false");
		
		final Path fichierDonneesPath 
			= PATH_ABSOLU_TEST_JEUX_ESSAI.resolve("HITDIRA2017.txt");
		final File fichierDonnees = fichierDonneesPath.toFile();
		final Charset charsetAnsi = Charset.forName("Windows-1252");
		
		// OBJET A TESTER.
		final ImporteurHit importeurHIT = new ImporteurHit();
		
		final Map<Integer, ISectionHit> fichierMapObjet 
			= importeurHIT.importerObjet(fichierDonnees, charsetAnsi);
		
		final SortedMap<Integer, SortedMap<Integer, String>> fichierImporteMap 
			= importeurHIT.getFichierImporteMap();
		
		objetRemplirStockage1 = fichierMapObjet.get(1);
		objetRemplirStockage2 = new SectionHit(fichierImporteMap.get(2));
		objetRemplirStockage3 = new SectionHit(fichierImporteMap.get(3));
		objetRemplirStockage4 = new SectionHit(fichierImporteMap.get(4));
		
		objetACreer1 = new SectionHit(fichierImporteMap.get(15));
		objetACreer2 = new SectionHit(fichierImporteMap.get(17));
		
		objet1 = new SectionHit(fichierImporteMap.get(50));
		objet1MemeInstance = objet1;
		objet2EqualsObj1 = new SectionHit(fichierImporteMap.get(50));
		objet3EqualsObj1 = new SectionHit(fichierImporteMap.get(50));
		
		objetNull1 = new SectionHit();
		objetNull2 = new SectionHit();
		
		objetInexistant = new SectionHit(fichierImporteMap.get(100));
		
		objetModifieCorrect = new SectionHit(fichierImporteMap.get(110));
		
		objetModifieDoublon = new SectionHit(fichierImporteMap.get(1));
				
	} // Fin de beforeClass()._____________________________________________
	
	
	
	/**
	 * <ul>
	 * <li>instructions exécutées <b>avant l'ensemble des tests</b> 
	 * de la classe JUnit.</li>
	 * <li>exécuté <i>avant</i> le Constructeur de la classe.</li>
	 * <li><b>A REMPLIR A LA MAIN</b></li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
public static void avantTests() throws Exception {
		
		/**/
//		System.out.println();
//		System.out.println("AVANT TOUS LES TESTS (AVANT CLASSE)");
//		System.out.println("CONTEXTE SPRING : " + contexteSpring);
		
	} // Fin de avantTests().______________________________________________


	
	/**
	 * <ul>
	 * <li>instructions exécutées <b>avant CHAQUE test</b> 
	 * de la classe JUnit.</li>
	 * <li><b>A REMPLIR A LA MAIN</b></li>
	 * </ul>
	 * 
	 * @throws Exception
	 */
	@Before
	public void avantChaqueTest() throws Exception {
		
		/**/
		
//		System.out.println();		
//		System.out.println("AVANT CHAQUE TEST");
//		System.out.println("CONTEXTE SPRING : " + contexteSpring);
//		System.out.println();
		
	} // Fin de avantChaqueTest()._________________________________________
	
	

	/**
	 * <ul>
	 * <li>instructions exécutées <b>APRES l'ensemble des tests</b> 
	 * de la classe JUnit.</li>
	 * <li>executé lors de la fermeture de la classe.</li>
	 * <li><b>A REMPLIR A LA MAIN</b></li>
	 * <li>clôt le contexte Spring déclaré par Annotations.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@AfterClass
	public static void apresTests() throws Exception {
		
		if (contexteSpring != null) {
			
			System.out.println();
			System.out.println("*************** apresTest() DANS LE TEST JUNIT ****************************");
			System.out.println("*****************CLOTURE DU CONTEXTE SPRING ******************************************************");
			
			/* clôt le contexte Spring déclaré par Annotations. */
			contexteSpring.close();
			
		}
		
	} // Fin de apresTests().______________________________________________
	
	
	
	/**
	 * <b>affiche les propriétés lues par le EMFactory</b>.<br/>
	 * <br/>
	 * - ne fait rien si pEntityManagerFactory == null.<br/>
	 * <br/>
	 *
	 * @param pEntityManagerFactory : EntityManagerFactory.<br/>
	 */
	private static void afficherEMFactory(
			final EntityManagerFactory pEntityManagerFactory) {
		
		/* ne fait rien si pEntityManagerFactory == null. */
		if (pEntityManagerFactory == null) {
			return;
		}
				
//		final String propsDansEntityManagerFactory 
//		= AfficheurContexteSpring
//			.afficherPrincipalesProperties(pEntityManagerFactory);
		
		final String propsDansEntityManagerFactory 
		= AfficheurContexteSpring
			.afficherProperties(pEntityManagerFactory);
	
		System.out.println(propsDansEntityManagerFactory);
	
	} // Fin de afficherEMFactory(...).____________________________________
	
	
	
	/**
	 * <b>affiche les propriétés lues par le EMFactory</b>.<br/>
	 * <b>affiche les beans contenus dans le contexte SPRING</b>.<br/>
	 */
	private static void afficherContexte() {
		
		String[] beansTableau = null;
		
		if (contexteSpring != null) {
			
			EntityManagerFactory entityManagerFactory = null;
			
			try {
				
				entityManagerFactory 
					= contexteSpring.getBean(
							"entityManagerFactory"
							, EntityManagerFactory.class);
				
			} catch (BeansException e) {
				e.printStackTrace();
			}
			
			if (entityManagerFactory != null) {
				
				System.out.println();
				System.out.println("*********** DANS CLASSE DE TEST - afficherContexte() *****************************");
				System.out.println("Proprietes du Bean EntityManagerFactory : " + entityManagerFactory.getClass());
				System.out.println();
				System.out.println("DANS CLASSE DE TEST - afficherContexte() - PROPRIETES DANS LE BEAN EntityManagerFactory : ");
				System.out.println();
				/* affiche les propriétés lues par le EMFactory. */
				afficherEMFactory(entityManagerFactory);
			}
			
			
			beansTableau = contexteSpring.getBeanDefinitionNames();
			
		} else {
			
			final String message 
				= CLASSE_SECTIONHIT_DAOJPASPRING_TEST 
					+ " - METHODE avantTests() - " 
						+ "LE CONTEXTE N'A PU ETRE INSTANCIE";
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		if (beansTableau != null) {
			
			System.out.println();
			System.out.println("*********** DANS CLASSE DE TEST - afficherContexte() *****************************");
			System.out.println("BEANS CONTENUS DANS le CONTEXTE SPRING (contexteSpring.getBeanDefinitionNames()) : ");
			
			for (int i = 0; i < beansTableau.length; i++) {
				System.out.println(beansTableau[i]);
			}
			
			System.out.println();
			
		}
		
	} // Fin de afficherContexte().________________________________________
	
	
		
} // FIN DE LA CLASSE SectionHitDAOJPASpringTest.----------------------------
