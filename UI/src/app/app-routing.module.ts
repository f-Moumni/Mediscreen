import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PatientListComponent} from "./components/patient-list/patient-list.component";
import {PatientComponent} from "./components/patient/patient.component";
import {PageNotFoundComponent} from "./components/pagenotfound/page-not-found.component";
import {LandingPageComponent} from "./components/landing-page/landing-page.component";

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'patients', component: PatientListComponent},
  {path: 'patients/:id', component: PatientComponent},
  {path: '404', component: PageNotFoundComponent},
  {path: 'home', component: LandingPageComponent},
  {path: '**', redirectTo: '/404'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
