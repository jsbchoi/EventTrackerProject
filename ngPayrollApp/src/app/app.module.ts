import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PayrollAppComponent } from './components/payroll-app/payroll-app.component';
import { PayrollService } from './services/payroll.service';

@NgModule({
  declarations: [
    AppComponent,
    PayrollAppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    PayrollService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
