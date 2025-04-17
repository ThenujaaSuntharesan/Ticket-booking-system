import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RealticketComponent } from './realticket.component';

describe('RealticketComponent', () => {
  let component: RealticketComponent;
  let fixture: ComponentFixture<RealticketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RealticketComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RealticketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
