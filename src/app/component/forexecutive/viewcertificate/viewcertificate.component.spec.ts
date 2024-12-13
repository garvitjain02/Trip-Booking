import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewcertificateComponent } from './viewcertificate.component';

describe('ViewcertificateComponent', () => {
  let component: ViewcertificateComponent;
  let fixture: ComponentFixture<ViewcertificateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewcertificateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewcertificateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
