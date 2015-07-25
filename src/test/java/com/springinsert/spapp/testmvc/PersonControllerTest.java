package com.springinsert.spapp.testmvc;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

import com.springinsert.spapp.PersonController;
import com.springinsert.spapp.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:dispatcher-servlet.xml"})
public class PersonControllerTest extends TestCase {

  @Mock
  private PersonService personService; 
  @Mock
  private ModelMap model;
  
  @InjectMocks
  private PersonController personController;

  private MockMvc mockMvc;
  
  @Before
  public void setup() {
      MockitoAnnotations.initMocks(this);
      mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
  }
  
  @Test
  public void testGetPersonList() throws Exception { 

	  ModelMap modelTest = model.addAttribute("personList", personService.listPerson());
	  
	  //modelTest is Null?
	  
	  assertNull(modelTest);

	  

  }
  
  
  
}
