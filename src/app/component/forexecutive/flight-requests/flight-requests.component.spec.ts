import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightRequestsComponent } from './flight-requests.component';

describe('FlightRequestsComponent', () => {
  let component: FlightRequestsComponent;
  let fixture: ComponentFixture<FlightRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FlightRequestsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FlightRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
