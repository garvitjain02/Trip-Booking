import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExecutiveSidebarComponent } from './executive-sidebar.component';

describe('ExecutiveSidebarComponent', () => {
  let component: ExecutiveSidebarComponent;
  let fixture: ComponentFixture<ExecutiveSidebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExecutiveSidebarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExecutiveSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
