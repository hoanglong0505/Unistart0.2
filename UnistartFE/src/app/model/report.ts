import { ReportPK } from './reportPK';
import { Rate } from './rate';
import { Users } from './users';

export class Report {
    reportPK: ReportPK;
    rpContent: string;
    rate: Rate;
    user: Users;

    constructor() { }

}