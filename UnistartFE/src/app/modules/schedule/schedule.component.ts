import { Component, OnInit } from '@angular/core';
import { Lession } from '../../model/lession';
import { ScheduleDate } from '../../model/scheduleDate';
import { MatDialogConfig, MatDialog } from '../../../../node_modules/@angular/material/dialog';
import { ScheduleDialogComponent } from './schedule-dialog/schedule-dialog.component';
import { ScheduleService } from '../../services/schedule.service';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {
  morningNum = 0;
  noonNum = 0;
  week: ScheduleDate[];
  week2: ScheduleDate[];
  constructor(private dialog: MatDialog, private scheduleService: ScheduleService) { }

  ngOnInit() {
    this.loadWeek();
  }
  loadWeek() {
this.week = this.scheduleService.createWeek(this.week);
this.week2 = this.scheduleService.createWeek(this.week2);
  }
  arrayOne(n: number): any[] {
    return Array(n);
  }

  openMorning( less, t) {
    less.title = t + ', buổi sáng, tiết ' + less.no  ;
    const dialogRef = this.dialog.open(ScheduleDialogComponent, {
      width: '70%',
      height: '70%',
      data: less
    });
    dialogRef.afterClosed().subscribe(result => {
    if (result !== undefined) {
      if (result.type !== undefined) {
      less.lessionName = result.name;
      less.note = result.note;
    }}

    });
  }

  openNoon( less, t) {
    less.title = t + ', buổi chiều, tiết ' + less.no  ;
    const dialogRef = this.dialog.open(ScheduleDialogComponent, {
      width: '70%',
      height: '70%',
      data: less
    });
    dialogRef.afterClosed().subscribe(result => {
    if (result !== undefined) {
      if (result.type !== undefined) {
      less.lessionName = result.name;
      less.note = result.note;
    }}

    });
  }
}




