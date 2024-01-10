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

import com.bezkoder.spring.hibernate.onetomany.model.Transporters;
import com.bezkoder.spring.hibernate.onetomany.model.Users;
import com.bezkoder.spring.hibernate.onetomany.exception.ResourceNotFoundException;
import com.bezkoder.spring.hibernate.onetomany.repository.UsersRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UsersController {
	
	@Autowired
	private UsersRepository usersepository ;
	
//	@GetMapping("/userlogin")
//	  public String userLogin(@RequestParam("username") String username,@RequestParam("password") String password)
//	  {
//		  Optional<Users> user = usersepository.findUser(username,password) ;
//		  if(user.isEmpty())
//			  return "User not found";
//		  else
//			  System.out.println(user.get());
//			  return "Success";
//	  }
	
	@GetMapping("/users")
	  public ResponseEntity<List<Users>> getAllUsers(@RequestParam(required = false) String fullname) {
	    List<Users> users = new ArrayList<Users>();

	    if (fullname == null)
	    	usersepository.findAll().forEach(users::add);
	    else
	    	usersepository.findByUsernameContaining(fullname).forEach(users::add);

	    if (users.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    return new ResponseEntity<>(users, HttpStatus.OK);
	  }

//	  @GetMapping("/users/{userid}")
//	  public ResponseEntity<Users> getUsersById(@PathVariable("userid") int userid) {
//		  Users users = usersepository.findById(userid)
//	        .orElseThrow(() -> new ResourceNotFoundException("Not found User with userid = " + userid));
//
//	    return new ResponseEntity<>(users, HttpStatus.OK);
//	  }
//	  
	  @GetMapping("/user")
	  public ResponseEntity<List<Users>> getUsersById(@RequestParam("username") String username) {
		  Users users = usersepository.findUser(username)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found User with username = " + username));
		  

		  List <Users> u = new ArrayList();
	      u.add (users);
		  return new ResponseEntity<>(u, HttpStatus.OK);
	  }

	  @PostMapping("/users")
	  public ResponseEntity<Users> createUsers(@RequestBody Users users) {
		  Users _users = usersepository.save(new Users(users.getUsername(), users.getPassword(),users.getUseremail(),users.getPhone(),users.getFulladdress(),"ROLE_USERS",users.isUserstatus()));
	    return new ResponseEntity<>(_users, HttpStatus.CREATED);
	  }

	  @PutMapping("/users/{userid}")
	  public ResponseEntity<Users> updateUsers(@PathVariable("userid") int userid, @RequestBody Users users) {
		  Users _users = usersepository.findById(userid)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userid));

//	    _users.setUsername(users.getUsername());
//	    _users.setPassword(users.getPassword());
	    _users.setUseremail(users.getUseremail());
	    _users.setPhone(users.getPhone());
	    _users.setFulladdress(users.getFulladdress());
	    
	    
	    return new ResponseEntity<>(usersepository.save(_users), HttpStatus.OK);
	  }

	  @DeleteMapping("/users/{userid}")
	  public ResponseEntity<HttpStatus> deleteUsers(@PathVariable("userid") int userid) {
		  usersepository.deleteById(userid);
	    
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }

	  @DeleteMapping("/users")
	  public ResponseEntity<HttpStatus> deleteAllUsers() {
		  usersepository.deleteAll();
	    
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }

	
	/*public UsersController(UsersService usersservice) {
		super();
		this.usersservice = usersservice;
	}
	
	@GetMapping("/users")
	public List<Users> findAll()
	{
		return usersservice.findAll();
	}
    
    @GetMapping("/users/{userid}")
    public Users getUsers(@PathVariable int userid)
    {
    	Users theUsers = usersservice.findById(userid);
    	if(theUsers == null)
    	{
    		throw new RuntimeException("User Not found - "+userid);
    	}
		return theUsers;
    }
    
    @PostMapping("/users")
    public Users addUsers(@RequestBody Users theUsers)
    {
    	theUsers.setUserId(0);
    	usersservice.save(theUsers);
		return theUsers;
    }
    
    @PutMapping("/users")
    public Users updateUsers(@RequestBody Users theUsers)
    {
    	usersservice.save(theUsers);
		return theUsers;
    }
    
    @DeleteMapping("/users/{userid}")
    public String deleteUsers(@PathVariable int userid)
    {
    	Users tempUsers = usersservice.findById(userid);
    	
    	if(tempUsers == null)
    	{
    		//throw new RuntimeException("Employee id not found: "+employeeId);
    		return "User id not found: "+userid ;
    	}
    	usersservice.deleteById(userid);
		return "Delete User ID - "+userid;
    }
	*/
	

}
