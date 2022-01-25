import { Transaction } from "./transaction.model";

export class CustomerDataResponse{

    firstName : string  ;  
    
    lastName : string ;  

    balance : number  ;  

    transactions:Transaction[]
    
}