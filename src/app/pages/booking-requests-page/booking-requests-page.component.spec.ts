import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookingRequestsPageComponent } from './booking-requests-page.component';

describe('BookingRequestsPageComponent', () => {
  let component: BookingRequestsPageComponent;
  let fixture: ComponentFixture<BookingRequestsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookingRequestsPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookingRequestsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
