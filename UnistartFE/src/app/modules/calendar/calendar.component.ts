import { Component, OnInit } from '@angular/core';
import { FormControl } from '../../../../node_modules/@angular/forms';
import { MatDialog } from '../../../../node_modules/@angular/material/dialog';
import { ScheduleService } from '../../services/schedule.service';
import { Class } from '../../model/class';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
  constructor(private dialog: MatDialog, private scheduleService: ScheduleService) { }
  panelColor = new FormControl();
  dropdownList = [];
  selectedItems = [];
  dropdownSettings = {};
  mon: Class = new Class;
  ngOnInit() {
    this.mon.listSession = this.scheduleService.loadDay(this.selectedItems);
    this.loadSelect();
    console.log(this.mon);
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
    this.mon.listSession = this.scheduleService.loadDay(this.selectedItems);
    console.log(this.mon);
  }
  OnItemDeSelect(item: any) {
    this.mon.listSession = this.scheduleService.loadDay(this.selectedItems);
  }
  onSelectAll(items: any) {
    this.mon.listSession = this.scheduleService.loadDay(this.selectedItems);
  }
  onDeSelectAll(items: any) {
    this.mon.listSession = this.scheduleService.loadDay(this.selectedItems);
  }
}
