import { Component, OnInit } from '@angular/core';
import { TinhDiemService } from '../../services/tinh-diem.service';

@Component({
  selector: 'app-tinh-diem',
  templateUrl: './tinh-diem.component.html',
  styleUrls: ['./tinh-diem.component.css']
})
export class TinhDiemComponent implements OnInit {
  dropdownList = [];
  selectedItems = [];
  dropdownSettings = {};
  listmon = [];
  displayedColumns: string[] = ['Type',  'Value', 'Remove'];
  constructor(private tinhDiemService: TinhDiemService) { }

  ngOnInit() {
    this.loadSelect();
  }
  reload() {
    this.tinhDiemService.loadSelects()
    .subscribe(c => { console.log(c);
        this.dropdownList = c;
       }
  );
  }
  loadSelect() {
    this.reload();
    this.selectedItems = [
    ];
    this.dropdownSettings = {
      singleSelection: false,
      text: 'Chọn môn học',
      selectAllText: 'Chọn hết',
      unSelectAllText: 'Bỏ hết',
      enableCheckAll: false,
      enableSearchFilter: false,
      classes: 'myclass custom-class'
    };
  }
  onItemSelect(item: any) {
    this.listmon = this.tinhDiemService.taoMon(item, this.listmon);
    console.log(this.listmon);
  }
  OnItemDeSelect(item: any) {
    this.listmon = this.tinhDiemService.huyMon(item, this.listmon);

  }

tao(item, hs) {
  item.listMart = this.tinhDiemService.taoDiem(item.listMart, hs, item.subjectId);
}
xoa(diem, item) {
  item.listMart = this.tinhDiemService.huyDiem(diem, item.listMart);

}

trungbinhmon(item) {
  let sum = 0;
  let div = 0;
item.listMart.forEach(element => {
  sum += element.markValue * element.markType;
  div += element.markType;
});
const n = sum / div;
item.average = Math.round(n * 1000) / 1000;
return item.average;
}
getColor(type) {

  switch (type) {
    case 1:
      return '#673AB7';
    case 2:
      return '#FBC02D';
      case 3:
      return '#F44336';
  }
}
}
