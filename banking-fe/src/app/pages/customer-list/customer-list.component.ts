import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Customer } from '../../model/customer.model';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.scss']
})
export class CustomerListComponent implements OnInit {


  customerList:Customer[]=[] ; 

  constructor(private customerService:CustomerService , private router :Router , private toastr : ToastrService) { }

  ngOnInit(): void {

    this.customerService.retrieveCustomerList().subscribe(data => {
        this.customerList = data; 
    },()=> {
      this.toastr.error("An Error Has Been Occured , Could You Please try again later")

    })
  }

  goTo(id:number){
    this.router.navigate(["pages/customer/details/"+id])

  }

}
