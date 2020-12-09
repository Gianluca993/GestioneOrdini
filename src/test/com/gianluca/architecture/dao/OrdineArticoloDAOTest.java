package test.com.gianluca.architecture.dao;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gianluca.architecture.dao.DAOException;
import com.gianluca.architecture.dao.DBAccess;
import com.gianluca.architecture.dao.OrdineArticoloDAO;
import com.gianluca.architecture.dao.OrdineDAO;
import com.gianluca.architecture.dao.UtenteDAO;
import com.gianluca.bc.model.Ordine;
import com.gianluca.bc.model.OrdineArticolo;
import com.gianluca.bc.model.Utente;
import com.gianluca.bc.security.Algoritmo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrdineArticoloDAOTest {
	private static OrdineArticolo ordArt;
	private static Ordine ordine;
	private static Utente utente;
	private static Connection conn;
	
	@BeforeAll
	static void setUp() throws Exception {
		conn = DBAccess.getConnection();

		utente = new Utente();
		utente.setNome("Mario");
		utente.setCognome("Rossi");
		utente.setIndirizzo("Via viale, 14");
		utente.setCap("00100");
		utente.setNascita(new GregorianCalendar(1993, 3, 29).getTime());
		utente.setUsername("MarioRos");
		utente.setPassword(Algoritmo.generaMD5("SuperPassword01"));
		utente.setEmail("mario.rossi@mail.it");
		
		ordine = new Ordine();
		ordine.setIdOrdine(1);
		ordine.setTotale(1000.00);
		ordine.setData(new Date());
		ordine.setUsername("MarioRos");
		
		ordArt = new OrdineArticolo();
		ordArt.setIdOrdine(1);
		ordArt.setIdArticolo(1);
		ordArt.setQuantita(1);
	}

	@AfterAll
	static void tearDown() throws Exception {
		try {
			OrdineDAO.getFactory().delete(conn, ordine.getIdOrdine());
			UtenteDAO.getFactory().delete(conn, utente.getUsername());
			ordine = null;
			utente = null;
			ordArt = null;
			conn.close();
			System.out.println("Pulizia effettuata");
		} catch (DAOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Order(1)
	void testCreate() {
		try {
			UtenteDAO.getFactory().create(conn, utente);
			OrdineDAO.getFactory().create(conn, ordine);
			OrdineArticoloDAO.getFactory().create(conn, ordArt);
			System.out.println("Ordine articolo creato");
		} catch (DAOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	void testGetById() {
		try {
			OrdineArticoloDAO.getFactory().getById(conn, ordArt.getIdArticolo());
		} catch (DAOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
