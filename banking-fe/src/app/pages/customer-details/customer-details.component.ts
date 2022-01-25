import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CustomerDataResponse } from '../../model/customer-data-response.model';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.scss']
})
export class CustomerDetailsComponent implements OnInit {
  subtitle: string;

  customerData : CustomerDataResponse ; 
  customerId : number  ;  
 

  
 
  constructor( private route: ActivatedRoute , private customerService : CustomerService  , private toastr : ToastrService) {
    this.subtitle = 'This is some text within a card block.';
  }
  ngOnInit(): void {
   this.route.paramMap.subscribe(params => {
    if (params.get('id')) {
     
      this.customerService.retrieveCustomerData(Number(params.get("id"))).subscribe(data => {
         this.customerData  = data; 
      },(err)=> {
         if(err.status === 500 || err.status === 404){
          this.toastr.error("An Error Has Been Occured , Could You Please try again later")
         }else if(err.status === 400){
           this.toastr.error(err.error)
         }
      })
    }
    })
   

    
  }

  

}
