import {Component, OnInit, ViewChild} from '@angular/core';
import {Patient} from "../../model/patient.model";
import {Router} from "@angular/router";
import {PatientService} from "../../service/Patient.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BehaviorSubject, map, take} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {
  patients!: Patient [];
  searchTerm!: string;

  // @ts-ignore
  dataSubject = new BehaviorSubject<Patient[]>(null);
  saveForm!: FormGroup;
  patient !: Patient;

  @ViewChild('closebutton') closebutton!: any;
  @ViewChild('cancelbutton') cancelbutton!: any;

  constructor(private router: Router, private patientService: PatientService, private formBuilder: FormBuilder) {
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

  ngOnInit(): void {
    this.getPatients();

  }

  getPatients() {
    this.patientService.getPatients().pipe(
      take(1),
      map(ps => {
        this.patients = ps;
        this.dataSubject.next(ps)
        return ps
      }, (error: HttpErrorResponse) => {
        console.log(error);
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
      }, (error: HttpErrorResponse) => {
        console.log(error);
      })).subscribe()
    this.closebutton.nativeElement.click();
  }

  doRemovePatient() {
    this.patientService.removePatient(this.patient).pipe(
      take(1),
      map(p => {
        this.dataSubject.next([
          ...this.dataSubject.value.filter((p => p.id !== this.patient.id))]);
        this.patients = this.dataSubject.value;
      }, (error: HttpErrorResponse) => {
        console.log(error);
      })).subscribe()
    this.cancelbutton.nativeElement.click();
  }

  onLoaDeleteForm(patient: any) {
    this.patient = patient
  }
}
