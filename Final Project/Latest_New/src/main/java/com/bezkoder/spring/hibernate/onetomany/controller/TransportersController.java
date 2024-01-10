package com.bezkoder.spring.hibernate.onetomany.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.bezkoder.spring.hibernate.onetomany.exception.ResourceNotFoundException;
import com.bezkoder.spring.hibernate.onetomany.model.Transporters;
import com.bezkoder.spring.hibernate.onetomany.model.Tutorial;
import com.bezkoder.spring.hibernate.onetomany.model.Users;
import com.bezkoder.spring.hibernate.onetomany.repository.TransportersRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TransportersController {
	
	@Autowired
	TransportersRepository transportersrepository ;
	 
	 
	 @GetMapping("/transporter")
	  public ResponseEntity<List<Transporters>> getUsersById(@RequestParam("username") String username) {
		 Transporters transporters = transportersrepository.findTransporter(username)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found User with username = " + username));
      List <Transporters> u = new ArrayList();
      u.add (transporters);
	    return new ResponseEntity<>(u, HttpStatus.OK);
	  }
	 
	
	 @GetMapping("/transporters")
	  public ResponseEntity<List<Transporters>> getAllTransporters(@RequestParam(required = false) String transportername) {
	    List<Transporters> transporters = new ArrayList<Transporters>();

	    if (transportername == null)
	    	transportersrepository.findAll().forEach(transporters::add);
	    else
	    	transportersrepository.findByTransporternameContaining(transportername).forEach(transporters::add);

	    if (transporters.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    return new ResponseEntity<>(transporters, HttpStatus.OK);
	  }
	 
	 @GetMapping("/transporters/{transporterid}")
	  public ResponseEntity<Transporters> getTransportersById(@PathVariable("transporterid") int transporterid) {
		 Transporters transporters = transportersrepository.findById(transporterid)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Transporter with id = " + transporterid));

	    return new ResponseEntity<>(transporters, HttpStatus.OK);
	  }
	 
	 @PostMapping("/transporters")
	  public ResponseEntity<Transporters> createTransporters(@RequestBody Transporters transporters) {
		 System.out.println(transporters.getUsername());
		 Transporters _transporters = transportersrepository.save(new Transporters(transporters.getTransportername(), transporters.getEmail(), transporters.getPhone(),transporters.getPassword(),transporters.getUsername(),"ROLE_TRANSPORTER",transporters.getIsactive()));
	    return new ResponseEntity<>(_transporters, HttpStatus.CREATED);
	  }
	 
	 @PutMapping("/transporters/{transporterid}")
	  public ResponseEntity<Transporters> updateTransporters(@PathVariable("transporterid") int transporterid, @RequestBody Transporters transporters) {
		 Transporters _transporters = transportersrepository.findById(transporterid)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Transporter with id = " + transporterid));

	    _transporters.setTransportername(transporters.getTransportername());
	    _transporters.setEmail(transporters.getEmail());
	    _transporters.setPhone(transporters.getPhone());
//	    _transporters.setPassword(transporters.getPassword());
	    _transporters.setUsername(transporters.getUsername());
//	    _transporters.setIsactive(transporters.getIsactive());
	    
	    return new ResponseEntity<>(transportersrepository.save(_transporters), HttpStatus.OK);
	  }
	 
	 @DeleteMapping("/transporters/{transporterid}")
	  public ResponseEntity<HttpStatus> deleteTransporters(@PathVariable("transporterid") int transporterid) {
		 transportersrepository.deleteById(transporterid);
	    
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }

	  @DeleteMapping("/transporters")
	  public ResponseEntity<HttpStatus> deleteAllTransporters() {
		  transportersrepository.deleteAll();
	    
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	
	/*@GetMapping("/transporters")
	public List<Transporters> findAll()
	{
		return transportersservice.findAll();
	}
	
	@GetMapping("/transporters/{transporterid}")
    public Transporters getTransporters(@PathVariable int transporterid)
    {
		Transporters theTransporters = transportersservice.findById(transporterid);
    	if(theTransporters == null)
    	{
    		throw new RuntimeException("Transporters Not found - "+transporterid);
    	}
		return theTransporters;
    }
	
	@PostMapping("/transporters")
    public Transporters addTransporters(@RequestBody Transporters theTransporters)
    {
		theTransporters.setTransporterId(0);
    	transportersservice.save(theTransporters);
		return theTransporters;
    }
	
	@PutMapping("/transporters")
    public Transporters updateTransporters(@RequestBody Transporters theTransporters)
    {
		transportersservice.save(theTransporters);
		return theTransporters;
    }
	
	@DeleteMapping("/transporters/{transporterid}")
    public String deleteTransporters(@PathVariable int transporterid)
    {
		Transporters tempTransporters = transportersservice.findById(transporterid);
    	
    	if(tempTransporters == null)
    	{
    		//throw new RuntimeException("Employee id not found: "+employeeId);
    		return "Employee id not found: "+transporterid ;
    	}
    	transportersservice.deleteById(transporterid);
		return "Delete Transporter ID - "+transporterid;
    }
	*/

}
