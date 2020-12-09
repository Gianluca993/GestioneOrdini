package test.com.gianluca.bc.idgenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.gianluca.architecture.dao.DAOException;
import com.gianluca.bc.idgenerator.OrdineIdGenerator;

class OrdineIdGeneratorTest {

	@Test
	void testNextId() {
		try {
			OrdineIdGenerator idGen = OrdineIdGenerator.getInstance();
			assertNotNull(idGen, "Istanza creata correttamente");
			long valore = idGen.nextId();
			assertEquals(valore, idGen.nextId() - 1, "Test sequenza");
		} catch (DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
