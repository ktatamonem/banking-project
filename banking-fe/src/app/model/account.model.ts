import { Customer } from "./customer.model";

export class Account {

    id:number ;  

    accountNumber:string  ;  

    balance:number;

    customer:Customer; 

    creationDate:Date  ;  

    lastUpdateDate:Date ;

}