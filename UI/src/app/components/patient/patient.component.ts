import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {map, take} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";
import {ReportService} from "../../service/report.service";
import {Report} from "../../model/report";

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {
  report!: Report;
  patientForm!: FormGroup;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private reportService: ReportService,
              private formBuilder: FormBuilder) {
    this.patientForm = this.formBuilder.group({
      id: [0],
      name: [null],
      age: [null],
      riskLevel: [null],
      gender: [null],
      address: [null],
      phone: [null]
    })

  }

  ngOnInit(): void {
    this.getReportPatient();
  }

  getReportPatient() {
    const patientId = +this.route.snapshot.params['id'];
    this.reportService.getReportByPatientId(patientId).pipe(
      take(1),
      map(event => {
        this.report = event;
        this.patientForm.setValue(
          {
            id: this.report.id,
            name: this.report.name,
            age: this.report.age,
            riskLevel: this.report.riskLevel,
            gender: this.report.gender,
            address: this.report.address,
            phone: this.report.phone
          })
      }, (error: HttpErrorResponse) => {
        this.router.navigate(['404'])
      })).subscribe()
  }


}
