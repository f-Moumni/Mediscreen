import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {PatientListComponent} from './components/patient-list/patient-list.component';
import {HttpClientModule} from "@angular/common/http";
import {Ng2SearchPipeModule} from "ng2-search-filter";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    PatientListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AppRoutingModule,
    Ng2SearchPipeModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
