import { Users } from '../model/users';
import { School } from '../model/school';
import { RateDetail } from '../model/rateDetail';
import { Report } from './report';

export class Rate {
    rateId: number;
    title: string;
    pros: string;
    cons: string;
    experience: string;
    encourage: boolean;
    anonymous: boolean;
    user: Users;
    school: School;
    rateDetails: RateDetail[];
    submitDateStr: string;
    reports: Report[] = new Array();
    averageValue: number;

    constructor() { }

}