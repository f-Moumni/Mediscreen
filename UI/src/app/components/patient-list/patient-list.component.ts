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
  searchTerm!: string;


  constructor(private router: Router, private patientService: PatientService) {
  }

  ngOnInit(): void {
    this.getPatients();
  }

  getPatients() {
    this.patientService.getPatients().subscribe(ps => {
      this.patients=ps;
    })
  }

  doRemove(patient: Patient) {

  }

  doUpdate(patient: Patient) {

  }

  onKeyUp(filterText : string){
    this.patients = this.patients.filter(item =>
      item.firstName.toLowerCase().includes(filterText));
  }
}
