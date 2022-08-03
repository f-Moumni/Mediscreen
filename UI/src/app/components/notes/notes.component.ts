import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Note} from "../../model/note.model";
import {BehaviorSubject, take, tap} from "rxjs";
import {NoteService} from "../../service/note.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {
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


  @ViewChild('addCancelbutton') addCancelbutton!: any;
  @ViewChild('editCancelbutton') editCancelbutton!: any;
  @ViewChild('cancelbutton') cancelbutton!: any;


  constructor(private route: ActivatedRoute, private noteService: NoteService, private formBuilder: FormBuilder) {

    this.saveForm = this.formBuilder.group({

      date: [null, [
        Validators.required]],
      note: [null, [Validators.required, Validators.minLength(5)]],

    })
  }

  ngOnInit(): void {
    this.patientId = +this.route.snapshot.params['id'];
    this.getNotes(this.patientId)
  }

  getNotes(patientId: number) {
    this.noteService.getNotes(patientId).pipe(take(1),
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
