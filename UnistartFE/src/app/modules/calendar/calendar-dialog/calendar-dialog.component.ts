import { Component, OnInit, Inject } from '@angular/core';
import { ScheduleService } from '../../../services/schedule.service';
import { FormControl } from '../../../../../node_modules/@angular/forms';
import { Class } from '../../../model/class';
import { MatDialogRef, MAT_DIALOG_DATA } from '../../../../../node_modules/@angular/material/dialog';
import { ScheduleDialogComponent } from '../../schedule/schedule-dialog/schedule-dialog.component';

@Component({
  selector: 'app-calendar-dialog',
  templateUrl: './calendar-dialog.component.html',
  styleUrls: ['./calendar-dialog.component.css']
})
export class CalendarDialogComponent implements OnInit {

  constructor(private scheduleService: ScheduleService    ,
    private dialogRef: MatDialogRef<CalendarDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data) { }

  panelColor = new FormControl();
  dropdownList = [];
  selectedItems = [];
  dropdownSettings = {};
  mon: Class = new Class;

  ngOnInit() {
    this.mon.sessionList = this.scheduleService.loadDay(this.selectedItems);
    this.loadSelect();
  }
  loadSelect() {
    this.dropdownList = [
      { 'id': 1, 'itemName': 'Thứ 2' },
      { 'id': 2, 'itemName': 'Thứ 3' },
      { 'id': 3, 'itemName': 'Thứ 4' },
      { 'id': 4, 'itemName': 'Thứ 5' },
      { 'id': 5, 'itemName': 'Thứ 6' },
      { 'id': 6, 'itemName': 'Thứ 7' },
      { 'id': 7, 'itemName': 'Chủ Nhật' }
    ];
    this.selectedItems = [
    ];
    this.dropdownSettings = {
      singleSelection: false,
      text: 'Chọn lịch hàng tuần',
      selectAllText: 'Chọn hết',
      unSelectAllText: 'Bỏ hết',
      enableSearchFilter: false,
      classes: 'myclass custom-class'
    };
  }
  onItemSelect(item: any) {
    this.mon.sessionList = this.scheduleService.loadDay(this.selectedItems);
  }
  OnItemDeSelect(item: any) {
    this.mon.sessionList = this.scheduleService.loadDay(this.selectedItems);
  }
  onSelectAll(items: any) {
    this.mon.sessionList = this.scheduleService.loadDay(this.selectedItems);
  }
  onDeSelectAll(items: any) {
    this.mon.sessionList = this.scheduleService.loadDay(this.selectedItems);
  }
  submit() {
    this.dialogRef.close(this.mon);
  }
}
