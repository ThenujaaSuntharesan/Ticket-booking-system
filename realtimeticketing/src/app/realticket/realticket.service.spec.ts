import { TestBed } from '@angular/core/testing';

import { RealticketService } from './realticket.service';

describe('RealticketService', () => {
  let service: RealticketService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RealticketService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
