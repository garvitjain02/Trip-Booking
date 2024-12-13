import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomBoxComponent } from './room-box.component';

describe('RoomBoxComponent', () => {
  let component: RoomBoxComponent;
  let fixture: ComponentFixture<RoomBoxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RoomBoxComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RoomBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
