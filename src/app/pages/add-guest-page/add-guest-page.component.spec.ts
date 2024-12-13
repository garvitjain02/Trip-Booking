import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddGuestPageComponent } from './add-guest-page.component';

describe('AddGuestPageComponent', () => {
  let component: AddGuestPageComponent;
  let fixture: ComponentFixture<AddGuestPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddGuestPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddGuestPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
