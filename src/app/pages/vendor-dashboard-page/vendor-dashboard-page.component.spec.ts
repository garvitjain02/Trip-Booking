import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VendorDashboardPageComponent } from './vendor-dashboard-page.component';

describe('VendorDashboardPageComponent', () => {
  let component: VendorDashboardPageComponent;
  let fixture: ComponentFixture<VendorDashboardPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VendorDashboardPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VendorDashboardPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
