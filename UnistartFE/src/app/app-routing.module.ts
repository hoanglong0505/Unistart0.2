import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SchoolsComponent } from './modules/schools/schools.component';
import { SchoolDetailComponent } from './modules/school-detail/school-detail.component';
import { MbtiComponent } from './modules/mbti/mbti.component';
import { UserProfileComponent } from './modules/user-profile/user-profile.component';

const routes: Routes = [
  { path: 'schools', component: SchoolsComponent },
  { path: 'school-detail/:id', component: SchoolDetailComponent },
  { path: 'user-profile/:id', component: UserProfileComponent },
  { path: 'mbti', component: MbtiComponent },
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
