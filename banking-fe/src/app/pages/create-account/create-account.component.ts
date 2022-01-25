import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CreateAccountRequest } from '../../model/create-account-request.model';
import { Customer } from '../../model/customer.model';
import { AccountService } from '../../services/account.service';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.scss']
})
export class CreateAccountComponent implements OnInit {

  customerList:Customer[]=[] ; 

  customer:Customer  ;  


  initialBalance:number=0; 

  constructor(private customerService:CustomerService , private accountService:AccountService , private toastr:ToastrService
    , private router : Router) { }

  ngOnInit(): void {

    this.customerService.retrieveCustomerList().subscribe(data => {
      this.customerList = data; 
    },(err)=>{
     
       this.toastr.error("An Error Has Been Occured , Could You Please try again later")
    })
  }


  create(){
    if(this.customer){
      let createAccountRequest  : CreateAccountRequest = new CreateAccountRequest()  ;  
      createAccountRequest.customerId = this.customer.id ;  
      createAccountRequest.initialBalance  =  this.initialBalance  ;
      console.log(this.initialBalance);  
      this.accountService.createAccount(createAccountRequest).subscribe(data  => {
         this.toastr.success("Account Has Been Created With Success"); 
         this.router.navigate(["/pages/customer"]);

      },(err)=>{
        console.log(err);
        if(err.status === 500 || err.status === 404){
          this.toastr.error("Error while creating account could you please try again");

        } else if(err.status === 400){
          this.toastr.error(err.error);
          
        }

      })
       
    }else {
      this.toastr.error("No Account has been selected")

    }

  }

}
