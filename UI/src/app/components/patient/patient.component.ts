import {Component, OnInit} from '@angular/core';
import {Patient} from "../../model/patient.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {PatientService} from "../../service/Patient.service";
import {take, tap} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {
  patient!: Patient;
  editForm!: FormGroup;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private patientService: PatientService,
              private formBuilder: FormBuilder) {
    this.editForm = this.formBuilder.group({
      id: [0],
      firstName: [null, [
        Validators.required,
        Validators.maxLength(25)]],
      lastName: [null, [
        Validators.required,
        Validators.maxLength(25)]],
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
    this.getPatient();
  }

  getPatient() {
    const patientId = +this.route.snapshot.params['id'];
    this.patientService.getPatientById(patientId).pipe(
      take(1),
      tap(event => {
        this.patient = event;
        this.editForm.setValue(
          {
            id: this.patient.id,
            firstName: this.patient.firstName,
            lastName: this.patient.lastName,
            birthdate: this.patient.birthdate,
            gender: this.patient.gender,
            address: this.patient.address,
            phone: this.patient.phone
          })
      }, (error: HttpErrorResponse) => {
        this.router.navigate(['404'])
      })).subscribe()
  }

  onEditPatient() {
    this.patientService.updatePatient(this.editForm.value).pipe(
      take(1),
      tap(p => {
          this.patient = p;
        }, (error: HttpErrorResponse) => {
          console.log(error.message)
        }
      )).subscribe()
  }

  onCancel() {
    this.getPatient();
  }
}
