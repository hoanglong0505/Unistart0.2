import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { HttpClientModule } from '@angular/common/http';
import { MatPaginatorModule } from '@angular/material/paginator';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatStepperModule} from '@angular/material/stepper';
import {MatListModule} from '@angular/material/list';
import {MatCheckboxModule} from '@angular/material/checkbox';
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
import { ReportComponent } from './modules/school-detail/info/report/report.component';
import { Constants } from './constanst';
import { MbtiComponent } from './modules/mbti/mbti.component';

import { BarRatingModule } from 'ngx-bar-rating';
<<<<<<< HEAD
import { UserProfileComponent } from './modules/user-profile/user-profile.component';
=======
import { MbtiDetailComponent } from './modules/mbti-detail/mbti-detail.component';
>>>>>>> c706fe57067f70be1fb89408b6214fb9c05b71e0

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
<<<<<<< HEAD
    UserProfileComponent,
=======
    MbtiDetailComponent,
>>>>>>> c706fe57067f70be1fb89408b6214fb9c05b71e0
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
    BarRatingModule

  ],
  providers: [WaitingBoxComponent, Constants],
  bootstrap: [AppComponent]
})
export class AppModule { }
