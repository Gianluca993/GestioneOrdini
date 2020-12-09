package test.com.gianluca.architecture.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gianluca.architecture.dao.DAOException;
import com.gianluca.architecture.dao.DBAccess;
import com.gianluca.architecture.dao.UtenteDAO;
import com.gianluca.bc.model.Utente;
import com.gianluca.bc.security.Algoritmo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UtenteDAOTest {
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
	}

	@AfterAll
	static void tearDown() throws Exception {
		UtenteDAO.getFactory().delete(conn, "MarioRos");
		System.out.println("Pulizia effettuata");
		utente = null;
		DBAccess.closeConnection();
	}

	@Test
	@Order(1)
	void testCreate() {
		try {
			UtenteDAO.getFactory().create(conn, utente);
		} catch (DAOException e) {
			e.printStackTrace();
			fail("\n" + e.getMessage());
		}
	}

	@Test
	void testUpdate() {
		utente = new Utente();
		utente.setNome("Paolo");
		utente.setCognome("Rossi");
		utente.setIndirizzo("Via vialetto, 14");
		utente.setCap("00100");
		utente.setNascita(new GregorianCalendar(1993, 3, 29).getTime());
		utente.setUsername("MarioRos");
		utente.setPassword(Algoritmo.generaMD5("SuperPass01"));
		utente.setEmail("paolo.rossi@mail.it");
		try {
			UtenteDAO.getFactory().update(conn, utente);
			System.out.println("Utente Aggiornato");
		} catch (DAOException e) {
			e.printStackTrace();
			fail("\n" + e.getMessage());
		}
	}

	@Test
	void testGetAll() {
		try {
			Utente[] utenti = UtenteDAO.getFactory().getAll(conn);
			assertNotNull(utenti);
			System.out.println("Utenti recuperati");
		} catch (DAOException e) {
			e.printStackTrace();
			fail("Recupero utenti fallito");
		}
	}
	
	@Test
	void testGetByUsername() {
		try {
			Utente utente = UtenteDAO.getFactory().getByUsername(conn, "MarioRos");
			assertNotNull(utente);
			System.out.println("Utente recuperato");
		} catch (DAOException e) {
			e.printStackTrace();
			fail("Recupero utente fallito");
		}
	}
}
