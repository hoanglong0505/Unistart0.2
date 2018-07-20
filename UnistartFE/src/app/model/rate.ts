import { Users } from './users';
import { School } from './school';
import { RateDetail } from './rateDetail';
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