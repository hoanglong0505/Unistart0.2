import { Field } from '../model/field';
import { SubjectCombination } from '../model/subjectCombination';

export class EntranceInfo {
    entranceId: number;
    year: number;
    subName: string;
    subCode: string;
    normalEntranceAmount: number;
    otherEntranceAmount: number;
    minPoint: number;
    field: Field;
    subjectCombinations: SubjectCombination[];

    constructor() { }
}