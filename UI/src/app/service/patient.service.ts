import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
const API = environment.AUTH_API;
const httpOptions =environment.httpOptions
@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) {
  }

  public getPatients(): Observable<any> {
    return this.http.get<any>(API + `/patient/all`, httpOptions);
  }
}
