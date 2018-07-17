import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SchoolsComponent } from './modules/schools/schools.component';
import { SchoolDetailComponent } from './modules/school-detail/school-detail.component';
import { MbtiComponent } from './modules/mbti/mbti.component';
import { MbtiDetailComponent } from './modules/mbti-detail/mbti-detail.component';

const routes: Routes = [
  { path: 'schools', component: SchoolsComponent },
  { path: 'school-detail/:id', component: SchoolDetailComponent },
  { path: 'mbti', component: MbtiComponent },
  { path: 'mbti-detail/:code', component: MbtiDetailComponent },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
