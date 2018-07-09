import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';



import { UniversityComponent } from './university/university.component';

const routes: Routes = [
  { path: 'university', component: UniversityComponent }
];
@NgModule({
  exports: [ RouterModule ],
  imports: [ RouterModule.forRoot(routes) ],
})
export class AppRoutingModule {}