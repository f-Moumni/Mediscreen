import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Note} from "../model/note.model";

const API = environment.Note_API;
const httpOptions = environment.httpOptions

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  constructor(private http: HttpClient) {
  }

  public getNotes(id:number): Observable<any> {
    return this.http.get<any>(API + `/note?patientId=${id}`, httpOptions);
  }

  public getNoteById(id :string): Observable<any> {
    return this.http.get<any>(API + `/note?id=${id}`,httpOptions);
  }

  public updateNote(note: Note): Observable<any> {
    return this.http.put<any>(API + `/note`, note, httpOptions);
  }

  public saveNote(note: Note): Observable<any> {
    return this.http.post<any>(API + `/note`, note, httpOptions);
  }

  public removeNote(note: Note): Observable<any> {
    return this.http.delete<any>(API + `/note?id=${note.id}`, httpOptions);
  }
}
