import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-top-cards',
  templateUrl: './top-cards.component.html'
})
export class TopCardsComponent implements OnInit {

topcards:topcard[] = [] ;

@Input()
balance : number ;  

@Input()
nbTransactions : number;  


  constructor() { 

   
  }

  ngOnInit(): void {
   
    let balanceTopCard :any =  {
      bgcolor: 'danger',
      icon: 'bi bi-coin',
      title: '$'+ (this.balance ? this.balance :0),
      subtitle: 'Balance of all accounts'
  };  
  let transactionTopCard: any = {
    bgcolor: 'warning',
    icon: 'bi bi-basket3',
    title: (this.nbTransactions? this.nbTransactions :0),
    subtitle: 'Transactions'
  }
  this.topcards.push(balanceTopCard); 
  this.topcards.push(transactionTopCard); 
    
  }

}
export interface topcard {
  bgcolor: string,
  icon: string,
  title: string,
  subtitle: string
}