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

  constructor(private router: Router, private patientService: PatientService, private formBuilder: FormBuilder) {
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
      })).subscribe()
  }

  doRemove(patient: Patient) {

  }

  onKeyUp(filterText: string) {
    this.patients = this.patients.filter(item =>
      item.firstName.toLowerCase().includes(filterText));
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
}
