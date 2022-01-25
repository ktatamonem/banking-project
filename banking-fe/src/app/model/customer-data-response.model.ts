import { Transaction } from "./transaction.model";

export class CustomerDataResponse{

    firstName : string  ;  
    
    lastName : string ;  

    balance : number  =0 ;  

    transactions:Transaction[];
    
}