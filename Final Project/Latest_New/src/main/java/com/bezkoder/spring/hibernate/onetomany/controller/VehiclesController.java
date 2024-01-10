package com.bezkoder.spring.hibernate.onetomany.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.hibernate.onetomany.model.Vehicles;
import com.bezkoder.spring.hibernate.onetomany.exception.ResourceNotFoundException;
import com.bezkoder.spring.hibernate.onetomany.model.Requests;
import com.bezkoder.spring.hibernate.onetomany.model.Transporters;
import com.bezkoder.spring.hibernate.onetomany.model.Users;
import com.bezkoder.spring.hibernate.onetomany.repository.VehiclesRepository;
import com.bezkoder.spring.hibernate.onetomany.repository.RequestsRepository;
import com.bezkoder.spring.hibernate.onetomany.repository.TransportersRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class VehiclesController {
	
	@Autowired
	private TransportersRepository transportersrepository ;
	
	@Autowired
	private VehiclesRepository vehiclesrepository ;
	
	@Autowired
	private RequestsRepository requestsrepository ;
	
	@GetMapping("/vehicles")
	public List<Vehicles> findAll()
	{
		return vehiclesrepository.findAll();
	}

	@GetMapping("/transporters/{transporterid}/vehicles")
	  public ResponseEntity<List<Vehicles>> getAllCommentsByTutorialId(@PathVariable(value = "transporterid") int transporterid) {    
		Transporters transporters = transportersrepository.findById(transporterid)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Transporter with id = " + transporterid));

	    List<Vehicles> vehicles = new ArrayList<Vehicles>();
	    vehicles.addAll(transporters.getVehicles());
	    
	    return new ResponseEntity<>(vehicles, HttpStatus.OK);
	  }
	
	@GetMapping("/vehicle/active")
	public Optional<List<Vehicles>> getActiveVehicle(@RequestParam("username") String username) {
		Transporters transporters = transportersrepository.findByUsername(username);
		
		return vehiclesrepository.findActiveVehiclesTransporter(transporters.getUserId());
	  }
	
	@GetMapping("/vehicle/requested")
	public Optional<List<Vehicles>> getRequestedVehicle(@RequestParam("username") String username) {
		Transporters transporters = transportersrepository.findByUsername(username);
		
		return vehiclesrepository.findRequestedVehiclesTransporter(transporters.getUserId());
	  }
	
	@GetMapping("/vehicle/pending")
	public Optional<List<Vehicles>> getPendingVehicle(@RequestParam("username") String username) {
		Transporters transporters = transportersrepository.findByUsername(username);
		
		return vehiclesrepository.findPendingVehiclesTransporter(transporters.getUserId());
	 }
	
	@GetMapping("/vehicle/completed")
	public Optional<List<Vehicles>> getCompletedVehicle(@RequestParam("username") String username) {
		Transporters transporters = transportersrepository.findByUsername(username);
		
		return vehiclesrepository.findCompletedVehiclesTransporter(transporters.getUserId());
	 }
	
	@GetMapping("/vehicle/cancelled")
	public Optional<List<Vehicles>> getCancelledVehicle(@RequestParam("username") String username) {
		Transporters transporters = transportersrepository.findByUsername(username);
		
		return vehiclesrepository.findCancelledVehiclesTransporter(transporters.getUserId());
	 }
	
	 @GetMapping("/vehicles/allactive")
	 public List<Vehicles> findActiveVehicles()
		{
			return vehiclesrepository.findActiveVehicles();
		}
	 
	 
	 @GetMapping("/vehicles/allpending")
	 public List<Vehicles> findAllPendingVehicles()
		{
			return vehiclesrepository.findAllPendingVehicles();
		}
	 
	 
	 @GetMapping("/vehicles/allrequested")
	 public List<Vehicles> findAllRequestedVehicles()
		{
			return vehiclesrepository.findAllRequestedVehicles();
		}
	 
	 @GetMapping("/vehicles/allcompleted")
	 public List<Vehicles> findAllCompletedVehicles()
		{
			return vehiclesrepository.findAllCompletedVehicles();
		}
	 
	
	 
	 @GetMapping("/vehicles/requestedusers")
	 public List<Vehicles> findRequestedVehiclesUsers(@RequestParam("userid") int userid)
		{  
		 System.out.println(userid);
		   List<Vehicles> vehicles = new ArrayList<>();
		   List<Requests> lu = requestsrepository.findByUserId(userid);
			lu.forEach(req -> {
				
				vehicles.add(vehiclesrepository.findById(req.getVehicleid()).get());
				
			});
		   return vehicles;
		}
	 
	 @GetMapping("/vehicles/activeusers")
	 public List<Vehicles> findActiveVehiclesUsers(@RequestParam("userid") int userid)
		{  
		 System.out.println(userid);
		   List<Vehicles> vehicles = new ArrayList<>();
		   List<Requests> lu = requestsrepository.findActiveByUserId(userid);
			lu.forEach(req -> {
				
				vehicles.add(vehiclesrepository.findById(req.getVehicleid()).get());
				
			});
		   return vehicles;
		}
	 
	 @GetMapping("/vehicles/completedusers")
	 public List<Vehicles> findCompletedTripsUsers(@RequestParam("userid") int userid)
		{  
		 System.out.println(userid);
		   List<Vehicles> vehicles = new ArrayList<>();
		   List<Requests> lu = requestsrepository.findCompletedTripsByUserId(userid);
			lu.forEach(req -> {
				
				vehicles.add(vehiclesrepository.findById(req.getVehicleid()).get());
				
			});
		   return vehicles;
		}
	
	@GetMapping("/vehicles/{vehicleid}")
	  public ResponseEntity<Vehicles> getVehiclesByTransporterId(@PathVariable(value = "vehicleid") int vehicleid) {
		Vehicles vehicles = vehiclesrepository.findById(vehicleid)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with vehicleid = " + vehicleid));

	    return new ResponseEntity<>(vehicles, HttpStatus.OK);
	  }

	  @PostMapping("/transporters/{transporterid}/vehicles")
	  public ResponseEntity<Vehicles> createVehicles(@PathVariable(value = "transporterid") int transporterid,
	      @RequestBody Vehicles vehiclesRequest) {
		  vehiclesRequest.setStatus("2");
		  vehiclesRequest.setTransporterid(transporterid);
		  Vehicles vehicles = transportersrepository.findById(transporterid).map(transporters -> {
			  transporters.getVehicles().add(vehiclesRequest);
	      return vehiclesrepository.save(vehiclesRequest);
	    }).orElseThrow(() -> new ResourceNotFoundException("Not found Transporters with transporterid = " + transporterid));
        
	    return new ResponseEntity<>(vehicles, HttpStatus.CREATED);
	  }
	  
	  @PutMapping("/vehicles/{vehicleid}")
	  public ResponseEntity<Vehicles> updateVehicles(@PathVariable("vehicleid") int vehicleid, @RequestBody Vehicles vehiclesRequest) {
		  Vehicles vehicles = vehiclesrepository.findById(vehicleid)
	        .orElseThrow(() -> new ResourceNotFoundException("VehicleId " + vehicleid + "not found"));

		  vehicles.setVehiclenumber(vehiclesRequest.getVehiclenumber());
		  vehicles.setVehicletype(vehiclesRequest.getVehicletype());
		  vehicles.setFromlocation(vehiclesRequest.getFromlocation());
		  vehicles.setTolocation(vehiclesRequest.getTolocation());
		  vehicles.setStatus(vehiclesRequest.getStatus());
		  vehicles.setDriver1name(vehiclesRequest.getDriver1name());
		  vehicles.setDriver2name(vehiclesRequest.getDriver2name());
		  vehicles.setDriver1phone(vehiclesRequest.getDriver1phone());
		  vehicles.setDriver2phone(vehiclesRequest.getDriver2phone());
		  vehicles.setFare(vehiclesRequest.getFare());

	    return new ResponseEntity<>(vehiclesrepository.save(vehicles), HttpStatus.OK);
	  }
	  
	  @PutMapping("/vehicle_l/{vehicleid}")
	  public ResponseEntity<Vehicles> latestVehicles(@PathVariable("vehicleid") int vehicleid, @RequestParam("status") String vehiclesRequest) {
		  Vehicles vehicles = vehiclesrepository.findById(vehicleid)
	        .orElseThrow(() -> new ResourceNotFoundException("VehicleId " + vehicleid + "not found"));
	      System.out.println(vehiclesRequest);
		  vehicles.setStatus(vehiclesRequest);
		  

	    return new ResponseEntity<>(vehiclesrepository.save(vehicles), HttpStatus.OK);
	  }
	  
	  @PutMapping("/vehiclesstatus/{vehicleid}")
	  public ResponseEntity<Vehicles> updateVehiclestatus(@PathVariable("vehicleid") int vehicleid) {
		  Vehicles vehicles = vehiclesrepository.findById(vehicleid)
	        .orElseThrow(() -> new ResourceNotFoundException("VehicleId " + vehicleid + "not found"));

		  
		  vehicles.setStatus("3");
		  
	    return new ResponseEntity<>(vehiclesrepository.save(vehicles), HttpStatus.OK);
	  }
	  
	  @PutMapping("/vehiclesstatuscomplete/{vehicleid}")
	  public ResponseEntity<Vehicles> updateVehiclestatusCompleted(@PathVariable("vehicleid") int vehicleid) {
		  Vehicles vehicles = vehiclesrepository.findById(vehicleid)
	        .orElseThrow(() -> new ResourceNotFoundException("VehicleId " + vehicleid + "not found"));

		  
		  vehicles.setStatus("4");
		  
	    return new ResponseEntity<>(vehiclesrepository.save(vehicles), HttpStatus.OK);
	  }
	  
	  @PutMapping("/vehiclesstatusbacktopending/{vehicleid}")
	  public ResponseEntity<Vehicles> updateVehiclestatusPending(@PathVariable("vehicleid") int vehicleid) {
		  Vehicles vehicles = vehiclesrepository.findById(vehicleid)
	        .orElseThrow(() -> new ResourceNotFoundException("VehicleId " + vehicleid + "not found"));

		  
		  vehicles.setStatus("2");
		  
	    return new ResponseEntity<>(vehiclesrepository.save(vehicles), HttpStatus.OK);
	  }

	  @DeleteMapping("/vehicles/{vehicleid}")
	  public ResponseEntity<HttpStatus> deleteVehicles(@PathVariable("vehicleid") int vehicleid) {
		  vehiclesrepository.deleteById(vehicleid);

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	  
	  @DeleteMapping("/transporters/{transporterid}/vehicles")
	  public ResponseEntity<List<Vehicles>> deleteAllVehiclesOfTransporters(@PathVariable(value = "transporterid") int transporterid) {
		  Transporters transporters = transportersrepository.findById(transporterid)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Transporter with transporterid = " + transporterid));
	    
		transporters.removeVehicles();
		transportersrepository.save(transporters);
	    
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	
	/*@GetMapping("/vehicles")
	public List<Vehicles> findAll()
	{
		return vehiclesservice.findAll();
	}
    
    @GetMapping("/vehicles/{vehicleid}")
    public Vehicles getVehicles(@PathVariable int vehicleid)
    {
    	Vehicles theVehicles = vehiclesservice.findById(vehicleid);
    	if(theVehicles == null)
    	{
    		throw new RuntimeException("Vehicle Not found - "+vehicleid);
    	}
		return theVehicles;
    }
    
    @PostMapping("/vehicles")
    public Vehicles addVehicles(@RequestBody Vehicles theVehicles)
    {
    	theVehicles.setVehicleId(0);
    	vehiclesservice.save(theVehicles);
		return theVehicles;
    }
    
    @PutMapping("/vehicles")
    public Vehicles updateVehicles(@RequestBody Vehicles theVehicles)
    {
    	vehiclesservice.save(theVehicles);
		return theVehicles;
    }
    
    @DeleteMapping("/vehicles/{vehicleid}")
    public String deleteVehicles(@PathVariable int vehicleid)
    {
    	Vehicles tempVehicles = vehiclesservice.findById(vehicleid);
    	
    	if(tempVehicles == null)
    	{
    		//throw new RuntimeException("Employee id not found: "+employeeId);
    		return "Vehicle id not found: "+vehicleid ;
    	}
    	vehiclesservice.deleteById(vehicleid);
		return "Delete Vehicle ID - "+vehicleid;
    }
    
    @GetMapping("/vehicles/count")
    public int getCount()
    {
    	return vehiclesservice.getVehiclesCount();
    }
*/
}
