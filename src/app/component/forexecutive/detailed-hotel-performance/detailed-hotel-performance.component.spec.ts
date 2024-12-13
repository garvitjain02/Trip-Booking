import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailedHotelPerformanceComponent } from './detailed-hotel-performance.component';

describe('DetailedHotelPerformanceComponent', () => {
  let component: DetailedHotelPerformanceComponent;
  let fixture: ComponentFixture<DetailedHotelPerformanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailedHotelPerformanceComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailedHotelPerformanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
