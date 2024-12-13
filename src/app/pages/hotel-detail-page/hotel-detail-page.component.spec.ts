import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelDetailPageComponent } from './hotel-detail-page.component';

describe('HotelDetailPageComponent', () => {
  let component: HotelDetailPageComponent;
  let fixture: ComponentFixture<HotelDetailPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HotelDetailPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HotelDetailPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
