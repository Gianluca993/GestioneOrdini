package test.com.gianluca.architecture.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.gianluca.architecture.dao.DAOException;
import com.gianluca.architecture.dao.DBAccess;

class DBAccessTest {

	@Test
	void testConnection() {
		try {
			DBAccess.getConnection();
			System.out.println("Connessione stabilita!");
		} catch(DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			fail("\n" + e.getMessage());
		} finally {
			try {
				DBAccess.closeConnection();
				System.out.println("Connessione chiusa!");
			} catch (DAOException e) {
				e.printStackTrace();
				fail("Errore nel tentativo di chiusura connessione");
			}
		}
	}

}
