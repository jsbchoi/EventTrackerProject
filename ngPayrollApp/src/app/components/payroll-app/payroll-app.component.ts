import { Component, OnInit } from '@angular/core';
import { PayrollService } from 'src/app/services/payroll.service';
import { Payroll } from 'src/app/models/payroll';

@Component({
  selector: 'app-payroll-app',
  templateUrl: './payroll-app.component.html',
  styleUrls: ['./payroll-app.component.css']
})
export class PayrollAppComponent implements OnInit {

  payrolls = [];
  newPayroll = new Payroll();
  editPayroll = new Payroll();
  records = null;
  showEditForm = false;

  constructor(private payrollService: PayrollService
  ) { }

  setEditPayroll(payroll) {
    this.editPayroll = Object.assign({}, payroll);
  }

  addPayroll() {
    this.payrollService.create(this.newPayroll).subscribe(
      data => {
        console.log(data);
        this.reloadPayroll();;
      },
      err => {
        console.error("Error in PayrollAppComponent.addPayroll");
        console.error(err);
      }
    )
    this.newPayroll = new Payroll();
  }

  updatePayroll() {
    this.payrollService.update(this.editPayroll).subscribe(
      () => {
        this.reloadPayroll();
      },
      err => {
        console.error("Error in PayrollAppComponent.updatePayroll")
        console.log(err);
      }
    )
    this.editPayroll = null;
    this.reloadPayroll();
    this.showEditForm = false;
  }

  deletePayroll(id: number) {
    this.payrollService.destroy(id).subscribe(
      () => {
        this.reloadPayroll();
      },
      err => {
        console.error("Error in PayrollAppComponent.deletePayroll")
        console.log(err);
      }
    );
  }



  reloadPayroll() {
    this.payrollService.index().subscribe(
      data => {
        this.payrolls = data;
      },
      err => {
        console.error("Error in PayrollAppComponent.reloadPayroll")
        console.error(err);
      }
    );
  }
  ngOnInit() {
    this.reloadPayroll();
  }

}
