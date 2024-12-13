import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExecutiveLogtableComponent } from './executive-logtable.component';

describe('ExecutiveLogtableComponent', () => {
  let component: ExecutiveLogtableComponent;
  let fixture: ComponentFixture<ExecutiveLogtableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExecutiveLogtableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExecutiveLogtableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
