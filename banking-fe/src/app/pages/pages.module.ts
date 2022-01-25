import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { PagesRoutingModule } from './pages-routing.module';
import { FormsModule } from '@angular/forms';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { TopCardsComponent } from './customer-details/top-cards/top-cards.component';



@NgModule({
  declarations: [
    CustomerListComponent,
    CreateAccountComponent,
    CustomerDetailsComponent,
    TopCardsComponent
  ],
  imports: [
    CommonModule, 
    PagesRoutingModule,
    FormsModule
  ]
})
export class PagesModule { }
