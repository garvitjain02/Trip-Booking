import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExecutiveRequestsComponent } from './executive-requests.component';

describe('ExecutiveRequestsComponent', () => {
  let component: ExecutiveRequestsComponent;
  let fixture: ComponentFixture<ExecutiveRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExecutiveRequestsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExecutiveRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
