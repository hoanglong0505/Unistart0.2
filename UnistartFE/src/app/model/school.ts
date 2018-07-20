import { Branch } from './branch';
import { EntranceInfo } from './entranceInfo';
import { Rate } from './rate';

export class School {
    schoolId: number;
    schoolName: string;
    schoolCode: string;
    website: string;
    phone: string;
    email: string;
    avatar: string;
    branchs: Branch[];
    entranceInfos: EntranceInfo[];
    rates: Rate[];

    constructor() { }

}