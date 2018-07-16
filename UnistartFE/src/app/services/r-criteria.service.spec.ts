import { TestBed, inject } from '@angular/core/testing';

import { RCriteriaService } from './r-criteria.service';

describe('RCriteriaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RCriteriaService]
    });
  });

  it('should be created', inject([RCriteriaService], (service: RCriteriaService) => {
    expect(service).toBeTruthy();
  }));
});
