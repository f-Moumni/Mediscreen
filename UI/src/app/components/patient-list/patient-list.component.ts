import {Component, OnInit} from '@angular/core';
import {Patient} from "../../model/patient.model";
import {Router} from "@angular/router";
import {PatientService} from "../../service/patient.service";
import {map, take} from "rxjs";

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {
  patients!: Patient [];

  constructor(private router: Router, private patientService: PatientService) {
  }

  ngOnInit(): void {
    this.getPatients();
  }

  getPatients() {
    this.patientService.getPatients().pipe(
      take(1),
      map(response => {
        this.patients = response.data.patients;
        return response.data.patients
      })
    ).subscribe()
  }

  doRemove(patient: Patient) {

  }

  doUpdate(patient: Patient) {

  }
}
