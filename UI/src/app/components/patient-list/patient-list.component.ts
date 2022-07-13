import {Component, OnInit} from '@angular/core';
import {Patient} from "../../model/patient.model";
import {Router} from "@angular/router";
import {PatientService} from "../../service/patient.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BehaviorSubject, map, take} from "rxjs";

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {
  patients!: Patient [];
  searchTerm!: string;
  isLoad = false;
  // @ts-ignore
  dataSubject = new BehaviorSubject<Patient[]>(null);
  saveForm!: FormGroup;
  patient !: Patient;
  firstName: Boolean | any;

  constructor(private router: Router, private patientService: PatientService, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.getPatients();
    this.saveForm = this.formBuilder.group({
      firstName: [null, [
        Validators.required,
        Validators.maxLength(24)]],
      lastName: [null, [
        Validators.required,
        Validators.maxLength(24)]],
      birthdate: [null, [
        Validators.required]],
      gender: [null,
        Validators.required
      ],
      address: [null, Validators.minLength(5)],
      phone: [null, [
        Validators.minLength(10),
        Validators.maxLength(20)]]
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
