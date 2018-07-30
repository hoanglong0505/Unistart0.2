import { Component, OnInit } from '@angular/core';
import { FormControl } from '../../../../node_modules/@angular/forms';
import { MatDialog } from '../../../../node_modules/@angular/material/dialog';
import { ScheduleService } from '../../services/schedule.service';
import { Class } from '../../model/class';
import { CalendarDialogComponent } from './calendar-dialog/calendar-dialog.component';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
  displayedColumns: string[] = ['className', 'session', 'control'];
  constructor(private dialog: MatDialog, private scheduleService: ScheduleService) { }
  listClass: Class[];
  chart: any[];
  ngOnInit() {
    this.reload();
  }
  reload() {
    this.scheduleService.loadListClass()
      .subscribe(c => {
      this.listClass = c;
        this.chart = this.scheduleService.loadChart(this.listClass);
      });
  }
  arrayOne(n: number): any[] {
    return Array(n);
  }
  createClass() {
    const dialogRef = this.dialog.open(CalendarDialogComponent, {
      width: '99%',
      height: '90%',
      data: '0'
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        if (result.className !== undefined) {
          this.scheduleService.createClass(result).subscribe(
            res => {
            }
          );
          this.reload();
        }
      } this.reload();
      console.log('reload');
    });
  }
  update(id) {
    const dialogRef = this.dialog.open(CalendarDialogComponent, {
      width: '99%',
      height: '90%',
      data: id
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        if (result.className !== undefined) {
          this.scheduleService.UpdateClass(result).subscribe(
            res => {
            }
          );
          this.reload();
        }
      } this.reload();
    });
  }

}
