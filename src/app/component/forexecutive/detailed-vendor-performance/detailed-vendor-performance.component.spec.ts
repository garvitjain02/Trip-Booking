import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailedVendorPerformanceComponent } from './detailed-vendor-performance.component';

describe('DetailedVendorPerformanceComponent', () => {
  let component: DetailedVendorPerformanceComponent;
  let fixture: ComponentFixture<DetailedVendorPerformanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailedVendorPerformanceComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailedVendorPerformanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
