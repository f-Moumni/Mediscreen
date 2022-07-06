import {Component, OnInit} from '@angular/core';
import {Patient} from "../../model/patient.model";
import {Router} from "@angular/router";
import {PatientService} from "../../service/patient.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {BehaviorSubject, map, take} from "rxjs";

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {
  patients!: Patient [];
  searchTerm!: string;
  editForm!: FormGroup;
  isLoad = false;
  // @ts-ignore
  dataSubject = new BehaviorSubject<Patient[]>(null);
  saveForm!: FormGroup;
  patient !: Patient;

  constructor(private router: Router, private patientService: PatientService, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.getPatients();
    this.saveForm = this.formBuilder.group({
      firstName: [null],
      lastName: [null],
      birthdate: [null],
      gender: [null],
      address: [null],
      phone: [null]
    })
  }

  getPatients() {
    this.patientService.getPatients().pipe(
      take(1),
      map(ps => {
        this.patients = ps;
        this.dataSubject.next(ps)
        return ps
      })).subscribe()
  }


  onLoadEditForm(patient: Patient) {
    this.isLoad = true;
    this.editForm = this.formBuilder.group(patient);

  }


  onEditPatient() {
    let patient = this.editForm.value
    this.patientService.updatePatient(patient).pipe(
      take(1),
      map(p => {
        this.dataSubject.next([
          ...this.dataSubject.value.filter((p => p.id !== patient.id))]);
        this.dataSubject.next([
          ...this.dataSubject.value, p]);
        this.patients = this.dataSubject.value;
      })).subscribe()
  }

  onSavePatient() {
    let patient = this.saveForm.value
    this.patientService.savePatient(patient).pipe(
      take(1),
      map(p => {
        this.dataSubject.next([
          ...this.dataSubject.value, p]);
        this.patients = this.dataSubject.value;
      })).subscribe()
  }

  onLoadAddForm() {
    this.saveForm.reset();
  }
  onLoaDeleteForm(patient: Patient) {
    this.patient = patient;
  }

  doRemovePatient() {
    this.patientService.removePatient(this.patient).pipe(
      take(1),
      map(p => {
        this.dataSubject.next([
          ...this.dataSubject.value.filter((p => p.id !== this.patient.id))]);
        this.patients = this.dataSubject.value;
      })).subscribe()
  }
}
