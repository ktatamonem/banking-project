import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AuthGuard } from "../services/authentication.guard";
import { CreateAccountComponent } from "./create-account/create-account.component";
import { CustomerDetailsComponent } from "./customer-details/customer-details.component";
import { CustomerListComponent } from "./customer-list/customer-list.component";


const routes: Routes = [

    {
      path: "account/create",
      component: CreateAccountComponent,
      canActivate:[AuthGuard]

    
    },
    {
        path: "customer",
        component: CustomerListComponent,
        canActivate:[AuthGuard]

      
    },
    {
        path: "customer/details/:id",
        component: CustomerDetailsComponent,
        canActivate:[AuthGuard]
        
      
    }
   
   
  ];
  
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class PagesRoutingModule { }
  