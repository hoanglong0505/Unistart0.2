import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SchoolsComponent } from './modules/schools/schools.component';
import { SchoolDetailComponent } from './modules/school-detail/school-detail.component';
import { MbtiComponent } from './modules/mbti/mbti.component';
import { UserProfileComponent } from './modules/user-profile/user-profile.component';
import { MbtiDetailComponent } from './modules/mbti-detail/mbti-detail.component';
import { ScheduleComponent } from './modules/schedule/schedule.component';
import { EditReviewComponent } from './modules/user-profile/edit-review/edit-review.component';
import { CalendarComponent } from './modules/calendar/calendar.component';
import { TinhDiemComponent } from './modules/tinh-diem/tinh-diem.component';

const routes: Routes = [
  { path: 'schools', component: SchoolsComponent },
  { path: 'school-detail/:id', component: SchoolDetailComponent },
  { path: 'personal/user-profile/:id', component: UserProfileComponent },
  { path: 'mbti', component: MbtiComponent },
  { path: 'mbti-detail/:code', component: MbtiDetailComponent },
  { path: 'schedule', component: ScheduleComponent },
  { path: 'personal/edit-review', component: EditReviewComponent },
  { path: 'calendar', component: CalendarComponent },
  { path: 'tinh-diem', component: TinhDiemComponent },
  { path: '', redirectTo: '/', pathMatch: 'full' }
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
