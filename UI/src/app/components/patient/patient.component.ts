import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {BehaviorSubject, take, tap} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";
import {ReportService} from "../../service/report.service";
import {Report} from "../../model/report";
import {Note} from "../../model/note.model";
import {NoteService} from "../../service/note.service";
import {RiskLevel} from "../../enum/RiskLevel.enum";

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {
  report!: Report;
  patientForm!: FormGroup;
  searchTerm!: string;
  saveForm!: FormGroup;
  editNoteForm!: FormGroup;
  notes!: Note[];
  patientId!: number
  // @ts-ignore
  dataSubject = new BehaviorSubject<Note[]>(null);
  oneNote !: Note;
  isLoad = false;
  date!: Date;
  page: number = 1;
  count: number = 0;
  tableSize: number = 5;
  readonly RiskLevel = RiskLevel;

  @ViewChild('addCancelbutton') addCancelbutton!: any;
  @ViewChild('editCancelbutton') editCancelbutton!: any;
  @ViewChild('cancelbutton') cancelbutton!: any;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private reportService: ReportService,
              private noteService: NoteService,
              private formBuilder: FormBuilder) {
    this.patientForm = this.formBuilder.group({
      id: [0],
      name: [null],
      age: [null],
      riskLevel: [null],
      gender: [null],
      address: [null],
      phone: [null]
    })
    this.saveForm = this.formBuilder.group({
      date: [null, [
        Validators.required]],
      note: [null, [Validators.required, Validators.minLength(5)]],

    })

  }

  ngOnInit(): void {

    this.patientId = +this.route.snapshot.params['id'];
    this.getNotes();
    this.getReportPatient();
  }

  getReportPatient() {

    this.reportService.getReportByPatientId(this.patientId).pipe(
      take(1),
      tap(event => {
        this.report = event;
        this.patientForm.setValue(
          {
            id: this.report.id,
            name: this.report.name,
            age: this.report.age,
            riskLevel: this.report.riskLevel,
            gender: this.report.gender,
            address: this.report.address,
            phone: this.report.phone
          })
      }, (err: HttpErrorResponse) => {
        if (err instanceof HttpErrorResponse) {
          this.router.navigate(['404'])
        }
      })).subscribe()
  }

  getNotes() {

    this.noteService.getNotes(this.patientId).pipe(take(1),
      tap(data => {
        this.notes = data;
        this.dataSubject.next(data);
        this.count = this.notes.length;
      })).subscribe()
  }

  onLoadDeleteForm(note: Note) {
    this.oneNote = note;
  }

  onSaveNote() {
    let note = this.saveForm.value
    note.patientId = this.patientId;
    this.noteService.saveNote(note).pipe(
      take(1),
      tap(data => {
        this.dataSubject.next([
          ...this.dataSubject.value, data]);
        this.notes = this.dataSubject.value;
        this.count = this.notes.length;
      })).subscribe()
    this.addCancelbutton.nativeElement.click();
    this.saveForm.reset();
  }

  onEditNote() {
    let note = this.editNoteForm.value;
    note.patientId = this.patientId;
    this.noteService.updateNote(note).pipe(
      take(1),
      tap(data => {
        this.dataSubject.next([
          ...this.dataSubject.value.filter((data => data.id !== note.id))]);
        this.notes = this.dataSubject.value;
        this.dataSubject.next([data,
          ...this.dataSubject.value]);
        this.notes = this.dataSubject.value;
        this.count = this.notes.length;
      })).subscribe()
    this.editCancelbutton.nativeElement.click();
    this.isLoad = false;
    this.editNoteForm.reset();
    this.getReportPatient();
  }

  doRemoveNote() {
    this.noteService.removeNote(this.oneNote).pipe(
      take(1),
      tap(note => {
        this.dataSubject.next([
          ...this.dataSubject.value.filter((note => note.id !== this.oneNote.id))]);
        this.notes = this.dataSubject.value;
        this.count = this.notes.length;
      })).subscribe()
    this.cancelbutton.nativeElement.click();
  }

  onLoadEditForm(note: Note) {
    this.isLoad = true
    this.editNoteForm = this.formBuilder.group({
      id: note.id,
      date: [note.date, [
        Validators.required]],
      note: [note.note, [Validators.required, Validators.minLength(5)]],

    })
  }


  onTableDataChange(event: any) {
    this.page = event;
  }
}
