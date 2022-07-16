import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {PatientListComponent} from './components/patient-list/patient-list.component';
import {HttpClientModule} from "@angular/common/http";
import {Ng2SearchPipeModule} from "ng2-search-filter";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {PatientComponent} from './components/patient/patient.component';
import {HeaderComponent} from './components/header/header.component';
import {NotesComponent} from './components/notes/notes.component';
import {NgxPaginationModule} from "ngx-pagination";

@NgModule({
  declarations: [
    AppComponent,
    PatientListComponent,
    PatientComponent,
    HeaderComponent,
    NotesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AppRoutingModule,
    Ng2SearchPipeModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
