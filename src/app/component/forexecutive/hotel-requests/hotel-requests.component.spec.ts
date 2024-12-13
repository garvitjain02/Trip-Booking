import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelRequestsComponent } from './hotel-requests.component';

describe('HotelRequestsComponent', () => {
  let component: HotelRequestsComponent;
  let fixture: ComponentFixture<HotelRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HotelRequestsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HotelRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
