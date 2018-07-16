import { Branch } from '../model/branch';
import { EntranceInfo } from '../model/entranceInfo';
import { Rate } from '../model/rate';

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