import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailedCustomerPerformanceComponent } from './detailed-customer-performance.component';

describe('DetailedCustomerPerformanceComponent', () => {
  let component: DetailedCustomerPerformanceComponent;
  let fixture: ComponentFixture<DetailedCustomerPerformanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailedCustomerPerformanceComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailedCustomerPerformanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
