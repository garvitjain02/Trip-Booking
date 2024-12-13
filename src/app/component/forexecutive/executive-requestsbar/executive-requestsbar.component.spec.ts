import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExecutiveRequestsbarComponent } from './executive-requestsbar.component';

describe('ExecutiveRequestsbarComponent', () => {
  let component: ExecutiveRequestsbarComponent;
  let fixture: ComponentFixture<ExecutiveRequestsbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExecutiveRequestsbarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExecutiveRequestsbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
