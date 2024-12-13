import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelBoxComponent } from './hotel-box.component';

describe('HotelBoxComponent', () => {
  let component: HotelBoxComponent;
  let fixture: ComponentFixture<HotelBoxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HotelBoxComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HotelBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
