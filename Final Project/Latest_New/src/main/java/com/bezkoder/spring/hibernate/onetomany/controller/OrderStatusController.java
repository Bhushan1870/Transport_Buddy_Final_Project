package com.bezkoder.spring.hibernate.onetomany.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.hibernate.onetomany.exception.ResourceNotFoundException;
import com.bezkoder.spring.hibernate.onetomany.model.Comment;
import com.bezkoder.spring.hibernate.onetomany.model.FinalOrder;
import com.bezkoder.spring.hibernate.onetomany.model.OrderStatus;
import com.bezkoder.spring.hibernate.onetomany.model.Tutorial;
import com.bezkoder.spring.hibernate.onetomany.repository.FinalOrderRepository;
import com.bezkoder.spring.hibernate.onetomany.repository.OrderStatusRepository;
import com.bezkoder.spring.hibernate.onetomany.repository.TutorialRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class OrderStatusController {
	
	@Autowired
	private FinalOrderRepository finalorderrepository;
	
	@Autowired
	private OrderStatusRepository orderstatusrepository ;
	
	@GetMapping("/orderstatus")
	public List<OrderStatus> findAll()
	{
		return orderstatusrepository.findAll();
	}
	
	@GetMapping("/finalorder/{orderid}/orderstatus")
	  public ResponseEntity<List<OrderStatus>> getAllOrderStatusByOrderId(@PathVariable(value = "orderid") int orderid) {    
		FinalOrder finalorder = finalorderrepository.findById(orderid)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found FinalOrder with orderid = " + orderid));

	    List<OrderStatus> orderstatus = new ArrayList<OrderStatus>();
	    orderstatus.addAll(finalorder.getOrderstatus());
	    
	    return new ResponseEntity<>(orderstatus, HttpStatus.OK);
	  }

	  @GetMapping("/orderstatus/{id}")
	  public ResponseEntity<OrderStatus> getOrderStatusByOrderId(@PathVariable(value = "id") int id) {
		  OrderStatus orderstatus = orderstatusrepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found OrderStatus with id = " + id));

	    return new ResponseEntity<>(orderstatus, HttpStatus.OK);
	  }

	  @PostMapping("/finalorder/{orderid}/orderstatus")
	  public ResponseEntity<OrderStatus> createOrderStatus(@PathVariable(value = "orderid") int orderid,
	      @RequestBody OrderStatus orderstatusRequest) {
		  OrderStatus orderstatus = finalorderrepository.findById(orderid).map(finalorder -> {
			  finalorder.getOrderstatus().add(orderstatusRequest);
	      return orderstatusrepository.save(orderstatusRequest);
	    }).orElseThrow(() -> new ResourceNotFoundException("Not found FinalOrder with orderid = " + orderid));

	    return new ResponseEntity<>(orderstatus, HttpStatus.CREATED);
	  }

	  @PutMapping("/orderstatus/{id}")
	  public ResponseEntity<OrderStatus> updateOrderStatus(@PathVariable("id") int id, @RequestBody OrderStatus orderstatusRequest) {
		  OrderStatus orderstatus = orderstatusrepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Id " + id + "not found"));

		  orderstatus.setOrderid(orderstatusRequest.getOrderid());
		  orderstatus.setPickedup(orderstatusRequest.getPickedup());
		  orderstatus.setOntransit(orderstatusRequest.getOntransit());
		  orderstatus.setDelivered(orderstatusRequest.getDelivered());
		  orderstatus.setPickupdate(orderstatusRequest.getPickupdate());
		  orderstatus.setDeliverydate(orderstatusRequest.getDeliverydate());
		  

	    return new ResponseEntity<>(orderstatusrepository.save(orderstatus), HttpStatus.OK);
	  }

	  @DeleteMapping("/orderstatus/{id}")
	  public ResponseEntity<HttpStatus> deleteOrderStatus(@PathVariable("id") int id) {
		  orderstatusrepository.deleteById(id);

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	  
	  @DeleteMapping("/finalorder/{orderid}/orderstatus")
	  public ResponseEntity<List<OrderStatus>> deleteAllOrderStatusOfFinalOrder(@PathVariable(value = "orderid") int orderid) {
		  FinalOrder finalorder = finalorderrepository.findById(orderid)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found FinalOrder with orderid = " + orderid));
	    
		  finalorder.removeOrderstatus();
		  finalorderrepository.save(finalorder);
	    
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	
    /*
    @GetMapping("/orderstatus/{id}")
    public OrderStatus getOrderStatus(@PathVariable int id)
    {
    	OrderStatus theOrderStatus = orderstatusservice.findById(id);
    	if(theOrderStatus == null)
    	{
    		throw new RuntimeException("OrderStatus Not found - "+id);
    	}
		return theOrderStatus;
    }
    
    @PostMapping("/orderstatus")
    public OrderStatus addOrderStatus(@RequestBody OrderStatus theOrderStatus)
    {
    	theOrderStatus.setId(0);
    	orderstatusservice.save(theOrderStatus);
		return theOrderStatus;
    }
    
    @PutMapping("/orderstatus")
    public OrderStatus updateOrderStatus(@RequestBody OrderStatus theOrderStatus)
    {
    	orderstatusservice.save(theOrderStatus);
		return theOrderStatus;
    }
    
    @DeleteMapping("/orderstatus/{id}")
    public String deleteOrderStatus(@PathVariable int id)
    {
    	OrderStatus tempOrderStatus = orderstatusservice.findById(id);
    	
    	if(tempOrderStatus == null)
    	{
    		//throw new RuntimeException("Employee id not found: "+employeeId);
    		return "Id not found: "+id ;
    	}
    	orderstatusservice.deleteById(id);
		return "Delete ID - "+id;
    }	
*/
}
