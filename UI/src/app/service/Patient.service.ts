import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Patient} from "../model/patient.model";

const API = 'http://localhost:8081'
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) {
  }

  public getPatients(): Observable<any> {
    return this.http.get<any>(API + `/patient/all`, httpOptions);
  }

  public getPatientById(id :number): Observable<any> {
    return this.http.get<any>(API + `/patient?id=${id}`,httpOptions);
  }

  public updatePatient(patient: Patient): Observable<any> {
    return this.http.put<any>(API + `/patient`, patient, httpOptions);
  }

  public savePatient(patient: Patient): Observable<any> {
    return this.http.post<any>(API + `/patient`, patient, httpOptions);
  }

  public removePatient(patient: Patient): Observable<any> {
    return this.http.delete<any>(API + `/patient?id=${patient.id}`, httpOptions);
  }
}
