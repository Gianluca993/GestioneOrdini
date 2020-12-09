package test.com.gianluca.architecture.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gianluca.architecture.dao.ArticoloDAO;
import com.gianluca.architecture.dao.DAOException;
import com.gianluca.architecture.dao.DBAccess;
import com.gianluca.bc.model.Articolo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArticoloDAOTest {
	private static Articolo articolo;
	private static Connection conn;

	@BeforeAll
	static void setUp() throws Exception {
		conn = DBAccess.getConnection();
		System.out.println("Connessione ok");
		articolo = new Articolo();
		articolo.setIdArticolo(6);
		articolo.setMarca("Marchetta");
		articolo.setModello("Marcotto");
		articolo.setPrezzo(500);
	}

	@AfterAll
	static void tearDown() throws Exception {
		try {
			ArticoloDAO.getFactory().delete(conn, 6L);
			articolo = null;
			conn.commit();
			System.out.println("Pulizia effettuata");
		} catch (DAOException e) {
			e.printStackTrace();
			fail("Pulizia articolo fallita");
		}
	}

	@Test
	@Order(1)
	void testCreate() {
		try {
			ArticoloDAO.getFactory().create(conn, articolo);
			System.out.println("Articolo registrato");
		} catch (DAOException e) {
			e.printStackTrace();
			fail("Creazione articolo fallita");
		}
	}

	@Test
	void testUpdate() {
		try {
			articolo = new Articolo();
			articolo.setIdArticolo(6);
			articolo.setMarca("Marchiotta");
			articolo.setModello("Pallotto");
			articolo.setPrezzo(980);
			ArticoloDAO.getFactory().update(conn, articolo);
			System.out.println("Aggiornamento eseguito");
			Articolo articolo = ArticoloDAO.getFactory().getById(conn, 6L);
			System.out.println("Test getArticolo: " + articolo.toString());
		} catch (DAOException e) {
			e.printStackTrace();
			fail("Update articolo fallita");
		}
	}
	
	@Test
	void testGetAll() {
		try {
			Articolo[] articoli = ArticoloDAO.getFactory().getAll(conn);
			assertNotNull(articoli);
			System.out.println("Articoli recuperati");
		} catch (DAOException e) {
			e.printStackTrace();
			fail("Recupero articoli fallito");
		}
	}
	
	@Test
	void testGetById() {
		try {
			Articolo articolo = ArticoloDAO.getFactory().getById(conn, 6L);
			assertNotNull(articolo);
			System.out.println("Articolo recuperato");
		} catch (DAOException e) {
			e.printStackTrace();
			fail("Recupero articolo fallito");
		}
	}
}
