import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectedRoomOverviewComponent } from './selected-room-overview.component';

describe('SelectedRoomOverviewComponent', () => {
  let component: SelectedRoomOverviewComponent;
  let fixture: ComponentFixture<SelectedRoomOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SelectedRoomOverviewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SelectedRoomOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
