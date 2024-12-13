import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelSearchPageComponent } from './hotel-search-page.component';

describe('HotelSearchPageComponent', () => {
  let component: HotelSearchPageComponent;
  let fixture: ComponentFixture<HotelSearchPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HotelSearchPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HotelSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
