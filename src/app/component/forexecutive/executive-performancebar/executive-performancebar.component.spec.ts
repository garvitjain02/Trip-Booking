import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExecutivePerformancebarComponent } from './executive-performancebar.component';

describe('ExecutivePerformancebarComponent', () => {
  let component: ExecutivePerformancebarComponent;
  let fixture: ComponentFixture<ExecutivePerformancebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExecutivePerformancebarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExecutivePerformancebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
