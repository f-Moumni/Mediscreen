import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PatientListComponent} from "./components/patient-list/patient-list.component";
import {PatientComponent} from "./components/patient/patient.component";

const routes: Routes = [
  // {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'patients', component: PatientListComponent},
  {path: 'patients/:id', component: PatientComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
