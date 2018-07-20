import { Component, OnInit } from '@angular/core';
import { Lession } from '../../model/lession';
import { ScheduleDate } from '../../model/scheduleDate';
import { MatDialogConfig, MatDialog } from '../../../../node_modules/@angular/material/dialog';
import { ScheduleDialogComponent } from './schedule-dialog/schedule-dialog.component';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {
  lession: Lession;
  morningNum = 0;
  noonNum = 0;
 
  dialogResult;
  Monday = [this.lession];
  morningOpenState = false;
  noonOpenState = false;
  week: ScheduleDate[];
  constructor(private dialog: MatDialog) { }

  ngOnInit() {
    console.log(this.Monday);
    this.loadWeek();
  }
  loadWeek() {
this.week = [{'dateTitle': 'Thứ Hai', 'listLession': []},
{'dateTitle': 'Thứ Ba', 'listLession': []},
{'dateTitle': 'Thứ Tư', 'listLession': []},
{'dateTitle': 'Thứ Năm', 'listLession': []},
{'dateTitle': 'Thứ Sáu', 'listLession': []},
{'dateTitle': 'Thứ Bảy', 'listLession': []},
];
  }
  load() {
    if (this.morningOpenState === true) {
      for (let index = 0; index < this.morningNum ; index++) {
        const l = new Lession;
        this.Monday.push(l);
      }
      console.log(this.morningNum);
    }
  }
  arrayOne(n: number): any[] {
    return Array(n);
  }
  openDialog() {
    const dialogRef = this.dialog.open(ScheduleDialogComponent, {
      width: '600px',
      data: 'This text is passed into the dialog!'
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog closed: ${result}`);
      this.dialogResult = result;
    });
  }
}




