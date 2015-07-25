package com.springinsert.spapp;


import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springinsert.spapp.model.Person;
import com.springinsert.spapp.service.PersonService;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

 /**
  * @author Tahsin
  */
@Controller    
public class PersonController {  

	@Autowired
	private PersonService personService;
	
	@Autowired
	ReCaptchaImpl reCaptcha;

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	

	/**
	 * getPersonList method lists recorded person(s).
	 * Also this method returns our index page.
	 * @param model
	 * @return
	 */
	
    @RequestMapping(value = {"/","/index.htm"}, method = RequestMethod.GET)  
	public String getPersonList(ModelMap model) {  
        model.addAttribute("personList", personService.listPerson()); 
        
        logger.info("Person lists is ready");
        
        return "index";  
    }  

    /**
     * ajaxAddPerson method mainly insert a document to MongoDB.
     * The method performs with request parameters which is sended with Ajax.
     * Also this method process Google recaptcha validation. 
     * @param person
     * @param model
     * @param name
     * @param surname
     * @param phoneNumber
     * @param challangeField
     * @param responseField
     * @param servletRequest 
     * @return
     */
    
    @RequestMapping(value = "/ajaxtest", method = RequestMethod.GET) 
    public @ResponseBody String ajaxAddPerson( 
    	   @ModelAttribute Person person, 
    	   ModelMap model,
    	   //@RequestParam(value="id") String id,
    	   @RequestParam(value="name") String name,
    	   @RequestParam(value="surname") String surname,
    	   @RequestParam(value="phone") String phoneNumber,
		   @RequestParam("recaptcha_challenge_field") String challangeField, 
		   @RequestParam("recaptcha_response_field") String responseField,
		   ServletRequest servletRequest) {
    	

    	String remoteAddress = servletRequest.getRemoteAddr();
    	ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(remoteAddress,challangeField, responseField);
    			
        String result = "";
        
        if(reCaptchaResponse.isValid()){
   
    		person.setName(name);
    		person.setSurname(surname);
    		person.setPhoneNumber(phoneNumber);
    		personService.addPerson(person);
    		result+="<span style='margin-left:180px;' class='label label-success'>"
    				+ "<i class=\"fa fa-check\"></i> The person has been inserted successfully!"
    				+"</span>";	

        
        model.addAttribute("personList", personService.listPerson());
        
        return result;
        
        }else{
        	
        	return  "<span style='margin-left:180px;' class='label label-danger'>"
    				+ "<i class=\"fa fa-exclamation-triangle\"></i> Answer is invalid! Please check your answer."
    				+"</span>";	
    		
        }
    	
 
    }

    
    /**
     * ajaxUpdatePerson method mainly updates a document into MongoDB with id which requested parameter.
     * The method performs with request parameters which is sended with Ajax.
     * @param person
     * @param model
     * @param id
     * @param name
     * @param surname
     * @param phoneNumber
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public @ResponseBody String ajaxUpdatePerson( 
    	   @ModelAttribute Person person, 
     	   ModelMap model,
     	   @RequestParam(value="id") String id,
     	   @RequestParam(value="name") String name,
     	   @RequestParam(value="surname") String surname,
     	   @RequestParam(value="phone") String phoneNumber) { 
    	
    	   String result = "";
    	   
           if(StringUtils.hasText(person.getId())) {
        	   
       		person.setName(name);
       		person.setSurname(surname);
       		person.setPhoneNumber(phoneNumber);
       		personService.updatePerson(person);
       		
       		logger.debug("ajaxUpdatePerson() is executed, value {}", "fc23fa90-4104-4379-b513-37d3c7072075,hh,hh,(666) 666-66-66");
       		
    		result+="<span style='margin-left:180px;' class='label label-success'>"
    				+ "<i class=\"fa fa-check\"></i> The person has been updated successfully!"
    				+"</span>";	
       		
       	}else{
       		result+="<span style='margin-left:180px;' class='label label-danger'>"
				+ "<i class=\"fa fa-exclamation-triangle\"></i> Update processing is failure!"
				+"</span>";	
       	}
           
    	   return result;
    }

    
    /**
     * ajaxDeletePerson method mainly deletes a document into MongoDB with id which requested parameter. 
     * @param person
     * @param model
     * @param id
     * @return
     */
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody String ajaxDeletePerson(
     	   @ModelAttribute Person person, 
     	   ModelMap model,
     	   @RequestParam(value="id") String id) {
    	   
    	   personService.deletePerson(person);
           
    	   return "The record has been deleted successfully!";
    }
    

    
    /**
     * ajaxEditPerson method mainly sends  a document fields to ajax request for update process.
     * @param person
     * @param model
     * @param id
     * @return
     */
    
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public @ResponseBody String ajaxEditPersonJSON(
           @ModelAttribute Person person, 
           ModelMap model,
           @RequestParam(value="id") String id) { 
    	
    	  person=personService.getPerson(id);

          return "<div class=\"form-group\">"+
				    "<label class=\"control-label col-sm-3\" for=\"name\">First Name(*):</label>"+
				    "<div class=\"col-sm-9\">"+
				      "<input type=\"text\" class=\"form-control\" id=\"ename\" name=\"ename\" value="+person.getName()+" required>"+
				    "</div>"+
				  "</div>"+
				  
				  "<div class=\"form-group\">"+
				    "<label class=\"control-label col-sm-3\" for=\"esurname\">Last Name(*):</label>"+
				    "<div class=\"col-sm-9\">"+
				      "<input type=\"text\" class=\"form-control\" id=\"esurname\" name=\"esurname\" value="+person.getSurname()+" required>"+
				    "</div>"+
				  "</div>"+
				  
				   "<div class=\"form-group\">"+
				    "<label class=\"control-label col-sm-3\" for=\"ephone\">Phone Number:</label>"+
				    "<div class=\"col-sm-9\">"+
				      "<input type=\"text\" class=\"form-control\" id=\"ephone\" name=\"ephone\" value="+person.getPhoneNumber()+">"+
				    "</div>"+
				  "</div>";
          		
    }
    
    
}
