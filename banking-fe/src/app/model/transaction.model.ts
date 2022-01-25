import { Account } from "./account.model";

export class Transaction {

    id:number;

    amount:number;  

    sourceAccount:Account; 

    destinationAccount:Account; 

    creationDate:Date ;  

    inCash:boolean; 

}