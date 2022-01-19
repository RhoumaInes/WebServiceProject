package rs.ressources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import rs.entities.Employe;

@Path("employe")
@Api
public class GestionEmploye {
	
	public static  List<Employe> employes=new ArrayList<Employe>();
	
	
		
	public GestionEmploye() {
		super();
		//employes.add(new Employe(123,"Ahmed","Rhouma"));
	}

	
	
	@Path("liste")
	@GET
	//@Produces(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_XML)
	@ApiOperation(value="Get all employees")
	@ApiResponses({
		@ApiResponse(code=200, message="Success")
	})
	
	public Response displayEmployeesList() {
		
		if(employes.size()!=0) {
			GenericEntity data = new GenericEntity<List<Employe>>(employes){};
			return Response.status(200).entity(data).build();
		}
		else
			return Response.status(200).entity("liste vide").build();
					
	}
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value="Ajout Employee")
	@ApiResponses({
		@ApiResponse(code=200, message="Success")
	})
	public Response ajouterEmploye(Employe employe) {
		 if(employes.add(employe))
			 return Response.status(201).entity("Add Successful").build();
		return Response.status(201).entity("Echec: Error").build();
	  
		
	}
	
	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value="Update Employee")
	@ApiResponses({
		@ApiResponse(code=200, message="Success")
	})
	public Response updateEmploye(Employe e) {
		int index= this.getIndexByCin(e.getCin());
		if (index!=-1) {
			employes.set(index, e);
			return Response.status(201).entity("update Successful").build();
			
		}
		return Response.status(200).entity("Echec: Error").build();
	
	}
	
	@ApiOperation(value="Get index of Employee par CIN")
	@ApiResponses({
		@ApiResponse(code=200, message="Success")
	})
	public int getIndexByCin(int cin) {
		for(Employe emp: employes) {
			if (emp.getCin()==cin)
				return employes.indexOf(emp);
		}
		return -1;
	}
	
	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value="Delete Employee")
	@ApiResponses({
		@ApiResponse(code=200, message="Success")
	})
	public Response deleteEmpl(@PathParam(value="id") int cin){
		int index= getIndexByCin(cin);
		
		if (index!=-1) {
			employes.remove(index);
			return Response.status(200).entity("delete Successful").build();
		}else 
			return Response.status(200).entity("delete error").build();
			
    }

			
	 
	@GET
	@Path("getEmp/{id}")
	//@Produces(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_XML)
	@ApiOperation(value="Get Employee par CIN")
	@ApiResponses({
		@ApiResponse(code=200, message="Success")
	})
	public Response getEmploye(@PathParam(value="id") int cin) {
		for (Employe info:employes) {
	       if(info.getCin()==cin) {
	    	   return  Response.status(200).entity(info).build(); 
	    	
	       }
		}	
		return Response.status(200).entity("No emp with this id").build();
	}
	
		
}
