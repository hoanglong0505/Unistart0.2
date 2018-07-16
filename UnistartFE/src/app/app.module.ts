import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { HttpClientModule } from '@angular/common/http';
import { MatPaginatorModule } from '@angular/material/paginator';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here

import { AppComponent } from './app.component';
import { NavMenuComponent } from './nav-menu/nav-menu.component';
import { AppRoutingModule } from './app-routing.module';
import { SchoolsComponent } from './schools/schools.component';
import { FilterSchoolComponent } from './schools/filter-school/filter-school.component';
import { SchoolDetailComponent } from './school-detail/school-detail.component';
import { InfoComponent } from './school-detail/info/info.component';
import { ReviewComponent } from './school-detail/review/review.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { WaitingBoxComponent } from './waiting-box/waiting-box.component';
import { DynamicTemplateComponent } from './dynamic-template/dynamic-template.component';
import { ReportComponent } from './school-detail/info/report/report.component';
import { Constants } from './constanst';

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
    FormsModule
  ],
  providers: [WaitingBoxComponent, Constants],
  bootstrap: [AppComponent]
})
export class AppModule { }
