import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, FormControl } from '../../../../../node_modules/@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '../../../../../node_modules/@angular/material/dialog';
import { Observable } from '../../../../../node_modules/rxjs';
import { startWith, map } from '../../../../../node_modules/rxjs/operators';

@Component({
  selector: 'app-schedule-dialog',
  templateUrl: './schedule-dialog.component.html',
  styleUrls: ['./schedule-dialog.component.css']
})
export class ScheduleDialogComponent  implements OnInit  {
  form: FormGroup;
  title: '';
  name = new FormControl();
  options: string[] = ['Toán học', 'Ngữ văn', 'Sinh học', 'Vật lí', 'Hóa học', 'Lịch sử',
   'Địa Lý', 'Ngoại ngữ', 'Giáo dục quốc phòng', 'Thể dục', 'Công nghệ', 'Tin học' ];
  filteredOptions: Observable<string[]>;
  constructor(private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<ScheduleDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data) { }

    ngOnInit() {
      this.name.setValue(this.data.lessionName);
      this.filteredOptions = this.name.valueChanges
      .pipe(
        startWith(this.data.lessionName),
        map(value => this._filter(value))
      );
      this.title = this.data.title;
      this.form = this.formBuilder.group({
        note:  this.data.note
      });
    }
  submit(form) {
    form.value.type = 'submit';
    form.value.name = this.name.value;
    this.dialogRef.close(form.value);
  }
  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }
}
