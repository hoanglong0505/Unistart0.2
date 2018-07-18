import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SchoolsComponent } from './modules/schools/schools.component';
import { SchoolDetailComponent } from './modules/school-detail/school-detail.component';
import { MbtiComponent } from './modules/mbti/mbti.component';
<<<<<<< HEAD
import { UserProfileComponent } from './modules/user-profile/user-profile.component';
=======
import { MbtiDetailComponent } from './modules/mbti-detail/mbti-detail.component';
>>>>>>> c706fe57067f70be1fb89408b6214fb9c05b71e0

const routes: Routes = [
  { path: 'schools', component: SchoolsComponent },
  { path: 'school-detail/:id', component: SchoolDetailComponent },
  { path: 'user-profile/:id', component: UserProfileComponent },
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
