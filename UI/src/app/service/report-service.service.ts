import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
const API = environment.Patient_API;
const httpOptions = environment.httpOptions
@Injectable({
  providedIn: 'root'
})
export class ReportServiceService {

  constructor(private http: HttpClient) {
  }

  public getReportByPatientId(id :number): Observable<any> {
    return this.http.get<any>(API + `/assess?patientId=${id}`,httpOptions);
  }
}
