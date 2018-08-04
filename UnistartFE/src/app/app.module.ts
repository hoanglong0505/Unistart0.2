import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { HttpClientModule } from '@angular/common/http';
import { MatPaginatorModule } from '@angular/material/paginator';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatStepperModule } from '@angular/material/stepper';
import { MatListModule } from '@angular/material/list';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { SlideshowModule } from 'ng-simple-slideshow';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here


import { AppComponent } from './app.component';
import { NavMenuComponent } from './modules/nav-menu/nav-menu.component';
import { AppRoutingModule } from './app-routing.module';
import { SchoolsComponent } from './modules/schools/schools.component';
import { FilterSchoolComponent } from './modules/schools/filter-school/filter-school.component';
import { SchoolDetailComponent } from './modules/school-detail/school-detail.component';
import { InfoComponent } from './modules/school-detail/info/info.component';
import { ReviewComponent } from './modules/school-detail/review/review.component';
import { SignInComponent } from './modules/sign-in/sign-in.component';
import { WaitingBoxComponent } from './modules/waiting-box/waiting-box.component';
import { DynamicTemplateComponent } from './modules/dynamic-template/dynamic-template.component';
import { ReportComponent } from './modules/report/report.component';
import { Constants } from './constanst';
import { MbtiComponent } from './modules/mbti/mbti.component';
import { MbtiDetailComponent } from './modules/mbti-detail/mbti-detail.component';

import { BarRatingModule } from 'ngx-bar-rating';
import { UserProfileComponent } from './modules/user-profile/user-profile.component';

// 19/7/2018 Quy
import { AngularMultiSelectModule } from 'angular2-multiselect-dropdown/angular2-multiselect-dropdown';
import { ReactiveFormsModule } from '@angular/forms';
import { EditReviewComponent } from './modules/user-profile/edit-review/edit-review.component';
import { ScheduleComponent } from './modules/schedule/schedule.component'; // <-- NgModel lives here

// 20/7/2018 Long
import { MatGridListModule } from '@angular/material/grid-list';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatDialogModule } from '@angular/material/dialog';
import { ScheduleDialogComponent } from './modules/schedule/schedule-dialog/schedule-dialog.component';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { CalendarComponent } from './modules/calendar/calendar.component';
import {MatSelectModule} from '@angular/material/select';
import { CalendarDialogComponent } from './modules/calendar/calendar-dialog/calendar-dialog.component';
import {MatTableModule} from '@angular/material/table';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { TinhDiemComponent } from './modules/tinh-diem/tinh-diem.component';
import { HomeComponent } from './modules/home/home.component';
import { SubjectComponent } from './modules/subject/subject.component';


@NgModule({
  declarations: [
    AppComponent,
    NavMenuComponent,
    SchoolsComponent,
    FilterSchoolComponent,
    SchoolDetailComponent,
    InfoComponent,
    ReviewComponent,
    SignInComponent,
    WaitingBoxComponent,
    DynamicTemplateComponent,
    ReportComponent,
    MbtiComponent,
    UserProfileComponent,
    MbtiDetailComponent,
    EditReviewComponent,
    ScheduleComponent,
    ScheduleDialogComponent,
    CalendarComponent,
    CalendarDialogComponent,
    TinhDiemComponent,
    HomeComponent,
    SubjectComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatCardModule,
    MatPaginatorModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    MatStepperModule,
    MatListModule,
    MatCheckboxModule,
    BarRatingModule,
    SlideshowModule,
    AngularMultiSelectModule,
    ReactiveFormsModule,
    MatGridListModule,
    MatExpansionModule,
    MatDialogModule,
    ReactiveFormsModule,
    MatAutocompleteModule,
    MatSelectModule,
    MatTableModule,
    MatSlideToggleModule,

  ],
  providers: [WaitingBoxComponent, Constants],
  bootstrap: [AppComponent],
  entryComponents: [ScheduleDialogComponent, CalendarDialogComponent]
})
export class AppModule { }
