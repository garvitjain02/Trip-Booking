import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VendorHotelsComponent } from './vendor-hotels.component';

describe('VendorHotelsComponent', () => {
  let component: VendorHotelsComponent;
  let fixture: ComponentFixture<VendorHotelsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VendorHotelsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VendorHotelsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
