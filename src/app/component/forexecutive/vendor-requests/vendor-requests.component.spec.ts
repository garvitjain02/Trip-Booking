import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VendorRequestsComponent } from './vendor-requests.component';

describe('VendorRequestsComponent', () => {
  let component: VendorRequestsComponent;
  let fixture: ComponentFixture<VendorRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VendorRequestsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VendorRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
