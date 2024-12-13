import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookingRequestsTableComponent } from './booking-requests-table.component';

describe('BookingRequestsTableComponent', () => {
  let component: BookingRequestsTableComponent;
  let fixture: ComponentFixture<BookingRequestsTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookingRequestsTableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookingRequestsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
