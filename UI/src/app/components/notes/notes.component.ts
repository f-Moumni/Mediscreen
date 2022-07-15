import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Note} from "../../model/note.model";
import {BehaviorSubject, map, take, tap} from "rxjs";
import {NoteService} from "../../service/NoteService";
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
  @ViewChild('addCancelbutton') addCancelbutton!: any;
  @ViewChild('editCancelbutton') editCancelbutton!: any;
  @ViewChild('cancelbutton') cancelbutton!: any;
  isLoad = false;


  constructor(private route: ActivatedRoute, private noteService: NoteService, private formBuilder: FormBuilder) {
    this.saveForm = this.formBuilder.group({
      date: [Date.now(), [
        Validators.required]],
      note: [null, [Validators.required, Validators.minLength(5)]],

    })
  }

  ngOnInit(): void {
    const patientId = +this.route.snapshot.params['id'];
    this.patientId = patientId;
    this.getNotes(patientId)
  }

  getNotes(patientId: number) {
    this.noteService.getNotes(patientId).pipe(take(1),
      tap(data => {
        this.notes = data;
        this.dataSubject.next(data)
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
      map(data => {
        this.dataSubject.next([
          ...this.dataSubject.value, data]);
        this.notes = this.dataSubject.value;
      })).subscribe()
    this.addCancelbutton.nativeElement.click();
  }

  onEditNote() {
    let note = this.editNoteForm.value;
    note.patientId = this.patientId;
    this.noteService.updateNote(note).pipe(
      take(1),
      map(data => {
        this.dataSubject.next([
          ...this.dataSubject.value.filter((data => data.id !== note.id))]);
        this.notes = this.dataSubject.value;
        this.dataSubject.next([data,
          ...this.dataSubject.value]);
        this.notes = this.dataSubject.value;
      })).subscribe()
    this.editCancelbutton.nativeElement.click();
    this.isLoad = false;
  }

  doRemoveNote() {
    this.noteService.removeNote(this.oneNote).pipe(
      take(1),
      map(note => {
        this.dataSubject.next([
          ...this.dataSubject.value.filter((note => note.id !== this.oneNote.id))]);
        this.notes = this.dataSubject.value;
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
}
