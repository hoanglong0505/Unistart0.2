import { Rate } from '../model/rate';

export class Users {
    userId: string;
    name: string;
    email: string;
    avatar: string;
    idToken: string;
    rates: Rate;
    
    constructor() { }
}