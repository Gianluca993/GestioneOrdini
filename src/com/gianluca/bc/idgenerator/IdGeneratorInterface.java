package com.gianluca.bc.idgenerator;

import java.io.IOException;

import com.gianluca.architecture.dao.DAOException;

public interface IdGeneratorInterface {

	long nextId() throws ClassNotFoundException, IOException, DAOException;
	
}
