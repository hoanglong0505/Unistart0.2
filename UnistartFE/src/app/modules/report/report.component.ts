import { Component, OnInit, Input } from '@angular/core';
import { Rate } from '../../model/rate';
import { Report } from '../../model/report';
import { ReportPK } from '../../model/reportPK';
import { Users } from '../../model/users';
import { ReportableComponent } from '../../model/reportable';
import { WaitingBoxComponent } from '../waiting-box/waiting-box.component';
import { ReportService } from '../../services/report.service';
import { HttpRequest, Session } from '../../server/http';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  @Input() reportableComp: ReportableComponent;
  rpContent: string;
  rpRate: Rate;

  constructor(private rpService: ReportService) { }

  ngOnInit() {
    this.reportableComp.reportComp = this;
  }

  close() {
    this.rpRate = null;
    this.rpContent = null;
  }

  send() {
    // console.log(this.userRate);
    if (WaitingBoxComponent.time == -1) {
      if (this.rpRate) {
        WaitingBoxComponent.start();
        var rp = new Report();

        rp.rpContent = this.rpContent;
        rp.user = new Users();
        rp.user.idToken = new HttpRequest().getSession(true).getItem('gToken');
        rp.rate = this.rpRate;
        console.log(rp);

        this.rpService.addReport(rp).subscribe(
          res => {
            switch (res.status) {
              case 200:
                this.rpRate = null;
                this.rpContent = null;
                location.reload();
                break;
              default:
                window.alert(res.content);
            }
            WaitingBoxComponent.stop();
          }
        );

      }
    }
  }

}
