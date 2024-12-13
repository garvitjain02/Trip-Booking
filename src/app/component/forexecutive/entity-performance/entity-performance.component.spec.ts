import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntityPerformanceComponent } from './entity-performance.component';

describe('EntityPerformanceComponent', () => {
  let component: EntityPerformanceComponent;
  let fixture: ComponentFixture<EntityPerformanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EntityPerformanceComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EntityPerformanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
