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
  displayedColumns: string[] = ['className', 'session'];
  constructor(private dialog: MatDialog, private scheduleService: ScheduleService) { }
  listClass:  Class[];
  chart: any[];
  ngOnInit() {
    this.listClass = this.scheduleService.loadListClass(this.listClass);
    this.chart = this.scheduleService.loadChart(this.listClass);
  }
  arrayOne(n: number): any[] {
    return Array(n);
  }
  createClass() {
    const dialogRef = this.dialog.open(CalendarDialogComponent, {
      width: '95%',
      height: '80%',
      data: 'Tạo mới lịch học'
    });
    dialogRef.afterClosed().subscribe(result => {
    if (result !== undefined) {
      this.scheduleService.createClass(result).subscribe(
        res => {
        }
      );
    console.log(result);
  }

    });
  }

}
