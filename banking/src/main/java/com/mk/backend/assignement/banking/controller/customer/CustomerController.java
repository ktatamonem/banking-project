package com.mk.backend.assignement.banking.controller.customer;

import com.mk.backend.assignement.banking.entities.customer.Customer;
import com.mk.backend.assignement.banking.exceptions.ObjectNotFoundException;
import com.mk.backend.assignement.banking.navigation.Navigation;
import com.mk.backend.assignement.banking.response.dto.CustomerDataResponse;
import com.mk.backend.assignement.banking.service.account.AccountService;
import com.mk.backend.assignement.banking.service.customer.CustomerService;
import org.apache.coyote.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(Navigation.CUSTOMER_API)
public class CustomerController {

    private final static Logger logger = LogManager.getLogger(CustomerController.class);

    private CustomerService customerService ;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomerDataResponse> retrieveCustomerData(@PathVariable @NotNull Long id ){
        logger.info("start retrieve customer data for id:{}",id);
        try {
            CustomerDataResponse   response = customerService.retrieveCustomerDateByCustomerId(id);
            return ResponseEntity.ok(response);
        } catch (ObjectNotFoundException e) {
          logger.error("customer with id: {} Not found ",e);
          return new ResponseEntity("Customer Not Found ", HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            logger.error("Exception raised while retrieveCustomerData with id {}", id , ex);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }


    }

    @GetMapping("/list")
    public ResponseEntity<List<Customer>> list(){
        logger.info("starting listing all customers ");
        try{
            List<Customer> customerList  = customerService.retrieveAllCustomers() ;
            return ResponseEntity.ok(customerList);
        }catch (Exception ex){
            logger.error("Exception  raised while retrieving all customers",ex);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }



}
