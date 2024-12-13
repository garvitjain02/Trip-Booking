import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VendorNavbarComponent } from './vendor-navbar.component';

describe('VendorNavbarComponent', () => {
  let component: VendorNavbarComponent;
  let fixture: ComponentFixture<VendorNavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VendorNavbarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VendorNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
