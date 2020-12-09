package test.com.gianluca.architecture.dao;

import static org.junit.jupiter.api.Assertions.*;

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
import com.gianluca.architecture.dao.ImmagineDAO;
import com.gianluca.bc.model.Articolo;
import com.gianluca.bc.model.Immagine;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ImmagineDAOTest {
	private static Connection conn;
	private static Immagine img;
	private static Articolo a;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		conn = DBAccess.getConnection();
		
		a = new Articolo();
		a.setIdArticolo(6);
		a.setMarca("Marchetta");
		a.setModello("Marcotto X Pro");
		a.setPrezzo(9999);
		
		img = new Immagine();
		img.setIdImg(6);
		img.setUrl("./img/img6.jpg");
		img.setDescrizione("Un meraviglioso Marcotto X Pro con processore Pallocchia M27 e 16GB di RAM");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		ArticoloDAO.getFactory().delete(conn, 6L);
		img = null;
		a = null;
		DBAccess.closeConnection();
		System.out.println("Pulizia effettuata");
	}

	@Test
	@Order(1)
	void testCreate() {
		try {
			ArticoloDAO.getFactory().create(conn, a);
			ImmagineDAO.getFactory().create(conn, img);
			System.out.println("Inserimento immagine riuscito");
		} catch (DAOException e) {
			e.printStackTrace();
			fail("Inserimento immagine non riuscito");
		}
	}
	
	@Test
	void testUpdate() {
		try {
			img = new Immagine();
			img.setIdImg(6);
			img.setUrl("./img/imgMod6.jpg");
			img.setDescrizione("[IMG MODIFICATA]Un meraviglioso Marcotto X Pro con processore Pallocchia M27 e 16GB di RAM");
			ImmagineDAO.getFactory().update(conn, img);
			System.out.println("Aggiornamento immagine riuscito");
		} catch (DAOException e) {
			e.printStackTrace();
			fail("Aggiornamento immagine non riuscito");
		}
	}
	
	@Test
	void testGetAll() {
		try {
			Immagine[] immagini = ImmagineDAO.getFactory().getAll(conn);
			assertNotNull(immagini);
			System.out.println("Recupero immagini effettuato");
		} catch (DAOException e) {
			e.printStackTrace();
			fail("Recupero immagini fallito");
		}
	}

	@Test
	void testGetById() {
		try {
			Immagine img = ImmagineDAO.getFactory().getById(conn, 6L);
			assertNotNull(img);
		} catch (DAOException e) {
			e.printStackTrace();
			fail("Recupero immagine fallito");
		}
	}
}
