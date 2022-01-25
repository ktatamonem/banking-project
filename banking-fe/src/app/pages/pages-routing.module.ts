import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { CreateAccountComponent } from "./create-account/create-account.component";
import { CustomerDetailsComponent } from "./customer-details/customer-details.component";
import { CustomerListComponent } from "./customer-list/customer-list.component";


const routes: Routes = [

    {
      path: "account/create",
      component: CreateAccountComponent,
    
    },
    {
        path: "customer",
        component: CustomerListComponent,
      
    },
    {
        path: "customer/details/:id",
        component: CustomerDetailsComponent,
      
    }
   
   
  ];
  
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class PagesRoutingModule { }
  