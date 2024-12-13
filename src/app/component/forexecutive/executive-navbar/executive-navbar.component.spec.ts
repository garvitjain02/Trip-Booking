import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExecutiveNavbarComponent } from './executive-navbar.component';

describe('ExecutiveNavbarComponent', () => {
  let component: ExecutiveNavbarComponent;
  let fixture: ComponentFixture<ExecutiveNavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExecutiveNavbarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExecutiveNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
