import {Component, OnInit, ViewChild} from '@angular/core';
import {Patient} from "../../model/patient.model";
import {Router} from "@angular/router";
import {PatientService} from "../../service/Patient.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BehaviorSubject, take, tap} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";
import {Gender} from "../../enum/Gender.enum";

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
  editForm!: FormGroup;
  patient !: Patient;
  page: number = 1;
  count: number = 0;
  tableSize: number = 5;
  @ViewChild('cancelEditbutton') cancelEditbutton!: any;
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
      gender: [Gender.MASCULINE,
        Validators.required
      ],
      address: [null, Validators.minLength(5)],
      phone: [null, [
        Validators.minLength(10),
        Validators.maxLength(20)]]
    })

    this.editForm = this.formBuilder.group({
      id: [null],
      firstName: [null, [
        Validators.required,
        Validators.maxLength(24)]],
      lastName: [null, [
        Validators.required,
        Validators.maxLength(24)]],
      birthdate: [null, [
        Validators.required]],
      gender: [Gender.MASCULINE,
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
      tap(ps => {
        this.patients = ps;
        this.dataSubject.next(ps);
        this.count = this.patients.length;
      }, (error: HttpErrorResponse) => {
        console.log(error);
      })).subscribe()
  }


  onSavePatient() {
    let patient = this.saveForm.value
    this.patientService.savePatient(patient).pipe(
      take(1),
      tap(p => {
        this.dataSubject.next([
          ...this.dataSubject.value, p]);
        this.patients = this.dataSubject.value;
        this.count = this.patients.length;
      }, (error: HttpErrorResponse) => {
        console.log(error);
      })).subscribe()
    this.saveForm.reset();
    this.closebutton.nativeElement.click();
  }

  doRemovePatient() {
    this.patientService.removePatient(this.patient).pipe(
      take(1),
      tap(p => {
        this.dataSubject.next([
          ...this.dataSubject.value.filter((p => p.id !== this.patient.id))]);
        this.patients = this.dataSubject.value;
        this.count = this.patients.length;
      }, (error: HttpErrorResponse) => {
        console.log(error);
      })).subscribe()
    this.cancelbutton.nativeElement.click();
  }

  onLoaDeleteForm(patient: any) {
    this.patient = patient
  }

  onTableDataChange(event: any) {
    this.page = event;
  }

  onLoadUpdateForm(patient: any) {
    this.editForm.setValue(
      {
        id: patient.id,
        firstName: patient.firstName,
        lastName: patient.lastName,
        birthdate: patient.birthdate,
        gender: patient.gender,
        address: patient.address,
        phone: patient.phone
      })
  }

  onEditPatient() {
    let patient = this.editForm.value
    this.patientService.updatePatient(patient).pipe(
      take(1),
      tap(p => {
        this.dataSubject.next([
          ...this.dataSubject.value.filter((p => p.id !== patient.id))]);
        this.dataSubject.next([p,
          ...this.dataSubject.value]);
        this.patients = this.dataSubject.value;
      })).subscribe()
    this.cancelEditbutton.nativeElement.click();
  }
}
