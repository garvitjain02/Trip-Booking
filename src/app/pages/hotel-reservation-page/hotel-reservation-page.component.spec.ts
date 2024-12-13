import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelReservationPageComponent } from './hotel-reservation-page.component';

describe('HotelReservationPageComponent', () => {
  let component: HotelReservationPageComponent;
  let fixture: ComponentFixture<HotelReservationPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HotelReservationPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HotelReservationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
