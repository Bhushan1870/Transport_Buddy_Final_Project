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

import com.bezkoder.spring.hibernate.onetomany.model.Comment;
import com.bezkoder.spring.hibernate.onetomany.model.FinalOrder;
import com.bezkoder.spring.hibernate.onetomany.model.Requests;
import com.bezkoder.spring.hibernate.onetomany.model.Transporters;
import com.bezkoder.spring.hibernate.onetomany.exception.ResourceNotFoundException;
import com.bezkoder.spring.hibernate.onetomany.model.Tutorial;
import com.bezkoder.spring.hibernate.onetomany.model.Vehicles;
import com.bezkoder.spring.hibernate.onetomany.repository.AdminRepository;
import com.bezkoder.spring.hibernate.onetomany.repository.FinalOrderRepository;
import com.bezkoder.spring.hibernate.onetomany.repository.RequestsRepository;
import com.bezkoder.spring.hibernate.onetomany.repository.TransportersRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class FinalOrderController {
	
	@Autowired
	private RequestsRepository requestsrepository ;
	
	@Autowired
	private AdminRepository adminrepository ;
	
	@Autowired
	private FinalOrderRepository finalorderrepository ;
	
	@Autowired
	private TransportersRepository transportersrepository ;
	
	@GetMapping("/requests/{requestid}/finalorder")
	  public ResponseEntity<List<FinalOrder>> getAllFinalOrderByRequestid(@PathVariable(value = "requestid") int requestid) {    
		Requests requests = requestsrepository.findById(requestid)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Request with orderid = " + requestid));

	    List<FinalOrder> finalorder = new ArrayList<FinalOrder>();
	    finalorder.addAll(requests.getFinalorder());
	    
	    return new ResponseEntity<>(finalorder, HttpStatus.OK);
	  }
	
	@GetMapping("/transporters/{userid}/finalorder")
	  public ResponseEntity<List<FinalOrder>> getAllFinalOrderByTransporterid(@PathVariable(value = "userid") int userid) {    
		Transporters transporters = transportersrepository.findById(userid)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Transporter with orderid = " + userid));

	    List<FinalOrder> finalorder = new ArrayList<FinalOrder>();
	    finalorder.addAll(transporters.getFinalorder());
	    
	    return new ResponseEntity<>(finalorder, HttpStatus.OK);
	  }
	
	 @GetMapping("/finalorder")
	  public ResponseEntity<List<FinalOrder>> getAllFinalOrder(@RequestParam(required = false) String theid) {
	    List<FinalOrder> finalorder = new ArrayList<FinalOrder>();

	    if (theid == null)
	    	finalorderrepository.findAll().forEach(finalorder::add);
	    else
	    	finalorderrepository.findByOrderid(theid).forEach(finalorder::add);

	    if (finalorder.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    return new ResponseEntity<>(finalorder, HttpStatus.OK);
	  }

	  @GetMapping("/finalorder/{orderid}")
	  public ResponseEntity<FinalOrder> getFinalOrderById(@PathVariable("orderid") int orderid) {
		  FinalOrder finalorder = finalorderrepository.findById(orderid)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found FinalOrder with Orderid = " + orderid));

	    return new ResponseEntity<>(finalorder, HttpStatus.OK);
	  }

	  @PostMapping("/finalorder")
	  public ResponseEntity<FinalOrder> createFinalOrder(@RequestBody FinalOrder finalorder) {
		 FinalOrder _finalorder = finalorderrepository.save(new FinalOrder(finalorder.isStatus(), finalorder.getAdminid(), finalorder.getTransporterid(),finalorder.getRequestid()));
	    return new ResponseEntity<>(_finalorder, HttpStatus.CREATED);
	  }


	  @PutMapping("/finalorder/{orderid}")
	  public ResponseEntity<FinalOrder> updateFinalOrder(@PathVariable("orderid") int orderid, @RequestBody FinalOrder finalorder) {
		  FinalOrder _finalorder = finalorderrepository.findById(orderid)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found FinalOrder with Orderid = " + orderid));

	    _finalorder.setStatus(finalorder.isStatus());
	    _finalorder.setAdminid(finalorder.getAdminid());
	    _finalorder.setTransporterid(finalorder.getTransporterid());
	    _finalorder.setRequestid(finalorder.getRequestid());
	    
	    return new ResponseEntity<>(finalorderrepository.save(_finalorder), HttpStatus.OK);
	  }

	  @DeleteMapping("/finalorder/{orderid}")
	  public ResponseEntity<HttpStatus> deleteFinalOrder(@PathVariable("orderid") int orderid) {
		  finalorderrepository.deleteById(orderid);
	    
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }

	  @DeleteMapping("/finalorder")
	  public ResponseEntity<HttpStatus> deleteAllFinalOrder() {
		  finalorderrepository.deleteAll();
	    
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }

	 /* @GetMapping("/tutorials/published")
	  public ResponseEntity<List<Tutorial>> findByPublished() {
	    List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

	    if (tutorials.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    return new ResponseEntity<>(tutorials, HttpStatus.OK);
	  }*/
	
	/*@GetMapping("/finalorder")
	public List<FinalOrder> findAll()
	{
		return finalorderservice.findAll();
	}
    
    @GetMapping("/finalorder/{orderid}")
    public FinalOrder getFinalOrder(@PathVariable int orderid)
    {
    	FinalOrder theFinalOrder = finalorderservice.findById(orderid);
    	if(theFinalOrder == null)
    	{
    		throw new RuntimeException("Order Not found - "+orderid);
    	}
		return theFinalOrder;
    }
    
    @PostMapping("/finalorder")
    public FinalOrder addFinalOrder(@RequestBody FinalOrder theFinalOrder)
    {
    	theFinalOrder.setOrderId(0);
    	finalorderservice.save(theFinalOrder);
		return theFinalOrder;
    }
    
    @PutMapping("/finalorder")
    public FinalOrder updateFinalOrder(@RequestBody FinalOrder theFinalOrder)
    {
    	finalorderservice.save(theFinalOrder);
		return theFinalOrder;
    }
    
    @DeleteMapping("/finalorder/{orderid}")
    public String deleteFinalOrder(@PathVariable int orderid)
    {
    	FinalOrder tempFinalOrder = finalorderservice.findById(orderid);
    	
    	if(tempFinalOrder == null)
    	{
    		//throw new RuntimeException("Employee id not found: "+employeeId);
    		return "Order id not found: "+orderid ;
    	}
    	finalorderservice.deleteById(orderid);
		return "Delete Order ID - "+orderid;
    }	
*/
}
