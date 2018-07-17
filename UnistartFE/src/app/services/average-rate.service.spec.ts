import { TestBed, inject } from '@angular/core/testing';

import { AverageRateService } from './average-rate.service';

describe('AverageRateService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AverageRateService]
    });
  });

  it('should be created', inject([AverageRateService], (service: AverageRateService) => {
    expect(service).toBeTruthy();
  }));
});
