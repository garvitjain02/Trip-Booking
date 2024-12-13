import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailedFlightPerformanceComponent } from './detailed-flight-performance.component';

describe('DetailedFlightPerformanceComponent', () => {
  let component: DetailedFlightPerformanceComponent;
  let fixture: ComponentFixture<DetailedFlightPerformanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailedFlightPerformanceComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailedFlightPerformanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
