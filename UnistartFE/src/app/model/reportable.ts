import { ReportComponent } from '../modules/report/report.component';
import { Rate } from './rate';

export abstract class ReportableComponent {
    reportComp: ReportComponent;
    constructor() { }

    protected turnOnReport(rate: Rate) {
        this.reportComp.rpRate = rate;
    }

}