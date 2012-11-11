package com.foo.service;

import com.foo.model.TestModel;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBException;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ejb.embeddable.EJBContainer;

public class TestModelServiceTest  {

	Logger logger = LoggerFactory.getLogger(TestModelServiceTest.class);

	static private TestModelService testService = null;

	private static EJBContainer container;

	public TestModelServiceTest() throws Exception {

	}

	@BeforeClass
	static public void setup()
		throws IOException, JAXBException
	{
		// domain.xml from http://embedded-glassfish.java.net/domain.xml
		try {
			Map<String, Object> properties = new HashMap<String, Object>();

			properties.put(EJBContainer.MODULES, new File("./build/libs/container-test-embedded-glassfish-tests.jar"));
			
			container = EJBContainer.createEJBContainer(properties);

			// Get a handle to the service through the context
			testService = (TestModelService)container.getContext().lookup("java:module/TestModelService");
		} catch(Exception e) {
			fail(e.toString());
		}
	}

	@AfterClass
	static public void teardown()
		throws IOException
	{
		if(container != null) {
			container.close();		
		}
	}

	@Test
	public void testValidUser() throws Exception {

		// TestModel a valid basic user
		{
			try {
				TestModel test = new TestModel();

				test.setTest("foo");

				testService.addTest(test);

			} catch(Exception e) {
				fail(e.toString());
			}
		}

	}	
}