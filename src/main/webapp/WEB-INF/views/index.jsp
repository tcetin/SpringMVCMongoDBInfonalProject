<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 
  - Author:Tahsin ÇETİN
  - Date:
  - @(#)
  - Description: 
  --%>

	
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<%
ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LeWdeYSAAAAAHfOyUAC7qO4F7ZbvOF1YyR1kQbP", "6LeWdeYSAAAAAKU2ocaWEIGZcE2rFqsWJ_eu1STY", false);
%>
<html>
	<head>
		<title>Spring MVC & MongoDB Infonal Project</title>
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

		<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		
		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
		
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		
		<!-- Optional theme -->
		<link rel="stylesheet" href="<c:url value="resources/css/main.css" />">
		
		<script src="<c:url value="resources/javascript/mask.js"/>"></script>
		
		<script src="<c:url value="resources/javascript/main.js"/>"></script>
		
	</head>
	<body> 


<h1 style="margin-left:400px;">Spring MVC  Infonal Project</h1>
<hr>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
         <div class="row-xs-8">
            <div class="main-box no-header clearfix">
                <div class="main-box-body clearfix">
                    <div class="table-responsive">
                        <table class="table user-list striped">
                            <thead>
                                <tr>
                                <th><span>First Name</span></th>
                                <th><span>Last Name</span></th>
								<th><span>Phone Number</span></th>
                                <th>&nbsp;</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="person" items="${personList}">
                                <tr>
                                    <td>
                                        <i class="fa fa-user"></i>
                                        <span class="label label-default">${person.name}</span>
                                    </td>
 
                                    <td>
                                        <span class="label label-default">${person.surname}</span>
                                    </td>
                                    <td>
                                      <span class="label label-default">${person.phoneNumber}</span>
                                    </td>
                                    <td style="width: 20%;">

                                        <a id="${person.id}" class="table-link edit"  data-toggle="modal" data-target="#basicModalEdit">
                                            <span class="fa-stack">
                                                <i class="fa fa-square fa-stack-2x"></i>
                                                <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
                                            </span>
                                        </a>
                                        <a id="${person.id}" class="table-link danger delete" data-toggle="modal" data-target="#confirm-delete">
                                            <span class="fa-stack">
                                                <i class="fa fa-square fa-stack-2x"></i>
                                                <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
                                            </span>
                                        </a>
                                        
                                    </td>
                                </tr>

                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            </div>
            <div class="row-xs-4">
                                       
                     <a href="#" class="btn btn-lg btn-success" data-toggle="modal" data-target="#basicModal" style="float:right">
                     <i class="fa fa-plus"></i> Add New Person
                     </a>
            
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
            <h4 class="modal-title" id="myModalLabel"><i class="fa fa-user"></i>  Add New Person</h4>
            </div>
            <div class="modal-body">
				<form class="form-horizontal" role="form" name="frm" id="frm" method="POST">
				  <div class="form-group">
				    <label class="control-label col-sm-3" for="name">First Name(*):</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="name" name="name" placeholder="Enter First Name" required>
				   	  <label id="stateName" class="label label-danger"></label>	
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label class="control-label col-sm-3" for="surname">Last Name(*):</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="surname" name="surname" placeholder="Enter Last Name" required>
				      <label id="stateSurName" class="label label-danger"></label>
				    </div>
				  </div>
				  
				   <div class="form-group">
				    <label class="control-label col-sm-3" for="phone">Phone Number:</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="phone" name="phone" placeholder="Enter Phone Number">
				      <label id="statePhone" class="label label-danger"></label>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label class="control-label col-sm-3" for="surname">Captcha(*):</label>
				    <div class="col-sm-9">
				     <%out.print(c.createRecaptchaHtml(null, null));%> 
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label class="control-label col-sm-3" for="surname"></label>
				    <div class="col-sm-9">
				     <label id="stateCaptcha" class="label label-danger"></label> 
				    </div>
				  </div>
					
					<div id="addResult"></div>

				</form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button id="save" name="save" type="button" class="btn btn-primary">Save </button>
           </div>
    </div>
  </div>
</div>

<!-- edit -->

<div class="modal fade" id="basicModalEdit" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
            <h4 class="modal-title" id="myModalLabel"><i class="fa fa-cog"></i>  Edit Person</h4>
            </div>
            <div class="modal-body">
				<form class="form-horizontal" role="form" name="frm" id="frm" method="POST">

		         <div id="editPerson">
		
				  <div class="form-group">
				    <label class="control-label col-sm-3" for="name">First Name(*):</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="ename" name="ename" value="${per.name}" required>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label class="control-label col-sm-3" for="esurname">Last Name(*):</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="esurname" name="esurname" placeholder="Enter Last Name" required>
				    </div>
				  </div>
				  
				   <div class="form-group">
				    <label class="control-label col-sm-3" for="ephone">Phone Number:</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="ephone" name="ephone" placeholder="Enter Phone Number">
				    </div>
				  </div>

				</div>
				
				</form>
				
				<div id="updateResult"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button id="update" name="update" type="button" class="btn btn-primary">Update </button>
           </div>
    </div>
  </div>
</div>

<!-- Delete Modal -->

    <div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
            
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">Confirm Delete</h4>
                </div>
            
                <div class="modal-body">
                    <p>You are about to delete this track, this procedure is irreversible.</p>
                    <p>Do you want to proceed?</p>
                    
                    <span id="deleteResult" style="margin-left:250px"></span>
                </div>
                
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-danger btn-ok" id="delete">Delete</a>
                </div>
            </div>
        </div>
    </div>


</body>	
</html>