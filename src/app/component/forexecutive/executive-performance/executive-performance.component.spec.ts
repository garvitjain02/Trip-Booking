import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExecutivePerformanceComponent } from './executive-performance.component';

describe('ExecutivePerformanceComponent', () => {
  let component: ExecutivePerformanceComponent;
  let fixture: ComponentFixture<ExecutivePerformanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExecutivePerformanceComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExecutivePerformanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
