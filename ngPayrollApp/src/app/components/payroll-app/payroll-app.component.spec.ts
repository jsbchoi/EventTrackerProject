import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PayrollAppComponent } from './payroll-app.component';

describe('PayrollAppComponent', () => {
  let component: PayrollAppComponent;
  let fixture: ComponentFixture<PayrollAppComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PayrollAppComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PayrollAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
