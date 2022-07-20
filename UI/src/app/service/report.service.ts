import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const API = environment.Assess_API;
const httpOptions = environment.httpOptions

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private http: HttpClient) {
  }

  public getReportByPatientId(id: number): Observable<any> {
    return this.http.get<any>(API + `/assess?patientId=${id}`, httpOptions);
  }
}
