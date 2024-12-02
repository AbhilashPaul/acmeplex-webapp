import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnualFeePaymentComponent } from './annual-fee-payment.component';

describe('AnnualFeePaymentComponent', () => {
  let component: AnnualFeePaymentComponent;
  let fixture: ComponentFixture<AnnualFeePaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnnualFeePaymentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnnualFeePaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
