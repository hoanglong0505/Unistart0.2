import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatCheckboxModule} from '@angular/material/checkbox';

import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { UniversityComponent } from './university/university.component';
import { MenuComponent } from './menu/menu.component';
import { ApiService } from './api.service';
import { UniversitytService } from './service/university.service';

@NgModule({
  declarations: [
    AppComponent,
    UniversityComponent,
    MenuComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    NoopAnimationsModule,
    AppRoutingModule,
    MatCardModule,
    MatButtonModule,
    MatCheckboxModule,
    HttpModule
  ],
  providers: [ApiService, UniversitytService],
  bootstrap: [AppComponent]
})
export class AppModule { }
