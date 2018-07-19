import { RateCriteria } from './rateCriteria';

class AverageRatePK {
    schoolId: number;
    criteriaId: number;
    constructor() { }
}

export class AverageRate {
    averageRatePK: AverageRatePK;
    rateCriteria: RateCriteria;
    constructor() { }
}