import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup } from '../../../../../node_modules/@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '../../../../../node_modules/@angular/material/dialog';

@Component({
  selector: 'app-schedule-dialog',
  templateUrl: './schedule-dialog.component.html',
  styleUrls: ['./schedule-dialog.component.css']
})
export class ScheduleDialogComponent  implements OnInit  {
  form: FormGroup;
  constructor(private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<ScheduleDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data) { }

    ngOnInit() {
      this.form = this.formBuilder.group({
        filename: this.data ? this.data.name : ''
      });
    }
  submit(form) {
    this.dialogRef.close(`${form.value.filename}`);
  }
}
